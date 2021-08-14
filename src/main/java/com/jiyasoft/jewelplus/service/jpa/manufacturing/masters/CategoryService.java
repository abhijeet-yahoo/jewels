package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QCategory;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.ICategoryRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> findAll(Integer limit, Integer offset, String sort,
			String order, String search) {

		limit = (limit == null ? 10 : limit);
		offset = (offset == null ? 0 : offset);

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return categoryRepository.findAll(new PageRequest(page, limit, (order
				.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC),
				sort));
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public void delete(int id) {
		Category category = categoryRepository.findOne(id);
		category.setDeactive(true);
		category.setDeactiveDt(new java.util.Date());
		categoryRepository.save(category);
	}

	@Override
	public Long count() {
		return categoryRepository.count();
	}

	@Override
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public Category findByName(String categoryName) {
		return categoryRepository.findByName(categoryName);
	}

	@Override
	public Map<Integer, String> getCategoryList() {
		Map<Integer, String> categoryMap = new LinkedHashMap<Integer, String>();
		Page<Category> categoryList = findActiveCategoriesSortByName();
		
		for (Category category : categoryList) {
			categoryMap.put(category.getId(), category.getName());
		}

		return categoryMap;
	}

	@Override
	public List<Category> findActiveCategories() {
		QCategory qCategory = QCategory.category;
		BooleanExpression expression = qCategory.deactive.eq(false);

		List<Category> categoryList = (List<Category>) categoryRepository
				.findAll(expression);

		return categoryList;
	}

	@Override
	public Page<Category> findActiveCategoriesSortByName() {
		QCategory qCategory = QCategory.category;
		BooleanExpression expression = qCategory.deactive.eq(false);

		Page<Category> categoryList = categoryRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return categoryList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		
		QCategory qCategory = QCategory.category;
		BooleanExpression expression = qCategory.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qCategory.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qCategory.deactive.eq(false).and(
						qCategory.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qCategory.name.like(colValue + "%");
			}
		}

		return categoryRepository.count(expression);
	}

	@Override
	public Page<Category> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QCategory qCategory = QCategory.category;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qCategory.deactive.eq(false);
			} else {
				expression = qCategory.deactive.eq(false).and(
						qCategory.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qCategory.name.like(name + "%");
			}
		}

		// new addition
		int page = 0;
		if (offset == null || limit == null) {
			limit = 10000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}
		
		// new addition

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<Category> categoryList = (Page<Category>) categoryRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return categoryList;
	}

	public Page<Category> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive){
		QCategory qCategory = QCategory.category;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qCategory.deactive.eq(false);
			}else{
				expression = qCategory.deactive.eq(false).and(qCategory.name.like("%" + search + "%").or(qCategory.categCode.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qCategory.name.like("%" + search + "%").or(qCategory.categCode.like("%" + search + "%"));
			}
		}
		
		if(limit == null){
			limit=1000;
		}
		if(offset == null){
			offset = 0;
		}
		
		int page = (offset == 0 ? 0 : (offset / limit));
		
		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		} else if (sort.equalsIgnoreCase("categCode")) {
			sort = "categCode";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<Category> categoryMastList =(Page<Category>) categoryRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));
		
		
		System.out.println("expression "+expression);
		return categoryMastList;
				
	}
	
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		QCategory qCategory = QCategory.category;
		BooleanExpression expression = null;
		
		if(onlyActive){
			  if(colName == null && colValue==null ){
					expression = qCategory.deactive.eq(false);			
				}else{
					expression = qCategory.deactive.eq(false).and(qCategory.name.like("%" + colValue + "%").or(qCategory.categCode.like("%" + colValue + "%")));
				}
			}else{
				if (colValue == null) {	
					expression = null;
				} else {
					expression = qCategory.name.like("%" + colValue + "%").or(qCategory.categCode.like("%" + colValue + "%"));
				}
			}
			
			return categoryRepository.count(expression);
	}

	@Override
	public Category findByCategCode(String code) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCategCode(code);
	}
	
	
}
