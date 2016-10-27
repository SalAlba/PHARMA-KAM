package pl.kielce.tu.pharmacy.web.back;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import pl.kielce.tu.pharmacy.core.model.User;
import pl.kielce.tu.pharmacy.core.shared.AbstractUserFactory;

@Named("defaultUserBean")
@RequestScoped
public class DefaultUserBean implements UserBean 
{	
	AbstractUserFactory factory = AbstractUserFactory.getInstance();
	
	private User user = factory.getUser();

	public DefaultUserBean()
	{
	}
	
	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
}
