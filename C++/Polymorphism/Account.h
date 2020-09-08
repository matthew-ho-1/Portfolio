#pragma once

#include<string>
#include <sstream>
using namespace std;

class Account {
    private:
        int idNumber;
        int balance;
    
    public:
        Account(int initID, int initBalance) {
            this->idNumber = initID;
            this->balance = initBalance;
        }
        
        int getIDNumber() {
            return this->idNumber;        
        }
        
        int getBalance() {
            return this->balance;
        } 
        
        void deposit(int amount) {
            if(amount > 0) 
                this->balance += amount;
        }
        
        void withdraw(int amount) {
            if(amount > 0)
                this->balance -= amount;
        }
        
        virtual string * toString() {
            stringstream ss;
            &ss << "Account #" << this->idNumber << ", balance: $" << balance;
            return ss.str();
        }
    
};