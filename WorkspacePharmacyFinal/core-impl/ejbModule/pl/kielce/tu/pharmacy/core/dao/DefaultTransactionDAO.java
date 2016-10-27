package pl.kielce.tu.pharmacy.core.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kielce.tu.pharmacy.core.model.DefaultTransaction;
import pl.kielce.tu.pharmacy.core.model.Transaction;

/**
 * Session Bean implementation class DefaultTransactionDAO
 */
@Stateless
public class DefaultTransactionDAO implements TransactionDAO 
{
	@PersistenceContext(name = "core-impl")
	private EntityManager entityManager;

	@Override
	public Transaction createTransaction(Transaction transaction) {
		entityManager.persist(transaction);
		return transaction;
	}
	
	@Override
	public Transaction findTransaction(int id) {
		return entityManager.find(DefaultTransaction.class, id);
	}
	
	@Override
	public void removeTransaction(Transaction transaction) {
		entityManager.remove(transaction);
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		entityManager.merge(transaction);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Transaction> selectTransactions() 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultTransaction.findAll");
			return (List<Transaction>) query.getResultList();
		
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
}
