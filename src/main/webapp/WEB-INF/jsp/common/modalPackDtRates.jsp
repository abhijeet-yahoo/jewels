<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>


<!--------- CostDt modal --------->
	
	<div class="modal fade" id="myPackRateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Rate Detail</h4>
	      </div>
	      
	      <div class="modal-body">
			
					<div class="row">
						<div class="col-sm-12 col-sm-offset-5"><span style="font-weight: bolder;color: red;text-decoration: underline;">Rate Details</span></div>
					</div>
					
					<div class="col-sm-12">&nbsp;</div>
			
					<div id="packMetalRateTableDivId">
						<table  id="packMetalRateIdTbl" data-toggle="table" 
							 data-height="250" >
							<thead>
							  <tr>
								<th data-field="metal" data-sortable="false">Metal</th>
								<th data-field="rate" data-formatter="modalCostRateFormatter">Rate</th>
								<th data-field="lossPerc" data-formatter="modalLossPercFormatter">Loss %</th>
							 </tr>
							</thead>
						</table> 
					</div>
			
			
	       
	      </div>
	      
	      <div class="modal-footer">
	        	<input type="button" value="Save" id="metalRateSaveBtnId" class="btn btn-default" onclick="javascript:popPackMetalRateSave()"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	<script type="text/javascript">
	
	
	$(document).ready(function(){
		 
		
		});
	
	 $(function() {
		    $('#packMetalRateIdTbl').bootstrapTable()
		  });
	
	 
	 
	 
	
	
	function popPackMetalRateTbl(){
		
	if(mtid>0){
		tempRateSrNo = 0;
		tempLossPercSrNo = 0;
		$("#packMetalRateIdTbl").bootstrapTable('refresh',{ 
			url : "/jewels/marketing/transactions/packMetal/rateMaster/listing.html?partyId="+$('#party\\.id').val()+"&packMtId="+mtid,
		});	
	}
		
		
	}
	
	
	var tempRateSrNo = 0;
	function modalCostRateFormatter(value, row, index){
		var tempId = 'rateval' + Number(index);
		return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:90px" value="'+ value+ '" onchange="javascript:updateRates(this,'+index+')"  />';
	}
	
	function updateRates(param,idx){
 		$('#packMetalRateIdTbl').bootstrapTable('updateRow', {
			index : Number(idx),
			row : {
				rate : param.value,
			}
		});
 	}
	
	
	
	var tempLossPercSrNo = 0;
	function modalLossPercFormatter(value, row, index){
		var tempId = 'lossval' + Number(index);
		return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateMtLossPerc(this,'+index+')"  />';
	}
	
	function updateMtLossPerc(param,idx){
 		$('#packMetalRateIdTbl').bootstrapTable('updateRow', {
			index : Number(idx),
			row : {
				lossPerc : param.value,
			}
		});
 	}
	
	
	function popPackMetalRateSave(){
		
		$('#myPackRateModal').modal('hide');
		
		$('#metalRateSaveBtnId').attr('disabled','disabled');
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		var tempData =  JSON.stringify($('#packMetalRateIdTbl').bootstrapTable('getData'));
		
		$.ajax({
			url:"/jewels/marketing/transactions/packMetalrate/addTabData.html",
			type:"GET",
			data:{
				tabData:tempData,
				packMtId:mtid,
				
			},
			success:function(data){
				$('#metalRateSaveBtnId').removeAttr('disabled','disabled');
				if(data === '-1'){
					popPackMetalRateTbl();
					popPackDt();
					
					$('#hideOnPageLoadItem').css('display','none');
					
				}else{
					displayMsg(this,null,"Error Occoured,Contact Support");
				}
				
				$.unblockUI();

			}
		});
		
	}
	
	
	
	
	
	</script>
	
	<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>