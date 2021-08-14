<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:set var="option" value="Color" />

<c:if test="${success eq true}">
		<div class="alert alert-success">Color ${action} successfully!</div>
</c:if>


<div>

	<div id="toolbar">
		
		<c:choose>
			<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/color/add.html"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Color</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Color</a>
			</c:otherwise>
		</c:choose>
		
		
	</div>	
	
	<div>
		<table  data-toggle="table" data-url="/jewels/manufacturing/masters/color/listing.html?opt=1"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="520" >
			<thead>
				<tr class="btn-primary">
					<th data-field="name" data-align="left" data-sortable="true">Color Name</th>
					<th data-field="desc" data-align="left" data-sortable="true">Description</th>
					<th data-field="twoTone" data-align="left" data-sortable="true">Two Tone</th>
					<th data-field="colorTone" data-align="left" data-sortable="true">Color Tone</th>
					<th data-field="defColor" data-sortable="true">Default Color</th>
					<th data-field="deactive" data-sortable="true">Deactive</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<script type="text/javascript">



	/* $(document).ready(function(e){
		
		var mytable = "";
		
		for(i = 0;i< 3; i++){
			mytable += "<th data-field='action2' data-align='center'>new</th>"
		}
		
		
		
		
		alert(mytable);

		
		
		$('#asd').append(mytable);
		
		
	}) */














</script>




 
