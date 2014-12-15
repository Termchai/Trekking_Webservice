package sabaii.trekking.resource;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import sabaii.trekking.database.ProductDao;
import sabaii.trekking.entity.Product;
import sabaii.trekking.jpa.JpaDaoFactory;

/**
 * Controller of the webservice
 * @author Sabaii Soft. SKE10
 *
 */
@Path("/products")
public class ProductResource {
	private ProductDao dao = JpaDaoFactory.getInstance().getProductDao();
	
	@Context
	UriInfo uriInfo;
	
	/**
	 * get product by ID
	 * @param id is the id of product
	 * @param none_match header param to check last update
	 * @param match header param to check last update
	 * @return Entity of the product
	 */
	@GET
	@Path("{id}")
	public Response getProductByID(@PathParam("id") String id, @HeaderParam ("If-None-Match") String none_match, @HeaderParam ("If-Match") String match)
	{
		Product p = dao.find(Long.parseLong(id));
		if (p==null)
			return Response.status(Status.NOT_FOUND).build();
		if (none_match != null && none_match.equals("\""+p.hashCode()+"\"") || match != null && !match.equals("\""+p.hashCode()+"\""))
			return Response.status(Status.NOT_MODIFIED).build();
		
		return Response.ok(p).tag(new EntityTag(p.hashCode()+"")).build();
	}

	/**
	 * Get all product
	 * @return Entity of all product
	 */
	@GET
	@Produces (MediaType.APPLICATION_XML)
	public Response getProducts()
	{
		GenericEntity<List<Product>> entity;
		entity = new GenericEntity<List<Product>>(dao.findAll()) {};
		
		return Response.ok(entity).build();
	}

}
