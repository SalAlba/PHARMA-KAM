package pl.kielce.tu.pharmacy.web.payment;

public abstract class PaymentHandler 
{
	protected PaymentHandler successor;

	public void setSuccessor(PaymentHandler successor) 
	{
		this.successor = successor;
	}
	
	public PaymentHandler getHandler()
	{
		return this.successor;
	}

	public abstract void handleRequest(String request);
}
