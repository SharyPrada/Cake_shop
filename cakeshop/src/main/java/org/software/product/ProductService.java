package org.software.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;




import org.software.util.DataBase;

@Path("/product")
public class ProductService {

	@GET
	@Path("/list/{category}")
	@Produces("application/json")
	// @Produces("application/xml")
	public ProductList getProductos(@PathParam(value = "category") int category) {
		ArrayList<Product> productList = new ArrayList<Product>();
		
		//int category_id = 1;
		
		DataBase database = new DataBase();
		Connection conexion1 = null;
		Statement statement1 = null;
		ResultSet rs1 = null;
		String sql = "";
		try {
			conexion1 = database.getConnection("admin");
			statement1 = conexion1.createStatement();
			sql = "select * from product where id_category = " + category;
			rs1 = statement1.executeQuery(sql);
			while (rs1.next()) {
				long id = rs1.getInt("id");
				int id_category = rs1.getInt("id_category");
				String name = rs1.getString("name");
				double price = rs1.getDouble("price");
				String photo = rs1.getString("photo");
				String description = rs1.getString("description");
				int posted = rs1.getInt("posted");
				
				
				Product product = new Product();
				product.setId(id);
				product.setId_category(id_category);
				product.setName(name);
				product.setPrice(price);
				product.setDescription(description);
				product.setPhoto(photo);
				product.setPosted(posted);
				productList.add(product);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			database.closeObject(rs1);
			database.closeObject(statement1);
			database.closeObject(conexion1);
		}
		
		return new ProductList(productList);
	}
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response add(Product product) {
		DataBase database = new DataBase();
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		String sql = "";
		String mensaje = "";
		int inserteds = 0;
		try {
			connection1 = database.getConnection("admin");
			sql = "INSERT INTO product(id_category, name, price, photo, description, posted)";
			sql += " VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement1 = connection1.prepareStatement(sql);
			preparedStatement1.setInt(1, product.getId_category());
			preparedStatement1.setString(2, product.getName());
			preparedStatement1.setDouble(3, (int) product.getPrice());
			preparedStatement1.setString(4, product.getPhoto());
			preparedStatement1.setString(5, product.getDescription());
			preparedStatement1.setInt(6, product.getPosted());
		
			
			
			inserteds = preparedStatement1.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(preparedStatement1);
			database.closeObject(connection1);
		}
		if (inserteds > 0) {
			mensaje = "{\"Mensaje\":\"Se adicionó correctamente.\"}";
			return Response.status(200).entity(mensaje).build();
		} else {
			mensaje = "{\"Mensaje\":\"Error al adicionar\"}";
			return Response.status(400).entity(mensaje).build();
		}
	}
	
	@GET
	@Path("/")
	@Produces("application/json")
// @Produces("application/xml")
	public ProductList getAll() {
		ArrayList<Product> productList = new ArrayList<Product>();
		DataBase database = new DataBase();
		Connection connection1 = null;
		Statement statement1 = null;
		ResultSet rs1 = null;
		String sql = "";
		try {
			connection1 = database.getConnection("admin");
			statement1 = connection1.createStatement();
			sql = "select * from product";
			rs1 = statement1.executeQuery(sql);
			while (rs1.next()) {
				int id = rs1.getInt("id");
			    int  id_category = rs1.getInt("id_category");
				String name = rs1.getString("name");
				double price = rs1.getDouble("price");
				String photo = rs1.getString("photo");
				String description = rs1.getString("description");
				int posted = rs1.getInt("posted");
				Product product = new Product();
				product.setId(id);
				product.setId_category(id_category);
			    product.setName(name);
				product.setPrice(price);
				product.setDescription(description);
				product.setPhoto(photo);
				product.setPosted(posted);
				productList.add(product);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(rs1);
			database.closeObject(statement1);
			database.closeObject(connection1);
		}
		return new ProductList(productList);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response update(Product product, @PathParam(value = "id") int id) {
		DataBase database = new DataBase();
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		String sql = "";
		String mensaje = "";
		int updates = 0;
		try {
			connection1 = database.getConnection("admin");
			sql = "UPDATE product SET  id_category=?, name=?, price=?, photo=?, description=?, posted=?";
			sql += " WHERE id=?";
			preparedStatement1 = connection1.prepareStatement(sql);

			preparedStatement1.setInt(1, product.getId_category());
			preparedStatement1.setString(2, product.getName());
			preparedStatement1.setDouble(3, product.getPrice());
			preparedStatement1.setString(4, product.getPhoto());
			preparedStatement1.setString(5, product.getDescription());
			preparedStatement1.setInt(6, product.getPosted());
			preparedStatement1.setInt(7, id);
			updates = preparedStatement1.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(preparedStatement1);
			database.closeObject(connection1);
		}
		if (updates > 0) {
			mensaje = "{\"Mensaje\":\"Se modificó correctamente.\"}";
			return Response.status(200).entity(mensaje).build();
		} else {
			mensaje = "{\"Mensaje\":\"Error al modificar\"}";
			return Response.status(400).entity(mensaje).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response adicionar(@PathParam(value = "id") int id) {
		DataBase database = new DataBase();
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		String sql = "";
		String mensaje = "";
		int deleteds = 0;
		try {
			connection1 = database.getConnection("admin");
			sql = "DELETE FROM product WHERE id=?";
			preparedStatement1 = connection1.prepareStatement(sql);
			preparedStatement1.setInt(1, id);
			deleteds = preparedStatement1.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(preparedStatement1);
			database.closeObject(connection1);
		}
		if (deleteds > 0) {
			mensaje = "{\"Mensaje\":\"Se eliminó correctamente.\"}";
			return Response.status(200).entity(mensaje).build();
		} else {
			mensaje = "{\"Mensaje\":\"Error al eliminar\"}";
			return Response.status(400).entity(mensaje).build();
		}
	}
	
}