package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.json.JSONArray;
import org.springframework.data.domain.Page;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;


public interface ICastingDtService {
	
	public List<CastingDt> findAll();

	public Page<CastingDt> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(CastingDt castingDt);

	public void delete(int id);

	public Long count();

	public CastingDt findOne(int id);
	
	public List<CastingDt> findByCastingMtAndDeactive(CastingMt castingMt,Boolean deactive);
	
	public List<CastingDt> findByCastingMtAndTransferAndDeactive(CastingMt castingMt, Boolean transfer,Boolean deactive);
	
	public List<CastingDt> findByBagMtAndDeactive(BagMt bagMt,Boolean deactive);
	
	
	public String transferToCasting(JSONArray tblData,Integer castMtId,Principal principal);
	
	
	public String castingToDepartmentTransfer(String pOIds,CastingDt castingDt,String metalWtt,Principal principal);
	
	public String CastingDelete(Integer id);
	
	public CastingDt findByRefMtIdAndDeactive(Integer refMtId,Boolean deactive);
	
	public List<CastingDt> getCastingDtListBagWise(Integer castMtId);
	
	public List<CastingDt> findByTransferTranMtIdAndDeactive(Integer transferTranMtId,Boolean deactive);
	
	public String addCastingBagListing(Integer castMtId);
	
	public String castingDtListing(String treeNo);

}
