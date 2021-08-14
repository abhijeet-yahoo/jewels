<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<c:if test="${success eq true}">
		<div class="col-xs-12">
			<div class="row">
				&nbsp;
				<div class="alert alert-success">Bag ${action} successfully!</div>
			</div>
		</div>
	</c:if>

	<div class="col-xs-10">
		<form:form commandName="diamondBagging"
			cssClass="form-horizontal diaBagForm">

			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Order
							No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Bag:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Client:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Style
							No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Bag
							Pcs:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Department:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-2 col-sm-2">
						<form:input path="orderDt.orderMt.invNo" cssClass="form-control" />
						<form:errors path="orderDt.orderMt.invNo" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="bagMt.name" cssClass="form-control" value=""
							onBlur="javascript:popDetails();" />
						<form:errors path="bagMt.name" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="orderDt.orderMt.party.name"
							cssClass="form-control" />
						<form:errors path="orderDt.orderMt.party.name" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="orderDt.design.styleNo" cssClass="form-control" />
						<form:errors path="orderDt.design.styleNo" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="bagMt.qty" cssClass="form-control" />
						<form:errors path="bagMt.qty" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<input type="text" name="vDepartment" id="vDepartment" class="form-control" disabled="true" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">KT:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Color:</label>
					</div>
					<div class="col-lg-4 col-sm-4">
						<p class="text-center" style="font-size: 13px;">
							<strong>-- Design Breakup --</strong>
						</p>
					</div>
					<div class="col-xs-4 col-sm-4">
						<p class="text-center" style="font-size: 13px;">
							<strong>-- Bag Breakup --</strong>
						</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-2 col-sm-2">
						<form:input path="orderDt.purity.name" cssClass="form-control" />
						<form:errors path="orderDt.purity.name" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="orderDt.color.name" cssClass="form-control" />
						<form:errors path="orderDt.color.name" />
					</div>
					<div class="col-sm-2 text-left">
						<input type="text" name="vTotalStones" id="vTotalStones"
							class="form-control" disabled="true" />
					</div>
					<div class="col-sm-2 text-left">
						<input type="text" name="vTotalCarats" id="vTotalCarats"
							class="form-control" disabled="true" />
					</div>
					<div class="col-sm-2 text-left">
						<input type="text" name="vTotalBagStones" id="vTotalBagStones"
							class="form-control" disabled="true" />
					</div>
					<div class="col-sm-2 text-left">
						<input type="text" name="vTotalBagCarats" id="vTotalBagCarats"
							class="form-control" disabled="true" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<form:input type="hidden" path="orderDt.id" />
			<div class="row">&nbsp;</div>
		</form:form>
	</div>



	<div id="odImgDivId" class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
		<div class="panel panel-primary" style=" height:170px;">
			<div class="panel-body">
				<div style="width: 150px; height: 50px" class="col-sm-4">
					<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png"
						data-lighter> <img id="ordImgId" class="img-responsive"
						src="/jewels/uploads/manufacturing/blank.png" />
					</a>
				</div>
			</div>
		</div>
	</div>


</div>

<!-- 
<div class="col-xs-12">
	<input type="button" id="deleteBtn" value="Bagging Return" onclick="javascript:doBaggingReturn()" class="btn btn-danger" >
</div>


 -->
 
 <div class="row">
	<div class="col-xs-8">
		<div id="marqueeIdDisp" style="display: none">
			<marquee style="color: #FF0000; font-size: large;" >Bagging done</marquee>
		</div>
	</div>
</div>

								



<div class="row">
	<div class="form-group">
		<div class="col-xs-12">
			<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Diamond
				Breakup</span>
		</div>
	</div>
</div>

<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="diaBagDtId" data-toggle="table"
						data-toolbar="#toolbarDt1" data-click-to-select="true"
						data-select-item-name="radioName" data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="200">
						<thead>
							<tr >
								<th data-field="state" data-radio="true"></th>
								<th data-field="stoneType">Stone Type</th>
								<th data-field="shape" data-align="left">Shape</th>
								<th data-field="subShape">Sub Shape</th>
								<th data-field="quality">Quality</th>
								<th data-field="mm" data-align="right">Size/MM</th>
								<th data-field="sieve" data-align="right">Sieve</th>
								<th data-field="sizeGroup">Size Group</th>
								<th data-field="stones" data-align="right">Stone</th>
								<th data-field="carat" data-align="right">Carat</th>
								<th data-field="bagStones" data-align="right">Bag Stone</th>
								<th data-field="bagCarat" data-align="right">Bag Carat</th>
								<th data-field="setting">Setting</th>
								<th data-field="setType">Set Type</th>
								<th data-field="centerStone" data-sortable="false">Center Stone</th>
								<th data-field="bagSrno">Bag Srno</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>



<div id = "bagCheckId" >

<div id="rowId">
	<div class="form-group">
		<div id="rowDiaBagStnId" style="display: none;">
			<table class="line-items editable table table-bordered">
				<tbody>
					<tr>
						<td class="col-lg-2 col-sm-2">
							<div class="form=control">
								<label>Bag Stone:&nbsp;</label><input type="text" name="fldBagStone" id="fldBagStone" maxlength="7" size="7" />
							</div>
						</td>
						<td class="col-lg-2 col-sm-2">
							<div class="form=control">
								<label>Bag Carat:&nbsp;</label><input type="text" name="fldBagCarat" id="fldBagCarat" onchange="javascript:setDecimal('fldBagCarat');" maxlength="7" size="7" />
							</div>
						</td>
						<td class="col-xs-2">
							<div id="qualityId">
								<form:select path="diamondBagging.quality.id"  class="form-control"  >
									<form:option value="" label="- Select Quality -" />
									<form:options items="${qualityMap}" />
								</form:select> 
							</div>
						</td>
						<td colspan="13">
							<span id="adjustmentId" style="color: red; font-size: 15px; display: none;">Adjustment done for this Bag.</span>
							<input type="button" value="Save Quality" id="qualityBtnId" class = "btn btn-info"  onclick="javascript:saveQuality();" />
							<input type="button" value="Import" id="importId" class="btn btn-primary" disabled="disabled" onClick="javascript:popStnRecDt();" />
							<input type="hidden" name="vPointer" id="vPointer" />
							<input type="hidden" name="vFifo" id="vFifo" value="0" />
							<input type="button" value="Filter Option" class="btn btn-primary" onClick="javascript:showFltrOpt();" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>

<div>
	<table class="table table-bordered" width="100%">
		<tbody id="filterOptId" style="display: none;">
			<tr>
				<td class="col-lg-2 col-sm-2 text-center control-label">
					<div class="radio">
						<label><input type="radio" name="choiceRd" id="choiceRd1" value="Group"><strong>Group</strong></label> &nbsp;&nbsp;&nbsp;
						<label><input type="radio" name="choiceRd" id="choiceRd2" value="Size" checked><strong>Size</strong></label> &nbsp;&nbsp;&nbsp;
						<label><input type="radio" name="choiceRd" id="choiceRd3" value="Quality" checked><strong>Quality</strong></label>
					</div>
				</td>
 				<td class="col-lg-1 col-sm-1  control-label text-right"><label class="text-right control-label">From Rate:</label></td>
				<td class="col-lg-1 col-sm-1"><input name="fromRate" id="fromRate" class="form-control" /></td>
				<td class="col-lg-1 col-sm-1  control-label text-right"><label class="text-right control-label">To Rate:</label></td>
				<td class="col-lg-1 col-sm-1"><input name="toRate" id="toRate" class="form-control" /></td>
				<td class="col-lg-2 col-sm-2">&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<table class="table-responsive" id="stnRecDtId" data-toggle="table" 
					data-toolbar="#toolbarDt" data-side-pagination="server"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-height="200" data-striped="true">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true" data-formatter="chkFormatter"></th>
							<th data-field="invNo" data-sortable="false" data-width="100">Invoice No</th>
							<th data-field="inwardType" data-sortable="true" >Type</th>
							<th data-field="stoneType" data-align="left">Stone Type</th>
							<th data-field="shape" data-align="left">Shape</th>
							<th data-field="quality" data-sortable="false">Quality</th>
							<th data-field="mm" data-align="right">Size/MM</th>
							<th data-field="sieve" data-align="right">Sieve</th>
							<th data-field="sizeGroup">Size Group</th>
							<th data-field="rate" data-align="right">Rate</th>
							<th data-field="balStone" data-align="right">Bal Stone</th>
							<th data-field="balCarat" data-align="right">Bal Carat</th>
							<th data-field="trfStone" data-align="right" >Trf Stone</th>
							<th data-field="trfCarat" data-align="right" bgcolor="red">Trf Carat</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="rowId">
	<!-- Adjustments -->
	<div id="trfAdjTblId" class="form-group" style="display: none;">
		<table class="line-items editable table table-bordered">
			<tbody>
				<tr>
					<td class="col-lg-2 col-sm-2">
						<div>
							<label>Adjusted Stone:&nbsp;</label><input type="text" name="fldTrfStone" id="fldTrfStone" maxlength="7" size="7" />
						</div>
					</td>
					<td class="col-lg-2 col-sm-2">
						<div>
							<label>Adjusted Carat:&nbsp;</label><input type="text" name="fldTrfCarat" id="fldTrfCarat" onchange="javascript:setDecimal('fldTrfCarat');" maxlength="7" size="7" />
						</div>
					</td>

					<td colspan="13">
						<input type="hidden" id="vBalStone" name="vBalStone" />
						<input type="hidden" id="vBalCarat" name="vBalCarat" />
					
					</td>
 				</tr>
			</tbody>
		</table>
	</div>


	<div id="adjTblId" class="form-group">
		<table class="line-items editable table table-bordered">
			<tbody>
				<tr>
					<td class="col-xs-1 text-center">
							<span>
								<a id="fifoBtnId" class="btn btn-info" style="font-size: 15px" type="button" href="javascript:void(0);" onclick="javascript:fifoAdj();" disabled="true">F I F O</a>
							</span>
							
							<input type = "hidden" name="fifoBalCarat" id="fifoBalCarat" />
					</td>

					<td colspan="13">
						<input type="button" value="Save Adjusted Data" class="btn btn-primary" id="shAdjDId"  onClick="javascript:saveAdjData();" />
						<input type="button" value="Verify & Transfer Adjusted Data" class="btn btn-primary" id="shAdjId" disabled="true" onClick="javascript:showAdjData();" />
						<input type="button" value="Transfer" class="btn btn-primary" id="transferBtnId" disabled="true" onClick="javascript:trfData(event);" />
					</td>

					<td class="col-lg-2 col-sm-2">
						<div>
							<label>Total Trf Stone:&nbsp;</label><input type="text" name="vTransStones" id="vTransStones" maxlength="7" size="7" disabled="disabled" />
						</div>
					</td>
					<td class="col-lg-2 col-sm-2">
						<div>
							<label>Total Trf Carat:&nbsp;</label><input type="text" name="vTransCarats" id="vTransCarats" maxlength="7" size="7" disabled="disabled" />
						</div>
					</td>

				</tr>
			</tbody>
		</table>
	</div>
</div>


</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<script type="text/javascript">
	
	$(document).ready(
	function(e) {
		$(".diaBagForm").validate(
				{
					rules : {
						styleNo : {
							required : true,
							minlength : 3
						},
						code : {
							required : true,
							minlength : 3
						}
					},
					highlight : function(element) {
						$(element).closest('.form-group')
								.removeClass('has-success')
								.addClass('has-error');
					},
					unhighlight : function(element) {
						$(element).closest('.form-group')
								.removeClass('has-error')
								.addClass('has-success');
					},
					messages : {
						name : {
							remote : ""
						}
					}
				});

		$("#fldBagStone").ForceNumeric();
		$("#fldBagCarat").ForceNumeric();

		$("#fldTrfStone").ForceNumeric();
		$("#fldTrfCarat").ForceNumeric();

		$("#bagMt\\.name").autocomplete(
		{
			source : "<spring:url value='/manufacturing/transactions/jobBag/list.html' />",
			minLength : 2
		});
		

		

	});

	
	function clearDBag() {
		var ids = $.map($("#diaBagDtId").bootstrapTable('getData'), function (row) {
		    return row.id;
		});
	
		$("#diaBagDtId").bootstrapTable('remove', {
		    field: 'id',
		    values: ids
		});

		$('#orderDt\\.orderMt\\.invNo').val('');
		$('#orderDt\\.orderMt\\.party\\.name').val('');
		$('#orderDt\\.design\\.styleNo').val('');
		$('#bagMt\\.qty').val('');
		$('#orderDt\\.purity\\.name').val('');
		$('#orderDt\\.color\\.name').val('');
		$('#orderDt\\.id').val('');
		$('#ordImgId').attr('src', '');
		$('#oImgHRId').attr('href', '');
		$('#vDepartment').val('');

		$('#vTotalStones').val('');
		$('#vTotalCarats').val('');
		$('#vTotalBagStones').val('');
		$('#vTotalBagCarats').val('');
		
		$('#transferBtnId').prop('disabled', true);
		$('#transferBtnIdTwo').prop('disabled', true);

		//$('#shAdjDId').prop('disabled', true);
		$('#shAdjId').prop('disabled', true);
		$('#fifoBtnId').prop('disabled', true);

	}


	
	
	function popDetails() {
		clearDBag();
		if ($("#bagMt\\.name").val() != "") {

			$
					.ajax({
						url : "<spring:url value='/manufacturing/transactions/jobBag/details.html?bagName=' />"
								+ $('#bagMt\\.name').val(),
						type : "GET",
						success : function(data, textStatus, jqXHR) {
							$.each(JSON.parse(data), function(idx, obj) {
								$('#orderDt\\.orderMt\\.invNo').val(obj.invNo);
								$('#orderDt\\.orderMt\\.party\\.name').val(obj.party);
								$('#orderDt\\.design\\.styleNo').val(obj.styleNo);
								$('#bagMt\\.qty').val(obj.bagPcs);
								$('#orderDt\\.purity\\.name').val(obj.kt);
								$('#orderDt\\.color\\.name').val(obj.color);
								$('#orderDt\\.id').val(obj.orderDtId);
								$('#ordImgId').attr('src', obj.image);
								$('#oImgHRId').attr('href', obj.image);
								$('#vDepartment').val(obj.department);
								$('#transferBtnId').prop('disabled', true);
								$('#transferBtnIdTwo').prop('disabled', true);

								//$('#shAdjDId').prop('disabled', true);
								$('#shAdjId').prop('disabled', false);
								$('#fifoBtnId').prop('disabled', true);

								popOrderStnDt();
								checkTranDt();
							});
						},
						error : function(jqXHR, textStatus, errorThrown) {
						}
					});
		}
	}

	function setDecimal(pFld) {
		$('#' + pFld).val(parseFloat($('#' + pFld).val()).toFixed(3));
	}

	function popOrderStnDt() {

		$("#diaBagDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/orderStnDt/listing.html?pInvNo="
									+ $('#orderDt\\.orderMt\\.invNo').val()
									+ "&pODt="+ $('#orderDt\\.id').val()
									+ "&opt=dbg"
									+ "&pIsBagDt=true"
									+ "&pBagName=" + $('#bagMt\\.name').val()
						});

		calTotals();

		clearAdjDtls();

		$("#filterOptId").css('display', 'none');
		
		
		
	}

	
	
	
	
	var diaBagRowId = -1;
	$('#diaBagDtId').bootstrapTable({}).on('click-row.bs.table',
			function(e, row, $element) {

				bagStones = jQuery.parseJSON(JSON.stringify(row)).bagStones;
				bagCarat = jQuery.parseJSON(JSON.stringify(row)).bagCarat;
				diaBagRowId = jQuery.parseJSON(JSON.stringify(row)).id;
				
				var sizeGrp = jQuery.parseJSON(JSON.stringify(row)).sizeGroup;
				var seting = jQuery.parseJSON(JSON.stringify(row)).setting;
				var settyp = jQuery.parseJSON(JSON.stringify(row)).setType;
				
				
				
				//-----------sizeGroup--------//
				
				if(sizeGrp === "" || sizeGrp === null){
					displayMsg(this, null, 'SizeGroup is null or blank');
					$('#importId').prop('disabled', true);
				}else{
					if(seting === "" || seting === null){
						displayMsg(this, null, 'Setting is null or blank');
						$('#importId').prop('disabled', true);
					}else{
						if(settyp === "" || settyp === null){
							displayMsg(this, null, 'SetType is null or blank');
							$('#importId').prop('disabled', true);
						}else{
							if (bagStones == 0.0 && bagCarat == 0.0) {
								$('#importId').prop('disabled', false);
							} else {
								$('#importId').prop('disabled', true);
							}
							
						}
						
					}
					
				}

				

				clearAdjDtls();

			});

	function clearAdjDtls() {
		var ids = $.map($("#stnRecDtId").bootstrapTable('getData'), function(
				row) {
			return row.id;
		});

		$("#stnRecDtId").bootstrapTable('remove', {
			field : 'id',
			values : ids
		});

		$('#choiceRd2').prop('checked', true);
		$('#fromRate').val('');
		$('#toRate').val('');
	}

	function calTotals() {
		$('#diaBagDtId').bootstrapTable({}).on(
				'load-success.bs.table',
				function(e, data) {
					var data = JSON.stringify($("#diaBagDtId").bootstrapTable(
							'getData'));

					var vStones = 0.0;
					var vCarat = 0.0;
					var vBagStones = 0.0;
					var vBagCarat = 0.0;
					$.each(JSON.parse(data), function(idx, obj) {
						vStones += Number(obj.stones);
						vCarat += Number(obj.carat);
						vBagStones += Number(obj.bagStones);
						vBagCarat += Number(obj.bagCarat);
						$('#vTotalStones').val(" " + vStones);
						$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));

						if (!(isNaN(vBagStones))) {
							$('#vTotalBagStones').val(" " + vBagStones);
						}
						if (!(isNaN(vBagCarat))) {
							$('#vTotalBagCarats').val(
									" " + parseFloat(vBagCarat).toFixed(3));
						}
					});
				});
	}


	$(document).on('submit', 'form#diaBagStnObj', function(e) {
		var data = $('#diaBagDtId').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});
		$('#pODIds').val(ids);

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				$("#diaBagDtId").bootstrapTable('updateRow', {
					index : rowIndex,
					row : {
						bagStones : $('#bagStone').val(),
						bagCarat : $('#bagCarat').val()
					}
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});

	var rowIndex = -1;
	var vrStnType = "";
	var vrShape = "";
	var vrQuality = "";
	var vrSize = "";
	var vrSizeGroup = "";
	var stnData = "";
	var uniOrderStnId = "";

	$('#diaBagDtId')
			.bootstrapTable({})
			.on(
					'click-row.bs.table',
					function(e, row, $element) {
						rowIndex = $element.attr('data-index');
						stnData = JSON.stringify(row);
						
						uniOrderStnId = jQuery.parseJSON(JSON.stringify(row)).id;
						vrStnType	 = jQuery.parseJSON(JSON.stringify(row)).stoneType;
						vrShape 	 = jQuery.parseJSON(JSON.stringify(row)).shape;
						vrQuality    = jQuery.parseJSON(JSON.stringify(row)).quality;
						vrSize       = jQuery.parseJSON(JSON.stringify(row)).mm;
						vrSizeGroup  = jQuery.parseJSON(JSON.stringify(row)).sizeGroup;

						$("#rowDiaBagStnId").css('display', 'block');

						$('#fldBagStone').val(jQuery.parseJSON(JSON.stringify(row)).stones);
						$('#fldBagCarat').val(jQuery.parseJSON(JSON.stringify(row)).carat);

						$("#filterOptId").css('display', 'none');

						
						$
								.ajax({
									url : '/jewels/manufacturing/transactions/diamondBagging/stonePointer.html',
									type : "GET",
									data : {
										shape : jQuery.parseJSON(JSON.stringify(row)).shape,
										size : jQuery.parseJSON(JSON.stringify(row)).mm
									},
									success : function(data, textStatus, jqXHR) {
										$("#vPointer").val(data);
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						
							
						popQualityDiamond();

					});
	

	
	function popQualityDiamond(){
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/diamondBagging/quality.html' />?shape='
					+ vrShape,
			type : 'GET',
			success : function(data) {
				$("#qualityId").html(data);
			}
		});

	}
	
	
	function saveQuality(){

		
		if($("#quality\\.id").val() === "" || $("#quality\\.id").val() === null){
			displayMsg(this, null, 'Please Select The Quality :-( ');
		}else{
			
			if(bagStones>0 ||bagCarat>0){
				displayMsg(this, null, 'Already Bagging Done ');
				
			}else{
			
			$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/diamondBagging/saveQuality.html' />?quality='
					+ $("#quality\\.id").val()
					+ "&ordStnId="+uniOrderStnId,
				type : 'GET',
				success : function(data) {
	
					if(data == "1"){
						displayMsg(this, null, 'Quality updated');
						popOrderStnDt();
					}else{
						displayMsg(this, null, 'Already Bagging Done ');
					}
					
					
				}
			});
			}

			
		} 
		
	}

	
	
	
	
	function popStnRecDt() {
		if (Number($('#fldBagStone').val() > 0)
				&& Number($('#fldBagCarat').val() > 0)) {
			doImport();
			$('#importId').prop('disabled', true);
		} else {
			displayMsg(this, null, 'Bag Stone/Bag Carat cannot be Zero');
		}
	}

	
	function doImport() {
		$("#vFifo").val('0');

		var rdChoice = $("input[type='radio'][name='choiceRd']:checked");
		var vlContinue = true;
		var vFrRt = $("#fromRate").val();
		var vToRt = $("#toRate").val();

		if (!(isNaN(vFrRt))) {
			if (!(isNaN(vToRt))) {
				if (Number(vFrRt) > Number(vToRt)) {
					vlContinue = false;
				}
			} else {
				vlContinue = false;
			}
		} else {
			vlContinue = false;
		}

		if (!vlContinue) {
			displayMsg(this, null, 'Check the Rates entered');

			var ids = $.map($("#stnRecDtId").bootstrapTable('getData'),
					function(row) {
						return row.id;
					});

			$("#stnRecDtId").bootstrapTable('remove', {
				field : 'id',
				values : ids
			});
		} else {
			if (rdChoice.val() == 'Group' && (vlContinue)
					&& (Number(vToRt) > Number(vFrRt))) {

				$("#stnRecDtId")
						.bootstrapTable(
								'refresh',
								{
									url : encodeURI("/jewels/manufacturing/transactions/stnRecDt/listing.html?pStoneType="
											+ vrStnType
											+ "&pShape="
											+ vrShape
											+ "&pQuality="
											+ vrQuality
											+ "&pSize="
											+ vrSize
											+ "&pBagStn="
											+ $('#fldBagStone').val()
											+ "&pCarat="
											+ $('#fldBagCarat').val()
											+ "&pPointer="
											+ $("#vPointer").val()
											+ "&pOpt="
											+ rdChoice.val()
											+ "&pSizeGroup="
											+ vrSizeGroup.replace("+", "$")
											+ "&pFrRate="
											+ $("#fromRate").val()
											+ "&pToRate=" + $("#toRate").val())
								});
			} else {
				$("#stnRecDtId")
						.bootstrapTable(
								'refresh',
								{
									url : encodeURI("/jewels/manufacturing/transactions/stnRecDt/listing.html?pStoneType="
											+ vrStnType
											+ "&pShape="
											+ vrShape
											+ "&pQuality="
											+ vrQuality
											+ "&pSize="
											+ vrSize
											+ "&pBagStn="
											+ $('#fldBagStone').val()
											+ "&pCarat="
											+ $('#fldBagCarat').val()
											+ "&pPointer="
											+ $("#vPointer").val()
											+ "&pOpt="
											+ rdChoice.val()
											+ "&pSizeGroup="
											+ vrSizeGroup.replace("+", "$")
											+ "&pFrRate="
											+ $("#fromRate").val()
											+ "&pToRate=" + $("#toRate").val())
								});

			}
		}

		if (rdChoice.val() == 'Group' || rdChoice.val() == 'Size') {
			$('#fifoBtnId').removeAttr('disabled');
		} else {
			$('#fifoBtnId').attr('disabled', 'disabled');
		}

		$("#trfAdjTblId").css('display', 'none');
		//$('#shAdjDId').prop('disabled', true);

		$('#vTransStones').val('');
		$('#vTransCarats').val('');

	}


	//------------new fifo --code---//------>>>>>

	function fifoAdj() {
		
		$('#fifoBtnId').attr('disabled', 'disabled');
		
		var fifoData = $('#stnRecDtId').bootstrapTable('getData');

		var vBalCarat = $.map(fifoData, function(item) {
			return item.balCarat;
		});
		
		//$('#fifoBalCarat').val(vBalCarat);

		var vPointer = $.map(fifoData, function(item) {
			return item.stonePointer;
		});

		//var rowBalCarat = $('#fifoBalCarat').val().split(",");

		var tempAdjCarat = $('#fldBagCarat').val();

		for (i = 0; i < fifoData.length; i++) {

			//alert(vPointer[i]+"-------"+i);

			if (Number(vBalCarat[i]) < Number(tempAdjCarat)) {

				$("#stnRecDtId").bootstrapTable(
						'updateRow',
						{
							index : i,
							row : {
								state : true,
								trfCarat : vBalCarat[i],
								balCarat : 0.0,
								trfStone : ((vBalCarat[i] / vPointer[i])
										.toFixed(0) <= 0 ? 1
										: (vBalCarat[i] / vPointer[i])
												.toFixed(0)),

							}
						});

				tempAdjCarat = tempAdjCarat - vBalCarat[i];
				tempAdjCarat = tempAdjCarat.toFixed(3);

			} else {
				if (Number(vBalCarat[i]) > Number(tempAdjCarat)) {

					$("#stnRecDtId").bootstrapTable(
							'updateRow',
							{
								index : i,
								row : {
									state : true,
									trfCarat : tempAdjCarat,
									balCarat : (vBalCarat[i] - tempAdjCarat)
											.toFixed(3),
									trfStone : ((tempAdjCarat / vPointer[i])
											.toFixed(0) <= 0 ? 1
											: (tempAdjCarat / vPointer[i])
													.toFixed(0)),
								}
							});

					tempAdjCarat = 0.0;

				}
			}

			if (tempAdjCarat === 0.0) {
				break;
			}

		} // ending loop

		transferTotal();

	}

	//-----------ending the fifo- button code-//---->>

	function updateBreakData() {
		lAdj = true;
		var tableData = JSON.stringify($("#stnRecDtId").bootstrapTable(
				'getData'));
		$
				.each(
						JSON.parse(tableData),
						function(idx, obj) {
							pId = Number(obj.id);

							$
									.each(
											JSON.parse(data),
											function(i, adDtl) {
												if (pId == adDtl.id) {
													lStnUpd = 0;
													if (adDtl.adjStone > 0) {
														lStnUpd = ($(
																'#fldBagStone')
																.val() == Math
																.ceil(adDtl.adjStone) ? 0
																: 1);
													}
													lCrtUpd = 0;
													if (adDtl.adjCarat > 0) {
														lCrtUpd = (parseFloat(
																$(
																		'#fldBagCarat')
																		.val())
																.toFixed(3) == parseFloat(
																adDtl.adjCarat)
																.toFixed(3) ? 0
																: 1);
													}

													lAdj = (lAdj == true) ? !(lStnUpd == 1)
															: lAdj;
													lAdj = (lAdj == true) ? !(lCrtUpd == 1)
															: lAdj;

													if (parseFloat(
															adDtl.adjCarat)
															.toFixed(3) > 0) {
														$("#stnRecDtId")
																.bootstrapTable(
																		'updateRow',
																		{
																			index : idx,
																			row : {
																				state : true,
																				//balStone : Math.ceil(adDtl.balStone),
																				//balCarat : parseFloat(adDtl.balCarat).toFixed(3),
																				trfStone : colFormatter(
																						Math
																								.ceil(adDtl.adjStone),
																						0 /* lStnUpd */),
																				trfCarat : colFormatter(
																						parseFloat(
																								adDtl.adjCarat)
																								.toFixed(
																										3),
																						0 /* lCrtUpd */)
																			}
																		});
													} else {
														$("#stnRecDtId")
																.bootstrapTable(
																		'updateRow',
																		{
																			index : idx,
																			row : {
																				state : false,
																				//balStone : Math.ceil(adDtl.balStone),
																				//balCarat : parseFloat(adDtl.balCarat).toFixed(3),
																				trfStone : colFormatter(
																						0,
																						0 /* lStnUpd */),
																				trfCarat : colFormatter(
																						parseFloat(
																								0)
																								.toFixed(
																										3),
																						0 /* lCrtUpd */)
																			}
																		});
													}

													lFifoDone = true;
												}
											});
						});
	}

	var lApply = 0;
	function saveAdjData() {
		
		$('#shAdjDId').attr('disabled', 'disabled');
		
		
		st1 = $('#fldBagStone').val();
		st2 = $('#vTransStones').val();
		crt1 = $('#fldBagCarat').val();
		crt2 = $('#vTransCarats').val();

		if ((Number(st1) == Number(st2))&& (parseFloat(crt1).toFixed(3) == parseFloat(crt2).toFixed(3))) {
			var rowData = $('#stnRecDtId').bootstrapTable('getSelections');

			var stnPk = $.map(rowData, function(item) {
				return item.id;
			});
			var bStn = $.map(rowData, function(item) {
				return item.balStone;
			});
			var bCart = $.map(rowData, function(item) {
				return item.balCarat;
			});
			var tBStn = $.map(rowData, function(item) {
				return item.trfStone;
			});
			var tBCart = $.map(rowData, function(item) {
				return item.trfCarat;
			});
			var postData = $(this).serializeArray();
			
			
			   $
					.ajax({
						url : '/jewels/manufacturing/transactions/diamondBagging/saveAdjustment.html?stnPk='
								+ stnPk
								+ "&tBStn="
								+ tBStn
								+ "&tBCart="
								+ tBCart
								+ "&bagNm="
								+ $("#bagMt\\.name").val()
								+ "&ordStnDtId=" + diaBagRowId,
						type : "POST",
						data : postData,
						success : function(data, textStatus, jqXHR) {

							if (data === "-3") {
								displayMsg(this, null, 'Stock not found');
							} else if (data === "-2") {
								displayMsg(this, null,'No record selected to save');
							} else if (data === "-4") {
								displayMsg(this, null,'Sieve or SizeGroup or Size or Quality is not present in Record');
							}
							else {

								var ids = $.map($("#stnRecDtId").bootstrapTable('getData'), function(row) {
									return row.id;
								});

								$("#stnRecDtId").bootstrapTable('remove', {
									field : 'id',
									values : ids
								});

								lFifoDone = false;
								$("#diaBagDtId").bootstrapTable('updateRow', {
									index : rowIndex,
									row : {
										bagStones : $('#vTransStones').val(),
										bagCarat : $('#vTransCarats').val()
									}
								});

								$('#fromRate').val('');
								$('#toRate').val('');

								//displayMsg(this, null,'Record Saved Successfully');
								
								$('#shAdjId').prop('disabled', false);
								
							}
							
							$('#shAdjDId').removeAttr('disabled');

						},
						error : function(jqXHR, textStatus, errorThrown) {
						}
					});

			$('#transferBtnId').removeAttr('disabled');
			$('#transferBtnIdTwo').removeAttr('disabled');

			// $('#shAdjId').prop('disabled', false);
			$('#fifoBtnId').attr('disabled', 'disabled'); 
			
		} else {
			displayMsg(this, null, 'Adjustment mismatch');
			$('#shAdjDId').removeAttr('disabled');
		}
	}

	function showAdjData() {
		if ($.trim($('#bagMt\\.name').val()).length > 0) {
			$('.nav-tabs a[href="#mainScreen"]').tab('show');

			if ($("#disableBagDetails").length != 0) {
				$("#" + document.querySelector("#disableBagDetails").id).attr(
						"id", "bagDetails");
				$('.nav-tabs a[href="#bagDetails"]').tab('show');
			} else {
				$('.nav-tabs a[href="#bagDetails"]').tab('show');
			}
		}

		refreshAdjData();
		$('#shAdjId').prop('disabled', false);
	}

	function refreshAdjData() {
		$("#dbDetailsId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/diamondBagging/listing.html?pBagName="
									+ $('#bagMt\\.name').val()
						});

		showHideTrfBtn();

	}

	function showHideTrfBtn() {
		$
				.ajax({
					url : '/jewels/manufacturing/transactions/diamondBagging/listing.html',
					type : "GET",
					data : {
						pBagName : $('#bagMt\\.name').val()
					},
					success : function(data, textStatus, jqXHR) {
						data = data.substring(data.indexOf("["));
						data = data.substring(0, data.length - 1);

						$.each(JSON.parse(data), function(idx, obj) {
							if (obj.transferred == 'false') {
								$('#transferBtnId').removeAttr('disabled');
								$('#transferBtnIdTwo').removeAttr('disabled');
							}
						});
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});
	}

	function adjDataDelete(e, id) {

		displayDlg(
				e,
				'javascript:deleteAdjData();',
				'Delete',
				'Do you want to Delete the selected record(s) ? <br><strong>Transfered records will not be deleted</strong>',
				'Continue');
	}

	function deleteAdjData() {
		$("#modalDialog").modal("hide");

		var adjData = JSON.stringify($("#dbDetailsId").bootstrapTable(
				'getSelections'));

		$
				.ajax({
					url : '/jewels/manufacturing/transactions/diamondBagging/deleteAdjustment.html',
					type : "POST",
					data : {
						adjData : adjData
					},
					success : function(data, textStatus, jqXHR) {
						popOrderStnDt();
						refreshAdjData();

						$('#delBtn').prop('disabled', true);

						if ($('#dbDetailsId').bootstrapTable('getSelections').length == 0) {
							$('#transferBtnIdTwo').prop('disabled', true);
						}

						$('#transferBtnId').attr('disabled', 'disabled');
						$('#transferBtnIdTwo').attr('disabled', 'disabled');

						showHideTrfBtn();
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});

		$("#modalRemove").modal("hide");
	}

	function trfData(e) {
		displayDlg(e, 'javascript:transferData();', 'Transfer Bags',
				'Do you want to transfer?', 'Continue');
	}

	function transferData() {
		$("#modalDialog").modal("hide");
		$('#transferBtnId').attr('disabled', 'disabled');
		$('#transferBtnIdTwo').prop('disabled', true);

		var data = JSON.stringify($("#diaBagDtId").bootstrapTable('getData'));
		

		$
				.ajax({
					url : "/jewels/manufacturing/transactions/diamondBagging/transfer.html",
					type : "POST",
					data : {
						pBagName : $('#bagMt\\.name').val(),
						stnData : stnData,
						data : data,
						pTransStones : $('#vTransStones').val(),
						pTransCarats : $('#vTransCarats').val(),
						pDiaBagRowId : diaBagRowId
					},
					success : function(data, textStatus, jqXHR) {
						diaBagRowId = -1;
						refreshAdjData();
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});
	}

	function transferDataOld() {

		var bagData = $('form#diamondBagging').serializeArray();
		bagData = JSON.stringify(bagData);

		var adjData = JSON.stringify($("#stnRecDtId").bootstrapTable(
				'getSelections'));

		$
				.ajax({
					url : "/jewels/manufacturing/transactions/diamondBagging/transfer.html",
					type : "GET",
					data : {
						bagData : bagData,
						stnData : stnData,
						adjData : adjData
					},

					success : function(data, textStatus, jqXHR) {
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});
	}

	function colFormatter(value, lApply) {
		var color = '#000000';

		if ($("#vFifo").val() == '1') {
			if (lApply == 1) {
				//color = '#00ff00';
			}

			if (lApply == 2) {
				//			color = "#FFA500";
				//color = "#ff0000";
			}
		}

		if (typeof value === 'undefined') {
			value = '';
		}

		return '<div  style="color: ' + color + '"><strong>' + value
				+ '</strong></div>';
	}

	function chkFormatter(value, row, index) {
		return value;
	}

	var prevTrfCarat = 0;
	var stnTblRow = -1;
	$('#stnRecDtId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
					
				
				stnTblRow = $element.attr('data-index');

				$("#trfAdjTblId").css('display', 'block');
				$('#fldTrfStone').val(jQuery.parseJSON(JSON.stringify(row)).trfStone);
				$('#fldTrfCarat').val(jQuery.parseJSON(JSON.stringify(row)).trfCarat);

				$('#vBalStone').val(jQuery.parseJSON(JSON.stringify(row)).balStone);
				$('#vBalCarat').val(jQuery.parseJSON(JSON.stringify(row)).balCarat);

				prevTrfCarat = jQuery.parseJSON(JSON.stringify(row)).trfCarat;

				$('#fldTrfStone').focus();
			});

	$('#fldTrfCarat').on(
			'blur',
			function(e) {
				if (Number($('#fldTrfStone').val() > 0)
						&& Number($('#fldTrfCarat').val() > 0)) {
				} else {
					displayMsg(this, null,
							'Adjusted Stone/Adjusted Carat cannot be Zero');
					return false;
				}

				
				if (Number($('#fldTrfCarat').val()) > Number($('#vBalCarat').val())
						+ Number(prevTrfCarat)) {
					displayMsg(this, null, 'Carat is less!  :-( ');
					return false;
				}

				var caratDiff = $('#fldTrfCarat').val() - Number(prevTrfCarat);

				$("#stnRecDtId").bootstrapTable(
						'updateRow',
						{

							index : stnTblRow,
							row : {
								state : true,
								trfStone : $('#fldTrfStone').val(),
								trfCarat : $('#fldTrfCarat').val(),
								balCarat : ($('#vBalCarat').val() - caratDiff).toFixed(3),
							}

						});

				lStnUpd = lCrtUpd = 2;
				dt1_1 = $('#fldBagStone').val();
				dt1_2 = $('#vTransStones').val();
				dt2_1 = $('#fldBagCarat').val();
				dt2_2 = $('#vTransCarats').val();
				if ((Number(dt1_1) == Number(dt1_2))
						&& (parseFloat(dt2_1).toFixed(3) == parseFloat(dt2_2)
								.toFixed(3))) {
					lStnUpd = lCrtUpd = 1;
				}

				$("#trfAdjTblId").css('display', 'none');
				//$('#shAdjDId').prop('disabled', false);

				$("#stnRecDtId").bootstrapTable('showRow', {
					index : stnTblRow,
					isIdField : false
				});

				transferTotal();

			});

	function transferTotal() {

		//var data = JSON.stringify($("#stnRecDtId").bootstrapTable('getSelections'));

		var data = $('#stnRecDtId').bootstrapTable('getData');

		var vTrfStone = $.map(data, function(item) {
			return item.trfStone;
		});
		var vTrfCarat = $.map(data, function(item) {
			return item.trfCarat;
		});

		var totTrfStone = 0;
		var totTrfCarat = 0.0;

		for (i = 0; i < data.length; i++) {
			totTrfStone += Number(vTrfStone[i]);
			totTrfCarat += Number(vTrfCarat[i]);
		}

		$('#vTransStones').val(totTrfStone);
		$('#vTransCarats').val(totTrfCarat.toFixed(3));

	}

	/* var vStones = 0.0;
	var vCarat = 0.0;
	$.each(JSON.parse(data), function(idx, obj) {
		if (typeof obj.trfCarat === 'undefined') {
		} else {
			vStones += getNumVal(Number(obj.trfStone));
			vCarat += getNumVal(Number(obj.trfCarat));
		}
	});

	$('#vTransStones').val(" " + vStones);
	$('#vTransCarats').val(" " + parseFloat(vCarat).toFixed(3));
	$('#vTotalBagStones').val(" " + vStones);
	$('#vTotalBagCarats').val(" " + parseFloat(vCarat).toFixed(3));
	
	 */

	/* function getNumVal(dt) {
			
			alert("in num val");
			
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt.indexOf("</strong>"));
		}

		return dt;
	}
	 */

	function showFltrOpt() {
		$("#filterOptId").css('display', 'block');
	}

	function fifoAdjOld() {
		var data = JSON.stringify($("#stnRecDtId").bootstrapTable('getData'));

		tmpBagStone = $("#fldBagCarat").val() / $("#vPointer").val();
		tmpBagCarat = $("#fldBagCarat").val();

		$.each(JSON.parse(data), function(idx, obj) {

			adjBagStone = 0;
			adjBagCarat = 0;

			if (tmpBagStone >= Number(obj.balStone)) {
				adjBagStone = Number(obj.balStone);
			} else {
				adjBagStone = tmpBagStone;
			}

			if (tmpBagCarat >= Number(obj.balCarat)) {
				adjBagCarat = Number(obj.balCarat);
			} else {
				adjBagCarat = tmpBagCarat;
			}

			$("#stnRecDtId").bootstrapTable('updateRow', {
				index : idx,
				row : {
					bagStone : adjBagStone,
					bagCarat : adjBagCarat
				}
			});

			tmpBagStone = tmpBagStone - Number(obj.balStone)
			tmpBagCarat = tmpBagCarat - Number(obj.balCarat)
		});
	}
	
	
	
	
	function checkTranDt(){
		
		//alert("XXXXXX");
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/bagCheck.html?bagNm="+$('#bagMt\\.name').val(),
			type : 'GET',
			success : function(data) {
				
				//alert(data);
				
				if(data === '-2'){
					//block the div
					$('#bagCheckId').css('display','none');
					$('#marqueeIdDisp').css('display','block');
				}else{
					$('#bagCheckId').css('display','block');
					$('#marqueeIdDisp').css('display','none');
				}
					
				
			}
			
			
		});
		
		
		
	}
	
	
	
	
	
	
	
	
	/* function doBaggingReturn(){
		
		$.ajax({
			url:'/jewels/manufacturing/transactions/baggingReturn.html?bagNm='+$('#bagMt\\.name').val(),
			type:'GET',
			success:function(data){
				
				//alert(data);
				
				if(data === '-2'){
					displayInfoMsg(this, null, ' Bagging Returned SucessFully ! ');
					popOrderStnDt();
				}else if(data === '-1'){
					displayMsg(this, null, ' Cannot Return Bag Present in Department ! ');
				}
				
				
				
				
			}
		});
		
		
		
	} */
	
	
	
	
	
</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/common/design.js' />"></script>
<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />
	