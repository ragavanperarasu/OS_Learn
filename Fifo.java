import java.util.Scanner;

public class Fifo {
    public static void main(String[] args) {
        final int MAX = 25;
        int[] frame = new int[10];
        int[] page = new int[MAX];
        int nf, np = 0, temp, pf = 0, top = 0;
        boolean flag;

        Scanner sc = new Scanner(System.in);

        // Input number of frames
        do {
            System.out.print("Enter number of frames (1 to 10): ");
            nf = sc.nextInt();
            if (nf <= 0 || nf > 10) {
                System.out.println("Invalid number of frames. Please enter between 1 and 10.");
            }
        } while (nf <= 0 || nf > 10);

        // Initialize frames to -1
        for (int i = 0; i < nf; i++) {
            frame[i] = -1;
        }

        // Input page reference string
        System.out.println("Enter page references (enter -999 to stop, max 25):");
        for (int i = 0; i < MAX; i++) {
            temp = sc.nextInt();
            if (temp == -999)
                break;
            page[np++] = temp;
        }

        // Process each page
        System.out.println("\nProcessing page references...\n");
        for (int i = 0; i < np; i++) {
            flag = false;

            // Check for page hit
            for (int j = 0; j < nf; j++) {
                if (frame[j] == page[i]) {
                    flag = true;
                    break;
                }
            }

            // Page fault handling
            if (!flag) {
                frame[top] = page[i];
                top = (top + 1) % nf;
                pf++;
                System.out.print("Page Fault: ");
            } else {
                System.out.print("Page Hit:   ");
            }

            // Print current frame state
            for (int j = 0; j < nf; j++) {
                if (frame[j] != -1)
                    System.out.print(frame[j] + "\t");
                else
                    System.out.print("-\t");
            }
            System.out.println();
        }

        System.out.println("\nTotal number of page faults: " + pf);
        sc.close();
    }
}
