package sms.entities.customer;

import java.sql.Date;
import java.util.List;

import sms.entities.location.Address;
import sms.entities.order.Orders;
import sms.entities.subscription.Subscription;
import sms.enums.AccountStatus;

public interface ICustomer {

	public Integer getId();

	public String getName();

	public String getEmail();

	public String getPhoneNumber();

	public Date getCreationDate();

	public AccountStatus getStatus();

	public Address getAddress();

	public List<Address> getDeliveryAddresses();

	public List<Subscription> getSubscriptions();

	public List<Orders> getOrders();

}
