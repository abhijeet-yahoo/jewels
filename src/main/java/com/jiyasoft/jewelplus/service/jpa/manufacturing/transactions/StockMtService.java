package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QStockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IStockMtRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockTranService;
import com.mysema.query.types.expr.BooleanExpression;

@Service
@Repository
@Transactional
public class StockMtService implements IStockMtService {
	
	@Autowired
	private IStockMtRepository stockMtRepository;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IDesignService designService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IStockMetalDtService stockMetalDtService;
	
	@Autowired
	private IStockStnDtService stockStnDtService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private IStockTranService stockTranService;
	

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IStockCompDtService stockCompDtService;
	
	@Autowired
	private IBagMtService bagMtService;
	

	@Override
	public void save(StockMt stockMt) {
		stockMtRepository.save(stockMt);
	}

	@Override
	public void delete(int id) {
		StockMt stockMt= stockMtRepository.findOne(id);
		stockMt.setDeactive(true);
		stockMt.setDeactiveDt(new Date());
		stockMtRepository.save(stockMt);
		
		
	}

	@Override
	public StockMt findOne(int id) {
		// TODO Auto-generated method stub
		return stockMtRepository.findOne(id);
	}

	@Override
	public StockMt findByBarcodeAndCurrStkAndDeactive(String barcode, Boolean currstk, Boolean deactive) {
		// TODO Auto-generated method stub
		return stockMtRepository.findByBarcodeAndCurrStkAndDeactive(barcode, currstk, deactive);
	}

	@Override
	public String stockExcelUpload(MultipartFile excelfile, HttpSession session, String tempExcelFile,
			Principal principal) throws ParseException {
		// TODO Auto-generated method stub
		
		synchronized (this) {
			
			String retVal ="";
			
			Boolean remarkFlg = false;
			
			  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			
			
			try {
				if(!tempExcelFile.isEmpty()){
				
				List<StockExcel> stockExcelList = new ArrayList<StockExcel>();
				
				
				    if (tempExcelFile.endsWith("xlsx")) {
				    	
				    	int i=1;
				    	String barcodeStr =null;
				    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				        XSSFSheet worksheet = workbook.getSheetAt(0);
						while (i <= worksheet.getLastRowNum()) {
							
							StockExcel stockExcel = new StockExcel();
							XSSFRow row = worksheet.getRow(i++);
					
							
							if( row != null) {
							
								String remark = "";	
								
								
								
								Shape shape = null;
								Cell shapeCell = row.getCell(8, row.RETURN_BLANK_AS_NULL);
								if (shapeCell != null) {
									if (row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() != "") {
										shape = shapeService.findByName(row.getCell(8).toString().trim());	
										if(shape == null){
											if(remark == ""){
												remark = "No such shape exists"; 
											}else {
												remark += ",No such shape exists";
											}
											
										}
									}
								}
								/*
								 * else { if(remark == ""){ remark = "Shape compulsory"; }else { remark +=
								 * ",Shape compulsory"; } }
								 */
							
								
								
								Cell qualityCell = row.getCell(10, row.RETURN_BLANK_AS_NULL);
								if (qualityCell != null) {
									if (row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Quality quality = qualityService.findByShapeAndName(shape,row.getCell(10).toString().trim());
										if(quality == null){
											if(remark == ""){
												remark = "No such quality exists";
											}else {
												remark += ",No such quality exists ";
											}
										
										}
									}
								}
								
								
								
								Cell sieveCell = row.getCell(9, row.RETURN_BLANK_AS_NULL);
								if (sieveCell != null)  {
									
									if (row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() != "") {
										StoneChart stoneChart = stoneChartService.findByShapeAndSieveAndDeactive(shape, row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString().trim() :null, false);
										if(stoneChart == null){
											if(remark == ""){
												remark = "No such Sieve exists"; 
											}else {
												remark += ",No such Sieve exists";
											}
											
										}
									}
								}
								
								
								Cell stoneTypeCell = row.getCell(7, row.RETURN_BLANK_AS_NULL);
								if (stoneTypeCell != null) {
									if (row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() != "") {
										StoneType stoneType = stoneTypeService.findByName(row.getCell(7).toString().trim());
										if(stoneType == null){
											if(remark == ""){
												remark = "No such stoneType exists";
											}else {
												remark += ",No such stoneType exists ";
											}
										
										}
									}
								}
								
								
								

								Cell barcodeCell = row.getCell(0, row.RETURN_BLANK_AS_NULL);
								if (barcodeCell != null) {
									
									barcodeStr =row.getCell(0, row.RETURN_BLANK_AS_NULL).toString();
									
									List<StockMt> stockMts = findByBarcodeAndDeactiveOrderByMtIdDesc(row.getCell(0, row.RETURN_BLANK_AS_NULL).toString().trim(), false);
									if(stockMts.size() > 0) {
										if (remark == "") {
											remark = "Barcode already exists";
										} else {
											remark += ",Barcode already exists";
										}
									}
								
								Cell memoPartyCell = row.getCell(2, row.RETURN_BLANK_AS_NULL);
								if (memoPartyCell != null) {
									if (row.getCell(2, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Party party = partyService.findByPartyCodeAndDeactive(row.getCell(2).toString().trim(), false);
										if (party == null) {
											if (remark == "") {
												remark = "No such party exists";
											} else {
												remark += ",No such party exists";
											}

										}
									}
								}
								
								Cell departmentCell = row.getCell(1, row.RETURN_BLANK_AS_NULL);
								if (departmentCell != null) {
									if (row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Department department = departmentService.findByName(row.getCell(1).toString().trim());
										if (department == null) {
											if (remark == "") {
												remark = "No such department exists";
											} else {
												remark += ",No such department exists";
											}

										}
									}
								}
								
								
								
								Cell designCell = row.getCell(3, row.RETURN_BLANK_AS_NULL);
								if (designCell != null) {
									if (row.getCell(3, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Design design = designService.findByDesignNoAndDeactive(row.getCell(3).toString().trim(),false);
										if(design == null){
											if(remark == ""){
												remark = "No such design exists"; 
											}else {
												remark += ",No such design exists";
											}
										}
									}
								}else {
									if(remark == ""){
										remark = "Design Compulsory"; 
									}else {
										remark += ",Design compulsory";
									}
								}
								
								
								Cell purityCell = row.getCell(4, row.RETURN_BLANK_AS_NULL);
								if (purityCell != null) {
									if (row.getCell(4, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Purity purity =  purityService.findByName(row.getCell(4).toString().trim());
										if(purity == null){
											if(remark == ""){
												remark = "No such purity exists"; 
											}else {
												remark += ",No such purity exists";
											}
											
										}
									}
								}
								
								Cell colorCell = row.getCell(5, row.RETURN_BLANK_AS_NULL);
								if (colorCell != null) {
									if (row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Color color = colorService.findByName(row.getCell(5).toString().trim());
										if(color == null){
											if(remark == ""){
												remark = "No such color exists"; 
											}else {
												remark += ",No such color exists";
											}
											
										}
									}
								}
								
								Cell caegoryCell = row.getCell(6, row.RETURN_BLANK_AS_NULL);
								if (caegoryCell != null) {
									if (row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Category category = categoryService.findByName(row.getCell(6).toString().trim());
										if(category == null){
											if(remark == ""){
												remark = "No such category exists"; 
											}else {
												remark += ",No such category exists";
											}
											
										}
									}
								}
								
							}
							
								
							
							if (remark != "") {
								
								
								remarkFlg = true;
								
								stockExcel.setBarcode(barcodeStr);
								stockExcel.setMemoParty(row.getCell(2, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(2, row.RETURN_BLANK_AS_NULL).toString() :"");
								stockExcel.setDesignNo(row.getCell(3, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(3, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setPurity(row.getCell(4, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(4, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setColor(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setCategory(row.getCell(6, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setShape(row.getCell(8, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setSieve(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setQuality(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setDepartment(row.getCell(1, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(1, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setStonetype(row.getCell(7, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setStatusRemark(remark);
									
									remark ="";
									stockExcelList.add(stockExcel);
								}
								
								
							}
							
							
							
						}
													
					
						
						workbook.close();
						
				    } else if (tempExcelFile.endsWith("xls")) {
				    	int i = 1;
						HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
						HSSFSheet worksheet = workbook.getSheetAt(0);
						while (i <= worksheet.getLastRowNum()) {
							
							StockExcel stockExcel = new StockExcel();
							
							HSSFRow row = worksheet.getRow(i++);
							if(row.getCell(0).toString()=="" || row.getCell(0).toString()==null ){
								continue;
							}
							
							if( row != null) {
								
								String remark = "";	
								
								Cell barcodeCell = row.getCell(0, row.RETURN_BLANK_AS_NULL);
								if (barcodeCell != null) {
									
									
									List<StockMt> stockMts = findByBarcodeAndDeactiveOrderByMtIdDesc(row.getCell(0, row.RETURN_BLANK_AS_NULL).toString().trim(), false);
									if(stockMts.size() > 0) {
										if (remark == "") {
											remark = "Barcode already exists";
										} else {
											remark += ",Barcode already exists";
										}
									}
								
								Cell memoPartyCell = row.getCell(2, row.RETURN_BLANK_AS_NULL);
								if (memoPartyCell != null) {
									if (row.getCell(2, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Party party = partyService.findByPartyCodeAndDeactive(row.getCell(2).toString().trim(), false);
										if (party == null) {
											if (remark == "") {
												remark = "No such party exists";
											} else {
												remark += ",No such party exists";
											}

										}
									}
								}
								
								
								Cell designCell = row.getCell(3, row.RETURN_BLANK_AS_NULL);
								if (designCell != null) {
									if (row.getCell(3, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Design design = designService.findByDesignNoAndDeactive(row.getCell(3).toString(),false);
										if(design == null){
											if(remark == ""){
												remark = "No such design exists"; 
											}else {
												remark += ",No such design exists";
											}
										}
									}
								}else {
									if(remark == ""){
										remark = "Design Compulsory"; 
									}else {
										remark += ",Design compulsory";
									}
								}
								
								
								Cell purityCell = row.getCell(4, row.RETURN_BLANK_AS_NULL);
								if (purityCell != null) {
									if (row.getCell(4, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Purity purity =  purityService.findByName(row.getCell(4).toString());
										if(purity == null){
											if(remark == ""){
												remark = "No such purity exists"; 
											}else {
												remark += ",No such purity exists";
											}
											
										}
									}
								}
								
								Cell colorCell = row.getCell(5, row.RETURN_BLANK_AS_NULL);
								if (colorCell != null) {
									if (row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() != "") {

										Color color = colorService.findByName(row.getCell(5).toString());
										if(color == null){
											if(remark == ""){
												remark = "No such color exists"; 
											}else {
												remark += ",No such color exists";
											}
											
										}
									}
								}
								
								Cell caegoryCell = row.getCell(6, row.RETURN_BLANK_AS_NULL);
								if (caegoryCell != null) {
									if (row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Category category = categoryService.findByName(row.getCell(6).toString());
										if(category == null){
											if(remark == ""){
												remark = "No such category exists"; 
											}else {
												remark += ",No such category exists";
											}
											
										}
									}
								}
								
								}
								
								Shape shape = null;
								Cell shapeCell = row.getCell(8, row.RETURN_BLANK_AS_NULL);
								if (shapeCell != null) {
									if (row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() != "") {
										shape = shapeService.findByName(row.getCell(8).toString());	
										if(shape == null){
											if(remark == ""){
												remark = "No such shape exists"; 
											}else {
												remark += ",No such shape exists";
											}
											
										}
									}
								}else {
									if(remark == ""){
										remark = "Shape compulsory"; 
									}else {
										remark += ",Shape compulsory";
									}
								}
							
								
								Cell qualityCell = row.getCell(10, row.RETURN_BLANK_AS_NULL);
								if (qualityCell != null) {
									if (row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() != "") {
										Quality quality = qualityService.findByShapeAndName(shape,row.getCell(10).toString());
										if(quality == null){
											if(remark == ""){
												remark = "No such quality exists";
											}else {
												remark += ",No such quality exists ";
											}
										
										}
									}
								}
								
							
							
							
							
							if (remark != "") {
								
								
								remarkFlg = true;
								
								stockExcel.setBarcode(row.getCell(0, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(0, row.RETURN_BLANK_AS_NULL).toString() :"");
								stockExcel.setMemoParty(row.getCell(2, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(2, row.RETURN_BLANK_AS_NULL).toString() :"");
								stockExcel.setDesignNo(row.getCell(3, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(3, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setPurity(row.getCell(4, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(4, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setColor(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setCategory(row.getCell(6, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(6, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setShape(row.getCell(8, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(8, row.RETURN_BLANK_AS_NULL).toString() :"");
									stockExcel.setQuality(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString() :"");
									
									stockExcel.setStatusRemark(remark);
									
									remark ="";
									stockExcelList.add(stockExcel);
								}
								
							}
					}
					
						workbook.close();
						
				    } else {
				        throw new IllegalArgumentException("The specified file is not Excel file");
				    }
				    
				    
				    // Create Design
				    
				    if (remarkFlg) {
				    	 session.setAttribute("stockExcelSessionList", stockExcelList);  
				    	 retVal="yes";
				    	
				    }else {
				    	
				    	
				    	   if (tempExcelFile.endsWith("xlsx")) {
				    		 
				    		   int i=1;
						    	XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
						        XSSFSheet worksheet = workbook.getSheetAt(0);
						        
						        Integer mtId=0;
						        Double stoneVal=0.0;
								while (i <= worksheet.getLastRowNum()) {
									
									XSSFRow row = worksheet.getRow(i++);
									
									if(row != null) {
										
								 
								
									//note : temporary list
									//remark is set in createdBy for temporary list
									//status is set in updatedBy for temporary list
											
									
									Shape shape = null;
									if(row.getCell(8, row.RETURN_BLANK_AS_NULL) != null) {
										shape = shapeService.findByName(row.getCell(8, row.RETURN_BLANK_AS_NULL).toString().trim());
									}
									
									
									Quality quality = null;
									if(shape != null) {
									if(row.getCell(10, row.RETURN_BLANK_AS_NULL) != null) {
									
											quality  = qualityService.findByShapeAndName(shape,row.getCell(10, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(10, row.RETURN_BLANK_AS_NULL).toString().trim() :null);	
										}
									}
									
									StoneType stoneType = stoneTypeService.findByName(row.getCell(7, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(7, row.RETURN_BLANK_AS_NULL).toString() :null);
								
									StoneChart stoneChart = null;
									SizeGroup sizeGroup = null;
									String sizeNm = null;
									if(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null) {
										stoneChart = stoneChartService.findByShapeAndSieveAndDeactive(shape, row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString().trim() :null, false);
										if(stoneChart != null) {
											sizeGroup =stoneChart.getSizeGroup();
											sizeNm = stoneChart.getSize();
										} 
										
									}
									
									
									
										if(row.getCell(0, row.RETURN_BLANK_AS_NULL) != null) {
											
											stoneVal = 0.0;
											
											Purity purity = purityService.findByName(row.getCell(4, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(4, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											
											Color color = colorService.findByName(row.getCell(5, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(5, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											
											Category category = categoryService.findByName(row.getCell(6, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(6, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
										
											Party party = partyService.findByPartyCodeAndDeactive(row.getCell(2, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(2, row.RETURN_BLANK_AS_NULL).toString().trim() :null,false);
											
											Department department = departmentService.findByName(row.getCell(1, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(1, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
											
											Design design = designService.findByMainStyleNoAndDeactive(row.getCell(3, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(3, row.RETURN_BLANK_AS_NULL).toString().trim() :null,false);	
													
											
										StockMt stockMt =  new StockMt();
										stockMt.setBarcode(row.getCell(0, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(0, row.RETURN_BLANK_AS_NULL).toString().trim() :null);
										stockMt.setLocation(department);
										stockMt.setMemoParty(party);
										stockMt.setDesign(design);
										stockMt.setCategory(category);
										stockMt.setMetalValue(row.getCell(19, row.RETURN_BLANK_AS_NULL) != null ?  Math.round(Double.parseDouble(row.getCell(19, row.RETURN_BLANK_AS_NULL).toString())*100.0)/100.0 :0.0);
										stockMt.setLabourValue(row.getCell(20, row.RETURN_BLANK_AS_NULL) != null ?  Math.round(Double.parseDouble(row.getCell(20, row.RETURN_BLANK_AS_NULL).toString())*100.0)/100.0 :0.0);
										stockMt.setFactoryCost(row.getCell(21, row.RETURN_BLANK_AS_NULL) != null ?  Math.round(Double.parseDouble(row.getCell(21, row.RETURN_BLANK_AS_NULL).toString())*100.0)/100.0 :0.0);
										stockMt.setNetWt((row.getCell(17, row.RETURN_BLANK_AS_NULL) != null ?  Math.round(Double.parseDouble(row.getCell(17, row.RETURN_BLANK_AS_NULL).toString())*1000.0)/1000.0 :0.0));
										stockMt.setGrossWt((row.getCell(16, row.RETURN_BLANK_AS_NULL) != null ?  Math.round(Double.parseDouble(row.getCell(16, row.RETURN_BLANK_AS_NULL).toString())*1000.0)/1000.0 :0.0));
										stockMt.setCreatedDate(new Date());
										stockMt.setCreatedBy(principal.getName());
										stockMt.setCurrStk(true);
										stockMt.setTranType("OPSTK");
										stockMt.setQty(row.getCell(15, row.RETURN_BLANK_AS_NULL) != null ? (Double.parseDouble(row.getCell(15, row.RETURN_BLANK_AS_NULL).toString())) :1);
										save(stockMt);
										
										
										StockMetalDt stockMetalDt =  new StockMetalDt();
										stockMetalDt.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
										stockMetalDt.setMainMetal(true);
										stockMetalDt.setMetalPcs(row.getCell(15, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(15, row.RETURN_BLANK_AS_NULL).toString())) :1);
										stockMetalDt.setMetalWt(row.getCell(17, row.RETURN_BLANK_AS_NULL) != null ?  Math.round(Double.parseDouble(row.getCell(17, row.RETURN_BLANK_AS_NULL).toString())*1000.0)/1000.0 :0.0);
										stockMetalDt.setPurity(purity);
										stockMetalDt.setColor(color);
										stockMetalDt.setCreatedDate(new Date());
										stockMetalDt.setCreatedBy(principal.getName());
										stockMetalDt.setStockMt(stockMt);
										stockMetalDt.setPurityConv(purity != null ? purity.getPurityConv() :0.0);
										stockMetalDt.setMetalRate((row.getCell(18, row.RETURN_BLANK_AS_NULL) != null ?  Math.round(Double.parseDouble(row.getCell(18, row.RETURN_BLANK_AS_NULL).toString())*100.0)/100.0 :0.0));
										stockMetalDt.setMetalValue((row.getCell(19, row.RETURN_BLANK_AS_NULL) != null ?  Math.round(Double.parseDouble(row.getCell(19, row.RETURN_BLANK_AS_NULL).toString())*100.0)/100.0 :0.0));
										stockMetalDtService.save(stockMetalDt);
										
										StockStnDt stockStnDt = new StockStnDt();
										stockStnDt.setStoneType(stoneType);
										stockStnDt.setShape(shape);
										stockStnDt.setQuality(quality);
										stockStnDt.setSize(sizeNm);
										stockStnDt.setSieve(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() :null);
										stockStnDt.setSizeGroup(sizeGroup);
										stockStnDt.setStone(row.getCell(11, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(11, row.RETURN_BLANK_AS_NULL).toString())) :0);
										stockStnDt.setCarat(row.getCell(12, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(12, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										stockStnDt.setRate(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(13, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										stockStnDt.setDiamValue(row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(14, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										stockStnDt.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
										stockStnDt.setStockMt(stockMt);
										stockStnDt.setAvgRate(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(13, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										stockStnDt.setFactoryRate(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(13, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										stockStnDt.setTransferRate(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(13, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
										stockStnDt.setCreatedDate(new Date());
										stockStnDt.setCreatedBy(principal.getName());
										
										stockStnDtService.save(stockStnDt);
										
										stoneVal += row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(14, row.RETURN_BLANK_AS_NULL).toString()) :0.0;
										

										StockTran stockTran = new StockTran();
										stockTran.setBarcode(row.getCell(0, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(0, row.RETURN_BLANK_AS_NULL).toString() :null);
										stockTran.setCreatedBy(principal.getName());
										stockTran.setCreatedDate(new Date());
										stockTran.setCurrStatus(department.getName());
										stockTran.setLocation(department);
										stockTran.setRefTranId(null);
										stockTran.setTranDate(new Date());
										stockTran.setTranType("OPSTK");
										stockTran.setCurrStk(true);
										stockTran.setStockMt(stockMt);
										stockTranService.save(stockTran);
										
										
										
										mtId = stockMt.getMtId();
										
										
										}else {
											
											if(stoneType != null) {
												
											StockStnDt stockStnDt = new StockStnDt();
											stockStnDt.setStoneType(stoneType);
											stockStnDt.setShape(shape);
											stockStnDt.setQuality(quality);
											stockStnDt.setSize(sizeNm);
											stockStnDt.setSieve(row.getCell(9, row.RETURN_BLANK_AS_NULL) != null ?  row.getCell(9, row.RETURN_BLANK_AS_NULL).toString() :null);
											stockStnDt.setSizeGroup(sizeGroup);
											stockStnDt.setStone(row.getCell(11, row.RETURN_BLANK_AS_NULL) != null ? (int) (Double.parseDouble(row.getCell(11, row.RETURN_BLANK_AS_NULL).toString())) :0);
											stockStnDt.setCarat(row.getCell(12, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(12, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
											stockStnDt.setRate(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(13, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
											stockStnDt.setDiamValue(row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(14, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
											stockStnDt.setPartNm(lookUpMastService.findByFieldValueAndDeactive("Main Part", false));
											stockStnDt.setAvgRate(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(13, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
											stockStnDt.setFactoryRate(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(13, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
											stockStnDt.setTransferRate(row.getCell(13, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(13, row.RETURN_BLANK_AS_NULL).toString()) :0.0);
											stockStnDt.setCreatedDate(new Date());
											stockStnDt.setCreatedBy(principal.getName());
											stockStnDt.setStockMt(findOne(mtId));
											stockStnDtService.save(stockStnDt);
											
											stoneVal += row.getCell(14, row.RETURN_BLANK_AS_NULL) != null ?  Double.parseDouble(row.getCell(14, row.RETURN_BLANK_AS_NULL).toString()) :0.0;
											
											}
										}
									
										StockMt stockMt =findOne(mtId);
										stockMt.setStoneValue(stoneVal);
										save(stockMt);
									
								

								}
								
								workbook.close();
								}
								
						    } else if (tempExcelFile.endsWith("xls")) {
						    	int i = 1;
								HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
								HSSFSheet worksheet = workbook.getSheetAt(0);
								
								 Integer styleId=0;	
								 String designDate = null;
									Date designdt = new Date();
									
									
									while (i <= worksheet.getLastRowNum()) {}

								workbook.close();
								
						    }
				    	
				    	retVal="1";
				    	   
				    }
				    
				    
				    
				    
				    
				   
					}
				
				}catch (IOException e) {
					
					e.printStackTrace();
				}
			
			
			
			return retVal;
			
			
			
			
			
			
		}
		
		}

	@Override
	public List<StockMt> findByLocationAndCurrStkAndDeactive(Department location, Boolean currstk, Boolean deactive) {
		// TODO Auto-generated method stub
		return stockMtRepository.findByLocationAndCurrStkAndDeactive(location, currstk, deactive);
	}

	@Override
	public Page<StockMt> barcodeAutofill(Integer limit, Integer offset, String sort, String order, String name,
			Boolean onlyActive, Integer locationId) {
		// TODO Auto-generated method stub

		QStockMt qStockMt = QStockMt.stockMt;
		BooleanExpression expression = null;
		if (onlyActive) {
			if (name == null) {
				expression = qStockMt.deactive.eq(false).and(qStockMt.currStk.eq(true)).and(qStockMt.location.id.eq(locationId));
			} else {
				expression = qStockMt.deactive.eq(false).and(qStockMt.currStk.eq(true)).and(qStockMt.location.id.eq(locationId)).and(qStockMt.barcode.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qStockMt.location.id.eq(locationId).and(qStockMt.currStk.eq(true)).and(qStockMt.barcode.like(name + "%"));
			}
		}

		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "barcode";
		}else if (sort.equalsIgnoreCase("name")) {
			sort = "barcode";
		} 

		
		
		Page<StockMt> stockList = (Page<StockMt>) stockMtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC :  Direction.ASC), sort));

		return stockList;
	}

	@Override
	public Page<StockMt> barcodeSearchAutofill(Integer limit, Integer offset, String sort, String order, String name,
			Boolean onlyActive) {
		// TODO Auto-generated method stub
		QStockMt qStockMt = QStockMt.stockMt;
		BooleanExpression expression  = qStockMt.deactive.eq(false).and(qStockMt.barcode.like(name + "%"));
	

		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "barcode";
		}else if (sort.equalsIgnoreCase("name")) {
			sort = "barcode";
		} 

		
		
		Page<StockMt> stockList = (Page<StockMt>) stockMtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC :  Direction.ASC), sort));

		return stockList;
	}

	@Override
	public String barcodeHistoryList(String barcode) throws ParseException {
		// TODO Auto-generated method stub
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		SimpleDateFormat dfOutput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfInput = new SimpleDateFormat("dd/MM/yyyy");
		
			
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_barcodeSearchList(?) }");
		
		query.setParameter(1, barcode);
		
		objects = query.getResultList();
		
		
		JSONObject jsonObject =  new JSONObject();
		JSONArray jsonArray =  new JSONArray();
		
		for(Object[] list:objects){
			
			 String	createdDate="";
			 String	tranDate="";
			 String	vouDate="";
			 
			 if(list[0] != null ){
				 Date ordDate = dfOutput.parse((list[3] != null ? list[3] : "").toString());
				 createdDate = dfInput.format(ordDate);
			 }
			 
			 if(list[26] != null ){
				 Date trDate = dfOutput.parse((list[26] != null ? list[26] : "").toString());
				 tranDate = dfInput.format(trDate);
			 }
			 
			 if(list[32] != null ){
				 Date trDate = dfOutput.parse((list[32] != null ? list[32] : "").toString());
				 vouDate = dfInput.format(trDate);
			 }
			 
		
			JSONObject jsonObj =  new JSONObject();
			jsonObj.put("createdDt", createdDate);
			jsonObj.put("barcode", list[0] != null ? list[0] : "");
			jsonObj.put("Location", list[25] != null ? list[25] : "");
			jsonObj.put("orderParty", list[24] != null ? list[24] : "");
			jsonObj.put("orderNo", list[12] != null ? list[12] : "");
			jsonObj.put("bagNm", list[11] != null ? list[11] : "");
			jsonObj.put("mainStyle", list[10] != null ? list[10] : "");
			jsonObj.put("ktCol", list[13] != null ? list[13] : "");
			jsonObj.put("netWt", list[6] != null ? list[6] : "");
			jsonObj.put("grossWt", list[5] != null ? list[5] : "");
			jsonObj.put("diaPcs", list[15] != null ? list[15] : "");
			jsonObj.put("diaWt", list[16] != null ? list[16] : "");
			jsonObj.put("colPcs", list[18] != null ? list[18] : "");
			jsonObj.put("colWt", list[19] != null ? list[19] : "");
			jsonObj.put("currStatus", list[23] != null ? list[23] : "");
			jsonObj.put("tranDate", tranDate);
			jsonObj.put("userName", list[27] != null ? list[27] : "");
			jsonObj.put("image", list[28] != null ? list[28] : "");
			jsonObj.put("vouNo", list[31] != null ? list[31] : "");
			jsonObj.put("vouParty", list[33] != null ? list[33] : "");
			jsonObj.put("vouDate", vouDate);
			
			jsonArray.put(jsonObj);
		}	
		
		jsonObject.put("total", objects.size());
		jsonObject.put("rows", jsonArray);
		 	 
			
		return jsonObject.toString();
	}

	@Override
	public StockMt findByRefTranIdAndTranTypeAndDeactive(Integer refTranId, String TranType, Boolean deactive) {
		// TODO Auto-generated method stub
		return stockMtRepository.findByRefTranIdAndTranTypeAndDeactive(refTranId, TranType, deactive);
	}

	@Override
	public Page<StockMt> fgRetBarcodeAutofill(Integer limit, Integer offset, String sort, String order, String name,
			Boolean onlyActive, Integer locationId) {
		// TODO Auto-generated method stub
		QStockMt qStockMt = QStockMt.stockMt;
		BooleanExpression expression = null;
		if (onlyActive) {
			if (name == null) {
				expression = qStockMt.deactive.eq(false).and(qStockMt.currStk.eq(true)).and(qStockMt.location.id.eq(locationId));
			} else {
				expression = qStockMt.deactive.eq(false).and(qStockMt.currStk.eq(true)).and(qStockMt.bagId.isNotNull()).and(qStockMt.location.id.eq(locationId)).and(qStockMt.barcode.like(name + "%"));
			}
		} else {
			if (name != null) {
				expression = qStockMt.location.id.eq(locationId).and(qStockMt.currStk.eq(true)).and(qStockMt.bagId.isNotNull()).and(qStockMt.barcode.like(name + "%"));
			}
		}

		int page = 0;
		if (offset == null || limit == null) {
			limit = 1000;
		} else {
			page = (offset == 0 ? 0 : (offset / limit));
		}

		if (sort == null) {
			sort = "barcode";
		}else if (sort.equalsIgnoreCase("name")) {
			sort = "barcode";
		} 

		
		
		Page<StockMt> stockList = (Page<StockMt>) stockMtRepository.findAll(
				expression,
				new PageRequest(page, limit,
						(order.equalsIgnoreCase("asc") ? Direction.DESC :  Direction.ASC), sort));

		return stockList;
	}

	@Override
	public String barcodeHistoryStoneList(String barcode) {
		// TODO Auto-generated method stub
		
		
		List<StockMt> stockMts = findByBarcodeAndDeactiveOrderByMtIdDesc(barcode, false);
		
		List<StockStnDt> stockStnDts = stockStnDtService.findByStockMtAndDeactive(stockMts.get(0), false);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stockStnDts.size()).append(",\"rows\": [");
		 
		for (StockStnDt stockStnDt : stockStnDts) {
			sb.append("{\"id\":\"")
			.append(stockStnDt.getStkStnId())
			.append("\",\"stoneType\":\"")
			.append((stockStnDt.getStoneType() != null ? stockStnDt.getStoneType().getName() : ""))
			.append("\",\"partNm\":\"")
			.append((stockStnDt.getPartNm() != null ? stockStnDt.getPartNm().getFieldValue() : ""))
			.append("\",\"shape\":\"")
			.append((stockStnDt.getShape() != null ? stockStnDt.getShape().getName() : ""))
			.append("\",\"quality\":\"")
			.append((stockStnDt.getQuality() != null ? stockStnDt.getQuality().getName() : ""))
			.append("\",\"size\":\"")
			.append((stockStnDt.getSize() != null ? stockStnDt.getSize() : ""))
			.append("\",\"sieve\":\"")
			.append((stockStnDt.getSieve() != null ? stockStnDt.getSieve() : ""))
			.append("\",\"sizeGroup\":\"")
			.append(stockStnDt.getSizeGroup() != null ? stockStnDt.getSizeGroup().getName() : "")
			.append("\",\"stone\":\"")
			.append((stockStnDt.getStone() != null ? stockStnDt.getStone() : ""))
			.append("\",\"carat\":\"")
			.append((stockStnDt.getCarat() != null ? stockStnDt.getCarat() : ""))
			.append("\",\"rate\":\"")
			.append((stockStnDt.getFactoryRate() != null ? stockStnDt.getFactoryRate() : ""))
			.append("\",\"stoneValue\":\"")
			.append((stockStnDt.getDiamValue() != null ? stockStnDt.getDiamValue() : ""))
			.append("\",\"setting\":\"")
			.append((stockStnDt.getSetting() != null ? stockStnDt.getSetting().getName() : ""))
			.append("\",\"settingType\":\"")
			.append((stockStnDt.getSettingType() != null ? stockStnDt.getSettingType().getName() : ""))
			.append("\",\"centerStone\":\"")
			.append(stockStnDt.getCenterStone())
			.append("\"},");
		}
		
		str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
	return str;
	}

	@Override
	public String barcodeComponentList(String barcode) {
		// TODO Auto-generated method stub
		
		List<StockMt> stockMts = findByBarcodeAndDeactiveOrderByMtIdDesc(barcode, false);
		
		List<StockCompDt> stockCompDts = stockCompDtService.findByStockMtAndDeactive(stockMts.get(0), false);
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(stockCompDts.size()).append(",\"rows\": [");
		for (StockCompDt stockCompDt : stockCompDts) {
			sb.append("{\"id\":\"")
			.append(stockCompDt.getStkCompId())
			.append("\",\"compName\":\"")
			.append((stockCompDt.getComponent() != null ? stockCompDt.getComponent().getName() : ""))
			.append("\",\"kt\":\"")
			.append((stockCompDt.getPurity() != null ? stockCompDt.getPurity().getName() : ""))
			.append("\",\"color\":\"")
			.append((stockCompDt.getColor() != null ? stockCompDt.getColor().getName() : ""))
			.append("\",\"metalWt\":\"")
			.append((stockCompDt.getCompWt() != null ? stockCompDt.getCompWt() : ""))
			.append("\",\"compPcs\":\"")
			.append((stockCompDt.getCompQty() != null ? stockCompDt.getCompQty() : ""))
			.append("\"},");
		}
	   
		str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
	return str;
	}

	@Override
	public List<StockMt> findByBarcodeAndDeactiveOrderByMtIdDesc(String barcode, Boolean deactive) {
		// TODO Auto-generated method stub
		return stockMtRepository.findByBarcodeAndDeactiveOrderByMtIdDesc(barcode, deactive);
	}

	@Override
	public StockMt findByBarcodeAndCurrStkAndDeactiveAndLocation(String barcode, Boolean currstk, Boolean deactive,
			Department location) {
		// TODO Auto-generated method stub
		return stockMtRepository.findByBarcodeAndCurrStkAndDeactiveAndLocation(barcode, currstk, deactive, location);
	}

	@Override
	public String barcodeAttachToStock(Principal principal, String bagTblData) {
		// TODO Auto-generated method stub
		
		String retVal ="-1";
		
		JSONArray jsonTrfTblArray = new JSONArray(bagTblData);
		
		for (int i = 0; i < jsonTrfTblArray.length(); i++) {
			
			JSONObject jsonObject =   (JSONObject) jsonTrfTblArray.get(i);
			
			StockMt stockMt = findByBarcodeAndCurrStkAndDeactive(jsonObject.get("barcode").toString(), true, false);
			if(stockMt != null && stockMt.getBagId() == null) {
				
				stockMt.setBagId(Integer.parseInt(jsonObject.get("id").toString()));
				stockMt.setModiBy(principal.getName());
				stockMt.setModiDt(new Date());
				save(stockMt);
				
				
				
				
				BagMt bagMt = bagMtService.findOne(Integer.parseInt(jsonObject.get("id").toString()));
				bagMt.setBagCloseFlg(true);
				bagMt.setBarcode(jsonObject.get("barcode").toString());
				bagMt.setModiBy(principal.getName());
				bagMt.setModiDate(new Date());
				bagMtService.save(bagMt);
			
				retVal ="1";
			}
			
			
			
		}
		return retVal;
	}
	

}
