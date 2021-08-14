package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ChangingList;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;

public interface IDiamondBaggingService {
	
	public List<DiamondBagging> findAll();
	
	public Page<DiamondBagging> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(DiamondBagging diamondBagging);
	
	public void delete(int id);
	
	public Long count();
	
	public DiamondBagging findOne(int id);
	
	public DiamondBagging findByName(String name);
	
	public List<DiamondBagging> findByBagMt(BagMt bagMt);
	
	public List<DiamondBagging> findByBagMtAndTranTypeAndDeactiveAndBaggingReturn(BagMt bagMt,String tranType,Boolean deactive,Boolean baggingReturn);

	public List<DiamondBagging> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);

	public List<DiamondBagging> findByOrderDtAndOrderStnDt(OrderDt orderDt, OrderStnDt orderStnDt);

	public Map<Integer, String> getDiamondBaggingList();

	public List<DiamondBagging> findActivebags();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Integer getMaxSrno(Integer BagId);

	public List<DiamondBagging> findByStoneTypeAndShapeAndQualityAndSize(StoneType stoneType, Shape shape, Quality quality, String size);

	public List<DiamondBagging> findByStoneTypeAndShapeAndQualityAndSizeGroup(StoneType stoneType, Shape shape, Quality quality, SizeGroup sizeGroup);

	public List<DiamondBagging> findByBagMtAndBagSrNo(BagMt bagMt, Integer bagSrNo);
	
	public List<ChangingList> getListForDiamondChanging(Integer bagId);
	
	
	public List<DiamondBagging> findByBagMtAndBagSrNoAndStoneInwardDtAndSettingAndSettingTypeAndDeactive(
			BagMt bagMt, Integer bagSrNo, StoneInwardDt stoneInwardDt,
			Setting setting, SettingType settingType,Boolean deactive);
	
	public DiamondBagging findByUniqueId(Long uniqueId);
	
	public List<DiamondBagging> getListForCostingTransfer(Integer bagId);
	
	public List<DiamondBagging> findByBagMtAndStoneInwardDtAndDeactive(BagMt bagMt,StoneInwardDt stoneInwardDt,Boolean deactive);
	
	public String diamondBaggingTransfer(String bagNm,String data,Principal principal,String stockType,Boolean allowNegative,Date tranDate,String companyName);
	
	public String diamondBaggingTransferOnBag(String bagNm,String data,Principal principal,String stockType,Boolean allowNegative,Date tranDate,String companyName,Integer deptId);
	
	public String saveNewBreakUp( String pOIds,String trfBalStone,String trfBalCarat,String cSetting,String cSettingType,String bagNm,String tempDeptNm,String pCenterStone,Principal principal);
	
	public String diamondChangingReturn(DiamondBagging diamondBagging,String pOIds,String trfBagStone,String trfBagCarat,String bagNm,String cDeptId,Principal principal);
	
	public String diamondChangingAddBreakUp(String pOIds, String trfBalStone,String trfBalCarat,Integer dbId,String cSetting,String cSettingType,
			String tempDeptNm,Principal principal);
	
	
	public String saveAdjustment(String stnPk,String trfStone,String trfCarat,String bagNm,Integer ordStnDtId,Principal principal);
	
	
	public List<DiamondBagging> findByBagMtAndTranTypeAndTransferedAndDeactive(BagMt bagMt,String tranType,Boolean transfered,Boolean deactive);
	
	public String diamondChangingAddNewBreakUp(Integer tranDtId,Integer reqStone,Double reqCarat,Principal principal,String stockType,Boolean allowNegative,Date tranDate);
	
	public String diamondChangingNewBreakUpTrf(String bagNm,String data,Principal principal,Integer partId,String vSize,String stockType,Boolean allowNegative,Date tranDate);
	
	public String diamondChangingSingleReturnBreakUp(Integer tranDtId,Integer retStone,Double retCarat,String changingType,Integer employeeId,Principal principal,Date tranDate);
	
	public String diamondReturnSelected(String tblData,Principal principal,Date tranDate);
	
	
}
