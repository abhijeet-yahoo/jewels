<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
  
  <div class="panel-body">
  
  <%@ include file="/WEB-INF/jsp/common/modalJobRecDt.jsp"%>
		
<%@ include file="/WEB-INF/jsp/common/modalJobProcess.jsp"%>


<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
  

		<div id="openOnEdit" style="display: none" >
		
	<div id="toolbarCostDt">
			
									<div id="bagPickupDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-info" type="button"  onClick="goToPickup()" ><span
											class="glyphicon glyphicon-plus"></span>&nbsp;Pick Up
										</a>
									</div>
								
								
									<div id="hallmarkDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-link" type="button"  onClick="applyLabourCharge('Hallmark')" >Apply Hallmark
										</a>
									</div>
									
									<div id="lazerDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-link" type="button"  onClick="applyLabourCharge('Stamping')" >Apply Stamping
										</a>
									</div>
									
									<div id="gradingDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-link" type="button"  onClick="applyGrading('Grade')" >Apply Grading
										</a>
									</div>
									
									
									<div id="engravingDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-link" type="button"  onClick="applyLabourCharge('Engraving')" >Apply Engraving
										</a>
									</div>
									
									 <div id="labDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-link" type="button"  onClick="applyLabourCharge('Labour')" >Apply Labour
										</a>
									</div>
									
									
														
							</div>
					
					
					
					
						<div class="table-responsive">
							<table  id="jobRecDtTabId"
								data-toggle="table" data-height="400" data-search="true" data-maintain-meta-data="true" >
								<thead>
									<tr>
										<th data-field="stateRd" data-checkbox="true"></th>  
										<th data-field="action1" data-align="center">Edit</th>
										<th data-field="action2" data-align="center">Delete</th>
										<th data-field="srNo">Sr. No.</th>
										<th data-field="itemNo">Item No</th>
										<th data-field="party" data-sortable="false">Client</th>
										<th data-field="ordNo" data-sortable="false">Order No</th>
										<th data-field="ordRefNo" data-sortable="false">Ref No</th>
										<th data-field="style" data-sortable="false" class="span5">Style No</th>
										<th data-field="bag" data-sortable="false" class="span5">Bag</th>
										<th data-field="purity" data-sortable="false">Kt</th>
										<th data-field="color" data-sortable="false">Color</th>
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false" data-formatter="grossWtFormatter">Gross Wt</th>
										<th data-field="netWt" data-sortable="false">Net Wt</th>
										<!-- <th data-field="process" data-sortable="false">Process</th> -->
										<th data-field="lossPercDt" data-sortable="false">Loss %</th>
										<th data-field="metalRate" data-sortable="false">Metal Rate</th>
										<th data-field="metalValue" data-sortable="false">Metal Value</th>
										<th data-field="stoneValue" data-sortable="false">Stn Value</th>
										<th data-field="compValue" data-sortable="false">Comp Value</th>
										<th data-field="labourValue" data-sortable="false">Lab Value</th>
										<th data-field="setValue" data-sortable="false">Set Value</th>
										<th data-field="handlingCost" data-sortable="false">Hdlg Cost</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="other" data-sortable="false">Other</th>
										<th data-field="dispPercentDt" data-sortable="false">Disp%</th>
										<th data-field="discAmount" data-sortable="false">Disc Amt</th>
										<th data-field="finalPrice" data-sortable="false">Final Price</th>
										<th data-field="rLock" data-sortable="false"data-formatter="inputFormatter">Lock</th>  
										<th data-field="actionLock" data-align="center"></th>
										
									</tr>
								</thead>
							</table>
								<input type="hidden" id="jobRecDtPKId" name="jobRecDtPKId" />
						</div>
						
			



			
			
		
		
		
		<!-- //entry of jobRecingDt -->
  <div id="entryJobRecDtIdId" style="display: none">	
	<div id="jobRecDtRowId">	
		<div class="form-group">
			<form:form commandName="jobRecDt" id="jobRecDtEnt"
			cssClass="form-horizontal jobRecDtEntForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
				<tr  class="panel-heading">
					<th class="col-xs-2" >Item No</th>
					<th class="col-xs-2" >Client</th>
					<th class="col-xs-1" >GrossWt</th>
					<th class="col-xs-1" >Other</th>
					<th class="col-xs-1" >Display%</th>
					<th class="col-xs-5" ></th>
				</tr>
				</thead>
					<tbody>
					<tr>
						<td class="col-xs-2"><form:input path="itemNo"  cssClass="form-control" /></td>
						 <td class="col-xs-2">
							<form:select path="party.id" id="allPartyId" class="form-control">
								<form:option value="" label="- Select Party -" />
								<form:options items="${allPartyMap}" />
							</form:select>
						</td>
						<td class="col-xs-1"><form:input path="grossWt" id="jobRecDtGrossWt" cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="other"  cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="dispPercentDt"  cssClass="form-control" /></td>
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" /> 
						</td>
						<td class="col-xs-4"></td>
					</tr>
				</tbody>
			</table>
			
			</form:form>
		 </div>
	   </div>
  	</div>	
		
			
		
		<div class="row">
			<div class="col-sm-12">&nbsp;</div>
		</div>

		</div>



	<!-- //hide on page load start here -->
	<div id="hideOnPageLoad" style="display: none" >
	
	<!------------------------------------------ Job Rec Metal Dt  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="jobRecMetalTableId" data-toggle="table" 
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
								<th data-field="lossPerc" data-sortable="false" >Loss %</th>
								<th data-field="metalValue" data-sortable="false">Metal Value</th>
								<th data-field="mainMetal"  data-formatter="mianMetalFormatter">Main Metal</th>
							</tr>
						</thead>
					</table>
				</div>
			
			


		<!------------------------------------------ jobRecStnDt -------------------------------------->
		
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

<div id="toolbarStnDt">
						
								<a class="btn btn-info" type="button" onClick="javascript:addJobRecStnDt()" id="addJobRecStnBtnId"><span class="glyphicon glyphicon-plus"></span>&nbsp;	Add Stone</a>
							
							
									
						
							
						


					
						</div>
		
	
			
											
						
					<div >
						<table class="table-responsive"  id="jobRecStnDtTabId"
							data-toggle="table" data-toolbar="#toolbarStnDt"
							data-click-to-select="true"
							>
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="partNm" data-sortable="true">Part Name</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
									<th data-field="quality" data-sortable="false">      Quality</th>
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
						
						<input type="hidden" id="jobRecStoneDtPKId" name="jobRecStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
				
				
				
	 			<!-- entry for orderStnDt -->
	 			<div class="form-group">
					<div id="rowJobStoneDtId" style="display: none">
					</div>
				</div>
			
			

		
		


	
	
		<!------------------------------------------ jobRecCompDt -------------------------------------->
		
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
			<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostCompDt()" id="addJobRecCompBtnId">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Component</a>
						
						
	</div>
					<div >
						<table  id="jobRecCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							 data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="compName" data-sortable="true">     Component</th>
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
					
		
			
			
			
			<!----- entry form (jobRecCompDt) ------->
			  <div id="entryCostCompDt" style="display: none">
				<div id="jobRecCompRowId">
					<div class="form-group">
						<form:form commandName="jobRecCompDt" id="jobRecCompDtEnt"
							action="/jewels/manufacturing/transactions/jobRecCompDt/add.html"
							cssClass="form-horizontal jobRecCompDtEntForm">
							
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Component</th>
										<th>Purity</th>
										<th>Color</th>
										<th>MetalWt</th>  
										<th>Rate</th>
										<th>Qty</th>
										<th>Lock</th>
									</tr>
								</thead>
								  <tbody>
									<tr>
									    <td>
									    	<form:select path="component.id" class="form-control">
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</td>
										<td>
											<form:select path="purity.id" class="form-control">
												<form:option value="" label="- Select Purity -" />
												<form:options items="${purityMap}" />
											</form:select>
										</td>
										<td>
											<form:select path="color.id" class="form-control">
												<form:option value="" label="- Select Color -" />
												<form:options items="${colorMap}" />
											</form:select>
										</td>
										<td><form:input path="metalWt" id="compMetalWt" cssClass="form-control" /></td>
										<td><form:input path="compRate"  cssClass="form-control" /></td>
										<td><form:input path="compPcs"  cssClass="form-control" /></td>
										<td><form:checkbox path="rLock" id="compLock" /></td>
								
								   </tr>
								   	
								   	<tr>
								   		<td colspan="12">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<form:input type="hidden" path="id" id="jobRecCompDtPk" />
											<input type="hidden" id="forCompCostMtId" name="forCompCostMtId" />
											<input type="hidden" id="forCompCostDtId" name="forCompCostDtId" /> 
										</td>
								   	
								   	</tr>
								</tbody>
						    </table>
			
						</form:form>
					</div>
				</div>
			</div>
			
			
		
		
		
		
		
		
				
		<!-----------  jobRecLabDt ---------->
		
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
						<!-- <a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostLabDt()" id="addJobRecLabBtnId">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour
						</a> -->
						
						<!-- <span style="display:inline-block; width: 11cm;"></span>
							<input type="checkbox" id="chkLockCostLabDt" 
						onclick="javascript:lockAllCostLabDt();" /> <strong style="color: Coral;">LOCK </strong> -->
						
					</div>
				
					<div>
						<table id="jobRecLabDtTabId" data-toolbar="#toolbarLabDt"
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
					
				
			
			
		<!-- entry form (jobRecLabDt) -->
		
		<div id="entryJobRecLabDt" style="display: none">
			<div id="jobRecLabRowId">
			</div> 
		</div>
		
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		</div> <!-- ending the hide on page load -->	  
  </div>
  
  
  
  
<script type="text/javascript">

	
	$(document)
			.ready(
					function(e) {
						
						
					/* 	
						$(".jobRecMtForm")
								.validate(
										{

											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/jobRecMt/invoiceNoAvailable.html' />",
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
						 */
						
						
						
						
						//jobRecingDt
						
						
						$(".jobRecingDtEntForm")
							.validate(
							 {	
							  rules : {
									'party.id' : {
										required : true,
									},
									grossWt : {
										required : true,
										greaterThan : "0,0",
									},
									other : {
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
									},
								
									other : {
										greaterThan : "This field is required"
									}
								},	
					
							});


				
						
						//jobRecCompDtForm
						
						$(".jobRecCompDtEntForm")
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
						
						
						//jobRecLabDtForm
						
						$(".jobRecLabDtEntForm")
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
							var jobRec = abcx.substring(window.location.href.indexOf('edit') + 5);
							var tempCost = jobRec.split(".");
							$('#jobRecMtId').val(tempCost[0]);
							
							
							popJobRecDetails();
							
							$('#openOnEdit').css('display','block');
							$('#entryCostDtItemId').css('display','block');
							
							$('#vDeptId').val('${deptMap}')
							
						}else{
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}
						
						
						
						$("#searchCostingDtBags").on("keyup", function() {
						    var value = $(this).val();
						    
						    $("#jobRecDtTabId tr").each(function(index) {
						
						        if (index != 0) {
				
						            $row = $(this);
						            
						            var iTNoId = $row.find('td:eq(1)').text();  
						            var styNoId = $row.find('td:eq(5)').text();  
						            var bId = $row.find('td:eq(6)').text();
						            
						            if (iTNoId.toLowerCase().indexOf(value.toLowerCase()) != 0 && styNoId.toLowerCase().indexOf(value.toLowerCase()) != 0 && bId.toLowerCase().indexOf(value.toLowerCase()) !=0 ) {
						                $(this).hide();
						            }
						            else {
						                $(this).show();
						            }
						            
						        }
						    });
						    
						    
						    $('#jobRecDtTabId').bootstrapTable('resetView');
						});
						
						
						
							
						
						
						 $(function() {
							    $('#jobRecMetalTableId').bootstrapTable()
							  });
				

	});
	
	

/* 	function goToPickup() {
		
		$('#jobRechiddenMtId').val($('#jobRecMtId').val());
		$('#myJobIssModal').modal('show');
		getJobIssPickUplist();
	}

	function getJobIssPickUplist(){
		
		$("#jobIssMtPickupTblId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/jobIssMt/pickupListing.html?partyId="
							+ $('#party\\.id').val()
				});
	} */
	


	function popJobRecDetails() {

		$("#jobRecDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/jobRecDt/listing.html?pInvNo="
									+ $('#invNo').val()
						});
	

	}
	

	
	
	var jobRecDtTableRow;
	var jobRecDtLockStatus = 'null';
	$('#jobRecDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				jobRecDtTableRow = $element.attr('data-index');
				
				$('#entryJobIssDtId').css('display','none');
				$('#jobRecDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var defImage = jQuery.parseJSON(JSON.stringify(row)).image;
			    jobRecDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			
				
				if ((defImage != 'null') && ($.trim(defImage).length > 0)) {
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+defImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+defImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
					
				}
				
				$('#hideOnPageLoad').css('display','block');
				popJobRecMetalDetails();
				popJobRecStoneDetail();
				popJobRecComponentDetails();
				popJobRecLabDetails();
				
			})
			
			
			function grossWtFormatter(value, row, index){
	
var tempId = 'grossWtval' + Number(index);

	var vId = row.id;
    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onblur="javascript:disableGrossWt()" onchange="javascript:updateGrossWt(this,'+vId+')" disabled />';
}
	
	
	function disableGrossWt(){
		
		$('#grossWtval'+jobRecDtTableRow).attr('disabled', 'disabled');
	}
	
	
	function updateGrossWt(param, val){
		$.ajax({
			url : "/jewels/manufacturing/transactions/jobRecBagDt/updateGrossWt.html?id="+val+"&grossWt="+ param.value,
			type : 'GET',
			success : function(data) {
				
				if(data=="1"){
					popJobRecMetalDetails();
					updateJobRecDtTable($('#jobRecDtPKId').val());

				}else{
					
					displayMsg(this, null, data);
				}
				
													
					
			
			}

		});
	}
	
	
	
	
	function updateJobRecDtTable(dtId){
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/jobRecDt/getData/"+dtId+".html",
			type : 'GET',
			dataType : 'JSON',
			success : function(data) {
									
				$('#jobRecDtTabId').bootstrapTable('updateRow', {
					index : Number(jobRecDtTableRow),
					row : data
				});
				
				popJobRecDetails();
			}
		});
	}
			
			
			
					$('#jobRecDtTabId').bootstrapTable({}).on(
			'dbl-click-cell.bs.table',
			function(e, field, value, row, $element,index) {
				
				
				if(field==='grossWt'){
					
					 $('#grossWtval'+jobRecDtTableRow).removeAttr('disabled');
					 $('#grossWtval'+jobRecDtTableRow).focus();
					
/* 					if(jQuery.parseJSON(JSON.stringify(row)).grossWt>0){
						 displayMsg(this, null, ' Cannot Edit Record ');
					 }else{
						 $('#grossWtval'+jobRecDtTableRow).removeAttr('disabled');
						 $('#grossWtval'+jobRecDtTableRow).focus();
					 }
 */					
					
				}
				
				
			
				
				 
			
			
			})
			
		
	
	
	function editJobRecDt(jobRecDtId){
	
			$.ajax({
						url : "/jewels/manufacturing/transactions/jobRecDt/edit/"+ jobRecDtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
							
							$('#myJobRecDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#jobRecDtModalDivId  #'+k).val(v);
								
								if(k ==='processRecDtTextBox'){
									
									$('#vProcessNm').val(v);
									var tempRes = v.toString().replace(/,/g, "\n");
									$('#processRecDtTextBox').val(v);
								}
								
								
							});
							
							
						}
					});
		

	}
	
	
	
	
	
	//----------------------Metal--------//------------->

	
	function popJobRecMetalDetails(){
	
		
		$("#jobRecMetalTableId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/jobRecMetalDt/listing.html?jobRecDtId="
							+ $('#jobRecDtPKId').val()
				});

		
	}
	


	//----------------------stone--------//------------->

	
	function popJobRecStoneDetail(){
		
		
		$("#jobRecStnDtTabId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/jobRecStnDt/listing.html?jobRecDtId="
							+ $('#jobRecDtPKId').val()+"&disableFlg="+disableFlg
				});

		
	}
	
	
	
	
	
	$('#jobRecStnDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#jobRecStnDtTabId").bootstrapTable('getData'));

				
				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					
					
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
					
					
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});
	
	
	
		
		function popStoneRateFromMaster(){
			
			if(!!$('#setting\\.id').val() && !!$('#settingType\\.id').val()){
		
				  $.ajax({
					  url:"/jewels/manufacturing/transactions/jobRecStnDt/rateFromMaster.html?setId="+$('#setting\\.id').val()
							  +"&setTypeId="+$('#settingType\\.id').val()
							  +"&jobRecStnDtId="+$('#jobRecStoneDtPKId').val(),
					  type:'GET',
					  success : function(data) {
						 
						 if(data === '-1'){
							 $('#setRate').val('0.0');
						 }else{
							 $('#setRate').val(data);
						 }
						
					 }
					  
				  });
			
			}else{
				
				displayMsg(this, null, ' setting or setting type is not selected');
				
			}
			
		}
	
	
	
	
	
	//----------------------component--------//------------->
	

	
	function popJobRecComponentDetails(){
		
		$("#jobRecCompDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/jobRecCompDt/listing.html?jobRecDtId="
						+ $('#jobRecDtPKId').val()+"&disableFlg="+disableFlg
			});
		
	}
	
	
	
	//----------------------jobRecLabDt--------//------------->
	function popJobRecLabDetails(){
	$("#jobRecLabDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/jobRecLabDt/listing.html?jobRecDtId="
						+ $('#jobRecDtPKId').val()+"&disableFlg="+disableFlg
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
	

	
	function deleteJobRecDt(e,jobDtId){
		
		
		displayDlg(
				e,
				'javascript:deleteJobRecDtData('+jobDtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	
	
}



function deleteJobRecDtData(jobDtId){

	$("#modalDialog").modal("hide");
	
	$.ajax({
		
		url:'/jewels/manufacturing/transactions/jobRecDt/delete/'+jobDtId+'.html',
		data: 'GET',
		success : function(data){
			
			if(data === '1'){
			 
				popJobRecDetails()
				/* popJobRecMetalDetails()
				popJobIssStoneDetails()
				popJobIssComponentDetails()
				popJobIssLabDetails() */
				$('#hideOnPageLoad').css('display','none');
			}else{
				  displayMsg(this, null,data );	
				
				
			}
			
		}
		
	})
	
	
}

//----------------------stone--------//------------->



function addJobRecStnDt(){
	$("#rowJobStoneDtId").html('');
	
	$.ajax({
		
		url:"/jewels/manufacturing/transactions/jobRecStnDt/add.html?jobDtId="+$('#jobRecDtPKId').val(),
		type:"GET",
		success:function(data){
			
			
			$('#rowJobStoneDtId').css('display','block');
			$("#rowJobStoneDtId").html(data);
			
			 
			$('#stnPkId').val('');
			
		  	$('#jobRecStnDtEnt input[type="text"],hidden').val('');
		    $('#jobRecStnDtEnt input[type="number"]').val('0');
		    $('#jobRecStnDtEnt').find('select').val('');
		    $('#jobRecStnDtEnt').find('textarea').val('');
			
			
			$('#jobRecStnDtEnt #stoneType\\.id').focus();
			
		}
	})
	
}



function popStoneCancel(){
	 $('#rowJobStoneDtId').css('display','none');
}



$(document).on(
		'submit',
		'form#jobRecStnDtEnt',
		 function(e){
			
			$('#pJobRecMtId').val($('#jobRecMtId').val());
			$('#pJobRecDtId').val($('#jobRecDtPKId').val());
			$('#pSieve').val($('#vSieve').val());
			$('#pSizeGroup').val($('#vSizeGroupStr').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					if(data=="1"){
						popJobRecStoneDetail();
						popJobRecMetalDetails();
						
							
							updateJobRecDtTable($('#jobRecDtPKId').val());
							
							$('#stnPkId').val('');
							$('form#jobRecStnDtEnt select').val('');
							$('form#jobRecStnDtEnt select').val('').trigger('chosen:updated');
							$('#vSieve').val('');
							$('#vSizeGroupStr').val('');
							$('#stone').val('0');
							$('#carat').val('0.0');
							$('#stnRate').val('0.0');
							$('#handlingRate').val('0.0');
							$('#setRate').val('0.0');
						
						
					}else{
						displayMsg(this,null,data);	
					}
						
				
						
						if(data === '-2'){
							$("#rowJobStoneDtId").css('display' , 'none');	
						}
						
					
					
				},
				
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();
	
	});
	
	
	
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


$('#jobRecStnDtTabId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {

			 $('#jobRecStoneDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
			 	$('#stnPkId').val('');
			  	$('#jobRecStnDtEnt input[type="text"],hidden').val('');
			    $('#jobRecStnDtEnt input[type="number"]').val('0');
			    $('#jobRecStnDtEnt').find('select').val('');
			    $('#jobRecStnDtEnt').find('textarea').val('');
			    $('#rowJobStoneDtId').css('display','none');
			 
		});


function applyLabourCharge(labType){

	var data = $('#jobRecDtTabId').bootstrapTable('getAllSelections');
	var ids = $.map(data, function(item) {
		return item.id;
	});

	if(ids != '' ){

		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
	
	$.ajax({
		url : "/jewels/manufacturing/transactions/jobRecDt/applyHallmarkingRate.html?dtIds="+ ids+"&labType="+labType,
	
		type : "POST",
		success : function(data, textStatus, jqXHR) {

			 $.unblockUI();
			 
			if (Number(data) === 1) {
				displayInfoMsg(this,null,"Rate Apply Successfully");	
				popJobRecDetails();
				
			}else{
				displayMsg(this,null,data);	
			}			
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {

		}

	});
	
	}else{

		displayMsg(this,null,"Please select record");	
		}
}




function editJobRecLabDt(jrLabDtId){
	
	 if(jobRecDtLockStatus == 'true'){
		 displayMsg(this, null, 'Main Item Is Lock');
	 }else{
		 
		 $.ajax({
				url : "/jewels/manufacturing/transactions/jobRecLabDt/validationEdit.html?labDtId="+jrLabDtId,
				type : 'GET',
				success : function(data) {
					
					if(data === "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
	
					$.ajax({
						url : "/jewels/manufacturing/transactions/jobRecLabDt/edit/"+ jrLabDtId + ".html",
						type : 'GET',
						success : function(data) {
							$("#jobRecLabRowId").html(data);
							$('#entryJobRecLabDt').css('display','block');
							$('#metal\\.id').focus();
						}
					});
					
					}
				    
				}
			});
					
				}
				    
		
	
	 }


 

$(document)
.on(
	'submit',
	'form#jobRecLabDtEnt',
	 function(e){

		
		$('#forLabJobRecMtId').val($('#id').val());
		$('#forLabJobRecDtId').val($('#jobRecDtPKId').val());
		
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
				}
				else{
				
					$('#entryJobRecLabDt').css('display','none');
					popJobRecLabDetails();
					popJobRecDetails();
				//	updatePackDtTable($('#packDtPKId').val());
				
				/* $('form#jobRecLabDtEnt select').val('');
				$('#jobRecLabDtEnt #labourRate').val('0.0')
				
				$('#jobRecLabDtEnt #pcsWise').attr('checked', false); 
				$('#jobRecLabDtEnt #gramWise').attr('checked', false); 
				$('#jobRecLabDtEnt #percentWise').attr('checked', false); 
				$('#jobRecLabDtEnt #perCaratRate').attr('checked', false);
				$('#jobRecLabDtEnt #rLock').attr('checked', false);
				$('#jobRecLabDtEnt #packLabDtPk').val(''); */
				
				if(data === '-2'){
					$('#entryJobRecDtIdId').css('display','none');
				}
				
				}
			
			},
			
			error : function(data, textStatus, jqXHR){
				alert("ERROR");
			}
				
		})
		
		e.preventDefault();

	});		 



function deleteJobRecLabDt(e,jobRecLabDtId){
	
	 if(jobRecDtLockStatus == 'true'){
		 displayMsg(this, null, ' Main Item Is Lock');
	 }else{
		 
		 $.ajax({
			 url : "/jewels/manufacturing/transactions/jobRecLabDt/validationEdit.html?labDtId="+jobRecLabDtId,
				type : 'GET',
				success : function(data) {
					
					if(data === "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
						
						displayDlg(
								e,
								'javascript:deleteJobRecLabDtData('+jobRecLabDtId+');',
								'Delete',
								'Do you want to Delete  ?',
								'Continue');
											
						
					}
					
				}
		 })
	
	 }
	
}


function deleteJobRecLabDtData(jobRecLabDtId){
	
	$("#modalDialog").modal("hide");
	
	$.ajax({
		
		url:'/jewels/manufacturing/transactions/jobRecLabDt/delete/'+jobRecLabDtId+'.html',
		data: 'GET',
		success : function(data){
			$('#entryJobRecLabDt').css('display','none');
			popJobRecLabDetails();
			popJobRecDetails();
				
		}
		
	})
	
}


function applyGrading(labType){

	var data = $('#jobRecDtTabId').bootstrapTable('getAllSelections');
	var ids = $.map(data, function(item) {
		return item.id;
	});

	
	if(ids != '' ){

		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
	
	$.ajax({
		url : "/jewels/manufacturing/transactions/jobRecDt/applyGrading.html?dtIds="+ ids+"&labType="+labType,
	
		type : "POST",
		success : function(data, textStatus, jqXHR) {

			 $.unblockUI();
			if (Number(data) === 1) {
				displayInfoMsg(this,null,"Rate Apply Successfully");	
				popJobRecDetails();
				
			}else{
				displayMsg(this,null,data);	
			}			
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {

		}

	});
	
	}else{

		displayMsg(this,null,"Please select record");	
		}
	
}



function deleteJobRecStnDt(e,StnIdDt){
	displayDlg(
			e,
			'javascript:deleteJobRecStnData('+StnIdDt+');',
			'Delete',
			'Do you want to Delete  ?',
			'Continue');
}

function deleteJobRecStnData(StnIdDt){

	$("#modalDialog").modal("hide");

	 $.ajax({
			url : "/jewels/manufacturing/transactions/jobRecStnDt/delete/"+StnIdDt+".html",
			type : 'GET',
			success : function(data) {
				
				if(data === '1'){
					popJobRecStoneDetail();
					
				}else{
					displayMsg(this, null, data);
				
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


  