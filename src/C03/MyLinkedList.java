package C03;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by 包子 on 2016/4/26.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int theSize = 0;
    private int modCount = 0;   //自从构造以来对链表所做的改变的次数
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    public MyLinkedList() {
        clear();
    }

    /**
     * 核心操作：定义Node节点
     */
    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;    //一个Node应该包含数据
        public Node<AnyType> prev;  //指向前一个Node
        public Node<AnyType> next;  //F指向后一个Node
    }

    public void clear() {   //创建并连接头节点和尾节点，然后设置大小为0
        beginMarker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        addBefore(getNode(idx), x);
    }

    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    private void addBefore(Node<AnyType> p, AnyType x) {    //在p处添加一个节点
        Node<AnyType> newNode = new Node<>(x, p.prev, p); //巧妙
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private AnyType remove(Node<AnyType> p) {    //删除一个节点
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    private Node<AnyType> getNode(int idx) { //根据角标获取一个元素
        Node<AnyType> p;

        if (idx < 0 || idx > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;//错误检验？原理？
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }


        public AnyType next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AnyType nextItem = current.data;
            current = current.next; //向下移动
            okToRemove = true;  //表示可以删除了
            return nextItem;
        }


        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            okToRemove = false;   //删除成功后重置
            expectedModCount++;
        }
    }
}
