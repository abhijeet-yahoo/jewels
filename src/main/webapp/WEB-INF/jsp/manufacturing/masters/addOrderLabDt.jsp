<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="orderLabDt" id="orderLabDtEnt"
		action="/jewels/manufacturing/masters/orderLabDt/add.html"
		cssClass="form-horizontal orderLabDtEntForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Metal</th>
					<th>Labour</th>
					<th>Rate</th>
					<th>PerPcs</th>
					<th>%Gram</th>
					<th>Percent</th>
					<th>PerCarat</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<form:select path="metal.id" class="form-control">
							<form:option value="" label="- Select Metal -" />
							<form:options items="${metalMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="labourType.id" class="form-control">
							<form:option value="" label="- Select LabourType -" />
							<form:options items="${labourTypeMap}" />
						</form:select>
					</td>
					<td><form:input path="labourRate" type="number" cssClass="form-control" /></td>
					<td><form:checkbox path="pcsWise"  /></td>
					<td><form:checkbox path="gramWise" /></td>
					<td><form:checkbox path="percentWise" /></td>
					<td><form:checkbox path="perCaratRate"/></td>

				</tr>

				<tr>
					<td colspan="12">
						<input type="submit" value="Save" class="btn btn-primary" />
						<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popLabCancel()"/>
						<form:input type="hidden" path="id" id="orderLabDtPk" /> 
						<input type="hidden" id="forLabOrderMtId" name="forLabOrderMtId" /> 
						<input type="hidden" id="forLabOrderDtId" name="forLabOrderDtId" />
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>
</div>


<script type="text/javascript">

$(document).ready(
		function(e){
			$(".orderLabDtEntForm")
					.validate(
							{	
							  rules : {
									'labourType.id' : {
										required : true,
									},
									
									labourRate : {
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
									labourRate : {
										greaterThan : "This field is required"
									}
								},
								

							});


		});
		



</script>