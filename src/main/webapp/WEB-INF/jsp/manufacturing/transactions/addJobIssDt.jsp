<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
		
<%@ include file="/WEB-INF/jsp/common/modalJobIssDt.jsp"%>
		
<%@ include file="/WEB-INF/jsp/common/modalJobProcess.jsp"%>


  
  <div class="panel-body">
  
  <div id="openOnEdit" style="display: none" >
		
	<div id="toolbarJobIssDt">
			
									<div id="addBagDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-info" type="button" id="jobissPickupBtn" onClick="goToNextPage()" ><span
											class="glyphicon glyphicon-plus"></span>&nbsp;Add Bags
										</a>
									</div>
									
														
							</div>
					
					
					
					
						<div class="table-responsive">
							<table  id="jobIssDtTabId"
								data-toggle="table" data-height="400" data-search="true"
								data-side-pagination="server" data-click-to-select="true"
								 data-pagination="true"	 >
								<thead>
									<tr>
										<th data-field="stateRd" data-radio="true"></th>  
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
										<th data-field="grossWt" data-sortable="false">Gross Wt</th>
										<th data-field="netWt" data-sortable="false">Net Wt</th>
										<th data-field="process" data-sortable="false">Process</th>
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
								<input type="hidden" id="jobIssDtPKId" name="jobIssDtPKId" />
						</div>
						
			



			
			
		
		
		
		<!-- //entry of jobIssingDt -->
  <div id="entryJobIssDtIdId" style="display: none">	
	<div id="jobIssingDtRowId">	
		<div class="form-group">
			<form:form commandName="jobIssDt" id="jobIssingDtEnt"
			cssClass="form-horizontal jobIssingDtEntForm">

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
						<td class="col-xs-1"><form:input path="grossWt" id="jobIssDtGrossWt" cssClass="form-control" /></td>
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
	
	<!------------------------------------------ JobIssMetalDt  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="jobIssMetalTableId" data-toggle="table" 
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
			
			


		<!------------------------------------------ jobIssStnDt -------------------------------------->
		
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
						<table class="table-responsive"  id="jobIssStnDtTabId"
							data-toggle="table" 
							data-click-to-select="true"
							>
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
								<!-- 	<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th> -->
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
						
						<input type="hidden" id="jobIssingStoneDtPKId" name="jobIssingStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
			
			

			
			<!-- entry for jobIssStnDt -->
	  <div id="entryCostStnDt" style="display: none">	
		<div id="jobIssStnDtRowId">	
		<div class="form-group">
			<form:form commandName="jobIssStnDt" id="jobIssStnDtEnt"
			cssClass="form-horizontal jobIssStnDtEntForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
				<tr  class="panel-heading">
					<th class="col-xs-2" >Setting</th>
					<th class="col-xs-2" >Setting Type</th>
					<th class="col-xs-1" >StoneRate</th>
					<th class="col-xs-1" >HandlingRate</th>
					<th class="col-xs-1" >SettingRate</th>
					<th class="col-xs-5" ></th>
				</tr>
				</thead>
					<tbody>
					<tr>
						 <td class="col-xs-2">
							<form:select path="setting.id" class="form-control" onchange="javascript:popStoneRateFromMaster()">
								<form:option value="" label="- Select Setting -" />
								<form:options items="${settingMap}" />
							</form:select>
						</td>
						<td class="col-xs-2">
							<form:select path="settingType.id" class="form-control" onchange="javascript:popStoneRateFromMaster()">
								<form:option value="" label="- Select SetType -" />
								<form:options items="${settingTypeMap}" />
							</form:select>
						</td>
						<td class="col-xs-1"><form:input path="stnRate"  cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="handlingRate"  cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="setRate"  cssClass="form-control" /></td>
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
			
		
		


	
	
		<!------------------------------------------ jobIssCompDt -------------------------------------->
		
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
			<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostCompDt()" id="addJobcompBtnId">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Component</a>
						
						
	</div>
					<div >
						<table  id="jobIssCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							 data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<!-- <th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th> -->
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
					
		
			
			
			
			<!----- entry form (jobIssCompDt) ------->
			  <div id="entryCostCompDt" style="display: none">
				<div id="jobIssCompRowId">
					<div class="form-group">
						<form:form commandName="jobIssCompDt" id="jobIssCompDtEnt"
							action="/jewels/manufacturing/transactions/jobIssCompDt/add.html"
							cssClass="form-horizontal jobIssCompDtEntForm">
							
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
											<form:input type="hidden" path="id" id="jobIssCompDtPk" />
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
			
			
		
		
		
		
		
		
				
		<!-----------  jobIssLabDt ---------->
		
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
						<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostLabDt()" id="addJobLabBtnId">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour
						</a>
						
						<!-- <span style="display:inline-block; width: 11cm;"></span>
							<input type="checkbox" id="chkLockCostLabDt" 
						onclick="javascript:lockAllCostLabDt();" /> <strong style="color: Coral;">LOCK </strong> -->
						
					</div>
				
					<div>
						<table id="jobIssLabDtTabId" data-toolbar="#toolbarLabDt"
							data-toggle="table" 
							data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<!-- <th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th> -->
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
					
				
			
			
		
		
		
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		</div> <!-- ending the hide on page load -->	
		
	</div>

<script type="text/javascript">

	
	$(document)
			.ready(
					function(e) {
						
						
						//jobIssingDt
						
						
						$(".jobIssingDtEntForm")
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


				
						
						//jobIssCompDtForm
						
						$(".jobIssCompDtEntForm")
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
						
						
						//jobIssLabDtForm
						
						$(".jobIssLabDtEntForm")
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
							var jobIss = abcx.substring(window.location.href.indexOf('edit') + 5);
							var tempCost = jobIss.split(".");
							$('#jobIssMtId').val(tempCost[0]);
							
						popJobIssDetails();
						
					//		$("#" + document.querySelector("#jobMtlIssDtt").id).attr("id","#jobMtlIssDtt");
							
							
							
						/* 	$("#" + document.querySelector("#disableJobStnIssDt").id).attr("id", "#jobStnIssDt");
							$("#" + document.querySelector("#disableJobCompIssDt").id).attr("id", "#jobCompIssDt");
						 */	
							$('#openOnEdit').css('display','block');
							$('#entryCostDtItemId').css('display','block');
							
							$('#vDeptId').val('${deptMap}')
							
						}else{
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}
						
						
						
						$("#searchCostingDtBags").on("keyup", function() {
						    var value = $(this).val();
						    
						    $("#jobIssDtTabId tr").each(function(index) {
						
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
						    
						    
						    $('#jobIssDtTabId').bootstrapTable('resetView');
						});
						
						
						
						
						 $(function() {
							    $('#jobIssMetalTableId').bootstrapTable()
							  });
				

	});
	
	
	

	function goToNextPage() {
		
		window.location.href = "/jewels/manufacturing/transactions/jobIssBagTransfer.html?MtId="+ $('#jobIssMtId').val()+"&deptId"+$('#department\\.id').val()
				+"&invDate="+$('#invDate').val();
	
	}

	

	

	function popJobIssDetails() {

		$("#jobIssDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/jobIssDt/listing.html?pInvNo="
									+ $('#invNo').val()
						});
	

	}
	

	
	
	var jobIssDtTableRow;
	var jobIssDtLockStatus = 'null';
	$('#jobIssDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				jobIssDtTableRow = $element.attr('data-index');
				
				$('#entryJobIssDtId').css('display','none');
				$('#jobIssDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var defImage = jQuery.parseJSON(JSON.stringify(row)).image;
			    jobIssDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			
				
				if ((defImage != 'null') && ($.trim(defImage).length > 0)) {
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+defImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+defImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
					
				}
				
				$('#hideOnPageLoad').css('display','block');
				popJobIssMetalDetails();
				popJobIssStoneDetails();
				popJobIssComponentDetails();
				popJobIssLabDetails();
				
			})
			
			
		
	
	
	function editJobIssDt(pkJobIssDtId){
		
					$.ajax({
						url : "/jewels/manufacturing/transactions/jobIssDt/edit/"+ pkJobIssDtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {

							$('#myJobIssDtModal').modal('show');
							$.each(data,function(k,v){
								$('#jobIssDtModalDivId  #'+k).val(v);
								if(k ==='processDtTextBox'){
									$('#vProcessNm').val(v);
									var tempRes = v.toString().replace(/,/g, "\n");
									$('#processDtTextBox').val(v);
								}
								
							});
						}
					});	
	}
	
	
	
	
	//----------------------Metal--------//------------->
	
	function popJobIssMetalDetails(){
		
		$("#jobIssMetalTableId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/jobIssMetalDt/listing.html?jobIssDtId="
							+ $('#jobIssDtPKId').val()
				});
		
	}


	//----------------------stone--------//------------->

	
	function popJobIssStoneDetails(){
		
		$("#jobIssStnDtTabId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/jobIssStnDt/listing.html?jobIssDtId="
							+ $('#jobIssDtPKId').val()
				});

		
	}
	
	
	
	$('#jobIssStnDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#jobIssStnDtTabId").bootstrapTable('getData'));

				
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
					  url:"/jewels/manufacturing/transactions/jobIssStnDt/rateFromMaster.html?setId="+$('#setting\\.id').val()
							  +"&setTypeId="+$('#settingType\\.id').val()
							  +"&jobIssStnDtId="+$('#jobIssingStoneDtPKId').val(),
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
	
	function popJobIssComponentDetails(){
		
		$("#jobIssCompDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/jobIssCompDt/listing.html?jobIssDtId="
						+ $('#jobIssDtPKId').val()
			});
		
	}
	
	
	
	//----------------------jobIssLabDt--------//------------->
	function popJobIssLabDetails(){
	$("#jobIssLabDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/jobIssLabDt/listing.html?jobIssDtId="
						+ $('#jobIssDtPKId').val()
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
		

	
		function deleteJobIssDt(e,jobDtId){
			
			
				displayDlg(
						e,
						'javascript:deleteJobIssDtData('+jobDtId+');',
						'Delete',
						'Do you want to Delete  ?',
						'Continue');
			
			
		}
		
		
		
		function deleteJobIssDtData(jobDtId){

			$("#modalDialog").modal("hide");
			
			$.ajax({
				
				url:'/jewels/manufacturing/transactions/jobIssDt/delete/'+jobDtId+'.html',
				data: 'GET',
				success : function(data){
					
					if(data === '1'){
					 
						popJobIssDetails()
						/* popJobIssMetalDetails()
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


