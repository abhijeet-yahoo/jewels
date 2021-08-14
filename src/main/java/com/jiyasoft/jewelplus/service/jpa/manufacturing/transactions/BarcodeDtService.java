package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IBarcodeDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBarcodeDtService;

@Service
@Repository
@Transactional
public class BarcodeDtService implements IBarcodeDtService {

	@Autowired
	private IBarcodeDtRepository barcodeDtRepository;

	@Override
	public BarcodeDt findOne(int id) {
		
		return barcodeDtRepository.findOne(id);
	}

	@Override
	public void save(BarcodeDt barcodeDt) {
		barcodeDtRepository.save(barcodeDt);
		}

	@Override
	public void delete(int id) {
		BarcodeDt barcodeDt = barcodeDtRepository.findOne(id);
		barcodeDt.setDeactive(true);
		barcodeDt.setDeactiveDt(new java.util.Date());
		barcodeDtRepository.save(barcodeDt);
		
	}

	@Override
	public List<BarcodeDt> findByBarcodeMt(BarcodeMt barcodeMt) {
		
		return barcodeDtRepository.findByBarcodeMt(barcodeMt);
	}
	
	
}
