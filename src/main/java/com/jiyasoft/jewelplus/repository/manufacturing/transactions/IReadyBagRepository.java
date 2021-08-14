package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface IReadyBagRepository extends JpaRepository<ReadyBag, Integer>,
		QueryDslPredicateExecutor<ReadyBag> {
	
	/*ReadyBag findByBagMtAndBagSrNoAndRetStoneAndRetCarat(BagMt bagMt,Integer bagSrNo,Integer retStone,Double retCarat);*/
	
	List<ReadyBag> findByBagMtAndCurrentStock(BagMt bagMt,Boolean currStk);
	
	List<ReadyBag> findByBagMtAndLocationIsNull(BagMt bagMt);
	
	List<ReadyBag> findByBagMtAndBagSrNoAndDeactive(BagMt bagMt,Integer bagSrNo,Boolean deactive);
	
	List<ReadyBag> findByTranMtAndCurrentStockAndDeactive(TranMt tranMt,Boolean currStk,Boolean deactive);

	List<ReadyBag> findByBagMt(BagMt bagMt);
	
	List<ReadyBag> findByBagMtAndCurrentStockAndLocationIsNull(BagMt bagMt,Boolean currStk);
	
	List<ReadyBag> findByBagMtAndCurrentStockAndLocationAndPendApprovalFlg(BagMt bagMt,Boolean currStk,Department department, Boolean pendApprovalFlg);
}
