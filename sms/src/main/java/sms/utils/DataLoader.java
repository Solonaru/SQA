package sms.utils;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import sms.entities.account.IAccountService;
import sms.entities.account.customer.Customer;
import sms.entities.account.customer.subscription.ISubscriptionService;
import sms.entities.account.customer.subscription.Subscription;
import sms.entities.account.employee.Employee;
import sms.entities.account.employee.logic.Admin;
import sms.entities.account.employee.logic.OperatorProducts;
import sms.entities.address.Address;
import sms.entities.address.IAddressService;
import sms.entities.address.city.City;
import sms.entities.address.city.ICityService;
import sms.entities.address.county.County;
import sms.entities.address.county.ICountyService;
import sms.entities.address.logic.AddressBuilder;
import sms.entities.category.Category;
import sms.entities.category.ICategoryService;
import sms.entities.item.IItemService;
import sms.entities.item.component.ingredient.Ingredient;
import sms.entities.item.recipe.Recipe;
import sms.entities.item.recipe.line.IRecipeLineService;
import sms.entities.item.recipe.line.RecipeLine;
import sms.enums.account.EmployeeStatus;
import sms.enums.account.SubscriptionType;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ICountyService countyService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private ISubscriptionService subscriptionService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IItemService itemService;
	@Autowired
	private IRecipeLineService recipeLineService;

	@Autowired
	private DisplayData displayData;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isDBEmpty()) {
			loadData();
		}
	}

	private void loadData() {
		displayData.printInfo("Starting data loading...");

		updateCounty();

		updateCity();

		updateAddress();

		updateSubscription();

		updateCustomer();

		updateEmployee();

		updateIngredients();

		displayData.printInfo("Data successfully loaded.");
	}

	private void updateCounty() {
		String[] names = { "Iasi", "Bacau", "Botosani" };
		String region = "Moldova";

		for (String name : names) {
			countyService.insertCounty(new County(name, region));
		}
	}

	private void updateCity() {
		List<County> counties = countyService.findAllCounties();
		String[] cities = { "Iasi", "Pascani", "Targu Frumos", "Bacau", "Slanic Moldova", "Ghimes", "Botosani",
				"Darabani", "Flamanzi" };
		int countyCounter = 0;

		for (County county : counties) {
			for (int cityCounter = countyCounter; cityCounter < countyCounter + 3; cityCounter++) {
				City city = new City(cities[cityCounter]);
				city.setCounty(county);
				cityService.insertCity(city);
			}
			countyCounter += 3;
		}
	}

	/* Have to re-think this */
	private void updateAddress() {
		Address address1 = new AddressBuilder("Alexandru cel Bun", 51).setApartamentNr(15).setZipCode(270123)
				.getAddress();
		address1.setCity(cityByName("Iasi"));
		Address address2 = new AddressBuilder("Marinelor", 11).setZipCode(280129).getAddress();
		address2.setCity(cityByName("Pascani"));
		Address address3 = new AddressBuilder("Marinelor", 27).setBuildingNr("B").setZipCode(280129).getAddress();
		address3.setCity(cityByName("Pascani"));
		Address address4 = new AddressBuilder("Marin Cordoba", 100).setZipCode(290111).getAddress();
		address4.setCity(cityByName("Targu Frumos"));
		Address address5 = new AddressBuilder("Cosmopolitan", 127).setBuildingNr("3").setApartamentNr(205)
				.setFloorNr("2").setZipCode(270123).getAddress();
		address5.setCity(cityByName("Iasi"));
		Address address6 = new AddressBuilder("Cosmopolitan", 129).setZipCode(270123).getAddress();
		address6.setCity(cityByName("Iasi"));
		Address address7 = new AddressBuilder("Cereselor", 12).setApartamentNr(115).setFloorNr("GF").setZipCode(270323)
				.getAddress();
		address7.setCity(cityByName("Iasi"));
		Address address8 = new AddressBuilder("Independentei", 30).setZipCode(278012).getAddress();
		address8.setCity(cityByName("Iasi"));

		addressService.insertAddress(address1);
		addressService.insertAddress(address2);
		addressService.insertAddress(address3);
		addressService.insertAddress(address4);
		addressService.insertAddress(address5);
		addressService.insertAddress(address6);
		addressService.insertAddress(address7);
		addressService.insertAddress(address8);
	}

	private void updateSubscription() {
		SubscriptionType[] subscriptions = { SubscriptionType.DISCOUNTS, SubscriptionType.NEW_PRODUCTS,
				SubscriptionType.PROMOTIONAL };

		for (SubscriptionType s : subscriptions) {
			subscriptionService.insertSubscription(new Subscription(s));
		}
	}

	private void updateCustomer() {
		String[] users = { "Alex", "Buzzy23", "Alinacika" };
		String[] passwords = { "alex123", "password", "saalina" };
		String[] names = { "Alexandru", "Maxim", "Alina" };
		String[] emails = { "alex_cozma@gmail.com", "max_96@yahoo.com", "alina_sandra@gmail.com" };
		String[] pNumbers = { "0748974419", "0742114411", "0742271410" };
		Random r = new Random();

		for (int i = 0; i < users.length; i++) {
			Customer customer = new Customer(users[i], passwords[i], names[i], emails[i], pNumbers[i]);
			customer.setAddress(randomAddress());
			customer.getDeliveryAddresses().add(randomAddress());
			addNSubscriptions(r.nextInt(3), customer);
			accountService.insertAccount(customer);
		}
	}

	private void updateEmployee() {
		Employee employee1 = new Employee("viorel", "viorel123", "Viorel", "viorelsolonaru@gmail.com", "0748974417",
				EmployeeStatus.SENIOR, new Admin());
		employee1.setAddress(randomAddress());
		Employee employee2 = new Employee("andrei", "andrei123", "Andrei", "andreihumulescu@gmail.com", "0721314417",
				EmployeeStatus.INTERNSHIP, new OperatorProducts());
		employee2.setAddress(randomAddress());

		Employee employee3 = new Employee("mihai", "mihai", "Mihai", "m.m@m.com", "0741234567", EmployeeStatus.JUNIOR,
				new OperatorProducts());
		employee3.setAddress(randomAddress());

		accountService.insertAccount(employee1);
		accountService.insertAccount(employee2);
		accountService.insertAccount(employee3);
	}

	/*
	 * Must rewrite this in order to accept images from different folders and also
	 * to separate the categories
	 */
	private void updateIngredients() {
		/*
		 * All images from https://www.freshdirect.com/
		 * Use same source, to maintain same format
		 */

		Category category1 = new Category("Pizzas", new Date(System.currentTimeMillis()), "Pizzas category");
		Category category2 = new Category("Deserts", new Date(System.currentTimeMillis()), "Deserts category");
		Category category3 = new Category("Beverages", new Date(System.currentTimeMillis()), "Beverages category");

		Category category4 = new Category("Ingredients", new Date(System.currentTimeMillis()), "Ingredients category");
		Category category401 = new Category("Vegetables", new Date(System.currentTimeMillis()), "Vegetables category");
		category401.setParentCategory(category4);
		Category category402 = new Category("Fruits", new Date(System.currentTimeMillis()), "Fruits category");
		category402.setParentCategory(category4);
		Category category403 = new Category("Grains, Beans and Nuts", new Date(System.currentTimeMillis()),
				"Grains, beans and nuts category");
		category403.setParentCategory(category4);
		Category category404 = new Category("Meat and Poultry", new Date(System.currentTimeMillis()),
				"Meat and poultry category");
		category404.setParentCategory(category4);
		Category category405 = new Category("Fish and Seafood", new Date(System.currentTimeMillis()),
				"Fish and seafood category");
		category405.setParentCategory(category4);
		Category category406 = new Category("Dairy Foods", new Date(System.currentTimeMillis()),
				"Dairy foods category");
		category406.setParentCategory(category4);
		Category category407 = new Category("Pantry", new Date(System.currentTimeMillis()), "Pantry category");
		category407.setParentCategory(category4);

		Category category5 = new Category("Dough recipes", new Date(System.currentTimeMillis()),
				"Dough recipes category");
		Category category6 = new Category("Tomato sauce recipes", new Date(System.currentTimeMillis()),
				"Tomato sauce recipes category");

		categoryService.insertCategory(category1);
		categoryService.insertCategory(category2);
		categoryService.insertCategory(category3);

		categoryService.insertCategory(category4);
		categoryService.insertCategory(category401);
		categoryService.insertCategory(category402);
		categoryService.insertCategory(category403);
		categoryService.insertCategory(category404);
		categoryService.insertCategory(category405);
		categoryService.insertCategory(category406);
		categoryService.insertCategory(category407);

		categoryService.insertCategory(category5);
		categoryService.insertCategory(category6);

		/*-------------------------------------------*/

		Ingredient meatAndPoultry1 = new Ingredient("Beef Eye Round Steak", 10, new Date(System.currentTimeMillis()),
				"");
		meatAndPoultry1.setCategory(category404);
		meatAndPoultry1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgBeefEyeRoundSteak.jpg");
		Ingredient meatAndPoultry2 = new Ingredient("Rib Eye Steak", 16, new Date(System.currentTimeMillis()), "");
		meatAndPoultry2.setCategory(category404);
		meatAndPoultry2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgRibEyeSteak.jpg");
		Ingredient meatAndPoultry3 = new Ingredient("Sweet Italian Pork Sausage", 22,
				new Date(System.currentTimeMillis()), "");
		meatAndPoultry3.setCategory(category404);
		meatAndPoultry3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgSweetItalianPorkSausage.jpg");
		Ingredient meatAndPoultry4 = new Ingredient("Pork Bacon", 22, new Date(System.currentTimeMillis()), "");
		meatAndPoultry4.setCategory(category404);
		meatAndPoultry4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgPorkBacon.jpg");

		Ingredient vegetables1 = new Ingredient("Sweet Cherry Tomatoes", 2, new Date(System.currentTimeMillis()), "");
		vegetables1.setCategory(category401);
		vegetables1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgSweetCherryTomatoes.jpg");
		Ingredient vegetables2 = new Ingredient("Campari Tomatoes", 2, new Date(System.currentTimeMillis()), "");
		vegetables2.setCategory(category401);
		vegetables2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgCampariTomatoes.jpg");
		Ingredient vegetables3 = new Ingredient("Baby Bella Mushrooms", 1, new Date(System.currentTimeMillis()), "");
		vegetables3.setCategory(category401);
		vegetables3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgBabyBellaMushrooms.jpg");
		Ingredient vegetables4 = new Ingredient("White Mushrooms", 1, new Date(System.currentTimeMillis()), "");
		vegetables4.setCategory(category401);
		vegetables4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgWhiteMushrooms.jpg");
		Ingredient vegetables5 = new Ingredient("Garlic", 2, new Date(System.currentTimeMillis()), "");
		vegetables5.setCategory(category401);
		vegetables5.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgGarlic.jpg");
		Ingredient vegetables6 = new Ingredient("Red Onion", 1, new Date(System.currentTimeMillis()), "");
		vegetables6.setCategory(category401);
		vegetables6.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgRedOnion.jpg");
		Ingredient vegetables7 = new Ingredient("Yellow Onion", 1, new Date(System.currentTimeMillis()), "");
		vegetables7.setCategory(category401);
		vegetables7.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgYellowOnion.jpg");
		Ingredient vegetables8 = new Ingredient("Oregano", 2, new Date(System.currentTimeMillis()), "");
		vegetables8.setCategory(category401);
		vegetables8.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgOregano.jpg");

		Ingredient dairyFoods1 = new Ingredient("Swiss Emmental", 16, new Date(System.currentTimeMillis()), "");
		dairyFoods1.setCategory(category406);
		dairyFoods1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgSwissEmmental.jpg");
		Ingredient dairyFoods2 = new Ingredient("Grana Padano", 18, new Date(System.currentTimeMillis()), "");
		dairyFoods2.setCategory(category406);
		dairyFoods2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgGranaPadano.jpg");
		Ingredient dairyFoods3 = new Ingredient("Pecorino Romano", 12, new Date(System.currentTimeMillis()), "");
		dairyFoods3.setCategory(category406);
		dairyFoods3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgPecorinoRomano.jpg");
		Ingredient dairyFoods4 = new Ingredient("Fresh Mozzarella", 9, new Date(System.currentTimeMillis()), "");
		dairyFoods4.setCategory(category406);
		dairyFoods4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgFreshMozzarella.jpg");
		Ingredient dairyFoods5 = new Ingredient("Gorgonzola", 18, new Date(System.currentTimeMillis()), "");
		dairyFoods5.setCategory(category406);
		dairyFoods5.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgGorgonzola.jpg");

		Ingredient pantry1 = new Ingredient("Extra-Virgin Olive Oil", 11, new Date(System.currentTimeMillis()), "");
		pantry1.setCategory(category407);
		pantry1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgExtraVirginOliveOil.jpg");
		Ingredient pantry2 = new Ingredient("Table Salt", 11, new Date(System.currentTimeMillis()), "");
		pantry2.setCategory(category407);
		pantry2.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgTableSalt.jpg");
		Ingredient pantry3 = new Ingredient("Whole Wheat Pre-Sifted Flour", 5, new Date(System.currentTimeMillis()),
				"");
		pantry3.setCategory(category407);
		pantry3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgWholeWheatPreSiftedFlour.jpg");
		Ingredient pantry4 = new Ingredient("Unbleached All-Purpose Flour", 4, new Date(System.currentTimeMillis()),
				"");
		pantry4.setCategory(category407);
		pantry4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgUnbleachedAllPurposeFlour.jpg");
		Ingredient pantry5 = new Ingredient("Canola Oil", 4, new Date(System.currentTimeMillis()), "");
		pantry5.setCategory(category407);
		pantry5.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgCanolaOil.jpg");
		Ingredient pantry6 = new Ingredient("White Flour", 4, new Date(System.currentTimeMillis()), "");
		pantry6.setCategory(category407);
		pantry6.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgWhiteFlour.jpg");
		Ingredient pantry7 = new Ingredient("Whole Wheat Flour", 6, new Date(System.currentTimeMillis()), "");
		pantry7.setCategory(category407);
		pantry7.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgWholeWheatFlour.jpg");
		Ingredient pantry8 = new Ingredient("Light Brown Sugar", 2, new Date(System.currentTimeMillis()), "");
		pantry8.setCategory(category407);
		pantry8.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgLightBrownSugar.jpg");
		Ingredient pantry9 = new Ingredient("Dark Brown Sugar", 2, new Date(System.currentTimeMillis()), "");
		pantry9.setCategory(category407);
		pantry9.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgDarkBrownSugar.jpg");

		itemService.insertItem(meatAndPoultry1);
		itemService.insertItem(meatAndPoultry2);
		itemService.insertItem(meatAndPoultry3);
		itemService.insertItem(meatAndPoultry4);

		itemService.insertItem(vegetables1);
		itemService.insertItem(vegetables2);
		itemService.insertItem(vegetables3);
		itemService.insertItem(vegetables4);
		itemService.insertItem(vegetables5);
		itemService.insertItem(vegetables6);
		itemService.insertItem(vegetables7);
		itemService.insertItem(vegetables8);

		itemService.insertItem(dairyFoods1);
		itemService.insertItem(dairyFoods2);
		itemService.insertItem(dairyFoods3);
		itemService.insertItem(dairyFoods4);
		itemService.insertItem(dairyFoods5);

		itemService.insertItem(pantry1);
		itemService.insertItem(pantry2);
		itemService.insertItem(pantry3);
		itemService.insertItem(pantry4);
		itemService.insertItem(pantry5);
		itemService.insertItem(pantry6);
		itemService.insertItem(pantry7);
		itemService.insertItem(pantry8);
		itemService.insertItem(pantry9);

		/*-------------------------------------------*/

		/*
		 * Grilled Thin-Crust
		 * 	- Unbleached All-Purpose Flour
		 *  - Salt
		 */
		RecipeLine recipeLine10101 = new RecipeLine(170);
		recipeLine10101.setComponent(pantry4);
		RecipeLine recipeLine10102 = new RecipeLine(10);
		recipeLine10102.setComponent(pantry2);

		Recipe recipe101 = new Recipe("Grilled Thin-Crust", 1, new Date(System.currentTimeMillis()),
				"Grilled Thin-Crust recipe");
		recipe101.setCategory(category5);
		recipe101.addLine(recipeLine10101);
		recipe101.addLine(recipeLine10102);

		itemService.insertItem(recipe101);
		recipeLineService.insertRecipeLine(recipeLine10101);
		recipeLineService.insertRecipeLine(recipeLine10102);

		/*
		 * Grilled Whole-Wheat Crust
		 * 	- Whole Wheat Pre-Sifted Flour
		 * 	- Canola Oil
		 * 	- Salt
		 * 	- Brown Sugar
		 */
		RecipeLine recipeLine10201 = new RecipeLine(280);
		recipeLine10201.setComponent(pantry3);
		RecipeLine recipeLine10202 = new RecipeLine(10);
		recipeLine10202.setComponent(pantry5);
		RecipeLine recipeLine10203 = new RecipeLine(10);
		recipeLine10203.setComponent(pantry2);
		RecipeLine recipeLine10204 = new RecipeLine(14);
		recipeLine10204.setComponent(pantry8);

		Recipe recipe102 = new Recipe("Grilled Whole-Wheat Crust", 1, new Date(System.currentTimeMillis()),
				"Grilled Whole-Wheat Crust recipe");
		recipe102.setCategory(category5);
		recipe102.addLine(recipeLine10201);
		recipe102.addLine(recipeLine10202);
		recipe102.addLine(recipeLine10203);
		recipe102.addLine(recipeLine10204);

		itemService.insertItem(recipe102);
		recipeLineService.insertRecipeLine(recipeLine10201);
		recipeLineService.insertRecipeLine(recipeLine10202);
		recipeLineService.insertRecipeLine(recipeLine10203);
		recipeLineService.insertRecipeLine(recipeLine10204);
		
		/* TODO: Add the listed recipes

		/*
		 * Tomato Sauce
		 *  - Tomato
		 *  - Garlic
		 *  - Olive Oil
		 *  - Oregano
		 *  - Salt)
		 */
		
		/* Grilled Thin-Crust Sweet Italian Sausage Pizza
		 * 	- Grilled Thin-Crust (Unbleached All-Purpose Flour, Salt)
		 *  - Tomato Sauce (Garlic, Olive Oil, Oregano, Tomato, Salt)
		 *  - Pecorino-Romano Cheese
		 *  - Fresh Mozzarella Cheese
		 *  - Italian Pork Sausage
		 */
		
		/*
		 * Grilled Whole-Wheat Crust Mozzarella Pizza
		 * 	- Grilled Whole-Wheat Crust (Whole Wheat Flour, Canola Oil, Salt, Brown Sugar)
		 *  - Tomato Sauce (Garlic, Olive Oil, Oregano, Tomato, Salt)
		 *  - Pecorino-Romano Cheese
		 *  - Fresh Mozzarella Cheese
		 */
		
		/*
		 * Grilled Thin-Crust Onion, Gorgonzola and Bacon Pizza
		 *  - Grilled Thin-Crust (Enriched Unbromated Wheat Flour, Salt)
		 *  - Gorgonzola Cheese
		 *  - Fresh Mozzarella Cheese
		 *  - Pork Bacon
		 *  - Onion
		 */

	}

	private City cityByName(String name) {
		Optional<City> city = cityService.findCityByName(name);
		return city.orElseGet(() -> new City(""));
	}

	private Address randomAddress() {
		List<Address> addresses = addressService.findAllAddresses();
		Random r = new Random();
		return addresses.get(r.nextInt(addresses.size()));
	}

	private Subscription randomSubscription() {
		List<Subscription> subscriptions = subscriptionService.findAllSubscriptions();
		Random r = new Random();
		return subscriptions.get(r.nextInt(subscriptions.size()));
	}

	private void addNSubscriptions(int n, Customer customer) {
		for (int i = 0; i < n; i++) {
			Subscription s = randomSubscription();
			if (!customer.getSubscriptions().contains(s)) {
				customer.getSubscriptions().add(randomSubscription());
			}
		}
	}

	private Boolean isDBEmpty() {
		boolean isEmpty = false;
		List<County> counties = countyService.findAllCounties();

		if (counties.isEmpty()) {
			displayData.printInfo("The database is empty.");
			isEmpty = true;
		} else {
			displayData.printInfo("The database in NOT empty. No data loading required.");
		}

		return isEmpty;
	}
}
