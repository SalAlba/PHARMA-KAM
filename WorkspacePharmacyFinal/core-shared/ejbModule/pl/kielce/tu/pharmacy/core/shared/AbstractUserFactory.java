package pl.kielce.tu.pharmacy.core.shared;

import pl.kielce.tu.pharmacy.core.model.DefaultUser;
import pl.kielce.tu.pharmacy.core.model.User;

public class AbstractUserFactory 
{	
	private static AbstractUserFactory instance;
	
	private AbstractUserFactory(){
	};
	
	public static AbstractUserFactory getInstance()
	{
		return (instance == null) ? new AbstractUserFactory() : instance;
	}
	
	public User getUser()
	{
		return new DefaultUser();
	}
}