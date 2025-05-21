import java.util.Scanner;

public class Disk_FCFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int head, size, sum = 0, diff;

        System.out.print("Enter the initial head position: ");
        head = scanner.nextInt();

        System.out.print("Enter the total size of request sequence: ");
        size = scanner.nextInt();

        int[] req = new int[size];

        System.out.println("Enter the request sequence elements:");

        for (int i = 0; i < size; i++) {
            req[i] = scanner.nextInt();
        }

        // Initial seek from head to first request
        sum = Math.abs(req[0] - head);

        // Remaining seek between requests
        for (int i = 0; i < size - 1; i++) {
            diff = Math.abs(req[i] - req[i + 1]);
            sum += diff;
        }

        System.out.println("\nTotal number of seek operations: " + sum);

        scanner.close();
    }
}