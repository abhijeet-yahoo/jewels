<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div align="center">
	<div class="panel " style="width: 80%;">
		<div class="panel-body">


			<c:set var="option" value="Color" />

			<c:if test="${success eq true}">
				<div class="alert alert-success">Role ${action} successfully!</div>
			</c:if>






			<div class="row" align="center">
				<div class="form-group">
					<div>

						<div class="col-xs-12">

							<div class="col-xs-1">
								<a class="btn btn-info" style="font-size: 15px" type="button"
									href="/jewels/admin/roleMast/add.html"><span
									class="glyphicon glyphicon-plus"></span>&nbsp;Add RoleMast</a>
							</div>

							<div class="col-xs-7"></div>
							<div class="col-xs-3">
								<input type="search" id="searchRole" class="search form-control"
									placeholder="Search" />



							</div>

						</div>

						<div class="col-xs-12">

							<div>
								<table id="roleIdTbl" data-toggle="table"
									data-url="/jewels/admin/roleMast/listing.html"
									data-toolbar="#toolbar" data-pagination="false"
									data-side-pagination="server"
									data-page-list="[5, 10, 20, 50, 100, 200]" data-search="false"
									data-striped="true" data-height="520">
									<thead>
										<tr class="btn-primary">
											<th data-field="name" data-align="left" data-sortable="true">Name</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>

				</div>
			</div>




		</div>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>



<script type="text/javascript">
	$(document).ready(function(e) {

		$("#searchRole").on("keyup", function() {
			var value = $(this).val();

			$("#roleIdTbl tr").each(function(index) {

				if (index != 0) {

					$row = $(this);
					var id = $row.find('td:eq(0)').text();
					if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
						$(this).hide();
					} else {
						$(this).show();
					}
				}
			});
		});

	});
</script>



