package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.WipStyle;

public interface IWipStyleRepository extends JpaRepository<WipStyle, Integer>, QueryDslPredicateExecutor<WipStyle> {

	List<WipStyle> findByPartyAndDeactive(Party party, Boolean deactive);
	WipStyle findByPartyAndDesignAndDeactive(Party party,Design design,  Boolean deactive);
}
