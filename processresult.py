import os

def process(file):
    with open(file, 'r', encoding="utf8") as read_file :
        filedata = read_file.read()
        # Replace the target string
        filedata = filedata.replace(',,', ',')
        filedata= filedata.replace(',  ==>', '  ==>')
        filedata= filedata.replace(', #SU', ' #SU')
        filedata= filedata.replace(',  #SU', ' #SU')
        filedata= filedata.replace('  #SU', ' #SU')
        filedata= filedata.replace('#SUP: :', '#SUP:')
	    # Write the file out again
        with open(file, 'w', encoding = "utf8") as file:
            file.write(filedata)

if(__name__ == "__main__"):
    process('resultatconv.txt') 
print("Done ! ")
