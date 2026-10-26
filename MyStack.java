public class MyStack<E extends Comparable<? super E>> {
    private final MyDblLinkedList<E> list = new MyDblLinkedList<E>();

    // PRE: Receives an item.
    // POST: Adds the item to the top of the stack.
    public void push(E item) {
        list.addFirst(item);
    }

    // PRE: Stack may be empty.
    // POST: Removes and returns the top item, or null if empty.
    public E pop() {
        return list.removeFirst();
    }

    // PRE: Stack may be empty.
    // POST: Returns the top item without removing it, or null if empty.
    public E peek() {
        return list.getFirst();
    }

    // PRE: None.
    // POST: Returns the number of items in the stack.
    public int size() {
        return list.size();
    }

    // PRE: None.
    // POST: Returns true if the stack is empty.
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // PRE: None.
    // POST: Removes every item.
    public void clear() {
        list.clear();
    }

    // PRE: None.
    // POST: Returns a string representation of the stack.
    public String stackString() {
        return list.toString();
    }
}