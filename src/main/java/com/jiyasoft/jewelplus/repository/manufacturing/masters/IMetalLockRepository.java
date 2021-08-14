package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.MetalLock;

public interface IMetalLockRepository extends JpaRepository<MetalLock, Integer>
											  ,QueryDslPredicateExecutor<MetalLock> {
	
	

}
