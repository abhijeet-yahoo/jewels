<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalDashboardFilter.jsp"%>



<style>
  h2 .red-text {
    color: red;
  }
</style>



<div  style="width: 97%; margin: 20px">
	<div>
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" align="left">
				 <span class="col-sm-12 label label-primary" 
					style="font-size: 22px;margin-left:auto; ">DASHBOARD</span>
			</div>
		</div>
	</div>
	
	<div class="container">
	
		<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
	
	
	 <div class="panel-group">
	<div class="row">
	
	
	
	<div class="col-sm-12">
		<div class="col-sm-3">
			 <div class="panel" style="height: 250px;border-color: grey;" >
		  
		       <div class="panel-heading"><span style="font-size: 20px;color:grey">Sales Quotation<hr style="border-color: red;margin-top: 10px;border-width:medium;"></span></div> 
		     
		      <div class="panel-body" style="margin-top: -32px" >
		    <!--  <h3  class="red-text" color=red;>Sales Quotation</h3> -->
		    
			    <h2 id="saleQuotValId"></h2>
			    <h6>Quot Value <a href="#">
          <span class="glyphicon glyphicon-arrow-up"></span>
        </a></h6>
		        <h4 id="saleQuotGrossWtId"></h4>	
		      	 <h6>Gross Wt <a href="#">
          <span class="glyphicon glyphicon-arrow-up"></span>
        </a></h6>
		      	 <h4 id="saleQuotPcsId"></h4>
		      	 <h6>Pcs <a href="#">
			          <span class="glyphicon glyphicon-arrow-up"></span>
			        </a></h6>
		      	
		      	
		       
		    
		      </div>
		    </div>
		
		</div>
		
		
		
		<div class="col-sm-3">
			 <div class="panel" style="height: 250px;border-color: grey;" onclick="javascript:popDashboardModal('Sales_Order');" >
      <!-- <div class="panel-heading"><span style="font-size: 20px;">Sales Order</span></div> -->
       <div class="panel-heading"><span style="font-size: 20px;color:grey">Sales Order<hr style="border-color: blue;margin-top: 10px;border-width:medium;"></span></div> 
      <div class="panel-body" style="margin-top: -32px" >
      			
      			<h2 id="saleOrderOrdValId"></h2>
      			<h6>Order Value <a href="#">
			          <span class="glyphicon glyphicon-arrow-up"></span>
			        </a></h6>
		      	<h4 id="saleOrderGrossWtId"></h4>
		      	<h6>Gross Wt.<a href="#">
			          <span class="glyphicon glyphicon-arrow-up"></span>
			        </a></h6>
		      	<h5 id="saleOrderPcsId"></h5>
		      	<h6>Pcs <a href="#">
			          <span class="glyphicon glyphicon-arrow-up"></span>
			        </a></h6>
	  </div>
    </div>
		
		</div>
		
		
		<div class="col-sm-3">
		<div class="panel" style="height: 250px;border-color: grey;" onclick="javascript:popDashboardModal('Sales_Invoice');">
		<div class="panel-heading"><span style="font-size: 20px;color:grey">Sales Invoice<hr style="border-color:green;margin-top: 10px;border-width:medium;"></span></div> 
		      <!-- <div class="panel-heading"><span style="font-size: 20px;">Sales Invoice</span></div> -->
		      <div class="panel-body" style="margin-top: -32px" >
		      
		      <h2 id="saleInvoiceOrdValId"></h2>
		      <h6>Value <a href="#">
		          <span class="glyphicon glyphicon-arrow-up"></span>
		        </a></h6>	
				<h4 id="saleInvoiceGrossWtId"></h4>
		      	 <h6>Gross Wt. <a href="#">
			          <span class="glyphicon glyphicon-arrow-up"></span>
			        </a></h6>
		      	 <h5 id="saleInvoicePcsId"></h5>
		    	 <h6>Pcs <a href="#">
			          <span class="glyphicon glyphicon-arrow-up"></span>
			        </a></h6>
		      </div>
		    </div>
				
		</div>
		
		
		
			<div class="col-sm-3">
		
	
		 <div class="panel" style="height: 250px;border-color: grey;" onclick="javascript:popDashboardModal('FG_Issue');">
      <!-- <div class="panel-heading"><span style="font-size: 20px;">FG Issue(Factory)</span></div> -->
       <div class="panel-heading"><span style="font-size: 20px;color:grey">FG Issue(Factory)<hr style="border-color: #87CEEB;margin-top: 10px;border-width:medium;"></span></div> 
      <div class="panel-body" style="margin-top: -32px" >
      
      			 <h2 id="fgIssueValId"></h2>
		      <h6>Value<a href="#">
          <span class="glyphicon glyphicon-arrow-up"></span>
        </a></h6>
				<h4 id="fgIssueGrossWtId"></h4>
		      	 <h6>Gross Wt. <a href="#">
          <span class="glyphicon glyphicon-arrow-up"></span>
        </a></h6>
		      	 <h5 id="fgIssuePcsId"></h5>
		    	 <h6>Pcs <a href="#">
          <span class="glyphicon glyphicon-arrow-up"></span>
        </a></h6>
		      	
      </div>
    </div>
		
		</div>
		
	
	
	</div>
	
	
	</div>
	
	
	
	<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
	
	
	<div class="row">
	
	<div class="col-sm-12">
		<div class="col-sm-6">
		
	
		 <div class="panel" style="height: 300px;border-color: grey;">
		     <!--  <div class="panel-heading"><span style="font-size: 20px;">WIP</span></div> -->
		      <div class="panel-heading"><span style="font-size: 20px;color:grey">WIP<hr style="border-color: green;margin-top: 10px;border-width:medium;"></span></div> 
		      <div class="panel-body" style="margin-top: -32px" >
		      	
			      <div class="row">
					<div class="col-xs-12">
						<div>
							<table id="wipDsTblId" data-toggle="table"
								 data-height="230">
								<thead>
									<tr>
									<th data-field="status" data-align="left">Status</th>
									<th data-field="pcs" data-align="left">Pcs</th>
									<th data-field="grossWt" data-align="left">Gross Wt.</th>
									<th data-field="diaWt" data-align="left">Dia Wt</th>
									<th data-field="colWt" data-align="left">Col Wt</th>
									<th data-field="silWt" data-align="left">Sil Wt</th>
									<th data-field="pureWt" data-align="left">Pure Wt</th>
									<th data-field="factoryCostt" data-align="left">Cost</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		      
		      </div>
		    </div>
		
		</div>
		
		
		
	
		
		
		<div class="col-sm-6">
		<div class="panel" style="height: 300px;border-color: grey;" onclick="javascript:popDashboardModal('Diamond_Stock');">
		     <!--  <div class="panel-heading"><span style="font-size: 20px;">Diamond Stock</span></div> -->
		      <div class="panel-heading"><span style="font-size: 20px;color:grey">Diamond Stock<hr style="border-color: red;margin-top: 10px;border-width:medium;"></span></div> 
		      <div class="panel-body" style="margin-top: -32px" >
		      
			    <div class="row">
					<div class="col-xs-12">
						<div>
							<table id="diaStockDsTblId" data-toggle="table"
								 data-height="230">
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
				
		</div>
		
	</div>
	
	
	</div>
	
	
	
	
	
	<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			
			
			<div class="row">
	
	<div class="col-sm-12">
		
		<div class="col-sm-6">
		 <div class="panel" style="height: 300px;border-color: grey;">
		      <div class="panel-heading"><span style="font-size: 20px;color:grey">Finish Goods<hr style="border-color: green;margin-top: 10px;border-width:medium;"></span></div> 
		      <div class="panel-body" style="margin-top: -32px" >
		      	
			      <div class="row">
					<div class="col-xs-12">
						<div>
							<table id="finishGoodsDsTblId" data-toggle="table"
								 data-height="230">
								<thead>
									<tr>
									<th data-field="status" data-align="left">Status</th>
									<th data-field="pcs" data-align="left">Pcs</th>
									<th data-field="grossWt" data-align="left">Gross Wt.</th>
									<th data-field="diaWt" data-align="left">Dia Wt</th>
									<th data-field="colWt" data-align="left">Col Wt</th>
									<th data-field="silWt" data-align="left">Sil Wt</th>
									<th data-field="pureWt" data-align="left">Pure Wt</th>
									<th data-field="factoryCostt" data-align="left">Cost</th>
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
	
		</div>
	
</div>



<script type="text/javascript">

	$(document)
			.ready(
					function(e) {
					
						getSalesOrderData();
						getSalesInvoiceData();
						getDiaStockData();
						getSalesQuatationData();
						getWipStockData();
						getFinishGoodsStockData();
						getFgIssueData();
					});
					
					
					
		function popDashboardModal(opt){
		//	$('#dashboardFilterModal').modal('show');

			window.location.href = "/jewels/manufacturing/masters/dashboardFilter.html?opt="+opt

		}


		function getSalesOrderData(){


			$.ajax({
				url:"/jewels/manufacturing/masters/dashboard/getSalesOrdersData.html",
				type:"GET",
				datatype:"JSON",
				success:function(data){

					 $( "#saleQuotBodyDivId" ).append('<div class="row"><h3>5000000    <span>xxxxx</span></h3>Testt</div>');
					
					  $.each(JSON.parse(data), function(key,value){
						  if(key === 'availableStkId'){
								$("#saleOrderPcsId").append(value);	
							}
						  if(key === 'grossWt'){
								$("#saleOrderGrossWtId").append(value);	
							}
						  if(key === 'orderValue'){
								$("#saleOrderOrdValId").append(value);	
							}	
					        
					    }); 

				
				
				}
			})

			}



		function getSalesInvoiceData(){

			$.ajax({
				url:"/jewels/manufacturing/masters/dashboard/getSalesInvoiceData.html",
				type:"GET",
				datatype:"JSON",
				success:function(data){

					  $.each(JSON.parse(data), function(key,value){
						  if(key === 'pcs'){
								$("#saleInvoicePcsId").append(value);	
							}
						  if(key === 'grossWt'){
								$("#saleInvoiceGrossWtId").append(value);	
							}
						  if(key === 'orderValue'){
								$("#saleInvoiceOrdValId").append(value);	
							}	
					    });
				
					}
				})

			}


		function getSalesQuatationData(){

			$.ajax({
				url:"/jewels/manufacturing/masters/dashboard/getSalesQuotationData.html",
				type:"GET",
				datatype:"JSON",
				success:function(data){

					   $.each(JSON.parse(data), function(key,value){
						  if(key === 'pcs'){
								$("#saleQuotPcsId").append(value);	
							}
						  if(key === 'grossWt'){
								$("#saleQuotGrossWtId").append(value);	
							}
						  if(key === 'quotValue'){
								$("#saleQuotValId").append(value);	
							}	
					    }); 
					}
				})

			}


		function getFgIssueData(){

			$.ajax({
				url:"/jewels/manufacturing/masters/dashboard/getFgIssueData.html",
				type:"GET",
				datatype:"JSON",
				success:function(data){

					   $.each(JSON.parse(data), function(key,value){
						  if(key === 'pcs'){
								$("#fgIssuePcsId").append(value);	
							}
						  if(key === 'grossWt'){
								$("#fgIssueGrossWtId").append(value);	
							}
						  if(key === 'fgValue'){
								$("#fgIssueValId").append(value);	
							}	
					    }); 
					}
				})

			}
		


			function getDiaStockData(){
				$("#diaStockDsTblId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/masters/dashboard/DiamondStock/listing.html"
						
					}); 
			}


			function getWipStockData(){
				$("#wipDsTblId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/masters/dashboard/wipStock/listing.html"
						
					}); 
			}

			function getFinishGoodsStockData(){
				$("#finishGoodsDsTblId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/masters/dashboard/finishGoodsSock/listing.html"
						
					}); 
			}



	</script>	




<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>


<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
