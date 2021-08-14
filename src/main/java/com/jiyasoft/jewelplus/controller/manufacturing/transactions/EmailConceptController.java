package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmailConcept;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IEmailConceptService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class EmailConceptController {

	@Autowired
	private IEmailConceptService emailConceptService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IDesignService designService;;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;

	@Value("${tmpUploadImage}")
	private String tmpUploadImage;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;
	
	private String uploadFilePath = "";

	

	@ModelAttribute("emailConcept")
	public EmailConcept construct() {
		return new EmailConcept();
	}

	@RequestMapping("/emailConcept")
	public String users(Model model) {
		return "emailConcept";
	}
	

	@RequestMapping("/emailConcept/listing")
	@ResponseBody
	public String emailConceptListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<EmailConcept> emailConcepts = null;
		
			
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = emailConceptService.count(sort, search, false);
			emailConcepts = emailConceptService.findByName(limit, offset, sort, order,search, false);
		}else {
			rowCount = emailConceptService.countAll(search);
			emailConcepts = emailConceptService.searchAll(limit, offset, sort, order,search);
			
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (EmailConcept emailConcept : emailConcepts) {
			sb.append("{\"id\":\"")
					.append(emailConcept.getId())
					.append("\",\"emailConIdMax\":\"")
					.append((emailConcept.getEmailConIdMax() == null ? "" : emailConcept.getEmailConIdMax() ))
					.append("\",\"date\":\"")
					.append((emailConcept.geteDateStr() == null ? "" : emailConcept.geteDateStr()))
					.append("\",\"name\":\"")
					.append((emailConcept.getName() == null ? "" : emailConcept.getName()))
					.append("\",\"version\":\"")
					.append((emailConcept.getVersion() == null ? "" : emailConcept.getVersion()))
					.append("\",\"party\":\"")
					.append((emailConcept.getParty() == null ? "" : emailConcept.getParty().getName()))
					.append("\",\"category\":\"")
					.append((emailConcept.getCategory() == null ? "" : emailConcept.getCategory().getName()))
					.append("\",\"hold\":\"")
					.append((emailConcept.getHold() == null ? "" : (emailConcept.getHold() ? "Yes":"No")))
					.append("\",\"cancel\":\"")
					.append((emailConcept.getCancel() == null ? "" : (emailConcept.getCancel() ? "Yes":"No")))
					.append("\",\"done\":\"")
					.append((emailConcept.getDone() == null ? "" : (emailConcept.getDone() ? "Yes":"No")))
					.append("\",\"modify\":\"")
					.append((emailConcept.getModify() == null ? "" : (emailConcept.getModify() ? "Yes":"No")))
					.append("\",\"versionFlg\":\"")
					.append((emailConcept.getVersionFlg() == null ? "" : (emailConcept.getVersionFlg() ? "Yes":"No")))
					.append("\",\"deactive\":\"")
					.append((emailConcept.getDeactive() == null ? "" : (emailConcept.getDeactive() ? "deactive":"active")))
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/emailConcept/edit/")
					.append(emailConcept.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/emailConcept/delete/")
					.append(emailConcept.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(emailConcept.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}

	
	@RequestMapping("/emailConcept/add")
	public String add(Model model,Principal principal) {
		
		Date aDate=new java.util.Date();
		
		EmailConcept emailConcept=new EmailConcept();
		emailConcept.setCreatedBy(principal.getName());
		emailConcept.setCreatedDate(new java.util.Date());
		emailConcept.setDeactive(false);
		emailConcept.setUniqueId(aDate.getTime());
		emailConcept.setEmailConIdMax(emailConceptService.maxSrNo()+1);	
		
		model = populateModel(model);		
		emailConceptService.save(emailConcept);		
		return "redirect:/manufacturing/transactions/emailConcept/edit/"+emailConcept.getId()+".html";
		
	}
	
	private Model populateModel(Model model) {
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("categoryMap", categoryService.getCategoryList());
			
		return model;
	}

	@RequestMapping(value = "/emailConcept/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("emailConcept") EmailConcept emailConcept,
			BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "emailImage") String emailImage,
			@RequestParam(value = "cadImage") String cadImage,
			@RequestParam(value = "roughImage") String roughImage,
			@RequestParam(value = "designImage") String designImage,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/emailConcept/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			return "emailConcept/add";
		}
		

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			aDate=new java.util.Date(); 
			emailConcept.setCreatedBy(principal.getName());
			emailConcept.setCreatedDate(new java.util.Date());
			emailConcept.setDeactive(false);
			emailConcept.setUniqueId(aDate.getTime());
			
		} else {
		
			if (emailConcept.getDeactive() != emailConceptService.findOne(id).getDeactive()) {
				emailConcept.setDeactiveDt(new java.util.Date());
			} else {
				emailConcept.setDeactiveDt(emailConceptService.findOne(id).getDeactiveDt());
			}
			
			emailConcept.setModiBy(principal.getName());
			emailConcept.setModiDate(new java.util.Date());
			emailConcept.setId(id);

			emailConcept.setEmailImage(emailImage != null ? emailImage : "blank.png");
			emailConcept.setCadImage(cadImage != null ? cadImage : "blank.png");
			emailConcept.setRoughImage(roughImage != null ? roughImage : "blank.png");
			emailConcept.setDesignImage(designImage != null ? designImage : "blank.png");
			
			action = "updated";
			retVal = "redirect:/manufacturing/transactions/emailConcept.html";
		}

		if (emailConcept.getCategory().getId() == null) {
			emailConcept.setCategory(null);
		}
		if (emailConcept.getParty().getId() == null) {
			emailConcept.setParty(null);
		}

		emailConceptService.save(emailConcept);		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/emailConcept/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		String uploadFilePathEmail = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/emailConcept/emailImage/";
		String uploadFilePathCad   = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/emailConcept/cadImage/";
		String uploadFilePathRough = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/emailConcept/roughImage/";
		String uploadFilePathDesign = "/jewels/uploads/"+ uploadDirecotryName.replaceAll("\\\\", "/") + "/emailConcept/designImage/";
		
		
		EmailConcept emailConcept = emailConceptService.findOne(id);
		model.addAttribute("emailConcept", emailConcept);
		model.addAttribute("emailImg", emailConcept.getEmailImage() != null ? emailConcept.getEmailImage() : "blank.png" );
		model.addAttribute("cadImg", emailConcept.getCadImage() != null ? emailConcept.getCadImage() : "blank.png" );
		model.addAttribute("roughImg", emailConcept.getRoughImage() != null ? emailConcept.getRoughImage() : "blank.png" );
		model.addAttribute("designImg", emailConcept.getDesignImage() != null ? emailConcept.getDesignImage() : "blank.png" );
		
		model.addAttribute("uploadFilePathEmail", uploadFilePathEmail);
		model.addAttribute("uploadFilePathCad", uploadFilePathCad);
		model.addAttribute("uploadFilePathRough", uploadFilePathRough);
		model.addAttribute("uploadFilePathDesign", uploadFilePathDesign);
		
		model = populateModel(model);

		return "emailConcept/add";
	}

	@RequestMapping("/emailConcept/delete/{id}")
	public String delete(@PathVariable int id) {
		try{
		emailConceptService.delete(id);
		}catch(Exception e){
			System.out.println("in catch");
			
		}

		return "redirect:/manufacturing/transactions/emailConcept.html";
	}

	@RequestMapping("/emailConceptAvailable")
	@ResponseBody
	public String emailConceptAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean emailConceptAvailable = true;

		if (id == null) {
			emailConceptAvailable = (emailConceptService.findByName(name) == null);
		} else {
			EmailConcept emailConcept = emailConceptService.findOne(id);
			if (!(name.equalsIgnoreCase(emailConcept.getName()))) {
				emailConceptAvailable = (emailConceptService.findByName(name) == null);
			}
		}

		return emailConceptAvailable.toString();
	}

	private String getDesignImage(String styleNo,String version) {
		
		Design design = designService.findByStyleNoAndVersion(styleNo, version);

		String uploadFilePath = "/jewels/" + uploadParentDirecotryName + File.separator
				+ uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		tmpUploadImage = tmpUploadImage.replaceAll("\\\\", "/");

		String imgTmp = null;
		if (design != null) {
			imgTmp = (design.getDefaultImage() == null ? null : (design.getDefaultImage() != null && design.getDefaultImage().trim().length() == 0) ? null : design.getDefaultImage());		
		}

		if (imgTmp == null) {
			imgTmp = uploadFilePath + tmpUploadImage;
		} else {
			imgTmp = uploadFilePath + imgTmp;
		}
			
		
		return imgTmp;
	}
	

	@RequestMapping("/design/image/version")
	@ResponseBody
	public String getDesignImg(
			@RequestParam(value = "styleNo") String styleNo,
			@RequestParam(value = "version") String version) {
		return getDesignImage(styleNo,version);
	}

	
	//for email Image
	@RequestMapping(value = "/emailConcept/emailImage/upload", method = RequestMethod.POST)
	@ResponseBody
	public String emailImageUpload(HttpServletRequest request,
			@RequestParam(value = "file", required = false) Part file,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "jid", required = false) String jid,
			Model model) throws IOException, ServletException {
		
		uploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "emailConcept" + File.separator + "emailImage"
				+ File.separator;

		String retVal = Utils.imageUpload(request, file, image, jid, model,uploadFilePath);

		return retVal;
	}
	
	
	
	// for design Image
	
	@RequestMapping(value = "/emailConcept/designImage/upload", method = RequestMethod.POST)
	@ResponseBody
	public String designImageUpload(HttpServletRequest request,
			@RequestParam(value = "file", required = false) Part file,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "jid", required = false) String jid,
			Model model) throws IOException, ServletException {
		
		uploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "emailConcept" + File.separator + "designImage"
				+ File.separator;

		String retVal = Utils.imageUpload(request, file, image, jid, model,uploadFilePath);

		return retVal;
	}
	
	
	// for cad Image
	
	@RequestMapping(value = "/emailConcept/cadImage/upload", method = RequestMethod.POST)
	@ResponseBody
	public String cadImageUpload(HttpServletRequest request,
			@RequestParam(value = "file", required = false) Part file,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "jid", required = false) String jid,
			Model model) throws IOException, ServletException {
		
		uploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "emailConcept" + File.separator + "cadImage"
				+ File.separator;

		String retVal = Utils.imageUpload(request, file, image, jid, model,uploadFilePath);

		return retVal;
	}
	
	// for rough Image
	
	@RequestMapping(value = "/emailConcept/roughImage/upload", method = RequestMethod.POST)
	@ResponseBody
	public String roughImageUpload(HttpServletRequest request,
			@RequestParam(value = "file", required = false) Part file,
			@RequestParam(value = "image", required = false) String image,
			@RequestParam(value = "jid", required = false) String jid,
			Model model) throws IOException, ServletException {
		
		uploadFilePath = uploadDirecotryPath + File.separator
				+ uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "emailConcept" + File.separator + "roughImage"
				+ File.separator;

		String retVal = Utils.imageUpload(request, file, image, jid, model,uploadFilePath);

		return retVal;
	}
	
	
	
	


	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}
