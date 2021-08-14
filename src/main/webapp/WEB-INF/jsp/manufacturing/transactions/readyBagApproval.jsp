<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<div class="panel panel-primary">
	<div class="panel-heading" >
		<span style="font-size: 18px;">ReadyBag Approval</span>
	</div>

	<div class="panel-body">
		
		<form:form commandName="readyBagIssMt" id="readyBagIssMtApprovalDivId"
					action="/jewels/manufacturing/transactions/readyBagIssMt/pendingApproval.html"
				cssClass="transferForm">

				<div class="row">
				
				<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label" for="trandate">TranDate</label>
								<form:input path="invDate" cssClass="form-control"  autocomplete="off"/>
								<form:errors path="invDate" />
						</div>
	
					</div>
				
				
				<div class="col-sm-3">

							<div class="form-group">

								<label class="control-label">Location</label> 
								<form:select path="location.id"  class="form-control" onChange="javascript:popReadyBagDetails();">
											<form:option value="" label="- Select department -" />
											<form:options items="${locationMap}" />
										</form:select>

							</div>

						</div>
	
						
					<div class="col-sm-1">
					<label class="control-label" for="trfDept"></label> 
					<div class="form-group">
							<div>
								<input type="button" value="Approval" onclick="javascript:popReadyBagPendingApproval()" id="pendingApprovalBtnId" class="btn btn-primary">
							</div>
						</div>
					
					</div>	
						
								
				</div>
			
				
				<!-- Hidden Field -->
				
						<input type="hidden" name="id"> 
						<input type="hidden" name="vBagIds" id="vBagIds" />


				<div class="table-responsive">
 					<table id="readyBagPendingApprovalTblId" data-toggle="table" data-search="true" data-toolbar="toolbarDt" data-maintain-meta-data="true" data-height="400"  >
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
									 
 			

				</div>


		</form:form>
				 
		</div>
	</div>		
	
	

<script type="text/javascript">


$(document)
.ready(
		function(e) {
			
			$("#invDate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
		
			$("#invDate").val('${currentDate}');
		});





function popReadyBagDetails(){

		
	if($('#location\\.id').val() !=''){
	
	$("#readyBagPendingApprovalTblId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/readyBagPendingApproval/listing.html?locationId="+$('#location\\.id').val(),
			}); 
	}
	
} 


function popReadyBagPendingApproval() {
	
	var data = $('#readyBagPendingApprovalTblId').bootstrapTable('getAllSelections');
	var ids = $.map(data, function(item) {
		return item.bagId;
	});
	$('#vBagIds').val(ids);
	
	if($('#location\\.id').val() !='' && $('#vBagIds').val() ){
		
		$('#pendingApprovalBtnId').attr("disabled", true);
	
	$.ajax({
		url : "/jewels/manufacturing/transactions/readyBagPendingApproval/Transfer.html?locationId="+$('#location\\.id').val()
				+"&pBagId="+$('#vBagIds').val(),
	
		type : "POST",
		success : function(data, textStatus, jqXHR) {
			
			if (Number(data) == 1) {

				displayInfoMsg(event, this,'data transfered sucessfully !');
				$('#pendingApprovalBtnId').attr("disabled", false);
				
			}			
			popReadyBagDetails();
			
		},
		error : function(jqXHR, textStatus, errorThrown) {

		}

	});
	
	}

}





function popUserInput(e, id) {
	displayDlg(
			e,
			'javascript:enableDisableTransferBtn();',
			'Warning-Information',
			'Production Already Entered ? <br><strong>Press Continue To Enter Again</strong>',
			'Continue');
	
}


function enableDisableTransferBtn(){
	
	$("#modalDialog").modal("hide");
	//$('#tranSubmitBtn').removeAttr('disabled');
	$('#pendingApprovalBtnId').prop('disabled', true);
	
}



$('#PendingApprovalTblId').on('check.bs.table', function (e, row) {
	 popSelectedQty();
});



$('#PendingApprovalTblId').on('check-all.bs.table', function (e, row) {
	 popSelectedQty();
});


$('#PendingApprovalTblId').on('uncheck.bs.table', function (e, row) {
	 popSelectedQty();
});



$('#PendingApprovalTblId').on('uncheck-all.bs.table', function (e, row) {
	
	 popSelectedQty();
	
});



$('#PendingApprovalTblId').bootstrapTable({}).on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#PendingApprovalTblId").bootstrapTable('getData'));
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
		
		
		var data = $('#PendingApprovalTblId').bootstrapTable('getAllSelections');
		
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
	

	function popNotApproval(){
		
		var data = $('#PendingApprovalTblId').bootstrapTable('getAllSelections');
		var ids = $.map(data, function(item) {
			return item.bagNo;
		});
		$('#vBagNo').val(ids);
		
		if($('#deptToo').val() !='' && $('#vBagNo').val() ){
		
			$('#myNoneApprovalRemarkModal').modal('show');
		
		$('#vvBagIds').val($('#vBagNo').val());
		$('#vDeptFromIds').val($('#deptFroom').val());
		$('#vDeptToIds').val($('#deptToo').val());
		$('#vTrandate').val($("#trandate").val());
		
		
		}
		
	}


</script>	
	
	


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />	
		