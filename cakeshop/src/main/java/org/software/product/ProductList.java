package org.software.product;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="product")
public class ProductList {
	private List<Product> products;

	public ProductList() {
	}

	public ProductList(List<Product> products) {

		this.products = products;
	}

	@XmlElement(name = "data")
	public List<Product> getProducts() {
		return products;
	}
}