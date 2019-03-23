package sms.entities.account.customer.rating;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import sms.entities.account.customer.Customer;
import sms.entities.item.Item;

@Entity
@NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_generator")
	@SequenceGenerator(name = "rating_generator", sequenceName = "rating_sequence", initialValue = 500001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Double stars;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private Item item;

	// -----Constructors-----
	public Rating() {
		super();
	}

	public Rating(Double stars) {
		super();
		this.stars = stars;
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getStars() {
		return stars;
	}

	public void setStars(Double stars) {
		this.stars = stars;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
