package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;

public interface IOrderMtService {

	public List<OrderMt> findAll();

	public Page<OrderMt> findAll(Integer limit, Integer offset, String sort,
			String orderMt, String search);

	public void save(OrderMt orderMt);

	public String delete(int id);

	public Long count();
	
	public OrderMt findOne(int id);

	public OrderMt findByInvNoAndDeactive(String invNo,Boolean deactive);

	public Page<OrderMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String invNo, Boolean onlyActive);
	
	public Page<OrderMt> findByInvNoListByParty(Integer limit, Integer offset, String sort,
			String order, String invNo, Boolean onlyActive,String partyNm);
	
	public Page<OrderMt> findByParty(Integer limit, Integer offset, String sort, String order, String party, Boolean onlyActive);

	public Long count(String colName, String colValue, Boolean onlyActive);

	public OrderMt findByUniqueId(Long uniqueId);

	public Long getMaxSrno();
	
	public List<OrderMt> getOrderList(String partyIds,String orderTypeIds,String ordFlg,String fromDate,String toDate,String divisionIds,
			String regionIds,String customerTypeIds) throws ParseException;
	
	public Long count(String partyIds,String orderTypeIds);
	
	public List<OrderMt> findByOrderCloseAndDeactive(Boolean orderClose,Boolean deactive);
	
	public Long countAll(String colValue);

	public Page<OrderMt> searchAll(Integer limit, Integer offset, String sort, String order, String name);
	
	public String orderExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile, Principal principal) throws ParseException;
	
	public String orderApproval(Integer mtId, Principal principal);
	
	public OrderMt findByInvNoAndDeactiveAndPendApprovalFlg(String invNo,Boolean deactive,Boolean pendApprovalFlg);
	
	public String autoOrderClose();
	
	public String applyBarcode(Integer mtId, Principal principal, Boolean netWtWithCompFlg);
	
public Page<OrderMt> pendingForApprovalList(Integer limit, Integer offset, String sort, String order, String name, Boolean approvalFlg);

	
	public String orderUnApproval(Integer mtId, Principal principal);
	
	public String getSalesPersonFromParty(Integer partyId, Principal principal);
	
}
