package pl.kielce.tu.pharmacy.core.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
@NamedQueries
({
	@NamedQuery(name="DefaultProduct.findAll", query="SELECT d FROM DefaultProduct d"),
	@NamedQuery(name="DefaultProduct.findByType", query="SELECT d FROM DefaultProduct d Where d.type= :type"),
	@NamedQuery(name="DefaultProduct.findByProductLabel", query="SELECT d FROM DefaultProduct d Where d.productLabel= :productLabel")
})
public class DefaultProduct implements Serializable, Product {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int available;

	private String name;

	private double price;

	private int productLabel;

	private int refunded;

	private String expirationDate;

	private String type;
	
	private String link;

	//bi-directional many-to-one association to DefaultTransaction
	@OneToMany(mappedBy="product", targetEntity = DefaultTransaction.class)
	private List<Transaction> transactions;

	public DefaultProduct() {
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
	public int getAvailable() {
		return this.available;
	}

	@Override
	public void setAvailable(int available) {
		this.available = available;
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
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
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
	public int getRefunded() {
		return this.refunded;
	}

	@Override
	public void setRefunded(int refunded) {
		this.refunded = refunded;
	}
	
	@Override
	public String getExpirationDate() {
		return this.expirationDate;
	}

	@Override
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String getLink() {
		return link;
	}

	@Override
	public void setLink(String link) {
		this.link = link;
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
		transaction.setProduct(this);

		return transaction;
	}

	@Override
	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setProduct(null);

		return transaction;
	}
	
	@Override
	public String toString()
	{
		return "ID: " + this.id + " " + this.name;
	}

	Product getProductComponent() {
		return null;
	}
}