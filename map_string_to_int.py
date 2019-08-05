import sys
import pickle
import json


#save_obj et load_obj sont les fonctions principales pour sauvegarder et loader un dictionnaire
def save_obj(obj, name):
    with open('dict/' + name + '.pkl', 'wb') as f:
        pickle.dump(obj, f, 0)

def load_obj(name):
    with open('dict/' + name + '.pkl', 'rb') as f:
        return pickle.load(f)

# lis le fichier et remplis une liste avec les différents lieux des séjours
def readSpotsFile(file):
    listSpots = []
    with open(file, encoding = "utf8") as f: # ouverture du fichier
        content = f.read().splitlines() # on split à chaque ligne
        for item in content:
            for x in item.split('\n'):
                listSpots.append(x.split(', '))
    return listSpots

# fonction pour mapper chaque String en un Int unique
def mapToInt(file, country):
    d = {}
    seen = {}
    listSpots = readSpotsFile(file)
    index = 1

    # dès qu'un lieu est "vu" et s'il ne l'a pas déjà été, on lui attribue un index
    for items in listSpots:
        for word in items:
            if word not in seen:
                seen[word] = index
                index += 1

    # on remplit une liste avec les différents lieux de la BDD
    finalList = []
    for transaction in listSpots:
        tmpList = []
        for spot in transaction:
            tmpList.append(seen[spot])
        finalList.append(tmpList)
    print(finalList)

    # remplissage du nouveau fichier avec les index des lieux séparés d'un espace dans un séjour
    newMap = open("test/Russia.txt", "w", encoding = "utf8")
    for tmp in finalList:
        tmpIndex = 0
        for spot in tmp:
            tmpIndex += 1
            if(tmpIndex == len(tmp)):
                newMap.write(str(spot))
            else:
                newMap.write(str(spot) + " ")
        newMap.write("\n")

    # Pas important juste au cas où le dictionnaire ne fonctionne pas
    dictionary = open("test/Russiamap.txt", "w", encoding = "utf8")
    for key, value in seen.items():
        tmp = "key : &{}&, value : \"{}\" \n".format(key, value)
        dictionary.write(tmp)
    dictionary.close()
    #Pickles le dictionnaire afin de le sauvegarder et pouvoir le réutiliser
    save_obj(seen, 'dicospotsRussiaFrance')

def printSavedDico(dico):
    d = load_obj(dico)
    print(d)

if(__name__ == "__main__"):
         country = 'Russia'
         file = 'RussiainFrance.txt'
         mapToInt(file, country)
