package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.mysema.query.Tuple;

public interface IStoneInwardDtService {

	public List<StoneInwardDt> findAll();

	public Page<StoneInwardDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(StoneInwardDt stoneInwardDt);

	public void delete(int id);

	public Long count();

	public StoneInwardDt findOne(int id);

	public Map<Integer, String> getStoneInwardDtList();

	public List<StoneInwardDt> findByStoneInwardMt(StoneInwardMt stoneInwardMt);

	public Page<StoneInwardDt> findByStoneInwardMt(StoneInwardMt stoneInwardMt,
			Integer limit, Integer offset, String sort, String order,
			String search);

	public StoneInwardDt findByUniqueId(Long uniqueId);

	public List<StoneInwardDt> findByStoneInwardMtAndDeactiveOrderByIdDesc(StoneInwardMt stoneInwardMt, Boolean deactive);

	public List<StoneInwardDt> findByStoneTypeAndShapeAndDeactive(StoneType stoneType,Shape shape,Boolean Deactive);

	public List<StoneInwardDt> getFifoDetails(String pStoneType, String pShape,
			String pQuality, String pSize, String pBagStn, String pCarat,
			String pPointer, String pSizeGroup);

	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndDeactive(StoneType stoneType, Shape shape, Quality quality, String size,Boolean deactive);

	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(StoneType stoneType, Shape shape, Quality quality, SizeGroup sizeGroup,Boolean deactive);
	
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndDeactiveOrderByIdAsc(StoneType stoneType, Shape shape, Quality quality,Boolean Deactive);
	
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndSizeGroupAndDeactive(StoneType stoneType, Shape shape, Quality quality, String size,SizeGroup sizeGroup,Boolean deactive);
	
	
	
	//----all new method for fifoButton
	
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeAndDeactiveOrderByRateDesc(StoneType stoneType, Shape shape, Quality quality, String size,Boolean deactive);

	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactiveOrderByRateDesc(StoneType stoneType, Shape shape, Quality quality, SizeGroup sizeGroup,Boolean deactive);
	
	public List<StoneInwardDt> findByStoneTypeAndShapeAndQualityAndDeactiveOrderByRateDesc(StoneType stoneType, Shape shape, Quality quality,Boolean deactive);
	
	//--for StoneRate master---//
	
	public List<Tuple> getListForStoneRateMast();
	
	public void saveAll(List<StoneInwardDt> stoneInwardDts);
	
	public String saveStoneInwardDt(StoneInwardDt stoneInwardDt,Integer id, String pInvNo,Double vCarat,Integer vStone,String sizeGroupStr,Principal principal);
	
	public String saveOrderStoneInward(Integer pInwardMtid,String data,Double pBagQty,String mOpt,Principal principal,Boolean allowNegative,String stockType,String companyName);
	
	public String stoneInwDtDelete(Integer id, Principal principal);
	
	public Integer getMaxTranSrno(Integer sordDtId);
	
	public Integer getMaxLotSrno();
	
	public String ordStockAllocation(String pInvNo,String data,Principal principal,Boolean allowNegative,String stockType,String companyName);
	
	public String returnOrdStock(String pInvNo,String data,Principal principal,Boolean allowNegative,String stockType,String companyName);
	
}
