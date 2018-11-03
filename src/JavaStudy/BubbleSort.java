package JavaStudy;

class BubbleSort
{
    public static void main (String[] args)
    {
        int[] a = {1, 3, 5, 9, 8, 6};
        bubbleSort(a);
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i]);
        }
    }
    public static void bubbleSort(int[] a)
    {
        for (int i = 0; i < a.length - 1; i++)
            {
                for (int j = 0; j < a.length - 1 - i; j++)
                {
                if (a[j] > a[j + 1])
                {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
}

