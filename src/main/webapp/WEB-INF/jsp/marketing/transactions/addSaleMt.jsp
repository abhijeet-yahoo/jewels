<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<%@ include file="/WEB-INF/jsp/common/modalPackingListPickup.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalLocationWisePickup.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalConsigDtSummary.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalConsigPickup.jsp"%>




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




<div id="saleDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Sales Invoice</span>
			</label>
			<div class="text-right">
			
			<!-- <button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="saleMtExcelPreviewBtn" onClick="javascript:salesReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button> -->
			 
			 
			 <div class="btn-group">
				
			  <button type="button" class="btn btn-xs btn-success" style="font-size: 14px width: 3cm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   Excel
				  </button>
				  
				   <div class="dropdown-menu">
  
    
				    <a  class="dropdown-item" href="#" id="saleMtExcelPreviewBtn1"  onClick="javascript:salesReport(1);">Invoice  </a>
				    <div class="dropdown-divider"></div>
				    
				    <a  class="dropdown-item" href="#" id="saleMtPlainExcelPreviewBtn1"  onClick="javascript:salesPlainGoldReport(1);">Plain Gold Invoice  </a>
				    <div class="dropdown-divider"></div>
				    
				    <a class="dropdown-item" id="saleMtExcelPreviewBtn2"  onClick="javascript:salesLooseStnReport(1);">Loose Stone Invoice</a>
				    <div class="dropdown-divider"></div>
				    
				    <a class="dropdown-item" id="saleMtExcelPreviewBtn3"  onClick="javascript:salesLooseMetalReport(1);">Loose Metal Invoice</a>
				    <div class="dropdown-divider"></div>
				    
				  </div>
				  
				</div> 
			
				<div class="btn-group">
				
			  <button type="button" class="btn btn-xs btn-success" style="font-size: 14px width: 3cm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   Preview
				  </button>
				  
				   <div class="dropdown-menu">
  
    
				    <a  class="dropdown-item" href="#" id="saleMtPreviewBtn"  onClick="javascript:salesReport(2);">Invoice  </a>
				    <div class="dropdown-divider"></div>
				    
				     <a  class="dropdown-item" href="#" id="saleMtPlainPreviewBtn1"  onClick="javascript:salesPlainGoldReport(2);">Plain Gold Invoice  </a>
				    <div class="dropdown-divider"></div>
				    
				    <a id="saleMtPreviewBtn2" class="dropdown-item"  onClick="javascript:salesLooseStnReport(2);">Loose Stone Invoice</a>
				    <div class="dropdown-divider"></div>
				    
				     <a id="saleMtPreviewBtn3" class="dropdown-item"  onClick="javascript:salesLooseMetalReport(2);">Loose Metal Invoice</a>
				    <div class="dropdown-divider"></div>
				    
				  </div>
				  
				</div>  
			
			<!-- <button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="saleMtPreviewBtn"  onClick="javascript:salesReport(2);">
			 <span class="fa fa-file-pdf"></span>Preview</button> -->
			
			<!-- <input type="button" value="Lock Sale" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info" id="lockInvoiceBtn" onClick="javascript:popLockInvoice();" /> -->
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="saleMtListingBtnId"
					href="/jewels/marketing/transactions/saleMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="saleMtSubmitBtn"  onclick="javascript:saleMtSave()" />
			
			</div>

		</div>

	</div>
	
	
		<div class="form-group">
		<form:form commandName="saleMt" id="saleMtFormId"
			action="/jewels/marketing/transactions/saleMt/add.html"
			cssClass="form-horizontal saleMtForm">

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
						<form:input path="invNo" cssClass="form-control" autocomplete="off" />
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="invDate" cssClass="form-control"  disabled="true"/>
						<form:errors path="invDate" />
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="party.id" class="form-control" required="true">
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
			
				<div class="row">
				<div class="col-xs-12">
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Description :</label>
					</div>
					
						<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Transport Mode:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Payment Term:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Exchange Rate:</label>
					</div>
					
				</div>
			</div>
			
				<div class="row">
				<div class="col-xs-12">
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="hsnMast.id" class="form-control" onchange="javascript:applyGst();" >
							<form:option value="" label=" Select Description" />
							<form:options items="${hsnMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="modeOfTransport.id" class="form-control" required="true">
							<form:option value="" label=" Select Mode Of Transport " />
							<form:options items="${transportMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="paymentTerm.id" class="form-control">
							<form:option value="" label=" Select Payment Term  " />
							<form:options items="${paymentTermMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:input path="exchangeRate" cssClass="form-control" />
						<form:errors path="exchangeRate" />
					</div>
					
				</div>
			</div>
				
	
			<div class="row">&nbsp;</div>
		
			<div class="row">
			<div class="col-xs-12">
			
			
				<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="srNo"/>
				<input type="hidden" id="pPartyIds" name="pPartyIds" />
				<input type="hidden" id="pLocationIds" name="pLocationIds" />
				<input type="hidden" id="vTranDate" name="vTranDate" />
				<input type="hidden" id="vOtherCharges" name="vOtherCharges" />
				<input type="hidden" id="vFinalPrice" name="vFinalPrice" />
				<input type="hidden" id="pIgst" name="pIgst" />
				<input type="hidden" id="pSgst" name="pSgst" />
				<input type="hidden" id="pCgst" name="pCgst" />
				</div>
			</div>
		</form:form>
		
		
		 <!-- Download Common Pdf Report -->
		 
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
	
	
	
	
	
	
	
	




	<div role="tabpanel">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="nav-item active">
					<a href="#mainScreen"
						aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
							
					<li role="presentation" class="nav-item">
					<a href="#saleRmDtt" id="saleRmMetalDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Metal Issue</a></li>
		
						<li role="presentation" class="nav-item">
					<a href="#saleCompRmDtt" id="saleCompRmDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Component Issue</a></li>
		
						
					<li role="presentation" class="nav-item">
					<a href="#saleStnRmDtt" id="saleStnRmDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Stone Issue</a></li> 
		
				</ul>
		
				<!-- Tab panes -->
				<div class="tab-content">
		 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
		 			
						<%@include file="addSaleDt.jsp"%>
					</div>
				 
				 	<div role="tabpanel" class="tab-pane" id="saleRmDtt">
						<%@include file="addSaleRmMetalDt.jsp"%>
					</div>
					
					<div role="tabpanel" class="tab-pane" id="saleCompRmDtt">
						<%@include file="addSaleRmCompDt.jsp"%>
					</div>	
					
						<div role="tabpanel" class="tab-pane" id="saleStnRmDtt">
						<%@include file="addSaleRmStnDt.jsp"%>
					</div>
				</div>
					
		</div>


</div> <!-- End of Div panel -->





<script type="text/javascript">




var canViewFlag=false;
var disableFlg=false;
var mtid;
var mode;
var partyType;
var lookType;

	$(document)
			.ready(
					function(e) {

										
						
						
//$('select').chosen();

$('#saleDivId #party\\.id').chosen();
$('#saleDivId #location\\.id').chosen();
$('#saleDivId #hsnMast\\.id').chosen();
$('#saleDivId #modeOfTransport\\.id').chosen();
$('#saleDivId #paymentTerm\\.id').chosen();


						
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
						
						
						
						$(".saleMtForm")
								.validate(
										{
											rules : {
												invNo : {
													required : true,
													 minlength : 1, 
													remote : {
														url : "<spring:url value='/marketing/transactions/saleInvoiceAvailable.html' />",
														type : "get",
														data : {
															
															id : function() {
																return $("#id").val();
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

												'location.id' : {
													required : true,
												},
												
									
											},

											messages : {
												invNo :{
													remote :"Duplicate Invoice No"
												}
											}

										});
						
						
						
			

						$("#invDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
			

						if (window.location.href.indexOf('edit') != -1) {
							$("#vTranDate").val($("#invDate").val());
							
							$("#saleDtDivId").css('display', 'block');
							mtid="${mtid}";
						//	mode ="${model}";
						
							partyType = "${partyType}";
							lookType = "${lookType}";
							
							
							$('#vOtherCharges').val("${totalOtherCharges}");
							$('#vFinalPrice').val("${finalPrice}");
							
							$('#otherCharges').val("${totalOtherCharges}");
							$('#totFinalPrice').val("${finalPrice}");

							

							setTimeout(function(){
								$('#saleDtDivId #igst').val("${pIgst}");
								$('#saleDtDivId #sgst').val("${pSgst}");
								$('#saleDtDivId #cgst').val("${pCgst}");

								$('#pIgst').val("${pIgst}");
								$('#pSgst').val("${pSgst}");
								$('#pCgst').val("${pCgst}");
								}, 20);

						
							if("${lookType}" === "Local"){
							if("${partyType}" === "Local"){
								$("#cgstDivId").css("display","block");
								$("#sgstDivId").css("display","block");
								
								}else{
									
									$("#igstDivId").css("display","block");
									}
							}
								


								if(process('${currentDate}') > process($('#invDate').val())){
									popSaleDt(true);
								
									
									disableFlg = true;

									
								//	$('#saleDivId .btn').hide();
								//	$('#saleDtDivId .btn').hide();
								//	$('#saleDtDivId').find('input, textarea, select').attr('disabled','disabled');
								//	$('#saleDtDivId :input').attr('disabled',true);
									$('#saleMtListingBtnId').show();

									$('#saleApplyRatesBtnId').hide();
									$('#salePickupBtnId').hide();
									$('#saleMtSubmitBtn').hide();
									$('#saleRowCompBtnId').hide();
									$('#saleRowStnBtnId').hide();
									$('#salesRowMetalBtnId').hide();
									$('#barcodeTxtId').attr('readonly','readonly');
									
								}else{
									disableFlg = false;
									popSaleDt(false);
									} 

								popSaleRmStnDt();
								popSaleRmMetalDt();
							
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


	function saleMtSave() {
		
		if($(".saleMtForm").valid()){
			
			$("form#saleMtFormId").submit();
			
		}

	//	applyGst();
		
	}


	  $(document)
		.on(
			'submit',
			'form#saleMtFormId',
			 function(e){
				 $("#saleMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});


	 var saleDtTableRow;
		var saleDtLockStatus = 'null';
		var finalPrice = "";
		 $('#saleDtTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					saleDtTableRow = $element.attr('data-index');
					
					$('#saleDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
					
					var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
					saleDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
					finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
					
					$('#hideOnPageLoad').css('display','block');
				
						
					popSaleMetal();
					popSaleStoneDetails();
					popSaleComponentDetails();
				
					popSaleLabDetails(); 
					
				
					
				});


			function popSaleDt(disableFlg) {
				
				$("#saleDtTblId")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/marketing/transactions/saleDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg,
								});
			}


			function popSaleMetal()
			{
				$("#saleMetalTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleMetalDt/listing.html?saleDtId="+$('#saleDtPKId').val(),
						});	
			}


			function popSaleStoneDetails(){
				
				$("#saleStnDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleStnDt/listing.html?saleDtId="+$('#saleDtPKId').val()+"&disableFlg="+disableFlg,
						});	
				
			}

			
			function popSaleComponentDetails(){
				
				$("#saleCompDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleCompDt/listing.html?saleDtId="+$('#saleDtPKId').val()+"&disableFlg="+disableFlg,
						});	
				
			}
			
			function popSaleLabDetails(){
				$("#saleLabDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleLabDt/listing.html?saleDtId="+$('#saleDtPKId').val()+"&disableFlg="+disableFlg,
						});	
			}
			


			function packingListPickup(){

				$('#myConsigPickupModal').modal('show');

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

				  
				  
				  function salesReport(val){

					  $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 

					$
				.ajax({
					url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=sale"+"&opt="+val,
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



				  function salesLooseStnReport(val){

					  $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 

						$
						.ajax({
							url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=saleLooseStn"+"&opt="+val,
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

				  function salesLooseMetalReport(val){

					  $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
						$
						.ajax({
							url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=saleLooseMetal"+"&opt="+val,
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


					  function salesPlainGoldReport(val){

					  $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
						$
						.ajax({
							url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=salePlainGold"+"&opt="+val,
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
