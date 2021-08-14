package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IStockTranRepository;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;

@Service
@Repository
@Transactional
public class StockTranService implements IStockTranService {
	
	@Autowired
	private IStockTranRepository stockTranRepository;

	@Override
	public void save(StockTran stockTran) {
		// TODO Auto-generated method stub
		stockTranRepository.save(stockTran);
		
	}

	@Override
	public void delete(int id) {
		stockTranRepository.delete(id);
	
	}

	@Override
	public StockTran findOne(int id) {
		// TODO Auto-generated method stub
		return stockTranRepository.findOne(id);
	}

	@Override
	public StockTran findByBarcodeAndCurrStk(String barcode, Boolean currStk) {
		// TODO Auto-generated method stub
		return stockTranRepository.findByBarcodeAndCurrStk(barcode, currStk);
	}

	@Override
	public StockTran findByTranTypeAndRefTranIdAndCurrStk(String tranType, Integer refTranId,
			 Boolean currStk) {
		// TODO Auto-generated method stub
		return stockTranRepository.findByTranTypeAndRefTranIdAndCurrStk(tranType, refTranId, currStk);
	}

	@Override
	public List<StockTran> findByBarcode(String barcode) {
		// TODO Auto-generated method stub
		return stockTranRepository.findByBarcode(barcode);
	}

	@Override
	public StockTran findByTranTypeAndRefTranIdAndCurrStatus(String tranType, Integer refTranId, String currStatus) {
		// TODO Auto-generated method stub
		return stockTranRepository.findByTranTypeAndRefTranIdAndCurrStatus(tranType, refTranId, currStatus);
	}

	@Override
	public List<StockTran> findByTranTypeAndRefTranId(String tranType, Integer refTranId) {
		// TODO Auto-generated method stub
		return stockTranRepository.findByTranTypeAndRefTranId(tranType, refTranId);
	}


}
