import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public abstract class PageParser {
    String url;
    public PageParser(String url){
        this.url = url;
    }
    public int getPageNumbers() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements pagePaginators = doc.select("a.paginator-catalog-l-link");
        int num = 1;
        if (pagePaginators.size() != 0) {
            num = Integer.parseInt(pagePaginators.get(pagePaginators.size() - 1).text());
        }
        return num;
    }
}
