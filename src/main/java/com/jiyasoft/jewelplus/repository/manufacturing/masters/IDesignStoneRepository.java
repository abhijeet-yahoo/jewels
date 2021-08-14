package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;

public interface IDesignStoneRepository extends JpaRepository<DesignStone, Integer>, QueryDslPredicateExecutor<DesignStone> {

	public List<DesignStone> findByDesign(Design design);

	public Page<DesignStone> findByDesign(Design design, Pageable pageable);
	
	

}
