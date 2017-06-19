package api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import persistence.ProductDAO;

import javax.transaction.Transactional;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Shea Prewett on 6/19/17.
 */
@Path("/delete")
public class DeleteProduct {

    @POST
    @Produces("text/plain")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public Response deleteProduct(String productID) {

        String output = "";

        // Parse JSON parameter into valid data
        JsonElement jsonElement = new JsonParser().parse(productID);
        JsonObject object = jsonElement.getAsJsonObject();
        Integer idValue = Integer.valueOf(object.get("id").toString());

        ProductDAO dao = new ProductDAO();
        // Check for valid id before insert
        if (idValue != null && idValue >= 0) {
            int status = dao.deleteProductByID(idValue);
            // 1 is returned on success, 0 on failure
            if (status == 1) {
                output = "Delete successful.";
                return Response.status(200).entity(output).build();
            } else {
                output = "Delete failed.";
                return Response
                        .status(Response.Status.BAD_REQUEST)// 400
                        .entity(output).build();
            }
        }else {
            output = "Invalid ID value.";
            return Response
                    .status(Response.Status.BAD_REQUEST)// 400
                    .entity(output).build();
        }

    }
}
