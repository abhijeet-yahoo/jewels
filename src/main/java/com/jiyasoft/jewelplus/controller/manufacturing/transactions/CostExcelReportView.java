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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostExportExcel;



public class CostExcelReportView extends AbstractExcelView  {
	
	private boolean  perPcFlg= true;
	
	

	public CostExcelReportView(Boolean flg) {
		perPcFlg =flg;
	}

	
	
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		 List<CostExportExcel> costList = (List<CostExportExcel>) model.get("costList");
		
		CostExportExcel exportExcel = costList.get(0);
		
		 String fileName = exportExcel.getInvNo();
		 
		 
		 
		 
		 response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+".xls\"");
		 
		 
		
		 Sheet sheet = workbook.createSheet("Cost Data");
		 final int SERIAL=0;
		 final int PONO=1;
		 final int ORDERRATE=2;
		 final int STYLENO=3;
		 final int BARCODE=4;
		 final int CATEGNM=5;
		 
		 
		 
		 final int KTCOL=6;
		 final int PCS=7;
		 final int GROSSWT=8;
		 final int NETWT=9;
		 
		 final int MTLRATE=10;
		 final int MTLVAL=11;
		 final int SHAPE=12;
		 final int QLTY=13;
		 final int SIZE=14;
		 final int SIZEGROUP=15;
		 final int STONE=16;
		 final int CARAT=17;
		 final int STNRATE=18;
		 final int STNVAL=19;
		 final int HDLGRATE=20;
		 final int HDLGVAL=21;
		 final int TOTHDLGVAL=22;
		 final int TOTSTONE=23;
		 final int TOTCARAT=24;
		 final int TOTSTNVAL=25;
		 final int SETNM=26;
		 final int SETRATE=27;
		 final int SETVAL=28;
		 final int TOTSETVAL=29;
		 final int TOTFNDVAL=30;
		 final int CFPL=31;
		 final int RHD=32;
		 final int MIL=33;
		 final int CERTIFY=34;
		 final int SOLDER=35;
		 final int TOTLAB=36;
		 final int FOB=37;
		 final int UNITPRC=38;
		 final int DISC=39;
		 final int FINALPRC=40;
		 
		  
		  
		  
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  
		  
		
		 		 
		 Row header = sheet.createRow(0);
		  header.createCell(0).setCellValue("Exp. No.");
		  header.createCell(1).setCellValue(exportExcel.getInvNo());

		  Row header1 = sheet.createRow(1);
		  header1.createCell(0).setCellValue("Exp. Date");
		  header1.createCell(1).setCellValue(dateFormat.format(exportExcel.getInvDate()));
		  
		  Row header2 = sheet.createRow(4);
		  header2.createCell(SERIAL).setCellValue("Serial");
		  header2.createCell(PONO).setCellValue("PO No.");
		  header2.createCell(ORDERRATE).setCellValue("Order Rate");
		  header2.createCell(CATEGNM).setCellValue("Category");
		  header2.createCell(FOB).setCellValue("Fob");
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
		 for(CostExportExcel exportExcel2:costList){
		 Row row = sheet.createRow(rowNum++);
		 
		 row.createCell(SERIAL).setCellValue(exportExcel2.getItemSr() !=null && exportExcel2.getSerial() ==1?exportExcel2.getItemSr().toString():"");
		 row.createCell(PONO).setCellValue(exportExcel2.getRefNo() !=null && exportExcel2.getSerial() ==1?exportExcel2.getRefNo().toString():"");
		 row.createCell(ORDERRATE).setCellValue(exportExcel2.getOrderRate() !=null && exportExcel2.getSerial() ==1?exportExcel2.getOrderRate().toString():"");
		 row.createCell(CATEGNM).setCellValue(exportExcel2.getCategNm() !=null && exportExcel2.getSerial() ==1?exportExcel2.getCategNm().toString():"");
		 row.createCell(FOB).setCellValue(exportExcel2.getFob() !=null && exportExcel2.getSerial() ==1?exportExcel2.getFob().toString():"");
		  row.createCell(BARCODE).setCellValue(exportExcel2.getBarcode() !=null && exportExcel2.getSerial() ==1?exportExcel2.getBarcode():"");
		 
		 row.createCell(TOTFNDVAL).setCellValue(exportExcel2.getCompValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getCompValue().toString():"");
		 
		 row.createCell(DISC).setCellValue(exportExcel2.getDiscAmount() !=null && exportExcel2.getSerial() ==1?exportExcel2.getDiscAmount().toString():"");
		 row.createCell(FINALPRC).setCellValue(exportExcel2.getFinalPrice() !=null && exportExcel2.getSerial() ==1?exportExcel2.getFinalPrice().toString():"");
		 row.createCell(GROSSWT).setCellValue(exportExcel2.getGrossWt() !=null && exportExcel2.getSerial() ==1?exportExcel2.getGrossWt().toString():"");
		 row.createCell(TOTHDLGVAL).setCellValue(exportExcel2.getHdlgValue() !=null && exportExcel2.getSerial() ==1?exportExcel2.getHdlgValue().toString():"");
		 
		 if(exportExcel2.getLabValue() !=null && exportExcel2.getSerial() ==1){
			 Double vTotLab = exportExcel2.getLabValue()+(exportExcel2.getSetValue() !=null ?exportExcel2.getSetValue():0)
					 +(exportExcel2.getCompValue() !=null ?exportExcel2.getCompValue():0)+(exportExcel2.getHdlgValue() !=null ?exportExcel2.getHdlgValue():0);
			 
			 row.createCell(TOTLAB).setCellValue(vTotLab);	 
		 }else{
			 row.createCell(TOTLAB).setCellValue("");
		 }
		 
		 
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
