package pl.kielce.tu.pharmacy.core.manager;

import java.util.List;

import javax.ejb.Remote;

import pl.kielce.tu.pharmacy.core.model.Transaction;

@Remote
public interface TransactionManager {

	List<Transaction> selectTransactions();

	Transaction createTransaction(Transaction transaction);

	Transaction findTransaction(int id);

	void removeTransaction(Transaction transaction);

	void updateTransaction(Transaction transaction);

}
