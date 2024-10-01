import java.util.Random;
public class Lab1 {
        public static void main(String[] args) {
            try {
                // Номер залікової книжки
                int studentId = 18;
                // Обчислення залишків
                int C5 = studentId % 5;
                int C7 = studentId % 7;
                int C11 = studentId % 11;

                // Розміри матриць
                int rows = 5;
                int cols = 5;

                // Створення матриць A і B
                long[][] A = generateMatrix(rows, cols);
                long[][] B = generateMatrix(rows, cols);

                // Виконання першої дії на основі C5
                long[][] C = performFirstAction(A, B, C5);

                // Друк результату першої дії
                System.out.println("Матриця C після першої операції:");
                printMatrix(C);

                // Виконання другої дії на основі C11
                long result = performSecondAction(C, C11);

                // Друк результату другої дії
                System.out.println("Результат другої операції: " + result);

            } catch (Exception e) {
                System.err.println("Виникла помилка: " + e.getMessage());
            }
        }

        // Метод для генерації матриці з елементами типу long
        private static long[][] generateMatrix(int rows, int cols) {
            Random random = new Random();
            long[][] matrix = new long[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = random.nextInt(100); // Заповнюємо матрицю випадковими числами
                }
            }
            return matrix;
        }

        // Метод для виконання першої дії з матрицями на основі значення C5
        private static long[][] performFirstAction(long[][] A, long[][] B, int C5) {
            int rows = A.length;
            int cols = A[0].length;
            long[][] C = new long[rows][cols];

            switch (C5) {
                case 0:
                    // C = aB, де a — константа
                    long a = 5;
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            C[i][j] = a * B[i][j];
                        }
                    }
                    break;
                case 1:
                    // C = BT (транспонування)
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            C[i][j] = B[j][i];
                        }
                    }
                    break;
                case 2:
                    // C = A + B
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            C[i][j] = A[i][j] + B[i][j];
                        }
                    }
                    break;
                case 3:
                    // C = A XOR B (побітове виключне “але”)
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            C[i][j] = A[i][j] ^ B[i][j];
                        }
                    }
                    break;
                case 4:
                    // C = AB (матричний добуток)
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            for (int k = 0; k < cols; k++) {
                                C[i][j] += A[i][k] * B[k][j];
                            }
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Невідоме значення C5");
            }
            return C;
        }

        // Метод для виконання другої дії на основі значення C11
        private static long performSecondAction(long[][] C, int C11) {
            int rows = C.length;
            int cols = C[0].length;
            long sum = 0;

            switch (C11) {
                case 0:
                    // Сума найменших елементів кожного стовпця
                    for (int j = 0; j < cols; j++) {
                        long min = C[0][j];
                        for (int i = 1; i < rows; i++) {
                            if (C[i][j] < min) {
                                min = C[i][j];
                            }
                        }
                        sum += min;
                    }
                    break;
                case 7:
                    // Сума найбільших елементів в стовпцях з непарними номерами та найменших в парних
                    for (int j = 0; j < cols; j++) {
                        if ((j + 1) % 2 == 0) {
                            long min = C[0][j];
                            for (int i = 1; i < rows; i++) {
                                if (C[i][j] < min) {
                                    min = C[i][j];
                                }
                            }
                            sum += min;
                        } else {
                            long max = C[0][j];
                            for (int i = 1; i < rows; i++) {
                                if (C[i][j] > max) {
                                    max = C[i][j];
                                }
                            }
                            sum += max;
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Невідоме значення C11");
            }
            return sum;
        }

        // Метод для виведення матриці на екран
        private static void printMatrix(long[][] matrix) {
            for (long[] row : matrix) {
                for (long element : row) {
                    System.out.print(element + "\t");
                }
                System.out.println();
            }
        }


}
