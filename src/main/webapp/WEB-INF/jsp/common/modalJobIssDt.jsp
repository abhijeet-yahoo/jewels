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



	<!--------- JobIssDt modal --------->
	
	<div class="modal fade" id="myJobIssDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Job Issue Detail</h4>
	      </div>
	      
	      <div class="modal-body">
				
				<div class="form-group" id="jobIssDtModalDivId">
				
				
				<div class="row">
				
				<div class="col-xs-12">
				<div class="col-sm-1">
					
						<label for="inputLabel3">Process</label>	
							</div>
							<div class="col-sm-7">
					   		<textarea  name="processDtTextBox" id="processDtTextBox" style="height: 2cm; width:13cm"  disabled="disabled" ></textarea>
			       	</div>
			       	<div class="col-sm-1">
			      <button  class="glyphicon glyphicon-eject" style="text-align: center;" data-toggle="modal" data-target="#myProcessDtModal" onclick="javascript:popProcessDt(0)" ></button>	
			  					</div>
				
				
				</div>
				
				</div>
				
		
					<div class="row">
						<div class="col-lg-12">
							<input type="hidden" path="id" id="modalJobIssDtId"/>
							<input type="hidden" id="vJobIssMtId" name="vJobIssMtId" />
							<input type="hidden" id="vProcessDtId" name="vProcessDtId" />
							<input type="hidden" id="vProcessNm" name="vProcessNm" />
							
						</div>
					</div>
					
						</div>
				
		
	       
	      </div>
	      
	      <div class="modal-footer">
	        	<input type="button" value="Save" class="btn btn-default" onclick="javascript:popJobIssDtSave()" id="jobIssBtnId"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       </div>
	      
	      
	      
	    
	      
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">


	
	function popJobIssDtSave(){
		
		$.ajax({
				url : "/jewels/manufacturing/transactions/jobIssDt/saveEdit.html?mtId="+$('#vJobIssMtId').val()
						+"&dtId="+$('#modalJobIssDtId').val()+"&vProcessDtId="+$('#vProcessDtId').val(),
				type : 'GET',
				success : function(data) {
					
					if(data != -1){
						displayInfoMsg(this,null,"Record Updated Successfully");
						$('#myJobIssDtModal').modal('hide');
					}
				}
			}); 
		 }
	

	var processDtStatus = "false";
	function popProcessDt(val) {
		
		
			if(val === 0){
				
				if(processDtStatus === 'false'){
					$("#processDtIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/labourType/listing.html",
					});
					processDtStatus = true;
				}
			}else{
				$("#processDtIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/labourType/listing.html",
				});
				$('#processDtTextBox').val('');
			}
			
					
	}
	

	setTimeout(function(){
		$('#processDtIdTbl').bootstrapTable({}).on(
				'load-success.bs.table',
				function(e, data) {
					
					var data = $('#processDtIdTbl').bootstrapTable('getData');
					var tempName = $.map(data, function(item) {
						return item.name;
					});
					
					tempName = tempName+'';
					
					var tableProcessList = tempName.split(",");
					var selectProcessList = $('#vProcessNm').val().split(",");

					for(i=0;i<selectProcessList.length;i++){
						for(j=0;j<tableProcessList.length;j++){
							
							if(selectProcessList[i].trim() === tableProcessList[j].trim()){
								 $("#processDtIdTbl").bootstrapTable('updateRow', {
										index : j,
										row : {
											state : true,
										}
									}); 
							}
						}
					}
			  });
	
	}, 500);
	

	
	function popProcessDtSave(){
		
		$("#myProcessDtModal").modal("hide");
		var	data = $('#processDtIdTbl').bootstrapTable('getSelections');
		var	processNm = $.map(data, function(item) {
				return item.name;
			});
		
		var	vProcDtId = $.map(data, function(item) {
			return item.id;
		});
		
		$('#vProcessDtId').val(vProcDtId);
		
		
		var tempRes = processNm.toString().replace(/,/g, "\n");
		$('#processDtTextBox').val(processNm);
	}
	
	
		
	
	</script>
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	
	
	