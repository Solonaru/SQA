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
import sms.entities.job.IJobService;
import sms.entities.job.Job;
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
	private IJobService jobService;

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
		
		updateJob();

		displayData.printInfo("Data successfully loaded.");
	}

	private void updateJob() {
		Job waiter = new Job("Waiter", "Part-time", "to add description", "to add image",
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

		Job manager = new Job("Manager", "Full-time", "to add description", "to add image",
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
						"Provide solutions to issues (e.g. profit decline, employee conflicts, loss of business to competitors)"));

		Job sysAdmin = new Job("System Administrator", "Full-time", "to add description", "to add image",
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

		Job operator = new Job("Operator", "Full-time", "to add description", "to add image",
				Arrays.asList("Great communication skills ",
						"Great English skills ",
						"Knowledge of business process and functions (finance, HR, procurement, operations etc.)",
						"Proeficient with handling software programs",
						"Problem-solving aptitude",
						"BSc/BA in Business or relevant field; MSc/MA is a plus"),
				Arrays.asList("Perform day-to-day operations ",
						"Keep in touch with the administrators",
						"Cooperate with the rest of the staff"));

		Job janitor = new Job("Janitor", "Full-time", "to add description", "to add image",
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

		Job frontendDev = new Job("Front End Developer", "Full-time", "to add description", "to add image",
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

		Job backendDev = new Job("Back End Developer", "Full-time", "to add description", "to add image",
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

		Job deliveryDriver = new Job("Delivery Driver", "Full-time", "to add description", "to add image",
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

		Job economist = new Job("Economist", "Full-time", "to add description", "to add image",
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

		Job cook = new Job("Cook", "Part-time", "to add description", "to add image",
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

		Job custRel = new Job("Customer Relations Representant", "Full-time", "to add description", "to add image",
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

		Category category1 = new Category("Pizzas", "Pizzas category");
		Category category2 = new Category("Deserts", "Deserts category");
		Category category3 = new Category("Beverages", "Beverages category");

		Category category4 = new Category("Ingredients", "Ingredients category");
		Category category401 = new Category("Vegetables", "Vegetables category");
		category401.setParentCategory(category4);
		Category category402 = new Category("Fruits", "Fruits category");
		category402.setParentCategory(category4);
		Category category403 = new Category("Grains, Beans and Nuts", "Grains, beans and nuts category");
		category403.setParentCategory(category4);
		Category category404 = new Category("Meat and Poultry", "Meat and poultry category");
		category404.setParentCategory(category4);
		Category category405 = new Category("Fish and Seafood", "Fish and seafood category");
		category405.setParentCategory(category4);
		Category category406 = new Category("Dairy Foods", "Dairy foods category");
		category406.setParentCategory(category4);
		Category category407 = new Category("Pantry", "Pantry category");
		category407.setParentCategory(category4);

		Category category5 = new Category("Dough recipes", "Dough recipes category");
		Category category6 = new Category("Tomato sauce recipes", "Tomato sauce recipes category");

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

		Ingredient meatAndPoultry1 = new Ingredient("Beef Eye Round Steak", 10, "");
		meatAndPoultry1.setCategory(category404);
		meatAndPoultry1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgBeefEyeRoundSteak.jpg");
		Ingredient meatAndPoultry2 = new Ingredient("Rib Eye Steak", 16, "");
		meatAndPoultry2.setCategory(category404);
		meatAndPoultry2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgRibEyeSteak.jpg");
		Ingredient meatAndPoultry3 = new Ingredient("Sweet Italian Pork Sausage", 22, "");
		meatAndPoultry3.setCategory(category404);
		meatAndPoultry3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgSweetItalianPorkSausage.jpg");
		Ingredient meatAndPoultry4 = new Ingredient("Pork Bacon", 22, "");
		meatAndPoultry4.setCategory(category404);
		meatAndPoultry4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/meat_and_poultry/imgPorkBacon.jpg");

		Ingredient vegetables1 = new Ingredient("Sweet Cherry Tomatoes", 2, "");
		vegetables1.setCategory(category401);
		vegetables1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgSweetCherryTomatoes.jpg");
		Ingredient vegetables2 = new Ingredient("Campari Tomatoes", 2, "");
		vegetables2.setCategory(category401);
		vegetables2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgCampariTomatoes.jpg");
		Ingredient vegetables3 = new Ingredient("Baby Bella Mushrooms", 1, "");
		vegetables3.setCategory(category401);
		vegetables3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgBabyBellaMushrooms.jpg");
		Ingredient vegetables4 = new Ingredient("White Mushrooms", 1, "");
		vegetables4.setCategory(category401);
		vegetables4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgWhiteMushrooms.jpg");
		Ingredient vegetables5 = new Ingredient("Garlic", 2, "");
		vegetables5.setCategory(category401);
		vegetables5.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgGarlic.jpg");
		Ingredient vegetables6 = new Ingredient("Red Onion", 1, "");
		vegetables6.setCategory(category401);
		vegetables6.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgRedOnion.jpg");
		Ingredient vegetables7 = new Ingredient("Yellow Onion", 1, "");
		vegetables7.setCategory(category401);
		vegetables7.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgYellowOnion.jpg");
		Ingredient vegetables8 = new Ingredient("Oregano", 2, "");
		vegetables8.setCategory(category401);
		vegetables8.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/vegetables/imgOregano.jpg");

		Ingredient dairyFoods1 = new Ingredient("Swiss Emmental", 16, "");
		dairyFoods1.setCategory(category406);
		dairyFoods1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgSwissEmmental.jpg");
		Ingredient dairyFoods2 = new Ingredient("Grana Padano", 18, "");
		dairyFoods2.setCategory(category406);
		dairyFoods2.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgGranaPadano.jpg");
		Ingredient dairyFoods3 = new Ingredient("Pecorino Romano", 12, "");
		dairyFoods3.setCategory(category406);
		dairyFoods3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgPecorinoRomano.jpg");
		Ingredient dairyFoods4 = new Ingredient("Fresh Mozzarella", 9, "");
		dairyFoods4.setCategory(category406);
		dairyFoods4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgFreshMozzarella.jpg");
		Ingredient dairyFoods5 = new Ingredient("Gorgonzola", 18, "");
		dairyFoods5.setCategory(category406);
		dairyFoods5.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/dairy_foods/imgGorgonzola.jpg");

		Ingredient pantry1 = new Ingredient("Extra-Virgin Olive Oil", 11, "");
		pantry1.setCategory(category407);
		pantry1.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgExtraVirginOliveOil.jpg");
		Ingredient pantry2 = new Ingredient("Table Salt", 11, "");
		pantry2.setCategory(category407);
		pantry2.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgTableSalt.jpg");
		Ingredient pantry3 = new Ingredient("Whole Wheat Pre-Sifted Flour", 5, "");
		pantry3.setCategory(category407);
		pantry3.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgWholeWheatPreSiftedFlour.jpg");
		Ingredient pantry4 = new Ingredient("Unbleached All-Purpose Flour", 4, "");
		pantry4.setCategory(category407);
		pantry4.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgUnbleachedAllPurposeFlour.jpg");
		Ingredient pantry5 = new Ingredient("Canola Oil", 4, "");
		pantry5.setCategory(category407);
		pantry5.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgCanolaOil.jpg");
		Ingredient pantry6 = new Ingredient("White Flour", 4, "");
		pantry6.setCategory(category407);
		pantry6.setImageUrl("../../../../assets/images/items/products/components/ingredients/pantry/imgWhiteFlour.jpg");
		Ingredient pantry7 = new Ingredient("Whole Wheat Flour", 6, "");
		pantry7.setCategory(category407);
		pantry7.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgWholeWheatFlour.jpg");
		Ingredient pantry8 = new Ingredient("Light Brown Sugar", 2, "");
		pantry8.setCategory(category407);
		pantry8.setImageUrl(
				"../../../../assets/images/items/products/components/ingredients/pantry/imgLightBrownSugar.jpg");
		Ingredient pantry9 = new Ingredient("Dark Brown Sugar", 2, "");
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

		Recipe recipe101 = new Recipe("Grilled Thin-Crust", 1,
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

		Recipe recipe102 = new Recipe("Grilled Whole-Wheat Crust", 1,
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
