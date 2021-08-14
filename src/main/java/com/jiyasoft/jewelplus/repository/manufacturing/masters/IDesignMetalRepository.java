package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;

public interface IDesignMetalRepository extends JpaRepository<DesignMetal, Integer>,
	QueryDslPredicateExecutor<DesignMetal>{
	
	
	List<DesignMetal> findByDesignAndDeactive(Design design,Boolean deactive);
	
	DesignMetal findByDesignAndDeactiveAndMainMetal(Design design,Boolean deactive,Boolean mainMetal);
	
	DesignMetal findByDesignAndPartNmAndDeactive(Design design,LookUpMast lookUpMast,Boolean deactive);
	
	
	

}
