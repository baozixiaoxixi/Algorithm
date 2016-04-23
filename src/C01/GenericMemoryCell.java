package C01;

import java.util.Collection;

/**
 * Created by 包子 on 2016/4/23.
 * 简单的泛型类
 */
public class GenericMemoryCell<AnyType> {

    private AnyType storedValue;

    public AnyType read() {
        return storedValue;
    }

    public void write(AnyType x) {
        storedValue = x;
    }

    /**
     * 泛型static方法
     */
    public static <AnyType> boolean contains(AnyType[] arr, AnyType x) {
        for (AnyType val : arr) {
            if (x.equals(val)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 类型限界
     */
    public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType[] arr) {
        return arr[1];
    }

}
