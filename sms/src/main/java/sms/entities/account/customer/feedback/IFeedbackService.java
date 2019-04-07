package sms.entities.account.customer.feedback;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {

	public Optional<Feedback> findFeedbackById(int feedbackId);

	public List<Feedback> findAllFeedbacks();

	public List<Feedback> findAllActiveFeedbacks();

	public void insertFeedback(Feedback feedback);

	public void updateFeedback(Feedback feedback);

	public void deleteFeedbackById(int feedbackId);

}
