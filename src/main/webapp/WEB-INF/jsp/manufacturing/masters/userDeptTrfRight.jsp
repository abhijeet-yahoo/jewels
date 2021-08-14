<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<div align="center">
<div class="panel panel-primary" style="width:80% ; ">
<div class="panel-body">

		<div align="center">
			<span style="font-size: 18px;">Transfer Right Master</span>
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
				<form:form commandName="userDeptTrfRight" >
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="col-xs-1">User Name :</th>
								<td class="col-xs-2"><form:select path="user.id" class="form-control" onChange ="javascript:refreshDepartment();">
										<form:option value="" label=" Select User-Name " />
										<form:options items="${userMap}" />
									</form:select></td>
								<th class="col-xs-1">Department :</th>
								<td class="col-xs-2"><form:select path="department.id" id="fromDeptId" class="form-control" onChange =" javascript:gettingList();">
										<form:option value="" label=" Select department " />
										<form:options items="${departmentMap}" />
									</form:select></td>
								<td class="col-xs-1">
									<!-- <input type="submit" value="Display" class="btn btn-primary" />  -->
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
						<table id="transferTable" data-toggle="table" data-toolbar="#toolbarDt"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="280">
							<thead>
								<tr>
									<th data-field="userName" data-sortable="true">UserName</th>
									<th data-field="fromDept" data-align="left" data-sortable="true">Department From</th>
									<th data-field="toDept" data-align="left" data-sortable="true">To Department</th>
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
				<form:form commandName="userDeptTrfRight" id="recordSave"
					action="/jewels/manufacturing/masters/userDeptTrfRight/add.html"
					cssClass="form-horizontal transferForm">

					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="col-xs-1">Department:</th>
								<td class="col-xs-2"><form:select path="toDeptId.id" id="toDept" class="form-control">
										<form:option value="" label=" Select department " />
										<form:options items="${departmentMap}" />
									</form:select></td>
								<td class="col-xs-5"> <input type="submit" value="Save" class="btn btn-primary" /> 
									<input type="hidden" name="id">
									<input type="hidden" name="vUserId" id="vUserId" /> 
									<input type="hidden" name="vfromDeptId" id="vfromDeptId" /> 
									<input type="hidden" name="vToDeptId" id="vToDeptId" /> 
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

function gettingList(){
	
	//alert("XXXXXXXXXXXX");
	
	$("#transferTable")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/userDeptTrfRight/listing.html?userId="
						+ $('#user\\.id').val()+"&deptId="+$('#fromDeptId').val()
			});
}

	 function refreshDepartment(){	 
		$('#fromDeptId').val('');
	
	}
	 
	
	function deleteUserDeptTransfer(id){
		
		$.ajax({
			url : "/jewels/manufacturing/masters/userDeptTrfRight/delete/" + id
					+ ".html",
			type : 'GET',
			success : function(data) {	
				
				if(data == 1){
					gettingList();
				}else{
			           alert("redirecting problem occoured while deleteing");		
					}
			}
		
		});
	}


$(document).on(
		'submit',
		'form#recordSave',
		function(e) {
			
			var vUser = $('#user\\.id').val();
			$('#vUserId').val(vUser);
		
			var vfromDept = $('#fromDeptId').val();
			$('#vfromDeptId').val(vfromDept);
			
			var vToDept = $('#toDept').val();
			$('#vToDeptId').val(vToDept);
									
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
							
			$.ajax({ 
				url : formURL,
				type : "POST",
				data : postData,
				
				 success : function(data, textStatus, jqXHR) {	
					
					// alert(data);
					var result = data;
					
					 
					  if(result == 1){
						
						  $("#transferTable")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/masters/userDeptTrfRight/listing.html?userId="
												+ $('#user\\.id').val()+"&deptId="+$('#fromDeptId').val()
									});
						 								 
						 }
					 	else{
						 	//InfoMsg(event, this);	
						 	alert("Cannot transfer to same department or existing department");
						 } 
				
				},
				error : function(jqXHR, textStatus,
						errorThrown) {
					
					//alert("please select atleast one record to transfer");
				} 
				
			});
			e.preventDefault(); //STOP default action
		});
	

	

</script>
