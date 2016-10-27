package pl.kielce.tu.pharmacy.core.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.kielce.tu.pharmacy.core.dao.PrescriptionDAO;
import pl.kielce.tu.pharmacy.core.model.Prescription;

/**
 * Session Bean implementation class DefaultPrescriptionManager
 */
@Stateless
public class DefaultPrescriptionManager implements PrescriptionManager 
{
	@EJB
	private PrescriptionDAO prescriptionDAO;
	
	@Override
	public void updatePrescription(Prescription prescription) {
		prescriptionDAO.updatePrescription(prescription);
	}

	@Override
	public void removePrescription(Prescription prescription) {
		prescriptionDAO.removePrescription(prescription);
	}

	@Override
	public Prescription findPrescription(int id) {
		return prescriptionDAO.findPrescription(id);
	}

	@Override
	public Prescription createPrescription(Prescription prescription) {
		return prescriptionDAO.createPrescription(prescription);
	}
	
	@Override
	public List<Prescription> selectPrescriptions()
	{
		return prescriptionDAO.selectPrescriptions();
	}
	
	@Override
	public List<Prescription> existPrescription(int prescriptionCode)
	{
		return prescriptionDAO.selectPrescriptionByPrescriptionCode(prescriptionCode);
	}
	
	@Override
	public List<Integer> selectProductLabelByPrescriptionCode(int prescriptionCode)
	{
		return prescriptionDAO.selectProductLabelByPrescriptionCode(prescriptionCode);
	}
}
