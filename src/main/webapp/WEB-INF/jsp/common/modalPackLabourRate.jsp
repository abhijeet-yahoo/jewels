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

<div class="modal fade" id="modalPackLabourRate" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Labour Rate</h4>
			</div>

			<div class="modal-body">
			 
					
					
					<div class="row">&nbsp;</div>
					
					<div class="row">
					<label for="labourRate" class="col-sm-5 control-label">Labour Per Gram Rate</label>
					<div class="col-sm-7" >
					<input type="number" id="labourRate" class="form-control" autocomplete="false">
					</div>
					</div>
						
						
			
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="labourRateSaveBtnId" onclick="javascript:updatePackLabourRate()">Update Rate</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>


		</div>


	</div>

</div>

<script type="text/javascript">



function updatePackLabourRate(){
	 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	
	if($('#labourRate').val() !=''){
		
		$('#labourRateSaveBtnId').attr('disabled','disabled');
		
		$
		.ajax({

			url : "/jewels/marketing/transactions/packLabDt/updatePackLabourRate.html?packMtId="+mtid+"&labourRate="+$('#labourRate').val(),
			type : 'GET',
			success : function(data) {
				$('#labourRateSaveBtnId').removeAttr('disabled','disabled');
					if(data==="1"){
						displayInfoMsg(this,null,"Labour Rate Update Successfully");	
						popPackDt(disableFlg);
						$('#labourRate').val('');
					}
					
					
					
					$.unblockUI();
					
			}
		})
		
		
		
	}else{
		displayMsg(this,null,"Shape,Quality and Group or Size Compulsary");	
		$.unblockUI();
	}
	
	
}

</script>
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>