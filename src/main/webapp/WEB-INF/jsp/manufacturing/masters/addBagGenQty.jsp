 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>



<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Bag Generation Qty</span>
	</div>
	<form:form commandName="bagGenQty"
		action="/jewels/manufacturing/masters/bagGenQty/add.html"
		cssClass="form-horizontal bagGenQtyForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Bag Generation Qty ${action} successfully!</div>
		</c:if>
		
		<c:if test="${success eq false}">
	<div class="col-xs-12">
		<div class="alert alert-danger"> ${action} !</div>
	</div>
</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
	
	<div class="form-group">
			<label for="fromCtsWt" class="col-sm-2 control-label">From Carat WT :</label>
			<div class="col-sm-4">
				<form:input path="fromCtsWt" cssClass="form-control" autocomplete="off" />
				<form:errors path="fromCtsWt" />
			</div>
		</div>
	
		<div class="form-group">
			<label for="toCtsWt" class="col-sm-2 control-label">To Carat WT :</label>
			<div class="col-sm-4">
				<form:input path="toCtsWt" cssClass="form-control" autocomplete="off" />
				<form:errors path="toCtsWt" />
			</div>
		</div>
	
	<div class="form-group">
			<label for="qty" class="col-sm-2 control-label">Qty :</label>
			<div class="col-sm-4">
				<form:input path="qty" cssClass="form-control" autocomplete="off" />
				<form:errors path="qty" />
			</div>
		</div>
		
		
		
		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="<spring:url value='/manufacturing/masters/bagGenQty.html' />">Bag To Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
	
	
	</form:form>
	
	</div>
	
	
<script type="text/javascript">
$(document)
.ready(
		function(e) {
			$(".bagGenQtyForm")
			.validate(
					{
						rules : {
						
							fromCtsWt : {
								required : true,
								greaterThan : "0,0",
							},
							
							toCtsWt : {
								required : true,
								greaterThan : "0,0",
							},
												
							qty : {
								required : true,
								greaterThan : "0,0",
							},
						
						},
						highlight : function(element) {
							$(element).closest(
									'.form-group')
									.removeClass(
											'has-success')
									.addClass('has-error');
						},
						unhighlight : function(element) {
							$(element)
									.closest('.form-group')
									.removeClass(
											'has-error')
									.addClass('has-success');
						},
						
						messages : {
							fromCtsWt : {
								greaterThan : "This field is required"
							},
							
							toCtsWt : {
								greaterThan : "This field is required"
							}, 
							qty : {
								greaterThan : "This field is required"
							}
							
						},
						
					});
		
				
			
		});	
		
$(document)
.on(
	'submit',
	'form#bagGenQty',
	function(e) {
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$
				.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {
												
						if(data === "-1"){
							displayMsg(this, null, ' Duplicate Entry');
						}else if(data.indexOf("id") != -1){
							window.location.href = data;
						}
						else{
							window.location.href = data;
						}
			

					},

					error : function(jqXHR, textStatus,errorThrown) {

					}
				});
		e.preventDefault(); //STOP default action
	});


		
		


</script>	
	
<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>





	
	
	