package sms.entities.order.cart;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sms.entities.account.customer.Customer;

public interface ICartRepository extends CrudRepository<Cart, Integer>{

	public List<Cart> findAllByCustomer(Customer customer);
	
}
