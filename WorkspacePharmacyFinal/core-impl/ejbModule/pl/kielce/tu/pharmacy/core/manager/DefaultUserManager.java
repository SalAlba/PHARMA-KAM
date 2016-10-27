package pl.kielce.tu.pharmacy.core.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.kielce.tu.pharmacy.core.dao.UserDAO;
import pl.kielce.tu.pharmacy.core.model.User;

/**
 * Session Bean implementation class DefaultUserManager
 */
@Stateless
public class DefaultUserManager implements UserManager 
{
	@EJB
	private UserDAO userDAO;

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public void removeUser(User user) {
		userDAO.removeUser(user);
	}

	@Override
	public User findUser(int id) {
		return userDAO.findUser(id);
	}

	@Override
	public User createUser(User user) {
		return userDAO.createUser(user);
	}
	
	@Override
	public List<User> selectUsers()
	{
		return userDAO.selectUsers();
	}
	
	@Override
	public User existEmail(String email)
	{
		return userDAO.selectUserByEmail(email);
	}
	
	@Override
	public User loginUser(String email, String password)
	{
		return userDAO.selectUserByEmailAndPassword(email, password);
	}
}
