<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="option" value="EmialConcept" />

<c:if test="${success eq true}">
		<div class="alert alert-success">emailConcept ${action} successfully!</div>
</c:if>
<div>
	<div id="toolbar">		
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="/jewels/manufacturing/transactions/emailConcept/add.html">
			<span class="glyphicon glyphicon-plus">
			</span> &nbsp;Add EmailConcept</a>
	</div>
	<div>
		<table data-toggle="table" data-url="/jewels/manufacturing/transactions/emailConcept/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-height="520" data-striped="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="emailConIdMax" data-align="center" data-sortable="true">Id</th>
					<th data-field="date" data-align="left" data-sortable="true">Date</th>
					<th data-field="name" data-align="left" data-sortable="true">EmialConceptNm</th>
					<th data-field="version" data-align="left" data-sortable="true">Version</th>
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
					<th data-field="category" data-align="left" data-sortable="true">Category</th>
					<th data-field="hold" data-align="left" data-sortable="true">Hold</th>
					<th data-field="cancel" data-align="left" data-sortable="true">Cancel</th>
					<th data-field="done" data-align="left" data-sortable="true">Done</th>
					<th data-field="modify" data-align="left" data-sortable="true">Modify</th>
					<th data-field="versionFlg" data-align="left" data-sortable="true">VersionFlg</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>