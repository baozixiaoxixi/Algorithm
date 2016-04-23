package C01;

/**
 * Created by 包子 on 2016/4/23.
 * 通过Object类表示泛型
 */
public class MemoryCell {

    private Object storedValue;

    public Object read() {
        return storedValue;
    }

    public void write(Object x) {
        storedValue = x;
    }
}
