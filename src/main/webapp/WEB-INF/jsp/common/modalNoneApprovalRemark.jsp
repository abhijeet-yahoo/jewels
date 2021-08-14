<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
	
	.modal-ku {
  width: 850px;
  margin: auto;
}
	
</style>
   
   

   
    <div class="modal fade" id="myNoneApprovalRemarkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-xl" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>

				<h4 class="modal-title" id="myModalLabel">None Approval Remark</h4>
			</div>

			<div class="modal-body">
					
					<div class="form-group col-sm-12">
					<label class="control-label" for="noneApprovalRemark">Remark</label>
			
					<textarea  rows="5" cols="6" id="noneApprovalRemark" name="noneApprovalRemark" class="form-control"></textarea>
					</div>
					
					<div class="row">
						<div class="col-lg-12">
							<input type="hidden" id="vvBagIds" name="vvBagIds" />
							<input type="hidden" id="vDeptFromIds" name="vDeptFromIds" />
							<input type="hidden" id="vDeptToIds" name="vDeptToIds" />
							<input type="hidden" id="vTrandate" name="vTrandate" />
						</div>
					</div>
					
					<div class="modal-footer">
					<input type="button" value="Save" id="noneApprovalReturnBtnId" class="btn btn-primary" onclick="javascript:noneApprovalReturnSave()"/>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
		
</div>
</div>


<script type="text/javascript">

		function noneApprovalReturnSave(){
			
			$('#noneApprovalReturnBtnId').attr("disabled", true);
			
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/notApprovalAndReturn.html?deptTo="+$('#vDeptToIds').val()
				+"&vBagNo="+$('#vvBagIds').val()+"&tranDate="+$("#vTrandate").val()+"&remark="+$('#noneApprovalRemark').val(),
			
				type : "POST",
				success : function(data, textStatus, jqXHR) {
					
					if (Number(data) == 1) {

						displayInfoMsg(event, this,'Approval Return sucessfully !');
						$('#noneApprovalReturnBtnId').attr("disabled", false);
						
						$('#noneApprovalRemark').val('')
						$('#myNoneApprovalRemarkModal').modal('show');
					}else{
						$('#noneApprovalReturnBtnId').attr("disabled", false);
							displayMsg(event, this,data);
						}			
					popBagDetails();
					
						

				},
				error : function(jqXHR, textStatus, errorThrown) {

				}

			});
		}




</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/common/common.js' />"></script>
<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />







