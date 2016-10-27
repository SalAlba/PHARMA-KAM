package pl.kielce.tu.pharmacy.core.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name="transaction")
@NamedQuery(name="DefaultTransaction.findAll", query="SELECT d FROM DefaultTransaction d")
public class DefaultTransaction implements Serializable, Transaction {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int amount;

	private int state;

	private String transactionDate;

	//bi-directional many-to-one association to DefaultProduct
	@ManyToOne(targetEntity = DefaultProduct.class)
	@JoinColumn(name="idProduct")
	private Product product;

	//bi-directional many-to-one association to DefaultUser
	@ManyToOne(targetEntity = DefaultUser.class)
	@JoinColumn(name="idUser")
	private User user;

	public DefaultTransaction() {
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
	public int getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
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
	public String getTransactionDate() {
		return this.transactionDate;
	}

	@Override
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public Product getProduct() {
		return this.product;
	}

	@Override
	public void setProduct(Product product) {
		this.product = product;
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