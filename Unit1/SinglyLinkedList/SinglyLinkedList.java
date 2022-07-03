import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: some exception was thrown");
        }
        if (this.size == 0) {
            this.head = new SinglyLinkedListNode<T>(data, null);
            this.tail = this.head;
            this.size++;
        }
        else {
            SinglyLinkedListNode<T> temp = new SinglyLinkedListNode<T>(data, this.head);
            if (size == 1) {
                this.tail = this.head;
            }
            this.head = temp;
            this.size++;
        }


    }

    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: some exception was thrown");
        }
        if (this.size == 0) {
            SinglyLinkedListNode<T> temp = new SinglyLinkedListNode<T>(data, null);
            this.head = temp;
            this.tail = temp;
            this.size++;
        }
        else {
            SinglyLinkedListNode<T> temp = new SinglyLinkedListNode<T>(data, null);
            this.tail.setNext(temp);
            this.tail = temp;
            this.size++;
        }

    }

     
    public T removeFromFront() {
        if (this.size == 0) {
            throw new NoSuchElementException("Error: list is empty");
        }
        else if (this.size == 1) {
            this.head.setNext(null);
            T val = this.head.getData();
            this.head = null;
            this.tail = null;
            this.size--;
            return val;
        }
        else {
            SinglyLinkedListNode<T> temp = this.head.getNext();
            this.head.setNext(null);
            T val = this.head.getData();
            this.head = temp;
            this.size--;
            return val;
        }
    }


    public T removeFromBack() {
         if (this.size == 0) {
            throw new NoSuchElementException("Error: list is empty");
        }
        else if (this.size == 1) {
            this.head.setNext(null);
            T val = this.head.getData();
            this.head = null;
            this.tail = null;
            this.size--;
            return val;
        }


        SinglyLinkedListNode<T> curr = this.head;

        while (curr.getNext() != this.tail) {
            curr = curr.getNext();
        }
        T val = this.tail.getData();
        this.tail = curr;
        this.tail.setNext(null);
        this.size--;
        return val;
    }


    
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

     
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }


    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }








}