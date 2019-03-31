package sms.entities.item;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sms.entities.account.customer.comment.Comment;
import sms.entities.account.customer.rating.Rating;
import sms.entities.account.employee.Employee;
import sms.entities.catalogue.item.CatalogueItem;
import sms.entities.category.Category;
import sms.enums.Month;
import sms.enums.item.ItemType;
import sms.utils.UtilMethods;

@Entity
@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "CLASS_TYPE")
public abstract class Item implements Serializable, Comparable<Item> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
	@SequenceGenerator(name = "item_generator", sequenceName = "item_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;
	protected String name;
	protected Integer stockQuantity;
	protected Double stockPrice;
	protected String description;
	protected String imageUrl;
	@OneToMany(mappedBy = "item")
	protected List<Rating> ratings = new ArrayList<Rating>();
	@OneToMany(mappedBy = "item")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@LazyCollection(LazyCollectionOption.FALSE)
	protected List<CatalogueItem> catalogueItems = new ArrayList<CatalogueItem>();
	@ManyToOne
	@JsonIgnoreProperties(value = "items")
	protected Category category;
	@OneToMany(mappedBy = "item")
	protected List<Comment> comments = new ArrayList<Comment>();
	protected ItemType itemType;
	@ManyToOne
	protected Employee employee;
	protected Date updateDate;

	// -----Constructors-----
	public Item() {
		super();
	}

	public Item(String name, Integer stockQuantity, Double stockPrice, String description) {
		super();
		this.name = name;
		this.stockQuantity = stockQuantity;
		this.stockPrice = stockPrice;
		this.description = description;
		this.updateDate = new Date(System.currentTimeMillis());
	}

	// -----Getters and Setters-----
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

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
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

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<CatalogueItem> getCatalogueItems() {
		return catalogueItems;
	}

	public void setCatalogueItems(List<CatalogueItem> catalogueItems) {
		this.catalogueItems = catalogueItems;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
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

	// -----Methods-----
	public Boolean isListed() {
		for (CatalogueItem catalogueItem : this.getCatalogueItems()) {
			if (UtilMethods.getMonthFromDate(Calendar.getInstance().getTime())
					.equals(catalogueItem.getCatalogue().getMonth())) {
				return true;
			}
		}

		return false;
	}

	public Double getPrice() {
		return this.getPrice(UtilMethods.getMonthFromDate(Calendar.getInstance().getTime()));
	}

	public Double getPrice(Month month) {
		for (CatalogueItem catalogueItem : this.getCatalogueItems()) {
			if (month.equals(catalogueItem.getCatalogue().getMonth())) {
				return catalogueItem.getPrice();
			}
		}

		return -1.0;
	}

	public int compareTo(Item item) {
		return -item.getName().compareToIgnoreCase(this.getName());
	}

	public String toString() {
		return "Name: " + name;
	}
}
