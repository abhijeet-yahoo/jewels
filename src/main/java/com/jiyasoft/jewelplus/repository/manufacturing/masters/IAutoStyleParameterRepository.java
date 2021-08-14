package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.AutoStyleParameter;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;

public interface IAutoStyleParameterRepository extends JpaRepository<AutoStyleParameter	, Integer>, QueryDslPredicateExecutor<AutoStyleParameter> {

	
	AutoStyleParameter findById(Integer id);
	
	AutoStyleParameter findByCategoryAndCollectionMasterAndDeactive(Category category,CollectionMaster collectionMaster, Boolean deactive);
}
