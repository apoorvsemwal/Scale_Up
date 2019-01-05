import urllib2
import webbrowser
import os
import sys
def disp_page():
	req = urllib2.Request('http://belspp.iscodom.com:51600/irj/portal')
	response = urllib2.urlopen(req)
	the_page = response.read()
	print the_page

	
def call_url():
	webbrowser.open('http://belspp.iscodom.com:51600/irj/portal')

	
def disp_prop():
	print sys.platform
	print os.statrtfile

	
