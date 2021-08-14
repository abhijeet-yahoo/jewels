<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalSaleIssPickup.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalSaleRetDt.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>
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




<div id="saleRetDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Sales Return</span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="saleRetMtExcelPreviewBtn" onClick="javascript:salesRetReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="saleRetMtPreviewBtn" onClick="javascript:salesRetReport(2);">
			 <span class="fa fa-file-pdf"></span>Preview</button> 
			
			<input type="button" value="Lock Sale" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info" id="lockInvoiceBtn" onClick="javascript:popLockInvoice();" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="saleRetListingBtnId"
					href="/jewels/marketing/transactions/saleRetMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="saleRetMtSubmitBtn"  onclick="javascript:saleRetMtSave()" />
			
			</div>

		</div>

	</div>
	
	
		<div class="form-group">
		<form:form commandName="saleRetMt" id="saleRetMtFormId"
			action="/jewels/marketing/transactions/saleRetMt/add.html"
			cssClass="form-horizontal saleRetMtForm">

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
						<label for="inputLabel3" class=".col-lg-2 text-right">Exchange Rate:</label>
					</div>
					
				</div>
			</div>
			
				<div class="row">
				<div class="col-xs-12">
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="hsnMast.id" class="form-control" onchange="javascript:applyGst();">
							<form:option value="" label=" Select Description" />
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
					<a href="#saleRetRmMetalDtt" id="saleRetRmMetalDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Metal Receive</a></li>
						
					 <li role="presentation" class="nav-item">
					<a href="#saleRetRmStnDtt" id="saleRetRmStnDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Stone Receive</a></li>
		
					<li role="presentation" class="nav-item">
					<a href="#saleRetRmCompDtt" id="saleRetRmCompDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Component Receive</a></li>
		
	
				</ul>
		
				<!-- Tab panes -->
				<div class="tab-content">
		 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
						<%@include file="addSaleRetDt.jsp"%>
					</div>
				  
					 <div role="tabpanel" class="tab-pane" id="saleRetRmMetalDtt">
						<%@include file="addSaleRetRmMetalDt.jsp"%>
					</div>
					
					<div role="tabpanel" class="tab-pane" id="saleRetRmStnDtt">
						<%@include file="addSaleRetRmStnDt.jsp"%>
					</div>
					
					<div role="tabpanel" class="tab-pane" id="saleRetRmCompDtt">
						<%@include file="addSaleRetRmCompDt.jsp"%>
					</div>	
					
				</div>
			
					
	</div>
	






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

$('#saleRetDivId #party\\.id').chosen();
$('#saleRetDivId #location\\.id').chosen();
$('#saleRetDivId #hsnMast\\.id').chosen();
$('#saleRetDivId #modeOfTransport\\.id').chosen();


						
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
						
						
						
						$(".saleRetMtForm")
								.validate(
										{
											rules : {
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
												
											}

										});
						
						
						
			

						$("#invDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
			

						if (window.location.href.indexOf('edit') != -1) {
							$("#vTranDate").val($("#invDate").val());
							$("#saleRetDtDivId").css('display', 'block');
							mtid="${mtid}";
						//	mode ="${model}";
						
							partyType="${partyType}";
							lookType = "${lookType}";
							
						//	popSaleRetDt();

							$('#vOtherCharges').val("${totalOtherCharges}");
							$('#vFinalPrice').val("${finalPrice}");

							$('#otherCharges').val("${totalOtherCharges}");
							$('#totFinalPrice').val("${finalPrice}");


							setTimeout(function(){
								$('#saleRetDtDivId #igst').val("${pIgst}");
								$('#saleRetDtDivId #sgst').val("${pSgst}");
								$('#saleRetDtDivId #cgst').val("${pCgst}");

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
								 popSaleRetDt(true);
									
									disableFlg = true;
									
									$('#saleRetDivId .btn').hide();
									$('#saleRetDtDivId .btn').hide();
								//	$('#saleRetDivId').find('input, textarea, select').attr('disabled','disabled');
								//	$('#saleRetDtDivId :input').attr('disabled',true);
									$('#saleRetListingBtnId').show();
									$('#saleRetMtPreviewBtn').show();
									
									$('#saleRetStnAddId').hide();
									$('#saleRetMetalAddId').hide();
									$('#saleRetCompAddId').hide();
									$('#saleRetMetalPickupId').hide();
									
									
								}else{
									disableFlg = false;
									popSaleRetDt(false);
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


	function saleRetMtSave() {
		
		if($(".saleRetMtForm").valid()){
			$("form#saleRetMtFormId").submit();
			
		}

	//	applyGst();
		
	}


	  $(document)
		.on(
			'submit',
			'form#saleRetMtFormId',
			 function(e){
				 $("#saleRetMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});






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

			

		
				  
				  function salesRetReport(val){


					$
				.ajax({
					url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+$('#id').val()+"&tranType=saleRet"+"&opt="+val,
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


			  function saleIssPickup(){

					$('#mySaleIssModal').modal('show');
					 $('#mySaleIssModal').on('shown.bs.modal', function () {
						 popSalePickList();
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




		
		
		


			