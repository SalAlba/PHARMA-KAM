package pl.kielce.tu.pharmacy.web.managed;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pl.kielce.tu.pharmacy.core.manager.ProductManager;
import pl.kielce.tu.pharmacy.core.model.Product;
import pl.kielce.tu.pharmacy.core.shared.AbstractProductFactory;
import pl.kielce.tu.pharmacy.web.messages.Messages;
import pl.kielce.tu.pharmacy.web.back.ProductBean;

@Named("defaultProductActionBean")
@ApplicationScoped
public class DefaultProductActionBean implements ProductActionBean
{
	@Inject
    private ProductBean productBean;
	
	@EJB
	private ProductManager productManager;
	
	AbstractProductFactory factory = AbstractProductFactory.getInstance();
	
	public DefaultProductActionBean() 
	{
    }

	@Override
	public ProductBean getProductBean() {
		return productBean;
	}

	@Override
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
	
	@Override
	public void addProduct() 
	{	
		if(productManager.existProduct(this.productBean.getProduct().getProductLabel()) == null)
		{
			if(this.productBean.getProduct().getName().isEmpty() || this.productBean.getProduct().getProductLabel() == 0 || 
					this.productBean.getProduct().getPrice() == 0 || this.productBean.getProduct().getExpirationDate().isEmpty() || 
					this.productBean.getProduct().getType().isEmpty() ||
					this.productBean.getProduct().getLink().isEmpty())
				Messages.errorMessage("Fields: ", "Enter data.");
			else
			{
				this.productBean.getProduct().setAvailable(1);
				productManager.createProduct(this.productBean.getProduct());
				Messages.infoMessage("Product: ", "Insterted.");
				
				this.productBean.setProducts(productManager.selectProducts());
				
				this.clean();
			}	
		}
		else
		{	
			Product p = productManager.existProduct(this.productBean.getProduct().getProductLabel());
			
			p.setAvailable(p.getAvailable() + 1);
			
			productManager.updateProduct(p);
			Messages.infoMessage("Product: ", "Updated available.");
			
			this.productBean.setProducts(productManager.selectProducts());
			
			this.clean();
		}
	}
	
	@Override
	public void removeProduct(int id)
	{
		productManager.removeProduct(id);
		this.productBean.setProducts(productManager.selectProducts());
	}
	
	@Override
	public void updateProduct()
	{
		if(this.productBean.getProduct().getName().isEmpty() || this.productBean.getProduct().getProductLabel() == 0 || 
				this.productBean.getProduct().getPrice() == 0 || this.productBean.getProduct().getExpirationDate().isEmpty() || 
				this.productBean.getProduct().getType().isEmpty() || this.productBean.getProduct().getLink().isEmpty())
			Messages.errorMessage("Fields: ", "Enter data.");
		else
		{
			Product p = factory.getProduct();
			p.setId(this.productBean.getProduct().getId());
			p.setName(this.productBean.getProduct().getName());
			p.setProductLabel(this.productBean.getProduct().getProductLabel());
			p.setPrice(this.productBean.getProduct().getPrice());
			p.setAvailable(this.productBean.getProduct().getAvailable());
			p.setExpirationDate(this.productBean.getProduct().getExpirationDate());
			p.setRefunded(this.productBean.getProduct().getRefunded());
			p.setType(this.productBean.getProduct().getType());
			p.setLink(this.productBean.getProduct().getLink());
			productManager.updateProduct(p);
			
			this.clean();
			
			this.productBean.setProducts(productManager.selectProducts());
		}
	}
	
	@Override
	public void getProduct(int id)
	{
		Product p = productManager.findProduct(id);
		
		this.productBean.setProduct(p);
	}
	
	@Override
	public void clean()
	{	
		this.productBean.getProduct().setId(0);
		this.productBean.getProduct().setName("");
		this.productBean.getProduct().setProductLabel(0);
		this.productBean.getProduct().setPrice(0);
		this.productBean.getProduct().setAvailable(0);
		this.productBean.getProduct().setExpirationDate("");
		this.productBean.getProduct().setRefunded(0);
		this.productBean.getProduct().setType("");
		this.productBean.getProduct().setLink("");
	}
	
	public List<Product> getProductsByType(String type)
	{
		this.productBean.setProductsByType(this.productManager.selectProductByType(type));
		return this.productBean.getProductsByType();
	}
}