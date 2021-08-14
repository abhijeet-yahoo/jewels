<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Size Group Master</span>
	</div>

	<form:form commandName="sizeGroup" id="sizeGroupSub"
		action="/jewels/manufacturing/masters/sizeGroup/add.html"
		cssClass="form-horizontal sizeGroupForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Size Group ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="shape" class="col-sm-2 control-label">Shape :</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="shape.id" class="form-control">
					<form:option value="" label="--- Select Shape ---" />
					<form:options items="${shapeList}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">SizeGroup
				Name :</label>
			<div class="col-sm-9">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>

		<div class="form-group">
			<label for="fromSize" class="col-sm-2 control-label">From Size :</label>
			<div class="col-sm-9">
				<form:input path="fromSize" cssClass="form-control" />
				<form:errors path="fromSize" />
			</div>
		</div>

		<div class="form-group">
			<label for="toSize" class="col-sm-2 control-label">To Size :</label>
			<div class="col-sm-9"> 
				<form:input path="toSize" cssClass="form-control" />
				<form:errors path="toSize" />
			</div>
		</div>

		<c:if test="${canAdd && canDelete}">
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label"></label>
				<div class="col-sm-8">
					<label> <form:checkbox path="deactive" /> <b>De-active</b></label>
				</div>
			</div>
		</c:if>

		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/sizeGroup.html">Size Group
						Listing</a>
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
						$(".sizeGroupForm")
								.validate(
										{
											rules : {
												'shape.id' : {
													required : true
												},
												name : {
													required : true,
													
													remote : {
														url : "<spring:url value='/manufacturing/masters/sizeGroupAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												}
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
												name : {
													remote : "SizeGroup already exists"
												}
											}
										});

						$("input:text:visible:first").focus();
					});
	
	
	
	$(document).
	on('submit',
		'form#sizeGroupSub',
		function(e){
	
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
	
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR){
				
				if(data === '-1'){
					displayMsg(this,null,"Dublicate Record Found")
				}else{
					window.location.href=data;	
				}
				
			},
			error : function(data, textStatus, jqXHR){
				
			}
			
		})
		
		e.preventDefault();
		
	})
	
	
	
	
	
	
	
	
	
</script>
