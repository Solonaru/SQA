package sms.entities.catalogue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import sms.entities.account.employee.Employee;
import sms.entities.catalogue.item.CatalogueItem;
import sms.entities.logic.ILine;
import sms.entities.logic.ILineIterator;
import sms.enums.Month;
import sms.enums.catalogue.CatalogueStatus;

@Entity
@NamedQuery(name = "Catalogue.findAll", query = "SELECT c FROM Catalogue c")
public class Catalogue implements Serializable, ILineIterator {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogue_generator")
	@SequenceGenerator(name = "catalogue_generator", sequenceName = "catalogue_sequence", initialValue = 9001, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Month month;
	private Integer year;
	private CatalogueStatus catalogueStatus;
	@OneToMany(mappedBy = "catalogue")
	@JsonIgnoreProperties(value = "catalogue")
	private List<CatalogueItem> catalogueItems = new ArrayList<CatalogueItem>();
	@ManyToOne
	private Employee employee;
	private Date updateDate;

	// -----Constructors-----
	public Catalogue() {
		super();
	}

	public Catalogue(Month month, Integer year, CatalogueStatus catalogueStatus) {
		super();
		this.month = month;
		this.year = year;
		this.catalogueStatus = catalogueStatus;
		this.updateDate = new Date(System.currentTimeMillis());
	}

	// -----Getters and Setters-----
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<CatalogueItem> getCatalogueItems() {
		return catalogueItems;
	}

	public void setCatalogueItems(List<CatalogueItem> catalogueItems) {
		this.catalogueItems = catalogueItems;
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

	public CatalogueStatus getCatalogueStatus() {
		return catalogueStatus;
	}

	public void setCatalogueStatus(CatalogueStatus catalogueStatus) {
		this.catalogueStatus = catalogueStatus;
	}

	// ----- Methods -----
	public void addLine(CatalogueItem catalogueLine) {
		catalogueItems.add(catalogueLine);
		catalogueLine.setCatalogue(this);
	}

	public Iterator<? extends ILine> createLinesIterator() {
		return catalogueItems.iterator();
	}

}
