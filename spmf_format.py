import os

def spmf_format(file):
    with open(file, 'r', encoding="utf8") as read_file :
        filedata = read_file.read()
        # Replace the target string
        filedata = filedata.replace('\n', ' -2\n')
        filedata= filedata.replace(' ', ' -1 ')
	    # Write the file out again
        with open(file, 'w', encoding = "utf8") as file:
            file.write(filedata)

if(__name__ == "__main__"):
    spmf_format('Russia.txt')
print("Done ! ")
