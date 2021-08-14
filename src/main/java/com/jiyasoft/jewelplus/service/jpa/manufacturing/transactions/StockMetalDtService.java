package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMetalDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;

@Service
@Repository
@Transactional
public class StockMetalDtService implements IStockMetalDtService {

	@Autowired
	private IStockMetalDtRepository stockMetalDtRepository;
	
	@Override
	public void save(StockMetalDt stockMetalDt) {
		// TODO Auto-generated method stub
		stockMetalDtRepository.save(stockMetalDt);
		
	}

	@Override
	public void delete(int id) {
		StockMetalDt stockMetalDt= stockMetalDtRepository.findOne(id);
		stockMetalDt.setDeactive(true);
		stockMetalDt.setDeactiveDt(new Date());
		stockMetalDtRepository.save(stockMetalDt);
		
	}

	@Override
	public StockMetalDt findOne(int id) {
		// TODO Auto-generated method stub
		return stockMetalDtRepository.findOne(id);
	}

	@Override
	public List<StockMetalDt> findByStockMtAndDeactive(StockMt stockMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return stockMetalDtRepository.findByStockMtAndDeactive(stockMt, deactive);
	}

}
