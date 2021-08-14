<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Stone Chart Master</span>
	</div>

	<form:form commandName="stoneChart" id="stnChartId"
		action="/jewels/manufacturing/masters/stoneChart/add.html"
		cssClass="form-horizontal stoneChartForm">

		<c:if test="${param.success eq true}">
				<div class="alert alert-success">Stone Chart added <%-- ${action} --%>successfully!</div>
			</c:if>

		

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="shape" class="col-sm-2 control-label">Shape :</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="shape.id" class="form-control"
					onblur="javascript:popSizeGrp(this);">
					<form:option value="" label=" Select Shape " />
					<form:options items="${shapeList}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="size" class="col-sm-2 control-label">Size :</label>
			<div class="col-sm-3">
				<form:input path="size" cssClass="form-control" />
				<form:errors path="size" />
			</div>
		</div>

		<div class="form-group">
			<label for="sieve" class="col-sm-2 control-label">Sieve :</label>
			<div class="col-sm-3">
				<form:input path="sieve" cssClass="form-control" />
				<form:errors path="sieve" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="sizeGroup" class="col-sm-2 control-label">Size
				Group :</label>
			<div class="col-xs-3   selectContainer" id="sizeGrpId">
				<form:select path="sizeGroup.id" class="form-control">
					<form:option value="" label=" Select SizeGroup " />
					<form:options items="${sizeGroupMap}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="perStoneWt" class="col-sm-2 control-label">PerStone
				Wt :</label>
			<div class="col-sm-3">
				<form:input path="perStoneWt" cssClass="form-control" />
				<form:errors path="perStoneWt" />
			</div>
		</div>
		
		<div class="form-group">
		<label for="perStoneWt" class="col-sm-2 control-label">Pointer:</label>
				
				<div class="col-sm-8">
					 <form:checkbox path="pointerFlg" />
				</div>
			</div>



		<c:if test="${canEdit && canDelete}">
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
						href="/jewels/manufacturing/masters/stoneChart.html">Stone Chart
						Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
	</form:form>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						$(".stoneChartForm")
								.validate(
										{
											rules : {
												'shape.id' : {
													required : true
												},
												'sizeGroup.id' : {
													required : true
												},
												size : {
													required : true
												},
												sieve : {
													required : true
												}
												
												
											
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
													remote : "StoneChart already exists"
												}
											}
										});

						$("input:text:visible:first").focus();
					});

	function popSizeGrp(vSel) {
		$

				.ajax({
					url : '<spring:url value='/manufacturing/masters/sizeGrooup/list.html' />?shpId='
							+ vSel.value,
					type : 'GET',
					success : function(data) {
						$("#sizeGrpId").html(data);
					}
				});
	}
	
	
	
	
	
	
	
	$(document).on('submit', 'form#stnChartId', function(e) {
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
			
				if(data === '-1'){
					displayMsg(this, null, 'Shape and Size already exists!');
				}else if(data === '-2'){
					displayMsg(this, null, 'Shape and Sieve already exists!');
				}
				else{
					//displayInfoMsg(this, null, 'StoneChart Added Succesfully');
					window.location.href = data;
				}
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});
	
	
	
	
</script>
