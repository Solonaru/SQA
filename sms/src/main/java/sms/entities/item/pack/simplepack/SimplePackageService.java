package sms.entities.item.pack.simplepack;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimplePackageService implements ISimplePackageService {

	@Autowired
	private ISimplePackageRepository simplePackageRepository;

	public Optional<SimplePackage> findSimplePackageById(int simplePackageId) {
		return simplePackageRepository.findById(simplePackageId);
	}

	public List<SimplePackage> findAllSimplePackages() {
		return (List<SimplePackage>) simplePackageRepository.findAll();
	}

	public void insertSimplePackage(SimplePackage simplePackage) {
		simplePackageRepository.save(simplePackage);
	}

	public void updateSimplePackage(SimplePackage simplePackage) {
		simplePackageRepository.save(simplePackage);
	}

	public void deleteSimplePackageById(int simplePackageId) {
		simplePackageRepository.deleteById(simplePackageId);
	}
}
