 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>



<c:set var="option" value="CostingJobMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">CostingJobMt ${action} successfully!</div>
</c:if>


<div>
<div id="toolbar">
	<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/costingJobMt/add.html"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Create New Invoice</a>

</div>

<div>

<table data-toggle="table" data-url="/jewels/manufacturing/transactions/costingJobMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="520" >
			<thead>
				<tr class="btn-primary">
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
					<th data-field="invNo" data-align="left" data-sortable="true">InvNo</th>
					<th data-field="invDate" data-align="left" data-sortable="true">InvDate</th>
					<th data-field="metalRate" data-align="left" data-sortable="true">MetalRate</th>
					<th data-field="silverRate" data-align="left" data-sortable="true">SilverRate</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>



</table>


</div>


</div> 