package sms.entities.job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sms.entities.account.employee.Employee;
import sms.entities.job.requirements.Requirements;
import sms.entities.job.responsibilities.Responsibilities;
import sms.enums.Status;

@Entity
@NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
@DiscriminatorValue("Job")
@JsonDeserialize(as = Job.class)
public class Job implements Serializable,  Comparable<Job> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_generator")
	@SequenceGenerator(name = "job_generator", sequenceName = "job_sequence", initialValue = 661, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String name;
	private String imageUrl;
	@Column(columnDefinition = "TEXT")
	private String description;
	@ManyToOne
	private Employee employee;
	@OneToMany(mappedBy = "job")
	@JsonIgnoreProperties(value = "job")
	private List<Requirements> requirements = new ArrayList<Requirements>();
	@OneToMany(mappedBy = "job")
	@JsonIgnoreProperties(value = "job")
	private List<Responsibilities> responsibilities = new ArrayList<Responsibilities>();
	private Status status;

	//// Constructors

	public Job() {
		super();
	}

	public Job(String name, String description, List<String> requirements, List<String> responsibilities) {
		super();
		this.name=name;
		this.description=description;
		this.requirements=new ArrayList<Requirements>();
		this.responsibilities=new ArrayList<Responsibilities>();
		this.status = Status.ACTIVE;
	}


	///// Getters and setters

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Requirements> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirements> requirements) {
		this.requirements = requirements;
	}

	public List<Responsibilities> getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(List<Responsibilities> responsibilities) {
		this.responsibilities = responsibilities;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	// ----- Methods -----
	public int compareTo(Job job) {
		return -job.getName().compareToIgnoreCase(this.getName());
	}

	public void removeJob() {
		this.setStatus(Status.INACTIVE);

		if (null != this.getRequirements()) {
			for (Requirements requirements : this.getRequirements()) {
				requirements.setJob(null);
			}
		}

		if (null != this.getResponsibilities()) {
			for (Responsibilities responsibilities : this.getResponsibilities()) {
				responsibilities.setJob(null);
			}
		}
		this.responsibilities=null;
		this.requirements = null;
	}
}
