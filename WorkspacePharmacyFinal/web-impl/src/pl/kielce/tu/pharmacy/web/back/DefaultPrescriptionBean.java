package pl.kielce.tu.pharmacy.web.back;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import pl.kielce.tu.pharmacy.core.manager.PrescriptionManager;
import pl.kielce.tu.pharmacy.core.model.Prescription;
import pl.kielce.tu.pharmacy.core.shared.AbstractPrescriptionFactory;

@Named("defaultPrescriptionBean")
@RequestScoped
public class DefaultPrescriptionBean implements PrescriptionBean 
{
	@EJB
    private PrescriptionManager prescriptionManager;
	
	AbstractPrescriptionFactory factory = AbstractPrescriptionFactory.getInstance();
	
	private Prescription prescription = factory.getPrescription();

	private List<Prescription> prescriptions;
	
	private String prescriptionCode;
	
	public DefaultPrescriptionBean()
	{
	}
	
	@Override
	public Prescription getPrescription() {
		return prescription;
	}
	
	@Override
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	@Override
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}
	
	
	@Override
	public String getPrescriptionCode() {
		return prescriptionCode;
	}

	@Override
	public void setPrescriptionCode(String prescriptionCode) {
		this.prescriptionCode = prescriptionCode;
	}

	@PostConstruct
	public void init() 
	{
		this.prescriptions = prescriptionManager.selectPrescriptions();
	}
}
