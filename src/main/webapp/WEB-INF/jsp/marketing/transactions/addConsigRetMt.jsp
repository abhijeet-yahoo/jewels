<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalConsigIssPickup.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalConsigRowMetalPickup.jsp"%>



<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script> 




<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>



<div id="consigRetDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Consignment Return</span>
			</label>
			<div class="text-right">
			<!--  <button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="consigRetMtExcelPreviewBtn" onClick="javascript:consigRetReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			 
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="consigMtPreviewBtn" onClick="javascript:consigReport(2);">
			 <span class="fa fa-file-pdf"></span>Preview</button>
			  -->
			
			<input type="button" value="Lock" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info" id="lockInvoiceBtn" onClick="javascript:popLockInvoice();" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="consigRetListingBtnId"
					href="/jewels/marketing/transactions/consigRetMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="consigRetMtSubmitBtn"  onclick="javascript:consigRetMtSave()" />
			
			</div>

		</div>

	</div>
	
	<div class="form-group">
		<form:form commandName="consigRetMt" id="consigRetMtFormId"
			action="/jewels/marketing/transactions/consigRetMt/add.html"
			cssClass="form-horizontal consigRetMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success"> ${action}
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
						<label for="inputLabel3" class=".col-lg-2 text-right">Party:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Location:</label>
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
						<form:input path="invDate" cssClass="form-control" disabled="true"/>
						<form:errors path="invDate" />
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="party.id" class="form-control" required="true" onchange="javascript:showHideSalesman();">
							<form:option value="" label=" Select Party " />
							<form:options items="${allPartyMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="location.id" class="form-control" required="true">
							<form:option value="" label=" Select Location " />
							<form:options items="${locationMap}" />
						</form:select>
					</div>
					
					
				
				
				</div>
			</div>

			<div class="row">&nbsp;</div>
			
				<div class="row">
				<div class="col-xs-12">
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Description :</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Transport Mode:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Salesman:</label>
					</div>
					
				</div>
			</div>
			
				<div class="row">
				<div class="col-xs-12">
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="hsnMast.id" class="form-control">
							<form:option value="" label=" Select Description " />
							<form:options items="${hsnMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="modeOfTransport.id" class="form-control">
							<form:option value="" label=" Select Mode Of Transport " />
							<form:options items="${transportMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="employee.id" class="form-control" required="true">
							<form:option value="" label=" Select Salesman " />
							<form:options items="${salesMap}" />
						</form:select>
					</div>
					
				</div>
			</div>
				
	
			<div class="row">&nbsp;</div>
		
			<div class="row">
			<div class="col-xs-12">
			<div class="col-lg-1 col-sm-1"
								style="font-size: smaller; font-weight: bold;">
								<!-- <a onclick="javascript:popConsigMetalRate()">Metal Rates</a> -->
							</div>
			
				<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="srNo"/>
					<input type="hidden" id="pPartyIds" name="pPartyIds" />
				<input type="hidden" id="pLocationIds" name="pLocationIds" />
				<input type="hidden" id="pEmployeeIds" name="pEmployeeIds" />
				<input type="hidden" id="vTranDate" name="vTranDate" /></td>
				
				</div>
			</div>
		</form:form>
		
		 <!-- Download Common Pdf  Report -->
		 
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




<!-- 	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     TabPanel UI                 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< -->


		
	<div role="tabpanel">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="nav-item active">
					<a href="#mainScreen"
						aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
					
					
					<li role="presentation" class="nav-item">
					<a href="#consigRetRmMetalDtt" id="consigRetRmMetalDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Metal Receive</a></li>
						
					<li role="presentation" class="nav-item">
					<a href="#consigRetRmStnDtt" id="consigRetRmStnDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Stone Receive</a></li>
		
					<li role="presentation" class="nav-item">
					<a href="#consigRetRmCompDtt" id="consigRetRmCompDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Component Receive</a></li>
		
	
				</ul>
		
				<!-- Tab panes -->
				<div class="tab-content">
		 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
						<%@include file="addConsigRetDt.jsp"%>
					</div>
				 
					 <div role="tabpanel" class="tab-pane" id="consigRetRmMetalDtt">
						<%@include file="addConsigRetRmMetalDt.jsp"%>
					</div>
					
						<div role="tabpanel" class="tab-pane" id="consigRetRmStnDtt">
						<%@include file="addConsigRetRmStnDt.jsp"%>
					</div>
					
					<div role="tabpanel" class="tab-pane" id="consigRetRmCompDtt">
						<%@include file="addConsigRetRmCompDt.jsp"%>
					</div>	
					
				</div>
			
					
	</div>


</div>







<script type="text/javascript">



var canViewFlag=false;
var disableFlg=false;
var mtid;
var mode;
	$(document)
			.ready(
					function(e) {

						
						
						
//$('select').chosen();

$('#consigRetDivId #party\\.id').chosen();
$('#consigRetDivId #location\\.id').chosen();
$('#consigRetDivId #hsnMast\\.id').chosen();
$('#consigRetDivId #modeOfTransport\\.id').chosen();
$('#consigRetDivId #employee\\.id').chosen();
					
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
						
						
			

						$("#invDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
						setTimeout(function(){
							showHideSalesman();
							}, 100);

						if (window.location.href.indexOf('edit') != -1) {
							$("#vTranDate").val($("#invDate").val());
							$("#consigRetDtDivId").css('display', 'block');
							mtid="${mtid}";
							mode ="${model}";
							
						//	popConsigRetDt();

							 if(process('${currentDate}') > process($('#invDate').val())){
								 popConsigRetDt(true);
									
									disableFlg = true;
									
									$('#consigRetDivId .btn').hide();
									$('#consigRetDtDivId .btn').hide();
									$('#consigRetDivId').find('input, textarea, select').attr('disabled','disabled');
									$('#consigRetDtDivId :input').attr('disabled',true);
									$('#consigRetListingBtnId').show();
									$('#consigRetDtSummaryBtn').show();
								
									$('#consigRetAddRowStnBtnId').hide();
									$('#consigRetPickupRowStnBtnId').hide();
									$('#consigRetAddRowMtlBtnId').hide();
									$('#consigRetPickupRowMtlBtnId').hide();
									$('#consigRetAddRowCompBtnId').hide();
									$('#consigRetPickupRowCompBtnId').hide();
									$('#consigRetDtPickupBtn').hide();
									
									$('#barcodeTxtId').attr('readonly','readonly');
									
								}else{
									disableFlg = false;
									popConsigRetDt(false);
									} 
							
							
						}
						else{
							$("#vTranDate").val('${currentDate}');	
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					
							
						}


					});


	function process(date){
		   var parts = date.split("/");
		   return new Date(parts[2], parts[1] - 1, parts[0]);
		}


	function consigRetMtSave() {
		
		if($(".consigRetMtForm").valid()){
			$("form#consigRetMtFormId").submit();
			
		}
		
	}


	  $(document)
		.on(
			'submit',
			'form#consigRetMtFormId',
			 function(e){
				 $("#consigRetMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});


		var consigDtTableRow;
		var consigDtLockStatus = 'null';
		var finalPrice = "";
		 $('#consigRetDtTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					consigDtTableRow = $element.attr('data-index');
					
					$('#consigRetDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
					
					var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
					consigDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
					finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
					
					$('#hideOnPageLoad').css('display','block');
				
						
					popConsigRetMetal();
					popConsigRetStoneDetails();
					popConsigRetComponentDetails();
				
					popConsigRetLabDetails(); 
					
				
					
				});


			function popConsigRetDt(flg) {
				$("#consigRetDtTblId")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/marketing/transactions/consigRetDt/listing.html?mtId="+mtid+"&disableFlg="+flg,
								});
			}


			function popConsigRetMetal()
			{
				$("#consigRetMetalTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigRetMetalDt/listing.html?dtId="+$('#consigRetDtPKId').val(),
						});	
			}


			function popConsigRetStoneDetails(){
				
				$("#consigRetStnDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigRetStnDt/listing.html?dtId="+$('#consigRetDtPKId').val()+"&disableFlg="+disableFlg,
						});	
				
			}

			
			function popConsigRetComponentDetails(){
				
				$("#consigRetCompDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigRetCompDt/listing.html?dtId="+$('#consigRetDtPKId').val()+"&disableFlg="+disableFlg,
						});	
				
			}
			
			function popConsigRetLabDetails(){
				$("#consigRetLabDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigRetLabDt/listing.html?dtId="+$('#consigRetDtPKId').val()+"&disableFlg="+disableFlg,
						});	
			}
			


			function consigIssPickup(){

				$('#myConsigModal').modal('show');

				setTimeout(function(){
					popConsigPickList();
					
					$('#consigMtTblId').bootstrapTable('resetView');
				}, 300);
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

			function lossPercFormatter(value, row, index){
				
				var tempId = 'lossPercval' + Number(index);

				var vId = row.id;
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateLossPerc(this,'+vId+')"  />';
			}

			

			
			function mianMetalFormatter(value) {
				var booleanValue;
				if (typeof (value) === "boolean") {
					booleanValue = (value);
				} else {
					booleanValue = (value == 'true');
				}
				
				var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
				return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
			}


			$('#consigRetStnDtTabId').bootstrapTable({})
			.on(
					'load-success.bs.table',
					function(e, data) {
						var data = JSON.stringify($("#consigRetStnDtTabId").bootstrapTable('getData'));

						
						var vStones = 0.0;
						var vCarat = 0.0;
			
						$.each(JSON.parse(data), function(idx, obj) {
							
							
							vStones		+= Number(obj.stone);
							vCarat		+= Number(obj.carat);
							
							
						});
						
						$('#vTotalStones').val(" " + vStones);
						$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
						
					});


	

			function deleteConsigRetDt(e,dtId){
				displayDlg(
						e,
						'javascript:deleteDt('+dtId+');',
						'Delete',
						'Do you want to Delete  ?',
						'Continue');

				}


			function deleteDt(dtId){
				
				$("#modalDialog").modal("hide");
								
								 $.ajax({
										url : "/jewels/marketing/transactions/consigRetDt/delete/"+ dtId + ".html",
										type : 'GET',
										success : function(data) {
											if(data === "1"){
												displayInfoMsg(event, this,'Delete sucessfully !');
												popConsigRetDt(disableFlg);
											}else{
												displayMsg(event, this, data);
											}
										}
									}); 
				
			
			}



			function displayDtSummary(){
				
				$('#myConsigDtSummaryModal').modal('show');
				 $('#myConsigDtSummaryModal').on('shown.bs.modal', function () {
					 popConsigSummary();
			        });
			

				}

			function popConsigSummary(){

				$.ajax({
					url:"/jewels/marketing/transactions/consigMt/dtSummary.html?mtId="+mtid,
					type :'GET',
					dataType:'JSON',
					success: function(data){
						
						if(data !== "-1"){
							
							$.each(data,function(k,v){
								$('#consigDtSummaryDivId  #'+k).val(v);
							});
							
						}else{
							displayMsg(this,null,'Error Contact Admin');
						}
						
					}	
				})
			}
	


			function consigReport(val){
					
				$
				.ajax({
					url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=consigRet"+"&opt="+val,
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


		

			var dtdata='';
			
			$('#consigRetDtTblId').bootstrapTable({}).on('load-success.bs.table',
					function(e, data) {
				   
				dtdata = $("#consigRetDtTblId").bootstrapTable('getData').length;

				
		
					
				 if(dtdata >0){

						$('#pPartyIds').val($('#consigRetDivId #party\\.id').val());
						$('#pLocationIds').val($('#consigRetDivId #location\\.id').val());
						$('#pEmployeeIds').val($('#consigRetDivId #employee\\.id').val());
						
						$('#consigRetDivId #party\\.id').attr("disabled","disabled").trigger('chosen:updated');
						$('#consigRetDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
						$('#consigRetDivId #employee\\.id').attr("disabled","disabled").trigger('chosen:updated');
						
						}else{
							$('#consigRetDivId #party\\.id').removeAttr('disabled').trigger('chosen:updated');
							$('#consigRetDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
							$('#consigRetDivId #employee\\.id').removeAttr('disabled').trigger('chosen:updated');
				
							
						}
					
				  })

			
			function showHideSalesman(){
				$('#pEmployeeIds').val($('#consigRetDivId #employee\\.id').val());
			
				if($('#party\\.id :selected').text() != "Sample"){
					$('#consigRetDivId #employee\\.id').attr("disabled","disabled").trigger('chosen:updated');					
					}else{
						$('#consigRetDivId #employee\\.id').removeAttr('disabled').trigger('chosen:updated');
							
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

<script src="<spring:url value='/js/bootstrap/lodash.min.js' />"></script>

