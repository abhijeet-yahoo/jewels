package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;


public interface ICollectionRepository extends JpaRepository<CollectionMaster, Integer>, QueryDslPredicateExecutor<CollectionMaster>{

	CollectionMaster findByName(String name);
	
	CollectionMaster findByCollectionCode(String collectionCode);
	
}
