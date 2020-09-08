class Person {
    constructor(initFirstName, initLastName) {
        this.firstName = initFirstName;
        this.lastName = initLastName;
        this.key = "";
    }
    
    getKey() {
        return this.key;
    }

    setKey(initKey) {
        this.key = initKey;
    }
    
    getFirstName() {
        return this.firstName;
    }
        
    getLastName() {
        return this.lastName;
    }

    toString() {
        return this.key + ": " + this.firstName + " " + this.lastName;
    }
};