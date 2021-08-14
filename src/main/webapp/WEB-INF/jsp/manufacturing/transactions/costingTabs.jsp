<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">

	 <div class="panel-heading" style="text-align: center;"> 
	 
	 <div>
		<label class="col-lg-2 col-sm-2 text-left">
			<span style="font-size: 18px;">Costing</span>
		</label>
		<div class="text-right">
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/transactions/costingMt.html">Costing
					Listing</a>
				<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-warning" id="costMtSubMitBtn" onClick="javascript:costingSave();"/>
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"  id="costingExcelId" href="#">Export Excel</a> 
			</div>
		
		
		
	 </div>
	 	
	</div> 
	
	
	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
			
			<li role="presentation"><a href="#changeLabour" id="cLabour"
				aria-controls="profile" role="tab" data-toggle="tab">Change Handling</a></li>
				
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="addCostingMt.jsp"%>
			</div>
 			<div role="tabpanel" class="tab-pane" id="disableChangeLabour">
				<%@include file="changeLabour.jsp"%>
			</div>	
			
		</div>
	</div>
	

	
	</div> <!-- ending the main panel -->