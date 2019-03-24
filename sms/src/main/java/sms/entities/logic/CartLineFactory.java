package sms.entities.logic;

import sms.entities.order.cart.line.CartLine;

public class CartLineFactory implements ILineAbstractFactory {

	private Integer quantity;

	// ----- Constructors -----
	public CartLineFactory(Integer quantity) {
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new CartLine(quantity);
	}

}
