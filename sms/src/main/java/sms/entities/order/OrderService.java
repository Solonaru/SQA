package sms.entities.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.entities.account.customer.Customer;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	public Optional<Orders> findOrderById(int orderId) {
		return orderRepository.findById(orderId);
	}

	public List<Orders> findAllOrders() {
		return (List<Orders>) orderRepository.findAll();
	}

	public List<Orders> findAllCustomerOrders(Customer customer) {
		return (List<Orders>) orderRepository.findAllByCustomer(customer);
	}

	public void insertOrder(Orders order) {
		orderRepository.save(order);
	}

	public void updateOrder(Orders order) {
		orderRepository.save(order);
	}

	public void deleteOrderById(int orderId) {
		orderRepository.deleteById(orderId);
	}
}
