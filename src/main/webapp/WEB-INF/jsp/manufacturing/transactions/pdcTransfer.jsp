<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

 <div class="panel panel-primary" style="width: 100%">
	
	<div class="panel-heading" >
		<span style="font-size: 18px;">PDC Process Entry</span>
	</div>

	<div class="panel-body">
		
             
     <div class="col-xs-12">
        
        <!-- starting of div size 9 -->   
        <div class="col-xs-9">
        
    		<div class="form-group">    
        
				<form:form commandName="pdcTranMt" id="transferDump"
					action="/jewels/manufacturing/transactions/pdcTransfer/add.html"
					cssClass="form-horizontal transferForm">
	
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					<div class="row">
						<div class="col-lg-8 col-sm-8">
							<label for="inputLabel3" class=".col-lg-2 text-right">Department :</label>
						</div>
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-left">Style No :</label>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-lg-8 col-sm-8">
							<form:select path="deptFrom.id" id="deptFroom" class="form-control" onChange="javascript:popDeptTo();">
								<form:option value="" label=" Select department " />
								<form:options items="${departmenttMap}" />
							</form:select>
						</div>
						<div class="col-lg-4 col-sm-4">
							<form:input path="design.styleNo" cssClass="form-control"  onblur="javascript:displayBreakUp();"  />
						    <form:errors path="design.styleNo" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					
					<div class="row">
						<div class="col-lg-8 col-sm-8">
							<label for="inputLabel3" class=".col-lg-2 text-right">Purity :</label>
						</div>
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-left">Qty :</label>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-lg-8 col-sm-8">
							<input type="text" id="purityNm" name="purityNm" class="form-control" disabled="true"/>
						</div>
						<div class="col-lg-4 col-sm-4">
							<form:input path="pcs" cssClass="form-control" disabled="true" />
							<form:errors path="pcs" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					<div class="row">
						<div class="col-lg-8 col-sm-8">
							<label for="inputLabel3" class=".col-lg-2 text-right">RecWt :</label>
						</div>
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-left">IssWt :</label>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-lg-8 col-sm-8">
							<form:input path="recWt" cssClass="form-control" disabled="true" />
				        	<form:errors path="recWt" />
						</div>
						<div class="col-lg-4 col-sm-4">
							<form:input path="issWt" cssClass="form-control" onChange="javascript:getLoss()" />
						    <form:errors path="issWt" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					<div class="row">
						<div class="col-lg-8 col-sm-8">
							<label for="inputLabel3" class=".col-lg-2 text-right">Loss :</label>
						</div>
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-left">To Department :</label>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-lg-8 col-sm-8">
							<form:input path="loss" cssClass="form-control" disabled="true" />
				        	<form:errors path="loss" />
						</div>
						<div class="col-lg-4 col-sm-4">
							<form:select path="deptTo.id" id="deptToo" class="form-control">
								<form:option value="" label=" Select department " />
								<form:options items="${departmentToMap}" />
							</form:select>
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					
					
					<div class="form-group">
		    			<div class="col-xs-12">&nbsp; </div>								 
						<div class="col-xs-12" align="center">
							<input type="submit" value="Transfer" class="btn btn-primary" id="tranSubmitBtn" /> 
							<input type="hidden" name="id">
							<input type="hidden" name="vDepartmentId" id="vDepartmentId" />
							<input type="hidden" name="vStyleNo" id="vStyleNo" /> 
							<input type="hidden" name="vDepartmentToId" id="vDepartmentToId" />
							<input type="hidden" name="vIssWt" id="vIssWt" /> 
							<input type="hidden" name="vLoss" id="vLoss" />
							<input type="hidden" name="vRecWt" id="vRecWt" />
						</div>
					</div>
					
					
		
				</form:form>
        	
        	</div>
        
        
        
        
        </div>
        <!-- ending of div size 9 -->
	
		
		
		<!-- starting of div size 3 -->
		<div class="col-xs-3">
		
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			<div class="row">
				&nbsp;&nbsp;&nbsp;&nbsp;<label for="image" class=".col-lg-5 text-right">Image :</label>
			</div>
		
			<div class="panel panel-primary"
				style="height: 170px">
				<div class="panel-body">
					<div style="width: 150px; height: 50px">
						<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png" data-lighter>
							 <img id="ordImgId" class="img-responsive" src="/jewels/uploads/manufacturing/blank.png" />
						</a>
					</div>
				</div>
			</div>
		
		
        
        </div>
         <!-- ending of div size 3 -->
        
        
        
        
     </div>
			
			


	</div> <!-- ending the panel body -->
	
</div> <!-- ending the panel -->

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						
						

						$(".transferForm")
								.validate(
										{
											rules : {
												'deptFrom.id' : {
													required : true,

												},
												
													'deptTo.id' : {
														required : true,

													},
												'design.styleNo' : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/pdctransfer/styleNoAvailable.html' />",
														type : "get",
														data : {

															'deptFrom.id' : function() {
																return $(
																		"#deptFroom")
																		.val();
															}

														}
													}

												},
												
												
												issWt : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/pdctransfer/issWtValidation.html' />",
														type : "get",
														data : {

															recWt : function() {
																return $("#recWt").val();
															}

														}
													}

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
												'design.styleNo' : {
													remote : "StyleNo not found on floor"
												},
												issWt : {
													remote : "RecWt is zero"
												}
								
											}

										});
						
						
						
						$("#design\\.styleNo").autocomplete({
							source : "/jewels/manufacturing/masters/styleNo/list.html",
							minLength : 2
						});

					});
	

	function displayBreakUp() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/pdcTransfer/display.html' />?styleNo='
							+ $('#design\\.styleNo').val()
							+ "&departmentId="
							+ $('#deptFroom').val(),
					type : 'GET',
					success : function(data) {
						
							if (Number(data) != -1 && Number(data) != -2) {

								var temp = data.split("#");
	
								$('#styleNoId').val(temp[0]);
								$('#pcs').val(temp[1]);
								$('#recWt').val(temp[2]);
								$('#ordImgId').attr('src', temp[3]);
								$('#oImgHRId').attr('href', temp[3]);
								$('#purityNm').val(temp[4]);
							
							}else{
								if (Number(data) == -1) {
								
									$('#styleNoId').val('');
									$('#purityNm').val('');
									$('#pcs').val('0.0');
									$('#recWt').val('0.0');
									$('#issWt').val('0.0');
									$('#loss').val('0.0');
									
								} else {
									alert("please select the department :-) ");
								}
								
							}

					}

				});

	}

	function getLoss() {

		var vRecWt = $('#recWt').val();
		var vIssWt = $('#issWt').val();
		var vLoss = vRecWt - vIssWt;
		
		$('#loss').val(vLoss.toFixed(3));

	}

	
	$(document).on('submit', 'form#transferDump', function(e) {


		$('#tranSubmitBtn').attr('disabled', 'disabled');
		
		
		var vDeptId = $('#deptFroom').val();
		$('#vDepartmentId').val(vDeptId);

		 var vvStyleNo = $('#design\\.styleNo').val();
		$('#vStyleNo').val(vvStyleNo); 

		var vDeptToId = $('#deptToo').val();
		$('#vDepartmentToId').val(vDeptToId);

		var vvIssWt = $('#issWt').val();
		$('#vIssWt').val(vvIssWt);

		var vvRecWt = $('#recWt').val();
		$('#vRecWt').val(vvRecWt);

		
		var vvLoss = $('#loss').val();
		$('#vLoss').val(vvLoss);

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,

			success : function(data, textStatus, jqXHR) {
				
				//alert(data);
				
				if (Number(data) == 1) {

					displayInfoMsg(event, this,'Record Transfered Sucessfully !');
					
					$('#styleNoId').val(''); 
					$('#pcs').val('0.0');
					$('#recWt').val('0.0');
					$('#issWt').val('0.0');
					$('#loss').val('0.0');
					$('#ordImgId').attr('src', '');
					$('#oImgHRId').attr('href', '');
					$('#deptToo').val('');
					$('#deptFroom').val('');
					$('#design\\.styleNo').val('');

				}else if(Number(data) == -3){
					displayMsg(event, this,'Could not transfer as Issue Weight is zero !');
				}else if(Number(data) == -4){
					displayMsg(event, this,'Could not transfer as Recieve Weight is not zero !');
				}
				 
				
				$('#tranSubmitBtn').removeAttr('disabled');

			},
			error : function(jqXHR, textStatus, errorThrown) {

			}

		});

		e.preventDefault(); //STOP default action
	});

	
	function popDeptTo() {
		
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/pdcTransfer/deptTo.html' />?departmentId='
							+ $('#deptFroom').val(),
					type : 'GET',
					success : function(data) {
						$("#deptToo").html(data);
						$('#design\\.styleNo').val('');
					}
				});
	}
	
	

	
	
</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

