<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="Department" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Department ${action} successfully!</div>
</c:if>

<div>
	<div id="toolbar">
		
		<c:choose>
			<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px" type="button"
				href="/jewels/manufacturing/masters/department/add.html"><span
				class="glyphicon glyphicon-plus"></span>&nbsp;Add Department</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Department</a>
			</c:otherwise>
		</c:choose>
			
	</div>
	<div class="table-responsive"> 
		<table data-toggle="table" data-url="/jewels/manufacturing/masters/department/listing.html?opt=1"
			data-toolbar="#toolbar" data-pagination="true" 
			class="table table-striped table-hover dt-responsive display nowrap" cellspacing="0"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-striped="true" data-pagination="true">
			<thead>
				<tr class = btn-primary>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
					<th data-field="processSeq"  data-sortable="true">Process Seq</th>
					<th data-field="name" data-align="left" data-sortable="true">Department</th>
					<th data-field="code"  data-sortable="true">Code</th>
					<th data-field="branchMaster"  data-sortable="true">Branch</th>
					<th data-field="lossPerc"  data-sortable="true">Loss%</th>
					<th data-field="extraWt"  data-sortable="true">Extra Wt</th>
					<th data-field="expectedRecovery"  data-sortable="true">Expected Recovery</th>
					<th data-field="process"  data-sortable="true">Process</th>
					<th data-field="wip"  data-formatter="dataFormatter">Wip</th>
					<th data-field="castBal"  data-formatter="dataFormatter">CastBal</th>
					<th data-field="pcsProd" data-formatter="dataFormatter">Pcs Prod</th>
					<th data-field="stoneProd" data-formatter="dataFormatter">Stone Prod</th>
					<th data-field="looseMetalStk" data-formatter="dataFormatter">Loose Metal Stk</th>
					<th data-field="compStk" data-formatter="dataFormatter">Component Stk</th>
					<th data-field="stoneStk" data-formatter="dataFormatter">Stone Stk</th>
					<th data-field="lossBookDept" data-formatter="dataFormatter">Loss Book</th>
					<th data-field="barcodeFlg" data-formatter="dataFormatter">Barcode</th>
					<th data-field="deactive">Status</th>
					
				</tr>
			</thead>
		</table>
	</div>
</div>

<script type="text/javascript">

	function dataFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
		
	}
	
	

	</script>

