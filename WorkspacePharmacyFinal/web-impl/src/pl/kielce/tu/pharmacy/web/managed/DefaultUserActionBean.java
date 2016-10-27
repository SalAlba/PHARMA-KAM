package pl.kielce.tu.pharmacy.web.managed;

import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.kielce.tu.pharmacy.core.manager.UserManager;
import pl.kielce.tu.pharmacy.web.messages.Messages;
import pl.kielce.tu.pharmacy.web.back.UserBean;;

@Named("defaultUserActionBean")
@RequestScoped
public class DefaultUserActionBean implements UserActionBean
{	
	@Inject
    private UserBean userBean;
	
	@EJB
	private UserManager userManager;
	
	private int transaction = 0;
	
	public DefaultUserActionBean() 
	{
    }

	@Override
	public UserBean getRegisterBean() {
		return userBean;
	}

	@Override
	public void setRegisterBean(UserBean registerBean) {
		this.userBean = registerBean;
	}

	@Override
	public void registerUser() 
	{
		if(userManager.existEmail(userBean.getUser().getEmail()) == null)
		{
			userBean.getUser().setState(1);
			
			userManager.createUser(userBean.getUser());
			Messages.infoMessage("Register: ", "You have successfully registered.");
			userBean.getUser().setId(0);
			userBean.getUser().setName("");
			userBean.getUser().setSurname("");
			userBean.getUser().setPesel("");
			userBean.getUser().setEmail("");
			userBean.getUser().setPassword("");
		}
		else
			Messages.errorMessage("Register: ", "Email exists");
	}
	
	@Override
	public boolean checkUserSession()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		if(session.getAttribute("user") != null)
		{
			return true;
		}
		else	
			return false;
	}
	
	@Override
	public String checkUserSessionAttribute()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		if(session.getAttribute("user") != null)
		{
			return "logged.xhtml?faces-redirect=true";
		}
		else	
			return "login.xhtml?faces-redirect=true";
	}
	
	@Override
	public String checkUserEmail()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

		return session.getAttribute("user").toString();
	}
	
	@Override
	public String checkUserName()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

		return session.getAttribute("name").toString();
	}
	
	@Override
	public String checkUserSurname()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

		return session.getAttribute("surname").toString();
	}
	
	@Override
	public String login(String email, String password)
	{
		if(email.isEmpty() || password.isEmpty())
		{
			Messages.errorMessage("Fields: ", "Enter data.");
			return "";
		}
		else
		{
			userBean.setUser(userManager.loginUser(email, password));
			
			if(userBean.getUser() == null)
			{
				Messages.errorMessage("Warning!", "Wrong email or password");
				return "";
			}
			else
			{	
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				Cookie cookieLogin= new Cookie("email", email);
				session.setAttribute("uid", userBean.getUser().getId());
				session.setAttribute("user", email);
				session.setAttribute("name", userBean.getUser().getName());
				session.setAttribute("surname", userBean.getUser().getSurname());
				session.setMaxInactiveInterval(60*60);
				cookieLogin.setMaxAge(60*60);
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
				response.addCookie(cookieLogin);
					
				if(userBean.getUser().getState() == 2)
					return "logged.xhtml?faces-redirect=true";
				else if(userBean.getUser().getState() == 1)
				{
					setTransaction(1);
					return "";
				}
				else
				{
					return "";
				}
			}
		}
	}
	
	@Override
	public String logout()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		session.setAttribute("user", null);
		
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		
		Cookie[] cookies = request.getCookies();
		Cookie cookieLogin = null;
		for(int i=0; i<cookies.length; i++)
		{
			if(cookies[i].getName().equals("email"))
				cookieLogin = cookies[i];	
		}
		
		cookieLogin.setMaxAge(0);
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.addCookie(cookieLogin);
		
		userBean.getUser().setId(0);
		userBean.getUser().setName("");
		userBean.getUser().setSurname("");
		userBean.getUser().setPesel("");
		userBean.getUser().setEmail("");
		userBean.getUser().setPassword("");
		
		return "index.xhtml?faces-redirect=true";
	}
	
	@Override
	public void redirectIfNoSession()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		try 
		{
			response.sendRedirect("index.xhtml");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}

	@Override
	public int getTransaction() {
		return transaction;
	}

	@Override
	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}
}
