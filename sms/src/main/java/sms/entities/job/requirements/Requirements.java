package sms.entities.job.requirements;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import sms.entities.account.employee.Employee;
import sms.entities.job.Job;


@Entity
@NamedQuery(name = "Requirements.findAll", query = "SELECT r FROM Requirements r")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "REQUIREMENTS_TYPE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Requirements implements Serializable, Comparable<Requirements> {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requirements_generator")
	@SequenceGenerator(name = "requirements_generator", sequenceName = "requirements_sequence", initialValue = 152, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;
	protected String name;
	@ManyToOne
	protected Job job;
	@ManyToOne
	protected Employee employee;
	
	/////Getters&Setters
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
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/////Constructors
	public Requirements() {
		super();
	}
	public Requirements(Integer id, String name, Job job, Employee employee) {
		super();
		this.name = name;
		this.job = job;
		this.employee = employee;
	}
	
	/////Methods
	@Override
	public int compareTo(Requirements o) {
		return 0;
	}
	

	
	

	
	
	
	
	
}
