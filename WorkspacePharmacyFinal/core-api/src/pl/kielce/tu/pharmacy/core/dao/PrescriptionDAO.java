package pl.kielce.tu.pharmacy.core.dao;

import java.util.List;

import javax.ejb.Local;

import pl.kielce.tu.pharmacy.core.model.Prescription;

@Local
public interface PrescriptionDAO {

	List<Prescription> selectPrescriptions();

	void updatePrescription(Prescription prescription);

	void removePrescription(Prescription prescription);

	Prescription findPrescription(int id);

	Prescription createPrescription(Prescription prescription);

	List<Prescription> selectPrescriptionByPrescriptionCode(int prescriptionCode);

	List<Integer> selectProductLabelByPrescriptionCode(int prescriptionCode);

}
