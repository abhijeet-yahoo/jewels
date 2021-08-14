<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

				<form:form commandName="dustRecDt" id="dustRecDtId"
					action="/jewels/manufacturing/transactions/dustRecDt/add.html"
					cssClass="form-horizontal dustRecDtForm">
		
					<table class="line-items editable table table-bordered">
						<thead class="panel-heading">
							<tr class="btn-warning" class="panel-heading">
								<th>Department</th>
								<th>Employee</th>
								<th>Other</th>
								<th>Burn Wt</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<form:select path="department.id" class="form-control" onchange="javascript:popEmpRecDt()">
										<form:option value="" label="- Select Department -" />
										<form:options items="${departmentMap}" />
									</form:select>
								</td>
								
								<td>
							<div id = "empDustRecDivId">	
							<form:select path="employee.id" class="form-control">
								<form:option value="" label="- Select Employee -" />
								<form:options items="${employeeMap}" />
							</form:select>
							</div>
						</td>
								
								<td><form:input path="other" cssClass="form-control"  /></td>
									<td><form:input path="burnWt"  id="burnWt" cssClass="form-control"  /></td>
								
							
							</tr>
							<%-- <tr>
								<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="receiveSubmitBtnId" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popRecCancel();"/> 
										<form:input type="hidden" path="id" id="recDtPk" />
										<form:input type="hidden" path="uniqueId" id="uniqueId" />
										<input type="hidden" id="pInvRecNo" name="pInvRecNo" />
								</td>
							</tr> --%>
						</tbody>
						
						<thead>
								<tr class="btn-warning" class="panel-heading">
								
								<th>Purity</th>
								<th>Color</th>
								<th>Metal Wt</th>
							</tr>
						
						</thead>
						
						<tbody>
							<tr>
														
							
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
								<td><form:input path="metalWt"  id="recMetWt" cssClass="form-control"  /></td>
							</tr>
							<tr>
								<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="receiveSubmitBtnId" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popRecCancel();"/> 
										<form:input type="hidden" path="id" id="recDtPk" />
										<form:input type="hidden" path="uniqueId" id="uniqueId" />
										<input type="hidden" id="pInvRecNo" name="pInvRecNo" />
								</td>
							</tr>
						</tbody>
						
					</table>
				</form:form>
				
				
				

		
<script type="text/javascript">

	$(document).ready(
			function(e) {
			
				
				$(".dustRecDtForm").validate(
						{
							rules : {
								
								
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},
								metalWt : {
									required : true,
									greaterThan : "0.0",
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
								metalWt : {
									greaterThan : "This field is required"
								}
							},
							
							
						});
				
				
			
			});
			
			
			
</script>