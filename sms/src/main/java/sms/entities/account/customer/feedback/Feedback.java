package sms.entities.account.customer.feedback;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
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
@NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
public class Feedback implements Serializable, Comparable<Feedback> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_generator")
	@SequenceGenerator(name = "feedback_generator", sequenceName = "feedback_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String userName;
	private String userEmail;
	private String subject;
	private String message;
	@ManyToOne
	private Employee employee;
	private Date updateDate;
	private Status status;

	// ----- Constructors -----
	public Feedback() {
		super();
	}

	public Feedback(String userName, String userEmail, String subject, String message) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.subject = subject;
		this.message = message;
		this.updateDate = new Date(System.currentTimeMillis());
		this.status = Status.ACTIVE;
	}

	// ----- Getters and Setters ------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	// ----- Methods -----
	public int compareTo(Feedback feedback) {
		return -feedback.getSubject().compareToIgnoreCase(this.getSubject());
	}

	public void removeFeedback() {
		this.setStatus(Status.INACTIVE);
	}
}
