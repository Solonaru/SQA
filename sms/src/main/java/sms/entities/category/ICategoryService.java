package sms.entities.category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
	
	public Optional<Category> findCategoryById(int categoryId);
	
	public Optional<Category> findCategoryByName(String name);

	public List<Category> findAllCategories();
	
	public List<Category> findAllActiveCategories();
	
	public List<Category> findAllActiveFrontOfficeCategories();
	
	public List<Category> findAllNoParentCategories();
	
	public List<Category> findAllNoChildCategories();

	public void insertCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategoryById(int categoryId);
}