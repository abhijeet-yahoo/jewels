
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>




	<div class="form-group">
					<div class="form-group">
						<form:form commandName="makingRecDt" id="makingRecDtEnt"
							action="/jewels/manufacturing/transactions/makingRecDt/add.html"
							cssClass="form-horizontal makingRecDtEntForm">

							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-warning" class="panel-heading">
										<th>Component</th>
										<th>Quantity</th>
										<th>Weight</th>  
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><form:select path="component.id" class="form-control" >
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</td>
										<td><form:input path="qty" cssClass="form-control" /></td>
										<td><form:input path="recWt" cssClass="form-control" onblur="javascript:validateLoss()"/></td>
									</tr>
								
									<tr id="saveBtnId" style="display: block">
										<td colspan="10">
										<input type="submit" value="Save" class="btn btn-primary" /> 
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMakingCancelBtn()">
											<form:input type="hidden" path="id" id="makRecDtId" /> 
											<form:input type="hidden" path="uniqueId" /> 
											<input type="hidden" id="pInvNo" name="pInvNo" />
											<input type="hidden" id="changedLoss" name="changedLoss" />
											<input type="hidden" id="changedIssueWt" name="changedIssueWt" />
											<input type="hidden" id="changeRetMetWt" name="changeRetMetWt" />
										</td>
									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				</div>
				
				
				
				
				
				
	<script type="text/javascript">
				
		
	$(document)
		.ready(
			function(e) {
				
				
				
				 $(".makingRecDtEntForm")
					
					.validate(
							{
								rules : {
									
									'component.id' : {
										required : true,
									},
									
									recWt : {
										required : true,
										greaterThan : "0.0",
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
									
									recWt : {
										greaterThan : "This field is required"
									}
								}

							});
				
				
				
				
				
				
			});
	
	
				
				
	</script>
				
				
				
				
				
				
				
				
				
				
				