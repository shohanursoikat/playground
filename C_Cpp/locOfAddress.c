#include <stdio.h>

int main() {
    // Base address of array
    int base = 200;
    // Size of each element
    int w = 2;
    // Lower bound of the index (starting year)
    int LB = 1981;
    // Upper bound (final year)
    int MAX_YEAR = 2005;

    for (int year = LB; year <= MAX_YEAR; year++) {
        // Apply the formula: Loc(AUTO[year]) = base + w * (year - LB)
        int address = base + w * (year - LB);
        printf("Loc(AUTO[%d]) = %d + %d*(%d - %d) = %d\n", 
               year, base, w, year, LB, address);
    }

    return 0;
}
