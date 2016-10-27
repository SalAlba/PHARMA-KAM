package pl.kielce.tu.pharmacy.core.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kielce.tu.pharmacy.core.model.DefaultProduct;
import pl.kielce.tu.pharmacy.core.model.Product;

/**
 * Session Bean implementation class DefaultProductDAO
 */
@Stateless
public class DefaultProductDAO implements ProductDAO 
{
	@PersistenceContext(name = "core-impl")
	private EntityManager entityManager;

	@Override
	public Product createProduct(Product product) {
		entityManager.persist(product);
		return product;
	}
	
	@Override
	public Product findProduct(int id) {
		return entityManager.find(DefaultProduct.class, id);
	}
	
	@Override
	public void removeProduct(int id) {
		entityManager.remove(findProduct(id));
	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> selectProducts() 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultProduct.findAll");
			return (List<Product>) query.getResultList();
		
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
	
	@Override
	public Product selectProductByProductLabel(int productLabel) 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultProduct.findByProductLabel");
			query.setParameter("productLabel", productLabel);
			return (Product) query.getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> selectProductByType(String type) 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultProduct.findByType");
			query.setParameter("type", type);
			return (List<Product>) query.getResultList();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
}
