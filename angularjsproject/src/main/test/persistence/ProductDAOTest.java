package persistence;

import entity.Product;
import org.junit.Test;

import java.util.List;

/**
 * Created by Shea Prewett on 6/19/17.
 */
public class ProductDAOTest {

    @Test
    public void getAllProducts() {
        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAllProducts();
        assert(products.size() > 0);
        for (Product product : products) {
            System.out.println(product.getName());
            System.out.println("$" + product.getPrice());
        }
    }

    @Test
    public void insertAndDeleteProduct() {
        ProductDAO dao = new ProductDAO();
        int startingSize = dao.getAllProducts().size();
        Integer newProductID = dao.insertProduct("test", 9.99);
        int endingSize = dao.getAllProducts().size();
        assert((endingSize - startingSize) == 1);
        Integer deleteStatus = dao.deleteProductByID(newProductID);
        assert(deleteStatus == 1);
        endingSize = dao.getAllProducts().size();
        assert(endingSize == startingSize);
    }
}
