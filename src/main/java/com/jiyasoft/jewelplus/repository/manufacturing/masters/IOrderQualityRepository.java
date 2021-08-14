package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderQuality;

public interface IOrderQualityRepository extends JpaRepository<OrderQuality, Integer>, QueryDslPredicateExecutor<OrderQuality> {

	public List<OrderQuality> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);

	public Page<OrderQuality> findByOrderMt(OrderMt orderMt, Pageable pageable);

}
