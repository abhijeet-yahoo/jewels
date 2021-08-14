<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>

<script src="/jewels/chosen/chosen.jquery.js"></script>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" id="tabdiv">
		<span style="font-size: 18px;margin-right:64em;">Stone Rate</span>
	</div>
	
	<form:form commandName="stoneRateMast" id="stoneRateEntryId"
	
		action="/jewels/manufacturing/masters/stoneRateMast/add.html"
		cssClass="form-horizontal stoneRateMastForm">
		
		<c:if test="${param.success eq true}">
			<div class="alert alert-success">Stone Rate added ${action}
				successfully!</div>
		</c:if>
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
       </div>
	   <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="partLabelId" >Party <span style="color: red;">*&nbsp;</span> :</label>
			<div class="col-sm-5">
				<form:select path="party.id" class="form-control" required="true">
					<form:option value="" label="--- Select Party---" />
					<form:options items="${partyMap}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Stone Type <span style="color: red;">*&nbsp;</span> :</label>
			<div class="col-sm-5">
				<form:select path="stoneType.id" class="form-control" required="true">
					<form:option value="" label="--- Select Stone Type ---" />
					<form:options items="${stoneTypeMap}" />
				</form:select>
			</div>
	   </div>
	
	    <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Shape <span style="color: red;">*&nbsp;</span> :</label>
			<div class="col-sm-5">
				<form:select path="shape.id" class="form-control" 
				onchange="javascript:popQuality(this.value);javascript:popSizeGrpp(this.value);"  required="true">
					<form:option value="" label="--- Select Shape ---" />
					<form:options items="${shapeMap}" />
				</form:select>
			</div>
		</div>
		
		  <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Quality <span style="color: red;">*&nbsp;</span> :</label>
			<div class="col-sm-5">
			  <div id="qualityId">
					<form:select path="quality.id" class="form-control"  required="true">
						<form:option value="" label="--- Select Quality ---" />
						<form:options items="${qualityMap}" />
					</form:select>
				</div>
			</div>
		</div>
		
		
		<div class="form-group">
			<label for="sizeGroup" class="col-sm-2 control-label">Size Group :</label>
			<div class="col-xs-5">
				<form:select path="sizeGroup.id" class="form-control" 
				onchange="javascript:popSieve(this.value);"  required="true">
					
					<form:option value="" label=" Select SizeGroup " />
					<form:options items="${sizeGroupMap}" />
				</form:select>
			</div>
		</div>
		
	 
	 
		<div class="form-group">
			<label for="sieve" class="col-sm-2 control-label">Sieve :</label>
			<div class="col-xs-5">
				<form:select path="sieve" class="form-control">
					<form:option value="" label=" Select Sieve " />
					<form:options items="${sieveMap}" />
				</form:select>
			</div>
		</div>
		
		
		
	   <%--  <div class="form-group">
			<label for="name" class="col-sm-2 control-label">From Weight <span style="color: red;">*</span> </label>
			<div class="col-sm-5">
				  <form:input path="fromWeight" cssClass="form-control" value="${fromWeightValue}" />
				  <form:errors path="fromWeight" />
			</div>
		</div>
		
	    <div class="form-group">
			<label for="name" class="col-sm-2 control-label">To Weight <span style="color: red;">*</span> </label>
			<div class="col-sm-5">
				<form:input path="toWeight" cssClass="form-control" value="${toWeightValue}"/>
				<form:errors path="toWeight" />
			</div>
		</div> --%>
		
		 <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Per Carat Rate :</label>
			<div class="col-sm-5">
				  <form:input path="stoneRate" cssClass="form-control" />
				  <form:errors path="stoneRate" />
			</div>
		</div>
		
	    <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Per Stone Rate :</label>
			<div class="col-sm-5">
				<form:input path="perPcRate" cssClass="form-control" />
				<form:errors path="perPcRate" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-4">
			
			<c:if test="${canAdd || canEdit}">
				<input type="submit" value="Save" class="btn btn-primary" />
			</c:if>
				 <a id="listingId"
					class="btn btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/masters/stoneRateMast.html">Stone Rate Listing</a>
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
		//	$('select').chosen();
			
				$('#party\\.id').chosen();
				$('#stoneType\\.id').chosen();
				$('#shape\\.id').chosen();
				$('#quality\\.id').chosen();
				$('#sizeGroup\\.id').chosen();
				$('#sieve').chosen();
				
			$.validator.setDefaults({ ignore: ":hidden:not(select)" });
			// validation of chosen on change
			if ($("select.form-control").length > 0) {
			    $("select.form-control").each(function() {
			        if ($(this).attr('required') !== undefined) {
			            $(this).on("change", function() {
			                $(this).valid();
			            });
			        }
			    });
			}
			
			$(".stoneRateMastForm")
					.validate(
							{
								rules : {
								/* 'party.id' : {
										required : true,
									},
								'stoneType.id' : {
										required : true,
									},
								'shape.id' : {
										required : true,
									},
								'quality.id' : {
										required : true,
									},
									
									'sizeGroup.id' : {
										required : true,
									}, */	
																		
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
									fromWeight : {
										greaterThan : "This field is required"
									},
									toWeight : {
										greaterThan : "This field is required"
									}
								},
								
							});
			
			if (window.location.href.indexOf('view') != -1) {
				checkViewMode();
			}
			
			
		}
		
			);
		

 $(document)
	.on(
		'submit',
		'form#stoneRateEntryId',
		function(e) {
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");

			$
					.ajax({
						url : formURL,
						type : "POST",
						data : postData,
						success : function(data, textStatus, jqXHR) {
							if(data === '-2'){
								displayMsg(this, null, 'Dublicate entry found, check the entry');
							}else if(data === '-3'){
								displayMsg(this, null, 'At a time one entry either PerCaratRate or PerPcsRate');
							}else if (data === '-4') {
								displayMsg(this, null, 'ToWeight Always Greater Than FromWeight');
							}
							else{
								window.location.href = data;	
							}
							
						},

						error : function(jqXHR, textStatus,
								errorThrown) {
						}
					});
			e.preventDefault(); 
		}
	
	); 
 
 
 function popSizeGrpp(vSel) {
		$
				.ajax({
					
					url : '/jewels/manufacturing/masters/stoneRateMast/sizeGroup/list.html?shpId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						
					//	$("#sizeGroupId").html(data);
						$("#sizeGroup\\.id").html(data).trigger('chosen:updated');
						
					}
				});
	}
 
 
 

 function popSieve(vSel) {
		
	 	$
				.ajax({
					
					url : '<spring:url value='/manufacturing/masters/stoneRateMast/sieve/list.html' />?sizeGrpId='
							+ vSel,					
				type : 'GET',
					success : function(data) {
					//	$("#sieveId").html(data);
					
						$('#sieve').html(data).trigger('chosen:updated');
					
					}
					
					
				});
			 
						
	}

	
	

 
 
 
 
 
 
	
 
 
 /* 	function popCancel(){
 		
 		window.location.href = "/jewels/manufacturing/masters/stoneRateMast.html?id="+$('#stoneType\\.id').val();
 		
 	} */
	



</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">
<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>
<script src="/jewels/js/common/design.js"></script>
<script src="/jewels/js/common/order.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="/jewels/js/common/common.js"></script>
		
<!-- <script src="/jewels/js/common/viewModeOnly.js"></script>	 -->
		
		
		