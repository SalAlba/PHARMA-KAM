package pl.kielce.tu.pharmacy.web.payment;

public class PayPalHandler extends PaymentHandler 
{
	public void handleRequest(String request) 
	{
		if (request.equalsIgnoreCase("PayPal")) 
		{
			System.out.println("PayPalHandler handles " + request);
		} 
		else 
		{
			System.out.println("PayPalHandler doesn't handle " + request);
			if (successor != null) 
			{
				successor.handleRequest(request);
			}
		}
	}
}

