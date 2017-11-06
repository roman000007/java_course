import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CategoryPageParser cp = new CategoryPageParser("https://hard.rozetka.com.ua/ua/monitors/c80089/");
        cp.getMostCommentedProduct();
    }
}
