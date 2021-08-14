<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>



<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Lookup Master</span>
	</div>

	<form:form commandName="lookUpMast" id="lookUpMastFormId"
		action="/jewels/manufacturing/masters/lookUpMast/add.html"
		cssClass="form-horizontal lookUpMastForm">

		<c:if test="${param.success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Save Successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

	  <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="masterTypeLabelId" >Master Type <span style="color: red;">*</span> </label>
			 <div class="col-sm-8">
				<form:select path="name" class="form-control">
						<form:option value="" label="--- Select Master Type ---" />
						<form:options items="${masterTypeMap}" />
                  	</form:select>
			</div>
		</div>
		
		
			<div class="form-group">
                  <label for="fieldValue" class="col-sm-2 control-label">Field Value</label>

                   <div class="col-sm-8">
                    <form:input path="fieldValue" cssClass="form-control"/>
				    <form:errors path="fieldValue" />
                  </div>
                </div>         
                
                <div class="form-group">
                  <label for="code" class="col-sm-2 control-label">Code</label>

                   <div class="col-sm-8">
                    <form:input path="code" cssClass="form-control"/>
				    <form:errors path="code" />
                  </div>
                </div> 
		
		
			<c:if test="${canAdd && canDelete}">
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label"></label>
				<div class="col-sm-8">
					<label> <form:checkbox path="deactive" /> <b>De-active</b></label>
				</div>
			</div>
		</c:if>
		
	<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/lookUpMast.html">Lookup Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(
		function(e) {
			$(".lookUpMastForm").validate(
				{
					
				rules : {
					name : {
						required : true,
					},
					fieldValue : {
						required : true,
					},
					code : {
						required : true,
					},
				},
				highlight : function(element) {
					$(element).closest('.form-group').removeClass(
							'has-success').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass(
							'has-error').addClass('has-success');
				},
				messages : {
					
				},

			});

			
		});
	
	
	
	$(document)
	.on
		('submit',
		'form#lookUpMastFormId',
		function(e){
			
			var postData = $(this).serializeArray();
			var formURL	 = $(this).attr("action");
			
			
			$.ajax({
				url  : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR){

					
					if(data === "-1"){
						displayMsg(this, null, 'Duplicate Entry Found');	
					}else if(data === "-2"){
						displayMsg(this, null, 'Duplicate Code Found');	
					}else if(data.indexOf("add") != -1){
						window.location.href = data;
					}else{
						window.location.href = data;
						
					}
					
				},
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
				
			})
			
			
			e.preventDefault();
			
		});
	
	
	
</script>

<script src="/jewels/js/common/common.js"></script>

















<%-- 

 <section class="content">
  
 <br>
 
  <div class="lookupMainDiv">
  
  	<div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Lookup</h3>
            </div>
            
 	
 		     <form:form commandName="lookup" id="masterFromId" 
     			action="/inventory/masters/lookup/add.html" method="POST" 
     			class="form-horizontal masterForm">
		
				<div id="msgDiv" style="display: none">
				 	<div id="msgId" class="alert alert-success"></div>
				 </div>
		
              <div class="box-body">
              
                <div class="form-group">
                  <label for="name" class="col-sm-2 control-label">Master Type</label>

                  <div class="col-sm-4">
				  	<form:select path="name" class="form-control" style="width: 100%;">
						<form:option value="" label="--- Select Master Type ---" />
						<form:options items="${masterTypeMap}" />
                  	</form:select>
                  </div>
                </div>
                
				
				<div class="form-group">
                  <label for="fieldValue" class="col-sm-2 control-label">Field Value</label>

                  <div class="col-sm-4">
                    <form:input path="fieldValue" cssClass="form-control"/>
				    <form:errors path="fieldValue" />
                  </div>
                </div>                
                
				
				<div class="form-group" id="${mastType}DeactiveDivId" style="display: none">
                  <div class="col-sm-offset-2 col-sm-4">
                    <div class="checkbox">
                      <label>
                        <form:checkbox path="deactive" value="0" /> Deactive
                      </label>
                    </div>
                  </div>
                </div>
              
              
               
                
            <div class="form-group">
				<div class="col-sm-offset-0 col-sm-5" id="${mastType}ButtonsDivId">
					<form:input type="hidden" path="id" id="masterPkId" />
				</div>
		 	</div>
             
          </div>
              <!-- /.box-body -->
          
       </form:form>
            
 	
 	
 	           
    </div>
  	
 
  </div>
  
 </section>
 
 
 <script type="text/javascript">
 
 $(document).ready(function(e) {
	  
	  $(".masterForm").toggleClass("masterForm ${mastformData}");
	  
	  $(".${mastformData}")
		  .validate(
				{
					rules : {
						name : {
							required : true,
						},
						fieldValue : {
							required : true,
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
							remote : "Category already exists"
						}
					}
				});

	  	renameMasterIds('${mastType}');
	  	popMasterSettingAddPageButton('${mastType}','${mastformData}','${roleRightMap}');
		
	});

 
 
 </script>
 
<script src="<spring:url value='/js/common/master-save.js' />"></script>

<script src="<spring:url value='/js/module/tran-setting.js' />"></script>

<script src="<spring:url value='/js/common/master-button-page.js' />"></script>
  --%>