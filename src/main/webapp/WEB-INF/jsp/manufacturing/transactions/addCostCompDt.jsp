
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div class="form-group">
	<form:form commandName="costCompDt" id="costCompDtEnt"
		action="/jewels/manufacturing/transactions/costCompDt/add.html"
		cssClass="form-horizontal costCompDtEntForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-primary" class="panel-heading">
				
				<c:if test="${costingType eq 'merge'}">
					<th>Component</th>
					<th>Purity</th>
					<th>Color</th>
					<th>Metal Wt</th>
					<th>Qty</th>
				</c:if>
				
				<c:if test="${costingType ne 'merge'}">
					<th>Component</th>
					<th>Purity</th>
					<th>Color</th>
					<th>Metal Wt</th>
					<th>Rate</th>
					<th>Qty</th>
					
				</c:if>
					
				</tr>
			</thead>
			<tbody>
				<tr>
				
				<c:if test="${costingType eq 'merge'}">
				
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
					<td>
						<form:input path="metalWt" id="compMetalWt" cssClass="form-control" />
					</td>
					<td><form:input path="compPcs"  cssClass="form-control" /></td>
				
				</c:if>
				
				<c:if test="${costingType ne 'merge'}">
				
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
					<td>
						<form:input path="metalWt" id="compMetalWt" cssClass="form-control" />
					</td>
					<td><form:input path="compRate"  cssClass="form-control" />
					</td>
					<td><form:input path="compPcs"  cssClass="form-control" /></td>
					<td><form:checkbox path="rLock" id="compLock" /></td>
				
				</c:if>	

				</tr>

				<tr>
					<td colspan="12">
						<input type="submit" value="Save" class="btn btn-primary" /> 
						<form:input type="hidden" path="id" id="costCompDtPk" />
						<input type="hidden" id="forCompCostMtId" name="forCompCostMtId" /> 
						<input type="hidden" id="forCompCostDtId" name="forCompCostDtId" />
						<input type="hidden" name="costingType" id="costingType" value="${costingType}"  />
					</td>

				</tr>
			</tbody>
		</table>


	</form:form>
</div>


<script type="text/javascript">



$(document).ready(
		function(e){
			$(".costCompDtEntForm")
					.validate(
							{	
							  rules : {
									'component.id' : {
										required : true,
									},
									'purity.id' : {
										required : true,
									},
									'color.id' : {
										required : true,
									},
									
									compPcs : {
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
									compPcs : {
										greaterThan : "This field is required"
									}
								},	

							});


		});


</script>
