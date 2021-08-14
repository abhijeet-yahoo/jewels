package com.jiyasoft.jewelplus.controller.manufacturing.masters.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.common.CommonUtils;
import com.jiyasoft.jewelplus.common.PasswordEncoderDecoder;
import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.EmailConfig;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.service.admin.EmailConfigService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;

@Controller
@RequestMapping("/manufacturing/masters/reports")
public class MailSendingController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailConfigService emailConfigService;
	
	@Autowired
	private IReportFilterService reportFilterService;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	@Value("${report.output.directory.path}")
	private String reportoutputdirectorypath;
	
	@Value("${report.xml.directory.path}")
	private String reportXmlDirectoryPath;

	
	
	@ModelAttribute("category")
	public Category construct() {
		return new Category();
	}
	
	
	@RequestMapping("/mailData")
	@ResponseBody
	public String mailData(){
		
		String retVal = "-1";
		List<EmailConfig> emailConfigs = emailConfigService.findAll();
		if(emailConfigs.size() > 0){
			EmailConfig emailConfig = emailConfigs.get(0);
			retVal = emailConfig.getToEmailId()+"$"+emailConfig.getSubject()+"$"+emailConfig.getMessage();
		}
		return retVal;
	}
	

	@ResponseBody
	@RequestMapping(value = "/send/mail" , method=RequestMethod.POST)
	public String sendEmail(HttpServletRequest request,
			@RequestParam(value = "fileNm") String fileNm,
			@RequestParam(value = "to") String to,
			@RequestParam(value = "cc") String cc,
			@RequestParam(value = "subject") String subject,
			@RequestParam(value = "message") String message) {
		
		String retVal = "-1";
		
		//System.out.println(fileNm);
		//System.out.println(to);
		//System.out.println(cc);
		//System.out.println(subject);
		//System.out.println(message);
		
		
		try {
			
			final String fileName = fileNm+".pdf";
			final File file = new File(uploadDirecotryPath+reportoutputdirectorypath+"wip"+File.separator+fileName);
			final String toMail[] = to.split(",");
			final String ccc = cc;
			final String subjects = subject;
			final String messages = message;
			
			EmailConfig emailConfig = null;
			List<EmailConfig> emailConfigs = emailConfigService.findAll();
			if(emailConfigs.size() > 0){
				 emailConfig = emailConfigs.get(0);
			}
			
			PasswordEncoderDecoder ped = new PasswordEncoderDecoder();
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			
			Properties properties = new Properties();
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.smtp.auth", true);
			properties.put("mail.smtp.starttls.enable", true);
			properties.put("mail.debug", true);
			mailSender.setJavaMailProperties(properties);
			mailSender.setHost("smtp.Mailhostbox.com");
			//mailSender.setHost("smtp.mail.yahoo.com");
			mailSender.setPort(587);
			//mailSender.setPort(465);
			mailSender.setUsername(emailConfig.getFromEmailId());
			mailSender.setPassword(ped.decrypt(emailConfig.getPassword()));
			
			
			mailSender.send(new MimeMessagePreparator() {
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
					mimeMessageHelper.setTo(toMail);
					if(ccc.length() > 0){
						mimeMessageHelper.setCc(ccc);
					}
					mimeMessageHelper.setSubject(subjects);
					mimeMessageHelper.setText(messages);
					
					if(file.exists()){
						mimeMessageHelper.addAttachment(fileName, file);
					}
					
				}
			});
			
			
			
			retVal = "-2";
			
			if(file.exists()){
				file.delete();
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			retVal = "-3";
		}
		
		
		return retVal;
	}
	
	
	
	
	
	
	@RequestMapping(value="/wip/gen/email", method = RequestMethod.POST)
	@ResponseBody
	public String generateReport(
			@RequestParam(value="pClientId") String clientIds,
			@RequestParam(value="pOrderTypeId") String orderTypeIds,
			@RequestParam(value="pOrderId") String orderIds,Principal principal) throws SQLException{
		
		String retVal="-1";
		
		ReportFilter reportFilter= reportFilterService.findByName("wip");
		String xml=reportFilter.getXml();
		
		Connection conn = null;

		try{
		conn = Utils.getConnection();
		String partyCond = "";
		String orderTypeCond = "";
		String orderCond = "";

		if (clientIds.length() > 0) {
			partyCond =  " ' PartyId in (" + clientIds + ") ' ";
		}else{
			partyCond = " ' ' ";
		}
		
		if (orderTypeIds.length() > 0) {
			orderTypeCond =  " ' OrdTypeId in (" + orderTypeIds + ") ' ";
		}else{
			orderTypeCond = " ' ' ";
		}
		
		
		if (orderIds.length() > 0) {
			orderCond = " ' sordMtId in (" + orderIds + ") ' ";
		}else{
			orderCond = " ' ' ";
		}
	
		
		String	fileName = uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/") +xml+".jasper";
		String	subRptPath =  uploadDirecotryPath + reportXmlDirectoryPath.replaceAll("\\\\", "/");
		String	imgPath = uploadDirecotryPath+"/" + uploadParentDirecotryName.replaceAll("\\\\", "/") +"/"+uploadDirecotryName.replaceAll("\\\\", "/") + "/design/" ;
	
		InputStream input = new FileInputStream(new File(fileName));
		java.util.Map<String, Object> parametersMap = new java.util.HashMap<String, Object>();

		parametersMap.put("partyCond", partyCond);
		parametersMap.put("orderTypeCond", orderTypeCond);
		parametersMap.put("orderCond", orderCond);
		parametersMap.put("imagepath", imgPath);
		parametersMap.put("subrptpath", subRptPath);
	
		
		JasperPrint jp = JasperFillManager.fillReport(input,parametersMap, conn);
		
		
		String commonFileName = System.currentTimeMillis()+"";
		commonFileName = commonFileName+xml;
		File file = new File(uploadDirecotryPath + reportoutputdirectorypath +"wip/"+ commonFileName+".pdf");
		file.createNewFile();
		JasperExportManager.exportReportToPdfFile(jp, uploadDirecotryPath + reportoutputdirectorypath +"wip/"+ commonFileName+".pdf");
		
		String exportCommonFileName = System.currentTimeMillis()+""+principal.getName()+xml;
		Utils.manipulatePdf(uploadDirecotryPath + reportoutputdirectorypath +"wip/" +commonFileName+".pdf", uploadDirecotryPath + reportoutputdirectorypath +"wip/"+exportCommonFileName+".pdf");
		
		file.delete();
		
		retVal = exportCommonFileName;
		
		
		}catch (Exception e) {
			//File file = new File(uploadDirecotryPath + reportoutputdirectorypath + fileName+".pdf");
			//file.delete();
			System.out.println(e);
			retVal = "-5";
		} finally {
			conn.close();
		}

		return retVal;

		
		
		
		
	}
	
	
	
	
	
	
	
	
}
