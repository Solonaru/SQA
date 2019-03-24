package sms.entities.z_lines_logic;

import sms.entities.item.recipe.recipe_line.RecipeLine;

public class RecipeLineFactory implements ILineAbstractFactory {

	private Integer quantity;

	// ----- Constructors -----
	public RecipeLineFactory(Integer quantity) {
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new RecipeLine(quantity);
	}

}
