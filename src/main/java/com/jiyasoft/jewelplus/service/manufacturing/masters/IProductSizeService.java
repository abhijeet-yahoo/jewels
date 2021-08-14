package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.mysema.query.types.Predicate;

public interface IProductSizeService {

	public List<ProductSize> findAll();

public Page<ProductSize> findAll(Integer limit, Integer offset, String sort,
		String order, String search);

public void save(ProductSize productSize);

public void delete(int id);

public Long count();

public Long count(Predicate predicate);

public ProductSize findOne(int id);

public ProductSize findByName(String productSizeName);

public Page<ProductSize> findByCategory(Category category, Integer limit,
		Integer offset, String sort, String order, String search);

public Predicate countByCategory(Integer id);

public Map<Integer, String> getProductSizeList(Integer catId);

public List<ProductSize> findActiveProductSizes(Integer catId);

public Long count(String colName, String colValue, Boolean onlyActive);

public Page<ProductSize> findByName(Integer limit, Integer offset,
		String sort, String order, String name, Boolean onlyActive);

public Page<ProductSize> searchAll(Integer limit, Integer offset,
		String sort, String order, String search, Boolean onlyActive);

public Long countAll(String colName, String colValue, Boolean onlyActive);


}
