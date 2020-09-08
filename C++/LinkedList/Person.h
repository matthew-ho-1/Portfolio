#pragma once

#include<string>
#include <sstream>
using namespace std;

class Person {
    private:
        string firstName;
        string lastName;
    
    public:
        Person(string initFirstName, string initLastName) {
            this->firstName = initFirstName;
            this->lastName = initLastName;
        }
        
        string getFirstName() {
            return this->firstName;
        }
        
        string getLastName() {
            return this->lastName;
        }
        
        virtual string toString() {
            stringstream ss;
            ss << this->firstName << " " << this->lastName;
            return ss.str();
        }
};