package sms.utils;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import sms.entities.account.IAccountService;
import sms.entities.category.Category;
import sms.entities.category.ICategoryService;
import sms.entities.customer.Customer;
import sms.entities.employee.Admin;
import sms.entities.employee.Employee;
import sms.entities.employee.IRight;
import sms.entities.employee.OperatorProducts;
import sms.entities.item.IItemService;
import sms.entities.item.component.Ingredient;
import sms.entities.location.Address;
import sms.entities.location.AddressBuilder;
import sms.entities.location.City;
import sms.entities.location.County;
import sms.entities.location.IAddressService;
import sms.entities.location.ICityService;
import sms.entities.location.ICountyService;
import sms.entities.subscription.ISubscriptionService;
import sms.entities.subscription.Subscription;
import sms.enums.EmployeeStatus;
import sms.enums.SubscriptionType;

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
	private DisplayData displayData;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isDBEmpty()) {
			loadData();
		}
	}

	private void loadData() {
		displayData.printInfo("Starting data loading...");

		County county1 = new County("Iasi", "Moldova");
		County county2 = new County("Bacau", "Moldova");
		County county3 = new County("Botosani", "Moldova");

		City city1 = new City("Iasi");
		city1.setCounty(county1);
		City city2 = new City("Pascani");
		city2.setCounty(county1);
		City city3 = new City("Targu Frumos");
		city3.setCounty(county1);

		City city4 = new City("Bacau");
		city4.setCounty(county2);
		City city5 = new City("Slanic Moldova");
		city5.setCounty(county2);
		City city6 = new City("Ghimes");
		city6.setCounty(county2);

		City city7 = new City("Botosani");
		city7.setCounty(county3);
		City city8 = new City("Darabani");
		city8.setCounty(county3);
		City city9 = new City("Flamanzi");
		city9.setCounty(county3);

		Address address1 = new AddressBuilder("Alexandru cel Bun", 51).setApartamentNr(15).setZipCode(270123)
				.getAddress();
		address1.setCity(city1);
		Address address2 = new AddressBuilder("Marinelor", 11).setZipCode(280129).getAddress();
		address2.setCity(city2);
		Address address3 = new AddressBuilder("Marinelor", 27).setBuildingNr("B").setZipCode(280129).getAddress();
		address3.setCity(city2);
		Address address4 = new AddressBuilder("Marin Cordoba", 100).setZipCode(290111).getAddress();
		address4.setCity(city3);
		Address address5 = new AddressBuilder("Cosmopolitan", 127).setBuildingNr("3").setApartamentNr(205)
				.setFloorNr("2").setZipCode(270123).getAddress();
		address5.setCity(city1);
		Address address6 = new AddressBuilder("Cosmopolitan", 129).setZipCode(270123).getAddress();
		address6.setCity(city1);
		Address address7 = new AddressBuilder("Cereselor", 12).setApartamentNr(115).setFloorNr("GF").setZipCode(270323)
				.getAddress();
		address7.setCity(city1);
		Address address8 = new AddressBuilder("Independentei", 30).setZipCode(278012).getAddress();
		address8.setCity(city1);

		Subscription subscription1 = new Subscription(SubscriptionType.DISCOUNTS);
		Subscription subscription2 = new Subscription(SubscriptionType.NEW_PRODUCTS);
		Subscription subscription3 = new Subscription(SubscriptionType.PROMOTIONAL);

		Customer customer1 = new Customer("Alex", "alex123", "Alexandru", "alex_cozma@gmail.com", "0748974419");
		customer1.setAddress(address1);
		customer1.getDeliveryAddresses().add(address5);
		customer1.getDeliveryAddresses().add(address6);
		customer1.getSubscriptions().add(subscription1);
		customer1.getSubscriptions().add(subscription3);
		Customer customer2 = new Customer("Buzzy23", "password", "Maxim", "max_96@yahoo.com", "0742114411");
		customer2.setAddress(address2);
		Customer customer3 = new Customer("Alinacika", "saalina", "Alina", "alina_sandra@gmail.com", "0742271410");
		customer3.setAddress(address3);
		customer3.getDeliveryAddresses().add(address4);
		customer3.getSubscriptions().add(subscription1);
		customer3.getSubscriptions().add(subscription2);
		customer3.getSubscriptions().add(subscription3);

		// ------------------------------------------------------------------- //

		IRight admin = new Admin();
		IRight operatorProducts = new OperatorProducts();

		Employee employee1 = new Employee("viorel", "viorel123", "Viorel", "viorelsolonaru@gmail.com", "0748974417",
				EmployeeStatus.SENIOR, admin);
		employee1.setAddress(address7);
		Employee employee2 = new Employee("andrei", "andrei123", "Andrei", "andreihumulescu@gmail.com", "0721314417",
				EmployeeStatus.INTERNSHIP, operatorProducts);
		employee2.setAddress(address8);
		
		// ------------------------------------------------------------------- //
		
		Category category1 = new Category("Pizzas", new Date(System.currentTimeMillis()),
				"Pizzas category");
		Category category2 = new Category("Deserts", new Date(System.currentTimeMillis()),
				"Deserts category");
		Category category3 = new Category("Beverages", new Date(System.currentTimeMillis()),
				"Beverages category");
		Category category4 = new Category("Ingredients", new Date(System.currentTimeMillis()),
				"Ingredients category");
		Category category401 = new Category("Vegetables", new Date(System.currentTimeMillis()),
				"Vegetables category");
		category401.setParentCategory(category4);
		Category category402 = new Category("Fruits", new Date(System.currentTimeMillis()),
				"Fruits category");
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
		
		
		Ingredient meatAndPoultry1 = new Ingredient("Beef Eye Round Steak", 10, new Date(System.currentTimeMillis()), "");
		meatAndPoultry1.setCategory(category404);
		meatAndPoultry1.setImageUrl("../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgBeefEyeRoundSteak.jpg");
		Ingredient meatAndPoultry2 = new Ingredient("Rib Eye Steak", 16, new Date(System.currentTimeMillis()), "");
		meatAndPoultry2.setCategory(category404);
		meatAndPoultry2.setImageUrl("../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgRibEyeSteak.jpg");
		
		Ingredient vegetables1 = new Ingredient("Sweet Cherry Tomatoes", 2, new Date(System.currentTimeMillis()), "");
		vegetables1.setCategory(category401);
		vegetables1.setImageUrl("../../../../assets/images/items/products/components/ingredients/vegetables/imgSweetCherryTomatoes.jpg");
		Ingredient vegetables2 = new Ingredient("Campari Tomatoes", 2, new Date(System.currentTimeMillis()), "");
		vegetables2.setCategory(category401);
		vegetables2.setImageUrl("../../../../assets/images/items/products/components/ingredients/vegetables/imgCampariTomatoes.jpg");
		Ingredient vegetables3 = new Ingredient("Baby Bella Mushrooms", 1, new Date(System.currentTimeMillis()), "");
		vegetables3.setCategory(category401);
		vegetables3.setImageUrl("../../../../assets/images/items/products/components/ingredients/vegetables/imgBabyBellaMushrooms.jpg");
		Ingredient vegetables4 = new Ingredient("White Mushrooms", 1, new Date(System.currentTimeMillis()), "");
		vegetables4.setCategory(category401);
		vegetables4.setImageUrl("../../../../assets/images/items/products/components/ingredients/vegetables/imgWhiteMushrooms.jpg");
		Ingredient vegetables5 = new Ingredient("Garlic", 2, new Date(System.currentTimeMillis()), "");
		vegetables5.setCategory(category401);
		vegetables5.setImageUrl("../../../../assets/images/items/products/components/ingredients/vegetables/imgGarlic.jpg");
		Ingredient vegetables6 = new Ingredient("Red Onion", 1, new Date(System.currentTimeMillis()), "");
		vegetables6.setCategory(category401);
		vegetables6.setImageUrl("../../../../assets/images/items/products/components/ingredients/vegetables/imgRedOnion.jpg");
		Ingredient vegetables7 = new Ingredient("Yellow Onion", 1, new Date(System.currentTimeMillis()), "");
		vegetables7.setCategory(category401);
		vegetables7.setImageUrl("../../../../assets/images/items/products/components/ingredients/vegetables/imgYellowOnion.jpg");
		
		Ingredient dairyFoods1 = new Ingredient("Swiss Emmental", 16, new Date(System.currentTimeMillis()), "");
		dairyFoods1.setCategory(category406);
		dairyFoods1.setImageUrl("../../../../assets/images/items/products/components/ingredients/dairy_foods/imgSwissEmmental.jpg");
		Ingredient dairyFoods2 = new Ingredient("Grana Padano", 18, new Date(System.currentTimeMillis()), "");
		dairyFoods2.setCategory(category406);
		dairyFoods2.setImageUrl("../../../../assets/images/items/products/components/ingredients/dairy_foods/imgGranaPadano.jpg");
		
		Ingredient grainsBeansAndNuts1 = new Ingredient("White Flour", 4, new Date(System.currentTimeMillis()), "");
		grainsBeansAndNuts1.setCategory(category403);
		grainsBeansAndNuts1.setImageUrl("../../../../assets/images/items/products/components/ingredients/grains_beans_and_nuts/imgWhiteFlour.jpg");
		Ingredient grainsBeansAndNuts2 = new Ingredient("Whole Wheat Flour", 6, new Date(System.currentTimeMillis()), "");
		grainsBeansAndNuts2.setCategory(category403);
		grainsBeansAndNuts2.setImageUrl("../../../../assets/images/items/products/components/ingredients/grains_beans_and_nuts/imgWholeWheatFlour.jpg");
		
		// ------------------------------------------------------------------- //
		// --------------------------- Persisting here ----------------------- //
		// ------------------------------------------------------------------- //

		countyService.insertCounty(county1);
		countyService.insertCounty(county2);
		countyService.insertCounty(county3);

		cityService.insertCity(city1);
		cityService.insertCity(city2);
		cityService.insertCity(city3);
		cityService.insertCity(city4);
		cityService.insertCity(city5);
		cityService.insertCity(city6);
		cityService.insertCity(city7);
		cityService.insertCity(city8);
		cityService.insertCity(city9);

		addressService.insertAddress(address1);
		addressService.insertAddress(address2);
		addressService.insertAddress(address3);
		addressService.insertAddress(address4);
		addressService.insertAddress(address5);
		addressService.insertAddress(address6);
		addressService.insertAddress(address7);
		addressService.insertAddress(address8);

		subscriptionService.insertSubscription(subscription1);
		subscriptionService.insertSubscription(subscription2);
		subscriptionService.insertSubscription(subscription3);

		accountService.insertAccount(customer1);
		accountService.insertAccount(customer2);
		accountService.insertAccount(customer3);

		// ------------------------------------------------------------------- //

		accountService.insertAccount(employee1);
		accountService.insertAccount(employee2);

		// ------------------------------------------------------------------- //
		
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
		
		// ------------------------------------------------------------------- //
		
		itemService.insertItem(meatAndPoultry1);
		itemService.insertItem(meatAndPoultry2);
		
		itemService.insertItem(vegetables1);
		itemService.insertItem(vegetables2);
		itemService.insertItem(vegetables3);
		itemService.insertItem(vegetables4);
		itemService.insertItem(vegetables5);
		itemService.insertItem(vegetables6);
		itemService.insertItem(vegetables7);
		
		itemService.insertItem(dairyFoods1);
		itemService.insertItem(dairyFoods2);
		
		itemService.insertItem(grainsBeansAndNuts1);
		itemService.insertItem(grainsBeansAndNuts2);
		
		// ------------------------------------------------------------------- //

		displayData.printInfo("Data successfully loaded.");
	}

	private Boolean isDBEmpty() {
		Boolean isEmpty = false;
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
