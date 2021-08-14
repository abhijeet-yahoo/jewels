package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductTypeMast;

public interface IProductTypeService {


	public void save(ProductTypeMast productTypeMast);

	public void delete(int id);
	
	public ProductTypeMast findOne(int id);

	public Map<Integer, String> getProductTypeMastList();

	public List<ProductTypeMast> findActiveProductTypeMasts();

	public Page<ProductTypeMast> findActiveProductTypeMastSortByName();
	
	public Page<ProductTypeMast> searchAll(Integer limit, Integer offset, String sort,
			String order, String search, Boolean onlyActive);
	

	public ProductTypeMast findByCodeAndDeactive(String code, Boolean deactive);
	
	public ProductTypeMast findByNameAndDeactive(String productTypeMastName, Boolean deactive);
	
	public List<ProductTypeMast> findAllByOrderByNameAsc();
}
