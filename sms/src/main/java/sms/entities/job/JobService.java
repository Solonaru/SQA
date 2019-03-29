package sms.entities.job;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.enums.Status;

@Service
public class JobService implements IJobService {

	@Autowired
	private IJobRepository jobRepository;

	public Optional<Job> findJobById(int jobId) {
		return jobRepository.findById(jobId);
	}

	public List<Job> findAllJobs() {
		return (List<Job>) jobRepository.findAll();
	}

	public List<Job> findAllActiveJobs() {
		return (List<Job>) jobRepository.findAllByStatus(Status.ACTIVE);
	}

	public void insertJob(Job job) {
		jobRepository.save(job);
	}

	public void updateJob(Job job) {
		jobRepository.save(job);
	}

	public void deleteJobById(int jobId) {
		jobRepository.deleteById(jobId);
	}

}
