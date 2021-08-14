<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
 <c:set var="option" value="Order Quality" />

<div id="OQIdTable">
	<div class="container-fluid">
		<div class="panel panel-primary col-sm-6">
			<div class="panel-body">
				<div class="col-sm-12">

					<div class="row">
						<div class="col-xs-12">
							<div align="center">
								<label for="inputLabel3">Order Quality Parameter </label>
							</div>
							<div id="toolbarOQ">
								<a class="btn btn-info" style="font-size: 15px" type="button"
									href="javascript:goToOQuality();"><span
									class="glyphicon glyphicon-plus"></span>&nbsp;Add Order Quality</a>
							</div>
							<div>
								<table id="orderQId" data-toggle="table"
									data-toolbar="#toolbarOQ" data-pagination="false"
									data-side-pagination="server" data-click-to-select="true"
									data-select-item-name="radioName"
									data-height="350">
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-radio="true"></th>
											<th data-field="stoneType" data-align="left" data-sortable="true">StoneType</th>
											<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
											<th data-field="quality" data-sortable="true">Quality</th>
											<th data-field="action1" data-align="center">Edit</th>
											<th data-field="action2" data-align="center">Delete</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>


					<div id="rowOQId" style="display: none;">



						<div class="form-group">
							<form:form commandName="orderQuality" id="orderQuality"
								action="/jewels/manufacturing/masters/orderQuality/add.html"
								cssClass="form-horizontal designComponentForm">

								<table class="line-items editable table table-bordered">
									<thead class="panel-heading">
										<tr class="panel-heading">
											<th>StoneType</th>
											<th>Shape</th>
											<th>Quality</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><form:select path="stoneType.id"
													class="form-control">
													<form:option value="" label="- Select Stone Type -" />
													<form:options items="${stoneTypeMap}" />
												</form:select></td>
											<td><form:select path="shape.id" id="oQShId"
													class="form-control"
													onChange="javascript:popOrderQuality();">
													<form:option value="" label="- Select Shape -" />
													<form:options items="${shapeMap}" />
												</form:select></td>
											<td>
												<div id="sQltyId">
													<form:select path="quality.id" class="form-control">
														<form:option value="" label="- Select Quality -" />
														<form:options items="${qualityMap}" />
													</form:select>
												</div>
											</td>
										</tr>
										<tr>
											<td><input type="submit" id="saveId" value="Save"
												class="btn btn-primary" />
											<td><input type="submit" id="saveAllId" value="Save & Update All Style"
												class="btn btn-primary" />	
												 <form:input type="hidden"
													path="id" id="OQltyId" /> <input type="hidden"
												id="pOQInvNo" name="pOQInvNo" />
												
												<input type="hidden"
												id="updateFlg" name="updateFlg" />
										</tr>
									</tbody>
								</table>
							</form:form>
						</div>

					</div>
				</div>
			</div>
		</div>
		
			
		
		<div class="panel panel-primary col-sm-6">
		
			<div class="row">
			
				
			
					<div class="panel-body">
		<div align="center">
			<label for="inputLabel3">Change Order Stone Type </label>
								
							
				<form:form commandName="orderStnDt" id="orderStnTypeChngId"
					action="/jewels/manufacturing/masters/orderStnDt/changeStoneType.html"
					cssClass="form-horizontal orderStnTypeChngIdForm">	
					<div class="row">
					<div class="form-group">
					<label for="inputLabel3" class="col-sm-3">Select Stone Type</label>
					<div class="col-sm-6">
									<form:select path="stoneType.id" id="stnTypeId" class="form-control"  onChange="javascript:popStntype(this);">
										<form:option value="" label="- Select Stone Type -" />
										<form:options items="${stoneTypeMap}" />
									</form:select>
						</div>
					</div>	
					
					<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-1">
					<input type="submit" value="Change Stone Type for All Items" class="btn btn-primary" id="chngStnTypeId" />
					<input type="hidden"  id="vStoneTypeId" name="vStoneTypeId"/>
					<input type="hidden"  id="vInvNo" name="vInvNo"/>
					</div>
					</div>
					
					</div>
					
					
					</form:form>			
								
				</div>
				</div>
			
			</div>
			
			<div class="row">
				<div class="col-xs-12"></div>
			</div>
			
			
				<div class="row">
				<div class="col-xs-12"><hr style="height:1px;border:none;color:#333;background-color:#333;"></div>
			</div>
			
			<div class="row">
				<div class="col-xs-12"></div>
			</div>
			
			
			<div class="row">
					<div class="panel-body">
		<div align="center">
			<label for="inputLabel3">Change Pointer Stone Type </label>
								
							
				<form:form commandName="orderStnDt" id="orderPointerStnTypeId"
					action="/jewels/manufacturing/masters/orderStnDt/changePointerStoneType.html"
					cssClass="form-horizontal orderPointerStnTypeIdForm">	
					<div class="row">
					<div class="form-group">
					<label for="inputLabel3" class="col-sm-3">Select Stone Type</label>
					<div class="col-sm-6">
									<form:select path="stoneType.id" id="pointerStnTypeId" class="form-control"  onChange="javascript:popPointerStntype(this);">
										<form:option value="" label="- Select Stone Type -" />
										<form:options items="${stoneTypeMap}" />
									</form:select>
						</div>
					</div>	
				
					<div class="form-group">
					<div class="col-sm-1"></div>
					<div class="col-sm-1">
					<input type="submit" value="Change Stone Type for All Items" class="btn btn-primary" id="chngPointerStnTypeId" />
					<input type="hidden"  id="vPointerStoneTypeId" name="vPointerStoneTypeId"/>
					<input type="hidden"  id="vMtId" name="vMtId"/>
					</div>
					</div>
					
					</div>
					
					
					</form:form>			
								
				</div>
				</div>
			
			</div>
	
	
	
		</div>
		
		
		
	</div>


</div>

<script type="text/javascript">

	$(document).ready(function(e) {
		

		
		
		
		$('#oQuality').on('click', function() {
			popOrderQDetails();
		});

	});
	

	
	function popStntype(temp){
		$('#vStoneTypeId').val(temp.value);
		
	}


	function popPointerStntype(temp){
		$('#vPointerStoneTypeId').val(temp.value);
		}
	

	var whichBtn;
	$('#orderQuality input[type="submit"]').click(function(e) {
		whichBtn = $(this).attr("id");
		
		
	});
	

	$(document).on('submit', 'form#orderQuality', function(e) {
		if (whichBtn == 'saveAllId') {
		$('#updateFlg').val('true');
		}else{
			$('#updateFlg').val('false');
		}
		
		$('#pOQInvNo').val($('#invNo').val());

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
		
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				$.unblockUI();
				$('#OQltyId').val('');
				popOrderQDetails();
				popOrderDetails('edit');
				$('form#orderQuality input[type="text"],texatrea').val('');
				$('form#orderQuality select').val('');
				$('#OQltyId').val('');
				$("#rowOQId").css('display', 'none');
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});
	
	
</script>

