package sms.entities.employee;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = RightDeserializer.class)
public interface IRight {

	public boolean canCreateProduct();

	public boolean canEditProduct();

	public boolean canRemoveProduct();

	public boolean canCreateCategory();

	public boolean canEditCategory();

	public boolean canRemoveCategory();

	public boolean canCreateCatalogue();

	public boolean canEditCatalogue();

	public boolean canRemoveCatalogue();

	public boolean canCreateEmployee();

	public boolean canEditEmployee();

	public boolean canRemoveEmployee();

}
