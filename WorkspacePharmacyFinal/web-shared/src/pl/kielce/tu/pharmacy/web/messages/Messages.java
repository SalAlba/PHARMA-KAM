package pl.kielce.tu.pharmacy.web.messages;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public interface Messages 
{
	public static void infoMessage(String summary, String detail) 
	{
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  detail));
    }
	
	public static void errorMessage(String summary, String detail)
	{
		FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  detail));
	}
}
