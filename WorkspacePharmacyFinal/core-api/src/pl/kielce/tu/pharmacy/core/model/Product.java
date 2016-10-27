package pl.kielce.tu.pharmacy.core.model;

import java.util.List;

public interface Product {

	int getId();

	void setId(int id);

	int getAvailable();

	void setAvailable(int available);

	String getName();

	void setName(String name);

	double getPrice();

	void setPrice(double price);

	int getProductLabel();

	void setProductLabel(int productLabel);

	int getRefunded();

	void setRefunded(int refunded);

	String getExpirationDate();

	void setExpirationDate(String expirationDate);

	String getType();

	void setType(String type);
	
	void setLink(String link);

	String getLink();

	List<Transaction> getTransactions();

	void setTransactions(List<Transaction> transactions);

	Transaction addTransaction(Transaction transaction);

	Transaction removeTransaction(Transaction transaction);
}