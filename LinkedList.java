import java.util.Arrays;

public class LinkedList {
	private int size = 0;
	private Node head;
	public int size() {
		return size;
	}
	public void add(int index, String value) {
		if (index > size || index < 0) {throw new IndexOutOfBoundsException();}
		if (size == 0) {
			head = new Node(value, null, null);
		} else {
			if (index == 0) {
				Node newHead = new Node(value, null, head);
				head.previous = newHead;
				head = newHead;
			} else {
				int currentIndex = 0;
				for (Node curr = head; curr != null; curr = curr.next) {
					if (currentIndex == index-1) {
						curr.next = new Node(value, curr, null);
						break;
					}
					currentIndex++;
				}
			}
		}
		size++;
	}
	public String get(int index) {
		if (index > size) {return "";}
		int currentIndex = 0;
		for (Node curr = head; curr != null; curr = curr.next) {
			if (currentIndex == index) {
				return curr.value;
			}
			currentIndex++;
		}
		return "null";
	}
	public void remove(int index) {
		int currentIndex = 0;
		for (Node curr = head; curr != null; curr = curr.next) {
			if (currentIndex == index) {
				if (index == 0) {
					if (size > 1) {
						head = curr.next;
						curr = head;
					} else {
						head = null;
					}
				} else if (index == size-1){
					curr.previous.next = null;
				} else {
					curr.previous.next = curr.next;
					curr = curr.previous;
					curr.next.previous = curr;
				}
			}
			currentIndex++;
		}
		size--;
	}
	public void clear() {
		size = 0;
		head = null;
	}
	public void set(int index, String value) {
		int currentIndex = 0;
		for (Node curr = head; curr != null; curr = curr.next) {
			if (currentIndex == index) {
				curr.value = value;
				return;
			}
			currentIndex++;
		}
	}
	@Override
	public String toString() {
		String[] output = new String[size];
		int currentIndex = 0;
		for (Node curr = head; curr != null; curr = curr.next) {
			output[currentIndex] = curr.value;
			currentIndex++;
		}
		System.out.println(Arrays.toString(output));
		return Arrays.toString(output);
		
	}
	
	
}
