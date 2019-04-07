package sms.entities.account.customer.feedback;

import java.sql.Date;
import java.util.Collections;
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
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {
	@Autowired
	private IFeedbackService feedbackService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{feedbackId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Feedback> findFeedbackById(@PathVariable("feedbackId") int feedbackId) {
		dataDisplay.printCrudInfo(feedbackId);
		return feedbackService.findFeedbackById(feedbackId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Feedback> getFeedbacks() {
		dataDisplay.printCrudInfo();
		List<Feedback> feedbacks = feedbackService.findAllActiveFeedbacks();
		Collections.sort(feedbacks);
		return feedbacks;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertFeedback(@RequestBody Feedback feedback) {
		dataDisplay.printCrudInfo();
		feedback.setStatus(Status.ACTIVE);
		feedback.setUpdateDate(new Date(System.currentTimeMillis()));
		feedbackService.insertFeedback(feedback);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateFeedback(@RequestBody Feedback feedback) {
		dataDisplay.printCrudInfo();
		feedback.setUpdateDate(new Date(System.currentTimeMillis()));
		feedbackService.updateFeedback(feedback);
	}

	@RequestMapping(value = "/delete/{feedbackId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCategory(@PathVariable("feedbackId") int feedbackId) {
		dataDisplay.printCrudInfo(feedbackId);
		Feedback feedback = feedbackService.findFeedbackById(feedbackId).get();
		feedback.removeFeedback();
		feedback.setUpdateDate(new Date(System.currentTimeMillis()));
		feedbackService.updateFeedback(feedback);
	}

}
