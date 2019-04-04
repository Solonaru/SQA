package sms.entities.logic;

import sms.entities.item.recipe.line.RecipeLine;

public class RecipeLineFactory implements ILineAbstractFactory {

	private Double quantity;

	// ----- Constructors -----
	public RecipeLineFactory(Double quantity) {
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new RecipeLine(quantity);
	}

}
