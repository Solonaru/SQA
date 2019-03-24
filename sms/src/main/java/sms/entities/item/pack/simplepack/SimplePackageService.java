package sms.entities.item.pack.simplepack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimplePackageService implements ISimplePackageService {

    @Autowired
    private ISimplePackageRepository simplePackageRepository;

    @Override
    public Optional<SimplePackage> findSimplePackageById(int simplePackageId) {
        return simplePackageRepository.findById(simplePackageId);
    }

    @Override
    public List<SimplePackage> findAllSimplePackages() {
        return (List<SimplePackage>) simplePackageRepository.findAll();
    }

    @Override
    public void insertSimplePackage(SimplePackage simplePackage) {
        simplePackageRepository.save(simplePackage);
    }

    @Override
    public void updateSimplePackage(SimplePackage simplePackage) {
        simplePackageRepository.save(simplePackage);
    }

    @Override
    public void deleteSimplePackageById(int simplePackageId) {
        simplePackageRepository.deleteById(simplePackageId);
    }
}
