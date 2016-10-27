package pl.kielce.tu.pharmacy.web.managed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import pl.kielce.tu.pharmacy.core.manager.ProductManager;
import pl.kielce.tu.pharmacy.core.manager.TransactionManager;
import pl.kielce.tu.pharmacy.core.manager.UserManager;
import pl.kielce.tu.pharmacy.core.model.Product;
import pl.kielce.tu.pharmacy.core.model.User;
import pl.kielce.tu.pharmacy.web.back.ProductBean;
import pl.kielce.tu.pharmacy.web.back.TransactionBean;
import pl.kielce.tu.pharmacy.web.messages.Messages;
import pl.kielce.tu.pharmacy.web.payment.CreditCardHandler;
import pl.kielce.tu.pharmacy.web.payment.MoneyTransferHandler;
import pl.kielce.tu.pharmacy.web.payment.PayPalHandler;
import pl.kielce.tu.pharmacy.web.payment.PaymentHandler;

@Named("defaultTransactionActionBean")
@ApplicationScoped
public class DefaultTransactionActionBean implements TransactionActionBean
{	
	@Inject
    private TransactionBean transactionBean;
	
	@Inject
	private ProductBean productBean;
	
	private List<Product> productCart;
	
	@EJB
	private TransactionManager transactionManager;
	
	@EJB
	private UserManager userManager;
	
	@EJB
	private ProductManager productManager;
	
	private double priceOfCart;
	
	private int sizeOfCart;
	
	public DefaultTransactionActionBean() 
	{
    }
	
	@Override
	public List<Product> getProductCart() {
		return productCart;
	}

	@Override
	public void setProductCart(List<Product> productCart) {
		this.productCart = productCart;
	}

	@Override
	public TransactionBean getTransactionBean() {
		return transactionBean;
	}

	@Override
	public void setTransactionBean(TransactionBean transactionBean) {
		this.transactionBean = transactionBean;
	}
	
	@Override
	public void addTransaction() 
	{	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		if(!this.productCart.isEmpty())
		{
			if(session.getAttribute("user") != null)
			{	
				PaymentHandler chain = setUpChain();
				
				chain.handleRequest(transactionBean.getPayment());
				
				int id = (int) session.getAttribute("uid");
				
				User u = userManager.findUser(id);
				
				SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
				Date date = new Date();
				
				transactionBean.getTransaction().setTransactionDate(ft.format(date).toString());
				transactionBean.getTransaction().setAmount(1);
				transactionBean.getTransaction().setState(1);
				transactionBean.getTransaction().setUser(u);
				transactionBean.getTransaction().setProduct(this.productCart.get(0));
				
				transactionManager.createTransaction(transactionBean.getTransaction());
				
				Product p = productManager.findProduct(this.productCart.get(0).getId());
				p.setAvailable(p.getAvailable() - 1);
				
				productManager.updateProduct(p);
			
				productBean.setProducts(productManager.selectProducts());
				
				Messages.infoMessage("Transaction: ", "Inserted.");
			}
			else
				Messages.infoMessage("Transaction: ", "You have to log in.");
		}
		else
			Messages.infoMessage("Transaction: ", "0 products in cart.");
		
	}
	
	@Override
	@PostConstruct
	public void init() 
	{
		this.productCart = new ArrayList<Product>();
	}
	
	@Override
	public void addProductToCart(Product product)
	{	
		if(product.getAvailable() > 0)
		{
			this.productCart.add(product);
			
			this.priceOfCart += product.getPrice();
			this.sizeOfCart++;
		}
	}
	
	@Override
	public void removeProductInCart(Product product)
	{
		this.productCart.remove(product);
		
		this.priceOfCart -= product.getPrice();
		this.sizeOfCart--;
	}
	
	@Override
	public void clearCart()
	{
		this.productCart.clear();
		
		this.sizeOfCart = 0;
		this.priceOfCart = 0;
	}
	
	@Override
	public void setPriceOfCart()
	{
		for(Product p : productCart)
		{
			this.priceOfCart += p.getPrice();
		}
	}
	
	@Override
	public double getPriceOfCart()
	{
		return this.priceOfCart;
	}
	
	@Override
	public void setSizeOfCart()
	{
		this.sizeOfCart = this.productCart.size();
	}
	
	@Override
	public int getSizeOfCart()
	{
		return this.sizeOfCart;
	}
	
	private PaymentHandler setUpChain() 
	{
		PaymentHandler creditCardHandler = new CreditCardHandler();
		PaymentHandler payPalHandler = new PayPalHandler();
		PaymentHandler moneyHandler = new MoneyTransferHandler();

		creditCardHandler.setSuccessor(payPalHandler);
		payPalHandler.setSuccessor(moneyHandler);

		return creditCardHandler;
	}
}
