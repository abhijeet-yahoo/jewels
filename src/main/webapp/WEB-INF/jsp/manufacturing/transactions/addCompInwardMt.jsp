<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<!-- <a href="/jewels/manufacturing/masters/design/image/upload.html">Upload
	Image</a> -->
<div id="compInwardMtDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Component Inward</span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="compInwExcelPreviewBtn" onClick="javascript:compInwReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="compInwPreviewBtn" onClick="javascript:compInwReport(2);" />
						</div>

		</div>

	</div>
	<div class="form-group">
		<form:form commandName="compInwardMt" id="compInwardSub"
			action="/jewels/manufacturing/transactions/compInwardMt/add.html"
			cssClass="form-horizontal compInwardMtForm">

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
				<div class="col-xs-12">&nbsp;</div>
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
						<form:select path="party.id" class="form-control" required="true">
							<form:option value="" label=" Select Party " />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					
						<div class="col-lg-4 col-sm-4">
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
				
					<input type="submit" value="Save" class="btn btn-primary" id="compMtBtnSubmit"  /> 
					<a id="compMtListngBtnId" class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/compInwardMt.html">Listing</a>
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


<div id="cInwardDt" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading">
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:goToCompInwardDtEntry();"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
					</div>
					<div>
				
						<table id="compInvDtId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
							data-striped="true">
							
							<thead>
								<tr class="btn-primary">
								<th data-field="department" data-sortable="true">Location</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="component" data-align="left">Component</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="invWt" data-sortable="true">Invoice Wt</th>
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

		

		
		</div>

		<div id="compInwardDtEntry" style="display: none">
			<div id="rowId">
				
					
			
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
						
						
						
						
						
						$(".compInwardMtForm")
								.validate(
										{
											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/cInward/invoiceNoAvailable.html' />",
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

						if (window.location.href.indexOf('edit') != -1) {

							if('${canEditTranddate}' === "false"){
								$("#vTranDate").val($("#invDate").val());
									
								}
														
							$("#cInwardDt").css('display', 'block');
							
						}else if (window.location.href.indexOf('view') != -1) {
							
							canViewFlag=true;
							
							$('#compMtListngBtnId').show();
							$("#cInwardDt").css('display', 'block');
							$('#compInwDivId').find('input, textarea, select').attr('disabled','disabled');
							
							if($('#adminRightsMap').val() != true){
								
							$('#compInwardMtDivId .btn').hide();
							
							$('#compInwardMtDivId').find('input, textarea, select').attr('disabled','disabled');
							$('#cInwardDt :input').attr('disabled',true);
							$('#cInwardDt .btn').hide();
							$('#cInwardDt .btn').removeAttr('onclick');
							$('#compInwDivId .btn').hide();
							$('#compInwardDtBtnId').hide();
							$('#compInwDivId').find('input, textarea, select').attr('disabled','disabled');
							$('#compMtListngBtnId').show();
						}
						}	
					
						else{
							
							$("#invDate").val('${currentDate}');
							
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

						popCompInwardDt();

					});
	
	
	
	

    
    
    $(document)
	.on(
		'submit',
		'form#compInwardSub',
		 function(e){
			 $("#compMtBtnSubmit").prop("disabled", true).addClass("disabled");
			
		});
	
	

	function popCompInwardDt() {
		$("#compInvDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/compInwardDt/listing.html?pInvNo="
									+ $('#invNo').val()+"&canViewFlag="+canViewFlag,
						});
	}



	
	$(document)
			.on(
					'submit',
					'form#compInwardDtt',
					function(e) {
						
						$('#compInwardDtBtnId').attr('disabled', 'disabled');
						$("#pInvNo").val($("#invNo").val());
						$("#vPurityId").val($("#purity\\.id").val());
						$("#invWtt").val($("#invWt").val());

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");
						
					
								
						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
									
										popCompInwardDt();
										
										//var vParty = $('#party\\.id').val();
										//var vInwardType = $('#inwardType\\.id').val();
										
										$('form#compInwardDtt input[type="text"],texatrea, select').val('');
										
										$('form#compInwardDtt select').val('').trigger('chosen:updated');
										
										$('form#compInwardDtt #perGramRate1').prop("checked", false );
										
     									//$('#party\\.id').val(vParty);
										//$('#inwardType\\.id').val(vInwardType);

										$("#invWt").val('0.0');
										$("#metalWt").val('0.0');
										$('#rate').val('0.0');
										$('#qty').val('0.0');

										
										
										if (data == -1) {
											
											$("#compInwardDtEntry").css('display', 'none');
											$("#compInwardDtEntryId").val('');
											$("#rowId").html('');
											
											
										}
										$('#compInwardDtBtnId').removeAttr('disabled');
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						e.preventDefault(); //STOP default action
					});

	
	function goToCompInwardDtEntry() {
		
		$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/compInwardDt/addRights.html' />?pInvNo='+ $('#invNo').val(),
				type : 'GET',
				success : function(data) {
					
					if(data == 1){
					
					$("#compInwardDtEntry").css('display', 'block');
					
				/* 	$('#purity\\.id').attr('disabled',false);
					$('#color\\.id').attr('disabled',false);
					$('#department\\.id').attr('disabled',false);
					$('#metal\\.id').attr('disabled',false);
					
			 		var vParty = $('#party\\.id').val();
					var vInwardType = $('#inwardType\\.id').val();
					
					$('form#compInwardDtt input[type="text"],textarea, select').val('');
					$('#compInwardDtEntryId').val('');
					
					$('#party\\.id').val(vParty);
					$('#inwardType\\.id').val(vInwardType);
					
					
					
					$("#invWt").val('0.0');
					$("#metalWt").val('0.0');
					$('#rate').val('0.0');
					$('#qty').val('0.0');
					$("#metal\\.id").focus();*/
					
					 
					$.ajax({
						url : "/jewels/manufacturing/transactions/compInwardDt/edit/"+ 0 + ".html",
						type : 'GET',
						success : function(data) {
							$("#compInwardDtEntry").css('display', 'block');
							$("#rowId").html(data);
							$("#metal\\.id").focus();
							
							
							$('html, body').animate({
								scrollTop : $("#compInwardDtEntry").offset().top
							}, 50);
						}
					});
					
								}else{
						displayMsg(event, this, 'You can not Add record In BackDate Invoice, Please contact Administrator');
					}

				}
			});  
		
	}
	
	function editCompIDt(dtId) {
		$.ajax({
			url : "/jewels/manufacturing/transactions/compInwardDt/editRights.html?dtId="+$("#id").val(),
			type : 'GET',
			success : function(data) {
				
				if(data === '1'){
					
					$.ajax({
						url : "/jewels/manufacturing/transactions/compInwardDt/edit/"+ dtId + ".html",
						type : 'GET',
						success : function(data) {
							$("#compInwardDtEntry").css('display', 'block');
							$("#rowId").html(data);
							$("#metal\\.id").focus();
							$('html, body').animate({
								scrollTop : $("#compInwardDtEntry").offset().top
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
		
	
		
		var metalWt = $('#metalWt').val();
		var qty = $('#qty').val();
		var rate = $('#rate').val();
		var invWt =$('#invWt').val();
		var defRate = rate * 1;
		var defMetalWt = metalWt *1;
		var defInvWt =invWt*1;
		
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

	
	
	
	
	function  popCompInwardDtCancelBtn() {
	
		$('form#compInwardDtt #perGramRate1').prop("checked", false );
		$('#compInwardDtEntry').css('display','none');
		$("#rowId").html('');
		
	}
	

		
		$('#compInvDtId').bootstrapTable({})
		.on(
				'load-success.bs.table',
				function(e, data) {
					var data = JSON.stringify($("#compInvDtId").bootstrapTable('getData'));

					
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
		
	
	
	function deleteCompInwardDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteCompInwardDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}
	
	function deleteCompInwardDtData(dtId){
		
	$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/compInwardDt/delete/'+dtId+'.html',
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


	function compInwReport(val){
		
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

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

