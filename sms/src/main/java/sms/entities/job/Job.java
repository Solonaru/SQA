package sms.entities.job;

import java.io.Serializable;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import sms.entities.account.employee.Employee;
import sms.enums.Status;

@Entity
@NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
public class Job implements Serializable, Comparable<Job> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_generator")
	@SequenceGenerator(name = "job_generator", sequenceName = "job_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String name;
	private String jobStatus;
	@Column(columnDefinition = "TEXT")
	private String description;
	private String imageUrl;
	@ElementCollection
	private List<String> requirements;
	@ElementCollection
	private List<String> responsabilities;
	@ManyToOne
	private Employee employee;
	private Date updateDate;
	private Status status;
	private String location;

	// ----- Constructors -----
	public Job() {
		super();
	}

	public Job(String name, String jobStatus, String description, String imageUrl, String location, List<String> requirements,
			List<String> responsabilities) {
		super();
		this.name = name;
		this.jobStatus = jobStatus;
		this.description = description;
		this.imageUrl = imageUrl;
		this.requirements = requirements;
		this.responsabilities = responsabilities;
		this.updateDate = new Date(System.currentTimeMillis());
		this.status = Status.ACTIVE;
	}

	// ----- Getters and Setters -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}

	public List<String> getResponsabilities() {
		return responsabilities;
	}

	public void setResponsabilities(List<String> responsibilities) {
		this.responsabilities = responsibilities;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	// ----- Methods -----
	public int compareTo(Job job) {
		return -job.getName().compareToIgnoreCase(this.getName());
	}

	public void removeJob() {
		this.setStatus(Status.INACTIVE);
	}
}
