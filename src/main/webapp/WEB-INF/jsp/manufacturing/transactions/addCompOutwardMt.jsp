<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<div id="compOutwardMtDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Component Outward</span>
	</div>
	<div class="form-group">
	
		<form:form commandName="compOutwardMt"
			action="/jewels/manufacturing/transactions/compOutwardMt/add.html"
			cssClass="form-horizontal compOutwardMtForm">

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
					<div class="col-lg-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							No:</label>
					</div>
					<div class="col-lg-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
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
						<label for="inputLabel3" class=".col-lg-2 text-right">Be
							Date:</label>
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
							<form:option value="" label=" Select Party " required="true" />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					<div class="col-lg-4">
						<form:select path="inwardType.id" class="form-control" required="true">
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
			<div class="col-xs-12">
				<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" id="compOutwartSaveBtn"/> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/compOutwardMt.html">Listing</a>
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="srNo" />
					 
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>


<div id="cOutwardDt" style="display: none">

	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading">
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
		
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:goToStoneOutwardDtEntry();"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
					</div>
					<div>
						<table id="compOutDtId" data-toggle="table"
							data-toolbar="#toolbar" data-pagination="false"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" 
							data-height="350">
							<thead>
								<tr class="btn-primary">
									<th data-field="department" data-sortable="true">Department</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="component" data-sortable="true">Component</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="invWt" data-sortable="true">Invoice Wt</th>
									<th data-field="qty" data-sortable="true">Qty</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
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

		
		</div>


		<div id="compOutwardDtEntry" style="display: none">
			<div id="rowId">
				<%-- <div class="form-group">
					<div class="form-group">
						<form:form commandName="compOutwardDt" id="compOutwardDtt"
							action="/jewels/manufacturing/transactions/compOutwardDt/add.html"
							cssClass="form-horizontal compOutwardDttForm">
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Location</th>
										<th>Metal</th>
										<th>Component</th>
										<th>Purity</th>
										<th>Color</th>
										<th>Invoice Wt</th>
									
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
											<div id="componentId">
												<form:select path="component.id" class="form-control" disabled="true">
													<form:option value="" label="- Select Component -" />
													<form:options items="${componentMap}" />
												</form:select>
											</div>
										</td>
										<td>
											<div id="purityId">
												<form:select path="purity.id" class="form-control" disabled="true">
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
										<td><form:input path="invWt" cssClass="form-control" onchange="javascript:setAmount()" /></td>


										
									</tr>
									</tbody>
									
									<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Per Gms Rate</th>
										<th>Qty</th>
										<th>Metal Wt</th>
										<th>Rate</th>
										<th>Amount</th>
									
									</tr>
									</thead>
									<tbody>
									<tr>
									<td><form:checkbox path="perGramRate"  onchange="javascript:setAmount()" /></td>
									<td><form:input path="qty" cssClass="form-control" onchange="javascript:setAmount()" /></td>
										<td><form:input path="metalWt" cssClass="form-control" onchange="javascript:setAmount()" /></td>
										<td><form:input path="rate" cssClass="form-control"	onchange="javascript:setAmount()" /></td>
										<td><form:input path="amount"  class="form-control" readonly="true" /></td>
									</tr>
											
									
									
									<tr>
										<td colspan="10"><input type="submit" value="Save" class="btn btn-primary" /> 
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popCompOutwardDtCancelBtn()"> 
										<form:input type="hidden" id="compOutwardDtId" path="id" /> 
										<input type="hidden" id="pInvNo" name="pInvNo" />
										<form:input type="hidden" path="department.Id" /> 
										<input type="hidden" id="prevMetalWt" name="prevMetalWt" /> 
										<input type="hidden" id="metalWt" name="metalWt" /> 
										<form:input type="hidden" path="uniqueId" /> 
										
										
																				
									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				</div> --%>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">


var canViewFlag = false;
	$(document)
			.ready(
					function(e) {
						
$('select').chosen();
						
						$.validator.setDefaults({ ignore: ":hidden:not(select)" });
					
											
					// validation of chosen on change
						if ($("select.form-control").length > 0) {
						    $("select.form-control").each(function() {
						        if ($(this).attr('required') !== undefined) {
						            $(this).on("change", function() {
						                $(this).valid();
						            });
						        }
						    });
						}
						
						
						$(".compOutwardMtForm")
								.validate(
										{
											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/cOutward/invoiceNoAvailable.html' />",
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
						
						
						$("#invDate").val('${currentDate}');
						
						
						if('${canEditTranddate}' === "false"){
							$("#invDate").attr("disabled","disabled");
						}

						$("#beDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						if (window.location.href.indexOf('edit') != -1) {
							var prev = $('#metalWt').val();
							$('#prevMetalWt').val(prev);
							$("#cOutwardDt").css('display', 'block');

						}
						
						else if (window.location.href.indexOf('view') != -1) {
							canViewFlag=true;
							var prev = $('#metalWt').val();
							$('#prevMetalWt').val(prev);
							$("#cOutwardDt").css('display', 'block');
							
							$('#compOutwardMtDivId').find('input, textarea, select').attr('disabled','disabled');
							$('#cOutwardDt .btn').hide();
							$('#cOutwardDt :input').attr('disabled',true);
							$('#cOutwardDt .btn').removeAttr('onclick');
							$('#compOutwartSaveBtn').hide();
							
						
						}

						popCompOutwardDt();

					});

	function popCompOutwardDt() {
		$("#compOutDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/compOutwardDt/listing.html?pInvNo="
									+ $('#invNo').val()	+"&canViewFlag="+canViewFlag,
						});
	}



	/* function getStockChecking(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/compOutwardDt/stock.html' />?metalWt='
							+ vSel
							+ "&purityId="
							+ $('#purity\\.id').val()
							+ "&colorId="
							+ $('#color\\.id').val()
							+ "&componentId=" + $('#component\\.id').val(),
					type : 'GET',
					success : function(data) {
						var vMetalWt = $('#metalWt').val()

						if (Number(data) < Number(vMetalWt)) {
							
							displayMsg(event, this,'Stock Not Found');
						}

						if (data == -1) {
							displayMsg(event, this,'Stock Not Found');
						}

						$('#tempBal').val(data);

					}
				});
	} */

	$(document)
			.on(
					'submit',
					'form#compOutwardDtt',
					function(e) {
						$("#pInvNo").val($("#invNo").val());

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
										
										if(data.indexOf("Error") != -1){
											displayMsg(this, null, data);
											
										}else{
											var vParty = $('#party\\.id').val();
											var vInwardType = $('#inwardType\\.id').val();
											
																					
											popCompOutwardDt();
											$( 'form#compOutwardDtt input[type="text"],texatrea, select').val('');
											
											$('#party\\.id').val(vParty);
											$('#inwardType\\.id').val(vInwardType);
											
											
											$('form#compOutwardDtt select').val('').trigger('chosen:updated');
											
											$('form#compOutwardDtt #perGramRate1').prop("checked", false );
											
											$("#invWt").val('0.0');
											$("#metalWt").val('0.0');
											$('#rate').val('0.0');
											
											
											if (data == -1) {
											$("#compOutwardDtEntry").css('display', 'none');
											$("#compOutwardDtId").val('');
											$("#rowId").html('');
													
													
												
											}
										}
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						e.preventDefault(); //STOP default action
					});

	function editCompODt(dtId) {
		$.ajax({
			url : "/jewels/manufacturing/transactions/compOutwardDt/editRights.html?dtId="+$("#id").val(),
			type : 'GET',
			success : function(data) {
			
				if(data === '1'){
					$("#compOutwardDtEntry").css('display', 'block');
					
					$.ajax({
						url : "/jewels/manufacturing/transactions/compOutwardDt/edit/"
								+ dtId + ".html",
						type : 'GET',
						success : function(data) {
							$("#rowId").html(data);
							
							$("#metal\\.id").focus();
							
							$('#metal\\.id').prop('disabled', true).trigger("chosen:updated");
							$('#department\\.id').prop('disabled', true).trigger("chosen:updated");
							$('#metal\\.id').prop('disabled', true).trigger("chosen:updated");
							$('#component\\.id').prop('disabled', true).trigger("chosen:updated");
							$('#purity\\.id').prop('disabled', true).trigger("chosen:updated");
							$('#color\\.id').prop('disabled', true).trigger("chosen:updated");
							$('html, body').animate({
								scrollTop : $("#compOutwardDtEntry").offset().top
							}, 50);
						}
					});
				}else{
					displayMsg(event, this,'You Can Not Edit BackDate Entry, Please Contact Administrator');
				}
				
			}
		
		});
		
	
	}

	function setAmount() {
		var qty = $('#qty').val();
		var metalWt = $('#metalWt').val();
		var rate = $('#rate').val();
		var invWt =$('#invWt').val();
		var defRate = rate * 1;
		var defMetalWt = metalWt *1;
		var defInvWt =invWt*1;
		var amount = metalWt * defRate;
		
		
		var amount = metalWt * defRate;
		if($('#perGramRate1').prop('checked')){
				
		 amount = metalWt * defRate;
				
			}else{

		 amount = qty * defRate;
				
			}
		

		$('#rate').val(defRate.toFixed(2));
		$('#amount').val(amount.toFixed(2));
		$('#metalWt').val(defMetalWt.toFixed(3));
		$('#invWt').val(defInvWt.toFixed(3));
	}

	function goToStoneOutwardDtEntry() {
		

		  $
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/compOutwardDt/addRights.html' />?pInvNo='+ $('#invNo').val(),
				type : 'GET',
				success : function(data) {
					
					
		if(Number(data) == 1){
		
			$("#compOutwardDtEntry").css('display', 'block');
			
			/* $('#purity\\.id').attr('disabled',false);
			$('#color\\.id').attr('disabled',false);
			$('#department\\.id').attr('disabled',false);
			$('#metal\\.id').attr('disabled',false);
			$('#component\\.id').attr('disabled',false);
			
	 		var vParty = $('#party\\.id').val();
			var vInwardType = $('#inwardType\\.id').val();
			
			$('form#compOutwardDtt input[type="text"],textarea, select').val('');
			$('#compOutwardDtId').val('');
			
			$('#party\\.id').val(vParty);
			$('#inwardType\\.id').val(vInwardType);
			
			
			
			$("#invWt").val('0.0');
			$("#metalWt").val('0.0');
			$('#rate').val('0.0');
			$('#qty').val('0.0');
			
			$("#department\\.id").focus(); */
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/compOutwardDt/edit/"
						+ 0 + ".html",
				type : 'GET',
				success : function(data) {
					$("#rowId").html(data);
					
					$("#metal\\.id").focus();
					
					$('html, body').animate({
						scrollTop : $("#compOutwardDtEntry").offset().top
					}, 50);
				}
			});
			
	
		}else{
				displayMsg(event, this, 'You can not Add record In BackDate Invoice, Please contact Administrator');
		}

	}
}); 
	}


	
	
		
		$('#compOutDtId').bootstrapTable({})
		.on(
				'load-success.bs.table',
				function(e, data) {
					var data = JSON.stringify($("#compOutDtId").bootstrapTable('getData'));

					
					var vInvWt = 0.0;
					var vQty = 0.0;
					var vMetalWt = 0.0;
					
					
					
					$.each(JSON.parse(data), function(idx, obj) {
						
						
						vInvWt		+= Number(obj.invWt);
						vQty		+= Number(obj.qty);
						vMetalWt	+= Number(obj.metalWt);  
						
						
					});
					
					$('#vTotalInvWt').val(" " + vInvWt.toFixed(3));
					$('#vTotalQty').val(" " + parseFloat(vQty));
					$('#vTotalMetalWt').val(" "+ parseFloat(vMetalWt).toFixed(3));
					
				});
		

	
	function deleteCompOutwardDt(e,dtId){
				
		displayDlg(
				e,
				'javascript:deleteCompOutwardDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}
	
	function deleteCompOutwardDtData(dtId){
					
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/compOutwardDt/delete/'+dtId+'.html',
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
	

	function  popCompOutwardDtCancelBtn() {
		$('form#compOutwardDtt #perGramRate1').prop("checked", false );
		$('#compOutwardDtEntry').css('display','none');
	}
	
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>