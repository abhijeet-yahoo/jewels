
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="packCompDt" id="packCompDtEnt"
							action="/jewels/marketing/transactions/packCompDt/add.html"
							cssClass="form-horizontal packCompDtEntForm">
							
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Component</th>
										<th>Purity</th>
										<th>Color</th>
										<th>MetalWt</th>  
										<th>Rate</th>
										<th>Qty</th>
										
										<th>Per Pcs</th>
										<th>Per Gram</th>
										
									</tr>
								</thead>
								  <tbody>
									<tr>
									    <td>
									    	<form:select path="component.id"  class="form-control" disabled="true">
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</td>
										<td>
											<form:select path="purity.id"  class="form-control" disabled="true">
												<form:option value="" label="- Select Purity -" />
												<form:options items="${purityMap}" />
											</form:select>
										</td>
										<td>
											<form:select path="color.id"  class="form-control" disabled="true">
												<form:option value="" label="- Select Color -" />
												<form:options items="${colorMap}" />
											</form:select>
										</td>
										<td><form:input path="compWt" id="compWt" cssClass="form-control" readonly="true"/></td>
										<td><form:input path="compRate" id="compRate"  cssClass="form-control" /></td>
										<td><form:input path="compQty" id="compQty"  cssClass="form-control" readonly="true"/></td>
								
										<td><form:checkbox path="perPcRate"  /></td>
										
										<td><form:checkbox path="perGramRate"  /></td>
										
								
								   </tr>
								   	
								   	<tr>
								   		<td colspan="12">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<form:input type="hidden" path="id" id="packCompDtPk" />
											<input type="hidden" id="forCompPackMtId" name="forCompPackMtId" />
											<input type="hidden" id="forCompPackDtId" name="forCompPackDtId" /> 
											<input type="hidden" id="pComponentId" name="pComponentId" /> 
											<input type="hidden" id="pPurityId" name="pPurityId" /> 
											<input type="hidden" id="pColorId" name="pColorId" /> 
											
											
										</td>
								   	
								   	</tr>
								</tbody>
						    </table>
			
						</form:form>
</div>
<script type="text/javascript">


		




</script>