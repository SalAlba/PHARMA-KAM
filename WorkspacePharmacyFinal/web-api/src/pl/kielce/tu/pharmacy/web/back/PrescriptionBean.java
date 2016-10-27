package pl.kielce.tu.pharmacy.web.back;

import java.util.List;

import pl.kielce.tu.pharmacy.core.model.Prescription;

public interface PrescriptionBean {

	Prescription getPrescription();

	void setPrescription(Prescription prescription);

	void setPrescriptionCode(String prescriptionCode);

	String getPrescriptionCode();

	List<Prescription> getPrescriptions();

}