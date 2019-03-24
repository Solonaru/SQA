package sms.entities.item.component.beverage;

import java.util.List;
import java.util.Optional;

public interface IBeverageService {

	public Optional<Beverage> findBeverageById(int beverageId);

	public List<Beverage> findAllBeverages();

	public void insertBeverage(Beverage beverage);

	public void updateBeverage(Beverage beverage);

	public void deleteBeverageById(int beverageId);
}
