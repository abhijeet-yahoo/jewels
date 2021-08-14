<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalPackDtRates.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalQualityRateChng.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalLocationWisePickup.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalPackDtSummary.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalPackLabourRate.jsp"%>


<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<div id="fgIssDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Packing List </span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="packMtExcelPreviewBtn" onClick="javascript:packingListReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="packMtPreviewBtn" onClick="javascript:packingListReport(2);" />
			
			
			<input type="button" value="Lock Packing List" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info" id="lockInvoiceBtn" onClick="javascript:popLockInvoice();" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="packingListingBtn"
					href="/jewels/marketing/transactions/packList.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="packMtSubmitBtn"  onclick="javascript:packListSave()" />
			

			
			
			
				<!-- <a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					id="costingExcelId" href="#"></a> -->
			</div>



		</div>

	</div>
	
	<div class="form-group">
		<form:form commandName="packMt" id="packFormId"
			action="/jewels/marketing/transactions/packList/add.html"
			cssClass="form-horizontal packMtForm">

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
						<form:select path="party.id" class="form-control" required="true" onchange="javascript:hideShowDtDiv()" disabled="false" >
							<form:option value="" label=" Select Party " />
							<form:options items="${allPartyMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="location.id" class="form-control" required="true" onchange="javascript:hideShowDtDiv()"  disabled="false">
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
						<label for="inputLabel3" class=".col-lg-2 text-right">Exchange Rate :</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Pointer Rate :</label>
					</div>
				</div>
			</div>
			
				<div class="row">
				<div class="col-xs-12">
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="hsnMast.id" class="form-control"  disabled="false">
							<form:option value="" label=" Select Description " />
							<form:options items="${hsnMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:input path="exchangeRate" cssClass="form-control" />
						<form:errors path="exchangeRate" />
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:input path="pointerRate" cssClass="form-control" />
						<form:errors path="pointerRate" />
					</div>
					
				</div>
			</div>
				
	
			<div class="row">&nbsp;</div>
		
			<div class="row">
			<div class="col-xs-12">
			<div class="col-lg-1 col-sm-1"
								style="font-size: smaller; font-weight: bold;">
								<a onclick="javascript:popPackMetalRate()">Metal Rates</a>
							</div>
							
				<div class="col-lg-1 col-sm-1"
								style="font-size: smaller; font-weight: bold;">
								<a onclick="javascript:popPackQualityRate()">Quality Rate Change</a>
							</div>			
							
				<div class="col-lg-1 col-sm-1"
					style="font-size: smaller; font-weight: bold;">
					<a onclick="javascript:popPackLabourRate()">Labour Rate</a>
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<label> <form:checkbox path="in999"  onchange="javascript:update999()"/> <b>As Per 999</b></label>
					</div>
					
			<!-- 	<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" id="packMtSubmitBtn" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/marketing/transactions/packList.html">Listing</a>
					
						
				</div> -->
				
				<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="srNo"/>
				<input type="hidden" id="pPartyIds" name="pPartyIds" />
				<input type="hidden" id="pLocationIds" name="pLocationIds" />
				<input type="hidden" id="vTranDate" name="vTranDate" /></td>
				
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


<div id="packDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-10">
					<div id="toolbar">
					
					<div class="row">
					<div class="col-md-12">
					
					<div class="col-md-6">
					<input type="text" id="barcodeTxtId" name="barcodeTxtId" class="form-control" placeholder="Type Barcode To Add" onchange="javascript:popPacklistAdd()">
					</div>
					
					
					<div class="col-sm-3">
					
					<a class="btn btn-info" style="font-size: 15px" type="button" id="dtStockUpBtnId"
					onClick="javascript:stockPickup()"
					href="javascript:void(0)"><span
				></span>&nbsp;Stock Pickup</a>
					</div>
					
					<div class="col-sm-3">
					
					<a class="btn btn-success" style="font-size: 15px" type="button" id="dtSummaryBtnId"
					onClick="javascript:displayDtSummary()"
					href="javascript:void(0)"><span
				></span>&nbsp;Summary</a>
					</div>
					
					</div>
					</div>
				
					
					
					
					
					
				
				
				
					
				<!-- 	<div class="col-sm-4">
					<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:locationWisePickup()"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
					
					</div> -->
					
					
					</div>
					<div>
						<table id="packDtTblId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-striped="true" data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
								<th data-field="stateRd" data-radio="true"></th>  
										<th data-field="action2" data-align="center">Delete</th>
										<th data-field="srNo">Sr. No.</th>
										<th data-field="barcode">Barcode</th>
										<th data-field="style" data-sortable="false" class="span5">Style No</th>
										<th data-field="ktCol" data-sortable="false">Kt-Col</th>
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false">Gross Wt</th>
										<th data-field="netWt" data-sortable="false">Net Wt</th>
										<th data-field="metalValue" data-sortable="false">Metal Value</th>
										<th data-field="stoneValue" data-sortable="false">Stn Value</th>
										<th data-field="compValue" data-sortable="false">Comp Value</th>
										<th data-field="hallMark" data-sortable="false">HallMarking</th>
										<th data-field="lazerMark" data-sortable="false">LazerMarking</th>
										<th data-field="grading" data-sortable="false">Grading</th>
										<th data-field="engraving" data-sortable="false">Engraving</th>
										<th data-field="labourValue" data-sortable="false">Lab Value</th>
										<th data-field="setValue" data-sortable="false">Set Value</th>
										<th data-field="handlingCost" data-sortable="false">Hdlg Cost</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="other" data-sortable="false">Other</th>
										<th data-field="discAmount" data-sortable="false">Disc Amt</th>
										<th data-field="finalPrice" data-sortable="false">Final Price</th>
										<th data-field="rLock" data-sortable="false"data-formatter="inputFormatter">Lock</th>  
										<th data-field="actionLock" data-align="center"></th>
									
									
								</tr>
							</thead>
						</table>
						
						<input type="hidden" id="packDtPKId" name="packDtPKId" />
					</div>
					
					
					
					
				</div>
				<div id="odImgDivId" class="col-xs-2 center-block">
					<div style="height:52px">&nbsp;</div>
					<div class="panel panel-primary" style="width:100%; height:245px">
						<div class="panel-body">
							<div style="width:80%; height:168px">
								<div class="row center-block">
									<span id='styleImgId'></span> 									
									<div id="tempImgDivId">
										
									</div>
								</div>
							</div>
							<div style="height:15px">&nbsp;</div>
							<div class="pull-left">
								<table id='stoneDtlsId' style="width:100%"
									class="line-items editable table table-bordered">
								</table>
							</div>
						</div>
					</div>
					
					
					
			<div class="row" >
				<div class="form-group">
					<div class="col-xs-1">
						<input type="button" value="Apply Rate" class="btn btn-info" id="applyRateBtn" style="width: 190px" onclick="javascript:applyRate()"/>
					</div>
				</div>
				</div>
				
				<div class="row">
				<div>&nbsp;</div>
			</div>
					
		
					
					
					
				</div>
				
				
				
			</div>
			
			
			
			
			
			
			
			
			
			
			
		</div>
		

		
<!-- //hide on page load start here -->
	<div id="hideOnPageLoad" style="display: none" >
	
	<!------------------------------------------ PackMetal  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="packMetalTableId" data-toggle="table" 
							data-unique-id="id" >
						<thead>
							<tr class="btn-primary">
								<th data-field="partNm" data-sortable="false">PartNm</th>
								<th data-field="purity" data-sortable="false">Purity</th>
								<th data-field="color" data-align="left">Color</th>
								<th data-field="qty" data-sortable="false">Qty</th>
								<th data-field="metalWt" data-sortable="false">Metal Wt</th>
								<th data-field="metalRate" data-sortable="false">Metal Rate</th>
								<th data-field="perGramRate" data-sortable="false">Per Gram Rate</th>
								<th data-field="lossPerc" data-sortable="false" data-formatter="lossPercFormatter">Loss %</th>
								<th data-field="metalValue" data-sortable="false">Metal Value</th>
								<th data-field="mainMetal"  data-formatter="mianMetalFormatter">Main Metal</th>
							</tr>
						</thead>
					</table>
				</div>
			
			


		<!------------------------------------------ packStnDt -------------------------------------->
		
			<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Stone Details</span>
				</div>			
			</div>
		</div>

		
	
			
											
						
					<div >
						<table class="table-responsive"  id="packStnDtTabId"
							data-toggle="table" 
							data-click-to-select="true"
							>
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="partNm" data-sortable="true">Part Name</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
									<th data-field="quality" data-sortable="false">Quality</th>
									<th data-field="size" data-sortable="false">Size</th>
									<th data-field="sieve" data-sortable="false">Sieve</th>
									<th data-field="sizeGroup" data-sortable="false">SizeGroup</th>
									<th data-field="stone" data-sortable="false">Stone</th>
									<th data-field="carat" data-sortable="false">Carat</th>
									<th data-field="rate" data-sortable="false"  data-formatter="stnRateFormatter">Rate</th>
									<th data-field="stoneValue" data-sortable="false">Stn Value</th>
									<th data-field="handlingRate" data-sortable="false" data-formatter="hdlgRateFormatter">Hdlg Rate</th>
									<th data-field="handlingValue" data-sortable="false">Hdlg Value</th>
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SetType</th>
									<th data-field="settingRate" data-sortable="false" data-formatter="settingRateFormatter">Set Rate</th>
									<th data-field="settingValue" data-sortable="false">Set Value</th>
									<th data-field="centerStone" data-sortable="false" data-formatter="inputFormatter">Center Stone</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center" >Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
						
						<input type="hidden" id="packStoneDtPKId" name="packStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
			
			

		
		
		
	
	
		<!------------------------------------------ packCompDt -------------------------------------->
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		
		<div class="row">
			<div class="form-group">
				<div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Component Details</span>
				</div>			
			</div>
		</div>
		
	<div id="toolbarCompDt">
		
						
						
	</div>
					<div >
						<table  id="packCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							 data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metal Wt</th>
									<th data-field="rate" data-sortable="false" data-formatter="compRateFormatter">Labour Rate</th>
									<th data-field="compPcs" data-sortable="false">Qty</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="perPcs" data-sortable="false" data-formatter="inputFormatter">Per Pcs</th>
									<th data-field="perGram" data-sortable="false" data-formatter="inputFormatter">Per Gram</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
		
			
			<!-- component edit mode -->
			<div id="entryPackCompDt" style="display: none">
			<div id="packCompRowId">
			</div> 
		</div>
		
			
			
		
		
		
		
		
		
				
		<!-----------  packLabDt ---------->
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		<div class="row">
		<div class="form-group">
		 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Labour Details</span>
				</div>
		</div>
		</div>
			 
								
					<div id="toolbarLabDt">
						<a class="btn btn-info" style="font-size: 15px" type="button" id="labourBtnId" onClick="javascript:addLabour()">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour
						</a>
					</div>
				
					<div>
						<table id="packLabDtTabId" data-toolbar="#toolbarLabDt"
							data-toggle="table" 
							data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="labourType" data-sortable="true">LabourType</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="perPcs" data-sortable="false" data-formatter="inputFormatter">Per Pcs</th>
									<th data-field="perGram" data-sortable="false" data-formatter="inputFormatter">Per Gram</th>
									<th data-field="percent" data-sortable="false" data-formatter="inputFormatter">Percentage</th>
									<th data-field="perCaratRate" data-sortable="false" data-formatter="inputFormatter">Per Carat Rate</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
						
						
						
					</div>
					
		
		<!-- entry form (costLabDt) -->
		
		<div id="entryPackLabDt" style="display: none">
			<div id="packLabRowId">
			</div> 
		</div>
				
		
		
		
		
		</div> <!-- ending the hide on page load -->	

		
	</div>


	

</div>

<script type="text/javascript">

canViewFlag=false;
var disableFlg = false;
var mtid;
var mode;
	$(document)
			.ready(
					function(e) {
						
						
//$('select').chosen();

$('#fgIssDivId #party\\.id').chosen();
$('#fgIssDivId #location\\.id').chosen();
$('#fgIssDivId #hsnMast\\.id').chosen();


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
						
						
						
						$(".packMtForm")
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
							
							$("#packDtDivId").css('display', 'block');
							mtid="${mtid}";
							mode ="${model}";
						
							
							$('#barcodeTxtId').focus();
							
							
						}
						else{

							$("#vTranDate").val('${currentDate}');					
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					
							
						}

						if('${disableFlg}'=='true'){

							disableFlg = true;
							popPackDt(disableFlg);
							$('#fgIssDivId .btn').hide();
							$('#packDtDivId .btn').hide();
							$('#metalRateSaveBtnId').hide();
							$('#stoneRateSaveBtnId').hide();
						//	$('#packDtDivId').find('input, textarea, select').attr('disabled','disabled');
						//	$('#packDtDivId :input').attr('disabled',true);
							$('#packingListingBtn').show();
							$('#dtSummaryBtnId').show();
							$('#packMtExcelPreviewBtn').show();
							$('#packMtPreviewBtn').show();	
							$('#barcodeTxtId').attr('readonly','readonly');
							
						}else{
							disableFlg = false;
							popPackDt(disableFlg);
							}
						

						$("#barcodeTxtId")
						.autocomplete(
							{
								source : '<spring:url value='/marketing/transactions/barcode/autoFill/list.html' />?locationId='
									+ $('#location\\.id').val(),

								minLength : 2
							})

						

					});

						
		

		function process(date){
			   var parts = date.split("/");
			   return new Date(parts[2], parts[1] - 1, parts[0]);
			}
	
	
	
	var packDtTableRow;
	var packDtLockStatus = 'null';
	var finalPrice = "";
	var dtdata='';
	 $('#packDtTblId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				packDtTableRow = $element.attr('data-index');
				
				$('#packDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
				packDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;
				$('#tempImgDivId').empty();
				var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
				$('#tempImgDivId').append(tempDiv);
				
				
				$('#hideOnPageLoad').css('display','block');
			
					
				popPackMetal();
				popPackStoneDetails();
				popPackComponentDetails();
			
				popPackLabDetails(disableFlg);
				
			
				
			});




	var dtdata='';
		
		$('#packDtTblId').bootstrapTable({}).on('load-success.bs.table',
				function(e, data) {
			   
			dtdata = $("#packDtTblId").bootstrapTable('getData').length;

			
				
			 if(dtdata >0){

				 $('#pPartyIds').val($('#fgIssDivId #party\\.id').val());
					$('#pLocationIds').val($('#fgIssDivId #location\\.id').val());
					
					
					$('#fgIssDivId #party\\.id').attr("disabled","disabled").trigger('chosen:updated');
					$('#fgIssDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
					
					}else{
						$('#fgIssDivId #party\\.id').removeAttr('disabled').trigger('chosen:updated');
						$('#fgIssDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
			
						
					}
				
			  })
	
	
	
	  $(document)
		.on(
			'submit',
			'form#packFormId',
			 function(e){
				 $("#packMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});
	
	
	
	
	

	function popPackDt(dateFlag) {
		$("#packDtTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/packDt/listing.html?mtId="+mtid+"&flag="+dateFlag,
						});

	
		
	}
	
	function popPackMetal()
	{
		$("#packMetalTableId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/packMetalDt/listing.html?packDtId="+$('#packDtPKId').val(),
				});	
	}
	
	function popPackStoneDetails(){
		
		$("#packStnDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/packStnDt/listing.html?packDtId="+$('#packDtPKId').val(),
				});	
		
	}

	
	function popPackComponentDetails(){
		
		$("#packCompDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/packCompDt/listing.html?packDtId="+$('#packDtPKId').val()+"&disableFlg="+disableFlg,
				});	
		
	}
	
	function popPackLabDetails(disableFlg){
		$("#packLabDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/packLabDt/listing.html?packDtId="+$('#packDtPKId').val()+"&disableFlg="+disableFlg,
				});	
	}
	
	
	

	function popPacklistAdd() {
		
		if($('#barcodeTxtId').val() !=''){

		
			$.ajax({
				url : "/jewels/marketing/transactions/packList/addBarcodeItem.html",
				type : 'POST',
				data :{barcode :$('#barcodeTxtId').val(),mtId :mtid,partyId:$('#party\\.id').val(),locationId:$('#location\\.id').val()},
				success : function(data) {
					
					if(data==='1'){
						
						popPackDt(disableFlg);
						
					}else{
						displayMsg(event, this, data);
					}
					
					
				

				}
			});
			
		}else{
			displayMsg(event, this, 'Enter Barcode To Add');
		}
					
	
					
			

		
	}
	

	
		
	
	
	
	function deleteDt(dtId){
		
		
		$("#modalDialog").modal("hide");
		
							
						 $.ajax({
								url : "/jewels/marketing/transactions/packDt/delete/"+ dtId + ".html",
								type : 'GET',
								success : function(data) {
									if(data === "1"){
										displayInfoMsg(event, this,'Delete sucessfully !');
										popPackDt(disableFlg);
									}else{
										displayMsg(event, this, data);
									}
								}
							}); 
		
	
	}
	
	function deletePackDt(e,dtId){

			
			displayDlg(
					e,
					'javascript:deleteDt('+dtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
				
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
	    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '"  onchange="javascript:updateLossPerc(this,'+vId+')"  />';
	}



	function updateLossPerc(param, val){

		if(!disableFlg){
			$.ajax({
				url : "/jewels/marketing/transactions/packMetalDt/updateLossPerc.html?packDtId="+$('#packDtPKId').val()+"&lossPerc="+ param.value,
				type : 'GET',
				success : function(data) {
					popPackDt(disableFlg);
						popPackMetal();
					
				}

			});
			}else{
				displayMsg(this,null,"Backdated entry not allowed");	
				}
		
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
	
	function packListSave() {
		
		if($(".packMtForm").valid()){
			$("form#packFormId").submit();
			
		}
		
		
		

	}

	

	function popPackMetalRate(){
		$('#myPackRateModal').modal('show');
		popPackMetalRateTbl();
		
		setTimeout(function(){
			$('#packMetalRateIdTbl').bootstrapTable('resetView');
		}, 300);
		
	}
	
	
	
	function popPackQualityRate(){
		
		$('#myQltyRateChng').modal('show');
		 $('#myQltyRateChng').on('shown.bs.modal', function () {
			 shapeDropdown();
			 
	        });
		
		
	}
	
	
	
	$('#packStnDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#packStnDtTabId").bootstrapTable('getData'));

				
				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					
					
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
					
					
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});
	
		
	 function applyRate(){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		 
				 
		 $.ajax({
				url : "/jewels/marketing/transactions/packDtApplyRate.html",
				type : "POST",
				data : {mtId :mtid},
				success : function(data, textStatus, jqXHR){
					
					if(data === '1'){
						popPackDt(disableFlg);
						$('#hideOnPageLoad').css('display','none');
						
						/* window.location.reload(true); */
					}else{
						displayMsg(this,null,"Error Can Not Apply Rate");	
					}
					
					
						
					$.unblockUI();

				},
				error : function(data, textStatus, jqXHR){
					$.unblockUI();

				}
				
			})
		 
		 
	 }
	 
	 function locationWisePickup(){
			$('#myLocationWisePickupModal').modal('show');
			 $('#myLocationWisePickupModal').on('shown.bs.modal', function () {
				 popDeptTo(); 	
		        });
			}

		function popDeptTo() {
			
			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/consigMt/locationDropdown.html' />',
						type : 'GET',
						success : function(data) {
							
							$("#locationnm").html(data);
							$("#locationnm").trigger("chosen:updated");
							
									}
					});
		}
		
		
		function stnRateFormatter(value, row, index){
			
			var tempId = 'stnRateval' + Number(index);
	
			var vId = row.id;
			
			
			   if (row.rLock === 'true') {
			    	
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateStnRate(this,'+vId+')" disabled />';	
			    }else{
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateStnRate(this,'+vId+')"  />';
			    }
				
			
			
			
		    	
		}	
		
		
		function updateStnRate(param, val){

			if(!disableFlg){
			$.ajax({
				url : "/jewels/marketing/transactions/packStnDt/updateStnRate.html?id="+val+"&stnRate="+ param.value,
				type : 'GET',
				success : function(data) {
				popPackStoneDetails();
				updatePackDtTable($('#packDtPKId').val());
				
				}

			});
			}else{
				displayMsg(this,null,"Backdated entry not allowed");	
				}
		}
		
		
		
		
		function hdlgRateFormatter(value, row, index){
			var tempId = 'hdlgRateval' + Number(index);
			
			var vId = row.id;
			
			
			   if (row.rLock === 'true') {
			    	
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateHdlgRate(this,'+vId+')" disabled />';	
			    }else{
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateHdlgRate(this,'+vId+')"  />';
			    }
				
			
		}
		
		
		
		function updateHdlgRate(param, val){

			if(!disableFlg){
			$.ajax({
				url : "/jewels/marketing/transactions/packStnDt/updateHdlgRate.html?id="+val+"&hdlgRate="+ param.value,
				type : 'GET',
				success : function(data) {
				popPackStoneDetails();
				updatePackDtTable($('#packDtPKId').val());
				
				}

			});
			}else{
				displayMsg(this,null,"Backdated entry not allowed");	
				}
		}
		
		
		
		function settingRateFormatter(value, row, index){
			var tempId = 'settRateval' + Number(index);
			
			var vId = row.id;
			
			
			   if (row.rLock === 'true') {
			    	
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateSettRate(this,'+vId+')" disabled />';	
			    }else{
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateSettRate(this,'+vId+')"  />';
			    }
				
			
		}
		
		
		
		function updateSettRate(param, val){
			if(!disableFlg){
			$.ajax({
				url : "/jewels/marketing/transactions/packStnDt/updateSettRate.html?id="+val+"&settRate="+ param.value,
				type : 'GET',
				success : function(data) {
				popPackStoneDetails();
				updatePackDtTable($('#packDtPKId').val());
				
				}

			});

		}else{
			displayMsg(this,null,"Backdated entry not allowed");	
			}
		}
		
		
		
		
		function compRateFormatter(value, row, index){
			var tempId = 'compRateval' + Number(index);
			
			var vId = row.id;
			
			
			   if (row.rLock === 'true') {
			    	
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateCompRate(this,'+vId+')" disabled />';	
			    }else{
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateCompRate(this,'+vId+')"  />';
			    }
				
			
		}
		
		
		
		function updateCompRate(param, val){

			if(!disableFlg){
			$.ajax({
				url : "/jewels/marketing/transactions/packCompDt/updateCompRate.html?id="+val+"&compRate="+ param.value,
				type : 'GET',
				success : function(data) {
					popPackComponentDetails();
					updatePackDtTable($('#packDtPKId').val());
				
				}

			});
			}else{
				displayMsg(this,null,"Backdated entry not allowed");	
				}
		}
		
		
		
		function updatePackDtTable(dtId){
			
			
			
			$.ajax({
				url : "/jewels/marketing/transactions/packDt/getData/"+dtId+".html",
				type : 'GET',
				dataType : 'JSON',
				success : function(data) {
										
					$('#packDtTblId').bootstrapTable('updateRow', {
						index : Number(packDtTableRow),
						row : data
					});
					
					
				}
			});
		}


		 function displayDtSummary(){
			
			$('#myPackDtSummaryModal').modal('show');
			 $('#myPackDtSummaryModal').on('shown.bs.modal', function () {
				 popPackSummary();
		        });
		

			}

		function popPackSummary(){

			$.ajax({
				url:"/jewels/marketing/transactions/packMt/dtSummary.html?mtId="+mtid,
				type :'GET',
				dataType:'JSON',
				success: function(data){
					
					if(data !== "-1"){
						
						$.each(data,function(k,v){
							
							$('#packDtSummaryDivId  #'+k).val(v);
						});
						
					}else{
						displayMsg(this,null,'Error Contact Admin');
					}
					
				}	
			})
		} 


	function packingListReport(val){

		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
			
			$
			.ajax({
				url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=pack"+"&opt="+val,
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
	
	
	
	function addLabour(){
		
		
		 if(packDtLockStatus == 'true'){
			 displayMsg(this, null, 'Main Item Is Lock');
		 }else{
		
			
			 var vId=0;
			 
			 $.ajax({
					url : "/jewels/marketing/transactions/packLabDt/edit/"+ vId + ".html",
					type : 'GET',
					success : function(data) {
						
						$("#packLabRowId").html(data);
						$('#entryPackLabDt').css('display','block');
						$('#metal\\.id').focus();
					}
				});
			 
				
			
				
				
		 }
	}
	
	
	
	$(document)
	.on(
		'submit',
		'form#packLabDtEnt',
		 function(e){
			
			$('#forLabPackMtId').val($('#id').val());
			$('#forLabPackDtId').val($('#packDtPKId').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					
					if(data === "-11"){
						displayMsg(this, null, 'please select only one check box');	
					}else if(data === "-12"){
						displayMsg(this, null, 'please check the entry');	
					}else if(data === "-13"){
						displayMsg(this, null, 'Dupliacte labour, already added');	
					}else if(data === "-15"){
						displayMsg(this, null, 'Can not add or edit Grading');	
					}
					else{
					
					
					popPackLabDetails(disableFlg)
					updatePackDtTable($('#packDtPKId').val());
					
					$('form#packLabDtEnt select').val('');
					$('#packLabDtEnt #labourRate').val('0.0')
					
					$('#packLabDtEnt #pcsWise').attr('checked', false); 
					$('#packLabDtEnt #gramWise').attr('checked', false); 
					$('#packLabDtEnt #percentWise').attr('checked', false); 
					$('#packLabDtEnt #perCaratRate').attr('checked', false);
					$('#packLabDtEnt #rLock').attr('checked', false);
					$('#packLabDtEnt #packLabDtPk').val('');
					
					if(data === '-2'){
						$('#entryPackLabDt').css('display','none');
					}
					
					}
				
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();
	
	})		
	
	
		function editPackLabDt(pkLabDtId){
			
			 if(packDtLockStatus == 'true'){
				 displayMsg(this, null, 'Main Item Is Lock');
			 }else{
				 
				 $.ajax({
						url : "/jewels/marketing/transactions/packLabDt/validationEdit.html?labDtId="+pkLabDtId,
						type : 'GET',
						success : function(data) {
							
							if(data === "-1"){
								displayMsg(this, null, 'Cannot edit,Record is Locked');	
							}else{
			
							$.ajax({
								url : "/jewels/marketing/transactions/packLabDt/edit/"+ pkLabDtId + ".html",
								type : 'GET',
								success : function(data) {
									$("#packLabRowId").html(data);
									$('#entryPackLabDt').css('display','block');
									$('#metal\\.id').focus();
								}
							});
							
							}
						    
						}
					});
							
						}
						    
				
			
			 }
	
	
	
	function deletePackLabDt(e,packLabDtId){
		
		 if(packDtLockStatus == 'true'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
			 
			 $.ajax({
					url : "/jewels/marketing/transactions/packLabDt/validationEdit.html?labDtId="+packLabDtId,
					type : 'GET',
					success : function(data) {
						
						if(data === "-1"){
							displayMsg(this, null, 'Cannot edit,Record is Locked');	
						}else{
							
							displayDlg(
									e,
									'javascript:deletePackLabDtData('+packLabDtId+');',
									'Delete',
									'Do you want to Delete  ?',
									'Continue');
												
							
						}
						
					}
			 })
		
			
			
		 }
		
	}
	
	
	function deletePackLabDtData(packLabDtPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/packLabDt/delete/'+packLabDtPk+'.html',
			data: 'GET',
			success : function(data){
							
					
					popPackLabDetails(disableFlg);
					updatePackDtTable($('#packDtPKId').val());
				
			}
			
		})
		
	}
	
	
	$('#packLabDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				$('#entryPackLabDt').css('display','none'); 
				
			})
			
			
			
			
	function doLabDtLockUnLock(labDtIdPk){
				
				 if(packDtLockStatus == 'Lock'){
					 displayMsg(this, null, ' Main Item Is Lock');
				 }else{
				
					 $.ajax({
							url : "/jewels/marketing/transactions/packLabDt/lockUnlock.html?labDtId="+labDtIdPk,
							type : 'GET',
							success : function(data) {
							
									popPackLabDetails(disableFlg);
								
							}
						});
				 }
			}



	function editPackCompDt(compId){
		$.ajax({
			url : "/jewels/marketing/transactions/packCompDt/edit/"+ compId + ".html",
			type : 'GET',
			success : function(data) {
				$("#packCompRowId").html(data);
				$('#entryPackCompDt').css('display','block');
				$('#rate').focus();
			}
		});
		}


	$(document)
	.on(
		'submit',
		'form#packCompDtEnt',
		 function(e){
			
			$('#forCompPackMtId').val(mtid);
			$('#forCompPackDtId').val($('#packDtPKId').val());
			$('#pComponentId').val($('#component\\.id').val());
			$('#pPurityId').val($('#purity\\.id').val());
			$('#pColorId').val($('#color\\.id').val());
			

		
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
		 	$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					
					if(data === "-11"){
						displayMsg(this, null, 'please select only one check box');	
					}else if(data === "-12"){
						displayMsg(this, null, 'please check the entry');	
					}
					else{
					
					
					popPackComponentDetails()
					updatePackDtTable($('#packDtPKId').val());
					
				
					
					if(data === '-2'){
						$('#entryPackCompDt').css('display','none');
					}
					
					}
				
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			}) 
			
			e.preventDefault();
	
	})		
	
	
	
	function popPackLabourRate(){
		$('#modalPackLabourRate').modal('show');
		}


	function update999(){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		 
		 
		 var postData = $('#packFormId').serializeArray();
		 
		 $.ajax({
				url : "/jewels/marketing/transactions/packMt/updatepuritycost.html",
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					
					if(data === '1'){
						popPackDt(disableFlg);
					
						
						$('#hideOnPageLoad').css('display','none');
					}else{
						displayMsg(this,null,"Error Can Not Update");	
					}
						
					$.unblockUI();

				},
				error : function(data, textStatus, jqXHR){
					$.unblockUI();

				}
				
			})
		 
		 
	}	



	function stockPickup(){

		 window.location.href = "/jewels/marketing/transactions/stockPickupForPacking.html?mtid="+mtid+"&locationId="+$('#location\\.id').val();
			
			}


	  function hideShowDtDiv(){
			$("#packDtDivId").css('display', 'none');
		}
	
			
	
</script>


<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
