package pl.kielce.tu.pharmacy.core.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prescription database table.
 * 
 */
@Entity
@Table(name="prescription")
@NamedQueries
({
	@NamedQuery(name="DefaultPrescription.findAll", query="SELECT d FROM DefaultPrescription d"),
	@NamedQuery(name="DefaultPrescription.findByPrescriptionCode", query="SELECT d FROM DefaultPrescription d Where d.prescriptionCode= :prescriptionCode"),
	@NamedQuery(name="DefaultPrescription.findProductByPrescriptionCode", query="SELECT d.productLabel FROM DefaultPrescription d Where d.prescriptionCode= :prescriptionCode")
})
public class DefaultPrescription implements Serializable, Prescription {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	private String executionDate;

	private int prescriptionCode;

	private int productLabel;

	private int state;

	private String termOfValidity;

	//bi-directional many-to-one association to DefaultUser
	@ManyToOne(targetEntity=DefaultUser.class)
	@JoinColumn(name="idUser")
	private User user;

	public DefaultPrescription() {
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
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getExecutionDate() {
		return this.executionDate;
	}

	@Override
	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	@Override
	public int getPrescriptionCode() {
		return this.prescriptionCode;
	}

	@Override
	public void setPrescriptionCode(int prescriptionCode) {
		this.prescriptionCode = prescriptionCode;
	}

	@Override
	public int getProductLabel() {
		return this.productLabel;
	}

	@Override
	public void setProductLabel(int productLabel) {
		this.productLabel = productLabel;
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
	public String getTermOfValidity() {
		return this.termOfValidity;
	}

	@Override
	public void setTermOfValidity(String termOfValidity) {
		this.termOfValidity = termOfValidity;
	}

	@Override
	public User getUser() {
		return this.user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

}