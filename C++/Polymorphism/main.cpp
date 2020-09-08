/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, PHP, Ruby, 
C#, VB, Perl, Swift, Prolog, Javascript, Pascal, HTML, CSS, JS
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <stdio.h>
#include "Account.h"
#include "PersonalAccount.h"
#include <iostream>
using namespace std;

void printAccounts(Account* list[]);

int main()
{
    Account* accounts [10];
    accounts[0] = new Account(512,216);
    accounts[1] = new Account(1234,521);
    accounts[2] = new Account(21,2992);
    accounts[3] = new Account(10000,12);
    accounts[4] = new Account(1,261261);
    accounts[5] = new PersonalAccount("Joe Shmo",513,216);
    accounts[6] = new PersonalAccount("Harry Potter",1235,999);
    accounts[7] = new PersonalAccount("Luke Skywalker",210,23);
    accounts[8] = new PersonalAccount("Bob Smith",10055,10);
    accounts[9] = new PersonalAccount("Jane Doe",12,26161);
    accounts[0]->deposit(200);
    accounts[1]->withdraw(21);
    accounts[2]->withdraw(-5);
    accounts[3]->deposit(-10);
    accounts[4]->deposit(2520);
    accounts[5]->withdraw(21);
    accounts[6]->deposit(25);
    accounts[7]->deposit(0);
    accounts[8]->withdraw(0);
    accounts[9]->deposit(-21);
    printAccounts(accounts);
    return 0;
}

void printAccounts(Account* list[]) {
    for(int i = 0; i < 10; i++) {
        cout << list[i]->toString() << "\n";
    }
}
