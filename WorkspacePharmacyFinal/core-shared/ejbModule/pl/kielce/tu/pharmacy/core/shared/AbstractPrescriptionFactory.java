package pl.kielce.tu.pharmacy.core.shared;

import pl.kielce.tu.pharmacy.core.model.DefaultPrescription;
import pl.kielce.tu.pharmacy.core.model.Prescription;

public class AbstractPrescriptionFactory 
{
	private static AbstractPrescriptionFactory instance;
	
	private AbstractPrescriptionFactory(){
	};
	
	public static AbstractPrescriptionFactory getInstance()
	{
		return (instance == null) ? new AbstractPrescriptionFactory() : instance;
	}
	
	public Prescription getPrescription()
	{
		return new DefaultPrescription();
	}
}
