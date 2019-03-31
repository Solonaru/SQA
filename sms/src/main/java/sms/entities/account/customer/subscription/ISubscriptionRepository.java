package sms.entities.account.customer.subscription;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sms.enums.Status;

public interface ISubscriptionRepository extends CrudRepository<Subscription, Integer> {

	public List<Subscription> findAllByStatus(Status status);

}
