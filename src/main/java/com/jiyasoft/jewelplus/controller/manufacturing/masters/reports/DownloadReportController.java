package com.jiyasoft.jewelplus.controller.manufacturing.masters.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/manufacturing/masters/reports")
@Controller
public class DownloadReportController {

	
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;
	
	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;
	
	
	//-----------------Common Pdf ---------//------------//
	
	@RequestMapping(value = "/download/report", method = RequestMethod.POST)
	public String downPdfRepo(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value = "timeValCommonPdf") String fileName) {
		
		try{  
			
	 		PrintWriter out = response.getWriter();  
	 		String filename = fileName+".pdf";     
	 		
	 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
	 		
	 		//response.setContentType("APPLICATION/OCTET-STREAM"); 
	 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   //  these two line will force the browser to download the pdf files
	 		
	 		response.setContentType("APPLICATION/pdf"); 
	 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");
	 		
	 		//response.setContentType(type);
	 		
	 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
	 		
	 		int i;   
	 		while ((i=fileInputStream.read()) != -1) {  
	 		out.write(i);   
	 		}   
	 		
	 		fileInputStream.close();   
	 		out.close();   
 		
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
		file.delete();
		
		return null;
	}
	
	
	//-----------------Dept Stockt ---------//------------//
	
	@RequestMapping(value = "/download/deptExcelReport", method = RequestMethod.POST)
	public String downExcelDept(HttpServletRequest request,
			HttpServletResponse response) {
		
		try{
			
	 		PrintWriter out = response.getWriter();  
	 		String filename = "deptEx.xlsx";   
	 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
	 		response.setContentType("application/vnd.ms-excel"); 
	 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
	 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");   
	 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
	 		 
	 		int i;   
	 		while ((i=fileInputStream.read()) != -1) {  
	 			out.write(i);   
	 		}   
	 		
	 		fileInputStream.close();   
	 		out.close();   
 		
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;

	}
	
	
	
	//-----------------Dept Stockt ---------//------------//
	
		@RequestMapping(value = "/download/exportPackingList", method = RequestMethod.POST)
		public String downExcelExportPackingList(HttpServletRequest request,
				HttpServletResponse response) {
			
			try{
				
		 		PrintWriter out = response.getWriter();  
		 		String filename = "packinglist.xlsx";   
		 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
		 		response.setContentType("application/vnd.ms-excel"); 
		 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
		 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");   
		 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		 		 
		 		int i;   
		 		while ((i=fileInputStream.read()) != -1) {  
		 			out.write(i);   
		 		}   
		 		
		 		fileInputStream.close();   
		 		out.close();   
	 		
			}catch (Exception e) {
				System.out.println(e);
			}
			
			return null;

		}
	
	
	//-----------------Bag Print ---------//------------//
	
	@RequestMapping(value = "/download/bagPrintReport", method = RequestMethod.POST)
	public String downBagPrintPdfRepo(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value = "timeValBagPrintPdf") String fileName) {
		
		try{  
			
	 		PrintWriter out = response.getWriter();  
	 		String filename = fileName+".pdf";    
	 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
	 		
	 		//response.setContentType("APPLICATION/OCTET-STREAM"); 
	 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   //  these two line will force the browser to download the pdf files
	 		
	 		response.setContentType("APPLICATION/pdf");
	 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");  
	 		
	 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
	 		
	 		int i;   
	 		while ((i=fileInputStream.read()) != -1) {  
	 		out.write(i);   
	 		}   
	 		
	 		fileInputStream.close();   
	 		out.close();   
 		
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
		file.delete();
		
		return null;

	}
	
	
	
	//-----------------Order Status ---------//------------//
	
	@RequestMapping(value = "/download/orderStatusReport", method = RequestMethod.POST)
	public String downOrderStatusPdfRepo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "timeValPdf") String fileName) {
		
		//System.out.println("printing the file name ============= "+fileName);
		
		try{  
			
	 		PrintWriter out = response.getWriter();  
	 		String filename = fileName+".pdf";     
	 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
	 		
	 		//response.setContentType("APPLICATION/OCTET-STREAM"); 
	 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   //  these two line will force the browser to download the pdf files
	 		
	 		response.setContentType("APPLICATION/pdf"); 
	 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");  
	 		
	 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
	 		
	 		int i;   
	 		while ((i=fileInputStream.read()) != -1) {  
	 		out.write(i);   
	 		}   
	 		
	 		fileInputStream.close();   
	 		out.close();   
 		
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
		file.delete();
		
		//System.out.println("printing the order file delete status==========///====:::::::::::===="+status);
		
		return null;

	}
	
	
	//--------------//---Costing tags Reports-----//
	
	
	@RequestMapping(value = "/download/excelReport", method = RequestMethod.POST)
	public String downExcelRepo(HttpServletRequest request,
			HttpServletResponse response) {
		
		try{
			
	 		PrintWriter out = response.getWriter();  
	 		String filename = "tags.xlsx";   
	 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
	 		response.setContentType("application/vnd.ms-excel"); 
	 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
	 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");   
	 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
	 		 
	 		int i;   
	 		while ((i=fileInputStream.read()) != -1) {  
	 			out.write(i);   
	 		}   
	 		
	 		fileInputStream.close();   
	 		out.close();   
 		
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;

	}
	
	
	//--------------//---Common Excel  Reports-----//
	 @RequestMapping(value="/download/Common/excelReport",method =RequestMethod.POST)
	 public void downExcelRepo(HttpServletRequest request,
				HttpServletResponse response,
				@RequestParam(value="pFileName",required = false) String pFileName) {
			
			try{
				
		 		PrintWriter out = response.getWriter();  
		 		String filename = pFileName ;   
		 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
		 		response.setContentType("application/vnd.ms-excel"); 
		 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
		 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");   
		 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		 		 
		 		int i;   
		 		while ((i=fileInputStream.read()) != -1) {  
		 			out.write(i);   
		 		}   
		 		
		 		File f =new File(filepath + filename);
		 		fileInputStream.close();   
		 		
		 		
		 		out.close();   
		 		f.delete();
			}catch (Exception e) {
				System.out.println(e);
			}
			
		

		}
	 
	 
	//--------------//---Common Excel  Reports for All-----//
		 @RequestMapping(value="/download/Common/excelReportAll",method =RequestMethod.POST)
		 public void downExcelRepoAll(HttpServletRequest request,
					HttpServletResponse response,
					@RequestParam(value="pFileNameAll",required = false) String pFileName) {
				
				try{
					
			 		PrintWriter out = response.getWriter();  
			 		String filename = pFileName ;   
			 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
			 		response.setContentType("application/vnd.ms-excel"); 
			 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
			 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");   
			 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
			 		 
			 		int i;   
			 		while ((i=fileInputStream.read()) != -1) {  
			 			out.write(i);   
			 		}   
			 		
			 		File f =new File(filepath + filename);
			 		fileInputStream.close();   
			 		
			 		
			 		out.close();   
			 		f.delete();
				}catch (Exception e) {
					System.out.println(e);
				}
				
			

			}
	 
	

	
	//--------------//---HM-InvoiceExcel Reports-----//
	
	@RequestMapping(value = "/download/hmInvExcelReport", method = RequestMethod.POST)
	public String downHmInvExcelRepo(HttpServletRequest request,
			HttpServletResponse response) {
		
		try{
			
	 		PrintWriter out = response.getWriter();  
	 		String filename = "hmInv.xlsx";   
	 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
	 		response.setContentType("application/vnd.ms-excel");    
	 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");   
	 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
	 		 
	 		int i;   
	 		while ((i=fileInputStream.read()) != -1) {  
	 			out.write(i); 
	 		}   
	 		
	 		fileInputStream.close();   
	 		out.close();   
 		
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;

	}
	
	
	//-----------------Design CatLog Reports ---------//------------//
	
		@RequestMapping(value = "/download/DesignCatlogReport", method = RequestMethod.POST)
		public String downDesignCatlogPdfRepo(HttpServletRequest request,
				@RequestParam(value = "timeValCommonPdf") String fileName,
				HttpServletResponse response) {
			
			try{
				

		 		PrintWriter out = response.getWriter();  
		 		String filename = fileName+".pdf";     
		 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
		 		
		 		//response.setContentType("APPLICATION/OCTET-STREAM"); 
		 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   //  these two line will force the browser to download the pdf files
		 		
		 		response.setContentType("APPLICATION/pdf"); 
		 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");  
		 		
		 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		 		
		 		int i;   
		 		while ((i=fileInputStream.read()) != -1) {  
		 		out.write(i);   
		 		}   
		 		
		 		fileInputStream.close();   
		 		out.close();   
	 		
				
			}catch (Exception e) {
				System.out.println(e);
			}
			
			File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
			file.delete();
			
			return null;

		}
		
		
		
		
		//-----------------Design CatLog Reports All ---------//------------//
		
			@RequestMapping(value = "/download/DesignCatlogReportAll", method = RequestMethod.POST)
			public String downDesignCatlogPdfRepoAll(HttpServletRequest request,
					@RequestParam(value = "timeValCommonPdfAll") String fileName,
					HttpServletResponse response) {
				
				try{
					

			 		PrintWriter out = response.getWriter();  
			 		String filename = fileName+".pdf";     
			 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
			 		
			 		//response.setContentType("APPLICATION/OCTET-STREAM"); 
			 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   //  these two line will force the browser to download the pdf files
			 		
			 		response.setContentType("APPLICATION/pdf"); 
			 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");  
			 		
			 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
			 		
			 		int i;   
			 		while ((i=fileInputStream.read()) != -1) {  
			 		out.write(i);   
			 		}   
			 		
			 		fileInputStream.close();   
			 		out.close();   
		 		
					
				}catch (Exception e) {
					System.out.println(e);
				}
				
				File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
				file.delete();
				
				return null;

			}
	
	
			
			
			//--------Best Seller -----------------------------------------------//
			
			
			@RequestMapping(value = "/download/BestSellerReport", method = RequestMethod.POST)
			public String downBestSellerPdfRepo(HttpServletRequest request,
					@RequestParam(value = "timeValCommonPdf") String fileName,
					HttpServletResponse response) {
				
				try{
					

			 		PrintWriter out = response.getWriter();  
			 		String filename = fileName+".pdf";     
			 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
			 		
			 		//response.setContentType("APPLICATION/OCTET-STREAM"); 
			 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   //  these two line will force the browser to download the pdf files
			 		
			 		response.setContentType("APPLICATION/pdf"); 
			 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");  
			 		
			 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
			 		
			 		int i;   
			 		while ((i=fileInputStream.read()) != -1) {  
			 		out.write(i);   
			 		}   
			 		
			 		fileInputStream.close();   
			 		out.close();   
		 		
					
				}catch (Exception e) {
					System.out.println(e);
				}
				
				File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
				file.delete();
				
				return null;

			}
			

			
			//------------------------------------------------------------//
		
		
		
		//-----------------Export Job Pdf ---------//------------//
		
		@RequestMapping(value = "/download/exportJobCatalog", method = RequestMethod.POST)
		public String exportJobCatalog(HttpServletRequest request,
				HttpServletResponse response,@RequestParam(value = "timeValCommonPdf") String fileName) {
			
			try{  
				
		 		PrintWriter out = response.getWriter();  
		 		String filename = fileName+".pdf";     
		 		
		 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
		 		
		 		//response.setContentType("APPLICATION/OCTET-STREAM"); 
		 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   //  these two line will force the browser to download the pdf files
		 		
		 		response.setContentType("APPLICATION/pdf"); 
		 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");  
		 		
		 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		 		
		 		int i;   
		 		while ((i=fileInputStream.read()) != -1) {  
		 		out.write(i);   
		 		}   
		 		
		 		fileInputStream.close();   
		 		out.close();   
	 		
				
			}catch (Exception e) {
				System.out.println(e);
			}
			
			File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
			file.delete();
			
			return null;
		}
		
		
		//------Export job Tag------------//
		
		@RequestMapping(value = "/download/excelReport/exportJobTag", method = RequestMethod.POST)
		public String downExcelRepoExpJobTag(HttpServletRequest request,
				HttpServletResponse response) {
			
			try{
				
		 		PrintWriter out = response.getWriter();  
		 		String filename = "exportJobCostingTag.xlsx";
		 		String filepath = uploadDirecotryPath + reportoutputdirectorypath;
		 		response.setContentType("application/vnd.ms-excel"); 
		 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
		 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");   
		 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		 		 
		 		int i;   
		 		while ((i=fileInputStream.read()) != -1) {  
		 			out.write(i);   
		 		}   
		 		
		 		fileInputStream.close();   
		 		out.close();   
	 		
			}catch (Exception e) {
				System.out.println(e);
			}
			
			return null;

		}
		
		
		
		
		//-----------------Wip mail Pdf ---------//------------//
		
		@RequestMapping(value = "/download/wip/report", method = RequestMethod.POST)
		public String downPdfWipRepo(HttpServletRequest request,
				HttpServletResponse response,@RequestParam(value = "timeValCommonWipPdf") String fileName) {
			
			try{  
				
		 		PrintWriter out = response.getWriter();  
		 		String filename = fileName+".pdf";     
		 		
		 		String filepath = uploadDirecotryPath + reportoutputdirectorypath+"wip/";
		 		
		 		//response.setContentType("APPLICATION/OCTET-STREAM"); 
		 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   //  these two line will force the browser to download the pdf files
		 		
		 		response.setContentType("APPLICATION/pdf"); 
		 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");
		 		
		 		//response.setContentType(type);
		 		
		 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		 		

		 		
		 		int i;   
		 		while ((i=fileInputStream.read()) != -1) {  
		 		out.write(i);   
		 		}   
		 		
		 		fileInputStream.close();   
		 		out.close();   
	 		
				
			}catch (Exception e) {
				System.out.println(e);
			}
			
			
			File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
			file.delete();
			
			return null;
		}
		
		
		//---------download excel format ---//
		
		@RequestMapping("/download/excelformat")
		public String excelBlankFormat(HttpServletRequest request,
				HttpServletResponse response,
				@RequestParam(value = "fileName") String fileName) {
			
			try{
				
		 		PrintWriter out = response.getWriter();  
		 		String filename = fileName;
		 		String filepath = uploadDirecotryPath +  File.separator +"excelfilecontent" + File.separator;
		 		response.setContentType("application/vnd.ms-excel"); 
		 		//response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
		 		response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");  
		 		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		 		 
		 		int i;   
		 		while ((i=fileInputStream.read()) != -1) {  
		 			out.write(i);   
		 		}   
		 		
		 		fileInputStream.close();
		 		out.close();   
	 		
			}catch (Exception e) {
				System.out.println(e);
			}
			
			
			File file = new File(uploadDirecotryPath +  File.separator +"excelfilecontent" + File.separator + fileName);
			file.delete();
			
			return null;

		}
		
		
		
		
		
		
		
		
		
}
