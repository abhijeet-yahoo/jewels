package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;
import java.util.Map;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;

public interface IVAddStnDtService {
	
	public void save(VAddStnDt vAddStnDt);
	
	public void delete(int id);
	
	public VAddStnDt findOne(int id);
	
	public Long count();
	
	public List<VAddStnDt> findByVAddDtAndDeactive(VAddDt VAddDt, Boolean deactive);
	
	public Double calulateVAddStnDtTotLoanVal(Integer costMt);
	
	public List<VAddStnDt> findByCostingMtAndDeactive(CostingMt costingMt, Boolean deactive);
	
	public Map<Integer, String> getQualityList(Integer costMtId);
	
	public Map<Integer, String> getShapeList(Integer costMtId);
	
	public Map<Integer, String> getSizeGroupList(Integer costMtId);
	
	public List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndDeactive(CostingMt costingMt,StoneType stoneType,Shape shape, Quality quality,Boolean deactive);
	
	public List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,SizeGroup sizeGroup,Boolean deactive);
	
	public	List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndDeactive(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,String size,Boolean deactive);
	
	public	List<VAddStnDt> findByCostingMtAndStoneTypeAndShapeAndDeactive(CostingMt costingMt,StoneType stoneType,Shape shape,Boolean deactive);
	
	
	public List<VAddStnDt> findByCostingMtAndShapeAndQualityAndDeactive(CostingMt costingMt,Shape shape, Quality quality,Boolean deactive);
	
	
}
