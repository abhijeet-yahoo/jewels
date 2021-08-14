<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="AutoStyleParameter" />

<div>

	<div id="toolbar">
		<c:choose>
			<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/autoStyleParameter/add.html"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div>
		<table data-toggle="table"
			data-url="/jewels/manufacturing/masters/autoStyleParameter/listing.html"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
			data-striped="true"  data-height="520">
			<thead>
				<tr class="btn-primary">
				
				<th data-field="category" data-align="left" data-sortable="true">Category</th>
					<th data-field="collection" data-align="left" data-sortable="true">Collection</th>
					
					<th data-field="lastNo" data-align="left" data-sortable="true">Last No.</th>
					
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>






