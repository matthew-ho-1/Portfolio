// FIRST WE INCLUDE ALL THE THINGS WE PLAN TO USE HERE

// FOR printf
#include <stdio.h>

// FOR cout
#include <iostream>
using namespace std;

// OUR STUFF
#include "Iterator.h"
#include "Person.h"
#include "SinglyLinkedList.h"
#include "Student.h"
#include "Employee.h"

// THESE ARE CALLED FORWARD DECLARATIONS AND ARE BASICALLY
// PLACEHOLDERS FOR METHODS DEFINED LATER IN THIS FILE
void printList(string header, SinglyLinkedList<Person> *list);
void printPerson(Person *p);

// EXECUTION OF OUR PROGRAM STARTS HERE
int main() {
    // MAKE THE LIST WE'LL BE USING
    SinglyLinkedList<Person> *list = new SinglyLinkedList<Person>();
    
    // FIRST LET'S TRY OUT APPEND
    list->append(new Student("George", "Harrison", 4.0));
    list->append(new Student("John", "Lennon", 3.8));
    list->append(new Employee("Paul", "McCartney", 80000));
    list->append(new Employee("Ringo", "Starr", 40000));
    printList("post-append", list);

    // LET'S EMPTY THE LIST AND CLEAN UP MEMORY
    while (list->getSize() > 0) {
        list->removeFirst();
    }
    
    printList("post-removeFirst", list);

    // NOW LET'S TRY OUT PREPEND
    list->prepend(new Student("George", "Harrison", 4.0));
    list->prepend(new Student("John", "Lennon", 3.8));
    list->prepend(new Employee("Paul", "McCartney", 80000));
    list->prepend(new Employee("Ringo", "Starr", 40000));
    printList("post-prepend", list);
    
    return 0;
}

void printList(string header, SinglyLinkedList<Person> *list) {
    cout <<header + "\n";
    
    // PRINT ALL PERSON OBJECTS IN THE LIST
    for (int i = 0; i < list->getSize(); i++) {
        printPerson(list->get(i));
    }

    // NOW USE OUR CUSTOM-BUILT ITERATOR TO GO THROUGH
    // ALL OF THE ITEMS IN THE LIST
    Iterator<Person> *it = list->getIterator();
    while (it->hasNext()) {
        Person *p = it->next();
        cout << "\tThe Person at memory location " << p
                    << " is " << p->toString() << "\n";
    }
    cout << "\n";
}

void printPerson(Person *p) {
    printf ("\t%s %s\n", p->getFirstName().c_str(), p->getLastName().c_str());
}