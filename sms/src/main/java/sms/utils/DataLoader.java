package sms.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import sms.entities.account.IAccountService;
import sms.entities.account.customer.Customer;
import sms.entities.account.customer.feedback.Feedback;
import sms.entities.account.customer.feedback.IFeedbackService;
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
import sms.entities.catalogue.Catalogue;
import sms.entities.catalogue.ICatalogueService;
import sms.entities.catalogue.item.CatalogueItem;
import sms.entities.catalogue.item.ICatalogueItemService;
import sms.entities.category.Category;
import sms.entities.category.ICategoryService;
import sms.entities.item.IItemService;
import sms.entities.item.Item;
import sms.entities.item.component.beverage.Beverage;
import sms.entities.item.component.ingredient.IIngredientService;
import sms.entities.item.component.ingredient.Ingredient;
import sms.entities.item.recipe.Recipe;
import sms.entities.item.recipe.line.IRecipeLineService;
import sms.entities.item.recipe.line.RecipeLine;
import sms.entities.job.IJobService;
import sms.entities.job.Job;
import sms.entities.location.ILocationService;
import sms.entities.location.Location;
import sms.enums.CategoryType;
import sms.enums.Month;
import sms.enums.Status;
import sms.enums.account.AccountType;
import sms.enums.account.EmployeeStatus;
import sms.enums.catalogue.CatalogueStatus;
import sms.enums.item.MeasurementUnit;

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
	private IIngredientService ingredientService;
	@Autowired
	private ICatalogueService catalogueService;
	@Autowired
	private ICatalogueItemService catalogueLineService;
	@Autowired
	private IRecipeLineService recipeLineService;
	@Autowired
	private IJobService jobService;
	@Autowired
	private ILocationService locationService;
	@Autowired
	private IFeedbackService feedbackService;

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
		
		updateConflictIngredients();
		
		updateCatalogue();
		
		updateLocation();
		
		updateJob();
		
		updateFeedback();

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
		String[] subscriptions = { "Discounts", "New products", "Promotional" };
		String[] descriptions = { "Notifications regarding discounts on available products",
				"Notifications regarding new pizzas and related products", "Promotional offers" };

		for (int i = 0; i < subscriptions.length; i++) {
			subscriptionService.insertSubscription(new Subscription(subscriptions[i], descriptions[i]));
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
		employee1.setAccountType(AccountType.ADMIN);
		Employee employee2 = new Employee("andrei", "andrei123", "Andrei", "andreihumulescu@gmail.com", "0721314417",
				EmployeeStatus.INTERNSHIP, new OperatorProducts());
		employee2.setAddress(randomAddress());
		employee2.setAccountType(AccountType.ADMIN);
		Employee employee3 = new Employee("mihai", "mihai", "Mihai", "m.m@m.com", "0741234567", EmployeeStatus.JUNIOR,
				new OperatorProducts());
		employee3.setAddress(randomAddress());
		employee3.setAccountType(AccountType.ADMIN);

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

		Category category1 = new Category("Pizzas", "Pizzas category");
		category1.setCategoryType(CategoryType.FRONT_OFFICE);
		Category category2 = new Category("Deserts", "Deserts category");
		category2.setCategoryType(CategoryType.FRONT_OFFICE);
		Category category3 = new Category("Beverages", "Beverages category");
		category3.setCategoryType(CategoryType.FRONT_OFFICE);

		Category category4 = new Category("Ingredients", "Ingredients category");
		category4.setCategoryType(CategoryType.BACK_OFFICE);
		Category category401 = new Category("Vegetables", "Vegetables category");
		category401.setParentCategory(category4);
		category401.setCategoryType(CategoryType.BACK_OFFICE);
		Category category402 = new Category("Fruits", "Fruits category");
		category402.setParentCategory(category4);
		category402.setCategoryType(CategoryType.BACK_OFFICE);
		Category category403 = new Category("Grains, Beans and Nuts", "Grains, beans and nuts category");
		category403.setParentCategory(category4);
		category403.setCategoryType(CategoryType.BACK_OFFICE);
		Category category404 = new Category("Meat and Poultry", "Meat and poultry category");
		category404.setParentCategory(category4);
		category404.setCategoryType(CategoryType.BACK_OFFICE);
		Category category405 = new Category("Fish and Seafood", "Fish and seafood category");
		category405.setParentCategory(category4);
		category405.setCategoryType(CategoryType.BACK_OFFICE);
		Category category406 = new Category("Dairy Foods", "Dairy foods category");
		category406.setParentCategory(category4);
		category406.setCategoryType(CategoryType.BACK_OFFICE);
		Category category407 = new Category("Pantry", "Pantry category");
		category407.setParentCategory(category4);
		category407.setCategoryType(CategoryType.BACK_OFFICE);

		Category category5 = new Category("Dough recipes", "Dough recipes category");
		category5.setCategoryType(CategoryType.BACK_OFFICE);
		Category category6 = new Category("Tomato sauce recipes", "Tomato sauce recipes category");
		category6.setCategoryType(CategoryType.BACK_OFFICE);

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

		Ingredient meatAndPoultry1 = new Ingredient("Beef Eye Round Steak", MeasurementUnit.KG, 10.0, 10.0, "");
		meatAndPoultry1.setCategory(category404);
		meatAndPoultry1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgBeefEyeRoundSteak.jpg");
		Ingredient meatAndPoultry2 = new Ingredient("Rib Eye Steak", MeasurementUnit.KG, 10.0, 16.0 , "");
		meatAndPoultry2.setCategory(category404);
		meatAndPoultry2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgRibEyeSteak.jpg");
		Ingredient meatAndPoultry3 = new Ingredient("Sweet Italian Pork Sausage", MeasurementUnit.KG, 10.0, 22.0, "");
		meatAndPoultry3.setCategory(category404);
		meatAndPoultry3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgSweetItalianPorkSausage.jpg");
		Ingredient meatAndPoultry4 = new Ingredient("Pork Bacon", MeasurementUnit.KG, 10.0, 22.0, "");
		meatAndPoultry4.setCategory(category404);
		meatAndPoultry4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgPorkBacon.jpg");

		Ingredient vegetables1 = new Ingredient("Sweet Cherry Tomatoes", MeasurementUnit.KG, 10.0, 2.0, "");
		vegetables1.setCategory(category401);
		vegetables1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgSweetCherryTomatoes.jpg");
		Ingredient vegetables2 = new Ingredient("Campari Tomatoes", MeasurementUnit.KG, 10.0, 2.0, "");
		vegetables2.setCategory(category401);
		vegetables2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgCampariTomatoes.jpg");
		Ingredient vegetables3 = new Ingredient("Baby Bella Mushrooms", MeasurementUnit.KG, 10.0, 1.0, "");
		vegetables3.setCategory(category401);
		vegetables3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgBabyBellaMushrooms.jpg");
		Ingredient vegetables4 = new Ingredient("White Mushrooms", MeasurementUnit.KG, 10.0, 1.0, "");
		vegetables4.setCategory(category401);
		vegetables4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgWhiteMushrooms.jpg");
		Ingredient vegetables5 = new Ingredient("Garlic", MeasurementUnit.KG, 10.0, 2.0, "");
		vegetables5.setCategory(category401);
		vegetables5.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgGarlic.jpg");
		Ingredient vegetables6 = new Ingredient("Red Onion", MeasurementUnit.KG, 10.0, 1.0, "");
		vegetables6.setCategory(category401);
		vegetables6.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgRedOnion.jpg");
		Ingredient vegetables7 = new Ingredient("Yellow Onion", MeasurementUnit.KG, 10.0, 1.0, "");
		vegetables7.setCategory(category401);
		vegetables7.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgYellowOnion.jpg");
		Ingredient vegetables8 = new Ingredient("Oregano", MeasurementUnit.KG, 10.0, 2.0, "");
		vegetables8.setCategory(category401);
		vegetables8.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgOregano.jpg");

		Ingredient dairyFoods1 = new Ingredient("Swiss Emmental", MeasurementUnit.KG, 10.0, 16.0, "");
		dairyFoods1.setCategory(category406);
		dairyFoods1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgSwissEmmental.jpg");
		Ingredient dairyFoods2 = new Ingredient("Grana Padano", MeasurementUnit.KG, 10.0, 18.0, "");
		dairyFoods2.setCategory(category406);
		dairyFoods2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgGranaPadano.jpg");
		Ingredient dairyFoods3 = new Ingredient("Pecorino Romano", MeasurementUnit.KG, 10.0, 12.0, "");
		dairyFoods3.setCategory(category406);
		dairyFoods3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgPecorinoRomano.jpg");
		Ingredient dairyFoods4 = new Ingredient("Fresh Mozzarella", MeasurementUnit.KG, 10.0, 9.0, "");
		dairyFoods4.setCategory(category406);
		dairyFoods4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgFreshMozzarella.jpg");
		Ingredient dairyFoods5 = new Ingredient("Gorgonzola", MeasurementUnit.KG, 10.0, 18.0, "");
		dairyFoods5.setCategory(category406);
		dairyFoods5.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgGorgonzola.jpg");

		Ingredient pantry1 = new Ingredient("Extra-Virgin Olive Oil", MeasurementUnit.L, 10.0, 11.0, "");
		pantry1.setCategory(category407);
		pantry1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgExtraVirginOliveOil.jpg");
		Ingredient pantry2 = new Ingredient("Table Salt", MeasurementUnit.KG, 10.0, 11.0, "");
		pantry2.setCategory(category407);
		pantry2.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgTableSalt.jpg");
		Ingredient pantry3 = new Ingredient("Whole Wheat Pre-Sifted Flour", MeasurementUnit.KG, 10.0, 5.0, "");
		pantry3.setCategory(category407);
		pantry3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgWholeWheatPreSiftedFlour.jpg");
		Ingredient pantry4 = new Ingredient("Unbleached All-Purpose Flour", MeasurementUnit.KG, 10.0, 4.0, "");
		pantry4.setCategory(category407);
		pantry4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgUnbleachedAllPurposeFlour.jpg");
		Ingredient pantry5 = new Ingredient("Canola Oil", MeasurementUnit.L, 10.0, 4.0, "");
		pantry5.setCategory(category407);
		pantry5.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgCanolaOil.jpg");
		Ingredient pantry6 = new Ingredient("White Flour", MeasurementUnit.KG, 10.0, 4.0, "");
		pantry6.setCategory(category407);
		pantry6.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgWhiteFlour.jpg");
		Ingredient pantry7 = new Ingredient("Whole Wheat Flour", MeasurementUnit.KG, 10.0, 6.0, "");
		pantry7.setCategory(category407);
		pantry7.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgWholeWheatFlour.jpg");
		Ingredient pantry8 = new Ingredient("Light Brown Sugar", MeasurementUnit.KG, 10.0, 2.0, "");
		pantry8.setCategory(category407);
		pantry8.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgLightBrownSugar.jpg");
		Ingredient pantry9 = new Ingredient("Dark Brown Sugar", MeasurementUnit.KG, 10.0, 2.0, "");
		pantry9.setCategory(category407);
		pantry9.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgDarkBrownSugar.jpg");
		
		Ingredient fruits1 = new Ingredient("Rainer Apple", MeasurementUnit.KG, 10.0, 2.4, "");
		fruits1.setCategory(category402);
		fruits1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/fruits/imgRainerApple.jpg");
		Ingredient fruits2 = new Ingredient("Lemon", MeasurementUnit.KG, 10.0, 1.5, "");
		fruits2.setCategory(category402);
		fruits2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/fruits/imgLemon.jpg");
		Ingredient fruits3 = new Ingredient("Golden Pineapple", MeasurementUnit.KG, 10.0, 6.2, "");
		fruits3.setCategory(category402);
		fruits3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/fruits/imgGoldenPineapple.jpg");
		
		Ingredient fishAndSeafood1 = new Ingredient("Fresh Tuna", MeasurementUnit.KG, 10.0, 6.4, "");
		fishAndSeafood1.setCategory(category405);
		fishAndSeafood1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/fish_and_seafood/imgFreshTuna.jpg");
		Ingredient fishAndSeafood2 = new Ingredient("Atlantic Salmon", MeasurementUnit.KG, 10.0, 9.2, "");
		fishAndSeafood2.setCategory(category405);
		fishAndSeafood2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/fish_and_seafood/imgAtlanticSalmon.jpg");
		Ingredient fishAndSeafood3 = new Ingredient("Sword Fish", MeasurementUnit.KG, 10.0, 10.8, "");
		fishAndSeafood3.setCategory(category405);
		fishAndSeafood3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/fish_and_seafood/imgSwordFish.jpg");
		Ingredient fishAndSeafood4 = new Ingredient("Salmon", MeasurementUnit.KG, 7.0, 10.8, "");
		fishAndSeafood4.setCategory(category405);
		fishAndSeafood4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/fish_and_seafood/imgSalmon.jpg");
		
		Beverage beverage1 = new Beverage("Coca-Cola Zero Sugar Sugar 250ml", MeasurementUnit.UNIT, 10.0, 2.20, "");
		beverage1.setCategory(category3);
		beverage1.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgCocaColaZeroSugar.jpg");
		Beverage beverage2 = new Beverage("Diet Coke Cola 250ml", MeasurementUnit.UNIT, 10.0, 1.80, "");
		beverage2.setCategory(category3);
		beverage2.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgDietCokeCola.jpg");
		Beverage beverage3 = new Beverage("Sprite Zero Lemon 2L", MeasurementUnit.UNIT, 10.0, 4.20, "");
		beverage3.setCategory(category3);
		beverage3.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgSpriteZeroLemon.jpg");
		Beverage beverage4 = new Beverage("Sprite Lemon 2L", MeasurementUnit.UNIT, 10.0, 3.80, "");
		beverage4.setCategory(category3);
		beverage4.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgSpriteLemon.jpg");
		Beverage beverage5 = new Beverage("Diet Pepsi 2L", MeasurementUnit.UNIT, 10.0, 4.40, "");
		beverage5.setCategory(category3);
		beverage5.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgDietPepsi.jpg");
		Beverage beverage6 = new Beverage("Pepsi 2L", MeasurementUnit.UNIT, 10.0, 4.20, "");
		beverage6.setCategory(category3);
		beverage6.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgPepsi.jpg");
		Beverage beverage7 = new Beverage("Coca Cola 250ml", MeasurementUnit.UNIT, 10.0, 1.69, "");
		beverage7.setCategory(category3);
		beverage7.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgCocaCola.jpg");
		Beverage beverage8 = new Beverage("Coca Cola Zero Sugar 2L", MeasurementUnit.UNIT, 10.0, 4.20, "");
		beverage8.setCategory(category3);
		beverage8.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgCocaColaZeroSugar2L.jpg");
		Beverage beverage9 = new Beverage("Coca Cola 2L", MeasurementUnit.UNIT, 10.0, 4.00, "");
		beverage9.setCategory(category3);
		beverage9.setImageUrl(
				"../../../../assets/images/items/products/components/beverages/imgCocaCola2L.jpg");

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
		
		itemService.insertItem(fruits1);
		itemService.insertItem(fruits2);
		itemService.insertItem(fruits3);
		
		itemService.insertItem(fishAndSeafood1);
		itemService.insertItem(fishAndSeafood2);
		itemService.insertItem(fishAndSeafood3);
		itemService.insertItem(fishAndSeafood4);
		
		
		itemService.insertItem(beverage1);
		itemService.insertItem(beverage2);
		itemService.insertItem(beverage3);
		itemService.insertItem(beverage4);
		itemService.insertItem(beverage5);
		itemService.insertItem(beverage6);
		itemService.insertItem(beverage7);
		itemService.insertItem(beverage8);
		itemService.insertItem(beverage9);

		/*-------------------------------------------*/

		/*
		 * Grilled Thin-Crust
		 * 	- Unbleached All-Purpose Flour
		 *  - Salt
		 */
		RecipeLine recipeLine10101 = new RecipeLine(.170);
		recipeLine10101.setComponent(pantry4);
		RecipeLine recipeLine10102 = new RecipeLine(.10);
		recipeLine10102.setComponent(pantry2);

		Recipe recipe101 = new Recipe("Grilled Thin-Crust", MeasurementUnit.UNIT, 10.0, "Grilled Thin-Crust recipe");
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
		RecipeLine recipeLine10201 = new RecipeLine(.280);
		recipeLine10201.setComponent(pantry3);
		RecipeLine recipeLine10202 = new RecipeLine(.10);
		recipeLine10202.setComponent(pantry5);
		RecipeLine recipeLine10203 = new RecipeLine(.10);
		recipeLine10203.setComponent(pantry2);
		RecipeLine recipeLine10204 = new RecipeLine(.14);
		recipeLine10204.setComponent(pantry8);

		Recipe recipe102 = new Recipe("Grilled Whole-Wheat Crust", MeasurementUnit.UNIT, 10.0, "Grilled Whole-Wheat Crust recipe");
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

		/*
		 * Tomato Sauce
		 *  - Tomato
		 *  - Garlic
		 *  - Olive Oil
		 *  - Oregano
		 *  - Salt)
		 */
		RecipeLine recipeLine10301 = new RecipeLine(.300);
		recipeLine10301.setComponent(vegetables1);
		RecipeLine recipeLine10302 = new RecipeLine(.50);
		recipeLine10302.setComponent(vegetables5);
		RecipeLine recipeLine10303 = new RecipeLine(.10);
		recipeLine10303.setComponent(pantry1);
		RecipeLine recipeLine10304 = new RecipeLine(.30);
		recipeLine10304.setComponent(vegetables8);
		RecipeLine recipeLine10305 = new RecipeLine(.10);
		recipeLine10305.setComponent(pantry2);
		
		Recipe recipe103 = new Recipe("Tomato Sauce", MeasurementUnit.UNIT, 10.0, "Tomato Sauce recipe");
		recipe103.setCategory(category6);
		recipe103.addLine(recipeLine10301);
		recipe103.addLine(recipeLine10302);
		recipe103.addLine(recipeLine10303);
		recipe103.addLine(recipeLine10304);
		recipe103.addLine(recipeLine10305);
		
		itemService.insertItem(recipe103);
		recipeLineService.insertRecipeLine(recipeLine10301);
		recipeLineService.insertRecipeLine(recipeLine10302);
		recipeLineService.insertRecipeLine(recipeLine10303);
		recipeLineService.insertRecipeLine(recipeLine10304);
		recipeLineService.insertRecipeLine(recipeLine10305);
		
		/* Grilled Thin-Crust Sweet Italian Sausage Pizza
		 * 	- Grilled Thin-Crust (Unbleached All-Purpose Flour, Salt)
		 *  - Tomato Sauce (Garlic, Olive Oil, Oregano, Tomato, Salt)
		 *  - Pecorino-Romano Cheese
		 *  - Fresh Mozzarella Cheese
		 *  - Italian Pork Sausage
		 */
		RecipeLine recipeLine10401 = new RecipeLine(1.0);
		recipeLine10401.setComponent(recipe101);
		RecipeLine recipeLine10402 = new RecipeLine(1.0);
		recipeLine10402.setComponent(recipe103);
		RecipeLine recipeLine10403 = new RecipeLine(.300);
		recipeLine10403.setComponent(dairyFoods3);
		RecipeLine recipeLine10404 = new RecipeLine(.280);
		recipeLine10404.setComponent(dairyFoods4);
		RecipeLine recipeLine10405 = new RecipeLine(.120);
		recipeLine10405.setComponent(meatAndPoultry3);
		
		Recipe recipe104 = new Recipe("Grilled Thin-Crust Sweet Italian Sausage Pizza", MeasurementUnit.UNIT, 10.0,
				"Grilled Thin-Crust Sweet Italian Sausage Pizza recipe");
		recipe104.setCategory(category1);
		recipe104.setImageUrl("../../../../assets/images/items/products/recipes/pizzas/imgGrilledThinCrustSweetItalianSausagePizza.jpg");
		recipe104.addLine(recipeLine10401);
		recipe104.addLine(recipeLine10402);
		recipe104.addLine(recipeLine10403);
		recipe104.addLine(recipeLine10404);
		recipe104.addLine(recipeLine10405);
		
		itemService.insertItem(recipe104);
		recipeLineService.insertRecipeLine(recipeLine10401);
		recipeLineService.insertRecipeLine(recipeLine10402);
		recipeLineService.insertRecipeLine(recipeLine10403);
		recipeLineService.insertRecipeLine(recipeLine10404);
		recipeLineService.insertRecipeLine(recipeLine10405);
		
		/*
		 * Grilled Whole-Wheat Crust Mozzarella Pizza
		 * 	- Grilled Whole-Wheat Crust (Whole Wheat Flour, Canola Oil, Salt, Brown Sugar)
		 *  - Tomato Sauce (Garlic, Olive Oil, Oregano, Tomato, Salt)
		 *  - Pecorino-Romano Cheese
		 *  - Fresh Mozzarella Cheese
		 */
		RecipeLine recipeLine10501 = new RecipeLine(1.0);
		recipeLine10501.setComponent(recipe102);
		RecipeLine recipeLine10502 = new RecipeLine(1.0);
		recipeLine10502.setComponent(recipe103);
		RecipeLine recipeLine10503 = new RecipeLine(.220);
		recipeLine10503.setComponent(dairyFoods3);
		RecipeLine recipeLine10504 = new RecipeLine(.420);
		recipeLine10504.setComponent(dairyFoods4);
		
		Recipe recipe105 = new Recipe("Grilled Whole-Wheat Crust Mozzarella Pizza", MeasurementUnit.UNIT, 10.0,
				"Grilled Whole-Wheat Crust Mozzarella Pizza recipe");
		recipe105.setCategory(category1);
		recipe105.setImageUrl("../../../../assets/images/items/products/recipes/pizzas/imgGrilledWholeWheatCrustMozzarellaPizza.jpg");
		recipe105.addLine(recipeLine10501);
		recipe105.addLine(recipeLine10502);
		recipe105.addLine(recipeLine10503);
		recipe105.addLine(recipeLine10504);
		
		itemService.insertItem(recipe105);
		recipeLineService.insertRecipeLine(recipeLine10501);
		recipeLineService.insertRecipeLine(recipeLine10502);
		recipeLineService.insertRecipeLine(recipeLine10503);
		recipeLineService.insertRecipeLine(recipeLine10504);
		
		/*
		 * Grilled Thin-Crust Onion, Gorgonzola and Bacon Pizza
		 *  - Grilled Thin-Crust (Unbleached All-Purpose Flour, Salt)
		 *  - Gorgonzola Cheese
		 *  - Fresh Mozzarella Cheese
		 *  - Pork Bacon
		 *  - Onion
		 */
		RecipeLine recipeLine10601 = new RecipeLine(1.0);
		recipeLine10601.setComponent(recipe101);
		RecipeLine recipeLine10602 = new RecipeLine(.140);
		recipeLine10602.setComponent(dairyFoods5);
		RecipeLine recipeLine10603 = new RecipeLine(.280);
		recipeLine10603.setComponent(dairyFoods4);
		RecipeLine recipeLine10604 = new RecipeLine(.220);
		recipeLine10604.setComponent(meatAndPoultry4);
		RecipeLine recipeLine10605 = new RecipeLine(.30);
		recipeLine10605.setComponent(vegetables6);
		
		Recipe recipe106 = new Recipe("Grilled Thin-Crust Onion, Gorgonzola and Bacon Pizza", MeasurementUnit.UNIT, 10.0,
				"Grilled Thin-Crust Onion, Gorgonzola and Bacon Pizza recipe");
		recipe106.setCategory(category1);
		recipe106.setImageUrl("../../../../assets/images/items/products/recipes/pizzas/imgGrilledThinCrustOnionGorgonzolaAndBaconPizza.jpg");
		recipe106.addLine(recipeLine10601);
		recipe106.addLine(recipeLine10602);
		recipe106.addLine(recipeLine10603);
		recipe106.addLine(recipeLine10604);
		recipe106.addLine(recipeLine10605);
		
		itemService.insertItem(recipe106);
		recipeLineService.insertRecipeLine(recipeLine10601);
		recipeLineService.insertRecipeLine(recipeLine10602);
		recipeLineService.insertRecipeLine(recipeLine10603);
		recipeLineService.insertRecipeLine(recipeLine10604);
		recipeLineService.insertRecipeLine(recipeLine10605);
		
		/*-------------------------------------------*/
		
		// Remove unused categories
		categoryService.deleteCategoryById(category403.getId());
	}
	
	private void updateConflictIngredients() {
		Ingredient redOnion = ingredientService.findIngredientByName("Red Onion").get();
		Ingredient yellowOnion = ingredientService.findIngredientByName("Yellow Onion").get();
		Ingredient lightBrownSugar = ingredientService.findIngredientByName("Light Brown Sugar").get();
		Ingredient darkBrownSugar = ingredientService.findIngredientByName("Dark Brown Sugar").get();
		Ingredient babyBellaMushrooms = ingredientService.findIngredientByName("Baby Bella Mushrooms").get();
		
		redOnion.addConflictIngredient(lightBrownSugar);
		redOnion.addConflictIngredient(darkBrownSugar);
		redOnion.addConflictIngredient(babyBellaMushrooms);
		yellowOnion.addConflictIngredient(lightBrownSugar);
		yellowOnion.addConflictIngredient(darkBrownSugar);
		yellowOnion.addConflictIngredient(babyBellaMushrooms);
		
		ingredientService.updateIngredient(redOnion);
		ingredientService.updateIngredient(yellowOnion);
		ingredientService.updateIngredient(lightBrownSugar);
		ingredientService.updateIngredient(darkBrownSugar);
		ingredientService.updateIngredient(babyBellaMushrooms);
	}
	
	private void updateCatalogue() {
		Double increase = 50.0;
		Catalogue catalogue = new Catalogue(Month.APRIL, 2019, CatalogueStatus.ACTIVE);
		List<Category> categories = categoryService.findAllActiveFrontOfficeCategories();
		
		for(Category category: categories) {
			for(Item item: category.getItems()) {
				CatalogueItem catalogueLine = new CatalogueItem(Double.parseDouble(String.format("%.2f", item.getStockPrice() * (1 + increase/100))));
				catalogueLine.setItem(item);
				catalogue.addLine(catalogueLine);
			}
		}
		
		catalogueService.insertCatalogue(catalogue);
		
		for(CatalogueItem catalogueLine: catalogue.getCatalogueItems()) {
			catalogueLineService.insertCatalogueItem(catalogueLine);
		}
		
	}
	
	private void updateLocation() {
		Address address1 = new AddressBuilder("Alexandru cel Mare", 21).setZipCode(270123).getAddress();
		address1.setCity(cityByName("Iasi"));
		Address address2 = new AddressBuilder("Marinelor", 51).setZipCode(280129).getAddress();
		address2.setCity(cityByName("Pascani"));
		
		addressService.insertAddress(address1);
		addressService.insertAddress(address2);		
		
		Location location1 = new Location("Pizzetta Iasi", "Pizzetta restaurant in Iasi");
		location1.setAddress(address1);
		Location location2 = new Location("Pizzetta Pascani", "Pizzetta restaurant in Pascani");
		location2.setAddress(address2);
		
		locationService.insertLocation(location1);
		locationService.insertLocation(location2);
	}

	private void updateJob() {
		List<Location> locations = locationService.findAllLocations(); 
		
		Job waiter = new Job("Waiter", "Part-time", "The right Waiter/Waitress uplifts the dining "
				+ "experience for customers. We are looking for someone who will have the patience,"
				+ " personality and perseverance to thrive in this role. "
				+ "Keep in mind that Waiter/Waitress duties may require working in shifts and/or"
				+ " occasionally during weekends and holidays.\r\n" + 
				"\r\n" + 
				"Ultimately, it is the duty of our Waiters/Waitresses to provide an excellent "
				+ "overall dining experience for our guests.", 
				"../../../../assets/images/jobs/imgWaiter.jpg", "Iasi",
				Arrays.asList("Math Skills ",
						"Previous experience as a waiter ",
						"Hands-on experience with cash register and ordering information system (e.g. Revel POS or Toast POS)",
						"Attentiveness and patience for customers", "Flexibility to work in shifts",
						"High school diploma; food safety training is a plus"),
				Arrays.asList("Serve food to clients ",
						"Clean tables",
						"Follow all relevant health department regulations ",
						"Deliver checks and collect bill payments", "Offer menu recommendations upon request",
						"Present menu and provide detailed information when asked (e.g. about portions, ingredients or potential food allergies)"));
		waiter.setLocation(locations.get(0));
		
		Job manager = new Job("Manager", "Full-time", "Manager responsibilities include formulating"
				+ " overall strategy, managing people and establishing policies. "
				+ "To be successful in this role, you should be a thoughtful leader and a "
				+ "confident decision-maker, helping our people develop and be productive, "
				+ "while ensuring our profits are on the rise.\r\n" + 
				"\r\n" + 
				"Ultimately, you’ll help our company grow and thrive.", 
				"../../../../assets/images/jobs/imgManager.jpg", "Bacau",
				Arrays.asList("Proven experience as a General Manager or similar executive role ",
						"Experience in planning and budgeting ",
						"Strong analytical ability",
						"Knowledge of business process and functions (finance, HR, procurement, operations etc.)",
						"Problem-solving aptitude",
						"BSc/BA in Business or relevant field; MSc/MA is a plus"),
				Arrays.asList("Oversee day-to-day operations ",
						"Design strategy and set goals for growth",
						"Maintain budgets and optimize expenses",
						"Set policies and processes",
						"Ensure employees work productively and develop professionally",
						"Cooperate with the rest of the staff",
						"Provide solutions to issues (e.g. profit decline, employee conflicts,"
						+ " loss of business to competitors)"));
		manager.setLocation(locations.get(1));

		Job sysAdmin = new Job("System Administrator", "Full-time", "Resourcefulness is a "
				+ "necessary skill in this role. You should be able to diagnose and resolve "
				+ "problems quickly. You should also have the patience to communicate with a"
				+ " variety of interdisciplinary teams and users.\r\n" + 
				"\r\n" + 
				"Your goal will be to ensure that our technology infrastructure runs smoothly "
				+ "and efficiently.", 
				"../../../../assets/images/jobs/imgSysAdmin.jpg", "Iasi",
				Arrays.asList("Proven experience as a System Administrator, Network Administrator or similar role ",
						"Experience with databases, networks (LAN, WAN) and patch management ",
						"Knowledge of system security (e.g. intrusion detection systems) and data backup/recovery",
						"Familiarity with various operating systems and platforms", "Problem-solving aptitude",
						"BSc/Ba in Information Technology, Computer Science or a related discipline; professional certification (e.g. Microsoft Certified Systems Administrator (MCSA)) is a plus"),
				Arrays.asList("Install and configure software and hardware ",
						"Manage network servers and technology toolss",
						"Set up accounts and workstations",
						"Troubleshoot issues and outages",
						"Monitor performance and maintain systems according to requirements",
						"Upgrade systems with new releases and models",
						"Develop expertise to train staff on new technologies",
						"Build an internal wiki with technical documentation, manuals and IT policies"));
		sysAdmin.setLocation(locations.get(0));

		Job operator = new Job("Operator", "Full-time", "A great operator is reliable and "
				+ "able to work with attention to detail and safety standards. On-the-job training"
				+ " is a good way to discover how to do the job better, so you should have"
				+ " willingness to learn and improve."
				+ " Being a team player is essential since all tasks will require close "
				+ "collaboration with co-workers.", 
				"../../../../assets/images/jobs/imgOperator.jpg", "Vaslui",
				Arrays.asList("Great communication skills ",
						"Great English skills ",
						"Knowledge of business process and functions (finance, HR, procurement, operations etc.)",
						"Proeficient with handling software programs",
						"Problem-solving aptitude",
						"BSc/BA in Business or relevant field; MSc/MA is a plus"),
				Arrays.asList("Perform day-to-day operations ",
						"Keep in touch with the administrators",
						"Cooperate with the rest of the staff"));
		operator.setLocation(locations.get(0));

		Job janitor = new Job("Janitor", "Full-time", "Your goal is to keep our "
				+ "building in a clean and orderly condition.n", 
				"../../../../assets/images/jobs/imgJanitor.jpg", "Vaslui",
				Arrays.asList("Proven working experience as a janitor ",
						"Ability to handle heavy equipment and machinery",
						"Knowledge of cleaning chemicals and supplies",
						"Familiarity with Material Safety Data Sheets",
						"Integrity and ability to work independently",
						"High school degree"),
				Arrays.asList(
						"Clean and supply designated building areas (dusting, sweeping, vacuuming, mopping, cleaning ceiling vents, restroom cleaning etc) ",
						"Stock and maintain supply rooms",
						"Perform and document routine inspection and maintenance activities",
						"Notify management of occurring deficiencies or needs for repairs",
						"Make adjustments and minor repairs",
						"Follow all health and safety regulations"));
		janitor.setLocation(locations.get(1));

		Job frontendDev = new Job("Front End Developer", "Full-time", "If you’re interested in "
				+ "creating a user-friendly environment by writing code and moving forward "
				+ "in your career, then this job is for you. We expect you to be a tech-savvy "
				+ "professional, who is curious about new digital technologies and aspires "
				+ "to combine usability with visual design.\r\n" + 
				"\r\n" + 
				"Ultimately, you should be able to create a "
				+ "functional and attractive digital environment for our company,"
				+ " ensuring great user experience.", 
				"../../../../assets/images/jobs/imgFrontEndDev.jpg", "Iasi",
				Arrays.asList("Proven work experience as a Front-end developer ",
						"Hands on experience with markup languages ",
						"Experience with JavaScript, CSS and jQuery",
						"In-depth understanding of the entire web development process (design, development and deployment)",
						"Understanding of layout aesthetics",
						"Knowledge of SEO principles",
						"Familiarity with software like Adobe Suite, Photoshop and content management systems",
						"BSc degree in Computer Science or relevant field"),
				Arrays.asList("Maintain and improve website ",
						"Use markup languages like HTML to create user-friendly web pages",
						"Optimize applications for maximum speed",
						"Design mobile-based features",
						"Manage cutting-edge technologies to improve legacy applications",
						"Collaborate with back-end developers and web designers to improve usability",
						"Get feedback from, and build solutions for, users and customers",
						"Write functional requirement documents and guides",
						"Create quality mockups and prototypes",
						"Ensure high quality graphic standards and brand consistency",
						"Stay up-to-date on emerging technologies"));
		frontendDev.setStatus(Status.INACTIVE);
		frontendDev.setLocation(locations.get(0));

		Job backendDev = new Job("Back End Developer", "Full-time", "If you have "
				+ "excellent programming skills and a passion for developing applications"
				+ " or improving existing ones, we would like to meet you. As a Back-end developer, "
				+ "you’ll work closely with our engineers to ensure system consistency and "
				+ "improve user experience.\r\n" + 
				"\r\n" + 
				"Ultimately, you should be able to develop and maintain functional"
				+ " and stable web applications to meet our company’s needs.", 
				"../../../../assets/images/jobs/imgBackEndDev.jpg", "Iasi",
				Arrays.asList("Proven work experience as a Back-end developer ",
						"In-depth understanding of the entire web development process"
								+ " (design, development and deployment) ",
						"Hands on experience with programming languages like Java, Ruby, PHP and Python",
						"Working knowledge of CMS framework",
						"Familiarity with front-end languages (e.g. HTML, JavaScript and CSS)",
						"Teamwork skills with a problem-solving attitude",
						"BSc degree in Computer Science or relevant field"),
				Arrays.asList("Participate in the entire application lifecycle, focusing on coding and debugging ",
						"Write clean code to develop functional web applications",
						"Troubleshoot and debug applications", "Perform UI tests to optimize performance",
						"Manage cutting-edge technologies to improve legacy applications",
						"Gather and address technical and design requirements",
						"Provide training and support to internal teams",
						"Build reusable code and libraries for future use",
						"Liaise with developers, designers and system administrators to identify new features",
						"Follow emerging technologies"));
		backendDev.setStatus(Status.INACTIVE);
		backendDev.setLocation(locations.get(0));

		Job deliveryDriver = new Job("Delivery Driver", "Full-time", "The Delivery Driver is responsible"
				+ "with distributing products promptly to our customers. You will represent our company "
				+ "in a professional and cost-effective manner to increase our profitability"
				+ " and customer satisfaction.", 
				"../../../../assets/images/jobs/imgDelivery.jpg", "Bacau",
				Arrays.asList("Proven working experience as a Delivery Driver ",
						"Valid professional driver’s license ",
						"Ability to operate forklifts and tractors in a variety of weather and traffic conditions",
						"Excellent organizational and time management skills",
						"Good driving record with no traffic violations",
						"High school degree"),
				Arrays.asList("Deliver a wide variety of items to different addresses and through different routes ",
						"Follow routes and time schedule",
						"Collect payments",
						"Load, unload, prepare, inspect and operate a delivery vehicle",
						"Ask for feedback on provided services and resolve clients’ complaints",
						"Inform customers about new products and services"));
		deliveryDriver.setLocation(locations.get(0));

		Job economist = new Job("Economist", "Full-time", 
				"an Economist's responsibilities include budgeting, "
				+ "managing tax payments and performing internal audits. "
				+ "You will act as a consultant for senior managers, conducting "
				+ "cost and revenues analyses. To be qualified for this role, "
				+ "you should have a degree in Accounting and relevant work experience.\r\n" + 
				"\r\n" + 
				"Ultimately, you will ensure all our accounting transactions comply"
				+ " with the law and support our company’s investments.", 
				"../../../../assets/images/jobs/imgEconomist.jpg", "Iasi",
				Arrays.asList("Proven experience as an economist",
						"Excellent communication skills",
						"Excellent knowledge of accounting regulations and practices",
						"In-depth experience in risk analysis, budgeting and forecasting",
						"Excellent organizational and time management skills",
						"Proficient in MS Office (especially Excel) and finance software",
						"BSc/BA in Accounting, Finance or related field; professional certification (e.g. CPA) is a plus"),
				Arrays.asList("Gather financial data and ledgers ",
						"Manage periodical reporting",
						"Consolidate and analyze financial statements and results",
						"Prepare budgets and monitor expenditures",
						"Handle monthly, quarterly and annual closings",
						"Oversee external and internal audits"));
		economist.setLocation(locations.get(0));

		Job cook = new Job("Cook", "Part-time", "An excellent cook"
				+ " must be able to follow instructions in cooking and delivering "
				+ "well-prepared meals. They must be deft in moving around the"
				+ " kitchen and apt in multi-tasking. Experience in using various "
				+ "ingredients and cooking techniques is also important.\r\n" + 
				"\r\n" + 
				"The goal is to help preserve and enhance our reputation so "
				+ "we can expand our clientele.", 
				"../../../../assets/images/jobs/imgCook.jpg", "Suceava",
				Arrays.asList("Proven experience as cook",
						"Ability to work in a team ",
						"Experience in using cutting tools, cookware and bakeware",
						"Knowledge of various cooking procedures and methods (grilling, baking, boiling etc.)",
						"Very good communication skills",
						"Excellent physical condition and stamina",
						"High school diploma or equivalent; Diploma from a culinary school will be an advantage"),
				Arrays.asList("Set up workstations with all needed ingredients and cooking equipment ",
						"Prepare ingredients to use in cooking (chopping and peeling vegetables, cutting meat etc.)",
						"Cook food in various utensils or grillers",
						"Check food while cooking to stir or turn",
						"Ensure great presentation by dressing dishes before they are served",
						"Keep a sanitized and orderly environment in the kitchen",
						"Ensure all food and other items are stored properly",
						"Check quality of ingredients",
						"Monitor stock and place orders when there are shortages"));
		cook.setLocation(locations.get(1));

		Job custRel = new Job("Customer Relations Representant", "Full-time", 
				"Customer Relations Specialist responsibilities include resolving customer queries, "
				+ "recommending solutions and guiding product users through features and functionalities."
				+ "To be successful in this role, you should be an excellent communicator who’s able to"
				+ " earn our clients’ trust. You should also be familiar with help desk software.\r\n" + 
				"\r\n" + 
				"Ultimately, you will help establish our reputation as a company that offers excellent"
				+ " customer support during all sales and after-sales procedures.", 
				"../../../../assets/images/jobs/imgCustomerSupport.jpg", "Botosani",
				Arrays.asList("Experience as a Customer Relations Specialist or similar CS role ",
						"Familiarity with our industry is a plus ",
						"Understanding of how CRM systems work",
						"Experience using help desk software and remote support tools",
						"Ability to work in a team",
						"Very good communication skills",
						"Multi-tasking abilities",
						"BSc in Information Technology or relevant diploma"),
				Arrays.asList("Respond to customer queries in a timely and accurate way, via phone, email or chat ",
						"Identify customer needs and help customers use specific features",
						"Monitor customer complaints on social media and reach out to provide assistance",
						"Share feature requests and effective workarounds with team member"));
		custRel.setLocation(locations.get(0));

		jobService.insertJob(waiter);
		jobService.insertJob(manager);
		jobService.insertJob(sysAdmin);
		jobService.insertJob(operator);
		jobService.insertJob(janitor);
		jobService.insertJob(frontendDev);
		jobService.insertJob(backendDev);
		jobService.insertJob(deliveryDriver);
		jobService.insertJob(economist);
		jobService.insertJob(cook);
		jobService.insertJob(custRel);
	}
	
	private void updateFeedback() {
		Feedback feedback1 = new Feedback("Maxim", "maxim96@gmail.com", "Good job",
				"Hi. I appreciate very much your work. The website looks great. Keep it up.");
		Feedback feedback2 = new Feedback("Alina", "marandiuc.alina@yahoo.com", "Great pizza",
				"Ordered a pizza from your web site and it was very delicious."
				+ "From now on I'll order only from you!!");
		Feedback feedback3 = new Feedback("George", "george.george@gmail.com", "Other languages",
				"Hi. I think it would be great to allow the user to choose multiple"
				+ "languages as not everyone know english. Besides that, great jobs,"
				+ "the web site looks amazing!!");
		
		feedbackService.insertFeedback(feedback1);
		feedbackService.insertFeedback(feedback2);
		feedbackService.insertFeedback(feedback3);
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
