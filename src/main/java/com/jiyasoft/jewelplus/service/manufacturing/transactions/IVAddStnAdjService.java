package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnAdj;

public interface IVAddStnAdjService {

	public void save(VAddStnAdj vAddStnAdj);
	
	public void delete(int id);
	
	public VAddStnAdj findOne(int id);
	
	public String saveStoneAdjustment(String dtIds,Integer costMtIdPk,Integer stonetypeid,Integer shapeid,Integer qualityid,Integer sizegroupid,String size,
			Double rate,Double carat,Principal principal,String adjCarat,String vaddstnadjtype);
	
	/*
	 * public List<VAddStnAdj>
	 * findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeAndStnRateAndSizeGroup(
	 * CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality, String
	 * size,Double stnRate,SizeGroup sizeGroup);
	 * 
	 * public List<VAddStnAdj>
	 * findByCostingMtAndStoneTypeAndShapeAndQualityAndStnRateAndSizeGroup(CostingMt
	 * costingMt,StoneType stoneType,Shape shape,Quality quality,Double
	 * stnRate,SizeGroup sizeGroup);
	 * 
	 * public List<VAddStnAdj>
	 * findByCostingMtAndStoneTypeAndShapeAndQualityAndStnRate(CostingMt
	 * costingMt,StoneType stoneType,Shape shape,Quality quality,Double stnRate);
	 */	
	public List<VAddStnAdj> findByCostingMtAndStoneTypeAndShape(CostingMt costingMt,StoneType stoneType,Shape shape);

	public String deleteAdjustment(Integer costMtIdPk,Integer stonetypeid,Integer shapeid,Integer qualityid,Integer sizegroupid,String size,
			Double rate,Principal principal,String vaddstnadjtype);
	
	public String deleteAllAdjustment(Integer costMtIdPk,Principal principal,String vaddstnadjtype);
	
	
	public VAddStnAdj findByStonePurchaseDt(StonePurchaseDt stonePurchaseDt);
	
	public String vaddStnDtListing(Integer vAddMtId,String vaddstnadjtype);
	
	public String adjListing(String size,Integer costMtIdPk,Integer stonetypeid,Integer shapeid,Integer qualityid,Integer sizegroupid,String vaddstnadjtype);
	public List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQualityAndSize(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,
			String size);

	public List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQualityAndSizeGroup(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality,SizeGroup sizeGroup);
	
	public List<VAddStnAdj> findByCostingMtAndStoneTypeAndShapeAndQuality(CostingMt costingMt,StoneType stoneType,Shape shape,Quality quality);
	
	public List<VAddStnAdj>findByCostingMt(CostingMt costingMt);
	
	
	public String fifoAllAdjustment(Integer vAddMtId,String vaddstnadjtype,Principal principal);

	
}