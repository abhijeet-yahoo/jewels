package com.jiyasoft.jewelplus.common;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfImageObject;

public class Utils {

	@Value("${report.directory.path}")
	private String reportPath;
	
	@Value("${tmpUploadImage}")
	private String tmpUploadImage;
	
	public static float FACTOR = 0.5f;


	/***********************************************************************************
	 * To get the file name of the uploaded file
	 **********************************************************************************/
	public static String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");

		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2,
						token.length() - 1);
			}
		}

		return "";
	}


	/***********************************************************************************
	 * File upload procedure
	 **********************************************************************************/
	public static String imageUpload(HttpServletRequest request,
			@RequestParam(value = "file", required = false) Part file,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "jid", required = false) String jid,
			Model model, String uploadFilePath) throws IOException, ServletException {

		
		
		String retVal = "1";

		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}

		String fileName = null;
		String fileNameExtn = null;
		List<String> fileNameExtnList = new ArrayList<String>();

		if (file != null) {
			if (file.getContentType() == null) {
				model.addAttribute("emptyError", "Image is empty");
				retVal = "-1";
			} else {
				// Check the file size, should not be more than 5MB
				if (file.getSize() > (1048576 * 10)) {
					model.addAttribute("sizeError", "Check the file size uploaded");
					retVal = "-1";

					return retVal;
				}

				fileNameExtnList.add("png");
				fileNameExtnList.add("PNG");
				fileNameExtnList.add("JPG");
				fileNameExtnList.add("jpg");
				fileNameExtnList.add("gif");
				fileNameExtnList.add("GIF");
				fileNameExtnList.add("xml");
				fileNameExtnList.add("txt");
				fileNameExtnList.add("pdf");
				fileNameExtnList.add("jpeg");

				Part part = request.getPart("file");
				fileName = getFileName(part);

				fileNameExtn = fileName.substring(fileName.lastIndexOf(".") + 1);

				if (!(fileNameExtnList.contains(fileNameExtn))) {
					model.addAttribute("formatError", "Please select png/gif/jpg files");
					retVal = "-1";
				} else {
					fileName = jid;

					InputStream inputStream = null;
					OutputStream outputStream = null;

					try {
						File outputFilePath = new File(uploadFilePath
								+ fileName);
	 					if (outputFilePath.exists()) {
							outputFilePath.delete();
						}

						inputStream = part.getInputStream();
						outputStream = new FileOutputStream(outputFilePath);

						int read = 0;
						final byte[] bytes = new byte[1024];
						while ((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
					} catch (FileNotFoundException fne) {
						fne.printStackTrace();
						retVal = "-1";
					} finally {
						if (outputStream != null) {
							outputStream.close();
						}
						if (inputStream != null) {
							inputStream.close();
						}
					}
				}
			}
		}

		return retVal;
	}


	/***********************************************************************************
	 * Check Image
	 **********************************************************************************/
	public static void checkImage(String uploadFilePath, String imgName) {
		File fn = new File(uploadFilePath + "__" + imgName); 
		if (fn.exists()) {
			File ff = new File(uploadFilePath + imgName); 
			if (ff.exists()) {
				ff.delete();
			}

			fn.renameTo(new File(uploadFilePath + imgName));
		}
	}


	/***********************************************************************************
	 * Get Database Connection
	 **********************************************************************************/
	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcUrl = getPropertyValue("jdbc.url");
			String username = getPropertyValue("db.username");
			String password = getPropertyValue("db.password");
			conn = DriverManager.getConnection(jdbcUrl, username, password);
 		} catch (Exception e) {
			System.out.println(e);
		}

		return conn;
	}


	/***********************************************************************************
	 * To get the report's directory path
	 * @throws IOException 
	 **********************************************************************************/
	public static String getPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		String propFileName = "jewelplus.properties";
		String reportPath = null;

		InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(propFileName);
		
		if (inputStream != null) {
			prop.load(inputStream);
			reportPath = prop.getProperty(key);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}

		return reportPath;
	}
	
	
	/***********************************************************************************
	 * Pdf Compression Code
	 **********************************************************************************/
	
	
	public static void manipulatePdf(String src, String dest) throws IOException, DocumentException {
	    PdfName key = new PdfName("ITXT_SpecialId");
	    PdfName value = new PdfName("123456789");
	    PdfReader reader = new PdfReader(src);
	    int n = reader.getXrefSize();
	    PdfObject object;
	    PRStream stream;
	    for (int i = 0; i < n; i++) {
	        object = reader.getPdfObject(i);
	        if (object == null || !object.isStream())
	            continue;
	        stream = (PRStream)object;
	        PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
	        if (pdfsubtype != null && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
	            PdfImageObject image = new PdfImageObject(stream);
	            BufferedImage bi = image.getBufferedImage();
	            if (bi == null) continue;
	            int width = (int)(bi.getWidth() * FACTOR);
	            int height = (int)(bi.getHeight() * FACTOR);
	            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            AffineTransform at = AffineTransform.getScaleInstance(FACTOR, FACTOR);
	            Graphics2D g = img.createGraphics();
	            g.drawRenderedImage(bi, at);
	            ByteArrayOutputStream imgBytes = new ByteArrayOutputStream();
	            ImageIO.write(img, "JPG", imgBytes);
	            stream.clear();
	            stream.setData(imgBytes.toByteArray(), false, PRStream.BEST_COMPRESSION);
	            stream.put(PdfName.TYPE, PdfName.XOBJECT);
	            stream.put(PdfName.SUBTYPE, PdfName.IMAGE);
	            stream.put(key, value);
	            stream.put(PdfName.FILTER, PdfName.DCTDECODE);
	            stream.put(PdfName.WIDTH, new PdfNumber(width));
	            stream.put(PdfName.HEIGHT, new PdfNumber(height));
	            stream.put(PdfName.BITSPERCOMPONENT, new PdfNumber(8));
	            stream.put(PdfName.COLORSPACE, PdfName.DEVICERGB);
	        }
	    }
	    
	    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
	    stamper.close();
	    reader.close();
	}
	
	
	
	
	
	/***********************************************************************************
	 * BarCode Genrater  Code
	 * @throws BarcodeException 
	 * @throws OutputException 
	 **********************************************************************************/
	
	
	
	public  static String barcodeGeneration(Integer styleId,String barCodeContent,String uploadFilePath){
		
		String retVal = "1";
		
		try {
			Barcode barcode = BarcodeFactory.createCode128B(barCodeContent);
			barcode.setBarHeight(60);
		    barcode.setBarWidth(2);
		    barcode.setDrawingText(false);
		    
		    File imgFile = new File(uploadFilePath+styleId+".jpeg");
		    BarcodeImageHandler.saveJPEG(barcode, imgFile);
		    
		    
		} catch (BarcodeException e) {
			e.printStackTrace();
			retVal = "-1";
		} catch (OutputException e) {
			e.printStackTrace();
			retVal = "-1";
		}
	    

		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*public static void main(String[] args) {
		String str = "129018";
		String str2 = String.format("%10s", str).replace(' ', '0');
		System.out.println(str2 + " " + String.format("%04d", 1));
	}*/
}
