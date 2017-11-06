import java.io.IOException;

public class CategoryPageParser extends PageParser {
    int pages;
    Product maxProduct;
    public CategoryPageParser(String url) throws IOException {
        super(url);
        maxProduct = new Product("No commented products", "None", 0);
        pages = getPageNumbers();
    }

    public void getMostCommentedProduct() throws IOException {
        for(int i = 0; i < pages; i++) {
            ProductListPageParser prodListParser = new ProductListPageParser(url + "page=" + String.valueOf(i + 1));
            maxProduct = prodListParser.getMostPopularProduct(maxProduct);
        }
        if(maxProduct.comments > 0) {
            System.out.println("The most commented product " + maxProduct.name +
                    " has " + maxProduct.comments + " comments (" + maxProduct.url + ")");
        } else {
            System.out.println(maxProduct.name);
        }
    }
}
