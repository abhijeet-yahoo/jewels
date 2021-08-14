<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Sub Concept Master</span>
	</div>

	<form:form commandName="subConcept" id="subConceptSubmitId"
		action="/jewels/manufacturing/masters/subConcept/add.html"
		cssClass="form-horizontal subConceptForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Sub Concept ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="concept" class="col-sm-2 control-label">Concept
				:</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="concept.id" class="form-control">
					<form:option value="" label="--- Select Concept ---" />
					<form:options items="${conceptList}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">SubConcept
				Name :</label>
			<div class="col-sm-9">
				<form:input path="name" cssClass="form-control" autocomplete="off"/>
				<form:errors path="name" />
			</div>
		</div>

		

		<c:if test="${canDelete}">
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label"></label>
				<div class="col-sm-8">
					<label> <form:checkbox path="deactive" /> <b>De-active</b></label>
				</div>
			</div>
		</c:if>

		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/subConcept.html">Sub Concept
						Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		
	</form:form>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						$(".subConceptForm")
								.validate(
										{
											rules : {
												'concept.id' : {
													required : true
												},
												name : {
													required : true,
													
												 	remote : {
														url : "<spring:url value='/manufacturing/masters/subConceptAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															},
														
														conceptId : function() {
															return $("#concept\\.id")
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
													remote : "Sub Concept already exists"
												}
											}
										});

						$("input:text:visible:first").focus();
					});
	
	
	
	
	$(document)
	.on
		('submit',
		'form#subConceptSubmitId',
		function(e){
			
			var postData = $(this).serializeArray();
			var formURL	 = $(this).attr("action");
			
			
			$.ajax({
				url  : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){
					
				if(data === "-1"){
						displayMsg(this, null, 'Duplicate Entry Found');	
					}else if(data.indexOf("add") != -1){
						window.location.href = data;
					}else{
						window.location.href = data;
						
					}
					
				},
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
				
			})
			
			
			e.preventDefault();
			
		});
</script>
