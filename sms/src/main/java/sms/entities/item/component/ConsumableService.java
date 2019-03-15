package sms.entities.item.component;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumableService implements IConsumableService{

	@Autowired
	private IConsumableRepository consumableRepository;

	public Optional<Consumable> findConsumableById(int consumableId) {
		return consumableRepository.findById(consumableId);
	}

	public List<Consumable> findAllConsumables() {
		return (List<Consumable>) consumableRepository.findAll();
	}

	public void insertConsumable(Consumable consumable) {
		consumableRepository.save(consumable);
	}

	public void updateConsumable(Consumable consumable) {
		consumableRepository.save(consumable);
	}

	public void deleteConsumableById(int consumableId) {
		consumableRepository.deleteById(consumableId);
	}
}
