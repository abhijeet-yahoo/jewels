<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<div class="row">&nbsp;</div>
	<form:form commandName="orderDt" id="orderDt"
		action="/jewels/manufacturing/masters/orderDt/add.html"
		cssClass="form-horizontal orderDetailsForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Style</th>
					<th>Version</th>
					<th>Purity</th>
					<th>Color</th>
					<th>Due Date</th>
					<th>Quantity</th>
					<th>Gross Wt</th>
					<th>Req Carats</th>
					<th>Size</th>
					<th>Ref No#</th>
					<th>Stamp</th>
					
				</tr>
			</thead>
			<tbody>
				<tr >
					<td><form:input path="design.styleNo" cssClass="form-control" onBlur="javascript:getStyleDt();" class="btn btn-info"/></td>
					<td><form:input path="design.version" cssClass="form-control" size="5" readonly="true" /></td>
					<td>
						<form:select path="purity.id" id="oDtPurId" class="form-control" onChange="javascript:getMtWt();">
							<form:option value="" label="- Select Purity -" />
							<form:options items="${purityMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="color.id" id="oDtClrId" class="form-control">
							<form:option value="" label="- Select Color -" />
							<form:options items="${colorMap}" />
						</form:select>
					</td>
					<td><form:input path="dueDate" cssClass="form-control" /></td>
					<td><form:input path="quantity" cssClass="form-control" /></td>
					<td><form:input path="grossWt" cssClass="form-control" /></td>
					<td><form:input path="reqCarat"  cssClass="form-control" /></td>
					<td>
						<form:select path="productSize.id" class="form-control">
							<form:option value="" label=" Select Size " />
							<form:options items="${productSizeMap}" />
						</form:select>
					</td>
					<td ><form:input path="refNo" cssClass="form-control" /></td>
					<td><form:input path="stampInst" cssClass="form-control" /></td>
				</tr>
				<tr>
					<th colspan="2" class="btn-warning">Tag Price</th>
					<th colspan="2" class="btn-warning">Destination</th>
				</tr>
				<tr>
					<td colspan="2"><form:input path="tagPrice" cssClass="form-control" /></td>
					<td colspan="2"><form:input path="destination"  cssClass="form-control" /></td>
				</tr>
				<tr>
					<th colspan="5" class="btn-warning">Order Item Remark</th>
					<th colspan="5" class="btn-warning">Style Master Remark</th>
				</tr>
				<tr>
					<td colspan="5"><form:input path="remark" cssClass="form-control" /></td>
					<td colspan="5"><form:input path="designRemark" value="${smRemark}"
							cssClass="form-control" disabled="true" /></td>
				</tr>

				<tr>
					<td colspan="11">
							<input type="submit" value="Save" class="btn btn-primary" /> 
							<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popOrderDtCancelBtn()" /> 
							<form:input type="hidden" path="id" id="pOrdDtId" /> 
							<input type="hidden" id="pInvNo" name="pInvNo" />
							<input type="hidden" id="pStones" name="pStones"  value="${totalStones}"  />
							<input type="hidden" id="jsonMetal" name="jsonMetal" value='${jsonWaxWt}'  />
							<input type="hidden" id="tmpODtId" name="tmpODtId" value="${tmpODtId}" />
							<input type="hidden" id="prodSizeId" name="prodSizeId" value="${prodSizeId}" />
							<input type="hidden" id="tmpVal" name="tmpVal" />
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(function(e) { 
		$("#design\\.styleNo").autocomplete({
			source: "/jewels/manufacturing/masters/styleNo/list.html",
			minLength : 2
		});

		$(".orderDetailsForm").validate(
			{
				rules : {
					'design.styleNo' : {
						required : true,
					},
					'purity.id' : {
						required : true,
					},
					'color.id' : {
						required : true,
					},
					'quantity' : {
						required : true,
					}
				},
				highlight : function(element) {
					$(element).closest('.form-group').removeClass(
							'has-success').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass(
							'has-error').addClass('has-success');
				}
			});
	});
</script>