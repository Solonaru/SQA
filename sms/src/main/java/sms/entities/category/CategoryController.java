package sms.entities.category;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sms.enums.Status;
import sms.utils.DisplayData;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Category> findCategoryById(@PathVariable("categoryId") int categoryId) {
		dataDisplay.printCrudInfo(categoryId);
		return categoryService.findCategoryById(categoryId);
	}
	
	@RequestMapping(value = "/name/{categoryName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Category> findCategoryByName(@PathVariable("categoryName") String categoryName) {
		return categoryService.findCategoryByName(categoryName);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getCategories() {
		dataDisplay.printCrudInfo();
		List<Category> categories = categoryService.findAllCategories();
		Collections.sort(categories);
		return categories;
	}
	
	@RequestMapping(value = "/all/active", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getActiveCategories() {
		dataDisplay.printCrudInfo();
		List<Category> categories = categoryService.findAllActiveCategories();
		Collections.sort(categories);
		return categories;
	}
	
	@RequestMapping(value = "/all/active/frontOffice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getFrontOfficeActiveCategories() {
		dataDisplay.printCrudInfo();
		List<Category> categories = categoryService.findAllActiveFrontOfficeCategories();
		Collections.sort(categories);
		return categories;
	}

	@RequestMapping(value = "/all/noParent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getNoParentCategories() {
		dataDisplay.printCrudInfo();
		List<Category> categories = categoryService.findAllNoParentCategories();
		Collections.sort(categories);
		return categories;
	}

	@RequestMapping(value = "/all/noChild", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getNoChildCategories() {
		dataDisplay.printCrudInfo();
		List<Category> categories = categoryService.findAllNoChildCategories();
		Collections.sort(categories);
		return categories;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertCategory(@RequestBody Category category) {
		dataDisplay.printCrudInfo();
		category.setStatus(Status.ACTIVE);
		category.setUpdateDate(new Date(System.currentTimeMillis()));
		categoryService.insertCategory(category);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCategory(@RequestBody Category category) {
		dataDisplay.printCrudInfo();
		category.setUpdateDate(new Date(System.currentTimeMillis()));
		categoryService.updateCategory(category);
	}

	/* TODO: Optimise, receive a Category object, not an ID */
	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCategory(@PathVariable("categoryId") int categoryId) {
		dataDisplay.printCrudInfo(categoryId);
		Category category = categoryService.findCategoryById(categoryId).get();
		category.removeCategory();
		category.setUpdateDate(new Date(System.currentTimeMillis()));
		categoryService.updateCategory(category);
	}
}