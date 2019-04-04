package sms.entities.logic;

import sms.entities.item.pack.line.PackageLine;

public class PackageLineFactory implements ILineAbstractFactory {

	private Double quantity;

	// ----- Constructors -----
	public PackageLineFactory(Double quantity) {
		this.quantity = quantity;
	}

	// ----- Methods -----
	public ILine createLine() {
		return new PackageLine(quantity);
	}

}
