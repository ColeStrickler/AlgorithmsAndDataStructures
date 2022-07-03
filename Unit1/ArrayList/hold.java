

    public static void main(String[] args) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	list.addToFront(2);
    	list.addToFront(3);
    	list.addToBack(9);
    	list.addToFront(5);
    	list.addToFront(2);
    	list.addToFront(3);
    	list.addToBack(9);
    	list.addToFront(5);
    	list.addToFront(5);
    	list.removeFromFront();



    	





    	Object[] result = list.getBackingArray();


    	for (int i = 0; i < result.length; i++) {
    		System.out.print(result[i] + ",");

    	}  
		System.out.print("\n");

    }
