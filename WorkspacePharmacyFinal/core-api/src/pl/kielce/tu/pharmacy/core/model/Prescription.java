package pl.kielce.tu.pharmacy.core.model;

public interface Prescription {

	int getId();

	void setId(int id);

	String getDescription();

	void setDescription(String description);

	String getExecutionDate();

	void setExecutionDate(String executionDate);

	int getPrescriptionCode();

	void setPrescriptionCode(int prescriptionCode);

	int getProductLabel();

	void setProductLabel(int productLabel);

	int getState();

	void setState(int state);

	String getTermOfValidity();

	void setTermOfValidity(String termOfValidity);

	User getUser();

	void setUser(User user);

}