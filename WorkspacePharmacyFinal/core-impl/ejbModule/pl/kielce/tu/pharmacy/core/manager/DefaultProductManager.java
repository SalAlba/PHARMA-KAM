package pl.kielce.tu.pharmacy.core.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.kielce.tu.pharmacy.core.dao.ProductDAO;
import pl.kielce.tu.pharmacy.core.model.Product;

/**
 * Session Bean implementation class DefaultUserProduct
 */
@Stateless
public class DefaultProductManager implements ProductManager 
{
	@EJB
	private ProductDAO productDAO;

	@Override
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}

	@Override
	public void removeProduct(int id) {
		productDAO.removeProduct(id);
	}

	@Override
	public Product findProduct(int id) {
		return productDAO.findProduct(id);
	}

	@Override
	public Product createProduct(Product product) {
		return productDAO.createProduct(product);
	}
	
	@Override
	public List<Product> selectProducts()
	{
		return productDAO.selectProducts();
	}
	
	@Override
	public Product existProduct(int productLabel)
	{
		return productDAO.selectProductByProductLabel(productLabel);
	}
	
	@Override
	public Product selectProductByProductLabel(int productLabel)
	{
		return productDAO.selectProductByProductLabel(productLabel);
	}
	
	@Override
	public List<Product> selectProductByType(String type)
	{
		return productDAO.selectProductByType(type);
	}
}
