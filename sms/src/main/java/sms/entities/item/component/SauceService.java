package sms.entities.item.component;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SauceService implements ISauceService{

	@Autowired
	private ISauceRepository sauceRepository;

	public Optional<Sauce> findSauceById(int sauceId) {
		return sauceRepository.findById(sauceId);
	}

	public List<Sauce> findAllSauces() {
		return (List<Sauce>) sauceRepository.findAll();
	}

	public void insertSauce(Sauce sauce) {
		sauceRepository.save(sauce);
	}

	public void updateSauce(Sauce sauce) {
		sauceRepository.save(sauce);
	}

	public void deleteSauceById(int sauceId) {
		sauceRepository.deleteById(sauceId);
	}
}
