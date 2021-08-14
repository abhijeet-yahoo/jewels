package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.AutoStyleParameter;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

public interface IAutoStyleParameterService {

	public List<AutoStyleParameter> findAll();
	
	public void save (AutoStyleParameter autoStyleParameter);
	
	public void delete (int id);
	
	public Page<AutoStyleParameter> searchAll(Integer limit, Integer offset, String sort,
			String order, String name);


	public AutoStyleParameter findOne(int id);


	public AutoStyleParameter findByCategoryAndCollectionMasterAndDeactive(Category category,CollectionMaster collectionMaster, Boolean deactive);
	
	
	public String  getautoStyleNo(Category category,CollectionMaster collectionMaster,Party party);
	
	
}
