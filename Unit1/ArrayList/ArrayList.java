import java.util.NoSuchElementException;

public class ArrayList<T> {

	public static final int INITIAL_CAPACITY = 9;
	private T[] backingArray; // new <T>[this.INITIAL_CAPACITY];
	private int size;







	public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }


	public void addToFront(T val) {
		if (val == null) {
			throw new IllegalArgumentException("Error: some exception was thrown");
		}


		if (this.size == 0) {
			this.backingArray[0] = val;
			this.size++;
		}
		else {
			if (this.size == this.backingArray.length) {
				T[] newArray = (T[]) new Object[backingArray.length * 2];
				for (int i = 0; i < backingArray.length; i++) {
					newArray[i+1] = backingArray[i];
				}
				newArray[0] = val;
				this.backingArray = newArray;
				this.size++;
			}
			else {
				for (int i = size; i > 0; i--) {
					T temp = this.backingArray[i-1];
					this.backingArray[i] = temp;
				}
				this.backingArray[0] = val;
				this.size++;
		}
		}
	}


	public void addToBack(T val) {
		if (val == null) {
			throw new IllegalArgumentException("Error: some exception was thrown");
		}


		if (this.size == 0) {
			this.backingArray[0] = val;
			this.size++;
		}
		else {
			if (this.backingArray.length == size) {
				T[] newArray = (T[]) new Object[backingArray.length * 2];
				for (int i = 0; i < backingArray.length; i++) {
					newArray[i] = backingArray[i];
				}
				newArray[this.size] = val;
				this.backingArray = newArray;
				this.size++;
			}
			else {
				this.backingArray[this.size] = val;
				this.size++;
			}



		}
	}




	public T removeFromFront() {
		if (this.size == 0) {
			throw new NoSuchElementException("Error: list is empty");
		}


		if (size == 1) {
			T retVal = this.backingArray[0];
			this.backingArray[0] = null;
			this.size--;
			return retVal;
		}
		else {
			T retVal = this.backingArray[0];
			for (int i = 0; i < this.size; i++) {
				try{
					if (i < this.size - 1) {
						this.backingArray[i] = this.backingArray[i + 1];	
					}
					else {
						this.backingArray[i] = null;
					}
				} catch(Exception e) {
					continue;
				}
			}
			this.size--;
			return retVal;
		}
	}


	public T removeFromBack() {
		if (this.size == 0) {
			throw new NoSuchElementException("Error: list is empty");
		}

		T retVal = this.backingArray[size - 1];
		this.backingArray[size - 1] = null;
		this.size--;
		return retVal;
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



