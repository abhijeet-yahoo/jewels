package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockCompDtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;

@Service
@Repository
@Transactional
public class StockCompDtService implements IStockCompDtService {
	
	@Autowired
	private IStockCompDtRepository stockCompDtRepository;

	@Override
	public void save(StockCompDt stockCompDt) {
		// TODO Auto-generated method stub
		stockCompDtRepository.save(stockCompDt);
	}

	@Override
	public void delete(int id) {
		StockCompDt stockCompDt= stockCompDtRepository.findOne(id);
		stockCompDt.setDeactive(true);
		stockCompDt.setDeactiveDt(new Date());
		stockCompDtRepository.save(stockCompDt);
		
	}

	@Override
	public StockCompDt findOne(int id) {
		// TODO Auto-generated method stub
		return stockCompDtRepository.findOne(id);
	}

	@Override
	public List<StockCompDt> findByStockMtAndDeactive(StockMt stockMt, Boolean deactive) {
		// TODO Auto-generated method stub
		return stockCompDtRepository.findByStockMtAndDeactive(stockMt, deactive);
	}

}
