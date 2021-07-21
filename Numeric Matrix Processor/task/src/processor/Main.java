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
                System.out.println("The result is:");
                displayMatrix(matrixC);
            } else if (chosenAction.equals("3")) {
                double[][] matrixC = multiplyMatrixes();
                if (main.areMatrixEqual == false) {
                    System.out.println("The operation cannot be performed.");
                    continue;
                }
                System.out.println("The result is:");
                displayMatrix(matrixC);
            }
        }
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
            // KONIEC???
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
        double[][] matrixA = createMatrix("first");
        double[][] matrixB = createMatrix("second");
        if (checkSize(matrixA, matrixB) == false) {
            main.areMatrixEqual = false;
        } else {
            main.areMatrixEqual = true;
        }
        double[][] matrixC = new double[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixB.length; k++) {
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return matrixC;
    }

    public static boolean checkSize(double[][] matrixA, double[][] matrixB) {
        if (matrixA[0].length != matrixB.length) {
            return false;
        }
        return true;
    }

    public static void displayMatrix(double[][] matrix) {
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
        System.out.println("0. Exit");
    }
}