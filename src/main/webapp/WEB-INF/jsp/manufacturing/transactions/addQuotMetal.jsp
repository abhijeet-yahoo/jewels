<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<form:form commandName="quotMetal" id="quotMetalId"
	action="/jewels/manufacturing/transactions/quotMetal/add.html"
	cssClass="form-horizontal quotMetalForm">

	<table class="line-items editable table table-bordered">
		
		
		<thead class="panel-heading">
			<tr class="btn-warning">
				<th>Part Nm</th>
				<th>Purity</th>
				<th>Color</th>
				<th>Qty</th>
				<th>Wax Wt</th>
				<th>Metal Wt</th>
			</tr>
		</thead>
		<tbody>
			<tr>			
				<td><form:select path="partNm.id" class="form-control">
						<form:option value="" label="- Select PartNm -" />
						<form:options items="${lookUpMastMap}" />
					</form:select>
				</td>
				<td><form:select path="purity.id" class="form-control">
						<form:option value="" label="- Select Purity -" />
						<form:options items="${purityMap}" />
					</form:select></td>
				<td><form:select path="color.id" class="form-control">
						<form:option value="" label="- Select Color -" />
						<form:options items="${colorMap}" />
					</form:select></td>
				<td>
					<form:input type="number" path="metalPcs" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="waxWt" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="metalWeight" cssClass="form-control"  />
				</td>
				
				
			</tr>
			
		</tbody>
		
		<thead class="panel-heading">
			<tr class="btn-warning">
				<th>Metal Rate</th>
				<th>Per Gram Rate</th>
				<th>Loss %</th>
				<th>Metal Value</th>
				<th>Main Metal</th>
				
			</tr>
		</thead>
		
			<tbody>
			<tr>
				<td>
					<form:input type="number" path="metalRate" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="perGramRate" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="lossPerc" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="metalValue" cssClass="form-control" />
				</td>
				<td>
					<form:checkbox  path="mainMetal"  />
				</td>
				
				
				
			</tr>
			
		</tbody>
		
		
		
			<tr>
				<td colspan="14">
					<input type="submit" value="Save" class="btn btn-primary" />
					<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popCancelQuotMetal()"/>
					<form:input type="hidden" path="id" id="quotMetalIdPk"/>
					<input type="hidden" id="vQuotDtId" name="vQuotDtId" />
				</td>
			</tr>
		
	</table>
</form:form>



<script type="text/javascript">
	$(document).ready(function(e) {
		$(".quotMetalForm").validate({
			rules : {
				'purity.id' : {
					required : true,
				},
				'color.id' : {
					required : true,
				},
				'partNm.id' : {
					required : true,
				},
				metalPcs : {
					required : true,
					greaterThan : "0,0",
				},
			},
			highlight : function(element) {
				$(element).closest('.form-group')
						.removeClass('has-success')
						.addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group')
						.removeClass('has-error')
						.addClass('has-success');
			},
			messages : {
				metalPcs : {
					greaterThan : "This field is required"
				}
			},
		});
	});

</script>