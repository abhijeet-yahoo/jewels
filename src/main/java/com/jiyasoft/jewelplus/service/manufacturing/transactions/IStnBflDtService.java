package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;



public interface IStnBflDtService {

	
	public List<StnBflDt> findAll();

	public Page<StnBflDt> findAll(Integer limit, Integer offset,
			String sort, String order, String search);

	public void save(StnBflDt stnBflDt);

	public void delete(int id);

	public Long count();

	public StnBflDt findOne(int id);

	public Map<Integer, String> getStnBflDtList();

	public List<StnBflDt> findByStnBflMt(StnBflMt stnBflMt);

	public Page<StnBflDt> findByStnBflMt(StnBflMt stnBflMt,
			Integer limit, Integer offset, String sort, String order,
			String search);

	public StnBflDt findByUniqueId(Long uniqueId);

	public List<StnBflDt> findByStnBflMtAndDeactiveOrderByIdDesc(StnBflMt stnBflMt, Boolean deactive);

	public List<StnBflDt> findByStoneTypeAndShapeAndDeactive(StoneType stoneType,Shape shape,Boolean Deactive);
	
	public void delete(StoneInwardDt stnInwardDt,Integer id);
	
	public void save(List<StoneInwardDt> stnInwardDts,List<StnBflDt> stnBflDts);
	
	public String deleteDt(Integer id,Principal principal);

	
}
