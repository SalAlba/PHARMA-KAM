package pl.kielce.tu.pharmacy.core.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.kielce.tu.pharmacy.core.dao.TransactionDAO;
import pl.kielce.tu.pharmacy.core.model.Transaction;

/**
 * Session Bean implementation class DefaultTransactionManager
 */
@Stateless
public class DefaultTransactionManager implements TransactionManager 
{
	@EJB
	private TransactionDAO transactionDAO;

	@Override
	public void updateTransaction(Transaction transaction) {
		transactionDAO.updateTransaction(transaction);
	}

	@Override
	public void removeTransaction(Transaction transaction) {
		transactionDAO.removeTransaction(transaction);
	}

	@Override
	public Transaction findTransaction(int id) {
		return transactionDAO.findTransaction(id);
	}

	@Override
	public Transaction createTransaction(Transaction transaction) {
		return transactionDAO.createTransaction(transaction);
	}
	
	@Override
	public List<Transaction> selectTransactions()
	{
		return transactionDAO.selectTransactions();
	}
}
