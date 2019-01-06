# Parent class
class Dog:

    # Class attribute
    species = 'mammal'

    # Initializer / Instance attributes
    def __init__(self, name, age):
        self.name = name
        self.age = age
        self.is_hungry = True

    # instance method
    def description(self):
        return "{} is {} years old".format(self.name, self.age)

    # instance method
    def eat(self):
        self.is_hungry = True

    # instance method
    def walk(self):
        print(f"{self.name} is walking!")

    # instance method
    def speak(self, sound):
        return "{} says {}".format(self.name, sound)

# Child class (inherits from Dog class)
class RussellTerrier(Dog):
    def run(self, speed):
        return "{} runs {}".format(self.name, speed)

# Child class (inherits from Dog class)
class Bulldog(Dog):
    def run(self, speed):
        return "{} runs {}".format(self.name, speed)

class Pets():
    pets = []

    def __init__(self, pets):
        self.pets = pets

    def walk(self):
        for pet in self.pets:
            pet.walk()

dog1 = Dog("Tom",6)
dog2 = Dog("Fletcher",7)
dog3 = Dog("Larry",9)

l_pets = [dog1,dog2,dog3]

print(f"I have {l_pets.__len__()} dogs.")

pets = Pets(l_pets)

for pet in pets.pets:
    print(pet.description())
    pet.eat()

print(f"And they are all {dog1.species}, of course.") #Formatter String Used for printing
pets.walk()