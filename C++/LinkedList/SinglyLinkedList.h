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
            Node* newNode = new Node();
            newNode->data = data;
            newNode->next = nullptr;
            if(head == nullptr && tail == nullptr)
            {
                head = tail = newNode;
            }
            else
            {
                Node* temp = new Node();
                temp = head; 
                while(temp->next != nullptr)
                    temp = temp->next;
                temp->next = newNode;
                tail = newNode;
            }
            size++;
        }
        
        void removeFirst() {
           if(head != nullptr)
                head = head->next;
           if(head == nullptr)
                tail = nullptr;
           size--;
        }
        
        void prepend(T* data) {
            Node* newNode = new Node();
            newNode->data = data;
            newNode->next = head;
            head = newNode;
            size++;
        }
        
        T* get(int index) {
            T* found = nullptr;
            Node* temp = new Node();
            temp = head;
            int count = 0;
            if(index == 0)
                found = temp->data;
            while(temp->next != nullptr)
            {
                temp = temp->next;
                count++;
                if(count == index)
                    found = temp->data;
            }
            return found;
        }
    
        class SinglyLinkedListIterator : public Iterator<T> {
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
                    T* lastData = nullptr;
                    if(traveller != nullptr)
                    {
                        lastData = traveller->data;
                        traveller = traveller->next;
                    }
                    return lastData;
                }
        };
    
        Iterator<T>* getIterator() {
            Iterator<T> *it = new SinglyLinkedListIterator(this);
            return it;
        }
};