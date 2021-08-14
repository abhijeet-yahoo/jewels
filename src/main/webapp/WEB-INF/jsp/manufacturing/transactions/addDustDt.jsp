
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


	<form:form commandName="dustDt" id="dustIssDt"
			action="/jewels/manufacturing/transactions/dustDt/add.html"
			cssClass="form-horizontal dustIssDtForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>Department</th>
						<th>Employee</th>
						<th>Other</th>
						<th>Weight</th>
						
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<form:select path="department.id" class="form-control">
								<form:option value="" label="- Select Department -" />
								<form:options items="${departmentMap}" />
							</form:select>
						</td>
								<td>
							<div id="employeeDivId">
							<form:select path="employee.id" class="form-control">
								<form:option value="" label="- Select Employee -" />
								<form:options items="${employeeMap}" />
							</form:select>
							</div>
						</td>
						<td><form:input path="other"  cssClass="form-control"  /></td>
						<td><form:input path="metalWt"   cssClass="form-control"  /></td>
					</tr>
					<tr>
						<td colspan="10">
							<input type="submit" value="Save" class="btn btn-primary" id="issueSubmitBtnId" />
							<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popCancel();"/> 
								<form:input type="hidden" path="id" id="issDtPk" />
								<input type="hidden" id="pInvNo" name="pInvNo" />
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
		
		
		
		
 <script type="text/javascript">

	$(document).ready(
			function(e) {
			
			
				$(".dustIssDtForm").validate(
						{
							rules : {								
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