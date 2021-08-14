<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalOrderFilter.jsp"%>
<%@ include file="/WEB-INF/jsp/common/wipMailModal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalWip.jsp"%>

<%@ page import="net.sf.jasperreports.view.*"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script
	src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script>
<script
	src="/jewels/bootstrap-table-master/dist/extensions/auto-refresh/bootstrap-table-auto-refresh.min.js"></script>

<style>
.MarkTotal tbody tr:last-child {
	font-weight: bold !important;
	color: orange;
	background-color: #245580;
}

.MarkOrdTotal {
	font-weight: bold !important;
	color: black;
	background-color: #5bc0de;
}

.tablestuff2 thead {
	display: table-header-group;
}

</style>

<div class="panel panel-primary" style="width: 100%; display: block;" id="mianPanel">

	<div class="panel-body" id="generatedReportDesign">


		<form:form commandName="design" id="ordFilter"
			action="/jewels/manufacturing/masters/reports/generateReport.html"
			cssClass="form-horizontal orderFilter">


			<div class="row">
			<!-- 	<div class="col-xs-12"> -->
				
										<!--     Heading        -->
					<div class="row">
						<div class="col-sm-2"></div>
							<div class="col-sm-8">
								<div class="row">
								<div align="left" 
									style="font-style: italic; color: gray; font-weight: bold;">
									<span style="font-size: 25px;">${headingName}</span>
								</div>
								
							</div>
							</div>
						<div class="col-sm-2"></div>
					</div>
					
					<!-- </div> -->
				
					
					
			<div class="col-xs-12">
				<hr	style="width: 100%; color: red; height: 2px; background-color: red;" />
			</div>
					
					
					<div class="row">

					<div class ="col-xs-2"></div>
					
					<!-- <div class="col-xs-8" align="right"> -->
					
					<div class="col-xs-8" >



						<!-- Report Format -->

					<!-- 	<div class="row col-sm-7" align="right" id="reportFormatDiv" style="display: none;">
							<div class="col-lg-4 col-sm-4">
								<h5 style="font-weight: bold;">Report Format</h5>
							</div>
							<div class="col-lg-8 col-sm-8" id="rptFormatDropDownDivId">

							</div>
						</div> -->
						
 												
							<!-- <div class="row">	
							<div id="reportFormatDiv"  style="display: none">	
						
							<div class="row">																												
							<div class="col-xs-12">	
																
										
											<div class="col-xs-2">	
												<label for="reportFormat" class=".col-lg-2 ">Report </label>											
											</div>
											
											<div class="col-xs-10">												
												<div class="col-lg-10 col-sm-10" id="rptFormatDropDownDivId" class="form-control"></div>	
												
														
											</div>										
				
																		
									
							</div>
							<div class="col-xs-2"></div>
							</div>
							
							
						</div>
					</div> End of Report Format Row -->
						
						
						
						
							<!-- Location of Genrate Excel And Generate Report -->
					

					<!-- 	<div class="col-sm-2" align="left" style="display: none" id="wipBtnDivId">
							<input type="button" value="Generate WIP " id="wipReportBtnId"
								class="btn btn-primary" onclick="javascript:generateWip()" />
						</div> -->

					<!-- 	<div class="col-sm-2" align="left" style="display: none" id="wipCastingBtnDivId">
							<input type="button" value="Generate WIP "
								id="wipCastingReportBtnId" class="btn btn-primary"
								onclick="javascript:generateWipCasting()" />
						</div> -->

						<div class="col-sm-2" align="left" style="display: none" id="wipStatusBtnDivId">
							<input type="button" value="Generate WIP"
								id="wipStatusReportBtnId" class="btn btn-primary"
								onclick="javascript:generateWipStatus()" />
						</div>

				<!-- 		<div class="col-sm-2" align="left" style="display: none" id="orderStatusBtnDivId">
							<input type="button" value="Generate WIP "
								id="orderStatusReportBtnId" class="btn btn-primary"
								onclick="javascript:generateOrderStatus()" />
						</div>
 -->

						<div class="col-sm-2" style="display: none" id="genMailBtnId">
							<input type="button" value="Mail Report" id="mailGen"
								class="btn btn-info" onclick="javascript:processMailReport()" />
						</div>


			  <input type="hidden" name= "procName" id="procName"	value="${procName}" />
			  <input type="hidden" name= "filterForm"	id="filterForm" value="${filterForm}" /> 
			  <input type="hidden" name= "xml" id="xml" value="${xml}" /> 
			  <input type="hidden" name= "compulsoryFilter" id="compulsoryFilter"	value="${compulsoryFilter}" />
			  <input type="hidden" name= "mandatoryFilter" id="mandatoryFilter" value="${mandatoryFilter}" /> 
			  <input type="hidden" name= "pClientIds" id="pClientIds" value="" /> 
			  <input type="hidden" name= "pOrderTypeIds" id="pOrderTypeIds" value="" />
			  <input type="hidden" name= "pOrderIds" id="pOrderIds" value="" />
			  <input type="hidden" name= "pDepartmentIds" id="pDepartmentIds" value="" />
			  <input type="hidden" name= "pLocationIds" id="pLocationIds" value="" />
			  <input type="hidden" name= "pMetalIds" id="pMetalIds" value="" /> 
			  <input type="hidden" name= "pCategoryIds" id="pCategoryIds" value="" />
			  <input type="hidden" name= "pSubCategoryIds" id="pSubCategoryIds"	value="" />
			  <input type="hidden" name= "pConceptIds"id="pConceptIds" value="" />
			  <input type="hidden" name= "pSubConceptIds" id="pSubConceptIds" value="" />
	     	  <input type="hidden" name= "pStoneTypeIds" id="pStoneTypeIds" value="" />
		      <input type="hidden" name= "pShapeIds" id="pShapeIds" value="" /> 
		      <input type="hidden" name= "pQualityIds" id="pQualityIds" value="" /> 
		      <input type="hidden" name= "pMtlInwardInvoiceIds" id="pMtlInwardInvoiceIds" value="" />
		      <input type="hidden" name= "pCompInwardInvoiceIds" id="pCompInwardInvoiceIds" value="" />
		      <input type="hidden" name= "pExportInvoiceIds"	id="pExportInvoiceIds" value="" /> 
		      <input type="hidden" name= "pExportAllInvoiceIds" id="pExportAllInvoiceIds" value="" />
		      <input type="hidden" name= "pQuotationInvoiceIds"	id="pQuotationInvoiceIds" value="" /> 
		      <input type="hidden" name= "pBagsIds" id="pBagsIds" value="" /> 
		      <input type="hidden" name= "pEmployeeIds" id="pEmployeeIds" value="" />
		      <input type="hidden" name= "pExportJobInvoiceIds" id="pExportJobInvoiceIds" value="" /> 
		      <input type="hidden" name= "pFromOrdDate" id="pFromOrdDate" value="" /> 
		      <input type="hidden" name= "pToOrdDate" id="pToOrdDate" value="" /> 
		      <input type="hidden" name= "pFromDelvDate" id="pFromDelvDate" value="" />
		      <input type="hidden" name= "pToDelvDate" id="pToDelvDate" value="" /> 
		      <input type="hidden" name= "pFromBetDate" id="pFromBetDate" value="" />
		      <input type="hidden" name= "pToBetDate" id="pToBetDate" value="" />
	          <input type="hidden" name= "pBal" id="pBal" />
	          <input type="hidden" name= "pGroup1" id="pGroup1" />
	          <input type="hidden" name= "pGroup2" id="pGroup2" />
	          <input type="hidden" name= "pGroup3" id="pGroup3" /> 
	          <input type="hidden" name= "pFormat" id="pFormat" />
	          <input type="hidden" name= "pDeptFormat" id="pDeptFormat" />
	          <input type="hidden" name= "pOrdCatalogFormat" id="pOrdCatalogFormat" />
		      <input type="hidden" name= "pCostSheetFormat" id="pCostSheetFormat" />
			  <input type="hidden" name= "pQuotationFormat" id="pQuotationFormat" /> 
			  <input type="hidden" name= "pLossFormat" id="pLossFormat" />
			  <input type="hidden" name= "pVAddSummryFormat" id="pVAddSummryFormat" /> 
			  <input type="hidden" name= "pStoneInvoiceIds" id="pStoneInvoiceIds" />
			  <input type="hidden" name= "pStonePurcInvoiceIds" id="pStonePurcInvoiceIds" />
			  <input type="hidden" name= "pVersion" id="pVersion" />
			  <input type="hidden" name= "pNoBbc" id="pNoBbc" />
			  <input type="hidden" name= "pChangingFormat" id="pChangingFormat" />
			  <input type="hidden" name= "pToProdDate" id="pToProdDate" value="" />
			  <input type="hidden" name= "pFromProdDate" id="pFromProdDate" value="" />
			  <input type="hidden" name= "pPurityIds" id="pPurityIds" value="" />
			  <input type="hidden" name= "pColorIds" id="pColorIds" value="" /> 
			  <input type="hidden" name= "pDelayCondIds" id="pDelayCondIds"	value="" /> 
			  <input type="hidden" name= "pStyleIds" id="pStyleIds" value="" />
			  <input type="hidden" name= "pComponents" id="pComponents" value="" />
			  <input type="hidden" name= "pMeltingInvoiceIds" id="pMeltingInvoiceIds" /> 
			  <input type="hidden" name= "pDesignStyleIds" id="pDesignStyleIds" value="" />
			  <input type="hidden" name= "pPriorityIds" id="pPriorityIds" value="" /> 
			  <input type="hidden" name= "pSetNoId" id="pSetNoId" value="" /> 
			  <input type="hidden" name= "pCostItemIds" id="pCostItemIds" value="" /> 
			  <input type="hidden" name= "pQuotDtIds" id="pQuotDtIds" value="" />
			  <input type="hidden" name= "transactedFlg" id="transactedFlg" value="" />
		      <input type="hidden" name= "pSettingTypeIds" id="pSettingTypeIds" value="" />
			  <input type="hidden" name= "pSordDtIds"	id="pSordDtIds" value="" /> 
			  <input type="hidden" name= "pExportQualityIds" id="pExportQualityIds" value="" />
			  <input type="hidden" name= "pExportSizegroupIds" id="pExportSizegroupIds" value="" /> 
			  <input type="hidden" name= "pmOpt" id="pmOpt" value="" />
			  <input type="hidden" name= "pWipFormat" id="pWipFormat" value="" /> 
			  <input type="hidden" name= "pCompOutwardInvoiceIds" id="pCompOutwardInvoiceIds" value="" />
			  <input type="hidden" name= "pMarketingLocationIds" id="pMarketingLocationIds" value="" />
			  <input type="hidden" name= "pWipCastingFormat" id="pWipCastingFormat" value="" />
			  <input type="hidden" name= "pMtlOutwardInvoiceIds" id="pMtlOutwardInvoiceIds" value="" /> 
 			  <input type="hidden" name="pInwardTypeIds" 	  	 id="pInwardTypeIds" value=""  />
 			  <input type="hidden" name="pFromDeptIds" 	  		 id="pFromDeptIds" value=""  />
 			  <input type="hidden" name="pDivisionIds" 	  	     id="pDivisionIds" value=""  />
 			  <input type="hidden" name="pRegionIds" 	  	     id="pRegionIds" value=""  />
 			  <input type="hidden" name="pCustomerTypeIds" 	  	 id="pCustomerTypeIds" value=""  />
 			  <input type="hidden" name="pBranchIds" 	  	 	 id="pBranchIds" value=""  />
 			  
 			  

						</div> <!-- End of 8 div -->
						
						
					<div class="col-sm-2"></div>  
					</div>
				<div class="col-sm-2"></div>
				
				<input type="hidden" id="metalStatus" name="metalStatus" />
				
				
			</div>






			<div class="col-xs-12">   


				<div class="col-xs-3" id="grpOrdId" style="display: none">

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>


					<div class="panel panel-primary">

						<div class="panel-body">

							<div align="center"
								style="font-style: italic; color: red; font-weight: bold;">
								<span style="font-size: 17px;">Grouping / Ordering</span>
							</div>

							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>



							<div class="row">
								<label for="inputLabel3" class="col-sm-4 control-label">Level
									1 :</label>
								<div class="col-sm-8">
									<div id="img1Id">
										<form:select path="image1" class="form-control"
											onchange="javascript:popLevelTwo();popLevelThree();">
											<form:option value="" label="- Select Level 1 -" />
											<form:options items="${comboLevelOne}" />
										</form:select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>

							<div class="row">
								<label for="inputLabel3" class="col-sm-4 control-label">Level
									2 :</label>
								<div class="col-sm-8">
									<div id="img2Id">
										<form:select path="image2" class="form-control"
											onchange="javascript:popLevelOne();popLevelThree();">
											<form:option value="" label="- Select Level 2 -" />
											<form:options items="${comboLevelTwo}" />
										</form:select>
									</div>
								</div>
							</div>


							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>

							<div class="row">
								<label for="inputLabel3" class="col-sm-4 control-label">Level
									3 :</label>
								<div class="col-sm-8">
									<div id="img3Id">
										<form:select path="image3" class="form-control"
											onchange="javascript:popLevelTwo();popLevelOne();">
											<form:option value="" label="- Select Level 2 -" />
											<form:options items="${comboLevelThree}" />
										</form:select>
									</div>
								</div>
							</div>



							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>

							<div align="center"
								style="font-style: italic; color: red; font-weight: bold;">
								<span style="font-size: 17px;">Report Format</span>
							</div>

							<div class="radio">
								<label><input type="radio" name="choiceRd"
									id="choiceDetail" value="Detail" checked><strong>Detail</strong></label>&nbsp;&nbsp;&nbsp;&nbsp;
								<label><input type="radio" name="choiceRd"
									id="choiceSummary" value="Summary"><strong>Summary</strong></label>
								&nbsp;
							</div>
						</div>
					</div>

				</div>


					<!-- <div class="row col-xs-3" id="reportFormatDiv" style="display:none">
							
							<div class="panel panel-primary" >
								<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-12 col-sm-12"  style="background-color:blue; color: white; text-align: center" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Report Format </label>
									</div>
								</div>
							</div>
						</div>	
							
							
							<div class="col-lg-12 col-sm-12"  id="rptFormatDropDownDivId">
								
							</div>	
						</div> -->

<!-- 


					<div class="row" id="dateDivId" style="display: none">
						
							<div class="col-xs-2"></div>
							<div id="orderDate" class="col-xs-8" style="display: none">


								<div class="row">
									<div class="col-xs-2">
										
											<label for="inputLabel4" class=".col-lg-2 text-right">Order
												Date </label>
												</div>
									
									<div class=" col-sm-3">
										<input type="text" class="form-control" name="fromOrdDate"
											id="fromOrdDate" autocomplete="off" />
									</div>

									<div class="col-sm-3">
										<input type="text" class="form-control" name="toOrdDate"
											id="toOrdDate" autocomplete="off" />
									</div>
								</div>

							</div>
						

						<div class="col-xs-2"></div>
						
					</div>
					End of order Date
					
					 -->
					

		
		
			<!-- 	<div class="row">
					
					<div classs="row" id="dispatchDate" style="display: none">					
							<div class="col-xs-2"></div>
							<div  class="col-xs-8">
					
								<div class="row">
										<div class="col-xs-2">	
											<label for="inputLabel4" class=".col-lg-2 text-right">Dispatch Date </label>
											
									</div>
									
									<div class=" col-sm-3">
										<input type="text" class="form-control" name="fromDelvDate"
											id="fromDelvDate" autocomplete="off" />
									</div>

									<div class="col-sm-3">
										<input type="text" class="form-control" name="toDelvDate"
											id="toDelvDate" autocomplete="off" />
									</div>
								</div>

							</div>

							<div class="col-xs-2"></div>
					</div> End of Dispatch date Id
					
</div> -->






<!-- 			<div class="row">
					<div id="betweenPeriod"  style="display: none">
				<div class="col-xs-2"></div>
							<div  class="col-xs-8">


								<div class="row">			
									<div class="col-xs-2">	
											<label for="inputLabel4" class=".col-lg-2 text-right">Between Period </label>
											
									</div>
									
									<div class=" col-sm-3">
										<input type="text" class="form-control" name="fromBetDate"
											id="fromBetDate" autocomplete="off" />
									</div>

									<div class="col-sm-3">
										<input type="text" class="form-control" name="toBetDate"
											id="toBetDate" autocomplete="off" />
									</div>
								</div>

							</div>

							<div class="col-xs-2"></div>
						</div>
			</div> End of betweenPeriod Row   -->



					

							<!-- 	productionPeriod -->

<!-- 
					<div id="productionPeriod" class="col-xs-3" style="display: none">

						<div class="panel panel-primary">

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-12 col-sm-12"
										style="background-color: blue; color: white; text-align: center">
										<label for="inputLabel4" class=".col-lg-2 text-right">Production
											Period </label>
									</div>
								</div>
							</div>



							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-6">
										<label for="inputLabel4" class=".col-lg-2 text-right">From
											Date : </label>
									</div>
									<div class="col-lg-6 col-sm-6">
										<label for="inputLabel4" class=".col-lg-2 text-right">To
											Date : </label>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-6">
										<input type="text" class="form-control" name="fromProdDate"
											id="fromProdDate" autocomplete="off" />
									</div>
									<div class="col-lg-6 col-sm-6">
										<input type="text" class="form-control" name="toProdDate"
											id="toProdDate" autocomplete="off" />
									</div>
								</div>
							</div>

						</div>

					</div> -->
<!-- 										productionPeriod  New UI
					<div class="row">
					<div id="productionPeriod"  style="display: none">
					<div class="col-xs-2"></div>
							<div  class="col-xs-8">


								<div class="row">			
									<div class="col-xs-2">	
											<label for="inputLabel4" class=".col-lg-2 ">Production Period </label>
											
									</div>
									
									<div class=" col-sm-3">
										<input type="text" class="form-control" name="fromProdDate"
											id="fromProdDate" autocomplete="off" />
									</div>

									<div class="col-sm-3">
										<input type="text" class="form-control" name="toProdDate"
											id="toProdDate" autocomplete="off" />
									</div>
								</div>

							</div>

							<div class="col-xs-2"></div>
						</div>
			</div> End of productionPeriod Row

 -->
					<!------------//---------- Delay condition ----//---------------->

				<!-- 	<div id="DelayCondDivId" class="col-xs-3" style="display: none">
						<label for="delayDay" class=".col-lg-2 text-right">Delay
							Days </label> <input type="number" id="delayDay" name="delayDay"
							class="form-control" min="0" pattern="[0-9]"
							onkeypress="return !(event.charCode == 46)" step="1"
							title="Must be an integer number">


					</div>


					<div id="PriorityCondDivId" class="col-xs-3" style="display: none">
						<label for="priority" class=".col-lg-2 text-right">Priority
						</label> <input type="text" id="priorityId" name="priorityId"
							class="form-control">


					</div> -->
			
			
<!-- 			
				----------//---------- Delay condition New Ui----//--------------
					<div class="row">
					
					<div class="col-xs-2"></div>
							<div  class="col-xs-8">
							
							
							
					<div id="DelayCondDivId" class="col-xs-4" style="display: none">
						<label for="delayDay" class=".col-lg-2 text-right">Delay
							Days </label> <input type="number" id="delayDay" name="delayDay"
							class="form-control" min="0" pattern="[0-9]"
							onkeypress="return !(event.charCode == 46)" step="1"
							title="Must be an integer number">


					</div>


					<div id="PriorityCondDivId" class="col-xs-4" style="display: none">
						<label for="priority" class=".col-lg-2 text-right">Priority
						</label> <input type="text" id="priorityId" name="priorityId"
							class="form-control">


					</div>
						

					

							<div class="col-xs-2"></div>
						</div>
			</div> End of Delay condition New Ui Row	
					
					 -->
					
					
					


					<!-- <div class="col-xs-3" id="deptStockDiv" style="display:none">
							
							<div class="panel panel-primary" >
								<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-12 col-sm-12"  style="background-color:blue; color: white; text-align: center" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Report Format </label>
									</div>
								</div>
							</div>
						</div>	
							
							
							<div class="col-lg-12 col-sm-12" >
								<select id="deptFormat" class="form-control" style="background:#CEF87A">
									<option value="BagWiseStock">Bag Wise Stock</option>
									<option value="ClientSummary">Client Wise Summary</option>
									<option value="DiamondBalClientSummary">DiamondBal Client Summary</option>
								</select>
							</div>	
						</div> -->


<!-- 					<div class="col-xs-3" id="changingRptDiv" style="display: none">

						<div class="panel panel-primary">
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-12 col-sm-12"
										style="background-color: blue; color: white; text-align: center">
										<label for="inputLabel4" class=".col-lg-2 text-right">Report
											Format </label>
									</div>
								</div>
							</div>
						</div>


						<div class="col-lg-12 col-sm-12">
							<select id="changingFormat" class="form-control"
								style="background: #CEF87A">
								<option value="ChangingIssue">Changing Issue</option>
								<option value="ChangingReturn">Changing Return</option>
								<option value="Broken">Broken</option>
								<option value="Fallen">Fallen</option>
							</select>
						</div>
					</div> -->



					<div  id="changingRptDiv" style="display: none">
						
						<!-- <div class="panel panel-primary"> -->
							<div class="row">
							<div class="col-xs-2"></div>
							<div class="col-xs-8">
							<div class="col-xs-2"></div>
							<div class="col-sm-3">
									<div  style="background-color: blue; color: white; text-align: center">
										<label for="inputLabel4" class=".col-lg-2 text-right">Report Format </label>
									</div>
								</div>
								<div class="col-sm-4">
									<select id="changingFormat" class="form-control" style="background: #CEF87A">
										<option value="ChangingIssue">Changing Issue</option>
										<option value="ChangingReturn">Changing Return</option>
										<option value="Broken">Broken</option>
										<option value="Fallen">Fallen</option>
									</select>
								</div>
							</div>		
									<div class="col-xs-2"></div>					
							<!-- </div> -->
						</div>


					<!-- 	<div class="col-lg-12 col-sm-12">
							<select id="changingFormat" class="form-control" style="background: #CEF87A">
								<option value="ChangingIssue">Changing Issue</option>
								<option value="ChangingReturn">Changing Return</option>
								<option value="Broken">Broken</option>
								<option value="Fallen">Fallen</option>
							</select>
						</div> -->
					</div>



					<!-- <div class="col-xs-3" id="valueAddDiv" style="display:none">
							
							
							<label for="inputLabel3" class="col-sm-4 control-label">Format</label>
							
							<div class="col-sm-8">
								<select id="valueAddFormat" class="form-control" style="background:#CEF87A">
									<option value="1">Gold With Dia</option>
									<option value="2">Bronze With Cz</option>
									</select>
								</div>	
								
						</div>				
						 -->


				<!-- 	<div class="col-xs-3" id="transactedFlgDivId" style="display: none">

						<div class="col-sm-8">

							<input type="checkbox" id="transactedId" title="Only Transacted" />
							<strong>Only Transacted</strong>
						</div>
					</div> -->

					<!-- 		<div id="ordCatalogDiv" class="col-xs-3" style="display: none">
					
						<div class="panel panel-primary" >
								<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-12 col-sm-12"  style="background-color:blue; color: white; text-align: center" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Report Format </label>
									</div>
								</div>
							</div>
						</div>		
						
							<div class="col-lg-12 col-sm-12" >
								<select id="ordCatalogFormat" class="form-control" style="background:#CEF87A">
									<option value="OrderCatalog">Order Catalog</option>
									<option value="SalesOrderCatalog">Sales Order Catalog</option>
									

								</select>
							</div>	
									
							
									
							</div> -->
					<!-- <div class="col-xs-3" id="quotCatlogDiv" style="display:none;">
						
							<div class="panel panel-primary" >
								<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-12 col-sm-12"  style="background-color:blue; color: white; text-align: center" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Report Format </label>
									</div>
								</div>
							</div>
						</div>	
						
							<div class="col-lg-12 col-sm-12" >
								<select id="quotationFormat" class="form-control" style="background:#CEF87A">
									<option value="formatOne">Format 1</option>
									<option value="formatTwo">Format 2</option>
									<option value="formatThree">Format 3</option>
									<option value="formatThreeBarCode">Format 3 With Barcode</option>
									
								</select>
							</div>	
						</div> -->

					<!-- <div class="col-xs-3" id="lossFormatDiv" style="display:none;">
							<div class="col-lg-8 col-sm-8" >
								<select id="lossFormat" class="form-control" style="background:#CEF87A">
									<option value="formatOne">Loss Report</option>
									<option value="formatTwo">Loss Report 10%</option>
																		
								</select>
							</div>	
						</div>	 -->



<!-- 					<div class="col-xs-4" id="costSheetSumDiv" style="display: none">
						<div class="col-lg-12 col-sm-12">
							<select id="costSheetFormat" class="form-control"
								style="background: #CEF87A">
								<option value="CostSheetSummary">Costsheet Summary</option>
								<option value="CostSheetSummaryNew">Costsheet Summary
									New</option>


							</select>
						</div>
					</div> -->

	
				<div  id="costSheetSumDiv" style="display: none">
							<div class="row">
							<div class="col-xs-2"></div>
							<div class="col-xs-8">
							
							<div class="col-sm-2">
									
								</div>
								<div class="col-sm-4">
									<select id="costSheetFormat" class="form-control" style="background: #CEF87A">
										<option value="CostSheetSummary">Costsheet Summary</option>
										<option value="CostSheetSummaryNew">Costsheet Summary New</option>
									</select>
								</div>
							</div>		
									<div class="col-xs-2"></div>					
							</div>
						</div> 






					<div class="col-xs-6" id="vAddSummryDiv" style="display: none;">
						<div class="col-lg-8 col-sm-8">
							<select id="vAddSummryFormat" class="form-control"
								style="background: #CEF87A">
								<option value="valueAdditionSummary">Value Addition
									Summary</option>
								<option value="valueAdditionStoneDetail">Value Addition
									Stone Detail</option>
							</select>
						</div>
					</div>

					<!-- <div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div> -->

				</div>				
				
				
			</div> <!-- "panel-body" id="generatedReportDesign" -->


			<!------------//---------- start of Date div and filter list div ----//---------------->
			<div class="row">  <!-- start of Date div and filter list div -->
				<div class="col-xs-12">
				
				<div class="row">
				<div id="reportFormatDiv"  style="display: none">	
						
							<div class="row">		
																																	
							
											<div class="col-xs-2"></div>					
										
										<div class="col-xs-8">
										<div class="row">
										
										<div class="col-xs-2">	
											<label for="reportFormat" class=".col-lg-2 text-left"> Report Format</label>	
											</div>
										
											<div class="col-xs-8">												
												<div id="rptFormatDropDownDivId" ></div>	
												
														
											</div>	
											
										</div>											
								</div>	
										<div class="col-xs-2"></div>								
									
							
							
							</div>
							
							
						</div>
						</div>

	
					<div class="row">
					<div id="dateDivId" style="display: none">
						<div class="row">
							<div class="col-xs-2"></div>
							
							<div id="orderDate" class="col-xs-8" style="display: none">
								<div class="row">
									<div class="col-xs-2">										
											<label for="inputLabel4" class=".col-lg-2 text-right">Order	Date </label>
									</div>
									
									<div class=" col-xs-3">
										<input type="text" class="form-control" name="fromOrdDate"
											id="fromOrdDate" autocomplete="off" />
									</div>

									<div class="col-xs-3">
										<input type="text" class="form-control" name="toOrdDate"
											id="toOrdDate" autocomplete="off" />
									</div>
								</div>

							</div>
							
						<div class="col-xs-2"></div>
						</div>
					</div><!-- End of order Date -->
					</div>



				<div class="row">
					
					<div id="dispatchDate" style="display: none">	
							<div class="row">		
							<div class="col-xs-2"></div>
							<div  class="col-xs-8">
					
								<div class="row">
										<div class="col-xs-2">	
											<label for="inputLabel4" class=".col-lg-2 text-right">Dispatch Date </label>
											
									</div>
									
									<div class=" col-sm-3">
										<input type="text" class="form-control" name="fromDelvDate"
											id="fromDelvDate" autocomplete="off" />
									</div>

									<div class="col-sm-3">
										<input type="text" class="form-control" name="toDelvDate"
											id="toDelvDate" autocomplete="off" />
									</div>
								</div>

							</div>

							<div class="col-xs-2"></div>
							
							</div>
					</div> <!-- End of Dispatch date Id -->
					
				</div>
				
			
				<div class="row">
				<div id="betweenPeriod"  style="display: none">
				<div class="row">
					
						<div class="col-xs-2"></div>
							<div  class="col-xs-8">
								<div class="row">			
									<div class="col-xs-2">	
											<label for="inputLabel4" class=".col-lg-2 text-right">Between Period </label>
											
									</div>	
									
									<div class=" col-sm-3">
										<input type="text" class="form-control" name="fromBetDate"
											id="fromBetDate" autocomplete="off" />
									</div>

									<div class="col-sm-3">
										<input type="text" class="form-control" name="toBetDate"
											id="toBetDate" autocomplete="off" />
									</div>
								</div>

							</div>
							<div class="col-xs-2"></div>
						</div>
			</div> 
			</div><!-- End of betweenPeriod Row -->

									<!-- 	productionPeriod  New UI-->
					<div class="row">
					<div id="productionPeriod"  style="display: none">
					<div class="row">
					<div class="col-xs-2"></div>
							<div  class="col-xs-8">


								<div class="row">			
									<div class="col-xs-2">	
											<label for="inputLabel4" class=".col-lg-1 ">Production Period </label>
											
									</div>
									
									<div class=" col-sm-3">
										<input type="text" class="form-control" name="fromProdDate"
											id="fromProdDate" autocomplete="off" />
									</div>

									<div class="col-sm-3">
										<input type="text" class="form-control" name="toProdDate"
											id="toProdDate" autocomplete="off" />
									</div>
								</div>

							</div>

							<div class="col-xs-2"></div>
						</div>
						</div>
			</div> <!-- End of productionPeriod Row -->

			
			
				
				<!------------//---------- Delay condition New Ui----//---------------->
				<!--	<div class="row">
					
					<div class="col-xs-2"></div>
						<div  class="col-xs-8">	
												
						 	<div id="DelayCondDivId" class="col-xs-4" style="display: none">
								<label for="delayDay" class=".col-lg-2 text-right">Delay
									Days </label> <input type="number" id="delayDay" name="delayDay"
									class="form-control" min="0" pattern="[0-9]"
									onkeypress="return !(event.charCode == 46)" step="1"
									title="Must be an integer number">
							</div> 
		
		
							<div id="PriorityCondDivId" class="col-xs-4" style="display: none">
								<label for="priority" class=".col-lg-2 text-right">Priority
								</label> <input type="text" id="priorityId" name="priorityId"
									class="form-control">

							</div> 
						
						</div>
						<div class="col-xs-2"></div>
			</div> -->
			<!-- End of Delay condition New Ui Row -->	
			
			<!------------//---------- Try Delay condition New Ui----//---------------->
			
							<div class="row">		
							<div id="DelayCondDivId"  style="display: none">
							<div class="row">								
							<div class="col-xs-2"></div>													
							<div class="col-xs-8">
								
								<div class="row">												
									<div class="col-xs-2">	
											<label for="delayDay" class=".col-lg-2 ">Delay Days </label>											
									</div>
									
									<div class=" col-xs-3">
										<input type="text" class="form-control" name="delayDay"
											id="delayDay" min="0" pattern="[0-9]" onkeypress="return !(event.charCode == 46)" step="1"
											title="Must be an integer number"/>
									</div>																																										
								</div>								
							</div>
							<div class="col-xs-2"></div>
							</div>
						</div> 
					</div> <!-- End of DelayCondition Row -->
						
									
							<div class="row">	
							<div id="PriorityCondDivId"  style="display: none">	
								<div class="row">								
							<div class="col-xs-2"></div>													
							<div class="col-xs-8">
									
										<div class="row">	
											<div class="col-xs-2">	
												<label for="priority" class=".col-lg-2 ">Priority </label>											
											</div>
											
											<div class="col-xs-3">												
												 <input type="text" id="priorityId" name="priorityId" class="form-control">			
											</div>										
																								
										</div>	
							</div>
							<div class="col-xs-2"></div>
							</div>
						</div>
					</div> <!-- End of PriorityCondition Row -->
					

			
			<!------------//---------- Try Delay condition New Ui   End  ----//---------------->
			
			
<!-- 					<div class="col-xs-3" id="transactedFlgDivId" style="display: none">

						<div class="col-sm-8">

							<input type="checkbox" id="transactedId" title="Only Transacted" />
							<strong>Only Transacted</strong>
						</div>
					</div> -->
			
			
				<!------------//----------  Only Transacted New Start  ----//---------------->		
					<div class="row">	
							<div id="transactedFlgDivId"  style="display: none">	
								<div class="row">								
							<div class="col-xs-2"></div>													
							<div class="col-xs-8">
									
										<div class="row">	
											<div class="col-xs-2">	
												<label for="onlyTransacted" class=".col-lg-2 "><strong>Only Transacted</strong> </label>											
											</div>
											
											<div class="col-xs-3">												
												<input type="checkbox" id="transactedId" title="Only Transacted" />		
											</div>										
																								
										</div>	
							</div>
							<div class="col-xs-2"></div>
							</div>
						</div>
					</div> <!-- End of Only Transacted Row -->
					
			<!------------//----------  Only Transacted End  ----//---------------->		
					
		</div>
		
		</div> <!--  End of row for Date and dispathck  start of Date div and filter list div-->




	<!------------//---------- Start of filter list div ----//---------------->
	
	<div class="row">  <!--  Start of List for Filter's  UI -->
	
	
						<div id="vFromDepartmentDiv"  style="display: none">	
						
							<div class="row">																												
							<div class="col-xs-12">	
											<div class="col-xs-2"></div>					
										
										<div class="col-xs-8">
										<div class="row">
										
										<div class="col-xs-2">	
											<label class=".col-lg-2 text-right"> Location</label>	
											</div>
										
											<div class="col-xs-8" id="vFromDepartmentId">												
												<select  class="form-control"></select>
											</div>	
											
										</div>											
								</div>	
										<div class="col-xs-2"></div>								
									
							</div>
							
							</div>
							
							<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>
							
						</div>
						
						
						
						
				</div> <!-- End of Report Format Row -->
	
					<!--//---------- client button------------//-->
				
				<div id="client" style="display: none">
				<div class="row">
				<!-- <div class="col-xs-12">	 -->			
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
						<!-- <div id="clientBtn" style="display: block">	 -->						
							<div class="row">
								<div class="col-xs-2">	
											<label for="client" class=".col-lg-2 text-right"> Client</label>	
								</div>
								<div class="col-xs-8">			
								
						<textarea type="text" name="clientTextBox" id="clientTextBox" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled" > 
										</textarea>
						
										</div>
								
						<div class="col-xs-2">	
																		
						<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myClientModal"
									onclick="javascript:popClient(0);"><span
							class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Client -->
						</a>&nbsp;										
						<a href="javascript:popClient(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>																				
						</div>	
						
							
							</div>			
							</div>		
						<div class="col-xs-2"></div>				
			</div>
					
					
		
			</div>  <!-- End of the Client -->
			
			
				
					<!-----------//----------Region New Ui--------//------>
			<div id="region" style="display: none">
			<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadRegion" class=".col-lg-2 ">Region</label>	
											</div>
								<div class="col-xs-8">			
									 <textarea  name="regionTextBox" id="regionTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myRegionModal"
								onclick="javascript:popRegion(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;
									</a>&nbsp;										
						<a href="javascript:popRegion(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
								</div>			
							</div>												
					 

						<!--//----------  Region list------------//-->

						<div id="regionTableDivId" style="display: block">
			
						</div>
					</div>  <!-- End of the Region button & List -->
					<div class="col-xs-2"></div>
				</div> <!-- End of Region Row -->
					</div>
					
					
					
							<!-----------//----------Customer Type New Ui--------//------>
					<div id="customerType" style="display: none">
					<div class="row">
								<div class="col-xs-2"></div>
								<div class="col-xs-8" >
									<div class="row">
										<div class="col-xs-2">	
													<label for="loadCustomerType" class=".col-lg-2 ">Customer Type</label>	
													</div>
										<div class="col-xs-8">			
											 <textarea  name="customerTypeTextBox" id="customerTypeTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
											 </textarea>
												</div>							
										<div class="col-xs-2">	
											<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myCustomerTypeModal"
										onclick="javascript:popCustomerType(0);"> <span
										class="glyphicon glyphicon-list"></span>&nbsp;
											</a>&nbsp;										
								<a href="javascript:popCustomerType(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
												data-placement="right" title="Clear Text " >
								</a>	
										</div>			
									</div>												
							 
		
								<!--//----------  customerType list------------//-->
		
								<div id="customerTypeTableDivId" style="display: block">
					
								</div>
							</div>  <!-- End of the customerType button & List -->
							<div class="col-xs-2"></div>
						</div> <!-- End of customerType Row -->
							</div>
							

				
					<!-----------//---------- OrderTypebutton ----------//-->
			
			<div id="orderType" style="display: none">
			<div class="row">
				<!-- <div class="col-xs-12">	 -->		
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
					<!-- 	<div id="orderTypeBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="orderType" class=".col-lg-2 text-right"> OrderType</label>	
											</div>
								<div class="col-xs-8">			
								<!-- <input type="text"  name="orderTypeTextBox" id="orderTypeTextBox" class=" form-control" disabled="disabled" > -->
					<textarea type="text" name="orderTypeTextBox" id="orderTypeTextBox" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled" > 
										</textarea> 
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myOrderTypeModal"
								onclick="javascript:popOrderType(0);">
								 <span class="glyphicon glyphicon-list"></span>&nbsp;<!--Load OrderType -->
									</a>&nbsp;										
						<a href="javascript:popOrderType(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>		
								</div>			
							</div>			
										
						</div>		

							
						<!--//----------  OrderType list------------//-->
					
					
			<div class="col-xs-2"></div>
				<!-- </div> -->
				
				</div> <!-- End of OrderType Row -->
				</div>
				
				
				
					
						<!-----------//----------Divison New Ui--------//------>
			<div id="division" style="display: none">
			<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadDivision" class=".col-lg-2 ">Division</label>	
											</div>
								<div class="col-xs-8">			
									 <textarea  name="divisionTextBox" id="divisionTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myDivisionModal"
								onclick="javascript:popDivision(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;
									</a>&nbsp;										
						<a href="javascript:popDivision(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
								</div>			
							</div>												
					 

						<!--//----------  Division list------------//-->

						<div id="divisionTableDivId" style="display: block">
			
						</div>
					</div>  <!-- End of the Division button & List -->
					<div class="col-xs-2"></div>
				</div> <!-- End of Division Row -->
					</div>
					
					
				
							
							
							
				<!-----------//----------BranchMaster New Ui--------//------>
					<div id="branchMaster" style="display: none">
					<div class="row">
								<div class="col-xs-2"></div>
								<div class="col-xs-8" >
									<div class="row">
										<div class="col-xs-2">	
													<label for="loadBranchMaster" class=".col-lg-2 ">Branch</label>	
													</div>
										<div class="col-xs-8">			
											 <textarea  name="branchMasterTextBox" id="branchMasterTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
											 </textarea>
												</div>							
										<div class="col-xs-2">	
											<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myBranchMasterModal"
										onclick="javascript:popBranchMaster(0);"> <span
										class="glyphicon glyphicon-list"></span>&nbsp;
											</a>&nbsp;										
								<a href="javascript:popBranchMaster(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
												data-placement="right" title="Clear Text " >
								</a>	
										</div>			
									</div>												
							 
		
								<!--//----------  BranchMaster list------------//-->
		
								<div id="branchMasterTableDivId" style="display: block">
					
								</div>
							</div>  <!-- End of the BranchMaster button & List -->
							<div class="col-xs-2"></div>
						</div> <!-- End of BranchMaster Row -->
							</div>
				


	<!-----------//---------- ORDER button  New UI--------//------>
 				<div id="order" style="display: none">
 			<div class="row">
			<!-- 	<div class="col-xs-12">	 -->			
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
						<div id="orderBtn" style="display: block">
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadOrder" class=".col-lg-2 text-right">Order</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchOrder" class="search form-control" placeholder="Search Order No  " /> -->
									<!-- <input type="text"  name="orderTextBox" id="orderTextBox" class=" form-control" disabled="disabled" >  -->
						<textarea type="text" name="orderTextBox" id="orderTextBox" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled" > 
										</textarea> 
										</div>
								
								<div class="col-xs-2">	
									
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myOrderModal"
								onclick="javascript:popOrder(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!--Load Order -->
									</a>&nbsp;		<!-- <input type="checkbox" id="closeOrdFlg"	title="Close Order" /> <strong>Close Order</strong> -->																			
						<a href="javascript:popOrder(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>		
								
								</div>			
							</div>													
						</div>

								<!-----------//---------- Order list ----------//-->
					</div> <!--  End of  ORDER button & List -->
					
					<div class="col-xs-2"></div>
			<!-- 	</div>  -->
				</div>  <!--  End of  ORDER button & List row -->
				</div>	
	
					<!-----------//---------- Order Style List still Old UI --------//------>
				<div id="orderStyleDiv" style="display: none">
				<div class="row">
			<!-- 	<div class="col-xs-12"> -->
				<div class="col-xs-2"></div>
					<div class="col-xs-9">	
					<!-- <div id="orderStyleDiv" class="col-xs-9" style="display: none"> -->
						<div id="orderStyleToolbar">
							<a class="btn btn-danger" style="font-size: 15px;" type="button"
								onclick="javascript:popOrderStyleList();"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;Load Order Style
							</a>	
						</div>

						<div id="orderStyleListTableDivId">
							<table id="orderStyleListTbl" data-toggle="table"
								data-height="350" data-search="true"
								data-toolbar="#orderStyleToolbar" data-pagination="true">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="srNo" data-width="1px">SrNo.</th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Order No.</th>
										<th data-field="style" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Style No.</th>
										<th data-field="purity" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Purity</th>
										<th data-field="color" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Color</th>
										<th data-field="pcs" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Pcs</th>
									</tr>
								</thead>
							</table>
						</div>
						<!-- <div class="row">
							<div class="col-xs-12">&nbsp;</div>
						</div> -->

					</div>
					<div class="col-xs-2"></div>
					
					</div>
					
					<!-- </div> -->
					</div>

					<!-----------//---------- DEPARTMENT button --------//------>
				<div id="department" style="display: none">
				<div class="row">
				<!-- <div class="col-xs-12">			 -->	
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
					<div id="departmentBtn" style="display: block">
							
					<div class="row">
						<div class="col-xs-2">	
											<label for="department" class=".col-lg-2 text-right">Department</label>	
								</div>
						<div class="col-xs-8">			
										<!-- <input type="text"  name="departmentTextBox" id="departmentTextBox" class=" form-control" disabled="disabled" > -->
						 <textarea type="text"  name="departmentTextBox" id="departmentTextBox" style="height: 1cm; resize: vertical;" 
									 class=" form-control" disabled="disabled">
						 </textarea>
										</div>
								
							<div class="col-xs-2">	
								<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myDepartmentModal"
								onclick="javascript:popDepartment(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!--Load Order -->
									</a>&nbsp;										
						<a href="javascript:popDepartment(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>		
									<!-- <input type="checkbox" id="balId" title="Balance" /> <strong>Balance </strong> -->
							</div>	
										
							<!-- <div class="col-xs-2">										
								<input type="checkbox" id="balId" title="Balance" /> <strong>Balance </strong> 
							</div> -->
												
					</div>	
					</div>
					<!-----------//---------- Department list ----------//-->

					</div> <!-- DEPARTMENT id button & LIst -->
					<div class="col-xs-2"></div>
				<!-- </div>  -->
  </div> <!-- End of DEPARTMENT  row -->
	</div>

					<!-----------//---------- LOCATION Button --------//------>
 							<div id="location" style="display: none">
 							<div class="row">
								<!-- 	<div class="col-xs-12"> -->
					
							<div class="col-xs-2"></div>
								<div class="col-xs-8">
									<!-- <div id="locationBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadLocation" class=".col-lg-2 text-right">Location</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchLocation" class="search form-control" placeholder="What you looking for ? " /> -->
			<textarea type="text"  name="locationTextBox" id="locationTextBox" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled">
			 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myLocationModal"
								onclick="javascript:popLocation(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Location -->
									</a>&nbsp;										
						<a href="javascript:popLocation(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>			
										</div>			
							</div>			
										
						<!-- </div>	 -->	

						<!--//---------- Location list------------//-->

						<div id="locationTableDivId" style="display: block">
							<!-- <table id="locationIdTbl" data-toggle="table" data-click-to-select="true" data-side-pagination="server"data-height="250">
								<thead><tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Location</th>
									</tr></thead></table> -->
						</div>
					</div>  <!-- End of the Location -->
			<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Location Row -->
			</div>

					<!-----------//----------MarketingLOCATION Button --------//------>

					<div id="marketingLocation" style="display: none">
					<div class="row">
									<!-- <div class="col-xs-12"> -->
							<div class="col-xs-2"></div>
								<div class="col-xs-8">
									<div id="marketingLocationBtn" style="display: block">
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="marketinglocation" class=".col-lg-2 text-right">Marketing Location</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchMarketingLocation" class="search form-control" placeholder="What you looking for ? " /> -->
			<textarea type="text"  name="marketingLocationTextBox" id="marketingLocationTextBox" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled">
			 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myMarketingLocationModal"
								onclick="javascript:popMarketingLocation(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Marketing Location -->
									</a>&nbsp;										
						<a href="javascript:popMarketingLocation(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>				
										</div>			
							</div>			
										
						</div>		

						<!--//----------Marketing  Location list------------//-->

						<div id="marketingLocationTableDivId" style="display: block">
									<!-- 	<table id="marketingLocationIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Location</th>
									</tr>
								</thead>
							</table> -->

						</div>
					</div>  <!-- End of the Marketing Location -->
			<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Marketing Location Row -->
				</div>
				
										<!-----------//---------- METAL button New UI--------//------>
						<div id="metal" style="display: none">
				
					<div class="row">
								<!-- 	<div class="col-xs-12"> -->
							<div class="col-xs-2"></div>
								<div class="col-xs-8">
						<!-- <div id="metalBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadMetal" class=".col-lg-2 text-right">Metal</label>	
											</div>
								<div class="col-xs-8">			
			<textarea type="text"  name="metalTextBox" id="metalTextBox" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled">
			 </textarea>
					</div>	
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myMetalModal"
								onclick="javascript:popMetal(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Metal -->
									</a>&nbsp;										
						<a href="javascript:popMetal(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
									<div id="chkBalMetal" style="display: none">
										<input type="checkbox" id="balIdMetal" title="Balance" /> <strong>Balance</strong>
									</div>

									<div id="chkBalComp" style="display: none">
										<input type="checkbox" id="balIdComp" title="Balance" /> <strong>Balance</strong>
									</div>
								</div>			
							</div>											
						<!-- </div>	 -->					
						<!-----------//---------- Metal list New UI-------//------>
								<div id="metalTableDivId" style="display: block">
									<!-- 		<table id="metalIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Metal</th>
									</tr>
								</thead>
							</table> -->
								</div>
							</div>  <!-- End of the Metal-->
					<div class="col-xs-2"></div>
						<!-- </div> -->
					</div>	<!-- End of the Metal Row -->				
					</div>
						
						<!-----------//---------- Style (design) button New Ui-------//------>
			<div id="design" style="display: none">
			<div class="row">
				<!-- <div class="col-xs-12"> -->
				
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
						<div id="designBtn" style="display: block">
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadStyle" class=".col-lg-2 text-right">Style</label>	
											</div>
								<div class="col-xs-8">			
								<!-- 	<input type="search" id="searchStyle" class="search form-control" placeholder="What you looking for ? " /> -->
								<textarea type="text"  name="designTextBox" id="designTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myDesignModal"
								onclick="javascript:popDesign(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Style -->
									</a>&nbsp;										
						<a href="javascript:popDesign(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
						</div>		


						<!--//----------  Design list------------//-->

						<div id="designTableDivId" style="display: none">
									<!-- <table id="designIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="srNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Sr No</th>
										<th data-field="designNo" data-align="left"
											data-sortable="true" style="font-weight: bolder;">Style</th>
									</tr>
								</thead>
							</table> -->

						</div>

					
					</div>  <!-- End of the Design -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Design Row -->
			</div>	
	

					<!--//---------- component button------------//-->

			<div id="component" style="display: none">
			<div class="row">
				<!-- <div class="col-xs-12">		 -->		
					<div class="col-xs-2"></div>
					<div class="col-xs-8">
				<!-- 		<div id="componentBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadComponent" class=".col-lg-2 text-right">Component</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchComponent" class="search form-control" placeholder="Search Component" /> -->
									<textarea type="text"  name="componentTextBox" id="componentTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myComponentModal"
								onclick="javascript:popComponent(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Component -->
									</a>&nbsp;										
						<a href="javascript:popComponent(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
						<!-- </div>	 -->	


						<!--//---------- component list------------//-->
					<div id="componentTableDivId" style="display: block">
									<!-- <table id="componentIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-pagination="false" data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">COMPONENT</th>
									</tr>
								</thead>
							</table> -->
						</div>				
					</div>  <!-- End of the Component -->
					<div class="col-xs-2"></div>
			<!-- 	</div> -->
			</div>	<!-- End of the Component Row -->
			</div>
			
					<!--//---------- Melting Invoice button------------//-->
						
				<div id="melting" style="display: none">		
				<div class="row">
				<!-- <div class="col-xs-12"> -->
				
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="meltingBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadMelting" class=".col-lg-2 text-right">Melting</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchMelting" class="search form-control" placeholder="Search Melting" /> -->
									<textarea type="text"  name="meltingTextBox" id="meltingTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myMeltingModal"
								onclick="javascript:popMelting(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Melting -->
									</a>&nbsp;										
						<a href="javascript:popMelting(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
					<!-- 	</div>	 -->	

						<!--//---------- Melting Invoice list------------//-->

					
								<div id="meltingTableDivId" style="display: block">
									<!-- <table id="meltingIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-pagination="false" data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Melting Invoice</th>
									</tr>

								</thead>
							</table> -->

						</div>

					
					</div>  <!-- End of the Melting -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Melting Row -->
				</div> 
				
					<!-----------//----------Purity button--------//------>						
					
					<div id="purityDivId" style="display: none">
						<div class="row">
			<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<div id="purityBtn" style="display: block">
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadPurity" class=".col-lg-2 text-right">Purity</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchPurity" class="search form-control" placeholder="What you looking for ? " /> -->									
									<textarea type="text"  name="purityTextBox" id="purityTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myPurityModal"
								onclick="javascript:popPurity(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Purity -->
									</a>&nbsp;										
						<a href="javascript:popPurity(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
						</div>		
		

						<!-----------//---------- Purity list--------//------>
					<div id="purityTableDivId" style="display: none">
									<!-- <table id="purityIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Purity</th>
									</tr>
								</thead>
							</table> -->

						</div>
					</div>  <!-- End of the Purity -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Purity Row -->
			</div>

					<!-----------//----------Color button--------//------>						
							<div id="colorDivId" style="display: none">
						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
					<!-- 	<div id="colorBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadColor" class=".col-lg-2 text-right">Color</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchColor" class="search form-control" placeholder="Search Color ? " /> -->
									<textarea type="text"  name="colorTextBox" id="colorTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myColorModal"
								onclick="javascript:popColor(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Color -->
									</a>&nbsp;										
						<a href="javascript:popColor(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
					<!-- 	</div>	 -->	
						<!-----------//---------- Color list--------//------>

						<div id="colorTableDivId" style="display: none">
							<!-- <table id="colorIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Color</th>
									</tr>
								</thead>
							</table> -->
						</div>
	</div>  <!-- End of the Color -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Color Row -->
			</div>						
							<!-----------//----------CATEGORY button New UI--------//------>
					
						<div id="category" style="display: none">
						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="categoryBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadColor" class=".col-lg-2 text-right">Category</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchCategory" class="search form-control" placeholder="What you looking for ? " /> -->
										<!-- <textarea type="text"  name="categoryTextBox" id=categoryTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea> -->
								 <textarea  name="categoryTextBox" id="categoryTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button"  data-toggle="modal" data-target="#myCategoryModal"
								onclick="javascript:popCategory(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Color -->
									</a>&nbsp;										
						<a href="javascript:popCategory(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
							

						<!-----------//---------- Category list--------//------>

						<div id="categoryTableDivId" style="display: none">
							<!-- <table id="categoryIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Category</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Category -->
					<div class="col-xs-2"></div>
			<!-- 	</div> -->
			</div>	<!-- End of the Category Row -->
			</div>	
					<!-----------//----------Sub Category button--------//------>

						<div id="subCategory" style="display: none">	
						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="subCategoryBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadSubCategory" class=".col-lg-2 text-right">Sub Category</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchSubCategory" class="search form-control" placeholder="What you looking for ? " /> -->
									<!-- <textarea type="text"  name="subCategoryTextBox" id=subCategoryTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea> -->
								 
								  <textarea  name="subCategoryTextBox" id="subCategoryTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button"  data-toggle="modal" data-target="#mySubCategoryModal"
								onclick="javascript:popSubCategory(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!--Load Sub Category -->
									</a>&nbsp;										
						<a href="javascript:popSubCategory(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
				<!-- 		</div>		 -->

						<!-----------//---------- Sub Category list--------//------>

						<div id="subCategoryTableDivId" style="display: none">
							<!-- <table id="subCategoryIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="categName" data-align="left"
											data-sortable="true" style="font-weight: bolder;">Category</th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Sub Category</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Sub Category -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Sub Category Row -->
			</div>	
			
				<!-----------//----------Version button--------//------>
					<div id="version" style="display: none">
					<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div id="version" class="col-xs-8" style="display: none">
						<div class="col-xs-8">
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadVersion" class=".col-lg-2 text-right">Version</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchVersion" class="search form-control" placeholder="What you looking for ? " /> -->
										<textarea type="text"  name="versionTextBox" id=versionTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea>								
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myVersionModal"
								onclick="javascript:popVersion(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!--Load Version -->
									</a>&nbsp;										
						<a href="javascript:popVersion(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>
									
									<div class=" col-sm-6" id="chkNoBbc" style="display: none">
								<input type="checkbox" id="chkNoBbcId" title="WithoutBbc" /> <strong>Without BBC</strong>
										</div>	
								</div>			
							</div>			
										
						</div>		
						

						<!-----------//---------- Version list--------//------>

						<div id="versionTableDivId" style="display: none">
						<!-- 	<table id="versionIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="version" data-align="left"
											data-sortable="true" style="font-weight: bolder;">Version</th>
									</tr>
								</thead>
							</table> -->
						
						</div>
					</div>  <!-- End of the Version -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Version Row -->
			</div>

					<!-----------//---------- CONCEPT button--------//------>

						<div id="concept" style="display: none">
						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="conceptBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadConcept" class=".col-lg-2 text-right">Concept</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchConcept" class="search form-control" placeholder="What you looking for ? " /> -->
							<textarea type="text"  name="conceptTextBox" id=conceptTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myConceptModal"
								onclick="javascript:popConcept(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!--Load Concept -->
									</a>&nbsp;										
						<a href="javascript:popConcept(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
						<!-----------//---------- Concept list--------//------>

						<div id="conceptTableDivId" style="display: none">
							<!-- <table id="conceptIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Concept</th>
									</tr>
								</thead>
							</table> -->
								</div>
					</div>  <!-- End of the Concept -->
					<div class="col-xs-2"></div>
			<!-- 	</div> -->
			</div>	<!-- End of the Concept Row -->
			</div>	

					<!-----------//----------Sub Concept button--------//------>

						<div id="subConcept" style="display: none">
						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="subConceptBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadSubConcept" class=".col-lg-2 text-right">Sub Concept</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchSubConcept" class="search form-control" placeholder="What you looking for ? " /> -->
									<textarea type="text"  name="subConceptTextBox" id=subConceptTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea>
									
									
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#mySubConceptModal"
								onclick="javascript:popSubConcept(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!--Load Sub Concept -->
									</a>&nbsp;										
						<a href="javascript:popSubConcept(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
						<!-- </div>		 -->

						<!-----------//---------- Sub Concept list--------//------>

						<div id="subConceptTableDivId" style="display: none">
							<!-- <table id="subConceptIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="conceptName" data-align="left"
											data-sortable="true" style="font-weight: bolder;">Concept</th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Sub Concept</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Sub Concept -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Sub Concept Row -->
			</div>
					<!-----------//---------- STONE TYPE button--------//------>							
						
						<div id="stoneType" style="display: none">
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="stoneTypeBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadStoneType" class=".col-lg-2 text-right">StoneType</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchStoneType" class="search form-control" placeholder="What you looking for ? " />
									<textarea type="text"  name="stoneTypeTextBox" id=stoneTypeTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" readonly="readonly">
								 </textarea>
								  -->
								 
								 
								 <textarea  name="stoneTypeTextBox" id="stoneTypeTextBox" style="height: 1cm; resize: vertical;" class=" form-control"  disabled="disabled" ></textarea> 
								 
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myStoneTypeModal"
								onclick="javascript:popStoneType(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!--Load StoneType -->
									</a>&nbsp;										
						<a href="javascript:popStoneType(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
						<!-- </div>	 -->	
						<!-----------//---------- StoneType list--------//------>

						<div id="stoneTypeTableDivId" style="display: none">
							<!-- <table id="stoneTypeIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">StoneType</th>
									</tr>
								</thead>
							</table> -->
						</div>

		</div>  <!-- End of the StoneType -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the StoneType Row -->
			</div>
			
					<!-----------//---------- SHAPE button--------//------>
					
						<div id="shape" style="display: none">
						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<div id="shapeBtn" style="display: block">
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadShape" class=".col-lg-2 text-right">Shape</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchShape" class="search form-control" placeholder="What you looking for ? " /> -->
								<!-- 	<textarea input="text" name="shapeTextBox" id=shapeTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea> -->
								 
								 <textarea  name="shapeTextBox" id="shapeTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" ></textarea> 
										</div>
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myShapeModal"
								onclick="javascript:popShape(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!--Load Shape -->
									</a>&nbsp;										
						<a href="javascript:popShape(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
						</div>							
												
						<!-----------//---------- Shape list--------//------>

						<!--<div id="shapeTableDivId" style="display: none">
							 <table id="shapeIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Shape</th>
									</tr>
								</thead>
							</table> 
						</div>-->
					</div>  <!-- End of the Shape -->
					<div class="col-xs-2"></div>
				<!-- </div> -->
			</div>	<!-- End of the Shape Row -->
			</div>
			
					<!-----------//---------- QUALITY --------//------>	
						
						<div id="quality" style="display: none">
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
						<!-- <div id="qualityBtn" style="display: block"> -->
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadQuality" class=".col-lg-2 text-right">Quality</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchQuality" class="search form-control" placeholder="What you looking for ? " /> -->
								<!-- 	<textarea name="qualityTextBox" id=qualityTextBox" style="height: 1cm; resize: vertical;" 
								class=" form-control" disabled="disabled">
								 </textarea> -->
								  <textarea  name="qualityTextBox" id="qualityTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" ></textarea>
								 
								 
										</div>
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myQualityModal"
								onclick="javascript:popQuality(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!--Load Quality-->
									</a>&nbsp;										
						<a href="javascript:popQuality(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
										</div>			
							</div>			
										
						<!-- </div>	 -->

						<div id="qualityTableDivId" style="display: none">
							<!-- <table id="qualityIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Quality</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Quality -->
					<div class="col-xs-2"></div>
			<!-- 	</div> -->
			</div>	<!-- End of the Quality Row -->
			</div>
				    
  		<!-----------//---------- Setting Type New Ui--------//------>
			<div id="settingType" style="display: none">
			<div class="row">
			<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
						<!-- <div id="settingTypeBtn" style="display: block"> -->						
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadSettingType" class=".col-lg-2 ">Setting Type</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchSettingType" class="search form-control" placeholder="What you looking for ?   " /> -->
									 <textarea  name="settingTypeTextBox" id="settingTypeTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#mySettingTypeModal"
								onclick="javascript:popSettingType(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!--Load OrderType -->
									</a>&nbsp;										
						<a href="javascript:popSettingType(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
								</div>			
							</div>												
						<!-- </div>-->
					 

						<!--//----------  Setting Type list------------//-->

						<div id="settingTypeTableDivId" style="display: block">
									<!-- <table id="settingTypeIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Setting Type</th>
									</tr>
								</thead>
							</table> -->

						</div>
					</div>  <!-- End of the Setting Type button & List -->
					<div class="col-xs-2"></div>
			<!-- 	</div>	 -->
				</div> <!-- End of Setting Type Row -->
					</div>
					
					
				
					
					
					
					
					
					
					<!-----------//---------- Inward Typebutton New Ui--------//------>
			<div id="inwardType" style="display: none">
			<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
								
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadInwardType" class=".col-lg-2 ">InwardType</label>	
											</div>
								<div class="col-xs-8">			
									 <textarea  name="inwardTypeTextBox" id="inwardTypeTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myInwardTypeModal"
								onclick="javascript:popInwardType(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;
									</a>&nbsp;										
						<a href="javascript:popInwardType" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
								</div>			
							</div>												
						<!-- </div>-->
					 

						<!--//----------  Inward Type list------------//-->

						<div id="inwardTableDivId" style="display: block">
								

						</div>
					</div>  <!-- End of the Setting Type button & List -->
					<div class="col-xs-2"></div>
			<!-- 	</div>	 -->
				</div> <!-- End of Setting Type Row -->
					</div>
					
					
					
					<!-----------//---------- Metal Inward Invoice button--------//------>
							<div id="mtlInwardInvoice" style="display: none">
						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
					<!-- 	<div id="mtlInwardInvoiceBtn" style="display: block">	 -->					
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadMtlInwardInvoice" class=".col-lg-2 ">Metal Inward</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchMtlInwardInvoice" class="search form-control" placeholder="What you looking for ?   " /> -->
									 <textarea  name="metalInwardTextBox" id="metalInwardTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
									
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myMetalInwardModal"
								onclick="javascript:popMtlInwardInvoice(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Metal Invoice -->
									</a>&nbsp;										
						<a href="javascript:popMtlInwardInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
								</div>			
							</div>												
						<!-- </div> -->
						<!-----------//---------- Metal Inward Invoice list ----------//-->

						<div id="mtlInwardInvoiceTableDivId" style="display: none">
							<!-- <table id="mtlInwardInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Invoice No</th>
									</tr>
								</thead>
							</table> -->
						</div>
					
					</div>  <!-- End of the  Metal Inward Invoice list button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
				</div> <!-- End of Metal Inward Invoice list Row -->
				</div>

						
					<!-----------//---------- Metal Outward Invoice button--------//------>
							<div id="mtlOutwardInvoice" style="display: none">
						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
					<!-- 	<div id="mtlInwardInvoiceBtn" style="display: block">	 -->					
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadMtlOutwardInvoice" class=".col-lg-2 ">Metal Outward</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchMtlInwardInvoice" class="search form-control" placeholder="What you looking for ?   " /> -->
									 <textarea  name="metalOutwardTextBox" id="metalOutwardTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
									
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myMetalOutwardModal"
								onclick="javascript:popMtlOutwardInvoice(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Metal Invoice -->
									</a>&nbsp;										
						<a href="javascript:popMtlOutwardInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
								</div>			
							</div>												
						<!-- </div> -->
						<!-----------//---------- Metal Outward Invoice list ----------//-->

						<div id="mtlOutwardInvoiceTableDivId" style="display: none">
							<!-- <table id="mtlOutwardInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Invoice No</th>
									</tr>
								</thead>
							</table> -->
						</div>
					
					</div>  <!-- End of the  Metal Outward Invoice list button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
				</div> <!-- End of Metal Outward Invoice list Row -->
				</div>
	
	
	
	
					<!-----------//---------- Comp Inward Invoice button--------//------>

						<div id="compInwardInvoice" style="display: none">
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="compInwardInvoiceBtn" style="display: block">	 -->					
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadCompInwardInvoice" class=".col-lg-2 "> Comp Inward </label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchCompInwardInvoice" class="search form-control" placeholder="What you looking for ?   " /> -->
										 <textarea  name="compInwardTextBox" id="compInwardTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
									 </div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myCompInwardModal"
								onclick="javascript:popCompInwardInvoice(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Comp Invoice -->
									</a>&nbsp;										
						<a href="javascript:popCompInwardInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
								</div>			
							</div>												
						<!-- </div> -->


						<!-----------//---------- Comp Inward Invoice list ----------//-->

						<div id="compInwardInvoiceTableDivId" style="display: none">
							<!-- <table id="compInwardInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Invoice No</th>
									</tr>
								</thead>
							</table> -->
						</div>				
					</div>  <!-- End of the Comp Inward Invoice button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
				</div> <!-- End of  Comp Inward Invoice Row -->
			</div>

					<!-----------//---------- Comp Outward Invoice button--------//------>

				<div id="compOutwardInvoice" style="display: none">
				<div class="row">
				<!-- <div class="col-xs-12"> -->
				<div class="col-xs-2"></div>
				<div class="col-xs-8">
				<!-- <div id="compOutwardInvoiceBtn" style="display: block"> -->						
					<div class="row">
						<div class="col-xs-2">	
									<label for="loadCompOutwardInvoice" class=".col-lg-2 "> Comp Outward </label>	
									</div>
						<div class="col-xs-8">			
							<!-- <input type="search" id="searchCompOutwardInvoice" class="search form-control" placeholder="What you looking for ?"/> -->
								<textarea  name="compOutwardTextBox" id="compOutwardTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
							 </textarea>
								</div>							
						<div class="col-xs-2">	
							<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myCompOutwardModal"
						onclick="javascript:popCompOutwardInvoice(0);"> <span
						class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Comp Invoice -->
							</a>&nbsp;										
						<a href="javascript:popCompOutwardInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>	
						</div>			
					</div>												
			<!-- 	</div> -->


						<!-----------//---------- Comp Outward Invoice list ----------//-->

						<div id="compOutwardInvoiceTableDivId" style="display: none">
							<!-- <table id="compOutwardInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Invoice No</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Comp Inward Invoice button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
				</div> <!-- End of  Comp Inward Invoice Row -->
				</div>	
										
 				<!-----------//---------- Export Invoice button New Ui--------//------>
					<div id="exportInvoice" style="display: none">
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
						
						<!-- 	<div class="row">
							
							<div class="col-xs-12">
							<div class="row">
							<div class="col-xs-2">
								<label >Set No.</label> </div>
								
								<div class="col-xs-8"> 
								<input type="number" id="setNoId" value="0">
								</div>	
							</div>
								</div>
							</div> -->
							
							<div class="row">
								<div class="col-xs-2">																							
									<label for="loadExportInvoice" class=".col-lg-2 ">Export Invoice</label>
								</div>							
								<div class="col-xs-5"> 
						
									<!-- <input type="search" id="searchExportInvoice" class="search form-control" placeholder="Search Export Invoice  " /> -->
									<textarea  name="exportInvoiceTextBox" id="exportInvoiceTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
								</div>							
								<div class="col-xs-1">															
										
									<a class="btn btn-info" style="font-size: 15px; " type="button"  data-toggle="modal" data-target="#myExportInvoiceModal"
									onclick="javascript:popExportInvoice(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load ExportInvoice -->
									</a>&nbsp;										
						<a href="javascript:popExportInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>
																		
								</div>	
									<div class="col-xs-4">
										<!-- <div class="row"> -->
										
											<label>Set No.</label> <input type="number" id="setNoId" value="0">
										<!-- </div> -->										
									</div>
									</div>
								</div>
																										
							<!-- </div>	 -->											
						<!-- </div> -->

							<!---------//---------- ExportInvoice list ----------//-->
							
						<div id="exportInvoiceTableDivId" style="display: none">
						<!-- 	<table id="exportInvoiceIdTbl" data-toggle="table"
								data-toolbar="#exportToolbar" data-side-pagination="server"
								data-pagination="true" data-search="true"
								data-checkbox-header="true" data-height="350"
								data-click-to-select="true">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Export Invoice</th>
									</tr>
								</thead>
							</table> -->
						<!-- </div> -->
						</div>  <!-- End of the ExportInvoice button & List -->
					<div class="col-xs-2"></div>
				<!-- </div> -->	
				</div> <!-- End of Export Invoice Row -->
		</div>


					<!-----------//---------- Export Invoice All button--------//------>

					<!-- 
					<div id="exportInvoiceAll" class="col-xs-6" style="display: none">
						<div id="exportAllToolbar">
							<a class="btn btn-danger" type="button" onclick="javascript:popExportInvoiceAll();"> <span class="glyphicon glyphicon-list"></span>Load</a>
						</div> -->
					<!-----------//---------- Export Invoice All button New Ui--------//------>
						
						<div id="exportInvoiceAll" style="display: none">
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
						<!-- <div id="exportAllToolbar"> -->
							<div class="row">
								<div class="col-xs-2">	
								<label for="loadExportInvoiceAll" class=".col-lg-2 ">Export Invoice All</label>	
									</div>
								<div class="col-xs-8">			
					<textarea  name="exportInvoiceAllTextBox" id="exportInvoiceAllTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button"  data-toggle="modal" data-target="#myExportInvoiceAllModal"
								onclick="javascript:popExportInvoiceAll(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load ExportInvoice -->
									</a>&nbsp;										
						<a href="javascript:popExportInvoiceAll(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>
									
								</div>												
							</div>												
					<!-- 	</div> -->

						<!-----------//---------- ExportInvoiceAll list ----------//-->
						<div id="exportInvoiceAllTableDivId" style="display: none">
							<!-- <table id="exportInvoiceAllIdTbl" data-toggle="table"
								data-toolbar="#exportAllToolbar" data-side-pagination="server"
								data-search="true" d data-height="350"
								data-click-to-select="true">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Export Invoice</th>
									</tr>
								</thead>
							</table> -->
						</div>
				</div>  <!-- End of the ExportInvoiceAll button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
				</div> <!-- End of Export Invoice All Row -->
			</div>

					<!-----------//---------- Export Invoice List /Invoice Style New Ui--------//------>
					<div id="exportListDiv" style="display: none">
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
					<!-- 	<div id="toolbar"> -->
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadExportInvoiceStyle" class=".col-lg-2 ">Export Style</label>	
											</div>
								<div class="col-xs-8">	
										<!-- 	<input type="text" class="form-control" name="exportStyle" id="exportStyleId" autocomplete="off" />	 -->		
							<textarea  name="exportInvoiceListTextBox" id="exportInvoiceListTextBox" style="height: 1cm; resize: vertical;"class=" form-control" 
							     disabled="disabled" >
									 </textarea>						
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myExportInvoiceListModal"
								onclick="javascript:popExportInvoiceList(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load ExportInvoiceStyle -->
									</a>&nbsp;										
						<a href="javascript:popExportInvoiceList(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text ">
						</a>									
								</div>											
							</div>												
						<!-- </div>  -->

						<!-----------//---------- ExportInvoice list ----------//-->
						<div id="exportInvoiceListTableDivId">
							<!-- <table id="exportInvoiceListTbl" data-toggle="table"
								data-search="true" data-toolbar="#toolbar"
								data-side-pagination="server" data-maintain-selected="true"
								data-pagination="true" data-height="350"
								data-response-handler="responseHandler">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Invoice No.</th>
										<th data-field="itemNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Barcode</th>
										<th data-field="ordRefNo" data-align="left"
											data-sortable="true" style="font-weight: bolder;">Ref
											No.</th>
										<th data-field="style" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Style No.</th>
										<th data-field="purity" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Purity</th>
										<th data-field="color" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Color</th>
									</tr>
								</thead>
							</table> -->
						</div>

					</div> <!-- End of the Export List OR Invoice Style button and list-->
					<div class="col-xs-2"></div>
				<!-- </div> -->	
				</div>  <!-- End of Export List OR Invoice Style Row -->
	</div>

					<!--//---------- Export Dia Quality------------//-->

				<!-- 	<div class="row">
						<div class="col-xs-12"> -->

			<!---------------------------------//---------- export Quality Button------------//-->

						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div id="exportQuality" class="col-xs-8" style="display: none">
						<!-- <div id="exportQualityBtn" style="display: block">	 -->				
						
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadExportQuality" class=".col-lg-2 ">Export Quality</label>	
											</div>
								<div class="col-xs-8">			
								<!-- <input type="search" id="searchExportQuality" class="search form-control" placeholder="Search Export Quality" /> -->
									<textarea  name="exportQualityTextBox" id="exportQualityTextBox" style="height: 1cm; resize: vertical;"class=" form-control" 
									disabled="disabled" > </textarea>		
									
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myExportQualityModal"
								onclick="javascript:popExportQuality(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Export Quality -->
									</a>&nbsp;										
						<a href="javascript:popExportQuality(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>
									
								</div>								
								</div>												
							<!-- </div>  -->
										<!--//---------- export Quality list------------//-->
								<div id="exportQualityTableDivId" style="display: none">
									<!-- <table id="exportQualityIdTbl" data-toggle="table"
										data-click-to-select="true" data-side-pagination="server"
										data-pagination="false" data-height="250">
										<thead>
											<tr>
												<th data-field="state" data-checkbox="true"></th>
												<th data-field="name" data-align="left" data-sortable="true"
													style="font-weight: bolder;">Quality</th>
											</tr>
										</thead>
									</table> --> 
								</div>
							 </div>   <!-- End of the Export Quality button & List -->
						<div class="col-xs-2"></div>
					<!-- </div>	 -->
					</div>     <!-- End of Export Quality Row -->
							
							<!--//---------- Export Size Group Button------------//-->
						<div id="exportSizegroup" style="display: none">		
						 <div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="exportSizegroupBtn" style="display: block">		 -->			
						
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadExportSizeGroup" class=".col-lg-2 ">Export Group</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchExportSizegroup" class="search form-control" placeholder="Search Export Group" /> -->
									
									<textarea  name="exportSizeGroupTextBox" id="exportSizeGroupTextBox" style="height: 1cm; resize: vertical;"class=" form-control" 
									disabled="disabled" > </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myExportSizeGroupModal"
								onclick="javascript:popExportSizegroup(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Export Group -->
									</a>&nbsp;										
						<a href="javascript:popExportSizegroup(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>
									
								</div>								
							<!-- 	</div>	 -->											
							</div>

								<!--//---------- Export Size Group list------------//-->
								<div id="exportSizegroupTableDivId" style="display: none">

									<!--<table id="exportSizegroupIdTbl" data-toggle="table"
										data-click-to-select="true" data-side-pagination="server"
										data-pagination="false" data-height="250">
										<thead>
											<tr>
												<th data-field="state" data-checkbox="true"></th>
												<th data-field="name" data-align="left" data-sortable="true"
													style="font-weight: bolder;">Size Group</th>
											</tr>
										</thead>
									</table> -->
								
							</div>
						 </div>  <!-- End of the Export Group button & List -->
							<div class="col-xs-2"></div>
					<!-- 	</div>	 -->
					</div> <!-- End of Export Group Row --> 

					</div>
					
				

					<!-----------//---------- Quotation Invoice button--------//------>
				<div id="quotationInvoice" style="display: none">
 				<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="quotToolbar" style="display: block">	 -->				
						
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadQuotation" class=".col-lg-2 ">Quotation</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="text" class="form-control" name="quotationInvoice"
											id="quotationInvoiceId" autocomplete="off" /> -->
											
					<textarea  name="quotationInvoiceTextBox" id="quotationInvoiceTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled">
						 </textarea>
						
								</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myQuotationInvoiceModal"
								onclick="javascript:popQuotationInvoice(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Quotation -->
									</a>&nbsp;										
						<a href="javascript:popQuotationInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>
									
								</div>								
								</div>												
							<!-- </div> -->

						<!-----------//---------- QuotationInvoice list ----------//-->
						<div id="quotationInvoiceTableDivId" style="display: none">
							<!-- <table id="quotationInvoiceIdTbl" data-toggle="table"
								data-toolbar="#quotToolbar" data-side-pagination="server"
								data-pagination="true" data-search="true"
								data-checkbox-header="false" data-height="350"
								data-single-select="true" data-click-to-select="true">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Quotation Invoice</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Quotation Group button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
			</div> <!-- End of Quotation Row -->
			</div>
			
			
					<!-----------//---------- Quot List /OR/ Details--------//------>


					<!-- <div id="quotListDiv" class="col-xs-12" style="display: none">
						<div id="quotListtoolbar">

							<a class="btn btn-danger" style="font-size: 15px;" type="button"
								onclick="javascript:popQuotInvoiceList();"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;Load Details
							</a>
						</div> -->
						<div id="quotListDiv" style="display: none">
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div class="col-xs-8">
						<!-- <div id="quotListtoolbar">		 -->			
						
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadDetails" class=".col-lg-2 ">Quotation Details</label>	
											</div>
								<div class="col-xs-8">			
									
				<textarea  name="quotationInvoiceListTextBox" id="chek12" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled">
						 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button"  data-toggle="modal" data-target="#myQuotationInvoiceListModal"
								onclick="javascript:popQuotInvoiceList(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Details -->
									</a>&nbsp;										
						<a href="javascript:popQuotInvoiceList(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>
									
								</div>								
								</div>												
							<!-- </div> -->
						
						<!-----------//---------- Quot Details list ----------//-->
						<div id="quotInvoiceListTableDivId">
							<!-- <table id="quotInvoiceListTbl" data-toggle="table"
								data-search="true" data-toolbar="#quotListtoolbar"
								data-side-pagination="server" data-maintain-selected="true"
								data-pagination="true" data-height="350"
								data-response-handler="responseHandler">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="barcode" data-align="left" data-sortable="true">Barcode</th>
										<th data-field="style" data-align="left" data-sortable="true">Style	No.</th>
										<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
										<th data-field="color" data-align="left" data-sortable="true">Color</th>
										<th data-field="pcs">Pcs</th>
										<th data-field="grossWt">Gross Wt.</th>
										<th data-field="netWt">Net Wt.</th>
										<th data-field="perPcFinalPrice">Per Pc Final Price</th>
										<th data-field="finalPrice">Final Price</th>

									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Quotation Details button & List -->
					<div class="col-xs-2"></div>
			<!-- 	</div> -->	
			</div> <!-- End of Quotation Details Row -->
			</div>
			
					<!-----------//----------Bag button--------//------>

						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div id="bags" class="col-xs-8" style="display: none">
						<!-- <div id="bagsBtn" style="display: block"> -->											
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadBags" class=".col-lg-2 ">Bags</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchBags" class="search form-control" placeholder="Search Bags  " /> -->
								<textarea  name="bagTextBox" id="bagTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled">
						 </textarea>									
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myBagModal"
									onclick="javascript:popBags(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Bags -->
									</a>&nbsp;										
						<a href="javascript:popBags(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>									
								</div>								
								</div>												
							<!-- </div> -->

						<!-----------//---------- Bags list ----------//-->
						<!-- <div id="bagsTableDivId" style="display: none">
							<table id="bagsIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Bag Name</th>
									</tr>
								</thead>
							</table> -->
						
						</div>  <!-- End of the Bags button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
			</div> <!-- End of Bags Row -->


					<!-----------//----------Employee button--------//------> 
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div id="employee" class="col-xs-8" style="display: none">
						<div id="employeeBtn" style="display: block">											
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadEmployee" class=".col-lg-2 ">Employee</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchEmployee" class="search form-control" placeholder="What you looking for ? " /> -->
									<textarea  name="employeeTextBox" id="employeeTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled">
						 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myEmployeeModal"
								onclick="javascript:popEmployee(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Employee -->
									</a>&nbsp;										
						<a href="javascript:popEmployee(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>									
								</div>								
								</div>												
							</div>
 
						<!-----------//---------- Employee list ----------//-->
						<div id="employeeTableDivId" style="display: none">
							<!-- <table id="employeeIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Employee Name</th>
									</tr>
								</thead>
							</table> -->
						</div>
							</div>  <!-- End of the Employee button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
			</div> <!-- End of Employee Row -->
						

					<!-----------//---------- Export Job Invoice button--------//------>
									
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div id="exportJobInvoice" class="col-xs-8" style="display: none">
						<div id="exportJobInvoiceBtn" style="display: block">											
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadExportJob" class=".col-lg-2 ">Export Job</label>	
											</div>
								<div class="col-xs-6">			
									<!-- <input type="search" id="searchExportJobInvoice" class="search form-control" placeholder="What you looking for ? " /> -->
						<textarea  name="exportJobInvoiceTextBox" id="exportJobInvoiceTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled">
						 </textarea>
										</div>							
								<div class="col-xs-4">	
							<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myExportJobInvoiceModal"
								onclick="javascript:popExportJobInvoice(0);"> <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Export Job -->
									</a>&nbsp;										
						<a href="javascript:popExportJobInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>									
								</div>								
								</div>												
							</div>

						<!-----------//---------- ExportJobInvoice list ----------//-->
						<div id="exportJobInvoiceTableDivId" style="display: none">
							<!-- <table id="exportJobInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Export Job Invoice</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the ExportJobInvoice  button & List -->
					<div class="col-xs-2"></div>
				<!-- </div> -->	
			</div> <!-- End of ExportJobInvoice  Row -->

					<!-----------//---------- InwardTypebutton ----------//-->
						
						<div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div id="inwardType" class="col-xs-8" style="display: none">
						<div id="inwardTypeBtn" style="display: block">											
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadInwardType" class=".col-lg-2 ">InwardType</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchInwardType" class="search form-control" placeholder="What you looking for ? " /> -->
							<textarea  name="inwardTypeTextBox" id="inwardTypeTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled">
						 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myInwardTypeModal"	
									onclick="javascript:popInwardType(0);">
									 <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load InwardType -->
									</a>&nbsp;										
						<a href="javascript:popInwardType(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>									
								</div>								
								</div>												
							</div>
						
						<!-----------//---------- InwardType list ----------//-->

						<div id="inwardTypeTableDivId" style="display: none">
							<!-- <table id="inwardTypeIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="name" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Inward Type</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the InwardType  button & List -->
					<div class="col-xs-2"></div>
				<!-- </div> -->	
			</div> <!-- End of InwardType Row -->



					<!-----------//---------- Stone Invoice button--------//------>

						<div class="row">
					<!-- 	<div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div id="stoneInvoice" class="col-xs-8" style="display: none">
						<div id="stoneInvoiceBtn" style="display: block">											
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadStoneInvoice" class=".col-lg-2 ">Stone</label>	
											</div>
								<div class="col-xs-8">			
									<!-- <input type="search" id="searchStoneInvoice" class="search form-control" placeholder="What you looking for ? " /> -->
						<textarea  name="stoneInvoiceTextBox" id="stoneInvoiceTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled">
						 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myStoneInvoiceModal"
										onclick="javascript:popStoneInvoice(0);">
									 <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Stone -->
									</a>&nbsp;										
						<a href="javascript:popStoneInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>									
								</div>								
								</div>												
							</div>
						<!-----------//---------- Stone Invoice list ----------//-->
						<div id="stoneInvoiceTableDivId" style="display: none">
							<!-- <table id="stoneInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true"
											style="font-weight: bolder;">Invoice No</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Stone Invoice  button & List -->
					<div class="col-xs-2"></div>
			<!-- 	</div> -->	
			</div> <!-- End of Stone Invoice Row -->
					
					<!-----------//---------- Stone Purchase Invoice button--------//------>

						 
						 <div class="row">
						<!-- <div class="col-xs-12"> -->
						<div class="col-xs-2"></div>
						<div id="stonePurcInvoice" class="col-xs-8" style="display: none">
						<div id="stonePurcInvoiceBtn" style="display: block">											
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadStonePurchaseInvoice" class=".col-lg-2 ">Stone Purchase</label>	
											</div>
								<div class="col-xs-6">			
									<!-- <input type="search" id="searchStonePurcInvoice" class="search form-control" placeholder="What you looking for ? " /> -->
						<textarea  name="stonePurcInvoiceTextBox" id="stonePurcInvoiceTextBox" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled">
						 </textarea>	
										</div>							
								<div class="col-xs-4">	
									<a class="btn btn-info" style="font-size: 15px; " type="button"	 data-toggle="modal" data-target="#myStonePurcInvoiceModal" 
									onclick="javascript:popStonePurcInvoice(0);">
									 <span class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Stone PurcInvoice -->
									</a>&nbsp;										
						<a href="javascript:popStonePurcInvoice(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>									
								</div>								
								</div>												
							</div>
						
						<!-----------//---------- Stone Invoice list ----------//-->
				<div id="stonePurcInvoiceTableDivId"  style="display: none">
							<!-- <table id="stonePurcInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								 data-height="250">
								<thead>
									<tr>
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Invoice No</th>
									</tr>
								</thead>
							</table> -->
						</div>
					</div>  <!-- End of the Stone Purchase Invoice  button & List -->
					<div class="col-xs-2"></div>
				<!-- </div>	 -->
			</div> <!-- End of Stone Purchase Invoice Row -->



					<input type="hidden" id="filterName" name="filterName"
						value="${filterName}" />

				</div>   <!-- End of List for Filter's  UI -->
				
				<div class= "row">
						<div class="col-xs-2"></div>				
							<div class="col-xs-8">
							<div class="col-xs-2"></div>
							<!-- <div class="col-xs-8"> -->
							<div class="row">
							<div class="col-xs-6">
									<div class="col-sm-4" align="left" style="display: none" id="rptBtnDivId">
										<input type="submit" value="Generate Report" id="genReportSubmitBtnId" class="btn btn-primary" />
									</div>
			
								
									<div class="col-sm-4" align="left" style="display: none"id="excelBtnDivId">
										<input type="submit" value="Generate Excel"	id="genExcelSubmitBtnId" class="btn btn-success" />
									</div>
									
									<div class="col-sm-4" align="left" style="display: none"id="wipBtnDivId">
										<input type="button" value="Generate WIP"	id="wipReportBtnId" 
										 class="btn btn-primary" onclick="javascript:generateWip()"/>
									</div>
									
									<div class="col-sm-4" align="left" style="display: none" id="wipCastingBtnDivId">
							<input type="button" value="Generate WIP "	id="wipCastingReportBtnId" 
							class="btn btn-primary"	onclick="javascript:generateWipCasting()" />
						</div>
								</div>	
								</div>
							<!-- </div> -->
							<div class="col-xs-2"></div>		
							</div>
						<div class="col-xs-2"></div>
				</div> <!-- End of button for Generate Report 7 Generate Excel row -->
			
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div> 
		


			<!------------//---------- end of filter list div ----//---------------->
			
			
	</div>
	<!-- xxxxxxxxxx ending 09  -->

<!-- xxxxxxxxxxxxxxx ending 12 -->

</form:form>



<input type="hidden" name="cId" id="cId" />
<input type="hidden" name="oTypeId" id="oTypeId" />


<div style="display: none">
	<form:form target="_blank"
		action="/jewels/manufacturing/masters/reports/download/report.html"
		cssClass="form-horizontal orderFilter">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Report" class="btn btn-primary"
					id="genReportss" /> <input type="hidden" id="timeValCommonPdf"
					name="timeValCommonPdf" />
			</div>
		</div>
	</form:form>
</div>


<div style="display: none">
	<form:form target="_blank"
		action="/jewels/manufacturing/masters/reports/download/wip/report.html"
		cssClass="form-horizontal orderFilter">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Report" class="btn btn-primary"
					id="genWipReportss" /> <input type="hidden"
					id="timeValCommonWipPdf" name="timeValCommonWipPdf" />
			</div>
		</div>
	</form:form>
</div>

<div style="display: none">
	<form:form target="_blank"
		action="/jewels/manufacturing/masters/reports/download/bagPrintReport.html"
		cssClass="form-horizontal orderFilterBagPrint">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Report" class="btn btn-primary"
					id="genBagPrintReport" /> <input type="hidden"
					id="timeValBagPrintPdf" name="timeValBagPrintPdf" />
			</div>
		</div>
	</form:form>
</div>

<div style="display: none">
	<form:form target="_blank"
		action="/jewels/manufacturing/masters/reports/download/orderStatusReport.html"
		cssClass="form-horizontal orderFilterStatus">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Report" class="btn btn-primary"
					id="genOrderStatusReport" /> <input type="hidden" id="timeValPdf"
					name="timeValPdf" />
			</div>
		</div>
	</form:form>
</div>

<div style="display: none">
	<form:form
		action="/jewels/manufacturing/masters/reports/download/excelReport.html"
		cssClass="form-horizontal orderFilterExcel">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Excel Report"
					class="btn btn-primary" id="genExcelReportss" />
			</div>
		</div>
	</form:form>
</div>

<!-- Download Common Excel Report -->

<div style="display: none">
	<form:form
		action="/jewels/manufacturing/masters/reports/download/Common/excelReport.html"
		cssClass="form-horizontal">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Excel Report"
					class="btn btn-primary" id="generateExcelReportss" />
			</div>
		</div>

		<input type='hidden' name='pFileName' id='pFileName' />

	</form:form>
</div>


<div style="display: none">
	<form:form
		action="/jewels/manufacturing/masters/reports/download/hmInvExcelReport.html"
		cssClass="form-horizontal orderFilterExcel">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Excel Report"
					class="btn btn-primary" id="genHmInvExcelReportss" />
			</div>
		</div>
	</form:form>
</div>


<div style="display: none">
	<form:form
		action="/jewels/manufacturing/masters/reports/download/excelReport/exportJobTag.html"
		cssClass="form-horizontal orderFilterExcel">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Excel Report"
					class="btn btn-primary" id="genJobTagsExcelReportss" />
			</div>
		</div>
	</form:form>
</div>

<div style="display: none">
	<form:form
		action="/jewels/manufacturing/masters/reports/download/deptExcelReport.html"
		cssClass="form-horizontal orderFilterExcel">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Excel Report"
					class="btn btn-primary" id="genDeptExcelReportss" />
			</div>
		</div>
	</form:form>
</div>

<div style="display: none">
	<form:form
		action="/jewels/manufacturing/masters/reports/download/exportPackingList.html"
		cssClass="form-horizontal orderFilterExcel">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" style="text-align: center">
				<input type="submit" value="Generate Excel Report"
					class="btn btn-primary" id="genExportPackingList" />
			</div>
		</div>
	</form:form>
</div>





</div>
<!-- ending the panel body -->



<!-- wip report -->


<div class="panel-body" id="wipGenerateReportId" style="display: none">
	<div class="col-sm-12">
	
						<div class="col-sm-2"></div>
							<div class="col-sm-8">
								<!-- <div class="row"> -->
								<div align="left" 
									style="font-style: italic; color: gray; font-weight: bold;">
									<span style="font-size: 25px;">${headingName}</span>
								</div>
								
					<!-- 		</div> -->
							</div>
						<div class="col-sm-2"></div>
			
					
					<!-- </div> -->														
			<div class="col-xs-12">
				<hr	style="width: 100%; color: red; height: 2px; background-color: red;" />
			</div>
			
		<div style="display: none" id="wipCloseBtnDivId">
			<input type="button" value="Return " id="wipCloseBtnId"
				class="btn btn-primary" onclick="javascript:closeWip()" />
		</div>

	
	</div>


	<div>
		<table id="wipTblId" class="MarkTotal" data-show-refresh="true"
			data-auto-refresh="false" data-show-export="true"
			data-striped="false" data-show-columns="true" data-height="500"
			data-row-style="rowStyle" data-search="true"
			data-toolbar="#wipCloseBtnDivId">
			<thead>
				<tr class="btn-primary">
				
				
				</tr>
			</thead>


		</table>


	</div>



</div>



<!-- wip Status -->

<div class="panel-body" id="wipStatusDivId" style="display: none">
	<div class="col-sm-12">
		<div style="display: none" id="wipStatusCloseBtnDivId">
			<input type="button" value="Return " id="wipStatusCloseBtnId"
				class="btn btn-primary" onclick="javascript:closeWipStatus()" />
		</div>

	</div>

	<div>
		<table id="wipStatusTblId" data-toggle="table" data-show-export="true"
			data-striped="false" data-pagination="false" data-show-columns="true"
			data-side-pagination="server" data-height="450">
			<thead>
				<tr class="btn-primary">
						 <th data-field="partyCode" data-align="left" data-sortable="true">Client Code</th>
						<th data-field="ordDate" data-align="left" data-sortable="true">Order Date</th>
						<th data-field="orderAging" data-align="left" data-sortable="true">Aging As Per Order</th>
						<th data-field="productionDate" data-align="left" data-sortable="true">Factory Confirm Date For Dia Rcvd Qty Only</th>
						<th data-field="bagNm" data-align="left" data-sortable="true">Bag</th>
						<th data-field="refNo" data-align="left" data-sortable="true">Customer Order No</th>
						
						<th data-field="invNo" data-align="left" data-sortable="true">Factory Order No</th>
						<th data-field="style" data-align="left" data-sortable="true">Factory Style</th>
						<th data-field="dtrefno" data-align="left" data-sortable="true">Ref No</th>
						<th data-field="dtitem" data-align="left" data-sortable="true">Item</th>
						<th data-field="ordRef" data-align="left" data-sortable="true">Ord Ref</th>
						<th data-field="ordnetwt" data-align="left" data-sortable="true">Net Wt as per order</th>
						<th data-field="ordreqcts" data-align="left" data-sortable="true">Ct Tw as per order </th>
						
						<th data-field="kt" data-align="left" data-sortable="true">Kt</th>
						<th data-field="color" data-align="left" data-sortable="true">Color</th>
						<th data-field="size" data-align="left" data-sortable="true">Ring Size</th>
						<th data-field="ordQty" data-align="left" data-sortable="true">Ord Pcs</th>
						<th data-field="bagQty" data-align="left" data-sortable="true">Bag Qty</th>
						<th data-field="department" data-align="left" data-sortable="true">Dept Status</th>
						<th data-field="locRecDate" data-align="left" data-sortable="true">Department in Date</th>
						<th data-field="ageing" data-align="left" data-sortable="true">Department Ageing</th>
						
						<th data-field="recWt" data-align="left" data-sortable="true">Gross Wt</th>
						<th data-field="metalWt" data-align="left" data-sortable="true">Net Wt</th>
						<th data-field="stoneWt" data-align="left" data-sortable="true">Dia Wt</th>
						<th data-field="colorstnWt" data-align="left" data-sortable="true">Col Wt</th>
						
						<th data-field="compDt" data-align="left" data-sortable="true">Actual Finding Wt</th>
						<th data-field="findingDetails" data-align="left" data-sortable="true">Finding Details</th>
						<!-- <th data-field="itemremark" data-align="left" data-sortable="true">Remark/Requirment</th>
						<th data-field="designremark" data-align="left" data-sortable="true">Design Remark</th> -->
						
						<th data-field="diarecPcs" data-align="left" data-sortable="true">Dia Rcvd Qty</th>
						<th data-field="diabalance" data-align="left" data-sortable="true">Dia Bal</th>
						<!-- <th data-field="stampinst" data-align="left" data-sortable="true">Stamp Inst.</th> -->
						
						
					</tr>
			</thead>
		</table>

	</div>

</div>


<!-- wip Status -->





<div class="panel-body" id="orderStatusDivId" style="display: none">
	<div class="col-sm-12">
		<div style="display: none" id="orderStatusCloseBtnDivId">
			<input type="button" value="Return " id="orderStatusCloseBtnId"
				class="btn btn-primary" onclick="javascript:closeOrderStatus()" />
		</div>


	</div>


	<div>
		<table id="OrderStatusTblId" data-toggle="table"
			data-show-export="true" data-striped="false" data-search="true"
			data-pagination="false" data-show-columns="true"
			data-toolbar="#orderStatusCloseBtnDivId" data-height="500">
			<thead>
				<tr class="btn-primary">

						<th data-field="partyNm" data-align="left" data-sortable="true">Party</th>
						 <th data-field="orderDate" data-align="left" data-sortable="true">Order Date</th>
						 <th data-field="dispatchDate" data-align="left" data-sortable="true">Fact. Confirm Date</th>
						 <th data-field="custOrderNo" data-align="left" data-sortable="true">Customer Order No</th>		
						 <th data-field="orderNo" data-align="left" data-sortable="true">Factory Order No</th>
						 <th data-field="mainStyleno" data-align="left" data-sortable="true">Factory Style</th>
						 <th data-field="dfRefNo" data-align="left" data-sortable="true">Ref No</th>	
						 <th data-field="item" data-align="left" data-sortable="true">Item</th>
						 <th data-field="ordRef" data-align="left" data-sortable="true">Ord Ref</th>
						 <th data-field="ordNetwt" data-align="left" data-sortable="true">1Pc Net Wt</th>
						 <th data-field="reqcarat" data-align="left" data-sortable="true">Ct Tw</th>
						 <th data-field="ktCol" data-align="left" data-sortable="true">Kt</th>
						<th data-field="sizeNm" data-align="left" data-sortable="true">Ring Size</th>
						<th data-field="qty" data-align="left" data-sortable="true">Qty</th>
						<th data-field="expqty" data-align="left" data-sortable="true">Export</th>
						<th data-field="ordBal" data-align="left" data-sortable="true">Bal To Ship</th>
						<th data-field="stninqty" data-align="left" data-sortable="true">Dia Rcvd Qty</th>
						<th data-field="diabal" data-align="left" data-sortable="true">Dia Bal</th>
						<th data-field="ordBalnetwt" data-align="left" data-sortable="true">Net Wt</th>
						<th data-field="ordBalpg" data-align="left" data-sortable="true">Pg</th>
						<th data-field="rate" data-align="left" data-sortable="true">Gold Lock</th>
						<th data-field="finalPrice" data-align="left" data-sortable="true">Final Price</th>
						
						<th data-field="deptstatus" data-align="left" data-sortable="true">Dept Status</th>
						<th data-field="ageing" data-align="left" data-sortable="true">Ageing</th>
						<th data-field="stampinst" data-align="left" data-sortable="true">Stamp Inst</th>
						<th data-field="compStatus" data-align="left" data-sortable="true">Finding Details</th>
						<th data-field="itemremark" data-align="left" data-sortable="true">Remark/Requirment</th>
						
				</tr>
			</thead>


		</table>


	</div>



</div>












</div>
<!-- ending the main panel -->



<script type="text/javascript">
	$(document)
			.ready(
					function(e) {

						fromDepartment();

					//	$('#generatedReportDesign #vFromDepartmentId').trigger();
						
					//	$.validator.setDefaults({ ignore: ":hidden:not(select)" });
									

						$('#pWipFormat').val("wipSummary");

						$('#pWipCastingFormat').val("wipCasting");

						displayReportFormat();

						//--Auto Date Code---//

						/* 
							$("#fromOrdDate").val(new Date().toLocaleDateString('en-GB'));
							$("#toOrdDate").val(new Date().toLocaleDateString('en-GB'));
							$("#fromDelvDate").val(new Date().toLocaleDateString('en-GB'));
							$("#toDelvDate").val(new Date().toLocaleDateString('en-GB'));
							$("#fromBetDate").val(new Date().toLocaleDateString('en-GB'));
							$("#toBetDate").val(new Date().toLocaleDateString('en-GB')); 
						 */

						//Search --- Function
						if ('${filterForm}' == 'wipSummary') {
							$('#wipBtnDivId').css('display', 'block');
							//popWipSummaryStructure();
						} else if ('${filterForm}' == 'wipStatus') {
							$('#wipStatusBtnDivId').css('display', 'block');

						} else if ('${filterForm}' == 'wipCasting') {
							$('#wipCastingBtnDivId').css('display', 'block');

						} else {
							$('#rptBtnDivId').css('display', 'block');
							$('#excelBtnDivId').css('display', 'block');

						}

						//----------client Search-------//

						$("#searchClient")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#clientIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------component Search-------//

						$("#searchComponent")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#componentIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();

																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------orderType Search-------//

						$("#searchOrderType")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#orderTypeIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();

																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------order Search-------//

						$("#searchOrder")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#orderIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	var idx = $row
																			.find(
																					'td:eq(2)')
																			.text();

																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0
																			&& idx
																					.toLowerCase()
																					.indexOf(
																							value
																									.toLowerCase()) != 0) {
																		/*   $(this).hide(); */

																		$(this)
																				.hide();
																	} else {

																		/*   $(this).show(); */
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------department Search-------//

						$("#searchDepartment")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#departmentIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------location Search-------//

						$("#searchLocation")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#locationIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------marketingLocation Search-------//

						$("#searchMarketingLocation")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#marketingLocationIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------metal Search-------//

						$("#searchMetal")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#metalIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------purity Search-------//

						$("#searchPurity")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#purityIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------Melting Search-------//

						$("#searchMelting")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#meltingIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------color Search-------//

						$("#searchColor")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#colorIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------category Search-------//

						$("#searchCategory")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#categoryIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------subcategory Search-------//

						$("#searchSubCategory")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#subCategoryIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {
																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	var subCatId = $row
																			.find(
																					'td:eq(2)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0
																			&& subCatId
																					.toLowerCase()
																					.indexOf(
																							value
																									.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------version Search-------//

						$("#searchVersion")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#versionIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {
																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------concept Search-------//

						$("#searchConcept")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#conceptIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------subConcept Search-------//

						$("#searchSubConcept")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#subConceptIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {
																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	var subConId = $row
																			.find(
																					'td:eq(2)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0
																			&& subConId
																					.toLowerCase()
																					.indexOf(
																							value
																									.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------stoneType Search-------//

						$("#searchStoneType")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#stoneTypeIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------shape Search-------//

						$("#searchShape")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#shapeIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------quality Search-------//

						$("#searchQuality")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#qualityIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------Setting Type Search-------//

						$("#searchSettingType")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#settingTypeIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------metal Inward Invoice Search-------//

						$("#searchMtlInwardInvoice")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#mtlInwardInvoiceIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------comp Inward Invoice Search-------//

						$("#searchCompInwardInvoice")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#compInwardInvoiceIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------comp Outward Invoice Search-------//

						$("#searchCompOutwardInvoice")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#compOutwardInvoiceIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------exportInvoice Search-------//

						$("#searchExportInvoice")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#exportInvoiceIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------quotationInvoice  Search-------//

						$("#searchQuotationInvoice")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#quotationInvoiceIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------bags Search-------//

						$("#searchBags")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#bagsIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------employee Search-------//

						$("#searchEmployee")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#employeeIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//-------Stone Invoice Search -----------------//

						$("#searchStoneInvoice")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#stoneInvoiceIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});
						
						
						
						
						
						
						//-------Stone purchase Invoice Search -----------------//
						
						$("#searchStonePurcInvoice").on("keyup", function() {
						    var value = $(this).val();
						    
						    $("#stonePurcInvoiceIdTbl tr").each(function(index) {
						
						        if (index != 0) {
				
						            $row = $(this);
						            var id = $row.find('td:eq(1)').text();  
						            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
						                $(this).hide();
						            }
						            else {
						                $(this).show();
						            }
						        }
						    });
						});
						
						

						//-------Inward Type Search -----------------//

						$("#searchInwardType")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#inwardTypeIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------exportJobInvoice Search-------//

						$("#searchExportJobInvoice")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#exportJobInvoiceIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();
																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});

						//----------Style Search-------//

						$("#searchStyle")
								.on(
										"keyup",
										function() {
											var value = $(this).val();

											$("#designIdTbl tr")
													.each(
															function(index) {

																if (index != 0) {

																	$row = $(this);
																	var id = $row
																			.find(
																					'td:eq(1)')
																			.text();

																	if (id
																			.toLowerCase()
																			.indexOf(
																					value
																							.toLowerCase()) != 0) {
																		$(this)
																				.hide();
																	} else {
																		$(this)
																				.show();
																	}
																}
															});
										});



						//----------Division Search-------//
						
						$("#searchDivision").on("keyup", function() {
						    var value = $(this).val();
						    
						    $("#divisionIdTbl tr").each(function(index) {
						
						        if (index != 0) {
						        	
						            $row = $(this);
						            var id = $row.find('td:eq(1)').text();  
						            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
						                $(this).hide();
						            }
						            else {
						                $(this).show();
						            }
						        }
						    });
						});


				//----------Region Search-------//
						
						$("#searchRegion").on("keyup", function() {
						    var value = $(this).val();
						    
						    $("#regionIdTbl tr").each(function(index) {
						
						        if (index != 0) {
						        	
						            $row = $(this);
						            var id = $row.find('td:eq(1)').text();  
						            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
						                $(this).hide();
						            }
						            else {
						                $(this).show();
						            }
						        }
						    });
						});


				//----------Region Search-------//
						
						$("#searchCustomerType").on("keyup", function() {
						    var value = $(this).val();
						    
						    $("#customerTypeIdTbl tr").each(function(index) {
						
						        if (index != 0) {
						        	
						            $row = $(this);
						            var id = $row.find('td:eq(1)').text();  
						            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
						                $(this).hide();
						            }
						            else {
						                $(this).show();
						            }
						        }
						    });
						});


						//----------BranchMaster Search-------//
						
						$("#searchBranchMaster").on("keyup", function() {
						    var value = $(this).val();
						    
						    $("#branchMasterIdTbl tr").each(function(index) {
						
						        if (index != 0) {
						        	
						            $row = $(this);
						            var id = $row.find('td:eq(1)').text();  
						            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
						                $(this).hide();
						            }
						            else {
						                $(this).show();
						            }
						        }
						    });
						});
						
						

						if (window.location.href.indexOf("ordDeptAndStyleWise") != -1) {
							$("#chkBal").css('display', 'block');
							$("#chkNoBbc").css('display', 'block');
							var dept = 1;
							$('#metalStatus').val(dept);
						}

						if (window.location.href.indexOf("metalAdjustment") != -1) {
							$("#chkBalMetal").css('display', 'block');
							var met = 2;
							$('#metalStatus').val(met);
						}

						if (window.location.href.indexOf("compAdjustment") != -1) {
							$("#chkBalComp").css('display', 'block');
							var comp = 3;
							$('#metalStatus').val(comp);
						}

						if (window.location.href.indexOf("departmentStock") != -1) {
							$('#grpOrdId').css('display', 'none');
							$('#deptStockDiv').css('display', 'block');
						}

						if (window.location.href.indexOf("changingReport") != -1) {
							$('#grpOrdId').css('display', 'none');
							$('#changingRptDiv').css('display', 'block');
						}

						if (window.location.href.indexOf("valueAddition") != -1) {
							$('#grpOrdId').css('display', 'none');
							$('#dateDivId').css('display', 'block');
							/* $('#valueAddDiv').css('display','block'); */
						}

						if (window.location.href.indexOf("orderCatalog") != -1) {
							$('#grpOrdId').css('display', 'none');
							$('#ordCatalogDiv').css('display', 'block');
						}

						if (window.location.href.indexOf("costSheetSummary") != -1) {
							$('#grpOrdId').css('display', 'none');
							$('#dateDivId').css('display', 'block');
							$('#costSheetSumDiv').css('display', 'block');
						}

						if (window.location.href.indexOf("quotationCatalog") != -1) {
							var rptnm = window.location.href;
							var tempnm = rptnm.substring(rptnm
									.indexOf("quotationCatalog"), rptnm
									.indexOf(".html"));

							$('#grpOrdId').css('display', 'none');
							$('#dateDivId').css('display', 'block');
							if (tempnm === "quotationCatalog") {
								$('#quotCatlogDiv').css('display', 'block');
							}
						}

						if (window.location.href
								.indexOf("valueAdditionSummary") != -1) {
							$('#grpOrdId').css('display', 'none');
							$('#dateDivId').css('display', 'block');
							$('#vAddSummryDiv').css('display', 'block');
						}

						if (window.location.href.indexOf("lossRepo") != -1) {
							$('#lossFormatDiv').css('display', 'block');
						}

						if (window.location.href.indexOf("diamondConsumption") != -1) {
							$('#grpOrdId').css('display', 'none');
							$('#dateDivId').css('display', 'none');

						}

						if (window.location.href.indexOf("diamondCustom") != -1) {
							$('#grpOrdId').css('display', 'none');

						}

						if (window.location.href.indexOf("wipEmail") != -1) {
							$('#genMailBtnId').css('display', 'block');
						}

						$("#fromOrdDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#toOrdDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#fromDelvDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#toDelvDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#fromBetDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#toBetDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#fromProdDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#toProdDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						//----------- hide and show code----------//---------

						//alert("filterName=="+$("#filterName").val()) ;

						if (!!$("#filterName").val()) {

							var str = $("#filterName").val().split(",");
							for (var i = 0; i < str.length; i++) {

								switch (str[i]) {

								case "orderDate":
									$('#dateDivId').css('display', 'block');
									$('#orderDate').css('display', 'block');
									break;

								case "transactedFlg":
									$('#transactedFlgDivId').css('display',
											'block');

									break;

								case "dispatchDate":
									$('#dateDivId').css('display', 'block');
									$('#dispatchDate').css('display', 'block');
									break;

								case "betweenPeriod":
									$('#dateDivId').css('display', 'block');
									$('#betweenPeriod').css('display', 'block');

									$('#fromBetDate').val('${currentDate}');
									$('#toBetDate').val('${currentDate}');

									break;

								case "productionPeriod":
									$('#dateDivId').css('display', 'block');
									$('#productionPeriod').css('display',
											'block');
									break;

								case "client":
									$('#client').css('display', 'block');
									//$('#clientTableDivId').css('display','block');
									break;

								case "component":
									$('#component').css('display', 'block');
									$('#componentTableDivId').css('display',
											'css');
									break;

								case "delayCond":
									$('#DelayCondDivId')
											.css('display', 'block');
									//$('#clientTableDivId').css('display','block');
									break;

								case "priorityCond":
									$('#PriorityCondDivId').css('display',
											'block');
									//$('#clientTableDivId').css('display','block');
									break;

								case "orderType":
									$('#orderType').css('display', 'block');
									$('#orderTypeTableDivId').css('display',
											'block');
									break;

								case "order":
									$('#order').css('display', 'block');
									$('#orderTableDivId').css('display',
											'block');
									break;

								case "orderStyle":
									$('#orderStyleDiv').css('display', 'block');
									break;

								case "department":
									$('#department').css('display', 'block');
									$('#departmentTableDivId').css('display',
											'block');
									break;

								case "location":
									$('#location').css('display', 'block');
									$('#locationTableDivId').css('display',
											'block');
									break;

								case "marketingLocation":
									$('#marketingLocation').css('display',
											'block');
									$('#marketingLocationTableDivId').css(
											'display', 'block');
									break;

								case "fromDepartment":
									$('#vFromDepartmentDiv').css('display','block');
									break;

								case "metal":
									$('#metal').css('display', 'block');
									$('#metalTableDivId').css('display',
											'block');
									break;

								case "purityFilter":
									$('#purityDivId').css('display', 'block');
									$('#purityTableDivId').css('display',
											'block');
									break;

								case "colorFilter":
									$('#colorDivId').css('display', 'block');
									$('#colorTableDivId').css('display',
											'block');
									break;

								case "category":
									$('#category').css('display', 'block');
									$('#categoryTableDivId').css('display',
											'block');
									break;

								case "subCategory":
									$('#subCategory').css('display', 'block');
									$('#subCategoryTableDivId').css('display',
											'block');
									break;

								case "version":
									$('#version').css('display', 'block');
									$('#versionTableDivId').css('display',
											'block');
									break;

								case "concept":
									$('#concept').css('display', 'block');
									$('#conceptTableDivId').css('display',
											'block');
									break;

								case "subConcept":
									$('#subConcept').css('display', 'block');
									$('#subConceptTableDivId').css('display',
											'block');
									break;

								case "stoneType":
									$('#stoneType').css('display', 'block');
									$('#stoneTypeTableDivId').css('display',
											'block');
									break;

								case "shape":
									$('#shape').css('display', 'block');
									$('#shapeTableDivId').css('display',
											'block');
									break;

								case "quality":
									$('#quality').css('display', 'block');
									$('#qualityTableDivId').css('display',
											'block');
									break;

								case "metalInwardInvoice":
									$('#mtlInwardInvoice').css('display',
											'block');
									$('#mtlInwardInvoiceTableDivId').css(
											'display', 'block');
									break;
					 				
							 	case "metalOutwardInvoice":
									$('#mtlOutwardInvoice').css('display',
											'block');
									$('#mtlOutwardInvoiceTableDivId').css(
											'display', 'block');
									break; 


								case "compInwardInvoice":
									$('#compInwardInvoice').css('display',
											'block');
									$('#compInwardInvoiceTableDivId').css(
											'display', 'block');
									break;

								case "compOutwardInvoice":
									$('#compOutwardInvoice').css('display',
											'block');
									$('#compOutwardInvoiceTableDivId').css(
											'display', 'block');
									break;

								case "exportInvoice":
									$('#exportInvoice').css('display', 'block');
									$('#exportInvoiceTableDivId').css(
											'display', 'block');
									break;

								case "exportInvoiceAll":
									$('#exportInvoiceAll').css('display',
											'block');
									$('#exportInvoiceAllTableDivId').css(
											'display', 'block');
									break;

								case "exportList":
									$('#exportListDiv').css('display', 'block');
									break;

								case "exportQuality":
									$('#exportQuality').css('display', 'block');
									$('#exportQualityTableDivId').css(
											'display', 'block');
									break;

								case "exportSizegroup":
									$('#exportSizegroup').css('display',
											'block');
									$('#exportSizegroupTableDivId').css(
											'display', 'block');
									break;

								case "quotList":
									$('#quotListDiv').css('display', 'block');
									break;

								case "quotationInvoice":
									$('#quotationInvoice').css('display',
											'block');
									$('#quotationInvoiceTableDivId').css(
											'display', 'block');
									break;

								case "bags":
									$('#bags').css('display', 'block');
									$('#bagsTableDivId')
											.css('display', 'block');
									break;

								case "employee":
									$('#employee').css('display', 'block');
									$('#employeeTableDivId').css('display',
											'block');
									break;

								case "exportJobInvoice":
									$('#exportJobInvoice').css('display',
											'block');
									$('#exportJobInvoiceTableDivId').css(
											'display', 'block');
									break;

								case "inwardType":
									$('#inwardType').css('display', 'block');
									$('#inwardTypeTableDivId').css('display',
											'block');
									break;

									

								case "stoneInvoice":
									$('#stoneInvoice').css('display', 'block');
									$('#stoneInvoiceTableDivId').css('display',
											'block');
									break;
									
									case "stonePurcInvoice":
						$('#stonePurcInvoice').css('display','block');
						$('#stonePurcInvoiceTableDivId').css('display','block');
						break;		

								case "melting":
									$('#melting').css('display', 'block');
									$('#meltingTableDivId').css('display',
											'block');
									break;

								case "design":
									$('#design').css('display', 'block');
									$('#designTableDivId').css('display',
											'block');
									break;

								case "grouping":
									$('#grpOrdId').css('display', 'block');
									break;

								case "settingType":
									$('#settingType').css('display', 'block');
									$('#settingTypeTableDivId').css('display',
											'block');
									break;

								
								case "division":
									$('#division').css('display', 'block');
									break;

								case "region":
									$('#region').css('display', 'block');
									break;

								case "customerType":
									$('#customerType').css('display', 'block');
									break;

								case "branchMaster":
									$('#branchMaster').css('display', 'block');
									break;
									
									
							}

								

							} // ending the for loop

						} else {
							$('#mianPanel').css('display', 'none');
						} // ending the if condition

					});
		
	
 
/* 	function popClientSave() {
		$("#clientIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/party/report/listing.html"
		});

		//$('#clientBtn').css('display','none');
		$('#clientTableDivId').css('display', 'block');
		$('#searchClient').val('');

	} */
	
	/*	function popComponent() {
		$('#componentIdTbl').bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/component/listing.html?opt=2"
		});

		$('#componentTableDivId').css('display', 'block');
		$('#searchComponent').val('');
	}

	/*		function popOrderType() {
		$("#orderTypeIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/orderType/listing.html?opt=3"
		});

		//$('#orderTypeBtn').css('display','none');
		$('#orderTypeTableDivId').css('display', 'block');
		$('#searchOrderType').val('');
	} */

/* 	function popInwardType() {
		$("#inwardTypeIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/inwardType/listing.html"
		});

		//$('#orderTypeBtn').css('display','none');
		$('#inwardTypeTableDivId').css('display', 'block');
		$('#searchInwardType').val('');
	} */

	/* function popOrder() {

		var orderFlg = "1";
		if ($("#closeOrdFlg").prop("checked") == true) {

			orderFlg = "2";
		} else {
			orderFlg = "1";
		}

		var clientData = $('#clientIdTbl').bootstrapTable('getSelections');
		var clientIds = $.map(clientData, function(item) {
			return item.id;
		});

		var orderTypeData = $('#orderTypeIdTbl')
				.bootstrapTable('getSelections');
		var orderTypeIds = $.map(orderTypeData, function(item) {
			return item.id;
		});

		$("#orderIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/order/report/listing.html?partyId="
									+ clientIds
									+ "&orderTypeId="
									+ orderTypeIds + "&ordFlg=" + orderFlg,
						});

		//$('#orderBtn').css('display','none');
		$('#orderTableDivId').css('display', 'block');
		$('#searchOrder').val('');
	} */

/* 	function popStoneInvoice() {

		var inwardTypeData = $('#inwardTypeIdTbl').bootstrapTable(
				'getSelections');
		var inwardTypeIds = $.map(inwardTypeData, function(item) {
			return item.id;
		});

		$("#stoneInvoiceIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/stoneInwardMt/report/listing.html?inwardTypeId="
									+ inwardTypeIds,
						});

		//$('#orderBtn').css('display','none');
		$('#stoneInvoiceTableDivId').css('display', 'block');
		$('#searchStoneInvoice').val('');
	}
	 */
	
	
	function popStonePurcInvoice() {
		
		
		var inwardTypeData = $('#inwardTypeIdTbl').bootstrapTable('getSelections');
		var inwardTypeIds = $.map(inwardTypeData, function(item) {
			return item.id;
		});
		
		$("#stonePurcInvoiceIdTbl").bootstrapTable('refresh', {
			
			url : "/jewels/manufacturing/transactions/stonePurcMt/report/listing.html?inwardTypeId="+inwardTypeIds+"&fromDate="+$('#fromBetDate').val()+"&toDate="+$('#toBetDate').val(),					
					
		});
		
		//$('#orderBtn').css('display','none');
		$('#stonePurcInvoiceTableDivId').css('display','block');
		$('#searchStonePurcInvoice').val('');
	}


	/*	function popCategory() {

		$("#categoryIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/category/listing.html?opt=3",
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#categoryTableDivId').css('display', 'block');
		$('#searchCategory').val('');
	}*/

	/*	function popPurity() {

		$("#purityIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/purity/listing.html?opt=3",
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#purityTableDivId').css('display', 'block');
		$('#searchPurity').val('');
	}*/

	/*function popMelting() {

		$("#meltingIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/meltingMt/listing.html?opt=0",
						});

		$('#meltingTableDivId').css('display', 'block');
		$('#searchMelting').val('');
	}*/

	/*	function popDesign() {

		var orderData = $('#orderIdTbl').bootstrapTable('getSelections');
		var orderIds = $.map(orderData, function(item) {
			return item.id;
		});

		$('#designIdTbl')
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/orderWiseStyle/report/listing.html?orderId="
									+ orderIds,
						});

		$('#designTableDivId').css('display', 'block');
		$('#searchStyle').val('');
	}*/

	 	/*function popDepartment() {

	$("#departmentIdTbl").bootstrapTable('refresh', {
		url : "/jewels/manufacturing/masters/department/listing.html?opt=4"
	});

	//$('#departmentBtn').css('display','none');
	$('#departmentTableDivId').css('display', 'block');
	$('#searchDepartment').val('');
	} */

/*function popLocation() {

	$("#locationIdTbl").bootstrapTable('refresh', {
		url : "/jewels/manufacturing/masters/department/listing.html?opt=3"
	});

	//$('#locationBtn').css('display','none');
	$('#locationTableDivId').css('display', 'block');
	$('#searchLocation').val('');
	}*/

/*function popMarketingLocation() {

	$("#marketingLocationIdTbl").bootstrapTable('refresh', {
		url : "/jewels/manufacturing/masters/department/listing.html?opt=5"
	});

	//$('#locationBtn').css('display','none');
	$('#marketingLocationTableDivId').css('display', 'block');
	$('#searchMarketingLocation').val('');
	}*/
	
/*function popMetal() {

	$("#metalIdTbl").bootstrapTable('refresh', {
		url : "/jewels/manufacturing/masters/metal/listing.html?opt=3"
	});

	//$('#metalBtn').css('display','none');
	$('#metalTableDivId').css('display', 'block');
	$('#searchMetal').val('');
	}*/
	
	/*function popColor() {

		$("#colorIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/color/listing.html?opt=2",
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#colorTableDivId').css('display', 'block');
		$('#searchColor').val('');
	} */

	
	
	/*function popSubCategory() {

		$("#subCategoryIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/subCategory/listing.html?opt=3",
						});

		//$('#stoneTypeBtn').css('display','none');
		$('#subCategoryTableDivId').css('display', 'block');
		$('#searchSubCategory').val('');
	}*/

	/*function popVersion() {

		$("#versionIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/version/reportList.html",
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#versionTableDivId').css('display', 'block');
		$('#searchVersion').val('');
	}*/

	/*function popConcept() {

		$("#conceptIdTbl").bootstrapTable('refresh', {
			url : '/jewels/manufacturing/masters/concept/listing.html?opt=3',
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#conceptTableDivId').css('display', 'block');
		$('#searchConcept').val('');
	}*/

	/*function popSubConcept() {

		$("#subConceptIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/subConcept/listing.html?opt=3",
						});

		//$('#stoneTypeBtn').css('display','none');
		$('#subConceptTableDivId').css('display', 'block');
		$('#searchSubConcept').val('');
	}*/

	/*function popStoneType() {

		$("#stoneTypeIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/stoneType/listing.html?opt=1"
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#stoneTypeTableDivId').css('display', 'block');
		$('#searchStoneType').val('');
	}*/

	/*function popShape() {

		$("#shapeIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/shape/listing.html?opt=3"
		});

		//$('#shapeBtn').css('display','none');
		$('#shapeTableDivId').css('display', 'block');
		$('#searchShape').val('');
	}*/

	/*function popQuality() {

		var shapeData = $('#shapeIdTbl').bootstrapTable('getSelections');
		var shapeIds = $.map(shapeData, function(item) {
			return item.id;
		});

		$("#qualityIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/quality/report/listing.html?shapeId="
									+ shapeIds
						});

		//$('#qualityBtn').css('display','none');
		$('#qualityTableDivId').css('display', 'block');
		$('#searchQuality').val('');
	}*/

/* 	function popSettingType() {

		$("#settingTypeIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/settingType/listing.html?opt=3"
						});

		$('#settingTypeTableDivId').css('display', 'block');
		$('#searchSettingType').val('');
	} */

	/* 	function popMtlInwardInvoice() {

		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
				|| !!$('#toBetDate').val()) {

			$("#mtlInwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/metalInwardMt/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {
			$("#mtlInwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/metalInwardMt/listing.html"
							});
		}

		//$('#orderBtn').css('display','none');
		$('#mtlInwardInvoiceTableDivId').css('display', 'block');
		$('#searchMtlInwardInvoice').val('');
	} */
/* 
	function popCompInwardInvoice() {

		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
				|| !!$('#toBetDate').val()) {

			$("#compInwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/compInwardMt/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {
			$("#compInwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/compInwardMt/listing.html"
							});
		}

		//$('#orderBtn').css('display','none');
		$('#compInwardInvoiceTableDivId').css('display', 'block');
		$('#searchCompInwardInvoice').val('');
	}
 */
	/* function popCompOutwardInvoice() {

		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {

			$("#compOutwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/compOutwardMt/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {
			$("#compOutwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/compOutwardMt/listing.html"
							});
		}

		//$('#orderBtn').css('display','none');
		$('#compOutwardInvoiceTableDivId').css('display', 'block');
		$('#searchCompOutwardInvoice').val('');
	} */

/* 	function popExportInvoice() {

		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
				|| !!$('#toBetDate').val()) {

			$("#exportInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingMt/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {
			$("#exportInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingMt/listing.html?opt=1"
							});
		}

		function popExportInvoice() {

			var data = $('#clientIdTbl').bootstrapTable('getSelections');
			var ids = $.map(data, function(item) {
				return item.id;
			});

			if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
					|| !!$('#toBetDate').val()) {

				$("#exportInvoiceIdTbl")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/transactions/costingMt/partyWiseListing.html?partyIds="
											+ ids
											+ "&fromDate="
											+ $('#fromBetDate').val()
											+ "&toDate="
											+ $('#toBetDate').val(),
								});

			} else {
				$("#exportInvoiceIdTbl")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/transactions/costingMt/listing.html?opt=1"
								});
			}

			//$('#orderBtn').css('display','none');
			$('#exportInvoiceTableDivId').css('display', 'block');
			$('#searchExportInvoice').val('');
		}

	}
 */
	/* function popExportInvoiceAll() {
		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
				|| !!$('#toBetDate').val()) {

			$("#exportInvoiceAllIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingMt/ExportInvoiceAll/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {

			$("#exportInvoiceAllIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingMt/ExportInvoiceAll/partyWiseListing.html?fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});
		}

		$('#exportInvoiceAllTableDivId').css('display', 'block');

	} */

	function popExportInvoiceList() {
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {
			$("#exportInvoiceListTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingDtItem/reportListing.html?mtIds="
										+ ids
										+ "&setNoId="
										+ $('#setNoId').val()
							});

		} else {

			displayMsg(this, null,
					'Invoice Not Selected ,Please Select Invoice ');

		}

	}

	function popOrderStyleList() {
		var data = $('#orderIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {
			$("#orderStyleListTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/orderDt/reportListing.html?mtIds="
										+ ids
							});

		} else {

			displayMsg(this, null, 'Order Not Selected ,Please Select Order ');

		}

	}

/* 	function popExportQuality() {
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {
			$("#exportQualityIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingStnDtItem/exportQualityListing.html?mtIds="
										+ ids
							});

		} else {

			displayMsg(this, null,
					'Invoice Not Selected ,Please Select Invoice ');

		}

	} */

/* 	function popExportSizegroup() {
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {
			$("#exportSizegroupIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingStnDtItem/exportSizegroupListing.html?mtIds="
										+ ids
							});

		} else {

			displayMsg(this, null,
					'Invoice Not Selected ,Please Select Invoice ');

		}

	} */

	function popQuotInvoiceList() {
		itemselections = [];
		var data = $('#quotationInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {

			$("#quotInvoiceListTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/quotDt/listing.html?mtId="
										+ ids
							});

		} else {

			displayMsg(this, null,
					'Invoice Not Selected ,Please Select Invoice ');

		}

	}

/* 	function popQuotationInvoice() {

		$("#quotationInvoiceIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/quotationMt/report/listing.html"
						});

		//$('#orderBtn').css('display','none');
		$('#quotationInvoiceTableDivId').css('display', 'block');
		//$('#searchQuotationInvoice').val('');
	} */

/* 	function popBags() {

		var orderData = $('#orderIdTbl').bootstrapTable('getSelections');
		var orderIds = $.map(orderData, function(item) {
			return item.id;
		});

		$("#bagsIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/bagMt/report/listing.html?orderIds="
									+ orderIds
						});

		//$('#orderBtn').css('display','none');
		$('#bagsTableDivId').css('display', 'block');
		$('#searchBags').val('');
	} */

	function popEmployee() {

		var deptData = $('#departmentIdTbl').bootstrapTable('getSelections');
		var deptIds = $.map(deptData, function(item) {
			return item.id;
		});

		$("#employeeIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/employee/report/listing.html?deptIds="
									+ deptIds
						});

		$('#employeeTableDivId').css('display', 'block');
		$('#searchEmployee').val('');
	}

	function popExportJobInvoice() {

		$("#exportJobInvoiceIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/costingJobMt/listing.html"
						});

		$('#exportJobInvoiceTableDivId').css('display', 'block');
		$('#searchExportJobInvoice').val('');
	}

	//optional hidden parameter for toggle excel and report button  --

	var mOpt;
	$("#genReportSubmitBtnId").click(function() {
		mOpt = 0;
	});

	$("#genExcelSubmitBtnId").click(function() {
		mOpt = 1;

	});

	$(document)
			.on(
					'submit',
					'form#ordFilter',
					function(e) {

						$
								.blockUI({
									message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>'
								});

						if (mOpt === 0) {
							$('#genReportSubmitBtnId').attr('disabled',
									'disabled');
						} else if (mOpt === 1) {
							$('#genExcelSubmitBtnId').attr('disabled',
									'disabled');
						}

						var data = "";
						var ids = "";

						$('#pmOpt').val(mOpt);

						$('#pFromDeptIds').val($('#vFromDepartmentId #fromDeptId').val());
						
						data = $('#stoneInvoiceIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pStoneInvoiceIds').val(ids);
						
						
						data = $('#stonePurcInvoiceIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pStonePurcInvoiceIds').val(ids);

						data = $('#clientIdTbl')
								.bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pClientIds').val(ids);

						data = $('#componentIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pComponents').val(ids);

						data = $('#meltingIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pMeltingInvoiceIds').val(ids);

						data = $('#designIdTbl')
								.bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pDesignStyleIds').val(ids);

						data = $('#orderTypeIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pOrderTypeIds').val(ids);

						data = $('#orderIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pOrderIds').val(ids);

						data = $('#departmentIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pDepartmentIds').val(ids);

						data = $('#locationIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pLocationIds').val(ids);

						data = $('#marketingLocationIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pMarketingLocationIds').val(ids);

						data = $('#metalIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pMetalIds').val(ids);

						data = $('#categoryIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pCategoryIds').val(ids);

						data = $('#subCategoryIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pSubCategoryIds').val(ids);

						data = $('#versionIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.version;
						});
						$('#pVersion').val(ids);

						data = $('#conceptIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pConceptIds').val(ids);

						data = $('#subConceptIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pSubConceptIds').val(ids);

						data = $('#stoneTypeIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pStoneTypeIds').val(ids);

						data = $('#shapeIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pShapeIds').val(ids);

						data = $('#qualityIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pQualityIds').val(ids);

						data = $('#settingTypeIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pSettingTypeIds').val(ids);

						data = $('#divisionIdTbl').bootstrapTable('getSelections');
							ids = $.map(data, function(item) {
								return item.id;
							});
						$('#pDivisionIds').val(ids);

						data = $('#regionIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pRegionIds').val(ids);

						data = $('#customerTypeIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pCustomerTypeIds').val(ids);


						data = $('#branchMasterIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pBranchIds').val(ids);

						data = $('#exportInvoiceIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pExportInvoiceIds').val(ids);

						data = $('#exportInvoiceAllIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pExportAllInvoiceIds').val(ids);

						data = $('#exportQualityIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pExportQualityIds').val(ids);

						data = $('#exportSizegroupIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pExportSizegroupIds').val(ids);

						data = $('#mtlInwardInvoiceIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pMtlInwardInvoiceIds').val(ids);
											
				 		data = $('#mtlOutwardInvoiceIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pMtlOutwardInvoiceIds').val(ids); 

						data = $('#compInwardInvoiceIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pCompInwardInvoiceIds').val(ids);

						data = $('#compOutwardInvoiceIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pCompOutwardInvoiceIds').val(ids);

						data = $('#quotationInvoiceIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pQuotationInvoiceIds').val(ids);

						data = $('#bagsIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pBagsIds').val(ids);

						data = $('#purityIdTbl')
								.bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pPurityIds').val(ids);

						data = $('#orderStyleListTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pSordDtIds').val(ids);

						data = $('#colorIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pColorIds').val(ids);

						data = $('#employeeIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pEmployeeIds').val(ids);

						data = $('#exportJobInvoiceIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pExportJobInvoiceIds').val(ids);

						data = $('#inwardTypeIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pInwardTypeIds').val(ids);	

						//checkbox

						if ($('#metalStatus').val() === '1') {
							var tempChkBoxBal = $('#balId').is(':checked');
							$("#pBal").val(tempChkBoxBal);
						} else if ($('#metalStatus').val() === '2') {
							var tempChkBoxMetBal = $('#balIdMetal').is(
									':checked');
							$("#pBal").val(tempChkBoxMetBal);
						} else {
							var tempChkBoxCompBal = $('#balIdComp').is(
									':checked');
							$("#pBal").val(tempChkBoxCompBal);
						}

						var tempchkNoBbc = $('#chkNoBbcId').is(':checked');
						$("#pNoBbc").val(tempchkNoBbc);

						$('#pFromOrdDate').val($("#fromOrdDate").val());
						$('#pToOrdDate').val($("#toOrdDate").val());
						$('#pFromDelvDate').val($("#fromDelvDate").val());
						$('#pToDelvDate').val($("#toDelvDate").val());
						$('#pFromBetDate').val($("#fromBetDate").val());
						$('#pToBetDate').val($("#toBetDate").val());

						$('#pToProdDate').val($("#toProdDate").val());
						$('#pFromProdDate').val($("#fromProdDate").val());

						//----Group--------//--------//

						$('#pGroup1').val($('#image1').val());
						$('#pGroup2').val($('#image2').val());
						$('#pGroup3').val($('#image3').val());
						$('#pFormat')
								.val(
										$(
												"input[type='radio'][name='choiceRd']:checked")
												.val());

						//---Departmemnt-Stock-Format---//

						var myVar = $('#rptFormatId').val();
						if (typeof myVar !== 'undefined') {
							$('#xml').val($('#rptFormatId').val());
						}

						$('#pDeptFormat').val($('#deptFormat').val());

						$('#pChangingFormat').val($('#changingFormat').val());

						$('#pOrdCatalogFormat').val(
								$('#ordCatalogFormat').val());

						$('#pCostSheetFormat').val($('#costSheetFormat').val());

						$('#pLossFormat').val($('#lossFormat').val());

						//---Quotation-Stock-Format---//

						$('#pQuotationFormat').val($('#quotationFormat').val());

						//---Value-Addition-Summary-Format---//

						$('#pVAddSummryFormat').val(
								$('#vAddSummryFormat').val());

						var tempChkBoxOnlyTransacted = $('#transactedId').is(
								':checked');
						$("#transactedFlg").val(tempChkBoxOnlyTransacted);

						$('#pPriorityIds').val($('#priorityId').val());
						$('#pSetNoId').val($('#setNoId').val());
						$('#pCostItemIds').val(itemselections);

						$('#pQuotDtIds').val(quotItemselections);

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$.ajax({
							url : formURL,
							type : "POST",
							data : postData,

							success : function(data, textStatus, jqXHR) {

								$.unblockUI();

								if (data.indexOf("^m^") != -1) {
									var tempVal = data.split("^m^");
									displayMsg(this, null, tempVal);
								}

								if (mOpt === 0) {

									if (data.indexOf("$") != -1) {
										var tempVal = data.split("$");

										if (tempVal[0] === '-1') {
											$('#timeValCommonPdf').val(
													tempVal[1]);
											$("#genReportss").trigger('click');
										} else if (tempVal[0] === '-4') {
											$('#timeValPdf').val(tempVal[1]);
											$("#genOrderStatusReport").trigger(
													'click');
										} else if (tempVal[0] === '-3') {
											$('#timeValBagPrintPdf').val(
													tempVal[1]);
											$("#genBagPrintReport").trigger(
													'click');
										} else {
											alert("Error , Contact admin");
										}
									} else {

										if (data === '-2') {
											$("#genExcelReportss").trigger(
													'click');
										} else if (data === '-6') {
											$("#genHmInvExcelReportss")
													.trigger('click');
										} else if (data === '-7') {
											$("#genJobTagsExcelReportss")
													.trigger('click');
										} else if (data === '-9') {
											$("#genDeptExcelReportss").trigger(
													'click');
										} else if (data === '-10') {
											$("#genExportPackingList").trigger(
													'click');
										} else {
											alert("Error , Contact admin");
										}

									}
								} else if (mOpt === 1) {
									if (data.indexOf("^m^") === -1) {

										$('#pFileName').val(data);
										$("#generateExcelReportss").trigger(
												'click');

									}

									$('#genExcelSubmitBtnId').removeAttr(
											'disabled');

								}

								$('#genReportSubmitBtnId').removeAttr(
										'disabled');

								$('#genExcelSubmitBtnId')
										.removeAttr('disabled');

							},
							error : function(jqXHR, textStatus, errorThrown) {

							}

						});

						e.preventDefault();

					});

	//-----level -- functions-------//

	function popLevelOne() {

		var tempLevOne = $('#image1').val();
		var pGroup = $('#image2').val() + "," + $('#image3').val();

		$
				.ajax({
					url : "/jewels/manufacturing/masters/reports/levelOne/combo.html?group="
							+ pGroup,
					type : "GET",
					success : function(data) {
						$('#img1Id').html(data);
						$("#image1 > [value=" + tempLevOne + "]").attr(
								"selected", "true");

					}
				});

	}

	function popLevelTwo() {

		var tempLevTwo = $('#image2').val();
		var pGroup = $('#image1').val() + "," + $('#image3').val();

		$
				.ajax({
					url : "/jewels/manufacturing/masters/reports/levelTwo/combo.html?group="
							+ pGroup,
					type : "GET",
					success : function(data) {
						$('#img2Id').html(data);
						$("#image2 > [value=" + tempLevTwo + "]").attr(
								"selected", "true");
					}
				});

	}

	function popLevelThree() {

		var tempLevThree = $('#image3').val();
		var pGroup = $('#image1').val() + "," + $('#image2').val();

		$
				.ajax({
					url : "/jewels/manufacturing/masters/reports/levelThree/combo.html?group="
							+ pGroup,
					type : "GET",
					success : function(data) {
						$('#img3Id').html(data);
						$("#image3 > [value=" + tempLevThree + "]").attr(
								"selected", "true");
					}
				});

	}

	//---------mail code wip--------//

	function processMailReport() {

		$
				.blockUI({
					message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>'
				});

		var data = "";
		var ids = "";

		data = $('#clientIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pClientIds').val(ids);

		data = $('#orderTypeIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderTypeIds').val(ids);

		data = $('#orderIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderIds').val(ids);

		$.ajax({
			url : "/jewels/manufacturing/masters/reports/wip/gen/email.html",
			data : {
				pClientId : $('#pClientIds').val() + "",
				pOrderTypeId : $('#pOrderTypeIds').val() + "",
				pOrderId : $('#pOrderIds').val() + "",
			},
			type : "POST",
			success : function(data) {
				$.unblockUI();
				if (data === "-1" || data === "-5") {
					displayMsg(this, null, 'Error Occoured , Contact Admin');
				} else {
					$('#timeValCommonWipPdf').val(data);
					$('#myMailModal').modal('show');
					getMailData();
				}
			}
		});

	}

	function viewRpt() {
		$("#genWipReportss").trigger('click');
	}

	function getMailData() {
		$.ajax({
			url : "/jewels/manufacturing/masters/reports/mailData.html",
			type : "POST",
			success : function(data) {
				if (data !== -1) {
					var temData = data.split("$");
					$('#toId').val(temData[0]);
					$('#subjectId').val(temData[1]);
					$('#txtEditor').Editor('setText', temData[2]);
				}
			}
		});
	}

	function sendMail() {

		//alert($("#txtEditor").Editor("getText"));

		$
				.blockUI({
					message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>'
				});
		$.ajax({
			url : "/jewels/manufacturing/masters/reports/send/mail.html",
			data : {
				fileNm : $('#timeValCommonWipPdf').val() + "",
				to : $('#toId').val() + "",
				cc : $('#ccId').val() + "",
				subject : $('#subjectId').val() + "",
				message : $("#txtEditor").Editor("getText") + "",
			},
			type : "POST",
			success : function(data) {
				$.unblockUI();

				if (data === '-1' || data === '-3') {
					displayMsg(this, null,
							"Error Occoured In Sending Mail, Contact Admin");
				} else {
					displayInfoMsg(this, null, "Mail Sent Successfully");
				}

			}
		});

	}

	var dynamicFlg = false;
	function generateWip() {

		$('#wipReportBtnId').attr('disabled', 'disabled');

		$
				.blockUI({
					message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>'
				});
		
		$('#wipGenerateReportId').css('display', 'block');
		
		$('#generatedReportDesign').css('display', 'none');
		$('#mianPanel').css('display', 'none'); //code to hide in wip then back to return button
		
		$('#wipBtnDivId').css('display', 'none');
		$('#wipCloseBtnDivId').css('display', 'block');

		data = $('#clientIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pClientIds').val(ids);

		data = $('#orderTypeIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderTypeIds').val(ids);

		data = $('#orderIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderIds').val(ids);

		data = $('#departmentIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pDepartmentIds').val(ids);
		
		
		
		data = $('#divisionIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
	$('#pDivisionIds').val(ids);

	data = $('#regionIdTbl').bootstrapTable('getSelections');
	ids = $.map(data, function(item) {
		return item.id;
	});
	$('#pRegionIds').val(ids);

	data = $('#customerTypeIdTbl').bootstrapTable('getSelections');
	ids = $.map(data, function(item) {
		return item.id;
	});
	$('#pCustomerTypeIds').val(ids);
		

		$('#pFromOrdDate').val($("#fromOrdDate").val());
		$('#pToOrdDate').val($("#toOrdDate").val());
		$('#pFromDelvDate').val($("#fromDelvDate").val());
		$('#pToDelvDate').val($("#toDelvDate").val());

		var tempColStruct = "";

		console.log('xxx   '+ $('#pWipFormat').val())

		if (dynamicFlg === false) {
			$
					.ajax({
						method : "GET",
						url : "/jewels/manufacturing/masters/reports/wipSummary/dynamicTable.html?pClientIds="
								+ $('#pClientIds').val()
								+ "&pOrderTypeIds="
								+ $('#pOrderTypeIds').val()
								+ "&pOrderIds="
								+ $('#pOrderIds').val()
								+ "&pFromOrdDate="
								+ $('#pFromOrdDate').val()
								+ "&pToOrdDate="
								+ $('#pToOrdDate').val()
								+ "&pFromDelvDate="
								+ $('#pFromDelvDate').val()
								+ "&pToDelvDate="
								+ $('#pToDelvDate').val()
								+ "&pDepartmentIds="
								+ $('#pDepartmentIds').val()
								+ "&pWipFormat="
								+ $('#pWipFormat').val(),

						dataType : "json",
						success : function(data) {

							var pData = JSON.stringify(data);
							if (pData.indexOf("error") != '-1') {
								displayMsg(this, null, data);
							} else {
								var columns = [];
								var colWidth = "";
								$
										.each(
												data.rows,
												function(key, value) {
													if (tempColStruct.length > 0) {
														tempColStruct += ',"'
																+ value.field
																+ '":' + '""';
													} else {
														tempColStruct = '"'
																+ value.field
																+ '":' + '""';
													}

													colWidth = '100px !important';

													columns
															.push({
																field : value.field,
																title : value.title,
																sortable : false,
																checkbox : value.field === 'state' ? true
																		: false,
																width : colWidth
															});
												});

								initDtTable(columns);
								dynamicFlg = true
							}

						},
						async : false,
						error : function(jqXHR, textStatus, errorThrown) {
							displayMsg(this, null,
									"Error : Creating structure , Contact Support");
						}
					});

		}

		if (dynamicFlg === true) {

			$('#wipTblId')
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/reports/generateWip.html?pClientIds="
										+ $('#pClientIds').val()
										+ "&pOrderTypeIds="
										+ $('#pOrderTypeIds').val()
										+ "&pOrderIds="
										+ $('#pOrderIds').val()
										+ "&pFromOrdDate="
										+ $('#pFromOrdDate').val()
										+ "&pToOrdDate="
										+ $('#pToOrdDate').val()
										+ "&pFromDelvDate="
										+ $('#pFromDelvDate').val()
										+ "&pToDelvDate="
										+ $('#pToDelvDate').val()
										+ "&pDepartmentIds="
										+ $('#pDepartmentIds').val()
										+ "&pWipFormat="
										+ $('#rptFormatId').val()
										+"&pDivisionIds="
										+$('#pDivisionIds').val()
										+"&pRegionIds="
										+$('#pRegionIds').val()
										+"&pCustomerTypeIds="
										+$('#pCustomerTypeIds').val(),

							});

		}

		$('#wipTblId').bootstrapTable({}).on('load-success.bs.table',
				function(e, data) {

					$.unblockUI();

					$('#wipReportBtnId').removeAttr('disabled');

				})

	}

	var dynamicCastFlg = false;
	function generateWipCasting() {

		$('#wipCastingReportBtnId').attr('disabled', 'disabled');

		$
				.blockUI({
					message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>'
				});

		$('#wipGenerateReportId').css('display', 'block');
		$('#generatedReportDesign').css('display', 'none');
		$('#mianPanel').css('display', 'none'); //code to hide in wip then back to return button
		$('#wipStatusBtnDivId').css('display', 'none');
		$('#wipCloseBtnDivId').css('display', 'block');

		data = $('#clientIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pClientIds').val(ids);

		data = $('#orderTypeIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderTypeIds').val(ids);

		data = $('#orderIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderIds').val(ids);

		data = $('#departmentIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pDepartmentIds').val(ids);

		$('#pFromOrdDate').val($("#fromOrdDate").val());
		$('#pToOrdDate').val($("#toOrdDate").val());
		$('#pFromDelvDate').val($("#fromDelvDate").val());
		$('#pToDelvDate').val($("#toDelvDate").val());

		var tempColStruct = "";

		if (dynamicFlg === false) {
			$
					.ajax({
						method : "GET",
						url : "/jewels/manufacturing/masters/reports/wipCasting/dynamicTable.html?pClientIds="
								+ $('#pClientIds').val()
								+ "&pOrderTypeIds="
								+ $('#pOrderTypeIds').val()
								+ "&pOrderIds="
								+ $('#pOrderIds').val()
								+ "&pFromOrdDate="
								+ $('#pFromOrdDate').val()
								+ "&pToOrdDate="
								+ $('#pToOrdDate').val()
								+ "&pFromDelvDate="
								+ $('#pFromDelvDate').val()
								+ "&pToDelvDate="
								+ $('#pToDelvDate').val()
								+ "&pDepartmentIds="
								+ $('#pDepartmentIds').val()
								+ "&pWipCastingFormat="
								+ $('#pWipCastingFormat').val(),

						dataType : "json",
						success : function(data) {

							var pData = JSON.stringify(data);
							if (pData.indexOf("error") != '-1') {
								displayMsg(this, null, data);
							} else {
								var columns = [];
								var colWidth = "";
								$
										.each(
												data.rows,
												function(key, value) {
													if (tempColStruct.length > 0) {
														tempColStruct += ',"'
																+ value.field
																+ '":' + '""';
													} else {
														tempColStruct = '"'
																+ value.field
																+ '":' + '""';
													}

													colWidth = '100px !important';

													columns
															.push({
																field : value.field,
																title : value.title,
																sortable : false,
																checkbox : value.field === 'state' ? true
																		: false,
																width : colWidth
															});
												});

								initDtTable(columns);
								dynamicFlg = true
							}

						},
						async : false,
						error : function(jqXHR, textStatus, errorThrown) {
							displayMsg(this, null,
									"Error : Creating structure , Contact Support");
						}
					});

		}

		if (dynamicFlg === true) {

			$('#wipTblId')
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/reports/generateWipCasting.html?pClientIds="
										+ $('#pClientIds').val()
										+ "&pOrderTypeIds="
										+ $('#pOrderTypeIds').val()
										+ "&pOrderIds="
										+ $('#pOrderIds').val()
										+ "&pFromOrdDate="
										+ $('#pFromOrdDate').val()
										+ "&pToOrdDate="
										+ $('#pToOrdDate').val()
										+ "&pFromDelvDate="
										+ $('#pFromDelvDate').val()
										+ "&pToDelvDate="
										+ $('#pToDelvDate').val()
										+ "&pDepartmentIds="
										+ $('#pDepartmentIds').val()
										+ "&pWipCastingFormat="
										+ $('#rptFormatId').val(),

							});

		}

		$('#wipTblId').bootstrapTable({}).on('load-success.bs.table',
				function(e, data) {

					$.unblockUI();

					$('#wipCastingReportBtnId').removeAttr('disabled');

				})

	}

	function closeWip() {
		$('#wipGenerateReportId').css('display', 'none');
		$('#generatedReportDesign').css('display', 'block');
		$('#mianPanel').css('display', 'block'); // back to return button to back wip		
		$('#wipCloseBtnDivId').css('display', 'none');

		if ('${filterForm}' == 'wipSummary') {
			$('#wipBtnDivId').css('display', 'block');
			//popWipSummaryStructure();
		} else if ('${filterForm}' == 'wipCasting') {
			$('#wipCastingBtnDivId').css('display', 'block');

		}
		//	$('#wipBtnDivId').css('display','block');
		//	$('#wipCastingBtnDivId').css('display','block');	

	}

	function closeWipStatus() {
		$('#wipGenerateReportId').css('display', 'none');
		$('#wipStatusDivId').css('display', 'none');
		$('#generatedReportDesign').css('display', 'block');
		$('#wipStatusBtnDivId').css('display', 'block');
		$('#wipStatusCloseBtnDivId').css('display', 'none');

	}

	function closeOrderStatus() {
		$('#orderStatusDivId').css('display', 'none');

		$('#generatedReportDesign').css('display', 'block');
		$('#orderStatusBtnDivId').css('display', 'block');
		$('#orderStatusCloseBtnDivId').css('display', 'none');

	}

	function generateWipStatus() {

		//popWipStatusStructure();	

		$('#wipStatusDivId').css('display', 'block');
		$('#wipGenerateReportId').css('display', 'none');
		$('#generatedReportDesign').css('display', 'none');
		$('#wipStatusBtnDivId').css('display', 'none');
		$('#wipBtnDivId').css('display', 'none');
		$('#wipCloseBtnDivId').css('display', 'none');
		$('#wipStatusCloseBtnDivId').css('display', 'block');

		data = $('#clientIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pClientIds').val(ids);

		data = $('#orderTypeIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderTypeIds').val(ids);

		data = $('#orderIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderIds').val(ids);

		data = $('#departmentIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pDepartmentIds').val(ids);

		data = $('#purityIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pPurityIds').val(ids);

		data = $('#colorTableDivId').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pColorIds').val(ids);

		data = $('#colorIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pColorIds').val(ids);

		$('#pDelayCondIds').val($('#delayDay').val());

		$('#pPriorityIds').val($('#priorityId').val());

		$('#pToProdDate').val($("#toProdDate").val());
		$('#pFromProdDate').val($("#fromProdDate").val());

		$('#wipStatusTblId')
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/reports/generateWipStatus.html?pClientIds="
									+ $('#pClientIds').val()
									+ "&pOrderTypeIds="
									+ $('#pOrderTypeIds').val()
									+ "&pOrderIds="
									+ $('#pOrderIds').val()
									+ "&pDepartmentIds="
									+ $('#pDepartmentIds').val()
									+ "&pPurityIds="
									+ $('#pPurityIds').val()
									+ "&pColorIds="
									+ $('#pColorIds').val()
									+ "&pFromOrdDate="
									+ $('#pFromOrdDate').val()
									+ "&pToOrdDate="
									+ $('#pToOrdDate').val()
									+ "&pFromProdDate="
									+ $('#pFromProdDate').val()
									+ "&pToProdDate="
									+ $('#pToProdDate').val()
									+ "&pDelayCondIds="
									+ $('#pDelayCondIds').val()
									+ "&pStyleIds="
									+ $('#pStyleIds').val()
									+ "&pPriorityIds="
									+ $('#pPriorityIds').val(),
							async : false

						});

	}

	function generateOrderStatus() {

		$('#orderStatusReportBtnId').attr('disabled', 'disabled');

		$
				.blockUI({
					message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>'
				});

		$('#orderStatusDivId').css('display', 'block');
		$('#generatedReportDesign').css('display', 'none');
		$('#wipStatusBtnDivId').css('display', 'none');
		$('#orderStatusCloseBtnDivId').css('display', 'block');

		data = $('#clientIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pClientIds').val(ids);

		data = $('#orderTypeIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderTypeIds').val(ids);

		data = $('#orderIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pOrderIds').val(ids);

		data = $('#departmentIdTbl').bootstrapTable('getSelections');
		ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pDepartmentIds').val(ids);

		$('#pFromOrdDate').val($("#fromOrdDate").val());
		$('#pToOrdDate').val($("#toOrdDate").val());
		$('#pFromDelvDate').val($("#fromDelvDate").val());
		$('#pToDelvDate').val($("#toDelvDate").val());

		$('#OrderStatusTblId')
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/reports/generateOrderStatus.html?pClientIds="
									+ $('#pClientIds').val()
									+ "&pOrderTypeIds="
									+ $('#pOrderTypeIds').val()
									+ "&pOrderIds="
									+ $('#pOrderIds').val()
									+ "&pFromOrdDate="
									+ $('#pFromOrdDate').val()
									+ "&pToOrdDate="
									+ $('#pToOrdDate').val()
									+ "&pFromDelvDate="
									+ $('#pFromDelvDate').val()
									+ "&pToDelvDate="
									+ $('#pToDelvDate').val()
									+ "&pDepartmentIds="
									+ $('#pDepartmentIds').val()
									+ "&pWipFormat=" + $('#rptFormatId').val(),

						});

		$.unblockUI();
		$('#orderStatusReportBtnId').removeAttr('disabled');
	}

	var vMtid = 0;

	function getWipDetails() {

		$('#wipTblId').bootstrapTable({}).on('click-row.bs.table',
				function(e, row, $element) {

					vMtid = jQuery.parseJSON(JSON.stringify(row)).sordMtId;

				})

		setTimeout(
				function() {

					$('#myWipModal').modal('show');

					$('#wipDetailTblId')
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/masters/reports/generateWipDetail.html?pSordMtId="
												+ vMtid,
										async : false

									});

				}, 2);

	}

	/* 	
	 function popWipLoad(){
	
	 $('#wipTblId').bootstrapTable('refresh',{
	 url :"/jewels/manufacturing/masters/reports/generateWip.html?pClientIds="+$('#pClientIds').val()
	 +"&pOrderTypeIds="+$('#pOrderTypeIds').val()
	 +"&pOrderIds="+$('#pOrderIds').val()
	 +"&pFromOrdDate="+$('#pFromOrdDate').val()
	 +"&pToOrdDate="+$('#pToOrdDate').val()
	 +"&pFromDelvDate="+$('#pFromDelvDate').val()
	 +"&pToDelvDate="+$('#pToDelvDate').val(),
	 async: false
	
	 });
	
	 } */

	function displayReportFormat() {

		$
				.ajax({

					url : "/jewels/manufacturing/masters/reports/display/reportFormat.html?reportNm="
							+ $('#filterForm').val(),
					data : 'GET',
					success : function(data) {

						if (data !== "-1") {
							$('#reportFormatDiv').css('display', 'block');

							$('#rptFormatDropDownDivId').html(data);

							$('#rptFormatId').val($('#xml').val());
						}

					}
				})

	}

	function changeReportFormat(val) {

		if (val === "wipSummary" || val === "styleWip" || val === "selStyleWip"
				|| val === "wipClientSummary") {
			dynamicFlg = false;
			$('#wipBtnDivId').css('display', 'block');
			$('#rptBtnDivId').css('display', 'none');
			$('#pWipFormat').val(val);
			$('#orderStatusBtnDivId').css('display', 'none');
			$('#wipCastingBtnDivId').css('display', 'none');
		} else if (val === "orderStatus") {
			dynamicFlg = false;

			$('#orderStatusBtnDivId').css('display', 'block');
			$('#wipBtnDivId').css('display', 'none');
			$('#rptBtnDivId').css('display', 'none');
			$('#pWipFormat').val(val);
			$('#wipCastingBtnDivId').css('display', 'none');
		} else if (val === "wipCasting" || val === "wipOrderWiseCastingBal") {
			dynamicFlg = false;

			$('#wipBtnDivId').css('display', 'none');
			$('#rptBtnDivId').css('display', 'none');
			$('#pWipCastingFormat').val(val);
			$('#orderStatusBtnDivId').css('display', 'none');
			$('#wipCastingBtnDivId').css('display', 'block');
			$('#excelBtnDivId').css('display','none');
		}else if(val === "castBalBagWise" ){
			dynamicFlg=false;
			
			$('#wipBtnDivId').css('display','none');
			$('#rptBtnDivId').css('display','block');
			$('#pWipCastingFormat').val(val);
			$('#orderStatusBtnDivId').css('display','none');
			$('#wipCastingBtnDivId').css('display','none');
			$('#excelBtnDivId').css('display','block');
			
			}

		else {
			$('#rptBtnDivId').css('display', 'block');
			$('#wipBtnDivId').css('display', 'none');
		}

	}

	function popWipSummaryStructure() {

		var tempColStruct = "";
		$
				.ajax({
					method : "GET",
					url : "/jewels/manufacturing/masters/reports/wipSummary/dynamicTable.html",
					dataType : "json",
					success : function(data) {

						var pData = JSON.stringify(data);
						if (pData.indexOf("error") != '-1') {
							displayMsg(this, null, data);
						} else {
							var columns = [];
							var colWidth = "";
							$.each(data.rows, function(key, value) {
								if (tempColStruct.length > 0) {
									tempColStruct += ',"' + value.field + '":'
											+ '""';
								} else {
									tempColStruct = '"' + value.field + '":'
											+ '""';
								}

								colWidth = '100px !important';

								columns.push({
									field : value.field,
									title : value.title,
									sortable : false,
									checkbox : value.field === 'state' ? true
											: false,
									width : colWidth
								});
							});

							initDtTable(columns);

						}

					},
					async : false,
					error : function(jqXHR, textStatus, errorThrown) {
						displayMsg(this, null,
								"Error : Creating structure , Contact Support");
					}
				});

	}

	function initDtTable(cols) {
		$('#wipTblId').bootstrapTable('destroy');
		$('#wipTblId').bootstrapTable({
			columns : cols
		});
	}

	/* 
	function imageFormatter(value){
		var tempId = 'trfStone' + Number(trfStoneSrNo++);
		return '<img src="/jewels/uploads/manufacturing/design/15.jpg" id="'+ tempId+ '" class="img-responsive " />';
	}
	 */

	var $table = $('#exportInvoiceListTbl')
	var itemselections = []

	function responseHandler(res) {
		$.each(res.rows, function(i, row) {
			row.state = $.inArray(row.id, itemselections) !== -1
		})
		return res
	}

	$(function() {
		$table
				.on(
						'check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table',
						function(e, rowsAfter, rowsBefore) {
							var rows = rowsAfter

							if (e.type == 'uncheck-all') {
								rows = rowsBefore
							}

							var ids = $.map(!$.isArray(rows) ? [ rows ] : rows,
									function(row) {
										return row.id
									})

							var func = $.inArray(e.type,
									[ 'check', 'check-all' ]) > -1 ? 'union'
									: 'difference'
							itemselections = window._[func]
									(itemselections, ids)
						})
	})

	var $quottable = $('#quotInvoiceListTbl')
	var quotItemselections = []

	function responseHandler(res) {
		$.each(res.rows, function(i, row) {
			row.state = $.inArray(row.id, quotItemselections) !== -1
		})
		return res
	}

	$(function() {
		$quottable
				.on(
						'check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table',
						function(e, rowsAfter, rowsBefore) {
							var rows = rowsAfter

							if (e.type == 'uncheck-all') {
								rows = rowsBefore
							}

							var ids = $.map(!$.isArray(rows) ? [ rows ] : rows,
									function(row) {
										return row.id
									})

							var func = $.inArray(e.type,
									[ 'check', 'check-all' ]) > -1 ? 'union'
									: 'difference'
							quotItemselections = window._[func](
									quotItemselections, ids)
						})
	})

	function rowStyle(row) {

		if (row.ordsr === '1') {
			return {
				classes : 'MarkOrdTotal',

			}
		}
		return {}
	}

	function fromDepartment() {

		$.ajax({
			url : '/jewels/manufacturing/masters/getToDepartmentListDropDownForAll.html',
			type : 'GET',
			success : function(data) {

				$('#vFromDepartmentId').html(data);
				$('#vFromDepartmentId #fromDeptId').chosen();
						

			}
		});

		
	
	}
	
</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>


<%-- 
 <script src="<spring:url value='/js/export/FileSaver.min.js' />"></script>
<script src="<spring:url value='/js/export/jspdf.min.js' />"></script>
<script src="<spring:url value='/js/export/jspdf.plugin.autotable.js' />"></script> --%>

<%-- <script src="<spring:url value='/js/export/tableExport.js' />"></script> --%>
<script src="<spring:url value='/js/bootstrap/lodash.min.js' />"></script>
<%-- <script src="<spring:url value='/js/export/bootstrap-table-export.js' />"></script> --%>


