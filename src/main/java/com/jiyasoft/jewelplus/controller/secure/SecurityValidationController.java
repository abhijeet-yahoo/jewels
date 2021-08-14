package com.jiyasoft.jewelplus.controller.secure;

import java.security.Principal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserLoginSecurity;
import com.jiyasoft.jewelplus.service.admin.UserLoginSecurityService;
import com.jiyasoft.jewelplus.service.admin.UserService;

@Controller
@RequestMapping("/secure")
public class SecurityValidationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserLoginSecurityService userLoginSecurityService;
	
	
	@ResponseBody
	@RequestMapping(value="/robatValidation", method=RequestMethod.POST)
	public String validateRobot(
			@RequestParam("pFirstVal") String pFirstVal,
			@RequestParam("pAnswerVal") String pAnswerVal,
			Principal principal){
		
		String retVal = "-1";
		
		try {
			
			if(pFirstVal.trim().equals(pAnswerVal.trim())){
				
				User user = userService.findByName(principal.getName()); 
				List<UserLoginSecurity> userLoginSecurities = userLoginSecurityService.findByUserAndDeactive(user, false);
				
				if(userLoginSecurities.size() > 0){
					
					String arr[] = new String[3];
					int i= 0;
					
					for(UserLoginSecurity userLogin:userLoginSecurities){
						
						if(i==0){
							arr[0] = userLogin.getQuestion();
						}else if(i == 1){
							arr[1] = userLogin.getQuestion();
						}else{
							arr[2] = userLogin.getQuestion();
						}
						
						i++;
					}
					
					
					Random random = new Random();
					int number = random.nextInt(2 - 0 + 1) + 0;
					String finalQuestion = arr[number];
					retVal = "-2$"+finalQuestion;

				}else{
					
					retVal = "-5";
					
				}
				
				
			}
			
		} catch (Exception e) {
			retVal = "-3";
		}
		
		return retVal;
	}
	
	
	
	@RequestMapping(value = "/questionValidation", method=RequestMethod.POST)
	@ResponseBody
	public String questionValidation(
			@RequestParam("pQuest") String pQuest,
			@RequestParam("pAnswer") String pAnswer,
			Principal principal){
		
			
		String retVal = "/jewels/secure/login.html";
		
		User user = userService.findByName(principal.getName());
		UserLoginSecurity userLoginSecurity = userLoginSecurityService.findByUserAndQuestionAndDeactive(user, pQuest, false);
		
		if(userLoginSecurity.getAnswer().trim().equalsIgnoreCase(pAnswer.trim())){
			retVal = "/jewels/secure/logiinDefault.html";
		}
		
		
		return retVal;
	}
	
	
	
	
	
	
	
}
