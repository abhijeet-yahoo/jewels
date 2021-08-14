package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;


public interface ISaleMtService {

	public Page<SaleMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	
	public Integer getMaxInvSrno();
	
	public void save(SaleMt saleMt);

	public void delete(int id);
	
	public SaleMt findOne(int id);
	
	public List<SaleMt> findByParty(Party party);
	
	public String deleteSaleMt(Integer mtId,Principal principal);
	
	public String saleMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;
	
	public String saveSaleMt(SaleMt saleMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,Integer pPartyIds,Integer pLocationIds,
			String vTranDate,Double vOtherCharges,Double vFinalPrice,Double pIgst, Double pSgst,Double pCgst, String barcodeuploadFilePath) throws ParseException;
	
	public String salePackingListTransfer(Integer pMtId,String data,Principal principal);
	
	public String saleConsignmetTransfer(Integer pMtId,String data,Principal principal);
	
	public String saleDtSummary(Integer mtId,Principal principal);
	
	public String addSummaryDetails(Integer mtId,Double fob,Double sgst,Double cgst,Double igst,Double otherCharges,Double finalPrice,Principal principal);
	
	public String saleMtPickupList(Integer partyId);
	
	public SaleMt findByInvNo(String invNo);
	
	public String checkInvoiceAvailable(Integer id, String invNo);
	
	public String saleMtMetalPickupList(Integer partyId);
	
	public String saleRowCompPickupList(Integer partyId);
	
	public String saleRowStonePickupList(Integer partyId);

}
