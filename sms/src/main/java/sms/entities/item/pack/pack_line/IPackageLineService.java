package sms.entities.item.pack.pack_line;

import java.util.List;
import java.util.Optional;

public interface IPackageLineService {

	public Optional<PackageLine> findPackageLineById(int packageLineId);

	public List<PackageLine> findAllPackageLines();

	public void insertPackageLine(PackageLine packageLine);

	public void updatePackageLine(PackageLine packageLine);

	public void deletePackageLineById(int packageLineId);

}
