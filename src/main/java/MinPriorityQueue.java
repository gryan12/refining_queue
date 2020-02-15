import java.util.Arrays;

public class MinPriorityQueue<T extends Comparable<T>> {

    private T[] data;
    private int back; //greatest index of any element in data

    //front always 1. 0 is left unused out to make nav func simpler
    private static int FRONT = 1;

    //starting 20 seems reasonable
    int capacity = 20;

    public MinPriorityQueue() {
        data = (T[]) new Comparable[capacity];
        back = 0;
    }

    public int size() {
        return back;
    }

    public void add(T elem) {

        if (back == capacity - 1) {
            doubleCapacity();
        }
        data[++back] = elem;

        //list was prev empty, no need to sort
        if (back == 1) {
            return;
        }

        int current = back;

        int parent = current/2;

        while (current > 1 && data[current].compareTo(data[parent]) < 0) {
            swap(current, parent);
            current = parent;
            parent = parent/2;
        }

    }

    public T remove() {
        T datum = data[FRONT];
        int originalIndex = back;
        swap(FRONT, back--);
        data[originalIndex] = null;

        int current = FRONT;
        int child = 2*current;
        while (child <= back) {
            if (child < back && (data[child].compareTo(data[child+1]) < 0)) {
                child++;
            }

            if (data[current].compareTo(data[child]) > 0) {
                break;
            }
            swap(current, child);
            current = child;
        }
        return datum;
    }

    public boolean isEmpty() {
        return back == 0;
    }

    private void swap(int x, int y) {
        T tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
    }

    private void doubleCapacity() {
        capacity *= 2;
        data = Arrays.copyOf(data, capacity);
    }

    public T peek() {
        return data[FRONT];
    }

    public int getCapacity() {
        return capacity;
    }

}
