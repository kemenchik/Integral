import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Kurs {

    public static void Simpson_Specific() {
        double A = 1, a = 0, B = 1, b = 0, n, m;
        double dx, h, k, z1, z2, z3, z4, abs;
        double[][] l_matrix;
        double[][] f_matrix;
        double[] x_mass;
        double[] y_mass;
        double result_mass, result_sum, sum = 0;

        System.out.println("Точное значение двойного интеграла:");
        z1 = A * A / 2;
        z2 = a * a / 2;
        z3 = B * B * B * B / 4;
        z4 = b * b * b * b / 4;
        dx = 0.5 * (z1 - z2 - z3 + z4);
        System.out.println(dx + "\n");

        n = 4;
        System.out.println("Значение n = " + n);

        m = n;
        System.out.println("Значение m = " + m);

        h = (A - a) / (2 * n);
        k = (B - b) / (2 * m);

        System.out.println("\nШаг h = " + h);
        System.out.println("Шаг k = " + k);

        x_mass = new double[1000];
        y_mass = new double[1000];
        f_matrix = new double[1000][1000];
        l_matrix = new double[1000][1000];

        System.out.print("\n");
        System.out.print("Значения на отрезке [а = x0, А = xn] с шагом h:\n");
        for (int i = 0; i < 2 * n + 1; i++) {
            x_mass[i] = a + i * h;
            System.out.printf("x" + i + " = ");
            System.out.printf("%2.6f", x_mass[i]);
            System.out.print("\n");
        }

        System.out.print("\n");
        System.out.print("\nЗначения на отрезке [b = y0, B = ym] с шагом k:\n");
        for (int j = 0; j < 2 * m + 1; j++) {
            y_mass[j] = b + j * k;
            System.out.printf("y" + j + " = ");
            System.out.printf("%2.6f",y_mass[j]);
            System.out.print("\n");
        }

        System.out.print("\n\nМатрица fij:\n");

        for (int i = 0; i < 2 * n + 1; i++)
            for (int j = 0; j < 2 * m + 1; j++) {
                if (sqrt(1-x_mass[i]*x_mass[i])<y_mass[j])
                    f_matrix[i][j] = x_mass[i] * y_mass[j];
            }

        for (int i = 0; i < 2 * n + 1; i++) {
            for (int j = 0; j < 2 * m + 1; j++) {
                System.out.printf("%5.5f", f_matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }

        for (int i = 1; i < 2 * n; i++) {
            for (int j = 1; j < 2 * m; j++) {
                if (i % 2 == 1)
                    l_matrix[0][i] = 4;
                if (i % 2 == 0)
                    l_matrix[0][i] = 2;
            }
        }

        for (int i = 1; i < 2 * n; i++) {
            for (int j = 1; j < 2 * m; j++) {
                if (i % 2 == 1)
                    l_matrix[(int) (2 * m)][i] = 4;
                if (i % 2 == 0)
                    l_matrix[(int) (2 * m)][i] = 2;
            }
        }

        for (int i = 1; i < 2 * n; i++) {
            for (int j = 1; j < 2 * m; j++) {
                if (j % 2 == 1)
                    l_matrix[j][(int) (2 * n)] = 4;
                if (j % 2 == 0)
                    l_matrix[j][(int) (2 * n)] = 2;
            }
        }

        for (int i = 1; i < 2 * n; i++) {
            for (int j = 1; j < 2 * m; j++) {
                if (j % 2 == 1)
                    l_matrix[j][0] = 4;
                if (j % 2 == 0)
                    l_matrix[j][0] = 2;
            }
        }

        for (int i = 1; i < 2 * n; i++) {
            for (int j = 1; j < 2 * m; j++) {
                l_matrix[i][j] = l_matrix[0][j] * l_matrix[i][0];
            }
        }

        l_matrix[0][0] = 1;
        l_matrix[0][(int) (2 * n)] = 1;
        l_matrix[(int) (2*m)][0] = 1;
        l_matrix[(int) (2*m)][(int) (2 * n)] = 1;

        System.out.print("\nМатрица λij:\n");
        for (int i = 0; i < 2 * n + 1; i++) {
            for (int j = 0; j < 2 * m + 1; j++) {
                System

                        .out.printf("%2.0f",l_matrix[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }

        for (int i = 0; i < 2 * n + 1; i++)
            for (int j = 0; j < 2 * m + 1; j++) {
                result_mass = l_matrix[i][j] * f_matrix[i][j];
                sum = sum + result_mass;

            }

        result_sum = (h*k)/9 * sum;
        System.out.println("\nЗначение двойного интеграла по кубатурной формуле Симпсона:");
        System.out.printf("%2.8f",result_sum);

        abs = abs(dx-result_sum);
        System.out.print("\nПогрешность:\n");
        System.out.printf("%2.8f",abs);
        System.out.print("\n");
    }

    public static void Simpson_Common() {
        double A = 1, a = 0, B = 1, b = 0, n, m;
        double dx, h, k, z1, z2, z3, z4, abs;
        double[][] l_matrix;
        double[][] f_matrix;
        double[] x_mass;
        double[] y_mass;
        double start_n, finish_n;
        double result_mass, result_sum, sum = 0, final_abs = 1000, final_sum = 0, final_n = 0, final_m = 0;

        z1 = A * A / 2;
        z2 = a * a / 2;
        z3 = B * B * B * B / 4;
        z4 = b * b * b * b / 4;
        dx = 0.5 * (z1 - z2 - z3 + z4);

        start_n = 1;
        finish_n = 500;

        for (n = start_n; n < finish_n; n++) {

            m = n;

            h = (A - a) / (2 * n);
            k = (B - b) / (2 * m);

            x_mass = new double[1000];
            y_mass = new double[1000];
            f_matrix = new double[1000][1000];
            l_matrix = new double[1000][1000];

            for (int i = 0; i < 2 * n + 1; i++) {
                x_mass[i] = a + i * h;
            }

            for (int j = 0; j < 2 * m + 1; j++) {
                y_mass[j] = b + j * k;
            }

            for (int i = 0; i < 2 * n + 1; i++)
                for (int j = 0; j < 2 * m + 1; j++) {
                    if (sqrt(1 - x_mass[i] * x_mass[i]) < y_mass[j])
                        f_matrix[i][j] = x_mass[i] * y_mass[j];
                }

            for (int i = 1; i < 2 * n; i++) {
                for (int j = 1; j < 2 * m; j++) {
                    if (i % 2 == 1)
                        l_matrix[0][i] = 4;
                    if (i % 2 == 0)
                        l_matrix[0][i] = 2;
                }
            }

            for (int i = 1; i < 2 * n; i++) {
                for (int j = 1; j < 2 * m; j++) {
                    if (i % 2 == 1)
                        l_matrix[(int) (2 * m)][i] = 4;
                    if (i % 2 == 0)
                        l_matrix[(int) (2 * m)][i] = 2;
                }
            }

            for (int i = 1; i < 2 * n; i++) {
                for (int j = 1; j < 2 * m; j++) {
                    if (j % 2 == 1)
                        l_matrix[j][(int) (2 * n)] = 4;
                    if (j % 2 == 0)
                        l_matrix[j][(int) (2 * n)] = 2;
                }
            }

            for (int i = 1; i < 2 * n; i++) {
                for (int j = 1; j < 2 * m; j++) {
                    if (j % 2 == 1)
                        l_matrix[j][0] = 4;
                    if (j % 2 == 0)
                        l_matrix[j][0] = 2;
                }
            }

            for (int i = 1; i < 2 * n; i++) {
                for (int j = 1; j < 2 * m; j++) {
                    l_matrix[i][j] = l_matrix[0][j] * l_matrix[i][0];
                }
            }

            l_matrix[0][0] = 1;
            l_matrix[0][(int) (2 * n)] = 1;
            l_matrix[(int) (2 * m)][0] = 1;
            l_matrix[(int) (2 * m)][(int) (2 * n)] = 1;

            for (int i = 0; i < 2 * n + 1; i++)
                for (int j = 0; j < 2 * m + 1; j++) {
                    result_mass = l_matrix[i][j] * f_matrix[i][j];
                    sum = sum + result_mass;
                }

            result_sum = (h * k) / 9 * sum;
            abs = abs(dx - result_sum);

            if (abs < final_abs){
                final_abs = abs;
                final_sum = result_sum;
                final_n = n;
                final_m = final_n;
            }

            sum = 0;
        }

        System.out.println("\nЗначение n:");
        System.out.printf("%2.1f", final_n);

        System.out.println("\nЗначение m:");
        System.out.printf("%2.1f", final_m);

        System.out.println("\n\nТочное значение двойного интеграла:");
        System.out.println(dx);

        System.out.println("Значение двойного интеграла по кубатурной формуле Симпсона:");
        System.out.printf("%2.8f", final_sum);

        System.out.println("\nПогрешность:");
        System.out.printf("%2.8f", final_abs);
    }

    public static void main(String[] args) {

        System.out.println("\tВычисление двойного интеграла по кубатурной формуле Симпсона при определенных значениях n и m.");
        Simpson_Specific();
        System.out.println("\n\n\tЗначения n и m, при которых погрешность кубаторной формулы Симпсона будет минимальной:");
        Simpson_Common();
    }
}