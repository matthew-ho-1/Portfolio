#include<string>
#include <sstream>

class Person;

class Employee : public Person
{
    private:
        int salary;
    
    public:
        Employee (string first, string last, int sal) : Person(first,last) { 
            salary = sal;
        } 
         
        int getSalary() { return salary; }
    
        void setSalary(int sal) { salary = sal; }
    
        string toString() { 
            stringstream ss;
            ss << Person::toString() << " ($" << salary << ") ";
            return ss.str();
    }
};