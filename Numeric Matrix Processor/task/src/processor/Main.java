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
        System.out.println("0. Exit");
    }

    public static void displayTransPositionMenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }
}