package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class CostingExcelReportView extends AbstractExcelView  {
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		 List<Object[]> costingList = (List<Object[]>) model.get("costingList");
		
		 Object[] objects = costingList.get(0);
		
		 String fileName = objects[3].toString();
		 
		 
		 response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+".xls\"");
		 
		 
		
		 Sheet sheet = workbook.createSheet("Costing Data");
		 
		  final int REFNO = 0;
		  final int ORDERTYPE =1;
		  final int BAGNO=2;
		  final int STYLENO=3;
		  final int CATEGNM=4;
		  final int KTCOL=5;
		  
		  final int PCS=6;
		  final int GROSSWT=7;
		  final int NETWT=8;
		  final int LOSSWT=9;
		  final int LOSSPERC=10;
		  final int TOTWT=11;
		  final int MTLRATE=12;
		  final int MTLVAL=13;
		  final int RDQLTY=14;
		  final int RDSTN=15;
		  final int RDCTS=16;
		  final int RDRATE=17;
		  final int RDVAL=18;
		  final int RDSETTYPE=19;
		  final int RDSETRATE=20;
		  final int RDSETVAL=21;
		  
		  
		  
		  final int BUGQLTY=22;
		  final int BUGSTN=23;
		  final int BUGCTS=24;
		  final int BUGRATE=25;
		  final int BUGVAL=26;
		  final int BUGSETTYPE=27;
		  final int BUGSETRATE=28;
		  final int BUGSETVAL=29;
		  
		  
		  final int CLQLTY=30;
		  final int CLSTN=31;
		  final int CLCTS=32;
		  final int CLRATE=33;
		  final int CLVAL=34;
		  final int CLSETTYPE=35;
		  final int CLSETRATE=36;
		  final int CLSETVAL=37;
		  
		  final int TOTLAB=38;
		  final int TOTFOB=39;
		  
		  
	final String NETWTCELL="I";
	final String LOSSPERCCELL="K";
	final String LOSSWTCELL="J";
	final String TOTWTCELL="L";
	final String MTLRATECELL="M";
	final String RDCARATCELL="Q";
	final String RDRATECELL="R";
	final String RDSTONECELL="P";
	final String RDSETRATECELL="U";
	
	final String BUGCARATCELL="Y";
	final String BUGRATECELL="Z";
	final String BUGSTONECELL="X";
	final String BUGSETRATECELL="AC";
	
	final String CLCARATCELL="AG";
	final String CLRATECELL="AH";
	final String CLSTONECELL="AF";
	final String CLSETRATECELL="AK";
	
	
		 
		  
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  
		  
		  
		 		 
		 Row header = sheet.createRow(0);
		  header.createCell(0).setCellValue("Exp No.");
		  header.createCell(1).setCellValue(objects[3].toString());

		  Row header1 = sheet.createRow(1);
		  header1.createCell(0).setCellValue("Exp Date");
		  header1.createCell(1).setCellValue(dateFormat.format(objects[4]));
		  
		  Row header2 = sheet.createRow(4);
		  header2.createCell(REFNO).setCellValue("Ref No.");
		  header2.createCell(MTLRATE).setCellValue("Metal Rate");
		  header2.createCell(MTLVAL).setCellValue("Metal Val");
		  header2.createCell(ORDERTYPE).setCellValue("Order Type");
		  header2.createCell(BAGNO).setCellValue("Bag No.");
		  header2.createCell(STYLENO).setCellValue("Style No.");
		  header2.createCell(CATEGNM).setCellValue("Category");
		  header2.createCell(KTCOL).setCellValue("KT");
		  header2.createCell(PCS).setCellValue("Pcs");
		  header2.createCell(GROSSWT).setCellValue("Gross Wt");
		  header2.createCell(NETWT).setCellValue("Net Wt");
		  header2.createCell(LOSSWT).setCellValue("Loss Wt");
		  header2.createCell(LOSSPERC).setCellValue("Loss %");
		  header2.createCell(TOTWT).setCellValue("Total Wt");
		  header2.createCell(RDQLTY).setCellValue("Quality");
		  header2.createCell(RDSTN).setCellValue("Stone");
		  header2.createCell(RDCTS).setCellValue("Carat");
		  header2.createCell(RDRATE).setCellValue("Rate $");
		  header2.createCell(RDVAL).setCellValue("Val $");
		  header2.createCell(RDSETTYPE).setCellValue("Set Code");
		  header2.createCell(RDSETRATE).setCellValue("Rate");
		  header2.createCell(RDSETVAL).setCellValue("Set Charge");
		  header2.createCell(BUGQLTY).setCellValue("Quality");
		  header2.createCell(BUGSTN).setCellValue("Stone");
		  header2.createCell(BUGCTS).setCellValue("Carat");
		  header2.createCell(BUGRATE).setCellValue("Rate $");
		  header2.createCell(BUGVAL).setCellValue("Val $");
		  header2.createCell(BUGSETTYPE).setCellValue("Set Code");
		  header2.createCell(BUGSETRATE).setCellValue("Rate");
		  header2.createCell(BUGSETVAL).setCellValue("Set Charge");
		  header2.createCell(CLQLTY).setCellValue("Quality");
		  header2.createCell(CLSTN).setCellValue("Stone");
		  header2.createCell(CLCTS).setCellValue("Carat");
		  header2.createCell(CLRATE).setCellValue("Rate $");
		  header2.createCell(CLVAL).setCellValue("Val $");
		  header2.createCell(CLSETTYPE).setCellValue("Set Code");
		  header2.createCell(CLSETRATE).setCellValue("Rate");
		  header2.createCell(CLSETVAL).setCellValue("Set Charge");
		  header2.createCell(TOTLAB).setCellValue("Tot Labour");
		  header2.createCell(TOTFOB).setCellValue("Tot Fob");
		  
		  
		  Row header3 = sheet.createRow(5);
		  header3.createCell(RDQLTY).setCellValue("ROUND");
		  header3.createCell(BUGQLTY).setCellValue("BUGGEST, PEARS ,MQ DIAMOND");
		  header3.createCell(CLQLTY).setCellValue("COLOR STONE");
		  
		  
		  
		  CellStyle style = workbook.createCellStyle();
		  Font font = workbook.createFont();
		  font.setBold(true);
		  style.setFont(font);
		  style.setAlignment(style.ALIGN_CENTER);
		  header3.getCell(RDQLTY).setCellStyle(style);
		  header3.getCell(BUGQLTY).setCellStyle(style);
		  header3.getCell(CLQLTY).setCellStyle(style);
		  
		
				  
		  
		  sheet.addMergedRegion(new CellRangeAddress(5,5,RDQLTY,RDSETVAL));
		  sheet.addMergedRegion(new CellRangeAddress(5,5,BUGQLTY,BUGSETVAL));
		  sheet.addMergedRegion(new CellRangeAddress(5,5,CLQLTY,CLSETVAL));
		 
		  
		 int rowNum = 6;
		 for(Object[] result:costingList){
		 Row row = sheet.createRow(rowNum++);
		  row.createCell(REFNO).setCellValue(result[6] !=null?result[6].toString():"");
		  row.createCell(MTLRATE).setCellValue(result[18] !=null?result[18].toString():"");
		  row.createCell(MTLVAL).setCellValue(result[19] !=null?result[19].toString():"");
		  row.createCell(ORDERTYPE).setCellValue(result[7] !=null?result[7].toString():"");
		  row.createCell(BAGNO).setCellValue(result[8] !=null?result[8].toString():"");
		  row.createCell(STYLENO).setCellValue(result[9] !=null?result[9].toString():"");
		  row.createCell(CATEGNM).setCellValue(result[10] !=null?result[10].toString():"");
		  row.createCell(KTCOL).setCellValue(result[11] !=null?result[11].toString():"");
		  row.createCell(PCS).setCellValue(result[12] !=null?result[12].toString():"");
		  row.createCell(GROSSWT).setCellValue(result[13] !=null?result[13].toString():"");
		  row.createCell(NETWT).setCellValue(result[14] !=null?result[14].toString():"");
		  row.createCell(LOSSWT).setCellValue(result[15] !=null?result[15].toString():"");
		  row.createCell(LOSSPERC).setCellValue(result[16] !=null?result[16].toString():"");
		  row.createCell(TOTWT).setCellValue(result[17] !=null?result[17].toString():"");
		  row.createCell(RDQLTY).setCellValue(result[20] !=null?result[20].toString():"");
		  row.createCell(RDSTN).setCellValue(result[21] !=null?result[21].toString():"");
		  row.createCell(RDCTS).setCellValue(result[22] !=null?result[22].toString():"");
		  row.createCell(RDRATE).setCellValue(result[23] !=null?result[23].toString():"");
		  row.createCell(RDVAL).setCellValue(result[24] !=null?result[24].toString():"");
		  row.createCell(RDSETTYPE).setCellValue(result[25] !=null?result[25].toString():"");
		  row.createCell(RDSETRATE).setCellValue(result[26] !=null?result[26].toString():"");
		  row.createCell(RDSETVAL).setCellValue(result[27] !=null?result[27].toString():"");
		  row.createCell(BUGQLTY).setCellValue(result[28] !=null?result[28].toString():"");
		  row.createCell(BUGSTN).setCellValue(result[29] !=null?result[29].toString():"");
		  row.createCell(BUGCTS).setCellValue(result[30] !=null?result[30].toString():"");
		  row.createCell(BUGRATE).setCellValue(result[31] !=null?result[31].toString():"");
		  row.createCell(BUGVAL).setCellValue(result[32] !=null?result[32].toString():"");
		  row.createCell(BUGSETTYPE).setCellValue(result[33] !=null?result[33].toString():"");
		  row.createCell(BUGSETRATE).setCellValue(result[34] !=null?result[34].toString():"");
		  row.createCell(BUGSETVAL).setCellValue(result[35] !=null?result[35].toString():"");
		  row.createCell(CLQLTY).setCellValue(result[36] !=null?result[36].toString():"");
		  row.createCell(CLSTN).setCellValue(result[37] !=null?result[37].toString():"");
		  row.createCell(CLCTS).setCellValue(result[38] !=null?result[38].toString():"");
		  row.createCell(CLRATE).setCellValue(result[39] !=null?result[39].toString():"");
		  row.createCell(CLVAL).setCellValue(result[40] !=null?result[40].toString():"");
		  row.createCell(CLSETTYPE).setCellValue(result[41] !=null?result[41].toString():"");
		  row.createCell(CLSETRATE).setCellValue(result[42] !=null?result[42].toString():"");
		  row.createCell(CLSETVAL).setCellValue(result[43] !=null?result[43].toString():"");
		  row.createCell(TOTLAB).setCellValue(result[44] !=null?result[44].toString():"");
		  row.createCell(TOTFOB).setCellValue(result[45] !=null?result[45].toString():"");
		 //row.createCell(CategAmt).setCellFormula(CategQtyCell+rowNum+" * "+CategRateCell+rowNum+"");
		  
		  
		 row.getCell(LOSSWT).setCellFormula("("+NETWTCELL+rowNum+"*"+LOSSPERCCELL+rowNum+")/100");
		 row.getCell(TOTWT).setCellFormula(NETWTCELL+rowNum+"+"+LOSSWTCELL+rowNum);
		 row.getCell(MTLVAL).setCellFormula(TOTWTCELL+rowNum+"*"+MTLRATECELL+rowNum);
		 
		 row.getCell(RDVAL).setCellFormula(RDCARATCELL+rowNum+"*"+RDRATECELL+rowNum);
		 row.getCell(RDSETVAL).setCellFormula(RDSTONECELL+rowNum+"*"+RDSETRATECELL+rowNum);
		 
		 row.getCell(BUGVAL).setCellFormula(BUGCARATCELL+rowNum+"*"+BUGRATECELL+rowNum);
		 row.getCell(BUGSETVAL).setCellFormula(BUGSTONECELL+rowNum+"*"+BUGSETRATECELL+rowNum);
		 
		 row.getCell(CLVAL).setCellFormula(CLCARATCELL+rowNum+"*"+CLRATECELL+rowNum);
		 row.getCell(CLSETVAL).setCellFormula(CLSTONECELL+rowNum+"*"+CLSETRATECELL+rowNum);
		 
		 
		 
		 }
		
	}
	

}
