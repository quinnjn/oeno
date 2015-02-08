from PIL import Image
from PIL import ImageFilter
import sys

inFile = sys.argv[1]
outFile = inFile.replace(".jpg", "-blur.jpg")

img = Image.open(inFile)
blurred_img = img
for i in xrange(10):
	blurred_img = blurred_img.filter(ImageFilter.BLUR)
blurred_img.save(outFile, "JPEG")
