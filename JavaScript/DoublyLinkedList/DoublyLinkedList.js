class Person {
  constructor(initFirstName, initLastName) {
    this.firstName = initFirstName;
    this.lastName = initLastName;
  }
  getFirstName() {
    return this.firstName;
  }

  getLastName() {
    return this.lastName;
  }

  toString() {
    return this.firstName + " " + this.lastName;
  }
}

class Iterator{
    constructor(initLinkedList){
        this.linkedList = initLinkedList;
        this.traveller = initLinkedList.getHead();
    }
    next(){
        let newData = this.traveller.getData();
        this.traveller = this.traveller.getNext();
        return newData;
    }
    prev(){
        let newData = this.traveller.getData();
        this.traveller = this.traveller.getPrevious();
        return newData;
    }
}

class Node {
  constructor(data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }

  getPrevious() {
    return this.prev;
  }

  getNext() {
    return this.next;
  }
  getData(){
      return this.data;
  }
}

class DoublyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  getHead(){
      return this.head;
  }
  getTail(){
      return this.tail;
  }
  getSize(){
      return this.size;
  }

  append(data) {
    const newNode = new Node(data);
    if (this.size == 0) {
      this.head = newNode;
      newNode.prev = newNode;
    } else {
      this.tail.next = newNode;
      newNode.prev = this.tail;
    }

    newNode.next = this.head;
    this.tail = newNode;
    this.head.prev = this.tail;
    this.size++;
  }

  prepend(data) {
    if (this.size === 0) {
      return this.append(data);
    }

    const newNode = new Node(data);
    newNode.next = this.head;
    newNode.prev = this.tail;
    this.head.prev = newNode;
    this.tail.next = newNode;
    this.head = newNode;
    this.size++;
  }

  get(index) {
    if (index >= 0) {
      let ptr = this.head;
      let i = 0;
      while (ptr !== null && i < index) {
        ptr = ptr.next;
        i++;
      }

      if (ptr != null) {
        return ptr.data.toString();
      } else {
        return undefined;
      }
    } else {
      return undefined;
    }
  }

  removeFirst() {
    let tempNode = this.head;
    if (this.size != 0) {
      this.head.prev.next = this.head.next;
      this.head.next.prev = this.head.prev;
      this.head = tempNode.next;
      this.size--;
    }
  }
}

let printList = function (header, linkedList) {
    if(linkedList.getSize() > 0) {
        let div = document.getElementById("output_div");
        div.innerHTML += "<br>" + header;
        let pointer = linkedList.getHead();
        let iterator = new Iterator(linkedList);
        let data = iterator.next();
        do {
          div.innerHTML += " " + data + ", ";
           data = iterator.next();
        } while (data !== linkedList.getHead().getData())
    }
}

let printListReverse = function (header, linkedList) {
    if(linkedList.getSize() > 0) {
        let div = document.getElementById("output_div");
        div.innerHTML += "<br>" + header;
        let pointer = linkedList.getHead();
        let iterator = new Iterator(linkedList);
        iterator.prev();
        let data = iterator.prev();
        do {
          div.innerHTML += " " + data + ", ";
           data = iterator.prev();
        } while (data !== linkedList.getTail().getData())
    }
}

let testAppend = function () {
  let names = new DoublyLinkedList();
  names.append(new Person("Bob", "Smith"));
  //console.log(names.tail.getNext().data.toString());
  // expect Bob Smith

  names.append(new Person("John", "Doe"));
  //console.log(names.tail.getNext().data.toString());
  // expect Bob Smith

  names.append(new Person("Luke", "Skywalker"));
  // console.log(names.tail.getNext().data.toString());
  // expect Bob Smith

  //console.log(names.tail.getPrevious().data.toString());
  // expect "John", "Doe"
  printList("Append Test:", names);
  console.log(names.get(0),names.get(1),names.get(2));
};

let testPrepend = function () {
  let names = new DoublyLinkedList();
  names.prepend(new Person("Bob", "Smith"));

  // expect Bob Smith

  names.prepend(new Person("John", "Doe"));

  // expect John Doe

  names.prepend(new Person("Luke", "Skywalker"));

  // expect Luke Skywalker
  printList("\nPrepend Test:", names);
  // expect John Doe
};

let testRemoveFirst = function () {
  let names = new DoublyLinkedList();
  names.append(new Person("Bob", "Smith"));

  names.append(new Person("John", "Doe"));

  names.append(new Person("Luke", "Skywalker"));

  //expect Bob Smith, John Doe, Luke Skywalker

  //expect Bob Smith

  names.removeFirst();

  printList("RemoveFirst Test:", names);
};

let testGetter = function () {
    let div = document.getElementById("output_div");
  div.innerHTML += "<br>" + "Test Getter: ";
  let names = new DoublyLinkedList();
  names.append(new Person("Bob", "Smith"));
  names.append(new Person("John", "Doe"));
  names.append(new Person("Luke", "Skywalker"));
  div.innerHTML += "names[0]: " + names.get(0) + ", ";
  div.innerHTML += "names[1]: " + names.get(1) + ", ";
  div.innerHTML += "names[2]: " + names.get(2) + ", ";
};

let testIterator = function () {
  let div = document.getElementById("output_div");
  div.innerHTML += "<br>" + "Iterator Test (multiple times through list):";
  let names = new DoublyLinkedList();
  names.append(new Person("Bob", "Smith"));
  names.append(new Person("John", "Doe"));
  names.append(new Person("Luke", "Skywalker"));
  let iter = new Iterator(names);
  for(i = 0; i < names.getSize() * 3; i++) {
      div.innerHTML += " " + iter.next() + ", ";
  }
  
  div.innerHTML += "<br>" + "Iterator Test (multiple times through list in reverse):";
  let iter2 = new Iterator(names);
  iter2.prev();
  for(i = 0; i < names.getSize() * 3; i++) {
      div.innerHTML += " " + iter2.prev() + ", ";
  }
};

testAppend();

testPrepend();

testRemoveFirst();

testGetter();

testIterator();
