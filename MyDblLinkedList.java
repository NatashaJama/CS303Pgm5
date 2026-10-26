public class MyDblLinkedList<E extends Comparable<? super E>>
        implements MyList<E> {

    private class Node {
        E element;
        Node next;
        Node prev;

        Node(E element) {
            this.element = element;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyDblLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // PRE: None.
    // POST: Returns the number of elements in the list.
    public int size() {
        return size;
    }

    // PRE: None.
    // POST: Returns true if the list contains no elements.
    public boolean isEmpty() {
        return size == 0;
    }

    // PRE: None.
    // POST: Adds the element to the front of the list.
    public void addFirst(E element) {
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    // PRE: None.
    // POST: Adds the element to the end of the list.
    public void addLast(E element) {
        Node newNode = new Node(element);

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    // PRE: None.
    // POST: Returns the first element, or null if the list is empty.
    public E getFirst() {
        if (head == null) {
            return null;
        }

        return head.element;
    }

    // PRE: None.
    // POST: Returns the last element, or null if the list is empty.
    public E getLast() {
        if (tail == null) {
            return null;
        }

        return tail.element;
    }

    // PRE: None.
    // POST: Removes and returns the first element,
    //       or returns null if the list is empty.
    public E removeFirst() {
        if (head == null) {
            return null;
        }

        E element = head.element;
        head = head.next;
        size--;

        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }

        return element;
    }

    // PRE: None.
    // POST: Removes and returns the last element,
    //       or returns null if the list is empty.
    public E removeLast() {
        if (tail == null) {
            return null;
        }

        E element = tail.element;
        tail = tail.prev;
        size--;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        return element;
    }

    // PRE: None.
    // POST: Removes all elements from the list.
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // PRE: None.
    // POST: Returns a string representation of the list.
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node current = head;

        while (current != null) {
            result.append(current.element);

            if (current.next != null) {
                result.append(", ");
            }

            current = current.next;
        }

        result.append("]");
        return result.toString();
    }
}