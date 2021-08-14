package com.jiyasoft.jewelplus.controller.secure;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyasoft.jewelplus.service.admin.IPAddressAccessService;

@RequestMapping("/secure")
@Controller
public class LoginController {
	
	
	@Autowired
	private IPAddressAccessService ipAddressAccessService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping("/loginDefault")
	public String loginDefault(Model model,HttpServletRequest request,Principal principal) {
		String retVal = "loginDefault";
		String url = request.getRequestURL().toString();
		String staticIp =  url.substring(url.indexOf("//")+2, url.lastIndexOf(":"));
		if(staticIp.equalsIgnoreCase(ipAddressAccessService.findOne(1).getStaticIP())){
			retVal = "securityValidation";
		}
		
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
	     model.addAttribute("userName", name);
		
		return retVal;
	}
	
	
	@RequestMapping("/logiinDefault")
	public String loginDefault() {
		return "loginDefault";
	}
	
	
	@RequestMapping("/loginFailedSession")
	public String loginFailed(){
		return "loginFailedSession";
	}
	
}
