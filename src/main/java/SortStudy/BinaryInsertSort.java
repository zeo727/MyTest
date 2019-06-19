package SortStudy;

/*
 * 二分插入排序
 */

public class BinaryInsertSort
{

    public static void main(String[] args)
    {
        int[] a = { 49, 38, 65, 97, 176, 213, 227, 49, 78, 34, 12, 164, 11, 18, 1 };
        System.out.print("排序之前：");
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        // 二分插入排序
        sort(a);
        System.out.println();
        System.out.print("排序之后：");
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }

    private static void sort(int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            int temp = a[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            System.out.println("i="+i);
            while (left <= right)
            {
                mid = (left + right) / 2;
                System.out.println("mid="+mid);
                System.out.println("temp="+temp);
                System.out.println("a[mid]="+a[mid]);
                if (temp < a[mid])
                {
                    right = mid - 1;

                    System.out.println("left="+left);
                    System.out.println("right="+right);
                }
                else
                {
                    left = mid + 1;

                    System.out.println("left="+left);
                    System.out.println("right="+right);
                }
            }

            for (int j = i - 1; j >= left; j--)
            {
                a[j + 1] = a[j];
                System.out.println("for");
                System.out.println("j="+j);
                System.out.println("a[j]="+a[j]);
            }
            if (left != i)
            {
                a[left] = temp;
                System.out.println("if");
                System.out.println("temp="+temp);
            }
            System.out.print("排序中：");
            for (int s = 0; s < a.length; s++)
            {
                System.out.print(a[s] + " ");
            }
            System.out.println();
        }
    }

}

