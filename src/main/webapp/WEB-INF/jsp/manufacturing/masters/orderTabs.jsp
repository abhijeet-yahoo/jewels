<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div id="orderMainDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<div>
			<label class="col-lg-5 text-left">
				<span style="font-size: 18px;">Order</span>
			</label>
			<div class="text-right">
			<a class="btn btn-xs btn-info" id="quotPickupBtnId" style="font-size: 14px" type="button" onclick="javascript:popQuotToOrderPickUp()" >Pick From Quot</a>
			<a class="btn btn-xs btn-info" id="orderPickupBtnId" style="font-size: 14px" type="button" onclick="javascript:popOrderPickUp()" >Pick From Order </a>	
			
				<input type="button" value="Save" style="font-size: 14px" class="btn btn-xs btn-warning" onClick="javascript:orderSave();" /> 
				<a id="orderListingBtnId" class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/masters/order.html">Order
					Listing</a>
			</div>
		</div>
	</div>

	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
			<li role="presentation"><a href="#orderQuality" id="oQuality"
				aria-controls="profile" role="tab" data-toggle="tab">Order Quality</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="addOrder.jsp"%>
			</div>
 			<div role="tabpanel" class="tab-pane" id="disableOrderQuality">
				<%@include file="orderQuality.jsp"%>
			</div>

		</div>
	</div>
</div>
</div>
<script type="text/javascript">

 </script>
 