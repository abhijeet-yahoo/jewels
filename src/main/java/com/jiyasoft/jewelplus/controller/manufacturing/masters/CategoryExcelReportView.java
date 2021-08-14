package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;


public class CategoryExcelReportView extends AbstractExcelView   {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 response.setHeader("Content-Disposition", "attachment;filename=\"category.xls\"");
		 @SuppressWarnings("unchecked")
		 List<Category> categoryList = (List<Category>) model.get("categoryList");
		 Sheet sheet = workbook.createSheet("Category Data");
		 
		  final int CategId = 0;
		  final int CategNm =1;
		  final int CategCode=2;
		  final int CategQty=3;
		  final int CategRate=4;
		  final int CategAmt=5;
		  
		  final String CategIdCell="A";
		  final String CategNmCell="B";
		  final String CategCodeCell="C";
		  final String CategQtyCell="D";
		  final String CategRateCell="E";
		  final String CategAmtCell="F";
		 
		 		 
		 Row header = sheet.createRow(0);
		 header.createCell(CategId).setCellValue("Categ ID");
		 header.createCell(CategRate).setCellValue("Categ Rate");
		 header.createCell(CategAmt).setCellValue("Categ Amount");
		 header.createCell(CategNm).setCellValue("Categ Name");
		 header.createCell(CategCode).setCellValue("Categ Code");
		 header.createCell(CategQty).setCellValue("Categ Qty");
		 
		
		 
		  
		 int rowNum = 1;
		 for(Category category:categoryList){
		 Row row = sheet.createRow(rowNum++);
		 row.createCell(CategId).setCellValue(category.getId());
		 row.createCell(CategNm).setCellValue(category.getName());
		 row.createCell(CategCode).setCellValue(category.getCategCode());
		 row.createCell(CategQty).setCellValue(10);
		 row.createCell(CategRate).setCellValue(200);
		 row.createCell(CategAmt).setCellFormula(CategQtyCell+rowNum+" * "+CategRateCell+rowNum+"");
		 
		 
		 }
		
	}
	
	
}
