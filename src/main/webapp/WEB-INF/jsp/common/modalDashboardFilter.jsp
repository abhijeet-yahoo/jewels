<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/jsp/common/modalDashboardFilterList.jsp"%>


<style>
.modal-footer1 {
    padding: 10px;
    text-align: left;
    border-top: 1px solid #e5e5e5;
 
}
    
.modal:nth-of-type(even) {
    z-index: 1052 !important;
}
.modal-backdrop.show:nth-of-type(even) {
    z-index: 1051 !important;
}
   
   
   
</style>





    <div class="modal fade" id="dashboardFilterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Filter</h4>
			</div>

			<div class="modal-body">
			
			
						<div class="row">
					<div id="dateDivId">
						<div class="row">
							<div class="col-xs-2"></div>
							
							<div id="orderDate" class="col-xs-8">
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
				<div class="col-xs-12">&nbsp;</div>
			</div>
		
		
		
				<!-----------//----------Region New Ui--------//------>
			<div id="region" style="display: block">
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
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myRegionModalDs" 
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
					
					
					<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
					
							<!-----------//----------Customer Type New Ui--------//------>
					<div id="customerType" style="display: block">
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

											<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myCustomerTypeModalDs"
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
							
							
							<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
		
		
					<!-----------//---------- OrderTypebutton ----------//-->
			
			<div id="orderType" style="display: block">
			<div class="row">
				
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
				
							
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
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myOrderTypeModalDs"
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
				
				
				</div> <!-- End of OrderType Row -->
				</div>
				
				
				
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
				

	
			<div id="order" style="display: block">
			<div class="row">
				
						<div class="col-xs-2"></div>
					<div class="col-xs-8">
				
							
							<div class="row">
								<div class="col-xs-2">	
											<label for="orderType" class=".col-lg-2 text-right"> Order</label>	
											</div>
								<div class="col-xs-8">			
								<!-- <input type="text"  name="orderTypeTextBox" id="orderTypeTextBox" class=" form-control" disabled="disabled" > -->
					<textarea type="text" name="orderTypeTextBox" id="orderTypeTextBox" style="height: 1cm; resize: vertical;" class=" form-control" disabled="disabled" > 
										</textarea> 
										</div>
								
								<div class="col-xs-2">	
									<a class="btn btn-info" style="font-size: 15px; " type="button" data-toggle="modal" data-target="#myOrderModalDashboard"
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
				
				
				</div> <!-- End of OrderType Row -->
				</div>
				
				
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
				
						
						<!-----------//----------Divison New Ui--------//------>
			<div id="division" style="display: block">
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
		
		<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
		
			<div class="row">
				<div id="reportFormatDiv"  style="display: block">	
						
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
						</div>
		
		
		
		
			</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>