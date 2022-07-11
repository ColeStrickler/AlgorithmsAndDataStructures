public class hold<T extends Comparable<? super T>> {


    public void updateHeightAndBF(AVLNode<T> currentNode) {
        int rHeight = 0;
        int lHeight = 0;

        lHeight = getHeight(currentNode.getLeft(), rHeight, lHeight);
        rHeight = getHeight(currentNode.getRight(), rHeight, 0);


        //System.out.println("\n");
        System.out.println(currentNode.getData() + " lHeight,rHeight = " + lHeight + "," + rHeight);
        if (rHeight > lHeight) {
            currentNode.setHeight(rHeight + 1);
        }
        else {
            currentNode.setHeight(lHeight + 1);
        }
        

        currentNode.setBalanceFactor(lHeight - rHeight);
        System.out.println("val:" + currentNode.getData() + " Height: " + currentNode.getHeight() + " BF: " + currentNode.getBalanceFactor());



    }



    private int getHeight(AVLNode<T> node, int rHeight, int lHeight) {
        if (node == null) {
            return -1;
        }

        if (node.getData().equals(6)) {

            System.out.println(node.getData() + " lHeight,rHeight = " + lHeight + "," + rHeight);


        }





        if (node.getRight() == null && node.getLeft() == null) {
            if (rHeight > lHeight) {
                return rHeight;
            }
            else {
               // System.out.println(node.getData() + " lHeight,rHeight = " + lHeight + "," + rHeight);
                return lHeight;
            }
        }
        else {


            if (node.getLeft() != null) {
                lHeight = getHeight(node.getLeft(), rHeight, lHeight + 1);
            }
            
            if (node.getRight() != null) {
                rHeight = getHeight(node.getRight(), rHeight + 1, lHeight);
            }
            
            


            if (rHeight > lHeight) {
                return rHeight;
            }
            else {
                return lHeight;
            }

        }
    }




    public AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        AVLNode<T> save = currentNode.getRight();
        currentNode.setRight(save.getLeft());
        save.setLeft(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(save);
        return save;
    }


    public AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        AVLNode<T> save = currentNode.getLeft();
        currentNode.setLeft(save.getRight());
        save.setRight(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(save);
        return save;
    }

    public AVLNode<T> balance(AVLNode<T> currentNode) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        /* First, we update the height and balance factor of the current node. */
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




    public static void main(String[] args) {
        AVLNode<Integer> five = new AVLNode<>(5);
        AVLNode<Integer> one = new AVLNode<>(1);
        AVLNode<Integer> six = new AVLNode<>(6);
        AVLNode<Integer> zero = new AVLNode<>(0);
        AVLNode<Integer> three = new AVLNode<>(3);
        AVLNode<Integer> two = new AVLNode<>(2);
        AVLNode<Integer> four = new AVLNode<>(4);






        five.setLeft(one);
        five.setRight(six);
        one.setLeft(zero);
        one.setRight(three);
        three.setLeft(two);
        three.setRight(four);





        hold<Integer> avl = new hold<>();


        avl.balance(five);

        
    }



    
}