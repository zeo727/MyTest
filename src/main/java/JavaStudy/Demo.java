package JavaStudy;

class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        try {
            System.out.print(arr[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("数组超出范围");
        }
    }

}
