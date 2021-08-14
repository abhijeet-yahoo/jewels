package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCostLabour;

public interface IDesignCostLabourService {
	
	public List<DesignCostLabour> findByDesignAndDeactive(Design design,Boolean deactive);
	
	public void save(DesignCostLabour designCostLabour);
	
	public void deleteAll(List<DesignCostLabour> designCostLabours);

}
