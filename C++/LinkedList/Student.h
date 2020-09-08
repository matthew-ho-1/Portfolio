#include<string>
#include <sstream>

class Person;

class Student : public Person
{
    private:
        double GPA;
    
    public:
        Student(string first, string last, double gpa) : Person(first,last) { 
            GPA = gpa;
        } 
        
        double getGPA() { return GPA; }
    
        void setGPA(double gpa) { GPA = gpa; }

        string toString() { 
           stringstream ss;
           ss << Person::toString() << " (" << GPA << ") ";
           return ss.str();
        }
    
};