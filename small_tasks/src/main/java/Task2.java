import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        while (n != 1) {
            System.out.print(String.valueOf(n) + " ");
            n = n % 2 == 0 ? n / 2 : 3 * n + 1;
        }
        System.out.println(String.valueOf(n));
    }
}
