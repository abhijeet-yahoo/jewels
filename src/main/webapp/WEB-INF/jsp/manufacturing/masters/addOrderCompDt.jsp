<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<div class="form-group">
	<form:form commandName="orderCompDt" id="orderCompDtEnt"
		action="/jewels/manufacturing/masters/orderCompDt/add.html"
		cssClass="form-horizontal orderCompDtEntForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Component</th>
					<th>Purity</th>
					<th>Color</th>
					<th>Pcs</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<form:select path="component.id" class="form-control" onchange="javascript:popOrderCompRate();" required="true">
							<form:option value="" label="- Select Component -" />
							<form:options items="${componentMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="purity.id" class="form-control" onchange="javascript:popOrderCompRate();" required="true">
							<form:option value="" label="- Select Purity -" />
							<form:options items="${purityMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="color.id" class="form-control" required="true">
							<form:option value="" label="- Select Color -" />
							<form:options items="${colorMap}" />
						</form:select>
					</td>
					<td><form:input path="compQty"  type="number" cssClass="form-control" /></td>
				</tr>
			</tbody>
			
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>MetalWt</th>
					<th>Rate</th>
					<th>Per Gram</th>
					<th>Per Pcs</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					
					<td>
						<form:input path="metalWt" type="number" cssClass="form-control" />
					</td>
					<td>
						<form:input path="compRate"  type="number" cssClass="form-control" />
					</td>
					<td><form:checkbox path="perGramRate"/></td>
					<td><form:checkbox path="perPcRate"/></td>
				</tr>
			</tbody>
			
				<tr>
					<td colspan="12">
						<input type="submit" value="Save" class="btn btn-primary" />
						<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popCompCancel()"/>
						<form:input type="hidden" path="id" id="orderCompDtPk" />
						<input type="hidden" id="forCompOrderMtId" name="forCompOrderMtId" /> 
						<input type="hidden" id="forCompOrderDtId" name="forCompOrderDtId" />
					</td>

				</tr>
			
		</table>


	</form:form>
</div>


<script type="text/javascript">


$(document).ready(
		function(e){
			
			
			$('#component\\.id').chosen();
			$('#orderCompDtEnt #purity\\.id').chosen();
			$('#orderCompDtEnt #color\\.id').chosen();
		
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
			
			$(".orderCompDtEntForm")
					.validate(
							{	
							  rules : {
									
									

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
								/* 	metalWt : {
										greaterThan : "This field is required"
									} */
								},	

							});


		});

		



</script>
