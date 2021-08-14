<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ include file="taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>


<link rel="stylesheet" href="<spring:url value='/css/bootstrap/bootstrap.min.css' />">
<link rel="stylesheet" href="<spring:url value='/css/bootstrap/bootstrap-theme.min.css' />">

<%-- <link rel="stylesheet" href="<spring:url value='/css/bootstrap/bootstrap-table.css' />"> --%>
<link href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css" rel="stylesheet">


<link rel="stylesheet" href="<spring:url value='/css/superfish/superfish.css' />">



<style type="text/css">
.error {
	color: red;
	font-size: 15px;
	}
  }
</style>

<script src="<spring:url value='/js/jquery/jquery.min.js' />"></script>
<script src="<spring:url value='/js/jquery/jquery.validate.min.js' />"></script>
<script src="<spring:url value='/js/bootstrap/bootstrap.min.js' />"></script>

<%-- <script src="<spring:url value='/js/bootstrap/bootstrap-table.js' />"></script> --%>
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<script src="<spring:url value='/js/superfish/superfish.js' />"></script>
<script src="<spring:url value='/js/superfish/hoverIntent.js' />"></script>

<!-- <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet"> -->


<script>
	(function($) { //create closure so we can safely use $ as alias for jQuery

		$(document).ready(function() {
			/* 
			 //Disable mouse right click
		    $("body").on("contextmenu",function(e){
		        return false;
		    });
			 */
			

			// initialise plugin
			var example = $('#example').superfish({
			//add options here if required
			});

			// buttons to demonstrate Superfish's public methods
			$('.destroy').on('click', function() {
				example.superfish('destroy');
			});

			$('.init').on('click', function() {
				example.superfish();
			});

			$('.open').on('click', function() {
				example.children('li:first').superfish('show');
			});

			$('.close').on('click', function() {
				example.children('li:first').superfish('hide');
			});
			
			
		});

	})(jQuery);

	function queryParams() {
		return {
			type : 'owner',
			sort : 'updated',
			direction : 'desc',
			per_page : 100,
			page : 1
		};
	}
	
	
	

	
	
	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>

	<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="txv"%>

	<txv:useAttribute name="view" />
	<div class="container-fluid" >
		<!-- Static navbar -->
		<c:if test="${(view ne 'login') && (view ne 'loginFailedSession') && (view ne 'securityValidation')}">
			<div id="nav">
				<p />
				<ul class="sf-menu" id="example">
					<%-- <li class="current"><a href="javascript:void(0)">Masters</a>
						<ul>
						
						<li class="current"><a
								href="<spring:url value='/admin/validation/lookUpMast.html' />">Lookup Master</a></li>
						
						 <li class="current"><a href="javascript:void(0);">Collection</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/collection.html' />">Collection</a></li>	
								</ul></li>
						
						
							<li class="current"><a href="javascript:void(0);">Category</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/manufacturing/masters/category.html' />">Category</a></li>
										
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/category.html' />">Category</a></li>	
										
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/subCategory.html' />">Sub
											Category</a></li>
									
									
									
									
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/productSize.html' />">Product
											Size</a></li>
									
								</ul></li>
							<li class="current"><a href="javascript:void(0);">Concept</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/concept.html' />">Concept</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/subConcept.html' />">Sub
											Concept</a></li>

								</ul>
							</li>
							<li class="current"><a
								href="<spring:url value='/admin/validation/color.html' />">Color</a></li>

							<li class="current"><a
								href="<spring:url value='/admin/validation/component.html' />">Component</a></li>

							<li class="current"><a href="javascript:void(0);">Department</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/department.html' />">Department</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/employee.html' />">Employee</a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/processMast.html' />">Mfg Process</a></li>	
										
								</ul></li>
								
							<li class="current"><a
								href="<spring:url value='/admin/validation/designGroup.html' />">Design Group</a></li>

							<li class="current"><a href="javascript:void(0);">Metal</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/metal.html' />">Metal</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/purity.html' />">Purity</a></li>
										<li class="current"><a
										href="<spring:url value='/admin/validation/metalLock.html' />">Metal Lock</a></li>
								</ul>
							</li>
								
								
								<li class="current"><a href="javascript:void(0);">Rate Policy</a>
									<ul>
								
										<li class="current"><a
											href="<spring:url value='/admin/validation/findingRateMast.html' />">Finding Rate</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/handlingMaster.html' />">Handling Rate</a></li>
									
										<li class="current"><a
											href="<spring:url value='/admin/validation/labourCharge.html' />">Labour Charge</a></li>
	
										<li class="current"><a
											href="<spring:url value='/admin/validation/settingCharge.html' />">Setting Charge</a></li>
	
	
										<li class="current"><a
											href="<spring:url value='/admin/validation/stoneRateMast.html' />">Stone Rate</a></li>

									</ul>
								</li>
								
								
							
								
								

							<li class="current"><a href="javascript:void(0);">Party</a>
								<ul>
									<li class="current"><a
								href="<spring:url value='/admin/validation/party.html' />">Party</a></li>
								<li class="current"><a
								href="<spring:url value='/admin/validation/clientRef.html' />">Client Reference Barcode</a></li>
								
								<li class="current"><a
								href="<spring:url value='/admin/validation/clientWastage.html' />">Client Wastage</a></li>
								<li class="current"><a
								href="<spring:url value='/admin/validation/clientStamp.html' />">Client Stamp</a></li>
								
								<li class="current"><a
								href="<spring:url value='/admin/validation/ledgerGroup.html' />">Ledger Group</a></li>
								
								<li class="current"><a
								href="<spring:url value='/admin/validation/ledger.html' />">New Party</a></li>
								
								<li class="current"><a
								href="<spring:url value='/admin/validation/marketTypeMast.html' />">Market Type</a></li>
								
								<li class="current"><a
								href="<spring:url value='/admin/validation/productTypeMast.html' />">Product Type</a></li>
								
								
								</ul></li>	
							<li class="current"><a href="javascript:void(0);">Stone</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/shape.html' />">Shape</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/subShape.html' />">Sub
											Shape</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/quality.html' />">Quality</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/stoneChart.html' />">Stone
											Chart</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/stoneType.html' />">Stone
											Type</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/sizeGroup.html' />">Size
											Group</a></li>
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/setting.html' />">Setting</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/settingType.html' />">SettingType</a></li>

								</ul></li>


							

							<li class="current"><a href="javascript:void(0);">Type
									Master</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/inwardType.html' />">Inward
											Type</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/labourType.html' />">Labour
											Type</a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/orderType.html' />">OrderType</a></li>

								</ul></li>



							<li class="current"><a
								href="<spring:url value='/admin/validation/prodLabourType.html' />">Production Labour Type</a></li>


					<li class="current"><a
								href="<spring:url value='/admin/validation/lookmast.html' />">Look Master</a></li>

							<li class="current"><a
								href="<spring:url value='/admin/validation/patternmast.html' />">Pattern Master</a></li>
					
					
						</ul></li>

					<li class="current"><a href="javascript:void(0)">Design</a>
						<ul>
							<li class="current"><a
								href="<spring:url value='/admin/validation/design.html' />">Design
									Master</a></li>
							<li class="current"><a
								href="<spring:url value='/admin/validation/designMoldType.html' />">Design Mold Type</a></li>	
								
								<li class="current"><a
								href="<spring:url value='/admin/validation/designMold.html' />">Design Mold</a></li>	
							<li class="current"><a
								href="<spring:url value='/admin/validation/emailConcept.html' />">Email
									Concept</a></li>
									
							<li class="current"><a
								href="<spring:url value='/admin/validation/styleChange.html' />">Style Change</a></li>
						</ul></li>

					<li class="current"><a href="javascript:void(0)">Order</a>
						<ul>
							<li class="current"><a
								href="<spring:url value='/admin/validation/quotMt.html' />">Quotation</a></li>
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/order.html' />">Order</a></li>	
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/bagGenQty.html' />">Bag Generation Qty</a></li>	
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/bagMt.html' />">Bag
									Generation</a></li>	
							<li class="current"><a
								href="<spring:url value='/admin/validation/tranMt.html' />">Set
									Opening</a></li>
									
									
								
							<li class="current"><a
								href="<spring:url value='/admin/validation/autoOrderClose.html' />">Auto Order Close</a></li>	
									
						</ul></li>
						
						
						  <li class="current"><a href="javascript:void(0)">PDC</a>
						<ul>
							<li class="current"><a 
											href="<spring:url value='/admin/validation/pdc.html' />">PD Order</a>
										</li>
										
							<li class="current"><a 
											href="<spring:url value='/admin/validation/pdctranmt.html' />">PD Set Opening</a>
										</li>
							<li class="current"><a 
											href="<spring:url value='/admin/validation/pdctransfer.html' />">PD Process Entery</a>
										</li>
										
							<li class="current"><a 
											href="<spring:url value='/admin/validation/empPdProduction.html' />">PD Production</a>
										</li>
										
							<li class="current"><a	
							            href="<spring:url value='/admin/validation/pdMetalAddition.html' />"> PD Metal
													Addition</a></li>
							<li class="current"><a	
							            href="<spring:url value='/admin/validation/pdMetalDeduction.html' />"> PD Metal
													Deduction</a></li>
													
							<li class="current"><a	
							            href="<spring:url value='/admin/validation/pdSearch.html' />"> PD Search</a></li>						
							
						</ul></li>
						



					<li class="current"><a href="javascript:void(0)">Transactions</a>
						<ul>

								<li class="current"><a href="javascript:void(0)">Diamond</a>
								  	<ul>
								  	
								  		<li class="current"><a
											href="<spring:url value='/admin/validation/diamondBaggingOld.html' />">Diamond Bagging Old</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/diamondBagging.html' />">Diamond Bagging</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/diamondChanging.html' />">Diamond
												Changing</a></li>
												
										<li class="current"><a
											href="<spring:url value='/admin/validation/diamondAllocationAgainstOrder.html' />">Diamond
												Allocation Against Order</a></li>
														
										<li class="current"><a
												href="<spring:url value='/admin/validation/readyBag.html' />">ReadyBag Issue</a></li>
										
										<li class="current"><a
												href="<spring:url value='/admin/validation/newReadyBagIssue.html' />">New ReadyBag Issue</a></li>
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/stoneInwardMt.html' />">Stone
												Inward</a></li>
												
									<li class="current"><a
											href="<spring:url value='/admin/validation/stoneOutwardMt.html' />">Stone
												Outward</a></li>			
												
										<li class="current"><a
											href="<spring:url value='/admin/validation/stoneConversion.html' />">Stone Conversion</a></li>		
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/stoneTransactions.html' />">Stone Transaction</a></li>											
												
												
										<li class="current"><a
											href="<spring:url value='/admin/validation/stnBflMt.html' />">Broken Fallen Adjustment</a></li>
										<li class="current"><a
											href="<spring:url value='/admin/validation/diamondRateChange.html' />">Diamond Rate Change On Bag</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/diamondRateStockChange.html' />">Diamond Rate Change In Stock </a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/stoneAdjustment.html' />">Diamond Stock Adjustment </a></li>		
											
										<li class="current"><a
												href="<spring:url value='/admin/validation/meltStnRec.html' />">Melting Stone Received
													</a></li>	
								  			
								  	</ul></li>



								
								<li class="current"><a href="javascript:void(0)">Component</a>
								  	<ul>
										<li class="current"><a
											href="<spring:url value='/admin/validation/compInwardMt.html' />">Component
											Inward</a></li>
	
										<li class="current"><a
											href="<spring:url value='/admin/validation/compOutwardMt.html' />">Component
											Outward</a></li>

											
										<li class="current"><a
											href="<spring:url value='/admin/validation/componentAddition.html' />">Component
												Addition</a></li>
										<li class="current"><a
											href="<spring:url value='/admin/validation/componentDeduction.html' />">Component
												Deduction</a></li>
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/componentDeductionDetails.html' />">Component
												Deduction Details</a></li>
														
												<li class="current"><a
											href="<spring:url value='/admin/validation/multiComponentAddition.html' />">Multicomponent
												Addition</a></li>
										<li class="current"><a
											href="<spring:url value='/admin/validation/multiComponentDeduction.html' />">Multicomponent
												Deduction</a></li>		
										
												
												
												
										<li class="current"><a
											href="<spring:url value='/admin/validation/componentTransaction.html' />">Component Loose
											Transaction</a></li>
										<li class="current"><a
											href="<spring:url value='/admin/validation/makingMt.html' />">Making
											</a></li>
										
								</ul></li>

								

								  	
								  	<li class="current"><a href="javascript:void(0)">Metal</a>
								  		<ul>
								  		
								  			
											<li class="current"><a
												href="<spring:url value='/admin/validation/ktConversionMt.html' />">KT
													Conversion</a></li>
											<li class="current"><a
												href="<spring:url value='/admin/validation/metalAddition.html' />">Metal
													Addition</a></li>
											<li class="current"><a
												href="<spring:url value='/admin/validation/metalDeduction.html' />">Metal
													Deduction</a></li>
											<li class="current"><a
												href="<spring:url value='/admin/validation/metalInwardMt.html' />">Metal
													Inward</a></li>
													
													<li class="current"><a
												href="<spring:url value='/admin/validation/metalOutwardMt.html' />">Metal
													Outward</a></li>
											<li class="current"><a
												href="<spring:url value='/admin/validation/metalTransaction.html' />">Metal Loose
													Transaction</a></li>
											<li class="current"><a
												href="<spring:url value='/admin/validation/meltingMt.html' />">Melting
													</a></li>
											<li class="current"><a
												href="<spring:url value='/admin/validation/dustMt.html' />">Dust Collection & Recovery
													</a></li>
								  		
								  		</ul></li>
								  		
 									<li class="current"><a href="javascript:void(0)">Casting</a>
								<ul>
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/castingMt.html' />">Casting
											Entry </a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/castingDt.html' />">Casting
											Bag & Component </a></li>
									
								</ul></li>
											
								  		
								  	<li class="current"><a href="javascript:void(0)">Transfer</a>
								  		<ul>
								  			
								  			<li class="current"><a
												href="<spring:url value='/admin/validation/transfer.html' />">Process Entry</a></li>
											
											<li class="current"><a
												href="<spring:url value='/admin/validation/multiBagTransfer.html' />">MultiBag Transfer</a></li>
												
											
											<li class="current"><a
												href="<spring:url value='/admin/validation/trfForReadybagIss.html' />">Transfer For ReadyBag Issue</a></li>		
											
											<li class="current"><a
												href="<spring:url value='/admin/validation/pendingApproval.html' />">Pending Approval</a></li>	
											
												
												<li class="current"><a
												href="<spring:url value='/admin/validation/voucherTrfMt.html' />">Voucher Transfer</a></li>
												
											<li class="current"><a
												href="<spring:url value='/admin/validation/lossBook.html' />">Loss Book</a></li>	
												
											<li class="current"><a
												href="<spring:url value='/admin/validation/bagSearch.html' />">Bag Search</a></li>	
												
											<li class="current"><a
												href="<spring:url value='/admin/validation/bagRollback.html' />">Bag Rollback</a></li>		
												
											
													<li class="current"><a 
											href="<spring:url value='/admin/validation/rejectionPiecesEntry.html' />">Rejection Pieces Entry</a>
										</li>	
												
											 <li class="current"><a
												href="<spring:url value='/admin/validation/bagHistory.html' />">Bag History</a></li> 	
								  			
								  			
								  		</ul></li>
								  		
								  		
								  			<li class="current"><a href="javascript:void(0)">Purchase</a>
								  		<ul>
								  		
								  		<li class="current"><a
												href="<spring:url value='/admin/validation/metalPurchaseMt.html' />">Metal Purchase</a></li>	
								  									  		
								  	
								  	<li class="current"><a
												href="<spring:url value='/admin/validation/stonePurchaseMt.html' />">Stone Purchase</a></li>
												
									<li class="current"><a
												href="<spring:url value='/admin/validation/componentPurchaseMt.html' />">Component Purchase</a></li>
												
													
																				  		
								  		</ul>
								  		</li>
								  		
								  		<li class="current"><a href="javascript:void(0)">Job Work</a>
								  		<ul>
								  		
								  		<li class="current"><a
												href="<spring:url value='/admin/validation/jobIssMt.html' />">Job Work Issue</a></li>	
								  		
								  		<li class="current"><a
												href="<spring:url value='/admin/validation/jobRecMt.html' />">Job Work Receive</a></li>	
								  									  		
								  		</ul>
								  		</li>
								  		
								  		
								  		
								  		
								    <li class="current"><a href="javascript:void(0)">Worker Production</a>
								  		<ul>
								  				<li class="current"><a
													href="<spring:url value='/admin/validation/empPcsProduction.html' />">Employee Pcs Production</a></li>
												<li class="current"><a
													href="<spring:url value='/admin/validation/empStoneProduction.html' />">Employee Stone Production</a></li>
								  			
								  		</ul>
								  	</li>



						</ul></li>

					<li class="current"><a href="javascript:void(0)">Costing</a>
						<ul>
							<li class="current"><a
								href="<spring:url value='/admin/validation/costingMt.html' />">Export Costing
							</a></li>
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/costingMtNew.html' />">Export Costing
							</a></li>
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/costingTransfer.html' />">Costing Transfer
							</a></li>
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/vaddMt.html' />">Value Addition
							</a></li>
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/exportClose.html' />">Export Close
							</a></li>
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/costingJobMt.html' />">Job Costing 
							</a></li>
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/costingTrfExcel.html' />">Costing To Excel 
							</a></li>
							
						</ul>
					</li>




					<li class="current"><a href="javascript:void(0)">Reports</a>
						<ul>
						
							 <li class="current"><a href="javascript:void(0)">Design Reports</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/designCatlog.html' />">Design Catlog </a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/bestSeller.html' />">Best Sales Report </a></li>	
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/designBioData.html' />">Design BioData </a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/emailConceptReport.html' />">Email Concept Report </a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/designExcel.html' />">Design To Excel </a></li>		
								</ul>
						  	</li>
						  	
						  	
						  	<li class="current"><a href="javascript:void(0)">Quotation Reports</a>
									<ul>
									
									<li class="current"><a
											href="<spring:url value='/admin/validation/quotSheet.html' />"> Quotation Sheet </a></li>	
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/quotDiaRate.html' />">Quotation Diamond Rate</a></li>	
																		
										<li class="current"><a
											href="<spring:url value='/admin/validation/quotationReport.html' />"> Quotation </a></li>	
								
										<li class="current"><a
											href="<spring:url value='/admin/validation/preCosting.html' />"> Pre Costing</a></li>	
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/quotationCatalog.html' />"> Quotation Catalog</a></li>
										<li class="current"><a
											href="<spring:url value='/admin/validation/quotationCatalogReport.html' />"> Quotation Catalog Report</a></li>	
										<li class="current"><a
										href="<spring:url value='/admin/validation/groupDiamondAvgRate.html' />">Group Wise Diamond Stock With Avg Rate</a></li>	
											
									</ul>
								</li>
						
		
							<li class="current"><a href="javascript:void(0)">Order
									Reports</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/orderSheet.html' />">Order
											Sheet Reports </a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/orderCatalog.html' />">Order
											Catalog </a></li>
											
											
									<li class="current"><a
										href="<spring:url value='/admin/validation/orderMetalStyleWiseReq.html' />">Order Metal-Style Wise Requirement</a></li>
											
									<li class="current"><a
										href="<spring:url value='/admin/validation/orderMetalSummaryReq.html' />">Order Metal-Summary Requirement </a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/orderStoneReq.html' />">Order Stone Requirement </a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/orderComponentReq.html' />">Order Component Requirement </a></li>
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/ordDeptAndStyleWise.html' />">Department & Style Wise(Order Status)</a></li>
									
									
								</ul></li>
								<li class="current"><a
								href="<spring:url value='/admin/validation/bagPrint.html' />">Bag Print </a></li>	
								
							
									
						   <li class="current"><a href="javascript:void(0)">Department Reports</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/departmentStock.html' />">Department Stock Reports </a></li>
										
										<li class="current"><a
										href="<spring:url value='/admin/validation/deptWiseSummary.html' />">Department Wise Summary Reports </a></li>
										
										
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/deptExportToExcel.html' />">Export To Excel Department Stock</a></li>	
										
										
											<li class="current"><a
										href="<spring:url value='/admin/validation/clientDeptInOut.html' />">Client Wise Department In Out </a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/departmentIssueReport.html' />">Department Issue Reports </a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/departmentReceiveReport.html' />">Department Receive Reports </a></li>
										
									<li class="current"><a
											href="<spring:url value='/admin/validation/bagWiseLoss.html' />">Bag Wise Loss Report</a></li>
											
									<li class="current"><a
											href="<spring:url value='/admin/validation/lossRepo.html' />">Department Wise Loss Report</a></li>
											
											
									<li class="current"><a
											href="<spring:url value='/admin/validation/deptWiseLoss.html' />">Department Wise Style Wise Loss Report</a></li>			
											
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/pcsProduction.html' />">Piece Production</a></li>
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/prodDeptWiseReport.html' />">Production Summary</a></li>
			
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/WorkerWiseLossAndDust.html' />">Worker Wise Loss & Dust Report</a></li>
											
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/lossDustRecoveryReport.html' />">Loss & Dust Recovery Report</a></li>
											
											
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/pcsProductionSummary.html' />">Piece Production Summary</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/stoneProduction.html' />">Stone Production</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/stoneProductionSummary.html' />">Stone Production Summary</a></li>
									
								</ul>
						  </li>
							
							

							<li class="current"><a href="javascript:void(0)">Stock Reports</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/metalStock.html' />">Metal
											Stock </a></li>
									<li class="current"><a
										href="<spring:url value='/admin/validation/componentStock.html' />">Component
											Stock </a></li>
								</ul>
						   </li>
						   
						   
						   <li class="current"><a href="javascript:void(0)">Component Reports</a>
								<ul>
								
							
								<li class="current"><a
										href="<spring:url value='/admin/validation/compInwardReport.html' />">Component
											Inward Report </a></li>								
								
								<li class="current"><a
										href="<spring:url value='/admin/validation/compOutwardReport.html' />">Component
											Outward Report </a></li>								
								
								
									<li class="current"><a
										href="<spring:url value='/admin/validation/componentIssueReport.html' />">Component
											Issue </a></li>
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/componentReceiveReport.html' />">Component
											Receive </a></li>
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/componentStock.html' />">Component
											Stock </a></li>
											
											<li class="current"><a
										href="<spring:url value='/admin/validation/makingReport.html' />">Making Report</a></li>
										
											<li class="current"><a
										href="<spring:url value='/admin/validation/exportWiseFinding.html' />">Export Finding Report</a></li>
										
										
								</ul>
						   </li>
						   
						   
						   <li class="current"><a href="javascript:void(0)">Metal Reports</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/metalInwardReport.html' />">Metal
											Inward Report </a></li>
								
									<li class="current"><a
										href="<spring:url value='/admin/validation/metalIssueReport.html' />">Metal
											Issue </a></li>
											
											<li class="current"><a
										href="<spring:url value='/admin/validation/metalAdditionReport.html' />">Metal
											Addition </a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/metalDeductionReport.html' />">Metal
											Deduction </a></li>		
											
									<li class="current"><a
										href="<spring:url value='/admin/validation/metalReceiveReport.html' />">Metal
											Receive </a></li>
								
									<li class="current"><a
										href="<spring:url value='/admin/validation/metalStock.html' />">Metal
											Stock </a></li>
											
									<li class="current"><a
										href="<spring:url value='/admin/validation/netLoss.html' />">Factory Net Loss Report</a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/meltingdetail.html' />">Melting Report</a></li>
										
										
									
								</ul>
						   </li>
						   
			
						
							<li class="current"><a href="javascript:void(0)">Diamond
									Reports</a>
								<ul>
								<li class="current"><a
										href="<spring:url value='/admin/validation/stoneInwardReport.html' />">Stone Inward Report</a></li>	
								
									<li class="current"><a
										href="<spring:url value='/admin/validation/bagging.html' />">Bagging
											Report </a></li>
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/baggingSummary.html' />">Bagging
											Summary Report </a></li>
									
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/readyBagReport.html' />">ReadyBag Stock Report</a></li>
										
										<li class="current"><a
										href="<spring:url value='/admin/validation/readyBagIssueReport.html' />">Ready Bag Issue Report</a></li>
										
										<li class="current"><a
										href="<spring:url value='/admin/validation/readyBagSummary.html' />">ReadyBag Summary</a></li>
									
											
									<li class="current"><a
										href="<spring:url value='/admin/validation/changingReceive.html' />">Changing Receive </a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/changingReceiveSummary.html' />">Changing Receive Summary</a></li>
									
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/changingIssue.html' />">Changing Issue </a></li>
										
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/changingIssueSummary.html' />">Changing Issue Summary </a></li>
									
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/invoiceDiamond.html' />">Invoice Wise Diamond Stock</a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/shapeDiamond.html' />">Shape Wise Diamond Stock</a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/brokenRep.html' />">Broken Report</a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/brokenSummaryRep.html' />">Broken Summary Report</a></li>
											
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/fallenRep.html' />">Fallen Report</a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/fallenSummaryRep.html' />">Fallen Summary Report</a></li>	
									
									<li class="current"><a
										href="<spring:url value='/admin/validation/diamondStock.html' />">Diamond Stock Report</a></li>
										
										
										<li class="current"><a
										href="<spring:url value='/admin/validation/diamondOpCl.html' />">Diamond Opening and Closing</a></li>
										
										<li class="current"><a
										href="<spring:url value='/admin/validation/groupWiseDiamond.html' />">Group Wise Diamond Stock</a></li>
								
								<li class="current"><a
										href="<spring:url value='/admin/validation/diamondOpClGrp.html' />">Group Wise Diamond Opening and Closing</a></li>
										
										
										<li class="current"><a
										href="<spring:url value='/admin/validation/changingReport.html' />">Changing Report (Issue,Return,Broken,Fallen)</a></li>
										
										<li class="current"><a
										href="<spring:url value='/admin/validation/stoneConversionRep.html' />">Stone Conversion Report</a></li>
										
										<li class="current"><a
										href="<spring:url value='/admin/validation/diaBalanceSizeWise.html' />">Diamond Balance Report</a></li>		
								
									<li class="current"><a
										href="<spring:url value='/admin/validation/consumptionReport.html' />">Consumption Report</a></li>		
								
								<li class="current"><a
										href="<spring:url value='/admin/validation/meltingStnRec.html' />">Melting Stone Receive Report</a></li>
										
								<li class="current"><a
										href="<spring:url value='/admin/validation/orderwisediastock.html' />">Order Wise Diamond Stock Report</a></li>		
								
										
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/groupDiamondRate.html' />">Group Wise Diamond Stock With Rate</a></li>
																		
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/diamondConsumption.html' />">Diamond Consumption</a></li>
										
									<li class="current"><a
										href="<spring:url value='/admin/validation/diamondCustom.html' />">Diamond Custom Report</a></li>
										
								<li class="current"><a
										href="<spring:url value='/admin/validation/diamondInwardStatus.html' />">Diamond Inward Status</a></li>			
											
									<li class="current"><a
										href="<spring:url value='/admin/validation/diamondAdjRep.html' />">Stone Adjustment Report</a></li>	
										
								</ul></li>
								
								
								
								<li class="current"><a
										href="<spring:url value='/admin/validation/casting.html' />">Casting
											Report </a></li>
								
								
								<li class="current"><a href="javascript:void(0)">Order Status</a>
								<ul>
									<li class="current"><a
										href="<spring:url value='/admin/validation/ordDeptAndStyleWise.html' />">Department & Style Wise</a></li>
											
								</ul></li>
								
								
								
								
								
								
								
								<li class="current"><a href="javascript:void(0)">Wip Reports</a>
									<ul>
									
									<li class="current"><a
											href="<spring:url value='/admin/validation/wipStatus.html' />">W.I.P. Status</a></li>
											
									<li class="current"><a
											href="<spring:url value='/admin/validation/wipStatusReport.html' />">W.I.P. Status Report</a></li>
											
											
									<li class="current"><a
											href="<spring:url value='/admin/validation/wipSummary.html' />">What Is Where(WIP)</a></li>				
								
								<li class="current"><a
											href="<spring:url value='/admin/validation/wipStyle.html' />">WIP Style</a></li>
											
									
											
									</ul>
								</li>
								
								
								
								
								
								<li class="current"><a href="javascript:void(0)">PDC Reports</a>
									<ul>
									
										<li class="current"><a
											href="<spring:url value='/admin/validation/pdDeptStock.html' />"> Department Stock </a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/pdProduction.html' />"> PD Production </a></li>	
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/pdProductionSummary.html' />"> PD Production Summary</a></li>		
											
									</ul>
								</li>
								
								
								
								
								
								
						</ul></li>
						
						
						
						
						<li class="current"><a href="javascript:void(0)">Export Reports</a>
							<ul>
							
								 <li class="current"><a href="javascript:void(0)">Value Addition Reports</a>
									<ul>
										<li class="current"><a
											href="<spring:url value='/admin/validation/valueAddition.html' />">Value Addition </a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/valueAdditionInvoice.html' />">Value Addition Invoice</a></li>	
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/hmInvoice.html' />">HM Invoice </a></li>
											
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/plainJewelleryCosting.html' />">Plain Costing Report</a></li>	
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/customProformaB.html' />">Custom Proforma B</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/metalAdjustment.html' />">Metal Adjustment</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/compAdjustment.html' />">Comp Adjustment</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/exportMetalAdjustment.html' />">Export Metal Adjustment</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/exportCompAdjustment.html' />">Export Comp Adjustment</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/exportDiamondAdjustment.html' />">Export Dia Adjustment</a></li>		
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/vAdditionAnnexure.html' />">Value Addition Annexure</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/valueAdditionSummary.html' />">Value Addition Summary</a></li>
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/valueAdditionDiaInSummary.html' />">Value Add Dia In Summary</a></li>
											
											
											
									</ul>
						  		</li>
							
							
							<li class="current"><a
											href="<spring:url value='/admin/validation/packingList.html' />">Packing List</a></li>
							
										<li class="current"><a
											href="<spring:url value='/admin/validation/costSheet.html' />">Cost Sheet</a></li>
									
									<li class="current"><a
											href="<spring:url value='/admin/validation/exportWtDiff.html' />">Export Weight Diffrence Report</a></li>
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/exportDiaDetails.html' />">Export Diamond Statement</a></li>
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/exportDiaStatementII.html' />">Export Diamond Statement II</a></li>
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/vAddExportDiaDt.html' />">Value Addition Dia Details </a></li>
										
										
										
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/costingStickerFl.html' />">Costing Sticker</a></li>	
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/exportDiaRate.html' />">Export Diamond Rate</a></li>	
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/exportGoldRate.html' />">Export Gold Rate</a></li>
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/exportPackingList.html' />">Export to Excel Costing</a></li>
											
											<li class="current"><a
											href="<spring:url value='/admin/validation/exportSalesSummary.html' />">Sales Summary</a></li>	

											
										<li class="current"><a
											href="<spring:url value='/admin/validation/costSheetSummary.html' />">Cost Sheet Summary</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/costingSummary.html' />">Costing Summary</a></li>
												
										<li class="current"><a
											href="<spring:url value='/admin/validation/annexure.html' />">Annexure </a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/annexureNoCost.html' />">Annexure Without Cost </a></li>	
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/hmannexureNocost.html' />">HM Annexure</a></li>	
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/costingTag.html' />">Costing Tag </a></li>	
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/costingSticker.html' />">Costing Sticker </a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/hmcostingSticker.html' />">Hm Costing Sticker </a></li>	
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/nyCostSheet.html' />">NY CostSheet </a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/tibCatalog.html' />">TIB Catalog </a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/exportClientCatalog.html' />">Export Client Catalog</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/dateWiseExportClientCatalog.html' />">Date Wise Export Client Catalog</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/webUpdateCatalog.html' />">Web Updation Catalog</a></li>	
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/exportJobCatalog.html' />">Export Job Catalog</a></li>
											
											
								 <li class="current"><a href="javascript:void(0)">Job Costing</a>
									<ul>
										<li class="current"><a
											href="<spring:url value='/admin/validation/jobAnnexure.html' />">Job Annexure </a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/jobCostingTag.html' />">Job Costing Tag</a></li>	
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/jobCostSheet.html' />">Job Cost Sheet</a></li>
											
											
									</ul>
						  		</li>
											
											
													
											
							
							</ul>
						</li>
						
						
						
				
				
				<li class="current"><a href="javascript:void(0)">MIS</a>
					<ul>
						
								<li class="current"><a href="javascript:void(0)">Reports</a>
									<ul>
									<li class="current"><a
											href="<spring:url value='/admin/validation/jobNoCatalog.html' />">Job No Catalog</a></li>
										
											
										
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/departmentWiseLossCatalog.html' />">Department Wise Loss Catalog</a></li>	
											
										
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/factoryStock.html' />">Factory Stock</a></li>
										
										<li class="current"><a
											href="<spring:url value='/admin/validation/factoryStockWithValue.html' />">Factory Stock With Value</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/factoryStockDateWise.html' />">Factory Stock Date Wise</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/factoryStockWithValueDateWise.html' />">Factory Stock With Value Date Wise</a></li>
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/wip.html' />">Work In Process(WIP)</a></li>
											
											
											
										<li class="current"><a
											href="<spring:url value='/admin/validation/wipEmail.html' />">Work In Process Email</a></li>	
										
										
								</ul>
							</li>
								
								<li class="current"><a
									href="<spring:url value='/admin/validation/generalSearch.html' />">General Search</a>
								</li>			

					</ul>
				</li>
				
				
		

					<li class="current"><a href="javascript:void(0)">Security</a>
						<ul>
							
							<li class="current"><a
								href="<spring:url value='/admin/validation/userDeptTrfRight.html' />">Transfer
									Rights</a></li>
									
							<li class="current"><a
								href="<spring:url value='/admin/validation/locationRights.html' />">Location
									Rights</a></li>		
									
									
							<li class="current"><a
								href="<spring:url value='/admin/validation/users.html' />">Users</a></li>
								
							<li class="current"><a
								href="<spring:url value='/admin/validation/roleMast.html' />">Role Master</a></li>		
								
							<li class="current"><a
								href="<spring:url value='/admin/validation/userRole.html' />">User Role</a></li>		
								
							<li class="current"><a
								href="<spring:url value='/admin/validation/roleRights.html' />">Role Rights</a></li>
								
							<li class="current"><a
								href="<spring:url value='/admin/validation/emailConfig.html' />">Email Config</a></li>
								
							<li class="current"><a
								href="<spring:url value='/admin/validation/userLoginSecurity.html' />">User Login Security</a></li>	
								
							<li class="current"><a
								href="<spring:url value='/admin/validation/dataBackup.html' />">Data Backup</a></li>	
						

						</ul>
					</li>
					
					<li class="current"><a
												href="<spring:url value='/admin/validation/bagSearch.html' />">Bag Search</a></li>	
											

					<li><a href="<spring:url value='/jewels/logout' />">Logout</a></li> --%>
					
				</ul>
				
				
				
			</div>
		</c:if>

		<br /> <br /> <br />
		<tiles:insertAttribute name="body" />

		<br>
		<center>
			<tiles:insertAttribute name="footer" />
		</center>
	</div>
</body>
</html>
<script type="text/javascript">


$(document).ready(function(){  
	
	
	if('${view}'==='loginDefault'){
			 localStorage.setItem('userName', '${userName}');
	}
	

	
	if('${view}'==='login'){
		 $('#example').html('');
	 localStorage.removeItem('menudata');
	 localStorage.removeItem('userName');
	}else{
		
			//alert('${view}');
	
			
			if(localStorage.getItem('menudata') == null && '${view}'!='loginFailedSession'){
				popMenus();
				
			}else{
						
				$('#example').append(localStorage.getItem('menudata'));
				
				
			} 
			
	
		
		
		

		
	}
	
 	
	
	
});


function popMenus(){
	 $('#example').html('');
	 localStorage.removeItem('menudata');
    $.ajax({
    		url:"/jewels/admin/menuHeading.html",
    		type:"GET",
    		success:function(data){
    		
    			
    			 localStorage.setItem('menudata', data);
    		 
    		
    			$('#example').append(data);
    			
    		//	window.location.href = "/jewels/secure/logiinDefault.html";
    			 
    			
    			
    		}
    	});
  }
</script>




