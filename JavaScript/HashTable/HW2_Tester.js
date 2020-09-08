function displayHashTable(header, hashTable) {
    let div = document.getElementById("output_div");
    div.innerHTML += "<pre><code>" 
        + header
        + "<br />"
        + toString(hashTable) 
        + "</code></pre>";
    console.log(div);
}

function toString(hashTable) {
    let keys = Object.keys(hashTable);
    
    let text = "[\n";
    for (const key of keys) {
        let value = hashTable[key].toString();
        text += " (" + key + ", " + value + ")\n"; 
    }
    text += "]\n";
    console.log(text);
    return text;
}

function generateKey(keyLength) {
    let randChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    let key = "";
    for (let i = 0; i < keyLength; i++) {
        let randomNum = Math.floor(Math.random() * 36);
        let randomChar = randChars.charAt(randomNum);
        key += randomChar;
    }
    return key;
}

function buildStudent(initFirstName, initLastName, initGpa) {
    let student = new Student(initFirstName, initLastName, initGpa);
    let key = generateKey(8);
    student.setKey(key);
    return student;
}

function buildEmployee(employeePrototype, initFirstName, initLastName, initSalary) {
    let employee = { "firstName": initFirstName,
                     "lastName": initLastName,
                     "salary": initSalary};
    Object.setPrototypeOf(employee, employeePrototype);
    let key = generateKey(9);
    employee.setKey(key);
    return employee;
}

function main() {
    let hashTable = [];
    
    // FIRST LET'S MAKE A DUMMY STUDENT
    let dummyStudent = buildStudent("", "", 0.0);
    let studentPrototype = Object.getPrototypeOf(dummyStudent);
    
    // NOW LET'S MAKE A NEW TYPE, AN EMPLOYEE. WE'LL THE
    // Object's create METHOD TO MAKE A COPY OF THE Student
    // PROTOTYPE SO THAT WE'LL HAVE A NEW TYPE FOR Employees
    let employeePrototype = Object.create(studentPrototype);
    employeePrototype.getSalary = function() {
        return this.salary;
    }
    employeePrototype.setSalary = function(initSalary) {
        this.salary = initSalary;
    }
    employeePrototype.toString = function() {
        return this.firstName + " " + this.lastName + " ($" + this.salary + ")";
    }

    let employee = buildEmployee(employeePrototype, "Paul", "McCartney", 80000);
    hashTable[employee.getKey()] = employee;
    let student = buildStudent("George", "Harrison", 4.0);
    hashTable[student.getKey()] = student;
    employee = buildEmployee(employeePrototype, "Ringo", "Starr", 40000);
    hashTable[employee.getKey()] = employee;
    employee = buildEmployee(employeePrototype, "Chuck", "Berry", 100000);
    hashTable[employee.getKey()] = employee;
    student = buildStudent("Mick", "Jagger", 3.5);
    hashTable[student.getKey()] = student;
    student = buildStudent("Jimi", "Hendrix", 3.6);
    hashTable[student.getKey()] = student;
    employee = buildEmployee(employeePrototype, "Roger", "Waters", 90000);
    hashTable[employee.getKey()] = employee;

    student = buildStudent("John", "Lennon", 3.8);
    let jlKey = student.getKey();
    hashTable[jlKey] = student;
    student = buildStudent("Charlie", "Watts", 3.1);
    let cwKey = student.getKey();
    hashTable[cwKey] = student;
    employee = buildEmployee(employeePrototype, "David", "Gilmour", 120000);
    let dgKey = employee.getKey();
    hashTable[dgKey] = employee;

    // AND DISPLAY THE HASH TABLE
    displayHashTable("After Adding 10 Items", hashTable);

    // NOW TRY GETTING A FEW
    let p = hashTable[jlKey];
    console.log("\nget " + jlKey + ": " + p.toString());
    p = hashTable[cwKey];
    console.log("\nget " + cwKey + ": " + p.toString());
    p = hashTable[dgKey];
    console.log("\nget " + dgKey + ": " + p.toString());

    // NOW LET'S TRY REPLACING THE DATA IN THE ABOVE THREE
    hashTable[jlKey] = buildStudent("Otis", "Redding", 3.5);
    hashTable[jlKey].setKey(jlKey);
    hashTable[cwKey] = buildStudent("Keith", "Richards", 3.1);
    hashTable[cwKey].setKey(cwKey);
    hashTable[dgKey] = buildEmployee(employeePrototype, "Bill", "Withers", 47000);
    hashTable[dgKey].setKey(dgKey);

    // AND DISPLAY THE HASH TABLE
    displayHashTable("\nAfter Changing 3 Items", hashTable);

    return 0;
}

main();