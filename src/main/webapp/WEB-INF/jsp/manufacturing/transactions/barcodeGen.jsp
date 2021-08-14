<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<div class="panel panel-primary">
	
	
	<div class="panel-heading" id="barcodeDivId">
		<div><!-- <span style="font-size: 18px;">Barcode Generation</span> -->
		<label class="col-lg-5 text-left">
				<span style="font-size: 18px;">Barcode Generation</span>
			</label>
		<div class="text-right">
		<a class="btn btn-xs btn-info" id="fgIssBackBtnId" style="font-size: 14px" type="button" onclick="javascript:barcodeIssBackBtn()"><b>Back</b></a>
		</div>	
		</div>
				
		
	</div>
	
	<div class="panel-body">
	
	
		<div class ="row" id="toolbarDt">
			<div class="col-sm-4" id="barcodeFlgIdtemp" >
							<select class="form-control">
							<option value="">Select Department</option>
							</select>
							
							</div>
			
			</div>

			<div class="row">
						
						<div class="col-md-10">
						


				<div class="table-responsive">
 					<table id="tblId" data-toggle="table" data-search="true" data-toolbar="toolbarDt" data-maintain-meta-data="true" data-height="450"  >
					<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
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
								<input type="button" value="Generate Barcode" onclick="javascript:popGenerateBarcode()" id="barcodeBtnId" class="btn btn-primary">
							</div>
						</div>

</div>


			</div>
			
			



	
				 
		</div>
		<input type="hidden" name="vBagNo" id="vBagNo" />
	</div>		
	
	

<script type="text/javascript">


var barcodeMtId;
$(document)
.ready(
		function(e) {
			
		
			if (window.location.href.indexOf('mtId') != -1) {

							var abcx = window.location.href;
							barcodeMtId = abcx.substring(window.location.href
									.indexOf('mtId') + 5,window.location.href.indexOf('&mOpt'));
							
							vOpt = abcx.substring(window.location.href
									.indexOf('&mOpt') + 6); 
							
						
						}
			
			
			//popBagDt();

			
			
			popdeptBarcodeDeptDropDown(); 

			
			
		});






function popBagDt(){
		
				
				$("#tblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/barcodeGen/listing.html?departmentId="+$('#barcodeFlgId').val(),
								
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
		
		
		
		
function popGenerateBarcode(){
	
	$('#barcodeBtnId').attr("disabled", true);
	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	var data = $('#tblId').bootstrapTable('getAllSelections');
	var ids = $.map(data, function(item) {
		return item.bagNo;
	});
	$('#vBagNo').val(ids);
	
	
	$.ajax({
		url : "/jewels/manufacturing/transactions/generateBarCode.html?vBagNo="+$('#vBagNo').val()+"&barcodeMtId="+barcodeMtId,
	
		type : "POST",
		success : function(data, textStatus, jqXHR) {
			
			
			if (Number(data) == 1) {

				displayInfoMsg(event, this,'Barcode Generated sucessfully !');
				
				
			}else{
				
			
				
			
			}			
			popBagDt();
			$.unblockUI();
		
			$('#barcodeBtnId').attr("disabled", false);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			$('#barcodeBtnId').attr("disabled", false);
			$.unblockUI();

		}

	});
	
	
}		

function barcodeIssBackBtn(){
	

	window.location.href = "/jewels/manufacturing/transactions/barcodeMt/edit/"+barcodeMtId+".html"


}




function popdeptBarcodeDeptDropDown() {

	$.ajax({
		url : '/jewels/manufacturing/transactions/barcode/department/dropDownlist.html',
		type : 'GET',
		success : function(data) {
			
		
			$("#barcodeFlgIdtemp").html(data);
			$('#toolbarDt #barcodeFlgId').chosen();

			
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
		