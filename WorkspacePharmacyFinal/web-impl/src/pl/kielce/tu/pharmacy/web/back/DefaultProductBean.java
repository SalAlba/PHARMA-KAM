package pl.kielce.tu.pharmacy.web.back;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import pl.kielce.tu.pharmacy.core.manager.ProductManager;
import pl.kielce.tu.pharmacy.core.model.Product;
import pl.kielce.tu.pharmacy.core.shared.AbstractProductFactory;

@Named("defaultProductBean")
@ApplicationScoped
public class DefaultProductBean implements ProductBean 
{
	@EJB
    private ProductManager productManager;
	
	AbstractProductFactory factory = AbstractProductFactory.getInstance();
	
	private Product product = factory.getProduct();
	
	private List<Product> products;
	private List<Product> filteredProducts;
	
	private List<Product> productsByType;
	
	private UploadedFile file;

	public DefaultProductBean()
	{
	}
	
	@Override
	public Product getProduct() {
		return product;
	}
	
	@Override
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public List<Product> getFilteredProducts() {
		return filteredProducts;
	}

	@Override
	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}
	
	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    @Override
	public void setImageLink()
    {
    	this.product.setLink("webroot/images/products" + file.getFileName());
    }
    
    @Override
	public String getImageLink()
    {
    	return this.product.getLink();
    }
    
    @Override
	public List<Product> getProductsByType() {
		return productsByType;
	}

	@Override
	public void setProductsByType(List<Product> productsByType) {
		this.productsByType = productsByType;
	}
	
	@Override
	public void reset() {
        RequestContext.getCurrentInstance().reset("form:panel");
	}
	
	@Override
	@PostConstruct
	public void init() 
	{
		this.products = productManager.selectProducts();
	}
}
