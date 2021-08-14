<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div align="center">
	<div class="panel panel-warning" style="width: 50%">
	
		<div class="panel panel-body">
		
			<div id="firstValidationId" style="display: none">
	
				<span id="robotId" style="color: blue">Captcha letters are case sensitive</span>
			
				<div class="row">
		          <div class="col-xs-12">&nbsp;</div>
				</div>
				
				<div class="row">
		          <div class="col-xs-12">&nbsp;</div>
				</div>
					
					<div class="form-group">
						<div class="col-xs-12">
							<form id="secureValidId" action="/jewels/secure/robatValidation.html">
													
								<div class="col-sm-12">
									<div class="col-sm-4">
										<input type="text" style="font-family: serif;" id="firstVal" name="firstVal" disabled="true" class="form-control" />
									</div>
									<div class="col-sm-2"><span class="glyphicon glyphicon-circle-arrow-right"></span></div>
									<div class="col-sm-4" >
										<input type="text" id="answerVal" name="answerVal" class="form-control" required  placeholder="captcha" />
									</div>
									<div class="col-sm-2" >
										<input type="submit" id="btnVal" name="btnVal" class="btn btn-primary" value="Validate" />
										<input type="hidden" id="pFirstVal" name="pFirstVal" />
										<input type="hidden" id="pAnswerVal" name="pAnswerVal" />
									</div>
								</div>
								
							</form>
						</div>
					</div>
					
					
					<div class="row">
		          		<div class="col-xs-12">&nbsp;</div>
					</div>
					
					<div id="answerId" style="display: none">
						<span  style="color: red">Incorrect Captcha</span>
					</div>
					
					
			</div>
			

			<div id="secondValidationId" style="display: none">
			
				<form id="secureQuestionId" action="/jewels/secure/questionValidation.html">
			
					<div class="row">
		          		<div class="col-sm-12">
							<span id="questId" style="color: purple;"></span>
						</div>
					</div>
					
					<div class="row">&nbsp;</div>
					
					<div class="row">
						<div class="col-sm-3">&nbsp;</div>
		          		<div class="col-sm-6">
							<input type="password" id="answer" name="answer" class="form-control" required style="background-color: #FFFF99"/>
						</div>
					</div>
					
					<div class="row">
	          			<div class="col-xs-12">&nbsp;</div>
					</div>
					
					<div>
						<input type="submit" id="btnSub" name="btnSub" value="Verify" class="btn btn-success" />
						<input type="hidden" id="pQuest" name="pQuest" />
						<input type="hidden" id="pAnswer" name="pAnswer" />
					</div>
				
				</form>
			
			</div>
			
			
			
		</div>

	</div>
</div>


<script type="text/javascript">


	$(document).ready(function(e){
		makeCaptach();
		$('#firstValidationId').css('display','block');
	})


	
	function makeCaptach(){
	    var text = "";
	    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    for( var i=0; i < 6; i++ )
	        text += possible.charAt(Math.floor(Math.random() * possible.length));

	    $('#firstVal').val(text);
	}

	
	$(document)
		.on(
			'submit',
			'form#secureValidId',
			function(e) {
				
				$('#pFirstVal').val($('#firstVal').val());
				$('#pAnswerVal').val($('#answerVal').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
	
					$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,
							success : function(data, textStatus, jqXHR) {
								
								//alert(data);
								
								if(data.indexOf("$") != -1){
									
									var temp = data.split("$");
									
									$('#questId').text(temp[1]);
									
									$('#answerId').css('display','none');
									$('#firstValidationId').css('display','none');
									$('#secondValidationId').css('display','block');
								}else{
									
									if(data === '-1'){
										makeCaptach();
										$('#answerVal').val('');
										$('#answerId').css('display','block');
									}else{
										
										displayMsg(this, null, 'Not Authorised To Access The Web');
									}
								}
								
								
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
	
							}
	
						});
					
				e.preventDefault();
				
			});
	
	
	
	
	//----------answer validation---//
	
	$(document)
		.on(
			'submit',
			'form#secureQuestionId',
			function(e) {
				
				//alert($('#questId').text());
				
				$('#pQuest').val($('#questId').text());
				$('#pAnswer').val($('#answer').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
	
					$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,
							success : function(data, textStatus, jqXHR) {
								
								if(data === '-1'){
									
								}else{
									window.location.href = data
								}
								
								
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
	
							}
	
						});
					
				e.preventDefault();
				
			});
	
	
	
	
	
	
	
	
	
	



</script>
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/common/design.js' />"></script>
<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />


