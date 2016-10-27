package pl.kielce.tu.pharmacy.core.manager;

import java.util.List;

import javax.ejb.Remote;

import pl.kielce.tu.pharmacy.core.model.User;

@Remote
public interface UserManager {

	List<User> selectUsers();

	User createUser(User user);

	User findUser(int id);

	void removeUser(User user);

	void updateUser(User user);

	User existEmail(String email);

	User loginUser(String email, String password);

}
