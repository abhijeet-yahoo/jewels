.<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="quotStnDt" id="quotStnDtEnt"
		action="/jewels/manufacturing/transactions/quotStnDt/addEdit.html"
		cssClass="form-horizontal quotStnDtForm">

		<table class="line-items editable table table-bquoted">
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Part</th>
					<th>Stone Type</th>
					<th>Shape</th>
					<th>Sub Shape</th>
					<th>Quality</th>
					<th>Breadth</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<form:select path="partNm.id" class="form-control">
							<form:option value="" label="- Select Part -" />
							<form:options items="${lookUpMastStoneMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="stoneType.id" class="form-control" onchange="javascript:popSetRateFromMaster()">
							<form:option value="" label="- Select Stone Type -" />
							<form:options items="${stoneTypeMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="shape.id" class="form-control"
							onChange="javascript:popSShape(this.value); javascript:popQuality(this.value); javascript:popSizeGroup(this.value); javascript:popChart(this.value)">
							<form:option value="" label="- Select Shape -" />
							<form:options items="${shapeMap}" />
						</form:select>
					</td>
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
							<form:select path="quality.id" class="form-control" onchange="javascript:popStoneRate();">
								<form:option value="" label="- Select Quality -" />
								<form:options items="${qualityMap}" />
							</form:select>
						</div>
					</td>
					<td><form:input path="breadth" cssClass="form-control"  /></td>
				</tr>
				
			
			</tbody>
			
			
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Size</th>
					<th>Sieve</th>
					<th>Size Group</th>
					<th>Stone</th>
					<th>Carat</th>
					<th>StoneRate</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td>
						<div id="sizeId">
							<form:select path="size" class="form-control"
								onChange="javascript:getSizeMMDetails();">
								<form:option value="" label="- Select Size -" />
								<form:options items="${chartMap}" />
							</form:select>
						</div>
					</td>
					<td><input type="text" id="vSieve" name="vSieve" class="form-control" disabled="disabled" value="${sieve}" /></td>
					<td><input type="text" id="vSizeGroupStr" name="vSizeGroupStr" value="${sizeGroupName}" class="form-control" disabled="disabled"/>
					</td>
					<td><form:input path="stone" cssClass="form-control"  onblur="javascript:getPointer()" /></td>
					<td><form:input path="carat" cssClass="form-control" /></td>
					<td><form:input path="stnRate"  type="number" cssClass="form-control" onchange="javascript:popHandlingRate();" /></td>
				</tr>
			</tbody>
			
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>HandlingRate</th>
					<th>Setting</th>
					
					<th>Setting Type</th>
					<th>Dia Category</th>
					<th>SettingRate</th>
					<th>Center Stone</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td><form:input path="handlingRate"  type="number" cssClass="form-control" /></td>
					<td>
						<form:select path="setting.id" class="form-control" onchange="javascript:popSetRateFromMaster()">
							<form:option value="" label="- Select Setting -" />
							<form:options items="${settingMap}" />
						</form:select>
					</td>
						
					<td>
						<form:select path="settingType.id" class="form-control" onchange="javascript:popSetRateFromMaster()">
							<form:option value="" label="- Select Setting -" />
							<form:options items="${settingTypeMap}" />
						</form:select>
					</td>
					<td><form:input path="diaCateg" class="form-control" />
						<form:errors path="diaCateg" /></td>
					<td><form:input path="setRate"  type="number" cssClass="form-control" /></td>
					<td><form:checkbox path="centerStone"></form:checkbox></td>	
				</tr>
			</tbody>
			
			
			
				<tr>
					<td colspan="11">
					<input type="submit" value="Save" class="btn btn-primary" />
					<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popStoneCancel()" />
						<form:input type="hidden" path="id" id="stnPkId" />
						<input type="hidden" id="pQuotMtId" name="pQuotMtId" />
						<input type="hidden" id="pQuotDtId" name="pQuotDtId" />
						<input type="hidden" id="pSieve" name="pSieve" />
						<input type="hidden" id="pSizeGroup" name="pSizeGroup" />
						
					</td>
				</tr>
			
		</table>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(function(e) { 
		$(".quotStnDtForm").validate(
			{
				rules : {
					'partNm.id' : {
						required : true,
					},
					'stoneType.id' : {
						required : true,
					},
					'shape.id' : {
						required : true,
					},
					'quality.id' : {
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