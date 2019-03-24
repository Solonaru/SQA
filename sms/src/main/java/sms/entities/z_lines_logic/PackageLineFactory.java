package sms.entities.z_lines_logic;

import sms.entities.item.pack.pack_line.PackageLine;

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