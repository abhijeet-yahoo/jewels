<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalPackingListPickup.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalLocationWisePickup.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalConsigDtSummary.jsp"%>



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



<div id="consigDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Consignment Issue </span>
			</label>
			<div class="text-right">
			 <!-- <button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="consigMtExcelPreviewBtn" onClick="javascript:consigReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			 
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="consigMtPreviewBtn" onClick="javascript:consigReport(2);">
			 <span class="fa fa-file-pdf"></span>Preview</button> -->
			 
			 <div class="btn-group">
				
			  <button type="button" class="btn btn-xs btn-success" style="font-size: 14px width: 3cm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   Excel
				  </button>
				  
				   <div class="dropdown-menu">
  
    
				    <a  class="dropdown-item" href="#" id="consigMtExcelPreviewBtn1"  onClick="javascript:consigReport(1);">Invoice  </a>
				    <div class="dropdown-divider"></div>
				    
				     <a id="saleMtPreviewBtn3" class="dropdown-item" id="consigMtExcelPreviewBtn3"  onClick="javascript:consigLooseMetalReport(1);">Loose Metal Invoice</a>
				    <div class="dropdown-divider"></div>
				    
				    <a id="saleMtPreviewBtn2" class="dropdown-item" id="consigMtExcelPreviewBtn2"  onClick="javascript:consigLooseStnReport(1);">Loose Stone Invoice</a>
				    <div class="dropdown-divider"></div>
				  </div>
				  
				</div> 
				
				
				<div class="btn-group">
				
			  <button type="button" class="btn btn-xs btn-success" style="font-size: 14px width: 3cm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				   Preview
				  </button>
				  
				   <div class="dropdown-menu">
  
    
				    <a  class="dropdown-item" href="#" id="consigMtPreviewBtn1"  onClick="javascript:consigReport(2);">Invoice  </a>
				    <div class="dropdown-divider"></div>
				    
				     <a id="saleMtPreviewBtn3" class="dropdown-item"  onClick="javascript:consigLooseMetalReport(2);">Loose Metal Invoice</a>
				    <div class="dropdown-divider"></div>
				    
				    <a id="consigMtPreviewBtn2" class="dropdown-item"  onClick="javascript:consigLooseStnReport(2);">Loose Stone Invoice</a>
				    <div class="dropdown-divider"></div>
				  </div>
				  
				</div>  
			 
			
			<!-- <input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="consigMtPreviewBtn" onClick="javascript:consigReport();" /> -->
			
			<!-- <input type="button" value="Lock Consignment" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info" id="lockInvoiceBtn" onClick="javascript:popLockInvoice();" /> -->
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="consigListingBtnId"
					href="/jewels/marketing/transactions/consigMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="consigMtSubmitBtn"  onclick="javascript:consigMtSave()" />
			
			</div>

		</div>

	</div>
	
	<div class="form-group">
		<form:form commandName="consigMt" id="consigMtFormId"
			action="/jewels/marketing/transactions/consigMt/add.html"
			cssClass="form-horizontal consigMtForm">

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
						<form:input path="invDate" cssClass="form-control"  disabled="true"/>
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
								<a onclick="javascript:popConsigMetalRate()">Metal Rates</a>
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
	
</div>  <!-- End of Panel primary -->

</div>


															<!-- TabPanel to tab pane -->
	
	
	<div role="tabpanel">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="nav-item active">
					<a href="#mainScreen"
						aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
					
					
					<li role="presentation" class="nav-item">
					<a href="#consigRmMetalDtt" id="consigRmMetalDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Metal Issue</a></li>
		
						<li role="presentation" class="nav-item">
					<a href="#consigRmCompDtt" id="consigRmCompDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Component Issue</a></li>
		
						
					<li role="presentation" class="nav-item">
					<a href="#consigRmStnDtt" id="consigRmStnDtId"
						aria-controls="profile" role="tab" data-toggle="tab">Stone Issue</a></li> 
		
				</ul>
		
				<!-- Tab panes -->
				<div class="tab-content">
		 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
		 			
		 			<%@include file="addConsigDt.jsp"%>
		 					
					</div>
				 
				 	<div role="tabpanel" class="tab-pane" id="consigRmMetalDtt">
						 <%@include file="addConsigRmMetalDt.jsp"%>
					</div>
					
					<div role="tabpanel" class="tab-pane" id="consigRmCompDtt">
						<%@include file="addConsigRmCompDt.jsp"%>
					</div>	
					
						<div role="tabpanel" class="tab-pane" id="consigRmStnDtt">
						<%@include file="addConsigRmStnDt.jsp"%>
					</div> 
					
					
				</div>
			
					
	</div> <!-- End of tab Pane Div -->

<script type="text/javascript">



canViewFlag=false;
var disableFlg = false;
var mtid;
var mode;
	$(document)
			.ready(
					function(e) {

						
						
						
//$('select').chosen();

$('#consigDivId #party\\.id').chosen();
$('#consigDivId #location\\.id').chosen();
$('#consigDivId #hsnMast\\.id').chosen();
$('#consigDivId #modeOfTransport\\.id').chosen();
$('#consigDivId #employee\\.id').chosen();
					
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
						
						
						
						$(".consigMtForm")
								.validate(
										{
											rules : {
												invDate : {
													required : true,
												},
												
												'party.id' : {
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
							$("#consigDtDivId").css('display', 'block');
							mtid="${mtid}";
							mode ="${model}";
							
						//	popConsigDt();

							
							
							
						}
						else{

							$("#vTranDate").val('${currentDate}');	
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					
							
						}
						setTimeout(function(){
						showHideSalesman();
						}, 100);
						if(process('${currentDate}') > process($('#invDate').val())){
							popConsigDt(true);
							
							disableFlg = true;
							
							
							$('#consigListingBtnId').show();
							$('#consigSummaryBtnId').show();
							$('#consigMtExcelPreviewBtn1').show();
							$('#consigMtExcelPreviewBtn2').show();
							$('#consigMtPreviewBtn1').show();
							$('#consigMtPreviewBtn2').show();
							$('#consigRowCompBtnId').hide();
							$('#consigRowStnBtnId').hide();
							$('#consigRowMetalBtnId').hide();
							$('#consigMtSubmitBtn').hide();
							$('#consigMtpickUpbtn').hide();
							
							
							$('#barcodeTxtId').attr('readonly','readonly');
							
						}else{
							disableFlg = false;
							popConsigDt(false);
							}

					

					});

	function process(date){
		   var parts = date.split("/");
		   return new Date(parts[2], parts[1] - 1, parts[0]);
		}

	function consigMtSave() {
		
		if($(".consigMtForm").valid()){
			$("form#consigMtFormId").submit();
			
		}
		
	}


	
			
		
			function showHideSalesman(){

				$('#pEmployeeIds').val($('#consigDivId #employee\\.id').val());
			
				if($('#party\\.id :selected').text() != "Sample"){

					$('#consigDivId #employee\\.id').attr("disabled","disabled").trigger('chosen:updated');		
					}else{						
						$('#consigDivId #employee\\.id').removeAttr('disabled').trigger('chosen:updated');							
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

