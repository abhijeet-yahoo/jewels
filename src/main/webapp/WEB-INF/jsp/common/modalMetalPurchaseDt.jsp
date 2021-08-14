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






<!--------- Metal Purchase modal --------->
	
	<div class="modal fade" id="myMetalPurchaseDtModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Metal Purchase Detail</h4>
	      </div>
	      
	      <div class="modal-body">
				
				<div class="form-group" id="metalPurchaseDtModalDivId">
				<form:form commandName="metalPurchaseDt" id="metalPurchaseDtEnt"
					action="/jewels/manufacturing/transactions/metalPurchaseDt/add.html"
					cssClass="form-horizontal metalInwardDtEntForm">
				
					
						<div class="row">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-4 text-right">Metal</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Purity</label>
						</div>
						
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Color</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
						
							<label for="inputLabel3" class=".col-lg-4 text-right">Invoice Wt</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Rate</label>
						</div>
						
						
					</div>
					<div class="row">
						<div class="col-lg-4 col-sm-4">
							<form:select path="metal.id" class="form-control"
												onChange="javascript:popPurity(this.value);">
												<form:option value="" label="Select Metal" />
												<form:options items="${metalMap}" />
											</form:select>
						</div>
						
							<div class="col-lg-2 col-sm-2">
							<div id="purityId">
												<form:select path="purity.id" class="form-control">
													<form:option value="" label="Select Purity" />
													<form:options items="${purityMap}" />
												</form:select>
											</div>
						</div>
						
						<div class="col-lg-2 col-sm-2">
									<div id="colorId">
												<form:select path="color.id" class="form-control">
													<form:option value="" label="Select Color" />
													<form:options items="${colorMap}" />
												</form:select>
											</div>
						
						
							
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="invWt" cssClass="form-control" onchange="javascript:setAmount();"/>
						</div>
						
							<div class="col-lg-2 col-sm-2">
							<form:input path="rate" cssClass="form-control"	onchange="javascript:setAmount();" />
						</div>
						
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					
					<div class="row">
					
					
							
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Amount</label>
						</div>
						
						
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">In 999</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Remark</label>
						</div>
						
						
					</div>
					<div class="row">
					
				
							
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="amount" cssClass="form-control" readonly="readonly" />
						</div>
						
						
						
						
						<div class="col-lg-2 col-sm-2">
							<form:checkbox path="in999"  />
						</div>
						
					
						
						<div class="col-lg-4 col-sm-4">
							<form:input path="remark" cssClass="form-control" />
						</div>
						
						
					
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					
					
					
					
					<div class="row">
					
						<div class="col-lg-12">
						
											<form:input type="hidden" id="metalPurchaseDtEntryId" path="id" />
											<form:input type="hidden" id="balance" path="balance" />  
											 <input type="hidden" id="pInvNo" name="pInvNo" /> 
											 
											
											 
						</div>
					</div>
					
				</form:form>
			</div>
				
			
			
					
			
			
					
			
	       
	      </div>
	      
	      <div class="modal-footer">
	         <input type="submit" value="Save" class="btn btn-default" onclick="javascript:popMetalPurcSave()"/>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       </div> 
	      
	    </div>
	  </div>
	</div>
	
	
	
<script type="text/javascript">
		
function popMetalPurcSave(){
	
				
				$("#pInvNo").val($("#invNo").val());
				
				
				
				var postData = $("#metalPurchaseDtEnt").serializeArray();
				var formURL = $("#metalPurchaseDtEnt").attr("action");
				
					$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,
							success : function(data, textStatus, jqXHR) {
						
								popMetalPurchaseDt();
								
								
								

								$('form#metalPurchaseDtEnt input[type="text"],textarea, select').val('');
								$('form#metalPurchaseDtEnt select').val('');

								$("#invWt").val('0.0');
								$('#rate').val('0.0');
								$('#metal\\.id').focus();
						if(data == -1){
							$('#myMetalPurchaseDtModal').modal('hide');
							$('#metalPurchaseDtEntryId').val('');
						}

							},

							error : function(jqXHR, textStatus,
									errorThrown) {
							}
						});
				
			}
	
	

$('#myMetalPurchaseDtModal').on('shown.bs.modal', function () {
	 $(document.documentElement).css('overflow', 'hidden');
	 $('#myMetalPurchaseDtModal').show().css('overflow', 'auto');
});




$('#myMetalPurchaseDtModal').on('hidden.bs.modal', function () {
	 $(document.documentElement).css('overflow', 'scroll');
	  $('#metalPurchaseDtModalDivId input[type="text"],textarea').val('');
	  $('#metalPurchaseDtModalDivId input[type="number"]').val('0');
	  $('#metalPurchaseDtModalDivId').find('select').val('');
	  $('#metalPurchaseDtEntryId').val('');
	
	})
		
		
		</script>
		
		
		

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>