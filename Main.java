import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws IOException {
        // This reads the .txt file and caopies it into the array
        File file = new File("data_sorted.txt");
        Scanner sc = new Scanner(file);
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data_sorted.txt"))) {
            while (bufferedReader.readLine() != null)
                count++;
        }
        int[] array = new int[count];
        int i = 0;
        while (sc.hasNextInt())
            array[i++] = sc.nextInt();
        sc.close();
        // end of reading from the .txt file

        PrintWriter printWriter = new PrintWriter("output_file.txt");

        // *********************** Sequential - not optimal ***********************
        MaxHeap seqHeap = new MaxHeap(count); // creates an object
        seqHeap.heapSequential(array);// orders it sequentially
        System.out.println("----------------------");
        System.out.println("This is the sequential order:");
        seqHeap.print(); 
        // end console order

        // adds to output file
        printWriter.print("Heap built using sequential insertions: ");
        int first10Seq[] = seqHeap.printTen();
        for (i = 0; i < first10Seq.length; i++) {
            printWriter.print(first10Seq[i] + ",");
        }
        printWriter.println();
        printWriter.println();
        printWriter.print("Number of swaps in the heap creation: ");
        printWriter.println(seqHeap.getSwapCount()); //Returns number of swaps in the heap creation
        printWriter.println();
        // printWriter.close();// end output addition

        // #of swaps
        System.out.println("----------------------");
        System.out.println("This is the number of Swaps:");
        System.out.println(seqHeap.getSwapCount());
        // end s# of swaps

        // removes 10
        int j = 0;
        while (j != 10) {
            seqHeap.removeMax();
            j++;
        }
        System.out.println("----------------------");
        System.out.println("This is the sequential order after removing 10:");
        seqHeap.print();
        // end removes 10

        // adds to putput file
        printWriter.print("Heap after 10 removals: ");
        int first10SeqRemove[] = seqHeap.printTen();
        for (i = 0; i < first10SeqRemove.length; i++) {
            printWriter.print(first10SeqRemove[i] + ",");
        }
        printWriter.println();
        printWriter.println();
        printWriter.println();
        // printWriter.close();// end output addition

        // *********************** Optimal Method ***********************
        MaxHeap optHeap = new MaxHeap(count); // creates an object
        optHeap.optimalHeap(array);// orders it optimally
        System.out.println("----------------------");
        System.out.println("This is the optimal order:");
        optHeap.print();// end optimal order

        // adds to output file
        printWriter.print("Heap built using optimal method: ");
        int first10Opt[] = optHeap.printTen();
        for (i = 0; i < first10Opt.length; i++) {
            printWriter.print(first10Opt[i] + ",");
        }
        printWriter.println();
        printWriter.println();
        printWriter.print("Number of swaps in the heap creation: ");
        printWriter.println(optHeap.getSwapCount()); //Returns number of swaps in the heap creation
        printWriter.println();
        // printWriter.close();// end output addition

        // #of swaps
        System.out.println("----------------------");
        System.out.println("This is the number of swaps:");
        System.out.println(optHeap.getSwapCount());
        // end # of swaps

        // removes 10
        int k = 0;
        while (k != 10) {
            optHeap.removeMax();
            k++;
        }
        System.out.println("----------------------");
        System.out.println("This is the optimal order after removing 10:");
        optHeap.print();
        // end removes 10

        // adds to output file
        printWriter.print("Heap after 10 removals: ");
        int first10OptRemover[] = optHeap.printTen();
        for (i = 0; i < first10OptRemover.length; i++) {
            printWriter.print(first10OptRemover[i] + ",");
        }
        printWriter.println();
        printWriter.close();
        // end output addition
    }
}
