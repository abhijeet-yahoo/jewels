<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div class="form-group">
	<form:form commandName="designMold" id="designMoldEnt"
		action="/jewels/manufacturing/masters/designMold/add.html"
		cssClass="form-horizontal designMoldEntForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-primary">
					<th class="col-sm-2">Style No</th>
						<th class="col-sm-2">Type</th>
						<th class="col-sm-2">Drawer No</th>
						<th class="col-sm-2">Rack No</th>
						<th class="col-sm-2">Mould Qty</th>
						<th class="col-sm-2"></th>  
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="col-sm-2"><form:input path="design.mainStyleNo" cssClass="form-control" /></td>
						<td class="col-sm-2">
							<form:select path="designMoldType.id" class="form-control">
								<form:option value="" label="- Select Type -" />
								<form:options items="${designMoldTypeMap}" />
							</form:select>
						</td>
						<td class="col-sm-2"><form:input path="drawerNo" cssClass="form-control" /></td>
						<td class="col-sm-2"><form:input path="rackNo" cssClass="form-control"/></td>
						<td class="col-sm-2"><form:input path="mouldQty" cssClass="form-control"/></td>
						<td class="col-sm-2">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" id="saveBtnId" name="saveBtnId"  value="Save" class="btn btn-primary"/>
							&nbsp;
							<input type="button" id="cancelBtn" value="Cancel" class="btn btn-danger" onclick="javascript:popCancel()"/>	
							<form:input type="hidden" path="id" />
						</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>


<script type="text/javascript">

	



</script>



