
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%-- 
<div class="form-group">
	<form:form commandName="quotCompDt" id="quotCompDtEnt"
		action="/jewels/manufacturing/transactions/quotCompDt/add.html"
		cssClass="form-horizontal quotCompDtEntForm">

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
						<form:select path="component.id" class="form-control" onchange="javascript:popQuotCompRate();">
							<form:option value="" label="- Select Component -" />
							<form:options items="${componentMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="purity.id" class="form-control" onchange="javascript:popQuotCompRate();">
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
						<form:input type="hidden" path="id" id="quotCompDtPk" />
						<input type="hidden" id="forCompQuotMtId" name="forCompQuotMtId" /> 
						<input type="hidden" id="forCompQuotDtId" name="forCompQuotDtId" />
					</td>

				</tr>
			
		</table>


	</form:form>
</div> --%>



<div class="form-group">

	<form:form commandName="quotCompDt" id="quotCompDtEnt"
		action="/jewels/manufacturing/transactions/quotCompDt/add.html"
		cssClass="form-horizontal quotCompDtEntForm">
							
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Component</th>
										<th>Purity</th>
										<th>Color</th>
										<th>Qty</th>
										<th>MetalWt</th>  
										<th>Rate</th>
																	
									</tr>
								</thead>
								  <tbody>
									<tr>
									    <td>
									    	<form:select path="component.id"  class="form-control" onchange="javascript:popQuotCompRate();">
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</td>
										<td>
											<form:select path="purity.id"  class="form-control" onchange="javascript:popQuotCompRate();">
												<form:option value="" label="- Select Purity -" />
												<form:options items="${purityMap}" />
											</form:select>
										</td>
										<td>
											<form:select path="color.id"  class="form-control">
												<form:option value="" label="- Select Color -" />
												<form:options items="${colorMap}" />
											</form:select>
										</td>
										<td><form:input path="compQty" id="compQty"  cssClass="form-control" /></td>
										<td><form:input path="metalWt" id="metalWt" cssClass="form-control" /></td>
										<td><form:input path="compRate" id="compRate"  cssClass="form-control" /></td>
										
										
										
								
								   </tr>
								   	
								   	<tr>
								   		<td colspan="12">
											<input type="submit" value="Save" class="btn btn-primary" />
											<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popCompCancel()"/>
											<form:input type="hidden" path="id" id="quotCompDtPk" />
											<input type="hidden" id="forCompQuotMtId" name="forCompQuotMtId" /> 
											<input type="hidden" id="forCompQuotDtId" name="forCompQuotDtId" /> 
										</td>
								   	
								   	</tr>
								</tbody>
						    </table>
			
						</form:form>
					</div>
					


<script type="text/javascript">



$(document).ready(
		function(e){
			$(".quotCompDtEntForm")
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
									compQty : {
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
									compQty : {
										greaterThan : "This field is required"
									}
								},	

							});


		});


</script>
