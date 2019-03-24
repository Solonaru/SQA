package sms.entities.item.pack.simplepack;

import java.util.List;
import java.util.Optional;

public interface ISimplePackageService {

	Optional<SimplePackage> findSimplePackageById(int simplePackageId);

	List<SimplePackage> findAllSimplePackages();

	void insertSimplePackage(SimplePackage simplePackage);

	void updateSimplePackage(SimplePackage simplePackage);

	void deleteSimplePackageById(int simplePackageId);
}
