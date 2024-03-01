
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        IDLList<Integer> idlList = new IDLList<Integer>();
        
        // Adding element to the list
        idlList.add(25);
        System.out.println("The first element of the list is: " + idlList.toString() + "\n");
        
        //Adding element to the head of the list
        idlList.add(0,7);
        System.out.println("After add 7 at the head, the list becomes " + idlList.toString() + "\n");
        
        //Adding element at index 1 of the list
        idlList.add(1,10);
        System.out.println("After add 10 at index 1, the list becomes " + idlList.toString() + "\n");
        
        //Appending element to the list
        idlList.append(44);
        System.out.println("After append, the list is : " + idlList.toString() + "\n");
        
        //Getting element at position 1
        System.out.println("Current list is: " + idlList.toString());
        System.out.println("Element at position 1 is : " + idlList.get(1) + "\n");

        // return the object at the head
        System.out.println("Current list is: " + idlList.toString());
        System.out.println("Head object in list: " + idlList.getHead() + "\n");

        // return the object at the tail
        System.out.println("Current list is: " + idlList.toString());
        System.out.println("Tail object in list: " + idlList.getLast() + "\n");

        // return the list size
        System.out.println("The current list size is: " + idlList.size() + "\n");

        // remove and return element at the head
        System.out.println("Now the list is: " + idlList.toString());
        System.out.println("The removed element is: " + idlList.remove());
        System.out.println("The current list is: " + idlList.toString() + "\n");

        // remove and return the element at the tail
        System.out.println("Now the list is: " + idlList.toString());
        System.out.println("The removed tail element is: " + idlList.removeLast());
        System.out.println("After remove, the current list is: " + idlList.toString() + "\n");

        // add two elements 35
        idlList.append(35);
        idlList.add(2, 35);
        System.out.println("Now the list is: " + idlList.toString());

        // remove the first occurrence of 35
        idlList.remove(35);
        System.out.println("After removed the first occurrence of 35, now the list is: "
                + idlList.toString());

	}

}
