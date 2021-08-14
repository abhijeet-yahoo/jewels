package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductTypeMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QProductTypeMast;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IProductTypeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductTypeService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class ProductTypeService implements IProductTypeService{

	@Autowired IProductTypeRepository productTypeRepository;
	
	
	@Override
	public void save(ProductTypeMast productTypeMast) {
		productTypeRepository.save(productTypeMast);
		
	}

	@Override
	public void delete(int id) {
		ProductTypeMast productTypeMast = productTypeRepository.findOne(id);
		productTypeMast.setDeactive(true);
		productTypeMast.setDeactiveDt(new java.util.Date());
		productTypeRepository.save(productTypeMast);
		
	}

	
	
	@Override
	public ProductTypeMast findOne(int id) {
		
		return productTypeRepository.findOne(id);
	}


	@Override
	public Map<Integer, String> getProductTypeMastList() {
		Map<Integer,String> productTypeMastMap = new LinkedHashMap<Integer, String>();
		Page<ProductTypeMast> productTypeMastList = findActiveProductTypeMastSortByName();
		
		for(ProductTypeMast productTypeMast : productTypeMastList)
		{
			productTypeMastMap.put(productTypeMast.getId(), productTypeMast.getName());
		}
		return productTypeMastMap;
	}

	@Override
	public List<ProductTypeMast> findActiveProductTypeMasts() {
		QProductTypeMast qProductTypeMast= QProductTypeMast.productTypeMast;
		BooleanExpression expression = qProductTypeMast.deactive.eq(false);

		List<ProductTypeMast> productTypeMastList = (List<ProductTypeMast>) productTypeRepository
				.findAll(expression);

		return productTypeMastList;
	}

	@Override
	public Page<ProductTypeMast> findActiveProductTypeMastSortByName() {
		QProductTypeMast qProductTypeMast = QProductTypeMast.productTypeMast;
		BooleanExpression expression = qProductTypeMast.deactive.eq(false);

		Page<ProductTypeMast> productTypeMastList = productTypeRepository.findAll(expression, new PageRequest(0, 10000, Direction.ASC, "name"));

		return productTypeMastList;
	}

	
	@Override
	public Page<ProductTypeMast> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive) {
		QProductTypeMast qProductTypeMast = QProductTypeMast.productTypeMast;
		BooleanExpression expression = null;
		
		if (onlyActive) {
			if (search == null) {
				expression = qProductTypeMast.deactive.eq(false);
			}else{
				expression = qProductTypeMast.deactive.eq(false).and(qProductTypeMast.name.like("%" + search + "%").or(qProductTypeMast.code.like("%" + search + "%")));
			}
		}else{
			if (search != null) {
				expression = qProductTypeMast.name.like("%" + search + "%").or(qProductTypeMast.code.like("%" + search + "%"));
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
		} else if (sort.equalsIgnoreCase("code")) {
			sort = "code";
		} else if (sort.equalsIgnoreCase("deactive")) {
			sort = "deactive";
		}
		
		Page<ProductTypeMast> productTypeMastList =(Page<ProductTypeMast>) productTypeRepository.findAll(
						expression, new PageRequest(page, limit, (order.equalsIgnoreCase("asc") ? Direction.ASC: Direction.DESC),sort));

		
		return productTypeMastList;
	}


	@Override
	public ProductTypeMast findByCodeAndDeactive(String code, Boolean deactive) {
		
		
		return productTypeRepository.findByCodeAndDeactive(code, deactive);
	}

	@Override
	public ProductTypeMast findByNameAndDeactive(String name, Boolean deactive) {
		
		return productTypeRepository.findByNameAndDeactive(name,deactive);
	}

	@Override
	public List<ProductTypeMast> findAllByOrderByNameAsc() {
		// TODO Auto-generated method stub
		return productTypeRepository.findAllByOrderByNameAsc();
	}

	
	
	
	

}
