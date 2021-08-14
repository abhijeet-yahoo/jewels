<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<div class="form-group">
	<form:form commandName="orderStnDt" id="orderStnDtEnt"
		action="/jewels/manufacturing/masters/orderStnDt/add.html"
		cssClass="form-horizontal orderStnDtForm">

		<table class="line-items editable table table-bordered">
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
						<form:select path="partNm.id" class="form-control" required="true">
							<form:option value="" label="- Select Part -" />
							<form:options items="${lookUpMastStoneMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="stoneType.id" class="form-control" onchange="javascript:popSetRateFromMaster();popHandlingRate();" required="true">
							<form:option value="" label="- Select Stone Type -" />
							<form:options items="${stoneTypeMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="shape.id" class="form-control" required="true"
							onChange="javascript:popSShape(this.value); javascript:popQuality(this.value); javascript:popSizeGroup(this.value); javascript:popChart(this.value);popHandlingRate();">
							<form:option value="" label="- Select Shape -" />
							<form:options items="${shapeMap}" />
						</form:select>
					</td>
					<td>
				
						<form:select path="subShape.id" class="form-control">
							<form:option value="" label="- Select Sub Shape -" />
							<form:options items="${subShapeMap}" />
						</form:select>
				
					</td>
					<td>
						
							<form:select path="quality.id" class="form-control" required="true" onchange="javascript:popStoneRate();">
								<form:option value="" label="- Select Quality -" />
								<form:options items="${qualityMap}" />
							</form:select>
						
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
						
							<form:select path="size" class="form-control" required="true"
								onChange="javascript:getSizeMMDetails();">
								<form:option value="" label="- Select Size -" />
								<form:options items="${chartMap}" />
							</form:select>
						
					</td>
					<td><input type="text" id="vSieve" name="vSieve" class="form-control" disabled="true" value="${sieve}" /></td>
					<td><input type="text" id="vSizeGroupStr" name="vSizeGroupStr" value="${sizeGroupName}" class="form-control" disabled="true" />
					</td>
					<td><form:input path="stone" cssClass="form-control"  onblur="javascript:getPointer()" /></td>
					<td><form:input path="carat" cssClass="form-control" /></td>
					<td><form:input path="stnRate"  type="number" cssClass="form-control" /></td>
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
						<input type="hidden" id="pOrderMtId" name="pOrderMtId" />
						<input type="hidden" id="pOrderDtId" name="pOrderDtId" />
						<input type="hidden" id="pSieve" name="pSieve" />
						<input type="hidden" id="pSizeGroup" name="pSizeGroup" />
						
					</td>
				</tr>
			
		</table>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(function(e) { 

		
		$('#orderStnDtEnt #partNm\\.id').chosen();
		$('#orderStnDtEnt #stoneType\\.id').chosen();
		$('#orderStnDtEnt #shape\\.id').chosen();
		$('#orderStnDtEnt #quality\\.id').chosen();
		$('#orderStnDtEnt #subShape\\.id').chosen();
		$('#orderStnDtEnt #size').chosen();
	
		
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
			
		
		
		$(".orderStnDtForm").validate(
			{
				rules : {
				
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