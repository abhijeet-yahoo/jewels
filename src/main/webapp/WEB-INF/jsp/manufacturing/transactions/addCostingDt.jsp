
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


		<div class="form-group">
			<form:form commandName="costingDt" id="costingDtEnt"
			cssClass="form-horizontal costingDtEntForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
				<tr  class="panel-heading">
				
				<c:if test="${costingType eq 'merge'}">
					<th class="col-xs-2" >Client</th>
					<th class="col-xs-1" >GrossWt</th>
					<th class="col-xs-4" ></th>
				</c:if>
				<c:if test="${costingType ne 'merge'}">
				<th class="col-xs-2" >Item No</th>
					<th class="col-xs-2" >Client</th>
					<th class="col-xs-1" >GrossWt</th>
					<th class="col-xs-1" >Loss %</th>
					<th class="col-xs-1" >Other</th>
					<th class="col-xs-1" >Display%</th>
					<th class="col-xs-4" ></th>
				</c:if>
					
				</tr>
				</thead>
					<tbody>
					<tr>
					
					<c:if test="${costingType eq 'merge'}">
					<td class="col-xs-2">
							<form:select path="party.id" id="allPartyId" class="form-control">
								<form:option value="" label="- Select Party -" />
								<form:options items="${allPartyMap}" />
							</form:select>
						</td>
						<td class="col-xs-1"> <form:input path="grossWt" id="costDtGrossWt" cssClass="form-control" /></td>
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:saveCostingDt()"/> 
						</td>
						<td class="col-xs-4"></td>
					
					</c:if>
					
					
					<c:if test="${costingType ne 'merge'}">
						<td class="col-xs-2"><form:input path="itemNo"  cssClass="form-control" /></td>
						 <td class="col-xs-2">
							<form:select path="party.id" id="allPartyId" class="form-control">
								<form:option value="" label="- Select Party -" />
								<form:options items="${allPartyMap}" />
							</form:select>
						</td>
						<td class="col-xs-1"> <form:input path="grossWt" id="costDtGrossWt" cssClass="form-control" /></td>
						<td class="col-xs-1"> <form:input path="lossPercDt" id="lossPercDt" cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="other"  cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="dispPercentDt"  cssClass="form-control" /></td>
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:saveCostingDt()"/> 
						</td>
						<td class="col-xs-4"></td>
						</c:if>
					</tr>
				</tbody>
			</table>
			
	</form:form>
 </div>

<script type="text/javascript">

$(document)
.ready(
		function(e) {
			
			
			
		});
</script>



	