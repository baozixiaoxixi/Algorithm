package C01;

import java.util.Comparator;

/**
 * Created by 包子 on 2016/4/23.
 */
public class FunctionDemo {
    public static void main(String[] args) {
        String[] arr = {"ZEBBRA", "alligator", "crocodile"};
        System.out.println(findMax(arr, new CaseInsensitiveCompare()));
    }

    public static <AnyType> AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp) {
        int maxIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (cmp.compare(arr[i], arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        System.out.println(maxIndex);
        return arr[maxIndex];
    }
}

class CaseInsensitiveCompare implements Comparator<String> {

    //忽略大小写比较
    public int compare(String lhs, String rhs) {
        return lhs.compareToIgnoreCase(rhs);
    }
}


