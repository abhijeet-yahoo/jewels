package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;
import java.util.Map;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;


public interface ITranMetalService {
	
	public List<TranMetal>findByBagMtAndCurrStk(BagMt bagMt,  Boolean currStk);
	
	public TranMetal findOne(int id);
	
	public Map<Integer, String> getLookpList(BagMt bagMt);
		
	public TranMetal findByBagMtIdAndPartNmIdAndCurrStk(Integer bagId,Integer partId,Boolean currStk);
	
	public TranMetal findByBagMtIdAndMainMetalAndCurrStk(Integer bagId,Boolean mainMetal,Boolean currStk);
	
	public List<TranMetal> findByTranMtAndCurrStk(TranMt tranMt, Boolean currStk);
	
	public void save(TranMetal tranMetal);
	
	public String delete(int id);
	
	public List<TranMetal> findByDepartmentAndCurrStkAndPurityAndColor(Department department,Boolean currStk,Purity purity,Color color);
	
	public Map<Integer, String> getLossBookPart(BagMt bagMt, Department department);
	
	
	public Map<Integer, String> getPartListByTranMt(TranMt tranMt);
	

	public List<TranMetal> findByBagMtAndDepartmentAndCurrStk(BagMt bagMt ,Department department,Boolean currStk);
	
	public List<TranMetal> findByBagMtIdAndPartNmId(Integer bagId,Integer partId);
	
	public TranMetal findByRefTranMetalIdAndCurrStk(Integer refTranMetalId,Boolean currStk);
	
	
	
	
	
	
	

}
