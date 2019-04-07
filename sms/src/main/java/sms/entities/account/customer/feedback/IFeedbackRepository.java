package sms.entities.account.customer.feedback;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sms.enums.Status;

public interface IFeedbackRepository extends CrudRepository<Feedback, Integer> {

	public List<Feedback> findAllByStatus(Status status);

}
