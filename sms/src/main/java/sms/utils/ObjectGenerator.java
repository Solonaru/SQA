package sms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import sms.entities.item.Item;
import sms.entities.order.Orders;
import sms.entities.order.cart.Cart;
import sms.entities.order.cart.line.CartLine;
import sms.entities.order.cart.line.logic.CartLineFactory;
import sms.entities.order.payment.Payment;
import sms.enums.order.OrderStatus;
import sms.enums.order.PaymentStatus;
import sms.enums.order.PaymentType;

@Component
public class ObjectGenerator {

	SimpleDateFormat sdf;

	// ----- Constructor -----
	public ObjectGenerator() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	// ----- Public methods -----
	public List<CartLine> genCartLines(List<Item> items, Integer amount) {
		List<CartLine> generatedCartLines = new ArrayList<CartLine>();

		for (int i = 0; i < amount; i++) {
			CartLine cartLine = CartLineFactory.getCartLine(genRandNum(1, 3));
			cartLine.setItem(items.get(genRandNum(0, items.size() - 1)));
			generatedCartLines.add(cartLine);
		}

		return generatedCartLines;
	}

	public List<Cart> genCarts(List<CartLine> cartLines) {
		int cartLinesCount = cartLines.size();
		List<Cart> generatedCarts = new ArrayList<Cart>();

		while (cartLinesCount > 0) {
			int linesToAdd = 0;

			do {
				linesToAdd = genRandNum(1, 5);
			} while (cartLinesCount < linesToAdd);

			Cart cart = new Cart();

			for (int i = 0; i < linesToAdd; i++) {
				cart.addLine(cartLines.get(cartLines.size() - cartLinesCount));
				cartLinesCount--;
			}

			generatedCarts.add(cart);
		}

		return generatedCarts;
	}

	public Object[] genCompleteOrders(List<CartLine> cartLines) {
		int cartLinesCount = cartLines.size();
		List<Cart> generatedCarts = new ArrayList<Cart>();
		List<Orders> generatedOrders = new ArrayList<Orders>();
		List<Payment> generatedPayments = new ArrayList<Payment>();

		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
		paymentTypes.add(PaymentType.VISA);
		paymentTypes.add(PaymentType.MAESTRO);
		paymentTypes.add(PaymentType.MASTERCARD);

		while (cartLinesCount > 0) {
			int linesToAdd = 0;

			do {
				linesToAdd = genRandNum(1, 5);
			} while (cartLinesCount < linesToAdd);

			Cart cart = new Cart();

			for (int i = 0; i < linesToAdd; i++) {
				cart.addLine(cartLines.get(cartLines.size() - cartLinesCount));
				cartLinesCount--;
			}

			Orders order = null;
			try {
				order = new Orders(genRandDate(sdf.parse("01/09/2018"), sdf.parse("09/04/2019")),
						OrderStatus.COMPLETED);
				order.setCart(cart);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			Payment payment = new Payment(paymentTypes.get(genRandNum(0, paymentTypes.size() - 1)),
					PaymentStatus.COMPLETED, order.getDate());
			payment.setOrder(order);

			generatedCarts.add(cart);
			generatedOrders.add(order);
			generatedPayments.add(payment);
		}

		Object[] array = { generatedCarts, generatedOrders, generatedPayments };

		return array;
	}

	// ----- Private methods -----
	private Integer genRandNum(Integer min, Integer max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	private Date genRandDate(Date from, Date to) {
		return new Date(ThreadLocalRandom.current().nextLong(from.getTime(), to.getTime()));
	}
}
