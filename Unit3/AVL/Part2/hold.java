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
            throw new NoSuchElementException("no null data");
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
                    dummy.setData(curr.getData());
                }
                else {
                    dummy.setData(curr.getData());
                }
            }
            else {

                AVLNode<T> dummy2 = new AVLNode<>(null);
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
                
            }




        }
        updateHeightAndBF(curr);
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
                //System.out.println(currentNode.getRight().getBalanceFactor());
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            System.out.println(currentNode.getRight().getBalanceFactor());
            currentNode = rotateLeft(currentNode);
        } else if (currentNode.getBalanceFactor() > 1) { // left heavy
            if (currentNode.getLeft().getBalanceFactor() <= -1) { // left-right rotation
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            //System.out.println(currentNode.getLeft().getBalanceFactor());
            currentNode = rotateRight(currentNode);
            //System.out.println(currentNode.getData());
        }
        //System.out.println(currentNode.getData() + "\n\n");

        return currentNode;
    }


    private void updateHeightAndBF(AVLNode<T> node) {

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

        if (node.getData().equals(2)) {
            System.out.println("val: " + node.getData() + " BF: " + node.getBalanceFactor() + " Height: " + node.getHeight() + " lheight: " + lHeight + " rheight: " + rHeight);

        }
        

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








    public static void main(String[] args) {
        AVLNode<Integer> zero = new AVLNode<>(0);
        AVLNode<Integer> two = new AVLNode<>(2);
        AVLNode<Integer> one = new AVLNode<>(1);
        AVLNode<Integer> seven = new AVLNode<>(7);
        AVLNode<Integer> four = new AVLNode<>(4);
        AVLNode<Integer> nine = new AVLNode<>(9);
        AVLNode<Integer> three = new AVLNode<>(3);
        AVLNode<Integer> five = new AVLNode<>(5);
        AVLNode<Integer> eight = new AVLNode<>(8);


       
        two.setLeft(one);
        two.setRight(seven);
        one.setLeft(zero);
        seven.setLeft(four);
        seven.setRight(nine);
        four.setLeft(three);
        four.setRight(five);
        nine.setLeft(eight);



        hold<Integer> avl = new hold<>();
        avl.setRoot(two);

      //  System.out.println("\n" + avl.getRoot().getHeight());
        avl.add(6);




       // avl.balance(six);

       System.out.println(avl.getRoot().getData());
       System.out.println(avl.getRoot().getLeft().getData());
       System.out.println(avl.getRoot().getRight().getData());
       System.out.println(avl.getRoot().getRight().getHeight());
        System.out.println(avl.getRoot().getRight().getLeft().getData());
        System.out.println(avl.getRoot().getRight().getLeft().getRight().getRight().getData());

       

    }

}