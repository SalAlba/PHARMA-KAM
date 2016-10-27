package pl.kielce.tu.pharmacy.web.back;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import pl.kielce.tu.pharmacy.core.model.Transaction;
import pl.kielce.tu.pharmacy.core.shared.AbstractTransactionFactory;

@Named("defaultTransactionBean")
@RequestScoped
public class DefaultTransactionBean implements TransactionBean 
{
	AbstractTransactionFactory factory = AbstractTransactionFactory.getInstance();
	
	private Transaction transaction = factory.getTransaction();
	
	private String payment;

	public DefaultTransactionBean()
	{
	}

	@Override
	public Transaction getTransaction() {
		return transaction;
	}
	
	@Override
	public void setProduct(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public String getPayment() {
		return payment;
	}

	@Override
	public void setPayment(String payment) {
		this.payment = payment;
	}
}
