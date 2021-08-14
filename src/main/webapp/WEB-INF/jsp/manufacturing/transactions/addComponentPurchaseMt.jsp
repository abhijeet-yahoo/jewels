<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@include file="/WEB-INF/jsp/common/modalComponentPurchaseDt.jsp"%>

<div id="componentPurchaseMtDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Component Purchase</span>
	</div>
	<div class="form-group">
		<form:form commandName="componentPurchaseMt" id="componentPurchaseMtSub"
			action="/jewels/manufacturing/transactions/componentPurchaseMt/add.html"
			cssClass="form-horizontal componentPurchaseMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">Component ${action}
							successfully!</div>
					</div>
				</div>
			</c:if>

			<!-- Column Sizing -->
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							No:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							Date:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Be
							No:</label>
					</div>
					
						<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">BeDate:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<form:input path="invNo" cssClass="form-control" />
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="invDate" cssClass="form-control" />
						<form:errors path="invDate" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="beNo" cssClass="form-control" />
						<form:errors path="beNo" />
					</div>
					
						<div class="col-lg-3 col-sm-3">
						<form:input path="beDate" cssClass="form-control" />
						<form:errors path="beDate" />
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">
				
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Party:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Type:</label>
					</div>
					
					
					<div class="col-lg-6 col-sm-6">
						<label for="inputLabel3" class=".col-lg-2 text-right">Remark:</label>
					</div>
				
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
				
					<div class="col-lg-3 col-sm-3">
						<form:select path="party.id" class="form-control">
							<form:option value="" label=" Select Party " />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:select path="inwardType.id" class="form-control">
							<form:option value="" label=" Select Type " />
							<form:options items="${inwardTypeMap}" />
						</form:select>

					</div>
					
					<div class="col-lg-6 col-sm-6">
						<form:textarea path="remark" cssClass="form-control" />
						<form:errors path="remark" />
					</div>
					
				</div>
			</div>
			
					
			<div class="form-group">
				<div class="col-xs-12">
				<div class="col-sm-4">
				
					<input type="submit" value="Save" class="btn btn-primary" id="compMtBtnSubmit"  /> 
					<a id="compMtListngBtnId" class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/componentPurchaseMt.html">Listing</a>
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="uniqueId" />
			</div>		
			</div>
			</div>
		</form:form>
	</div>
</div>
</div>




<script type="text/javascript">


var canViewFlag = false;
	$(document)
			.ready(
					function(e) {
						$(".componentPurchaseMtForm")
								.validate(
										{
											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/componentPurchaseMt/invoiceNoAvailable.html' />",
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
												'inwardType.id' : {
													required : true,
												},

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
						
						
						if('${canEditTranddate}' === "false"){
							$("#invDate").attr("disabled","disabled");
						}

						$("#beDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						if (window.location.href.indexOf('edit') != -1) {
							
							$('#componentPurchaseDtDiv').css('display', 'block');
							
						}
						else{
							
							$("#invDate").val('${currentDate}');
							
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

						popComponentPurchaseDt();

					});
	
	
	
    
    
    $(document)
	.on(
		'submit',
		'form#componentPurchaseMtSub',
		 function(e){
			 $("#compMtBtnSubmit").prop("disabled", true).addClass("disabled");
			
		});
	
	

	function popComponentPurchaseDt() {
		$("#componentPurchaseDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/componentPurchaseDt/listing.html?mtId="+$('#componentPurchaseMtSub #id').val(),
						});
	}
	
function goToComponentPurchaseDtEntry(){
		
		$('#myComponentPurchaseDtModal').modal('show');
		$('#vCompPurcMtId').val($('#componentPurchaseMtSub #id').val());
	}
	

	function editComponentPurchaseDt(dtId) {
		
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/componentPurchaseDt/edit/"+ dtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
							
							if(Number(data) === -2){
								displayMsg(this,null,'Balance Adjusted, Can Not Edit');
							}else{
						
							var validator = $( ".componentPurchaseDtForm" ).validate();
							validator.resetForm();
							
							$('#myComponentPurchaseDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#componentPurchaseDtModalDivId #'+k).val(v);
								
							});
						}
							}
					});	
	
	}
	
	
	
	
	function deleteComponentPurchaseDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteComponentPurchaseDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}
	
	function deleteComponentPurchaseDtData(dtId){
		
	$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/componentPurchaseDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
			
				if(Number(data) === -2){
					displayMsg(this,null,'Balance Adjusted, Can Not Delete');
				}else{
				popComponentPurchaseDt();
					
				}
				
			}
			
		})
		
	}
	
	
	
</script>

<div id="componentPurchaseDtDiv" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading">
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:goToComponentPurchaseDtEntry();"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
					</div>
					<div>
				
						<table id="componentPurchaseDtId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
							data-striped="true">
							
							<thead>
								<tr class="btn-primary">
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="component" data-align="left">Component</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
									<th data-field="qty" data-sortable="true">Qty</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

					<div>
				
				<span style="display:inline-block; width: 15cm;"></span>
					Total Invoice Wt : <input type="text" id="vTotalInvWt" name="vTotalInvWt" value="${totalInvWt}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;								
					Total Qty : <input type="text" id="vTotalQty" name="vTotalQty" value="${totalQty}"  
									maxlength="7" size="7" disabled="disabled" />
					
						&nbsp;&nbsp;
					Total Metal Wt : <input type="text" id="vTotalMetalWt" name="vTotalMetalWt" value="${totalMetalWt}"  
									maxlength="7" size="7" disabled="disabled" />			
					
					</div>

			<c:set var="option" value="User" />

			
		</div>

	</div>
</div>




<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

