package com.jiyasoft.jewelplus.controller.admin;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.common.CommonUtils;
import com.jiyasoft.jewelplus.common.PasswordEncoderDecoder;
import com.jiyasoft.jewelplus.domain.admin.EmailConfig;
import com.jiyasoft.jewelplus.service.admin.EmailConfigService;
import com.jiyasoft.jewelplus.service.admin.UserService;


@RequestMapping("/admin")
@Controller
public class EmailConfigController {

	@Autowired
	private EmailConfigService emailConfigService;
	
	@Autowired
	private UserService userService;
	
	
	@ModelAttribute("emailConfig")
	public EmailConfig construct() {
		return new EmailConfig();
	}
	
	@RequestMapping("/emailConfig")
	public String emailConfig(Model model, Principal principal) {
		
		List<EmailConfig> emailConfigs = emailConfigService.findAll();
		
		if(emailConfigs.size() > 0){
			EmailConfig emailConfig = emailConfigs.get(0);
			model.addAttribute("emailConfig", emailConfig);
			model.addAttribute("toEmialValFromDb", emailConfig.getToEmailId());
		}
			return "emailConfig";
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/emailConfig", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("emailConfig") EmailConfig emailConfig,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value="eMailIds") String eMailIds) throws Exception {

		String action = "added";
		String retVal = "/jewels/admin/emailConfig.html?success=true";

		if (result.hasErrors()) {
			return "emailConfig";
		}
		
		
		
		if(eMailIds.length() > 0){
			String tempEmailIds[] = eMailIds.split(",");
			for(int i=0;i<tempEmailIds.length;i++){
				String tempVal = tempEmailIds[i];
				if(tempVal.length() <= 0){
					return retVal = "-1$"+i;
				}else{
					Boolean resultVal = CommonUtils.isValidEmailAddress(tempVal);
					if(!resultVal){
						return retVal = "-2#"+i;
					}
				}
			}
		}
		
		String toEmailIdTemp = "";
		if(eMailIds.length() > 0){
			toEmailIdTemp = emailConfig.getToEmailId()+","+eMailIds;
		}else{
			toEmailIdTemp = emailConfig.getToEmailId();
		}
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			emailConfig.setCreatedBy(principal.getName());
			emailConfig.setCreatedDt(new java.util.Date());
			emailConfig.setDeactive(false);
		} else {
			emailConfig.setModiBy(principal.getName());
			emailConfig.setModiDt(new java.util.Date());
			action = "updated";
		}
		
		emailConfig.setToEmailId(toEmailIdTemp);
		
		PasswordEncoderDecoder ped = new PasswordEncoderDecoder();
		emailConfig.setPassword(ped.encrypt(emailConfig.getPassword()));
		
		emailConfigService.save(emailConfig);
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}


	@RequestMapping("/emailConfig/delete/{id}")
	public String delete(@PathVariable int id) {
		emailConfigService.delete(id);

		return "redirect:/admin/emailConfig.html";
	}

	@RequestMapping("/emailConfigAvailable")
	@ResponseBody
	public String emailConfigAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean emailConfigAvailable = true;

		name = name.trim();

		if (id == null) {
			emailConfigAvailable = (emailConfigService.findByName(name) == null);
		} else {
			EmailConfig emailConfig = emailConfigService.findOne(id);
			if (!(name.equalsIgnoreCase(emailConfig.getName()))) {
				emailConfigAvailable = (emailConfigService.findByName(name) == null);
			}
		}

		return emailConfigAvailable.toString();
	}
}
