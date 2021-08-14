<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
			<div class="form-group">
				<form:form commandName="meltingRecDt" id="meltingRecDt"
					action="/jewels/manufacturing/transactions/meltingRecDt/add.html"
					cssClass="form-horizontal meltingRecDtForm">

					<table class="line-items editable table table-bordered">
						<thead class="panel-heading">
							<tr class="btn-warning" class="panel-heading">
								<th>Location</th>
								<th>Purity</th>
								<th>Color</th>
								<th>Metal Wt</th>
								<th>Stone</th>
								<th>Carat</th>
								<th>Net Wt</th>
								<th>Pure Wt</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							
							<td><form:select path="deptLocation.id" class="form-control"  required="true">
												<form:option value="" label="- Select Location -" />
												<form:options items="${locationMetalMap}" />
											</form:select></td>
						
								 <td>
									<div id="purityId">
										<form:select path="purity.id" id="recPurity" class="form-control" onChange="javascript:getPurityRecConversion();refreshRecData()">
											<form:option value="" label="- Select Purity -" />
											<form:options items="${purityMap}" />
										</form:select>
									</div>
								</td> 
								<td>
									<div id="colorId">
										<form:select path="color.id" id="recColor" class="form-control">
											<form:option value="" label="- Select Color -" />
											<form:options items="${colorMap}" />
										</form:select>
									</div>
								</td>
								<td><form:input path="recFreshMetalWt" cssClass="form-control" onblur="javascript:getRecNetWt();" /></td>
								<td><form:input path="recStone"  cssClass="form-control"  onblur="javascript:roundUpRecStone()" /></td>
								<td><form:input path="recCarat"  cssClass="form-control"/></td>
								<td><form:input path="recNetWt" cssClass="form-control" disabled="true" /></td>
								<td><form:input path="recExcpPureWt" cssClass="form-control" disabled="true" /></td>
								
							</tr>
							<tr>
								<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="receiveSubmitBtnId" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMelRecCancelBtn()"/> 
									<form:input type="hidden" path="id" id="recDtUniPk" />
									<form:input type="hidden" path="uniqueId" />
									<input type="hidden" id="recInvNo" name="recInvNo" />
									<input type="hidden" id="recPNetWt" name="recPNetWt" />
									<input type="hidden" id="recPExcpPureWt" name="recPExcpPureWt" />
									<input type="hidden" id="recPurityConversion" name="recPurityConversion" />
									<input type="hidden" id="pRecFMetalWt" name="pRecFMetalWt" />
									<input type="hidden" id="vLoss" name="vLoss" />
									<input type="hidden" id="pvRecTotalExpcPureWts" name="pvRecTotalExpcPureWts" />
									<input type="hidden" id="pMeltingBal" name="pMeltingBal" />
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
		
		
		
		
<script type="text/javascript">
	$(document).ready(function(e) { 

		$(".meltingRecDtForm").validate(
				{
					rules : {
						
						
						'purity.id' : {
							required : true,
						},
						'color.id' : {
							required : true,
						},
						
						recFreshMetalWt : {
							required : true,
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
				
					
				});
		
		
		if($("#recFreshMetalWt").val() === '0.0'){
			$("#recFreshMetalWt").prop('disabled', true);
		}
		
	
		
		
		
		
	});
</script>
