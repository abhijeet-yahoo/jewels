package com.jiyasoft.jewelplus.repository.manufacturing.masters;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;

public interface IDesignRepository extends JpaRepository<Design, Integer>, QueryDslPredicateExecutor<Design> {

	public Design findByStyleNo(String styleNo);

	public Design findByStyleNoAndVersion(String styleNo, String version);

	public Design findByUniqueId(Long uniqueId);
	
	public Design findByDesignNoAndDeactive(String designNo,Boolean deactive);
	
	public Design findByMainStyleNoAndDeactive(String mainStyleNo,Boolean deactive);
	public Design findByDesignNoAndVersionAndDeactive(String designNo,String version,Boolean deactive);
	
	
	public List<Design> findByMainStyleNoContaining(String name);
	

	


}
