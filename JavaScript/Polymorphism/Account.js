class Account {
    constructor(initID, initBalance) {
        this.idNumber = initID;
        this.balance = initBalance;;
    }
        
    getIDNumber() {
        return this.idNumber;
    }
    
    getBalance() {
        return this.balance;
    }
    
    deposit(amount) {
        if(amount > 0)
            this.balance += amount;
    }
    
    withdraw(amount) {
        if(amount > 0)
            this.balance -= amount;
    }

    toString() {
        return "Account #" + this.idNumber + ", balance: $" + this.balance;
    }
};