import java.util.*;

public class Disk_CLOOK {
    private int headPosition;
    private List<Integer> requestSequence = new ArrayList<>();
    private List<Integer> seekSequence = new ArrayList<>();
    private int seekTime = 0;

    public void getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---- C-LOOK Disk Scheduling ----");

        System.out.print("Enter initial head position: ");
        headPosition = scanner.nextInt();

        System.out.println("Enter the request sequence (space-separated, end with a non-integer):");

        while (scanner.hasNextInt()) {
            requestSequence.add(scanner.nextInt());
        }

        // scanner.close(); // Uncomment if no more input needed
    }

    public void runCLOOK() {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int req : requestSequence) {
            if (req < headPosition)
                left.add(req);
            else
                right.add(req);
        }

        Collections.sort(left);
        Collections.sort(right);

        int currentHead = headPosition;

        // Serve requests to the right
        for (int track : right) {
            seekSequence.add(track);
            seekTime += Math.abs(track - currentHead);
            currentHead = track;
        }

        // Jump to the leftmost request if left exists
        if (!left.isEmpty()) {
            // Add jump seek time
            seekTime += Math.abs(currentHead - left.get(0));
            currentHead = left.get(0);

            for (int track : left) {
                seekSequence.add(track);
                seekTime += Math.abs(track - currentHead);
                currentHead = track;
            }
        }

        System.out.println("\nSeek Sequence:");
        for (int track : seekSequence)
            System.out.print(track + " ");

        System.out.println("\nTotal Seek Time = " + seekTime);
    }

    public static void main(String[] args) {
        Disk_CLOOK scheduler = new Disk_CLOOK();
        scheduler.getInput();
        scheduler.runCLOOK();
    }
}