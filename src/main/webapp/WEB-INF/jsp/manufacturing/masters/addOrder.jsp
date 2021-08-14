<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalOrderDt.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalOrderRates.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalClientStyleList.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<c:set var="optionText" value="Delete" />

<c:if test="${param.success eq true}">
	<div class="col-xs-12">
		<div class="alert alert-success">Order ${param.action} successfully!</div>
	</div>
</c:if>

<div class="form-group">
	<div class="col-xs-12">
		<form:form commandName="orderMt" id="orderMtDataId"
			action="/jewels/manufacturing/masters/order/add.html"
			cssClass="form-horizontal orderMtForm">

			<!-- Column Sizing -->
			<div class="row">
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Order
						No:</label>
				</div>
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-left">Order
						Date:</label>
				</div>
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-left">Division:
						</label>
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-left">Order
						Type:</label>
				</div>
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-left">Client:</label>
				</div>
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Reference
						No:</label>
				</div>
				
			</div>
			<div class="row">
				<div class="col-lg-2 col-sm-2">
					<form:input path="invNo" cssClass="form-control"  />
					<form:errors path="invNo" />
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:input path="invDate" cssClass="form-control" autocomplete="off"/>
					<form:errors path="invDate" />
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:select path="ordDivision.id" class="form-control" required="true">
						<form:option value="" label="- Select Division -" />
						<form:options items="${ordDivisionMap}" />
					</form:select>
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:select path="orderType.id" class="form-control" required="true">
						<form:option value="" label="- Select Order Type -" />
						<form:options items="${orderTypeMap}" />
					</form:select>
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:select path="party.id" class="form-control" onchange="javascript:getSalesPersonFromParty()"  required="true">
						<form:option value="" label="- Select Party -" />
						<form:options items="${partyMap}" />
					</form:select>
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:input path="refNo" cssClass="form-control" autocomplete="off"/>
					<form:errors path="refNo" />
				</div>
				
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
			<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Factory
						Date:</label>
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Delivery
						Date:</label>
				</div>
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Stone
						Req Date:</label>
				</div>
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Cancel
						Date:</label>
				</div>
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Purity:</label>
				</div>
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Color:</label>
				</div>
				
			
			</div>
			<div class="row">
			<div class="col-lg-2 col-sm-2">
					<form:input path="productionDate" cssClass="form-control" onchange="javascript:orderDateValidation(this)" autocomplete="off"/>
					<form:errors path="productionDate" />
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<form:input path="dispatchDate" cssClass="form-control" onchange="javascript:orderDateValidation(this)" autocomplete="off"/>
					<form:errors path="dispatchDate" />
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:input path="stoneReqDate" cssClass="form-control" onchange="javascript:orderDateValidation(this)" autocomplete="off"/>
					<form:errors path="stoneReqDate" />
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:input path="cancelDate" cssClass="form-control" onchange="javascript:orderDateValidation(this)" autocomplete="off"/>
					<form:errors path="cancelDate" />
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:select path="purity.id"  class="form-control">
						<form:option value="" label="- Select Purity -" />
						<form:options items="${purityMap}" />
					</form:select>
				</div>
				<div class="col-lg-2 col-sm-2">
					<form:select path="color.id" class="form-control">
						<form:option value="" label="- Select Color -" />
						<form:options items="${colorMap}" />
					</form:select>
				</div>
			
				
				
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			<div class="row">
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-1 text-right">Total Qty:</label>
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Sales Person:</label>
				</div>
				
				<!-- <div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Discount %:</label>
				</div> -->
				
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Hall Mark:</label>
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Grading:</label>
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Laser Mark:</label>
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Priority:</label>
				</div>
				
				
				
				
				
				
			</div>
			<div class="row">
			<div class="col-lg-2 col-sm-2">
					<input type="text" Class="form-control" id="totQty" name="totQty" disabled="disabled" />
				</div>
				
				<div class="col-lg-2 col-sm-2">
				
					<form:input path="salesPerson" cssClass="form-control" autocomplete="off"/>
					<form:errors path="salesPerson" />
				</div>
				
					<%-- <div class="col-lg-2 col-sm-2">
							<form:input path="discPercent" cssClass="form-control" autocomplete="off" />
							<form:errors path="discPercent" />
					</div> --%>
					
					<div class="col-lg-2 col-sm-2">
					<form:select path="hallMark.id" class="form-control">
					<form:option value="" label="Select HallMark" />				
						<form:options items="${hallMarkMap}" />
					</form:select>
				</div>
					
					 <div class="col-lg-2 col-sm-2">
					<form:select path="grading.id" class="form-control">
					<form:option value="" label="Select Grading" />
						<form:options items="${gradingMap}" />
					</form:select>
				</div>
					
					<div class="col-lg-2 col-sm-2">
					<form:select path="laserMark.id" class="form-control">
						<form:option value="" label="Select LaserMark" />
						<form:options items="${laserMarkMap}" />
					</form:select>
				</div> 
					
					
					<div class="col-lg-2 col-sm-2">
					<form:select path="priority" class="form-control">
						<form:option value="" label="- Select Order Priority -" />
						<form:options items="${priorityMap}" />
					</form:select>
				</div>
				
				
			
			
			</div>
			
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			<div class="row">
			<div class="col-lg-2 col-sm-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Exchange Rate:</label>
				</div>
				
				<div class="col-lg-4 col-sm-4">
					<label for="inputLabel4" class=".col-lg-4 text-right">Remark:</label>
				</div>
			
			</div>
			
			<div class="row">
			<div class="col-lg-2 col-sm-2">
					<form:input path="exchangeRate" cssClass="form-control" autocomplete="off"/>
					<form:errors path="exchangeRate" />
				</div>
			<div class="col-lg-4 col-sm-4">
						<form:textarea path="remark" cssClass="form-control" />
						<form:errors path="remark" />
					</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-lg-2 col-sm-2">
					<a id="metalRatesBtn"  onclick="javascript:popOrderMetalRate()">Metal Rates</a>
					
				</div>
				
				<div class="col-lg-2 col-sm-2">
					<label> <form:checkbox path="in999"  onchange="javascript:update999()"/> <b>As Per 999</b></label>
					</div>
					
				
			</div>
			
					
			<div class="form-group">
				<div class="col-lg-12 text-center">
					<input type="submit" value="Save" class="btn btn-primary" id="mtSaveId"/> 
					<a class="btn btn-info" id="orderListBtn" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/order.html">Order Listing
					</a>
					
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="uniqueId" />
					<%-- <form:input type="hidden" path="invNo" /> --%>
					<input type="hidden" id="orderMtPartyId" name="orderMtPartyId" />
					<input type="hidden" id="orderMtId" name="orderMtId" />
					<input type="hidden" id="orderStnDtPk" name="orderStnDtPk" />
					<input type="hidden" name="adminRightsMap" id="adminRightsMap" value="${adminRightsMap}"  />
					<form:input type="hidden" path="pendApprovalFlg" value="${pendingApprovalFlg}" />
					
				</div>
			</div>
		</form:form>
	</div>
</div>

<div class="row"></div>

<div id="openOnEdit" class="form-group" style="display: none;">
	<div class="row">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" align="left">
				 <span class="col-sm-12 label label-default " 
					style="font-size: 18px;margin-left:auto; ">Order Details</span>
			</div>
		</div>
	</div>

	<div class="form-group" id="dsPId">
		<div class="container-fluid">
			<div class="row" id="forOrderDt" style="font-size: smaller;font-weight: bold;">
				<div id="odDivId" class="col-xs-10">
					<div id="toolbarDt">
							<a id="dtAddBtnId" class="btn btn-info" style="font-size: 15px" type="button" onClick="openDtEntryTab()"><span
										class="glyphicon glyphicon-plus"></span>&nbsp;Style Master
							</a>
							&nbsp;&nbsp;
							<a id="dtAddQuotBtnId" class="btn btn-info" style="font-size: 15px" type="button" onClick="getMasterQuot()"><span
										class="glyphicon glyphicon-plus"></span>&nbsp;Client Style Master
							</a>
							&nbsp;&nbsp;
							<a class="btn btn-warning" style="font-size: 15px" type="button" onClick="orderDtExcelUpload()"><span
										class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel
							</a>
							
							&nbsp;&nbsp;
							<input type="checkbox" id="chkLockOrderDt" onclick="javascript:lockAllOrderDt();"  /> <strong style="color: Coral;">LOCK </strong>
							
					</div>
					<div>
						<table  id="orderDtTabId" data-toggle="table" data-toolbar="#toolbarDt" data-search="true"
								data-side-pagination="server" data-click-to-select="true"
								data-select-item-name="radioNameOrderDt"
								data-pagination="true" data-height="450">
							<thead>
								<tr>
									<th data-field="stateRd" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="srNo" data-sortable="true">Sr. No.</th>
									<th data-field="barcode" data-sortable="false">Barcode</th>
									<th data-field="deliveryDate" data-sortable="false">Delivery Date</th>
									<th data-field="style"  data-sortable="false">Style No</th>
									<th data-field="ktCol" data-sortable="false">Kt-Col</th>
									<th data-field="dueDate" data-sortable="false">Due Date</th>
									<th data-field="pcs" data-sortable="false">Pcs</th>
									<th data-field="cancelQty" data-sortable="false">Cancel Qty</th>
									<th data-field="grossWt" data-sortable="false">GrossWt</th>
									<th data-field="netWt" data-sortable="false">NetWt</th>
									<th data-field="productSize" data-sortable="false">Size</th>
									<th data-field="refNo" data-sortable="false">Ref No</th>
									<th data-field="item" data-sortable="false">Item</th>
									<th data-field="ordRef" data-sortable="false">Ord Ref</th>
									<th data-field="reqCarat" data-sortable="false">Req Cts</th>
									<th data-field="stamp" data-sortable="false">Stamp Inst</th>
									<th data-field="totStone" data-sortable="false">Per Stone</th>
									<th data-field="totCarat" data-sortable="false">Per Carat</th>
									<th data-field="metalValue" data-sortable="false">Metal Val</th>
									<th data-field="stoneValue" data-sortable="false">Stn Val</th>
									<th data-field="compValue" data-sortable="false">find Val</th>
									<th data-field="labourValue" data-sortable="false">Lab Val</th>
									<th data-field="setValue" data-sortable="false">Set Val</th>
									<th data-field="handlingCost" data-sortable="false">Hdlg Val</th>
									<th data-field="fob" data-sortable="false">Fob</th>
									<th data-field="other" data-sortable="false">Other</th>
									<th data-field="finalPrice" data-sortable="false">FinalPrice</th>
									<th data-field="discPerc" data-sortable="false">Disc %</th>
									<th data-field="discAmount" data-sortable="false">Disc Amt</th>
									<th data-field="netAmount" data-sortable="false">Net Amt</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">LockUnLock</th>
									<!-- <th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th> -->
								</tr>
							</thead>
						</table>
						
						<div>
						<label for="inputLabel3" class=".col-lg-2 text-right">Remark:</label>
						<input type="text" class="form-control" id="itemremark" name="itemremark"  readonly="readonly">
						
						</div>
					</div>
					<input type="hidden" id="orderDtPKId" name="orderDtPKId" />
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
				<div class="form-group">
					
						<input type="button" value="Apply Rate" class="btn btn-info" style="width: 190px" onclick="javascript:applyRate()"/>
					
					
				</div>
				<div class="form-group">
					
						<input type="button" value="Apply Quot Rate" class="btn btn-info" style="width: 190px" onclick="javascript:applyQuotRate()"/>
					
					
				</div>
				
				<div class="form-group">
					
						<input type="button" value="Update Barcode" class="btn btn-info" style="width: 190px" onclick="javascript:updateBarcode()"/>
					
					
				</div>
				</div>
				
		
			
					

					
					
					
				</div>
				
		
				
				
			</div>

						
		</div>

		
	</div>
	
	
	
	
		<!----------------------------------------- ApplyRate Button ------------------------------>
		
		
		
			
	
			<!-- <div class="row">
				<div>&nbsp;</div>
			</div> -->
	
</div>


<!-- hide on page load start here -->
<div id="hideOnPageLoad" style="display: none">


	<div class="row">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12">
				<span class="col-lg-12 col-sm-12 label label-default text-right"
					style="font-size: 18px;">Metal Details</span>
			</div>
		</div>
	</div>


	<div class="form-group" id="metalDivId">
		<div class="container-fluid">
			<div class="row" id="forOrderMetal" style="font-size: smaller;font-weight: bold;">
				<div class="col-xs-12">
					<div>
						<table id="orderMetalTableId" data-toggle="table" data-toolbar="#toolbarOrderMetal">
							<thead>
								<tr>
									<th data-field="partNm" data-sortable="false">PartNm</th>
									<th data-field="purity" data-sortable="false">Purity</th>
									<th data-field="color" data-align="left">Color</th>
									<th data-field="qty" data-sortable="false">Qty</th>
									<th data-field="metalWt" data-sortable="false">Metal Wt</th>
									<th data-field="metalRate" data-sortable="false">Metal Rate</th>
									<th data-field="perGramRate" data-sortable="false">Per Gram Rate</th>
									<th data-field="lossPerc" data-formatter="lossPercFormatter" >Loss %</th>
									<th data-field="metalValue" data-sortable="false">Metal Value</th>
									<th data-field="mainMetal" data-formatter="inputFormatter" >Main Metal</th>
								</tr>
							</thead>
						</table>
				</div>
				
			</div>
		
		</div>

	</div>

</div>

<div class="row">
		<div class="col-sm-12">&nbsp;</div>
	</div>


	
	<!------------------------------------------ orderStnDt -------------------------------------->

		
		<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Stone Details</span>
		</div>
	</div>
		
		
	<div id="csDivId" class="col-xs-12">
		<div class="container-fluid">
			<div class="row" style="font-size: smaller;font-weight: bold;">
						
						<div id="toolbarStnDt">
						
								<a class="btn btn-info" type="button" onClick="javascript:addOrderStnDt()"><span class="glyphicon glyphicon-plus"></span>&nbsp;	Add Stone</a>
							<a class="btn btn-info"  type="button" href="javascript:popDsgStoneDt();">&nbsp;Stone Details Update as per Master</a>
							
									
						
							
						


					
						</div>
						
					<div>
						<table id="orderStnDtTabId"
							data-toggle="table" data-toolbar="#toolbarStnDt"
							 data-click-to-select="true"
							data-select-item-name="radioNameOrderStnDt"	>
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th class="OrderStntoneRowId" data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="partNm" data-sortable="true">Part</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
									<th data-field="subShape" data-sortable="false">Sub Shape</th>
									<th data-field="quality" data-sortable="false">Quality</th>
									<th data-field="size" data-sortable="false">Size</th>
									<th data-field="sieve" data-sortable="false">Sieve</th>
									<th data-field="sizeGroup" data-sortable="false">SizeGroup</th>
									<th data-field="stone" data-sortable="false">Stone</th>
									<th data-field="carat" data-sortable="false">Carat</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="stoneValue" data-sortable="false">StoneValue</th>
									<th data-field="handlingRate" data-sortable="false">HandlingRate</th>
									<th data-field="handlingValue" data-sortable="false">HandlingValue</th>
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SettingType</th>
									<th data-field="diaCateg" data-sortable="false">Dia Category</th>
									<th data-field="settingRate" data-sortable="false">SettingRate</th>
									<th data-field="settingValue" data-sortable="false">SettingValue</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center" >LockUnlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
						<input type="hidden" id="orderStnDtPkId" name="orderStnDtPkId" />
						
				</div>
			</div>
			
				<div class="row">
					<div class="col-sm-2">
							<select class="form-control" id="shapeDropDownId" name="shapeDropDownId"  onchange="javascript:popQualityDropDown(this)">
							<option value="">Select Shape</option>
							</select>
							
							</div>
							
					<div class="col-sm-2" >
							<select class="form-control" id="qualityDropDownId" >
							<option value="">Select Quality</option>
							</select>
							
					</div>
					<a class="btn btn-danger" type="button" onClick="javascript:updateQuality()">Update Quality</a>
					<span style="display:inline-block; width: 6cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />	
				</div>
			
			
	 			<!-- entry for orderStnDt -->
	 			<div class="form-group">
					<div id="rowOrderStoneDtId" style="display: none">
					</div>
				</div>
	 
	 
	    </div>
	
	
	
	
	
	
	
	
	
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

	<div id="DCId">
		<div class="container-fluid" style="font-size: smaller;font-weight: bold;">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarCompDt">
						<a class="btn btn-info" style="font-size: 15px" type="button" href="javascript:addOrderCompDt();">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Component Details</a>
						<a class="btn btn-info" style="font-size: 15px" type="button"
							href="javascript:popDsgCompDtls();">&nbsp;Component Update as per Master</a>
					</div>
					<div>
						<table  id="orderCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							data-click-to-select="true"
							data-select-item-name="radioNameOrderCompDt">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metalWt</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="compQty" data-sortable="false">Pcs</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>

		
		<!-- entry for orderCompDt -->
 			<div class="form-group">
				<div id="rowOrderCompDtId">
				</div>
			</div>
		
		
	</div>
	
	
	 <!-----------  orderLabDt ---------->
			 
	<div id="comDivIds" class="col-sm-12">	
		
		<div class="row">
			<div class="form-group">
				<div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Labour Details</span>
				</div>
			</div>
		</div>
			
			<div class="container-fluid">
				<div class="row" style="font-size: smaller;font-weight: bold;">
				
					<div id="toolbarLabDt">
						<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addOrderLabDt()">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour
						</a>
						
					</div>
				
					<div>
						<table id="orderLabDtTabId"
							data-toggle="table" data-toolbar="#toolbarLabDt"
							data-click-to-select="true"
							data-select-item-name="radioNameOrderLabDt"	>
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="metal" data-align="center">Metal</th>
									<th data-field="labourType" data-sortable="true">LabourType</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="perPcs" data-sortable="false" data-formatter="inputFormatter">PerPcs</th>
									<th data-field="perGram" data-sortable="false" data-formatter="inputFormatter">PerGram</th>
									<th data-field="percent" data-sortable="false" data-formatter="inputFormatter">Percentage</th>
									<th data-field="perCarat" data-sortable="false" data-formatter="inputFormatter">PerCarat</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
				</div>
			</div>
			
			
			
			<!-- entry form (orderLabDt) -->
			<div class="form-group">
				<div id="rowOrderLabDtId">
				</div>
			</div>
			
		</div>
		
		
		
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
	
	
	</div>
	



<script type="text/javascript">


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

var canViewFlag = false;
	$(document).ready(
			function(e) {


				
				
				//$('select').chosen();
				$('#orderMtDataId #orderType\\.id').chosen();
				
				$('#orderMtDataId #party\\.id').chosen();
				$('#orderMtDataId #purity\\.id').chosen();
				$('#orderMtDataId #color\\.id').chosen();
				$('#orderMtDataId #priority').chosen();
				$('#orderMtDataId #hallMark\\.id').chosen();
				$('#orderMtDataId #grading\\.id').chosen();
				$('#orderMtDataId #laserMark\\.id').chosen();
				$('#orderMtDataId #ordDivision\\.id').chosen();

				
				
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
				

				
				
				
				if('${orderAuotoNumber}'=='true'){
					$('#invNo').attr('readonly','readonly');
				}else{
					
				}
				
				
				
				
				$(".orderMtForm").validate(
						{
							rules : {

									invDate : {
									required : true,
								},
								
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							}
						});

				
				  $("#invDate").datepicker({
					dateFormat : 'dd/mm/yy'
				}); 

				 $("#productionDate").datepicker({
					dateFormat : 'dd/mm/yy'
				}); 

				$("#dispatchDate").datepicker({
					dateFormat : 'dd/mm/yy'
				}); 

		      

				$("#stoneReqDate").datepicker({
					dateFormat : 'dd/mm/yy'
				}); 

				$("#cancelDate").datepicker({
					dateFormat : 'dd/mm/yy'
				});

				if (window.location.href.indexOf('edit') != -1) {
					
					var abcx = window.location.href;
					var order = abcx.substring(window.location.href.indexOf('edit') + 5);
					var tempCost = order.split(".");
					$('#orderMtId').val(tempCost[0]);
					
					$("#" + document.querySelector("#disableOrderQuality").id).attr("id", "orderQuality");
					
					$('#openOnEdit').css('display','block');
					
					if($('#rLockStatus').val() === 'true'){
						$('#chkLockOrderDt').prop('checked', true);
					}

					//	$('#applyRateDivId').css("display","show")
					
					if('${pendingApprovalFlg}' === 'false'){
						
						if($('#adminRightsMap').val() === 'true'){
							$('#orderMainDivId .btn').hide();
							$('#metalRatesBtn').hide();
							
							$('#orderListingBtnId').show();
							$('#mtSaveId').show();
							$('#dtAddBtnId').show();
							$('#dtAddQuotBtnId').show();
							$('#orderDtBtnId').show();
							$('#btnLblId').show();
							
						}else{

							canViewFlag = true;

							$('#orderMainDivId :input').attr('disabled',true);
							$('#orderMainDivId .btn').hide();
							$('#orderMainDivId .btn').removeAttr('onClick');
							$('#dtAddBtnId').removeAttr('onClick');
							$('#orderMainDivId .select').attr('disabled',true);
							$('#metalRatesBtn').hide();
							
							$('#orderListingBtnId').show();
						
							}
						
					}
					
				
					popOrderDetails('edit');
					getOrdDtTotQty();

				
					
				}else if (window.location.href.indexOf('view') != -1) {
					
					canViewFlag = true;
					
					var abcx = window.location.href;
					var order = abcx.substring(window.location.href.indexOf('view') + 5);
					var tempCost = order.split(".");
					$('#orderMtId').val(tempCost[0]);
					
					$("#" + document.querySelector("#disableOrderQuality").id).attr("id", "orderQuality");
					
					$('#openOnEdit').css('display','block');
					
					if($('#rLockStatus').val() === 'true'){
						$('#chkLockOrderDt').prop('checked', true);
					}
					
					
					popOrderDetails('edit');
					getOrdDtTotQty();
					
					$('#orderMainDivId .btn').removeAttr('onClick');
					$('#dtAddBtnId').removeAttr('onClick');


					
					if($('#adminRightsMap').val() != true){
						$('#orderMainDivId :input').attr('disabled',true);
						$('#orderMainDivId .btn').hide();
						$('#orderMainDivId .btn').removeAttr('onClick');
						$('#dtAddBtnId').removeAttr('onClick');
						$('#orderMainDivId .select').attr('disabled',true).trigger('chosen:updated');;
						$('#metalRatesBtn').hide();
						
						$('#orderListingBtnId').show();
						
					//	$('#orderListBtn').show();
						
						
					}
					
					
				}
				
				else {
					$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					$("#orderType\\.id").focus();
				}
				
			
				
				
				
		
				
				
		});

	
	
	
	var orderMetalDtTableRow;
	var orderMetalDtLockStatus = 'null';
	 $('#orderMetalTableId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				orderMetalDtTableRow = $element.attr('data-index');
				orderMetalDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				
			});
	 
	 
	 
		$('#orderMetalTableId').bootstrapTable({}).on(
				'dbl-click-cell.bs.table',
				function(e, field, value, row, $element,index) {
					
					
					if(field==='lossPerc'){
						
						if(jQuery.parseJSON(JSON.stringify(row)).rLock==='true'){
							 displayMsg(this, null, ' Cannot Edit Record Is Lock');
						 }else{
							 $('#lossPercVal'+orderMetalDtTableRow).removeAttr('disabled');
							 $('#lossPercVal'+orderMetalDtTableRow).focus();
						 }
						
						
					}
					
					
				
					
					 
				
				
				})
	
	

	
//----------MT save-------//
	
	function orderSave(){
		$("form#orderMtDataId").submit();
	}
	
	
	$(document).on(
		'submit',
		'form#orderMtDataId',
		 function(e){
		
			$('#orderMtPartyId').val($('#party\\.id').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					if(data.indexOf('Error') !=-1){
						 displayMsg(this, null, data);
					}else{
						window.location.href = data;	
					}
					
					
					
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();
	
	});
	
	

	
	//--------------DT-----------------------//
	function popOrderDetails(mOpt) {
		
		$("#orderDtTabId").bootstrapTable('refresh',{
							url : "/jewels/manufacturing/masters/orderDt/listing.html?mtId="+$('#orderMtId').val()+"&canViewFlag="+canViewFlag+"&mOpt="+mOpt,
						});
		
		
		var dtdata='';
		
		$('#orderDtTabId').bootstrapTable({}).on('load-success.bs.table',
				function(e, data) {
			   
			 dtdata = $("#orderDtTabId").bootstrapTable('getData').length;

						 
				
		// if($('#adminRightsMap').val() != 'true'){
				 
				 if(dtdata >0){
						$('#party\\.id').attr('disabled','disabled').trigger('chosen:updated');
						$('#orderType\\.id').attr('disabled','disabled').trigger('chosen:updated');
						$('#invDate').attr('disabled','disabled').trigger('chosen:updated');
						$('#invNo').attr('disabled','disabled').trigger('chosen:updated');

						
						
						
					}else{
						$('#party\\.id').removeAttr('disabled','disabled').trigger('chosen:updated');
						$('#orderType\\.id').removeAttr('disabled','disabled').trigger('chosen:updated');
						$('#invDate').removeAttr('disabled','disabled').trigger('chosen:updated');
						$('#invNo').removeAttr('disabled','disabled').trigger('chosen:updated');
					}
				 
		//	 }
				
			  })
		
		
		
		

		
		$('#hideOnPageLoad').css('display','none');	
		
		
		
	}
	
	var orderDtTableRow;
	var orderDtLockStatus = 'null';
	var finalPrice = "";
	 $('#orderDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				orderDtTableRow = $element.attr('data-index');
				
				$('#rowOrderDtId').css('display','none');
				$('#orderDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
				orderDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;

				$('#itemremark').val(jQuery.parseJSON(JSON.stringify(row)).remark);
	

				$('#tempImgDivId').empty();
				var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
				$('#tempImgDivId').append(tempDiv);
				
				$('#hideOnPageLoad').css('display','block');
				$('#shapeDropDownId').chosen();
				$('#qualityDropDownId').chosen();
				  $('#qualityDropDownId').val('').trigger('chosen:updated');

				popShapeDropDown();
			
				
				
				popOrderMetal();
				popOrderStoneDetails();
				popOrderComponentDetails();
			
				popOrderLabDetails();
				
				popStoneCancel();
				popCompCancel();
				popLabCancel(); 
				
			});
	


	
	function editOrderDt(orderDtId){
		 $.ajax({
				url : "/jewels/manufacturing/masters/orderDt/edit/"+ orderDtId + ".html",
				type : 'GET',
				dataType:"JSON",
				success : function(data) {
					modalModeFlag = "edit";
					
					$.ajax({
						url:"/jewels/manufacturing/masters/orderDt/getSize.html?orderDtId="+orderDtId,
						type:"GET",
						success:function(data){
							$("#productSizevId").html(data);
						},
						async:false,
					}); 
					
					var validator = $( ".orderDtForm" ).validate();
					validator.resetForm();
					
					$('#myOrderDtModal').modal('show');
					
					setTimeout(function(){
						 $('#orderMetalTableDivId').css('display','block');
						 $('#orderMetalIdTbl').bootstrapTable('resetView');
					}, 200);
					
					modelFlag = false;
					
					$.each(data,function(k,v){
						$('#orderDtModalDivId  #'+k).val(v);
						
					});
					
					//setMetalZeros();
					  $("#orderMetalIdTbl").bootstrapTable('refresh',{
						url : "/jewels/manufacturing/masters/orderMetal/listing/fromOrderDt.html?dtId="+orderDtId
					});
					
					  
					$('#design\\.mainStyleNo').attr('readonly','readonly');
					
					$('#orderDtFormId #purity\\.id').attr('disabled','disabled');
					$('#orderDtFormId #color\\.id').attr('disabled','disabled');
					
				}
			});
		}

	
	function getMasterQuot(){
		
		$('#myClientStyleModal').modal('show');
		
	
		
	}
	
	$('#myClientStyleModal').on('show.bs.modal', function(e) {
		
	popClientStyleTable();

	})
	
	function popClientStyleTable(){
		$("#clientStyleTblId")
		.bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/transactions/quotDt/clientStyleListing.html?partyId="+$('#party\\.id').val(),
				});
	}
	
	
	function openDtEntryTab(){
		 modalModeFlag = "add";
		 var validator = $( ".orderDtForm" ).validate();
			validator.resetForm();
			
			$('#prodDt').val($('#productionDate').val());
		 
		$('#myOrderDtModal').modal('show');
		
		
		
		setTimeout(function(){
			 $('#orderMetalTableDivId').css('display','none');
			$('#orderMetalIdTbl').bootstrapTable('resetView');
		}, 200);
		
		modelFlag = true;
		
		
		 $('#orderDtModalDivId input[type="text"]').val('');
		 $('#orderDtModalDivId input[type="number"]').val('0');
		 $('#orderDtModalDivId').find('select').val('');
		 $('#orderDtModalDivId').find('textarea').val('');
		 $('#modalOrderDtId').val('');
		 
		 $('#orderDtFormId #purity\\.id').val($('#orderMtDataId #purity\\.id').val());
		 $('#orderDtFormId #color\\.id').val($('#orderMtDataId #color\\.id').val());
		
		 $('#orderDtModalDivId  #design\\.mainStyleNo').focus();
		 $('#design\\.mainStyleNo').removeAttr('readonly','readonly');
		 
		 $('#orderDtFormId #purity\\.id').removeAttr('disabled','disabled');
			$('#orderDtFormId #color\\.id').removeAttr('disabled','disabled');
		 
		 
	}
	
	
	
	function doOrderDtLockUnLock(dtIdPk){
		$.ajax({
			url : "/jewels/manufacturing/masters/orderDt/lockUnlock.html?dtId="+dtIdPk,
			type : 'GET',
			success : function(data) {
				if(data == -1){
					$('#hideOnPageLoad').css('display','none');
					popOrderDetails('edit');
				}
			}
		});
	}
	
	
	
	function lockAllOrderDt(){
		var orderStatus = document.getElementById("chkLockOrderDt").checked;
		var intStatus;
		if(orderStatus == true){
			intStatus = 1;
		}else{
			intStatus = 0;
		}
		
		 $.ajax({
			url : "/jewels/manufacturing/masters/orderDt/dtLockUnlockAll.html?mtId="+$('#orderMtId').val()
					+"&status="+intStatus,
			type : 'GET',
			success : function(data) {
				if(data == -1){
					 $('#hideOnPageLoad').css('display','none');
					 popOrderDetails('edit');
					
				}
			}
		}); 
		
	}
	
	
	
function deleteOrdDt(e,orderDtIdPk){
			
		if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, 'Cannot Delete Record Is Lock');
		}else{
		
		displayDlg(
				e,
				'javascript:deleteOrderDtData('+orderDtIdPk+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	
		}
	}
	
	
	
	
	
	
	function deleteOrderDtData(orderDtId){
		
		$("#modalDialog").modal("hide");
		
		 $.ajax({
				url : "/jewels/manufacturing/masters/orderDt/delete/"+ orderDtId + ".html",
				type : 'GET',
				success : function(data) {
					
					if (data == '1') {
						displayMsg(this, null, 'This Order cannot be deactivated, since bag has been generated');
					}else if (data == '2') {
						displayMsg(this, null, 'This Order cannot be deactivated, Diamond inward done');
					}else{
						$('#hideOnPageLoad').css('display','none');
						popOrderDetails('edit');					}
					
					
					
				}
			}); 

				
		
	}
	
	
	
	
//----------------------stone--------//------------->



	function addOrderStnDt(){
		$("#rowOrderStoneDtId").html('');
		
		$.ajax({
			url:"/jewels/manufacturing/masters/orderStnDt/add.html?orderDtId="+$('#orderDtPKId').val(),
			type:"GET",
			success:function(data){
				
				$('#rowOrderStoneDtId').css('display','block');
				$("#rowOrderStoneDtId").html(data);
				
				 
				$('#stnPkId').val('');
				
			  	$('#orderStnDtEnt input[type="text"],hidden').val('');
			    $('#orderStnDtEnt input[type="number"]').val('0');
			    $('#orderStnDtEnt').find('select').val('');
			    $('#orderStnDtEnt').find('textarea').val('');
				
				
				$('#stoneType\\.id').focus();
				
			}
		})
		
	}
	
	
	
	function popStoneCancel(){
		$('#stnPkId').val('');
	  	$('#orderStnDtEnt input[type="text"],hidden').val('');
	    $('#orderStnDtEnt input[type="number"]').val('0');
	    $('#orderStnDtEnt').find('select').val('');
	    $('#orderStnDtEnt').find('textarea').val('');
	    $('#rowOrderStoneDtId').css('display','none');
	}
	
	
	
	function popOrderStoneDetails(){
		
		$("#orderStnDtTabId")
			.bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/masters/orderStoneDt/listing.html?orderDtId="
							+ $('#orderDtPKId').val()+"&canViewFlag="+canViewFlag,
				});

		
	}
	
	var orderStnLockStatus = 'null';
	$('#orderStnDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				orderStnLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				 $('#orderStnDtPkId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				 	$('#stnPkId').val('');
				  	$('#orderStnDtEnt input[type="text"],hidden').val('');
				    $('#orderStnDtEnt input[type="number"]').val('0');
				    $('#orderStnDtEnt').find('select').val('');
				    $('#orderStnDtEnt').find('textarea').val('');
				    $('#rowOrderStoneDtId').css('display','none');
				 
			});
	
	
	
	function editOrderStnDt(pkOrderStnDtId){
		
		
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			$.ajax({
				url : "/jewels/manufacturing/masters/orderStnDt/validationEdit.html?stnId="+pkOrderStnDtId,
				type : 'GET',
				success : function(data) {
					
					if(data == "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
					
						$('#orderStnDtPk').val(pkOrderStnDtId);
						$.ajax({
							url : "/jewels/manufacturing/masters/orderStnDt/edit/"+ pkOrderStnDtId + ".html",
							type : 'GET',
							success : function(data) {
								
								
								$('#rowOrderStoneDtId').css('display','block');
								$("#rowOrderStoneDtId").html('');
								$("#rowOrderStoneDtId").html(data);
								
							  $("#stoneType\\.id").focus();
								 
							}
						});
					}
					
				}
			});
			
		 }	
		
	}
	
	
	function deleteOrderStnDt(e,orderStnIdDt){
		
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(orderStnLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record Is Lock');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteOrderStnData('+orderStnIdDt+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		 }
		
	}
	
	
	
	function deleteOrderStnData(orderStnDtIdPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/masters/orderStnDt/validationEdit.html?stnId="+orderStnDtIdPk,
			type : 'GET',
			success : function(data) {
				
				if(data == "-1"){
					displayMsg(this, null, 'Cannot delete,Record is Locked');	
				}else{
		
					 $.ajax({
							url : "/jewels/manufacturing/masters/orderStnDt/delete/"+orderStnDtIdPk+".html",
							type : 'GET',
							success : function(data) {
								
								if(data === '-2'){
									displayMsg(this, null, 'Export is Closed');
								}else{
									popOrderStoneDetails();
									updateOrderDtTable($('#orderDtPKId').val());	
								}
								
							 }
						}); 
		
					}
				
				}
			});
		
	}
	
	
	
	
	$(document).on(
		'submit',
		'form#orderStnDtEnt',
		 function(e){
			
			$('#pOrderMtId').val($('#orderMtId').val());
			$('#pOrderDtId').val($('#orderDtPKId').val());
			$('#pSieve').val($('#vSieve').val());
			$('#pSizeGroup').val($('#vSizeGroupStr').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
						
						popOrderStoneDetails();
					
						updateOrderDtTable($('#orderDtPKId').val());
						
						$('#stnPkId').val('');
						$('form#orderStnDtEnt select').val('');
						$('form#orderStnDtEnt select').val('').trigger('chosen:updated');
						$('#vSieve').val('');
						$('#vSizeGroupStr').val('');
						$('#stone').val('0');
						$('#carat').val('0.0');
						$('#stnRate').val('0.0');
						$('#handlingRate').val('0.0');
						$('#setRate').val('0.0');
						
						if(data === '-2'){
							$("#rowOrderStoneDtId").css('display' , 'none');	
						}
						
					
					
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();
	
	});

	
 
 
	 function doStoneDtLockUnLock(stnDtIdPk){
		 
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		 
			 $.ajax({
					url : "/jewels/manufacturing/masters/orderStnDt/lockUnlock.html?stnDtId="+stnDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data == -1){
							popOrderStoneDetails();
						}
					}
				});
		 }
	 
	 }
 
	 
 
	function lockAllOrderStnDt(){
		var orderStnStatus = document.getElementById("chkLockOrderStnDt").checked;
		var intStnStatus;
			if(orderStnStatus == true){
				intStnStatus = 1;
			}else{
				intStnStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/masters/orderStnDt/stnLockUnlockAll.html?mtId="+$('#orderMtId').val()
						+"&status="+intStnStatus,
				type : 'GET',
				success : function(data) {
					if(data == -1){
						popOrderStoneDetails();
					}
				}
			}); 
			 
		}
	
	
	
	function popQualitys(vSel){
		$
		.ajax({
			url : '/jewels/manufacturing/masters/qualityOrderDt/list.html?shapeId='
					+ vSel,
			type : 'GET',
			success : function(data) {
				$("#qualityId").html(data);
			}
		});
	}
	
	
	
	
	$('#orderStnDtTabId').bootstrapTable({}).on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#orderStnDtTabId").bootstrapTable('getData'));
				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});
	
	
	
	
	function popSetRateFromMaster(){
		
		if(!!$('#setting\\.id').val() && !!$('#settingType\\.id').val() && !!$('#shape\\.id').val() && !!$('#stoneType\\.id').val() && !!$('#quality\\.id').val()
				&& !!$('#party\\.id').val() && !!$('#stone').val() && !!$('#carat').val()){
		
			$.ajax({
				url : "/jewels/manufacturing/masters/orderStnDt/settingRateFromMaster.html?orderDtId="+$('#orderDtPKId').val()
						+"&partId="+$('#partNm\\.id').val()
						+"&shapeId="+$('#shape\\.id').val()
						+"&stoneTypeId="+$('#stoneType\\.id').val()
						+"&settingId="+$('#setting\\.id').val()
						+"&settingTypeId="+$('#settingType\\.id').val()
						+"&qualityId="+$('#quality\\.id').val()
						+"&mtPartyId="+$('#party\\.id').val()
						+"&stone="+$('#stone').val()
						+"&carat="+$('#carat').val(),
				type : "GET",
				success : function(data){
					$('#setRate').val(data);
				}
				
			});
			
		}else{
			//displayMsg(this, null, ' stonetype or shape or setting or setting type is not selected');
		}
		
	}
	
	
	
	function popStoneRate(){
		
		if(!!$('#shape\\.id').val() && !!$('#quality\\.id').val() && !!$('#vSizeGroupStr').val() && !!$('#party\\.id').val()){
			
			$.ajax({
				url : "/jewels/manufacturing/masters/orderStnDt/stoneRateFromMaster.html",
				type : "GET",
				data :{
					qualityId:$('#quality\\.id').val(),
					sizeGroupStr:$('#vSizeGroupStr').val(),
					shapeId:$('#shape\\.id').val(),
					mtPartyId:$('#party\\.id').val(),
					stoneTypeId:$('#stoneType\\.id').val(),
					sieve:$('#vSieve').val(),
				},
				success : function(data){
					$('#stnRate').val(data);
					
				}
				
				
			});
			
			
			
		}else{
			//displayMsg(this, null, ' shape or quality or party or sizegroup  is not selected');
		}
		
		
		
	}
	
	
	function popHandlingRate(){
		
		if(!!$('#shape\\.id').val() && !!$('#partNm\\.id').val() && !!$('#stoneTypeId\\.id').val() && !!$('#party\\.id').val()){
			
			$.ajax({
				url : "/jewels/manufacturing/masters/orderStnDt/handlingRateFromMaster.html?orderDtId="+$('#orderDtPKId').val()
						+"&partId="+$('#partNm\\.id').val()
						+"&shapeId="+$('#shape\\.id').val()
						+"&stoneTypeId="+$('#stoneType\\.id').val()
						+"&mtPartyId="+$('#party\\.id').val(),
				type : "GET",
				success : function(data){
					$('#handlingRate').val(data);
				}
			
			});
			
		}else{
			
			//displayMsg(this, null, ' shape or part or stonetype   is not selected');
			
		}
	}


	function getPointer() {
		$.ajax({
			url : '/jewels/manufacturing/masters/designStone/stonePointer.html',
			type : "GET",
			data : {shape : $("#shape\\.id :selected").text(), size : $("#size").val()} ,
			success : function(data, textStatus, jqXHR) {
				$("#carat").val(parseFloat(Number(data) * $("#stone").val()).toFixed(3));
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});		
	}

//----------//--------component--------//------------->
	

	
	function popOrderComponentDetails(){
		
		$("#orderCompDtTabId")
		.bootstrapTable(
			'refresh',
			{
				 url : "/jewels/manufacturing/masters/orderCompDt/listing.html?orderDtId="
						+ $('#orderDtPKId').val()+"&canViewFlag="+canViewFlag,
						
						
						});
		
	}
	

	var orderCompLockStatus = 'null';
	$('#orderCompDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				orderCompLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				$('#orderCompDtEnt input[type="text"],hidden').val('');
			    $('#orderCompDtEnt input[type="number"]').val('0');
			    $('#orderCompDtEnt').find('select').val('');
			    $('#orderCompDtEnt').find('textarea').val('');
			    $('#orderCompDtPk').val('');
			    $('#orderCompDtEnt input[type="checkbox"]').prop("checked", false);
			    $('#rowOrderCompDtId').css('display','none');
			})
	
	
	
	

		
	function addOrderCompDt(){
				
		if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			 $.ajax({
					url : "/jewels/manufacturing/masters/orderCompDt/add.html",
					type : 'GET',
					success : function(data) {
						$('#rowOrderCompDtId').css('display','block');
						$("#rowOrderCompDtId").html(data);
						$('#component\\.id').focus();
						$('#orderCompDtEnt input[type="text"],hidden').val('');
					    $('#orderCompDtEnt input[type="number"]').val('0');
					    $('#orderCompDtEnt').find('select').val('');
					    $('#orderCompDtEnt').find('textarea').val('');
					    $('#orderCompDtPk').val('');
					    $('#orderCompDtEnt input[type="checkbox"]').prop("checked", false);
						
					}
				});
			
		 }
	}
	
	
	function popCompCancel(){
		$('#orderCompDtEnt input[type="text"],hidden').val('');
	    $('#orderCompDtEnt input[type="number"]').val('0');
	    $('#orderCompDtEnt').find('select').val('');
	    $('#orderCompDtEnt').find('textarea').val('');
	    $('#orderCompDtPk').val('');
	    $('#orderCompDtEnt input[type="checkbox"]').prop("checked", false);
	    $('#rowOrderCompDtId').css('display','none');
	}
	
	
	
	function editOrderCompDt(pkCompDtId){
		
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			$.ajax({
				url : "/jewels/manufacturing/masters/orderCompDt/validationEdit.html?compId="+pkCompDtId,
				type : 'GET',
				success : function(data) {
					
					if(data == "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/masters/orderCompDt/edit/"+ pkCompDtId + ".html",
							type : 'GET',
							success : function(data) {
								$('#rowOrderCompDtId').css('display','block');
								$("#rowOrderCompDtId").html(data);
								$('#component\\.id').focus();
							}
						});
					}
					
				}
			});
			
		 }
		
	}
	
	
	
	
	function deleteOrderCompDt(e,orderCompDtId){
		
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(orderCompLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record Is Lock');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteOrderCompData('+orderCompDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		 }
		
		
	}
	
	
	function deleteOrderCompData(pkCompDtId){
		
		$("#modalDialog").modal("hide");

		
			 $.ajax({
				url : "/jewels/manufacturing/masters/orderCompDt/delete/"+ pkCompDtId + ".html",
				type : 'GET',
				success : function(data) {
					popOrderComponentDetails();
					updateOrderDtTable($('#orderDtPKId').val());
				}
			}); 
		
		}
	
	
	
	
	
	
	function doCompDtLockUnLock(compDtIdPk){
		
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
			 $.ajax({
					url : "/jewels/manufacturing/masters/orderCompDt/lockUnlock.html?compDtId="+compDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data === '-1'){
							popOrderComponentDetails();
						}
					}
				});
		 }
	}
	
	
	function lockAllOrderCompDt(){
		
		var orderCompStatus = document.getElementById("chkLockOrderCompDt").checked;
		var intCompStatus;
			if(orderCompStatus == true){
				intCompStatus = 1;
			}else{
				intCompStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/masters/orderCompDt/LockUnlockAll.html?mtId="+$('#orderMtId').val()
						+"&status="+intCompStatus,
				type : 'GET',
				success : function(data) {
					if(data === '-1'){
						popOrderComponentDetails();
					}
				}
			}); 
		
	}
	
	
	function popOrderCompRate(){
		
	
		if(!!$('#orderCompDtEnt #component\\.id').val() && !!$('#orderCompDtEnt #purity\\.id').val() && !!$('#party\\.id').val()){
			
			
			$.ajax({
				url:"/jewels/manufacturing/masters/orderCompDt/rateFromMaster.html?componentId="+$('#component\\.id').val()
						+"&purityId="+$('#orderCompDtEnt #purity\\.id').val()
						+"&mtPartyId="+$('#party\\.id').val(),
				type:"GET",
				dataType:"JSON",
				success:function(data){
					
					$('#orderCompDtEnt #compRate').val('0.0');
					$("#perGramRate1").prop("checked", false);
					$("#perPcRate1").prop("checked", false);
					
					$.each(data,function(k,v){
						
						if(k === 'rate'){
							$('#orderCompDtEnt #compRate').val(v);
						}else if(k === 'perPcRate'){
							$("#perGramRate1").prop("checked", v);	
						}else if(k === 'perGramRate'){
							$("#perPcRate1").prop("checked", v);
						}
					});
					
				}
			});
		}else{
			//displayMsg(this,null,"Component or Purity cannot be blank");
		}
		
	}
	
	
	$(document)
	.on(
		'submit',
		'form#orderCompDtEnt',
		 function(e){
		
			$('#forCompOrderMtId').val($('#orderMtId').val());
			$('#forCompOrderDtId').val($('#orderDtPKId').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					popOrderComponentDetails();
					updateOrderDtTable($('#orderDtPKId').val());
					$('#component\\.id').focus();
					$('#orderCompDtEnt input[type="text"],hidden').val('');
				    $('#orderCompDtEnt input[type="number"]').val('0');
				    $('#orderCompDtEnt').find('select').val('');
				    $('#orderCompDtEnt').find('textarea').val('');
				    $('form#orderCompDtEnt select').val('').trigger('chosen:updated');
				    $('#orderCompDtPk').val('');
				    $('#orderCompDtEnt input[type="checkbox"]').prop("checked", false);
					
					
					if(data === '-2'){
						$('#rowOrderCompDtId').css('display','none');
					}
					
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();

	})
	
	
	
//----------------------orderLabDt--------//------------->
	
	
	
	
	function popOrderLabDetails(){
		
		$("#orderLabDtTabId").bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/orderLabDt/listing.html?orderDtId="
						+ $('#orderDtPKId').val()+"&canViewFlag="+canViewFlag,
			});
		
	}
	
	var orderLabDtLockStatus = 'null'
	$('#orderLabDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				orderLabDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				$('#orderLabDtEnt input[type="text"],hidden').val('');
			    $('#orderLabDtEnt input[type="number"]').val('0');
			    $('#orderLabDtEnt').find('select').val('');
			    $('#orderLabDtEnt').find('textarea').val('');
			    $('#orderLabDtPk').val('');
			    $('#orderLabDtEnt input[type="checkbox"]').prop("checked", false);
			    $('#rowOrderLabDtId').css('display','none');
			})
	
	
			
	$(document).
		on('submit',
			'form#orderLabDtEnt',
			function(e){
			
			$('#forLabOrderMtId').val($('#orderMtId').val());
			$('#forLabOrderDtId').val($('#orderDtPKId').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					
					//alert(data);
					
					if(data === "-11"){
						displayMsg(this, null, 'please select only one check box');	
					}else if(data === "-12"){
						displayMsg(this, null, 'please check the entry');	
					}else{
						
						popOrderLabDetails();
						
						$('#orderLabDtEnt input[type="text"],hidden').val('');
					    $('#orderLabDtEnt input[type="number"]').val('0');
					    $('#orderLabDtEnt').find('select').val('');
					    $('#orderLabDtEnt').find('textarea').val('');
					    $('#orderLabDtPk').val('');
					    $('#orderLabDtEnt input[type="checkbox"]').prop("checked", false);
						
						if(data === '-2'){
							$('#rowOrderLabDtId').css('display','none');
						}
						
						updateOrderDtTable($('#orderDtPKId').val());
						
					}
					
				},
				error : function(data, textStatus, jqXHR){
					
				}
				
			})
			
			e.preventDefault();
			
		})
		
		
	function addOrderLabDt(){
		
		if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
			
				$.ajax({
					url : "/jewels/manufacturing/masters/orderLabDt/add.html",
					type : 'GET',
					success : function(data) {
						$("#rowOrderLabDtId").html(data); 
						$('#rowOrderLabDtId').css('display','block');
						$('#labourType\\.id').focus();
						
						$('#orderLabDtEnt input[type="text"],hidden').val('');
					    $('#orderLabDtEnt input[type="number"]').val('0');
					    $('#orderLabDtEnt').find('select').val('');
					    $('#orderLabDtEnt').find('textarea').val('');
					    $('#orderLabDtPk').val('');
					    $('#orderLabDtEnt input[type="checkbox"]').prop("checked", false);
						
					}
				});
			
			
		 }
	}	
	
	
	
	function popLabCancel(){
		$('#orderLabDtEnt input[type="text"],hidden').val('');
	    $('#orderLabDtEnt input[type="number"]').val('0');
	    $('#orderLabDtEnt').find('select').val('');
	    $('#orderLabDtEnt').find('textarea').val('');
	    $('#orderLabDtPk').val('');
	    $('#orderLabDtEnt input[type="checkbox"]').prop("checked", false);
	    $('#rowOrderLabDtId').css('display','none');
	}
	
		
		
	function editOrderLabDt(pkLabDt){
		
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
			$.ajax({
				url : "/jewels/manufacturing/masters/orderLabDt/validationEdit.html?labId="+pkLabDt,
				type : 'GET',
				success : function(data) {
					if(data == "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/masters/orderLabDt/edit/"+ pkLabDt + ".html",
							type : 'GET',
							success : function(data) {
								$("#rowOrderLabDtId").html(data); 
								$('#rowOrderLabDtId').css('display','block');
								$('#labourType\\.id').focus();
							}
						});
						
					}
					
				}
			});
			
		 }
		
		
	}
	
	
	
	function deleteOrderLabDt(e,orderLabDtId){
		
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(orderLabDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record Is Locked ');
		 }else{
			
			displayDlg(
					e,
					'javascript:deleteOrderLabData('+orderLabDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		  }
			
	}
	
	
	function deleteOrderLabData(pkLabDt){
		
		$("#modalDialog").modal("hide");
		
					 $.ajax({
						url : "/jewels/manufacturing/masters/orderLabDt/delete/"+ pkLabDt + ".html",
						type : 'GET',
						success : function(data) {
							popOrderLabDetails();
							updateOrderDtTable($('#orderDtPKId').val());
						}
					}); 
		
					
	 }
	
	
	
	
	
	
	
	function doLabDtLockUnLock(labDtIdPk){
		
		 if(orderDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			 $.ajax({
					url : "/jewels/manufacturing/masters/orderLabDt/lockUnlock.html?labDtId="+labDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data == -1){
							popOrderLabDetails();
						}
					}
				});
		 }
	}
	
	
	function lockAllOrderLabDt(){
		var orderLabStatus = document.getElementById("chkLockOrderLabDt").checked;
		var intLabStatus;
			if(orderLabStatus == true){
				intLabStatus = 1;
			}else{
				intLabStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/masters/orderLabDt/LockUnlockAll.html?mtId="+$('#orderMtId').val()
						+"&status="+intLabStatus,
				type : 'GET',
				success : function(data) {
					if(data == -1){
						popOrderLabDetails();
					}
				}
			}); 
		
	}
	




//------------------------------------------------------------------------------------------------------------------------------------------------------//
	
	
	
	
	
	function centerStoneFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
	}
	

	
	
	//------------------metal------------------------//
	
	function popOrderMetalRate(){
		$('#myOrderRateModal').modal('show');
		popOrderMetalRateTbl();
		
		setTimeout(function(){
			$('#orderMetalRateIdTbl').bootstrapTable('resetView');
		}, 300);
	}
	
	
	
	
	
	 function popOrderMetal(){
		 
		  $("#orderMetalTableId").bootstrapTable('refresh',{
						url : "/jewels/manufacturing/masters/orderMetal/listing.html?orderDtId="+$('#orderDtPKId').val(),
					});
		}
	
	
	
	//---------------------- APPLY RATE --------//----------------->
	 
	 function applyRate(){

		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
		 
		 var postData = $('#orderMtDataId').serializeArray();
		 		 
		 $.ajax({
				url : "/jewels/manufacturing/masters/applyRate/orderMt.html?tempPartyId="+$('#party\\.id').val(),
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					$.unblockUI();
					
					if(data==='1'){
						window.location.reload(true);
					}else{
						displayMsg(this,null,"Error Can Not Apply Rate");	
					}
						
					
				},
				error : function(data, textStatus, jqXHR){
					
				}
				
			})
		 
		 
	 }
	 
	 
	//---------------------- APPLY Quot RATE --------//-----------------> 
	 
 function applyQuotRate(){

	 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
		 
		 var postData = $('#orderMtDataId').serializeArray();
		 		 
		 $.ajax({
				url : "/jewels/manufacturing/masters/applyQuotRate/orderMt.html?tempPartyId="+$('#party\\.id').val(),
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					$.unblockUI();
					if(data==='1'){
						window.location.reload(true);
					}else{
						displayMsg(this,null,"Error Can Not Apply Rate");	
					}
						
					
				},
				error : function(data, textStatus, jqXHR){
					
				}
				
			})
		 
		 
	 }
	 
	 

	 
	 
	 function updateOrderDtTable(dtId){
			
			$.ajax({
				url : "/jewels/manufacturing/masters/orderDt/getData/"+dtId+".html",
				type : 'GET',
				dataType : 'JSON',
				success : function(data) {
					
					$('#orderDtTabId').bootstrapTable('updateRow', {
						index : Number(orderDtTableRow),
						row : data
					});
					
					
				}
			});
		}
		
	 
	 
	 //---------------------order quality -------------------//
	 	function popOrderQDetails() {
		$("#orderQId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/orderQuality/listing.html?pInvNo="
									+ $('#invNo').val()
						});
	}

	function goToODt() {
		$("#rowODtId").css('display', 'block');
		$('#pOrdDtId').val('');
		temp = '';
		$('form#orderDt input[type="text"],texatrea').val('');
		$('form#orderDt select').val('');
		$('#design\\.styleNo').focus();

		if ($("#oDtPurId").val() == "") {
			$("#oDtPurId > [value=" + $('#oMtPurId').val() + "]").attr(
					"selected", "true");
		}

		if ($("#oDtClrId").val() == "") {
			$("#oDtClrId > [value=" + $('#oMtClrId').val() + "]").attr(
					"selected", "true");
		}
	}
	 
	 
	 
	 
	function goToOQuality() {
		$.ajax({
			url : "/jewels/manufacturing/masters/orderQuality/edit/0.html",
			type : 'GET',
			success : function(data) {
				
				$("#rowOQId").css('display', 'block');
				$("#rowOQId").html(data);

				
/* 				$("#rowOQId").css('display', 'block');
				$('form#orderQuality input[type="text"],texatrea').val('');
				$('form#orderQuality select').val('');
				$('#OQltyId').val('');
 */			
 				$('#oQShId').focus();
			}
		});
	}
	
	
	
	
	 
	function popOrderQuality() {
		$.ajax({
			url : '/jewels/manufacturing/masters/orderQuality/list.html?shapeId=' + $('#oQShId').val()+'&qualityDropDownId=quality.id',
			type : 'GET',
			success : function(data) {
				
				$('#orderQuality #quality\\.id').html(data);
				$('#orderQuality #quality\\.id').trigger("chosen:updated");
			}
		});
	}
	
	
	
	function popShapeDropDown() {
		$.ajax({
			url : '/jewels/manufacturing/masters/shape/dropDownlist.html',
			type : 'GET',
			success : function(data) {
				$("#shapeDropDownId").html(data);
				$("#shapeDropDownId").trigger("chosen:updated");
			}
		});
	}
	
	function popQualityDropDown(val){
    	var e = document.getElementById("shapeDropDownId");
		var shpId = e.options[e.selectedIndex].value;
		
		$.ajax({
			url : '/jewels/manufacturing/masters/orderQuality/list.html?shapeId=' + shpId+'&qualityDropDownId=qltyDropDownId',
			type : 'GET',
			success : function(data) {
				$("#qualityDropDownId").html(data);
				$("#qualityDropDownId").trigger("chosen:updated");
			}
		});
		
	}
	 
	 
	function editOrderQuality(oMtId) {
		$.ajax({
			url : "/jewels/manufacturing/masters/orderQuality/edit/" + oMtId
					+ ".html",
			type : 'GET',
			success : function(data) {
				$("#rowOQId").css('display', 'block');
				$("#rowOQId").html(data);

				$('#pOQInvNo').val($('#invNo').val());
			}
		});
	}
	
	
	function deleteOrderQuality(e,id){
		displayDlg(
				e,
				'javascript:deleteOrderQualityData('+id+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}
	
	function deleteOrderQualityData(id){
		
		$("#modalDialog").modal("hide");
		 $.ajax({
			url : "/jewels/manufacturing/masters/orderQuality/delete/"+id+".html",
			type : 'GET',
			success : function(data) {
				popOrderQDetails();
			}
		}); 
		
	}
	
	
	
	//------------------Change order Stone type ---------------//
	
	$(document).on('submit', 'form#orderStnTypeChngId', function(e) {
	
		$('#chngStnTypeId').attr('disabled', 'disabled');
		$('#vInvNo').val($('#invNo').val());
					
		var postData = $(this).serializeArray();
					
		var formURL = $(this).attr("action");
	
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {

				$.unblockUI();
								
				$('#chngStnTypeId').removeAttr('disabled');
				
				if(data ==='1'){
					displayInfoMsg(this, null, 'Stone Type update Successfully');
					$('#stnTypeId').val('');
					$('#hideOnPageLoad').css('display','none');
				}else if(data=== '-2'){
					displayMsg(this, null, 'Please Select Stone Type ');
				}else{
					displayMsg(this, null, 'error Contact Admin');
				}
				
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action   
	
	});



//------------------Change Pointer Stone type ---------------//
	
	$(document).on('submit', 'form#orderPointerStnTypeId', function(e) {
	
		$('#chngPointerStnTypeId').attr('disabled', 'disabled');
		$('#vMtId').val($('#orderMtId').val());

				
		var postData = $(this).serializeArray();
					
		var formURL = $(this).attr("action");
	
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {

				$.unblockUI();
								
				$('#chngPointerStnTypeId').removeAttr('disabled');
				
				if(data ==='1'){
					displayInfoMsg(this, null, 'Stone Type update Successfully');
					$('#pointerStnTypeId').val('');
					$('#hideOnPageLoad').css('display','none');
				}else if(data=== '-2'){
					displayMsg(this, null, 'Please Select Stone Type ');
				}else{
					displayMsg(this, null, 'error Contact Admin');
				}
				
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action   
	
	});
	
	
	function updateBarcode(){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
		 $.ajax({
				url : "/jewels/manufacturing/masters/orderMt/barcode.html?mtId="+$('#orderMtId').val(),
				type : 'GET',
				success : function(data) {
					$.unblockUI();
					if(data === "1"){
						 $('#hideOnPageLoad').css('display','none');
						 popOrderDetails('edit');						
					}else{
						
						displayMsg(this,null,'Barcode Not Updated Successfully ');
					}
				}
			}); 	
		
	}


function popOrderPickUp() {
		
		//alert('part ID '+$('#party\\.id').val());
		
		if($('#party\\.id').val() !="" && $('#orderMtId').val()!= ""){
			
			
			 window.location.href ="/jewels/manufacturing/masters/order/pickUp.html?partyId="+$('#party\\.id').val()+ "&mtId="
					 +$('#orderMtId').val()+ "&pickUpType=order";
		}else{
			displayMsg(this, null, 'Please Select Party or order not created !');
		}
	  
	}
	
	
	
function popQuotToOrderPickUp() {
	
	//alert('part ID '+$('#party\\.id').val());
	
	if($('#party\\.id').val() !="" && $('#orderMtId').val()!= ""){
		
		
		 window.location.href ="/jewels/manufacturing/masters/order/pickUp.html?partyId="+$('#party\\.id').val()+ "&mtId="
				 +$('#orderMtId').val()+ "&pickUpType=orderFromQuot";
	}else{
		displayMsg(this, null, 'Please Select Party or order not created !');
	}
  
}
	
	
	


function popDsgCompDtls() {

	 $.ajax({
			url : "/jewels/manufacturing/masters/orderCompDt/updateAsPerMaster.html?orderDtId="
				+ $('#orderDtPKId').val(),
			type : 'GET',
			success : function(data) {
				if(data === "1"){
					popOrderComponentDetails();
				}else{
					displayMsg(this, null, 'Can not Update As Per Master !');
				}
			
			}
		});
}

function popDsgStoneDt(){
	 $.ajax({
			url : "/jewels/manufacturing/masters/orderStnDt/updateAsPerMaster.html?orderDtId="
				+ $('#orderDtPKId').val(),
			type : 'GET',
			success : function(data) {
				if(data === "1"){
					popOrderStoneDetails();
				}else{
					displayMsg(this, null, 'Can not Update As Per Master !');
				}
			
			}
		});
	
}
	

function getOrdDtTotQty(){
	
	
	 $.ajax({
			url : "/jewels/manufacturing/masters/order/getTotal.html?orderMtId="+ $('#id').val(),
			type : 'GET',
			success : function(data) {
				
				$('#totQty').val(data);
			}
		});

	
}


function updateQuality(){
	
	var e = document.getElementById("shapeDropDownId");
	var shpId = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("qualityDropDownId");
	var qualityId = e.options[e.selectedIndex].value;
	
	
	 $.ajax({
			url : "/jewels/manufacturing/masters/orderStnDt/updateQlty.html?orderDtId="
				+ $('#orderDtPKId').val()+"&shapeId="+shpId+"&qualityId="+qualityId,
			type : 'GET',
			success : function(data) {
				
				if(data === "1"){
					
					$('#qltyDropDownId').val('');
					popOrderStoneDetails();
				}else{
					displayMsg(this, null, 'Can not Update Quality Contact Admin !');
				}
			}
		});
}


function update999(){
	 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	 
	 
	 var postData = $('#orderMtDataId').serializeArray();
	 
	 $.ajax({
			url : "/jewels/manufacturing/masters/orderMt/updatepuritycost.html?tempPartyId="+$('#party\\.id').val()+"&tempPurityId="+$('#orderMtDataId #purity\\.id').val()
			+"&tempColorId="+$('#orderMtDataId #color\\.id').val(),
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR){
				
				if(data === '1'){
					popOrderDetails('edit');
					getOrdDtTotQty();
					
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


function lossPercFormatter(value, row, index){
	
	var tempId = 'lossPercVal' + Number(index);

		var vId = row.id;
	    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onblur="javascript:disableLossPerc()" onchange="javascript:updateLossPerc(this,'+vId+')" disabled />';
	}
	
	
function disableLossPerc(){
	
	$('#lossPercVal'+orderMetalDtTableRow).attr('disabled', 'disabled');
}

function updateLossPerc(param, val){
	$.ajax({
		url : "/jewels/manufacturing/masters/orderMetal/updateLossPerc.html?id="+val+"&lossPerc="+ param.value,
		type : 'GET',
		success : function(data) {
			
				popOrderMetal();
				updateOrderDtTable($('#orderDtPKId').val());
							
				
		
		}

	});
}


function orderDateValidation(val){
	
	
	if(process(val.value) < process($('#invDate').val())){
		 displayMsg(this,null,"Date Is Less Than Order Date");	
		   $('#'+val.id).val('');
		}
	
	
	/* date1 = new Date(val.value);
	ordDate = new Date($('#invDate').val());
	
	alert('date1 '+date1);
	alert('ordDate '+ordDate);
	
	alert('date1  time '+date1.getTime());
	alert('ordDate time '+ordDate.getTime());
	
	if(date1.getTime() < ordDate.getTime()){
		   //start is less than End
		   displayMsg(this,null,"Date Is Less Than Order Date");	
		   $('#'+val.id).val('');
		   
		} */
	
	
}

function process(date){
	   var parts = date.split("/");
	   return new Date(parts[2], parts[1] - 1, parts[0]);
	}
			

function orderDtExcelUpload(){

	window.location.href="/jewels/manufacturing/masters/orderDt/uploadExcel.html?mtId="+ $('#id').val();
}


function getSalesPersonFromParty(){
	
	$.ajax({
		url : "/jewels/manufacturing/masters/orderMt/getSalesPersonFromParty.html?partyId="+$('#party\\.id').val(),
		type : 'GET',
		success : function(data) {
			
			$('#salesPerson').val(data)
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





