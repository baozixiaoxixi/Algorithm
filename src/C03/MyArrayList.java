package C03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by 包子 on 2016/4/26.
 * Iterable接口：增强for以及其他的工具
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private AnyType[] theItems;

    public MyArrayList() {
        clear();
    }

    public void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public AnyType get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    public AnyType set(int idx, AnyType newVal) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = theItems[idx];//原来该位置的数
        theItems[idx] = newVal;
        return old;//返回原来的数字
    }


    public void ensureCapacity(int newCapacity) {//容量扩充
        if (newCapacity < theSize) {
            return;
        }
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];//为新数组分配内存
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public boolean add(AnyType x) {//添加到表的末端
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        if (theItems.length == size()) {//表中元素满了，扩容
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = theSize; i > idx; i--) {//数组中的添加操作，耗时
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;

        theSize++;
    }

    public AnyType remove(int idx) {
        AnyType removeItem = theItems[idx];
        for (int i = idx; i < size() - 1; i++) {//数组中删除操作，耗时
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removeItem;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<AnyType> {
        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
