package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;


public interface IStockMetalDtService {
	public void save(StockMetalDt stockMetalDt);
	public void delete(int id);
	public StockMetalDt findOne(int id);
	
	public List<StockMetalDt> findByStockMtAndDeactive(StockMt stockMt,Boolean deactive);
	
}
