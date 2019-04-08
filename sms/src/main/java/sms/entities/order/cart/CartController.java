package sms.entities.order.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sms.entities.account.customer.Customer;
import sms.entities.account.customer.ICustomerService;
import sms.utils.DisplayData;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

	@Autowired
	private ICartService cartService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private DisplayData dataDisplay;
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Cart> findCartById(@PathVariable("cartId") int cartId) {
		dataDisplay.printCrudInfo(cartId); 
		return cartService.findCartById(cartId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cart> getCarts() {
		dataDisplay.printCrudInfo(); 
		return cartService.findAllCarts();
	}
	
	@RequestMapping(value = "/all/customer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cart> findCartsByCustomer(@RequestBody Customer customer) {
		dataDisplay.printCrudInfo();
		return cartService.findAllCustomerCarts(customer);
	}
	
	@RequestMapping(value = "/all/customer/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cart> findCartsByCustomerId(@PathVariable("customerId") int customerId) {
		Customer customer = customerService.findCustomerById(customerId).get();
		return cartService.findAllCustomerCarts(customer);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCart(@RequestBody Cart cart) {
		dataDisplay.printCrudInfo(); 
		cartService.insertCart(cart);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCart(@RequestBody Cart cart) {
		dataDisplay.printCrudInfo(); 
		cartService.updateCart(cart);
	}
	
	@RequestMapping(value = "/delete/{cartId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCart(@PathVariable("cartId") int cartId) {		
		dataDisplay.printCrudInfo(cartId); 
		cartService.deleteCartById(cartId);
	}
}
