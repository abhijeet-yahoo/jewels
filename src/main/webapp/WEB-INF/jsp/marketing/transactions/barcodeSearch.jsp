<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Barcode Search</span>
	</div>
	
	<div class="panel body">
	
	<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
			
	<%--  <form:form commandName="stockMt" id="stockMtFormId"
	 		action="/jewels/marketing/transactions/barcodeSearch/loadData.html"
			cssClass="form-horizontal bagMtForm"> --%>
		
		
		<div class="row">
				<div class="col-xs-12">	
		
				<div class="col-md-10">
					<label for="inputLabel3" class="col-sm-2 control-label">Barcode :</label>
						<div class="col-sm-4">
					   		<%-- <form:input path="barcode" cssClass="form-control" onchange="javascript:getBarcodeList();"/> --%>
							<input type="text" id="barcode" name="barcode" class="form-control" onchange="javascript:getBarcodeList();"/>
						</div>
						</div>
						
						<div id="odImgDivId" class="col-md-2 center-block">
					<!-- <div style="height:5px">&nbsp;</div> -->
					<div class="panel panel-primary" style="width:100%; height:125px">
						<div class="panel-body">
							<div style="width:100%; height:100px">
								<div class="row center-block">
									<span id='styleImgId'></span> 									
									<div id="tempImgDivId">
										
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
		
	
		<%-- </form:form> --%>
	
	
	
	
	
	
	
		
<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;"> Details</span>
		</div>
	</div>
	

				
				
				<div class="panel-body">

			<div class="row">
						
						<div class="col-md-12">
						


				<div class="table-responsive">
 						<table id="barcodeHistoryTblId" data-toggle="table" data-height="300">
						<thead>
							<tr>
								<th data-field="tranDate" data-sortable="false">Date</th>
								<th data-field="barcode" data-sortable="false">Barcode</th>
								<th data-field="Location" data-align="left">Location</th>
								<th data-field="orderParty" data-align="left">Order Party</th>
								<th data-field="orderNo" data-sortable="false">Order No</th>
								<th data-field="bagNm" data-sortable="false">Bag No</th>
								<th data-field="mainStyle" data-sortable="false">Style No</th>
								<th data-field="ktCol" data-sortable="false">Kt-Color</th>
								<th data-field="grossWt" data-sortable="false">Gross Wt</th>
								<th data-field="netWt" data-sortable="false">Net Wt</th>
								<th data-field="diaPcs" data-sortable="false">Dia Pcs</th>
								<th data-field="diaWt" data-sortable="false">Dia Wt</th>
								<th data-field="colPcs" data-sortable="false">Col Pcs</th>
								<th data-field="colWt" data-sortable="false">Col Wt</th>
								<th data-field="currStatus" data-sortable="false">Status</th>
								<th data-field="vouNo" data-sortable="false">Voucher No</th>
								<th data-field="vouDate" data-sortable="false">Voucher Date</th>
								<th data-field="vouParty" data-align="left">Voucher Party</th>
								<th data-field="userName" data-sortable="false">User</th>
								
								
							</tr>
						</thead>
					</table>
				<div class="row">&nbsp;
				</div>	
									 
 			

				</div>
				
				
				
				
</div>

			


			</div>
			
			



	
				 
		</div>
				
				
				
				
				
				
		
<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;"> Stone Details</span>
		</div>
	</div>
		
				<div class="panel-body">

			<div class="row">
		
				<div class="table-responsive">
 						<table id="barcodeHistoryStnTblId" data-toggle="table" data-height="250">
						<thead>
							<tr>
								<th data-field="partNm" data-sortable="true">Part Name</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
									<th data-field="quality" data-sortable="false">Quality</th>
									<th data-field="size" data-sortable="false">Size</th>
									<th data-field="sieve" data-sortable="false">Sieve</th>
									<th data-field="sizeGroup" data-sortable="false">SizeGroup</th>
									<th data-field="stone" data-sortable="false">Stone</th>
									<th data-field="carat" data-sortable="false">Carat</th>
									<!-- <th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="stoneValue" data-sortable="false">Stn Value</th> -->
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SetType</th>
								
							</tr>
						</thead>
					</table>
				<div class="row">&nbsp;
				</div>	
									 
 			

				</div>
		
			</div>
			
			
		</div>	
				
				
				
				
				
				
<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;"> Component Details</span>
		</div>
	</div>
		
				<div class="panel-body">

			<div class="row">
		
				<div class="table-responsive">
 						<table id="barcodeHistoryCompTblId" data-toggle="table" data-height="350">
						<thead>
							<tr>
								<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metal Wt</th>
									<th data-field="compPcs" data-sortable="false">Qty</th>
									
								
								
							</tr>
						</thead>
					</table>
				<div class="row">&nbsp;
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
			
$("#barcode")
.autocomplete(
	{
		source : '<spring:url value='/marketing/transactions/barcodeSearch/autoFill/list.html' />',
		minLength : 2
	})

});


function popBarcodeList(){

	$("#barcodeHistoryTblId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/marketing/transactions/stockMt/barcodeHistory.html?barcode="+$('#barcode').val(),
			});
}


function popBarcodeStoneList(){

	$("#barcodeHistoryStnTblId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/marketing/transactions/stockMt/barcodeStoneList.html?barcode="+$('#barcode').val(),
			});
}


function popBarcodeComponentList(){

	$("#barcodeHistoryCompTblId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/marketing/transactions/stockMt/barcodeComponentList.html?barcode="+$('#barcode').val(),
			});
}
	
function getBarcodeList(){

	

	if($('#barcode').val() !=''){
		
		 popBarcodeList();
		 popBarcodeStoneList();
		 popBarcodeComponentList();
		
		
	}else{
		displayMsg(event, this, 'Enter Barcode To Add');
	}
}


$('#barcodeHistoryTblId').bootstrapTable({}).on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#barcodeHistoryTblId").bootstrapTable('getData'));
			var tempDefaultImage;
			var imgFlg = false;
			$.each(JSON.parse(data), function(idx, obj) {
				tempDefaultImage = obj.image;
			});
			
			$('#tempImgDivId').empty();
			var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
			$('#tempImgDivId').append(tempDiv);
			
		});

</script>
	
	
	<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
	
	