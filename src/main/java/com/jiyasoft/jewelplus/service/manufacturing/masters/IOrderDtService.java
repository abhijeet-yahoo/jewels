package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;


public interface IOrderDtService {

	public List<OrderDt> findAll();

	public Page<OrderDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(OrderDt orderDt);

	public String delete(int id);

	public Long count();
	
	public OrderDt findOne(int id);

	public OrderDt findByUniqueId(Long uniqueId);

	public Map<Integer, String> getOrderDtList();

	public Page<OrderDt> findByStyleNo(Integer limit, Integer offset, String sort, String order,
			String styleNo, Boolean onlyActive);

	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Double getTotalOrderQtys(Integer orderMtId);
	
	public String transactionalSave(OrderDt orderDt,Integer id,Integer orderMtIdPk,String metalDtData,Principal principal,Boolean netWtWithCompFlg);
	
	public String applyRate(OrderDt orderDt,Principal principal, Boolean netWtWithCompFlg);
		
	public String applyMetal(OrderDt orderDt);
	
	public String applyStoneRate(List<OrderStnDt> orderStnDts);
	
	public String applyQuotStoneRate(List<OrderStnDt> orderStnDts,List<QuotStnDt>quotStnDts);
	
	
	public String applyCompRate(List<OrderCompDt>orderCompDts);
	public String applyQuotCompRate(List<OrderCompDt>orderCompDts,List<QuotCompDt>quotCompDts);
	
	public String applyLabRate(OrderDt orderDt,Principal principal);
	
	public String applyQuotLabRate(OrderDt orderDt,QuotDt quotDt,Principal principal);
	
	public String updateFob(OrderDt orderDt,Boolean netWtWithCompFlg); 
	
	public String updateGrossWt(OrderDt orderDt,Boolean netWtWithComp);
	
	public List<OrderDt> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);
	
	
	public Long countAll(String colValue,Integer mtId);

	public Page<OrderDt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId,String mOpt);
	
	public String quotToOderPickup(String pOIds,String pTransferQty,
			Integer mtId, Principal principal,Integer partyId);
	
	
	public String orderToOderPickup(String pOIds,String pTransferQty,
			Integer mtId, Principal principal,Integer partyId);
	
	public Integer getMaxSrNo(Integer mtId);

	public List<OrderDt> getorderStyleList(String orderId);
	
	public String getOrderDtTotal(Integer mtId);

	public OrderStnDt applySettingRate(OrderStnDt orderStnDt);
	
	public OrderStnDt applyHandlingRate(OrderStnDt orderStnDt);
	
	public OrderStnDt applyStoneRateDt(OrderStnDt orderStnDt);
	
	public OrderCompDt applyCompRateDt(OrderCompDt orderCompDt);
	
	public String addOrderFromQuot(Integer orderMtId,String data,Principal principal);

	public String orderDtReportListing(String mtIds);

	public String updateKtDesc(Integer ordDtId);
	
	public String updateQltyDesc(Integer ordDtId);
	
	public String orderDtPickupListing(String orderNm);
	
	public String applyQuotRate(OrderDt orderDt,Principal principal, Boolean netWtWithCompFlg);

	public String applyQuotMetal(OrderDt orderDt,QuotDt quotDt);
	
	public String orderDtExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile, Principal principal,Integer mtId) throws ParseException;
	
}
