import itertools as it
import collections

print("Hello Apoorv")

game = [0 for i in range(3)]
print(game)
#game = [[0 for i in range(3)]  for i in range(3)] #List Comprehension

test_tuple1 = "C++", "Java", "Python"
test_tuple2 = ("C#", "R", "Scala")
print(type(test_tuple1))
print(type(test_tuple2))
# Above both are valid ways to define a tuple
# Tuples are imutable cant add or change whereas items in a list can be deleted added
# So use tuple when you want safety and are sure that the contents will not be changing

# Loop
for lang in test_tuple1:
    print(lang)

# Lists
l = [1, 2, 3, 4]
print(id(l))  # prints the id of object
print(l[-1])  # Start from last index
print(l[1:])
print(l[1:3])  # excludes last index
# lists are mutable
l[1] = 99
print(l)
print(id(l))  # same id means the same object got changed and no new object got created

# Strings are immutable
test_string = "Hello"
print(test_string)
print(id(test_string))
test_string = test_string + "Apoorv"
print(test_string)
print(id(test_string))  # id gets changed so immutable i.e. new object got created.


def test_function():
    global test_string  # to access global string reference and not creating a new reference
    print(test_string)
    test_string = "bye"
    print(test_string)


test_function()
print(test_string)

x = [1]
y = [10]

def test():
    x[0] = 2
    y = [20]

test()
print(x)
print(y)

# Multi line comment
''' Naive way to put labels
print('   0  1  2')
count = 0
for row in game:
    print(count, row)
    count += 1
'''

# Using Enumerate
'''print('   a  b  c')
for count, row in enumerate(game):
    print(count, row)'''

a = ['bar','foo',10,True]
#print(min(a)) # Error cannot compare int and str
a = ['bar','foo','too']
print(min(a))

s = 'foobar'
print(s.upper())

print(a*0)

t = "foo","boo"
print("boo" not in t)


set1 = set(["Foo","Bar"])
print(set1)

set1 = set((("Foo", "Bar"), ("Car", "Far")))
print(set1)

lst_chain = [[1,2,3,[6,7,8]],[3,4,5]]
flat_lst = it.chain(lst_chain)
print(flat_lst)
for flat in flat_lst:
    print(flat)

new_lst = []


def flatten(lst):
    for val in lst:
        if isinstance(val, collections.Iterable):
            flatten(val)
        else:
            new_lst.append(val)
    return new_lst


new_lst = flatten(lst_chain)
print(new_lst)


def fibo_gen(n):
    a, b, counter = 0, 1, 0
    while True:
        if counter > n:
            return
        yield a
        a, b = b, a + b
        counter += 1


generator = fibo_gen(6)
for val in generator:
    print(val)

for val in generator:
    print(val)


class Robo:
    pass

x = Robo()
y = Robo()

x.name = "Apoorv"
x.age = 26

print(x.__dict__)
print(y.__dict__)

print(x)
print(eval('x'))

class X:pass
class Y: pass
class Z:pass
class T:pass
class A(X,Y):pass
class B(Y,T,Z):pass
class M(B,A,Z):pass

print(M.__mro__)

1+1
print(1+1)
print(4^2)
10 // -4
10 % -4 #Allows negative remainders
10 ** 2
x = 0
not x
x = -1
not x
x = 10
type(x > 5)
x = 20
callable(x) #Tests if an object is callable or not
