package com.jiyasoft.jewelplus.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserLoginSecurity;
import com.jiyasoft.jewelplus.repository.admin.IUserLoginSecurityRepository;


@Service
@Repository
@Transactional
public class UserLoginSecurityService {

	
	@Autowired
	private IUserLoginSecurityRepository userLoginSecurityRepository;
	
	
	public void save(UserLoginSecurity userLoginSecurity){
		userLoginSecurityRepository.save(userLoginSecurity);
	}
	
	public List<UserLoginSecurity> findByUserAndDeactive(User user,Boolean deactive){
		return userLoginSecurityRepository.findByUserAndDeactive(user, deactive);
	}
	
	
	public List<String> questionList(){
		
		List<String> questions = new ArrayList<String>();
			
		questions.add("When is your anniversary ?");
		questions.add("What is your favorite color ?");
		questions.add("What is your father's middle name ?");
		questions.add("What is the name of your favorite pet ?");
		questions.add("What is your favorite movie ?");
		questions.add("what is your favorite food ?");
		questions.add("Who is your favorite actor, musician, or artist ?");
		questions.add("Who is your favorite sports personality ?");
		questions.add("who is your favorite leader ?");
		questions.add("In what city were you born ?");
		
		return questions;
		
	}
	
	
	public List<String> questionList1(String question){
		List<String> allQuestions1List = questionList();
		allQuestions1List.remove(question);
		return allQuestions1List;
	}
	
	public List<String> questionList2(String question){
		List<String> allQuestions2List = questionList();
		allQuestions2List.remove(question);
		return allQuestions2List;
	}
	
	
	public List<String> questionList3(String question){
		List<String> allQuestions3List = questionList();
		allQuestions3List.remove(question);
		return allQuestions3List;
	}
	
	
	public String popEditQuestions(List<String> questionLists) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<select id=\"questions\" name=\"question\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Your Saved Questions -</option>");
		
		for(String list:questionLists){
			sb.append("<option value=\"").append(list).append("\">").append(list).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
		
	}
	
	
	public UserLoginSecurity findByUserAndQuestionAndDeactive(User user,String question,Boolean deactive){
		return userLoginSecurityRepository.findByUserAndQuestionAndDeactive(user, question, deactive);
	}
	
	
	//---------on change---------//
	
	
	public String onChangeQuestionOne(String question) {
		
		StringBuilder sb = new StringBuilder();
		
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(questionList());
		
		String questArr[] = question.split(",");
		
		for(int i=0;i<questArr.length;i++){
			tempList.remove(questArr[i]);
		}
		
		sb.append("<select id=\"question1\" name=\"question\" class=\"form-control\"onChange=\"javascript:popQuestionTwo();popQuestionThree();\">");
		sb.append("<option value=\"\">- Select Question 1 -</option>");
		
		for(String list:tempList){
			sb.append("<option value=\"").append(list).append("\">").append(list).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
		
	}
	
	
	
	public String onChangeQuestionTwo(String question) {
		
		StringBuilder sb = new StringBuilder();
		
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(questionList());
		String questArr[] = question.split(",");
		
		for(int i=0;i<questArr.length;i++){
			tempList.remove(questArr[i]);
		}
		
		sb.append("<select id=\"question2\" name=\"question\" class=\"form-control\"onChange=\"javascript:popQuestionOne();popQuestionThree();\">");
		sb.append("<option value=\"\">- Select Question 2 -</option>");
		
		for(String list:tempList){
			sb.append("<option value=\"").append(list).append("\">").append(list).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
		
	}

	

	
	public String onChangeQuestionThree(String question) {
		
		StringBuilder sb = new StringBuilder();
		
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(questionList());
		String questArr[] = question.split(",");
		
		for(int i=0;i<questArr.length;i++){	
			tempList.remove(questArr[i]);
		}
		
		sb.append("<select id=\"question3\" name=\"question\" class=\"form-control\"onChange=\"javascript:popQuestionOne();popQuestionTwo();\">");
		sb.append("<option value=\"\">- Select Question 3 -</option>");
		
		for(String list:tempList){
			sb.append("<option value=\"").append(list).append("\">").append(list).append("</option>");
		}
		
		sb.append("</select>");
		return sb.toString();
		
	}



 }
