<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<div id="cDeductionEntry">
<div class="form-group">
				<div class="form-group">
					<form:form commandName="compTran" id="compDeductionEntry"
						action="/jewels/manufacturing/transactions/componentDeduction/add.html"
						cssClass="form-horizontal compDeductionEntryForm">

						<table class="line-items editable table table-bordered">
							<thead class="panel-heading">
								<tr class="btn-warning" class="panel-heading">
								
									<th id="partNmThId">Part Name</th>
									<th>Location</th>
									<th>Component</th>
									<th>Purity</th>
									<th>Color</th>
									<th>Weight</th>
									<th>Qty</th>
								</tr>
							</thead>
							<tbody>
								<tr>
								<td id="partNmTdId">
										<div id="partNmId" >
											<form:select path="partNm.id" class="form-control" onChange="javascript:getPartData();">
												<form:option value="" label="- Select Part Name -" />
												<form:options items="${partNmMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="locationId">
											<form:select path="department.id" class="form-control" required="true">
												<form:option value="" label="- Select Location -" />
												<form:options items="${departmentMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div>
											<form:select path="component.id" class="form-control" required="true">
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="purityId">
											<form:select path="purity.id" class="form-control" required="true">
												<form:option value="" label="- Select Purity -" />
												<form:options items="${purityMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="colorId">
											<form:select path="color.id" class="form-control" required="true">
												<form:option value="" label="- Select Color -" />
												<form:options items="${colorMap}" />
											</form:select>
										</div>
									</td>
									<td><form:input path="metalWt" cssClass="form-control" /></td>
									<td><form:input path="pcs" id="compDedPcs" cssClass="form-control" /></td>
								</tr>
								<tr>
									<td colspan="10">
										<input type="submit" value="Save" class="btn btn-primary" id="compDedBtn" />
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popCompDedCancelBtn()">
										<form:input type="hidden" path="id" />
										<input type="hidden" name="vBagNo" id="vBagNo" />
										<input type="hidden" name="vPurityId" id="vPurityId" /> 
										<input type="hidden" name="vColorId" id="vColorId" /> 
										<input type="hidden" name="vQty" id="vQty" />
										<input type="hidden" name="vPresentDept" id="vPresentDept" />
										<input type="hidden" id="findingFlg" name="findingFlg" />
										<input type="hidden" id="vTranDate" name="vTranDate" />
										<input type="hidden" id="vDeptId" name="vDeptId" /></td>
									
								</tr>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
			
		</div>	
			
			
<script type="text/javascript">
	$(document).ready(function(e) {

		$('#partNm\\.id').chosen();
		$('#metal\\.id').chosen();
		$('#component\\.id').chosen();
		$('#purity\\.id').chosen();
		$('#color\\.id').chosen();
		$('#department\\.id').chosen();
		
		$.validator.setDefaults({ ignore: ":hidden:not(select)" });
	
							
	// validation of chosen on change
		if ($("select.form-control").length > 0) {
		    $("select.form-control").each(function() {
		        if ($(this).attr('required') !== undefined) {
		            $(this).on("change", function() {
		                $(this).valid();
		            });
		        }
		    });
		}


		$(".compDeductionEntryForm").validate(
				{
					rules : {
						
						metalWt : {
							required : true,
							greaterThan : "0,0",
						}
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
<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>
	