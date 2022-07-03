import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;


public class Traversals<T extends Comparable<? super T>> {


    public List<T> preorder(TreeNode<T> root) {
        List<T> tList = new ArrayList<>();
        if (root != null) {
            traversePre(root, tList);
            return tList;
        }
        return tList;
    }


    public List<T> inorder(TreeNode<T> root) {
        List<T> tList = new ArrayList<>();
        if (root != null) {
            traverseIn(root, tList);
            return tList;
        }
        return tList;
    }


    public List<T> postorder(TreeNode<T> root) {
        List<T> tList = new ArrayList<>();
        if (root != null) {
            traversePost(root, tList);
            return tList;
        }
        return tList;
    }

    private void traversePre(TreeNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            traversePre(node.getLeft(), list);
            traversePre(node.getRight(), list);

        }
        return;
    }


    private void traversePost(TreeNode<T> node, List<T> list) {
        if (node != null) {
            traversePost(node.getLeft(), list);
            traversePost(node.getRight(), list);
            list.add(node.getData());

        }
        return;
    }

    private void traverseIn(TreeNode<T> node, List<T> list) {
        if (node != null) {
            traverseIn(node.getLeft(), list);
            list.add(node.getData());
            traverseIn(node.getRight(), list);
        }
        return;
    }


}