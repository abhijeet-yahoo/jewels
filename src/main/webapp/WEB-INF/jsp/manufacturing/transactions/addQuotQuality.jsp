<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>



		<div id="rowQQId" style="display: block;">
			<div class="form-group">
				<form:form commandName="quotQuality" id="quottQuality"
					action="/jewels/manufacturing/transactions/quotQuality/add.html"
					cssClass="form-horizontal quottQualityForm">
	
					<table class="line-items editable table table-bordered">
						<thead class="panel-heading">
							<tr class="panel-heading">
								<th>Stone Type</th>
								<th>Shape</th>
								<th>Quality</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<form:select path="stoneType.id" id="quotStoneTypeId" class="form-control" >
										<form:option value="" label="- Select StoneType -" />
										<form:options items="${stoneTypeMap}" />
									</form:select>
								</td>
								<td>
									<form:select path="shape.id" id="quotShapeId" class="form-control" onchange="javascript:popQuotQualitys();">
										<form:option value="" label="- Select Shape -" />
										<form:options items="${shapeMap}" />
									</form:select>
								</td>
								<td>
									<div id="quotQtilyId">
										<form:select path="quality.id" class="form-control">
											<form:option value="" label="- Select Quality -" />
											<form:options items="${qualityMap}" />
										</form:select>
									</div>
								</td>
							</tr>
							
								<tr>
											<td><input type="submit" id="saveQuotDtId" value="Save"
												class="btn btn-primary" />
											<td><input type="submit" id="saveAllQuotDtId" value="Save & Update All Style"
												class="btn btn-primary" />	
												<form:input type="hidden" path="id" id="quotQualityPkId"/>
												<input type="hidden" id="pOQInvNo" name="pOQInvNo" />
												<input type="hidden" id="updateFlg" name="updateFlg" />
												
										</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
		
		
		<script type="text/javascript">
		
var whichBtn;
$('#quottQuality input[type="submit"]').click(function(e) {
	whichBtn = $(this).attr("id");
	
	
});

</script>
		