<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<div id="fgIssDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" style="text-align: center;">
	
	<div>
		
		<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Fg Stock Issue </span>
			</label>
			
				<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="fbIssMtExcelPreviewBtn" onClick="javascript:fbIssListReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="packMtPreviewBtn" onClick="javascript:fbIssListReport(2);" />
			
			</div>
	</div>	
	</div>
	
	
	
	<div class="form-group">
		<form:form commandName="fgIssMt" id="fgIss"
			action="/jewels/manufacturing/transactions/fgIssMt/add.html"
			cssClass="form-horizontal fgIssMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success"> ${action}
							Successfully!</div>
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
						<label for="inputLabel3" class=".col-lg-2 text-right">Department:</label>
					</div>
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<form:input path="invNo" cssClass="form-control" autocomplete="off" readonly="true"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="invDate" cssClass="form-control" disabled="true" />
						<form:errors path="invDate" />
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="department.id" class="form-control" onchange="javascript:hideShowDtDiv();" required="true">
							<form:option value="" label=" Select Dept " />
							<form:options items="${deptMap}" />
						</form:select>
					</div>
				
				</div>
			</div>
	
			<div class="row">&nbsp;</div>
		
			<div class="row">
			<div class="col-xs-12">
				<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" id="fgIssSubmitBtn" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/fgIssMt.html">Listing</a>
					<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="invSrNo"/>
					<input type="hidden" id="pLocationIds" name="pLocationIds" />
						<input type="hidden" id="vTranDate" name="vTranDate" />
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


<div id="fgIssDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button" id="fgPickupBtnId"
							onclick="javascript:popFgStkModal();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add Fg Stock
						</a>
						
					
					
					</div>
					<div>
						<table id="fgIssDtId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-striped="true">
							<thead>
								<tr class="btn-primary">
								<!-- <th data-field="action2" data-align="center">Delete</th> -->
								<th data-field="barcode" data-align="left" data-sortable="true" data-filter-control="input">Barcode</th>
								<th data-field="party" data-align="left" data-sortable="true" data-filter-control="input">Party</th>
								<th data-field="orderNo" data-align="left" data-sortable="true" data-filter-control="input">Order</th>
								<th data-field="refNo" data-align="left" data-sortable="true" data-filter-control="input">Ref</th>
								<th data-field="bagNo" data-align="left" data-sortable="true" data-filter-control="input">Bag</th>
								<th data-field="styleNo" data-align="left" data-sortable="true" data-filter-control="input">Style No</th>
								<th data-field="qty" data-align="left" data-sortable="true">Qty</th>
								<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
								<th data-field="totCarat" data-align="left" data-sortable="true">Total Cts</th>
								<th data-field="totStone" data-align="left" data-sortable="true">Total Stn</th>
									
									
								</tr>
							</thead>
						</table>
					</div>
					
					
					
					
				</div>
				
				
				
			</div>
		</div>
		

		


		
	</div>


	

</div>

<script type="text/javascript">

canViewFlag=false;
var mtid;
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
						
						
						
						$(".fgIssMtForm")
								.validate(
										{
											rules : {
												invDate : {
													required : true,
												},
												
									
											},

											messages : {
												
											}

										});
						
						
						
			

						$("#invDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
			

						if (window.location.href.indexOf('edit') != -1) {
							$("#vTranDate").val($("#invDate").val());
							$("#fgIssDtDivId").css('display', 'block');
							mtid="${mtid}";
							popFgIssDt();


							if(process('${currentDate}') > process($('#invDate').val())){

								$('#fgIssSubmitBtn').hide();
								$('#fgPickupBtnId').hide();
								
							}
							
						}
						else{
							$("#vTranDate").val('${currentDate}');		
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					
							
						}

						

					});
	
	
	
	
	  $(document)
		.on(
			'submit',
			'form#fgIss',
			 function(e){
				 $("#fgIssSubmitBtn").prop("disabled", true).addClass("disabled");
			});
	
	
		function process(date){
			   var parts = date.split("/");
			   return new Date(parts[2], parts[1] - 1, parts[0]);
			}
	
	

	function popFgIssDt() {
		$("#fgIssDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/fgIssDt/listing.html?mtId="+mtid,
						});
	}

	

	function addeditStoneInwDt(dtId) {
		
		
		 $
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/stnInward/addeditRights.html' />?mtId='+$('#id').val()+"&dtId="+dtId,
			type : 'GET',
			success : function(data) {
				
				
				if(data === "1"){
					
					$.ajax({
						url : "/jewels/manufacturing/transactions/stoneInwardDt/addedit/"+ dtId + ".html",
						type : 'GET',
						success : function(data) {
							$("#stoneInwardDtEntry").css('display', 'block');
							$("#rowId").html(data);
							$('#stoneType\\.id').focus();
							$('#vSieve').val($('#sieve').val());
							
							$('#stoneInwardDtt #stoneType\\.id').chosen();
							$('#stoneInwardDtt #shape\\.id').chosen();
							$('#stoneInwardDtt #quality\\.id').chosen();
							$('#stoneInwardDtt #size').chosen();
							$('#stoneInwardDtt #subShape\\.id').chosen();
							
						

						}
					});
					
				}else if(data === "-2"){
					displayMsg(event, this, 'Can Not Edit Against Order Entry');
				}else if(data === "-3"){
					
					displayMsg(event, this, 'Stock Adjusted Can Not Edit');
					
				}else{
					displayMsg(event, this,'BackDate Entry Not Allow, Contact Administrator');
				}

			}
		});  
		
	}
	

	
		
	
	
	
	function deleteDt(dtId){
		
		
		$("#modalDialog").modal("hide");
		
		
		
		
		 $
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/stnInward/addeditRights.html' />?mtId='+$('#id').val()+"&dtId="+dtId,
				type : 'GET',
				success : function(data) {
					
					if(data === "1"){
						
						 $.ajax({
								url : "/jewels/manufacturing/transactions/stoneInwardDt/delete/"+ dtId + ".html",
								type : 'GET',
								success : function(data) {
									if(data === "1"){
										popStoneInwardDt();
									}
								}
							}); 
						
					}else if(data === "-2"){
						displayMsg(event, this, 'Can Not Delete Against Order Entry');
					}else if(data === "-3"){
						
						displayMsg(event, this, 'Stock Adjusted Can Not Delete');
						
					}else{
						displayMsg(event, this,'Can Not Delete BackDate Entry, Contact Administrator');
					}

				}
			}); 
		
		
	
	}
	
	function deleteStoneInwDt(e,dtId){

			
			displayDlg(
					e,
					'javascript:deleteDt('+dtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
				
  }
	
	
	
	function popFgStkModal(){

	 window.location.href = "/jewels/manufacturing/transactions/fgBagTransfer.html?mtid="+mtid;
		
	}
	
	function fbIssListReport(val){
		
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		$
		.ajax({
			url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=fgIss"+"&opt="+val,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
				$.unblockUI();
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



	var dtdata='';
	
	$('#fgIssDtId').bootstrapTable({}).on('load-success.bs.table',
			function(e, data) {
		   
		dtdata = $("#fgIssDtId").bootstrapTable('getData').length;
			
		 if(dtdata >0){

				$('#pLocationIds').val($('#fgIssDivId #department\\.id').val());
				$('#fgIssDivId #department\\.id').attr("disabled","disabled").trigger('chosen:updated');
				
				}else{
					$('#fgIssDivId #department\\.id').removeAttr('disabled').trigger('chosen:updated');
		
					
				}
			
		  })
		  
		  
		  function hideShowDtDiv(){
		$("#fgIssDtDivId").css('display', 'none');
		  }
	
	
	
</script>


<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

