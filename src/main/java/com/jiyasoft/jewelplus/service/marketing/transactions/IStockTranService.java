package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;

public interface IStockTranService {
	
	public void save(StockTran stockTran);
	public void delete(int id);
	public StockTran findOne(int id);
	
	public StockTran findByBarcodeAndCurrStk(String barcode,Boolean currStk);
	
	public StockTran findByTranTypeAndRefTranIdAndCurrStk(String tranType,Integer refTranId,Boolean currStk);
	
	public List <StockTran> findByTranTypeAndRefTranId(String tranType,Integer refTranId);
	
	public StockTran findByTranTypeAndRefTranIdAndCurrStatus(String tranType,Integer refTranId,String currStatus);

	public List <StockTran> findByBarcode(String barcode);
}
