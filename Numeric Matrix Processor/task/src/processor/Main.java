package processor;

import java.util.Scanner;

public class Main {

    boolean areMatrixEqual = true;

    public static void main(String[] args) {
        appinit();
    }

    public static void appinit() {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();

        while (true) {
            displayMenu();
            System.out.print("Your choice: ");
            String chosenAction = scanner.next();
            if (chosenAction.equals("0")) {
                break;
            }
            if (chosenAction.equals("1")) {
                double[][] matrixC = sumMatrix();
                if (matrixC[0][0] == 0) {
                    System.out.println("\nThe operation cannot be performed.");
                    continue;
                }
                System.out.println("The result is:");
                displayMatrix(matrixC);
            } else if (chosenAction.equals("2")) {
                double[][] matrixC = multiplyMatrixByConstant();
                displayMatrix(matrixC);
            } else if (chosenAction.equals("3")) {
                double[][] matrixC = multiplyMatrixes();
                if (main.areMatrixEqual == false) {
                    System.out.println("The operation cannot be performed.");
                    continue;
                }
                displayMatrix(matrixC);
            } else if (chosenAction.equals("4")) {
                displayTransPositionMenu();
                System.out.print("Your choice: ");
                String transpositionType = scanner.next();
                if (transpositionType.equals("1")) {
                    double[][] mainDiagonalTransposedMatrix = mainDiagonalTransposition();
                    displayMatrix(mainDiagonalTransposedMatrix);
                } else if (transpositionType.equals("2")) {
                    double[][] sideDiagonalTransposedMatrix = sideDiagonalTransposition();
                    displayMatrix(sideDiagonalTransposedMatrix);
                } else if (transpositionType.equals("3")) {
                    double[][] verticalLineTransposedMatrix = verticalLineTransposition();
                    displayMatrix(verticalLineTransposedMatrix);
                } else if (transpositionType.equals("4")) {
                    double[][] horizontalLineTransposedMatrix = horizontalLineTransposition();
                    displayMatrix(horizontalLineTransposedMatrix);
                }
            } else if (chosenAction.equals("5")) {
                double[][] arr = createMatrix("");
                double result = calculateDeterminant(arr);
                System.out.println("The result is:");
                System.out.println(result + "\n");
            } else if (chosenAction.equals("6")) {
                double[][] arr = createMatrix("");
                double inversedMatrix[][] = invert(arr);
                System.out.println("The result is:");
                for (int i = 0; i < inversedMatrix.length; ++i) {
                    for (int j = 0; j < inversedMatrix[i].length; ++j) {
                        int temp = (int)(inversedMatrix[i][j]*100.0);
                        double shortDouble = ((double)temp)/100.0;
                        System.out.print(shortDouble + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    public static double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;


        // Transform the matrix into an upper triangle
        gaussian(a, index);
        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }


    // Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.
    public static void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public static double calculateDeterminant(double[][] arr) {

        double result = 0;
        if (arr.length == 1) {
            result = arr[0][0];
            return result;
        }
        if (arr.length == 2) {
            result = arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
            return result;
        }
        for (int i = 0; i < arr[0].length; i++) {
            double temp[][] = new double[arr.length - 1][arr[0].length - 1];

            for (int j = 1; j < arr.length; j++) {
                for (int k = 0; k < arr[0].length; k++) {

                    if (k < i) {
                        temp[j - 1][k] = arr[j][k];
                    } else if (k > i) {
                        temp[j - 1][k - 1] = arr[j][k];
                    }
                }
            }
            result += arr[0][i] * Math.pow(-1, (int) i) * calculateDeterminant(temp);
        }
        return result;
    }

    public static double[][] mainDiagonalTransposition() {
        double[][] matrix = createMatrix("");
        double[][] mainDiagonal = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < mainDiagonal.length; i++) {
            for (int j = 0; j < mainDiagonal[i].length; j++) {
                mainDiagonal[i][j] = matrix[j][i];
            }
        }
        return mainDiagonal;
    }

    public static double[][] sideDiagonalTransposition() {
        double[][] matrix = createMatrix("");
        double[][] sideDiagonal = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < sideDiagonal.length; i++) {
            for (int j = 0; j < sideDiagonal[i].length; j++) {
                sideDiagonal[i][j] = matrix[sideDiagonal.length - 1 - j][sideDiagonal.length - 1 - i];
            }
        }
        return sideDiagonal;
    }

    public static double[][] verticalLineTransposition() {
        double[][] matrix = createMatrix("");
        double[][] verticalarray = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < verticalarray.length; i++) {
            for (int j = 0; j < verticalarray[i].length; j++) {
                verticalarray[i][j] = matrix[i][matrix[i].length - 1 - j];
            }
        }
        return verticalarray;
    }

    public static double[][] horizontalLineTransposition() {
        double[][] matrix = createMatrix("");
        double[][] horizontalarray = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < horizontalarray.length; i++) {
            for (int j = 0; j < horizontalarray[i].length; j++) {
                horizontalarray[i][j] = matrix[matrix.length - 1 - i][j];
            }
        }
        return horizontalarray;
    }

    public static double[][] createMatrix(String order) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of " + order + " matrix: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        System.out.println("Enter " + order + " matrix: ");
        double[][] matrix = new double[row][col];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }


    public static double[][] sumMatrix() {
        Main main = new Main();
        double[][] matrix1 = createMatrix("first");
        double[][] matrix2 = createMatrix("second");
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            main.areMatrixEqual = true;
        } else {
            main.areMatrixEqual = false;
        }
        double[][] matrixesSum = new double[matrix1.length][matrix1[0].length];

        if (main.areMatrixEqual == true) {
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[0].length; j++) {
                    matrixesSum[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
        }
        return matrixesSum;
    }


    public static double[][] multiplyMatrixByConstant() {
        Scanner scanner = new Scanner((System.in));
        double[][] matrix = createMatrix("");
        System.out.print("Enter constant: ");
        double constant = scanner.nextDouble();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j] * constant;
            }
        }
        return matrix;
    }

    public static double[][] multiplyMatrixes() {
        Main main = new Main();
        double[][] arr = createMatrix("first");
        double[][] matrixB = createMatrix("second");
        if (checkSize(arr, matrixB) == false) {
            main.areMatrixEqual = false;
        } else {
            main.areMatrixEqual = true;
        }
        double[][] matrixC = new double[arr.length][matrixB[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixB.length; k++) {
                    matrixC[i][j] += arr[i][k] * matrixB[k][j];
                }
            }
        }
        return matrixC;
    }

    public static boolean checkSize(double[][] arr, double[][] matrixB) {
        if (arr[0].length != matrixB.length) {
            return false;
        }
        return true;
    }

    public static void displayMatrix(double[][] matrix) {
        System.out.println("The result is:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose Matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
    }

    public static void displayTransPositionMenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }
}