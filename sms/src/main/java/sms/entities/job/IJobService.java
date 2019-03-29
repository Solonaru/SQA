package sms.entities.job;

import java.util.List;
import java.util.Optional;

public interface IJobService {

	public Optional<Job> findJobById(int jobId);

	public List<Job> findAllJobs();

	public List<Job> findAllActiveJobs();

	public void insertJob(Job job);

	public void updateJob(Job job);

	public void deleteJobById(int jobId);

}
