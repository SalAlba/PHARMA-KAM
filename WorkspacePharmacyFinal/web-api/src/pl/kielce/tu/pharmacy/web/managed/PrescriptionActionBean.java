package pl.kielce.tu.pharmacy.web.managed;

import java.util.List;

import pl.kielce.tu.pharmacy.core.model.Product;
import pl.kielce.tu.pharmacy.web.back.PrescriptionBean;

public interface PrescriptionActionBean {

	PrescriptionBean getvBean();

	void setPrescriptionBean(PrescriptionBean prescriptionBean);

	void addPrescription();

	void completePrescription(String prescriptionCode);

	List<Product> selectProductOnPrescirption();

	void setProductPrescription(int productPrescription);

	int getProductPrescription();

	void setCheckProductPrescription();

	void init();

}