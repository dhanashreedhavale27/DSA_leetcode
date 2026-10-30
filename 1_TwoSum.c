/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
#include <stdio.h>
#include <stdlib.h>

#define TABLE_SIZE 10007 

typedef struct Node {
    int key;       
    int val;       
    struct Node* next;
} Node;

void insert(Node** hashTable, int key, int val) {
    int hashIndex = abs(key) % TABLE_SIZE;
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->key = key;
    newNode->val = val;
    newNode->next = hashTable[hashIndex];
    hashTable[hashIndex] = newNode;
}

int search(Node** hashTable, int key) {
    int hashIndex = abs(key) % TABLE_SIZE;
    Node* curr = hashTable[hashIndex];
    while (curr) {
        if (curr->key == key) {
            return curr->val;
        }
        curr = curr->next;
    }
    return -1;
}

int* twoSum(int* nums, int numsSize, int target, int* returnSize) {
    *returnSize = 2;
    int* result = (int*)malloc(2 * sizeof(int));
    
    Node** hashTable = (Node**)calloc(TABLE_SIZE, sizeof(Node*));

    for (int i = 0; i < numsSize; i++) {
        int complement = target - nums[i];
        int complementIndex = search(hashTable, complement);
        
        if (complementIndex != -1) {
            result[0] = complementIndex;
            result[1] = i;
            
            for (int j = 0; j < TABLE_SIZE; j++) {
                Node* curr = hashTable[j];
                while (curr) {
                    Node* temp = curr;
                    curr = curr->next;
                    free(temp);
                }
            }
            free(hashTable);
            
            return result;
        }
        
        insert(hashTable, nums[i], i);
    }

    free(hashTable);
    return NULL;
}