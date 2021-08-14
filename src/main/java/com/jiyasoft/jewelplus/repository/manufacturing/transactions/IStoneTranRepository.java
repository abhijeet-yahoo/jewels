package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;

public interface IStoneTranRepository extends
		JpaRepository<StoneTran, Integer>, QueryDslPredicateExecutor<Integer> {
	
	
	List<StoneTran> findByRefTranIdAndTranType(Integer refTranId, String tranType);

	StoneTran RefTranIdAndTranType(Integer refTranId, String tranType);
	
	List<StoneTran> findByBagMtAndBagSrNoAndTranType(BagMt bagMt,Integer bagSrNo,String tranType);
	
	StoneTran findByQualityAndSize(Quality quality, String size);
	
	List<StoneTran> findByTranMtIdAndDeactive(Integer tranMtId,Boolean deactive);
	
	List<StoneTran> findBySordDtIdAndDeactive(Integer sordDtId,Boolean deactive);
	
	List<StoneTran> findByStnRecDtIdAndDeactive(Integer stnRecDtId,Boolean deactive);

	
	
	List<StoneTran> findByTranTypeAndDeactiveAndSordMtIdAndShapeAndQualityAndSizeAndInOutFld(String tranType,
			Boolean deactive, Integer sordMtId, Shape shape, Quality quality, String size,String inOutFld);
}
