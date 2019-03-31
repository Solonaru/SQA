package sms.entities.account.customer.subscription;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.enums.Status;

@Service
public class SubscriptionService implements ISubscriptionService {

	@Autowired
	private ISubscriptionRepository subscriptionRepository;

	public Optional<Subscription> findSubscriptionById(int subscriptionId) {
		return subscriptionRepository.findById(subscriptionId);
	}

	public List<Subscription> findAllSubscriptions() {
		return (List<Subscription>) subscriptionRepository.findAll();
	}

	public List<Subscription> findAllActiveSubscriptions() {
		return (List<Subscription>) subscriptionRepository.findAllByStatus(Status.ACTIVE);
	}

	public void insertSubscription(Subscription subscription) {
		subscriptionRepository.save(subscription);
	}

	public void updateSubscription(Subscription subscription) {
		subscriptionRepository.save(subscription);
	}

	public void deleteSubscriptionById(int subscriptionId) {
		subscriptionRepository.deleteById(subscriptionId);
	}
}
