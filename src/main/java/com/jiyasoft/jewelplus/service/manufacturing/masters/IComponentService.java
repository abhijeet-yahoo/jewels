package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;


public interface IComponentService {

	public List<Component> findAll();

	public Page<Component> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(Component component);

	public void delete(int id);

	public Long count();

	public Component findOne(int id);

	public Component findByName(String name);
	
	public Map<Integer, String> getComponentList();

	public List<Component> findActiveComponents();
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<Component> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public Page<Component> findActiveComponentsSortByName();
	
	public List<Component> findByChargableAndDeactive(Boolean chargable,Boolean deactive);
	
	//--------deduction component----//
	
	public Map<Integer,String> getComponentForDeduction(List<String> components);
	
	public List<Component> findDeductionComponent(List<String> components);
	
	public String getComponentListDropDownForDeduction(List<String> components);

	public Page<Component> searchAll(Integer limit, Integer offset,
			String sort, String order, String search, Boolean onlyActive);

	public Long countAll(String colName, String colValue, Boolean onlyActive);
	
	public Map<Integer, String> getChargebaleOrNonChargableComponentList(Boolean chargable);
	
	public String getChargebaleOrNonChargablComponentDropDown(Boolean chargable);
	
	
	
	
	
	
	
	
}
