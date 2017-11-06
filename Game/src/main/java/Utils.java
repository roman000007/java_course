import java.util.Random;

public class Utils {
    public static int generateNum(int minimum, int maximum) {
        Random random = new Random();
        return random.nextInt(maximum - minimum + 1) + minimum;
    }
}
