<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<!-- <a href="/jewels/manufacturing/masters/design/image/upload.html">Upload
	Image</a> -->
<div id="metalOutwardMtDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Metal Outward</span>
	</div>
	<div class="form-group">
		<form:form commandName="metalOutwardMt"
			action="/jewels/manufacturing/transactions/metalOutwardMt/add.html"
			cssClass="form-horizontal metalOutwardMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">Metal ${action} successfully!</div>
					</div>
				</div>
			</c:if>

			<!-- Column Sizing -->
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Invoice
							No:</label>
					</div>
					<div class="col-lg-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Invoice
							Date:</label>
					</div>
					<div class="col-lg-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Be
							No:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-4">
						<form:input path="invNo" cssClass="form-control" readonly="true" />
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-4">
						<form:input path="invDate" cssClass="form-control" />
						<form:errors path="invDate" />
					</div>
					<div class="col-lg-4">
						<form:input path="beNo" cssClass="form-control" />
						<form:errors path="beNo" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Be Date:</label>
					</div>
					<div class="col-lg-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Party:</label>
					</div>
					<div class="col-lg-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Type:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-4">
						<form:input path="beDate" cssClass="form-control" />
						<form:errors path="beDate" />
					</div>
					<div class="col-lg-4">
						<form:select path="party.id" class="form-control">
							<form:option value="" label=" Select Party " />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					<div class="col-lg-4">
						<form:select path="inwardType.id" class="form-control">
							<form:option value="" label=" Select Type " />
							<form:options items="${inwardTypeMap}" />
						</form:select>

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Remark:</label>
					</div>
				
				</div>
			</div>
			
			<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-4 col-sm-4">
						<form:textarea path="remark" cssClass="form-control" />
						<form:errors path="remark" />
					</div>
			</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="form-group">
			<div class=col-xs-12>
				<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" /> 
					<a id="mtListingBtnId"	class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/metalOutwardMt.html">Listing</a>
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="srNo" />
					<input type="hidden" name="adminRightsMap" id="adminRightsMap" value="${adminRightsMap}"  />
				</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>
<script type="text/javascript">
var canViewFlag=false;

	$(document)
			.ready(
					function(e) {
						$(".metalOutwardMtForm")
								.validate(
										{
											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/mOutward/invoiceNoAvailable.html' />",
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
												'Party.id' : {
													required : true,
												},
												'InwardType.id' : {
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
							var prev = $('#metalWt').val();
							$('#prevMetalWt').val(prev);
							$("#mOutwardDt").css('display', 'block');
							
							
						}
						else if (window.location.href.indexOf('view') != -1) {
							canViewFlag=true;
							
							$('#metalOutwardDtEntry').find('input, textarea, select').attr('disabled','disabled');
							$('#rowId').find('input, textarea, select').attr('disabled','disabled');
							$('#rowId').removeAttr('onclick');
							$('#metalOutwardMtDivId').removeAttr('onclick');
							$('#metalOutwardMtDivId .btn').hide();
							$('#mtListingBtnId').show();
							$('#btnLblId').hide();
							
							
							
						
							$('#metalOutwardDtEntry .button').hide();
							
							if($('#adminRightsMap').val() != true){
							
							$("#mOutwardDt").css('display', 'block');
							$('#metalOutwardMtDivId').find('input, textarea, select').attr('disabled','disabled');
							$('#mOutwardDt :input').attr('disabled',true);
							$('#mOutwardDt .btn').hide();
							$('#mOutwardDt .btn').removeAttr('onclick');
							}
							
						}else{
						
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
						}
						
						popMetalOutwardDt();

					});

	function popMetalOutwardDt() {
		$("#metalODtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/metalOutwardDt/listing.html?pInvNo="
									+ $('#invNo').val()+"&canViewFlag="+canViewFlag,
						});
	}

	function popPurity(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/purityy/list.html' />?metalId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#purityId").html(data);
					}
				});
	}

/* 	function getStock(vSel) {

		
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/metalOutwardDt/stock.html' />?metalWt='
							+ vSel
							+ "&purityId="
							+ $('#purity\\.id').val()
							+ "&colorId=" + $('#color\\.id').val(),
					type : 'GET',
					success : function(data) {

						var vMetalWt = $('#metalWt').val()

						if (Number(data) < Number(vMetalWt)) {
							
							displayMsg(event, this);
						}

						if (data == -1) {
							
							displayMsg(event, this);
						}

						//alert("tempbal==" + data);
						$('#tempBal').val(data);

					}
				});
	} */

	$(document)
			.on(
					'submit',
					'form#metalOutwardDtt',
					function(e) {
						$("#pInvNo").val($("#invNo").val());
						
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");
						
						//$('form#metalOutwardDtt input[type="text"],textarea, select').val('');
					//	$('form#metalOutwardDtt select').val('');

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {

										//	alert("in success" + data);
										if(data.indexOf("Error") != -1){
											displayMsg(this, null, data);
										}else{
											
											popMetalOutwardDt();
											
											var vParty = $('#party\\.id').val();
											var vInwardType = $('#inwardType\\.id').val();
											
											$('form#metalOutwardDtt input[type="text"],texatrea, select').val('');
											$('form#metalOutwardDtt select').val('');
											
											$('#party\\.id').val(vParty);
											$('#inwardType\\.id').val(vInwardType);

											$("#invWt").val('0.0');
											$("#metalWt").val('0.0');
											$('#rate1').val('0.0');
									
											$("#department\\.id").focus();
											
										if (data == -1) {
											$("#metalOutwardDtEntry").css('display', 'none');
											$('#metalOutwardDtEntryId').val('');
										}
									}
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						e.preventDefault(); //STOP default action
					});

	function editMetalOutwardDt(mtId) {
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/metalOutwardDt/editRights.html?dtId="+$("#id").val(),
			type : 'GET',
			success : function(data) {
				
				if(data === '1'){
					$.ajax({
						url : "/jewels/manufacturing/transactions/metalOutwardDt/edit/"
								+ mtId + ".html",
						type : 'GET',
						success : function(data) {
							$("#rowId").html(data);

							$("#metalOutwardDtEntry").css('display', 'block');
							$("#department\\.id").focus();

						}
					});	
				
				}else{
					displayMsg(event, this,'You Can Not Edit BackDate Entry, Please Contact Administrator');
				}
				
			}
		
		});
		
		
	
	}

	function setAmount() {
			
		var metalWt = $('#metalWt').val();
		var rate = $('#rate1').val();
		var invWt =$('#invWt').val();
		var defRate = rate * 1;
		var defMetalWt = metalWt *1;
		var defInvWt =invWt*1;
		var amount = metalWt * defRate;
		

		$('#rate1').val(defRate.toFixed(2));
		$('#aAmount').val(amount.toFixed(2));
		$('#amount').val(amount.toFixed(2));
		$('#metalWt').val(defMetalWt.toFixed(3));
		$('#invWt').val(defInvWt.toFixed(3));
		
	}

	function goToMetalOutwardDtEntry() {
		
		  $
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/metalOutwardDt/addRights.html' />?pInvNo='+ $('#invNo').val(),
				type : 'GET',
				success : function(data) {
					
					if(Number(data) == 1){
		
		
					$("#metalOutwardDtEntry").css('display', 'block');
					
					$('#purity\\.id').attr('disabled',false);
					$('#color\\.id').attr('disabled',false);
					$('#department\\.id').attr('disabled',false);
					$('#metal\\.id').attr('disabled',false);
					
					var vParty = $('#party\\.id').val();
					var vInwardType = $('#inwardType\\.id').val();
					
					$('form#metalOutwardDtt input[type="text"],textarea, select').val('');
					$('#metalOutwardDtEntryId').val('');
					
					$('#party\\.id').val(vParty);
					$('#inwardType\\.id').val(vInwardType);
					
					$("#invWt").val('0.0');
					$("#metalWt").val('0.0');
					$('#rate1').val('0.0');
					
					$("#department\\.id").focus();
					
					}else{
						displayMsg(event, this, 'You can not Add record In BackDate Invoice, Please contact Administrator');
					}

				}
			}); 
	}

	$(document).ready(function(e) {
		$(".metalOutwardDttForm").validate({
			rules : {
				'metal.id' : {
					required : true,
				},
				'purity.id' : {
					required : true,
				},
				'color.id' : {
					required : true,
				},
				invWt : {
					required : true,
					greaterThan : "0,0",
				},
				metalWt : {
					required : true,
					greaterThan : "0,0",
				}

			},

			messages : {
				invWt : {
					greaterThan : "This field is required"
				},
				metalWt : {
					greaterThan : "This field is required"
				}
			},

		});

		totsummary();
	});
	

function totsummary(){
		
		$('#metalODtId').bootstrapTable({})
		.on(
				'load-success.bs.table',
				function(e, data) {
					var data = JSON.stringify($("#metalODtId").bootstrapTable('getData'));

					
					var vInvWt = 0.0;
					var vAmount = 0.0;
					var vMetalWt = 0.0;
					
					
					
					$.each(JSON.parse(data), function(idx, obj) {
						
						
						vInvWt		+= Number(obj.invWt);
						vAmount		+= Number(obj.amount);
						vMetalWt	+= Number(obj.metalWt);  
						
						
					});
					
					$('#vTotalInvWt').val(" " + vInvWt.toFixed(3));
					$('#vTotalAmount').val(" " + parseFloat(vAmount).toFixed(2));
					$('#vTotalMetalWt').val(" "+ parseFloat(vMetalWt).toFixed(3));
					
				});
		
		}
		
function deleteMetalOutwardDt(e,dtId){
	
	displayDlg(
			e,
			'javascript:deleteMetalOutwardDtData('+dtId+');',
			'Delete',
			'Do you want to Delete  ?',
			'Continue');
}

function deleteMetalOutwardDtData(dtId){

	$("#modalDialog").modal("hide");
	
	$.ajax({
		
		url:'/jewels/manufacturing/transactions/metalOutwardDt/delete/'+dtId+'.html',
		data: 'GET',
		success : function(data){
			
							
			if(Number(data) == -1){
				displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
				
			}else{
				
				window.location.href = data;
				
				}
			
		}
		
	})
	
}

function popMetalOutCancelBtn(){
	$("#metalOutwardDtEntry").css('display', 'none');
}
	
		
	
</script>
<div id="mOutwardDt" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading">
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:goToMetalOutwardDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div>
						<table id="metalODtId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
							data-striped="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="department" data-sortable="true">Location</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="invWt" data-sortable="true">Invoice Wt</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
									<th data-field="rate1" data-sortable="true">Rate</th>
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
					Total Metal Wt : <input type="text" id="vTotalMetalWt" name="vTotalMetalWt" value="${totalMetalWt}"  
									maxlength="7" size="7" disabled="disabled" />
					
						&nbsp;&nbsp;
					Total Amount : <input type="text" id="vTotalAmount" name="vTotalAmount" value="${totalAmount}"  
									maxlength="7" size="7" disabled="disabled" />			
					
					</div>

			<c:set var="option" value="User" />

			<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
		</div>
		<div id="metalOutwardDtEntry" style="display: none">
			<div id="rowId">
				<div class="form-group">
					<div class="form-group">
						<form:form commandName="metalOutwardDt" id="metalOutwardDtt"
							action="/jewels/manufacturing/transactions/metalOutwardDt/add.html"
							cssClass="form-horizontal metalOutwardDttForm">

							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Location</th>
										<th>Metal</th>
										<th>Purity</th>
										<th>Color</th>
										<th>Invoice Wt</th>
										<th>Metal Wt</th>
										<th>Rate</th>
										<th>Amount</th>
										<!-- but on next row  <th>Remark</th>  -->
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><form:select path="department.id" class="form-control" disabled="true">
												<form:option value="" label="- Select Department -" />
												<form:options items="${departmentMap}" />
											</form:select></td>
										
										
										<td><form:select path="metal.id" class="form-control" onChange="javascript:popPurity(this.value);" disabled="true">
												<form:option value="" label="- Select Metal -" />
												<form:options items="${metalMap}" />
											</form:select></td>
										<td>
										
											<div id="purityId">
												<form:select path="purity.id" class="form-control" disabled="true" >
													<form:option value="" label="- Select Purity -" />
													<form:options items="${purityMap}" />
												</form:select>
											</div>
										</td>
										<td>
											<div id="colorId">
												<form:select path="color.id" class="form-control" disabled="true">
													<form:option value="" label="- Select Color -" />
													<form:options items="${colorMap}" />
												</form:select>
											</div>
										</td>


										<td><form:input path="invWt" cssClass="form-control" onchange="javascript:setAmount()"/></td>
										<td><form:input path="metalWt" cssClass="form-control"  onChange="javascript:setAmount()" /></td>
										<td><form:input path="rate1" cssClass="form-control"	onchange="javascript:setAmount()" /></td>
										<td><input type="text" id="aAmount" name="aAmount" class="form-control" disabled="true" /></td>
									</tr>
									<tr>
										<td colspan="10"><input type="submit" value="Save" class="btn btn-primary" id="dtSaveBtnId"/> 
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMetalOutCancelBtn()">
										<form:input type="hidden" id="metalOutwardDtEntryId" path="id" /> 
										<input type="hidden" id="pInvNo" name="pInvNo" />
											<form:input type="hidden" path="department.id" />  
											<input type="hidden" id="prevMetalWt" name="prevMetalWt" /> 
											<input type="hidden" id="metalWt" name="metalWt" /> 
											<form:input	type="hidden" path="uniqueId" /> 
											<form:input type="hidden" path="amount" /></td>
									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>
