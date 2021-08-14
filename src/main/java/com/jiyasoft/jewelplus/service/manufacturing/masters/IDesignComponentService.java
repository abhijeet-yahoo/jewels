package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;

public interface IDesignComponentService {

	public List<DesignComponent> findAll();

	public Page<DesignComponent> findAll(Integer limit, Integer offset, String sort,
			String order, String search);

	public void save(DesignComponent designComponent);

	public void delete(int id);

	public Long count();

	public Long count(String colName, String colValue, Boolean onlyActive);

	public DesignComponent findOne(int id);

//	public DesignComponent findByDesign(Design design);

	public Map<Integer, String> getDesignComponentList();

	public List<DesignComponent> findByDesign(Design design);

	public Page<DesignComponent> findByDesign(Design design, Integer limit, Integer offset, String sort,
			String order, String search);

}
