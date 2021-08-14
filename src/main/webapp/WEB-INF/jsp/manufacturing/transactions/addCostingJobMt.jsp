<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

	
	<div class="panel-body">
	
	
	 <c:if test="${success eq true}">
		<div class="col-xs-12">
			<div class="alert alert-success">Job Costing  ${action} successfully!</div>
		</div>
	 </c:if> 
	
	
	
		<div  class="form-group">
			<form:form commandName="costingJobMt" 
				action="/jewels/manufacturing/transactions/costingJobMt/add.html"
				cssClass="form-horizontal costingJobMtForm">
				
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
							<label for="inputLabel3" class=".col-lg-2 text-right">Gold
								Rate :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Silver
								Rate :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Add
								% :</label>
						</div>
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
							<form:input path="metalRate" cssClass="form-control" />
							<form:errors path="metalRate" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="silverRate" cssClass="form-control" />
							<form:errors path="silverRate" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="addPercent" cssClass="form-control" />
							<form:errors path="addPercent" />
						</div>
					</div>
				</div>


				<div class="row" style="font-size: smaller;font-weight: bold;">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Tag
								% :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Display
								% :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">TagColor  : </label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Total Pcs  : </label>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="tagPercent" cssClass="form-control" />
							<form:errors path="tagPercent" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="dispPercent" cssClass="form-control" />
							<form:errors path="dispPercent" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:select path="tagColor"  class="form-control">
								<form:option value="" label=" Select TagColor " />
								<form:options items="${tagColorMap}" />
							</form:select>
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="totPcs" name="totPcs" Class="form-control" disabled="true"/>
						</div>
						
					
					
					<div class="col-lg-2 col-sm-2"></div>
					
					
					
					<div id="odImgDivId" class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
						<div class="panel panel-primary" style=" height:125px">
							<div class="panel-body">
								<div style="width: 150px; height: 50px" id="tempImgDivId">
									
								</div>
							</div>
							
							
						</div>
					</div>
						
						
					</div>
				</div>
				

				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-5">
						<form:input type="hidden" path="id" />
						<form:input type="hidden" path="uniqueId" />
						<input type="hidden" id=costJobMtId name=costJobMtId /> 
						<input type="hidden" id="costJobStnDtPk" name="costJobStnDtPk" /> 
					</div>
				</div>

			</form:form>
			
			
		</div>
	
	
	
	<!----------------------costingJobDt------------------------ -->
		
	<div id="openOnEdit" style="display: none" >
		
		<div class="form-group">
			<div class="container-fluid">
				<div class="row" style="font-size: smaller;font-weight: bold;">
					
					
				<div id="cdDivId" class="col-xs-12">
					<div class="row" >
						<div id="toolbarDt">
			
								<div class="col-xs-12">
									
									<div class="col-xs-1" style="display: block">
										<a class="btn btn-info" style="font-size: 15px;" type="button"  onClick="javascript:dumpCostingJobDetails()" ><span
											class="glyphicon glyphicon-plus"></span>&nbsp;Add
										</a>
									</div>
									
									<div class="col-xs-2"><span style="display:inline-block; width:51cm; color: #FF0066;font-size:large;font-weight: normal;">&nbsp;JOB COSTING DETAILS</span></div>
									
									<div class="col-xs-2">
										<input type="search" id="searchCostingJobDtBags"  placeholder="Search" class="form-control" style="width: 4cm "/>
									</div>
									
									<div class="col-xs-1"></div>
									<div class="col-xs-1"></div>
									
									<div class="col-xs-2">
										<input type="checkbox" id="chkLockCostJobDt" 
											onclick="javascript:lockAllCostJobDt();" /> <strong style="color: Coral;">LOCK </strong>	
									</div>
									
									

								</div> 
								
							</div>
						</div>
					
					
					
						<div>
							<table  class="table-responsive" id="costJobDtTabId"
								data-toggle="table" data-toolbar="#toolbarDt"
								data-side-pagination="server" data-click-to-select="true"
								data-select-item-name="radioNameCostJobDt"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
								<thead>
									<tr class="btn-primary">
										<th data-field="stateRd" data-radio="true"></th>  
										<th data-field="itemNo" data-sortable="true">ItemNo</th>
										<th data-field="party" data-sortable="false">Client</th>
										<th data-field="ordNo" data-sortable="false">OrderNo</th>
										<th data-field="ordRefNo" data-sortable="false">RefNo</th>
										<th data-field="style" data-sortable="false" class="span5">Style</th>
										<th data-field="bag" data-sortable="false" class="span5">Bag</th>
										<th data-field="purity" data-sortable="false">Kt</th>
										<th data-field="color" data-sortable="false">Color</th>
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false">GrosWt</th>
										<th data-field="netWt" data-sortable="false">NetWt</th>
										<th data-field="metalRate" data-sortable="false">MetRate</th>
										<th data-field="metalValue" data-sortable="false">MetValue</th>
										<th data-field="stoneValue" data-sortable="false">StnValue</th>
										<th data-field="compValue" data-sortable="false">CompValue</th>
										<th data-field="labourValue" data-sortable="false">LabValue</th>
										<th data-field="setValue" data-sortable="false">SetValue</th>
										<th data-field="handlingCost" data-sortable="false">HdlgCost</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="other" data-sortable="false">Other</th>
										<th data-field="finalPrice" data-sortable="false">FinalPrice</th>
										<th data-field="rLock" data-sortable="false">Status</th>  
										<th data-field="actionLock" data-align="center">LockUnLock</th>
										<th data-field="action1" data-align="center">Edit</th>
										<th data-field="action2" data-align="center">Delete</th>
									</tr>
								</thead>
							</table>
								<input type="hidden" id="costingJobDtPKId" name="costingJobDtPKId" />
						</div>
						
					</div>



				</div>
			</div>
		</div>
		
		
		
		
	<!-- //entry of costingJobDt -->
	
  <div id="entryCostJobDtId" style="display: none">	
	<div id="costingJobDtRowId">	
		<div class="form-group">
			<form:form commandName="costingJobDt" id="costingJobDtEnt"
			cssClass="form-horizontal costingJobDtForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
				<tr  class="panel-heading">
					<th class="col-xs-1" >GrossWt</th>
					<th class="col-xs-1" >Other</th>
					<th class="col-xs-10" ></th>
				</tr>
				</thead>
					<tbody>
					<tr>
						<td class="col-xs-1"><form:input path="grossWt" id="costJobDtGrossWt" cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="other"  cssClass="form-control" /></td>
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" /> 
						</td>
						<td class="col-xs-10"></td>
					</tr>
				</tbody>
			</table>
			
			</form:form>
		 </div>
	   </div>
  	</div>	
		


	

		<!----------------------------------------- ApplyRate Button ------------------------------>
		
		
			<div class="row"  style="font-size: smaller;font-weight:normal;">
				<div class="form-group">
					<div class="col-xs-12" >
					
						  <div class="col-xs-1">
						   
							 <form:form commandName="costingJobMt" id="applyRateCosting"
								action="/jewels/manufacturing/transactions/applyRate/costJob/transfer.html"
								cssClass="form-horizontal transferApplyRate">
								<table class="table">
									<tbody>
										<tr>
											<td class="col-xs-1" >
												<input type="submit" value="Apply Rate" class="btn btn-primary" style="color: lime;" id="appRateId"/> 
												<input type="hidden" id="costJobMtIdPkAR" name="costJobMtIdPkAR" />
												<input type="hidden" id="costGldRate" name="costGldRate" />
												<input type="hidden" id="costSlvRate" name="costSlvRate" />
												<input type="hidden" id="costAddPerc" name="costAddPerc" />
												<input type="hidden" id="costTagPerc" name="costTagPerc" />
												<input type="hidden" id="costDisPerc" name="costDisPerc" />
											</td>
										</tr>
									</tbody>
								</table>
							 </form:form>
							
							
						 </div>
							
						<div class="col-xs-8"></div>
						
						<div class="col-xs-1"><input type="button" id="app" value="Update Labour As Per Master" class="btn btn-primary" style="color: lime;" onclick="javascript:popLabAsPerMast()"></div>
						
						<div class="col-xs-2"></div>
							
					</div>
					
					 
					
				</div>
			</div>
			
			

		</div>



	<!-- //hide on page load start here -->
	<div id="hideOnPageLoad" style="display: none" >


		<!------------------------------------------ costJobStnDt -------------------------------------->

		
	<div id="csDivId" class="col-xs-12">
		<div class="container-fluid">
			<div class="row" style="font-size: smaller;font-weight: bold;">
						
						
					<div >
						<table class="table-responsive" id="costJobStnDtTabId"
							data-toggle="table" data-toolbar="#toolbarStnDt"
							data-side-pagination="server" data-click-to-select="true"
							data-select-item-name="radioNameCostStnDt"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
									<th data-field="quality" data-sortable="false">.      Quality</th>
									<th data-field="size" data-sortable="false">Size</th>
									<th data-field="sizeGroup" data-sortable="false">SizeGroup</th>
									<th data-field="stone" data-sortable="false">Stone</th>
									<th data-field="carat" data-sortable="false">Carat</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="stoneValue" data-sortable="false">StnValue</th>
									<th data-field="handlingRate" data-sortable="false">HdlgRate</th>
									<th data-field="handlingValue" data-sortable="false">HdlgValue</th>
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SetType</th>
									<th data-field="settingRate" data-sortable="false">SetRate</th>
									<th data-field="settingValue" data-sortable="false">SetValue</th>
									<th data-field="rLock" data-sortable="false">Lock</th>
									<th data-field="actionLock" data-align="center" >LockUnlock</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
						<input type="hidden" id="costingJobStoneDtPKId" name="costingJobStoneDtPKId" />
				</div>
			</div>
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="true" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="true" />		
				</div>
			
			

			
	  <!-- entry for costJobStnDt -->
			
	  <div id="entryCostJobStnDt" style="display: none">	
		<div id="costJobStnDtRowId">	
		<div class="form-group">
			<form:form commandName="costJobStnDt" id="costJobStnDtEnt"
			cssClass="form-horizontal costJobStnDtForm">

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
			
		</div>
		
		


	
	
		<!------------------------------------------ costJobCompDt -------------------------------------->
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-lg-6 col-sm-6">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Component Details</span>
				</div>
				
				<div class="col-lg-6 col-sm-6">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Labour Details</span>
				</div>
				
			</div>
		</div>
		
	
	<div id="comDivIdf" class="col-xs-6">
		
		<div class="container-fluid">
			<div class="row" style="font-size: smaller;font-weight: bold;">
			
					<div id="toolbarCompDt">
						<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostJobCompDt()">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Component</a>
						
						
					</div>
					<div >
						<table class="table-responsive" id="costJobCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							data-side-pagination="server" data-click-to-select="true"
							data-select-item-name="radioNameCostJobCompDt"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="compName" data-sortable="true">.     Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metalWt</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="rLock" data-sortable="false">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
					
				</div>
			</div>
			
			
			<!----- entry form (costJobCompDt) ------->
			
			  <div id="entryCostJobCompDt" style="display: none">
				<div id="costJobCompRowId">
					<div class="form-group">
						<form:form commandName="costJobCompDt" id="costJobCompDtEnt"
							action="/jewels/manufacturing/transactions/costJobCompDt/add.html"
							cssClass="form-horizontal costJobCompDtEntForm">
							
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
										<td><form:checkbox path="rLock" id="compJobLock" /></td>
								
								   </tr>
								   	
								   	<tr>
								   		<td colspan="12">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<form:input type="hidden" path="id" id="costJobCompDtPk" />
											<input type="hidden" id="forCompCostJobMtId" name="forCompCostJobMtId" />
											<input type="hidden" id="forCompCostJobDtId" name="forCompCostJobDtId" /> 
										</td>
								   	
								   	</tr>
								</tbody>
						    </table>
			
						</form:form>
					</div>
				</div>
			</div>
			
			
		</div>
				
		<!-----------  costJobLabDt ---------->
			 
		<div id="comDivIds" class="col-xs-6">	
			
			<div class="container-fluid">
				<div class="row" style="font-size: smaller;font-weight: bold;">
				
					<div id="toolbarLabDt">
						<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostJobLabDt()">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour
						</a>
						
						<!-- <span style="display:inline-block; width: 11cm;"></span>
							<input type="checkbox" id="chkLockCostLabDt" 
						onclick="javascript:lockAllCostLabDt();" /> <strong style="color: Coral;">LOCK </strong> -->
						
					</div>
				
					<div >
						<table class="table-responsive" id="costJobLabDtTabId"
							data-toggle="table" data-toolbar="#toolbarLabDt"
							data-side-pagination="server" data-click-to-select="true"
							data-select-item-name="radioNameCostJobLabDt"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="labourType" data-sortable="true">LabourType</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="rLock" data-sortable="false">Lock</th>
									<th data-field="actionLock" data-align="center">Edit</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
					
				</div>
			</div>
			
		<!-- entry form (costLabDt) -->
		
		<div id="entryCostJobLabDt" style="display: none">
			<div id="costJobLabRowId">
					<div class="form-group">
						<form:form commandName="costJobLabDt" id="costJobLabDtEnt"
							action="/jewels/manufacturing/transactions/costJobLabDt/add.html"
							cssClass="form-horizontal costJobLabDtEntForm">
							
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Labour</th>
										<th>Rate</th>  
										<th>PerPcs</th>
										<th>%Gram</th>
										<th>Percent</th>
										<th>Lock</th>
									</tr>
								</thead>
								  <tbody>
									<tr>
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
										<td><form:checkbox path="rLock" id="labJobLock" /></td>
								
								   </tr>
								   	
								   	<tr>
								   		<td colspan="12">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<form:input type="hidden" path="id" id="costJobLabDtPk" />
											<input type="hidden" id="forLabCostJobMtId" name="forLabCostJobMtId" />
											<input type="hidden" id="forLabCostJobDtId" name="forLabCostJobDtId" /> 
										</td>
								   	
								   	</tr>
								</tbody>
						    </table>
			
						</form:form>
					</div>
				</div>
			</div>
			
			
		</div>
		
		
		
		
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="col-xs-12">
			<div class="col-xs-5"></div>
			<div class="col-xs-1"><input type="button" id="updateBtn" value="Finding" class="btn btn-primary" onclick="javascript:updateJobFinding()"></div>
			<div class="col-xs-6"></div>
		</div>
		
		
		
		
		</div> <!-- ending the hide on page load -->	
	
	
	
	
	
	
	
	
	
	
	
	</div> <!-- ending the  panel body -->



<script type="text/javascript">
	
	
	
	$(document).ready(function(){
		
		
		
		$(".costingJobMtForm")
			.validate(
				{

					rules : {
						invNo : {
							required : true,
							remote : {
								url : "<spring:url value='/manufacturing/transactions/costJobMt/invoiceNoAvailable.html' />",
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








		//costJobCompDtForm
		
			$(".costJobCompDtEntForm")
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


		//costJobLabDtForm
		
		$(".costJobLabDtEntForm")
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
			$('#costJobMtId').val(tempCost[0]);
			popCostingJobDetails()
			
			$("#" + document.querySelector("#disableChangeLabourJob").id).attr("id", "changeLabourJob");
			
			$('#openOnEdit').css('display','block');
			
			
		}else{
			$("#invDate").val(new Date().toLocaleDateString('en-GB'));
			$("#invNo").focus();
		}
		
		
		
		
		
		
		$("#searchCostingJobDtBags").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#costJobDtTabId tr").each(function(index) {
		
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
		});
		
		
		
		
		
	})
	
	
	
	function costingJobSave(){
		$("form#costingJobMt").submit();
	}
	

	
	
	
	
	
	
	
	//----------------//--CostingJobDt-------------//
	
	
	function dumpCostingJobDetails(){
		
		
		window.location.href = "/jewels/manufacturing/transactions/costingJobMtTransfer.html?MtId="+$('#costJobMtId').val()
		
		
		
		/* if(!!$('#itemNoId').val() || $('#itemNoId').val() != ""){
			
			//alert("you have entered itemno "+$('#itemNoId').val());
			
			
			
			$.ajax({
				url:'/jewels/manufacturing/transactions/addItemNoWise.html?vItemNo='+$('#itemNoId').val()+"&mtId="+$('#costJobMtId').val(),
				type:'GET',
				success: function(data){
					
					popCostingJobDetails();
					popCostingJobStoneDetails();
					popCostingJobComponentDetails();
					popCostingJobLabDetails();
					
				}
			})
			
			
		}else{
			alert("In null , please enter the item no")
		} */
		
		
		
	}
	
	
	

	function popCostingJobDetails() {

		$("#costJobDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/costingJobDt/listing.html?pInvNo="
									+ $('#invNo').val()
						});

	}
	
	
	var costJobDtTableRow;
	var costJobDtLockStatus = 'null';
	$('#costJobDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				costJobDtTableRow = $element.attr('data-index');
				
				$('#entryCostJobDtId').css('display','none');
				$('#costingJobDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var defImage = jQuery.parseJSON(JSON.stringify(row)).image;
			    costJobDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			
				
				if ((defImage != 'null') && ($.trim(defImage).length > 0)) {
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+defImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+defImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
				}
				
				$('#hideOnPageLoad').css('display','block');
				popCostingJobStoneDetails();
				popCostingJobComponentDetails();
				popCostingJobLabDetails();
				
			})
	
	
	function editCostingJobDt(pkCostJobDtId){
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/costingJobDt/validation.html?dtId="+pkCostJobDtId,
			type : 'GET',
			success : function(data) {
				
				if(data === "-1"){
					displayMsg(this, null, 'Cannot edit,Record is Locked');	
				}else{
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/costingJobDt/edit/"+ pkCostJobDtId + ".html",
						type : 'GET',
						success : function(data) {
							//alert(data);
							 $("#costingJobDtRowId").html(data);
							 $("#entryCostJobDtId").css('display' , 'block');
							 $("#costJobDtGrossWt").focus();
						}
					});
					
				}
				
				
			}
		});

	}
	
	
	function saveCostingJobDt(){
		
		if(!! $('#costJobDtGrossWt').val() && !!$('#other').val()){

			 $
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/costJobDt/saveEdit.html' />?costJobDtId='+
				 			$('#costingJobDtPKId').val()
				 			+"&grossWt=" + $('#costJobDtGrossWt').val()
				 			+"&otherWt=" + $('#other').val(),
				type : 'GET',
				success : function(data) {
					if(data == -1){
						popCostingJobDetails();
						$("#costJobDtGrossWt").val('');
						$('#other').val('');
						$("#entryCostJobDtId").css('display' , 'none');
						$('#hideOnPageLoad').css('display','none');
					}
	
				}
			});
			 
		}else{
			displayMsg(this, null, 'All Fields Are Compulsary');
		}

	}

	
	
	function doCostJobDtLockUnLock(dtIdPk){
		$.ajax({
			url : "/jewels/manufacturing/transactions/costingJobDt/lockUnlock.html?dtId="+dtIdPk,
			type : 'GET',
			success : function(data) {
				if(data == -1){
					popCostingJobDetails();
					popCostingJobStoneDetails();
					popCostingJobComponentDetails();
					popCostingJobLabDetails();
					$('#hideOnPageLoad').css('display','none');
				}
				
			}
		});
	}
	
	
	
	function lockAllCostJobDt(){
		
			var costJobStatus = document.getElementById("chkLockCostJobDt").checked;
			var intStatus;
			if(costJobStatus == true){
				intStatus = 1;
			}else{
				intStatus = 0;
			}
			
			 $.ajax({
				url : "/jewels/manufacturing/transactions/costingJobDt/dtLockUnlockAll.html?mtId="+$('#costJobMtId').val()
						+"&status="+intStatus,
				type : 'GET',
				success : function(data) {
					if(data === '-1'){
						popCostingJobDetails();
						displayInfoMsg(this, null, 'Lock-Unlock Applied Succesfully');
						$('#hideOnPageLoad').css('display','none');
					}
				}
			}); 
			
		 
		
		
	}
	
	
	
	
	$('#costJobDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#costJobDtTabId").bootstrapTable('getData'));

				var vTotPcs = 0.0;
				
				
				$.each(JSON.parse(data), function(idx, obj) {
					vTotPcs		+= Number(obj.pcs);
				});
				
				$('#totPcs').val(" " + vTotPcs);
			});
		
	
	
	
	function deleteCostingJobDt(e,costJobDtd){
		
		if(costJobDtLockStatus === 'Lock'){
			displayMsg(this, null, ' Cannot Delete Record Is Locked');
		}else{
		
			displayDlg(
					e,
					'javascript:deleteCostJobDtData('+costJobDtd+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
		}
		
	}
	
	
	
	function deleteCostJobDtData(costJobDtPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/costingJobDt/delete/'+costJobDtPk+'.html',
			data: 'GET',
			success : function(data){
			
					popCostingJobDetails();
					popCostingJobStoneDetails();
					popCostingJobComponentDetails();
					popCostingJobLabDetails();
					$('#hideOnPageLoad').css('display','none');
				
				
			}
			
		})
		
		
	}
	
	

	
	//----------------------stone--------//------------->

	
	function popCostingJobStoneDetails(){
		
		
		$("#costJobStnDtTabId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/costJobStnDt/listing.html?costJobDtId="
							+ $('#costingJobDtPKId').val()
				});

		}
	
	
	var costJobStnLockStatus = 'null';
	$('#costJobStnDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				$('#costingJobStoneDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				costJobStnLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				 $("#entryCostJobStnDt").css('display' , 'none');
				 $('#costJobStnDtPk').val('');
				 $('form#costJobStnDtEnt select').val('');
				 $('#handlingRate').val('');
				 $('#setRate').val('');
				 
			})
	
	
	
	function editCostJobStnDt(pkCostJobStnDtId){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
				$.ajax({
					url : "/jewels/manufacturing/transactions/costJobStnDt/validationEdit.html?stnId="+pkCostJobStnDtId,
					type : 'GET',
					success : function(data) {
						
						//alert(data);
						
						if(data === "-1"){
							displayMsg(this, null, 'Cannot edit,Record is Locked');	
						}else{
						
							$('#costJobStnDtPk').val(pkCostJobStnDtId);
							
							$.ajax({
								url : "/jewels/manufacturing/transactions/costJobStnDt/edit/"+ pkCostJobStnDtId + ".html",
								type : 'GET',
								success : function(data) {
									 $("#costJobStnDtRowId").html(data);
									 $("#entryCostJobStnDt").css('display' , 'block');
									 $("#setting\\.id").focus();
								}
							});
							
						}
						
					}
				});
				
		 }
		
	}
	
	
	
 	function saveCostJobStnDt(){
		 
 		
 		if(!!$('#setting\\.id').val() && !!$('#settingType\\.id').val() && !!$('#stnRate').val() && !!$('#handlingRate').val() && !!$('#setRate').val()){
 		
 		
 		
			 $
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/costJobStnDt/saveEdit.html' />?costJobStnDtId='+
					 			$('#costJobStnDtPk').val()
					 			+"&settingId=" + $('#setting\\.id').val()
					 			+"&setTypeId=" + $('#settingType\\.id').val()
					 			+"&stoneRate=" + $('#stnRate').val()
					 			+"&handRate=" + $('#handlingRate').val()
					 			+"&setRate=" + $('#setRate').val(),
					type : 'GET',
					success : function(data) {
						
							if(data.indexOf("#") != -1){
							}else{
								
								var tempData = data.split("_");
								
								$("#costJobDtTabId").bootstrapTable(
										'updateRow',
										{
											index : costJobDtTableRow,
											row : {
												state : true,
												stoneValue  	: tempData[1],
												setValue  		: tempData[2],
												handlingCost  	: tempData[2],
												fob 	   		: tempData[7],
												finalPrice 		: tempData[8],
	
											}
										});
								
							}	
							
			
							$('#costJobStnDtPk').val('');
							popCostingJobStoneDetails();
							$('form#costJobStnDtEnt select').val('');
							$('#handlingRate').val('');
							$('#setRate').val('');
							$("#entryCostJobStnDt").css('display' , 'none');
						}
	
					
				});
		 
 		}else{
 			
 			 displayMsg(this, null, ' All Fields Are Compulsary');
 		}
		 
	 }
	
 
 
	 function doStoneJobDtLockUnLock(stnJobDtId){
		 
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		 
			 $.ajax({
					url : "/jewels/manufacturing/transactions/costJobStnDt/lockUnlock.html?stnJobDtId="+stnJobDtId,
					type : 'GET',
					success : function(data) {
						if(data === '-1'){
							popCostingJobStoneDetails();
						}
					}
				});
			 
		 }
	 
	 }
 
	 
 
	/* function lockAllCostJobStnDt(){
		var costJobStnStatus = document.getElementById("chkLockCostStnDt").checked;
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
			 
		} */
	
	
	
	function deleteCostJobStnDt(e,costStnJobDtId){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(costJobStnLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record is Locked');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteCostJobStnDtData('+costStnJobDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
			 }
			
		}
	
	
	
	function deleteCostJobStnDtData(costJobStnDtId){ 
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/costJobStnDt/delete/'+costJobStnDtId+'.html',
			data: 'GET',
			success : function(data){
			
					if(data.indexOf("#") != -1){
					}else{
						
						var tempData = data.split("_");
						
						$("#costJobDtTabId").bootstrapTable(
								'updateRow',
								{
									index : costJobDtTableRow,
									row : {
										state : true,
										stoneValue  	: tempData[1],
										setValue  		: tempData[2],
										handlingCost  	: tempData[2],
										fob 	   		: tempData[7],
										finalPrice 		: tempData[8],

									}
								});
						
					}	
					
					popCostingJobStoneDetails();
						
						
				
					
		  }

			
		})
		
		
	}
	
	
	
	$('#costJobStnDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#costJobStnDtTabId").bootstrapTable('getData'));

				
				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					
					
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
					
					
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});
	
	
	
	
	
	
	//----------------------component--------//------------->
	

	
	function popCostingJobComponentDetails(){
		
	$("#costJobCompDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costJobCompDt/listing.html?costJobDtId="
						+ $('#costingJobDtPKId').val()
			});
		
	}
	
	
	var costJobCompLockStatus = 'null';
	$('#costJobCompDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				costJobCompLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				$('form#costJobCompDtEnt select').val('');
				$('#compMetalWt').val('0.0');
				$('#compRate').val('0.0');
				$('#compJobLock').attr('checked', false); 
				$('#costJobCompDtPk').val('');
				$('#entryCostJobCompDt').css('display','none');
			})
	
	
	
	
	$(document)
		.on(
			'submit',
			'form#costJobCompDtEnt',
			 function(e){
				
				$('#forCompCostJobMtId').val($('#costJobMtId').val());
				$('#forCompCostJobDtId').val($('#costingJobDtPKId').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {
						
						var tData  ;
						
						if(data.indexOf("#") != -1){
							var tempDa = data.split("_");
							tData = tempDa[0];
						}else{
							
							var tempData = data.split("_");
							tData = tempData[0];
							
							$("#costJobDtTabId").bootstrapTable(
									'updateRow',
									{
										index : costJobDtTableRow,
										row : {
											state : true,
											compValue  : tempData[4],
											netWt 	   : tempData[6],
											fob 	   : tempData[7] ,
											finalPrice : tempData[8] ,

										}
									});
							
						}
						
						popCostingJobComponentDetails();
						
						$('form#costJobCompDtEnt select').val('');
						$('#compMetalWt').val('0.0');
						$('#compRate').val('0.0');
						$('#compLock').attr('checked', false); 
						$('#costJobCompDtPk').val('');
						
						if(tData === '-2'){
							$('#entryCostJobCompDt').css('display','none');
						}
					
					},
					
					error : function(data, textStatus, jqXHR){
						alert("ERROR");
					}
						
				})
				
				e.preventDefault();
		
	})
	
	
	function addCostJobCompDt(){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
			
				$('form#costJobCompDtEnt select').val('');
				$('#compJobMetalWt').val('0.0');
				$('#compJobRate').val('0.0');
				$('#compJobLock').attr('checked', false);
				$('#costJobCompDtPk').val('');
				$('#entryCostJobCompDt').css('display','block');
				$('#component\\.id').focus();
					
				
		
		 }
		
	}
	
	
	function editCostJobCompDt(pkCompJobDtId){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/costJobCompDt/validationEdit.html?compJobId="+pkCompJobDtId,
				type : 'GET',
				success : function(data) {
					
					if(data === "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/transactions/costJobCompDt/edit/"+ pkCompJobDtId + ".html",
							type : 'GET',
							success : function(data) {
								$("#costJobCompRowId").html(data);
								$('#entryCostJobCompDt').css('display','block');
								$('#component\\.id').focus();
							}
						});
					}
					    
				}
			});
		
		 }
	}
	
	
	function doCompJobDtLockUnLock(compJobDtIdPk){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			 $.ajax({
					url : "/jewels/manufacturing/transactions/costJobCompDt/lockUnlock.html?compJobDtId="+compJobDtIdPk,
					type : 'GET',
					success : function(data) {
							popCostingJobComponentDetails();
						
					}
				});
		 }
	}
	
	
	/* function lockAllCostCompDt(){
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
		
	} */
	
	
	
	
	function deleteCostJobCompDt(e,costJobCompDtId){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }if(costJobCompLockStatus == 'Lock'){
			 displayMsg(this, null, ' Cannot Delete Record Is Locked ');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteCostJobCompDtData('+costJobCompDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		 }
		
		
	}



	function deleteCostJobCompDtData(costCompJobDtPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/costJobCompDt/delete/'+costCompJobDtPk+'.html',
			data: 'GET',
			success : function(data){
				
					if(data.indexOf("#") != -1){
					}else{
						
						var tempData = data.split("_");
						
						$("#costJobDtTabId").bootstrapTable(
								'updateRow',
								{
									index : costJobDtTableRow,
									row : {
										state : true,
										compValue  : tempData[4],
										netWt 	   : tempData[6],
										fob 	   : tempData[7] ,
										finalPrice : tempData[8] ,

									}
								});
						
					}	
					
					popCostingJobComponentDetails();
						
						
				
				
				
			}
			
		})
		
	}
		
	
	 function popStoneRateFromMaster(){
	
		if(!!$('#setting\\.id').val() && !!$('#settingType\\.id').val()){
			
			  $.ajax({
				  url:"/jewels/manufacturing/transactions/costJobStnDt/rateFromMaster.html?setId="+$('#setting\\.id').val()
						  +"&setTypeId="+$('#settingType\\.id').val()
						  +"&costJobStnDtId="+$('#costingJobStoneDtPKId').val(),
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
	
	//----------------------costJobLabDt--------//------------->
	
	
	
	
	function popCostingJobLabDetails(){
		
		$("#costJobLabDtTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costJobLabDt/listing.html?costJobDtId="
						+ $('#costingJobDtPKId').val()
			});
		
	}
	
	var costJobLabLockStatus = 'null';
	$('#costJobLabDtTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				costJobLabLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				$('form#costJobLabDtEnt select').val('');
				$('#labourRate').val(0.0);
				$('#labJobLock').attr('checked', false);
				$('#pcsWise').attr('checked', false);
				$('#costJobLabDtPk').val('');
				$('#entryCostJobLabDt').css('display','none');
			})
	
	
			
	$(document).
		on('submit',
			'form#costJobLabDtEnt',
			function(e){
			
			$('#forLabCostJobMtId').val($('#costJobMtId').val());
			$('#forLabCostJobDtId').val($('#costingJobDtPKId').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					
					if(data === "-11"){
						displayMsg(this, null, 'please select only one check box');	
					}else{
						
						var tData  ;
						
						if(data.indexOf("#") != -1){
							var tempDa = data.split("_");
							tData = tempDa[0];
						}else{
							
							var tempData = data.split("_");
							tData = tempData[0];
							
							$("#costJobDtTabId").bootstrapTable(
									'updateRow',
									{
										index : costJobDtTableRow,
										row : {
											state : true,
											labourValue  : tempData[5],
											fob 	   : tempData[7] ,
											finalPrice : tempData[8] ,

										}
									});
							
						}
						
						popCostingJobLabDetails();
						
						$('form#costJobLabDtEnt select').val('');
						$('#labourRate').val(0.0);
						$('#labJobLock').attr('checked', false);
						$('#pcsWise').attr('checked', false);
						$('#costJobLabDtPk').val('');
						
						if(tData == -2){
							$('#entryCostJobLabDt').css('display','none');
						}
						
					}
					
				},
				error : function(data, textStatus, jqXHR){
					
				}
				
			})
			
			e.preventDefault();
			
		})
		
		
		
	function addCostJobLabDt(){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
					
			$('form#costJobLabDtEnt select').val('');
			$('#labourRate').val(0.0);
			$('#labJobLock').attr('checked', false);
			$('#pcsWise').attr('checked', false);
			$('#costJobLabDtPk').val('');
			$('#entryCostJobLabDt').css('display','block');
			$('#labourType\\.id').focus();
					
				
				
		 }
		
		
	}	
		
		
	function editCostJobLabDt(pkLabDt){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/costJobLabDt/validationEdit.html?labCostId="+pkLabDt,
				type : 'GET',
				success : function(data) {
					if(data === "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/transactions/costJobLabDt/edit/"+ pkLabDt + ".html",
							type : 'GET',
							success : function(data) {
								$("#costJobLabRowId").html(data); 
								$('#entryCostJobLabDt').css('display','block');
								$('#labourType\\.id').focus();
							}
						});
						
					}
					
					
					
				}
			});
		
		 }
		
	}
	
	
	function doLabJobDtLockUnLock(labDtIdPk){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
			 $.ajax({
					url : "/jewels/manufacturing/transactions/costJobLabDt/lockUnlock.html?labCostDtId="+labDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data === '-1'){
							popCostingJobLabDetails();
						}
					}
				});
		 }
	}
	
	
	/* function lockAllCostLabDt(){
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
		
	} */
	
	
	
	
	function deleteCostJobLabDt(e,costJobLabDtId){
		
		 if(costJobDtLockStatus == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(costJobLabLockStatus == 'Lock'){
			 displayMsg(this, null, 'Cannot Delete Record Is Locked');
		 }else{
			
			displayDlg(
					e,
					'javascript:deleteCostJobLabDtData('+costJobLabDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		  }
			
	 }
	
	
	
		function deleteCostJobLabDtData(costJobLabDtId){
			
			$("#modalDialog").modal("hide");
			
			$.ajax({
				url:'/jewels/manufacturing/transactions/costJobLabDt/delete/'+costJobLabDtId+'.html',
				data: 'GET',
				success : function(data){
				
					
						if(data.indexOf("#") != -1){
						}else{
							
							var tempData = data.split("_");
							
							$("#costJobDtTabId").bootstrapTable(
									'updateRow',
									{
										index : costJobDtTableRow,
										row : {
											state : true,
											labourValue  : tempData[5],
											fob 	   : tempData[7] ,
											finalPrice : tempData[8] ,

										}
									});
							
						}	
						
						popCostingJobLabDetails();
		
					
				}
				
			})
			
		}
		
		
	
		
	// --------------tab-------------------------//------------->>>>>
		
		
		/* function popInwardType(){
			$("#changeInwardId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/changeInwardType/listing.html"
					});
		}
		 */
		
		
		function popLabourType(){
			$("#labJobTypeId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/changeLabourJobType/listing.html"
					});
		}

		
	
		
		
		
		
		//---------------------- APPLY RATE --------//----------------->
		
		
		$(document).
			on('submit',
				'form#applyRateCosting',
				function(e){
				
				
				$("#costJobMtIdPkAR").val($('#costJobMtId').val());
				$("#costGldRate").val($("#metalRate").val());
				$("#costSlvRate").val($("#silverRate").val());
				$("#costAddPerc").val($("#addPercent").val());
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
				
			})
		
		
	
			
		function pageRelod(){
			window.location.reload(true);
		}
		
		
	    function updateJobFinding(){
			
			 if(costJobDtLockStatus == 'Lock'){
				 displayMsg(this, null, ' Main Item Is Lock');
			 }else{
				window.location.href = "/jewels/manufacturing/transactions/costJobCompFinding.html?dtId="+$('#costingJobDtPKId').val()+"&mtId="+$('#costJobMtId').val();
			 }
		}
		
		
		function costingSave(){
			$("form#costingMt").submit();
		}
		
		
		//--------//----Labour As Per Master---------//
		
		function popLabAsPerMast(){
			
			$.ajax({
				
				url:"/jewels/manufacturing/transactions/labourAsPerMaster/job/save.html?mtId="+$('#costJobMtId').val(),
				type : 'GET',
				success : function(data,e) {
						disp(event);
				}
			
			});
			
		}
		
		
		function disp(e){
			 displayConfirmation(e,'javascript:pageRelod();','Notification','Rate Applied Succesfully As Per Master ! ','Continue');
		}



</script>



<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/common/design.js' />"></script>
<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

