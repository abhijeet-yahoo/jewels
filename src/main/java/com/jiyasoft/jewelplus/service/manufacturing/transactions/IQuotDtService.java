package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;

public interface IQuotDtService {
	
	public List<QuotDt> findAll();
	
	public void save(QuotDt quotDt);
	
	public void delete(int id);
	
	public Long count();
	
	public QuotDt findOne(int id);
	
	public QuotDt findByUniqueId(Long uniqueId);
	
	public List<QuotDt> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	public String applyRate(QuotDt quotDt,Principal principal,Boolean netWtWithCompFlg);
	
	public String applyMetal(QuotDt quotDt);
	
	public String applyStoneRate(List<QuotStnDt> quotStnDts);
	
	public String applyCompRate(List<QuotCompDt>quotCompDts);
	
	public String applyLabRate(QuotDt quotDt,Principal principal);
	
	public String updateFob(QuotDt quotDt,Boolean netWtWithCompFlg); 
	
	public String applyDiamondRate(QuotDt quotDt);
	
	public String updateGrossWt(QuotDt quotDt,Boolean netWtWithCompFlg); 

	public String transactionalSave(QuotDt quotDt,Integer id,Integer quotMtIdPk,String metalDtData,Principal principal,Boolean netWtWithCompFlg);
	
	
	public Page<QuotDt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId);
	
	public Long countAll(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId);
	
	
	public String quotToQuotPickup(String pOIds,String pTransferQty,
			Integer mtId, Principal principal,Integer partyId);
	
	public String orderToQuotPickup(String pOIds,Integer mtId,Principal principal);
	
	
	public Page<QuotDt> quotDtPickUpList(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId,String forOrderNm,String pickUpType);
	
	public QuotStnDt applySettingRate(QuotStnDt quotStnDt);
	
	public QuotStnDt applyHandlingRate(QuotStnDt quotStnDt);
	public QuotStnDt applyStoneRateDt(QuotStnDt quotStnDt);
	
	
	public QuotCompDt applyCompRateDt(QuotCompDt quotCompDt);
	
	
	public String updateKtDesc(Integer quotDtId);
	
	public String updateQltyDesc(Integer quotDtId);
	
	
	public QuotDt findByQuotMtAndDesignAndKtDescAndQltyDescAndDeactive(QuotMt quotMt,Design design,String ktDesc,String qltyDesc,Boolean deactive);
	
	public String quotDtExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile, Principal principal, Integer mtId,Boolean netWtWithCompFlg) throws ParseException;
	
	
	
	
}
