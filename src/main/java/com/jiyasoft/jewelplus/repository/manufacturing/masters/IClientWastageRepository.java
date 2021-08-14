package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;

public interface IClientWastageRepository extends JpaRepository<ClientWastage, Integer>, QueryDslPredicateExecutor<ClientWastage> {

	ClientWastage findByMetalAndPartyAndDeactive(Metal metal,Party party,Boolean deactive);
	
	ClientWastage findByMetalAndPartyAndDesignGroupAndCategoryAndSubCategoryAndDeactive(Metal metal,Party party,DesignGroup designGroup,Category category, SubCategory subCategory, Boolean deactive);

}
