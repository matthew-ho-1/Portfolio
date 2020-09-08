#include <stdlib.h>
#include <ctime>
#include<string>
#include <sstream>
using namespace std;
#include "SinglyLinkedList.h"

template <class S>

class ChainedHashTable {
    class KeyValuePair {
        public:
            string key;
            S* value;
            
            string toString() {
                stringstream ss;
                ss << "(" << this->key << "," << value->toString() << ")";
                return ss.str();
            }
    };

    private:
        SinglyLinkedList<KeyValuePair> *hashTable;
        int bins;
        int keyLength;

    public:
        ChainedHashTable(int initBins, int initKeyLength) {
            this->bins = initBins;
            this->keyLength = initKeyLength;
            this->hashTable = new SinglyLinkedList<KeyValuePair>[initBins];
            srand((unsigned) time(0));
        }

        string generateKey() {
            string key {""};
            int count = 0;
            while(count < keyLength)
            {
               int alphaNum = rand() % 2;
               int randInt;
               if(alphaNum == 0)
                    randInt = rand() % 10 + 48;
               else
                    randInt = rand() % 26 + 65;
               char ch = (char) randInt;
               key += ch;
               if(count == keyLength)
               {
                   if(get(key) != NULL)
                   {
                       count = 0;
                       key = "";
                   }
                   else
                    count++;
               }
               else
                count++;
            }
            return key;
        }

        int hashCode(string key) {
            int totalASCII = 0;
            for(int i = 0; i < key.length(); i++)
            {
                char ch = key[i];
                totalASCII += (int) ch;
            }
            return totalASCII % bins;
        }

        S* get(string key) {
          for(int i = 0; i < bins; i++)
          {
              Iterator<KeyValuePair> *it = hashTable[i].getIterator(); 
              while(it->hasNext()) {
                 KeyValuePair *kvp = it->next();
                 if(kvp->key == key)
                     return kvp->value;
              }
           }
           return nullptr;
        }

        void put(string key, S* value) {
            int index = hashCode(key);
            KeyValuePair *kvp = new KeyValuePair;
            if(get(key) != nullptr)
             {
                Iterator<KeyValuePair> *it = hashTable[index].getIterator(); 
                while(it->hasNext()) {
                    KeyValuePair *kvp = it->next();
                    if(kvp->key == key)
                        kvp->value = value;
                }
             }
            else
            {
                kvp->key = key;
                kvp->value = value;
                hashTable[index].append(kvp);
            }
            
         }

        string toString() {
            stringstream ss;
             ss << "[ \n"; 
             for(int i = 0; i < bins; i++)
             {
                 ss << " " << i << ": " << hashTable[i].toString() + " \n";
             }
             ss << "]";
             return ss.str();
        }
};