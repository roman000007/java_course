import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class ProductPageParser extends PageParser {
    CSVWriter writer;
    public ProductPageParser(String url){
        super(url);

    }


    public Product getMostPopularProduct(Product maxProduct) throws IOException {
        String product = url.substring(31, url.length() - 20);
        writer = new CSVWriter(new FileWriter("data/" + product + ".csv"), ',');
        int num = getPageNumbers();
        Document doc = Jsoup.connect(url).get();
        Elements comments = doc.select("span.pp-review-heading-title-inner");
        int commentsValue = 0;
        if (comments.size() > 0) {
            commentsValue = Integer.parseInt(comments.get(0).text());
        }
        if (commentsValue > maxProduct.comments) {
            maxProduct.comments = commentsValue;
            maxProduct.name = product;
            maxProduct.url = url;
        }

        int total = 0;
        int[] returned;
        for(int i = 0; i < num; i++) {
            ReviewsPageParser reviewsParser = new ReviewsPageParser(url + "page=" + String.valueOf(i + 1));
            returned = reviewsParser.getStars(writer);
            total += returned[0];
        }
        System.out.println(String.valueOf(total) + " reviews from " + url);
        writer.close();

        return maxProduct;
    }
}
