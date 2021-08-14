package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;

public interface ICastingMtService {

	public List<CastingMt> findAll();

	public Page<CastingMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public Page<CastingMt> findByTreeNo(Integer limit, Integer offset, String sort, String order, String name, Boolean onlyActive);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public void save(CastingMt castingMt);

	public void delete(int id);

	public Long count();

	public CastingMt findOne(int id);

	public Map<Integer, String> getCastingMtList(Date date);

	public List<CastingMt> findActivecastingMt(Date date);
	
	public CastingMt findByCDateAndTreeNo(Date cDate,String treeNo);
	
	public CastingMt findByUniqueId(Long uniqueId);
	
	public List<CastingMt> findByCDate(Date cDAte);
	
	public String castingMtSave(CastingMt castingMt,Integer id, Double freshIssWt,Double prevIssWt,Double pcsWt,Double prevPcsWt,Integer prevKt,Integer prevColor,
			Double vReqWt,Principal principal,RedirectAttributes redirectAttributes );
	

}
