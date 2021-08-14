<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@include file="/WEB-INF/jsp/common/modalAgainstOrder.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalOrder.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>


<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>


<div id="stnInwAgainstOrderDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
	
	<div>
	
	<div id="lblInwardDivId" style="display: none">
	<label class="col-lg-5 text-left">
				<span style="font-size: 18px;">Order Stone Inward</span>
			</label>
		
	
	</div>
		
		<div id="lblOutwardDivId" style="display: none">
	<label class="col-lg-5 text-left">
				<span style="font-size: 18px;">Order Stone Outward</span>
			</label>
		
	
	</div>
			
		<div class="text-right">
		<a class="btn btn-xs btn-info" id="stnInwBackBtnId" style="font-size: 14px" type="button" onclick="javascript:stnInwBackBtn()"><b>Back</b></a>
		</div>	
	
	</div>		
	
	</div>
	
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
	
		<div class="row">
				
				<div class="col-xs-12">
					<div class="form-group col-sm-2">
					<label class="control-label" for="orderNo">Order No.</label>
					<div class="input-group">
					<input type="text" id="orderNo" name="orderNo" class="form-control" onchange="javascript:checkOrderApproval();">
					 <span class="input-group-btn">
					   <button type="button" class="btn btn-default glyphicon glyphicon-list"  onClick="javascript:openOrderList()"></button>
					     </span>
					</div>
					</div>
					
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="orderNo">Client</label>
					<input type="text" id="partyCode" name="partyCode" class="form-control" readonly="readonly">
					
					</div>
			
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="Order Qty">Order Qty</label>
					<input type="text" id="orderQty" name="orderQty" class="form-control" readonly="readonly">
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="inwardQty">Inward</label>
					<input type="text" id="inwardQty" name="inwardQty" class="form-control" readonly="readonly">
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="outwardQty">Outward</label>
					<input type="text" id="outwardQty" name="outwardQty" class="form-control" readonly="readonly">
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="Balance">Balance</label>
					<input type="text" id="balanceQty" name="balanceQty" class="form-control" readonly="readonly">
					
					</div>
					
					</div>	
			
					
			
		
			</div>
			
			
				
			<div class="row">
				
					<div class="col-xs-12">
						<span class="col-lg-12 label label-default text-right"
							style="font-size: 18px;">Order Details</span>
					</div>
				</div>
				
			
			<div class="row">
			
				<div class="col-xs-12">
				
				
				<div class="col-sm-12 table-responsive">				
					<table id="stnInwOrderDtTblId" data-toggle="table" data-toolbar="#toolbarDt"   
					 data-search="true"	data-height="350">
						<thead class="thead-dark">
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="srNo" >Sr No</th>
								<th data-field="style" >Style</th>
								<th data-field="refNo" >Ref No</th>
								<th data-field="item">Item</th>
								<th data-field="ordRef" >Ord Ref</th>
								<th data-field="purity" data-align="left" >KT</th>
								<th data-field="color" data-align="left" >Color</th>
								<th data-field="prodSize" >Prod Size</th>
								<th data-field="ordQty" >Ord Qty</th>
								<th data-field="stnRecQty" >Stn Rec Qty</th>
								<th data-field="stnOutQty" >Stn Out Qty</th>
								<th data-field="bagQty" data-formatter="bagQtyFormatter" data-align="left" >Bag Qty</th>
								
							</tr>
						</thead>
					</table>
				</div>
				
				
					</div>
				
				</div>
		
			
			<div class="row">
			
			
			
		<span style="display:inline-block; width: 1cm;"></span>
					<strong><b>Total Invoice Stone :  </b></strong><input type="text" id="totalStones" name="totalStones" maxlength="7" size="7"  readonly="readonly" style="font-weight: bold; border: none;" /> 
						&nbsp;&nbsp;
					<b>Total Invoice Carat :  </b><input type="text" id="totalCarats" name="totalCarats" 	maxlength="7" size="7" readonly="readonly"  style="font-weight: bold;border: none;" />			
						&nbsp;&nbsp;								
					
					
					</div>
			
			
			</div>
			
	
	</div>
	





		
<script type="text/javascript">

var stnMtid;
var vStone;
var vCarat;
var vmOpt;




var orderQty;
var stnRecQty;
var cancelQty;
$(document).ready(
		function(e) {
			
			
			$("#orderNo")
			.autocomplete(
			{
				source : "<spring:url value='/manufacturing/transactions/bag/order/list.html' />",
				minLength : 2,

				   change: function (event, ui) {
	                    if (ui.item == null || ui.item == undefined) {
	                        $("#orderNo").val("");
	                       
	                    }
	                 
	                }
			});
			
			
			
		 	if (window.location.href.indexOf('mtid') != -1) {
						var vUrl = window.location.href;
				
						stnMtid = vUrl.substring(window.location.href.indexOf('mtid') + 5,window.location.href.indexOf('totalcarat'));
						
						vCarat = vUrl.substring(window.location.href.indexOf('totalcarat') + 13,window.location.href.indexOf('totalstone'));
						
						vStone = vUrl.substring(window.location.href.indexOf('totalstone') + 13,window.location.href.indexOf('mOpt'));
					
						vmOpt  = vUrl.substring(window.location.href.indexOf('mOpt') + 5);
						
						if(vmOpt == 'Inward'){
							$('#lblInwardDivId').css('display','block');
						}else{
							$('#lblOutwardDivId').css('display','block');
						}
																	
						}
	
		 	stnInwardTotalStoneCarat();
		 	
		});
		
		
		function stnInwardTotalStoneCarat(){
			
			$('#totalStones').val(vStone);
		 	$('#totalCarats').val(vCarat);
		}
		
		function popOrderDetails(){
			
			$("#stnInwOrderDtTblId")
				.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/masters/orderDt/stnInwOrderDt/listing.html?pInvNo="
								+ $("#orderNo").val()
					});
			
			
			
		}

		
			
			$('#stnInwOrderDtTblId').bootstrapTable({}).on(
					'load-success.bs.table',
					function(e, data) {
			
			var data = JSON.stringify($("#stnInwOrderDtTblId").bootstrapTable('getData'));
			
			var vOrdqty = 0.0;
			var vRecQty = 0.0;
			var vOutward= 0.0;
			
			$.each(JSON.parse(data), function(idx, obj) {
				
				vOrdqty	+= Number(obj.ordQty);
				vRecQty	+= Number(obj.stnRecQty);
				vOutward+= Number(obj.stnOutQty);
				
				if(Number(obj.stnRecQty) > 0 || obj.stnOutQty > 0){
					$('#stnInwOrderDtTblId tr:eq('+Number(idx+1)+')').addClass('mySelected');	
				}
			
			});
			
			
			
			$('#orderQty').val(vOrdqty);
			$('#inwardQty').val(vRecQty);
			$('#outwardQty').val(vOutward);
			
			$('#balanceQty').val(Math.round((Number(vOrdqty)-Number(vRecQty)+Number(vOutward))*100)/100);
			
			});
			
		
			
			function stnInwBackBtn(){
				
				if(vmOpt == 'Inward'){
					window.location.href = "/jewels/manufacturing/transactions/stoneInwardMt/edit/"+stnMtid+".html"
				}else{
					window.location.href = "/jewels/manufacturing/transactions/stoneOutwardMt/edit/"+stnMtid+".html"
				}
				
				
			} 

			
			
			 function bagQtyFormatter(value,row,index){

				 return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateBagQty(this,'+row.dtid+','+index+','+row.cancelQty+');"/>';
			} 

					 
			 function updateBagQty(param,dtid,vidx,cancelQty){
				 
				 if(cancelQty > 0){
					 displayDlg(
								this,
								'javascript:openStnInwModal('+param.value+','+dtid+','+vidx+');',
								'Stone Inward',
								'Order Item Qty is Cancel. Do You want continue  ?',
								'Continue');
					 }else{

				 

						 openStnInwModal(param.value,dtid,vidx)
		}
	}

	function openStnInwModal(paramVal,dtid,vidx) {

		$("#modalDialog").modal("hide");
	 	if(paramVal > 0){
	 		
			 if(vmOpt == 'Outward' && Number(stnRecQty) < Number(paramVal)){
				 
				 $('#stnInwOrderDtTblId').bootstrapTable('updateRow', {
						index : Number(vidx),
						row : {
							bagQty : 0,
						}
					}); 
				 
					displayMsg(event, this,'Can Not Enter Greater Than Received Qty');
				 
				 $('#myStnInwOrderStnModal').modal('hide');
			 }else{
				 
				 $('#stnInwOrderDtTblId').bootstrapTable('updateRow', {
						index : Number(vidx),
						row : {
							bagQty :paramVal,
						}
					}); 
			  		
				
				
				
				 
				 $('#ordDtId').val(dtid);
		 		 $('#vBagQty').val(paramVal);
		 		 $('#stnInwMtId').val(stnMtid);
		 		 $('#mOpt').val(vmOpt)
		 		
		 		$('#myStnInwOrderStnModal').modal('show');
		 			
			
}

		}
	}

	$('#myStnInwOrderStnModal').on('show.bs.modal', function(e) {
		//popOrderDetails();
		orderStnList($('#ordDtId').val(), $('#vBagQty').val());

	})

	$('#orderStoneDtTblId').bootstrapTable({}).on('load-success.bs.table',
			function(e, data) {

				sumBagCaratStoneDtl();

			});

	$('#stnInwOrderDtTblId').bootstrapTable({}).on('click-row.bs.table',
			function(e, row, $element) {

				cancelQty = jQuery.parseJSON(JSON.stringify(row)).cancelQty;
				orderQty = jQuery.parseJSON(JSON.stringify(row)).ordQty;
				stnRecQty = jQuery.parseJSON(JSON.stringify(row)).stnRecQty;

			})

	function orderStnList(dtid, bagqty) {

		$("#orderStoneDtTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/orderStoneDt/stnInw/listing.html?orderDtId="
									+ dtid + "&bagQty=" + bagqty,
						});

	}

	function getPartyCode() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/masters/orderMt/getPartyCode.html' />?orderNo='
							+ $('#orderNo').val(),
					type : 'GET',
					dataType : "JSON",
					success : function(data) {
						$.each(data, function(k, v) {

							$('#stnInwAgainstOrderDivId  #' + k).val(v);

						});
					}
				});
	}

	function openOrderList() {

		$('#myOrderMtModal').modal('show');

		setTimeout(function() {

			$("#modalOrderMtTblId").bootstrapTable('refresh', {
				url : "/jewels/manufacturing//masters/order/filterListing.html"

			});

		}, 200);

	}

	$('#modalOrderMtTblId').bootstrapTable({}).on('dbl-click-row.bs.table',
			function(e, row, $element) {

				var invNo = jQuery.parseJSON(JSON.stringify(row)).invNo;

				$('#orderNo').val(invNo);

				$('#myOrderMtModal').modal('hide');
				$('#orderNo').trigger('onchange');

			})

	function checkOrderApproval() {

		$
				.ajax({
					url : "/jewels/manufacturing/masters/orderDt/checkOrderApproval.html?pInvNo="
							+ $('#orderNo').val(),
					type : 'GET',
					success : function(data) {

						if (data === 'true') {

							displayMsg(this, null, 'Order is not appoved');

						} else {

							popOrderDetails();
							getPartyCode();
						}

					}
				});
	}
</script>	



<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>			
			
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />
