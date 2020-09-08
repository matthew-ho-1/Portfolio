#pragma once
#include <string>
#include <sstream>
using namespace std;

class Person
{
private:
    string firstName;
    string lastName;

public:
    Person(string initFirstName, string initLastName)
    {
        firstName = initFirstName;
        lastName = initLastName;
    }

    string getFirstName()
    {
        return firstName;
    }

    string getLastName()
    {
        return lastName;
    }

    virtual string toString()
    {
        stringstream ss;
        ss << firstName << " " << lastName;
        return ss.str();
    }
};

class Student : public Person
{
private:
    string firstName;
    string lastName;
    float GPA;

public:
    Student(string initFirstName, string initLastName, float initGPA) : Person(initFirstName, initLastName)
    {
        GPA = initGPA;
    }

    float getGPA()
    {
        return GPA;
    }

    string toString()
    {
        char gpaString[50];
        snprintf(gpaString, sizeof(gpaString), "(%g)", GPA);
        stringstream ss;
        ss << getFirstName() << " " << getLastName() << " " << gpaString;
        return ss.str();
    }
};

class Employee : public Person
{
private:
    string firstName;
    string lastName;
    int salary;

public:
    Employee(string initFirstName, string initLastName, int initSalary) : Person(initFirstName, initLastName)
    {
        salary = initSalary;
    }

    int getSalary()
    {
        return salary;
    }

    string toString()
    {
        stringstream ss;
        ss << getFirstName() << " " << getLastName() << " ($" << salary << ")";
        return ss.str();
    }
};