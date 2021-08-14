<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<form:form commandName="designStone" id="designStone"
	action="/jewels/manufacturing/masters/designStone/add.html"
	cssClass="form-horizontal designStoneForm">

	<table class="line-items editable table table-bordered">
		
		
		<thead class="panel-heading">
			<tr class="btn-warning">
				<th>Part</th>
				<th>Stone Type</th>
				<th>Shape</th>
				<th>Sub Shape</th>
				<th>Quality</th>
				<th>Width</th>
				<th>Size/MM</th>
				<th>Sieve</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			
				<td><form:select path="partNm.id" class="form-control">
						<form:option value="" label="- Select PartNm -" />
						<form:options items="${lookUpMastStoneMap}" />
					</form:select>
				</td>
				<td><form:select path="stoneType.id" class="form-control">
						<form:option value="" label="- Select Stone Type -" />
						<form:options items="${stoneTypeMap}" />
					</form:select></td>
				<td><form:select path="shape.id" class="form-control"
						onChange="javascript:popSShape(this.value); javascript:popQuality(this.value); javascript:popSizeGroup(this.value); javascript:popChart(this.value);">
						<form:option value="" label="- Select Shape -" />
						<form:options items="${shapeMap}" />
					</form:select></td>
				<td>
					<div id="sShapeId">
						<form:select path="subShape.id" class="form-control">
							<form:option value="" label="- Select Sub Shape -" />
							<form:options items="${subShapeMap}" />
						</form:select>
					</div>
				</td>
				<td>
					<div id="qualityId">
						<form:select path="quality.id" class="form-control"  >
							<form:option value="" label="- Select Quality -" />
							<form:options items="${qualityMap}" />
						</form:select>
					</div>
				</td>
				<td><form:input path="breadth" cssClass="form-control"  /></td>
				<td>
					<div id="sizeId">
						<form:select path="size" class="form-control"
							onChange="javascript:getSizeMMDetails();">
							<form:option value="" label="- Select Size -" />
							<form:options items="${chartMap}" />
						</form:select>
					</div>
				</td>
				<td><input type="text" id="vSieve" name="vSieve"
					class="form-control" maxlength="7" size="5" disabled="true" /></td>
				
			</tr>
			
		</tbody>
			
			
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Size Group</th>
					<th>Stone</th>
					<th>Carat</th>
					<th>MCarat</th>
					<th>Stone Rate</th>
					<th>Setting</th>
					<th>Set Type</th>
					<th>Dia Category</th>
					
				</tr>
			</thead>
			
			
			
			<tbody>
				<tr>
				
					<td><input type="text" id="vSizeGroupStr"
							name="vSizeGroupStr" value="${sizeGroupName}" class="form-control" disabled="true" />
					</td>
					<td><form:input path="stone" cssClass="form-control"
							maxlength="7" size="7" onBlur="javascript:getPointer();" /></td>
					<td><form:input path="carat" cssClass="form-control"
							maxlength="7" size="7" /></td>
					
					<td><form:input path="mcarat" cssClass="form-control"
							maxlength="7" size="7" /></td>
									
					<td><form:input path="stnRate" cssClass="form-control"
							maxlength="7" size="7" /></td>		
							
					<td><form:select path="setting.id" class="form-control">
							<form:option value="" label="- Select Setting -" />
							<form:options items="${settingMap}" />
						</form:select></td>
						
					<td><form:select path="settingType.id" class="form-control">
							<form:option value="" label="- Select Setting Type -" />
							<form:options items="${settingTypeMap}" />
						</form:select>
					</td>
					<td><form:input path="diaCateg" class="form-control" />
						<form:errors path="diaCateg" /></td>
					
				</tr>
			</tbody>
			
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Center_Stone</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td><form:checkbox path="centerStone" width="3cm"></form:checkbox></td>
				</tr>
			</tbody>
			
			
			<tr>
				<td colspan="14">
					<input type="submit" value="Save" class="btn btn-primary" />
					<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popCancelBtn()"/>
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="sieve" />
					<form:input type="hidden" path="sizeGroupStr" /> 
					<input type="hidden" id="dsStyleNo" name="dsStyleNo" /> 
					<input type="hidden" id="dsVersion" name="dsVersion" />
				</td>
			</tr>
		
	</table>
</form:form>



<script type="text/javascript">
	$(document).ready(function(e) {
		$(".designStoneForm").validate({
			rules : {
				'partNm.id' :{
					required : true,
				},
				'stoneType.id' : {
					required : true,
				},
				'shape.id' : {
					required : true,
				},
				size : {
					required : true,
				},
				stone : {
					required : true,
				},
				carat : {
					required : true,
				},
			},
			highlight : function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages : {
			}
		});
	});

</script>