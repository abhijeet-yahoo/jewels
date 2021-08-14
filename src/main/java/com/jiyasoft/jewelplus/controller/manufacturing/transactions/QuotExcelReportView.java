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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotExportExcel;



public class QuotExcelReportView extends AbstractExcelView  {
	
	private boolean  perPcFlg= true;
	
	

	public QuotExcelReportView(Boolean flg) {
		perPcFlg =flg;
	}

	
	
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		 List<QuotExportExcel> quotList = (List<QuotExportExcel>) model.get("quotList");
		
		QuotExportExcel exportExcel = quotList.get(0);
		
		 String fileName = exportExcel.getInvNo();
		 
		 
		 
		 
		 response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+".xls\"");
		 
		 
		
		 Sheet sheet = workbook.createSheet("Quot Data");
		 
		 final int STYLENO=0;
		 final int BARCODE=1;
		 final int KTCOL=2;
		 final int PCS=3;
		 final int GROSSWT=4;
		 final int NETWT=5;
		  final int MTLRATE=6;
		  final int MTLVAL=7;
		  final int SHAPE=8;
		  final int QLTY=9;
		  final int SIZE=10;
		  final int SIZEGROUP=11;
		  final int STONE=12;
		  final int CARAT=13;
		  final int STNRATE=14;
		  final int STNVAL=15;
		  final int HDLGRATE=16;
		  final int HDLGVAL=17;
		  final int TOTHDLGVAL=18;
		  final int TOTSTONE=19;
		  final int TOTCARAT=20;
		  final int TOTSTNVAL=21;
		  final int SETNM=22;
		  final int SETRATE=23;
		  final int SETVAL=24;
		  final int TOTSETVAL=25;
		  final int TOTFNDVAL=26;
		  final int CFPL=27;
		  final int RHD=28;
		  final int MIL=29;
		  final int CERTIFY=30;
		  final int SOLDER=31;
		  final int TOTLAB=32;
		  final int UNITPRC=33;
		  final int DISC=34;
		  final int FINALPRC=35;
		  
		  
		  
		  
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  
		  
		
		 		 
		 Row header = sheet.createRow(0);
		  header.createCell(0).setCellValue("Quot No.");
		  header.createCell(1).setCellValue(exportExcel.getInvNo());

		  Row header1 = sheet.createRow(1);
		  header1.createCell(0).setCellValue("Quot Date");
		  header1.createCell(1).setCellValue(dateFormat.format(exportExcel.getInvDate()));
		  
		  Row header2 = sheet.createRow(4);
		  header2.createCell(STYLENO).setCellValue("Style No");
		  header2.createCell(BARCODE).setCellValue("Barcode");
		  header2.createCell(KTCOL).setCellValue("Kt-Col");
		  header2.createCell(PCS).setCellValue("Pcs");
		  header2.createCell(GROSSWT).setCellValue("Gross Wt");
		  header2.createCell(NETWT).setCellValue("Net Wt");
		  header2.createCell(MTLRATE).setCellValue("Metal Rate");
		  header2.createCell(MTLVAL).setCellValue("Metal Value");
		  header2.createCell(SHAPE).setCellValue("Shape");
		  header2.createCell(QLTY).setCellValue("Quality");
		  header2.createCell(SIZE).setCellValue("Size");
		  header2.createCell(SIZEGROUP).setCellValue("Size Group");
		  header2.createCell(STONE).setCellValue("Stone");
		  header2.createCell(CARAT).setCellValue("Carat");
		  header2.createCell(STNRATE).setCellValue("Stn Rate");
		  header2.createCell(STNVAL).setCellValue("Stn Value");
		  header2.createCell(HDLGRATE).setCellValue("Hdlg Rate");
		  
		  header2.createCell(HDLGVAL).setCellValue("Hdlg Value");
		  
		  header2.createCell(TOTHDLGVAL).setCellValue("TotHdlg Value");
		  
		  header2.createCell(TOTSTONE).setCellValue("Tot Stone");
		  
		  header2.createCell(TOTCARAT).setCellValue("Tot Carat");
		  
		  header2.createCell(TOTSTNVAL).setCellValue("Tot StnVal");
		  
		  header2.createCell(SETNM).setCellValue("Setting");
		  
		  header2.createCell(SETRATE).setCellValue("Set Rate");
		  header2.createCell(SETVAL).setCellValue("Set Val");
		  
		  header2.createCell(TOTSETVAL).setCellValue("Tot SetVal");
		  header2.createCell(TOTFNDVAL).setCellValue("Tot FndVal");
		  header2.createCell(CFPL).setCellValue("Cfpl");
		  
		  header2.createCell(RHD).setCellValue("Rhd");
		  header2.createCell(MIL).setCellValue("Mil");
		  
		  header2.createCell(CERTIFY).setCellValue("Certify");
		  header2.createCell(SOLDER).setCellValue("Solder");
		  
		  header2.createCell(TOTLAB).setCellValue("Tot Labour");
		  header2.createCell(UNITPRC).setCellValue("Unit Price");
		  header2.createCell(DISC).setCellValue("Disc");
		  header2.createCell(FINALPRC).setCellValue("Final Price");

		 
		  
		 int rowNum = 5;
		 for(QuotExportExcel exportExcel2:quotList){
		 Row row = sheet.createRow(rowNum++);
		 
		 String totfindingValue =exportExcel2.getCompValue() !=null ?exportExcel2.getCompValue().toString():"";
		 String totHdlgValue =  exportExcel2.getHandlingValue() !=null ? exportExcel2.getHandlingValue().toString() :"";
		 String totLabValue =  exportExcel2.getLabValue() !=null ? exportExcel2.getLabValue().toString() :"";
		 String totSetValue =  exportExcel2.getSetVal() !=null ? exportExcel2.getSetVal().toString() :"";
		
		 double totalLabVal =0.0; 
		 
		 
		 double findVal = Double.parseDouble(totHdlgValue);
		 double hdlgVal = Double.parseDouble(totfindingValue);
 		 double labVal = Double.parseDouble(totLabValue);
 		 double setVal = Double.parseDouble(totSetValue);
	
 		 
 		totalLabVal = findVal+hdlgVal+labVal+setVal;
		 
 		Double totLabourValue = Math.round((totalLabVal)*100.0)/100.0;
		
		 
		 row.createCell(BARCODE).setCellValue(exportExcel2.getBarcode() !=null && exportExcel2.getSerial() ==1?exportExcel2.getBarcode():"");
		 
		 row.createCell(TOTFNDVAL).setCellValue(exportExcel2.getCompValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getCompValue().toString():"");
		 
		 row.createCell(DISC).setCellValue(exportExcel2.getDiscAmount() !=null && exportExcel2.getSerial() ==1?exportExcel2.getDiscAmount().toString():"");
		 row.createCell(FINALPRC).setCellValue(exportExcel2.getFinalPrice() !=null && exportExcel2.getSerial() ==1?exportExcel2.getFinalPrice().toString():"");
		 row.createCell(GROSSWT).setCellValue(exportExcel2.getGrossWt() !=null && exportExcel2.getSerial() ==1?exportExcel2.getGrossWt().toString():"");
		 row.createCell(TOTHDLGVAL).setCellValue(exportExcel2.getHdlgValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getHdlgValue().toString():"");
		 /*row.createCell(TOTLAB).setCellValue(exportExcel2.getLabValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getLabValue().toString():"");*/
		 
		 
		 if(exportExcel2.getLabValue() !=null && exportExcel2.getSerial() ==1){
			 Double vTotLab = exportExcel2.getLabValue()+(exportExcel2.getSetValue() !=null ?exportExcel2.getSetValue():0)
					 +(exportExcel2.getCompValue() !=null ?exportExcel2.getCompValue():0)+(exportExcel2.getHdlgValue() !=null ?exportExcel2.getHdlgValue():0);
			 
			 row.createCell(TOTLAB).setCellValue(vTotLab);	 
		 }else{
			 row.createCell(TOTLAB).setCellValue("");
		 }
		 
	//	 row.createCell(TOTLAB).setCellValue(totLabourValue);
		 
		 row.createCell(MTLVAL).setCellValue(exportExcel2.getMetalValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getMetalValue().toString():"");
		 row.createCell(NETWT).setCellValue(exportExcel2.getNetWt() !=null && exportExcel2.getSerial() ==1?exportExcel2.getNetWt().toString():"");
		 row.createCell(PCS).setCellValue(exportExcel2.getPcs() !=null && exportExcel2.getSerial() ==1?exportExcel2.getPcs().toString():"");
		 row.createCell(TOTSETVAL).setCellValue(exportExcel2.getSetValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getSetValue().toString():"");
		
		 row.createCell(TOTSTNVAL).setCellValue(exportExcel2.getStoneValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getStoneValue().toString():"");
		 row.createCell(UNITPRC).setCellValue(exportExcel2.getPerPcFinalPrice() !=null && exportExcel2.getSerial() ==1?exportExcel2.getPerPcFinalPrice().toString():"");
		 row.createCell(STYLENO).setCellValue(exportExcel2.getStyleNo() !=null && exportExcel2.getSerial() ==1?exportExcel2.getStyleNo().toString():"");
		 row.createCell(MTLRATE).setCellValue(exportExcel2.getPerGramRate() !=null && exportExcel2.getSerial() ==1?exportExcel2.getPerGramRate().toString():"");
		 
		 
		 
		 row.createCell(KTCOL).setCellValue(exportExcel2.getPurityNm() !=null &&exportExcel2.getColorNm() !=null  && exportExcel2.getSerial() ==1?
				 exportExcel2.getPurityNm()+"/"+exportExcel2.getColorNm():"");
		 
		 row.createCell(CARAT).setCellValue(exportExcel2.getCarat() !=null ?exportExcel2.getCarat().toString() :"");
		 row.createCell(HDLGRATE).setCellValue(exportExcel2.getHandlingRate() !=null ?exportExcel2.getHandlingRate().toString() :"");
		 row.createCell(HDLGVAL).setCellValue(exportExcel2.getHandlingValue() !=null ?exportExcel2.getHandlingValue().toString() :"");
		 row.createCell(SETRATE).setCellValue(exportExcel2.getSetRate() !=null ?exportExcel2.getSetRate().toString() :"");
		 row.createCell(SETVAL).setCellValue(exportExcel2.getSetVal() !=null ?exportExcel2.getSetVal().toString() :"");
		 row.createCell(SIZE).setCellValue(exportExcel2.getSize() !=null ?exportExcel2.getSize().toString() :"");
		 row.createCell(STNRATE).setCellValue(exportExcel2.getStnRate() !=null ?exportExcel2.getStnRate().toString() :"");
		 row.createCell(STONE).setCellValue(exportExcel2.getStone() !=null ?exportExcel2.getStone().toString() :"");
		 row.createCell(STNVAL).setCellValue(exportExcel2.getStoneVal() !=null ?exportExcel2.getStoneVal().toString() :"");
		 row.createCell(QLTY).setCellValue(exportExcel2.getQualityNm() !=null ?exportExcel2.getQualityNm().toString() :"");
		 
		 
		 row.createCell(SETNM).setCellValue(exportExcel2.getSetNm() !=null && exportExcel2.getSettypeNm() !=null ?
				 exportExcel2.getSetNm()+"/"+exportExcel2.getSettypeNm() :"");
		 row.createCell(SHAPE).setCellValue(exportExcel2.getShapeNm() !=null ?exportExcel2.getShapeNm().toString() :"");
		 row.createCell(SIZEGROUP).setCellValue(exportExcel2.getSizegroupNm() !=null ?exportExcel2.getSizegroupNm().toString() :"");
		 row.createCell(CFPL).setCellValue(exportExcel2.getCfpl() !=null && exportExcel2.getSerial() ==1?exportExcel2.getCfpl().toString():"");
		 row.createCell(RHD).setCellValue(exportExcel2.getRhd() !=null && exportExcel2.getSerial() ==1?exportExcel2.getRhd().toString():"");
		 row.createCell(MIL).setCellValue(exportExcel2.getMil() !=null && exportExcel2.getSerial() ==1?exportExcel2.getMil().toString():"");
		 row.createCell(CERTIFY).setCellValue(exportExcel2.getCertify() !=null && exportExcel2.getSerial() ==1?exportExcel2.getCertify().toString():"");
		 row.createCell(SOLDER).setCellValue(exportExcel2.getSolder() !=null && exportExcel2.getSerial() ==1?exportExcel2.getSolder().toString():"");
		 
		 row.createCell(TOTCARAT).setCellValue(exportExcel2.getTotCarat() !=null && exportExcel2.getSerial() ==1?exportExcel2.getTotCarat().toString():"");
		 row.createCell(TOTSTONE).setCellValue(exportExcel2.getTotStone() !=null && exportExcel2.getSerial() ==1?exportExcel2.getTotStone().toString():"");
		 
		 
		 
		  
		  
		
		 
		 
		 
		 }
		
	}
	

}
