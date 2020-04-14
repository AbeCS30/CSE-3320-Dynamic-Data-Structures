import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList implements ListIterator {
	private int size = 0;
	private Node head = null;
	private int currentIndex = -1;
	private Node previous = null;
	private Node next = null;
	private Node current = null;
	public int size() {
		return size;
	}
	public void add(int index, String value) {
		current = null;
		if (index > size || index < 0) {throw new IndexOutOfBoundsException();}
		if (size == 0) {
			head = new Node(value, null, null);
		} else {
			if (index == 0) {
				Node newHead = new Node(value, null, head);
				head.previous = newHead;
				head = newHead;
			} else {
				int currI = 0;
				for (Node curr = head; curr != null; curr = curr.next) {
					if (currI == index-1) {
						curr.next = (new Node(value, curr, null));
						break;
					}
					currI++;
				}
			}
		}
		size++;
	}
	public String get(int index) {
		int curIndex = 0;
		current = null;
		if (index > size) {return "";}
		for (Node curr = head; curr != null; curr = curr.next) {
			if (curIndex == index) {
				return curr.value;
			}
			curIndex++;
		}
		return "null";
	}
	public void remove(int index) {
		current = null;
		int curIndex = 0;
		for (Node curr = head; curr != null; curr = curr.next) {
			if (curIndex == index) {
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
			curIndex++;
		}
		size--;
	}
	public void clear() {
		size = 0;
		head = null;
	}
	public void set(int index, String value) {
		int curIndex = 0;
		for (Node curr = head; curr != null; curr = curr.next) {
			if (curIndex == index) {
				curr.value = value;
				return;
			}
			curIndex++;
		}
	}
	@Override
	public String toString() {
		String[] output = new String[size];
		int curIndex = 0;
		for (Node curr = head; curr != null; curr = curr.next) {
			output[curIndex] = curr.value;
			curIndex++;
		}
		System.out.println(Arrays.toString(output));
		return Arrays.toString(output);
		
	}
	@Override
	public void add(Object e) {
		Node newNode = new Node((String)e, null, null);
		current = null;
		size++;
		if (head == null) {
			head = newNode;
			previous = newNode;
			currentIndex = 0;
			return;
		} else {
			Node temp = next;
			next = newNode;
			if (previous != null) {
				previous.next = next;
				next.previous = previous;
			} 
			if (temp != null) {
				next.next = temp;
				temp.previous = next;
			}
			shiftNodes(1);
		}
		
	}
	@Override
	public boolean hasNext() {
		return next != null;
	}
	@Override
	public boolean hasPrevious() {
		return previous != null;
	}
	@Override
	public Object next() {
		if (next == null) {
			throw new NoSuchElementException();
		}
		String val = next.value;
		current = next;
		shiftNodes(1);
		return val;
	}
	@Override
	public int nextIndex() {
		return currentIndex + 1;
	}
	@Override
	public Object previous() {
		if (previous == null) {
			throw new NoSuchElementException();
		}
		current = previous;
		String val = previous.value;
		shiftNodes(-1);
		return val;
	}
	@Override
	public int previousIndex() {
		return currentIndex;
	}
	@Override
	public void remove() {
		if (current == null) {
			throw new IllegalStateException();
		}
		size--;
		if (current.next == null) {
			current.previous.next = null;
			next = null;
		} else if (current.previous == null) {
			current.previous = null;
			previous = null;
		} else {
			current.previous.next = current.next;
			current.next.previous =current.previous;
			previous = current.previous;
			next = current.next;
			currentIndex--;
		}
		current = null;
	}
	@Override
	public void set(Object e) {
		if (current == null) {
			throw new IllegalStateException();
		}
		current.value = (String)e;
	}
	private void shiftNodes(int dir) {
		if (dir == 1) {
			currentIndex++;
			previous = next;
			if (next.next != null) {
				next = next.next;
			} else {
				next = null;
			}
		} else {
			currentIndex--;
			next = previous;
			if (previous.previous != null) {
				previous = previous.previous;
			} else {
				previous = null;
			}
		}
	}
	public ListIterator listIterator() {
		currentIndex = -1;
		previous = null;
		next = head;
		return this;
	}
	
}
