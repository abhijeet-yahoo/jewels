<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">ClientWastage Master</span>
	</div>

	<form:form commandName="clientWastage" id="clientWastageFormId"
		action="/jewels/manufacturing/masters/clientWastage/add.html"
		cssClass="form-horizontal clientWastageForm">

		<c:if test="${param.success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Client Wastage Save
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

	  <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="partLabelId" >Party <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="party.id" class="form-control">
					<form:option value="" label="--- Select Party---" />
					<form:options items="${partyMap}" />
				</form:select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="designGroupLabelId" >DesignGroup </label>
			<div class="col-sm-8">
				<form:select path="designGroup.id" class="form-control">
					<form:option value="" label="--- Select DesignGroup---" />
					<form:options items="${designGroupMap}" />
				</form:select>
			</div>
		</div>
		
		  <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="categoryId" >Category </label>
			<div class="col-sm-8">
				<form:select path="category.id" class="form-control"
				onChange="javascript:popSCat(this); ">
					<form:option value="" label="--- Select Catgory---" />
					<form:options items="${categoryMap}" />
				</form:select>
			</div>
		</div>
		
		
			 <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="subCategoryLabelId" >SubCategory  </label>
			<div class="col-sm-8"  >
				<form:select path="subCategory.id" class="form-control">
					<form:option value="" label="--- Select SubCategory---" />
					<form:options items="${subCategoryMap}" />
				</form:select>
			</div>
		</div>
		
		 <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="metalLabelId" >Metal <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="metal.id" class="form-control">
					<form:option value="" label="--- Select Metal---" />
					<form:options items="${metalMap}" />
				</form:select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="wastage" class="col-sm-2 control-label">Wastage %</label>
			<div class="col-sm-8">
				<form:input path="wastagePerc" cssClass="form-control" tyep="number" autocomplete="off"/>
				<form:errors path="wastagePerc" />
			</div>
		</div>
	

		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/clientWastage.html">ClientWastage Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(
		function(e) {
			$(".clientWastageForm").validate(
				{
					
				rules : {
					'metal.id' : {
						required : true,
					},
					'party.id' : {
						required : true,
					},
					
					wastagePerc : {
						required : true,
						greaterThan : "0,0",
					}
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
					wastagePerc : {
						greaterThan : "This field is required"
					}
				},

			});

			
		});
	
	
	
	$(document)
	.on
		('submit',
		'form#clientWastageFormId',
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



	function popSCat(vSel) {
		$
				.ajax({
					url : '/jewels/manufacturing/masters/subCategory/list.html?catId='
							+ vSel.value,
					type : 'GET',
					success : function(data) {
						$("#subCategory\\.id").html(data);
					}
				});
	}

	
	
	
</script>

<script src="/jewels/js/common/common.js"></script>