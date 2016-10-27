package pl.kielce.tu.pharmacy.core.model;

public interface Transaction {

	int getId();

	void setId(int id);

	int getAmount();

	void setAmount(int amount);

	int getState();

	void setState(int state);

	String getTransactionDate();

	void setTransactionDate(String transactionDate);

	Product getProduct();

	void setProduct(Product product);

	User getUser();

	void setUser(User user);

}