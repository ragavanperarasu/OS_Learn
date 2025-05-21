import java.util.*;

public class Disk_SCAN {
    private int head;
    private final int diskSize = 200;
    private List<Integer> requests = new ArrayList<>();
    private List<Integer> seekSequence = new ArrayList<>();
    private int seekTime = 0;

    public void getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial head position: ");
        head = scanner.nextInt();

        System.out.println("Enter the request sequence (space-separated, end with a non-integer):");
        while (scanner.hasNextInt()) {
            requests.add(scanner.nextInt());
        }
    }

    private void displaySeekSequence() {
        System.out.println("Seek Sequence:");
        for (int track : seekSequence) {
            System.out.print(track + " ");
        }
        System.out.println();
    }

    public void runSCAN(String direction) {
        seekSequence.clear();
        seekTime = 0;

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int req : requests) {
            if (req < head)
                left.add(req);
            else
                right.add(req);
        }

        if (direction.equalsIgnoreCase("left")) {
            left.add(0); // start of disk
        } else {
            right.add(diskSize - 1); // end of disk
        }

        Collections.sort(left);
        Collections.sort(right);

        int currentHead = head;

        for (int turn = 0; turn < 2; turn++) {
            if (direction.equalsIgnoreCase("right")) {
                for (int track : right) {
                    seekSequence.add(track);
                    seekTime += Math.abs(currentHead - track);
                    currentHead = track;
                }
                direction = "left";
            } else {
                for (int i = left.size() - 1; i >= 0; i--) {
                    int track = left.get(i);
                    seekSequence.add(track);
                    seekTime += Math.abs(currentHead - track);
                    currentHead = track;
                }
                direction = "right";
            }
        }

        System.out.println("\nTotal Seek Time (SCAN): " + seekTime);
        displaySeekSequence();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Disk_SCAN scheduler = new Disk_SCAN();

        scheduler.getInput();
        System.out.print("Enter direction (left/right): ");
        String dir = scanner.next();
        scheduler.runSCAN(dir);
    }
}