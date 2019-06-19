package SortStudy;

/*
 * 快速排序
 */
public class QuickSort
{
    public static void main(String[] args)
    {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        // 快速排序
        quick(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
    }

    private static void quick(int[] a)
    {
        if (a.length > 0)
        {
            quickSort(a, 0, a.length - 1);
        }
    }

    private static void quickSort(int[] a, int low, int high)
    {
        if (low < high)
        { // 如果不加这个判断递归会无法退出导致堆栈溢出异常

            System.out.println("low="+low+"   high="+high);
            int middle = getMiddle(a, low, high);

            System.out.println("当前排序左边"+"high=middle-1="+(middle-1)+"   a[]="+a[middle]);
            quickSort(a, 0, middle - 1);

            System.out.println("当前排序右边"+"low=middle-1="+(middle+1)+"   a[]="+a[middle]);
            quickSort(a, middle + 1, high);
        }else {
            System.out.println("当前部分排序完成"+"   low="+low+"   high="+high);
        }
    }

    private static int getMiddle(int[] a, int low, int high)
    {
        int temp = a[low];// 基准元素

        System.out.println();
        System.out.print("排序1："+"   low="+low+"   high="+high+"   tem="+temp);

        while (low < high)
        {
            // 找到比基准元素小的元素位置
            while (low < high && a[high] >= temp)
            {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp)
            {
                low++;
            }
            a[high] = a[low];

            System.out.println();
            System.out.println("排序2："+"   low="+low+"   high="+high+"   tem="+temp);
            for (int i = 0; i < a.length; i++)
            {
                System.out.print(a[i] + " ");
            }
        }
        a[low] = temp;
        System.out.println();
        System.out.println("排序中：");
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        return low;
    }
}
