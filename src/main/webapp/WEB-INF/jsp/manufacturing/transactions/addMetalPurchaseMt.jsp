<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalMetalPurchaseDt.jsp"%>


<div id="metalPurchaseMtDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 15px;">Metal Purchase</span>
	</div>
	<div class="form-group">
	
		<form:form commandName="metalPurchaseMt" id="metalPurchaseMtFormId"
			action="/jewels/manufacturing/transactions/metalPurchaseMt/add.html"
			cssClass="form-horizontal metalPurchaseMtForm">

					<!-- Column Sizing -->
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							No</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							Date</label>
					</div>
					
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Be
							No</label>
					</div>
							<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Be Date</label>
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
				<div class="col-xs-12">
			
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Party</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Type</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Due Date</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Remark</label>
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
					
					<div class="col-lg-3 col-sm-3">
						<form:input path="dueDate" cssClass="form-control" />
						<form:errors path="dueDate" />
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:textarea path="remark" cssClass="form-control" />
						<form:errors path="remark" />
					</div>
				</div>
			</div>
			
			<div class="row">
			<div class="col-xs-12">
				
			</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
			<div class="col-xs-12">
				<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" id="metPurcSubmitBtn" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/metalPurchaseMt.html">Listing</a>
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
var vPurityId;
	$(document)
			.ready(
					function(e) {
						$(".metalPurchaseMtForm")
								.validate(
										{

											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/purcInvoiceNoAvailable.html' />",
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
						
						
						$("#dueDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#beDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
						
						if(window.location.href.indexOf('edit') != -1) {
							
					
						$("#mPurchDt").css('display', 'block');
						popMetalPurchaseDt();
							
						}else if (window.location.href.indexOf('view') != -1) {
							
							canViewFlag=true;
							
							$('#mPurchDt .btn').removeAttr('onclick');
							$('#metPurcSubmitBtn').hide();
							$('#metPurcSubmitBtn').removeAttr('type');
								
							popMetalPurchaseDt();
								
							$("#mPurchDt").css('display', 'block');
							$('#metalPurchaseMtDivId').find('input, textarea, select').attr('disabled',true);
							$('#mPurchDt :input').attr('disabled',true);
							$('#mPurchDt .btn').hide();
							$('#mPurchDt .btn').removeAttr('onclick');
							
						}else{
							
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

						
					

					});

	
	
	
	
	
	
	 $(document)
		.on(
			'submit',
			'form#metalPurchaseMtFormId',
			 function(e){
				 $("#metPurcSubmitBtn").prop("disabled", true).addClass("disabled");
				
				
			});
	 
	 
	 function goToMetalPurchaseDtEntry(){
		 
		 vPurityId ="";
		 $('#myMetalPurchaseDtModal').modal('show');
		 $('#in9991').prop("checked", false);
		 
			var vParty = $('#party\\.id').val();
			var vInwardType = $('#inwardType\\.id').val();
			
			$('form#metalPurchaseDtEnt input[type="text"],textarea, select').val('');
			$('#metalPurchaseDtEntryId').val('');
			
			$('#party\\.id').val(vParty);
			
			$('#inwardType\\.id').val(vInwardType);
			
			$("#invWt").val('0.0');
			$('#rate').val('0.0');
			
			setTimeout(function(){
				$("#metal\\.id").focus();	
			}, 500);
			
		 
	 }
	 
	 
		function setAmount() {
			
			var rate = $('#rate').val();
			var invWt =$('#invWt').val();
			var defRate = rate * 1;
			var defInvWt =invWt*1;
			var amount = invWt * defRate;
			

			$('#rate').val(defRate.toFixed(2));
			$('#aAmount').val(amount.toFixed(2));
			$('#amount').val(amount.toFixed(2));
			$('#invWt').val(defInvWt.toFixed(3));
			

		}
		
		
		
		function editMetalPurcDt(dtId){
			
		//	$("#mPurchDtEntry").css('display', 'block');
		
		
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/metalPurchaseDt/edit/"+ dtId + ".html",
				type : 'GET',
				dataType:"JSON",
				success : function(data) {
					
					if(data =="-1"){
						displayMsg(this,null,'Balance Adjusted, Can Not Edit');
					}else{
						
						
						$('#myMetalPurchaseDtModal').modal('show');
						setTimeout(function(){
							$("#metal\\.id").focus();	
						}, 500);
						
		
						vPurityId="";
							
						setTimeout(function()  {
							
							$.each(data,function(k,v){
								
								$('#metalPurchaseDtModalDivId  #'+k).val(v);
								
								if(k ==='metal\\.id'){
									
									$('#metal\\.id').attr('selected', 'selected').trigger('change');
									
								}
								
								if(k ==='purity'){
									vPurityId=v;
									
								}
								
								if(k === 'in999'){
								
									if(v === true){
									$('#in9991').prop("checked", true);
								}else{
									$('#in9991').prop("checked", false);
								}
								}
							});
							
							
							
						}, 100);
						
						
					}
				
			
					
				}
			});	
		}
	 
		
		function popMetalPurchaseDt() {
			$("#metalPurcDtTblId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/metalPurchaseDt/listing.html?pInvNo="
										+ $('#invNo').val()+"&canViewFlag="+canViewFlag
							});
		}

		function popPurity(vSel) {
			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/metalPurchaseMt/purity/list.html' />?metalId='
								+ vSel,
						type : 'GET',
						success : function(data) {
							
							$("#purityId").html(data);
							$('#purity\\.id').val(vPurityId);	
						}
					});
		}
		
		

		
		/* $(document)
		.on(
				'submit',
				'form#metalPurchaseDtEnt',
				function(e) {
					$("#pInvNo").val($("#invNo").val());
					$("#purityyId").val($("#purity\\.id").val());
					$("#invWtt").val($("#invWt").val());

					var postData = $(this).serializeArray();
					var formURL = $(this).attr("action");
					
			
					$
							.ajax({
								url : formURL,
								type : "POST",
								data : postData,
								success : function(data, textStatus, jqXHR) {
							
									popMetalPurchaseDt();
									
									var vParty = $('#party\\.id').val();
									var vInwardType = $('#inwardType\\.id').val();

									$('form#metalPurchaseDtEnt input[type="text"],textarea, select').val('');
									$('form#metalPurchaseDtEnt select').val('');
									
									$('#party\\.id').val(vParty);
									$('#inwardType\\.id').val(vInwardType);

									$("#invWt").val('0.0');
									$('#rate').val('0.0');
									$('#metal\\.id').focus();
							if(data == -1){
								$('#mPurchDtEntry').css('display','none');
								$('#metalPurchaseDtEntryId').val('');
							}

								},

								error : function(jqXHR, textStatus,
										errorThrown) {
								}
							});
					e.preventDefault(); //STOP default action
				});
		 */
		
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
									
								},

								

							});

					totsummary();
				});
		
		function totsummary(){
			
			$('#metalPurcDtTblId').bootstrapTable({})
			.on(
					'load-success.bs.table',
					function(e, data) {
						var data = JSON.stringify($("#metalPurcDtTblId").bootstrapTable('getData'));
						
						var vInvWt = 0.0;
						var vAmount = 0.0;
											
						$.each(JSON.parse(data), function(idx, obj) {
							
							
							vInvWt		+= Number(obj.invWt);
							vAmount		+= Number(obj.amount);
						  
							
							
						});
						
						$('#vTotalInvWt').val(" " + vInvWt.toFixed(3));
						$('#vTotalAmount').val(" " + parseFloat(vAmount).toFixed(2));
						
						
					});
			
			}
		
		function deleteMetalPurcDt(e,dtId){
			
			displayDlg(
					e,
					'javascript:deleteMetalPurchaseDtData('+dtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
		}
		
		function deleteMetalPurchaseDtData(dtId){
		
			$("#modalDialog").modal("hide");
			
			$.ajax({
				
				url:'/jewels/manufacturing/transactions/metalPurchaseDt/delete/'+dtId+'.html',
				data: 'GET',
				success : function(data){
					 if(data == '-1'){
						 displayMsg(this,null,'Balance Adjusted, Can Not Delete');
					 }else{
						 popMetalPurchaseDt();	 
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
	 
	
	 </script>
	 
 
<div id="mPurchDt" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 15px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:goToMetalPurchaseDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div>
						<table id="metalPurcDtTblId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
							data-striped="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="invWt" data-sortable="true">Invoice Wt</th>
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
					Total Amount : <input type="text" id="vTotalAmount" name="vTotalAmount" value="${totalAmount}"  
									maxlength="7" size="7" disabled="disabled" />			
					
					</div>

			<c:set var="option" value="User" />

			<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
		</div>
		
		 <%-- <div id="mPurchDtEntry" style="display: none">
			<div id="rowId">
				<div class="form-group">
					<div class="form-group">
						<form:form commandName="metalPurchaseDt" id="metalPurchaseDtEnt"
							action="/jewels/manufacturing/transactions/metalPurchaseDt/add.html"
							cssClass="form-horizontal metalInwardDtEntForm">

							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-warning" class="panel-heading">
										<th>Metal</th>
										<th> Purity</th>
										<th>Color</th>
										<th>Invoice Wt</th>
										<th>Rate</th>
										<th>Amount</th>
										<th data-width="50px">In_999</th>
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
										<td><form:input path="rate" cssClass="form-control"	onchange="javascript:setAmount();" /></td>
										<td><input type="text" id="aAmount" name="aAmount" class="form-control" disabled="disabled" /></td>
										<td><form:checkbox path="in999"  /></td>
										<td><form:input path="remark" cssClass="form-control" /></td>
									</tr>
									<tr>
										<td colspan="10"><input type="submit" value="Save" class="btn btn-primary" /> 
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMetalPurcCancelBtn()">
											<form:input type="hidden" id="metalPurchaseDtEntryId" path="id" /> 
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
		</div>   --%>
	</div>
</div>	 

	 

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>
