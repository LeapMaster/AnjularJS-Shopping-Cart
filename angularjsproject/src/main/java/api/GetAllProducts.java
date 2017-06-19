package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Product;
import persistence.ProductDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Shea Prewett on 6/19/17.
 */
@Path("/all")
public class GetAllProducts {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTasks() {

        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAllProducts();

        String output = "";
        Gson gson = new Gson();

        if (!(products.size() > 0)) {
            output = "No products found!";
        } else {
            // Serialize the list of products to send to target
            output = new GsonBuilder().create().toJson(products);
        }

        return Response.status(200).entity(output).build();
    }
}
