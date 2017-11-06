import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class ProductListPageParser extends PageParser {
    public ProductListPageParser(String url){
        super(url);
    }

    public Product getMostPopularProduct(Product maxProduct) throws IOException {
        Document doc = Jsoup.connect(url).get();
        new File("data").mkdirs();
        Elements products = doc.select("div.g-i-tile-i-title a");
        for(int i = 0; i < products.size(); i++) {
            ProductPageParser prodParcer = new ProductPageParser(products.get(i).attr("href") + "comments/");
            maxProduct = prodParcer.getMostPopularProduct(maxProduct);
        }
        return maxProduct;
    }
}
