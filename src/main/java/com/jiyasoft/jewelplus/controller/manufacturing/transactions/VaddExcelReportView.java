package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddExportExcel;



public class VaddExcelReportView extends AbstractExcelView  {
	
	
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		 List<VaddExportExcel> vaddList = (List<VaddExportExcel>) model.get("vaddList");
		
		VaddExportExcel exportExcel = vaddList.get(0);
		
		 String fileName = exportExcel.getInvNo();
		 
		 
		 
		 
		 response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+".xls\"");
		 
		 
		
		 Sheet sheet = workbook.createSheet("Vadd Data");
		 
		 final int SERIAL=0;
		 final int STYLENO=1;
		 final int KTCOL=2;
		 final int CATEGNM=3;
		 final int PCS=4;
		 final int GROSSWT=5;
		 final int NETWT=6;
		 final int LOSSWT=7;
		 final int MTLRATE=8;
		 final int LOSSVAL=9;
		 final int TOTMTLVAL=10;
		 final int SHAPE=11;
		 final int QLTY=12;
		 final int INTQLTY=13;
		 final int DIACUT=14;
		 final int SIZEGROUP=15;
		 final int STONE=16;
		 final int CARAT=17;
		 final int STNRATE=18;
		 final int STNVAL=19;
		 final int TOTSTONE=20;
		 final int TOTCARAT=21;
		 final int TOTSTNVAL=22;
		 final int TOTLAB=23;
		 final int UNITPRC=24;
		 final int FINALPRC=25;
		
		  
		  
		  
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  
		  
		
		 		 
		 Row header = sheet.createRow(0);
		  header.createCell(0).setCellValue("Exp. No.");
		  header.createCell(1).setCellValue(exportExcel.getInvNo());
		  header.createCell(10).setCellValue("Packing List");

		  Row header1 = sheet.createRow(1);
		  header1.createCell(0).setCellValue("Exp. Date");
		  header1.createCell(1).setCellValue(dateFormat.format(exportExcel.getInvDate()));
		  
		  Row header2 = sheet.createRow(4);
		  header2.createCell(SERIAL).setCellValue("Serial");
		  header2.createCell(STYLENO).setCellValue("Style No");
		   header2.createCell(KTCOL).setCellValue("Kt-Col");
		   header2.createCell(CATEGNM).setCellValue("Caegory");
		  header2.createCell(PCS).setCellValue("Pcs/Pairs");
		  header2.createCell(GROSSWT).setCellValue("Gross Wt");
		  header2.createCell(NETWT).setCellValue("Net Wt");
		  header2.createCell(LOSSWT).setCellValue("Loss Wt");
		  header2.createCell(LOSSVAL).setCellValue("Loss Value");
		  header2.createCell(MTLRATE).setCellValue("Metal Rate");
		  header2.createCell(TOTMTLVAL).setCellValue("Tot Metal Value");
		  header2.createCell(SHAPE).setCellValue("Diamonds/PS/SPS");
		  header2.createCell(QLTY).setCellValue("Quality");
		  header2.createCell(INTQLTY).setCellValue("Clarity/Color");
		  header2.createCell(DIACUT).setCellValue("Type");
		  header2.createCell(SIZEGROUP).setCellValue("Size Group");
		  header2.createCell(STONE).setCellValue("Diam./PS/SPS Pcs");
		  header2.createCell(CARAT).setCellValue("Diam./PS/ SPS Wt.");
		  header2.createCell(STNRATE).setCellValue("Diam./PS/SPS Rate");
		  header2.createCell(STNVAL).setCellValue("Diam./PS/SPS Amount");
		  header2.createCell(TOTSTONE).setCellValue("Tot. Diam./PS/SPS Pcs");
		  header2.createCell(TOTCARAT).setCellValue("Tot. Diam./PS/SPS Carat");
		  header2.createCell(TOTSTNVAL).setCellValue("Tot. Diam./PS/ SPS Amount");
		  header2.createCell(TOTLAB).setCellValue("Tot Labour");
		  header2.createCell(UNITPRC).setCellValue("Unit Price");
		  header2.createCell(FINALPRC).setCellValue("Final Price");

		 
		  
		 int rowNum = 5;
		 for(VaddExportExcel exportExcel2:vaddList){
		 Row row = sheet.createRow(rowNum++);
		 
		 row.createCell(SERIAL).setCellValue(exportExcel2.getItemSr() !=null && exportExcel2.getSerial() ==1?exportExcel2.getItemSr().toString():"");
		 
		 row.createCell(CATEGNM).setCellValue(exportExcel2.getCategNm() !=null && exportExcel2.getSerial() ==1?exportExcel2.getCategNm().toString():"");
		 
		 row.createCell(LOSSWT).setCellValue(exportExcel2.getLossWt() !=null && exportExcel2.getSerial() ==1?exportExcel2.getLossWt().toString():"");
		 row.createCell(LOSSVAL).setCellValue(exportExcel2.getLossValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getLossValue().toString():"");
		 
		 row.createCell(FINALPRC).setCellValue(exportExcel2.getFinalPrice() !=null && exportExcel2.getSerial() ==1?exportExcel2.getFinalPrice().toString():"");
		 row.createCell(GROSSWT).setCellValue(exportExcel2.getGrossWt() !=null && exportExcel2.getSerial() ==1?exportExcel2.getGrossWt().toString():"");
		
		 if(exportExcel2.getFinalPrice() !=null && exportExcel2.getSerial() ==1){
			 Double vTotLab = exportExcel2.getFinalPrice()-(exportExcel2.getTotMetalValue() !=null ?exportExcel2.getTotMetalValue():0)-(exportExcel2.getTotStoneValue() !=null ?exportExcel2.getTotStoneValue():0);
			 
			 row.createCell(TOTLAB).setCellValue(vTotLab);	 
		 }else{
			 row.createCell(TOTLAB).setCellValue("");
		 }
		 
		  
		 
		 row.createCell(TOTMTLVAL).setCellValue(exportExcel2.getTotMetalValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getTotMetalValue().toString():"");
		 
		 row.createCell(NETWT).setCellValue(exportExcel2.getNetWt() !=null && exportExcel2.getSerial() ==1?exportExcel2.getNetWt().toString():"");
		 row.createCell(PCS).setCellValue(exportExcel2.getPcs() !=null && exportExcel2.getSerial() ==1?exportExcel2.getPcs().toString():"");
		 row.createCell(TOTSTNVAL).setCellValue(exportExcel2.getTotStoneValue()!=null && exportExcel2.getSerial() ==1?exportExcel2.getTotStoneValue().toString():"");
		 row.createCell(UNITPRC).setCellValue(exportExcel2.getFinalPrcPerPcs() !=null && exportExcel2.getSerial() ==1?exportExcel2.getFinalPrcPerPcs().toString():"");
		 row.createCell(STYLENO).setCellValue(exportExcel2.getStyleNo() !=null && exportExcel2.getSerial() ==1?exportExcel2.getStyleNo().toString():"");
		 row.createCell(MTLRATE).setCellValue(exportExcel2.getPerGramRate() !=null && exportExcel2.getSerial() ==1?exportExcel2.getPerGramRate().toString():"");
		 
		 
		 
		 row.createCell(KTCOL).setCellValue(exportExcel2.getPurityNm() !=null &&exportExcel2.getColorNm() !=null  && exportExcel2.getSerial() ==1?
				 exportExcel2.getPurityNm()+"/"+exportExcel2.getColorNm():"");
		 
		 row.createCell(CARAT).setCellValue(exportExcel2.getCarat() !=null ?exportExcel2.getCarat().toString() :"");
		 row.createCell(STNRATE).setCellValue(exportExcel2.getStnRate() !=null ?exportExcel2.getStnRate().toString() :"");
		 row.createCell(STONE).setCellValue(exportExcel2.getStone() !=null ?exportExcel2.getStone().toString() :"");
		 row.createCell(STNVAL).setCellValue(exportExcel2.getStoneValue() !=null ?exportExcel2.getStoneValue().toString() :"");
		 row.createCell(QLTY).setCellValue(exportExcel2.getQualityNm() !=null ?exportExcel2.getQualityNm().toString() :"");
		 row.createCell(INTQLTY).setCellValue(exportExcel2.getIntQuality() !=null ?exportExcel2.getIntQuality().toString() :"");
		 row.createCell(DIACUT).setCellValue(exportExcel2.getDiaCut() !=null ?exportExcel2.getDiaCut().toString() :"");
		 row.createCell(SHAPE).setCellValue(exportExcel2.getShapeNm() !=null ?exportExcel2.getShapeNm().toString() :"");
		 row.createCell(SIZEGROUP).setCellValue(exportExcel2.getSizegroupNm() !=null ?exportExcel2.getSizegroupNm().toString() :"");
		 row.createCell(TOTCARAT).setCellValue(exportExcel2.getTotCarat() !=null && exportExcel2.getSerial() ==1?exportExcel2.getTotCarat().toString():"");
		 row.createCell(TOTSTONE).setCellValue(exportExcel2.getTotStone() !=null && exportExcel2.getSerial() ==1?exportExcel2.getTotStone().toString():"");
		 
		 
		 
		  
		  
		
		 
		 
		 
		 }
		
	}
	

}
