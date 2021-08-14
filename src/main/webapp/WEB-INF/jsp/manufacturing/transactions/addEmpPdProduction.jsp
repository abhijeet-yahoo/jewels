<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

						<div class="form-group">
				<div class="form-group">
					<form:form commandName="empPdProduction"
						id="empPcsProductionEntry"
						action="/jewels/manufacturing/transactions/empPdProduction/add.html"
						cssClass="form-horizontal empPcsProductionEntryForm">

						<table class="line-items editable table table-bordered">
							<thead class="panel-heading">
								<tr class="btn-warning" class="panel-heading">
									<th>Employee</th>
									<th>ProdLabType</th>
									<th>Rate</th>
									<th>StyleNo</th>
									<th>Pcs</th>
									<th>Prod Pcs</th>
									<th>RecWt</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div id="employeeId">
											<form:select path="employee.id" class="form-control">
												<form:option value="" label="- Select Employee -" />
												<form:options items="${employeeMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="prodLabTypeId">
											<form:select path="prodLabType.id" class="form-control"
												onChange="javascript:getRate(this.value) ">
												<form:option value="" label="- Select ProdLabType -" />
												<form:options items="${prodLabTypeMap}" />
											</form:select>
										</div>
									</td>
									<td><form:input path="rate" cssClass="form-control" disabled="true" /></td>
									<td><form:input path="design.styleNo" id="styleNoId" cssClass="form-control" disabled="true" /></td>
									<td><form:input path="pcs" cssClass="form-control" disabled="true" /></td>
									<td><form:input path="prodPcs" cssClass="form-control" />
									<td><form:input path="recWt" cssClass="form-control" disabled="true" /></td>
								</tr>
								<tr>
									<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="empPcsSubmitBtn" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popEmpPcsCancelBtn()"/> 
										<form:input type="hidden" path="id" /> 
										<input type="hidden" id="vDeptId" name="vDeptId">
										<input type="hidden" id="vStyleNo" name="vStyleNo"> 
										<input type="hidden" id="vRate" name="rate">
										<input type="hidden" id="vPcs" name="pcs">
										<input type="hidden" id="vRecWt" name="recWt">
									</td>
								</tr>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>

			
	<script type="text/javascript">
	
	$(document).ready(function(e) {		
			
		$(".empPcsProductionEntryForm").validate({

			rules : {
				'employee.id' : {
					required : true,

				},
				'prodLabType.id' : {
					required : true,

				},
				pcs :  {
					required : true,
					greaterThan : "0.0",
				},
				prodPcs :  {
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
				pcs : {
					greaterThan : "This field is required"
				},
				prodPcs : {
					greaterThan : "This field is required"
				}
			},
		
			
		});
		
		
	});
	
	</script>
			
			
<script src="/jewels/js/common/tran.js"></script>