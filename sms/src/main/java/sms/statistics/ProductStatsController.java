package sms.statistics;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sms.entities.order.cart.line.CartLine;
import sms.entities.order.cart.line.ICartLineService;
import sms.utils.UtilMethods;

@RestController
@RequestMapping("/productStats")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductStatsController {

	@Autowired
	private ICartLineService cartLineService;

	@RequestMapping(value = "/day/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Date, Double> getProductsStatisticDataByDay(@PathVariable("productId") int productId) {
		Map<Date, Double> statisticData = new TreeMap<Date, Double>();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
			if (cartLine.getItem().getId().equals(productId)) {
				Double value = cartLine.getValue();
				if (statisticData.get(cartLine.getCart().getOrder().getDate()) != null) {
					value += statisticData.get(cartLine.getCart().getOrder().getDate());
				}
				statisticData.put(cartLine.getCart().getOrder().getDate(), value);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getProductsStatisticDataByMonth(@PathVariable("productId") int productId) {
		Map<Integer, Double> statisticData = new TreeMap<Integer, Double>();
		Calendar cal = Calendar.getInstance();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
			if (cartLine.getItem().getId().equals(productId)) {
				Double value = cartLine.getValue();
				cal.setTime(cartLine.getCart().getOrder().getDate());
				Integer time = (cal.get(Calendar.YEAR) * 100) + cal.get(Calendar.MONTH);
				if (statisticData.get(time) != null) {
					value += statisticData.get(time);
				}
				statisticData.put(time, value);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/complete/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getCompleteProductsStatisticDataByMonth(@PathVariable("productId") int productId) {
		Map<Integer, Double> statisticData = this.getProductsStatisticDataByMonth(productId);

		List<Integer> months = Arrays.asList(201808, 201809, 201810, 201811, 201900, 201901, 201902, 201903);

		for (Integer month : months) {
			if (statisticData.get(month) == null) {
				statisticData.put(month, 0.0);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/price/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Double, Double> getProductsStatisticDataBasedOnPriceByMonth(@PathVariable("productId") int productId) {
		Map<Double, Double> statisticData = new TreeMap<Double, Double>();

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
			if (cartLine.getItem().getId().equals(productId)) {
				Double price = cartLine.getValue();
				Double value = price;
				if (statisticData.get(price) != null) {
					value += statisticData.get(price);
				}
				statisticData.put(price, value);
			}
		}

		return statisticData;
	}

	@RequestMapping(value = "/month/complex", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, Double> getProductsComplexStatisticDataByMonth(@RequestBody DataRequest dataRequest) {
		Map<Integer, Double> tempData = new TreeMap<Integer, Double>();
		Map<Integer, Double> statisticData = new TreeMap<Integer, Double>();
		Calendar cal = Calendar.getInstance();
		
		Integer startMonth = dataRequest.getMonthStart().ordinal();
		Integer endMonth = dataRequest.getMonthEnd().ordinal();

		if (startMonth < 4) {
			startMonth += 12;
			endMonth += 12;
		} else if (endMonth < 4) {
			endMonth += 12;
		}

		for (CartLine cartLine : cartLineService.findAllCartLines()) {
			Integer cartStartMonth = UtilMethods.getMonthFromDate(cartLine.getCart().getOrder().getDate()).ordinal();
			Integer cartEndMonth = UtilMethods.getMonthFromDate(cartLine.getCart().getOrder().getDate()).ordinal();

			if (cartStartMonth < 4) {
				cartStartMonth += 12;
				cartEndMonth += 12;
			} else if (cartEndMonth < 4) {
				cartEndMonth += 12;
			}

			if ((cartStartMonth >= startMonth) && (cartEndMonth <= endMonth)) {

				if (cartLine.getItem().getId().equals(dataRequest.getObjectId())) {
					Double value = cartLine.getValue();
					cal.setTime(cartLine.getCart().getOrder().getDate());
					Integer time = (cal.get(Calendar.YEAR) * 100) + cal.get(Calendar.MONTH);
					if (tempData.get(time) != null) {
						value += tempData.get(time);
					}
					tempData.put(time, value);
				}
			}
		}

		for (Map.Entry<Integer, Double> entry : tempData.entrySet()) {
			if (entry.getValue() <= dataRequest.getMaxValue() && entry.getValue() >= dataRequest.getMinValue()) {
				statisticData.put(entry.getKey(), entry.getValue());
			}
		}

		return statisticData;
	}

}
