package sms.entities.order.cart;

import java.util.List;
import java.util.Optional;

import sms.entities.account.customer.Customer;

public interface ICartService {

	public Optional<Cart> findCartById(int cartId);
	
	public List<Cart> findAllCarts();
	
	public List<Cart> findAllCustomerCarts(Customer customer);

	public void insertCart(Cart cart);

	public void updateCart(Cart cart);

	public void deleteCartById(int cart);
}
