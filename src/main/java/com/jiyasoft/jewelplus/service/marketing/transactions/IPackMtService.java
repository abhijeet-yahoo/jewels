package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;

public interface IPackMtService {

	public Page<PackMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	
	public Integer getMaxInvSrno();
	
	public void save(PackMt packMt);

	public void delete(int id);
	
	public PackMt findOne(int id);
	
	public List<PackMt> findByParty(Party party);
	
	public String deletePackMt(Integer mtId);
	
	public String marketingReport(Integer mtId,String tranType,String uploadDirecotryPath,String uploadParentDirecotryName,String uploadDirecotryName,
			String tmpUploadImage,String reportXmlDirectoryPath,String reportoutputdirectorypath,Principal principal, String opt) throws SQLException;
	
	public String packMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;
	
	public String savePackMt(PackMt packMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,Integer pPartyIds,Integer pLocationIds,String vTranDate) throws ParseException;
	
	public String rateMasterListing(Integer partyId,Integer packMtId);
	
	public String getDtItemSummary(Integer mtId);
	
	public String stockPackingTransferListing(Integer deptId);


}
