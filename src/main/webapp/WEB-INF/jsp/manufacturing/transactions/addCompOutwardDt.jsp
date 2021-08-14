
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<div class="form-group">
	<div class="form-group">
		<form:form commandName="compOutwardDt" id="compOutwardDtt"
							action="/jewels/manufacturing/transactions/compOutwardDt/add.html"
							cssClass="form-horizontal compOutwardDttForm">
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Location</th>
										<th>Metal</th>
										<th>Component</th>
										<th>Purity</th>
										<th>Color</th>
									<th>InvoiceWt</th>
										<!-- but on next row  <th>Remark</th>  -->
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><form:select path="department.id" class="form-control" required="true">
											<form:option value="" label="- Select Department -" />
												<form:options items="${departmentMap}" />
											</form:select></td>
										
										
										<td><form:select path="metal.id" class="form-control" onChange="javascript:popPurity(this.value);" required="true">
												<form:option value="" label="- Select Metal -" />
												<form:options items="${metalMap}" />
											</form:select></td>
										<td>
											<div id="componentId">
												<form:select path="component.id" class="form-control"  required="true">
													<form:option value="" label="- Select Component -" />
													<form:options items="${componentMap}" />
												</form:select>
											</div>
										</td>
										<td>
											<div id="purityId">
												<form:select path="purity.id" class="form-control"  required="true">
													<form:option value="" label="- Select Purity -" />
													<form:options items="${purityMap}" />
												</form:select>
											</div>
										</td>
										<td>
											<div id="colorId">
												<form:select path="color.id" class="form-control"  required="true">
													<form:option value="" label="- Select Color -" />
													<form:options items="${colorMap}" />
												</form:select>
											</div>
										</td>
										<td><form:input path="invWt" cssClass="form-control" /></td>

										
									</tr>
									</tbody>
									
									<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Per Gms Rate</th>
										<th>Qty</th>
										<th>MetalWt</th>
										<th>Rate</th>
										<th>Amount</th>
									
									</tr>
									</thead>
									<tbody>
									<tr>
									<td><form:checkbox path="perGramRate"  onchange="javascript:setAmount()" /></td>
									<td><form:input path="qty" cssClass="form-control" onchange="javascript:setAmount()"/></td>
										<td><form:input path="metalWt" cssClass="form-control"
												 /></td>
										<td><form:input path="rate" cssClass="form-control"
												onchange="javascript:setAmount()" /></td>
										<td><form:input path="amount"  class="form-control" readonly="true" /></td>
									</tr>
											
									
									
									<tr>
										<td colspan="10"><input type="submit" value="Save" class="btn btn-primary" /> 
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popCompOutwardDtCancelBtn()"> 
										<form:input type="hidden" id="compOutwardDtId" path="id" /> 
										<input type="hidden" id="pInvNo" name="pInvNo" />
										<%-- <form:input type="hidden" path="department.Id" />  --%>
										<input type="hidden" id="prevMetalWt" name="prevMetalWt" /> 
										<input type="hidden" id="metalWt" name="metalWt" /> 
										<form:input type="hidden" path="uniqueId" /> 
										
										
																				
									</tr>
								</tbody>
							</table>
						</form:form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(e) {
		
		$('#department\\.id').chosen();
		$('#metal\\.id').chosen();
		$('#component\\.id').chosen();
		$('#purity\\.id').chosen();
		$('#color\\.id').chosen();
		
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
		
		$(".compOutwardDttForm").validate({

			rules : {
				
				invWt : {
					required : true,
					greaterThan : "0,0",
				},
				metalWt : {
					required : true,
					greaterThan : "0,0",
				}
				

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

		if (window.location.href.indexOf('edit') != -1) {
			var prev = $('#metalWt').val();
			$('#prevMetalWt').val(prev);
		}

	});
	
	
	function popPurity(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/purityCompOut/list.html' />?metalId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#purityId").html(data);
					}
				});
	}
</script>

<script src="/jewels/js/common/common.js"></script>