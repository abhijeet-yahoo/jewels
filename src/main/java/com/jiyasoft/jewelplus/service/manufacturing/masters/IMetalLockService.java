package com.jiyasoft.jewelplus.service.manufacturing.masters;


import java.util.Date;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.MetalLock;

public interface IMetalLockService {
	
	public void save(MetalLock metalLock);

	public void delete(int id);
	
	public MetalLock findOne(int id);
	
	public Page<MetalLock> searchAll(Integer limit, Integer offset, String sort, String order, String search,
			Boolean onlyActive);
	
	
	public MetalLock findByMetalLockDtAndMetalAndDeactive(Date lockDate,Metal metal,Boolean deactive);


}
