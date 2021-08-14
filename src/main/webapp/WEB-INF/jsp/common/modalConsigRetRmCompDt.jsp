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



	<!---------ConsigRetRmCompDt modal --------->
	
	<div class="modal fade" id="myConsigRetRmCompDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Component Receive Detail</h4>
	      </div>
	      
	      <div class="modal-body">
				
				<div class="form-group" id="consigRetRmCompDtModalDivId">
				<form:form commandName="consigRetRmCompDt" id="consigRetRmCompDtFormId"
					action="/jewels/marketing/transactions/consigRetRmCompDt/add.html"
					cssClass="form-horizontal consigRetRmCompDtForm">
				
				
				<div class="row">
				
				<div class="col-xs-12">
				
						<div class="row">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Location</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Metal</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Component</label>
						</div>
						
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Purity</label>
						</div>
						
						
						
					</div>
					<div class="row">
						<div class="col-lg-3 col-sm-3">
							<form:select path="department.id" class="form-control">
									<form:option value="" label=" Select Location " />
									<form:options items="${locationCompMap}" />
								</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="metal.id" class="form-control">
									<form:option value="" label=" Select Metal" />
									<form:options items="${metalCompMap}" />
								</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="component.id" class="form-control">
									<form:option value="" label=" Select Component" />
									<form:options items="${componentCompMap}" />
								</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="purity.id" class="form-control">
									<form:option value="" label=" Select Purity " />
									<form:options items="${purityCompMap}" />
								</form:select>
						</div>
						
					</div>
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					<div class="row"> 
					
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Color</label>
						</div>
					
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Qty</label>
						</div>
							
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Metal Wt</label>
						</div>
						
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Rate</label>
						</div>	
					
					</div>
					
					
						<div class="row"> 
	
					<div class="col-lg-3 col-sm-3">
									<form:select path="color.id" class="form-control">
									<form:option value="" label=" Select Color " />
									<form:options items="${colorCompMap}" />
								</form:select>
			
						</div>
					<div class="col-lg-3 col-sm-3">
							<form:input path="qty" cssClass="form-control"  autocomplete="off"/>
						</div>
					
												
					<div class="col-lg-3 col-sm-3">
							<form:input path="metalWt" cssClass="form-control"  autocomplete="off"  onchange="javascript:setAmountComp()"/>
						</div>
						
					<div class="col-lg-3 col-sm-3">
							<form:input path="rate" cssClass="form-control"  autocomplete="off"  onchange="javascript:setAmountComp()"/>
						</div>	
						
					
					</div>
		
		<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					<div class="row"> 
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-4 text-right">Amount</label>
						</div>
					</div>
					
					
					<div class="row"> 
					
					<div class="col-lg-3 col-sm-3">
							<form:input path="amount" cssClass="form-control"  autocomplete="off" readonly="true"/>
						</div>		
					
					</div>
				
				
				</div>
				
				</div>
				
		
					<div class="row">
						<div class="col-lg-12">
							<form:input type="hidden" path="id" id="modalConsigRetRmCompDtId"/>
							<input type="hidden" id="vConsigRetRmCompMtId" name="vConsigRetRmCompMtId" />
							
						</div>
					</div>
					
				</form:form>
			</div>
				
		
	       
	      </div>
	      
	      <div class="modal-footer">
	        	<input type="button" value="Save" class="btn btn-default" onclick="javascript:popConsigRetRmCompDtSave()" id="consigRetRmCompBtnId"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">

	$(document).ready(function(e) {
		$(".consigRetRmCompDtForm").validate({
			rules : {
				'department.id' :{
					required : true,
				},
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
	


	
	function popConsigRetRmCompDtSave(){
		var postData = $("#consigRetRmCompDtFormId").serializeArray();
	
		var formURL = $("#consigRetRmCompDtFormId").attr("action");
		$('#consigRetRmCompBtnId').attr('disabled', 'disabled');
		 if($(".consigRetRmCompDtForm").valid()){
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					$('#consigRetRmCompBtnId').removeAttr('disabled');
					if(data === '1'){
						displayInfoMsg(this,null,"Record Added Successfully !");
						popConsigRetRmCompDt();
						
						 $('#consigRetRmCompDtModalDivId input[type="text"],textarea').val('');
						 $('#consigRetRmCompDtModalDivId input[type="number"]').val('0');
						 $('#consigRetRmCompDtModalDivId').find('select').val('');
						 $('#modalConsigRmCompDtId').val('');
					
					}else if(data === '2'){
						 displayInfoMsg(this,null,"Record Updated Successfully !");
						 $('#myConsigRetRmCompDtModal').modal('hide');
						 popConsigRetRmCompDt();
						 
						 $('#consigRetRmCompDtModalDivId input[type="text"],textarea').val('');
						 $('#consigRetRmCompDtModalDivId input[type="number"]').val('0');
						 $('#consigRetRmCompDtModalDivId').find('select').val('');
						 $('#modalConsigRetRmCompDtId').val('');
						 
					}else{
						displayMsg(this,null,data);
					}
					
				},
				error : function(data, textStatus, jqXHR) {
					displayMsg(this,null,"Error Occoured , Contact Admin !");
				}
		
			});
		
		 }
		 $('#consigRetRmCompBtnId').removeAttr('disabled');
	}
	
	function setAmountComp() {
		var metalWt = $('#consigRetRmCompDtModalDivId #metalWt').val();
		var rate = $('#consigRetRmCompDtModalDivId #rate').val();
		var defRate = rate * 1;
		var defMetalWt = metalWt *1;
		var amount = metalWt * defRate;

		$('#consigRetRmCompDtModalDivId #rate').val(defRate.toFixed(2));
		$('#consigRetRmCompDtModalDivId #amount').val(amount.toFixed(2));
		$('#consigRetRmCompDtModalDivId #metalWt').val(defMetalWt.toFixed(3));
		
	}

	
	
	</script>
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	
	
	