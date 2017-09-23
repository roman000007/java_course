import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser {

    public static void main(String[] args) throws IOException {
        Parser.parseCategory("https://hard.rozetka.com.ua/ua/monitors/c80089/");

    }

    private static void parseCategory(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements pagePaginators = doc.select("a.paginator-catalog-l-link");
        int num = 0;
        if (pagePaginators.size() != 0) {
            num = Integer.parseInt(pagePaginators.get(pagePaginators.size() - 1).text());
        }
        for(int i = 0; i < num; i++) {
            Parser.parsePage("https://hard.rozetka.com.ua/ua/monitors/c80089/page=" + String.valueOf(i + 1));
        }
    }

    private static void parsePage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements products = doc.select("div.g-i-tile-i-title a");
        for(int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).attr("href"));
        }


    }
}
