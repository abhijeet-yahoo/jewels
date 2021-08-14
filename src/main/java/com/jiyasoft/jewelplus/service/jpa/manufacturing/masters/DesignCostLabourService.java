package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCostLabour;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignCostLabourRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignCostLabourService;

@Service
@Repository
@Transactional
public class DesignCostLabourService implements IDesignCostLabourService{

	
	@Autowired
	IDesignCostLabourRepository designCostLabourRepository;
	
	
	@Override
	public List<DesignCostLabour> findByDesignAndDeactive(Design design,
			Boolean deactive) {
		return designCostLabourRepository.findByDesignAndDeactive(design, deactive);
	}
	
	@Override
	public void save(DesignCostLabour designCostLabour) {
		designCostLabourRepository.save(designCostLabour);
	}
	
	
	@Override
	public void deleteAll(List<DesignCostLabour> designCostLabours) {
		designCostLabourRepository.deleteInBatch(designCostLabours);
		
	}
	
}
