package pl.kielce.tu.pharmacy.core.shared;

import pl.kielce.tu.pharmacy.core.model.DefaultTransaction;
import pl.kielce.tu.pharmacy.core.model.Transaction;

public class AbstractTransactionFactory 
{	
	private static AbstractTransactionFactory instance;
	
	private AbstractTransactionFactory(){
	};
	
	public static AbstractTransactionFactory getInstance()
	{
		return (instance == null) ? new AbstractTransactionFactory() : instance;
	}
	
	public Transaction getTransaction()
	{
		return new DefaultTransaction();
	}
}
