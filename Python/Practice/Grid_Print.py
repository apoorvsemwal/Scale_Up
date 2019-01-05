def print_grid(rows , cols):
	while(rows > 0):
		temp_str1 = '+------'*cols+'+'
		print temp_str1
		temp_cols = cols
		while(temp_cols > 0):
			temp_str2 = ('|'+" "*6)*cols+'|'
			print temp_str2
			temp_cols = temp_cols - 1
		rows = rows - 1
	temp_str1 = '+------'*cols+'+'
	print temp_str1
	exit_string = raw_input('Press Return to exit.\n')

def ask_input():
        rows = raw_input('How many rows do you want?\n')
        rows = int(rows)
        cols = raw_input('How many coloumns do you want?\n')
        cols = int(cols)
        print_grid(rows, cols)

ask_input()        
        
        
