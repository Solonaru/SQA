package sms.entities.item.component.consumable;

import java.util.List;
import java.util.Optional;

public interface IConsumableService {

	public Optional<Consumable> findConsumableById(int consumableId);

	public List<Consumable> findAllConsumables();

	public void insertConsumable(Consumable consumable);

	public void updateConsumable(Consumable consumable);

	public void deleteConsumableById(int consumableId);
}
