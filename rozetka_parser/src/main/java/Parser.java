import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {
    private static CSVWriter writer;
    private static int maxComments = 0;
    private static int count = 0;
    private static final int ITEMS_COUNT = 582;
    private static String mostCommentedProduct = "No commented products";
    private static String mostCommentedUrl = "None";

    public static void main(String[] args) throws IOException {
        Parser.parseCategory("https://hard.rozetka.com.ua/ua/monitors/c80089/");
    }

    private static void parseCategory(String url) throws IOException {
        int num = Parser.getPageNumbers(url);
        for(int i = 0; i < num; i++) {
            Parser.parseProductPage(url + "page=" + String.valueOf(i + 1));
        }
        if(Parser.maxComments > 0) {
            System.out.println("The most commented product " + Parser.mostCommentedProduct +
                    " has " + Parser.maxComments + " comments (" + Parser.mostCommentedUrl + ")");
        } else {
            System.out.println(Parser.mostCommentedProduct);
        }
    }

    private static int getPageNumbers(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements pagePaginators = doc.select("a.paginator-catalog-l-link");
        int num = 1;
        if (pagePaginators.size() != 0) {
            num = Integer.parseInt(pagePaginators.get(pagePaginators.size() - 1).text());
        }
        return num;
    }

    private static void parseProductPage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        new File("data").mkdirs();
        Elements products = doc.select("div.g-i-tile-i-title a");
        for(int i = 0; i < products.size(); i++) {
            Parser.parseProduct(products.get(i).attr("href") + "comments/");
        }
    }

    private static void parseProduct(String url) throws IOException {
        String product = url.substring(31, url.length() - 20);
        Parser.writer = new CSVWriter(new FileWriter("data/" + product + ".csv"), ',');
        int num = Parser.getPageNumbers(url);
        Document doc = Jsoup.connect(url).get();
        Elements comments = doc.select("span.pp-review-heading-title-inner");
        int commentsValue = 0;
        if (comments.size() > 0) {
            commentsValue = Integer.parseInt(comments.get(0).text());
        }
        if (commentsValue > Parser.maxComments) {
            Parser.maxComments = commentsValue;
            Parser.mostCommentedProduct = product;
            Parser.mostCommentedUrl = url;
        }
        int total = 0;
        int[] returned;
        for(int i = 0; i < num; i++) {
            returned = Parser.parseReviewsPage(url + "page=" + String.valueOf(i + 1));
            total += returned[0];
        }
        Parser.count++;
        System.out.println(String.valueOf(Parser.count) + '/' +
                String.valueOf(Parser.ITEMS_COUNT) + "("+ Math.round((float)Parser.count / Parser.ITEMS_COUNT * 100) + "%), " + String.valueOf(total) + " reviews from " + url);
        Parser.writer.close();
    }

    private static int[] parseReviewsPage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        int[] returned = {0, 0};
        Elements reviews = doc.select("article.pp-review-i");
        for(int i = 0; i < reviews.size(); i++) {
            Elements checkStars = reviews.get(i).select("span.g-rating-stars-i");
            if (checkStars.size() != 0) {
                int rate = Integer.parseInt(checkStars.get(0).attr("content"));
                returned[0]++;
                returned[1] += rate;
                Elements haveStars = reviews.get(i).select("div.pp-review-text");
                String comment = haveStars.select("div.pp-review-text-i").get(0).text();
                String[] result = {String.valueOf(rate), comment};
                Parser.writer.writeNext(result);
            }
        }
        return returned;
    }
}
