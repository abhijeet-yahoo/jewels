<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalQuotDt.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalQuotRates.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalQuotSummary.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalOrderPickup.jsp"%>

  <div class="panel-body">
	
	<c:if test="${success eq true}">
		<div class="col-xs-12">
			<div class="alert alert-success">Quotation ${action} successfully!</div>
		</div>
	</c:if> 
	
	
		<div class="form-group">
		
			<form:form commandName="quotMt" id="quotMtDataId"
				action="/jewels/manufacturing/transactions/quotMt/add.html"
				cssClass="form-horizontal quotMtForm">

			<!-- 	<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div> -->
				
				<div  id="mDivId" class="col-xs-10">

				<div style="font-size: smaller;font-weight: bold;" class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Quot No</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Quot Date</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Party</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Ref. No.</label>
						</div>
						
						
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="invNo" cssClass="form-control" disabled="true"/>
							<form:errors path="invNo" />
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:input path="invDate" cssClass="form-control" />
							<form:errors path="invDate" />
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="party.id" class="form-control" onChange="javascript:popDiscPerc();">
								<form:option value="" label=" Select Party " />
								<form:options items="${partyMap}" />
							</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:input path="refNo" cssClass="form-control" autocomplete="off"/>
							<form:errors path="refNo" />
						</div>
						
						
						
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">
					
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Purity</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Color</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-right">Exchange Rate</label>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">
					
						<div class="col-lg-3 col-sm-3">
							<form:select path="purity.id" class="form-control">
								<form:option value="" label=" Select Purity " />
								<form:options items="${purityMap}" />
							</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="color.id" class="form-control">
								<form:option value="" label=" Select Color " />
								<form:options items="${colorMap}" />
							</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:input path="exchangeRate" cssClass="form-control" autocomplete="off"/>
							<form:errors path="exchangeRate" />
						</div>
						
					</div>
				</div>	
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
					<label> <form:checkbox path="in999"  onchange="javascript:update999()"/> <b>As Per 999</b></label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
					<label> <form:checkbox path="masterFlg"/> <b>Master Flg</b></label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
							<a id="metalRatesBtn"  onclick="javascript:popMetalRate()">Metal Rates</a>
						</div>
					
					</div>
				</div>
				


				<!-- <div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				<div style="font-size: smaller;font-weight: bold;" class="row">
					<div class="col-xs-12">
						
						
						
					</div>
				</div> -->


				
     </div>

		<div id="imageDivId" class="col-xs-2 center-block">
		       <div class="panel panel-primary" style=" height:150px;">
					<div class="panel-body">
						<div style="width: 150px; height: 60px;" id="tempImgDivId">
									
						</div>
					</div>
			  </div>
		
		</div>


				 <div class="form-group">
					<div class="col-sm-offset-5 col-sm-5">
						<form:input type="hidden" path="id" />
						<form:input type="hidden" path="uniqueId" />
						<form:input type="hidden" path="invNo" />
						<input type="hidden" id="quotMtPartyId" name="quotMtPartyId" />
						<input type="hidden" id=quotMtId name=quotMtId />
						<input type="hidden" id="quotStnDtPk" name="quotStnDtPk" /> 
						<input type="hidden" name="adminRightsMap" id="adminRightsMap" value="${adminRightsMap}"  />
					</div>
					
					<input type="hidden" id="rLockStatus" name="rLockStatus" value="${rLockStatus}">
					
				</div>

			</form:form>
		</div>



		<!----------------------quotDt------------------------ -->
		
		<div id="openOnEdit" style="display: none" >


		<div class="form-group">
			<div class="container-fluid">
				<div class="row" style="font-size: smaller;font-weight: bold;">

					<div id="cdDivId" class="col-xs-12">
						<div id="toolbarQuotDt">
							<a class="btn btn-info" style="font-size: 15px" type="button" onClick="openDtEntryTab()"><span
										class="glyphicon glyphicon-plus"></span>&nbsp;Add
							</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-warning" style="font-size: 15px" type="button" onClick="quotDtExcelUpload()"><span
										class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel
							</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-info" style="font-size: 15px" type="button" onClick="openQuotSummary()">Display Summary
							</a>
							&nbsp;&nbsp;
							<input type="checkbox" id="chkLockQuotDt" onclick="javascript:lockAllQuotDt();"  /> <strong style="color: Coral;">LOCK </strong>
							
						</div>
						<div>
							<table  id="quotDtTabId"
								data-toggle="table" data-toolbar="#toolbarQuotDt" data-search="true"
								data-side-pagination="server" data-click-to-select="true"
								data-select-item-name="radioNameQuotDt"
								data-height="450"
								 data-pagination="true">
								<thead>
									<tr class="btn-primary">
										<th data-field="stateRd" data-radio="true"></th>
										<th data-field="action1" data-align="center">Edit</th>
										<th data-field="action2" data-align="center">Delete</th>
										<th data-field="barcode" data-sortable="false">Barcode</th>
										<th data-field="style" data-sortable="false">Style No</th>
										<th data-field="ktCol" data-sortable="false">Kt-Col</th>
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false">GrossWt</th>
										<th data-field="netWt" data-sortable="false">NetWt</th>
										<th data-field="lossPercDt" data-sortable="false">Loss %</th>
										<th data-field="metalValue" data-sortable="false">Metal Val</th>
										<th data-field="stoneValue" data-sortable="false">Stn Val</th>
										<th data-field="compValue" data-sortable="false">find Val</th>
										<th data-field="labourValue" data-sortable="false">Lab Val</th>
										<th data-field="setValue" data-sortable="false">Set Val</th>
										<th data-field="handlingCost" data-sortable="false">Hdlg Val</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="discAmount" data-sortable="false">Disc Amt</th>
										<th data-field="perPcFinalPrice" data-sortable="false">PerPc FinalPrice</th>
										<th data-field="finalPrice" data-sortable="false">FinalPrice</th>
										<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
										<th data-field="actionLock" data-align="center">LockUnLock</th>
										
									</tr>
								</thead>
							</table>
								<input type="hidden" id="quotDtPKId" name="quotingDtPKId" />
						</div>
					</div>


				</div>
			</div>
			
	
			
	</div>
		
		
	

		<!----------------------------------------- ApplyRate Button ------------------------------>
		
		
			<div class="row" >
				
					<div class="col-sm-1">
						<input type="button" value="Apply Rate" class="btn btn-primary" style="color: lime;" onclick="javascript:applyNewRate()"/>
					</div>
					
					<div class="col-sm-1">
						<input type="button" value="Update Barcode" class="btn btn-info" onclick="javascript:updateBarcode()"/>
					</div>
					<div class="col-sm-2" align="right">
						<input type="button" value="Update Diamond Rate" class="btn btn-info" onclick="javascript:applyDiamondRate()"/>
					</div>
				
			</div>
			
			
			
			<div class="row">
				<div>&nbsp;</div>
			</div>

	</div>



	<!-- hide on page load start here -->
	<div id="hideOnPageLoad" style="display: none">

	<!------------------------------------------ quotMetal  -------------------------------------->



<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			<div class="col-xs-12" style="font-size: smaller;font-weight: bold;">
				<div>
					<table id="quotMetalTableId" data-toggle="table" data-toolbar="#toolbarQuotMetal"
						data-pagination="false" data-side-pagination="server"
						data-click-to-select="false"
					    data-striped="false" 
						data-page-list="[5, 10, 20, 50, 100, 200]">
						<thead>
							<tr class="btn-primary">
								<th data-field="partNm" data-sortable="false">PartNm</th>
								<th data-field="purity" data-sortable="false">Purity</th>
								<th data-field="color" data-align="left">Color</th>
								<th data-field="qty" data-sortable="false">Qty</th>
								<th data-field="metalWt" data-sortable="false">Metal Wt</th>
								<th data-field="metalRate" data-sortable="false">Metal Rate</th>
								<th data-field="perGramRate" data-sortable="false">Per Gram Rate</th>
								<th data-field="lossPerc" data-formatter="lossPercFormatter">Loss %</th>
								<th data-field="metalValue" data-sortable="false">Metal Value</th>
								<th data-field="mainMetal" data-formatter="mianMetalFormatter">Main Metal</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			

	
	<div class="row">
		<div class="col-sm-12">&nbsp;</div>
	</div>






		<!------------------------------------------ quotStnDt -------------------------------------->

		
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
								<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addQuotStnDt()">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone</a>
							
							
						<input type="button" value="Update as Per Design Master" class="btn btn-primary"  onclick="javascript:updateDesignStoneDetail()"/>
								
							
						</div>
						
					<div>
						<table class="table-responsive" id="quotStnDtTabId"
							data-toggle="table" data-toolbar="#toolbarStnDt"
							data-side-pagination="server" data-click-to-select="true"
							data-select-item-name="radioNameQuotStnDt"
							data-page-list="[5, 10, 20, 50, 100, 200]">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="partNm" data-sortable="true">Part</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
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
						<input type="hidden" id="quotStnDtPkId" name="quotStnDtPkId" />
						
				</div>
				
				
			</div>
			
			
					
			<div class="row">
		<div class="col-sm-12">
					<div class="col-sm-2" id="shapeDivId" >
							<select class="form-control">
							<option value="">Select Shape</option>
							</select>
							
							</div>
							
					<div class="col-sm-2" id="qltyDivId" >
							<select class="form-control">
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
				</div>
				
			
	 			<!-- entry for quotStnDt -->
	 			<div class="form-group">
					<div id="rowQuotStoneDtId" style="display: none">
					</div>
				</div>
	 
	 
	    </div>
		
		


	
	
		<!------------------------------------------ quotCompDt -------------------------------------->
		
		
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
		
	
	<div id="comDivIdf" class="col-xs-12">
		
		<div class="container-fluid">
			<div class="row" style="font-size: smaller;font-weight: bold;">
			
					<div id="toolbarCompDt">
						<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addQuotCompDt()">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Component</a>
						
					</div>
					<div>
						<table class="table-responsive" id="quotCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							data-side-pagination="server" data-click-to-select="true"
							data-select-item-name="radioNameQuotCompDt"
							data-page-list="[5, 10, 20, 50, 100, 200]" >
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">Metal Wt</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="compQty" data-sortable="false">Pcs</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="perGramRate" data-sortable="false" data-formatter="inputFormatter">Per Gram Rate</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
				</div>
			</div>
			
			
			
			<!-- entry for quotCompDt -->
 			<div class="form-group">
				<div id="rowQuotCompDtId">
				</div>
			</div>
			
			
			
		</div>
				
		 <!-----------  quotLabDt ---------->
		 
		 
		 
		 <div class="row">
			<div class="form-group">
				<div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Labour Details</span>
				</div>
				
			</div>
		</div>
			 
		<div id="comDivIds" class="col-xs-12">	
			
			<div class="container-fluid">
				<div class="row" style="font-size: smaller;font-weight: bold;">
				
					<div id="toolbarLabDt">
						<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addQuotLabDt()">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour
						</a>
						
					</div>
				
					<div>
						<table class="table-responsive" id="quotLabDtTabId"
							data-toggle="table" data-toolbar="#toolbarLabDt"
							data-side-pagination="server" data-click-to-select="true"
							data-select-item-name="radioNameQuotLabDt"
							data-page-list="[5, 10, 20, 50, 100, 200]" >
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
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
			
			
			
			<!-- entry form (quotLabDt) -->
			<div class="form-group">
				<div id="rowQuotLabDtId">
				</div>
			</div>
			
		</div>
		
		
		
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		</div> <!-- ending the hide on page load -->	
		
	


	</div>  <!-- ending the panel body -->




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
	$(document)
			.ready(
					function(e) {
						
						
						
						$(".quotMtForm")
								.validate(
										{

											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/quotMt/invoiceNoAvailable.html' />",
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
												'party.id' : {
													required : true,
												},

											},

											highlight : function(element) {
												$(element).closest(
														'.form-group')
														.removeClass(
																'has-success')
														.addClass('has-error');
											},
											unhighlight : function(element) {
												$(element)
														.closest('.form-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											},

											messages : {
												invNo : {
													remote : "Invoice No already exists"
												}
											}

										});
						
						
						
						
						//-----quotDtForm
			
						$(".quotDetailsForm").validate(
							{
								rules : {
									'design.styleNo' : {
										required : true,
									},
									'grossWt' : {
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

						
						
						//quotMetalForm

						
						$(".quotMetalForm").validate({
							rules : {
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},
								'partNm.id' : {
									required : true,
								},
								metalPcs : {
									required : true,
									greaterThan : "0,0",
								},
							},
							highlight : function(element) {
								$(element).closest('.form-group')
										.removeClass('has-success')
										.addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group')
										.removeClass('has-error')
										.addClass('has-success');
							},
							messages : {
								metalPcs : {
									greaterThan : "This field is required"
								}
							},
						});
						
						
						//quotStnDtForm
						
						$(".quotStnDtForm")
								.validate(
										{	
										  rules : {
											  	'partNm.id' : {
													required : true,
												},
												'stoneType.id' : {
													required : true,
												},
												'shape.id' : {
													required : true,
												},
												'quality.id' : {
													required : true,
												},
												'setting.id' : {
													required : true,
												},
												'settingType.id' : {
													required : true,
												},
												size : {
													required : true,
												},
												vSieve : {
													required : true,
												},
												vSizeGroupStr : {
													required : true,
												},
												stone : {
													required : true,
													greaterThan : "0,0",
												},
												carat : {
													required : true,
													greaterThan : "0,0",
												},
												stnRate : {
													required : true,
												},
												handlingRate : {
													required : true,
												},
												setRate : {
													required : true,
												},
											},

											highlight : function(element) {
												$(element).closest(
														'.form-group')
														.removeClass(
																'has-success')
														.addClass('has-error');
											},
											unhighlight : function(element) {
												$(element)
														.closest('.form-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											},
											
											messages : {
												stone : {
													greaterThan : "This field is required"
												},
												carat : {
													greaterThan : "This field is required"
												}
											},	

										});
						
						
						
						
					
				
						
						//quotCompDtForm
						
						$(".quotCompDtEntForm")
								.validate(
										{	
										  rules : {
												'component.id' : {
													required : true,
												},
												'purity.id' : {
													required : true,
												},
												'color.id' : {
													required : true,
												},
												metalWt : {
													required : true,
													greaterThan : "0,0",
												},

											},

											highlight : function(element) {
												$(element).closest(
														'.form-group')
														.removeClass(
																'has-success')
														.addClass('has-error');
											},
											unhighlight : function(element) {
												$(element)
														.closest('.form-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											},
											
											messages : {
												metalWt : {
													greaterThan : "This field is required"
												}
											},	

										});
						
						
						//quotLabDtForm
						
						$(".quotLabDtEntForm")
								.validate(
										{	
										  rules : {
												'labourType.id' : {
													required : true,
												},
												
												labourRate : {
													required : true,
													greaterThan : "0,0",
												},

											},

											highlight : function(element) {
												$(element).closest(
														'.form-group')
														.removeClass(
																'has-success')
														.addClass('has-error');
											},
											unhighlight : function(element) {
												$(element)
														.closest('.form-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											},
											messages : {
												labourRate : {
													greaterThan : "This field is required"
												}
											},
											

										});
						
						
						

						$("#invDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});


						if (window.location.href.indexOf('edit') != -1) {
							var abcx = window.location.href;
							var quot = abcx.substring(window.location.href.indexOf('edit') + 5);
							var tempCost = quot.split(".");
							$('#quotMtId').val(tempCost[0]);
							
							var url = "/jewels/manufacturing/transactions/quot/reportExcel.html?mtId=" + $('#quotMtId').val();
							$("#quotExcelId").attr("href",url)
							
							
							popQuotationDetails();
							$("#" + document.querySelector("#disableQuotQuality").id).attr("id", "quotQuality");
							
							$('#openOnEdit').css('display','block');
							
							if($('#rLockStatus').val() === 'true'){
								$('#chkLockQuotDt').prop('checked', true);
							}
				
						}else if (window.location.href.indexOf('view') != -1) {
							
							canViewFlag = true;
							var abcx = window.location.href;
							var quot = abcx.substring(window.location.href.indexOf('view') + 5);
							var tempCost = quot.split(".");
							$('#quotMtId').val(tempCost[0]);
							popQuotationDetails();
							$("#" + document.querySelector("#disableQuotQuality").id).attr("id", "quotQuality");
							
							$('#openOnEdit').css('display','block');
							
							if($('#rLockStatus').val() === 'true'){
								$('#chkLockQuotDt').prop('checked', true);
							}
							
							$('#quotMtDivId .btn').removeAttr('onClick');
							$('#quotMtListBtnId').show();
							
							if($('#adminRightsMap').val() != true){
								$('#quotMtDivId :input').attr('disabled',true);
								$('#quotMtDivId .select').attr('disabled',true);
								$('#quotMtDivId .btn').hide();
								$('#quotMtDivId .btn').removeAttr('onClick');
								$('#quotMtListBtnId').show();
								$('#metalRatesBtn').hide();
									
							}
							
						}
						else{
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}
						
						
						$(function(){
					        $('.pagination').on( 'rightarrow-click leftarrow-click pagination-click', function(e){
					            
					        } );
					});
						
					
					});
	
	

	//----------MT save-------//
	
	function quotSave(){
		$("form#quotMtDataId").submit();
	}
	
	
	$(document)
	.on(
		'submit',
		'form#quotMtDataId',
		 function(e){
			
			$('#quotMtPartyId').val($('#party\\.id').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					if(data ==='-1'){
						displayMsg(this,null,'RefNo Duplicate Can Not Save');
					}else if(data ==='-2'){
						displayMsg(this,null,'MasterFlg Duplicate Can Not Save');
					}else{
						window.location.href = data;	
					}
					
					
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();
	
	})
	
	
	//-------------------quotationDt-------------------->
	
	$(document).on(
		'submit',
		'form#quotDtDataSubmitId',
		 function(e){
			
			$('#quotMtIdPk').val($('#quotMtId').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					$('#hideOnPageLoad').css('display','none');
					popQuotationDetails();
					$("#rowQuotDtId").css('display','none');
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();
	
	})

	
	
	
	
	function editQuotDt(quotDtId){
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotDt/validation.html?dtId="+quotDtId,
			type : 'GET',
			success : function(data) {
				if(data === "-1"){
					displayMsg(this, null, 'Cannot edit,Record is Locked');	
				}else{
						 $.ajax({
							url : "/jewels/manufacturing/transactions/quotDt/edit/"+ quotDtId + ".html",
							type : 'GET',
							dataType:"JSON",
							success : function(data) {
								modalModeFlag = "edit";
								
								$.ajax({
									url:"/jewels/manufacturing/transactions/quotDt/getSize.html?quotDtId="+quotDtId,
									type:"GET",
									success:function(data){
										$("#productSizevId").html(data);
									},
									async:false,
								});
								
								
								var validator = $( ".quotDtForm" ).validate();
								validator.resetForm();
								$('#myQuotDtModal').modal('show');
								
								
								
								setTimeout(function(){
									 $('#quotMetalTableDivId').css('display','block');
									$('#quotMetalIdTbl').bootstrapTable('resetView');
								}, 200);
								
								modelFlag = true;
								
								$.each(data,function(k,v){
									$('#quotDtModalDivId  #'+k).val(v);
								});
								
								
								  $("#quotMetalIdTbl").bootstrapTable('refresh',{
									url : "/jewels/manufacturing/transactions/quotMetal/listing/fromQuotDt.html?dtId="+quotDtId
								});
								
								  
								$('#design\\.mainStyleNo').attr('readonly','readonly');
								$('#quotDtFormId #purity\\.id').attr('disabled','disabled');
								$('#quotDtFormId #color\\.id').attr('disabled','disabled');
							}
						});
					
				  }
			  }
		
		  });
		
		}
	
	
	function deleteQuotDt(e,quotDtIdPk){
		
		
		if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, 'Cannot Delete Record Is Lock');
		}else{
		
		displayDlg(
				e,
				'javascript:deleteQuotDtData('+quotDtIdPk+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	
		}
	}
	
	
	
	
	
	
	function deleteQuotDtData(quotDtId){
		
		$("#modalDialog").modal("hide");
		
	
		
			 $.ajax({
				url : "/jewels/manufacturing/transactions/quotDt/delete/"+ quotDtId + ".html",
				type : 'GET',
				success : function(data) {
					$('#hideOnPageLoad').css('display','none');
					popQuotationDetails();
					
				}
			}); 

				
		
	}
	
	
	function popQuotationDetails() {
		
		$("#quotDtTabId")
				.bootstrapTable(
						'refresh',{
							url : "/jewels/manufacturing/transactions/quotDt/listing.html?mtId="+$('#quotMtId').val()+"&canViewFlag="+canViewFlag,
						});
		
		
		
		
		var dtdata='';
		
		setTimeout(function(){
			 dtdata = $("#quotDtTabId").bootstrapTable('getData').length;
			
			if(dtdata >0){
				$('#party\\.id').attr('disabled','disabled');
			}else{
				$('#party\\.id').removeAttr('disabled','disabled');
			}
			
		}, 100)
		
		$('#hideOnPageLoad').css('display','none');	
		
		
	}
	 
	

	var quotDtTableRow;
	var quotDtLockStatus = 'null';
	var finalPrice = "";
	var dtPurityVal;
	var dtColorVal;
	 $('#quotDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				quotDtTableRow = $element.attr('data-index');
				
				$('#rowQuotDtId').css('display','none');
				$('#quotDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
				quotDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;
				
				   dtPurityVal = jQuery.parseJSON(JSON.stringify(row)).purity;
				    dtColorVal = jQuery.parseJSON(JSON.stringify(row)).color;
				
				$('#tempImgDivId').empty();
				var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
				$('#tempImgDivId').append(tempDiv);
				
				$('#hideOnPageLoad').css('display','block');
				
				popQuotMetal();
				popQuotationStoneDetails();
				popQuotationComponentDetails();
				popQuotationLabDetails();
				popShapeDropDown();
				popStoneCancel();
				popCompCancel();
				popLabCancel();
			}) 
	
	
	function doQuotDtLockUnLock(dtIdPk){
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotDt/lockUnlock.html?dtId="+dtIdPk,
			type : 'GET',
			success : function(data) {
				if(data == -1){
					$('#hideOnPageLoad').css('display','none');
					popQuotationDetails();
					

				}
			}
		});
	}
	
	
	
	function lockAllQuotDt(){
		var quotStatus = document.getElementById("chkLockQuotDt").checked;
		var intStatus;
		if(quotStatus == true){
			intStatus = 1;
		}else{
			intStatus = 0;
		}
		
		 $.ajax({
			url : "/jewels/manufacturing/transactions/quotDt/dtLockUnlockAll.html?mtId="+$('#quotMtId').val()
					+"&status="+intStatus,
			type : 'GET',
			success : function(data) {
				if(data == -1){
					 $('#hideOnPageLoad').css('display','none');
					 popQuotationDetails();
					
				}
			}
		}); 
		
	}
	
	
	
	
	function openDtEntryTab(){
		 modalModeFlag = "add";
		 
		 
			var validator = $( ".quotDtForm" ).validate();
			validator.resetForm();
		$('#myQuotDtModal').modal('show');
		
		setTimeout(function(){
			 $('#quotMetalTableDivId').css('display','none');
			$('#quotMetalIdTbl').bootstrapTable('resetView');
		}, 200);
		
		modelFlag = true;
		
		 $('#quotDtModalDivId input[type="text"],textarea').val('');
		 $('#quotDtModalDivId input[type="number"]').val('0.00');
		 $('#quotDtModalDivId').find('select').val('');
		 $('#modalQuotDtId').val('');
		 
		 $('#quotDtFormId #purity\\.id').val($('#quotMtDataId #purity\\.id').val());
		 $('#quotDtFormId #color\\.id').val($('#quotMtDataId #color\\.id').val());
		
		 $('#design\\.mainStyleNo').removeAttr('readonly','readonly');
		 
		 $('#quotDtFormId #purity\\.id').removeAttr('disabled','disabled');
			$('#quotDtFormId #color\\.id').removeAttr('disabled','disabled');
		 
	}
	
	

	//----------------------stone--------//------------->

	
	function addQuotStnDt(){
		$.ajax({
			url:"/jewels/manufacturing/transactions/add/quotStnDt.html?quotDtId="+$('#quotDtPKId').val(),
			type:"GET",
			success:function(data){
				
				$('#rowQuotStoneDtId').css('display','block');
				$("#rowQuotStoneDtId").html(data);
				
				 
				$('#stnPkId').val('');
				
			  	$('#quotStnDtEnt input[type="text"],textarea,hidden').val('');
			    $('#quotStnDtEnt input[type="number"]').val('0');
			    $('#quotStnDtEnt').find('select').val('');
				
				
				$('#stoneType\\.id').focus();
				
			}
		})
		
	}
	
	
	
	function popStoneCancel(){
		$('#stnPkId').val('');
	  	$('#quotStnDtEnt input[type="text"],textarea,hidden').val('');
	    $('#quotStnDtEnt input[type="number"]').val('0');
	    $('#quotStnDtEnt').find('select').val('');
	    $('#rowQuotStoneDtId').css('display','none');
	}
	
	
	
	function popQuotationStoneDetails(){
		
		
		$("#quotStnDtTabId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/quotStnDt/listing.html?quotDtId="
							+ $('#quotDtPKId').val()+"&canViewFlag="+canViewFlag,
				});

		
	}
	
	var quotStnLockStatus = 'null';
	$('#quotStnDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				quotStnLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				 $('#quotStnDtPkId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				 	$('#stnPkId').val('');
				  	$('#quotStnDtEnt input[type="text"],textarea,hidden').val('');
				    $('#quotStnDtEnt input[type="number"]').val('0');
				    $('#quotStnDtEnt').find('select').val('');
				    $('#rowQuotStoneDtId').css('display','none');
				 
			});
	
	
	
	function editQuotStnDt(pkQuotStnDtId){
		
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/quotStnDt/validationEdit.html?stnId="+pkQuotStnDtId,
				type : 'GET',
				success : function(data) {
					
					if(data == "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
					
						$('#quotStnDtPk').val(pkQuotStnDtId);
						$.ajax({
							url : "/jewels/manufacturing/transactions/quotStnDt/edit/"+ pkQuotStnDtId + ".html",
							type : 'GET',
							success : function(data) {
								 $("#rowQuotStoneDtId").html(data);
								 $("#rowQuotStoneDtId").css('display','block');
								 $("#stoneType\\.id").focus();
								 
							}
						});
					}
					
				}
			});
			
		 }	
		
	}
	
	
	function deleteQuotStnDt(e,quotStnIdDt){
		
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(quotStnLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record Is Lock');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteQuotStnData('+quotStnIdDt+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		 }
		
	}
	
	
	
	function deleteQuotStnData(quotStnDtIdPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotStnDt/validationEdit.html?stnId="+quotStnDtIdPk,
			type : 'GET',
			success : function(data) {
				
				if(data == "-1"){
					displayMsg(this, null, 'Cannot delete,Record is Locked');	
				}else{
		
					 $.ajax({
						url : "/jewels/manufacturing/transactions/quotStnDt/delete/"+ quotStnDtIdPk + ".html",
						type : 'GET',
						success : function(data) {
							
							if(data === '-2'){
								displayMsg(this, null, 'Export is Closed');
							}else{
								popQuotationStoneDetails();
								updateQuotDtTable($('#quotDtPKId').val());
									
							}
							
						 }
					 
						}); 
		
					}
				
				}
			});
		
	}
	
	
	
	
	$(document)
	.on(
		'submit',
		'form#quotStnDtEnt',
		 function(e){
			
			$('#pQuotMtId').val($('#quotMtId').val());
			$('#pQuotDtId').val($('#quotDtPKId').val());
			$('#pSieve').val($('#vSieve').val());
			$('#pSizeGroup').val($('#vSizeGroupStr').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
						popQuotationStoneDetails();
						
						updateQuotDtTable($('#quotDtPKId').val());
						popQuotMetal();
					
						$('#stnPkId').val('');
						$('form#quotStnDtEnt select').val('');
						$('#vSieve').val('');
						$('#vSizeGroupStr').val('');
						$('#stone').val('0');
						$('#carat').val('0.0');
						$('#stnRate').val('0.0');
						$('#handlingRate').val('0.0');
						$('#setRate').val('0.0');
						
						if(data === '-2'){
							$("#rowQuotStoneDtId").css('display' , 'none');	
						}
						
					
					
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();
	
	});

	
 
 
	 function doStoneDtLockUnLock(stnDtIdPk){
		 
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		 
			 $.ajax({
					url : "/jewels/manufacturing/transactions/quotStnDt/lockUnlock.html?stnDtId="+stnDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data == -1){
							popQuotationStoneDetails();
						}
					}
				});
		 }
	 
	 }
 
	 
 
	function lockAllQuotStnDt(){
		var quotStnStatus = document.getElementById("chkLockQuotStnDt").checked;
		var intStnStatus;
			if(quotStnStatus == true){
				intStnStatus = 1;
			}else{
				intStnStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/transactions/quotStnDt/stnLockUnlockAll.html?mtId="+$('#quotMtId').val()
						+"&status="+intStnStatus,
				type : 'GET',
				success : function(data) {
					if(data == -1){
						popQuotationStoneDetails();
					}
				}
			}); 
			 
		}
	
	
	
	function popQualitys(vSel){
		$
		.ajax({
			url : '/jewels/manufacturing/transactions/qualityQuotDt/list.html?shapeId='
					+ vSel,
			type : 'GET',
			success : function(data) {
				$("#qualityId").html(data);
			}
		});
	}
	
	
	
	
	$('#quotStnDtTabId').bootstrapTable({})
		.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#quotStnDtTabId").bootstrapTable('getData'));
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
				url : "/jewels/manufacturing/transactions/quotStnDt/settingRateFromMaster.html?quotDtId="+$('#quotDtPKId').val()
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
				url : "/jewels/manufacturing/transactions/quotStnDt/stoneRateFromMaster.html",
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
			if(!!$('#shape\\.id').val() && !!$('#partNm\\.id').val() && !!$('#stoneType\\.id').val() && !!$('#party\\.id').val() && $('#stnRate').val()>0 ){
			$.ajax({
				url : "/jewels/manufacturing/transactions/quotStnDt/handlingRateFromMaster.html?quotDtId="+$('#quotDtPKId').val()
						+"&partId="+$('#partNm\\.id').val()
						+"&shapeId="+$('#shape\\.id').val()
						+"&stoneTypeId="+$('#stoneType\\.id').val()
						+"&mtPartyId="+$('#party\\.id').val()
						+"&stnRate="+$('#stnRate').val(),
						
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
	

	
	function popQuotationComponentDetails(){
		
		$("#quotCompDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/quotCompDt/listing.html?quotDtId="
						+ $('#quotDtPKId').val()+"&canViewFlag="+canViewFlag,
			});
		
	}
	

	var quotCompLockStatus = 'null';
	$('#quotCompDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				quotCompLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				$('#quotCompDtEnt input[type="text"],textarea,hidden').val('');
			    $('#quotCompDtEnt input[type="number"]').val('0');
			    $('#quotCompDtEnt').find('select').val('');
			    $('#quotCompDtPk').val('');
			    $('#quotCompDtEnt input[type="checkbox"]').prop("checked", false);
			    $('#rowQuotCompDtId').css('display','none');
			})
	
	
	
	
	$(document)
		.on(
			'submit',
			'form#quotCompDtEnt',
			 function(e){
				
				$('#forCompQuotMtId').val($('#quotMtId').val());
				$('#forCompQuotDtId').val($('#quotDtPKId').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {
						
						popQuotationComponentDetails();
						updateQuotDtTable($('#quotDtPKId').val());
						popQuotMetal();
											
						$('#component\\.id').focus();
						
						$('#component\\.id').val('');
						
						$("#quotCompDtEnt #purity\\.id option").filter(function() {
						    return $(this).text() ==dtPurityVal;
						}).prop("selected", true);
						
						
						$("#quotCompDtEnt #color\\.id option").filter(function() {
						    return $(this).text() ==dtColorVal;
						}).prop("selected", true);
						
			/* 			$('#quotCompDtEnt input[type="text"],textarea,hidden').val('');
					    $('#quotCompDtEnt input[type="number"]').val('0');
					    $('#quotCompDtEnt').find('select').val(''); */
					    $('#quotCompDtPk').val('');
					    $('#quotCompDtEnt #compMetalWt').val('0.0');
						$('#quotCompDtEnt #compPcs').val('0.0');
						$('#quotCompDtEnt #compRate').val('0.0');
					    
					    $('#quotCompDtEnt input[type="checkbox"]').prop("checked", false);
						
						
						if(data === '-2'){
							$('#rowQuotCompDtId').css('display','none');
						}
						
					},
					
					error : function(data, textStatus, jqXHR){
						alert("ERROR");
					}
						
				})
				
				e.preventDefault();
		
	})
	
	
	function addQuotCompDt(){
		
		if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			 $.ajax({
					url : "/jewels/manufacturing/transactions/quotCompDt/add.html",
					type : 'GET',
					success : function(data) {
						$('#rowQuotCompDtId').css('display','block');
						$("#rowQuotCompDtId").html(data);
						$('#component\\.id').focus();
						$('#quotCompDtEnt input[type="text"],textarea,hidden').val('');
					    $('#quotCompDtEnt input[type="number"]').val('0');
					    $('#quotCompDtEnt').find('select').val('');
					    $('#quotCompDtPk').val('');
					    $('#quotCompDtEnt input[type="checkbox"]').prop("checked", false);
					    
					    $("#quotCompDtEnt #purity\\.id option").filter(function() {
						    return $(this).text() ==dtPurityVal;
						}).prop("selected", true);
						
						
						$("#quotCompDtEnt #color\\.id option").filter(function() {
						    return $(this).text() ==dtColorVal;
						}).prop("selected", true);
						
					}
				});
			
		 }
	}
	
	
	function popCompCancel(){
		$('#quotCompDtEnt input[type="text"],textarea,hidden').val('');
	    $('#quotCompDtEnt input[type="number"]').val('0');
	    $('#quotCompDtEnt').find('select').val('');
	    $('#quotCompDtPk').val('');
	    $('#quotCompDtEnt input[type="checkbox"]').prop("checked", false);
	    $('#rowQuotCompDtId').css('display','none');
	}
	
	
	
	function editQuotCompDt(pkCompDtId){
		
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/quotCompDt/validationEdit.html?compId="+pkCompDtId,
				type : 'GET',
				success : function(data) {
					
					if(data == "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/transactions/quotCompDt/edit/"+ pkCompDtId + ".html",
							type : 'GET',
							success : function(data) {
								$('#rowQuotCompDtId').css('display','block');
								$("#rowQuotCompDtId").html(data);
								$('#component\\.id').focus();
							}
						});
					}
					
				}
			});
			
		 }
		
	}
	
	
	
	
	function deleteQuotCompDt(e,quotCompDtId){
		
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(quotCompLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record Is Lock');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteQuotCompData('+quotCompDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		 }
		
		
	}
	
	
	function deleteQuotCompData(pkCompDtId){
		
		$("#modalDialog").modal("hide");

		
			 $.ajax({
				url : "/jewels/manufacturing/transactions/quotCompDt/delete/"+ pkCompDtId + ".html",
				type : 'GET',
				success : function(data) {
					popQuotationComponentDetails();
					updateQuotDtTable($('#quotDtPKId').val());
					
				}
			}); 
		
		}
	
	
	
	
	
	
	function doCompDtLockUnLock(compDtIdPk){
		
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
			 $.ajax({
					url : "/jewels/manufacturing/transactions/quotCompDt/lockUnlock.html?compDtId="+compDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data === '-1'){
							popQuotationComponentDetails();
						}
					}
				});
		 }
	}
	
	
	function lockAllQuotCompDt(){
		
		var quotCompStatus = document.getElementById("chkLockQuotCompDt").checked;
		var intCompStatus;
			if(quotCompStatus == true){
				intCompStatus = 1;
			}else{
				intCompStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/transactions/quotCompDt/LockUnlockAll.html?mtId="+$('#quotMtId').val()
						+"&status="+intCompStatus,
				type : 'GET',
				success : function(data) {
					if(data === '-1'){
						popQuotationComponentDetails();
					}
				}
			}); 
		
	}
	
	
	function popQuotCompRate(){
		
	
		if(!!$('#quotCompDtEnt #component\\.id').val() && !!$('#quotCompDtEnt #purity\\.id').val() && !!$('#party\\.id').val()){
			
			$.ajax({
				url:"/jewels/manufacturing/transactions/quotCompDt/rateFromMaster.html?componentId="+$('#component\\.id').val()
						+"&purityId="+$('#quotCompDtEnt #purity\\.id').val()
						+"&mPartyId="+$('#party\\.id').val(),
				type:"GET",
				dataType:"JSON",
				success:function(data){
					
					$('#quotCompDtEnt #compRate').val('0.0');
					$("#perGramRate1").prop("checked", false);
					$("#perPcRate1").prop("checked", false);
					
					
					$.each(data,function(k,v){
						if(k === 'rate'){
							$('#quotCompDtEnt #compRate').val(v);
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
	
	
	
	//----------------------quotLabDt--------//------------->
	
	
	
	
	function popQuotationLabDetails(){
		
		$("#quotLabDtTabId").bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/transactions/quotLabDt/listing.html?quotDtId="
						+ $('#quotDtPKId').val()+"&canViewFlag="+canViewFlag,
			});
		
	}
	
	var quotLabDtLockStatus = 'null'
	$('#quotLabDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				quotLabDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				$('#quotLabDtEnt input[type="text"],textarea,hidden').val('');
			    $('#quotLabDtEnt input[type="number"]').val('0');
			    $('#quotLabDtEnt').find('select').val('');
			    $('#quotLabDtPk').val('');
			    $('#quotLabDtEnt input[type="checkbox"]').prop("checked", false);
			    $('#rowQuotLabDtId').css('display','none');
			})
	
	
			
	$(document).
		on('submit',
			'form#quotLabDtEnt',
			function(e){
			
			$('#forLabQuotMtId').val($('#quotMtId').val());
			$('#forLabQuotDtId').val($('#quotDtPKId').val());
			
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
					}else{
						
						popQuotationLabDetails();
						updateQuotDtTable($('#quotDtPKId').val());
						
						$('#quotLabDtEnt input[type="text"],textarea,hidden').val('');
					    $('#quotLabDtEnt input[type="number"]').val('0');
					    $('#quotLabDtEnt').find('select').val('');
					    $('#quotLabDtPk').val('');
					    $('#quotLabDtEnt input[type="checkbox"]').prop("checked", false);
						
						if(data === '-2'){
							$('#rowQuotLabDtId').css('display','none');
						}
						
					}
					
				},
				error : function(data, textStatus, jqXHR){
					
				}
				
			})
			
			e.preventDefault();
			
		})
		
		
	function addQuotLabDt(){
		
		if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
			
				$.ajax({
					url : "/jewels/manufacturing/transactions/quotLabDt/add.html",
					type : 'GET',
					success : function(data) {
						$("#rowQuotLabDtId").html(data); 
						$('#rowQuotLabDtId').css('display','block');
						$('#labourType\\.id').focus();
						
						$('#quotLabDtEnt input[type="text"],textarea,hidden').val('');
					    $('#quotLabDtEnt input[type="number"]').val('0');
					    $('#quotLabDtEnt').find('select').val('');
					    $('#quotLabDtPk').val('');
					    $('#quotLabDtEnt input[type="checkbox"]').prop("checked", false);
						
					}
				});
			
			
		 }
	}	
	
	
	
	function popLabCancel(){
		$('#quotLabDtEnt input[type="text"],textarea,hidden').val('');
	    $('#quotLabDtEnt input[type="number"]').val('0');
	    $('#quotLabDtEnt').find('select').val('');
	    $('#quotLabDtPk').val('');
	    $('#quotLabDtEnt input[type="checkbox"]').prop("checked", false);
	    $('#rowQuotLabDtId').css('display','none');
	}
	
		
		
	function editQuotLabDt(pkLabDt){
		
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/quotLabDt/validationEdit.html?labId="+pkLabDt,
				type : 'GET',
				success : function(data) {
					if(data == "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/transactions/quotLabDt/edit/"+ pkLabDt + ".html",
							type : 'GET',
							success : function(data) {
								$("#rowQuotLabDtId").html(data); 
								$('#rowQuotLabDtId').css('display','block');
								$('#labourType\\.id').focus();
							}
						});
						
					}
					
				}
			});
			
		 }
		
		
	}
	
	
	
	function deleteQuotLabDt(e,quotLabDtId){
		
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(quotLabDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record Is Locked ');
		 }else{
			
			displayDlg(
					e,
					'javascript:deleteQuotLabData('+quotLabDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		  }
			
	}
	
	
	function deleteQuotLabData(pkLabDt){
		
		$("#modalDialog").modal("hide");
		
					 $.ajax({
						url : "/jewels/manufacturing/transactions/quotLabDt/delete/"+ pkLabDt + ".html",
						type : 'GET',
						success : function(data) {
							popQuotationLabDetails();
							updateQuotDtTable($('#quotDtPKId').val());
						}
					}); 
		
					
	 }
	
	
	
	
	
	
	
	function doLabDtLockUnLock(labDtIdPk){
		
		 if(quotDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			 $.ajax({
					url : "/jewels/manufacturing/transactions/quotLabDt/lockUnlock.html?labDtId="+labDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data == -1){
							popQuotationLabDetails();
						}
					}
				});
		 }
	}
	
	
	function lockAllQuotLabDt(){
		var quotLabStatus = document.getElementById("chkLockQuotLabDt").checked;
		var intLabStatus;
			if(quotLabStatus == true){
				intLabStatus = 1;
			}else{
				intLabStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/transactions/quotLabDt/LockUnlockAll.html?mtId="+$('#quotMtId').val()
						+"&status="+intLabStatus,
				type : 'GET',
				success : function(data) {
					if(data == -1){
						popQuotationLabDetails();
					}
				}
			}); 
		
	}
	
	
	
		
		/* // --------------tab-------------------------//------------->>>>>
		
		function popLabourType(){
			$("#changeLabId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/changeLabourType/listing.html"
					});
		}
		
		function popInwardType(){
			$("#changeInwardId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/changeInwardType/listing.html"
					});
		}

		
	 */
		
		
		
		
	//---------------------- APPLY RATE --------//----------------->
	 
	 
		
		function applyNewRate(e){
				
			displayDlg(
					e,
					'javascript:applyRate();',
					'Apply Rate',
					'Are you sure Apply New Rate  ?',
					'Continue');
		
	
		}
	 
	 
	 
	 function applyRate(){
		 $("#modalDialog").modal("hide");
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		 
		 var postData = $('#quotMtDataId').serializeArray();
		 
		 
		 $.ajax({
				url : "/jewels/manufacturing/transactions/applyRate/quotMt.html?tempPartyId="+$('#party\\.id').val()+"&tempPurityId="+$('#quotMtDataId #purity\\.id').val()
				+"&tempColorId="+$('#quotMtDataId #color\\.id').val(),
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					
					if(data === '1'){
						popQuotationDetails()
						
						$('#hideOnPageLoad').css('display','none');
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
		
		
		
	
			
	//---------------------- QUOT QUALITY --------//----------------->
		
		
	function popQuotQDetails() {
		
		$("#quotQId")
			.bootstrapTable(
				'refresh',
					{
						url : "/jewels/manufacturing/transactions/quotQuality/listing.html?pInvNo="+ $('#invNo').val(),
					});
		
		}
		
	
	function goToQuotQuality(){
		$('#rowQQId').css('display','');
		$('#quotQualityPkId').val('');
		
		$('form#quottQuality input[type="text"],texatrea').val('');
		$('form#quottQuality select').val('');
		$('#quotShapeId').focus();
	}
	
	function editQuotQuality(quotQualId) {
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotQuality/edit/" + quotQualId + ".html",
			type : 'GET',
			success : function(data) {
				//alert(data);	
				$("#rowQQId").html(data);
				$("#rowQQId").css('display', 'block');
				$('#pOQInvNo').val($('#invNo').val());
			}
		});
	}
	
	
	/* $(document).on('submit', 'form#quottQuality', function(e) {
		
		$('#pOQInvNo').val($('#invNo').val());

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				$('#quotQualityPkId').val('');
				popQuotQDetails();
				$('form#quottQuality input[type="text"],texatrea').val('');
				$('form#quottQuality select').val('');
				$("#rowQQId").css('display', 'none');
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});
	 */
	
	
	
	function popQuotQualitys(){
		
		$.ajax({
			url : '/jewels/manufacturing/transactions/quotQuality/list.html?shapeId=' + $('#quotShapeId').val(),
			type : 'GET',
			success : function(data) {
				$("#quotQtilyId").html(data);
			}
		});
		
	}
	
	
	function popDiscPerc(){
	 	$.ajax({
			url : "/jewels/manufacturing/transactions/quotParty/discPerc.html?partyId="+$('#party\\.id').val(),
			type : 'GET',
			success : function(data) {
				$('#discPercent').val(data);
			}
		}); 
		
	}
	
	
	function popDiscPerAmt(val){
		
		if(val=== 1){
			if(finalPrice === '0.0'){
				$('#discAmount').val('0.0');
			}else{
				var discAmt = parseFloat(($('#mDiscPerc').val()*finalPrice)/100);
				$('#discAmount').val(discAmt.toFixed(2));
			}
			
			
		}else if (val=== 2) {
			if(finalPrice === '0.0'){
				$('#mDiscPerc').val('0.0');
			}else{
			var discPerc = parseFloat(($('#discAmount').val()*100)/finalPrice);
			$('#mDiscPerc').val(discPerc.toFixed(2));
			}
		}
	
	}
	
	
	
	//----------------Metal details -------------//
	
	function addQuotMetal() {
		$("#rowQuotMetalDtId").css('display','block');
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotMetal/add.html",
			type : 'GET',
			success : function(data) {
				$("#quotMetalIdPk").val('');
				$("#rowQuotMetalDtId").html(data);
				$('#purity\\.id').focus();
			}
		});
	}
	
	
	
	$('#quotMetalTableId').bootstrapTable({}).on('click-row.bs.table',
		function(e, row, $element) {
			$("#rowQuotMetalDtId").css('display','none');
		});
	 
	
	function editQuotMetal(id){
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotMetal/edit/"+id+".html",
			type : 'GET',
			success : function(data) {
				$("#rowQuotMetalDtId").css('display', 'block');
				$("#rowQuotMetalDtId").html(data);
			}
		});
	}
	
	
	function popQuotMetal(){
	  $("#quotMetalTableId").bootstrapTable('refresh',{
					url : "/jewels/manufacturing/transactions/quotMetal/listing.html?quotDtId="+ $('#quotDtPKId').val(),
				});
	}
	
	
	$(document).on('submit', 'form#quotMetalId', function(e) {
		$('#vQuotDtId').val($('#quotDtPKId').val());

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
			
				if(data === '-2'){
					popCancelQuotMetal();
					popQuotMetal();
				}else if(data === '-3'){
					displayMsg(this,null,"only one record can be main metal");	
				}else{
					popQuotMetal();
					$('#quotMetalId input[type="text"],textarea,input[type="password"]').val('');
					$('#quotMetalId input[type="number"]').val('0');
					$('#quotMetalId').find('input[type=checkbox]:checked').removeAttr('checked');
					$('#quotMetalIdPk').val('');
					$('#quotMetalId').find('select').val('');	
				}
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});

	function deleteQuotMetal(e,id){
		displayDlg(e, 'javascript:confirmDeleteQuotMetal('+id+');','Quot Metal', 'Do You Want To Delete', 'Continue');
	}
	 
	
	function confirmDeleteQuotMetal(id){
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotMetal/delete/"+id+".html",
			type : 'GET',
			success : function(data) {
				popQuotMetal();
			}
		});
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
	
	
	function popCancelQuotMetal(){
		$('#quotMetalId input[type="text"],textarea,input[type="password"]').val('');
		$('#quotMetalId input[type="number"]').val('0');
		$('#quotMetalId').find('input[type=checkbox]:checked').removeAttr('checked');
		$('#quotMetalIdPk').val('');
		$('#quotMetalId').find('select').val('');
		$("#rowQuotMetalDtId").css('display','none');
	}
	
	
//----------------------------------------------//
	
	function popMetalRate(){
		$('#myQuotRateModal').modal('show');
		popQuotMetalRateTbl();
		
		setTimeout(function(){
			$('#quotMetalRateIdTbl').bootstrapTable('resetView');
		}, 300);
		
	}
	
	
	
	
	function updateQuotDtTable(dtId){
		
		
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotDt/getData/"+dtId+".html",
			type : 'GET',
			dataType : 'JSON',
			success : function(data) {
				$('#quotDtTabId').bootstrapTable('updateRow', {
					index : Number(quotDtTableRow),
					row : data
				});
				
				
			}
		});
	}
	
	
	
	function deleteQuotQuality(e,id){
		displayDlg(
				e,
				'javascript:deleteQuotQualityData('+id+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}
	
	
	function deleteQuotQualityData(id){
		$("#modalDialog").modal("hide");

		 $.ajax({
			url : "/jewels/manufacturing/transactions/quotQuality/delete/"+id+".html",
			type : 'GET',
			success : function(data) {
				popQuotQDetails();
			}
		}); 
		
	}
	
	
	function updateBarcode(){
		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		 $.ajax({
				url : "/jewels/manufacturing/transactions/quotMt/barcode.html?mtId="+$('#quotMtId').val(),
				type : 'GET',
				success : function(data) {
					if(data === "1"){
						 $('#hideOnPageLoad').css('display','none');
						 popQuotationDetails();
						
					}else{
						
						displayMsg(this,null,'Barcode Not Updated Successfully ');
					}
					$.unblockUI();
				}
			}); 
		
		
		
		
	}
	
	
	function openQuotSummary(){
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/quotMt/summary.html?mtId="+$('#quotMtId').val(),
			type :'GET',
			dataType:'JSON',
			success: function(data){
				if(data !== "-1"){
					
					$('#myQuotSumModal').modal('show');
					
					$.each(data,function(k,v){
						$('#quotSumDivId  #'+k).val(v);
					});
					
					
					
				}else{
					displayMsg(this,null,'Error Contact Admin');
				}
				
			}	
		})
		
		
		
	}
	
	
	function applyDiamondRate(){
		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		
		 $.ajax({
				url : "/jewels/manufacturing/transactions/quotMt/applyDiamondRate.html?mtId="+$('#quotMtId').val(),
				type : 'GET',
				success : function(data) {
					
					if(data === "1"){
						 $('#hideOnPageLoad').css('display','none');
						 popQuotationDetails();
						
					}else{
						
						displayMsg(this,null,'Error Occur In Diamond Rate ');
					}
					$.unblockUI();
				}
			}); 
		 
		
	}
	
	
	

	function updateDesignStoneDetail(){
		
		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		
		 $.ajax({
			 url : "/jewels/manufacturing/transactions/quotStnDt/updateStoneAsPerDesign.html?quotDtId="
					+ $('#quotDtPKId').val(),
				type : 'GET',
				success : function(data) {
					
					if(data === "1"){
						popQuotationStoneDetails();
						
					}else{
						
						displayMsg(this,null,'Error Occur ');
					}
					$.unblockUI();
				}
			}); 
		 
		
		
		
	}
	
	
	
function popQuotPickUp() {
		
		//alert('part ID '+$('#party\\.id').val());
		
		if($('#party\\.id').val() !="" && $('#quotMtId').val()!= ""){
			
			
			 window.location.href ="/jewels/manufacturing/masters/order/pickUp.html?partyId="+$('#party\\.id').val()
					 + "&mtId="+$('#quotMtId').val()+ "&pickUpType=quot";
		}else{
			displayMsg(this, null, 'Please Select Party or Quotation not created !');
		}
	  
	}
	
	
function update999(){
	 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	 
	 
	 var postData = $('#quotMtDataId').serializeArray();
	 
	 $.ajax({
			url : "/jewels/manufacturing/transactions/quotMt/updatepuritycost.html?tempPartyId="+$('#party\\.id').val()+"&tempPurityId="+$('#quotMtDataId #purity\\.id').val()
			+"&tempColorId="+$('#quotMtDataId #color\\.id').val(),
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR){
				
				if(data === '1'){
					popQuotationDetails()
					
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




	
	function popShapeDropDown() {
		$.ajax({
			url : '/jewels/manufacturing/masters/shape/dropDownlist.html',
			type : 'GET',
			success : function(data) {
				$("#shapeDivId").html(data);
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
				$("#qltyDivId").html(data);
			}
		});
		
	}
	
	
	function updateQuality(){
	
	var e = document.getElementById("shapeDropDownId");
	var shpId = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("qltyDropDownId");
	var qualityId = e.options[e.selectedIndex].value;
	
	
	 $.ajax({
			url : "/jewels/manufacturing/transactions/quotStnDt/updateQlty.html?dtId="+$('#quotDtPKId').val()+"&shapeId="+shpId+"&qualityId="+qualityId,
			type : 'GET',
			success : function(data) {
				
				if(data === "1"){
					
					$('#qltyDropDownId').val('');
					popQuotationStoneDetails();
				}else{
					displayMsg(this, null, 'Can not Update Quality Contact Admin !');
				}
			}
		});
	}
	
	
	function popOrderPickUp(){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/party/getClientName.html?partyId="+ $('#party\\.id').val(),
			type:"GET",
			success:function(data){
				
				$('#partyNm').val(data);
				}
				
				
			});
		
		
		setTimeout(function() {
			
		}, 500);
		$('#modalquotMtId').val($('#quotMtId').val());
		$('#myOrderPickupModal').modal('show');	
	}
	
	
	
	var quotMetalDtTableRow;
	var quotMetalDtLockStatus = 'null';
	 $('#quotMetalTableId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				quotMetalDtTableRow = $element.attr('data-index');
				quotMetalDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				
			});
	 
	 
	 
		$('#quotMetalTableId').bootstrapTable({}).on(
				'dbl-click-cell.bs.table',
				function(e, field, value, row, $element,index) {
					
					
					if(field==='lossPerc'){
						
						if(jQuery.parseJSON(JSON.stringify(row)).rLock==='true'){
							 displayMsg(this, null, ' Cannot Edit Record Is Lock');
						 }else{
							 $('#lossPercVal'+quotMetalDtTableRow).removeAttr('disabled');
							 $('#lossPercVal'+quotMetalDtTableRow).focus();
						 }
						
						
					}
					
					
				
					
					 
				
				
				})
	
	
	
	function lossPercFormatter(value, row, index){
		
		var tempId = 'lossPercVal' + Number(index);

			var vId = row.id;
		    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onblur="javascript:disableLossPerc()" onchange="javascript:updateLossPerc(this,'+vId+')" disabled />';
		}
		
		
	function disableLossPerc(){
		
		$('#lossPercVal'+quotMetalDtTableRow).attr('disabled', 'disabled');
	}

	function updateLossPerc(param, val){
		$.ajax({
			url : "/jewels/manufacturing/transactions/quotMetal/updateLossPerc.html?id="+val+"&lossPerc="+ param.value,
			type : 'GET',
			success : function(data) {
				
					popQuotMetal();
					updateQuotDtTable($('#quotDtPKId').val());
								
					
			
			}

		});
	}


	function quotDtExcelUpload(){

		window.location.href="/jewels/manufacturing/transactions/quotMt/uploadExcel.html?mtId="+$('#quotMtId').val()
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


