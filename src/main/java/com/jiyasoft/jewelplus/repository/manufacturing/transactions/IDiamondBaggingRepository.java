package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;

public interface IDiamondBaggingRepository extends
		JpaRepository<DiamondBagging, Integer>,
		QueryDslPredicateExecutor<DiamondBagging> {

	public List<DiamondBagging> findByBagMt(BagMt bagMt);
	
	public List<DiamondBagging> findByBagMtAndTranTypeAndDeactiveAndBaggingReturn(BagMt bagMt,String tranType,Boolean deactive,Boolean baggingReturn);

	public List<DiamondBagging> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);

	public List<DiamondBagging> findByOrderDtAndOrderStnDt(OrderDt orderDt,OrderStnDt orderStnDt);

	public List<DiamondBagging> findByStoneTypeAndShapeAndQualityAndSize(StoneType stoneType, Shape shape, Quality quality, String size);

	public List<DiamondBagging> findByStoneTypeAndShapeAndQualityAndSizeGroup(StoneType stoneType, Shape shape, Quality quality,SizeGroup sizeGroup);

	public List<DiamondBagging> findByBagMtAndBagSrNo(BagMt bagMt,Integer bagSrNo);

	public List<DiamondBagging> findByBagMtAndBagSrNoAndStoneInwardDtAndSettingAndSettingTypeAndDeactive(BagMt bagMt, Integer bagSrNo, StoneInwardDt stoneInwardDt,
			Setting setting, SettingType settingType,Boolean deactive);

	public DiamondBagging findByUniqueId(Long uniqueId);
	
	public List<DiamondBagging> findByBagMtAndStoneInwardDtAndDeactive(BagMt bagMt,StoneInwardDt stoneInwardDt,Boolean deactive);
	
	public List<DiamondBagging> findByBagMtAndTranTypeAndTransferedAndDeactive(BagMt bagMt,String tranType,Boolean transfered,Boolean deactive);
	

}
