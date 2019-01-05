def rot_encry(text_rot, rot_num):
	lv_rot_text = text_rot
	fin_rot_text = ''
	for char_alpha in lv_rot_text:
		lv_ascii = int(ord(char_alpha))
		lv_char  = chr(lv_ascii)
		if ( lv_ascii >= 65 and lv_ascii <= 90 ):
			lv_ascii = lv_ascii + rot_num
			while ( lv_ascii > 90 ):
				lv_extra = lv_ascii - 90
				lv_ascii = 64 + lv_extra
			lv_char  = chr(lv_ascii)
			while ( lv_ascii < 65 ):
				lv_extra = 65 - lv_ascii
				lv_ascii = 91 - lv_extra
			lv_char  = chr(lv_ascii)			

		elif ( lv_ascii >= 97 and lv_ascii <= 122 ):
			lv_ascii = lv_ascii + rot_num
			while ( lv_ascii > 122 ):
				lv_extra = lv_ascii - 122
				lv_ascii = 96 + lv_extra
			lv_char  = chr(lv_ascii)
			while ( lv_ascii < 97 ):
				lv_extra = 97 - lv_ascii
				lv_ascii = 123 - lv_extra
			lv_char  = chr(lv_ascii)			
		fin_rot_text = fin_rot_text + lv_char
	print 'Your Encrypted text is:'
	print fin_rot_text
	return fin_rot_text

def rot_decrypt(text_rot, rot_num):
	lv_rot_text = text_rot
	fin_rot_text = ''
	for char_alpha in lv_rot_text:
		lv_ascii = int(ord(char_alpha))
		lv_char  = chr(lv_ascii)
		if ( lv_ascii >= 65 and lv_ascii <= 90 ):
			lv_ascii = lv_ascii - rot_num
			while ( lv_ascii > 90 ):
				lv_extra = lv_ascii - 90
				lv_ascii = 64 + lv_extra
			lv_char  = chr(lv_ascii)
			while ( lv_ascii < 65 ):
				lv_extra = 65 - lv_ascii
				lv_ascii = 91 - lv_extra
			lv_char  = chr(lv_ascii)			
		elif ( lv_ascii >= 97 and lv_ascii <= 122 ):
			lv_ascii = lv_ascii - rot_num
			while ( lv_ascii < 97 ):
				lv_extra = 97 - lv_ascii
				lv_ascii = 123 - lv_extra
			lv_char  = chr(lv_ascii)
			while ( lv_ascii > 122 ):
				lv_extra = lv_ascii - 122
				lv_ascii = 96 + lv_extra
			lv_char  = chr(lv_ascii)			
		fin_rot_text = fin_rot_text + lv_char
	print 'Your Decrypted text is:'
	print fin_rot_text
	return

def ask_input():
	lv_input = raw_input('Enter your text to encrypt.\n')
	return lv_input

def ask_rot_factor():
	lv_rot_fact = raw_input('Enter rotation factor to encrypt.\n')
	return lv_rot_fact


def main():
	lv_input = raw_input('Enter your text to encrypt.\n')
	if( len(lv_input) <= 0 ):
		while ( len(lv_input) <= 0 ):
			print 'Blank Texts are not accepted!\n'
			lv_input = ask_input()
		
	lv_rot_fact = raw_input('Enter a rotation factor to encrypt.\n')
	if( len(lv_rot_fact) <= 0 ):
		while ( len(lv_rot_fact) <= 0 ):
			print 'Invalid rotation factor to encode!\n'
			lv_rot_fact = ask_rot_factor()
	fin_rot_text = rot_encry(str(lv_input), int(lv_rot_fact))
	lv_que = raw_input('Do you wish to decrypt it back? (Yes/No) \n')
	lv_ans = str(lv_que).upper()
	if( lv_ans == 'YES' ):
		rot_decrypt(str(fin_rot_text), int(lv_rot_fact))
		print 'Thanks for using.'
	else:
		print 'Thanks for using.'

