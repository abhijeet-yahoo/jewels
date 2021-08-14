package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostStnDtItemService {

	public List<CostStnDtItem> findAll();
	
	public void save(CostStnDtItem costStnDtItem);

	public void delete(int id);

	public Long count();

	public CostStnDtItem findOne(int id);
	
	public List<CostStnDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<CostStnDtItem> findByCostingDtItemAndDeactive(CostingDtItem costingDtItem,Boolean deactive);
	
	public List<CostStnDtItem> findByItemNoAndCostingDtItemAndDeactive(String itemNo,CostingDtItem costingDtItem,Boolean deactive);
	
	public void lockUnlockStnDt(Integer CostMtId,Boolean status);
	
	public void updateItemNo(Integer costDtId,String itemNo);

	public CostStnDtItem findByCostingDtItemAndPartNmAndQualityAndSettingAndSettingTypeAndShapeAndSieveAndSizeAndSizeGroupAndStoneType(CostingDtItem costingDtItem,LookUpMast lookUpMast, Quality quality, Setting setting, 
			SettingType settingType,Shape shape,String sieve, String size, SizeGroup sizeGroup,StoneType stoneType);
	
	public String updateManualTagWt(Integer costDtItemId,Double tagWt,Principal principal,Boolean netWtWithCompFlg);
	
	public List<CostStnDtItem> findByCostingDtItemAndDeactiveOrderByStoneDesc(CostingDtItem costingDtItem,Boolean deactive);
	
	public List<CostStnDtItem> findByCostingDtItemAndDeactiveOrderByStnRateAsc(CostingDtItem costingDtItem,Boolean deactive);
	
	
	public String updateTagWtReqCts(CostingMt costingMt,Principal principal,Boolean netWtWithCompFlg);
	
	public String autoApplyTagWt(CostingMt costingMt,Principal principal,Boolean netWtWithCompFlg);
	
	public String apply005TagWt(CostingMt costingMt,Principal principal,Boolean netWtWithCompFlg);
	
}
