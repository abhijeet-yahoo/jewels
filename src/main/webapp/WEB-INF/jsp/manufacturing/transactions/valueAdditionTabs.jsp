<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

 <%@ include file="/WEB-INF/jsp/common/modal.jsp"%> 

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<div>
			<label class="col-lg-2 col-sm-2 text-left">
				<span style="font-size: 18px;">Value Addition</span>
			</label>
			<div class="text-right">
			
				<a
					class="btn btn-xs btn-warning" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/transactions/vaddMt.html">ValueAddition Listing
				</a>
				
					<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					id="vaddExcelId" href="#">Export Excel</a> 
				
					
			</div>
		</div>
	</div>
	
	
	
	<div role="tabpanel">
	
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Invoice Detail</a></li>

			<li role="presentation"><a href="#valueAddition" id="vDetails"
				aria-controls="profile" role="tab" data-toggle="tab">ValueAddition Details</a></li>
				
				
				<li role="presentation"><a href="#stockAdjustments" id="stockAdjustScreenId"
				aria-controls="profile" role="tab" data-toggle="tab">Stock Adjustments</a></li>
				
		</ul>
	
	
		<!-- Tab panes -->
		<div class="tab-content">
			
			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="addValueAdditionMt.jsp"%>
			</div>
			
			<div role="tabpanel" class="tab-pane" id="disableValueAdditionDetail">
				<%@include file="valueAdditionDetail.jsp"%>
			</div>
			
			<div role="tabpanel" class="tab-pane" id="disableStockAdjustment">
				<%@include file="stockAdjustment.jsp"%>
			</div>
			
		</div>
		
		
	</div>
	
		
 </div>
 
 
 
 <script type="text/javascript">

	$(document).ready(function(e) {
		
		$('#stockAdjustScreenId').on('click', function() {
			popMetalVadd();
		});
		
		
		
	});

</script>
 
 
 