package JavaStudy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
* https://gitee.com/SnailClimb/JavaGuide/blob/master/docs/java/Basis/Arrays,CollectionsCommonMethods.md
* */
public class sort {
    public static void main(String args[]) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        arrayList2.add(-3);
        arrayList2.add(-5);
        arrayList2.add(7);

        System.out.println("原始数组:");
        System.out.println(arrayList);
        // void reverse(List list)：反转
        Collections.reverse(arrayList);
        System.out.println("Collections.reverse(arrayList):");
        System.out.println(arrayList);

        //void rotate(List list, int distance)//旋转。当distance为正数时，将list后distance个元素整体移到前面。
        //当distance为负数时，将 list的前distance个元素整体移到后面。
        Collections.rotate(arrayList, 4);
        System.out.println("Collections.rotate(arrayList, 4):");
        System.out.println(arrayList);

        // void sort(List list),按自然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):");
        System.out.println(arrayList);

        // void shuffle(List list),随机排序
        Collections.shuffle(arrayList);
        System.out.println("Collections.shuffle(arrayList):");
        System.out.println(arrayList);

        // void swap(List list, int i , int j),交换两个索引位置的元素
        System.out.println(arrayList);
        Collections.swap(arrayList, 2, 5);
        System.out.println("Collections.swap(arrayList, 2, 5):");
        System.out.println(arrayList);

        //void sort(List list, Comparator c)//定制排序，由Comparator控制排序逻辑
        Collections.sort(arrayList, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后：");
        System.out.println(arrayList);

        System.out.println("Collections.max(arrayList):");
        System.out.println(Collections.max(arrayList));

        System.out.println("Collections.min(arrayList):");
        System.out.println(Collections.min(arrayList));

        //boolean replaceAll(List list, Object oldVal, Object newVal), 用新元素替换旧元素
        System.out.println("Collections.replaceAll(arrayList, 3, -3):");
        Collections.replaceAll(arrayList, 3, -3);
        System.out.println(arrayList);

        //void fill(List list, Object obj)//用指定的元素代替指定list中的所有元素。
        System.out.println("Collections.fill(arrayList, 3):");
        Collections.fill(arrayList, 3);
        System.out.println(arrayList);

        //int frequency(Collection c, Object o)//统计元素出现次数
        System.out.println("Collections.frequency(arrayList, -3):");
        System.out.println(Collections.frequency(arrayList, -3));

        //int indexOfSubList(List list, List target)//统计target在list中第一次出现的索引，找不到则返回-1
        System.out.println("Collections.indexOfSubList(arrayList, arrayList2):");
        System.out.println(Collections.indexOfSubList(arrayList, arrayList2));

        // 对List进行二分查找，返回索引，List必须是有序的
        System.out.println("Collections.binarySearch(arrayList, 7):");
        Collections.sort(arrayList);
        System.out.println(Collections.binarySearch(arrayList, 7));
    }
}
