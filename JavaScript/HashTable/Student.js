class Student extends Person {
    constructor(firstName, lastName, initGPA) {
        super(firstName, lastName);
        this.gpa = initGPA;
    }
    
    getGpa() {
        return this.gpa;
    }
    
    setGpa(initGpa) {
        this.gpa = initGpa;
    }
    
    toString() {
        return this.firstName + " " + this.lastName + " (" + this.gpa + ")";
    }
};