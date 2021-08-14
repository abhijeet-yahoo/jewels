<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Set Opening</span>
	</div>

<div class="form-group">
	<form:form commandName="tranMt" id="transferToTranMt"
		action="/jewels/manufacturing/transactions/tranMt/add.html"
		cssClass="form-horizontal tranMtForm">

		<c:if test="${success eq true}">
			<div class="col-xs-12">
				<div class="row">
					&nbsp;
					<div class="alert alert-success">Data Transfered  ${action}
						successfully!</div>
				</div>
			</div>
		</c:if>


		<div class="row">

			<div class="col-xs-12">&nbsp;</div>
		</div>
		



		<div class="form-group">
			<label class="col-lg-1 col-sm-2 text-right">Order No</label>
			<div class="col-lg-2 col-sm-2">
				<form:input path="orderMt.invNo" cssClass="form-control"
					 onBlur="javascript:popBagMt();" />
				<form:errors path="orderMt.invNo" />
			</div>
		</div>
		
		
		
		
<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div >
					<table id="tranId" data-toggle="table" data-toolbar="#toolbarDt"
						 data-height="350">
						<thead>
							<tr class="btn-primary">							
								<th data-field="state"  data-checkbox="true"></th>
								<th data-field="name" data-sortable="true">Bag No.</th>
								<th data-field="styleNo" data-sortable="true">Style No.</th>
								<th data-field="qty" data-sortable="true">Qty.</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
		
		
		<div class="form-group">
			<label class="col-lg-1 col-sm-2 text-right">Department </label>
			<div class="col-lg-2 col-sm-2">
					<form:select path="department.id"
									class="form-control">
									<form:option value="" label="- Select department -" />
									<form:options items="${departmentMap}" />
								</form:select>
			</div>
			
			<input type="submit" value="Transfer" class="btn btn-primary" id="transferBtnId"  />
				<input type="hidden" name="pInvNo" id="pInvNo" /> 
				<input type="hidden" name="pODIds" id="pODIds">
				<input type="hidden" id="returned" />				
				
		</div>
		
		

				
					
						
			
		
				
		
		
		
		
		
		
		
		
	</form:form>
</div>




</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<script type="text/javascript">
	$(document).ready(
			function(e) {
				$(".tranMtForm").validate(
						{
							rules : {
								'department.id' : {
									required : true,
									
								}
								
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
								'department.id' : {
									remote : "Department Not Selected "
								}
							}
						});
				
				$("#orderMt\\.invNo").autocomplete({
					source: "<spring:url value='/manufacturing/transactions/tran/bag/list.html' />",
					minLength : 2
				});

			});

	function popBagMt() {
		$("#tranId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/setOpening/listing.html?pInvNo="
									+ $("#orderMt\\.invNo").val()+"&opt=1"
						});
	}
	
	
	
	$(document).on(
			'submit',
			'form#transferToTranMt',
			function(e) {
				
				//$('#transferBtnId').attr('disabled', 'disabled');
				
				$("#transferBtnId").prop("disabled", true);
				
				var data = $('#tranId').bootstrapTable('getSelections');
				
				var ids = $.map(data, function (item) {
						return item.id;
				});
								
				var bag = $.map(data, function(item){
						return item.name;
				});
								
				$('#pODIds').val(ids);				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
								
				$.ajax({ 
					url : formURL,
					type : "POST",
					data : postData,
					
					 success : function(data, textStatus, jqXHR) {	
						 
						 $('#tranId input:checkbox').attr('checked',false);
						
						 $('#returned').val(data);
						 var result=$('#returned').val();
						 
						 if(result == 1)
							{
							 
							 $('#department\\.id').val('');
							
							 
							 $("#tranId")
								.bootstrapTable(
										'refresh',
										{
											url : "/jewels/manufacturing/transactions/setOpening/listing.html?pInvNo="
													+ $("#orderMt\\.invNo").val()
										});
							 		
							 displayInfoMsg(this, null, 'Data Transfered Successfully');	
							 
							 
							 
							 }
						 else
							 {
							 	alert(result);
							 	$('#transferBtnId').prop('disabled', false);
							 	
							 }
						 
						 
					
					},
					error : function(jqXHR, textStatus,
							errorThrown) {
						$('#transferBtnId').prop('disabled', false);
					} 
					
				});
				e.preventDefault(); //STOP default action
			});
		
	
	
	
	$('#tranId').bootstrapTable({})
		.on(
			'load-success.bs.table',
			function(e, data) {
				$('#transferBtnId').prop('disabled', false);
			});
	
	
	
</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>