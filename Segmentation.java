import java.util.Scanner;

public class Segmentation {
    public static void main(String[] args) {
        int[][] a = new int[10][2]; // [segment][0=size, 1=base]
        int[] b = new int[100]; // memory array
        int n, size, base, seg, off, x;

        Scanner sc = new Scanner(System.in);

        // Input number of segments (max 10)
        do {
            System.out.print("Enter the number of segments (max 10): ");
            n = sc.nextInt();
            if (n > 10 || n <= 0) {
                System.out.println("Invalid number of segments. Please enter between 1 and 10.");
            }
        } while (n > 10 || n <= 0);

        // Input each segment
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the size of segment " + i + ": ");
            size = sc.nextInt();
            while (size <= 0) {
                System.out.print("Size must be positive. Re-enter size of segment " + i + ": ");
                size = sc.nextInt();
            }
            a[i][0] = size;

            System.out.print("Enter the base address for segment " + i + ": ");
            base = sc.nextInt();
            while (base < 0 || base + size > 100) {
                System.out.print("Invalid base address (must be ≥0 and base+size ≤100). Re-enter: ");
                base = sc.nextInt();
            }
            a[i][1] = base;

            // Fill memory for this segment
            for (int j = 0; j < size; j++) {
                System.out.print("Enter value for address " + (base + j) + ": ");
                x = sc.nextInt();
                b[base + j] = x;
            }
        }

        // Address translation
        System.out.print("\nEnter the segment number and the offset value: ");
        seg = sc.nextInt();
        off = sc.nextInt();

        if (seg >= 0 && seg < n && off >= 0 && off < a[seg][0]) {
            int absAddr = a[seg][1] + off;
            System.out.println("The offset is valid (less than " + a[seg][0] + ").");
            System.out.println("Physical Address: " + a[seg][1] + " + " + off + " = " + absAddr);
            System.out.println("The element at physical address " + absAddr + " is " + b[absAddr]);
        } else {
            System.out.println("Error in locating: Invalid segment number or offset.");
        }

        sc.close();
    }
}
