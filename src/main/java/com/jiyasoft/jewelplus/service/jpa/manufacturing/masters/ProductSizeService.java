package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QProductSize;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IProductSizeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ProductSizeService implements IProductSizeService {

	@Autowired
	private IProductSizeRepository productSizeRepository;

	@Override
	public List<ProductSize> findAll() {
		return productSizeRepository.findAll();
	}

	@Override
	public Page<ProductSize> findAll(Integer limit, Integer offset,
			String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return productSizeRepository
				.findAll(new PageRequest(page, limit, (order
						.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public void save(ProductSize productSize) {
		productSizeRepository.save(productSize);
	}

	@Override
	public void delete(int id) {
		ProductSize productSize = productSizeRepository.findOne(id);
		productSize.setDeactive(true);
		productSize.setDeactiveDt(new java.util.Date());
		productSizeRepository.save(productSize);
	}

	@Override
	public Long count() {
		return productSizeRepository.count();
	}

	@Override
	public ProductSize findOne(int id) {
		return productSizeRepository.findOne(id);
	}

	@Override
	public ProductSize findByName(String productSizename) {
		return productSizeRepository.findByName(productSizename);
	}

	@Override
	public Page<ProductSize> findByCategory(Category category, Integer limit,
			Integer offset, String sort, String order, String search) {

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		}

		return productSizeRepository.findByCategory(category, new PageRequest(
				page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC
						: Direction.DESC), sort));
	}

	@Override
	public Long count(Predicate predicate) {
		return productSizeRepository.count(predicate);
	}

	@Override
	public Predicate countByCategory(Integer id) {
		QProductSize qProductSize = QProductSize.productSize;

		// Deactive was not considered, since all the records have to be
		// displayed
		BooleanExpression expression = qProductSize.category.id.eq(id);

		return expression;
	}

	@Override
	public Map<Integer, String> getProductSizeList(Integer catId) {
		Map<Integer, String> productSizeMap = new HashMap<Integer, String>();
		List<ProductSize> productSizeList = findActiveProductSizes(catId);

		for (ProductSize productSize : productSizeList) {
			productSizeMap.put(productSize.getId(), productSize.getName());
		}

		return productSizeMap;
	}

	@Override
	public List<ProductSize> findActiveProductSizes(Integer catId) {
		QProductSize qproductSize = QProductSize.productSize;
		BooleanExpression expression = null;

		if (catId == 0) {
			expression = qproductSize.deactive.eq(false);
		} else {
			expression = qproductSize.category.id.eq(catId).and(
					qproductSize.deactive.eq(false));
		}

		List<ProductSize> productSizeList = (List<ProductSize>) productSizeRepository
				.findAll(expression);

		return productSizeList;
	}

	@Override
	public Long count(String colName, String colValue, Boolean onlyActive) {
		QProductSize qProductSize = QProductSize.productSize;
		BooleanExpression expression = qProductSize.deactive.eq(false);

		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qProductSize.deactive.eq(false);
			} else if (colName.equalsIgnoreCase("name") && colValue != null) {
				expression = qProductSize.deactive.eq(false).and(
						qProductSize.name.like(colValue + "%"));
			}
		} else {
			if ((colName == null || colName.equalsIgnoreCase("name"))
					&& colValue != null) {
				expression = qProductSize.name.like(colValue + "%");
			}
		}

		return productSizeRepository.count(expression);
	}

	@Override
	public Page<ProductSize> findByName(Integer limit, Integer offset,
			String sort, String order, String name, Boolean onlyActive) {

		QProductSize qProductSize = QProductSize.productSize;
		BooleanExpression expression = null;

		if (onlyActive) {
			if (name == null) {
				expression = qProductSize.deactive.eq(false);
			} else {
				expression = qProductSize.deactive.eq(false).and(
						qProductSize.name.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qProductSize.name.like(name + "%");
			}
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("updatedBy")) {
			sort = "modiBy";
		} else if (sort.equalsIgnoreCase("updatedDt")) {
			sort = "modiDt";
		}

		Page<ProductSize> productSizeList = (Page<ProductSize>) productSizeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		return productSizeList;
	}

	@Override
	public Page<ProductSize> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive) {
		QProductSize qProductSize = QProductSize.productSize;
		BooleanExpression expression = null;
		

		if (onlyActive) {
			if (search == null) {
				expression = qProductSize.deactive.eq(false);
			} else {
				expression = qProductSize.deactive.eq(false).and(qProductSize.category.name.like("%" + search + "%").or(qProductSize.name.like("%" + search+ "%")));
			}
		} else {
			if (search != null) {
				expression = qProductSize.category.name.like("%" + search + "%").or(qProductSize.name.like("%" + search+ "%"));
			}
		}

		if (limit == null) {
			limit = 1000;
		}
		if (offset == null) {
			offset = 0;
		}

		int page = (offset == 0 ? 0 : (offset / limit));

		if (sort == null) {
			sort = "id";
		} else if (sort.equalsIgnoreCase("category")) {
			sort = "category";
		} else if (sort.equalsIgnoreCase("name")) {
			sort = "name";
		}
		Page<ProductSize> productSizeMastList = (Page<ProductSize>) productSizeRepository
				.findAll(
						expression,
						new PageRequest(page, limit, (order
								.equalsIgnoreCase("asc") ? Direction.ASC
								: Direction.DESC), sort));

		System.out.println("expression " + expression);
		return productSizeMastList;

	
	}

	@Override
	public Long countAll(String colName, String colValue, Boolean onlyActive) {
		QProductSize qProductSize = QProductSize.productSize;
		BooleanExpression expression = null;
		if (onlyActive) {
			if (colName == null && colValue == null) {
				expression = qProductSize.deactive.eq(false);
			} else {
				expression = qProductSize.deactive.eq(false).and(qProductSize.category.name.like("%" + colValue + "%").or(qProductSize.name.like("%" + colValue + "%")));
			}
		} else {
			if (colValue == null) {
				expression = null;
			} else {
				expression = qProductSize.category.name.like("%" + colValue + "%").or(
								qProductSize.name.like("%" + colValue + "%"));
			}
		}

		return productSizeRepository.count(expression);

	}

}
