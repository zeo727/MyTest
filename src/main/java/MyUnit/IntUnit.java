package MyUnit;

public class IntUnit {

    public int[] del(int[] arr, int position) {
        if (position >= arr.length || position < 0) {
            return null;
        }
        int[] res = new int[arr.length - 1];
        for (int i = 0; i < res.length; i++) {
            if (i < position) {
                res[i] = arr[i];
            } else {
                res[i] = arr[i + 1];
            }
        }
        return res;
    }

    public int[] add(int[] a, int[] b) {
        int c[] = null;
        c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);

        return c;
    }
}
