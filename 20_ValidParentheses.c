#include <stdbool.h>
#include <string.h>
#include <stdlib.h>

bool isValid(char* s) {
    int len = strlen(s);
    
    char stack[len];
    int top = -1; 

    for (int i = 0; i < len; i++) {
        char c = s[i];

                if (c == '(' || c == '{' || c == '[') {
            top++;
            stack[top] = c;
        } 
     
        else {
            if (top == -1) {
                return false;
            }

            char topChar = stack[top];
            top--; 

            if ((c == ')' && topChar != '(') ||
                (c == '}' && topChar != '{') ||
                (c == ']' && topChar != '[')) {
                return false;
            }
        }
    }
    return top == -1;
}