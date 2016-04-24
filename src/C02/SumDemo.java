package C02;

import java.util.Arrays;

/**
 * Created by 包子 on 2016/4/24.
 * 最大子序列和问题
 */
public class SumDemo {
    /**
     * O(N^3)
     */
    public static int maxSubSum1(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;

                for (int k = i; k <= j; k++) {
                    thisSum += a[k];

                    if (thisSum > maxSum) {
                        maxSum = thisSum;
                    }
                }
            }
        }
        return maxSum;
    }

    /**
     * O(N^2)
     */
    public static int maxSubSum2(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }

        }
        return maxSum;
    }

    /**
     * 分至策略求最大序列和
     * T(1) = 1
     * T(N) = 2T(N/2) +O(N)
     * <p>
     * 总的复杂度是：NlogN+N = O(NlogN)
     */
    private static int maxSumRec(int[] a, int left, int right) {
        //左边等于右边，只有一个元素，那么这个元素非负时他就是最大子序列
        if (left == right) {
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        }

        int center = (left + right) / 2;

        //求解大小为N/2的子序列的问题，每行花费T(N/2)个时间
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

        //求左边的值。是吧？
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        //两个for循环花费的时间为O(N)
        for (int i = center; i >= left; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        int[] intArray = {maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum};   //java中数组的表示形式
        Arrays.sort(intArray);
        return intArray[2];
    }

    /**
     * 最简单的一种算法
     * O(N)
     */
    public static int maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0;

        for (int j = 0; j < a.length; j++) {
            thisSum += a[j];

            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {   //这一步太巧妙了
                thisSum = 0;
            }
        }
        return thisSum;
    }

    public static void main(String[] args) {
        int[] a = {4, -3, 5, -2, -1, 2, 6, -2};
        System.out.println(maxSumRec(a, 0, a.length - 1));
    }

}
