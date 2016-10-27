package pl.kielce.tu.pharmacy.web.managed;

import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pl.kielce.tu.pharmacy.core.manager.PrescriptionManager;
import pl.kielce.tu.pharmacy.core.manager.ProductManager;
import pl.kielce.tu.pharmacy.core.model.Prescription;
import pl.kielce.tu.pharmacy.core.model.Product;
import pl.kielce.tu.pharmacy.web.messages.Messages;
import pl.kielce.tu.pharmacy.web.back.PrescriptionBean;

@Named("defaultPrescriptionActionBean")
@RequestScoped
public class DefaultPrescriptionActionBean implements PrescriptionActionBean
{	
	@Inject
    private PrescriptionBean prescriptionBean;
	
	@EJB
	private PrescriptionManager prescriptionManager;
	
	@EJB
	private ProductManager productManager;
	
	private List<Product> productOnPrescription;
	
	private int productPrescription = -1;
	
	public DefaultPrescriptionActionBean() 
	{
    }

	@Override
	public PrescriptionBean getvBean() {
		return prescriptionBean;
	}

	@Override
	public void setPrescriptionBean(PrescriptionBean prescriptionBean) {
		this.prescriptionBean = prescriptionBean;
	}
	
	@Override
	public int getProductPrescription() {
		return productPrescription;
	}

	@Override
	public void setProductPrescription(int productPrescription) {
		this.productPrescription = productPrescription;
	}
	
	@Override
	public void setCheckProductPrescription()
	{
		this.productPrescription = 1;
	}
	
	@Override
	public void addPrescription() 
	{
		prescriptionManager.createPrescription(prescriptionBean.getPrescription());
	}
	
	@Override
	public void completePrescription(String prescriptionCode)
	{	
		if(prescriptionCode.isEmpty())
		{
			Messages.infoMessage("Prescription: ", "Enter prescription code.");
		}
		else
		{
			List<Prescription> prescriptions = prescriptionManager.existPrescription(Integer.parseInt(prescriptionCode));
			
			if(prescriptions != null)
			{	
				for(Prescription p : prescriptions)
				{
					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					Date dateobj = new Date();
						
					String executionDate = df.format(dateobj);

					p.setExecutionDate(executionDate);
					p.setState(1);
					
					prescriptionManager.updatePrescription(p);
					Messages.infoMessage("Prescription: ", "Updated execution date and state of prescription.");
				}
			}
			else
			{
				Messages.infoMessage("Prescription: ", "This prescription code does not exist.");
			}
		}
	}
	
	@Override
	public List<Product> selectProductOnPrescirption()
	{	
		List<Integer> productLabels = prescriptionManager.selectProductLabelByPrescriptionCode(Integer.parseInt(prescriptionBean.getPrescriptionCode()));
		
		if(!productLabels.isEmpty())
		{
			for(Integer pL : productLabels)
			{
				Product p = productManager.selectProductByProductLabel(pL);
				productOnPrescription.add(p);
			}
			
			return productOnPrescription;
		}
		else
			Messages.infoMessage("Prescription: ", "This prescription code does not exist.");
		
		return productOnPrescription;
	}
	
	@Override
	@PostConstruct
	public void init() 
	{
		this.productOnPrescription = new ArrayList<Product>();
	}
}
