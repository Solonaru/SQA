package sms.entities.item.component.sauce;

import java.util.List;
import java.util.Optional;

public interface ISauceService {

	public Optional<Sauce> findSauceById(int sauceId);

	public List<Sauce> findAllSauces();

	public void insertSauce(Sauce sauce);

	public void updateSauce(Sauce sauce);

	public void deleteSauceById(int sauceId);
}
