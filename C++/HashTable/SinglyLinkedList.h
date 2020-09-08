#include<string>
#include <sstream>
using namespace std;
#include "Iterator.h"

template <class T>

class SinglyLinkedList {
    
    // THIS INNER CLASS IS FOR OUR LIST NODES
    class Node {
        public:
            T* data;
            Node *next;
    };
    
    private:
        Node *head;
        Node *tail;
        int size;

    public:
        SinglyLinkedList() {
            head = nullptr;
            tail = nullptr;
            size = 0;
        }
        
        int getSize() { 
            return this->size;
        }
        
        void append(T* data) {
            // MAKE A NEW Node
            Node *newNode = new Node();
            newNode->data = data;
            newNode->next = nullptr;
            
            if (this->head == nullptr) {
                head = newNode;
            }
            else {
                tail->next = newNode;
            }
            tail = newNode;
            size++;
        }
        
        void removeFirst() {
            // REMEMBER, WE NEED TO DELETE THE Node
            // BUT NOT THE DATA AS SOMETHING ELSE
            // MIGHT STILL BE USING THE DATA
            if (this->head != nullptr) {
                // FIRST GET THE head
                Node *nodeToDelete = this->head;
                
                // MOVE THE HEAD TO THE NEXT ONE
                this->head = nodeToDelete->next;
                
                // CORRECT THE tail ONLY IF NEEDED
                if (this->head == nullptr)
                    this->tail = nullptr;
                
                // DECREMENT OUR COUNTER
                this->size--;
                
                // AND DELETE THE Node
                delete nodeToDelete;
            }
        }
        
        void prepend(T* data) {
            // MAKE A NEW Node
            Node *newNode = new Node();
            newNode->data = data;
            newNode->next = head;
            this->head = newNode;
            if (this->tail == nullptr)
                this->tail = newNode;
            size++;
        }
        
        T* get(int index) {
            if ((size == 0) || (index < 0) || (index > (size-1))) {
                return nullptr;
            }
            Node *traveller = head;
            for (int i = 0; i < index; i++) {
                traveller = traveller->next;
            }
            return traveller->data;
        }

    
        class SinglyLinkedListIterator:public Iterator<T> {
            SinglyLinkedList *list;
            Node *traveller;
        
            public:
                SinglyLinkedListIterator(SinglyLinkedList *initList) {
                    this->list = initList;
                    this->traveller = list->head;
                }
                
                bool hasNext() {
                    return traveller != nullptr;
                }
            
                T* next() {
                    if (!this->hasNext()) {
                        return nullptr;
                    }
                    T *dataToReturn = traveller->data;
                    traveller = traveller->next;
                    return dataToReturn;
                }
        };

        Iterator<T>* getIterator() {
            Iterator<T> *it = new SinglyLinkedListIterator(this);
            return it;
        }

        string toString() {
            stringstream ss;
            Iterator<T> *it = getIterator();
            while (it->hasNext()) {
                T* item = it->next();
                ss << item->toString() << "->";
            }
            ss << "null";
            return ss.str();
        }
};