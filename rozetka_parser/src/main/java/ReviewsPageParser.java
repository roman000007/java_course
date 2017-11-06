import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ReviewsPageParser extends PageParser{
    public ReviewsPageParser(String url){
        super(url);
    }

    public int[] reviewsToCSV(CSVWriter writer) throws IOException {
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
                writer.writeNext(result);
            }
        }
        return returned;
    }
}
