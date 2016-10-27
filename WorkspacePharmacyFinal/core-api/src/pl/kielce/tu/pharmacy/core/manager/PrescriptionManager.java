package pl.kielce.tu.pharmacy.core.manager;

import java.util.List;

import javax.ejb.Remote;

import pl.kielce.tu.pharmacy.core.model.Prescription;

@Remote
public interface PrescriptionManager {

	List<Prescription> selectPrescriptions();

	Prescription createPrescription(Prescription prescription);

	Prescription findPrescription(int id);

	void removePrescription(Prescription prescription);

	void updatePrescription(Prescription prescription);

	List<Prescription> existPrescription(int prescriptionCode);

	List<Integer> selectProductLabelByPrescriptionCode(int prescriptionCode);

}
