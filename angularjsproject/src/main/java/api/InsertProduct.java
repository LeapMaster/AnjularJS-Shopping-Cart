package api;

import com.google.gson.*;
import entity.Product;
import persistence.ProductDAO;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shea Prewett on 6/19/17.
 */
@Path("/insert")
public class InsertProduct {

    @POST
    @Produces("text/plain")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response insertProduct(String jsonData) {

        ProductDAO dao = new ProductDAO();

        // Parse the JSON parameter into valid data
        JsonElement jsonElement = new JsonParser().parse(jsonData);
        JsonObject object = jsonElement.getAsJsonObject();
        String nameValue = object.get("name").toString();

        // Remove the quotes from the string
        nameValue = nameValue.substring(1, nameValue.length() - 1);
        Double priceValue = Double.parseDouble(object.get("price").toString());

        // Insert the product
        Integer newProductID = dao.insertProduct(nameValue, priceValue);

        // 0 is returned on error, bad insert
        if (newProductID == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)// 400
                    .entity("Product was not inserted. ").build();
        } else {
            return Response
                    .status(Response.Status.CREATED)// 201
                    .entity("#" + newProductID).build();
        }
    }



}
