package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;

public interface IGrecMtService {

	public void save(GrecMt grecMt);

	public void delete(int id);
	
	public GrecMt findOne(int id);

	public GrecMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public GrecMt findByUniqueId(Long uniqueId);
		
	public Page<GrecMt> searchAll(Integer limit, Integer offset, String sort, String order, String name,Boolean onlyActive);
	
	public String grecMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;
	
	public String saveGrecMt(GrecMt grecMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,Integer pPartyIds,Integer pOrderTypeIds,String vTranDate,Integer pLocationIds) throws ParseException;
	
	public Integer getMaxInvSrno();
	
	public String deleteGrecMt(Integer mtId);
	
	public String orderPickListing(Integer orderTypeId,Integer partyId);
	
	public String orderPickupTransferToStock(Principal principal,String bagIds,Integer grcmtId);
	
	public String generateBarcodeForGrec(Integer mtId,String barcodeuploadFilePath, Principal principal);
	
}
