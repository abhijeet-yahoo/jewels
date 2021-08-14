<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script> 


<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalCostSummary.jsp"%> 

<%@ include file="/WEB-INF/jsp/common/modalCostDtRates.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalCostLockInvoice.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalCostingOrdPcs.jsp"%>



<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Costing</span>
			</label>
			<div class="text-right">
			
						
			<div class="btn-group">
  <button type="button" class="btn btn-xs btn-info" style="font-size: 14px" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Export Excel
  </button>
  <div class="dropdown-menu">
  
    
    <a id="costingExcelId" class="dropdown-item" href="#">Excel </a>
    <div class="dropdown-divider"></div>
    
    <a id="costingPOExcelId" class="dropdown-item" href="#">PO Wise Excel</a>
    <div class="dropdown-divider"></div>
    
    <a id="costingPerPcExcelId" class="dropdown-item" href="#">Per Pc Excel</a>
    <div class="dropdown-divider"></div>
    
    <a id="costingPerPcPoExcelId" class="dropdown-item" href="#">Po Wise Per Pc Excel</a>
    <div class="dropdown-divider"></div>
    
    <a id="costingExcelMergeId" class="dropdown-item" href="#">Excel Merge</a>
    
  </div>
</div>

			<input type="button" value="Ord Pcs" style="font-size: 14px; width: 2cm"  class="btn btn-xs btn-info"  onclick="javascript:popOrdPcsBtn()"/>
			
			<input type="button" value="Style Not Match" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info"  onclick="javascript:popStyleNotMatchBtn()"/>
			
			<input type="button" value="Lock Invoice" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info" id="lockInvoiceBtn" onClick="javascript:popLockInvoice();" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/transactions/costingMtNew.html">Costing	Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="costMtItemSubMitBtn" onClick="javascript:costingNewSave();" />
			

			
			
			
				<!-- <a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					id="costingExcelId" href="#"></a> -->
			</div>



		</div>

	</div>

	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>



	<c:if test="${success eq true}">
		<div class="col-xs-12">
			<div class="alert alert-success">Costing ${action}
				successfully!</div>
		</div>
	</c:if>
	
		<c:if test="${success eq false}">
		<div class="col-xs-12">
			<div class="alert alert-danger">${action}</div>
		</div>
	</c:if>

		<div class="col-xs-12">
		<div id="marqueeIdDisp" style="display: none">
			<marquee style="color: #00FF00; font-size: xx-large;">This
				Export Is Closed</marquee>
		</div>
	</div>




	<div class="form-group">
		<form:form commandName="costingMt" id="costingMtNewDivId"
			action="/jewels/manufacturing/transactions/costingMt/add.html?opt=1"
			cssClass="form-horizontal costingMtForm">

			<div class="col-sm-12">
				<div class="col-sm-10">


					<div style="font-size: smaller; font-weight: bold;" class="row">
						<div class="col-xs-12">
							<div class="col-lg-4 col-sm-4">
								<label for="inputLabel3" class=".col-lg-2 text-right">Inv
									No :</label>
							</div>
							<div class="col-lg-2 col-sm-2">
								<label for="inputLabel3" class=".col-lg-2 text-right">Inv
									Date :</label>
							</div>
							<div class="col-lg-2 col-sm-2">
								<label for="inputLabel3" class=".col-lg-2 text-right">Party
									:</label>
							</div>

							<div class="col-lg-2 col-sm-2">
								<label for="inputLabel3" class=".col-lg-2 text-right">Duty
									% :</label>
							</div>
							
							<div class="col-lg-2 col-sm-2">
								<label for="inputLabel3" class=".col-lg-2 text-right">Discount
									% :</label>
							</div>

						

						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-4 col-sm-4">
								<form:input path="invNo" cssClass="form-control" />
								<form:errors path="invNo" />
							</div>
							<div class="col-lg-2 col-sm-2">
								<form:input path="invDate" cssClass="form-control" />
								<form:errors path="invDate" />
							</div>
							<div class="col-lg-2 col-sm-2">
								<form:select path="party.id" class="form-control">
									<form:option value="" label=" Select Party " />
									<form:options items="${partyMap}" />
								</form:select>
							</div>
							
							
							<div class="col-lg-2 col-sm-2">
								<form:input path="dutyPerc" cssClass="form-control" />
								<form:errors path="dutyPerc" />
							</div>
							
							<div class="col-lg-2 col-sm-2">
								<form:input path="discountPerc" cssClass="form-control" />
								<form:errors path="discountPerc" />
							</div>
							

						</div>
					</div>




					<div class="row">
						<div class="col-xs-12"></div>

					</div>

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-2 col-sm-2">
								<label> <form:checkbox path="in999" onchange="javascript:update999()" /> <b>As Per
										999</b></label>
							</div>

							<div class="col-lg-2 col-sm-2"
								style="font-size: smaller; font-weight: bold;">
								<a onclick="javascript:popCostMetalRate()">Metal Rates</a>
							</div>

						</div>
					</div>


				</div>


				<div id="odImgDivId"
					class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
					<div class="panel panel-primary" style="height: 125px">
						<div class="panel-body">
							<div style="width: 150px; height: 50px" id="tempImgDivId">

							</div>
						</div>
					</div>
				</div>

			</div>


			
			
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="uniqueId" />
					<input type="hidden" id=costMtId name=costMtId /> 
					<input type="hidden" id="costStnDtPk" name="costStnDtPk" />
					
					<input type="hidden" name="timeValStyleNotMatchPdf" id="timeValStyleNotMatchPdf" />
					<input type="hidden" name="xml" id="xml" value="${xml}"  />
					
		</form:form>
		
		<div style="display: none">		
				<form:form target="_blank"  id="styleNotMatchForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="genStyleNotMatchRpt"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
		


			<input type="hidden" id="expCloseStatus" name="expCloseStatus"	value="${closeStatus}">
			
			
			
			</div>
		





		<div role="tabpanel">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#mainScreen"
						aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
					
					
					<li role="presentation"><a href="#mergeCosting" id="mergeCostId"
						aria-controls="profile" role="tab" data-toggle="tab">Merge Costing</a></li>
							
					
					<!-- <li role="presentation"><a href="#changeLabour" id="cLabour"
						aria-controls="profile" role="tab" data-toggle="tab">Change Handling</a></li> -->
						
					<li role="presentation"><a href="#costDtDisplaySummary" id="displaySummary"
						aria-controls="profile" role="tab" data-toggle="tab">Summary</a></li>	
						
				</ul>
		
				<!-- Tab panes -->
				<div class="tab-content">
		 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
						<%@include file="addCostingDtNew.jsp"%>
					</div>
					
					<div role="tabpanel" class="tab-pane" id="disableMergeCosting">
						<%@include file="addMergeCosting.jsp"%>
					</div>
					
		 		<%-- 	<div role="tabpanel" class="tab-pane" id="disableChangeLabour">
						<%@include file="changeLabour.jsp"%>
					</div> --%>
					
					<div role="tabpanel" class="tab-pane" id="disableDisplaySummary">
					<%@include file="costDtDisplaySummary.jsp"%>
					</div>	
					
				</div>
			
					
	</div>
	</div>
	
	
	
	
	
<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						
						$(".costingMtForm")
								.validate(
										{

											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/costMt/invoiceNoAvailable.html' />",
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
							var abcx = window.location.href;
							var cost = abcx.substring(window.location.href
									.indexOf('edit') + 5);
							var tempCost = cost.split(".");
							$('#costMtId').val(tempCost[0]);

							popCostingDetails();
							

						}
					});

	function costingNewSave() {
		
		if($(".costingMtForm").valid()){
			$("form#costingMtNewDivId").submit();
			
		}
		
		
		

	}
	
	
	function downloadexcel(){
		
		
		
	}
	

	
	
	 function update999(){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		 
		 var postData = $('#costingMtNewDivId').serializeArray();
		 
		 $.ajax({
				url : "/jewels/manufacturing/transactions/costMt/updatepuritycost.html?tempPartyId="+$('#party\\.id').val(),
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					
					if(data === '1'){
						popCostingDtItemDetails();
						
						$('#hideOnPageLoadItem').css('display','none');
						/* window.location.reload(true); */
					}else{
						displayMsg(this,null,"Error Can Not Update");	
					}
					
					
						
					$.unblockUI();

				},
				error : function(data, textStatus, jqXHR){
					$.unblockUI();

				}
				
			})
		 
		 
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

<script src="<spring:url value='/js/bootstrap/lodash.min.js' />"></script>

