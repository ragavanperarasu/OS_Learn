import java.util.Scanner;

public class Disk_SSTF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n, head, seek = 0, min, diff, index = 0;
        boolean[] visited;

        System.out.print("Enter the size of Queue: ");
        n = scanner.nextInt();

        int[] queue = new int[n];
        visited = new boolean[n];

        System.out.println("Enter the Queue elements:");
        for (int i = 0; i < n; i++) {
            queue[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        head = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            min = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    diff = Math.abs(head - queue[j]);
                    if (diff < min) {
                        min = diff;
                        index = j;
                    }
                }
            }

            seek += Math.abs(head - queue[index]);
            head = queue[index];
            visited[index] = true;
        }

        System.out.println("\nTotal Seek Time is: " + seek);
        scanner.close();
    }
}