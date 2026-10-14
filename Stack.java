import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// ============================================================
// BAD VERSION: inheritance — Stack IS-NOT-A ArrayList
// ============================================================
// This compiles and runs, but it exposes every ArrayList method
// (add(int, E), get(int), remove(int), set(int, E), clear(), ...)
// which lets callers break the LIFO invariant.
class BadStack<T> extends ArrayList<T> {

    public void push(T item) {
        add(item);
    }

    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return remove(size() - 1);
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return get(size() - 1);
    }
}

// ============================================================
// GOOD VERSION: composition — Stack HAS-A List
// ============================================================
// The internal list is private. Only push/pop/peek/isEmpty/size
// are exposed. The LIFO invariant is enforced by the class itself.
class Stack<T> {

    private final List<T> items = new ArrayList<>(); // HAS-A

    public void push(T item) {
        items.add(item);
    }

    public T pop() {
        if (items.isEmpty()) throw new NoSuchElementException("Stack is empty");
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (items.isEmpty()) throw new NoSuchElementException("Stack is empty");
        return items.get(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }
}

public class StackDemo {
    public static void main(String[] args) {

        System.out.println("=== BAD: inheritance version ===");
        BadStack<Integer> bad = new BadStack<>();
        bad.push(1);
        bad.push(2);
        bad.push(3);
        System.out.println("Stack after pushes: " + bad);

        // These should NOT be possible on a stack:
        bad.add(0, 999);          // insert in the middle
        bad.set(2, 777);          // replace mid-stack
        bad.remove(0);            // remove from the bottom
        System.out.println("After misuse:        " + bad);
        System.out.println("LIFO invariant broken: pop() returns " + bad.pop()
                + " but the stack was tampered with");

        System.out.println();
        System.out.println("=== GOOD: composition version ===");
        Stack<Integer> good = new Stack<>();
        good.push(1);
        good.push(2);
        good.push(3);
        System.out.println("peek: " + good.peek());
        System.out.println("pop:  " + good.pop());
        System.out.println("pop:  " + good.pop());
        System.out.println("size: " + good.size());

        // good.add(0, 999); // COMPILE ERROR — no such method
        // good.get(0);      // COMPILE ERROR — no such method
    }
}