package sms.entities.order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sms.entities.account.customer.Customer;

public interface IOrderRepository extends CrudRepository<Orders, Integer> {

	public List<Orders> findAllByCustomer(Customer customer);

}
