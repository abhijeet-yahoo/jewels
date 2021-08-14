package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;

public interface IClientWastageService {

	public List<ClientWastage> findAll();

	public Page<ClientWastage> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(ClientWastage clientWastage);
	
	public ClientWastage findByMetalAndPartyAndDeactive(Metal metal,Party party,Boolean deactive);

	public void delete(int id);

	public Long count();
	
	public ClientWastage findOne(int id);

	public List<ClientWastage> findActiveClientWastages();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<ClientWastage> findByName(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	
	
	public Long countAll(String colValue);

	
	
	public Page<ClientWastage> searchAll(Integer limit, Integer offset, String sort,
			String order, String name);
	
	
	public ClientWastage findByMetalAndPartyAndDesignGroupAndCategoryAndSubCategoryAndDeactive(Metal metal,Party party,DesignGroup designGroup,Category category, SubCategory subCategory, Boolean deactive);

	

}
