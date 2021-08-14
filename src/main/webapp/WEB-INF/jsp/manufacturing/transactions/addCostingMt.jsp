<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalCostDtRates.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalCostSummary.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>


  <div class="panel-body">
	
	 <c:if test="${success eq true}">
		<div class="col-xs-12">
			<div class="alert alert-success">Costing ${action} successfully!</div>
		</div>
	</c:if> 
	
		<div class="row">
										<div id="marqueeIdDisp" style="display: none">
											<marquee style="color: #00FF00; font-size: xx-large;" >This Export Is Closed</marquee>
										</div>
									</div>

	
		<div  class="form-group">
			<form:form commandName="costingMt" id="costingMtDivId"
				action="/jewels/manufacturing/transactions/costingMt/add.html?opt=0"
				cssClass="form-horizontal costingMtForm">
				
				<div class="col-sm-12">
				<div class="col-sm-10">
				
				<div style="font-size: smaller;font-weight: bold;" class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Inv
								No :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Inv
								Date :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Party
								:</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Tag
								% :</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Total Pcs  : </label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Total Fob  : </label>
						</div>
						
						<!-- <div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Gold
								Rate :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Silver
								Rate :</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Loss % :</label>
						</div> -->
					
						
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<form:input path="invNo" cssClass="form-control" />
							<form:errors path="invNo" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="invDate" cssClass="form-control" />
							<form:errors path="invDate" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:select path="party.id" class="form-control">
								<form:option value="" label=" Select Party " />
								<form:options items="${partyMap}" />
							</form:select>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="tagPercent" cssClass="form-control" />
							<form:errors path="tagPercent" />
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="totPcsxx" name="totPcsxx" Class="form-control" disabled="disabled"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="totFob" name="totFob" Class="form-control" disabled="disabled"/>
						</div>
						
						<%-- <div class="col-lg-2 col-sm-2">
							<form:input path="metalRate" cssClass="form-control" />
							<form:errors path="metalRate" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="silverRate" cssClass="form-control" />
							<form:errors path="silverRate" />
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="lossPercMt" cssClass="form-control" />
							<form:errors path="lossPercMt" />
						</div> --%>
						
						
					</div>
				</div>



				
				<div class="row">
				<div class="col-xs-12">			
				</div>
				
				</div>

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-2 col-sm-2">
								<label> <form:checkbox path="in999" /> <b>As Per 999</b></label>
							</div>
							
							<div class="col-lg-2 col-sm-2" style="font-size: smaller;font-weight: bold;">
							<a  onclick="javascript:popCostMetalRate()">Metal Rates</a>
							</div>
							
						</div>
					</div>
	
				
				</div>
				
				
				<div id="odImgDivId" class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
						<div class="panel panel-primary" style=" height:125px">
							<div class="panel-body">
								<div style="width: 150px; height: 50px" id="tempImgDivId">
									
								</div>
							</div>
						</div>
				</div>
				
				</div>
				

				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-5">
						<form:input type="hidden" path="id" />
						<form:input type="hidden" path="uniqueId" />
						<input type="hidden" id=costMtId name=costMtId />
						<input type="hidden" id="costStnDtPk" name="costStnDtPk" /> 
						
					</div>
				</div>

			</form:form>
			
			
			<input type="hidden" id="expCloseStatus" name="expCloseStatus" value="${closeStatus}">
		</div>


		

		<!----------------------costingDt------------------------ -->
		
	<div id="openOnEdit" style="display: none" >
		
	<div id="toolbarCostDt">
			
									<div id="addBagDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-info" type="button"  onClick="goToNextPage()" ><span
											class="glyphicon glyphicon-plus"></span>&nbsp;Add Bags
										</a>
									</div>
									
									
									<div  class="col-sm-4" style="display: block">
										<a class="btn btn-info"  type="button"  onClick="openCostSummary()" >Display Summary
										</a>
									</div>
									
									
									<div class="col-sm-3">
										<input type="checkbox" id="chkLockCostDt" 
											onclick="javascript:lockAllCostDt();" /> <strong style="color: Coral;">LOCK </strong>	
									</div>
									
								
									
									

								
								
							</div>
					
					
					
					
						<div class="table-responsive">
							<table  id="costDtTabId"
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
								<input type="hidden" id="costingDtPKId" name="costingDtPKId" />
						</div>
						
			



			
			
		
		
		
		<!-- //entry of costingDt -->
  <div id="entryCostDtId" style="display: none">	
	<div id="costingDtRowId">	
		<div class="form-group">
			<form:form commandName="costingDt" id="costingDtEnt"
			cssClass="form-horizontal costingDtEntForm">

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
						<td class="col-xs-1"><form:input path="grossWt" id="costDtGrossWt" cssClass="form-control" /></td>
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
		


	

		<!----------------------------------------- ApplyRate Button ------------------------------>
		
		
		<div class="row"> 
						<div class="col-sm-1">
							<input type="button" value="Apply Rate" class="btn btn-primary" style="color: lime;" onclick="javascript:applyCostRate()"/>
						</div>
						
						<!-- <div class="col-xs-8"></div>
							
							<div class="col-xs-1"><input type="button" id="app" value="Update Labour As Per Master" class="btn btn-primary" style="color: lime;" onclick="javascript:popLabAsPerMast()"></div>
							
							<div class="col-xs-2"></div> -->
		
		</div>		
		
		<div class="row">
			<div class="col-sm-12">&nbsp;</div>
		</div>

		</div>



	<!-- //hide on page load start here -->
	<div id="hideOnPageLoad" style="display: none" >
	
	<!------------------------------------------ CostMetal  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="costMetalTableId" data-toggle="table" 
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
			
			


		<!------------------------------------------ costStnDt -------------------------------------->
		
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
						<table class="table-responsive"  id="costStnDtTabId"
							data-toggle="table" 
							data-click-to-select="true"
							>
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
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
						
						<input type="hidden" id="costingStoneDtPKId" name="costingStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
			
			

			
			<!-- entry for costStnDt -->
	  <div id="entryCostStnDt" style="display: none">	
		<div id="costStnDtRowId">	
		<div class="form-group">
			<form:form commandName="costStnDt" id="costStnDtEnt"
			cssClass="form-horizontal costStnDtEntForm">

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
			
		
		


	
	
		<!------------------------------------------ costCompDt -------------------------------------->
		
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
			<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostCompDt()">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Component</a>
						
						
	</div>
					<div >
						<table  id="costCompDtTabId"
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
					
		
			
			
			
			<!----- entry form (costCompDt) ------->
			  <div id="entryCostCompDt" style="display: none">
				<div id="costCompRowId">
					<div class="form-group">
						<form:form commandName="costCompDt" id="costCompDtEnt"
							action="/jewels/manufacturing/transactions/costCompDt/add.html"
							cssClass="form-horizontal costCompDtEntForm">
							
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
											<form:input type="hidden" path="id" id="costCompDtPk" />
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
			
			
		
		
		
		
		
		
				
		<!-----------  costLabDt ---------->
		
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
						<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostLabDt()">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour
						</a>
						
						<!-- <span style="display:inline-block; width: 11cm;"></span>
							<input type="checkbox" id="chkLockCostLabDt" 
						onclick="javascript:lockAllCostLabDt();" /> <strong style="color: Coral;">LOCK </strong> -->
						
					</div>
				
					<div>
						<table id="costLabDtTabId" data-toolbar="#toolbarLabDt"
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
		
		<div id="entryCostLabDt" style="display: none">
			<div id="costLabRowId">
					<div class="form-group">
						<form:form commandName="costLabDt" id="costLabDtEnt"
							action="/jewels/manufacturing/transactions/costLabDt/add.html"
							cssClass="form-horizontal costLabDtEntForm">
							
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Metal</th>
										<th>Labour</th>
										<th>Rate</th>  
										<th>Per Pcs</th>
										<th>%Gram</th>
										<th>Percent</th>
										<th>Lock</th>
									</tr>
								</thead>
								  <tbody>
									<tr>
										<td>
									    	<form:select path="metal.id" class="form-control">
												<form:option value="" label="- Select Metal -" />
												<form:options items="${metalMap}" />
											</form:select>
										</td>
									    <td>
									    	<form:select path="labourType.id" class="form-control">
												<form:option value="" label="- Select LabourType -" />
												<form:options items="${labourTypeMap}" />
											</form:select>
										</td>
										<td><form:input path="labourRate"  cssClass="form-control" /></td>
										 <td><form:checkbox path="pcsWise" id="labPcsWise" /></td>
										<td><form:checkbox path="gramWise" id="labGramWise" /></td>
										<td><form:checkbox path="percentWise" id="labPercentWise" /></td> 
										<td><form:checkbox path="rLock" id="labLock" /></td>
								
								   </tr>
								   	
								   	<tr>
								   		<td colspan="12">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<form:input type="hidden" path="id" id="costLabDtPk" />
											<input type="hidden" id="forLabCostMtId" name="forLabCostMtId" />
											<input type="hidden" id="forLabCostDtId" name="forLabCostDtId" /> 
										</td>
								   	
								   	</tr>
								</tbody>
						    </table>
			
						</form:form>
					</div>
				</div>
			</div>
			
			
		
		
		
		
		
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
	<!-- 	<div class="col-xs-12">
			<div class="col-xs-5"></div>
			<div class="col-xs-1"><input type="button" id="updateBtn" value="Finding" class="btn btn-primary" onclick="javascript:updateFinding()"></div>
			<div class="col-xs-6"></div>
		</div> -->
		
		
		
		
		</div> <!-- ending the hide on page load -->	
		
	
	

	</div>  <!-- ending the panel body -->




<script type="text/javascript">

	
	$(document)
			.ready(
					function(e) {
						
						
						
						$(".costingMtForm")
								.validate(
										{

											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/costMt/invoiceNoAvailable.html' />",
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
						
						
						
						
						
						//costingDt
						
						
						$(".costingDtEntForm")
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


				
						
						//costCompDtForm
						
						$(".costCompDtEntForm")
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
						
						
						//costLabDtForm
						
						$(".costLabDtEntForm")
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
							var cost = abcx.substring(window.location.href.indexOf('edit') + 5);
							var tempCost = cost.split(".");
							$('#costMtId').val(tempCost[0]);
							
							
							
							
							
							var url = "/jewels/manufacturing/transactions/costing/reportExcel.html?mtId=" + $('#costMtId').val();
							$("#costingExcelId").attr("href",url)
							
							
							popCostingDetails();
							
							if($('#expCloseStatus').val() == 'false'){
								$("#" + document.querySelector("#disableChangeLabour").id).attr("id", "changeLabour");
							}
							
							$('#openOnEdit').css('display','block');
							$('#entryCostDtItemId').css('display','block');
							
							
							
						}else{
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}
						
						
						
						$("#searchCostingDtBags").on("keyup", function() {
						    var value = $(this).val();
						    
						    $("#costDtTabId tr").each(function(index) {
						
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
						    
						    
						    $('#costDtTabId').bootstrapTable('resetView');
						});
						
						
						
						
						if($('#expCloseStatus').val() == 'true'){
							
							$('#addBagDivId').css('display','none');
							$('#appRateId').prop('disabled', true);
							$('#app').prop('disabled', true);
							$('#costMtSubMitBtn').prop('disabled',true);
							$('#updateBtn').prop('disabled',true);
							$("#marqueeIdDisp").css('display', 'block');
							
						}
						
						
						
						
						 $(function() {
							    $('#costMetalTableId').bootstrapTable()
							  });
				

	});
	
	

	function goToNextPage() {
		
		window.location.href = "/jewels/manufacturing/transactions/transferCosting.html?MtId="+ $('#costMtId').val()+"&mOpt=0&partyId="+$('#party\\.id').val();
	
	}

	
	
	//-------------------costingDt-------------------->
	
	
	function setDtDisplayPerc(e){

		displayDlg(
				e,
				'javascript:confirmDispDtUpdate();',
				'Delete',
				'Do you want to Update Display % of CostingDt  ?',
				'Continue');

	}
	
	
	
	function confirmDispDtUpdate(){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/costingDt/updateDispPercDt.html?mtId="+$('#costMtId').val()
					+"&dispPerc="+$('#dispPercent').val(),
			type:"GET",
			success:function(data){
				if(data === '-1'){
					popCostingDetails();
				}
				
			}
		})	
		
	}
	

	function popCostingDetails() {

		$("#costDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/costingDt/listing.html?pInvNo="
									+ $('#invNo').val()
						});
		
		
		
		
		
		/* $.ajax({
			url:"/jewels/manufacturing/transactions/costingMt/summary.html?mtId="+$('#costMtId').val(),
			type :'GET',
			dataType:'JSON',
			success: function(data){
				
				if(data !== "-1"){
					$.each(data,function(k,v){
						
						if(k==='totPcsxx'){
							$('#totPcsxx').val(v);
							
						}else if(k==='totFobVal'){
							
							$('#totFob').val(v);
							
						}
						
						
					});
					
					
					
				}else{
					
				}
				
			}	
		}) */
		

	}
	

	
	
	var costDtTableRow;
	var costDtLockStatus = 'null';
	$('#costDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				costDtTableRow = $element.attr('data-index');
				
				$('#entryCostDtId').css('display','none');
				$('#costingDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var defImage = jQuery.parseJSON(JSON.stringify(row)).image;
			    costDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			
				
				if ((defImage != 'null') && ($.trim(defImage).length > 0)) {
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+defImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+defImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
					
				}
				
				$('#hideOnPageLoad').css('display','block');
				popCostingMetalDetails();
				popCostingStoneDetails();
				popCostingComponentDetails();
				popCostingLabDetails();
				
			})
			
			
		
	
	
	function editCostingDt(pkCostDtId){
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/costingDt/validation.html?dtId="+pkCostDtId,
			type : 'GET',
			success : function(data) {
				
				if(data === "-1"){
					displayMsg(this, null, 'Cannot edit,Record is Locked');	
				}else if(data === '-2'){
					displayMsg(this, null, 'Export is Closed');
				}else{
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/costingDt/edit/"+ pkCostDtId + ".html",
						type : 'GET',
						success : function(data) {
							 $("#costingDtRowId").html(data);
							 $("#entryCostDtId").css('display' , 'block');
							 $("#itemNo").focus();
						}
					});
					
				}
				
				
			}
		});

	}
	
	
	function saveCostingDt(){
		
		if(!!$('#itemNo').val() && !!$('#costDtGrossWt').val() && !!$('#other').val() && !!$('#allPartyId').val() && !!$('#dispPercentDt').val()){
		
			$.ajax({
				url : '<spring:url value='/manufacturing/transactions/costDt/saveEdit.html' />?costDtId='+
				 			$('#costingDtPKId').val()
				 			+"&grossWt=" + $('#costDtGrossWt').val()
				 			+"&otherWt=" + $('#other').val()
				 			+"&partyId=" + $('#allPartyId').val()
				 			+"&itemNo=" + $('#itemNo').val()
				 			+"&dispPercentDt=" + $('#dispPercentDt').val()
				 			+"&lossPercDt=" + $('#lossPercDt').val(),
				type : 'GET',
				success : function(data) {
					if(data === '-1'){
						popCostingDetails();
						$("#costDtGrossWt").val('');
						$('#other').val('');
						$('#itemNo').val('');
						 $('#lossPercDt').val('')
						$("#entryCostDtId").css('display' , 'none');
						$('#hideOnPageLoad').css('display','none');
					}else{
						displayMsg(this, null, 'Duplicate Item No ');
					}
	
				}
			});
			
		}else{
			displayMsg(this, null, 'All Fields Are Compulsary');
		}

	}

	
	function doCostDtLockUnLock(dtIdPk){
		
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/costingDt/lockUnlock.html?dtId="+dtIdPk,
			type : 'GET',
			success : function(data) {
				if(data == -1){
					popCostingDetails();
					popCostingStoneDetails();
					popCostingComponentDetails();
					popCostingLabDetails();
					$('#hideOnPageLoad').css('display','none');
				}else{
					displayMsg(this, null, 'Export Is Closed');
				}
			}
		});
	}
	
	
	
	function lockAllCostDt(){
		
		
		
		if($('#expCloseStatus').val() == 'false'){
			
			var costStatus = document.getElementById("chkLockCostDt").checked;
			var intStatus;
			if(costStatus == true){
				intStatus = 1;
			}else{
				intStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/transactions/costingDt/dtLockUnlockAll.html?mtId="+$('#costMtId').val()
						+"&status="+intStatus,
				type : 'GET',
				success : function(data) {
					if(data === '-1'){
						popCostingDetails();
					//	displayInfoMsg(this, null, 'Lock-Unlock Applied Succesfully');
						$('#hideOnPageLoad').css('display','none');
					}
				}
			}); 
			
		}else{
			displayMsg(this, null, 'Export is Closed');
			$('#chkLockCostDt').attr('checked', false);
		}
		
		
	}
	
	
	
	
/* 	$('#costDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#costDtTabId").bootstrapTable('getData'));

				var vTotPcs = 0.0;
				var vTotFob = 0.0;
				
				$.each(JSON.parse(data), function(idx, obj) {
					vTotPcs		+= Number(obj.pcs);
					vTotFob		+= Number(obj.fob);
				});
				
				$('#totPcs').val(" " + vTotPcs);
				$('#totFob').val(" " + vTotFob.toFixed(2));
			}); */
		
	
	
	
	function deleteCostingDt(e,costDtd){
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record Is Lock');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteCostDtData('+costDtd+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
		 }
		
	}
	
	
	
	function deleteCostDtData(costDtPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/costingDt/delete/'+costDtPk+'.html',
			data: 'GET',
			success : function(data){
				
				//alert(data);
				
				if(data === '-2'){
				   displayMsg(this, null, 'Export is Closed,Cannot Delete');	
				}else{
					popCostingDetails();
					popCostingStoneDetails();
					popCostingComponentDetails();
					popCostingLabDetails();
					$('#hideOnPageLoad').css('display','none');
				}
				
			}
			
		})
		
		
	}
	
	
	//----------------------Metal--------//------------->

	
	function popCostingMetalDetails(){
	
		
		$("#costMetalTableId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/costMetalDt/listing.html?costDtId="
							+ $('#costingDtPKId').val()
				});

		
	}
	


	//----------------------stone--------//------------->

	
	function popCostingStoneDetails(){
		
		
		$("#costStnDtTabId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/costStnDt/listing.html?costDtId="
							+ $('#costingDtPKId').val()
				});

		
	}
	
	
	var costStnLockStatus = 'null';
	$('#costStnDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				$('#costingStoneDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				costStnLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				 $("#entryCostStnDt").css('display' , 'none');
				 $('#costStnDtPk').val('');
				 $('form#costStnDtEnt select').val('');
				 $('#handlingRate').val('');
				 $('#setRate').val('');
				 
				 
			})
	
	
	
	function editCostStnDt(pkCostStnDtId){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
				$.ajax({
					url : "/jewels/manufacturing/transactions/costStnDt/validationEdit.html?stnId="+pkCostStnDtId,
					type : 'GET',
					success : function(data) {
						
						//alert(data);
						
						if(data === "-1"){
							displayMsg(this, null, 'Cannot edit,Record is Locked');	
						}else if(data === '-2'){
							displayMsg(this, null, 'Export is Closed');
						}else{
						
							$('#costStnDtPk').val(pkCostStnDtId);
							$.ajax({
								url : "/jewels/manufacturing/transactions/costStnDt/edit/"+ pkCostStnDtId + ".html",
								type : 'GET',
								success : function(data) {
									 $("#costStnDtRowId").html(data);
									 $("#entryCostStnDt").css('display' , 'block');
									 $("#setting\\.id").focus();
								}
							});
						}
						
					}
				});
				
		 }
		
	}
	
	
	
 	function saveCostStnDt(){
 		
 		 $
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/costStnDt/saveEdit.html' />?costStnDtId='+
				 			$('#costStnDtPk').val()
				 			+"&settingId=" + $('#setting\\.id').val()
				 			+"&setTypeId=" + $('#settingType\\.id').val()
				 			+"&stoneRate=" + $('#stnRate').val()
				 			+"&handRate=" + $('#handlingRate').val()
				 			+"&centerStone="+(document.getElementById('centerStoneId').checked ? "true" :"false")
				 			+"&setRate=" + $('#setRate').val(),
				type : 'GET',
				success : function(data) {
				
						$('#costStnDtPk').val('');
						
						$('form#costStnDtEnt select').val('');
						$('#handlingRate').val('');
						$('#setRate').val('');
						$("#entryCostStnDt").css('display' , 'none');
						popCostingStoneDetails();
						updateCostingDtTable($('#costingDtPKId').val());
						
						if(data === '-2'){
							$("#entryCostStnDt").css('display' , 'none');	
						}
					}

				
			});
		 
		
		 
		 
	 }
	
 
 
	 function doStoneDtLockUnLock(stnDtIdPk){
		 
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		 
			 $.ajax({
					url : "/jewels/manufacturing/transactions/costStnDt/lockUnlock.html?stnDtId="+stnDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data === '-1'){
							popCostingStoneDetails();
						}else if(data === '-2'){
							displayMsg(this, null, 'Export is Closed');
						}
					}
				});
			 
		 }
	 
	 }
 
	 
 
	function lockAllCostStnDt(){
		var costStnStatus = document.getElementById("chkLockCostStnDt").checked;
		var intStnStatus;
			if(costStnStatus == true){
				intStnStatus = 1;
			}else{
				intStnStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/transactions/costStnDt/stnLockUnlockAll.html?mtId="+$('#costMtId').val()
						+"&status="+intStnStatus,
				type : 'GET',
				success : function(data) {
					if(data == -1){
						popCostingStoneDetails();
					}
				}
			}); 
			 
		}
	
	
	
	function deleteCostStnDt(e,costStnDtId){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(costStnLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record is Lock');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteCostStnDtData('+costStnDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
			 }
			
		}
	
	
	
	function deleteCostStnDtData(costStnDtPk){ 
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/costStnDt/delete/'+costStnDtPk+'.html',
			data: 'GET',
			success : function(data){
						
				if(data === '-2'){
					displayMsg(this, null, 'Export is Closed');
				}else{
					
					popCostingStoneDetails();
					updateCostingDtTable($('#costingDtPKId').val());	
						
				}
					
		  }

			
		})
		
		
	}
	
	
	
	$('#costStnDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#costStnDtTabId").bootstrapTable('getData'));

				
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
					  url:"/jewels/manufacturing/transactions/costStnDt/rateFromMaster.html?setId="+$('#setting\\.id').val()
							  +"&setTypeId="+$('#settingType\\.id').val()
							  +"&costStnDtId="+$('#costingStoneDtPKId').val(),
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
	

	
	function popCostingComponentDetails(){
		
		$("#costCompDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costCompDt/listing.html?costDtId="
						+ $('#costingDtPKId').val()
			});
		
	}
	
	
	var costCompLockStatus = 'null';
	$('#costCompDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				$('form#costCompDtEnt select').val('');
				$('#compMetalWt').val('0.0');
				$('#compRate').val('0.0');
				$('#compLock').attr('checked', false); 
				$('#costCompDtPk').val('');
				$('#entryCostCompDt').css('display','none');
				costCompLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			})
	
	
	
	
	$(document)
		.on(
			'submit',
			'form#costCompDtEnt',
			 function(e){
				
				$('#forCompCostMtId').val($('#costMtId').val());
				$('#forCompCostDtId').val($('#costingDtPKId').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {
						
						popCostingComponentDetails();
						updateCostingDtTable($('#costingDtPKId').val());
						
						$('form#costCompDtEnt select').val('');
						$('#compMetalWt').val('0.0');
						$('#compRate').val('0.0');
						$('#compLock').attr('checked', false); 
						$('#costCompDtPk').val('');
						
						if(data === '-2'){
							$('#entryCostCompDt').css('display','none');
						}
					
					},
					
					error : function(data, textStatus, jqXHR){
						alert("ERROR");
					}
						
				})
				
				e.preventDefault();
		
	})
	
	
	function addCostCompDt(){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
				if($('#expCloseStatus').val() == 'false'){
					
					$('form#costCompDtEnt select').val('');
					$('#compMetalWt').val('0.0');
					$('#compRate').val('0.0');
					$('#compLock').attr('checked', false);
					$('#costCompDtPk').val('');
					$('#entryCostCompDt').css('display','block');
					$('#component\\.id').focus();
					
				}else{
					displayMsg(this, null, 'Export is Closed');
				}
		
		 }
		
	}
	
	
	function editCostCompDt(pkCompDtId){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/costCompDt/validationEdit.html?compId="+pkCompDtId,
				type : 'GET',
				success : function(data) {
					
					if(data === "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else if(data === "-2"){
						displayMsg(this, null, 'Export is Closed');
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/transactions/costCompDt/edit/"+ pkCompDtId + ".html",
							type : 'GET',
							success : function(data) {
								$("#costCompRowId").html(data);
								$('#entryCostCompDt').css('display','block');
								$('#component\\.id').focus();
							}
						});
					}
					    
				}
			});
		
		 }
	}
	
	
	function doCompDtLockUnLock(compDtIdPk){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			 $.ajax({
					url : "/jewels/manufacturing/transactions/costCompDt/lockUnlock.html?compDtId="+compDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data === '-2'){
							displayMsg(this, null, 'Export is Closed');
						}else{
							popCostingComponentDetails();
						}
					}
				});
		 }
	}
	
	
	function lockAllCostCompDt(){
		var costCompStatus = document.getElementById("chkLockCostCompDt").checked;
		var intCompStatus;
			if(costCompStatus == true){
				intCompStatus = 1;
			}else{
				intCompStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/transactions/costCompDt/LockUnlockAll.html?mtId="+$('#costMtId').val()
						+"&status="+intCompStatus,
				type : 'GET',
				success : function(data) {
					if(data == -1){
						popCostingComponentDetails();
					}
				}
			}); 
		
	}
	
	
	
	
	function deleteCostCompDt(e,costCompDtId){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(costCompLockStatus == 'Lock'){
			 displayMsg(this, null, ' Record is Locked Cannot Delete ! ');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteCostCompDtData('+costCompDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		 }
		
		
	}



	function deleteCostCompDtData(costCompDtPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/costCompDt/delete/'+costCompDtPk+'.html',
			data: 'GET',
			success : function(data){
				
				if(data === '-2'){
					displayMsg(this, null, 'Export is Closed');
				}else{
									
					popCostingComponentDetails();
					updateCostingDtTable($('#costingDtPKId').val());
						
				}
				
				
			}
			
		})
		
	}
		
	
	
	//----------------------costLabDt--------//------------->
	function popCostingLabDetails(){
	$("#costLabDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costLabDt/listing.html?costDtId="
						+ $('#costingDtPKId').val()
			});
		
	}
	
	
	var costLabLockStatus = 'null';
	$('#costLabDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				$('form#costLabDtEnt select').val('');
				$('#labourRate').val(0.0);
				$('#labLock').attr('checked', false);
				$('#pcsWise').attr('checked', false);
				$('#costLabDtPk').val('');
				$('#entryCostLabDt').css('display','none');
				costLabLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			})
	
	
			
	$(document).
		on('submit',
			'form#costLabDtEnt',
			function(e){
			
			
			$('#forLabCostMtId').val($('#costMtId').val());
			$('#forLabCostDtId').val($('#costingDtPKId').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					
					
					if(data === "-11"){
						displayMsg(this, null, 'please select only one check box');	
					}else if(data === "-12"){
						displayMsg(this, null, 'please check the entry');	
					}else{
						
						popCostingLabDetails();
						updateCostingDtTable($('#costingDtPKId').val());
						
						$('#costLabDtEnt input[type="text"],textarea,hidden').val('');
					    $('#costLabDtEnt input[type="number"]').val('0');
					    $('#costLabDtEnt').find('select').val('');
					    $('#costLabDtPk').val('');
					    $('#costLabDtEnt input[type="checkbox"]').prop("checked", false);
						
						if(data === '-2'){
							$('#entryCostLabDt').css('display','none');
						}
						
					}
					
				},
				error : function(data, textStatus, jqXHR){
					
				}
				
			})
			
			e.preventDefault();
			
		})
		
		
	function addCostLabDt(){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
				if($('#expCloseStatus').val() == 'false'){
					
					$('form#costLabDtEnt select').val('');
					$('#labourRate').val(0.0);
					$('#labLock').attr('checked', false);
					$('#pcsWise').attr('checked', false);
					$('#costLabDtPk').val('');
					$('#entryCostLabDt').css('display','block');
					$('#labourType\\.id').focus();
					
				}else{
					displayMsg(this, null, 'Export is Closed');
				}
				
		 }
		
		
	}	
		
		
	function editCostLabDt(pkLabDt){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/costLabDt/validationEdit.html?labId="+pkLabDt,
				type : 'GET',
				success : function(data) {
					if(data === "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else if(data === "-2"){
						displayMsg(this, null, 'Export is Closed');
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/transactions/costLabDt/edit/"+ pkLabDt + ".html",
							type : 'GET',
							success : function(data) {
								$("#costLabRowId").html(data); 
								$('#entryCostLabDt').css('display','block');
								$('#labourType\\.id').focus();
							}
						});
						
					}
					
					
					
				}
			});
		
		 }
		
	}
	
	
	function doLabDtLockUnLock(labDtIdPk){
		
		 if(costDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			 $.ajax({
					url : "/jewels/manufacturing/transactions/costLabDt/lockUnlock.html?labDtId="+labDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data === '-1'){
							popCostingLabDetails();
						}else if(data === '-2'){
							displayMsg(this, null, 'Export is Closed');
						}
					}
				});
		 }
	}
	
	
	function lockAllCostLabDt(){
		var costLabStatus = document.getElementById("chkLockCostLabDt").checked;
		var intLabStatus;
			if(costLabStatus == true){
				intLabStatus = 1;
			}else{
				intLabStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/transactions/costLabDt/LockUnlockAll.html?mtId="+$('#costMtId').val()
						+"&status="+intLabStatus,
				type : 'GET',
				success : function(data) {
					if(data == -1){
						popCostingLabDetails();
					}
				}
			}); 
		
	}
	
	
	
	
	function deleteCostLabDt(e,costLabDtId){
		
		
		
		
		 if(costDtLockStatus === 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(costLabLockStatus === 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record is Locked');
		 }else{
			
			displayDlg(
					e,
					'javascript:deleteCostLabDtData('+costLabDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		  }
			
	  }
	
	
	
		function deleteCostLabDtData(costLabDtPk){
			
			$("#modalDialog").modal("hide");
			
			$.ajax({
				url:'/jewels/manufacturing/transactions/costLabDt/delete/'+costLabDtPk+'.html',
				data: 'GET',
				success : function(data){
					
					if(data === '-2'){
						displayMsg(this, null, 'Export is Closed');
					}else{
						
						popCostingLabDetails();
						updateCostingDtTable($('#costingDtPKId').val());
							
							
					}
					
					
				}
				
			})
			
		}
		
		

	
		
		// --------------tab-------------------------//------------->>>>>
		
		
		function popInwardType(){
			$("#changeInwardId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/changeInwardType/listing.html"
					});
		}
		
		
		
		function popLabourType(){
			$("#labTypeId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/changeLabourType/listing.html"
					});
		}

		
	
		
		
		
		
		//---------------------- APPLY RATE --------//----------------->
		
		
		
	 
	 function applyCostRate(){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		 
		 var postData = $('#costingMtDivId').serializeArray();
		 
		 $.ajax({
				url : "/jewels/manufacturing/transactions/applyRate/transfer.html?tempPartyId="+$('#party\\.id').val(),
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					
					if(data === '1'){
						window.location.reload(true);
					}else{
						displayMsg(this,null,"Error Can Not Apply Rate");	
					}
					
					popCostingLabDetails();
						
					$.unblockUI();

				},
				error : function(data, textStatus, jqXHR){
					$.unblockUI();

				}
				
			})
		 
		 
	 }
		
		
		
		
		
		
		
		
		
/* 		
		$(document).
			on('submit',
				'form#applyRateCosting',
				function(e){
				
				
				
				
				$("#costMtIdPkAR").val($('#costMtId').val());
				$("#costGldRate").val($("#metalRate").val());
				$("#costSlvRate").val($("#silverRate").val());
				$("#costAlloyRate").val($("#alloyRate").val());
				$("#costAddPerc").val($("#addPercent").val());
				$("#costLossPercMt").val($("#lossPercMt").val());
				$("#costTagPerc").val($("#tagPercent").val());
				$("#costDisPerc").val($("#dispPercent").val());
				
			
				
				
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
					
				 $.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR){
						displayConfirmation(e,'javascript:pageRelod();','Notification','Rate Applied Succesfully!, -Press Continue-','Continue');
					
					},
					error : function(data, textStatus, jqXHR){
						
					}
					
				})
				
				e.preventDefault(); 
				
			}) */
		
		
	
			
		function pageRelod(){
			window.location.reload(true);
		}
			
			
	//---------------------- UPDATE FOB --------//----------------->
		
			
		/* function popFob(){
			
			 $.ajax({
					url : "/jewels/manufacturing/transactions/update/fob.html?mtId="+$('#costMtId').val()
							+"&goldRate="+$("#metalRate").val()
							+"&slvRate="+$("#silverRate").val()
							+"&addPer="+$("#addPercent").val()
							+"&tagPer="+$("#tagPercent").val()
							+"&dispPer="+$("#dispPercent").val(),
					type : 'GET',
					success : function(data) {
						
						
						
					}
				}); 
			
			
		} */
		
	
		
		function updateFinding(){
			
			 if(costDtLockStatus == 'Lock'){
				 displayMsg(this, null, ' Main Item Is Lock');
			 }else{
				window.location.href = "/jewels/manufacturing/transactions/costCompFinding.html?dtId="+$('#costingDtPKId').val()+"&mtId="+$('#costMtId').val();
			 }
		}
		
		
		function costingSave(){
			$("form#costingMtDivId").submit();
		}
		
		
		
		//--------//----Labour As Per Master---------//
		
		function popLabAsPerMast(){
			
			$.ajax({
				
				url:"/jewels/manufacturing/transactions/labourAsPerMaster/save.html?mtId="+$('#costMtId').val(),
				type : 'GET',
				success : function(data,e) {
		
						disp(event);
					
				}
			
			});
			
		}
		
		
		function disp(e){
			 displayConfirmation(e,'javascript:pageRelod();','Notification','Rate Applied Succesfully As Per Master ! ','Continue');
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
		

		//----------------------------------------------//
		
		function popCostMetalRate(){
			$('#myCostRateModal').modal('show');
			popCostMetalRateTbl();
			
			setTimeout(function(){
				$('#costMetalRateIdTbl').bootstrapTable('resetView');
			}, 300);
			
		}
		
		
		function updateCostingDtTable(dtId){
			
			
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/costingDt/getData/"+dtId+".html",
				type : 'GET',
				dataType : 'JSON',
				success : function(data) {
										
					$('#costDtTabId').bootstrapTable('updateRow', {
						index : Number(costDtTableRow),
						row : data
					});
					
					
				}
			});
		}
		
		
		function openCostSummary(){
			
			$.ajax({
				url:"/jewels/manufacturing/transactions/costingMt/summary.html?mtId="+$('#costMtId').val(),
				type :'GET',
				dataType:'JSON',
				success: function(data){
					
					if(data !== "-1"){
						
						$('#myCostSumModal').modal('show');
						
						$.each(data,function(k,v){
							
							
							$('#costSumDivId  #'+k).val(v);
						});
						
						
						
					}else{
						displayMsg(this,null,'Error Contact Admin');
					}
					
				}	
			})
		}
		
		
		
		var tempMetalWtSrNo = 0;
			function lossPercFormatter(value, row, index){
				
				var tempId = 'lossPercval' + Number(index);
		
				var vId = row.id;
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateLossPerc(this,'+vId+')"  />';
			}	
							
				function updateLossPerc(param, val) {
					var tempId = param.id.substring(11);
			
					$('#lossPercval' + tempId).val(param.value);
			
					$('#costMetalTableId').bootstrapTable('updateByUniqueId', {
						id : val,
						row : {
							lossPerc : param.value
						}
					});
			
					updateTotLossPerc(val, param.value);
					
			
				}
			
				function updateTotLossPerc(id, vLossPerc) {
			
					$
							.ajax({

					url : "/jewels/manufacturing/transactions/costingMt/updateLossPerc.html?id="
							+ id + "&lossPerc=" + vLossPerc,
					type : 'GET',
					success : function(data) {
						popCostingMetalDetails();
						updateCostingDtTable($('#costingDtPKId').val());
						
					}

				});
	}
				
				
				function downloadCostingExcel(){
					
					$.ajax({
					 url:"/jewels/manufacturing/transactions/costing/reportExcel.html",
					 type :'POST',
					 data :{
						 mtId :$('#costMtId').val()
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



