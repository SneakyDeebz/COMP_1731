import java.util.Scanner;

public class GridAnalyticsSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;

        // Input validation
        while (n < 4 || n > 8) {
            System.out.print("Enter matrix size (4-8): ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n < 4 || n > 8) {
                    System.out.println("Invalid size. Must be between 4 and 8 inclusive.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
        }

        int[][] matrix = new int[n][n];

        System.out.println("Enter " + n + "x" + n + " matrix elements row by row:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("matrix[" + i + "][" + j + "]: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Invalid. Re-enter matrix[" + i + "][" + j + "]: ");
                    sc.next();
                }
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nMatrix Analysis Report");
        System.out.println("----------------------");
        System.out.println("Dominant Rows: " + getDominantRows(matrix, n));
        System.out.println("Primary Diagonal Stable: " + isDiagonalStable(matrix, n));
        System.out.println("Boundary Symmetric: " + isBoundarySymmetric(matrix, n));
        System.out.println("Total Local Peaks: " + countLocalPeaks(matrix, n));

        sc.close();
    }

    // Part 1: Row Dominance Analysis
    static String getDominantRows(int[][] matrix, int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int rowSum = 0, colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            if (rowSum > colSum) {
                if (result.length() > 0)
                    result.append(" ");
                result.append(i);
            }
        }
        return result.length() == 0 ? "None" : result.toString();
    }

    // Part 2: Primary Diagonal Stability
    static boolean isDiagonalStable(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
            }
            double rowAvg = (double) rowSum / n;
            if (matrix[i][i] <= rowAvg) {
                return false;
            }
        }
        return true;
    }

    // Part 3: Boundary Symmetry Check
    static boolean isBoundarySymmetric(int[][] matrix, int n) {
        int totalBoundary = 4 * (n - 1);
        int[] boundary = new int[totalBoundary];
        int idx = 0;

        for (int j = 0; j < n; j++)
            boundary[idx++] = matrix[0][j];

        for (int i = 1; i < n; i++)
            boundary[idx++] = matrix[i][n - 1];

        for (int j = n - 2; j >= 0; j--)
            boundary[idx++] = matrix[n - 1][j];

        for (int i = n - 2; i >= 1; i--)
            boundary[idx++] = matrix[i][0];

        for (int i = 0; i < totalBoundary / 2; i++) {
            if (boundary[i] != boundary[totalBoundary - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    // Part 4: Local Peak Detection
    static int countLocalPeaks(int[][] matrix, int n) {
        int count = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int val = matrix[i][j];
                if (val > matrix[i - 1][j] &&
                        val > matrix[i + 1][j] &&
                        val > matrix[i][j - 1] &&
                        val > matrix[i][j + 1]) {
                    count++;
                }
            }
        }
        return count;
    }
}