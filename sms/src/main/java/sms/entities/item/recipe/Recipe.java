package sms.entities.item.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import sms.entities.item.product.Product;
import sms.entities.item.recipe.line.RecipeLine;
import sms.entities.logic.ILine;
import sms.entities.logic.ILineIterator;

@Entity
@NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r")
@DiscriminatorValue("Recipe")
public class Recipe extends Product implements ILineIterator {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "recipe")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties(value = { "recipe", "category" })
	private List<RecipeLine> recipeLines;

	// ----- Constructors -----
	public Recipe() {
		super();
	}

	public Recipe(String name, Integer stockQuantity, String description) {
		super(name, stockQuantity, description);
	}

	public List<RecipeLine> getRecipeLines() {
		return recipeLines;
	}

	public void setRecipeLines(List<RecipeLine> recipeLines) {
		this.recipeLines = recipeLines;
	}

	// ----- Methods -----
	public void addLine(RecipeLine recipeLine) {
		if (null == recipeLines) {
			recipeLines = new ArrayList<RecipeLine>();
		}

		recipeLines.add(recipeLine);
		recipeLine.setRecipe(this);
	}

	public Iterator<? extends ILine> createLinesIterator() {
		return recipeLines.iterator();
	}
}
