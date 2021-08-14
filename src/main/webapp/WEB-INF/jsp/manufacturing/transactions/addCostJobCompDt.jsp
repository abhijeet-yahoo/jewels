
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


			<div class="form-group">
						<form:form commandName="costJobCompDt" id="costJobCompDtEnt"
							action="/jewels/manufacturing/transactions/costJobCompDt/add.html"
							cssClass="form-horizontal costJobCompDtEntForm">
							
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Component</th>
										<th>Purity</th>
										<th>Color</th>
										<th>MetalWt</th>  
										<th>Rate</th>
										<th>Qty</th>
										<th>Lock</th>
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
										<td><form:input path="metalWt" id="compMetalWt" cssClass="form-control" /></td>
										<td><form:input path="compRate"  cssClass="form-control" /></td>
										<td><form:input path="compPcs"  cssClass="form-control" /></td>
										<td><form:checkbox path="rLock" id="compJobLock" /></td>
								
								   </tr>
								   	
								   	<tr>
								   		<td colspan="12">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<form:input type="hidden" path="id" id="costJobCompDtPk" />
											<input type="hidden" id="forCompCostJobMtId" name="forCompCostJobMtId" />
											<input type="hidden" id="forCompCostJobDtId" name="forCompCostJobDtId" /> 
										</td>
								   	
								   	</tr>
								</tbody>
						    </table>
			
						</form:form>
					</div>
					
			
					
<script type="text/javascript">



$(document).ready(
		function(e){
		
		
		$(".costJobCompDtEntForm")
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
							metalWt : {
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
							metalWt : {
								greaterThan : "This field is required"
							}
						},	
	
					});

	
		});
		
		
		
		
		
		
		
		
		
</script>		