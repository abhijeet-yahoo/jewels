<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>




<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>


<div class="modal fade" id="multiComponentModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>

				<h4 class="modal-title" id="myModalLabel">Multicomponent Addition</h4>
			</div>



			<div class="modal-body">


				<form:form commandName="compTran" id="multiComponentAdditionFormId" 
				action="/jewels/manufacturing/transactions/multiComponentAddition/add.html"
					cssClass="multiAdditionForm">


					<div class="row" id="xyz">
					
					<div class="form-group  col-sm-4">

							<label class="control-label" for="location">Location</label>
							<div id="deptDivId">
								<form:select path="department.id" id="departmentId" class="form-control">
									<form:option value="" label=" Select Location " />
									<form:options items="${deptMap}" />
								</form:select>


							</div>


						</div>

						<div class="form-group  col-sm-4">

							<label class="control-label" for="comp">Component</label>
							<div id="compDivId">
								<form:select path="component.id" class="form-control">
									<form:option value="" label=" Select Component " />
									<form:options items="${componentMap}" />
								</form:select>


							</div>


						</div>

						<div class="form-group  col-sm-4">

							<label class="control-label" for="purity">Purity</label>
							<div id="purityDivId">
								<form:select path="purity.id" class="form-control">
									<form:option value="" label=" Select Purity " />
									<form:options items="${purityMap}" />
								</form:select>


							</div>


						</div>

						<div class="form-group  col-sm-4">

							<label class="control-label" for="part">Color</label>
							<div id="colorDivId">
								<form:select path="color.id" class="form-control">
									<form:option value="" label=" Select Color " />
									<form:options items="${colorMap}" />
								</form:select>


							</div>


						</div>

						<div class="form-group col-sm-4">

							<label class="control-label" for="metalWt">Weight</label>
							<form:input path="metalWt" cssClass="form-control"
								autocomplete="off" />
							<form:errors path="metalWt" />


						</div>

						<div class="form-group col-sm-4">

							<label class="control-label" for="qty">Qty</label>
							<form:input path="pcs"  cssClass="form-control"
								autocomplete="off" />
							<form:errors path="pcs" />


						</div>
					</div>

					<div class="form-group">
						<div>
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:multiCompAdd()" 
								style="width: 100px;" id="multiCompSaveBtn"> 
								<input type="hidden" id=vvBagNo name="vvBagNo" />
								<input type="hidden" id="vFindingFlg" name="vFindingFlg" />
								<input type="hidden" id="vPresentDept" name="vPresentDept" />
								<input type="hidden" id="vTotQty" name="vTotQty" />
								<input type="hidden" name="vTranDate" id="vTranDate" />
								
								
						</div>


					</div>
				</form:form>
			</div>


			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>


			</div>


		</div>


	</div>
</div>
<script type="text/javascript">
$(document).ready(
		function(e) {
			$(".multiAdditionForm").validate(
					{
						
						rules : {
							'department.id' : {
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
							},
							pcs : {
								required : true,
								greaterThan : "0,0",
							},
												
						},

						highlight : function(element) {
							$(element).closest('.form-group').removeClass(
									'has-success').addClass('has-error');
						},
						unhighlight : function(element) {
							$(element).closest('.form-group').removeClass(
									'has-error').addClass('has-success');
						},

						messages : {
							metalWt : {
								greaterThan : "This field is required"
							},
							
							pcs : {
								greaterThan : "This field is required"
							}
							
						},
						


					});

		});
		
		
		
		function  multiCompAdd() {
			
			
			if($(".multiAdditionForm").valid()){
				$('#multiCompSaveBtn').attr('disabled', 'disabled');
				
				var postData = $('#multiComponentAdditionFormId').serializeArray();
				var formURL = $('#multiComponentAdditionFormId').attr("action");
				
				
					$
							.ajax({
								url : formURL,
								type : "POST",
								data : postData,
								success : function(data, textStatus, jqXHR) {
									if (data !== '1') {
										displayMsg(event, this,data);
									} else {
							
										
										 /* $('form#multiComponentAdditionFormId input[type="text"],textarea').val(''); */
										 $('#xyz').find('input[type="text"],textarea').val('');
										/* $('form#multiComponentAdditionFormId select').val(''); */
										 $('#xyz').find('select').val('');
										
										 displayInfoMsg(this,null,"Transaction Successfully Saved !");
										
									}
									
									
									$('#multiCompSaveBtn').removeAttr('disabled');
								
								},
								error : function(data, textStatus, jqXHR) {
									displayMsg(this,null,"Error Occoured , Contact Admin !");
								}
								

							});
					
			
			}else{
				
				/* alert(' form validate'); */
			}
			
			
				
			
		}
		
/* function multiAddSave(){
	
	if($(".multiComponentAdditionForm").valid()){
		
		$('#multiCompSaveBtn').attr('disabled', 'disabled');
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		
		
			$
					.ajax({
						url : formURL,
						type : "POST",
						data : postData,
						success : function(data, textStatus, jqXHR) {

							if (data !== '1') {
								displayMsg(event, this,data);
							} else {
					
								
								
								 $('#xyz').find('input[type="text"],textarea').val('');
								
								 $('#xyz').find('select').val('');
								
							}
							
							
							$('#multiCompSaveBtn').removeAttr('disabled');
						
						},

					});
			
		}else{
		alert('form is not valid');
	}
	
} */
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
	

<c:set var="optionText" value="Delete" />

<script src="/jewels/js/common/common.js"></script>


	




	