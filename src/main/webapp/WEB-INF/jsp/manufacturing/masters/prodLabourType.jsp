<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary" style="width: 100%">
	<c:set var="option" value="ProdLabourType" />
	<c:if test="${success eq true}">

		<div class="alert alert-success">ProdLabourType ${action}
			successfully!</div>
	</c:if>


	<div class="form-group">
		<form:form commandName="prodLabourType"
			action="<spring:url value='/manufacturing/masters/prodLabourType/add.html' />"
			cssClass="form-horizontal prodLabourTypeForm">

			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<%-- <div class="form-group">
				<label class="col-sm-4 text-right"></label> <label
					class="col-sm-1 text-right">Department:</label>
				<div class="col-sm-2">
					<form:select path="department.id" class="form-control">
						<form:option value="" label=" Select department " />
						<form:options items="${departmentMap}" />
					</form:select>
				</div>
			</div> --%>


		</form:form>
	</div>

	<!-- <div class="col-xs-12">
		<hr
			style="width: 100%; color: red; height: 3px; background-color: darkblue;" />
	</div> -->


	<div class="row">
		<div class="col-xs-12">
			<!-- <div class="col-xs-2">
				<div>
					<table id="catId" data-toggle="table" class="pull-left"
						data-url="/jewels/manufacturing/masters/category/listing.html?opt=2"
						data-pagination="true" data-side-pagination="server"
						data-height="470" data-search="true">
						<thead>
							<tr class="btn-primary">
								<th data-field="name">Category</th>
							</tr>
						</thead>
					</table>
				</div>
			</div> -->
			<div class="col-xs-12">
				<div>
					<div id="toolbar">



						<c:choose>
							<c:when test="${canAdd}">
								<a class="btn btn-info" style="font-size: 15px" type="button"
									href="/jewels/manufacturing/masters/prodLabourType/add.html">
									<span class="glyphicon glyphicon-plus"></span> &nbsp;Add
									ProdLabour Type
								</a>
							</c:when>
							<c:otherwise>
								<a class="btn btn-info" style="font-size: 15px" type="button"
									onClick="javascript:displayMsg(event, this)"
									href="javascript:void(0)"><span
									class="glyphicon glyphicon-plus"></span>&nbsp;Add ProdLabour
									Type</a>
							</c:otherwise>
						</c:choose>


			<div align="left">
			
							<br>
							<input type="text" id="deptartmentName" name="deptartmentName" size="15cm" placeholder="Department" onblur="javascript:popLabourChargeSearchListing()">
							<input type="text" id="categoryNm" name="categoryNm" size="15cm" placeholder="category" onblur="javascript:popLabourChargeSearchListing()">
							<input type="text" id="prodLobourName" name="prodLobourName" size="15cm" placeholder="ProdLabourType" onblur="javascript:popLabourChargeSearchListing()">
							
						</div>

						<input type="hidden" id="departmentId" name="departmentId">
					</div>
					<div>
						<div>
							<table id="prodLabTypeId" data-toggle="table"
								data-url="/jewels/manufacturing/masters/prodLabourType/listing.html"
								data-toolbar="#toolbar" data-pagination="true"
								data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
								data-striped="true" data-pagination="false" data-height="550">
								<thead>
									<tr class="btn-primary">
										<th data-field="department" data-align="left "data-sortable="true">Department</th>
										<th data-field="categName" data-align="left "data-sortable="true">Category</th>
										<th data-field="name" data-align="left" data-sortable="true">ProdLabourType</th>
										<th data-field="prodLabTypeDefault" data-sortable="true">Default</th>
										<th data-field="deactive" data-sortable="true">Status</th>
										<th data-field="action1" data-align="center">Edit</th>
										<th data-field="action2" data-align="center">Deactivate</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>

					<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- ending the panel -->

<script type="text/javascript">
	$(document).ready(
			function(e) {
				if (window.location.href.indexOf('categId') != -1) {

					var abcx = window.location.href;
					var categId = abcx.substring(window.location.href
							.indexOf('categId') + 8);

					var data = categId.split("_");
					var categrId = data[0];

					var deptIdd = data[1];
					$('#departmentId').val(deptIdd);
					$('#department\\.id').val(deptIdd);

					setChildOnEdit(categrId);
				}

				$("#department\\.id").focus();

			});

	function setChildOnEdit(id) {
		$("#prodLabTypeId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/prodLabourType/listing.html?id="
									+ id
									+ "&deptId="
									+ $('#departmentId').val()
						});

		$("#catId").bootstrapTable({
			pagingType : "bootstrapPager",
			pagerSettings : {
				textboxWidth : 70,
				firstIcon : "",
				previousIcon : "glyphicon glyphicon-arrow-left",
				nextIcon : "glyphicon glyphicon-arrow-right",
				lastIcon : "",
				searchOnEnter : true,
				language : "Page ~ of ~ pages"
			}
		});
	}

	function getChild(id) {

		var vDept = $('#department\\.id').val()

		if (vDept == null) {
			alert("please select the department and then select category");
		} else {

			$("#prodLabTypeId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/prodLabourType/listing.html?id="
										+ id
										+ "&deptId="
										+ $('#department\\.id').val()
							});

			$("#catId").bootstrapTable({
				pagingType : "bootstrapPager",
				pagerSettings : {
					textboxWidth : 70,
					firstIcon : "",
					previousIcon : "glyphicon glyphicon-arrow-left",
					nextIcon : "glyphicon glyphicon-arrow-right",
					lastIcon : "",
					searchOnEnter : true,
					language : "Page ~ of ~ pages"
				}
			});
		}
	}
	
function popLabourChargeSearchListing(){
		
		if($('#deptartmentName').val()!='' || $('#categoryNm').val()!='' || $('#prodLobourName').val()!='' ){
			$("#prodLabTypeId")
			  .bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/masters/prodLabourType/custom/listing.html?deptartmentName="+$('#deptartmentName').val()+"&categoryNm="+$('#categoryNm').val()
																					+"&prodLobourName="+$('#prodLobourName').val(),
			});
		}else{
			popLabourChargeListing();
		}
		
		
	}
	
</script>

<!-- <link rel="stylesheet" href="/css/jquery/jquery-ui.min.css">

<script src="/js/jquery/jquery-ui.min.js"></script> -->