package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;

public interface IBagMtService {
	
	public List<BagMt> findAll();
	
	public Page<BagMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(BagMt bagMt);
	
	public void delete(int id);
	
	public Long count();
	
	public BagMt findOne(int id);
	
	public BagMt findByName(String name);
	
	public List<BagMt> findByOrderMt(OrderMt orderMt);

	public List<BagMt> findByOrderMtAndOrderDt(OrderMt orderMt, OrderDt orderDt);

	public Map<Integer, String> getBagMtList();

	public List<BagMt> findActivebags();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<BagMt> findByName(Integer limit, Integer offset, String sort, String order, String invNo, Boolean onlyActive);

	public Integer getMaxSrno();

	public Double getGenQty(Integer orderMtId, Integer orderDtId);
	
	public String getMaxItemno();
	
	public List<BagMt> findByItemNo(String itemNo);
	
	public List<BagMt> findBagsByOrderMt(String orderIds);
	
	BagMt findByItemNoAndDeactive(String itemNo,Boolean deactive);
	
	public String bagSplit(String bagNo,Double bagQty,Double splitQty, Double weight,Principal principal,Double splitWeight,String barcodeuploadFilePath);
	
	List<BagMt> findByOrderDtAndDeactive(OrderDt orderDt, Boolean deactive);
	
	public String recastBag(String bagIds,Principal principal,String barcodeuploadFilePath);
	
	public Integer getMaxBarcodeSrno(String barcodeType);
	
	public String generateBarcode(String bagNm,String barcodeuploadFilePath, Principal principal, Integer barcodeMtId);
	
	public String bagMtListing(Integer limit,Integer offset,String sort,String order,String search,String pInvNo,Integer pOrdDt);
	
	public String setOpeningListing(Integer limit,Integer offset,String sort,String order,String search,String pInvNo,String opt);
	
	public String jobBagDetails(String bagName,String uploadFilePath);
	
	public String jobBagDiamondDetails(String bagName,String uploadFilePath);
	
	public String diaRateChngDetails(String bagName,String uploadFilePath);
	
	public String metalAdditionBagDetail(String bagName,String uploadFilePath);
	
	public String bagSearch(String bagName,String uploadFilePath);
	
	public String bagDetailsList(String orderNo,Principal principal);
	
	public BagMt findByBarcode(String barcode);
	


}
