<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div id="metalInwardDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Metal Inward</span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="metalInwExcelPreviewBtn" onClick="javascript:metalInwReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="metalInwPreviewBtn" onClick="javascript:metalInwReport(2);" />
						</div>

		</div>

	</div>
	<div class="form-group">
	
		<form:form commandName="metalInwardMt" id="metInwardFormId"
			action="/jewels/manufacturing/transactions/metalInwardMt/add.html"
			cssClass="form-horizontal metalInwardMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">Metal Inward ${action}
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
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							No:</label>
					</div>
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							Date:</label>
					</div>
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Be
							No:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<form:input path="invNo" cssClass="form-control"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-4 col-sm-4">
						<form:input path="invDate" cssClass="form-control" />
						<form:errors path="invDate" />
					</div>
					<div class="col-lg-4 col-sm-4">
						<form:input path="beNo" cssClass="form-control" />
						<form:errors path="beNo" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">BeDate:</label>
					</div>
					
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Supplier:</label>
					</div>
					
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Type:</label>
					</div>
					
										
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<form:input path="beDate" cssClass="form-control" />
						<form:errors path="beDate" />
					</div>
					
				
					
					<div class="col-lg-4 col-sm-4">
						<form:select path="party.id" class="form-control">
							<form:option value="" label=" Select Party " />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					
							<div class="col-lg-4 col-sm-4">
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
			<div class="row">
			<div class="col-xs-12">
				<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" id="metInwSubmitBtn" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/metalInwardMt.html">Listing</a>
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="uniqueId" />
					<input type="hidden" name="adminRightsMap" id="adminRightsMap" value="${adminRightsMap}"  />
					<input type="hidden" name="vTranDate" id="vTranDate" />		
					<input type="hidden" name="xml" id="xml" value="${xml}"  />		

				</div>
			</div>
			
			
			</div>

		</form:form>
		
		
		 <!-- Download Common pdf Report -->
		
			<div style="display: none">		
				<form:form target="_blank"  id="styleNotMatchForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="generateDataRpt"/>
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
</div>
</div>

<script type="text/javascript">
var canViewFlag = false;
	$(document)
			.ready(
					function(e) {
						$(".metalInwardMtForm")
								.validate(
										{

											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/invoiceNoAvailable.html' />",
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
						
						if('${company}' != "nuance"){
							$("#invNo").attr("disabled","disabled");
						}
						
						
						if('${canEditTranddate}' === "false"){
							$("#vTranDate").val('${currentDate}');
							$("#invDate").attr("disabled","disabled");
						}

						$("#beDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
						
						if(window.location.href.indexOf('edit') != -1) {

							if('${canEditTranddate}' === "false"){
								$("#vTranDate").val($("#invDate").val());
									
								}

						
							$("#mInwardDt").css('display', 'block');
							
						}else if (window.location.href.indexOf('view') != -1) {
							
							canViewFlag=true;
							
							$('#mInwardDt .btn').removeAttr('onclick');
							$('#metInwSubmitBtn').hide();
							$('#metInwSubmitBtn').removeAttr('type');
							
							
							if($('#adminRightsMap').val() != true){
								
								
							$("#mInwardDt").css('display', 'block');
							$('#metalInwardDivId').find('input, textarea, select').attr('disabled',true);
							$('#mInwardDt :input').attr('disabled',true);
							$('#mInwardDt .btn').hide();
							$('#mInwardDt .btn').removeAttr('onclick');
							}
						}else{
							
							$("#invDate").val('${currentDate}');
							
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

						popMetalInwardDt();
					

					});

	
	
	
	
	
	
	 $(document)
		.on(
			'submit',
			'form#metInwardFormId',
			 function(e){
				 $("#metInwSubmitBtn").prop("disabled", true).addClass("disabled");
				
				
			});
	
	
	
	function popMetalInwardDt() {
		$("#metalIDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/metalInwardDt/listing.html?pInvNo="
									+ $('#invNo').val()+"&canViewFlag="+canViewFlag,
						});
	}

	function popPurity(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/purity/list.html' />?metalId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#purityId").html(data);
					}
				});
	}
	

	$(document)
			.on(
					'submit',
					'form#metalInwardDtEnt',
					function(e) {
						$("#pInvNo").val($("#invNo").val());
						$("#purityyId").val($("#purity\\.id").val());
						$("#invWtt").val($("#invWt").val());

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");
						
					/* 	$('form#metalInwardDtEnt input[type="text"],textarea, select').val('');
						$('form#metalInwardDtEnt select').val(''); */

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
										
									
										popMetalInwardDt();
										
										var vParty = $('#party\\.id').val();
										var vInwardType = $('#inwardType\\.id').val();

										$('form#metalInwardDtEnt input[type="text"],textarea, select').val('');
										$('form#metalInwardDtEnt select').val('');
										
										$('#party\\.id').val(vParty);
										$('#inwardType\\.id').val(vInwardType);

										$("#invWt").val('0.0');
										$("#metalWt").val('0.0');
										$('#rate').val('0.0');
										$('#metal\\.id').focus();
								if(data == -1){
									$('#mInwardDtEntry').css('display','none');
									$('#metalInwardDtEntryId').val('');
								}

									},

									error : function(jqXHR, textStatus,
											errorThrown) {
										
									
									alert('Error : '+JSON.stringify(jqXHR));
									
										
									}
								});
						e.preventDefault(); //STOP default action
					});
	

	function editMetalInwDt(dtId) {
	
		$.ajax({
			url : "/jewels/manufacturing/transactions/metalInwardDt/editRights.html?dtId="+$("#id").val(),
			type : 'GET',
			success : function(data) {
				
				if(data === '1'){
					$("#mInwardDtEntry").css('display', 'block');
					$.ajax({
						url : "/jewels/manufacturing/transactions/metalInwardDt/edit/"+ dtId + ".html",
						type : 'GET',
						success : function(data) {
							$("#rowId").html(data);
							$("#metal\\.id").focus();
						}
					});	
				
				}else{
					displayMsg(event, this,'You Can Not Edit Back Dated Entry, Please Contact Administrator');
				}
				
			}
		
		});
		
	}

	function setAmount() {
		var metalWt = $('#metalWt').val();
		var rate = $('#rate').val();
		var invWt =$('#invWt').val();
		var defRate = rate * 1;
		var defMetalWt = metalWt *1;
		var defInvWt =invWt*1;
		var amount = metalWt * defRate;
		

		$('#rate').val(defRate.toFixed(2));
		$('#aAmount').val(amount.toFixed(2));
		$('#amount').val(amount.toFixed(2));
		$('#metalWt').val(defMetalWt.toFixed(3));
		$('#invWt').val(defInvWt.toFixed(3));
		

	}

	function goToMetalInwardDtEntry() {
		

		  $
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/metalInwardDt/addRights.html' />?pInvNo='+ $('#invNo').val(),
				type : 'GET',
				success : function(data) {
					
					if(Number(data) == 1){	
		
					$("#mInwardDtEntry").css('display', 'block');
					
					var vParty = $('#party\\.id').val();
					var vInwardType = $('#inwardType\\.id').val();
					
					$('form#metalInwardDtEnt input[type="text"],textarea, select').val('');
					$('#metalInwardDtEntryId').val('');
					
					$('#party\\.id').val(vParty);
					$('#inwardType\\.id').val(vInwardType);
					
					$("#invWt").val('0.0');
					$("#metalWt").val('0.0');
					$('#rate').val('0.0');
					
					$("#metal\\.id").focus();
					
					}else{
						displayMsg(event, this, 'You Can Not Add Record In BackDate Invoice, Please contact Administrator');
					}

				}
			});  
				
				
	}

	$(document).ready(
			function(e) {
				$(".metalInwardDtEntForm").validate(
						{
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
						 	highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
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
	
	
	function  popMetalInwCancelBtn() {
		$('#mInwardDtEntry').css('display','none');
	}
	
	function totsummary(){
		
		$('#metalIDtId').bootstrapTable({})
		.on(
				'load-success.bs.table',
				function(e, data) {
					var data = JSON.stringify($("#metalIDtId").bootstrapTable('getData'));

					
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
	
	
	function deleteMetalInwardDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteMetalInwardDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}
	
	function deleteMetalInwardDtData(dtId){
	
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/metalInwardDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
				
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Please Contact Administrator');
				}else{
					
					window.location.href = data;
					
				}
				
			}
			
		})
		
	}
	
	
	function inputFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}

		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
	}
	
	function metalInwReport(val){
			
			$
			.ajax({
				url : "/jewels/manufacturing/masters/reports/common/transactionReport.html?mtId="+ $("#id").val()+"&xml="+ $('#xml').val()+"&opt="+val,
				type : 'POST',
				success : function(data, textStatus, jqXHR) {
		
					if(val === 2){
						$('#timeValCommonPdf').val(data);
						$("#generateDataRpt").trigger('click');
						
					}else{
						$('#pFileName').val(data);
						$("#generateExcelReportss").trigger('click');
	
						}	
					
				}
			});
		}

	
</script>
<div id="mInwardDt" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading">
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:goToMetalInwardDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div>
						<table id="metalIDtId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
							data-striped="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="invWt" data-sortable="true">Invoice Wt</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="in999" data-sortable="false" data-formatter="inputFormatter">In 999</th>
									<th data-field="remark" data-sortable="true">Remark</th>
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
		<div id="mInwardDtEntry" style="display: none">
			<div id="rowId">
				<div class="form-group">
					<div class="form-group">
						<form:form commandName="metalInwardDt" id="metalInwardDtEnt"
							action="/jewels/manufacturing/transactions/metalInwardDt/add.html"
							cssClass="form-horizontal metalInwardDtEntForm">

							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-warning" class="panel-heading">
										<th>Metal</th>
										<th>Purity</th>
										<th>Color</th>
										<th>Invoice Wt</th>
										<th>Metal Wt</th>
										<th>Rate</th>
										<th>Amount</th>
										<th>In 999</th>
									  	<th>Remark</th>  
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><form:select path="metal.id" class="form-control"
												onChange="javascript:popPurity(this.value);">
												<form:option value="" label="- Select Metal -" />
												<form:options items="${metalMap}" />
											</form:select></td>
										<td>
											<div id="purityId">
												<form:select path="purity.id" class="form-control">
													<form:option value="" label="- Select Purity -" />
													<form:options items="${purityMap}" />
												</form:select>
											</div>
										</td>
										<td>
											<div id="colorId">
												<form:select path="color.id" class="form-control">
													<form:option value="" label="- Select Color -" />
													<form:options items="${colorMap}" />
												</form:select>
											</div>
										</td>

										<td><form:input path="invWt" cssClass="form-control" onchange="javascript:setAmount();"/></td>
										<td><form:input path="metalWt" cssClass="form-control" onchange="javascript:setAmount();" /></td>
										<td><form:input path="rate" cssClass="form-control"	onchange="javascript:setAmount();" /></td>
										<td><input type="text" id="aAmount" name="aAmount" class="form-control" disabled="disabled" /></td>
										<td><form:checkbox path="in999"  /></td>
										<td><form:input path="remark" cssClass="form-control" /></td>
									</tr>
									<tr>
										<td colspan="10"><input type="submit" value="Save" class="btn btn-primary" /> 
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMetalInwCancelBtn()">
											<form:input type="hidden" id="metalInwardDtEntryId" path="id" /> 
											<input type="hidden" id="pInvNo" name="pInvNo" />
											<input type="hidden" id="metal.id" name="metal.id" /> 
											<form:input type="hidden" path="uniqueId" /> 
											<form:input type="hidden" path="department.id" />
											<form:input type="hidden" path="amount" />
											<input type="hidden" id="purityyId" name="purityyId" />
											<input type="hidden" id="invWtt" name="invWtt" />
										</td>
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

