package pl.kielce.tu.pharmacy.web.payment;

public class MoneyTransferHandler extends PaymentHandler
{
	@Override
	public void handleRequest(String request)
	{
		if ((request.equalsIgnoreCase("Money transfer"))) 
		{
			System.out.println("MoneyTransferHandler OK");
		} 
		else
		{
			System.out.println("MoneyTransferHandler doesn't handle " + request);
			if (successor != null) 
			{
				successor.handleRequest(request);
			}
		}	
	}

}
