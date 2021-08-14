package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;



public interface IHandlingMasterService {

	public List<HandlingMaster> findAll();

	public Page<HandlingMaster> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(HandlingMaster handlingMaster);

	public void delete(int id);

	public Long count();

	public HandlingMaster findOne(int id);
	
	public Long count(String colName, String colValue, Boolean onlyActive);
	
	public Page<HandlingMaster> findByPartyAndShap(Integer limit, Integer offset, String sort,
			String order, String name, Boolean onlyActive);
	
	public HandlingMaster findByPartyAndMetalAndStoneTypeAndShapeAndDeactive(Party party,Metal metal, StoneType stoneType, Shape shape, Boolean deactive);
	
	
}
