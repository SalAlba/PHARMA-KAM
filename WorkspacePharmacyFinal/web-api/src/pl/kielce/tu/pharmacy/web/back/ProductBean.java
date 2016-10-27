package pl.kielce.tu.pharmacy.web.back;

import java.util.List;

import pl.kielce.tu.pharmacy.core.model.Product;

public interface ProductBean {

	Product getProduct();

	void setProduct(Product product);

	void init();

	void setFilteredProducts(List<Product> filteredProducts);

	List<Product> getFilteredProducts();

	void setProducts(List<Product> products);

	List<Product> getProducts();

	void setImageLink();

	String getImageLink();

	void setProductsByType(List<Product> productsByType);

	List<Product> getProductsByType();

	void reset();
}