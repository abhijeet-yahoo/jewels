<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<div align="center">
<div class="panel panel-primary" style="width:80% ; ">
<div class="panel-body">

		<div align="center">
			<span style="font-size: 18px;">Location Right Master</span>
		</div>

		<div class="col-xs-12">
			<hr style="width: 100%; color: red; height: 2px; background-color: red;" />
		</div>

	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>


	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<form:form commandName="locationRights" >
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="col-xs-1">User Name :</th>
								<td class="col-xs-2"><form:select path="user.id" class="form-control" onChange =" javascript:locationightsList();">
										<form:option value="" label=" Select User-Name " />
										<form:options items="${userMap}" />
									</form:select></td>
								<th class="col-xs-1">Location :</th>
								<td class="col-xs-2"><form:select path="department.id" class="form-control" >
										<form:option value="" label=" Select department " />
										<form:options items="${departmentMap}" />
									</form:select></td>
									<th class="col-xs-1">Main Location</th>
									
								<td class="col-xs-1">
									<form:checkbox path="defaultFlg" onchange="javascript:popDefaultFlg();" cssClass="form-control" />
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
	

	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 label label-default text-right"
					style="font-size: 18px; ">Details
				</span>
			</div>
		</div>
	</div>


	<div class="form-group" id="dsPId">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
						<table id="locationRightsTable" data-toggle="table" data-toolbar="#toolbarDt"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="280">
							<thead>
								<tr>
									<th data-field="userName" data-sortable="true">UserName</th>
									<th data-field="toDept" data-align="left" data-sortable="true">To Department</th>
									<th data-field="defaultFlg" data-sortable="true">Default</th>
									<th data-field="deactive" data-sortable="true">Status</th>
									<th data-field="action2" data-align="center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<form:form commandName="locationRights" id="locationRightsForm"
					action="/jewels/manufacturing/masters/locationRights/add.html"
					cssClass="form-horizontal transferForm">

					<table class="table table-bordered">
						<tbody>
							<tr>
								<td class="col-xs-5"> <input type="submit" value="Save" class="btn btn-primary" /> 
									<input type="hidden" name="id">
									<input type="hidden" name="vUserId" id="vUserId" /> 
									<input type="hidden" name="vToDeptId" id="vToDeptId" />
									<input type="hidden" name="vDefaultFlg" id="vDefaultFlg" /> 
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>




</div> <!-- ending the body -->
</div> <!-- ending the panel -->
</div>

<script type="text/javascript">

function locationightsList(){
	
	$("#locationRightsTable")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/locationRights/listing.html?userId="
						+ $('#user\\.id').val()
			});
}

	
	function deleteUserLocationRights(id){
		
		$.ajax({
			url : "/jewels/manufacturing/masters/locationRights/delete/" + id
					+ ".html",
			type : 'GET',
			success : function(data) {	
				if(data == 1){
					locationightsList();
				}else{
			           alert("redirecting problem occoured while deleteing");		
					}
			}
		
		});
	}


	function popDefaultFlg(){

		$('#vDefaultFlg').val($('#defaultFlg1').prop('checked')); 
		}


$(document).on(
		'submit',
		'form#locationRightsForm',
		function(e) {
			
			var vUser = $('#user\\.id').val();
			$('#vUserId').val(vUser);
				
			var vToDept = $('#department\\.id').val();
			$('#vToDeptId').val(vToDept);

			
			$('#vDefaultFlg').val($('#defaultFlg1').prop('checked')); 
			
									
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
							
			$.ajax({ 
				url : formURL,
				type : "POST",
				data : postData,
				
				 success : function(data, textStatus, jqXHR) {	
					  if(data === "1"){
						  locationightsList();
						 								 
						 }
					 	else if(data === "-1"){
						 	displayMsg(this,null,"Cannot Give Rights For existing Location");
						 }else{
							 displayMsg(this,null,"Cannot Set Multiple Location");
							 } 
				
				},
				error : function(jqXHR, textStatus,
						errorThrown) {
					
					
				} 
				
			});
			e.preventDefault(); //STOP default action
		});
	

	

</script>
