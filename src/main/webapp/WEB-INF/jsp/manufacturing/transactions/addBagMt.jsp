<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:set var="optionText" value="Delete" />

<div class="form-group">
	<form:form commandName="bagMt"
		action="<spring:url value='/manufacturing/masters/order/add.html' />"
		cssClass="form-horizontal bagMtForm">

		<c:if test="${success eq true}">
			<div class="col-xs-12">
				<div class="row">
					&nbsp;
					<div class="alert alert-success">Bag ${action} successfully!</div>
				</div>
			</div>
		</c:if>

		<!-- Column Sizing -->
		<div class="form-group">
			<label class="col-sm-1 text-right control-label">Order No:</label>
			<div class="col-sm-2">
				<form:input path="orderMt.invNo" cssClass="form-control" 
					onblur="javascript:popOrderDt();checkOrderClose();checkOrderApproval();" />
				<form:errors path="orderMt.invNo" />
			</div>
			
			<div>
				<input type="search" id="searchStyleNo" placeholder="Style Search" class="form-control" style="width: 5cm;"  />
			</div>
		
			
		</div>
	</form:form>
</div>

<div class="row">
	<div class="form-group">
		<div class="col-xs-12">
			<span class="col-lg-12 label label-default text-right"
				style="font-size: 18px;">Bag Details</span>
		</div>
	</div>
</div>

<!-- <div class="row">
  <input type="search" id="searchStyleNo"  placeholder="Search" class="form-control" style="width: 5cm"/>
</div> -->

<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row">
		
			<div class="col-xs-8">
			
				<div>				
					<table id="bagDtId" data-toggle="table" data-toolbar="#toolbarDt"
						 data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="280">
						<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="srNo" >Sr No</th>
								<th data-field="style" data-sortable="true">Style</th>
								<th data-field="purity" data-align="left" data-sortable="true">KT</th>
								<th data-field="color" data-align="left" data-sortable="true">Color</th>
								<th data-field="quantity" data-sortable="true">Order Pcs</th>
								<th data-field="genQty" data-sortable="true">Gen Qty</th>
								<th data-field="balQty" data-sortable="true">Bal Qty</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			
			<div class="col-xs-4">
			
				<div>
					<table id="jobBagId" data-toggle="table" data-toolbar="#toolbarBgDt"
						data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="280">
						<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="name" data-sortable="true">Jobbag No</th>
								<th data-field="qty" data-align="left">PCs</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<c:set var="option" value="Generated Bag(s)" />

<div class="row">
	<div class="form-group">
		<div class="col-xs-12">
			<form:form commandName="bagMt" id="jobBagGenId"
				action="/jewels/manufacturing/transactions/jobBag/add.html"
				cssClass="form-horizontal jobBagForm">

				<table class="table table-bordered">
					<tbody>
						<tr>
							<th class="col-xs-1"><label class="control-label">Bag
									Quantity:</label></th>
							<td class="col-xs-1"><input name="bagQty" id="bagQty" class="form-control" /></td>
							<td class="col-xs-8">
							
								<input type="submit" id="GBId" value="Generate Bags" class="btn btn-primary" /> 
								<input type="submit" id="OrdId" value="Generate As Per Order" class="btn btn-primary" />
								<input type="submit" id="bagParamId" value="Generate As Per Bag Parameter" class="btn btn-primary" /> 
								<input type="hidden" name="pInvNo" id="pInvNo" /> 
								<input type="hidden" name="pODIds" id="pODIds" />
							</td>
							<td class="col-xs-1">
								<button id="rmId" class="btn btn-danger" onClick="javascript:delSelBags(this);" disabled="disabled">
									Delete selected Bag(s)
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
	</div>
</div>


<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<script type="text/javascript">

	$(document).ready(
		function(e) {
			
			$(".bagMtForm").validate(
			{
				rules : {
					'orderMt.invNo' : {
						required : true,
						remote : {
							url : "<spring:url value='/manufacturing/masters/orderNo/Available.html' />",
							type : "get",
							data : {
								orderNo : function() {
									return $("#orderMt\\.invNo").val();
								}
							}
						}
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
					'orderMt.invNo' : {
						remote : "Order No. does not exist"
					}
				}
			});

			$("#orderMt\\.invNo")
				.autocomplete(
				{
					source : "<spring:url value='/manufacturing/transactions/bag/order/list.html' />",
					minLength : 2
				});
			
			
			//----search ---//
			
			$("#searchStyleNo").on("keyup", function() {
			    var value = $(this).val();
			    
			    $("#bagDtId tr").each(function(index) {
			
			        if (index != 0) {
	
			            $row = $(this);
			            
			            var styNoId = $row.find('td:eq(2)').text();  
			            
			            if (styNoId.toLowerCase().indexOf(value.toLowerCase()) != 0 ) {
			                $(this).hide();
			            }else {
			                $(this).show();
			            }
			            
			        }
			    });
			});
			

		});

	function popOrderDt() {
		
		if ($.trim($("#orderMt\\.invNo").val()).length > 0) {
			$("#bagDtId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/masters/orderDt/bagGeneration/listing.html?pInvNo="
							+ $("#orderMt\\.invNo").val()
				});

			
		}
	}

	function popJobBagDt() {
		$("#jobBagId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/bagMt/listing.html?pInvNo="
						+ $("#orderMt\\.invNo").val() + "&pOrdDt=" + bagDtRow
			});
	}

	var whichBtn;
	$('#jobBagGenId input[type="submit"]').click(function(e) {
		whichBtn = $(this).attr("id");
	});

	$(document).on('submit', 'form#jobBagGenId', function(e) {

		var lContinue = 1;
		var vQty = $('#bagQty').val();

		if (whichBtn == 'GBId') {
			if (($.trim(vQty).length == 0) || (isNaN(vQty))) {
				displayMsg(this, null, 'Bag quantity entered is incorrect');

				lContinue = 0;
			}
		}
		
		$('#pInvNo').val($("#orderMt\\.invNo").val());

		if (lContinue == 1) {
			var data = $('#bagDtId').bootstrapTable('getSelections');
			var ids = $.map(data, function(item) {
				return item.id;
			});
			
			$('#pODIds').val(ids);

			if ($.trim(ids).length > 0) {
				$('#bagQty').val(whichBtn == 'OrdId' ? -1 :whichBtn == 'bagParamId' ? -2 : $('#bagQty').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
				
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {

						$.unblockUI();

						popOrderDt();
						$('#bagQty').val('');
			
						//compareGenPcs();	
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});	
			} else {
				if (whichBtn == 'GBId' || whichBtn == 'OrdId') {
					displayMsg(this, null, 'Select atleast one Style Id');
				}
			}
		}
		e.preventDefault(); //STOP default action

		whichBtn = '';
	});

	var bagDtRow = -1
	$('#bagDtId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			bagDtRow = jQuery.parseJSON(JSON.stringify(row)).id;

			$("#jobBagId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/bagMt/listing.html?pInvNo="
						+ $("#orderMt\\.invNo").val() + "&pOrdDt=" + jQuery.parseJSON(JSON.stringify(row)).id
				});
	
	/* 		var data = JSON.stringify($("#bagDtId").bootstrapTable('getData'));
			$.each(JSON.parse(data), function(idx, obj) {
				$("#bagDtId").bootstrapTable('updateRow', {
					index : idx,
					row : {
						style : colFormatter(jQuery.parseJSON(JSON.stringify(obj)).style, 0),
					}
				});									
			}); */
	
		/* 	idx = $element.attr('data-index');
			$("#bagDtId").bootstrapTable('updateRow', {
				index : idx,
				row : {
					style : colFormatter(jQuery.parseJSON(JSON.stringify(row)).style, 1),
				}
			});		 */							

			$('#rmId').prop('disabled', true);
	});

	$('#bagDtId').on('load-success.bs.table', function () {
		/* compareGenPcs(); */
	});

	

	
		$('#jobBagId').on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table', function () {
			$('#rmId').prop('disabled', (!$('#jobBagId').bootstrapTable('getSelections').length));
		});
		
	

	function compareGenPcs() {

		var data = JSON.stringify($("#bagDtId").bootstrapTable('getData'));
		$.each(JSON.parse(data), function(idx, obj) {
			var vOrdPcs = jQuery.parseJSON(JSON.stringify(obj)).quantity;
			var vGenQty = jQuery.parseJSON(JSON.stringify(obj)).genQty;

			if (vOrdPcs == vGenQty) {
				$("#bagDtId").bootstrapTable('updateRow', {
					index : idx,
					row : {
						quantity : colFormatter(jQuery.parseJSON(JSON.stringify(obj)).quantity, 2),
						genQty : colFormatter(jQuery.parseJSON(JSON.stringify(obj)).genQty, 2),
					}
				});									
			}
		});
	}

	function delSelBags(e) {
		doDelete(e, "javascript:deleteBags();");
	}

	function deleteBags() {
		var data = $('#jobBagId').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			deleteBagDt(item.id, 1);
		});		
	}

	$('#jobBagId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
		});


	function bagDtDelete(e, id, dt) {
		doDelete(e, "javascript:deleteBagDt(" + id + ", " + dt + ");");
	}

	function deleteBagDt(id, dt) {
		$("#modalRemove").modal("hide");

		var url = "/jewels/manufacturing/transactions/bagMt/delete/" + id;

		$.ajax({
			url : url + ".html",
			type : 'GET',
			success : function(data, textStatus, jqXHR) {
				if (dt == 1) {
					popOrderDt();
					popJobBagDt();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}

	function colFormatter(value, lApply) {
		var color = '#000000';

		if (lApply == 0) {
			value = getNumVal(value);
		} else if (lApply == 1) {
			color = "#FFA500";

			value = '<div  style="color: ' + color + '"><strong>' + value + '</strong></div>';
		} else if (lApply == 2) {
			color = "#40FF00";

			value = '<div  style="color: ' + color + '"><strong>' + value + '</strong></div>';
		}

		return value;
    }

	
	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>")+8, dt.indexOf("</strong>"));		
		}

		return dt;
	}

	
	function checkOrderClose(){

		 $.ajax({
				url : "/jewels/manufacturing/masters/orderDt/checkOrderClose.html?pInvNo="
					+ $("#orderMt\\.invNo").val(),
				type : 'GET',
				success : function(data) {
					
					if(data === 'true'){
						displayMsg(this, null, 'Order is Closed');
																		
						$('#GBId').prop('disabled', true);
						$('#OrdId').prop('disabled', true);
						$('#bagParamId').prop('disabled', true);
						$('#rmId').prop('disabled', true);
						
					}else{
						
						$('#GBId').prop('disabled', false);
						$('#OrdId').prop('disabled', false);
						$('#bagParamId').prop('disabled', false);
						$('#rmId').prop('disabled', false);
						}
					
				 }
			}); 
		}


	function checkOrderApproval(){

		 $.ajax({
				url : "/jewels/manufacturing/masters/orderDt/checkOrderApproval.html?pInvNo="
					+ $("#orderMt\\.invNo").val(),
				type : 'GET',
				success : function(data) {
					
					if(data === 'true'){
						

						setTimeout(
								function() {										
						$('#GBId').prop('disabled', true);
						$('#OrdId').prop('disabled', true);
						$('#bagParamId').prop('disabled', true);
						$('#rmId').prop('disabled', true);

						displayMsg(this, null, 'Order is not appoved');
								}, 30);
					}else{
						
						$('#GBId').prop('disabled', false);
						$('#OrdId').prop('disabled', false);
						$('#bagParamId').prop('disabled', false);
						$('#rmId').prop('disabled', false);
						}
					
				 }
			}); 
		}
	
</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

	<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
