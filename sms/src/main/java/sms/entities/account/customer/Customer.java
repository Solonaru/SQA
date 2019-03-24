package sms.entities.account.customer;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sms.entities.account.Account;
import sms.entities.account.customer.logic.ICustomer;
import sms.entities.account.customer.subscription.Subscription;
import sms.entities.address.Address;
import sms.entities.order.Orders;
import sms.enums.account.AccountStatus;

@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
@DiscriminatorValue("Customer")
@JsonDeserialize(as = Customer.class)
public class Customer extends Account implements Serializable, ICustomer {
	private static final long serialVersionUID = 1L;

	@ManyToMany
	@JoinTable(name = "delivery_addresses", joinColumns = { @JoinColumn(name = "customer_id") }, inverseJoinColumns = {
			@JoinColumn(name = "address_id") })
	private List<Address> addresses = new ArrayList<Address>();
	@ManyToMany
	@JoinTable(name = "customer_subscriptions", joinColumns = {
			@JoinColumn(name = "customer_id") }, inverseJoinColumns = { @JoinColumn(name = "subscription_id") })
	@JsonIgnoreProperties(value = "customers")
	private List<Subscription> subscriptions = new ArrayList<Subscription>();
	@OneToMany(mappedBy = "customer")
	private List<Orders> orders = new ArrayList<Orders>();

	// -----Constructors-----
	public Customer() {
		super.creationDate = new Date(System.currentTimeMillis());
		super.status = AccountStatus.ACTIVE;
	}

	public Customer(String username, String password, String name, String email, String phoneNumber) {
		super(username, password, name, email, phoneNumber);
		super.creationDate = new Date(System.currentTimeMillis());
		super.status = AccountStatus.ACTIVE;
	}

	// -----Getters and Setters-----
	public List<Address> getDeliveryAddresses() {
		return addresses;
	}

	public void setDeliveryAddresses(List<Address> deliveryAddresses) {
		this.addresses = deliveryAddresses;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	// -----Methods-----
	public String toString() {
		return username;
	}
}
