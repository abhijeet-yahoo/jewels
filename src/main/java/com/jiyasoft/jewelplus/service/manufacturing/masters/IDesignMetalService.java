package com.jiyasoft.jewelplus.service.manufacturing.masters;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;



public interface IDesignMetalService {
	
	public void save(DesignMetal designMetal);

	public void delete(int id);
		
	public DesignMetal findOne(int id);

	public List<DesignMetal> findByDesignAndDeactive(Design design,Boolean deactive);
	
	public DesignMetal findByDesignAndDeactiveAndMainMetal(Design design,Boolean deactive,Boolean mainMetal);
	
	public DesignMetal findByDesignAndPartNmAndDeactive(Design design,LookUpMast lookUpMast,Boolean deactive);

}
