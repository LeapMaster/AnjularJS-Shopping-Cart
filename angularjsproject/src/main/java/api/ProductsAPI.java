package api;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shea Prewett on 6/19/17.
 */
public class ProductsAPI extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet classes = new HashSet<Class<?>>();
        classes.add(DeleteProduct.class);
        classes.add(InsertProduct.class);
        classes.add(GetAllProducts.class);
        return classes;
    }
}
