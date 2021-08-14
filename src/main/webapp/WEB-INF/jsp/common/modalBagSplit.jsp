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
   
   

   
    <div class="modal fade" id="myBagSplitModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-xl" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>

				<h4 class="modal-title" id="myModalLabel">Bag Split</h4>
			</div>

			<div class="modal-body">

				
					<div class="panel panel panel-default col-sm-12">
					
					
					<div class="form-group col-sm-4">
					<label class="control-label" for="bagNo">Bag No.</label>
					<input type="text" id="bagId" name="bagId" class="form-control" readonly="readonly" >
					
					</div>
					
					<div class="form-group col-sm-4">
					<label class="control-label" for="bagQty">Bag Qty.</label>
					<input type="text" id="bagQty" name="bagQty" class="form-control" readonly="readonly" >
					
					</div>
					
					

					<div class="form-group col-sm-3">
						<label class="control-label" for="splitQty">Split Qty.</label> <input
							type="number" id="splitQty" name="splitQty" class="form-control"
							min="0" pattern="[0-9]"
							onkeypress="return !(event.charCode == 46)" step="1"
							title="Must be an integer number">


					</div>
					
				
						<div class="form-group col-sm-3">
					<label class="control-label" for="vWeight">Metal Wt.</label>
					<input type="text" id="vWeight" name="vWeight" class="form-control"  readonly="readonly">
					
					</div>
				
				 	<div class="form-group col-sm-3">
					<label class="control-label" for="splitWeight">Split Metal Wt.</label>
					<input type="text" id="splitWeight" name="splitWeight" class="form-control" >
					
					</div>


								

				</div>
				<div class="modal-footer">
					<input type="button" value="Split" id="splitBtnId" class="btn btn-primary" onclick="javascript:splitBagSave()"/>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
			
		
		
</div>
</div>


<script type="text/javascript">

$(document)
.ready(
		function(e) {
			/* $("#bagId")
					.autocomplete(
							{
								source : "<spring:url value='/manufacturing/transactions/bagSearch/jobBag/list.html' />",
								minLength : 2
							});
		 */
			
	

		});
		
		
		function splitBagSave(){
			 

			
			 $('#splitBtnId').attr('disabled', 'disabled');
			
	 	$.ajax({
							url : '<spring:url value='/manufacturing/transactions/splitBagSave.html' />?bagNo='
									+ $('#bagId').val()
									+ "&bgQty="
									+$('#bagQty').val()
									+"&splitQty="
									+$('#splitQty').val()
									+ "&weight="
									+$('#vWeight').val()
									+"&splitWeight="
									+$('#splitWeight').val(),
								
							type : 'GET',
							success : function(data) {
								if(data.indexOf("error")!="-1"){
									displayMsg(this,null,data);
								}else{
									displayInfoMsg(event, this, 'Bag Split Successfully   ! '+"\n"+'New Bag No = '+data);
									 $('#bagMt\\.name').trigger('onchange');
									 $('#myBagSplitModal').modal('hide');
								}
									
								
								 $('#splitBtnId').removeAttr('disabled');
							
							}
						})




	
}




</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/common/common.js' />"></script>
<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />







