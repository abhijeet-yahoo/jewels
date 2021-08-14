<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading" >
		<span style="font-size: 18px;">New ReadyBag Issue</span>
	</div>

	<div class="panel-body">
	
		
	
	
	<div class="form-group">
		<div class="col-xs-12">
		
			
					
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td class="col-lg-2 col-sm-2">
							<input type="text" id="trandate" name="trandate" class="form-control"  autocomplete="off" placeholder="Tran Date"/>
						</td>
						
						<td class="col-lg-1 col-sm-1 text-center">
							<label>From Location</label> 
						</td>
						<td class="col-lg-2 col-sm-2">
							<select id="locationId" name="locationId" class="form-control" onChange="javascript:popReadyBagDetails(this.value);popDeptTo();">
								<option value="">- Select Location -</option>
							</select>
						</td>
						<td class="col-lg-1 col-sm-1 text-center">
							<label>To Location</label> 
						</td>
						<td class="col-lg-2 col-sm-2">
							<select id="deptId" name="deptId" class="form-control">
								<option value="">- Select Location -</option>
							</select>
						</td>
						<td class="col-lg-1 col-sm-1 text-center">
							<button id="trfId" class="btn btn-primary" onClick="javascript:trfData(event);" disabled="disabled">Transfer</button> 
						</td>
						<td class="col-lg-3 col-sm-3" id="updGId">
							<div class="checkbox">
								<label><input id="updGold" name="updGold" value="1" type="checkbox" class="">Don't update Metal Wt.</label>
								<input type="hidden" id="vBagNo" name="vBagNo" />
							</div>
							
							
						</td>
					</tr>
				</tbody>
			</table>
			
		</div>
		
		
		
	</div>

		
	<div class="table-responsive">
	

					<table id="newReadyBagTblId" data-toggle="table" 
										data-toolbar="#toolbarDt" data-search="true"  data-maintain-meta-data="true" data-pagination="true"
										data-height="450">

						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="party" data-align="left" data-sortable="true">Party</th>
								<th data-field="orderNo" data-align="left" data-sortable="true">Order</th>
								<th data-field="refNo" data-align="left" data-sortable="true">Ref</th>
								<th data-field="bagNo" data-align="left" data-sortable="true">Bag</th>
								<th data-field="styleNo" data-align="left" data-sortable="true">Style No</th>
								<th data-field="qty" data-align="left" data-sortable="true">Qty</th>
								<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
								
								

							</tr>
						</thead>


					</table>

				</div>
				
			
</div>


<input type="hidden" id="vBagNo" name="vBagNo" />

			
				
			
		<div class="form-group">
			
				<span class="col-lg-12 col-sm-12 label label-default"
					style="font-size: 18px;">Ready Bag Issue Details</span>
		
		</div>
		
		
		<div class="row">&nbsp;</div>
		


<div class="form-group">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="readyBagId" data-toggle="table">
						<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="partNm" data-sortable="true">Part No.</th>
								<th data-field="stoneType" data-sortable="true">Stn. Type</th>
								<th data-field="shape" data-sortable="true">Shape</th>
								<th data-field="quality" data-align="left">Quality</th>
								<th data-field="mm" data-align="center">Size</th>
								<th data-field="sieve" data-align="center">Sieve</th>
								<th data-field="stone" data-align="center">Stone</th>
								<th data-field="carat" data-align="center">Carat</th>
								<th data-field="setting" data-align="center">Setting</th>
								<th data-field="setType" data-align="center">Set Type</th>
								<th data-field="centerStone" data-formatter="centerStoneFormatter">CenterStn</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

</div>	
			
	
	

<script type="text/javascript">

var locId;
$(document)
.ready(
		function(e) {
			

		//	popBagList();
			popLocation();
		//	popDept();
		
			
			$("#trandate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
		
			$("#trandate").val('${currentDate}');
			
			
			if('${canEditTranddate}' === "false"){
				$("#trandate").attr("disabled","disabled");
			}
			
		});


function popReadyBagDetails(val){
	locId= val;
	
	popBagList();
}
		
		function popBagList(){
			
		$("#newReadyBagTblId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/readyBagIssue/listing.html?locationId="+locId
					});
			
		}
		
		
		function popDept() {
		
			$.ajax({
				url : '/jewels/manufacturing/transactions/toDepartment/list.html',
				type : 'GET',
				success : function(data) {
					$("#deptId").html(data);
				}
			});
		}

		function popLocation() {
			
			$.ajax({
				url : '/jewels/manufacturing/transactions/fromLocationDropdown/list.html',
				type : 'GET',
				success : function(data) {
					$("#locationId").html(data);
				}
			});
		}
		
			
				$('#newReadyBagTblId').bootstrapTable({}).on(
						'click-row.bs.table',
						function(e, row, $element) {
							
							$('#vBagNo').val(jQuery.parseJSON(JSON.stringify(row)).bagNo);
							
						
							 $("#readyBagId")
								.bootstrapTable(
										'refresh',
										{
											url : "/jewels/manufacturing/transactions/readyBag/listing.html?pBagNm="+$('#vBagNo').val()+"&locationId="+locId
										});
							
						});
				

				 $('#newReadyBagTblId')
						.on(
								'check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table',
								function() {
									$('#trfId').prop(
											'disabled',
											(!$('#newReadyBagTblId').bootstrapTable('getAllSelections').length));
								});
				

		 		function trfData(e) {
 			
		 			dpId = $('#deptId').val();
		 				displayDlg(e, 'javascript:doTransferReadyBag();', 'Transfer Ready Bags', 'Do you want to transfer the selected rows?', 'Continue');
		 			}
		 		
		 		function doTransferReadyBag(e) {
		 			$('#trfId').prop('disabled', true);
		 			$("#modalDialog").modal("hide");
		 			var ids = $.map($("#newReadyBagTblId").bootstrapTable('getAllSelections'),
		 					function(row) {
		 						return row.bagNo;
		 					});
		 			$('#vBagNo').val(ids);
	
		 			dpId = $('#deptId').val();
		 			
		 			$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
		 			
		 			$.ajax({
		 				url : "/jewels/manufacturing/transactions/multiReadyBag/transfer.html",
		 				type : "POST",
		 				data : {
		 					pBagNm : $('#vBagNo').val(),
		 					pDeptId : dpId,
		 					pUpdGold : $('#updGold').is(":checked"),
		 					pTrandate : $('#trandate').val(),
		 					pLocationId :locId
		 				},
		 				success : function(data, textStatus, jqXHR) {
		 					
		 					if(data === '1'){
		 						$.unblockUI();
		 						displayInfoMsg(this, null, 'Transfer Successfully ! ');
		 						popBagList();
		 			
		 					}else{
		 						$.unblockUI();
		 						displayMsg(this, null, data);
		 						
		 					} 					
		 				},
		 				error : function(jqXHR, textStatus, errorThrown) {
		 				}
		 			});

		 			$('#trfId').prop('disabled', false);
		
		 		}	
		 				
		 		
		 		function centerStoneFormatter(value) {

		 			var booleanValue;
		 			if (typeof (value) === "boolean") {
		 				booleanValue = (value);
		 			} else {
		 				booleanValue = (value == 'true');
		 			}

		 			var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		 			return '<input  type="checkbox" ' + checked + ' disabled="true"   />';

		 		}
		 
		
		
	function popDeptTo(){
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/transfer/deptTo.html' />?departmentId='
					+ $('#locationId').val()+"&dropDownId=deptId",
			type : 'GET',
			success : function(data) {
				$("#deptId").html(data);
				}
		});
		}


</script>


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />	

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

<script src="/jewels/js/common/design.js"></script>

