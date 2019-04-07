package sms.entities.account.customer.feedback;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.enums.Status;

@Service
public class FeedbackService implements IFeedbackService {

	@Autowired
	private IFeedbackRepository feedbackRepository;

	public Optional<Feedback> findFeedbackById(int feedbackId) {
		return feedbackRepository.findById(feedbackId);
	}

	public List<Feedback> findAllFeedbacks() {
		return (List<Feedback>) feedbackRepository.findAll();
	}

	public List<Feedback> findAllActiveFeedbacks() {
		return (List<Feedback>) feedbackRepository.findAllByStatus(Status.ACTIVE);
	}

	public void insertFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
	}

	public void updateFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
	}

	public void deleteFeedbackById(int feedbackId) {
		feedbackRepository.deleteById(feedbackId);
	}

}
