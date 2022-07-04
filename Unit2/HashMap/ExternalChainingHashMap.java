import java.util.NoSuchElementException;


public class ExternalChainingHashMap<K, V> {


    public static final int INITIAL_CAPACITY = 13;


    public static final double MAX_LOAD_FACTOR = 0.67;


    private ExternalChainingMapEntry<K, V>[] table;
    private int size;


    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }


    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("can't add null");
        }

        if (this.checkKey(key) != null) {
            ExternalChainingMapEntry<K, V> temp = this.checkKey(key);
            temp.setValue(value);
            return value;
        }

        this.size++;

        if (calcLoadFactor() > this.MAX_LOAD_FACTOR) {
            resizeBackingTable((table.length * 2) + 1);
        }


        ExternalChainingMapEntry<K, V> entry = new ExternalChainingMapEntry<>(key, value, null);

        
        int initIndex = key.hashCode() % table.length;

        if (table[initIndex] == null) {
            table[initIndex] = entry;
        }
        else {
            entry.setNext(table[initIndex]);
            table[initIndex] = entry;
        }
        return value;

        }


    


    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("cant remove null");
        }
        else if (this.size == 0) {
            throw new NoSuchElementException("empty array");
        }

        int index = key.hashCode() % table.length;

        ExternalChainingMapEntry<K, V> check = table[index];


        int depth = 0;

        while (check != null) {

            if (check.getKey() == key && depth == 0) { // if removing from head
                table[index] = check.getNext();
                check.setNext(null);
                this.size--;
                return check.getValue();
            }

            if (check.getNext().getKey() == key) {
                ExternalChainingMapEntry<K, V> next = check.getNext();
                check.setNext(next.getNext());
                next.setNext(null);
                this.size--;
                return next.getValue();
            }
            check = check.getNext();

        }

        return null;




    }


    private void resizeBackingTable(int length) {
        ExternalChainingMapEntry<K, V>[] temp = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                ExternalChainingMapEntry<K, V> entry = table[i];

                while(entry != null) {
                    K key = entry.getKey(); // extract key from entry in original table
                    int initIndex  = key.hashCode() % temp.length; // compute the initial index
                    if (temp[initIndex] == null) {
                        temp[initIndex] = entry;
                    }
                    else {
                        entry.setNext(temp[i]);
                        temp[i] = entry;
                    }
                    entry = entry.getNext();
                }

            }

        }
        this.table = temp;

    }





    private ExternalChainingMapEntry<K, V> checkKey(K key) {
        int index = key.hashCode() % table.length;
        ExternalChainingMapEntry<K, V> check = table[index];


        while(check != null) {
            if (check.getKey() == key) {
                return check;
            }
            check = check.getNext();

        }
        return null;

    }


    private double calcLoadFactor() {
        return (double)this.size / (double)this.table.length;
    }


    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}