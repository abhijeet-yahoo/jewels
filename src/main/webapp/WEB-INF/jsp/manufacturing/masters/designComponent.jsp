<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:set var="option" value="Design Item" />
<c:set var="optionText" value="Delete" />

<div id="DCId">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div id="toolbarDC">
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="javascript:goToComponent();"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Component
						Details</a>
				</div>
				<div>
					<table id="stoneDCId" data-toggle="table" data-toolbar="#toolbarDC"
						data-pagination="false" data-side-pagination="server"
						data-striped="true" 
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350">
						<thead>
							<tr class="btn-primary">
								<th data-field="component" data-align="left" data-sortable="true">Component</th>
								<th data-field="quantity" data-align="left" data-sortable="true">Quantity</th>
								<th data-field="compWt" data-align="left" data-sortable="true">Component Wt</th>
								<th data-field="deactive" data-sortable="true">Status</th>
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="rowDCId" style="display: none;">
		<div class="form-group">
			<form:form commandName="designComponent" id="designComponent"
				action="/jewels/manufacturing/masters/designComponent/add.html"
				cssClass="form-horizontal designComponentForm">

				<table class="line-items editable table table-bordered">
					<thead class="panel-heading">
						<tr class="panel-heading">
							<th>Component</th>
							<th>Quantity</th>
							<th>Comp Wt</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><form:select path="component.id" class="form-control">
									<form:option value="" label="- Select Component -" />
									<form:options items="${componentMap}" />
								</form:select></td>
							<td><form:input path="quantity" cssClass="form-control" /></td>
							<td><form:input path="compWt" cssClass="form-control" /></td>
						</tr>
						<tr>
							<td colspan="10"><input type="submit" value="Save"
								class="btn btn-primary" /> <form:input type="hidden" path="id" />
								<input type="hidden" id="dcStyleNo" name="dcStyleNo" /> <input
								type="hidden" id="dcVersion" name="dcVersion" /></td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
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


	$('#stoneDCId').bootstrapTable({}).on('click-row.bs.table',
		function(e, row, $element) {
			$("#rowDCId").css('display','none');
		});
</script>