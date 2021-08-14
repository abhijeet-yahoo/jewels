<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">SubShape Master</span>
	</div>

	<form:form commandName="subShape"
		action="/jewels/manufacturing/masters/subShape/add.html"
		cssClass="form-horizontal subShapeForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Sub Shape ${action}
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
			<label for="name" class="col-sm-2 control-label">Name :</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Code :</label>
			<div class="col-sm-10">
				<form:input path="code" cssClass="form-control" />
				<form:errors path="code" />
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
						href="/jewels/manufacturing/masters/subShape.html">Sub Shape Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
		
		
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(
			function(e) {
				$(".subShapeForm").validate(
						{
							rules : {
								'shape.id' : {
									required : true,
								},
								name : {
									required : true,
								},
								code : {
									required : false,
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
								name : {
									remote : "SubShape already exists"
								}
							}
						});
			});
</script>