package sms.entities.order.payment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sms.entities.order.Orders;
import sms.enums.order.PaymentStatus;
import sms.enums.order.PaymentType;

@Entity
@NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_generator")
	@SequenceGenerator(name = "payment_generator", sequenceName = "payment_sequence", initialValue = 600000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private PaymentType type;
	@OneToOne
	@JsonIgnoreProperties(value = "payment")
	private Orders orderz;
	private PaymentStatus paymentStatus;
	private Date date;

	// -----Constructors-----
	public Payment() {
		super();
	}

	public Payment(PaymentType type) {
		super();
		this.type = type;
		this.paymentStatus = PaymentStatus.PENDING;
		this.date = new Date(System.currentTimeMillis());
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public Orders getOrder() {
		return orderz;
	}

	public void setOrder(Orders order) {
		this.orderz = order;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
