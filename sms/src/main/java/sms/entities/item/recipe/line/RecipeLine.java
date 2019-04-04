package sms.entities.item.recipe.line;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sms.entities.item.product.Product;
import sms.entities.item.recipe.Recipe;
import sms.entities.logic.ILine;

@Entity
@NamedQuery(name = "RecipeLine.findAll", query = "SELECT r FROM RecipeLine r")
public class RecipeLine implements Serializable, ILine {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_line_generator")
	@SequenceGenerator(name = "recipe_line_generator", sequenceName = "recipe_line_sequence", initialValue = 1, allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	private Double quantity;
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	@JsonIgnoreProperties(value = "category")
	private Recipe recipe;
	@ManyToOne
	@JsonIgnoreProperties(value = "category")
	private Product product;

	// ------- Constructors ------
	public RecipeLine() {
		super();
	}

	public RecipeLine(Double quantity) {
		super();
		this.quantity = quantity;
	}

	// ------ Getters and Setters -------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getNeededQuantity() {
		return quantity;
	}

	public void setNeededQuantity(Double neededQuantity) {
		this.quantity = neededQuantity;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Product getComponent() {
		return product;
	}

	public void setComponent(Product component) {
		this.product = component;
	}

	// ----- Methods -----
	public ILine makeCopy() {
		RecipeLine recipeLine = null;

		try {
			recipeLine = (RecipeLine) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return recipeLine;
	}

}
