<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Process Master</span>
	</div>

	<form:form commandName="processMast" id="processMastFormId"
		action="/jewels/manufacturing/masters/processMast/add.html"
		cssClass="form-horizontal processMastForm">

		<c:if test="${param.success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">process save
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

	  <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="processLabelId" >Process <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="processLookUp.id" class="form-control">
					<form:option value="" label="--- Select Process---" />
					<form:options items="${processLookUpMap}" />
				</form:select>
			</div>
		</div>
		
		 <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="deptLabelId" >Department <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="department.id" class="form-control">
					<form:option value="" label="--- Select Dept---" />
					<form:options items="${departmentMap}" />
				</form:select>
			</div>
		</div>
		
		
		 <div class="form-group">
			<label for="seqNo" class="col-sm-2 control-label" >Seq No <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
			<form:input path="seqNo" cssClass="form-control"/>
						<form:errors path="seqNo" />
			</div>
		</div>
		
		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/processMast.html">Process Mast Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(
		function(e) {
			$(".processMastForm").validate(
				{
					
				rules : {
					'processLookUp.id' : {
						required : true,
					},
					'department.id' : {
						required : true,
					},
					'seqNo':{
						required :true,
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
		'form#processMastFormId',
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