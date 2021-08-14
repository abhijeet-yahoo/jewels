package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;

public interface IStockMtService {
	public void save(StockMt stockMt);
	public void delete(int id);
	public StockMt findOne(int id);
	public StockMt findByBarcodeAndCurrStkAndDeactive(String barcode,Boolean currstk,Boolean deactive);

	public String stockExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile, Principal principal) throws ParseException;
	
	public List<StockMt> findByLocationAndCurrStkAndDeactive(Department location,Boolean currstk,Boolean deactive);

	public Page<StockMt> barcodeAutofill(Integer limit, Integer offset, String sort, String order, String name, Boolean onlyActive, Integer locationId);

	public Page<StockMt> barcodeSearchAutofill(Integer limit, Integer offset, String sort, String order, String name, Boolean onlyActive);

	public String barcodeHistoryList(String barcode) throws ParseException;
	
	public StockMt findByRefTranIdAndTranTypeAndDeactive(Integer refTranId,String TranType,Boolean deactive);

	public Page<StockMt> fgRetBarcodeAutofill(Integer limit, Integer offset, String sort, String order, String name, Boolean onlyActive, Integer locationId);

	public String barcodeHistoryStoneList(String barcode);
	
	public String barcodeComponentList(String barcode);
	
	public List<StockMt> findByBarcodeAndDeactiveOrderByMtIdDesc(String barcode,Boolean deactive);
	
	public StockMt findByBarcodeAndCurrStkAndDeactiveAndLocation(String barcode,Boolean currstk,Boolean deactive,Department location);

	public String barcodeAttachToStock(Principal principal,String bagTblData);
}
