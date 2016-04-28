package C04;

/**
 * Created by 包子 on 2016/4/27.
 * 二叉查找树
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {    //Comparable:树中的两项可以使用compareTo方法进行排序

    //二叉树的节点
    private static class BinaryNode<AnyType> {
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;    //节点的数据
        BinaryNode<AnyType> left;   //左节点
        BinaryNode<AnyType> right;  //右节点
    }

    private BinaryNode<AnyType> root;   //根节点

    public BinarySearchTree() {  //初始化树：根节点为null
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public AnyType findMin() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return findMin(root).element;
    }

    public AnyType findMax() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return findMax(root).element;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty Tree");
        } else {
            printTree(root);
        }
    }

    //t节点下是否有x:这里存在递归调用
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }
        int compareReslut = x.compareTo(t.element);

        if (compareReslut < 0) {
            return contains(x, t.left);
        } else if (compareReslut > 0) {
            return contains(x, t.right);
        } else {
            return true;    //Match
        }
    }

    //找到一个节点下 存在最小元素的节点
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    //找到一个节点下 存在最小元素的节点
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null) {
            while ((t.right != null)) {
                t = t.right;
            }
        }
        return t;
    }

    //插入元素
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {  //对t节点下进行插入
        if (t == null) {
            return new BinaryNode<AnyType>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);     //将要插入的元素与该节点的元素进行比较

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            // do nothing
        }
        return t;
    }

    //删除一个元素（最为复杂）
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {     //有两个孩子---->最为复杂：用右边子树最小的数据来代替该节点，递归删除那个节点(因为最小的数据不可能有左儿子)
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;    //一个孩子
        }
        return t;
    }

    //按顺序打印二叉树
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }
}