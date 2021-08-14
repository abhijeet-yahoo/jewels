.
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div class="form-group">
	<div class="form-group">
		<form:form commandName="metalInwardDt" id="metalInwardDtEnt"
			action="/jewels/manufacturing/transactions/metalInwardDt/add.html"
			cssClass="form-horizontal metalInwardDtEntForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>Metal</th>
						<th>Purity</th>
						<th>Color</th>
						<th>Invoice Wt</th>
						<th>Metal Wt</th>
						<th>Rate</th>
						<th>Amount</th>
						<th>In 999</th>
						<th>Remark</th>  
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><form:select path="metal.id" class="form-control"
								onChange="javascript:popPurity(this.value);">
								<form:option value="" label="- Select Metal -" />
								<form:options items="${metalMap}" />
							</form:select></td>
						<td>
							<div id="purityId">
								<form:select path="purity.id" class="form-control">
									<form:option value="" label="- Select Purity -" />
									<form:options items="${purityMap}" />
								</form:select>
							</div>
						</td>
						<td>
							<div id="colorId">
								<form:select path="color.id" class="form-control">
									<form:option value="" label="- Select Color -" />
									<form:options items="${colorMap}" />
								</form:select>
							</div>
						</td>


						<td><form:input path="invWt" cssClass="form-control" /></td>
						<td><form:input path="metalWt" cssClass="form-control"
								onchange="javascript:setAmount()" /></td>
						<td><form:input path="rate" cssClass="form-control"
								onchange="javascript:setAmount()" /></td>
						<td><input type="text" id="aAmount" name="aAmount"
							class="form-control" disabled="disabled" /></td>
							<td><form:checkbox path="in999"  /></td>
						<td><form:input path="remark" cssClass="form-control" /></td>

					</tr>
					<tr>
						<td colspan="10">
						<input type="submit" value="Save" class="btn btn-primary" />
						<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMetalInwCancelBtn()">
							<form:input type="hidden" id="metalInwardDtEntryId" path="id" />
							<input type="hidden" id="pInvNo" name="pInvNo" /> <form:input
								type="hidden" path="uniqueId" /> <form:input type="hidden"
								path="department.id" /> <form:input type="hidden" path="amount" />
								<input type="hidden" id="purityyId" name="purityyId" />
								<input type="hidden" id="invWtt" name="invWtt" />
													
								
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(
			function(e) {
				$(".metalInwardDtEntForm").validate(
						{
							rules : {
								'metal.id' : {
									required : true,
								},
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},
								

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
								invWt : {
									greaterThan : "This field is required"
								},
								metalWt : {
									greaterThan : "This field is required"
								}
							},

						});

				setAmount();
				
				
				if('${balFlg}' == 'false'){
					$('#invWt').attr('disabled','disabled');
				}

			});
</script>

<script src="/jewels/js/common/common.js"></script>