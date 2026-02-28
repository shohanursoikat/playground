#include <stdio.h>

int main()
{

    int data[] = {11, 222, 33, 44, 55, 323, 213, 43};
    int n = sizeof(data) / sizeof(data[0]);

    int ptr;
    int temp;

    for (int k = 1; k <= n - 1; k++)
    {
        ptr = 0;

        while (ptr <= n - k - 1)
        {
            if (data[ptr] > data[ptr + 1])
            {
                temp = data[ptr];
                data[ptr] = data[ptr + 1];
                data[ptr + 1] = temp;
            }
            ptr++;
        }
    }

    for (int i = 0; i < n; i++)
    {
        printf("%d ", data[i]);
    }

    return 0;
}
