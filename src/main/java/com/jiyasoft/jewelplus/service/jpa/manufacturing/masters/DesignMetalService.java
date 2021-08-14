package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDesignMetal;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDesignMetalRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.mysema.query.jpa.impl.JPAUpdateClause;


@Service
@Repository
@Transactional
public class DesignMetalService implements IDesignMetalService {
	
	@Autowired
	IDesignMetalRepository designMetalRepository;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public void save(DesignMetal designMetal) {
		designMetalRepository.save(designMetal);
	}

	@Override
	public void delete(int id) {
		DesignMetal designMetal =designMetalRepository.findOne(id);
		designMetal.setDeactive(true);
		designMetal.setDeactiveDt(new java.util.Date());
		designMetalRepository.save(designMetal);
	}

	@Override
	public DesignMetal findOne(int id) {
		return designMetalRepository.findOne(id);
	}

	
	@Override
	public List<DesignMetal> findByDesignAndDeactive(Design design,
			Boolean deactive) {
		return designMetalRepository.findByDesignAndDeactive(design, deactive);
	}

	@Override
	public DesignMetal findByDesignAndDeactiveAndMainMetal(Design design,
			Boolean deactive, Boolean mainMetal) {
		return designMetalRepository.findByDesignAndDeactiveAndMainMetal(design, deactive, mainMetal);
	}

	
	@Override
	public DesignMetal findByDesignAndPartNmAndDeactive(Design design,
			LookUpMast lookUpMast, Boolean deactive) {
		return designMetalRepository.findByDesignAndPartNmAndDeactive(design, lookUpMast, deactive);
	}
	
	
	

}
