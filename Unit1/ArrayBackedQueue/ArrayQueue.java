import java.util.NoSuchElementException;


public class ArrayQueue<T> {

    public static final int INITIAL_CAPACITY = 9;


    private T[] backingArray;
    private int front;
    private int size;


     
    public ArrayQueue() {
        // DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }


    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: some exception was thrown");
        }

        
        if (this.size == this.backingArray.length) {
            T[] newArray = (T[]) new Object[this.backingArray.length * 2];
            int index = this.front;
            for (int i = 0; i < this.size; i++) {
                index += 1;
                if (index == this.size) {
                    index = 0;
                }
                newArray[i] = this.backingArray[index];
            }
            this.backingArray = newArray;
            this.backingArray[this.size] = data;
            this.size++;
        }
        else {
            int index = (front + size) % backingArray.length;
            this.backingArray[index] = data;
            this.size++;
        }
        if (this.size == 1) {
            this.front = 0;
        }
        
    }
    


    public T dequeue() {
        if (this.size == 0) {
            throw new NoSuchElementException("Error: list is empty");
        }
        T ret = this.backingArray[this.front];
        this.backingArray[this.front] = null;
        this.front++;
        if (this.front == this.backingArray.length) {
            this.front = 0;
        }
        this.size--;
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