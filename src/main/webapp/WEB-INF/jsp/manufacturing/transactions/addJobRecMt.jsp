<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalJobIssPickup.jsp"%>


<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>


<style>
.flex, .flex > div[class*='col-'] {  
    display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    flex:1 0 auto;
}
</style>

<div class="panel panel-primary">
		 <div class="panel-heading" style="text-align: center;"> 
	 
	 <div>
		<label class="col-lg-2 col-sm-2 text-left">
			<span style="font-size: 18px;">Receive Challan</span>
		</label>
		<div class="text-right">
			
			<!-- <input type="button" value="Preview" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-success" id="previewBtnId" onClick="javascript:previewReport();"/> -->


			 <div class="btn-group">
				
			  <button type="button" class="btn btn-xs btn-success" style="font-size: 14px width: 3cm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   Excel
				  </button>
				  
				   <div class="dropdown-menu">
  			    
				    <a class="dropdown-item" id="jobissExcelPreviewBtn2"  onClick="javascript:deliveryChallanReport(1);">Delivery challan</a>
				    <div class="dropdown-divider"></div>
				    
				    <a  class="dropdown-item" href="#" id="jobissExcelPreviewBtn1"  onClick="javascript:previewReport(1);">Delivery challan(Detail)</a>
				    <div class="dropdown-divider"></div>
				    
				  </div>
				  
				</div> 
				
				
				<div class="btn-group">
				
			  <button type="button" class="btn btn-xs btn-success" style="font-size: 14px width: 3cm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   Preview
				  </button>
				  
				   <div class="dropdown-menu">
  
    				 <a id="previewBtnId2" class="dropdown-item"  onClick="javascript:deliveryChallanReport(2);">Delivery challan</a>
				    <div class="dropdown-divider"></div>
				    
				    <a  class="dropdown-item" href="#" id="previewBtnId"  onClick="javascript:previewReport(2);">Delivery challan(Detail)</a>
				    <div class="dropdown-divider"></div>
				    
				   
				  </div>
				  
				</div>

		
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/transactions/jobRecMt.html">Listing</a>
	
				<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-warning" id="jobRecMtSubMitBtn" onClick="javascript:jobRecSave();"/>
				 
			</div>
		
		
		
	 </div>
	 	
	</div> 

  <div class="panel-body">
	
	 <c:if test="${success eq true}">
		<div class="col-xs-12">
			<div class="alert alert-success">Receive Challan ${action} successfully!</div>
		</div>
	</c:if> 
	
	
		<div  class="form-group">
			<form:form commandName="jobRecMt" id="jobRecMtDivId"
				action="/jewels/manufacturing/transactions/jobRecMt/add.html"
				cssClass="form-horizontal jobRecMtForm">
				
				<div class="col-sm-12">
				<div class="col-sm-10">
				
				<div style="font-size: smaller;font-weight: bold;" class="row">
					<div class="col-xs-12">
					
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Voucher Type :</label>
						</div>
					
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Voucher
								No :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Voucher
								Date :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Party
								:</label>
						</div>
						
					
						
					
						
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
					
								<div class="col-lg-3 col-sm-3">
							<form:select path="department.id" class="form-control" onchange="javascript:toggleLabBtn()">
								<form:option value="" label=" Select Vou Type " />
								<form:options items="${deptMap}" />
							</form:select>
						</div>
					
						<div class="col-lg-3 col-sm-3">
							<form:input path="invNo" cssClass="form-control" disabled="true" />
							<form:errors path="invNo" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="invDate" cssClass="form-control" disabled="true" />
							<form:errors path="invDate" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:select path="party.id" class="form-control">
								<form:option value="" label=" Select Party " />
								<form:options items="${allPartyMap}" />
							</form:select>
						</div>
						
			
											
						
					</div>
				</div>



				
				<div class="row">
				<div class="col-xs-12">			
				</div>
				
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
					
					
					<div class="col-lg-6 col-sm-6">
							<label for="inputLabel3" class=".col-lg-3 text-right">Description 
								:</label>
						</div>
						
						
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Pointer Rate 
								:</label>
						</div>
					
					</div>
				</div>	
				
				<div class="row">
					<div class="col-xs-12">
					
					
						<div class="col-lg-6 col-sm-6">
							<form:select path="hsnMast.id" class="form-control" onchange="javascript:hideDtDiv();">
								<form:option value="" label=" Select Description " />
								<form:options items="${hsnMap}" />
							</form:select>
						</div>
						
						
					<div class="col-lg-3 col-sm-3">
							<form:input path="pointerRate" cssClass="form-control"  />
							<form:errors path="pointerRate" />
						</div>
					</div>
				</div>
				
				
				<div class="row">
				<div class="col-xs-12">			
				</div>
				
				</div>
				
				
				</div>
				
				
				<div id="odImgDivId" class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
						<div class="panel panel-primary" style=" height:125px">
							<div class="panel-body">
								<div style="width: 150px; height: 50px" id="tempImgDivId">
									
								</div>
							</div>
						</div>
				</div>
				
				</div>
				

				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-5">
						<form:input type="hidden" path="id" />
						<form:input type="hidden" path="uniqueId" />
						<input type="hidden" id=jobRecMtId name=jobRecMtId />
						<input type="hidden" id="jobRecStnDtPk" name="jobRecStnDtPk" /> 
						<input type="hidden" id="vDeptId" name="vDeptId" /> 
						<input type="hidden" name="xml" id="xml" value="${xml}"  />
						<input type="hidden" id="pLocationIds" name="pLocationIds" />
						<input type="hidden" id="pPartyIds" name="pPartyIds" />
						<input type="hidden" id="vTranDate" name="vTranDate" />
						<input type="hidden" id="vHsnId" name="vHsnId" />
						<input type="hidden" name="xml1" id="xml1" value="${xml1}"  />
						
					</div>
				</div>

			</form:form>
			
			
				<div style="display: none">		
				<form:form target="_blank"  id="jobRecMtRptForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="genJobRecMtRptBtn"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
			
			
			 <!-- Download Common Excel Report -->
			 
			 <div style="display: none">
				<form:form 	action="/jewels/manufacturing/masters/reports/download/Common/excelReport.html"
						cssClass="form-horizontal">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Excel Report" class="btn btn-primary" id="generateExcelReportss"/>
							</div>
						</div>
						
					<input type='hidden' name='pFileName' id='pFileName'/>
						
				</form:form>
			 </div>
		
			
		</div>

		
	<div role="tabpanel" id="tabDivId">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="nav-item active">
					<a href="#mainScreen"
						aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
					
					
					<li role="presentation" class="nav-item">
					<a href="#jobMtlRecDtt" id="jobMtlRecDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Metal Receive</a></li>
						
					<li role="presentation" class="nav-item">
					<a href="#jobStnRecDtt" id="jobStnRecDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Stone Receive</a></li>
		
					<li role="presentation" class="nav-item">
					<a href="#jobCompRecDtt" id="jobCompRecDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Component Receive</a></li>
		
	
				</ul>
		
				<!-- Tab panes -->
				<div class="tab-content">
		 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
						<%@include file="addJobRecDt.jsp"%>
					</div>
				 
					<div role="tabpanel" class="tab-pane" id="jobMtlRecDtt">
						<%@include file="addJobMtlRecDt.jsp"%>
					</div>
					
						<div role="tabpanel" class="tab-pane" id="jobStnRecDtt">
						<%@include file="addJobStnRecDt.jsp"%>
					</div>
					
					<div role="tabpanel" class="tab-pane" id="jobCompRecDtt">
						<%@include file="addJobCompRecDt.jsp"%>
					</div>	
					
				</div>
			
					
	</div>
		
	
	

	</div>  <!-- ending the panel body -->
	</div>


<script type="text/javascript">

var disableFlg = false;
	$(document)
			.ready(
					function(e) {
						
						toggleLabBtn();
						
						$(".jobRecMtForm")
								.validate(
										{

											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/jobRecMt/invoiceNoAvailable.html' />",
														type : "get",
														data : {

															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},
												invDate : {
													required : true,
												},
												'party.id' : {
													required : true,
												},
												
												'department.id' :{
													required : true,
												},

												'hsnMast.id' :{
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
												invNo : {
													remote : "Invoice No already exists"
												}
											}

										});
					

						$("#invDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});


						if (window.location.href.indexOf('edit') != -1) {
							$("#vTranDate").val($("#invDate").val());
							var abcx = window.location.href;
							var jobRec = abcx.substring(window.location.href.indexOf('edit') + 5);
							var tempCost = jobRec.split(".");
							$('#jobRecMtId').val(tempCost[0]);

							$('#jobRecMtDivId #party\\.id').attr("disabled","disabled").trigger('updated');
							$('#jobRecMtDivId #department\\.id').attr("disabled","disabled").trigger('updated');
							$('#jobRecMtDivId #hsnMast\\.id').attr("disabled","disabled").trigger('updated');
							
						//	popJobRecDetails();
							
							$('#openOnEdit').css('display','block');
							$('#entryCostDtItemId').css('display','block');
							
							$('#vDeptId').val('${deptMap}');

							if(process('${currentDate}') > process($('#invDate').val())){

								disableFlg = true;
								popJobRecDetails();
								
								$('#jobRecMtSubMitBtn').hide();
								$('#jobMtlRecDivId 	#jobMtlRecBtnId').hide();
								$('#jobStnRecDivId 	#jobStnRecBtnId').hide();
								$('#jobCompRecDivId #jobCompRecBtnId').hide();
								$('#addJobRecStnBtnId').hide();
								$('#addJobRecCompBtnId').hide();
								$('#addJobRecLabBtnId').hide();
								
								$('#bagPickupDivId').css('display','none');
							}else{
								disableFlg = false;
								popJobRecDetails();
								}
							
						}else{
							$("#vTranDate").val('${currentDate}');	
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}
						
						
						
						 $(function() {
							    $('#jobRecMetalTableId').bootstrapTable()
							  });
				

	});


	function process(date){
		   var parts = date.split("/");
		   return new Date(parts[2], parts[1] - 1, parts[0]);
		}
	
	
	function jobRecSave(){
		$('#pPartyIds').val($('#party\\.id').val());
		$('#pLocationIds').val($('#department\\.id').val());
		$('#vHsnId').val($('#hsnMast\\.id').val());
		
		$("form#jobRecMtDivId").submit();
	}

	function popJobRecDetails() {

		$("#jobRecDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/jobRecDt/listing.html?pInvNo="
									+ $('#invNo').val()+"&disableFlg="+disableFlg
						});
	

	}
	

			
	function goToPickup() {
		
		$('#jobRechiddenMtId').val($('#jobRecMtId').val());
		$('#myJobIssModal').modal('show');
		getJobIssPickUplist();
	}

	function getJobIssPickUplist(){
		
		$("#jobIssMtPickupTblId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/jobIssMt/pickupListing.html?partyId="
							+ $('#party\\.id').val()+"&deptId="+$('#jobRecMtDivId #department\\.id').val()
				});
	}
	
	
	function previewReport(val){
		
		$
		.ajax({
			url : "/jewels/manufacturing/transactions/jobIssMt/jobWorkReceiveReport.html?mtid="+$('#jobRecMtId').val()+ "&xml="+ $('#xml').val()+"&opt="+val,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
				
				if(val === 2){
					$('#timeValCommonPdf').val(data);
					$("#genJobRecMtRptBtn").trigger('click');
					
				}else{
					$('#pFileName').val(data);
					$("#generateExcelReportss").trigger('click');

					}
				
			}
		}); 
		
	}

	function deliveryChallanReport(val){
		
		$
		.ajax({
			url : "/jewels/manufacturing/transactions/jobIssMt/jobWorkReceiveReport.html?mtid="+$('#jobRecMtId').val()+"&xml="+ $('#xml1').val()+"&opt="+val,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {

				if(val === 2){
					$('#timeValCommonPdf').val(data);
					$("#genJobRecMtRptBtn").trigger('click');
					
				}else{
					$('#pFileName').val(data);
					$("#generateExcelReportss").trigger('click');

					}
				
			}
		}); 
		
	}

	 function hideDtDiv(){
			$('#tabDivId').css('display','none');
		}
				
	 
	 
	 function toggleLabBtn() {
		 
		if( $("#department\\.id :selected").text()==='Job Work'){
			
			$('#hallmarkDivId').css('display','none');
			$('#lazerDivId').css('display','none');
			$('#gradingDivId').css('display','none');
			$('#engravingDivId').css('display','none');
			$('#labDivId').css('display','block');
			
			
			
		}else{
			$('#hallmarkDivId').css('display','block');
			$('#lazerDivId').css('display','block');
			$('#gradingDivId').css('display','block');
			$('#engravingDivId').css('display','block');
			$('#labDivId').css('display','none');
		}
		
	}
	 
				
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

<script src="/jewels/js/common/design.js"></script>



