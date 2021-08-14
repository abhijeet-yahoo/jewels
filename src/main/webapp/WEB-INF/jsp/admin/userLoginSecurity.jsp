<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div align="center">
<div class="panel panel-primary" style="width: 100%">
	
	<div class="panel-heading">
		<span style="font-size: 18px;">User Login Security</span>
	</div>
	
	
	<div class="panel-body">
	
	
		
			<div class="form-group">
				<div class="col-xs-12">
					<form:form commandName="userLoginSecurity" cssClass="form-horizontal securityForm">
					
					<div class="col-sm-12">
					
						<div class="form-group">
							<label for="name" class="col-sm-4 control-label">User Name  :</label>
							<div class="col-sm-4">
								<form:select path="user.id" class="form-control" onchange="javascript:displayDiv()">
										<form:option value="" label=" Select User-Name " />
										<form:options items="${userMap}" />
								</form:select>
							</div>
						</div>
						
						<div class="col-xs-12">
							<hr style="width: 100%; color: red; height: 2px; background-color: red;" />
						</div>
						
					</div>
						
					</form:form>
				</div>
			</div>
	
	


		<div id="addDivId" style="display: none">
			<form:form commandName="userLoginSecurity" id="userLoginId" action="/jewels/admin/add.html"
				cssClass="form-horizontal userLoginSecurityForm">
				
				<div class="col-sm-12">
						<div class="form-group">
							<label for="question1" class="col-sm-4 control-label">Question 1 :</label>
							<div class="col-sm-6">
								 <div id="quest1">
									<form:select path="question" id="question1" class="form-control" onchange="javascript:popQuestionTwo();popQuestionThree();">
										<form:option value="" label=" Select Question 1 " />
										<form:options items="${question1Map}" />
									</form:select>
								 </div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="answer1" class="col-sm-4 control-label">Answer :</label>
							<div class="col-sm-6">
								<form:input path="answer" id="answer1" cssClass="form-control" />
								<form:errors path="answer" />
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-12">&nbsp;</div>
						</div>
						
						
						
						<div class="form-group">
							<label for="name" class="col-sm-4 control-label">Question 2 :</label>
							<div class="col-sm-6">
								 <div id="quest2">
									<form:select path="question" id="question2" class="form-control" onchange="javascript:popQuestionOne();popQuestionThree();">
										<form:option value="" label=" Select Question 2 " />
										<form:options items="${question2Map}" />
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="answer2" class="col-sm-4 control-label">Answer :</label>
							<div class="col-sm-6">
								<form:input path="answer" id="answer2" cssClass="form-control" />
								<form:errors path="answer" />
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-12">&nbsp;</div>
						</div>
						
						<div class="form-group">
							<label for="question3" class="col-sm-4 control-label">Question 3 :</label>
							<div class="col-sm-6">
							 <div id="quest3">
								<form:select path="question" id="question3" class="form-control" onchange="javascript:popQuestionTwo();popQuestionOne();">
									<form:option value="" label=" Select Question 3 " />
									<form:options items="${question3Map}" />
								</form:select>
							 </div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="answer3" class="col-sm-4 control-label">Answer :</label>
							<div class="col-sm-6">
								<form:input path="answer" id="answer3" cssClass="form-control" />
								<form:errors path="answer" />
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-xs-12">&nbsp;</div>
						</div>
						
						<div class="row" id="msgId" style="display: none">
							<span style="color: red">All Fields are mandatory</span>
						</div>
						
						<div class="row">&nbsp;</div>
						
						<div>
							<input type="submit" id="saveBtn" name="saveBtn" value="Save" class="btn btn-primary" style="width: 2cm"/>
							<input type="hidden" id="id" name="id" />
							<input type="hidden" id="pQuestion1" name="pQuestion1" />
							<input type="hidden" id="pQuestion2" name="pQuestion2" />
							<input type="hidden" id="pQuestion3" name="pQuestion3" />
							<input type="hidden" id="pAnswer1" name="pAnswer1" />
							<input type="hidden" id="pAnswer2" name="pAnswer2" />
							<input type="hidden" id="pAnswer3" name="pAnswer3" />
							<input type="hidden" id="pUserId" name="pUserId" />
						</div>
					
						
						<div class="row">
							<div class="col-xs-12">&nbsp;</div>
						</div>
						
				
				</div>
			
			
			</form:form>
		</div>
		
		
		
		<div id="editDivId" style="display: none">
			
			<form:form commandName="userLoginSecurity"
				cssClass="form-horizontal userLoginSecurityEditForm">
				
				<div class="col-sm-12">
						
						<div class="form-group">
							<label for="questions" class="col-sm-4 control-label">Questions :</label>
							<div class="col-sm-6">
							 <div id="questId">
								<form:select path="question" id="questions" class="form-control">
									<form:option value="" label=" Select Your Saved Questions " />
									<form:options items="${questionsMap}" />
								</form:select>
							 </div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="answers" class="col-sm-4 control-label">Answer :</label>
							<div class="col-sm-6">
								<form:input path="answer" id="answers" cssClass="form-control" />
								<form:errors path="answer" />
							</div>
						</div>
						
						<div class="row">
							<div class="col-xs-12">&nbsp;</div>
						</div>
						
						<div class="row" id="msgEditId" style="display: none">
							<span style="color: red">All Fields are mandatory</span>
						</div>
						
						<div class="row">&nbsp;</div>
						
						<input type="button" id="updateBtn" name="updateBtn" value="Update" class="btn btn-warning" onclick="javascript:popUpdate()" />
						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" id="delete" name="delete" value="Delete All" class="btn btn-danger" onclick="javascript:popDelete()" />
							
						
						
				 </div>
				
				
			</form:form>
		
		
		</div>
		
		
	
	
	
		</div>
	
	
	
	
	</div>

</div>



<script type="text/javascript">

	 $(document).ready(function(e){
		 
		 
		 
		 
		 
		 
		 
	 });




 
 	//---------save-code-----//
 	
 	$(document).
		on('submit',
			'form#userLoginId',
			function(e){
			
			
		    $('#pQuestion1').val($('#question1').val());
			$('#pQuestion2').val($('#question2').val());
			$('#pQuestion3').val($('#question3').val());
			
			$('#pAnswer1').val($('#answer1').val());
			$('#pAnswer2').val($('#answer2').val());
			$('#pAnswer3').val($('#answer3').val());
			
			$('#pUserId').val($('#user\\.id').val()); 
			
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
						
						if(data === '-1'){
							displayInfoMsg(this, null, 'Record Addedd Successfully');
							$('#msgId').css('display','none');
							clearFields();
						}else if(data === '-2'){
							$('#msgId').css('display','block');
						}else if(data === '-4'){   
							displayMsg(this, null, 'All Answers cannot be same');
						}else{
							displayMsg(this, null, 'User is compulsary');
							$('#msgId').css('display','none');
						}
					
					
					
				},
				error : function(data, textStatus, jqXHR){
					
				}
				
			})
			
			e.preventDefault();
			
		});
 
 
 
 
 	
 		function clearFields(){
 			
 			$('#question1').val('');
 			$('#question2').val('');
 			$('#question3').val('');
 			$('#answer1').val('');
 			$('#answer2').val('');
 			$('#answer3').val('');
 			$('#user\\.id').val('');
 			
 		}
 	
 	
 		
 		//---call to view add or edit page ---- // ---- //
 		
 		
 		function displayDiv(){
 			
 			if(!!$('#user\\.id').val()){
 			
	 			$.ajax({
	 				url:"/jewels/admin/status.html?userId="+$('#user\\.id').val(),
	 				type:"GET",
	 				success:function(data){
	 					
	 					if(data === '-1'){
	 						$('#addDivId').css('display','block');
	 						$('#editDivId').css('display','none');
	 						$('#question1').val('');
	 			 			$('#question2').val('');
	 			 			$('#question3').val('');
	 			 			$('#answer1').val('');
	 			 			$('#answer2').val('');
	 			 			$('#answer3').val('');
	 					}else{
	 						
	 						$('#addDivId').css('display','none');
	 						$('#editDivId').css('display','block');
	 						
	 						$('#answers').val('');
	 						
	 						var temp = data.split('$');
	 						$('#questId').html(temp[1]);
	 						
	 						
	 					}
	 					
	 					
	 				} 
	 				
	 			});
	 			
 			}
 			
 		}
 
 		
 
 		
 		function popUpdate(){
 			
 			if(!!$('#user\\.id').val() && !!$('#questions').val() && !!$('#answers').val()){
 			
 				$('#msgEditId').css('display','none');
 				
	 			$.ajax({
	 				url:"/jewels/admin/update.html?userId="+$('#user\\.id').val()
	 						+"&question="+$('#questions').val()
	 						+"&answer="+$('#answers').val(),
	 				type:'GET',
	 				success: function(data){
	 					
	 					if(data === '-1'){
	 						displayInfoMsg(this, null, 'Record updated Successfully');
	 						$('#questions').val('');
	 						$('#answers').val('');
	 						$('#user\\.id').val('')
	 					}else{
	 						displayMsg(this, null, 'Problem occoured contact admin');
	 					}
	 					
	 				}
	 				
	 			});
	 			
 			
 			}else{
 				$('#msgEditId').css('display','block');
 			}
 			
 		}
 
 		
 		
 		
 		
 		
 		
 		//-----Questions level -- functions-------//
 		 
 	  	function popQuestionOne(){
 			
 	 		var tempLevOne = $('#question1').val();
 	 		var pQuestion = $('#question2').val()+","+$('#question3').val();
 	 		
 		 		$.ajax({
 					url : "/jewels/admin/questionOne/combo.html?question="+pQuestion,
 					type : "GET",
 					success: function(data){
 						$('#quest1').html(data);
 						$("#question1").val(tempLevOne);
 					}
 		 		});
 		 		
 	 	}
 		
 		
 		function popQuestionTwo(){
 			
 			var tempLevTwo = $('#question2').val();
 			var pQuestion = $('#question1').val()+","+$('#question3').val();
 			
 		 		$.ajax({
 					url : "/jewels/admin/questionTwo/combo.html?question="+pQuestion,
 					type : "GET",
 					success: function(data){
 						$('#quest2').html(data);
 						$('#question2').val(tempLevTwo);
 					}
 		 		});
 			
 	 	}
 		
 		
 		function popQuestionThree(){
 			
 			var tempLevThree = $('#question3').val();
 			var pQuestion = $('#question1').val()+","+$('#question2').val();
 	 		
 				$.ajax({
 					url : "/jewels/admin/questionThree/combo.html?question="+pQuestion,
 					type : "GET",
 					success: function(data){
 						$('#quest3').html(data);
 						$('#question3').val(tempLevThree);
 					}
 		 		});
 	 		
 	 	}
 		
 		
	
 		function popDelete(){
 			
 			$.ajax({
					url : "/jewels/admin/deleteAll.html?userId="+$('#user\\.id').val(),
					type : "GET",
					success: function(data){
						displayInfoMsg(this, null, 'Record Deleted Successfully');
						displayDiv();
					}
		 		});
 			
 		}
 
 
</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

