<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="valueAdditionMt" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Value Addition ${action} successfully!</div>
</c:if>


<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/vaddMt/listing.html"
			data-toolbar="#toolbar" data-pagination="true" 
			data-side-pagination="server"
			data-search="true"
			data-height="550" >
			<thead>
				<tr class="btn-primary">
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
					<th data-field="invNo" data-align="left" data-sortable="true">InvNo</th>
					<th data-field="invDate" data-align="left" data-sortable="true">InvDate</th>
					<th data-field="metalRate" data-align="left" data-sortable="true">Metal Rate</th>
					<th data-field="silverRate" data-align="left" data-sortable="true">Silver Rate</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
	
	