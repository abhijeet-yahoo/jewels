package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;

public interface IMeltingIssDtService {
	
	public List<MeltingIssDt> findAll();
	
	public void save(MeltingIssDt meltingIssDt);

	public void delete(int id);

	public MeltingIssDt findOne(int id);
	
	public Page<MeltingIssDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);
	
	public Long count();
	
	List<MeltingIssDt> findByMeltingMtAndDeactive(MeltingMt meltingMt,Boolean deactive);
	
	public MeltingIssDt findByUniqueId(Long uniqueId);
	
	public String meltingPickupsave(Integer mtId,JSONArray tblData,Principal principal);
	
	public String meltingIssDtDelete(Integer id);
	
	public List<MeltingIssDt> findByMeltingMtAndDeactiveAndBagMt(MeltingMt meltingMt,Boolean deactive,BagMt bagMt);
	
	public String meltingIssDtSave(MeltingIssDt meltingIssDt,Integer id,String pInvNo,Double prevNetWt,Double pFMetalWt,Double prevKt,Double prevColor,Double currNetWt,Double pExcpPureWt,Principal principal);
	
	public List<Object[]> meltingStnRecList(Integer mtid, Integer deptid);
	
	public String meltingStnTransfer(Integer mtid, String jsonData,Principal principal,Integer locationId);
	
	public String stockIssueToMeltingTransfer(Integer mtId, String stockDtId,Principal principal);
	
	public String meltingIssDtListing(Principal principal,String pInvNo,Boolean canViewFlag) throws ParseException;

	public MeltingIssDt findByTranTypeAndRefTranMetalIdAndDeactive(String tranType, Integer refTranMetalId,Boolean deactive);
	
	public List<MeltingIssDt> findByMeltingMtAndBarcodeAndDeactive(MeltingMt meltingMt,String barcode,Boolean deactive);
}
