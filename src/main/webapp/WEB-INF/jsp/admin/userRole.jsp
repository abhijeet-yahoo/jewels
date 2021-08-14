<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<%@ include file="/WEB-INF/jsp/common/modal.jsp" %>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
		<span style="font-size: 18px;">User Role</span>
	</div>

	<div class="panel-body">
		<div class="form-group">
			<form:form commandName="userRole" id="userRoleSubmit"
				action= "/jewels/admin/userRole/add.html"
				cssClass="form-horizontal userRoleForm">


				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="form-group">
					<label class="col-sm-1 text-right"></label> 
					
					<label class="col-sm-1 text-right">User:</label>
					<div class="col-sm-3">
						<form:select path="user.id" class="form-control" >
							<form:option value="" label=" Select User " />
							<form:options items="${userMap}" />
						</form:select>
					</div>
					
					<label class="col-sm-1 text-right">Role:</label>
					<div class="col-sm-3">
						<form:select path="roleMast.id" class="form-control" >
							<form:option value="" label=" Select Role " />
							<form:options items="${roleMastMap}" />
						</form:select>
					</div>
					
					
				</div>


				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-10">
						<input type="submit" value="Save" class="btn btn-success" style="width: 3cm" /> 
						<form:input type="hidden" path="id" />
					</div>
				</div>
				
				
				
				
			
				</form:form>
			</div>
		</div>
		
		
		
 </div>
 
 
 <script type="text/javascript">
 
 
 
 
 $(document).on('submit', 'form#userRoleSubmit', function(e) {


		//$('#tranSubmitBtn').attr('disabled', 'disabled');

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		
		
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,

			success : function(data, textStatus, jqXHR) {
			
				if(data === '-1'){
					displayInfoMsg(event, this,'Record Added Sucessfully!');
					$("#user\\.id").val('');
					$("#roleMast\\.id").val('');
				}
				
			},
			error : function(jqXHR, textStatus, errorThrown) {

			}

		});

		e.preventDefault(); //STOP default action
	});

	
 
 
 
 
 
 </script>
 
 
 
 
 