package pl.kielce.tu.pharmacy.core.model;

import java.util.List;

public interface User {

	int getId();

	void setId(int id);

	String getEmail();

	void setEmail(String email);

	String getName();

	void setName(String name);

	String getPassword();

	void setPassword(String password);

	String getPesel();

	void setPesel(String pesel);

	int getState();

	void setState(int state);

	String getSurname();

	void setSurname(String surname);

	List<Prescription> getPrescriptions();

	void setPrescriptions(List<Prescription> prescriptions);

	Prescription addPrescription(Prescription prescription);

	Prescription removePrescription(Prescription prescription);

	List<Transaction> getTransactions();

	void setTransactions(List<Transaction> transactions);

	Transaction addTransaction(Transaction transaction);

	Transaction removeTransaction(Transaction transaction);

}