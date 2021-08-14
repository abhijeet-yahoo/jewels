<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="Order" />
<c:set var="optionText" value="Delete" />

<div>
	<div class="table-responsive"> 
			<table id="orderApproval" data-toggle="table" data-url="/jewels/manufacturing/masters/orderApproval/listing.html?approvalFlg=true"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server" data-search="true"  
		    data-height="550">
			<thead>
				<tr class="btn-primary">
					<th data-field="invNo" data-align="left" data-sortable="true">Order No</th>
					<th data-field="party" data-sortable="true">Client</th>
					<th data-field="refNo" data-sortable="true">Reference No</th>
					<th data-field="ordDate" data-sortable="true">Order Date</th>
					<th data-field="prodDate" data-sortable="true">PO Date</th>
					<th data-field="delDate" data-sortable="true">Delivery Date</th>
					<th data-field="totQty" data-sortable="true">Total Qty</th>
					<th data-field="orderClose" data-sortable="true">Order Status</th>
					<th data-field="action1" data-align="center">Approve</th>
					
				</tr>
			</thead>
		</table>
	</div>
</div>





<script type="text/javascript">

$(document).ready(
		function(e) {
				pendingApprovalList();
			})

	
	function pendingApprovalList(){

			$("#orderApproval").bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/masters/orderApproval/listing.html?approvalFlg=true"
				});
		}

	function orderApprovalDt(mtId){
		
		$("#modalDialog").modal("hide");

		$.ajax({
			url : "/jewels/manufacturing/masters/order/orderApprove.html?mtId="+mtId,
			type : 'GET',
			success : function(data) {

				if(data === "1"){
					displayInfoMsg(this, null, 'Order Approve Successfully');
					pendingApprovalList();
					}else{
						displayMsg(this, null, 'Quality not present in order');
						}
				
				
			}
		});
		}
	

	

</script>


<script src="/jewels/js/common/order.js"></script>

