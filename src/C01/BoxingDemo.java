package C01;

/**
 * Created by 包子 on 2016/4/23.
 * 自动拆装箱
 */
public class BoxingDemo {
    public static void main(String[] args) {
        GenericMemoryCell<Integer> m = new GenericMemoryCell<>();
        m.write(37);
        int val = m.read();

        System.out.println("Contents are:" + val);
    }
}
