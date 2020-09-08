class Node:
    def __init__(self, data = None, next=None): 
        self.data = data
        self.next = next

    def getData(self):
        return self.data

    def getNext(self):
        return self.next

class SinglyLinkedList:
    def __init__(self):  
        self.head = None
        self.tail = None
        self.size = 0

    def getSize(self):
        return self.size
  
    def append(self, data):
        newNode = Node(data)
        if(self.head):
            newNode.next = self.head
            self.tail.next = newNode
        else:
            newNode.next = newNode
            self.head = newNode
        self.tail = newNode
        self.size += 1

    def prepend(self, data):
        newNode = Node(data)
        if(self.head):
            current = self.head
            newNode.next = current
            self.head = newNode
            self.tail.next = self.head
        else:
            newNode.next = newNode
            self.head = newNode
            self.tail = newNode
        self.size += 1

    def printlist(self):
        current = self.head
        while(True):
            print(current.data)
            current = current.next
            if(current == self.tail):
                break
        if(self.size > 1):
            print(current.data)

    def removeFirst(self):
        if(self.head):
            current = self.head
            self.head = current.next
        if(self.head == None):
            self.tail = None
        self.size -= 1

    def getHead(self):
        return self.head.getData()
    
    def getTail(self):
        return self.tail.getData()

    def __iter__(self):
        self.traveller = self.head
        return self

    def __next__(self): 
        if self.traveller is None: 
            raise StopIteration
        answer = self.traveller
        self.traveller = self.traveller.getNext()
        return answer.getData()

    def get(self, index):
        if (index < 0 or index >= self.size):
            return None
        it = iter(self)
        for x in range(0, index):
            next(it)
        return next(it)

class Person:
    def __init__(self, firstName = None, lastName=None): 
        self.firstName = firstName
        self.lastName = lastName

    def getFirstName(self):
        return self.firstName

    def getLastName(self):
        return self.lastName

    def __str__(self):
        return self.firstName + " " + self.lastName

class Student(Person): 
    def __init__(self, firstName = None, lastName = None, initGPA = 0.0):
        super().__init__(firstName, lastName)
        self.GPA = initGPA
        
    def getGPA(self):
        return self.GPA
        
    def setGPA(self,gpa):
        self.GPA = gpa
    
    def __str__(self):
        return super().__str__() + " (" + str(self.GPA) + ") "

class Employee(Person): 
    def __init__(self, firstName = None, lastName = None, initSalary = 0.0):
        super().__init__(firstName, lastName)
        self.salary = initSalary
        
    def getSalary(self):
        return self.GPA
        
    def setSalary(self,sal):
        self.salary = sal 
    
    def __str__(self):
        return super().__str__() + " ($" + str(self.salary) + ") "
        
# One node prepend test        
print("One node prepend iterator test")
list = SinglyLinkedList()
list.prepend(Student("Bob", "Jones",4.5))
myiter = iter(list)
print(next(myiter))
print(next(myiter))
print("One node print test")
list.printlist()

# One node append test  
print("\nOne node append iterator test")
list = SinglyLinkedList()
list.append(Student("Bob", "Jones",4.5))
myiter = iter(list)
print(next(myiter))
print(next(myiter))
print("One node print test")
list.printlist()

# Two node prepend test        
print("\nTwo node prepend iterator test")
list = SinglyLinkedList()
list.prepend(Student("Bob", "Jones",4.5))
list.prepend(Employee("Ryan", "Smith",5000))
myiter = iter(list)
print(next(myiter))
print(next(myiter))
print(next(myiter))
print("Two node print test")
list.printlist()

# Two node append test        
print("\nTwo node append iterator test")
list = SinglyLinkedList()
list.append(Student("Bob", "Jones",4.5))
list.append(Employee("Ryan", "Smith",5000))
myiter = iter(list)
print(next(myiter))
print(next(myiter))
print(next(myiter))
print(next(myiter))
print("Two node print test")
list.printlist()

# Get test
print("\nGet test")
print(list.get(0))
print(list.get(1))

import unittest
class TestSinglyLinkedListMethods(unittest.TestCase):

    def test_append(self):
        list = SinglyLinkedList()
        s1 = Student("Bob", "Jones",4.5)
        list.append(s1)
        myiter = iter(list)
        self.assertEqual(next(myiter), s1)
        self.assertEqual(next(myiter), s1)
        self.assertEqual(next(myiter), s1)

    def test_prepend(self):
        list = SinglyLinkedList()
        s1 = Student("Bob", "Jones",4.5)
        list.prepend(s1)
        myiter = iter(list)
        self.assertEqual(next(myiter), s1)
        self.assertEqual(next(myiter), s1)
        self.assertEqual(next(myiter), s1)

    def test_get(self):
        list = SinglyLinkedList()
        s1 = Student("Bob", "Jones",4.5)
        list.append(s1)
        s2 = Employee("Ryan", "Smith",5000)
        list.append(s2)
        self.assertEqual(list.get(0), s1)
        self.assertEqual(list.get(1), s2)

    def test_removeFirst(self):
        list = SinglyLinkedList()
        list.prepend(1)
        list.append(4)
        list.append(5)
        list.prepend(2)
        list.removeFirst()
        self.assertEqual(list.getHead(), 1)

if __name__ == '__main__':
    unittest.main()

