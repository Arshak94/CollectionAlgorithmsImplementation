import java.util.ArrayList;
import java.util.Iterator;

/**
 * Date 3/27/18
 * Developer: Arshak Tovmasyan
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;

    protected int size = 0;

    public BinarySearchTree(){}

    public BinarySearchTree(E[] objects){
        for (int i = 0; i < objects.length; i++){
            insert(objects[i]);
        }
    }

    @Override
    public void inorder() {
       inorder(root);
    }

    protected void inorder(TreeNode<E> root){
        if (root == null) return;
        inorder(root.left);
        System.out.println(root.element+"");
        inorder(root.right);
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void preorder(TreeNode<E> root){
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.element+" ");
    }

    protected void postorder(TreeNode<E> root){
        if (root == null) return;
        System.out.println(root.element+" ");
        preorder(root.left);
        preorder(root.right);
    }

    public TreeNode<E> minimum(TreeNode<E> root){
        if (root.left==null)
             return root;
        else return minimum(root.left);
    }

    public boolean myDelete(E e){
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current!=null){
            if (e.compareTo(current.element)<0){
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element)>0){
                parent = current;
                current = current.right;
            } else break;
        }
        if (current == null){
            return false;
        }
        // Case 1 when deleted node has no left child
        if (current.left == null){
            current.element=current.right.element;
            current.left = current.right.left;
            current.right = current.right.right;
            size--;
        } else
            // Case 2 when deleted node has no right child
        if (current.right == null){
            current.element = current.left.element;
            current.left = current.left.left;
            if (current.left != null)
            current.right = current.left.right;
            else current.right = null;
            size --;
        } else {
            // Case 3 when deleted node has a both right and left child
            TreeNode<E> minimumOfRightSubTree = minimum(current.right);
            // Case 3.1 if minimum element of right subtree right child of deleted element
            if (current.right.element.compareTo(minimumOfRightSubTree.element)==0){
                current.element = minimumOfRightSubTree.element;
                current.right = minimumOfRightSubTree.right;
                current.left = minimumOfRightSubTree.left;
                size--;
            } else {
                // Case 3.2 if minimum of right subtree have a right child
                if (minimumOfRightSubTree.right!=null){
                    E currentElementValue = minimumOfRightSubTree.element;
                    minimumOfRightSubTree.element = minimumOfRightSubTree.right.element;
                    minimumOfRightSubTree.right = minimumOfRightSubTree.right.right;
                    if (minimumOfRightSubTree.right != null)
                    minimumOfRightSubTree.left = minimumOfRightSubTree.right.left;
                    current.element = currentElementValue;
                    size--;
                } else {
                    // Case 3.3 if minimum of right subtree does not have a child
                    current.element = minimumOfRightSubTree.element;
                    TreeNode<E> parentOfMinimumElement = null;
                    TreeNode<E> minimumElement = current.right;
                    while (minimumElement.left!=null){
                        parentOfMinimumElement = minimumElement;
                        minimumElement = minimumElement.left;
                    }
                    parentOfMinimumElement.left = null;
                    parentOfMinimumElement.right = null;
                    size--;
                }
            }

        }
        return true;
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current!=null){
            if (current.element.compareTo(e)<0){
                current=current.left;
            } else if (current.element.compareTo(e)>0){
                current=current.right;
            } else return true;
        }

        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root==null){
            root = createTreeNode(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current!=null){
                if (e.compareTo(current.element)<0){
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element)>0){
                    parent = current;
                    current = current.right;
                } else return false;
            }

            if (e.compareTo(parent.element)<0){
                    parent.left=createTreeNode(e);
                } else
                    parent.right=createTreeNode(e);
        }
        size++;
        return true;
    }

    private TreeNode<E> createTreeNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public boolean delete(E e) {

        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current!=null){
            if (e.compareTo(current.element)<0){
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element)>0){
                parent = current;
                current = current.right;
            } else break;
        }

        if (current == null){
            return false;
        }
        if (current.left==null){
            if (parent == null){
                parent = current.right;
            } else {
                if (e.compareTo(current.element)<0){
                    parent.left=current.right;
                } else parent.right = current.right;
            }
        } else {
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (current != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;
            if (current.element == rightMost.element){
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> arrayList = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null){
            arrayList.add(current);
            if (e.compareTo(current.element)<0){
                current = current.left;
            } else if (e.compareTo(current.element)>0){
                current = current.right;
            } else break;
        }
        return arrayList;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator<E extends Comparable<E>> implements Iterator<E>{

        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InorderIterator(){inorder();}

        private void inorder(){
            inorder((TreeNode<E>) root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext(){
            if (current<list.size()){
                return true;
            } else return false;
        }

        @Override
        public E next(){
            return list.get(current++);
        }

        @Override
        public void remove(){
            BinarySearchTree<E> binarySearchTree = new BinarySearchTree<>();
            binarySearchTree.myDelete(list.get(current));
        }

    }

    private class TreeNode<E>{
        private E element;

        private TreeNode<E> left;

        private TreeNode<E> right;

        public TreeNode(E element){
            this.element=element;
        }
    }
}
