#pragma once

#include<string>
#include <sstream>
using namespace std;
#include "Person.h"

class Employee : public Person {
    private:
        double salary;
    
    public:
        Employee(string firstName, string lastName, double initSalary)
            : Person(firstName, lastName) {
            this->salary = initSalary;
        }
        
        double getSalary() {
            return this->salary;
        }
        
        void setSalary(double initSalary) {
            this->salary = initSalary;
        }
        
        string toString() {
            stringstream ss;
            ss << this->getFirstName() << " " << this->getLastName() << " ($" << this->salary << ")";
            return ss.str();
        }
};