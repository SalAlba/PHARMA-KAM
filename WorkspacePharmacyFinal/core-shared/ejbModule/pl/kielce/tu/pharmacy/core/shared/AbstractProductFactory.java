package pl.kielce.tu.pharmacy.core.shared;

import pl.kielce.tu.pharmacy.core.model.DefaultProduct;
import pl.kielce.tu.pharmacy.core.model.Product;

public class AbstractProductFactory 
{
	private static AbstractProductFactory instance;
	
	private AbstractProductFactory(){
	};
	
	public static AbstractProductFactory getInstance()
	{
		return (instance == null) ? new AbstractProductFactory() : instance;
	}
	
	public Product getProduct()
	{
		return new DefaultProduct();
	}
}
