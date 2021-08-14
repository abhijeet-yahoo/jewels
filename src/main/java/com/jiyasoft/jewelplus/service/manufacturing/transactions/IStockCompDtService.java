package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;


public interface IStockCompDtService {
	
	public void save(StockCompDt stockCompDt);
	public void delete(int id);
	public StockCompDt findOne(int id);
	
	public List<StockCompDt> findByStockMtAndDeactive(StockMt stockMt,Boolean deactive);

}
