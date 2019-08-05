import sys
import numpy
import pickle
import json


#save_obj et load_obj sont les fonctions principales pour sauvegarder et loader un dictionnaire
def save_obj(obj, name):
    with open('dict/' + name + '.pkl', 'wb') as f:
        pickle.dump(obj, f, 0)

def load_obj(name):
    with open('dict/' + name + '.pkl', 'rb') as f:
        return pickle.load(f)

def is_number(a):
    # will be True also for 'NaN'
    try:
        number = float(a)
        return True
    except ValueError:
        return False


#Fonction pour load le dictionnaire et faire la mise en forme du nouveau fichier décodé
def decodePatternsv2(fname, country, nb):
    integerRules = []
    rules = open('result' + nb + '.txt', "w", encoding = "utf8")
    referenceDico = load_obj('dicospotsRussiaFrance')
    with open(fname, encoding = "utf8") as f:
        content = f.read().splitlines()
        for item in content:
            stringRules = []
            stringLeftSide = []
            stringRightSide = []
            pat = []

            tmp = item.split(' -1 #SUP')

            pattern = tmp[0].split(' -1 ')

            for number in pattern:
                pat.append(list(referenceDico.keys())[list(referenceDico.values()).index(int(number))] + ',')
            rules.write("{} #SUP: {}\n".format(', '.join(pat), tmp[1]))

def decodeitemsets(fname):
    integerRules = []
    rules = open('resultconv.txt', "w", encoding = "utf8")
    referenceDico = load_obj('dicospotsRussiaFrance')
    with open(fname, encoding = "utf8") as f:
        content = f.read().splitlines()
        for item in content:
            stringRules = []
            stringLeftSide = []
            stringRightSide = []
            pat = []

            tmp = item.split(' #SUP')

            pattern = tmp[0].split(' ')

            for number in pattern:
                pat.append(list(referenceDico.keys())[list(referenceDico.values()).index(int(number))] + ',')
            rules.write("{} #SUP: {}\n".format(', '.join(pat), tmp[1]))

def decodeRulesv2(fname, country, nb):
    integerRules = []
    rules = open('result' + nb + '.txt', "w", encoding = "utf8")
    referenceDico = load_obj('dicospotsRussiaFrance')
    with open(fname, encoding = "utf8") as f:
        content = f.read().splitlines()
        for item in content:
            stringRules = []
            stringLeftSide = []
            stringRightSide = []
            pat = []

            tmp = item.split(' ==> ')
            leftSide = tmp[0].split(' ')
            rightSide = tmp[1].split(' #SUP')


            separatedleft = leftSide[0].split(',')
            separatedright = rightSide[0].split(',')


            for number in separatedleft:
                stringLeftSide.append(list(referenceDico.keys())[list(referenceDico.values()).index(int(number))] + ',')
            for number in separatedright:
                stringRightSide.append(list(referenceDico.keys())[list(referenceDico.values()).index(int(number))] + ',')
            rules.write("{}  ==> {} #SUP: {}\n".format(', '.join(stringLeftSide), ' '.join(stringRightSide), rightSide[1]))


if(__name__ == "__main__"):
    country = 'Russia'
    decodeitemsets('resultat.txt')
    print("Done ! ")
