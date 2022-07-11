import java.util.NoSuchElementException;


public class hold<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;


    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("cant add null");
        }
        
        this.root = rAdd(data, this.root);
        System.out.println("Initial Root: " + this.root.getData());
        this.root = balance(this.root);
        System.out.println("After Balance Root: " + this.root.getData() + "\n\n");
    }


    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("no null data");
        }


        AVLNode<T> dummy = new AVLNode<>(null);

        this.root = rRemove(this.root, data, dummy);
        this.root = balance(this.root);
        return dummy.getData();

    }


    private void recurseBalance(AVLNode<T> curr) {

        balance(curr);

        if (curr.getLeft() != null) {
            balance(curr.getLeft());
        }

        if (curr.getRight() != null) {
            balance(curr.getRight());
        }



    }





    private AVLNode<T> rRemove(AVLNode<T> curr, T data, AVLNode<T> dummy) {

        if (curr == null) {
            throw new NoSuchElementException("no such element");
        }
        else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
        }
        else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
        }
        else { // data found
            this.size--;
            int numChilds = numChilds(curr);


            if (numChilds == 0) {
                dummy.setData(curr.getData());
                return null;
            }
            else if (numChilds == 1) {
                if (curr.getRight() != null) {
                    dummy.setData(curr.getRight().getData());
                    return curr.getRight();
                }
                else {
                    dummy.setData(curr.getLeft().getData());
                    return curr.getLeft();
                }
            }
            else {

                AVLNode<T> dummy2 = new AVLNode<>(null);
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
                
            }




        }
        updateHeightAndBF(curr);
        curr = balance(curr);
        return curr;

    }



    private AVLNode<T> removeSuccessor(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        }
        else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
            return curr;
        }
    }



    private int numChilds(AVLNode<T> node) {
        int num = 0;

        if (node.getLeft() != null) {
            num += 1;
        }

        if (node.getRight() != null) {
            num += 1;
        }

        return num;


    }


    private AVLNode<T> rAdd(T data, AVLNode<T> node) {
        if (node == null) {
            this.size++;
            AVLNode<T> newNode = new AVLNode<T>(data);
            updateHeightAndBF(newNode);
            return newNode;
        }
        else if (data.compareTo(node.getData()) > 0) {
            node.setRight(rAdd(data, node.getRight()));
        }
        else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(rAdd(data, node.getLeft()));
        }
        updateHeightAndBF(node);
        node = balance(node);
        return node;

    }









    


    private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        AVLNode<T> save = currentNode.getRight();
        currentNode.setRight(save.getLeft());
        save.setLeft(currentNode);
        if (this.root == currentNode) {
            this.root = save;
        }
        updateHeightAndBF(currentNode);
        updateHeightAndBF(save);
        return save;
    }


    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        AVLNode<T> save = currentNode.getLeft();
        //System.out.println(save.getData());
        currentNode.setLeft(save.getRight());
        save.setRight(currentNode);
        if (this.root == currentNode) {
            this.root = save;
        }
        updateHeightAndBF(currentNode);
        updateHeightAndBF(save);
        return save;
    }


    private AVLNode<T> balance(AVLNode<T> currentNode) {
        updateHeightAndBF(currentNode);

        if (currentNode.getBalanceFactor() < -1) { // right heavy
            if (currentNode.getRight().getBalanceFactor() >= 1) { // right-left rotation
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if (currentNode.getBalanceFactor() > 1) { // left heavy
            if (currentNode.getLeft().getBalanceFactor() <= -1) { // left-right rotation
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }

        return currentNode;
    }


    private void updateHeightAndBF(AVLNode<T> node) {
        if (node == null) {
            return;
        }

        int rHeight = 0;
        int lHeight = 0;

        lHeight = getHeight(node.getLeft()) + 1;
        rHeight = getHeight(node.getRight()) + 1;




        if (rHeight > lHeight) {
            node.setHeight(rHeight);
        }
        else {
            node.setHeight(lHeight);
        }

        node.setBalanceFactor(lHeight - rHeight);
        

    }


    private int getHeight(AVLNode<T> node) {
        if (node == null) {
            return -0;
        }
        else {

            int lHeight = getHeight(node.getLeft());
            int rHeight = getHeight(node.getRight());

            if (lHeight > rHeight) {
                return lHeight + 1;
            }
            else {
                return rHeight + 1;
            }

        }
        

        
    }

    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }


    private void setRoot(AVLNode<T> node) {
        this.root = node;
    }










}