import java.util.Scanner;

public class LRU {
    public static int findLRU(int[] time, int n) {
        int minimum = time[0], pos = 0;
        for (int i = 1; i < n; i++) {
            if (time[i] < minimum) {
                minimum = time[i];
                pos = i;
            }
        }
        return pos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int no_of_frames, no_of_pages, counter = 0, faults = 0;
        int[] frames = new int[10];
        int[] pages = new int[30];
        int[] time = new int[10];

        // Input
        do {
            System.out.print("Enter number of frames (1-10): ");
            no_of_frames = sc.nextInt();
            if (no_of_frames <= 0 || no_of_frames > 10) {
                System.out.println("Invalid! Please enter between 1 and 10.");
            }
        } while (no_of_frames <= 0 || no_of_frames > 10);

        System.out.print("Enter number of pages (max 30): ");
        no_of_pages = sc.nextInt();
        System.out.println("Enter reference string: ");
        for (int i = 0; i < no_of_pages; i++) {
            pages[i] = sc.nextInt();
        }

        // Initialize frames to -1
        for (int i = 0; i < no_of_frames; i++) {
            frames[i] = -1;
        }

        // LRU Page Replacement Logic
        System.out.println("\nProcessing...\n");
        for (int i = 0; i < no_of_pages; i++) {
            boolean flag1 = false, flag2 = false;

            // Page hit
            for (int j = 0; j < no_of_frames; j++) {
                if (frames[j] == pages[i]) {
                    counter++;
                    time[j] = counter;
                    flag1 = flag2 = true;
                    break;
                }
            }

            // Page miss + empty frame
            if (!flag1) {
                for (int j = 0; j < no_of_frames; j++) {
                    if (frames[j] == -1) {
                        counter++;
                        faults++;
                        frames[j] = pages[i];
                        time[j] = counter;
                        flag2 = true;
                        break;
                    }
                }
            }

            // Page miss + replacement using LRU
            if (!flag2) {
                int pos = findLRU(time, no_of_frames);
                counter++;
                faults++;
                frames[pos] = pages[i];
                time[pos] = counter;
            }

            // Print current frame state
            for (int j = 0; j < no_of_frames; j++) {
                if (frames[j] != -1)
                    System.out.print(frames[j] + "\t");
                else
                    System.out.print("-\t");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults = " + faults);
        sc.close();
    }
}
