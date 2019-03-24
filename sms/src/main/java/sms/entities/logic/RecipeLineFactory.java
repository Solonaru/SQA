package sms.entities.logic;

import sms.entities.item.recipe.line.RecipeLine;

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
