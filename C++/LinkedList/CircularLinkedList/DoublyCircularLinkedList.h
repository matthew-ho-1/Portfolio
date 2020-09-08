#pragma once
#include "Iterator.h"

template <class T>
class DoublyCircularLinkedList
{
    class Node
    {
    public:
        T *data;
        Node *next;
        Node *prev;
    };

private:
    Node *head;
    Node *tail;
    int size;

public:
    DoublyCircularLinkedList()
    {
        head = nullptr;
        tail = nullptr;
        size = 0;
    }
    int getSize()
    {
        return size;
    }
    void append(T *data)
    {
        Node *newNode = new Node;
        newNode->data = data;

        if (size == 0)
        {
            newNode->next = newNode;
            newNode->prev = newNode;
            head = newNode;
            tail = newNode;
            size++;
        } else if (size == 1)
        {
            newNode->next = head;
            newNode->prev = head;
            head->next = newNode;
            head->prev = newNode;
            tail = newNode;
            size++;
        } else
        {
            newNode->next = head;
            newNode->prev = tail;
            head->prev = newNode;
            tail->next = newNode;
            tail = newNode;
            size++;
        }
        
    }
    void removeFirst()
    {
        if (head == nullptr)
        {
            return;
        }
        delete head;
        if (size == 1)
        {
            head = nullptr;
            tail = nullptr;
        } else
        {
            head = head->next;
            tail->next = head;
            head->prev = tail;
        }
        size--;
    }
    void prepend(T *data)
    {
        Node *newNode = new Node;
        newNode->data = data;
        
        if (size == 0)
        {
            newNode->next = newNode;
            newNode->prev = newNode;
            head = newNode;
            tail = newNode;
            size++;
        } else if (size == 1)
        {
            newNode->next = head;
            newNode->prev = head;
            head->next = newNode;
            head->prev = newNode;
            head = newNode;
            size++;
        } else
        {
            newNode->next = head;
            newNode->prev = tail;
            head->prev = newNode;
            tail->next = newNode;
            head = newNode;
            size++;
        }
    }
    T *get(int index)
    {
        Iterator<T> *it = this->getIterator();
        if (index < 0 || index >= size)
            return nullptr;
        for (int i = 0; i < index; i++)
        {
            it->next();
        }
        return it->next();
    }
    class DoublyCircularLinkedListIterator : public Iterator<T>
    {
        DoublyCircularLinkedList *list;
        Node *traveller;

    public:
        DoublyCircularLinkedListIterator(DoublyCircularLinkedList *initList)
        {
            list = initList;
            traveller = list->head;
        }
        bool hasNext()
        {
            return traveller != nullptr;
        }
        T *next()
        {
            if (!hasNext())
                return nullptr;
            T *answer = traveller->data;
            traveller = traveller->next;
            return answer;
        }
    };
    Iterator<T> *getIterator()
    {
        Iterator<T> *it = new DoublyCircularLinkedListIterator(this);
        return it;
    }
};