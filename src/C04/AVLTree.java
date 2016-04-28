package C04;

/**
 * Created by 包子 on 2016/4/28.
 * AVL平衡树：智商不足，只能了解个大概，who can save me?
 */
public class AVLTree<AnyType extends Comparable<? super AnyType>> {

    //树的节点
    private static class AvlNode<AnyType> {

        AvlNode(AnyType theElement) {
            this(theElement, null, null);
        }

        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

        AnyType element;    //The data in the node
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;     //AVL平衡树的高度

    }

    //计算AVL节点的高度
    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    //向AVL树插入
    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
        if (t == null) {
            return new AvlNode<AnyType>(x, null, null);
        }

        int compareReslut = x.compareTo(t.element);

        if (compareReslut < 0) {    //插左节点
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.element) < 0) {
                    t = rotateWithLeftChild(t); //单旋转,左左
                } else {
                    t = doubleWithLeftChild(t); //双旋转，左右
                }
            }
        } else if (compareReslut > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.element) > 0) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleWithRightChild(t);
                }
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    //执行单旋转(左左)
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    //执行单旋转(右右)
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k1) {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }


    //执行双旋转
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    //执行双旋转
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3) {
        k3.left = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

}
