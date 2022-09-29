import java.util.Arrays;

/**
 * A class that implements the ADT maxheap by using an array.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 */
public final class MaxHeap implements MaxHeapInterface {
    private int[] heap; // Array of heap entries; ignore heap[0]
    private int lastIndex; // Index of last entry and number of entries
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    private int swapCounter = 0;

    public MaxHeap() {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    public MaxHeap(int initialCapacity) {
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else // Is initialCapacity too big?
            checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        int[] tempHeap = (int[]) new int[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        integrityOK = true;
    } // end constructor

    public void add(int newEntry) {
        checkIntegrity();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ((parentIndex > 0) && (newEntry > heap[parentIndex])) {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
            swapCounter++;
        } // end while
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    } // end add

    public int removeMax() {
        checkIntegrity();
        int root = 0;
        if (!isEmpty()) {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        } // end if
        return root;
    } // end removeMax

    public int getMax() {
        checkIntegrity();
        int root = 0;
        if (!isEmpty())
            root = heap[1];
        return root;
    } // end getMax

    public boolean isEmpty() {
        return lastIndex < 1;
    } // end isEmpty

    public int getSize() {
        return lastIndex;
    } // end getSize

    public void clear() {
        checkIntegrity();
        while (lastIndex > -1) {
            heap[lastIndex] = 0;
            lastIndex--;
        } // end while
        lastIndex = 0;
    } // end clear

    // Private methods

    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("Object is corrupt");
        }

    }// end checkIntegrity

    private void ensureCapacity() {
        if (lastIndex >= heap.length) {
            int newCapacity = 2 * heap.length;
            checkCapacity(newCapacity);
            heap = Arrays.copyOf(heap, newCapacity);
        }
    }// end ensureCapacity

    private void checkCapacity(int x) {
        if (x > MAX_CAPACITY) {
            throw new IllegalStateException(
                    "Attempted to create a max heap that exceeds the default dcapacity of " + MAX_CAPACITY);
        }
    }// end checkCapacity

    private void reheap(int x) {
        boolean done = false;
        int orphan = heap[x];
        int leftChildIndex = 2 * x;
        while (!done && (leftChildIndex <= lastIndex)) {
            int largerChildIndex = leftChildIndex; // Assume larger
            int rightChildIndex = leftChildIndex + 1;
            swapCounter++;

            if ((rightChildIndex <= lastIndex) && heap[rightChildIndex] > (heap[largerChildIndex])) {
                largerChildIndex = rightChildIndex;
            } // end if

            if (orphan < (heap[largerChildIndex])) {
                heap[x] = heap[largerChildIndex];
                x = largerChildIndex;
                leftChildIndex = 2 * x;
            } else
                done = true;
        } // end while
        heap[x] = orphan;
    }// end reheap

    public void optimalHeap(int[] maxHeap) {
        lastIndex = maxHeap.length;
        checkIntegrity();
        swapCounter = 0;
        for (int i = 0; i < maxHeap.length; i++) {
            heap[i + 1] = maxHeap[i];
        } // end for
        for (int j = lastIndex / 2; j > 0; j--) {
            reheap(j);
        } // end for
    }// end optimalHeap

    public int heapSequential(int[] maxHeap) {
        checkIntegrity();
        swapCounter = 0;
        for (int i = 0; i < maxHeap.length; i++) {
            int j = maxHeap[i];
            add(j);
        }
        return swapCounter;
    }// end heapSequential

    public void print() {
        for (int i = 1; i < lastIndex + 1; i++) {
            System.out.println(heap[i]);
        }
    }// end print

    public MaxHeap(MaxHeap another) {
        this.heap = another.heap;
    }

    public int getSwapCount() {
        return swapCounter;
    } // end getSwapCount

    public int[] printTen() {
        int ten[] = new int[10];
        for (int i = 0; i < ten.length; i++) {
            ten[i] = heap[i + 1];
        }
        return ten;
    }
    //end printTen
}// end MaxHeap