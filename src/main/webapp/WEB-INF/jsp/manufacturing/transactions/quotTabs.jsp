
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>




<div id="quotMtDivId">
<div class="panel panel-primary">

	 <div class="panel-heading" style="text-align: center;"> 
	 
		 <div>
			<label class="col-lg-2 col-sm-2 text-left">
				<span style="font-size: 18px;">Quotation</span>
			</label>
			<div class="text-right">
			<a class="btn btn-xs btn-info" id="orderPickupBtnId" style="font-size: 14px" type="button" onclick="javascript:popOrderPickUp()" >Pick Up From Order</a>
			
			<a class="btn btn-xs btn-info" id="quotPickupBtnId" style="font-size: 14px" type="button" onclick="javascript:popQuotPickUp()" >Pick Up</a>
			
			<a id="quotMtListBtnId" class="btn btn-xs btn-info" style="font-size: 14px" type="button"
						href="/jewels/manufacturing/transactions/quotMt.html">Quotation Listing</a>
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-warning" id="quotMtSubMitBtn" onClick="javascript:quotSave();"/>
					
					<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					id="quotExcelId" href="#">Export Excel</a> 
			</div>
			
		 </div>
	 	
	</div> 
	
	
	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
			
			<li role="presentation"><a href="#quotQuality" id="qQuality"
				aria-controls="profile" role="tab" data-toggle="tab">Quotation Quality</a></li>
				
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
 			
 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="addQuotMt.jsp"%>
			</div>
 			
 			<div role="tabpanel" class="tab-pane" id="disableQuotQuality">
				<%@include file="quotQuality.jsp"%>
			</div>	
			
		</div>
	</div>
	

	
	</div> <!-- ending the main panel -->
	</div>