import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE, sum = 0, n = 5, product = 1;
        float averageValue;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for(int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            minValue = value < minValue ? value : minValue;
            maxValue = value > maxValue ? value : maxValue;
            sum += value;
            product *= value;
        }
        averageValue = sum / (float) n;
        System.out.println(String.valueOf(maxValue) + " " + String.valueOf(minValue) + " " +
                String.valueOf(averageValue) + " " + String.valueOf(sum) + " " + String.valueOf(product));
    }
}
