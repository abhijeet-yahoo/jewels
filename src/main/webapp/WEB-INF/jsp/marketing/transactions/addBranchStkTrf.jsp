<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalStkTrfDtSummary.jsp"%>
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



<div id="branchTrfDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Branch Transfer </span>
			</label>
			<div class="text-right">
				<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="branchMstExcelPreviewBtn" onClick="javascript:packingListReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="branchMstPreviewBtn" onClick="javascript:packingListReport(2);" />
			
			
			<!-- <input type="button" value="Lock" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info" id="lockInvoiceBtn" onClick="javascript:popLockInvoice();" /> -->
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="stkTrfListingBtnId"
					href="/jewels/marketing/transactions/branchStkTrfMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="stkTrfMtSubmitBtn"  onclick="javascript:stkTrfMtSave()" />
			
			</div>

		</div>

	</div>
	
	<div class="form-group">
		<form:form commandName="stkTrfMt" id="stkTrfMtFormId"
			action="/jewels/marketing/transactions/branchStkTrfMt/add.html"
			cssClass="form-horizontal stkTrfMtForm">

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
					
				<!-- 	<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Party:</label>
					</div> -->
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">From Location:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">To Location :</label>
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
						<form:select path="location.id" class="form-control" required="true" onchange="javascript:toDeptLocation();branchWiseGst();">
							<form:option value="" label=" Select Location " />
							<form:options items="${locationMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="toLocation.id" class="form-control" required="true">
							<form:option value="" label=" Select Location" />
							<form:options items="${departmentToMap}" />
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
					<input type="hidden" id="pLocationIds" name="pLocationIds" />
					<input type="hidden" id="pToLocationIds" name="pToLocationIds"/>
					<input type="hidden" id="vTranDate" name="vTranDate" />
					<input type="hidden" id="vOtherCharges" name="vOtherCharges" />
					<input type="hidden" id="vFinalPrice" name="vFinalPrice" />
					<input type="hidden" id="pIgst" name="pIgst" />
				<input type="hidden" id="pSgst" name="pSgst" />
				<input type="hidden" id="pCgst" name="pCgst" />
					
				
				</div>
			</div>
		
		
		
		
		</form:form>
		
		
	
		
		
	</div>
	
</div>



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

	

<div id="stkTrfDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-10">
					<div id="toolbar">
					<div class="row">
				
				
					<div class="col-sm-6">
					<input type="text" id="barcodeTxtId" name="barcodeTxtId" class="form-control" placeholder="Type Barcode To Add" onchange="javascript:popstkTrflistAdd()">
					</div>		
					
				
					
					<!-- <div class="col-sm-3">
					<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayDtSummary()"
					href="javascript:void(0)"><span
				></span>&nbsp;Summary</a>
					
					</div> -->
					
					 <div class="col-sm-3">
					<a class="btn btn-success" style="font-size: 15px" type="button" id="applyGstBtnId"
					onClick="javascript:applyGstRates()"
					href="javascript:void(0)"><span
					></span>&nbsp;Apply Rates</a>
					</div> 
					
					
					</div>
					</div>
					<div>
						<table id="stkTrfDtTblId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-click-to-select="true" data-striped="true">
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
						
						<input type="hidden" id="stkTrfDtPKId" name="stkTrfDtPKId" />
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
					
		
				
				<div class="row">
				<div>&nbsp;</div>
			</div>
					
		
					
					
					
				</div>
				
				
				
			</div>
			
			
			
			
			
			
			
			
			
			
			
		</div>
		

<div class="container-fluid">
			<div class="row">
				<div class="col-md-9">
				
				
		
<!-- //hide on page load start here -->
	<div id="hideOnPageLoad" style="display: none" >
	
	<!------------------------------------------ stkTrfMetal  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="stkTrfMetalTableId" data-toggle="table" 
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
			
			


		<!------------------------------------------ stkTrfStnDt -------------------------------------->
		
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
						<table class="table-responsive"  id="stkTrfStnDtTabId"
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
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="stoneValue" data-sortable="false">Stn Value</th>
									<th data-field="handlingRate" data-sortable="false">Hdlg Rate</th>
									<th data-field="handlingValue" data-sortable="false">Hdlg Value</th>
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SetType</th>
									<th data-field="settingRate" data-sortable="false">Set Rate</th>
									<th data-field="settingValue" data-sortable="false">Set Value</th>
									<th data-field="centerStone" data-sortable="false" data-formatter="inputFormatter">Center Stone</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center" >Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
						
						<input type="hidden" id="stkTrfStoneDtPKId" name="stkTrfStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
			
			

		
		
		


	
	
		<!------------------------------------------ stkTrfCompDt -------------------------------------->
		
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
						<table  id="stkTrfCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							 data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metal Wt</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="compPcs" data-sortable="false">Qty</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
		
			
			
		
			
			
		
		
		
		
		
		
				
		<!-----------  stkTrfLabDt ---------->
		
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
						
					</div>
				
					<div>
						<table id="stkTrfLabDtTabId" data-toolbar="#toolbarLabDt"
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
				
		
		
		</div> <!-- ending the hide on page load -->	
		
		
			
			</div>
			
				<div class="col-md-3">
				
				<div class="row">
				<div>&nbsp;</div>
			</div>
					<div class="row">
					
					<div class="col-sm-12 form-group">

						<label for="totalPcs" class="col-sm-6 control-label">Pcs</label>
						<div class="col-sm-6">
							<input type="number" id="totalPcs" class="form-control" name="totalPcs"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="totMetalValue" class="col-sm-6 control-label">Metal Value</label>
						<div class="col-sm-6">
							<input type="number" id="totMetalValue" class="form-control" name="totMetalValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="totStoneValue" class="col-sm-6 control-label">Stone Value</label>
						<div class="col-sm-6">
							<input type="number" id="totStoneValue" class="form-control" name="totStoneValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
					<div class="col-sm-12 form-group">

						<label for="totSettingValue" class="col-sm-6 control-label">Setting Value</label>
						<div class="col-sm-6">
							<input type="number" id="totSettingValue" class="form-control" name="totSettingValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="totHandlingValue" class="col-sm-6 control-label">Handling Value</label>
						<div class="col-sm-6">
							<input type="number" id="totHandlingValue" class="form-control" name="totHandlingValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
						<div class="col-sm-12 form-group">

						<label for="totLabourValue" class="col-sm-6 control-label">Labour Value</label>
						<div class="col-sm-6">
							<input type="number" id="totLabourValue" class="form-control" name="totLabourValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="totalFob" class="col-sm-6 control-label">FOB</label>
						<div class="col-sm-6">
							<input type="number" id="totalFob" class="form-control" name="totalFob"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="otherCharges" class="col-sm-6 control-label">Other Charges</label>
						<div class="col-sm-6">
							<input type="number" id="otherCharges" class="form-control" name="otherCharges"
								 style="text-align: right;" onchange="javascript:otherChargesFun();"/>
						</div>

					</div>
					
					
					
					<div class="col-sm-12 form-group" id="cgstDivId" style="display: none">

						<label for="cgst" class="col-sm-6 control-label">Cgst</label>
						<div class="col-sm-6">
							<input type="number" id="cgst" class="form-control" name="cgst"
							readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
					<div class="col-sm-12 form-group" id="sgstDivId" style="display: none">

						<label for="sgst" class="col-sm-6 control-label">Sgst</label>
						<div class="col-sm-6">
							<input type="number" id="sgst" class="form-control" name="sgst"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
				
					
					
					<div class="col-sm-12 form-group" id="igstDivId" style="display: none">

						<label for="igst" class="col-sm-6 control-label">Igst</label>
						<div class="col-sm-6">
							<input type="number" id="igst" class="form-control" name="igst"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
				
					
					<div class="col-sm-12 form-group">

						<label for="totFinalPrice" class="col-sm-6 control-label">Final Price</label>
						<div class="col-sm-6">
							<input type="number" id="totFinalPrice" class="form-control" name="totFinalPrice"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
					
					</div>
		
		
	</div>
			
			
		</div>
		
	
	
	
	</div>
		
		
		
		

		
	</div>


	

</div>

















</div>







<script type="text/javascript">



var canViewFlag=false;
var mtid;
var mode;
var gstNoData;
var disableFlg=false;
	$(document)
			.ready(
					function(e) {

						
						
						
//$('select').chosen();

$('#branchTrfDivId #party\\.id').chosen();
$('#branchTrfDivId #location\\.id').chosen();
$('#branchTrfDivId #hsnMast\\.id').chosen();
$("#branchTrfDivId #toLocation\\.id").chosen();
$("#branchTrfDivId #modeOfTransport\\.id").chosen();

				
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
						
						
						
						$(".stkTrfMtForm")
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
							$("#stkTrfDtDivId").css('display', 'block');
							mtid="${mtid}";
							mode ="${model}";

							
							
							

							$('#vOtherCharges').val("${totalOtherCharges}");
							$('#vFinalPrice').val("${finalPrice}");

							$('#otherCharges').val("${totalOtherCharges}");
							$('#totFinalPrice').val("${finalPrice}");

							setTimeout(function(){
								$('#branchTrfDivId #igst').val("${pIgst}");
								$('#branchTrfDivId #sgst').val("${pSgst}");
								$('#branchTrfDivId #cgst').val("${pCgst}");

								$('#pIgst').val("${pIgst}");
								$('#pSgst').val("${pSgst}");
								$('#pCgst').val("${pCgst}");
								}, 20);

							branchWiseGst();
							
							// applyGst();

							if(process('${currentDate}') > process($('#invDate').val())){
								popStkTrfDt(true);
								
								disableFlg = true;
								popStkTrfDt(true);
								$('#applyGstBtnId').hide();
								$('#stkTrfMtSubmitBtn').hide();
								
							//	$('#branchTrfDivId').find('input, textarea, select').attr('disabled','disabled');
							//	$('#branchTrfDivId :input').attr('disabled',true);
								$('#stkTrfListingBtnId').show();
								$('#branchMstExcelPreviewBtn').show();
								$('#branchMstPreviewBtn').show();
								
								$('#barcodeTxtId').attr('disabled',true);
								
							}else{
								disableFlg = false;
								popStkTrfDt(false);
								} 
						
							
						}
						else{
							
							$("#vTranDate").val('${currentDate}');	
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					
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


	function stkTrfMtSave() {

		if($(".stkTrfMtForm").valid()){
			$("form#stkTrfMtFormId").submit();
			
		}
	//	applyGst();
	}


	  $(document)
		.on(
			'submit',
			'form#stkTrfMtFormId',
			 function(e){
				 $("#stkTrfMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});


		var stkTrfDtTableRow;
		var stkTrfDtLockStatus = 'null';
		 $('#stkTrfDtTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					stkTrfDtTableRow = $element.attr('data-index');
					
					$('#stkTrfDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
					
					var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
					stkTrfDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
					
					$('#hideOnPageLoad').css('display','block');
				
						
					popStkTrfMetal();
					popStkTrfStoneDetails();
					popStkTrfComponentDetails();
				
					popStkTrfLabDetails(); 
					
				
					
				});


			function popStkTrfDt(disableFlg) {
				$("#stkTrfDtTblId")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/marketing/transactions/stkTrfDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg,
								});
			}


			function popStkTrfMetal()
			{
				$("#stkTrfMetalTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stkTrfMetalDt/listing.html?stkTrfDtId="+$('#stkTrfDtPKId').val(),
						});	
			}


			function popStkTrfStoneDetails(){
				
				$("#stkTrfStnDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stkTrfStnDt/listing.html?stkTrfDtId="+$('#stkTrfDtPKId').val()+"&disableFlg="+disableFlg,
						});	
				
			}

			
			function popStkTrfComponentDetails(){
				
				$("#stkTrfCompDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stkTrfCompDt/listing.html?stkTrfDtId="+$('#stkTrfDtPKId').val()+"&disableFlg="+disableFlg,
						});	
				
			}
			
			function popStkTrfLabDetails(){
				$("#stkTrfLabDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stkTrfLabDt/listing.html?stkTrfDtId="+$('#stkTrfDtPKId').val()+"&disableFlg="+disableFlg,
						});	
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


			$('#stkTrfStnDtTabId').bootstrapTable({})
			.on(
					'load-success.bs.table',
					function(e, data) {
						var data = JSON.stringify($("#stkTrfStnDtTabId").bootstrapTable('getData'));

						
						var vStones = 0.0;
						var vCarat = 0.0;
			
						$.each(JSON.parse(data), function(idx, obj) {
							
							
							vStones		+= Number(obj.stone);
							vCarat		+= Number(obj.carat);
							
							
						});
						
						$('#vTotalStones').val(" " + vStones);
						$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
						
					});


			function popstkTrflistAdd(){

				if($('#barcodeTxtId').val() !=''){
					
					
					$.ajax({
						url : "/jewels/marketing/transactions/stkTrfMt/addBarcodeItem.html",
						type : 'POST',
						data :{barcode :$('#barcodeTxtId').val(),mtId :mtid, tranType :"BRANCHTRF"},
						success : function(data) {
							
							if(data==='1'){
								
								popStkTrfDt(disableFlg);
								$('#barcodeTxtId').val('')
								
							}else{
								displayMsg(event, this, data);
							}
							

						}
					});
					
				}else{
					displayMsg(event, this, 'Enter Barcode To Add');
				}
						

				}


			function deleteStkTrfDt(e,dtId){
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
										url : "/jewels/marketing/transactions/stkTrfDt/delete/"+ dtId + ".html?tranType=BRANCHTRF",
										type : 'GET',
										success : function(data) {
											if(data === "1"){
												displayInfoMsg(event, this,'Delete sucessfully !');
												popStkTrfDt(disableFlg);
												$('#hideOnPageLoad').css('display','none');
											}else{
												displayMsg(event, this, data);
											}
										}
									}); 
				
			
			}



			function displayDtSummary(){
				
				$('#myStkTrfDtSummaryModal').modal('show');
				 $('#myStkTrfDtSummaryModal').on('shown.bs.modal', function () {
					 popStkTrfSummary();
			        });
			

				}

			function popStkTrfSummary(){

				$.ajax({
					url:"/jewels/marketing/transactions/stkTrfMt/dtSummary.html?mtId="+mtid,
					type :'GET',
					dataType:'JSON',
					success: function(data){
						
						if(data !== "-1"){
							
							$.each(data,function(k,v){
								$('#stkTrfDtSummaryDivId  #'+k).val(v);
							});
							
						}else{
							displayMsg(this,null,'Error Contact Admin');
						}
						
					}	
				})
			}
	
			function toDeptLocation(){

				if($('#location\\.id').val() != ''){
				$
						.ajax({
							url:"/jewels/marketing/transactions/stkTrfMt/tolocationDropdown.html?locationId="+$('#location\\.id').val(),
							
							type : 'GET',
							success : function(data) {
								
								$("#toLocation\\.id").html(data);
								$("#toLocation\\.id").trigger("chosen:updated");
								
										}
						});
				}
				}

			


			function otherChargesFun(){

			/* 	applyGst();
				var finalPrice = (Number($('#totalFob').val()) + Number($('#otherCharges').val())).toFixed(2);
				
				var vSgst = 0.0;
				var vCgst = 0.0;
				var vIgst = 0.0;
				var totprice = 0.0
				
				if(gstNoData === "1"){
				 $('#totFinalPrice').val((Number(finalPrice)+ Number($('#sgst').val()) + Number($('#cgst').val())).toFixed(2));
					 vIgst = 0.0;
					 vSgst =Number($('#sgst').val());
					 vCgst = Number($('#cgst').val());
					 

				}else{
					
					 $('#totFinalPrice').val((Number(finalPrice) + Number($('#igst').val())).toFixed(2));

				   	vIgst =  Number($('#igst').val());
					 vSgst = 0.0;
					 vCgst = 0.0;
				}

				$('#vOtherCharges').val($('#otherCharges').val());
				$('#vFinalPrice').val($('#totFinalPrice').val());
				
				$.ajax({
					url:"/jewels/marketing/transactions/branchStkTrfMt/addSummaryDetails.html?mtId="+mtid+"&fob="+ $('#totalFob').val()+"&sgst="+vSgst
					+"&cgst="+vCgst +"&igst="+vIgst+"&otherCharges="+$('#otherCharges').val()+"&finalPrice="+ $('#totFinalPrice').val(),
					type :'GET',
					success: function(data){
						
						if(data === "-1"){
							displayMsg(this,null,'Error Contact Admin');
						}
					}	
				}) */




				setTimeout(function(){
					 applyGst();
					}, 100);
		
			$('#vOtherCharges').val($('#otherCharges').val());
				}


	
			function applyGst(){

				if($('#hsnMast\\.id').val() != '' ){
				$.ajax({
					url:"/jewels/marketing/transactions/saleMt/applyGst.html?hsnId="+$('#hsnMast\\.id').val(),
					type :'GET',
					dataType:'JSON',
					success: function(data){

						var finalPrice = (Number($('#totalFob').val()) + Number($('#otherCharges').val())).toFixed(2);
						
						var vSgst = 0.0;
						var vCgst = 0.0;
						var vIgst = 0.0;
					
						$.each(data, function(k, v) {
							
							if (k == "igst") {
								vIgst=(Number(finalPrice) * Number(v)/100).toFixed(0);
								vvigst = v;
								$('#igst').val(vIgst);
								$('#pIgst').val(vIgst);

								 $('#totFinalPrice').val((Number(finalPrice) + Number(vIgst)).toFixed(2));

								 $('#vFinalPrice').val($('#totFinalPrice').val()); 
								
							}
							if (k == "cgst") {
								vCgst=(Number(finalPrice) * Number(v)/100).toFixed(0);
								vvsgst = v;
								$('#cgst').val(vCgst);
								$('#pCgst').val(vCgst);

								 
								
							}

							if (k == "sgst") {
								vSgst=(Number(finalPrice) * Number(v)/100).toFixed(0);
								vvsgst = v;
								$('#sgst').val(vSgst);
								$('#pSgst').val(vSgst);

								 $('#totFinalPrice').val((Number(finalPrice) + Number(vSgst) + Number(vCgst)).toFixed(2));

								 $('#vFinalPrice').val($('#totFinalPrice').val());
								
							}

						});


						if(partyType === "Local"){
						
								 vIgst = 0.0;
								 vSgst =Number($('#sgst').val()).toFixed(0);
								 vCgst = Number($('#cgst').val()).toFixed(0);
								 
							}else{
								vIgst =  Number($('#igst').val()).toFixed(0);
								 vSgst = 0.0;
								 vCgst = 0.0;
							}


						$('#pSgst').val(vSgst);
						 $('#pCgst').val(vCgst);
						 $('#pIgst').val(vIgst);

						$.ajax({
							url:"/jewels/marketing/transactions/branchStkTrfMt/addSummaryDetails.html?mtId="+mtid+"&fob="+ $('#totalFob').val()+"&sgst="+vSgst
							+"&cgst="+vCgst +"&igst="+vIgst+"&otherCharges="+$('#otherCharges').val()+"&finalPrice="+ $('#totFinalPrice').val(),
							type :'GET',
							success: function(data){
								
								if(data === "-1"){
									displayMsg(this,null,'Error Contact Admin');
								}
							}	
						})

						
					}	
				})
				}
				}


			var dtdata='';
			
			$('#stkTrfDtTblId').bootstrapTable({}).on('load-success.bs.table',
					function(e, data) {
				   
				dtdata = $("#stkTrfDtTblId").bootstrapTable('getData').length;

					
				 if(dtdata >0){

						$('#pLocationIds').val($('#branchTrfDivId #location\\.id').val());
						$('#pToLocationIds').val($('#branchTrfDivId #toLocation\\.id').val());
					
						$('#branchTrfDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
						$('#branchTrfDivId #toLocation\\.id').attr("disabled","disabled").trigger('chosen:updated');
						
						}else{
							
							$('#branchTrfDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
							$('#branchTrfDivId #toLocation\\.id').removeAttr('disabled').trigger('chosen:updated');
				
							
						}
					
				  })
				  
				  
				 //Summary
				  
				  $('#stkTrfDtTblId').bootstrapTable({})
			.on(
					'load-success.bs.table',
					function(e, data) {
						var data = JSON.stringify($("#stkTrfDtTblId").bootstrapTable('getData'));

						
						var vTotPcs = 0.0;
						var vTotMetalValue = 0.0;
						var vTotStoneValue = 0.0;
						var vTotSettingValue = 0.0;
						var vTotHandlingValue = 0.0;
						var vTotLabourValue = 0.0;
						var vTotFob = 0.0;
						

						
						$.each(JSON.parse(data), function(idx, obj) {
							
							vTotPcs		+= Number(obj.pcs);
							vTotMetalValue		+= Number(obj.metalValue);
							vTotStoneValue		+= Number(obj.stoneValue);
							vTotSettingValue		+= Number(obj.setValue);
							vTotHandlingValue		+= Number(obj.handlingCost);
							vTotLabourValue		+= Number(obj.labourValue);
							vTotFob		+= Number(obj.fob);
							
						});
						
						
						$('#totalPcs').val(vTotPcs);
						$('#totMetalValue').val((vTotMetalValue).toFixed(2));
						$('#totStoneValue').val((vTotStoneValue).toFixed(2));
						$('#totSettingValue').val((vTotSettingValue).toFixed(2));
						$('#totHandlingValue').val((vTotHandlingValue).toFixed(2));
						$('#totLabourValue').val((vTotLabourValue).toFixed(2));
						$('#totalFob').val((vTotFob).toFixed(2));

						
					});


			var partyType;
			function branchWiseGst(){

				if($('#location\\.id').val() != '' && $('#toLocation\\.id').val() != ''){
					$.ajax({
						url:"/jewels/marketing/transactions/branchStkTrfMt/branchWiseGst.html?locationId="+$('#location\\.id').val()+"&toLocationId="+$('#toLocation\\.id').val(),
						type :'GET',
						success: function(data){
							gstNoData = data;
							if(data === "1"){
								partyType = "Local";
								$("#cgstDivId").css("display","block");
								$("#sgstDivId").css("display","block");
								}else{
									partyType = "OutOf";
									$("#igstDivId").css("display","block");
									}
							
						}	
					})
					}
				}


			function packingListReport(val){
				
				
				$
				.ajax({
					url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=branchTrf"+"&opt="+val,
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



			function applyGstRates(){
				//	otherChargesFun();

				applyGst();
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

