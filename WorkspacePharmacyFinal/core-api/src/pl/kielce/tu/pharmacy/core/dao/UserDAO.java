package pl.kielce.tu.pharmacy.core.dao;

import java.util.List;

import javax.ejb.Local;

import pl.kielce.tu.pharmacy.core.model.User;

@Local
public interface UserDAO {

	List<User> selectUsers();

	void updateUser(User user);

	void removeUser(User user);

	User findUser(int id);

	User createUser(User user);

	User selectUserByEmail(String email);

	User selectUserByEmailAndPassword(String email, String password);

}
