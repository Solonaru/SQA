package sms.entities.account.employee;

import java.sql.Date;

import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sms.entities.account.Account;
import sms.entities.account.employee.a_employee_logic.IRight;
import sms.entities.account.employee.a_employee_logic.NoRight;
import sms.entities.account.employee.a_employee_logic.RightConverter;
import sms.enums.account_enums.AccountStatus;
import sms.enums.account_enums.EmployeeStatus;

@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
@DiscriminatorValue("Employee")
@JsonDeserialize(as = Employee.class)
public class Employee extends Account {
	private static final long serialVersionUID = 1L;

	private EmployeeStatus employeeStatus;

	@Convert(converter = RightConverter.class)
	private IRight rightType;

	// ----- Constructors -----
	public Employee() {
		super.creationDate = new Date(System.currentTimeMillis());
		super.status = AccountStatus.ACTIVE;
		this.employeeStatus = EmployeeStatus.INTERNSHIP;
		this.rightType = new NoRight();
	}

	public Employee(String username, String password, String name, String email, String phoneNumber,
			EmployeeStatus employeeStatus, IRight rightType) {
		super(username, password, name, email, phoneNumber);
		super.creationDate = new Date(System.currentTimeMillis());
		super.status = AccountStatus.ACTIVE;
		this.employeeStatus = employeeStatus;
		this.rightType = rightType;
	}

	// ----- Getters and Setters -----
	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public IRight getRightType() {
		return rightType;
	}

	public void setRightType(IRight rightType) {
		this.rightType = rightType;
	}

	// ----- Methods -----
	public String toString() {
		return username;
	}

}
