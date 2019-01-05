#if conditions can be like 

if __name__ == "__main__": #it will run as a main file if its imported somewhere
    l = [1,2,3,4]
    idx = 0
    for item in l:
        l[idx] = item*2
        idx = idx + 1
    print(l)

    for index,val in enumerate(l):   #Returns a tuple (index,val) once size is fixed cannot be modified.
                                     #append unlike list will not work -> l.append
        l[index] = val*2
        print("Item"+str(l[index]))  #Converting Integer to String

    print(list(map(lambda x: x*2, l))) #Wont replace the original list

    print(l)

def my_sum(a,b):
    return a+b

