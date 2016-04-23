package C01;

/**
 * Created by 包子 on 2016/4/23.
 * 递归
 */
public class Recursive {

    /**
     * 函数递归
     */
    public static int f(int x) {
        if (x == 0) {
            return 0;
        } else {
            return 2 * f(x - 1) + x * x;
        }
    }

    /**
     * 打印递归
     */
    public static void printOut(int n) {
        if (n >= 10) {
            printOut(n / 10);   //递归解决
        }
        System.out.print(n % 10);
    }

    public static void main(String[] args) {
        printOut(76234);
    }

}
