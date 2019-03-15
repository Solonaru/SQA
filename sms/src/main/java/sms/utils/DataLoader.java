package sms.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import sms.entities.account.IAccountService;
import sms.entities.customer.Customer;
import sms.entities.employee.Admin;
import sms.entities.employee.Employee;
import sms.entities.employee.IRight;
import sms.entities.employee.OperatorProducts;
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
