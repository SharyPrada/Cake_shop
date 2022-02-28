package org.software.category;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
public class CategoryList {
	private List<Category> categories;

	public CategoryList() {
	}

	public CategoryList(List<Category> categories) {
		this.categories = categories;
	}

	@XmlElement(name = "data")
	public List<Category> getCategories() {
		return categories;
	}
}
