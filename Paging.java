import java.util.Scanner;

public class Paging {
    public static void main(String[] args) {
        int memsize = 15;
        int pagesize, noofpage;
        int[] p;
        int frameno, offset, phyadd, choice, pageno;

        Scanner sc = new Scanner(System.in);

        System.out.println("Your memory size is: " + memsize);

        // Validate page size input
        do {
            System.out.print("Enter page size (must be > 0 and <= memory size): ");
            pagesize = sc.nextInt();
            if (pagesize <= 0 || pagesize > memsize) {
                System.out.println("Invalid page size. Try again.");
            }
        } while (pagesize <= 0 || pagesize > memsize);

        // Calculate number of pages
        noofpage = memsize / pagesize;
        System.out.println("The total number of pages is: " + noofpage);

        // Initialize frame number array dynamically
        p = new int[noofpage];

        // Fill frame numbers
        for (int i = 0; i < noofpage; i++) {
            System.out.print("Enter the frame number for page " + i + ": ");
            p[i] = sc.nextInt();
        }

        do {
            System.out.println("\nLogical address access:");
            System.out.print("Enter the page number and the offset value: ");
            pageno = sc.nextInt();
            offset = sc.nextInt();

            if (pageno < noofpage && offset < pagesize && pageno >= 0 && offset >= 0) {
                phyadd = (p[pageno] * pagesize) + offset;
                System.out.println("Physical address is: " + phyadd);
            } else {
                System.out.println("Invalid input! Page number should be between 0 and " + (noofpage - 1)
                        + ", and offset should be between 0 and " + (pagesize - 1));
            }

            System.out.print("\nDo you want to continue? (1 = Yes / 0 = No): ");
            choice = sc.nextInt();

        } while (choice == 1);

        sc.close();
    }
}
