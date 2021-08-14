<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalDashboardFilterList.jsp"%>



<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>



<div class="panel panel-primary" style="width: 100%">

	<div class="panel-heading">
			
		
		<div>
	<label class="col-lg-5 text-left">
				<span style="font-size: 18px;" id="dashboardDivId"></span>
			</label>
		
	
	</div>
			
		<div class="text-right">
		<a class="btn btn-xs btn-success" style="font-size: 14px" type="button" onclick="javascript:dashboardBackBtn()"><b>Back</b></a>
		</div>	
	
	</div>	

	
	<div class="container-fluid" id="dashboardMainDiv">
	
	
	
		
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			
					
					
			
		
				<div id="betweenDateDiv" style="display: none">
					
						<div class="row">
							<div class="col-xs-2"></div>
							
							<div id="orderDate" class="col-xs-6">
								<div class="row">
									<div class="col-xs-3">										
											<label for="inputLabel4" class=".col-lg-2">Between Date </label>
									</div>
									
									<div class=" col-xs-4">
										<input type="text" class="form-control" name="fromOrdDate"
											id="fromOrdDate" autocomplete="off" />
									</div>

									<div class="col-xs-4">
										<input type="text" class="form-control" name="toOrdDate"
											id="toOrdDate" autocomplete="off" />
									</div>
								</div>

							</div>
							
						<div class="col-xs-2" align="left">	
							<input type="button" id="todayId" name="todayId" value="Today"
								class="btn-sm btn-secondary" onclick="javascript:popTodayDt()">
							<input type="button" id="monthId" name="monthId" value="Month"
								class="btn-sm btn-secondary" onclick="javascript:popMonthDt()">
							<input type="button" id="yearId" name="yearId" value="Year"
								class="btn-sm btn-secondary" onclick="javascript:popYearDt()">
						
					</div><!-- End of order Date -->
					</div>
					
					</div>
					
					
					<div id="asOnDateDiv" style="display: none">
					
						<div class="row">
							<div class="col-xs-2"></div>
							
							<div id="orderDate" class="col-xs-6">
								<div class="row">
									<div class="col-xs-3">										
											<label for="inputLabel4" class=".col-lg-2">As On Date </label>
									</div>
							

									<div class="col-xs-4">
										<input type="text" class="form-control" name="toOrdDate"
											id="asOntoOrdDate" autocomplete="off" />
									</div>
								</div>

							</div>
							
					
					</div>
					
					</div>
					
					
		
		
						<!-----------//----------Divison New Ui--------//------>
			<div id="divisionDs" style="display: none">
				
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadDivision" class=".col-lg-2 ">Division</label>	
											</div>
								<div class="col-xs-8">			
									 <textarea  name="divisionTextBoxDs" id="divisionTextBoxDs" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myDivisionModalDs"
								onclick="javascript:popDivisionDs(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;
									</a>&nbsp;										
						<a href="javascript:popDivisionDs(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
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
					
					
					
					
						<!-----------//----------Region New Ui--------//------>
			<div id="regionDs" style="display: none">
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			<div class="row">
						<div class="col-xs-2"></div>
						<div class="col-xs-8" >
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadRegion" class=".col-lg-2 ">Region</label>	
											</div>
								<div class="col-xs-8">			
									 <textarea  name="regionTextBoxDs" id="regionTextBoxDs" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
									 </textarea>
										</div>							
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myRegionModalDs" 
								onclick="javascript:popRegionDs(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;
									</a>&nbsp;										
						<a href="javascript:popRegionDs(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
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
					<div id="customerTypeDs" style="display: none">
					
						
					<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
					<div class="row">
								<div class="col-xs-2"></div>
								<div class="col-xs-8" >
									<div class="row">
										<div class="col-xs-2">	
													<label for="loadCustomerType" class=".col-lg-2 ">Customer Type</label>	
													</div>
										<div class="col-xs-8">			
											 <textarea  name="customerTypeTextBoxDs" id="customerTypeTextBoxDs" style="height: 1cm; resize: vertical;"class=" form-control" disabled="disabled" >
											 </textarea>
												</div>							
										<div class="col-xs-2">	

											<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myCustomerTypeModalDs"
										onclick="javascript:popCustomerTypeDs(0);"> <span
										class="glyphicon glyphicon-list"></span>&nbsp;
											</a>&nbsp;										
								<a href="javascript:popCustomerTypeDs(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
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
			
			<div id="orderTypeDs" style="display: none">
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			<div class="row">
				
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
				
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="orderType" class=".col-lg-2 text-right"> OrderType</label>	
											</div>
								<div class="col-xs-8">			
								
					<textarea type="text" name="orderTypeTextBoxDs" id="orderTypeTextBoxDs" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled" > 
										</textarea> 
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myOrderTypeModalDs"
								onclick="javascript:popOrderTypeDs(0);">
								 <span class="glyphicon glyphicon-list"></span>&nbsp;<!--Load OrderType -->
									</a>&nbsp;										
						<a href="javascript:popOrderTypeDs(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>		
								</div>			
							</div>			
										
						</div>		

							
						<!--//----------  OrderType list------------//-->
					
					
			<div class="col-xs-2"></div>
				
				
				</div> <!-- End of OrderType Row -->
				</div>
				
				
			
			
			
				<!--//---------- client button------------//-->
				
				<div id="clientDs" style="display: block">
				
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
				<div class="row">
				
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
										
							<div class="row">
								<div class="col-xs-2">	
											<label for="client" class=".col-lg-2 text-right"> Client</label>	
								</div>
								<div class="col-xs-8">			
								
						<textarea type="text" name="clientTextBoxDs" id="clientTextBoxDs" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled" > 
										</textarea>
						
										</div>
								
						<div class="col-xs-2">	
																		
						<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myClientModalDs"
									onclick="javascript:popClientDs(0);"><span
							class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Client -->
						</a>&nbsp;										
						<a href="javascript:popClientDs(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>																				
						</div>	
						
							
							</div>			
							</div>		
						<div class="col-xs-2"></div>				
			</div>
					
					
		
			</div>  <!-- End of the Client -->
			
			
				
		
			
					
					
					
				
					
				
				
				
					<!-----------//---------- LOCATION Button --------//------>
 							<div id="locationDs" style="display: none">
 							
								<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
			
 							<div class="row">
							
							<div class="col-xs-2"></div>
								<div class="col-xs-8">
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadLocationDs" class=".col-lg-2 text-right">Location</label>	
											</div>
								<div class="col-xs-8">			
									
								<textarea type="text"  name="locationTextBoxDs" id="locationTextBoxDs" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled">
								 </textarea>
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myLocationModalDs"
								onclick="javascript:popLocationDs(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;<!-- Load Location -->
									</a>&nbsp;										
						<a href="javascript:popLocationDs(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>			
										</div>			
							</div>			
										
					

						<!--//---------- Location list------------//-->

						<div id="locationTableDivId" style="display: block">
							
						</div>
					</div>  <!-- End of the Location -->
			<div class="col-xs-2"></div>
		
			</div>	<!-- End of the Location Row -->
			</div>
			
		

	
			<!--  <div id="orderDs" style="display: block">
			<div class="row">
					
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
						<div id="orderBtn" style="display: block">
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="loadOrder" class=".col-lg-2 text-right">Order</label>	
											</div>
								<div class="col-xs-8">			
						<textarea type="text" name="orderTextBoxDs" id="orderTextBoxDs" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled" > 
										</textarea> 
										</div>
								
								<div class="col-xs-2">	
									
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myOrderModalDs"
								onclick="javascript:popOrderDs(0);"> <span
								class="glyphicon glyphicon-list"></span>&nbsp;Load Order
									</a>&nbsp;																					
						<a href="javascript:popOrderDs(1)" class="glyphicon glyphicon-refresh" data-toggle="tooltip" 
										data-placement="right" title="Clear Text " >
						</a>		
								
								</div>			
							</div>													
						</div>

								
					</div>  
					
					<div class="col-xs-2"></div>
		
				</div>  
				</div> -->
				
			
				 
						
			
		
		
		
		
			
				<div id="salesmanDiv"  style="display: none">
			
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
				
							<div class="row">		
									<div class="col-xs-2"></div>					
										<div class="col-xs-8">
										<div class="row">
										<div class="col-xs-2">	
											<label for="salesman" class=".col-lg-2 text-left">Salesman</label>	
											</div>
		
											<div class="col-xs-8">												
												<div id="salesmanDivId" ></div>	
											</div>	
											
										</div>											
									</div>	
										<div class="col-xs-2"></div>								
								</div>
							
							
						</div>
						
						
						
				
		
			
				<div id="tranStatusDiv"  style="display: none">
				
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
				
							<div class="row">		
									<div class="col-xs-2"></div>					
										<div class="col-xs-8">
										<div class="row">
										<div class="col-xs-2">	
											<label for="tranStatus" class=".col-lg-2 text-left">Status</label>	
											</div>
		
											<div class="col-xs-8">												
												<div id="tranStatusDivId" ></div>	
											</div>	
											
										</div>											
									</div>	
										<div class="col-xs-2"></div>								
							</div>
							
						</div>
						
		
	
		
			
				<div id="reportFormatDiv"  style="display: none">
				
					<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
				
				
						
							<div class="row">		
																																	
							
									<div class="col-xs-2"></div>					
										
										<div class="col-xs-8">
										<div class="row">
										
										<div class="col-xs-2">	
											<label for="groupBy" class=".col-lg-2 text-left">Group By</label>	
											</div>
										
											<div class="col-xs-8">												
												<div id="groupByDropDownDivId" ></div>	
											</div>	
											
										</div>											
									</div>	
										<div class="col-xs-2"></div>								
							</div>
						</div>
					
						
						
						
					<div class="row">
					
					<div class="col-md-4">
							<input type="button" value="Show List"	class="btn btn-primary"
								onclick="javascript:dashboardList()" />
						</div>
					</div>	
					
					
					
					
					<div class="row">
							<div class="col-xs-12">&nbsp;</div>
						</div>
						
							<div>
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" align="left">
				 <span class="col-sm-12 label label-default" 
					style="font-size: 22px;margin-left:auto; ">Details</span>
			</div>
		</div>
		
		
	<div id="dashboardDtDivId">
		<!-- sales Order -->
		<div class="form-group" id="salesOrderDsDivId" style="display: none">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="saleOrderTblId" data-toggle="table"
								data-toolbar="#toolbarDt" data-height="300">
								<thead>
									<tr>
									
									<th data-field="groupBy" data-align="left">Group By</th>
									<th data-field="ordQty" data-align="left">Pcs</th>
									<th data-field="grossWt" data-align="left">Gross Wt.</th>
									<th data-field="ordValue" data-align="left">Order Value</th>
									
									
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
		
		
		
		<!-- sales Invoice -->
			<div class="form-group" id="salesInvoiceDsDivId" style="display: none">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="saleInvoiceTblId" data-toggle="table"
								data-toolbar="#toolbarDt" data-height="300">
								<thead>
									<tr>
									
									<th data-field="groupBy" data-align="left">Group By</th>
									<th data-field="ordQty" data-align="left">Pcs</th>
									<th data-field="grossWt" data-align="left">Gross Wt.</th>
									<th data-field="ordValue" data-align="left">Sales Value</th>
									
									
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- sales Quatation -->
			<div class="form-group" id="salesQuatationDsDivId" style="display: none">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="saleQuatationTblId" data-toggle="table"
								data-toolbar="#toolbarDt" data-height="300">
								<thead>
									<tr>
									<th data-field="groupBy" data-align="left">Group By</th>
									<th data-field="ordQty" data-align="left">Pcs</th>
									<th data-field="grossWt" data-align="left">Gross Wt.</th>
									<th data-field="quotValue" data-align="left">Quot Value</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- Diamond Stock -->
			<div class="form-group" id="diaStockDsDivId" style="display: none">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="diaStockTblId" data-toggle="table"
								data-toolbar="#toolbarDt" data-height="300">
								<thead>
									<tr>
									<th data-field="deptNm" data-align="left">Department</th>
									<th data-field="carat" data-align="left">carat</th>
									<th data-field="diaCost" data-align="left">Value</th>
									<th data-field="tranStatus" data-align="left">Status</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
		<!-- Fg Issue -->
			<div class="form-group" id="fgIssueDsDivId" style="display: none">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="fgIssueTblId" data-toggle="table"
								data-toolbar="#toolbarDt" data-height="300">
								<thead>
									<tr>
									<th data-field="deptNm" data-align="left">Department</th>
									<th data-field="pcs" data-align="left">Pcs</th>
									<th data-field="grossWt" data-align="left">Gross Wt</th>
									<th data-field="fgValue" data-align="left">Cost</th>
								</tr>
							</thead>
						</table>
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


	var dashboardmodalNm;
	var groupBycond;

	$(document)
			.ready(
					function(e) {

						displayGrouByFormat();
						displaySalesmanFormat();
						displayTranStatusFormat();

						 $("#fromOrdDate").val(new Date().toLocaleDateString('en-GB'));
						$("#toOrdDate").val(new Date().toLocaleDateString('en-GB'));
						$("#asOntoOrdDate").val(new Date().toLocaleDateString('en-GB'));

						

						$("#fromOrdDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#toOrdDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#asOntoOrdDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						if (window.location.href.indexOf('opt') != -1) {
							
							var mainUrl = window.location.href;
							dashboardmodalNm = mainUrl.substring(window.location.href.indexOf('opt') + 4);

							if(dashboardmodalNm  == 'Sales_Order'){
									$('#salesOrderDsDivId').css('display','block');
									$('#regionDs').css('display','block');
									$('#customerTypeDs').css('display','block');
									$('#orderTypeDs').css('display','block');
									$('#divisionDs').css('display','block');
									$('#reportFormatDiv').css('display','block');
									$('#betweenDateDiv').css('display','block');

									
									
							}else if(dashboardmodalNm  == 'Sales_Invoice'){
									$('#salesInvoiceDsDivId').css('display','block');
									$('#regionDs').css('display','block');
									$('#customerTypeDs').css('display','block');
									$('#orderTypeDs').css('display','block');
									$('#reportFormatDiv').css('display','block');
									$('#betweenDateDiv').css('display','block');
									
							}else if(dashboardmodalNm  == 'Sales_Quatation'){
								$('#salesQuatationDsDivId').css('display','block');

							}else if(dashboardmodalNm  == 'Diamond_Stock'){
								$('#diaStockDsDivId').css('display','block');
								$('#tranStatusDiv').css('display','block');
								$('#asOnDateDiv').css('display','block');

							}else if(dashboardmodalNm  == 'FG_Issue'){
							
								$('#fgIssueDsDivId').css('display','block');
								$('#regionDs').css('display','block');
								$('#customerTypeDs').css('display','block');
								$('#orderTypeDs').css('display','block');
								$('#reportFormatDiv').css('display','block');
								$('#betweenDateDiv').css('display','block');

								
							}  
							
							
						}



						$('#dashboardDivId').text(dashboardmodalNm + " Filters");
					


					});
				
				
				
				
					function displayGrouByFormat() {

						$
							.ajax({
								url : "/jewels/manufacturing/masters/displayGrouByFormat.html",
								data : 'GET',
								success : function(data) {
										$('#groupByDropDownDivId').html(data);
									//	$('#rptFormatId').val($('#xml').val());
								}
							})
						}


					function displaySalesmanFormat() {

						$
							.ajax({
								url : "/jewels/manufacturing/masters/displaySalesmanFormat.html",
								data : 'GET',
								success : function(data) {
										$('#salesmanDivId').html(data);
									//	$('#rptFormatId').val($('#xml').val());
								}
							})
						}


					function displayTranStatusFormat() {

						$
							.ajax({
								url : "/jewels/manufacturing/masters/displayTranStatusFormat.html",
								data : 'GET',
								success : function(data) {
										$('#tranStatusDivId').html(data);
									//	$('#rptFormatId').val($('#xml').val());
								}
							})
						}


					function dashboardList(){

						var clientdata = $('#clientIdTbl').bootstrapTable('getSelections');
						var clientIds = $.map(clientdata, function(item) {
							return item.id;
						});
			

					var otdata = $('#orderTypeIdTbl').bootstrapTable('getSelections');
						var orderTypeIds = $.map(otdata, function(item) {
							return item.id;
						});
						
						
						var odata = $('#orderIdTbl').bootstrapTable('getSelections');
						var orderIds = $.map(odata, function(item) {
							return item.id;
						});
						

					var	dvdata = $('#divisionIdTbl').bootstrapTable('getSelections');
						var divisionIds = $.map(dvdata, function(item) {
							return item.id;
						});
					

					var	rdata = $('#regionIdTbl').bootstrapTable('getSelections');
						var regionIds = $.map(rdata, function(item) {
							return item.id;
						});


	
					var	csdata = $('#customerTypeIdTbl').bootstrapTable('getSelections');
						var customerTypeIds = $.map(csdata, function(item) {
							return item.id;
						});

						$('html, body').animate({
							scrollTop: $("#dashboardDtDivId").offset().top
						}, 1000);

				if(dashboardmodalNm == 'Sales_Order'){

					 $("#saleOrderTblId")
					.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/dashboard/salesOrder/listing.html?pFromOrdDate="+ $("#fromOrdDate").val()
							+"&pToOrdDate="+$("#toOrdDate").val()
							+"&pRegionIds="+regionIds
							+"&pCustomerTypeIds="+customerTypeIds
							+"&pOrderTypeIds="+orderTypeIds
							+"&pOrderIds="+orderIds
							+"&pOrdDivisionIds="+divisionIds
							+"&pGroupBys="+$('#groupById').val()
							+"&pClientIds="+clientIds
							
						}); 

					}else if(dashboardmodalNm == 'Sales_Invoice'){
						
					 $("#saleInvoiceTblId")
						.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/dashboard/salesInvoice/listing.html?pFromOrdDate="+ $("#fromOrdDate").val()
								+"&pToOrdDate="+$("#toOrdDate").val()
								+"&pRegionIds="+regionIds
								+"&pCustomerTypeIds="+customerTypeIds
								+"&pOrderTypeIds="+orderTypeIds
								+"&pOrderIds="+orderIds
								+"&pGroupBys="+$('#groupById').val()
								+"&pClientIds="+clientIds
								
							}); 
					
					}else if(dashboardmodalNm == 'Diamond_Stock'){
					
					 $("#diaStockTblId")
						.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/dashboard/DiamondStock/listing.html?pToOrdDate="+ $("#asOntoOrdDate").val()
								+"&pTranStatus="+$('#tranStatusId').val()
							}); 


						}else if(dashboardmodalNm == 'FG_Issue'){

							alert('in')
							
							 $("#fgIssueTblId")
								.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/masters/dashboard/getFgIssueListing.html?pFromOrdDate="+ $("#fromOrdDate").val()
										+"&pToOrdDate="+$("#toOrdDate").val()
										+"&pRegionIds="+regionIds
										+"&pCustomerTypeIds="+customerTypeIds
										+"&pOrderTypeIds="+orderTypeIds
										+"&pOrderIds="+orderIds
										+"&pGroupBys="+$('#groupById').val()
										+"&pClientIds="+clientIds
										
									}); 
							
							}
				


						
					
					}	




				function dashboardBackBtn(){
						window.location.href = "/jewels/manufacturing/masters/dashboard.html"
					}



				function popTodayDt() {
					 $("#fromOrdDate").val(new Date().toLocaleDateString('en-GB'));
						$("#toOrdDate").val(new Date().toLocaleDateString('en-GB'));
				}

				function popYearDt() {
					var dt = new Date();
					var month = dt.getMonth();
					if(month < 3){
						$("#fromOrdDate").val('01/04/' + (dt.getFullYear()-1));
						$("#toOrdDate").val('31/03/' + dt.getFullYear());	
					}else{
						
						$("#fromOrdDate").val('01/04/' + dt.getFullYear());
						$("#toOrdDate").val('31/03/' + (dt.getFullYear() + 1));
					}
					

				}

				function popMonthDt() {
					var date = new Date();
					var firstDay = "01" + "/" + ("0" + (date.getMonth() + 1)).slice(-2)
							+ "/" + date.getFullYear();

					var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
					var vlastDay = (lastDay.getDate()) + '/'
							+ ("0" + (lastDay.getMonth() + 1)).slice(-2) + '/'
							+ lastDay.getFullYear();

					 $("#fromOrdDate").val(firstDay);
					 $("#toOrdDate").val(vlastDay);

				}
		
			</script>	




<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>