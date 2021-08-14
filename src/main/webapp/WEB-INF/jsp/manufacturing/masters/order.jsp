<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="Order" />
<c:set var="optionText" value="Delete" />

<div>
	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/masters/order/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Order</a>
			
			<a class="btn btn-warning" style="font-size: 15px" type="button"
									href="#" onclick="popOrderExcel()"><span
									class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel</a>	
	</div>
	<div class="table-responsive"> 
			<table id="orderXyz" data-toggle="table" data-url="/jewels/manufacturing/masters/order/listing.html"
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
						<th data-field="approve" data-align="center">Approve</th>
					<th data-field="actionClose" data-align="center">Open/Close Order</th>
					
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
					<th data-field="action3" data-align="center">View</th>
				</tr>
			</thead>
		</table>
	</div>
</div>





<script type="text/javascript">

	function doOrderCloseOpen(e,MtId){
		
		$.ajax({
			
			url : "/jewels/manufacturing/masters/orderMt/orderCloseOpen/"+MtId+".html",
			type : 'GET',
			success : function (data, textStatus, jqXHR){
				
				
				if(data === "-1"){
					displayConfirmation(e,'javascript:pageRelod();','Notification','Order Opened Successfully!, -Press Continue-','Continue');
				}else if(data === "-2"){
					displayConfirmation(e,'javascript:pageRelod();','Notification','Order Closed Successfully!, -Press Continue-','Continue');
				}else{
					displayMsg(this,null,'Access Denied ,Contact Admin');
				}
				
			}
		})
		
	}

	
	function pageRelod(){
		window.location.reload(true);
	}
	
	
	function popOrderExcel(){
		window.location.href="/jewels/manufacturing/masters/order/uploadExcel.html";
	}
	

</script>


<script src="/jewels/js/common/order.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
