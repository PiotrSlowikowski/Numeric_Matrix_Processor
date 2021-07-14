package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        appinit();
    }

    public static void appinit() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Your choice: ");
            String chosenAction = scanner.next();
            if (chosenAction.equals("0")) {
                break;
            }
            if (chosenAction.equals("1")) {
                float[][] matrixC = sumMatrix();
                System.out.println("The result is:");
                displayMatrix(matrixC);
            } else if (chosenAction.equals("2")) {
                float[][] matrixC = multiplyMatrixByConstant();
                System.out.println("The result is:");
                displayMatrix(matrixC);
            }
        }
    }

    public static int[][] createMatrix(String order) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of " + order + " matrix: ");
        int rowsQuantity = scanner.nextInt();
        int columnsQuantity = scanner.nextInt();
        System.out.println("Enter " + order + " matrix: ");
        int[][] matrix = new int[rowsQuantity][columnsQuantity];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static float[][] sumMatrix() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of first matrix: ");
        int rowsQuantityA = scanner.nextInt();
        int columnsQuantityA = scanner.nextInt();
        System.out.println("Enter first matrix: ");
        int[][] matrixA = new int[rowsQuantityA][columnsQuantityA];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                matrixA[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter size of second matrix: ");
        int rowsQuantityB = scanner.nextInt();
        int columnsQuantityB = scanner.nextInt();
        System.out.println("Enter second matrix: ");
        int[][] matrixB = new int[rowsQuantityB][columnsQuantityB];
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[i].length; j++) {
                matrixB[i][j] = scanner.nextInt();
            }
        }

        float[][] matrixC = new float[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixC.length; i++) {
            for (int j = 0; j < matrixC[i].length; j++) {
                matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return matrixC;
    }

    public static float[][] multiplyMatrixByConstant() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of matrix: ");
        int rowsQuantity = scanner.nextInt();
        int columnsQuantity = scanner.nextInt();
        System.out.println("Enter matrix: ");
        float[][] matrix = new float[rowsQuantity][columnsQuantity];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextFloat();
            }
        }
        float[][] matrixMultipliedByConst = new float[matrix.length][matrix[0].length];
        System.out.print("Enter constant: ");
        float constant = scanner.nextFloat();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrixMultipliedByConst[i][j] = matrix[i][j] * constant;
            }
        }
        return matrixMultipliedByConst;
    }

    public static int[][] multiplyMatrixes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of first matrix: ");
        int rowsQuantityA = scanner.nextInt();
        int columnsQuantityA = scanner.nextInt();
        System.out.println("Enter first matrix: ");
        int[][] matrixA = new int[rowsQuantityA][columnsQuantityA];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                matrixA[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter size of second matrix: ");
        int rowsQuantityB = scanner.nextInt();
        int columnsQuantityB = scanner.nextInt();
        System.out.println("Enter second matrix: ");
        int[][] matrixB = new int[rowsQuantityB][columnsQuantityB];
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB[i].length; j++) {
                matrixB[i][j] = scanner.nextInt();
            }
        }

        int[][] matrixC = new int[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixC.length; i++) {
            for (int j = 0; j < matrixC[i].length; j++) {

            }
        }
        return matrixC;

    }

    public static boolean checkSize(int[][] matrixA, int[][] matrixB) {
        return matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length;
    }

    public static void displayMatrix(float[][] matrix) {
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
