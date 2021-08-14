<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


	<div class="panel-body">
	

		<div class="col-xs-12">
			<hr style="width: 100%; list-style:disc; height: 2px; background-color: red;" />
		</div>

		<div role="tabpanel">
	
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				
				<li role="presentation" class="active"><a href="#metalAdjust" id="metalAdjustScreenId"
					aria-controls="home" role="tab" data-toggle="tab">Metal Adjustment</a></li>
	
				<li role="presentation"><a href="#compAdjust" id="compAdjustScreenId" 
					aria-controls="profile" role="tab" data-toggle="tab">Component Adjustment</a></li>
					
						<li role="presentation"><a href="#stoneAdjustment" id="stoneAdjustScreenId"
					aria-controls="home" role="tab" data-toggle="tab">Diamond Adjustment</a></li>
					
			</ul>
					

<!-- Tab panes -->
			<div class="tab-content">
				
				<div role="tabpanel" class="tab-pane active" id="metalAdjust">
					<%@include file="metalStockAdjustment.jsp"%>
				</div>
				
				<div role="tabpanel" class="tab-pane" id="disableCompAdjust">
					<%@include file="componentStockAdjustment.jsp"%>
				</div>
				
				
				<div role="tabpanel" class="tab-pane" id="stoneAdjustmentId">
					<%@include file="vaddStoneAdjustment.jsp"%>
				</div>
				
			</div>
		
		
	  </div>
		
		
	
	</div> <!-- ending the panel body -->

<script type="text/javascript">

$(document).ready(function(e) {
	$('#compAdjustScreenId').on('click', function() {
		
		popCompVadd();
		
		$('#importData').prop('disabled', true);
		$('#saveAdj').prop('disabled', true);
		$('#fifoBtn').prop('disabled', true);
		
	});
	
	$('#metalAdjustScreenId').on('click', function() {
		popMetalVadd();
		
		$('#importMetalData').prop('disabled', true);
		$('#saveMetalAdj').prop('disabled', true);
		$('#fifoMetalBtn').prop('disabled', true);
	});
	
	
	$('#stoneAdjustScreenId').on('click', function() {
		popVaddStone();
		
	});
	
	
	//$("#modalDialog").modal("hide");
});

</script>

