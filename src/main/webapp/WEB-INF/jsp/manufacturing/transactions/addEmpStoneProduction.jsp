<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="empStoneProduction" id="empStoneProdBrkEntry"
		action="/jewels/manufacturing/transactions/empStoneProduction/add.html"
		cssClass="form-horizontal empStoneProdBrkEntryForm">
		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Employee</th>
					<th>Stone Type</th>
					<th>Shape</th>
					<th>Quality</th>
					<th>Size Group</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<form:select path="employee.id" class="form-control">
							<form:option value="" label="- Select Employee -" />
							<form:options items="${employeeMap}" />
						</form:select>
					</td>

					<td>
						<form:select path="stoneType.id" class="form-control" disabled="true" >
							<form:option value="" label="- Select StoneType -" />
							<form:options items="${stoneTypeMap}" />
						</form:select>
					</td>
					<td>	
						<form:select path="shape.id" class="form-control" disabled="true" >
							<form:option value="" label="- Select Shape -" />
							<form:options items="${shapeMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="quality.id" class="form-control" disabled="true" >
							<form:option value="" label="- Select Quality -" />
							<form:options items="${qualityMap}" />
						</form:select>
					</td>	
					<td>
						<form:select path="sizeGroup.id" class="form-control" disabled="true" >
							<form:option value="" label="- Select SizeGroup -" />
							<form:options items="${sizeGroupMap}" />
						</form:select>
					</td>

				</tr>
				<tr class="btn-warning">
					<th>Stone</th>
					<th>Carat</th>
					<th>Setting</th>
					<th>Set Type</th>
					<th>rate</th>
				</tr>
				<tr>
					<td><form:input path="stone" cssClass="form-control" disabled="true" /></td>
					<td><form:input path="carat" cssClass="form-control" disabled="true" /></td>
					<td><form:select path="setting.id" class="form-control">
							<form:option value="" label="- Select Setting -" />
							<form:options items="${settingMap}" />
						</form:select></td>
					<td><form:select path="settingType.id" class="form-control" onchange="javascript:calRate()">
							<form:option value="" label="- Select Setting Type -" />
							<form:options items="${settingTypeMap}" />
						</form:select></td>
					<td><form:input path="rate" cssClass="form-control"  /></td>

				</tr>
				<tr>
					<td colspan="11">
						<input type="submit" value="Save" class="btn btn-primary" onclick="editEmpStnProd();" />
						<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popEmpStnCancelBtn()"/> 
								<form:input type="hidden" path="id" /> 
								<form:input type="hidden" path="stoneType.id" />
								<form:input type="hidden" path="shape.id" />
								<form:input type="hidden" path="quality.id" />
								<form:input type="hidden" path="size" />
								<form:input type="hidden" path="sieve" />
								<form:input type="hidden" path="sizeGroup.id" />
								<form:input type="hidden" path="stone" />
								<form:input type="hidden" path="carat" />
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>