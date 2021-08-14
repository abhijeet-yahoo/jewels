<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="diaBagStnObj" id="diaBagStnObj"
		action="/jewels/manufacturing/transactions/diamondBagging/add.html"
		cssClass="form-horizontal diaBagStnDtForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
			</thead>
			<tbody>
				<tr>
					<th>Design Stone</th>
					<th>Design Carat</th>
					<th>Bag Stone</th>
					<th>Bag Carat</th>
					<th>Setting</th>
					<th>Set Type</th>
					<th>&nbsp;</th>
				</tr>
				<tr>
					<td><form:input path="stone" cssClass="form-control" /></td>
					<td><form:input path="carat" cssClass="form-control" /></td>
					<td><form:input path="bagStone" cssClass="form-control" /></td>
					<td><form:input path="bagCarat" cssClass="form-control" /></td>
					<td>
						<form:select path="setting.id" class="form-control">
							<form:option value="" label="- Select Setting -" />
							<form:options items="${settingMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="settingType.id" class="form-control">
							<form:option value="" label="- Select Setting -" />
							<form:options items="${settingTypeMap}" />
						</form:select>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="11"><input type="submit" value="Save"
						class="btn btn-primary" />
						<form:input type="hidden" path="id"/>
						<form:input type="hidden" path="sieve" />
						<form:input type="hidden" path="sizeGroupStr" />
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
