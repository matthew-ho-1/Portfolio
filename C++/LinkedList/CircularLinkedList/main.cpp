#include <stdio.h>
#include <iostream>
#include "Iterator.h"
#include "Person.h"
#include "DoublyCircularLinkedList.h"
using namespace std;
void printList(string header, DoublyCircularLinkedList<Person> *list);
void printPerson(Person *p);

int main()
{
    DoublyCircularLinkedList<Person> *list = new DoublyCircularLinkedList<Person>();

    list->prepend(new Employee("Harry", "Potter", 80000));
    printList("One node prepend test", list);

    cout << "One node prepend iterator test" << endl;
    Iterator<Person> *it = list->getIterator();
    Person *p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";

    while (list->getSize() > 0)
    {
        list->removeFirst();
    }

    list->append(new Employee("Harry", "Potter", 80000));
    printList("One node append test", list);

    cout << "One node append iterator test" << endl;
    it = list->getIterator();
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";

    while (list->getSize() > 0)
    {
        list->removeFirst();
    }

    list->prepend(new Employee("Harry", "Potter", 80000));
    list->prepend(new Employee("Mary", "James", 80000));
    printList("Two node prepend test", list);

    cout << "Two node prepend iterator test" << endl;
    it = list->getIterator();
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";

    while (list->getSize() > 0)
    {
        list->removeFirst();
    }

    list->append(new Employee("Harry", "Potter", 80000));
    list->append(new Employee("Mary", "James", 80000));
    printList("Two node append test", list);

    cout << "Two node append iterator test" << endl;
    it = list->getIterator();
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";

    while (list->getSize() > 0)
    {
        list->removeFirst();
    }

    list->prepend(new Employee("Harry", "Potter", 80000));
    list->prepend(new Employee("Mary", "James", 80000));
    list->prepend(new Employee("Lucy", "Darson", 80000));
    printList("Three node prepend test", list);

    cout << "Three node prepend iterator test" << endl;
    it = list->getIterator();
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";

    while (list->getSize() > 0)
    {
        list->removeFirst();
    }

    list->append(new Employee("Harry", "Potter", 80000));
    list->append(new Employee("Mary", "James", 80000));
    list->append(new Employee("Lucy", "Darson", 80000));
    printList("Three node append test", list);

    cout << "Three node append iterator test" << endl;
    it = list->getIterator();
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";

    while (list->getSize() > 0)
    {
        list->removeFirst();
    }

    list->append(new Employee("Harry", "Potter", 80000));
    list->append(new Employee("Mary", "James", 80000));
    list->append(new Employee("Lucy", "Darson", 80000));
    list->removeFirst();
    printList("removeFirst test", list);

    cout << "removeFirst iterator test" << endl;
    it = list->getIterator();
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    p = it->next();
    cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";

    while (list->getSize() > 0)
    {
        list->removeFirst();
    }

    return 0;
}

void printList(string header, DoublyCircularLinkedList<Person> *list)
{
    cout << header + "\n";
    for (int i = 0; i < list->getSize(); i++)
    {
        printPerson(list->get(i));
    }
    Iterator<Person> *it = list->getIterator();
    for (int i = 0; i < list->getSize(); i++)
    {
        Person *p = it->next();
        cout << "\tThe Person at memory location " << p << " is " << p->toString() << "\n";
    }
    cout << "\n";
}

void printPerson(Person *p)
{
    printf("\t%s %s\n", p->getFirstName().c_str(), p->getLastName().c_str());
}