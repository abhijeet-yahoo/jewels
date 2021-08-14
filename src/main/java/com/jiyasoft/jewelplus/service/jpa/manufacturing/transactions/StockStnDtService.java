package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockStnDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;

@Service
@Repository
@Transactional
public class StockStnDtService implements IStockStnDtService {
	
	@Autowired
	private IStockStnDtRepository stockStnDtRepository;

	@Override
	public void save(StockStnDt stockStnDt) {
		// TODO Auto-generated method stub
		stockStnDtRepository.save(stockStnDt);
	}

	@Override
	public void delete(int id) {
		StockStnDt stockStnDt= stockStnDtRepository.findOne(id);
		stockStnDt.setDeactive(true);
		stockStnDt.setDeactiveDt(new Date());
		stockStnDtRepository.save(stockStnDt);
	}

	@Override
	public StockStnDt findOne(int id) {
		// TODO Auto-generated method stub
		return stockStnDtRepository.findOne(id);
	}

	@Override
	public List<StockStnDt> findByStockMtAndDeactive(StockMt stockMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return stockStnDtRepository.findByStockMtAndDeactive(stockMt, deactive);
	}

}
