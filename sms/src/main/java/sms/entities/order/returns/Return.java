package sms.entities.order.returns;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import sms.entities.order.cart.line.CartLine;
import sms.enums.order.ReturnStatus;

@Entity
@NamedQuery(name = "Return.findAll", query = "SELECT r FROM Return r")
public class Return implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "return_generator")
	@SequenceGenerator(name = "return_generator", sequenceName = "return_sequence", initialValue = 700000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String details;
	@OneToOne
	private CartLine cartLine;
	private ReturnStatus status;
	private Date date;

	// ----- Constructors -----
	public Return() {
		super();
	}

	public Return(String details) {
		super();
		this.details = details;
		this.status = ReturnStatus.PENDING;
		this.date = new Date(System.currentTimeMillis());
	}

	// ----- Getters and Setters -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public CartLine getCartLine() {
		return cartLine;
	}

	public void setCartLine(CartLine cartLine) {
		this.cartLine = cartLine;
	}

	public ReturnStatus getStatus() {
		return status;
	}

	public void setStatus(ReturnStatus status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
