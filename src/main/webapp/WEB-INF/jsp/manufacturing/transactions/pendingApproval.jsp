<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalNoneApprovalRemark.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<div class="panel panel-primary">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Pending Approval</span>
	</div>

	<div class="panel-body">
		
		<form:form commandName="tranMt" id="pendingApprovalDivId"
					action="/jewels/manufacturing/transactions/transfer/add.html"
				cssClass="transferForm">

				<div class="row">
				
				<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label" for="trandate">TranDate</label>
								<form:input path="trandate" cssClass="form-control"  autocomplete="off"/>
								<form:errors path="trandate" />
						</div>
	
					</div>
				
				
				<div class="col-sm-3">

							<div class="form-group">

								<label class="control-label" for="trfDept">Department</label> 
								<form:select path="deptTo.id" id="deptToo" class="form-control" onChange="javascript:popFromDeptList();popBagDetails();">
											<form:option value="" label="- Select department -" />
												<form:options items="${departmenttMap}" />
										</form:select>

							</div>

						</div>
						
						
		<%-- 			<div class="col-sm-3">

					<div class="form-group">
										
						<label class="control-label" for="dept">From Department</label>
							<form:select path="deptFrom.id" id="deptFroom"	class="form-control" onChange="javascript:popBagDetails();">
								<form:option value="" label=" Select department " />
								<form:options items="${departmentToMap}" />
							</form:select>

						
					</div>

				</div>	 --%>
						
					<div class="col-sm-1">
					<label class="control-label" for="trfDept"></label> 
					<div class="form-group">
							<div>
								<input type="button" value="Approval" onclick="javascript:popPendingApproval()" id="pendingApprovalBtnId" class="btn btn-primary">
							</div>
						</div>
					
					</div>	
					
					
					<div class="col-sm-1">
					<label class="control-label" for="Remark"></label> 
					<div class="form-group">
							<div>
								<input type="button" value="Not Approval & Return" onclick="javascript:popNotApproval()" id="notApprovalBtnId" class="btn btn-danger">
							</div>
						</div>
					
					</div>	
						
								
				</div>
			
				
				<!-- Hidden Field -->
				
						<input type="hidden" name="id"> 
						<input type="hidden" name="vBagNo" id="vBagNo" />


				<div class="table-responsive">
 					<table id="PendingApprovalTblId" data-toggle="table" data-search="true" data-toolbar="toolbarDt" data-maintain-meta-data="true" data-height="400"  >
					<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="fromDept" data-align="left" data-sortable="true" data-filter-control="input">From Dept</th>
								<th data-field="party" data-align="left" data-sortable="true" data-filter-control="input">Party</th>
								<th data-field="orderNo" data-align="left" data-sortable="true" data-filter-control="input">Order</th>
								<th data-field="refNo" data-align="left" data-sortable="true" data-filter-control="input">Ref</th>
								<th data-field="bagNo" data-align="left" data-sortable="true" data-filter-control="input">Bag</th>
								<th data-field="styleNo" data-align="left" data-sortable="true" data-filter-control="input">Style No</th>
								<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
								<th data-field="color" data-align="left" data-sortable="true">Color</th>
								<th data-field="size" data-align="left" data-sortable="true">Size</th>
								<th data-field="qty" data-align="left" data-sortable="true">Qty</th>
								<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
								<th data-field="totStone" data-align="left" data-sortable="true">Tot Stone</th>
								<th data-field="totCarat" data-align="left" data-sortable="true">Tot Carat</th>								
								

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
				Selected Bags : <input type="text" id="vSelectBags" name="vSelectBags" 	maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
						&nbsp;&nbsp;
				Selected Qty : <input type="text" id="vSelectQty" name="vSelectQty"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
							&nbsp;&nbsp;
			Selected Wt : <input type="text" id="vSelectWt" name="vSelectWt"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>			
 				 </div>

				</div>


			
			
			



		</form:form>
				 
		</div>
	</div>		
	
	

<script type="text/javascript">


$(document)
.ready(
		function(e) {
			
			
			$("#trandate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
		
			$("#trandate").val('${currentDate}');
			
			
			if('${canEditTranddate}' === "false"){
				$("#trandate").attr("disabled","disabled");
			}
			
			
		});

function popFromDeptList() {
	
	$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/transfer/deptTo.html' />?departmentId='
						+ $('#deptToo').val(),
				type : 'GET',
				success : function(data) {
					$("#deptFroom").html(data);
					  
				}
			});
}




function popBagDetails(){
	
	if($('#deptToo').val() !=''){
	
	$("#PendingApprovalTblId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/penndingApproval/listing.html?deptToId="+$('#deptToo').val(),
			}); 
	}
	
} 


function popPendingApproval() {
	
	var data = $('#PendingApprovalTblId').bootstrapTable('getAllSelections');
	var ids = $.map(data, function(item) {
		return item.bagNo;
	});
	$('#vBagNo').val(ids);
	
	if($('#deptToo').val() !='' && $('#vBagNo').val() ){
		
		$('#pendingApprovalBtnId').attr("disabled", true);
	
	$.ajax({
		url : "/jewels/manufacturing/transactions/pendingApproval/Transfer.html?deptTo="+$('#deptToo').val()
				+"&vBagNo="+$('#vBagNo').val()+"&tranDate="+$("#trandate").val(),
	
		type : "POST",
		success : function(data, textStatus, jqXHR) {
			
			if (Number(data) == 1) {

				displayInfoMsg(event, this,'data transfered sucessfully !');
				$('#pendingApprovalBtnId').attr("disabled", false);
				
			}			
			popBagDetails();
			
				

		},
		error : function(jqXHR, textStatus, errorThrown) {

		}

	});
	
	}

	/* e.preventDefault(); */ //STOP default action
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
		