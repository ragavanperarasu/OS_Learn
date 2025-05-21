import java.util.*;

public class Disk_LOOK {

    public static void LOOK(int[] arr, int head, String direction) {
        int seek_count = 0;
        int distance, cur_track;

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        ArrayList<Integer> seek_sequence = new ArrayList<>();

        for (int track : arr) {
            if (track < head)
                left.add(track);
            else if (track > head)
                right.add(track);
        }

        Collections.sort(left);
        Collections.sort(right);

        int run = 2;
        while (run-- > 0) {
            if (direction.equalsIgnoreCase("left")) {
                for (int i = left.size() - 1; i >= 0; i--) {
                    cur_track = left.get(i);
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "right";
            } else if (direction.equalsIgnoreCase("right")) {
                for (int track : right) {
                    cur_track = track;
                    seek_sequence.add(cur_track);
                    distance = Math.abs(cur_track - head);
                    seek_count += distance;
                    head = cur_track;
                }
                direction = "left";
            }
        }

        System.out.println("\nSeek Sequence is:");
        for (int track : seek_sequence)
            System.out.print(track + " ");

        System.out.println("\nTotal number of seek operations = " + seek_count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of disk requests: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];

        System.out.println("Enter the request sequence:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int head = scanner.nextInt();

        System.out.print("Enter the initial direction (left/right): ");
        String direction = scanner.next();

        LOOK(arr, head, direction);

        scanner.close();
    }
}