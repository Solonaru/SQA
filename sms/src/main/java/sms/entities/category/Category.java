package sms.entities.category;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sms.entities.employee.Employee;
import sms.entities.item.IItemIterator;
import sms.entities.item.Item;
import sms.enums.Status;

@Entity
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable, IItemIterator, Comparable<Category> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
	@SequenceGenerator(name = "category_generator", sequenceName = "category_sequence", initialValue = 8001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private String name;
	private Date updateDate;
	@Column(columnDefinition = "TEXT")
	private String description;
	@ManyToOne
	@JsonIgnoreProperties(value = "childCategories")
	private Category parentCategory;
	@OneToMany(mappedBy = "parentCategory")
	@JsonIgnoreProperties(value = "parentCategory")
	private List<Category> childCategories;
	@ManyToOne
	private Employee employee;
	@OneToMany(mappedBy = "category")
	@JsonIgnoreProperties(value = "category")
	private List<Item> items = new ArrayList<Item>();
	private Status status;

	// ----- Constructors -----
	public Category() {
		super();
	}

	public Category(String name, Date updateDate, String description) {
		super();
		this.name = name;
		this.updateDate = updateDate;
		this.description = description;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<Category> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(List<Category> childCategories) {
		this.childCategories = childCategories;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Iterator<Item> createIterator() {
		return this.items.iterator();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	// ----- Methods -----
	public int compareTo(Category category) {
		return -category.getName().compareToIgnoreCase(this.getName());
	}

	public Boolean isParent() {
		return this.childCategories.size() != 0 ? true : false;
	}

	public void removeCategory() {
		this.setStatus(Status.INACTIVE);
		this.setParentCategory(null);

		if (null != this.getChildCategories()) {
			for (Category childCategory : this.getChildCategories()) {
				childCategory.setParentCategory(null);
			}
		}

		this.childCategories = null;

		if (null != this.getItems()) {
			for (Item item : this.getItems()) {
				item.setCategory(null);
			}
		}

		this.items = null;
	}
}