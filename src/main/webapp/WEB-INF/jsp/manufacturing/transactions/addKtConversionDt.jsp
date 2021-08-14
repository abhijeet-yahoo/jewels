<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

	<div class="form-group">
		<form:form commandName="ktConversionDt" id="ktConvDtFormId"
				action="/jewels/manufacturing/transactions/ktConversionDt/add.html"
				cssClass="form-horizontal ktConversionDtForm">
	
				<table class="line-items editable table table-bordered">
					<thead class="panel-heading">
						<tr class="btn-warning" class="panel-heading">
							<th>Purity</th>
							<th>Color</th>
							<th>MetalWt</th>
							<!-- <th>UsedMetalWt</th> -->
							<th>PureWt</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>   
								<form:select path="issPurity.id" class="form-control" onchange="javascript:getIssPurityConversion();refreshData()">
									<form:option value="" label=" Select Purity " />
									<form:options items="${purityMapp}" />
								</form:select>
							</td>
							<td>
								<form:select path="issColor.id" class="form-control">
									<form:option value="" label=" Select Color " />
									<form:options items="${colorMap}" />
								</form:select>
							</td>
							<td><form:input path="issFreshMetalWt"  cssClass="form-control" onblur="javascript:calculatePureWt()" /></td>
							<%-- <td><form:input path="issUsedMetalWt"   cssClass="form-control" onblur="javascript:popValidation();calculatePureWt()" /></td> --%>
							<td><form:input path="pureWt" cssClass="form-control" readonly="true"/></td>
						</tr>
						<tr>
							<td colspan="10">
								<input type="submit" value="Save" class="btn btn-primary" id="issueSubmitBtnId" />
								<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popKtConvIssCancelBtn()" />
								<form:input type="hidden" path="id" id="issKtDtId" />
								<form:input type="hidden" path="uniqueId" />
								<input type="hidden" id="vMtId" name="vMtId" />
							</td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
		
		
		
		
 <script>
 
 $(document).ready(function(e){
	 
	 $(".ktConversionDtForm")
		.validate(
				{
					rules : {
						'issPurity.id' : {
							required : true,
						},
						'issColor.id' : {
							required : true,
						},
						
						issFreshMetalWt : {
							required : true,
						}
						

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
				});
	 
	 
	 
	 
 })
 
 
 
 </script>