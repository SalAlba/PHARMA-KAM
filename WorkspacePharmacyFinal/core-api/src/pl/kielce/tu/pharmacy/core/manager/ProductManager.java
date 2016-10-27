package pl.kielce.tu.pharmacy.core.manager;

import java.util.List;

import javax.ejb.Remote;

import pl.kielce.tu.pharmacy.core.model.Product;

@Remote
public interface ProductManager {

	void updateProduct(Product product);

	void removeProduct(int id);

	Product findProduct(int id);

	Product createProduct(Product product);

	List<Product> selectProducts();

	Product existProduct(int productLabel);

	List<Product> selectProductByType(String type);

	Product selectProductByProductLabel(int productLabel);

}