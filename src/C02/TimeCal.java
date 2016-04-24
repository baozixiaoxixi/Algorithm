package C02;

/**
 * Created by 包子 on 2016/4/24.
 * 运行时间的计算
 */
public class TimeCal {

    /**
     * 所有的声明均不计时间
     * 所以一共6N+4个时间单元
     */
    public static int sum(int n) {
        int partialSum;

        partialSum = 0; //占用一个时间单元
        for (int i = 1; i <= n; i++) {  //执行N次，隐藏着开销，初始化一个单元化时间，测试N+1单元时间，自增运算一个单元时间，共2N+2个单元时间
            partialSum += i * i * i;    //执行一次占用四个时间单元（两次乘法，一次加法，一次赋值）
        }
        return partialSum;  //占用一个时间单元
    }

    /**
     * 递归的运行时间：可以当成一个for循环
     * 运行时间为 O(N)
     */
    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * 递归的第二个例子，分析其运行时间
     * 当N=0或1时，运行试剑石某个常数值，即第一行上做判断以及返回所有的时间=2
     * N>3时，T(N)=T(N-1)+T(N-2)+2  (其中的2是指第一行的判断和返回所用的时间)------->T(N)代表运行时间公式
     */
    public static long fib(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

}
