package pl.kielce.tu.pharmacy.web.payment;

public class CreditCardHandler extends PaymentHandler 
{
	@Override
	public void handleRequest(String request)
	{
		if ((request.equalsIgnoreCase("Credit card"))) 
		{
			System.out.println("CardHandler handle " + request);
		} 
		else
		{
			System.out.println("CardHandler doesn't handle " + request);
			
			if (successor != null) 
			{
				successor.handleRequest(request);
			}
		}
	}
}
