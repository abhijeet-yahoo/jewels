<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Order Listing</a></li>
			<li role="presentation"><a href="#designComponent"
				aria-controls="profile" role="tab" data-toggle="tab">Order Details</a></li>
		</ul>
	
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="orderDesigns.jsp"%>
			</div>
			<div role="tabpanel" class="tab-pane" id="designComponent">
<%-- 				<%@include file="orderDetailsTab.jsp"%> --%>
			</div>
		</div>
	</div>
</div>

