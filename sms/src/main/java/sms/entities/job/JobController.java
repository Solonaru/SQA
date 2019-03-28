package sms.entities.job;

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

import sms.entities.job.IJobService;
import sms.entities.job.Job;
import sms.utils.DisplayData;

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {
	@Autowired
	private IJobService jobService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{jobId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Job> findJobById(@PathVariable("jobId") int jobId) {
		dataDisplay.printCrudInfo(jobId);
		return jobService.findJobById(jobId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Job> getJobs() {
		dataDisplay.printCrudInfo();
		List<Job> jobs = jobService.findAllActiveJobs();
		Collections.sort(jobs);
		return jobs;
	}


	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertJob(@RequestBody Job job) {
		dataDisplay.printCrudInfo();
		jobService.insertJob(job);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateJob(@RequestBody Job job) {
		dataDisplay.printCrudInfo();
		jobService.updateJob(job);
	}

	@RequestMapping(value = "/delete/{jobId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteJob(@PathVariable("jobId") int jobId) {
		dataDisplay.printCrudInfo(jobId);
		jobService.deleteJobById(jobId);
	}

}
