<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%-- <%@ include file="/WEB-INF/jsp/excelModel/ledgerExcelUpload.jsp"%> --%>


<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script>
 <script src="/jewels/bootstrap-table-master/dist/extensions/auto-refresh/bootstrap-table-auto-refresh.min.js"></script>



<c:set var="option" value="ledger" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Ledger ${action} successfully!</div>
</c:if>

	
	
		<div id="ledgerToolBar">
		
		<c:choose>
			<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/ledger/add.html"><span
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
	
	<div>
		<table id="tableId" data-toggle="table" data-url="/jewels/manufacturing/masters/ledger/listing.html"
			data-toolbar="#ledgerToolBar" data-pagination="true" data-show-columns="true"
			data-side-pagination="server" data-striped="true" data-show-export="true"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-height="520">
			<thead>
				<tr class="btn-primary">
				    <th data-field="code"  data-width="70px" data-sortable="true">Code</th>
					<th data-field="name" data-sortable="true">Name</th>
					<th data-field="ledgerGroup" data-sortable="true">Group</th>
					<th data-field="transportArea" data-sortable="true">Transport Area</th>
					<th data-field="city"  data-width="70px" data-sortable="true">City</th>
					<th data-field="zip"  data-width="70px" data-sortable="true">Zip</th>
					<th data-field="gstn" data-sortable="true">GSTN</th>
					<th data-field="defaultFlag" data-sortable="true">Default</th>
					<th data-field="exportClient" data-sortable="true">Export Client</th>
					<th data-field="action1">Edit</th>
					<th data-field="action2">Delete</th>
				</tr>
			</thead>
		</table>
	</div>


<script type="text/javascript">


	
function popLedgerList(){
	$("#ledgerTableId").bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/ledger/listing.html"
			});
}

</script>
