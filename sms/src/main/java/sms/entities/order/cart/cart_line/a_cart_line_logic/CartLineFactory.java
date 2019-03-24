package sms.entities.order.cart.cart_line.a_cart_line_logic;

import java.util.HashMap;

import sms.entities.order.cart.cart_line.CartLine;
import sms.entities.z_lines_logic.LineCloneFactory;

public class CartLineFactory {

	private static final HashMap<Integer, CartLine> cartLinesByQuantity = new HashMap<Integer, CartLine>();

	public static CartLine getCartLine(Integer quantity) {
		LineCloneFactory lineCloneFactory = new LineCloneFactory();
		CartLine cartLine = (CartLine) cartLinesByQuantity.get(quantity);

		if (cartLine == null) {
			cartLine = new CartLine(quantity);
			cartLinesByQuantity.put(quantity, cartLine);
		} else {
			cartLine = (CartLine) lineCloneFactory.getClone(cartLine);
		}

		return cartLine;
	}
}
