#pragma once

#include<string>
#include <sstream>
using namespace std;
#include "Account.h"

class PersonalAccount : public Account {
    private:
        string name;
    
    public:
        PersonalAccount(string initName, int initID, int initBalance) : Account(initID, initBalance) {
            this->name = initName;
        }
        
        string getName() {
            return this->name;
        }
        
        string toString() {
            stringstream ss;
            ss << "Personal Account #" << Account::getIDNumber() << " for " << this->name  << ", balance: $" << Account::getBalance(); 
            return ss.str();
        }
};