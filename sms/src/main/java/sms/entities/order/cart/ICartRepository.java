package sms.entities.order.cart;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sms.entities.account.customer.Customer;
import sms.entities.order.Orders;

public interface ICartRepository extends CrudRepository<Cart, Integer>{

	public List<Orders> findAllByCustomer(Customer customer);
	
}
