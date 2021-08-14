package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

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

public interface ICostStnDtItemRepository extends JpaRepository<CostStnDtItem, Integer>, QueryDslPredicateExecutor<CostStnDtItem>{

List<CostStnDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	List<CostStnDtItem> findByCostingDtItemAndDeactive(CostingDtItem costingDtItem,Boolean deactive);
		
	List<CostStnDtItem> findByItemNoAndCostingDtItemAndDeactive(String itemNo,CostingDtItem costingDtItem,Boolean deactive);
	
	CostStnDtItem findByCostingDtItemAndPartNmAndQualityAndSettingAndSettingTypeAndShapeAndSieveAndSizeAndSizeGroupAndStoneTypeAndDeactive(CostingDtItem costingDtItem,LookUpMast lookUpMast, Quality quality, Setting setting, 
			SettingType settingType,Shape shape,String sieve, String size, SizeGroup sizeGroup,StoneType stoneType,Boolean deactive);
	
	List<CostStnDtItem> findByCostingDtItemAndDeactiveOrderByStoneDesc(CostingDtItem costingDtItem,Boolean deactive);
	
	List<CostStnDtItem> findByCostingDtItemAndDeactiveOrderByStnRateAsc(CostingDtItem costingDtItem,Boolean deactive);
}
