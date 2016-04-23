package C01;

/**
 * Created by 包子 on 2016/4/23.
 */
public class FindMaxDemo {

    public static void main(String[] args) {
        String[] st1 = {"Joe", "Bob", "Bill", "Zeke"};

        System.out.println(findMax(st1));
    }

    public static Comparable findMax(Comparable[] arr) {
        int maxIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }
}
