package com.jiyasoft.jewelplus.service.jpa.manufacturing.masters;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DiaWtTagRangeMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QDiaWtTagRangeMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.repository.manufacturing.masters.IDiaWtTagRangeRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDiaWtTagRangeService;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Repository
@Transactional
public class DiaWtTagRangeService implements IDiaWtTagRangeService {
	
	@Autowired
	private IDiaWtTagRangeRepository diaWtTagRangeRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public DiaWtTagRangeMaster findOne(int id) {
		// TODO Auto-generated method stub
		return diaWtTagRangeRepository.findOne(id);
	}

	@Override
	public void save(DiaWtTagRangeMaster diaWtTagRangeMaster) {
		// TODO Auto-generated method stub
		diaWtTagRangeRepository.save(diaWtTagRangeMaster);
	}

	@Override
	public void delete(int id) {
		
		DiaWtTagRangeMaster diaWtTagRangeMaster = diaWtTagRangeRepository.findOne(id);
		diaWtTagRangeMaster.setDeactive(true);
		diaWtTagRangeMaster.setDeactiveDt(new Date());
		diaWtTagRangeRepository.save(diaWtTagRangeMaster);
		
	}

	@Override
	public List<DiaWtTagRangeMaster> getDiaWt(Double diaWt) {
		// TODO Auto-generated method stub
		
		QDiaWtTagRangeMaster qDiaWtTagRangeMaster = QDiaWtTagRangeMaster.diaWtTagRangeMaster;
		JPAQuery query = new JPAQuery(entityManager);
		
		
		List<DiaWtTagRangeMaster> diaWtTagRangeMasters=query.from(qDiaWtTagRangeMaster)
				.where(qDiaWtTagRangeMaster.deactive.eq(false).and(qDiaWtTagRangeMaster.fromCts.lt(diaWt).or(qDiaWtTagRangeMaster.fromCts.eq(diaWt))).
						and(qDiaWtTagRangeMaster.toCts.gt(diaWt).or(qDiaWtTagRangeMaster.toCts.eq(diaWt)))).list(qDiaWtTagRangeMaster);
		
		
		
		return diaWtTagRangeMasters;
	}

}
