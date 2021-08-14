<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
	
</style>


    <div class="modal fade" id="myStnInwOrderStnModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Order Stone Details</h4>
			</div>

			<div class="modal-body">
			
			
	
		<div class="row">
				
					<table id="orderStoneDtTblId" data-toggle="table" 
						 data-side-pagination="server"	data-height="350">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="srNo" data-sortable="true">Sr No.</th>
								<th data-field="shape" data-sortable="true">Shape</th>
								<th data-field="quality" data-sortable="true">Quality</th>
								<th data-field="size" data-sortable="true">Size</th>
								<th data-field="sieve" data-sortable="true">Sieve</th>
								<th data-field="sizeGroup" data-align="left" data-sortable="true">Group</th>
								<th data-field="stone" data-align="left" data-sortable="true">Per Pcs Stone</th>
								<th data-field="carat" data-sortable="true">Per Pcs Carat</th>
								<th data-field="bagStone" data-formatter="bagStoneFormatter" data-sortable="true">Bag Stone</th>
								<th data-field="bagCarat" data-formatter="bagCaratFormatter" data-sortable="true">Bag Carat</th>
								<th data-field="diaCateg" data-sortable="true">Categ</th>
								<th data-field="rate" data-formatter="bagRateFormatter" data-sortable="true">Rate</th>
							</tr>
						</thead>
					</table>
				
			
		</div>
		
		
	<div>
		<span style="display:inline-block; width: 8cm;"></span>
					
					<b>Per Pcs Stone :  </b><input type="text" id="vvTotalStone" name="vvTotalStone"  maxlength="7" size="7"  readonly="readonly" style="font-weight: bold; border: none;" /> 
						&nbsp;
					<b>Per Pcs Carat : </b><input type="text" id="vvTotalCarat" name="vvTotalCarat" maxlength="7" size="7"  readonly="readonly"  style="font-weight: bold;border: none;" />			
						&nbsp;								
					
					
					<b>Bag Stone :  </b><input type="text" id="vvTotalBagStone" name="vvTotalBagStone" maxlength="7" size="7"  readonly="readonly" style="font-weight: bold; border: none;" /> 
						&nbsp;
					<b>Bag Carat :  </b><input type="text" id="vvTotalBagCarat" name="vvTotalBagCarat" 	maxlength="7" size="7" readonly="readonly"  style="font-weight: bold;border: none;" />			
						&nbsp;							
					
					
					</div>
			
			
		<div class="modal-footer">
		
			<input type="button" value="Save" id="orderStnSaveBtnId" class="btn btn-primary" onclick="javascript:orderStnSave()"/>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
					<input type="hidden" id="stnInwMtId" name="stnInwMtId" />
						<input type="hidden" id="ordDtId" name="ordDtId" />
					<input type="hidden" id="vBagQty" name="vBagQty" />
					<input type="hidden" id="mOpt" name="mOpt" />
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
<script type="text/javascript">

$(document).ready(
		function(e) {
			
			
			$("#orderNo")
			.autocomplete(
			{
				source : "<spring:url value='/manufacturing/transactions/bag/order/list.html' />",
				minLength : 2
			});
			
			
		});
		
		
		var InwardStnTableRow;
		var stnQuality;
		$('#orderStoneDtTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					InwardStnTableRow = $element.attr('data-index');
					stnQuality = jQuery.parseJSON(JSON.stringify(row)).quality;
					
				
				})

		
		
		function bagStoneFormatter(value,row,index){
			
			return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateBagStone(this,'+index+')"  />';
		}

		function updateBagStone(param,vidx){
			 
	 		$('#orderStoneDtTblId').bootstrapTable('updateRow', {
				index : Number(vidx),
				row : {
					bagStone : param.value,
				}
			});
	 		
	 		sumBagCaratStoneDtl();
		}
		
		
		function sumBagCaratStoneDtl(){
			
			var totstone =0;
			var totcarat =0.0;
			var bagstones =0;
			var bagcarats =0.0;
			var data =JSON.stringify($('#orderStoneDtTblId').bootstrapTable('getData'));
			$.each(JSON.parse(data),function(idx,obj){
				
				totstone +=Number(obj.stone);
				totcarat +=Number(obj.carat);
				bagstones +=Number(obj.bagStone);
				bagcarats +=Number(obj.bagCarat);
			});
			
			$('#vvTotalBagStone').val(bagstones);
			$('#vvTotalBagCarat').val(Number(bagcarats).toFixed(3));
			$('#vvTotalStone').val(Number(totstone));
			$('#vvTotalCarat').val(Number(totcarat).toFixed(3));
		}
		
		
		function bagCaratFormatter(value,row,index){
			
			return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateBagCarat(this,'+index+')"/>';
			
		}
		
		

		function updateBagCarat(param,vidx){
			
	 		$('#orderStoneDtTblId').bootstrapTable('updateRow', {
				index : Number(vidx),
				row : {
					bagCarat : param.value,
				}
			});
	 		
	 		sumBagCaratStoneDtl();
		}
		
	
		var bagRateData;
		function bagRateFormatter(value,row,index){
			
			return '<input class="form-control data-input" style="width:65px" value="'+ value+ '" onchange="javascript:updateBagRate(this,'+index+')"/>';
				
	}
		
		
		
		function updateBagRate(param,vidx){
			
			/* 	$('#orderStoneDtTblId').bootstrapTable('updateRow', {
						index : Number(vidx),
						row : {
							rate : param.value,
						}
					}); */
			
				var data =JSON.stringify($('#orderStoneDtTblId').bootstrapTable('getData'));
				
				
				$.each(JSON.parse(data),function(idx,obj){
										
					if(obj.quality == stnQuality && obj.rate == 0){
						
						$('#orderStoneDtTblId').bootstrapTable('updateRow', {
							index : Number(idx),
							row : {
								rate : param.value,
							}
						});
						
						
					}else{
					
						$('#orderStoneDtTblId').bootstrapTable('updateRow', {
							index : Number(vidx),
							row : {
								rate : param.value,
							}
						});
					}
					
					
				});
	 	
	 		
	 		
		}
		
		
		
		function orderStnSave(){
			
			
			$('#orderStnSaveBtnId').attr('disabled', 'disabled');
			
			
			
			if(Number($("#orderStoneDtTblId").bootstrapTable('getSelections').length) > 0){
				
				 var data = JSON.stringify($("#orderStoneDtTblId").bootstrapTable('getSelections'));
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/stoneInwardDt/saveOrderStoneDt.html",
						type : "POST",
						data : {
							pInwardMtid :  $('#stnInwMtId').val(),
							pBagQty     :  $('#vBagQty').val(),
							mOpt        :    $('#mOpt').val(),
							data        : data,
						},
						success : function(data, textStatus, jqXHR) {
							
							var tempData = data.split("^");
							
							if(tempData[0] === "-1"){
								displayInfoMsg(this,null,"Data Transfered Successfully");
								$('#myStnInwOrderStnModal').modal('hide');
								popOrderDetails();
								$('#totalStones').val(tempData[1]);
							 	$('#totalCarats').val(tempData[2]);
							}else{
								displayMsg(this,null,data);
							}
							
							
							
							
							
							 $('#orderStnSaveBtnId').removeAttr('disabled');
						},
						error : function(jqXHR, textStatus, errorThrown) {
						}
					});
				 
				
			
			
			}else{
				displayMsg(this,null,"Record Not Selected");
				$('#orderStnSaveBtnId').removeAttr('disabled');
			}
			
			
			
		}
		
		
		$('#myStnInwOrderStnModal').on('hidden.bs.modal', function (e) {
			
			popOrderDetails();
			})
		
		


</script>			
			

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>			
			
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />