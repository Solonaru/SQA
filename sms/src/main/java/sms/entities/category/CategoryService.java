package sms.entities.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sms.enums.CategoryType;
import sms.enums.Status;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	public Optional<Category> findCategoryById(int categoryId) {
		return categoryRepository.findById(categoryId);
	}

	public List<Category> findAllCategories() {
		return (List<Category>) categoryRepository.findAll();
	}

	public List<Category> findAllActiveCategories() {
		return (List<Category>) categoryRepository.findAllByStatus(Status.ACTIVE);
	}
	
	public List<Category> findAllActiveFrontOfficeCategories() {
		return (List<Category>) categoryRepository.findAllByStatusAndCategoryType(Status.ACTIVE, CategoryType.FRONT_OFFICE);
	}

	public List<Category> findAllNoParentCategories() {
		return (List<Category>) categoryRepository.findAllByChildCategoriesIsNullAndStatus(Status.ACTIVE);
	}
	
	public List<Category> findAllNoChildCategories() {
		return (List<Category>) categoryRepository.findAllByParentCategoryIsNullAndStatus(Status.ACTIVE);
	}

	public void insertCategory(Category category) {
		categoryRepository.save(category);
	}

	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}

	public void deleteCategoryById(int categoryId) {
		categoryRepository.deleteById(categoryId);
	}
}
