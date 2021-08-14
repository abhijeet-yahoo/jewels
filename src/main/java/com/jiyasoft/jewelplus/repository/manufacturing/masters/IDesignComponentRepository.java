package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;

public interface IDesignComponentRepository extends JpaRepository<DesignComponent, Integer>, QueryDslPredicateExecutor<DesignComponent> {

	//public DesignComponent findByDesign(Design design);

	public List<DesignComponent> findByDesign(Design design);

	public Page<DesignComponent> findByDesign(Design design, Pageable pageable);

}
