package pl.kielce.tu.pharmacy.core.dao;

import java.util.List;

import javax.ejb.Local;

import pl.kielce.tu.pharmacy.core.model.Product;

@Local
public interface ProductDAO {

	Product createProduct(Product product);

	Product findProduct(int id);

	void removeProduct(int id);

	void updateProduct(Product product);

	List<Product> selectProducts();

	Product selectProductByProductLabel(int productLabel);

	List<Product> selectProductByType(String type);

}