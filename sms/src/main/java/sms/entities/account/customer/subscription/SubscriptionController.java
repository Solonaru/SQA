package sms.entities.account.customer.subscription;

import java.sql.Date;
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

import sms.enums.Status;
import sms.utils.DisplayData;

@RestController
@RequestMapping("/subscription")
@CrossOrigin(origins = "http://localhost:4200")
public class SubscriptionController {

	@Autowired
	private ISubscriptionService subscriptionService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{subscriptionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Subscription> findSubscriptionById(@PathVariable("subscriptionId") int subscriptionId) {
		dataDisplay.printCrudInfo(subscriptionId);
		return subscriptionService.findSubscriptionById(subscriptionId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Subscription> getSubscriptions() {
		dataDisplay.printCrudInfo();
		return subscriptionService.findAllSubscriptions();
	}
	
	@RequestMapping(value = "/allActive", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Subscription> getActiveSubscriptions() {
		dataDisplay.printCrudInfo();
		return subscriptionService.findAllActiveSubscriptions();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertSubscription(@RequestBody Subscription subscription) {
		dataDisplay.printCrudInfo();
		subscription.setStatus(Status.ACTIVE);
		subscription.setUpdateDate(new Date(System.currentTimeMillis()));
		subscriptionService.insertSubscription(subscription);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSubscription(@RequestBody Subscription subscription) {
		dataDisplay.printCrudInfo();
		subscription.setUpdateDate(new Date(System.currentTimeMillis()));
		subscriptionService.updateSubscription(subscription);
	}

	@RequestMapping(value = "/delete/{subscriptionId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteSubscription(@PathVariable("subscriptionId") int subscriptionId) {
		dataDisplay.printCrudInfo(subscriptionId);
		Subscription subscription = subscriptionService.findSubscriptionById(subscriptionId).get();
		subscription.removeSubscription();
		subscription.setUpdateDate(new Date(System.currentTimeMillis()));
		subscriptionService.updateSubscription(subscription);
	}
}
