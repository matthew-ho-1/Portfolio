class PersonalAccount extends Account {
    constructor(initname, initID, initBalance) {
        super(initID, initBalance);
        this.name = initname;
    }
    
    getName() {
        return this.name;
    }
    
    toString() {
        return "Personal Account #" + this.idNumber + " for " + this.name  + ", balance: $" + this.balance;
    }
};