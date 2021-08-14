package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;

public interface ITranMtRepository extends JpaRepository<TranMt, Integer>,
		QueryDslPredicateExecutor<TranMt> {


	List<TranMt> findByBagMt(BagMt bagMt);
		
	List<TranMt> findByDepartmentAndCurrStk(Department department, Boolean currStk);

	TranMt findByBagMtAndDepartmentAndCurrStk(BagMt bagMt,Department department, Boolean currStk);
	
	TranMt findByBagMtAndDeptToAndCurrStkAndPendApprovalFlg(BagMt bagMt,Department departmentTo,Boolean currStk,Boolean pendApprovalFlg);
	       
	/*TranMt findByBagMtAndCurrStk(BagMt bagMt, Boolean currStk);*/
	
    TranMt findByDepartmentAndDeptFromAndBagMtAndCurrStk(Department department,Department deptFrom,BagMt bagMt,Boolean currStk);
    
    TranMt findByIdAndCurrStk(Integer id,Boolean currStk);
    
    TranMt findByRefMtIdAndCurrStk(Integer refMtId,Boolean currStk);
    
    TranMt findByBagMtIdAndCurrStk(Integer bagId, Boolean currStk);
    
    List<TranMt>findByBagMtAndCurrStk(BagMt bagMt, Boolean currStk);
    
    List<TranMt> findByRefMtId(Integer refMtid);
    
	TranMt findByBagMtAndPendApprovalFlgAndCurrStk(BagMt bagMt,Boolean pendApprovalFlg, Boolean currStk);
	
	
	List<TranMt> findByDepartmentAndCurrStkAndPendApprovalFlg(Department department, Boolean currStk,Boolean pendApprovalFlg);
	
 
}
