package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;

public interface ISaleRetMtService {
	
	public Integer getMaxInvSrno();

	public void save(SaleRetMt saleRetMt);

	public void delete(int id);

	public SaleRetMt findOne(int id);

	public String deleteSaleRetMt(Integer mtId);

	public List<SaleRetMt> findByParty(Party party);

	public String saleRetMtListing(Integer limit, Integer offset, String sort, String order, String search,
			Principal principal) throws ParseException;

	public String saveSaleRetMt(SaleRetMt saleRetMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result, Integer pPartyIds, Integer pLocationIds,String vTranDate,Double vOtherCharges,Double vFinalPrice,
			Double pIgst, Double pSgst,Double pCgst) throws ParseException;

	public Page<SaleRetMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive);

	public String saleTransfer(Integer pMtId, String data, Principal principal);
	 
	public String addSaleRetSummaryDetails(Integer mtId,Double fob,Double sgst,Double cgst,Double igst,Double otherCharges,Double finalPrice,Principal principal);

}
