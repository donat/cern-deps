import os
import sys
import fileinput

def sourceFiles():
	fileList = []
	rootdir = "."
	for root, subFolders, files in os.walk(rootdir):
		for file in files:
			if file.endswith('.java'):
				nf = os.path.join(root,file)
				fileList.append(nf)
	return fileList

def removeLicenseHeader(sourceFile): 
	## read the content of the file
	f = open(sourceFile, 'r')
	content = f.readlines()
	
	# write if a license header is present
	headerEnd = -1
	
	if content[0].startswith("/*") and content[1].startswith(" * "):
		for i in range(len(content)):
			if "*/" in content[i]:
				headerEnd = i
				break

	# write back if needed
	if headerEnd > 0:
		df = open(sourceFile,'w')
		for i in range(headerEnd+1, len(content)):
			df.write(content[i])
			
#print sourceFiles()	
for sf in sourceFiles(): 
	removeLicenseHeader(sf)