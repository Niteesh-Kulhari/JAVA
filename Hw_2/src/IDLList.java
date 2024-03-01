import java.util.ArrayList;



public class IDLList<E> {
	
	// To construct a new node
	private static class Node<E>{
		
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		//Constructor
		private Node(E elem) {
			this.data = elem;
		}
	}
	
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	// Indexed doubly linked list for quick access
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<>();
	}
	
	
	// Add element in the DLL
	public boolean add(int index, E elem) {
		
		if(index<0 || index >= indices.size()) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		
		if(index==0) {
			add(elem);
			return true;
		}
		else {
			Node<E> node = indices.get(index);
			Node<E> newItem = new Node<>(elem);
			
			newItem.next = node;
			newItem.prev = node.prev;
			node.prev.next = newItem;
			node.prev = newItem;
			size++;
			
			
			
			indices.add(index, newItem);
			return true;
		}
	}
	
	// Add item at the head of DLL
	public boolean add(E elem) {
		if(head==null) {
			head = new Node<>(elem);
			tail = head;
			size++;
			
			indices.add(0,head);
			return true;
		}
		else {
			Node<E> firstItem = new Node<>(elem);
			firstItem.next = head;
			head.prev = firstItem;
			head = firstItem;
			size++;
			
			indices.add(0,firstItem);
			return true;
		}
	}
	
	// Add element at the last of the list
	public boolean append(E elem) {
		
		if(head==null) {
			add(elem);
			return true;
		}
		else{
			Node<E> lastNode = new Node<>(elem);
			tail.next = lastNode;
			lastNode.prev = tail;
			tail = lastNode;
			size++;
			
			indices.add(lastNode);
			return true;
		}
	}
	
	//Get the value at given index
	public E get(int index) {
		return indices.get(index).data;
	}
	
	//Get the head of list
	public E getHead() {
		return head.data;
	}
	
	//Get the value of last link
	public E getLast() {
		return tail.data;
	}
	
	//To get the size of linked list
	public int size() {
		return indices.size();
	}
	
	//To remove head element
	public E remove() {
		if(head == null) {
			return null;
		}
		
		Node<E> temp = head;
		
		head = head.next;
		head.prev = null;
		size--;
		
		indices.remove(temp);
		
		return temp.data;
	}
	
	//To remove last element
	public E removeLast() {
		if(tail==null) {
			return null;
		}
		
		Node<E> temp = tail;
		
		tail = tail.prev;
		tail.next = null;
		size--;
		
		indices.remove(temp);
		return temp.data;
	}
	
	//To remove at a given index
	public E removeAt(int index) {
		
		if (index < 0 || index >= indices.size()) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		
		if(index==0) {
			return remove();
		}
		if(index==indices.size()-1) {
			return removeLast();
		}
		
		Node<E> removeNode = indices.get(index);
		removeNode.prev.next = removeNode.next;
		removeNode.next.prev = removeNode.prev;
		size--;
		
		indices.remove(removeNode);
		return removeNode.data;
	}
	
	//To remove a given element
	public boolean remove(E elem) {
		for(int i=0; i<indices.size(); i++) {
			if(indices.get(i).data==elem) {
				removeAt(i);
				return true;
			}
		}
		
		return false;
	}
	
	
//	public String toString() {
//		
//		Node<E> cur = head;
//		String rs = "";
//		
//		while(cur!=null) {
//			rs = "" + cur.data.toString();
//			cur = cur.next;
//		}
//		
//		return rs;
//	}
	
	public String toString() {
		Node<E> nodeRef = head;
		StringBuilder result = new StringBuilder();
		while (nodeRef != null) {
			result.append(nodeRef.data);
			if (nodeRef.next != null) {
				result.append(" --> ");
			}
			nodeRef = nodeRef.next;
		}
		return result.toString();
	}
	

}
