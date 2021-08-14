package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BagGenQty;

public interface IBagGenQtyRepository extends JpaRepository<BagGenQty, Integer>, QueryDslPredicateExecutor<BagGenQty>
{
 BagGenQty findByQty(Double qty);
	
}
