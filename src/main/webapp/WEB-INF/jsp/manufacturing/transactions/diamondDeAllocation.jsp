
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalOrderDeAllocation.jsp"%>


<style>
.bacground {
	bgcolor: "red";
}

.mySelected {
	background-color: #FFB6C1;
}
</style>


<div id="diaDeAllocationDivId">
<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading">
			<span style="font-size: 18px;">Diamond De Allocation </span>
		</div>


		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="row">

			<div class="col-xs-12">
				<div class="form-group col-sm-2">
					<label class="control-label" for="orderNo">Order No.</label>
					<div class="input-group">
						<input type="text" id="orderNo" name="orderNo"
							class="form-control"
							onchange="javascript:popDiaDeAllocationDetails();getPartyCode();">
						<span class="input-group-btn">
							<button type="button"
								class="btn btn-default glyphicon glyphicon-list"
								onClick="javascript:openOrderList()"></button>
						</span>
					</div>
				</div>


				<div class="form-group col-sm-2">
					<label class="control-label" for="orderNo">Client</label> <input
						type="text" id="partyCode" name="partyCode" class="form-control"
						readonly="readonly">

				</div>


				<div class="form-group col-sm-2">
					<label class="control-label" for="Order Qty">Order Qty</label> <input
						type="text" id="orderPcs" name="orderPcs" class="form-control"
						readonly="readonly">

				</div>



				<div class="form-group col-sm-2">
					<label class="control-label" for="Ref No">Ref No</label> <input
						type="text" id="refNo" name="refNo" class="form-control"
						readonly="readonly">

				</div>



			</div>




		</div>

		<div class="row">

			<div class="col-xs-12">
				<span class="col-lg-12 label label-default text-right"
					style="font-size: 18px;">Diamond Allocation Details</span>
			</div>
		</div>

		
			<div class="container-fluid">

				<div class="row">

					<div class="col-xs-12">


						<div class="col-sm-12 table-responsive">
							<table id="diaDeAlloacationTblId" data-toggle="table"
								data-toolbar="#toolbarDt" data-search="true" data-height="350">
								<thead class="thead-dark">
									<tr class="btn-primary">
										<th data-field="state" data-checkbox="true" data-formatter="stateFormatter">Select</th>
										<th data-field="srno">Sr. No</th>
										<th data-field="stoneType">StoneType</th>
										<th data-field="shape">Shape</th>
										<th data-field="quality">Quality</th>
										<th data-field="size">Size</th>
										<th data-field="sieve">Sieve</th>
										<th data-field="sizeGroup" data-align="left">SizeGroup</th>
										<th data-field="allocateCarat">Allocated Stk Cts</th>
										<th data-field="allocateStone">Allocated Stk Stn</th>
										<th data-field="issueCarat"	data-formatter="issueCaratFormatter" data-align="left">Return Cts</th>
										<th data-field="issueStone" data-formatter="issueStoneFormatter" data-align="left">Return Stn</th>

									</tr>
								</thead>
							</table>
						</div>


					</div>

				</div>
				
				<div class="row">
			<div class="col-xs-12">&nbsp;</div>
			</div>
				<div class="col-sm-12">
			<input type="button" value="Return" class="btn btn-primary"
				id="returnAllocateBtnId" onClick="javascript:returnStock(event);" />
		</div>
		
			</div>

	


		


	</div>

</div>




<script type="text/javascript">
	$(document)
			.ready(
					function(e) {

						$("#orderNo")
								.autocomplete(
										{
											source : "<spring:url value='/manufacturing/transactions/bag/order/list.html' />",
											minLength : 2,

											change : function(event, ui) {
												if (ui.item == null
														|| ui.item == undefined) {
													$("#orderNo").val("");

												}

											}
										});

					});

	

	function openOrderList() {
		$('#myOrderMtModal').modal('show');

		setTimeout(function() {

			$("#modalOrderMtTblId").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/diamondAllocationOrderList.html"

			});

		}, 200);

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

							$('#diaDeAllocationDivId  #' + k).val(v);

						});

					}
				});
	}

	$('#modalOrderMtTblId').bootstrapTable({}).on('dbl-click-row.bs.table',
			function(e, row, $element) {

				var invNo = jQuery.parseJSON(JSON.stringify(row)).invNo;

				$('#orderNo').val(invNo);

				$('#myOrderMtModal').modal('hide');
				$('#orderNo').trigger('onchange');

			})

	function popDiaDeAllocationDetails() {

		$("#diaDeAlloacationTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/diamondDeAllocation/listing.html?pInvNo="
									+ $("#orderNo").val()
						});

	}

	function issueCaratFormatter(value, row, index) {
		
		var tempId = row.srno;
		
		return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '"  style="width:65px" value="'
				+ value
				+ '" onchange="javascript:updateIssueCarat(this,'+tempId+','+row.totCarat+','+row.allocateCarat+')" />';
	}

	function updateIssueCarat(param, tempId,reqCts,allocateCarat) {
		
		var tolReqCarat =(Number(reqCts)+(Number(reqCts)*250/100)).toFixed(3);
		
		
		var totallcts =(Number(allocateCarat)+Number(param.value)).toFixed(3);
		
if(Number(totallcts)>Number(tolReqCarat)){
	
	displayMsg(this,null,'Out Of Tolerance')
	$('#diaDeAlloacationTblId').bootstrapTable('updateRow', {
		index : Number(tempId-1),
		row : {
			issueCarat : 0.0,
		}
	});
	
}else{
	$('#diaDeAlloacationTblId').bootstrapTable('updateRow', {
		index : Number(tempId-1),
		row : {
			issueCarat : param.value,
		}
	});
}	


	}

	function issueStoneFormatter(value, row, index) {

		var tempId = row.srno;
		return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '"  style="width:65px" value="'
				+ value
				+ '" onchange="javascript:updateIssueStone(this,'
				+ tempId + ')"  />';
	}

	function updateIssueStone(param, tempId) {

		$('#diaAlloacationOrderDtTblId').bootstrapTable('updateRow', {
			index : Number(tempId-1),
			row : {
				issueStone : param.value,
			}
		});

	}


	$('#diaDeAlloacationTblId').on('check.bs.table check-all.bs.table', function () {
		
		 var data =JSON.stringify($('#diaDeAlloacationTblId').bootstrapTable('getData'));
		 $.each(JSON.parse(data),function(idx,obj){
 
			 if(obj.state==true){
 
					var carat =  Number(obj.allocateCarat).toFixed(3);
					var stone = Number(obj.allocateStone);
					if(carat>0){
						 $("#diaDeAlloacationTblId").bootstrapTable('updateRow', {
								index : idx,
								row : {
									issueCarat : carat,
									issueStone : stone,
									

								}
							});
					}
						
					 
					
		
				 
			 }
				 
		 });
		
	});



	
	function stateFormatter(value, row, index) {
		
		var tolCarat = (Number(row.totCarat)*10/100).toFixed(3);
		
	
		if((Number(row.totCarat+Number(tolCarat))).toFixed(3) < Number(row.allocateCarat)){
				return {
			        disabled: true,
			        
			      }	
				
			}else{
				  return value
			}

	  }
	
	
	function returnStock(){
		
		$('#returnAllocateBtnId').attr('disabled', 'disabled');
		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		
		if(Number($("#diaDeAlloacationTblId").bootstrapTable('getSelections').length) > 0){
			
			 var data = JSON.stringify($("#diaDeAlloacationTblId").bootstrapTable('getSelections'));
					
				$.ajax({
					url : "/jewels/manufacturing/transactions/stoneInwardDt/returnOrdStock.html",
					type : "POST",
					data : {
						pInvNo :  $("#orderNo").val(),
						data   : data,
					},
					success : function(data, textStatus, jqXHR) {
						
						if(data === "1"){
							displayInfoMsg(this,null,"Stock Return Successfully");
							popDiaDeAllocationDetails();
						
						}else{
							displayMsg(this,null,data);
						}
						
						 $('#returnAllocateBtnId').removeAttr('disabled');
						 
							$.unblockUI();
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$.unblockUI();
					}
				});
			 
			
		
		
		}else{
			displayMsg(this,null,"Record Not Selected");
			$('#returnAllocateBtnId').removeAttr('disabled');
			$.unblockUI();
		}
		
		
		
	}
	


	
</script>


<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<link rel="stylesheet"
	href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js"
	type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet"
	type="text/css" />
	
	<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>







