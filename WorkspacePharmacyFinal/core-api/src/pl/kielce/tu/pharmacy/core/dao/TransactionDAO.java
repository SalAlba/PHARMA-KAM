package pl.kielce.tu.pharmacy.core.dao;

import java.util.List;

import javax.ejb.Local;

import pl.kielce.tu.pharmacy.core.model.Transaction;

@Local
public interface TransactionDAO {

	List<Transaction> selectTransactions();

	void updateTransaction(Transaction transaction);

	void removeTransaction(Transaction transaction);

	Transaction findTransaction(int id);

	Transaction createTransaction(Transaction transaction);

}
