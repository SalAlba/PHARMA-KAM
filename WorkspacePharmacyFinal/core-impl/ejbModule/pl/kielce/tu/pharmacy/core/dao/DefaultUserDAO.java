package pl.kielce.tu.pharmacy.core.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.kielce.tu.pharmacy.core.model.DefaultUser;
import pl.kielce.tu.pharmacy.core.model.User;

/**
 * Session Bean implementation class DefaultUserDAO
 */
@Stateless
public class DefaultUserDAO implements UserDAO
{
	@PersistenceContext(name = "core-impl")
	private EntityManager entityManager;

	@Override
	public User createUser(User user) {
		entityManager.persist(user);
		return user;
	}
	
	@Override
	public User findUser(int id) {
		return entityManager.find(DefaultUser.class, id);
	}
	
	@Override
	public void removeUser(User user) {
		entityManager.remove(user);
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> selectUsers() 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultUser.findAll");
			return (List<User>) query.getResultList();
		
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
	
	@Override
	public User selectUserByEmail(String email) 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultUser.findByEmail");
			query.setParameter("email", email);
			return (User) query.getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
	
	@Override
	public User selectUserByEmailAndPassword(String email, String password) 
	{
		try
		{
			Query query = entityManager.createNamedQuery("DefaultUser.findByEmailAndPassword");
			query.setParameter("email", email);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		}
		catch (NoResultException e)
		{
			return null;
		}
	}
}
