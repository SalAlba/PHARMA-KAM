package pl.kielce.tu.pharmacy.core.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQueries
({
	@NamedQuery(name="DefaultUser.findAll", query="SELECT d FROM DefaultUser d"),
	@NamedQuery(name="DefaultUser.findByEmailAndPassword", query="SELECT d FROM DefaultUser d Where d.email= :email And d.password= :password"),
	@NamedQuery(name="DefaultUser.findByEmail", query="SELECT d FROM DefaultUser d Where d.email= :email")
})
public class DefaultUser implements Serializable, User {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String name;

	private String password;

	private String pesel;

	private int state;

	private String surname;

	//bi-directional many-to-one association to DefaultPrescription
	@OneToMany(mappedBy="user", targetEntity = DefaultPrescription.class)
	private List<Prescription> prescriptions;

	//bi-directional many-to-one association to DefaultTransaction
	@OneToMany(mappedBy="user", targetEntity = DefaultTransaction.class)
	private List<Transaction> transactions;

	public DefaultUser() {
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPesel() {
		return this.pesel;
	}

	@Override
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	@Override
	public int getState() {
		return this.state;
	}

	@Override
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String getSurname() {
		return this.surname;
	}

	@Override
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public List<Prescription> getPrescriptions() {
		return this.prescriptions;
	}

	@Override
	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	@Override
	public Prescription addPrescription(Prescription prescription) {
		getPrescriptions().add(prescription);
		prescription.setUser(this);

		return prescription;
	}

	@Override
	public Prescription removePrescription(Prescription prescription) {
		getPrescriptions().remove(prescription);
		prescription.setUser(null);

		return prescription;
	}

	@Override
	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	@Override
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setUser(this);

		return transaction;
	}

	@Override
	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setUser(null);

		return transaction;
	}

}