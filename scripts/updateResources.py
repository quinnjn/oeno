import os, fnmatch
import shutil

def find(pattern, path):
    result = []
    for root, dirs, files in os.walk(path):
        for name in files:
            if fnmatch.fnmatch(name, pattern):
                result.append(os.path.join(root, name))
    return result


toFolder = '../app/src/main/assets'
fromFolder = '../../oenopass/oeno/Resources'

jsonFiles = find('*.json', fromFolder+'/Data')
wineryImages = find('*.jpg', fromFolder+'/Images/wineries')
otherImages = find('*.jpg', fromFolder+'/Images/wineries')

images = wineryImages + otherImages

print jsonFiles
print wineryImages
print otherImages

files = jsonFiles + images

for fromFile in files:
	toFile = fromFile.replace(fromFolder, toFolder)
	toFile = toFile.replace("@2x", "")

	print "mv " + fromFile + " " + toFile
	print shutil.copyfile(fromFile, toFile)