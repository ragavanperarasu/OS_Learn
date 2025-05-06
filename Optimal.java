import java.util.Scanner;

public class Optimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noOfFrames, noOfPages, faults = 0;

        System.out.print("Enter number of frames: ");
        noOfFrames = sc.nextInt();

        System.out.print("Enter number of pages: ");
        noOfPages = sc.nextInt();

        int[] pages = new int[noOfPages];
        int[] frames = new int[noOfFrames];
        int[] temp = new int[noOfFrames];

        System.out.println("Enter page reference string:");
        for (int i = 0; i < noOfPages; i++) {
            pages[i] = sc.nextInt();
        }

        // Initialize frames to -1
        for (int i = 0; i < noOfFrames; i++) {
            frames[i] = -1;
        }

        System.out.println("\nProcessing...\n");

        for (int i = 0; i < noOfPages; i++) {
            boolean flag1 = false, flag2 = false;

            // Check if page already in frame
            for (int j = 0; j < noOfFrames; j++) {
                if (frames[j] == pages[i]) {
                    flag1 = flag2 = true;
                    break;
                }
            }

            // Page fault + empty frame
            if (!flag1) {
                for (int j = 0; j < noOfFrames; j++) {
                    if (frames[j] == -1) {
                        frames[j] = pages[i];
                        faults++;
                        flag2 = true;
                        break;
                    }
                }
            }

            // Page fault + optimal replacement
            if (!flag2) {
                for (int j = 0; j < noOfFrames; j++) {
                    temp[j] = -1;
                    for (int k = i + 1; k < noOfPages; k++) {
                        if (frames[j] == pages[k]) {
                            temp[j] = k;
                            break;
                        }
                    }
                }

                int pos = -1;
                boolean flag3 = false;
                for (int j = 0; j < noOfFrames; j++) {
                    if (temp[j] == -1) {
                        pos = j;
                        flag3 = true;
                        break;
                    }
                }

                if (!flag3) {
                    int max = temp[0];
                    pos = 0;
                    for (int j = 1; j < noOfFrames; j++) {
                        if (temp[j] > max) {
                            max = temp[j];
                            pos = j;
                        }
                    }
                }

                frames[pos] = pages[i];
                faults++;
            }

            // Print current frame state
            System.out.println();
            for (int j = 0; j < noOfFrames; j++) {
                System.out.print((frames[j] == -1 ? "-" : frames[j]) + "\t");
            }
        }

        System.out.println("\n\nTotal Page Faults = " + faults);
        sc.close();
    }
}
