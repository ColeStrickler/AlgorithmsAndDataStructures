import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {


    private BSTNode<T> root;
    private int size;

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("cant add null data");
        }
        if (root.getData() == null) {
            this.size++;
            this.root.setData(data);
        }
        else {
            this.size++;
            traverseAndAdd(data, this.root);
        }


    }





    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    private void traverseAndAdd(T data, BSTNode<T> curNode) {

        if (curNode.getData().compareTo(data) > 0) {
            if (curNode.getLeft() == null) {
                BSTNode<T> newNode = new BSTNode<>(data);
                curNode.setLeft(newNode);
                return;
            }
            else {
                traverseAndAdd(data, curNode.getLeft());
            }
            
        }
        else if (curNode.getData().compareTo(data) < 0){
            if (curNode.getRight() == null) {
                BSTNode<T> newNode = new BSTNode<>(data);
                curNode.setRight(newNode);
                return;
            }
            else {
                traverseAndAdd(data, curNode.getRight());
            }
            
        }
        else {
            return;
        }

    }




    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("cant add null data");
        }
        if (this.root == null) {
            throw new NoSuchElementException("no such element");
        }
        this.size--;
        BSTNode<T> dummyNode = new BSTNode<T>(null);
        this.root = rHelper(this.root, data, dummyNode);
        return dummyNode.getData();
    }

    private BSTNode<T> rHelper(BSTNode<T> node, T data, BSTNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("no such element");
        }


        if (data == node.getData()) {
            dummy.setData(data);
            int numChilds = 0;
            if (node.getLeft() != null) {
                numChilds++;
            }
            if (node.getRight() != null) {
                numChilds++;
            }

            if (numChilds == 0) {
                return null;
            }
            else if (numChilds == 1) {
                if (node.getRight() != null) {
                    return node.getRight();
                }
                else {
                    return node.getLeft();
                }
            }
            else {

                BSTNode<T> temp = new BSTNode<>(null);
                node.setRight(hSuccessor(node.getRight(), temp));
                //return hSuccessor(node.getRight(), temp);
                node.setData(temp.getData());
            }


        }
        else if (node.getData().compareTo(data) > 0) {
            node.setLeft(rHelper(node.getLeft(), data, dummy));
           // return node;
        }
        else if (node.getData().compareTo(data) < 0) {
            node.setRight(rHelper(node.getRight(), data, dummy));
           // return node;
        }

        return node;
    }


    private BSTNode<T> hSuccessor(BSTNode<T> node, BSTNode<T> temp) {
        if (node.getLeft() == null) {
            temp.setData(node.getData());
            return node.getRight();
        }
        else {
            node.setLeft(hSuccessor(node.getLeft(), temp));
            return node;
        }

    }

    
       


    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}