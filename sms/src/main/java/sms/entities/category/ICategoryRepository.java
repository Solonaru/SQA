package sms.entities.category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sms.enums.CategoryType;
import sms.enums.Status;

public interface ICategoryRepository extends CrudRepository<Category, Integer> {

	public List<Category> findAllByStatus(Status status);
	
	public List<Category> findAllByStatusAndCategoryType(Status status, CategoryType categoryType);

	public List<Category> findAllByParentCategoryIsNullAndStatus(Status status);

	public List<Category> findAllByChildCategoriesIsNullAndStatus(Status status);

}
