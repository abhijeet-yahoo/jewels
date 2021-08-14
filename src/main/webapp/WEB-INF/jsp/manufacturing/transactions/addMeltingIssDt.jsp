<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group" >
			<div class="form-group">
			
		<form:form commandName="meltingIssDt" id="meltingIssDt"
			action="/jewels/manufacturing/transactions/meltingIssDt/add.html"
			cssClass="form-horizontal meltingIssDtForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>Location</th>
						<th>Purity</th>
						<th>Color</th>
						<th>Metal Wt</th>
						<th>Stone</th>
						<th>Carat</th>
						<th>Net Wt</th>
						<th>ExcpPure Wt</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<td><form:select path="deptLocation.id" class="form-control"  required="true">
												<form:option value="" label="- Select Location -" />
												<form:options items="${locationMetalMap}" />
											</form:select></td>
						
						<td>
							<form:select path="purity.id" id="issPurity" class="form-control"
								onChange="javascript:getPurityConversion();refreshData()">
								<form:option value="" label="- Select Purity -" />
								<form:options items="${purityMap}" />
							</form:select>
						</td>
						<td>
							<form:select path="color.id" id="issColor" class="form-control">
								<form:option value="" label="- Select Color -" />
								<form:options items="${colorMap}" />
							</form:select>
						</td>
						<td><form:input path="issFreshMetalWt"  cssClass="form-control" onblur="javascript:getNetWt();" /></td>
						<%-- <td><form:input path="issUsedMetalWt"   cssClass="form-control" onblur="javascript:popValidation();getNetWt();" /></td> --%>
						<td><form:input type="number" path="stone" id="issStone"  cssClass="form-control" min="0" pattern="[0-9]"
							onkeypress="return !(event.charCode == 46)" step="1"
							title="Must be an integer number"/></td>
						
						<!-- <input
							type="number" id="splitQty" name="splitQty" class="form-control"
							min="0" pattern="[0-9]"
							onkeypress="return !(event.charCode == 46)" step="1"
							title="Must be an integer number"> -->
						
						
						
						<td><form:input path="carat" id="issCarat" cssClass="form-control" onblur="javascript:getNetWt()"/></td>
						<td><form:input path="netWt" cssClass="form-control" disabled="true" /></td>
						<td><form:input path="excpPureWt" cssClass="form-control" disabled="true" /></td>
					</tr>
			
				</tbody>
				
					<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>Remarks</th>
						
					</tr>
				</thead>
				
					<tbody>
					<tr>
						
						<td><form:input path="dtRemark"  cssClass="form-control"/></td>
					
					</tr>
					<tr>
						<td colspan="10">
						<input type="submit" value="Save" class="btn btn-primary" id="issueSubmitBtnId" />
						<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMelIssCancelBtn()"> 
							<form:input type="hidden" path="id" id="issDtPk" />
							<form:input type="hidden" path="uniqueId" />
							<input type="hidden" id="pInvNo" name="pInvNo" />
							<input type="hidden" id="purityConversion" name="purityConversion" />
							<input type="hidden" id="prevNetWt" name="prevNetWt" />	
							<input type="hidden" id="prevKt" name="prevKt" />
							<input type="hidden" id="prevColor" name="prevColor" />
							<input type="hidden" id="currNetWt" name="currNetWt" /> 
							<input type="hidden" id="pExcpPureWt" name="pExcpPureWt" /> 
							<input type="hidden" id="pFMetalWt" name="pFMetalWt" />
								
						</td>
					</tr>
				</tbody>
				
			</table>
		</form:form>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function(e) { 

		$(".meltingIssDtForm").validate(
				{
					rules : {
						
						
						'purity.id' : {
							required : true,
						},
						'color.id' : {
							required : true,
						},
						
						issFreshMetalWt : {
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
					
				});
		
		
		
		if($("#issFreshMetalWt").val() === '0.0'){
			$("#issFreshMetalWt").prop('disabled', true);
		}
		
		
		
	});
</script>








