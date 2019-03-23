package sms.entities.z_lines_logic;

import sms.entities.item.recipe.recipe_line.RecipeLine;
import sms.enums.ComponentType;

public class RecipeLineFactory implements ILineAbstractFactory {

	private ComponentType componentType;
	private Integer quantity;

	// ----- Constructors -----
	public RecipeLineFactory(ComponentType componentType, Integer quantity) {
		this.componentType = componentType;
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new RecipeLine(componentType, quantity);
	}

}
