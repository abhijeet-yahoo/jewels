package com.jiyasoft.jewelplus.controller.admin;

import java.security.Principal;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserLoginSecurity;
import com.jiyasoft.jewelplus.service.admin.UserLoginSecurityService;
import com.jiyasoft.jewelplus.service.admin.UserService;

@Controller
@RequestMapping("/admin")
public class UserLoginSecurityController {
	
	
	@Autowired
	private UserLoginSecurityService userLoginSecurityService;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("userLoginSecurity")
	public UserLoginSecurity construct(){
		return new UserLoginSecurity();
	}
	
	
	@RequestMapping("/userLoginSecurity")
	public String userLoginSecurity(Model model){
		model = populateModel(model);
		return "userLoginSecurity";
	}
	
	
	public Model populateModel(Model model){
		model.addAttribute("userMap", userService.getUserList());
		model.addAttribute("question1Map", userLoginSecurityService.questionList());
		model.addAttribute("question2Map", userLoginSecurityService.questionList());
		model.addAttribute("question3Map", userLoginSecurityService.questionList());
		return model;
	}
	
	
	@ResponseBody
	@RequestMapping("/status")
	public String status(
			@RequestParam("userId") Integer userId){
		
		String retVal = "-1";
		
		User user = userService.findOne(userId);
		List<UserLoginSecurity> userLoginSecurity = userLoginSecurityService.findByUserAndDeactive(user, false);
		
		if(userLoginSecurity.size() > 0){
			
			List<String> questionsList = new ArrayList<String>();
			for(UserLoginSecurity userLoginSec : userLoginSecurity){
				questionsList.add(userLoginSec.getQuestion());
			}
			
			retVal = "-2$"+userLoginSecurityService.popEditQuestions(questionsList);
		}
		
		return retVal;
	}
	
	
	
	@RequestMapping("/questionOne/combo")
	@ResponseBody
	public String questionOne(
			@RequestParam(value = "question") String question){
		String tempList = userLoginSecurityService.onChangeQuestionOne(question);
		return tempList;
	}
	
	@RequestMapping("/questionTwo/combo")
	@ResponseBody
	public String questionTwo(
			@RequestParam(value = "question") String question){
		String tempList = userLoginSecurityService.onChangeQuestionTwo(question);
		return tempList;
	}
	
	@RequestMapping("/questionThree/combo")
	@ResponseBody
	public String questionThree(
			@RequestParam(value = "question") String question){
		String tempList = userLoginSecurityService.onChangeQuestionThree(question);
		return tempList;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addEdit(@ModelAttribute("userLoginSecurity") UserLoginSecurity userLoginSecurity,Principal principal,
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "pQuestion1") String question1,
			@RequestParam(value = "pQuestion2") String question2,
			@RequestParam(value = "pQuestion3") String question3,
			@RequestParam(value = "pAnswer1") String answer1,
			@RequestParam(value = "pAnswer2") String answer2,
			@RequestParam(value = "pAnswer3") String answer3,
			@RequestParam(value = "pUserId") Integer userId){
		
		
			String retVal = "-1";
			
			if(userId != null){
				
				
				User user = userService.findOne(userId);
				
				if(question1 != "" || question2 != "" || question3 != "" || answer1 != "" || answer2 != "" || answer3 != ""){
					
					
					
					if(answer1.trim().equalsIgnoreCase(answer2.trim()) && answer1.trim().equalsIgnoreCase(answer3.trim())){
						return retVal = "-4";
					}
					
					
					for(int i=0 ; i<3;i++){
						
						System.out.println(i);
						
						UserLoginSecurity userLSObj = new UserLoginSecurity();
						
						if(i == 0){
							userLSObj.setQuestion(question1);
							userLSObj.setAnswer(answer1);
							userLSObj.setUser(user);
							userLSObj.setCreatedBy(principal.getName());
							userLSObj.setCreateDate(new java.util.Date());
						}else if(i == 1){
							userLSObj.setQuestion(question2);
							userLSObj.setAnswer(answer2);
							userLSObj.setUser(user);
							userLSObj.setCreatedBy(principal.getName());
							userLSObj.setCreateDate(new java.util.Date());
						}else{
							userLSObj.setQuestion(question3);
							userLSObj.setAnswer(answer3);
							userLSObj.setUser(user);
							userLSObj.setCreatedBy(principal.getName());
							userLSObj.setCreateDate(new java.util.Date());
						}
						
						userLoginSecurityService.save(userLSObj);
						
					}
					
					
				}else{
					return retVal = "-2";
				}
				
				
				
			}else{
				
				return retVal = "-3";
				
			}
			
		
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/update")
	public String editUpdate(
			@RequestParam("userId") Integer userId,
			@RequestParam("question") String question,
			@RequestParam("answer") String answer,
			Principal principal){
		
			String retVal = "-1";
			
			User user = userService.findOne(userId);
			
			UserLoginSecurity userLoginSecurity = userLoginSecurityService.findByUserAndQuestionAndDeactive(user, question, false);
			
			if(userLoginSecurity != null){
				userLoginSecurity.setAnswer(answer);
				userLoginSecurity.setModiBy(principal.getName());
				userLoginSecurity.setModiDt(new java.util.Date());
				userLoginSecurityService.save(userLoginSecurity);
			}else{
				retVal = "-2";
			}
			
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/deleteAll")
	public String deleteAll(
			@RequestParam("userId") Integer userId){
		
		
		User user = userService.findOne(userId);
		List<UserLoginSecurity> userLoginSecurity = userLoginSecurityService.findByUserAndDeactive(user, false);
		
		for(UserLoginSecurity userLogin:userLoginSecurity){
			userLogin.setDeactive(true);
			userLoginSecurityService.save(userLogin);
		}
		
		return "-1";
		
		
	}
	

 }
