package sms.entities.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import sms.entities.account.Account;
import sms.entities.item.Item;

@Entity
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
	@SequenceGenerator(name = "comment_generator", sequenceName = "comment_sequence", initialValue = 1200000001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String comment;
	@ManyToOne
	private Account account;
	@ManyToOne
	private Item item;
	@ManyToOne
	private Comment previousComment;

	// ----- Constructors -----
	public Comment() {
		super();
	}

	public Comment(String comment) {
		super();
		this.comment = comment;
	}

	// ----- Getters and Setters -----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Comment getPreviousComment() {
		return previousComment;
	}

	public void setPreviousComment(Comment previousComment) {
		this.previousComment = previousComment;
	}

}
