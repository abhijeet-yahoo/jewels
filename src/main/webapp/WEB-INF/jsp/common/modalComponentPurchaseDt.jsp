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



	<!--------- ComponentPurchaseDt modal --------->
	
	<div class="modal fade" id="myComponentPurchaseDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Component PUrchase Detail</h4>
	      </div>
	      
	      <div class="modal-body">
				
				<div class="form-group" id="componentPurchaseDtModalDivId">
				<form:form commandName="componentPurchaseDt" id="componentPurchaseDtFormId"
					action="/jewels/manufacturing/transactions/componentPurchaseDt/add.html"
					cssClass="form-horizontal componentPurchaseDtForm">
				
				
				<div class="row">
				
				<div class="col-xs-12">
				
						<div class="row">
					
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Metal</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Component</label>
						</div>
						
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Purity</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Color</label>
						</div>
					
						
						
					</div>
					<div class="row">
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="metal.id" class="form-control" onChange="javascript:popPurity(this.value);">
									<form:option value="" label=" Select Metal" />
									<form:options items="${metalMap}" />
								</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="component.id" class="form-control">
									<form:option value="" label=" Select Component" />
									<form:options items="${componentMap}" />
								</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
						<div id="purityId">
							<form:select path="purity.id" class="form-control">
									<form:option value="" label=" Select Purity " />
									<form:options items="${purityMap}" />
								</form:select>
								</div>
						</div>
					
					<div class="col-lg-3 col-sm-3">
									<form:select path="color.id" class="form-control">
									<form:option value="" label=" Select Color " />
									<form:options items="${colorMap}" />
								</form:select>
			
						</div>
				
					</div>
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					<div class="row"> 
					
					
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Qty</label>
						</div>
							
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Metal Wt</label>
						</div>
						
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Rate</label>
						</div>	
				
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Amount</label>
						</div>	
					
					</div>
					
					
						<div class="row"> 
	
							<div class="col-lg-3 col-sm-3">
							<form:input path="qty" cssClass="form-control"  autocomplete="off"/>
						</div>
					
												
					<div class="col-lg-3 col-sm-3">
							<form:input path="metalWt" cssClass="form-control"  autocomplete="off"  onchange="javascript:setAmountComp()"/>
						</div>
						
					<div class="col-lg-3 col-sm-3">
							<form:input path="rate" cssClass="form-control"  autocomplete="off"  onchange="javascript:setAmountComp()"/>
						</div>	
					
					<div class="col-lg-3 col-sm-3">
							<form:input path="amount" cssClass="form-control"  autocomplete="off" readonly="true"/>
						</div>		
						
					
					</div>
					
						
					
					
				
				</div>
				
				</div>
				
		
					<div class="row">
						<div class="col-lg-12">
							<form:input type="hidden" path="id" id="modalCompPurcDtId"/>
							<input type="hidden" id="vCompPurcMtId" name="vCompPurcMtId" />
							
						</div>
					</div>
					
				</form:form>
			</div>
				
		
	       
	      </div>
	      
	      <div class="modal-footer">
	        	<input type="button" value="Save" class="btn btn-default" onclick="javascript:popComponentPurchaseDtSave()" id="ComponentPurchaseDtBtnId"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">

	$(document).ready(function(e) {
		$(".componentPurchaseDtForm").validate({
			rules : {
			
				'metal.id' : {
					required : true,
				},
				'component.id' : {
					required : true,
				},
				'purity.id' : {
					required : true,
				},
				'color.id' : {
					required : true,
				},
				metalWt : {
					required : true,
					greaterThan : "0,0",
				}

			},

			messages : {
				metalWt : {
					greaterThan : "This field is required"
				}
			},

		});
		
	});
	


	
	function popComponentPurchaseDtSave(){
		var postData = $("#componentPurchaseDtFormId").serializeArray();
	
		var formURL = $("#componentPurchaseDtFormId").attr("action");
		$('#ComponentPurchaseDtBtnId').attr('disabled', 'disabled');
		 if($(".componentPurchaseDtForm").valid()){
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					$('#ComponentPurchaseDtBtnId').removeAttr('disabled');
					if(data === '1'){
						displayInfoMsg(this,null,"Record Added Successfully !");
						popComponentPurchaseDt();
						
						 $('#componentPurchaseDtModalDivId input[type="text"],textarea').val('');
						 $('#componentPurchaseDtModalDivId input[type="number"]').val('0');
						 $('#componentPurchaseDtModalDivId').find('select').val('');
						 $('#modalCompPurcDtId').val('');
					
					}else if(data === '2'){
						 displayInfoMsg(this,null,"Record Updated Successfully !");
						 $('#myComponentPurchaseDtModal').modal('hide');
						 popComponentPurchaseDt();
						 
						 $('#componentPurchaseDtModalDivId input[type="text"],textarea').val('');
						 $('#componentPurchaseDtModalDivId input[type="number"]').val('0');
						 $('#componentPurchaseDtModalDivId').find('select').val('');
						 $('#modalCompPurcDtId').val('');
						 
					}else{
						displayMsg(this,null,data);
					}
					
				},
				error : function(data, textStatus, jqXHR) {
					displayMsg(this,null,"Error Occoured , Contact Admin !");
				}
		
			});
		
		 }
		 $('#componentPurchaseDtBtnId').removeAttr('disabled');
	}
	
	function setAmountComp() {
		var metalWt = $('#componentPurchaseDtModalDivId #metalWt').val();
		var rate = $('#componentPurchaseDtModalDivId #rate').val();
		var defRate = rate * 1;
		var defMetalWt = metalWt *1;
		var amount = metalWt * defRate;

		$('#componentPurchaseDtModalDivId #rate').val(defRate.toFixed(2));
		$('#componentPurchaseDtModalDivId #amount').val(amount.toFixed(2));
		$('#componentPurchaseDtModalDivId #metalWt').val(defMetalWt.toFixed(3));
		
	}


	function popPurity(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/purityComp/list.html' />?metalId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#purityId").html(data);
					}
				});
	}
	
	
	</script>
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	
	
	