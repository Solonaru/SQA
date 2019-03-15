package sms.entities.item.component;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeverageService implements IBeverageService{

	@Autowired
	private IBeverageRepository beverageRepository;

	public Optional<Beverage> findBeverageById(int beverageId) {
		return beverageRepository.findById(beverageId);
	}

	public List<Beverage> findAllBeverages() {
		return (List<Beverage>) beverageRepository.findAll();
	}

	public void insertBeverage(Beverage beverage) {
		beverageRepository.save(beverage);
	}

	public void updateBeverage(Beverage beverage) {
		beverageRepository.save(beverage);
	}

	public void deleteBeverageById(int beverageId) {
		beverageRepository.deleteById(beverageId);
	}
}
