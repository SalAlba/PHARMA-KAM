package pl.kielce.tu.pharmacy.web.back;

import pl.kielce.tu.pharmacy.core.model.Transaction;

public interface TransactionBean {

	Transaction getTransaction();

	void setProduct(Transaction transaction);

	void setPayment(String payment);

	String getPayment();

}