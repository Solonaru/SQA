package sms.entities.account.customer.subscription;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sms.entities.account.customer.Customer;
import sms.entities.account.employee.Employee;
import sms.enums.Status;

@Entity
@NamedQuery(name = "Subscription.findAll", query = "SELECT s FROM Subscription s")
public class Subscription implements Serializable, Comparable<Subscription> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_generator")
	@SequenceGenerator(name = "subscription_generator", sequenceName = "subscription_sequence", initialValue = 1101, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String type;
	private String description;
	@ManyToMany
	@JoinTable(name = "customer_subscriptions", joinColumns = {
			@JoinColumn(name = "subscription_id") }, inverseJoinColumns = { @JoinColumn(name = "customer_id") })
	@JsonIgnoreProperties(value = "subscriptions")
	private List<Customer> customers;
	@ManyToOne
	private Employee employee;
	private Date updateDate;
	private Status status;

	// -----Constructors-----
	public Subscription() {
		super();
	}

	public Subscription(String type, String description) {
		super();
		this.type = type;
		this.description = description;
		this.customers = new ArrayList<Customer>();
		this.updateDate = new Date(System.currentTimeMillis());
		this.status = Status.ACTIVE;
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	// -----Methods-----
	public String toString() {
		return type.toString();
	}

	public void removeSubscription() {
		this.setStatus(Status.INACTIVE);
	}

	public Integer getSubscriptionsCount() {
		return customers.size();
	}

	@Override
	public int compareTo(Subscription subscription) {
		return -subscription.getType().compareToIgnoreCase(this.getType());
	}
}
