<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:set var="option" value="Design Item" />

<div id="rowDCId">
	<div class="form-group">
		<form:form commandName="designComponent" id="designComponent"
			action="/jewels/manufacturing/masters/designComponent/add.html"
			cssClass="form-horizontal designComponentForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="panel-heading">
						<th>Component</th>
						<th>Kt</th>
						<th>Color</th>
						<th>Quantity</th>
						<th>Comp Wt</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<form:select path="component.id" class="form-control">
								<form:option value="" label="- Select Component -" />
								<form:options items="${componentMap}" />
							</form:select>
						</td>
						<td>
							<form:select path="purity.id" class="form-control">
								<form:option value="" label="- Select Purity -" />
								<form:options items="${purityMap}" />
							</form:select>
						</td>
						<td>
							<form:select path="color.id" class="form-control">
								<form:option value="" label="- Select Color -" />
								<form:options items="${colorMap}" />
							</form:select>
						</td>
						<td><form:input path="quantity" cssClass="form-control" /></td>
						<td><form:input path="compWt" cssClass="form-control" /></td>
					</tr>
					<tr>
						<td colspan="10"><input type="submit" value="Save" class="btn btn-primary" />
						<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popCancelDesignComponent()"/>
							 <form:input type="hidden" path="id" id="dcId" />
							<input type="hidden" id="dcStyleNo" name="dcStyleNo" /> <input
							type="hidden" id="dcVersion" name="dcVersion" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(e) {
		$(".designComponentForm").validate({
			rules : {
				'component.id' : {
					required : true,
				},
			},
			highlight : function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			}
		});
	});
</script>