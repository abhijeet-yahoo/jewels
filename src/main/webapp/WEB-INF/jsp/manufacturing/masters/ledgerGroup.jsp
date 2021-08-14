<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>




<c:if test="${success eq true}">
		<div class="alert alert-success">Ledger ${action} successfully!</div>
</c:if>

	<div id="ledgerGroupToolBar">
		
		<c:choose>
			<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/ledgerGroup/add.html"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Ledger</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Ledger</a>
			</c:otherwise>
		</c:choose>
	</div>		
		
														
	<div class="table-responsive"> 
		<table id="tableId" data-toggle="table" data-url="/jewels/manufacturing/masters/ledgerGroup/listing.html"
			data-toolbar="#ledgerGroupToolBar" data-pagination="true" data-show-columns="true"
			class="table table-striped table-hover dt-responsive display nowrap" cellspacing="0"
			data-side-pagination="server" data-striped="true" 
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-height="500" data-show-export="true">
			<thead>
				<tr class="btn-primary">
				    <th data-field="state" data-checkbox="true"></th>
					<th data-field="name" data-sortable="true">Account Group Name</th>
					<th data-field="ledgerGroupCode" data-sortable="true">Code</th>
					<th data-field="mainGroup" data-sortable="true">Main Group</th>
					<th data-field="deactive" data-sortable="true">Status</th>
					<th data-field="action1" data-sortable="true">Edit</th>
					<th data-field="action2" data-sortable="true">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
	
	</div>

<script type="text/javascript">



</script>

