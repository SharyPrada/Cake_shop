package org.software.category;

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

@Path("/category")
public class CategoryService {
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response add(Category category) {
		DataBase database = new DataBase();
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		String sql = "";
		String mensaje = "";
		int inserteds = 0;
		try {
			connection1 = database.getConnection("admin");
			sql ="SELECT SETVAL ('category_id_seq', (SELECT MAX(id) FROM category))";
			sql = "INSERT INTO category(name, icon)";
			sql += " VALUES (?, ?)";
			preparedStatement1 = connection1.prepareStatement(sql);
			preparedStatement1.setString(1, category.getName());
			preparedStatement1.setString(2, category.getIcon());
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
	public CategoryList getAll() {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		DataBase database = new DataBase();
		Connection connection1 = null;
		Statement statement1 = null;
		ResultSet rs1 = null;
		String sql = "";
		try {
			connection1 = database.getConnection("admin");
			statement1 = connection1.createStatement();
			sql = "select * from category";
			rs1 = statement1.executeQuery(sql);
			while (rs1.next()) {
				int id = rs1.getInt("id");
				String name = rs1.getString("name");
				String icon = rs1.getString("icon");
				Category category = new Category();
				category.setId(id);
				category.setName(name);
				category.setIcon(icon);
				categoryList.add(category);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(rs1);
			database.closeObject(statement1);
			database.closeObject(connection1);
		}
		return new CategoryList(categoryList);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response update(Category category, @PathParam(value = "id") int id) {
		DataBase database = new DataBase();
		Connection connection1 = null;
		PreparedStatement preparedStatement1 = null;
		String sql = "";
		String mensaje = "";
		int updates = 0;
		try {
			connection1 = database.getConnection("admin");
			sql = "UPDATE category SET  name=?, icon=?";
			sql += " WHERE id=?";
			preparedStatement1 = connection1.prepareStatement(sql);

			
			preparedStatement1.setString(1, category.getName());
			preparedStatement1.setString(2, category.getIcon());
			preparedStatement1.setInt(3, id);
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
			sql = "DELETE FROM category WHERE id=?";
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