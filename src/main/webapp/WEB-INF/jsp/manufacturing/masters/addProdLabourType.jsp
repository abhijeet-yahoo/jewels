<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%" >
	<div class="panel-heading">
		<span style="font-size: 18px;">Product Labour Master</span>
	</div>


	<form:form commandName="prodLabourType" 
		action="/jewels/manufacturing/masters/prodLabourType/add.html"
		cssClass="form-horizontal prodLabourTypeForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">prodLabourType ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<%-- <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Department
				Name :</label>
			<div class="col-sm-3">
				<form:input path="department" cssClass="form-control" />
				<form:errors path="department" />
			</div>
		</div> --%>
		
		<div class="form-group">
			<label for="concept" class="col-sm-2 control-label">Department
				:</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="department.id" class="form-control">
					<form:option value="" label="--- Select Department ---" />
					<form:options items="${departmentMap}" />
				</form:select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="concept" class="col-sm-2 control-label">Category
				:</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="category.id" class="form-control">
					<form:option value="" label="--- Select Category ---" />
					<form:options items="${categoryMap}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Type
				Name :</label>
			<div class="col-sm-3">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Rate :</label>
			<div class="col-sm-3">
				<form:input path="rate" cssClass="form-control" />
				<form:errors path="rate" />
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="row">
			<div class="col-xs-12">

				<div class="col-lg-1">
					<label></label>
				</div>

				<div class="col-lg-1">
					<label></label>
				</div>

				<div class="col-lg-2">
					<div class="checkbox">
						<form:checkbox path="prodLabTypeDefault" value="2" />
						<strong>Default</strong>
					</div>
				</div>
				<c:if test="${canAdd && canDelete}">
				<div class="col-lg-2">
					<div class="checkbox">
						<form:checkbox path="deactive" value="2" />
						<strong>Deactive</strong>
					</div>
				</div>
				</c:if>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/prodLabourType.html">ProLabourType Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>

	</form:form>

</div>
<!-- ending the main panel -->

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						$(".prodLabourTypeForm")
								.validate(
										{
											rules : {
											'department.id' : {
													required : true,
												},
											'category.id' : {
													required : true,
												},
											name : {
													required : true,
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
												name : {
													remote : "Product Labour Type already exists"
												}
											}
										});
						
						
						

						
					});	
	
</script>