package pl.kielce.tu.pharmacy.web.managed;

import pl.kielce.tu.pharmacy.web.back.UserBean;

public interface UserActionBean {

	UserBean getRegisterBean();

	void setRegisterBean(UserBean registerBean);

	void registerUser();

	String checkUserSessionAttribute();

	void redirectIfNoSession();

	String logout();

	String login(String email, String password);

	String checkUserSurname();

	String checkUserName();

	String checkUserEmail();

	boolean checkUserSession();

	void setTransaction(int transaction);

	int getTransaction();

}