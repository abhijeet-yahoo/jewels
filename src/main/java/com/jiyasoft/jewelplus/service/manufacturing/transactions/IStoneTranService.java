package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneTran;

public interface IStoneTranService {

	public List<StoneTran> findAll();

	public void save(StoneTran stoneTran);

	public void delete(int id);

	public List<StoneTran> findByRefTranIdAndTranType(Integer refTranId,String tranType);

	public StoneTran RefTranIdAndTranType(Integer refTranId, String tranType);
	
	//public Double getStockBalance(Integer purityId,Integer colorId,Integer locationId,Integer componentId);
	
	Double getStockBalance(Integer locationId,Integer stoneTypeId,Integer shapeId,Integer qualityId,Integer sizeGroupId,String size,String flag);
	
	Double getStockBalanceStr(Integer locationId,String stoneTypeNm,String shapeNm,String qualityNm,String sizeGroupNm,String sizeNm,String flag,Integer orderDtId);
	
	List<StoneTran> findByBagMtAndBagSrNoAndTranType(BagMt bagMt,Integer bagSrNo,String tranType);
	
	List<Object[]> getStoneTranList(Integer stonetypeId,Integer shapeId,Integer qualityId,Integer sizeGroupId,String size,String flag,String bagNm,Principal principal);
	
	Integer getMaxSrno(Integer BagId);
	
	public StoneTran findByQualityAndSize(Quality quality, String size);
	
	public String saveStnTran(String vSizeIdFrom,Integer vshapeIdFrom,Principal principal, Integer vQualityIdFrom, Integer vStoneTypeIdFrom, Double vtrfCarat,
			Integer vtrfStone,StoneTran stoneTran,String diamondStockType,Date tranDate,Integer locationId);
	
	public Double getDiamondCaratStock(Integer locationId, Integer stoneTypeId, Integer shapeId, Integer qualityId, StoneChart stoneChart,String flag);
	
	
	public List<StoneTran> findByTranMtIdAndDeactive(Integer tranMtId,Boolean deactive);

	public List<StoneTran> findBySordDtIdAndDeactive(Integer sordDtId,Boolean deactive);
	
	public List<StoneTran> findByStnRecDtIdAndDeactive(Integer stnRecDtId,Boolean deactive);
	
	public List<StoneTran> findByTranTypeAndDeactiveAndSordMtIdAndShapeAndQualityAndSizeAndInOutFld(String tranType,Boolean deactive,
			Integer sordMtId,Shape shape,Quality quality,String size,String inOutFld);
	
	public List<StoneTran> getDiamondStockForAllocation(Integer locationId,String stoneTypeNm,String shapeNm,String qualityNm,String sizeNm,String sieve);
	
	public List<StoneTran> getDiamondStockRate(Integer locationId,String stoneTypeNm,String shapeNm,String qualityNm,String sizeNm,String sieve);
	
	public String getAvailableStock(Integer locationId,Integer stoneTypeId, Integer shapeId, Integer qualityId,String sieve);
}
