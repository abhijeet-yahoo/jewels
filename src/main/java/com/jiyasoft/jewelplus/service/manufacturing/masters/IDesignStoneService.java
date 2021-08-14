package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.mysema.query.Tuple;

public interface IDesignStoneService {

	public List<DesignStone> findAll();

	public Page<DesignStone> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(DesignStone designStone);

	public void delete(int id);

	public Long count();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public DesignStone findOne(int id);

	public List<DesignStone> findByDesign(Design design);

	public Map<Integer, String> getDesignStoneList();

	public Page<DesignStone> findByDesign(Design design, Integer limit, Integer offset, String sort,
			String order, String search);
	
	public List<Tuple> getDesignStoneSumList(Integer styleId);

}
