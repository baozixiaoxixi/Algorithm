package C01;

/**
 * Created by 包子 on 2016/4/23.
 */
public class MemoryCellTest {

    public static void main(String[] args) {
        //通过Object表示泛型
        MemoryCell m = new MemoryCell();
        m.write("34");
        String val = (String) m.read();
        System.out.println("Contents are:" + val);

        //包装类的使用
        MemoryCell m1 = new MemoryCell();
        m.write(new Integer(32));//包装类
        Integer wrapperVal = (Integer) m.read();
        int val1 = wrapperVal.intValue();
        System.out.println("Contents are:" + val1);

    }
}
