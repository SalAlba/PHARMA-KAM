package pl.kielce.tu.pharmacy.web.managed;

import java.util.List;

import pl.kielce.tu.pharmacy.core.model.Product;
import pl.kielce.tu.pharmacy.web.back.TransactionBean;

public interface TransactionActionBean {

	TransactionBean getTransactionBean();

	void setTransactionBean(TransactionBean transactionBean);

	void addTransaction();

	void init();

	void setProductCart(List<Product> productCart);

	List<Product> getProductCart();
	
	void addProductToCart(Product product);

	void removeProductInCart(Product product);

	void clearCart();

	double getPriceOfCart();

	void setPriceOfCart();

	int getSizeOfCart();

	void setSizeOfCart();

}