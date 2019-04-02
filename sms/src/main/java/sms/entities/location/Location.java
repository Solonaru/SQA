package sms.entities.location;

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
import sms.entities.address.Address;
import sms.enums.Status;

@Entity
@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l")
public class Location implements Serializable, Comparable<Location> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
	@SequenceGenerator(name = "location_generator", sequenceName = "location_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String name;
	@Column(columnDefinition = "TEXT")
	private String description;
	@ManyToOne
	private Address address;
	@ManyToOne
	private Employee employee;
	private Date updateDate;
	private Status status;

	// ----- Constructors -----
	public Location() {
		super();
	}

	public Location(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
	public int compareTo(Location location) {
		return -location.getName().compareToIgnoreCase(this.getName());
	}

	public void removeLocation() {
		this.setStatus(Status.INACTIVE);
	}

	public String getCityName() {
		return (null == this.getAddress()) ? (null)
				: ((null == this.getAddress().getCity()) ? (null) : (this.getAddress().getCity().getName()));
	}
}
