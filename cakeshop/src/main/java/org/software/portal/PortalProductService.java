package org.software.portal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.software.product.Product;
import org.software.product.ProductList;
import org.software.util.DataBase;

@Path("/portal")
public class PortalProductService {
	@GET
	@Path("/products/{id_category}")
	@Produces("application/json")
	public ProductList getProducts(@PathParam(value = "id_category") int id_category) {
		ArrayList<Product> productList = new ArrayList<Product>();
		DataBase database = new DataBase();
		Connection connection1 = null;
		Statement statement1 = null;
		ResultSet rs1 = null;
		String sql = "";
		try {
			connection1 = database.getConnection("admin");
			statement1 = connection1.createStatement();
			sql = "select * from product where id_category = " + id_category;
			rs1 = statement1.executeQuery(sql);
			while (rs1.next()) {
				long id = rs1.getLong("id");
				int category_id = rs1.getInt("id_category");
				String name = rs1.getString("name");
				double price = rs1.getDouble("price");
				String icon = rs1.getString("photo");
				String description = rs1.getString("description");
				// String long_description = rs1.getString("long_description");
				Product product = new Product();
				product.setId(id);

				product.setId_category(category_id);
				product.setName(name);
				product.setPrice(price);
				product.setPhoto(icon);
				product.setDescription(description);
				// product.setLong_description(long_description);
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
}
