<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<div class="form-group">
	<form:form commandName="jobRecStnDt" id="jobRecStnDtEnt"
		action="/jewels/manufacturing/transactions/jobRecStnDt/add.html"
		cssClass="form-horizontal jobRecStnDtForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Part</th>
					<th>Stone Type</th>
					<th>Shape</th>
					<th>Quality</th>
					<th>Size</th>
					<th>Sieve</th>
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
						<form:select path="stoneType.id" class="form-control"  required="true">
							<form:option value="" label="- Select Stone Type -" />
							<form:options items="${stoneTypeMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="shape.id" class="form-control" required="true"
							onChange="javascript:popSShape(this.value); javascript:popQuality(this.value); javascript:popSizeGroup(this.value); javascript:popChart(this.value);">
							<form:option value="" label="- Select Shape -" />
							<form:options items="${shapeMap}" />
						</form:select>
					</td>
					
					<td>
						
							<form:select path="quality.id" class="form-control" required="true">
								<form:option value="" label="- Select Quality -" />
								<form:options items="${qualityMap}" />
							</form:select>
						
					</td>
					
					<td>
						
							<form:select path="size" class="form-control" required="true"
								onChange="javascript:getSizeMMDetails();">
								<form:option value="" label="- Select Size -" />
								<form:options items="${chartMap}" />
							</form:select>
						
					</td>
					<td><input type="text" id="vSieve" name="vSieve" class="form-control" disabled="true" value="${sieve}" /></td>
					
				</tr>
				
			
			</tbody>
			
			
			<thead class="panel-heading">
				<tr class="btn-warning">
				
					<th>Size Group</th>
					<th>Stone</th>
					<th>Carat</th>
					<th>StoneRate</th>
						<th>HandlingRate</th>
					<th>Setting</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					
					<td><input type="text" id="vSizeGroupStr" name="vSizeGroupStr" value="${sizeGroupName}" class="form-control" disabled="true" />
					</td>
					<td><form:input path="stone" cssClass="form-control"  onblur="javascript:getPointer()" /></td>
					<td><form:input path="carat" cssClass="form-control" /></td>
					<td><form:input path="stnRate"  type="number" cssClass="form-control" /></td>
						<td><form:input path="handlingRate"  type="number" cssClass="form-control" /></td>
					<td>
						<form:select path="setting.id" class="form-control" onchange="javascript:popSetRateFromMaster()">
							<form:option value="" label="- Select Setting -" />
							<form:options items="${settingMap}" />
						</form:select>
					</td>
				</tr>
			</tbody>
			
			<thead class="panel-heading">
				<tr class="btn-warning">
				
					<th>Setting Type</th>
					<th>SettingRate</th>
					<th>Center Stone</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
				
						
					<td>
						<form:select path="settingType.id" class="form-control" onchange="javascript:popSetRateFromMaster()">
							<form:option value="" label="- Select Setting -" />
							<form:options items="${settingTypeMap}" />
						</form:select>
					</td>
					
						
					<td><form:input path="setRate"  type="number" cssClass="form-control" /></td>
					<td><form:checkbox path="centerStone"></form:checkbox></td>	
				</tr>
			</tbody>
			
			
			
				<tr>
					<td colspan="11">
					<input type="submit" value="Save" class="btn btn-primary" />
					<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popStoneCancel()" />
						<form:input type="hidden" path="id" id="stnPkId" />
						<input type="hidden" id="pJobRecMtId" name="pJobRecMtId" />
						<input type="hidden" id="pJobRecDtId" name="pJobRecDtId" />
						<input type="hidden" id="pSieve" name="pSieve" />
						<input type="hidden" id="pSizeGroup" name="pSizeGroup" />
						
					</td>
				</tr>
			
		</table>
	</form:form>
</div> 

<script type="text/javascript">
	$(document).ready(function(e) { 

		
		$('#jobRecStnDtEnt #partNm\\.id').chosen();
		$('#jobRecStnDtEnt #stoneType\\.id').chosen();
		$('#jobRecStnDtEnt #shape\\.id').chosen();
		$('#jobRecStnDtEnt #quality\\.id').chosen();
		$('#jobRecStnDtEnt #subShape\\.id').chosen();
		$('#jobRecStnDtEnt #size').chosen();
	
		
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
			
		
		
		$(".jobRecStnDtForm").validate(
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