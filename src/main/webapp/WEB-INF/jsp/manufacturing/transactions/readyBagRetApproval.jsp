<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<div class="panel panel-primary">
	
	
	<div class="panel-heading" id="barcodeDivId">
		<span style="font-size: 18px;">Ready Bag Return Approval</span>
	</div>
	
	<div class="panel-body">

			<div class="row">
						
						<div class="col-md-10">
						


				<div class="table-responsive">
 					<table id="tblId" data-toggle="table" data-search="true" data-toolbar="toolbarDt" data-maintain-meta-data="true" data-height="450"  >
					<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="partyCode" data-sortable="false" class="span5">Party</th>
								<th data-field="orderNo">Order No</th>
								<th data-field="styleNo">Style No</th>
								<th data-field="purity" data-sortable="false">Kt</th>
								<th data-field="color" data-sortable="false">Color</th>
								<th data-field="bagNm" data-sortable="false">Bag No</th>
								<th data-field="bagQty" data-sortable="false">Bag Qty</th>
								<th data-field="stone" data-sortable="false">Stone</th>
								<th data-field="carat" data-sortable="false">carat</th>
									
									
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
								<input type="button" value="Approve" onclick="javascript:popApproveReadyBagRet()" id="appBtnId" class="btn btn-primary">
							</div>
						</div>

</div>


			</div>
			
			



	
				 
		</div>
		<input type="hidden" name="vBagIds" id="vBagIds" />
	</div>		
	
	

<script type="text/javascript">


$(document)
.ready(
		function(e) {
			
			
			popBagDt();

			
			
		});






function popBagDt(){
		
				
				$("#tblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/readyBagRet/appListing.html"
						}); 
	
} 












$('#tblId').on('check.bs.table', function (e, row) {
	
	 popSelectedQty();
	
});



$('#tblId').on('check-all.bs.table', function (e, row) {
	
	 popSelectedQty();
	
});


$('#tblId').on('uncheck.bs.table', function (e, row) {
	
	 popSelectedQty();
	
	
});



$('#tblId').on('uncheck-all.bs.table', function (e, row) {
	
	 popSelectedQty();
	
});



$('#tblId').bootstrapTable({}).on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#tblId").bootstrapTable('getData'));
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
	
	
	var data = $('#tblId').bootstrapTable('getAllSelections');
	
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
$('#tblId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
			TableRow = $element.attr('data-index');
			var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
			
			$('#tempImgDivId').empty();
			var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
			$('#tempImgDivId').append(tempDiv);
			
		
		});
		
		
		
		
function popApproveReadyBagRet(){
	
	$('#appBtnId').attr("disabled", true);
	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	var data = $('#tblId').bootstrapTable('getAllSelections');
	var ids = $.map(data, function(item) {
		return item.bagId;
	});
	$('#vBagIds').val(ids);
	
	
	$.ajax({
		url : "/jewels/manufacturing/transactions/readyBagRetApprove.html?vBagIds="+$('#vBagIds').val(),
	
		type : "POST",
		success : function(data, textStatus, jqXHR) {
			$.unblockUI();
			
			if (Number(data) == 1) {

				displayInfoMsg(event, this,'Approve sucessfully !');
				
				
			}else{
				
			
				
			
			}			
			popBagDt();
			
		
			$('#appBtnId').attr("disabled", false);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#appBtnId').attr("disabled", false);
			$.unblockUI();

		}

	});
	
	
}		



</script>	
	
	


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />	
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
		