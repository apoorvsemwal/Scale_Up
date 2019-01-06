from urllib.request import urlopen
import webbrowser
import os
import sys
def disp_page():
	req = urllib.Request('http://www.google.com')
	response = urllib.urlopen(req)
	the_page = response.read()
	print (the_page)

	
def call_url():
	webbrowser.open('http://www.google.com')

	
def disp_prop():
	print (sys.platform)
	print (os.statrtfile)

