<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<div class="panel panel-primary">
	

	
	
		<div class="panel-heading" >
	
	<div>
	
	<div>
	<label class="col-lg-5 text-left">
				<span style="font-size: 18px;">Stock Details</span>
			</label>
		
	
	</div>
		

			
		<div class="text-right">
		<a class="btn btn-xs btn-info" id="stockPackBackBtnId" style="font-size: 14px" type="button" onclick="javascript:stockPackBackBtn()"><b>Back</b></a>
		</div>	
	
	</div>		
	
	</div>
	
	
	
	<div class="panel-body">

			<div class="row">
						
						<div class="col-md-10">
						


				<div class="table-responsive">
 					<table id="stockPackingTblId" data-toggle="table" data-search="true" data-toolbar="toolbarDt" data-maintain-meta-data="true" data-height="450"  >
					<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="barcode" data-align="left" data-sortable="true" data-filter-control="input">Barcode</th>
								<th data-field="party" data-align="left" data-sortable="true" data-filter-control="input">Party</th>
								<th data-field="orderNo" data-align="left" data-sortable="true" data-filter-control="input">Order</th>
								<th data-field="refNo" data-align="left" data-sortable="true" data-filter-control="input">Ref</th>
								<th data-field="bagNo" data-align="left" data-sortable="true" data-filter-control="input">Bag</th>
								<th data-field="styleNo" data-align="left" data-sortable="true" data-filter-control="input">Style No</th>
								<th data-field="qty" data-align="left" data-sortable="true">Qty</th>
								<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
							
							</tr>
						</thead>


					</table>
				<div class="row">&nbsp;
				</div>	
									 
 				 <div align="right" style="padding-right: 10px; font-weight: bold;">
 				 Total Bags : <input type="text" id="vTotalBags" name="vTotalBags" 	maxlength="7" size="7" disabled="disabled" style="text-align: right;"/> 
						&nbsp;&nbsp;
				Total Qty : <input type="text" id="vTotalQty" name="vTotalQty"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
							&nbsp;&nbsp;
				Total Wt : <input type="text" id="vTotalWt" name="vTotalWt"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
																&nbsp;&nbsp;
				Sel Bags : <input type="text" id="vSelectBags" name="vSelectBags" 	maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
						&nbsp;&nbsp;
				Sel Qty : <input type="text" id="vSelectQty" name="vSelectQty"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
							&nbsp;&nbsp;
			Sel Wt : <input type="text" id="vSelectWt" name="vSelectWt"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>			
 				 </div>

				</div>
				
				
				
				
</div>

			<div id="odImgDivId" class="col-md-2 center-block">
					<div style="height:52px">&nbsp;</div>
					<div class="panel panel-primary" style="width:100%; height:245px">
						<div class="panel-body">
							<div style="width:80%; height:168px">
								<div class="row center-block">
									<span id='styleImgId'></span> 									
									<div id="tempImgDivId">
										
									</div>
								</div>
							</div>
							
							
						</div>
					</div>
					
						<div class="form-group">
							<div>
								<input type="button" value="Transfer" onclick="javascript:popStockTrfToMelting()" id="trfBtnId" class="btn btn-primary">
							</div>
						</div>

</div>


			</div>
			
			



	
				 
		</div>
		<input type="hidden" name="vBarcodeIds" id="vBarcodeIds" />
	</div>		
	
	

<script type="text/javascript">
var stkPackMtId;
var deptId;
$(document)
.ready(
		function(e) {
			
			
			
			
			
		 	if (window.location.href.indexOf('mtid') != -1) {
						var vUrl = window.location.href;
				
						stkPackMtId = vUrl.substring(window.location.href.indexOf('mtid') + 5,window.location.href.indexOf('&locationId'));

						deptId = vUrl.substring(window.location.href.indexOf('&locationId') + 12 );
														
						}
			
			
			popBagDt();

			
			
		});






function popBagDt(){
		
				
				$("#stockPackingTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stockPackingListTransfer/listing.html?deptId="+deptId,
						}); 
	
} 





$('#stockPackingTblId').on('check.bs.table', function (e, row) {
	 popSelectedQty();
});

$('#stockPackingTblId').on('check-all.bs.table', function (e, row) {
	 popSelectedQty();
});

$('#stockPackingTblId').on('uncheck.bs.table', function (e, row) {
	 popSelectedQty();
});

$('#stockMeltTransferTblId').on('uncheck-all.bs.table', function (e, row) {
	 popSelectedQty();
});



$('#stockPackingTblId').bootstrapTable({}).on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#stockPackingTblId").bootstrapTable('getData'));
			var vBagPcs = 0.0;
			var vWt = 0.0;
			var i=0;
			$.each(JSON.parse(data), function(idx, obj) {
				i =i+1;
				vBagPcs		+= Number(obj.qty);
				vWt		+= Number(obj.grossWt);
			});
			
			$('#vTotalBags').val(i);
			$('#vTotalQty').val(vBagPcs.toFixed(2));
			$('#vTotalWt').val(Number(vWt).toFixed(3));
			
			$('#vSelectBags').val(0);
			$('#vSelectQty').val(0);
			$('#vSelectWt').val(0.0);
			
			
		});



function popSelectedQty(){
	
	
	var data = $('#stockPackingTblId').bootstrapTable('getAllSelections');
	
	var vBagPcs = 0.0;
	var vWt = 0.0;
	var i=0;
	$.each(data, function(idx, obj) {
		i =i+1;
		vBagPcs		+= Number(obj.qty);
		vWt		+= Number(obj.grossWt);
	});
	
	$('#vSelectBags').val(i);
	$('#vSelectQty').val(Number(vBagPcs).toFixed(2));
	$('#vSelectWt').val(Number(vWt).toFixed(3));
	
	
}


var TableRow;
$('#stockPackingTblId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
			TableRow = $element.attr('data-index');
			var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
			
			$('#tempImgDivId').empty();
			var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
			$('#tempImgDivId').append(tempDiv);
			
		
		});
		
		
		
		
function popStockTrfToMelting(){
	
	$('#trfBtnId').attr("disabled", true);
	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	var data = $('#stockPackingTblId').bootstrapTable('getAllSelections');
	var ids = $.map(data, function(item) {
		return item.barcode;
	});
	$('#vBarcodeIds').val(ids);
	
	
	$.ajax({
		url : "/jewels/marketing/transactions/stockTransferToPackingList.html?vBarcodeIds="+$('#vBarcodeIds').val()+"&stkPackMtId="+stkPackMtId+"&deptId="+deptId,
	
		type : "POST",
		success : function(data, textStatus, jqXHR) {
			$.unblockUI();
			
			if (Number(data) == 1) {

				displayInfoMsg(event, this,'Transfer sucessfully !');
				
				
			}		
			popBagDt();
			
		
			$('#trfBtnId').attr("disabled", false);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#trfBtnId').attr("disabled", false);
			$.unblockUI();

		}

	});
	
	
}		




function stockPackBackBtn(){
			window.location.href = "/jewels/marketing/transactions/packList/edit/"+stkPackMtId+".html"
	}


</script>	
	
	


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />	
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
		