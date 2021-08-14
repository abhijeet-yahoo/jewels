package com.jiyasoft.jewelplus.service.manufacturing.transactions;


import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;

public interface IStockStnDtService {
	
	public void save(StockStnDt stockStnDt);
	public void delete(int id);
	public StockStnDt findOne(int id);
	
	public List<StockStnDt> findByStockMtAndDeactive(StockMt stockMt,Boolean deactive);
	

}
