package sms.entities.lines;

import sms.entities.pack.PackageLine;

public class PackageLineFactory implements ILineAbstractFactory {

	private Integer quantity;

	// ----- Constructors -----
	public PackageLineFactory(Integer quantity) {
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new PackageLine(quantity);
	}

}
