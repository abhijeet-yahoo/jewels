<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<style type="text/css">

.text,.form-group{
text-align: left;
padding-left: 60px;
}

</style>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">	Email Config</span>
	</div>
	
		<c:if test="${param.success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Record Saved successfully!</div>
		</c:if>

	<form:form commandName="emailConfig" id="emailConfigSubBtnId" 
		action="/jewels/admin/emailConfig.html"
		cssClass="form-horizontal emailConfigForm">

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 text">Name :</label>
			<div class="col-sm-4">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 text">Email Id :</label>
			<div class="col-sm-4">
				<form:input type="email" path="fromEmailId" cssClass="form-control" />
				<form:errors path="fromEmailId" />
			</div>
		</div>
		
		
		<div class="form-group">
			<label for="password" class="col-sm-2 text">Password
				:</label>
			<div class="col-sm-4">
				<form:password path="password" cssClass="form-control" />
				<form:errors path="password" />
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 text">Re-enter
				Password :</label>
			<div class="col-sm-4">
				<input type="password" name="re_password" id="re_password"
					class="form-control" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 text">Subject :</label>
			<div class="col-sm-4">
				<form:input path="subject" cssClass="form-control" />
				<form:errors path="subject" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 text">Message :</label>
			<div class="col-sm-4">
				<form:input type="text" path="message" cssClass="form-control" />
				<form:errors path="message" />
			</div>
		</div>
		
		
		
		<div class="form-group">
			<label for="name" class="col-sm-2 text">To Email Id :</label>
			<div class="col-sm-4">
				<form:input type="email" path="toEmailId" cssClass="form-control" />
				<form:errors path="toEmailId" />
			</div>
			<a href="#" id="btId" class="glyphicon glyphicon-plus-sign" style="text-decoration: none;"></a>
		</div>
		
	   
	   <div id="tempEmailDivId">
	   </div>
	   

		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> 
					<form:input type="hidden" path="id" />
					<input type="hidden" id="eMailIds" name="eMailIds" />
				</div>
				<input type="hidden" id="toEmailDb" name="toEmailDb" value="${toEmialValFromDb}" />
			</div>
		
	</form:form>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						
						$(".emailConfigForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													minlength : 3,
													remote : {
														url : "<spring:url value='/admin/emailConfigAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},
												password : {
													required : true,
													minlength : 5
												},
												re_password : {
													required : true,
													minlength : 5,
													equalTo : "#password"
												},
												fromEmailId : {
													required : true,
												},
												
												toEmailId : {
													required : true,
												},
												
											},
											highlight : function(element) {
												$(element).closest(
														'.form-group')
														.removeClass(
																'has-success')
														.addClass('has-error');
											},
											unhighlight : function(element) {
												$(element)
														.closest('.form-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											},
											messages : {
												name : {
													remote : "Name already exists"
												}
											}
										});

						$("input:text:visible:first").focus();
						
						
						
						//edit mode code
						
						
						popEditMode();
						
						
				
					});
 	
		//global variables
		var count = 0;
		var tempId = "";
 	    var tempTextFields = "";
		
		$("#btId").click(function () {
 	       count = Number(count)+1;
 	       tempId = "toEmail"+count;
 	       
 	       if(tempTextFields === ""){
 	    	   tempTextFields = "mainDivId"+count;
 	       }else{
 	    	  tempTextFields = tempTextFields+","+"mainDivId"+count;
 	       }
 	       
 	       var mainId = "mainDivId"+count;
 	       var cBtnId = "cancelBtnId"+count;
 	       
 	       var tempDiv = '<div id='+mainId+'><div class="form-group"><label for="name" class="col-sm-2 text">To Email Id '+count+' :</label><div class="col-sm-4"><input type="text" id='+tempId+' name='+tempId+' class="form-control" onblur="validateEmail(this.value,this.id);" /></div><a href="javascript:void(0)" id='+cBtnId+' onclick="javascript:popRemoveDiv(this.id)" class="glyphicon glyphicon-remove" style="text-decoration: none;" ></a></div></div>';
 	       $('#tempEmailDivId').append(tempDiv);
 	    });
 	    
 	    
 	    function popRemoveDiv(val){
 	    	var tempLast = val.slice(-1);
 	    	var tempDiv = "mainDivId"+tempLast;
 	    	$('#'+tempDiv).remove();
 	    	count = Number(count)+1;
 	    	
 	    	var textFieldVal = "";
 	    	var tempDivText = tempTextFields.split(",");
 	    	for(var i=0;i<tempDivText.length;i++){
 	    		
 	    		if(tempDiv !== tempDivText[i]+""){
 	    			if(textFieldVal === ""){
 	    				textFieldVal = 	tempDivText[i]+"";
 	    			}else{
 	    				textFieldVal = textFieldVal+","+tempDivText[i]+"";
 	    			}
 	    		}
 	    		
 	    		
 	    	}
 	    	
 	    	tempTextFields = "";
 	    	tempTextFields = textFieldVal;
 	    	
 	    }
 	    
 	    
 	    function popEditMode(){
 	    	if(!!$('#toEmailDb').val()){
 	    		var tempToEmailDb = $('#toEmailDb').val().split(",");
 	    		for(var i=0;i< tempToEmailDb.length;i++){
 	    			
 	    			if(i === 0){
 	    				$('#toEmailId').val('');
 	    				$('#toEmailId').val(tempToEmailDb[0]);
 	    				continue;
 	    			}
 	    			
	   			   count = Number(count)+1;
	   	 	       tempId = "toEmail"+count;
	   	 	       
		   	 	   if(tempTextFields === ""){
		 	    	   tempTextFields = "mainDivId"+count;
		 	       }else{
		 	    	  tempTextFields = tempTextFields+","+"mainDivId"+count;
		 	       }
		 	       
		 	       var mainId = "mainDivId"+count;
		 	       var cBtnId = "cancelBtnId"+count;
	   	 	       
	   	 	      var tempDiv = '<div id='+mainId+'><div class="form-group"><label for="name" class="col-sm-2 text">To Email Id '+count+' :</label><div class="col-sm-4"><input type="text" id='+tempId+' name='+tempId+' value='+tempToEmailDb[i]+' class="form-control" onblur="validateEmail(this.value,this.id);" /></div><a href="javascript:void(0)" id='+cBtnId+' onclick="javascript:popRemoveDiv(this.id)" class="glyphicon glyphicon-remove" style="text-decoration: none;" ></a></div></div>'; 
	   	 	      $('#tempEmailDivId').append(tempDiv);
 	    			
 	    		}
 	    	}
 	    }
 	
 	    
 	    function validateEmail(sEmail,tempId) {
 	    	  var reEmail = /^(?:[\w\!\#\$\%\&\'\*\+\-\/\=\?\^\`\{\|\}\~]+\.)*[\w\!\#\$\%\&\'\*\+\-\/\=\?\^\`\{\|\}\~]+@(?:(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9\-](?!\.)){0,61}[a-zA-Z0-9]?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9\-](?!$)){0,61}[a-zA-Z0-9]?)|(?:\[(?:(?:[01]?\d{1,2}|2[0-4]\d|25[0-5])\.){3}(?:[01]?\d{1,2}|2[0-4]\d|25[0-5])\]))$/;

 	    	  if(!sEmail.match(reEmail)) {
 	    	    displayMsg(this,null,tempId+" Invalid email address")
 	    	    return false;
 	    	  }

 	    	  return true;

 	    	}
 	
 	    
 	    
 	    //---save--code--//
 	    
 	    $(document).
			on('submit',
				'form#emailConfigSubBtnId',
				function(e){
				 
				 var tempEmailsIds = "";
				 if(!!tempTextFields){
					 var tempTxtDiv =  tempTextFields.split(",");
					 for(var i=0;i<tempTxtDiv.length;i++){
						var tempLastNo = tempTxtDiv[i].slice(-1);
						var tempFieldsId = "toEmail"+tempLastNo;
						 if(tempEmailsIds === ""){
							 tempEmailsIds = $('#'+tempFieldsId).val();
						 }else{
							 tempEmailsIds = tempEmailsIds+","+$('#'+tempFieldsId).val();
						 }
						 
					 }
				 }
				 
			
				$('#eMailIds').val(tempEmailsIds); 
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
			
				 $.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR){
						if(data.indexOf("$") != -1){
							var tempData = data.split("$");
							 displayMsg(this,null,"To Email Id"+(Number(tempData[1])+1)+" Cannot Be Blank");
						}else if(data.indexOf("#") != -1){
							var tempData = data.split("#");
							displayMsg(this,null,"To Email Id"+(Number(tempData[1])+1)+" Is Incorrect");
						}else{
							window.location.href = data;	
						}
						
					},
					error : function(data, textStatus, jqXHR){
						alert(error);
					}
					
				})
				
				e.preventDefault();
				
			});
 	    
 	    
 	    
 	    
 	    
 	    
 	    
 	    
 	    
 	    
 	    
 	

</script>