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


  <div class="modal fade" id="myOrderBagDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Bag Details</h4>
			</div>

			<div class="modal-body">
			
			<div class="row">
			
				<div class="col-sm-4 form-group">

						<label for="orderNo" class="col-sm-4 control-label">Order No</label>
						<div class="col-sm-8">
							<input type="text" id="orderNo" class="form-control" name="orderNo" onchange="javascript:popBagDetails();"/>
						</div>

					</div>
					
					
						<div class="col-sm-4 form-group">
						<label for="partyNm" class="col-sm-4 control-label">Party</label>
						<div class="col-sm-8">
							<input
						type="text" id="partyCode" class="form-control" name="partyCode"
						readonly="readonly" />
						</div>
					</div>
			
			</div>
			
			
	<div class="row">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12">
				<span class="col-lg-12 col-sm-12 label label-default text-right"
					style="font-size: 18px;">Bag Details</span>
			</div>
		</div>
	</div>
			
	
		<div class="row">
				
					<table id="orderBagDtTblId" data-toggle="table"  data-pagination="true"	
							data-height="350"  data-search="true">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="bagNo" data-sortable="true">Bag No.</th>
								<th data-field="styleNo" data-sortable="true">Style No</th>
								<th data-field="barcode" data-sortable="true">Old Bag No</th>
								<th data-field="kt" data-sortable="true">Kt</th>
								<th data-field="color" data-sortable="true">Color</th>
								<th data-field="qty" data-sortable="true">Qty</th>
							</tr>
						</thead>
					</table>
				
			
		</div>
		
		

			
			
		<div class="modal-footer">
		
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
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
				minLength : 2,

				  change: function (event, ui) {
	                    if (ui.item == null || ui.item == undefined) {
	                        $("#orderNo").val("");
	                    }
	                }
			});
			
			
		});


	function popBagDetails(){

		$
		.ajax({
			url : '<spring:url value='/manufacturing/masters/orderMt/getPartyCode.html' />?orderNo='
					+ $('#orderNo').val(),
			type : 'GET',
			dataType : "JSON",
			success : function(data) {
				$.each(data, function(k, v) {
					if(k === 'partyCode'){
							$('#partyCode').val(v);
						}
				});

				popBagList();
			}
		});
		
		}



	function popBagList() {

		$("#orderBagDtTblId")
			.bootstrapTable('refresh',{
				url : "/jewels/manufacturing/transactions/bagDt/listing.html?pInvNo="+$('#orderNo').val()
			});

	
		
	}

		
		</script>	