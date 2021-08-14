<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" id="tabdiv">
		<span style="font-size: 18px;margin-right:63em;">Handling Rate</span>
	</div>

	<form:form commandName="handlingMaster" id="handlingSubmitId"
		action="/jewels/manufacturing/masters/handlingMaster/add.html"
		cssClass="form-horizontal handlingForm">
		
		<c:if test="${param.success eq true}">
			<div class="alert alert-success">Handling Rate added ${action}
				successfully!</div>
		</c:if>
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
   
  		<div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="partLabelId">Party <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="party.id" class="form-control">
					<form:option value="" label=" --- Select Party --- " />
					<form:options items="${partyMap}" />
				 </form:select>
			</div>
		</div>
		
			   <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Metal <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="metal.id" class="form-control">
					<form:option value="" label="--- Select Metal ---" />
					<form:options items="${metalMap}" />
				</form:select>
			</div>
	   </div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Stone Type <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="stoneType.id" class="form-control">
					<form:option value="" label=" --- Select Stone Type--- " />
					<form:options items="${stoneTypeMap}" />
				</form:select>
			</div>
		</div>
	
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Shape <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="shape.id" class="form-control">
					<form:option value="" label=" --- Select Shape --- " />
					<form:options items="${shapeMap}" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Per Carat </label>
			<div class="col-sm-5">
				<form:input path="perCarate" cssClass="form-control" />
				<form:errors path="perCarate" />
			</div>
		</div> 
   		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Percentage</label>
			<div class="col-sm-5">
				<form:input path="percentage" cssClass="form-control" />
				     <form:errors path="percentage" />
			</div>
		</div> 
   
    
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-4" style="padding-left: 35px;">
			  <c:if test="${canAdd || canEdit}">
				<input type="submit" value="Save" class="btn btn-primary" />
			 
			 </c:if>	
				 <a id="listingId"
					class="btn btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/masters/handlingMaster.html">Handling Rate Listing</a>
				<form:input type="hidden" path="id" />
				 
			</div>
		</div>
		
	</form:form>
	
	  <input type="hidden" id="viewOnly" name="viewOnly" value="${canView}">
	 <input type="hidden" id="editRight" name="editRight" value="${editRight}">
	
</div>

<script type="text/javascript">
$(document)
.ready(
		function(e) {
			$(".handlingForm")
					.validate(
							{
								rules : {
								'party.id' : {
										required : true,
									},
								'metal.id' : {
										required : true,
									},	
								'stoneType.id' : {
										required : true,
									},
								'shape.id' : {
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
									rate : {
										greaterThan : "This field is required"
									}
								},
								
							});
			
			if (window.location.href.indexOf('view') != -1) {
				checkViewMode();
				
			}
			
			
			
			
			
		});	




	
	$(document).on('submit','form#handlingSubmitId',function(e){
	
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
				displayMsg(this, null, 'Either PerCarat Or Percentage Is Required, Not Both');	
			}else if(data === "-3"){
				displayMsg(this, null, 'Only One is compulsary , Either PerCarat Or Percentage,');	
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

<script src="/jewels/js/common/viewModeOnly.js"></script>