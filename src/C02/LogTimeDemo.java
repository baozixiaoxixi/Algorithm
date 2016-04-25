package C02;

/**
 * Created by 包子 on 2016/4/25.
 * 运行时间中的对数
 * 如果一个算法用常数时间将问题的大小消减为其一部分，通常为1/2，那么该算法就是O(logN)
 */
public class LogTimeDemo {
    /**
     * 折半查找法    O(logN)
     */
    public static <AnyType extends Comparable<? super AnyType>> int binarySearch(AnyType[] a, AnyType x) {
        int low = 0, high = a.length - 1;
        //从这里开始折半查找
        while (low <= high) {
            int mid = (low + high) / 2;

            if (a[mid].compareTo(x) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 欧几里得算法,计算最大公因数   O(logN)
     */
    public static long gcd(long m, long n) {
        while (n != 0) {
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    /**
     * 高效率的幂运算  O(logN)
     */
    public static long pow(long x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            return pow(x * x, n / 2);
        } else {
            return pow(x * x, n / 2) * x;
        }
    }

    /**
     * 验证算法：估计两个随机数互素的概率
     */
    public static double probRelPrim(int n) {
        int rel = 0, tot = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tot++;
                if (gcd(i, j) == 1) {
                    rel++;
                }
            }
        }
        return (double) rel / tot;
    }


}
