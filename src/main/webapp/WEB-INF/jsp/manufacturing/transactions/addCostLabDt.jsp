<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="costLabDt" id="costLabDtEnt"
		action="/jewels/manufacturing/transactions/costLabDt/add.html"
		cssClass="form-horizontal costLabDtEntForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-primary" class="panel-heading">
					<th>Metal</th>
					<th>Labour</th>
					<th>Rate</th>
					<th>Per Pcs</th>
					<th>Per Gram</th>
					<th>Percent</th>
					<th>Per Carat</th>
					<th>Lock</th>
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
					<td><form:input path="labourRate" cssClass="form-control" /></td>
					<td><form:checkbox path="pcsWise" id="labPcsWise" /></td>
					<td><form:checkbox path="gramWise" id="labGramWise" /></td>
					<td><form:checkbox path="percentWise" id="labPercentWise" /></td>
					<td><form:checkbox path="perCaratRate"/></td>
					<td><form:checkbox path="rLock" id="labLock" /></td>

				</tr>

				<tr>
					<td colspan="12">
						<input type="submit" value="Save" class="btn btn-primary" /> 
						<form:input type="hidden" path="id" id="costLabDtPk" /> 
						<input type="hidden" id="forLabCostMtId" name="forLabCostMtId" /> 
						<input type="hidden" id="forLabCostDtId" name="forLabCostDtId" />
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>
</div>


<script type="text/javascript">

$(document).ready(
		function(e){
			
			$(".costLabDtEntForm")
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