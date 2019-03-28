package sms.entities.job;

import java.util.List;

import java.util.Optional;

import sms.entities.job.Job;

public interface IJobService {
	public Optional<Job> findJobById(int jobId);

	public List<Job> findAllJobs();
	
	public List<Job> findAllActiveJobs();

	public void insertJob(Job job);

	public void updateJob(Job job);

	public void deleteJobById(int jobId);
}
