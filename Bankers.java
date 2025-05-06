import java.io.*;
import java.util.Scanner;

public class Bankers {
    private int numberOfProcesses, numberOfResources;
    private int[] available;
    private int[][] maximum, allocation, need;

    public Bankers() {
        // Initialization is handled in the input method
    }

    public void input(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename));
             Scanner scanner = new Scanner(reader)) {

            numberOfProcesses = scanner.nextInt();
            numberOfResources = scanner.nextInt();

            allocation = new int[numberOfProcesses][numberOfResources];
            maximum = new int[numberOfProcesses][numberOfResources];
            need = new int[numberOfProcesses][numberOfResources];
            available = new int[numberOfResources];

            // Read Allocation matrix
            for (int i = 0; i < numberOfProcesses; i++) {
                for (int j = 0; j < numberOfResources; j++) {
                    allocation[i][j] = scanner.nextInt();
                }
            }

            // Read Maximum matrix and calculate Need matrix
            for (int i = 0; i < numberOfProcesses; i++) {
                for (int j = 0; j < numberOfResources; j++) {
                    maximum[i][j] = scanner.nextInt();
                    need[i][j] = maximum[i][j] - allocation[i][j];
                }
            }

            // Read Available resources
            for (int j = 0; j < numberOfResources; j++) {
                available[j] = scanner.nextInt();
            }
        }
    }

    public void isSafe(String outputFilename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {

            boolean[] finished = new boolean[numberOfProcesses];
            int[] work = available.clone();
            int[] safeSequence = new int[numberOfProcesses];
            int count = 0;

            while (count < numberOfProcesses) {
                boolean found = false;
                writer.write("=== Iteration " + (count + 1) + " ===\n");
                System.out.println("=== Iteration " + (count + 1) + " ===");

                printMatrix(writer, "Allocation", allocation);
                printMatrix(writer, "Need", need);
                printMatrix(writer, "Maximum", maximum);

                for (int i = 0; i < numberOfProcesses; i++) {
                    if (!finished[i]) {
                        boolean canAllocate = true;
                        for (int j = 0; j < numberOfResources; j++) {
                            if (need[i][j] > work[j]) {
                                canAllocate = false;
                                break;
                            }
                        }

                        if (canAllocate) {
                            for (int j = 0; j < numberOfResources; j++) {
                                work[j] += allocation[i][j];
                            }
                            safeSequence[count++] = i;
                            finished[i] = true;
                            found = true;
                            break; // Start next iteration
                        }
                    }
                }

                if (!found) {
                    writer.write("System is in an unsafe state! Deadlock may occur.\n");
                    System.out.println("System is in an unsafe state! Deadlock may occur.");
                    return;
                }
            }

            writer.write("System is in a safe state. Safe sequence: ");
            System.out.print("System is in a safe state. Safe sequence: ");
            for (int i = 0; i < numberOfProcesses; i++) {
                writer.write(safeSequence[i] + " ");
                System.out.print(safeSequence[i] + " ");
            }
            writer.write("\n");
            System.out.println();
        }
    }

    private void printMatrix(BufferedWriter writer, String name, int[][] matrix) throws IOException {
        writer.write(name + " Matrix:\n");
        System.out.println(name + " Matrix:");
        for (int i = 0; i < numberOfProcesses; i++) {
            for (int j = 0; j < numberOfResources; j++) {
                writer.write(matrix[i][j] + " ");
                System.out.print(matrix[i][j] + " ");
            }
            writer.write("\n");
            System.out.println();
        }
        writer.write("\n");
        System.out.println();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter input file name: ");
            String inputFile = scanner.nextLine();
            System.out.print("Enter output file name: ");
            String outputFile = scanner.nextLine();

            Bankers bankersAlgorithm = new Bankers();
            bankersAlgorithm.input(inputFile);
            bankersAlgorithm.isSafe(outputFile);
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }
}
