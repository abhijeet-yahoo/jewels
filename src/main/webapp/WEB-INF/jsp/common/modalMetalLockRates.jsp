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


<!--------- MetalLock modal --------->
	
	<div class="modal fade" id="metalLockRateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
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
					
					<form:form commandName="metalLock" id="metalLockDivId"
				cssClass="metalLockForm">
				
				<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
				
						<div class="form-group">
					<label for="name" class="col-sm-2 control-label">MetalLock Date :</label>
					<div class="col-sm-3">
						<form:input path="metalLockDt" cssClass="form-control" />
						<form:errors path="metalLockDt" />
					</div>
				</div>
					
				
				
				</form:form>
					
					<div class="col-sm-12">&nbsp;</div>
			
					<div id="metalLockRateTableDivId">
						<table  id="metalLockRateIdTbl" data-toggle="table" 
							data-click-to-select="false" data-side-pagination="server" 
							data-striped="true" data-height="250" >
							<thead>
							  <tr>
								<th data-field="metal" data-sortable="false">Metal</th>
								<th data-field="rate" data-formatter="modalMetalLockRateFormatter">Rate</th>
								
							 </tr>
							</thead>
						</table> 
					</div>
			
			
	       
	      </div>
	      
	      <div class="modal-footer">
	        	<input type="button" value="Save" id="metalRateSaveBtnId" class="btn btn-default" onclick="javascript:popMetalLockRateSave()"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<input type="hidden" id="metalLockDate" name="metalLockDate">
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	<script type="text/javascript">


	$(document)
	.ready(
			function(e) {
				$("#metalLockRateModal #metalLockDt").datepicker({
					dateFormat : 'dd/mm/yy'
				});

			});
	
	
	function popMetalLockRateTbl(){
	
		$("#metalLockRateIdTbl").bootstrapTable('refresh',{ 
			url : "/jewels/manufacturing/masters/metalLockRate/rateMaster/listing.html",
		});	
	}
	
	
	var tempRateSrNo = 0;
	function modalMetalLockRateFormatter(value, row, index){
		var tempId = 'rateval' + Number(index);
		return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:90px" value="'+ value+ '" onchange="javascript:updateRates(this,'+index+')"  />';
	}
	
	function updateRates(param,idx){
		$('#metalLockRateIdTbl').bootstrapTable('updateRow', {
 			index : Number(idx),
			row : {
				rate : param.value,
			}
		});
 	}
	
	

	
	
	function popMetalLockRateSave(){

		if($('#metalLockDt').val() != "" ){
		$('#metalLockDate').val($('#metalLockDt').val());
		
		$('#metalRateSaveBtnId').attr('disabled','disabled');
		var tempData =  JSON.stringify($('#metalLockRateIdTbl').bootstrapTable('getData'));
		
		$.ajax({
			url:"/jewels/manufacturing/masters/metalLockRate/addTabData.html",
			type:"GET",
			data:{
				tabData:tempData,
				mtlLockDt:$('#metalLockDate').val(),
			},
			success:function(data){
				$('#metalRateSaveBtnId').removeAttr('disabled','disabled');
				if(data === '-1'){
					popOrderMetalRateTbl();
					$('#metalLockRateModal').modal('hide');
					window.location.reload(true);
				}else{
					displayMsg(this,null,"Error Occoured,Contact Support");
				}
				
				
			}
		});

		}else{
			displayMsg(this,null,"MetalLock date is compulsory");
			}
		
	}
	
	
	
	
	
	</script>