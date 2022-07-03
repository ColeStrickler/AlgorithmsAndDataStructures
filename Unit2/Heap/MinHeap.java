import java.util.NoSuchElementException;
import java.lang.*;



 
public class MinHeap<T extends Comparable<? super T>> {

    public static final int INITIAL_CAPACITY = 13;
    private T[] backingArray;
    private int size;


    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }


    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("cant add null");
        }
        if (this.size == 0) {
            this.backingArray[1] = data;
            this.size++;
            return;
        }
        else if (this.size == (backingArray.length - 1)) {
            T[] temp = (T[]) new Comparable[this.backingArray.length * 2];
            
            for (int i = 1; i < this.backingArray.length; i++) {
                temp[i] = this.backingArray[i];
            }


            this.backingArray = temp;
        }
        this.size++;
        this.backingArray[size] = data;
        this.addReorder(size);
    }



    private void addReorder(int index) {
        if (index / 2 == 0) {return;} // FUCKING UP HERE
        if (backingArray[index].compareTo(backingArray[index / 2]) < 0) {
            T temp = backingArray[index];
            backingArray[index] = backingArray[index / 2];
            backingArray[index / 2] = temp;
        }
        if (Math.floor(index / 2) >= 1) {

            addReorder(index / 2);
        }


    }


    private void removeReorder(int index) {
        if ((2 * index + 1) <= backingArray.length - 1 && backingArray[2 * index + 1] != null) {
            if (backingArray[2 * index + 1].compareTo(backingArray[2 * index]) > 0) { // if rchild > lchild

                if (backingArray[index].compareTo(backingArray[2 * index]) > 0) { // then switch lchild
                    T temp = backingArray[index];
                    backingArray[index] = backingArray[index * 2];
                    backingArray[index * 2] = temp;
                    removeReorder(index * 2);
                }

            }
            else {

                if (backingArray[index].compareTo(backingArray[2 * index + 1]) > 0) { // switch r child
                    T temp = backingArray[index];
                    backingArray[index] = backingArray[index * 2 + 1];
                    backingArray[index * 2 + 1] = temp;
                    removeReorder(index * 2 + 1);
                }

            }
        }
        else if ((2 * index) <= backingArray.length - 1 && backingArray[2 * index] != null) { // only has an lchild

            if (backingArray[index].compareTo(backingArray[index * 2]) > 0) {
                T temp = backingArray[index];
                backingArray[index] = backingArray[index * 2];
                backingArray[index * 2] = temp;
                removeReorder(index * 2);
            }

        }
    }


    public T remove() {
        if (this.size == 0) {
            throw new NoSuchElementException("empty array");
        }
        T ret = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        this.size--;
        removeReorder(1);
        return ret;

    }

    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }


    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }





}