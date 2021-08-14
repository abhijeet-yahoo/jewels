 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<section class="content">
  <div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Ledger Group</span>
	</div>
 <br>
  <div class="ledgerGroupMainDiv" id="ledgerGroupledgerGroupDivId">

	<form:form commandName="ledgerGroup" id="ledgerGroupId"
		action="/jewels/manufacturing/masters/ledgerGroup/add.html"
		cssClass="form-horizontal ledgerGroupForm">
		
		<c:if test="${success eq true}">
			<div class="alert alert-success">LedgerGroup ${action} successfully!</div>
		</c:if>
		
		
		      <div class="box-body">
                <div class="form-group">
                  <label for="inputEmail3" class="col-sm-2 control-label">Name : </label>

                  <div class="col-sm-4">
                    <form:input path="name" cssClass="form-control" onblur="javascript:upperCase(this)" autocomplete="off"/>
				    <form:errors path="name" />
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label">Main Group :</label>

                 <div id="mainLedgerGroupId" class="col-sm-4">
				 	<form:select path="mainGroup" class="form-control select2" onblur="javascript:upperCase(this)" >
						<form:option value="" label="--Select Main Group"/>
						<form:options items="${mainGroup}" />
                	</form:select>
                </div>	
                <div class="col-sm-1">
                	<a href="#" onclick="javascript:refreshMainGroup()" class="glyphicon glyphicon-refresh"></a>
                </div>	
                  
                </div>
               
               <div class="form-group">
                  <label for="inputPassword3" class="col-sm-2 control-label"> Code :</label>

                  <div class="col-sm-4">
                      <form:input path="ledgerGroupCode" cssClass="form-control" onblur="javascript:upperCase(this)" autocomplete="off"/>
				      <form:errors path="ledgerGroupCode" />
                  </div>
                </div>
                
                <div class="form-group">
                
                
                  
                  		
                  		
           	<c:if test="${canAdd && canDelete}">
		
				
		<div class="col-sm-offset-2 col-sm-4">
                    <div class="checkbox">
                      <label>
                        <form:checkbox path="isGroup" value="0" /> Is Group
                      </label>
                    </div>
                  </div>
			
			
			
			
			
		</c:if>
		
                  
                </div>
              
          </div>
              


		
		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="<spring:url value='/manufacturing/masters/ledgerGroup.html' />">Ledger Group Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>






	</form:form>
</div>
</div>
</section>

<script type="text/javascript">
	 
$(document).ready(
		function(e) {
			$(".ledgerGroupForm").validate(
				{
					
				rules : {
					name : {
						required : true,
	
						remote : {
							url : "<spring:url value='/manufacturing/masters/ledgerGroupAvailable.html' />",
							type : "get",
							data : {
								id : function() {
									return $("#id")
											.val();
								}
							}
						}
					},
					
					code : {
						required : true,
						
						remote : {
							url : "<spring:url value='/manufacturing/masters/ledgerGroupAvailable.html' />",
							type : "get",
							data : {
								id : function() {
									return $("#id")
											.val();
								}
							}
						}
					},
					
				},
							highlight : function(element) {
								$(element).closest(
										'.form-group')
										.removeClass(
												'has-success')
										.addClass('has-error');
							},
							unhighlight : function(element) {
								$(element)
										.closest('.form-group')
										.removeClass(
												'has-error')
										.addClass('has-success');
							},
							messages : { 
								name : {
									remote : "Ledger Group Name already exists"
								},
								code : {
									remote : "Ledger Group Code already exists"
								}
							}
						});


							
				
				});

 function refreshMainGroup(){
			$
			.ajax({
				url : "<spring:url value='/masters/mainLedgerGroup/list.html' />",
				type : 'GET',
				success : function(data) {

					
					if(data === '-1'){
						displayMsg(this,null,"Error : Company not found , contact support")
					}else{
						$("#mainLedgerGroupId").html(data);
						$('.select2').select2();	
					}
					
				}
			});
		 
		 
	 }
	 
	
	 
	  
</script>
