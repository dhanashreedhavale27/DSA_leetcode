#include <string.h>
int getValue(char c) {
    if (c == 'I') return 1;
    if (c == 'V') return 5;
    if (c == 'X') return 10;
    if (c == 'L') return 50;
    if (c == 'C') return 100;
    if (c == 'D') return 500;
    if (c == 'M') return 1000;
    return 0;
}

int romanToInt(char* s) {
    int total = 0;
    int len = strlen(s);

    for (int i = 0; i < len; i++) {
        int current = getValue(s[i]);
        int next = getValue(s[i + 1]);

        // If a smaller value comes before a larger value, subtract it
        if (current < next) {
            total -= current;
        } else {
            total += current;
        }
    }
    return total;
}