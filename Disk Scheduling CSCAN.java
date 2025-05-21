import java.util.*;

public class Disk_CSCAN {
    private int head; // The initial head position
    private final int diskSize = 200; // The size of the disk (end of the disk)
    private List<Integer> requests = new ArrayList<>(); // The list of request sequence
    private List<Integer> seekSequence = new ArrayList<>(); // To store the sequence of requests processed
    private int seekTime = 0; // To store the total seek time

    // Method to take input from the user
    public void getInput() {
        Scanner scanner = new Scanner(System.in);

        // Taking the initial head position as input
        System.out.print("Enter initial head position: ");
        head = scanner.nextInt();

        // Taking the request sequence input (space-separated values)
        System.out.println("Enter the request sequence (space-separated, end input with any non-integer):");
        while (scanner.hasNextInt()) {
            requests.add(scanner.nextInt());
        }

        // Taking the direction input (either 'left' or 'right')
        System.out.print("Enter the initial direction (left or right): ");
        String direction = scanner.next().toLowerCase();

        // Running the C-SCAN algorithm with the given direction
        runCSCAN(direction);
    }

    // Method to display the seek sequence processed
    private void displaySeekSequence() {
        System.out.println("Seek Sequence:");
        for (int track : seekSequence) {
            System.out.print(track + " ");
        }
        System.out.println();
    }

    // C-SCAN Algorithm
    public void runCSCAN(String direction) {
        // Clearing previous values
        seekSequence.clear();
        seekTime = 0;

        // Lists to store left and right requests relative to the initial head position
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Separating the requests into left and right sides of the initial head position
        for (int req : requests) {
            if (req >= head) {
                right.add(req);
            } else {
                left.add(req);
            }
        }

        // Sorting the requests
        Collections.sort(left);
        Collections.sort(right);

        int currentHead = head;

        if (direction.equals("right")) {
            // Processing requests to the right
            for (int track : right) {
                seekSequence.add(track);
                seekTime += Math.abs(currentHead - track);
                currentHead = track;
            }

            // Jump to start and serve left-side requests
            if (!left.isEmpty()) {
                seekTime += (diskSize - 1 - currentHead); // go to end
                seekTime += (diskSize - 1);               // jump to start
                currentHead = 0;
            }

            for (int track : left) {
                seekSequence.add(track);
                seekTime += Math.abs(currentHead - track);
                currentHead = track;
            }

        } else if (direction.equals("left")) {
            // Processing requests to the left
            for (int i = left.size() - 1; i >= 0; i--) {
                int track = left.get(i);
                seekSequence.add(track);
                seekTime += Math.abs(currentHead - track);
                currentHead = track;
            }

            // Jump to end and serve right-side requests
            if (!right.isEmpty()) {
                seekTime += currentHead; // go to start
                seekTime += (diskSize - 1); // jump to end
                currentHead = diskSize - 1;
            }

            for (int track : right) {
                seekSequence.add(track);
                seekTime += Math.abs(currentHead - track);
                currentHead = track;
            }
        }

        // Output the results
        System.out.println("\nTotal Seek Time (C-SCAN): " + seekTime);
        displaySeekSequence();
    }

    // Main method to run the program
    public static void main(String[] args) {
        Disk_CSCAN scheduler = new Disk_CSCAN();
        scheduler.getInput();
    }
}