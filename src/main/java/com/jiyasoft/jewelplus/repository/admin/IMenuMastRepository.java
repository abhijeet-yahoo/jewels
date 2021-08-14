package com.jiyasoft.jewelplus.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;

public interface IMenuMastRepository extends JpaRepository<MenuMast, Integer>,QueryDslPredicateExecutor<MenuMast>{
	
	MenuMast findByMenuNm(String menuNm);

	MenuMast findByMenuHeading(String menuHeading);

	//List<MenuMast> findByTranType(String tranType);

	List<MenuMast> findByParentIdAndDeactiveOrderBySeqNoAsc(Integer parentId,Boolean deactive);
	
	
	
}
