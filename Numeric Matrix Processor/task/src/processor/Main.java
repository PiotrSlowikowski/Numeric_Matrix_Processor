package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        appinit();
    }

    public static void appinit() {
        int[][] matrixA = createMatrix();
        int[][] matrixB = multiplyMatrixByConstant(matrixA);
        displayMatrix(matrixB);
    }

    public static int[][] createMatrix() {
        Scanner scanner = new Scanner(System.in);
        int rowsQuantity = scanner.nextInt();
        int columnsQuantity = scanner.nextInt();
        int[][] matrix = new int[rowsQuantity][columnsQuantity];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static int[][] multiplyMatrixByConstant(int[][] matrix) {
        Scanner scanner = new Scanner(System.in);
        int constant = scanner.nextInt();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] * constant;
            }
        }
        return matrix;
    }

    public static int[][] sumMatrix(int[][] matrixA, int[][] matrixB) {
        int[][] matrixC = new int[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrixC.length; i++) {
            for (int j = 0; j < matrixC[i].length; j++) {
                matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return matrixC;
    }

    public static boolean checkSize(int[][] matrixA, int[][] matrixB) {
        return matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length;
    }

    public static void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
