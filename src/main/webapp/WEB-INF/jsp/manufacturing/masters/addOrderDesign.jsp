<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="design"
		action="/jewels/manufacturing/masters/design/add.html"
		cssClass="form-horizontal designForm">

		<c:if test="${success eq true}">
			<div class="col-xs-12">
				<div class="row">
					&nbsp;
					<div class="alert alert-success">Design ${action}
						successfully!</div>
				</div>
			</div>
		</c:if>

		<!-- For Order -->
		<div class="col-xs-12" id="oMsgId" style="display: none;">
			<div class="row">
				&nbsp;
				<div class="alert alert-success message-text">Order ${action}
					successfully!</div>
			</div>
		</div>
		<!-- For Order -->

		<!-- Column Sizing -->
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Group:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Create
						Date:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Design
						No:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Style
						No:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Version:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Designer:</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-2">
					<form:select path="group.id" class="form-control">
						<form:option value="" label="--- Select Group ---" />
						<form:options items="${groupMap}" />
					</form:select>
				</div>
				<div class="col-lg-2">
					<form:input path="cDate" cssClass="form-control" />
					<form:errors path="cDate" />
				</div>
				<div class="col-lg-2">
					<form:input path="designNo" cssClass="form-control" />
					<form:errors path="designNo" />
				</div>
				<div class="col-lg-2">
					<form:input path="styleNo" cssClass="form-control" />
					<form:errors path="styleNo" />
				</div>
				<div class="col-lg-2">
					<form:input path="version" cssClass="form-control" />
					<form:errors path="version" />
				</div>
				<div class="col-lg-2">
					<form:input path="designerId" cssClass="form-control" />
					<form:errors path="designerId" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Category:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Sub
						Category:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Model
						/ Maker:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Concept:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Sub
						Concept:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Sizer:</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-2">
					<form:select path="category.id" class="form-control"
						onChange="javascript:popSCat(this);">
						<form:option value="" label="- Select Category -" />
						<form:options items="${categoryMap}" />
					</form:select>
				</div>
				<div class="col-lg-2" id="sCatId">
					<form:select path="subCategory.id" class="form-control">
						<form:option value="" label="- Select Sub Category ---" />
						<form:options items="${subCategoryMap}" />
					</form:select>
				</div>
				<div class="col-lg-2">
					<form:input path="modelMakerId" cssClass="form-control" />
					<form:errors path="modelMakerId" />
				</div>
				<div class="col-lg-2">
					<form:select path="concept.id" class="form-control"
						onChange="javascript:popSCon(this);">
						<form:option value="" label="--- Select Concept ---" />
						<form:options items="${conceptMap}" />
					</form:select>
				</div>
				<div class="col-lg-2" id="s">
					<form:select path="subCategory.id" class="form-control">
						<form:option value="" label="- Select Sub Category ---" />
						<form:options items="${subCategoryMap}" />
					</form:select>
				</div>
				<div class="col-lg-2">
					<form:input path="sizerId" cssClass="form-control" />
					<form:errors path="sizerId" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Size:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Wax
						Temp:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Wax
						Pressure:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Wax
						Weight:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Silver
						Weight:</label>
				</div>
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">14K:</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-2">
					<form:input path="psizeId" cssClass="form-control" />
					<form:errors path="psizeId" />
				</div>
				<div class="col-lg-2">
					<form:input path="waxTemp" cssClass="form-control" />
					<form:errors path="waxTemp" />
				</div>
				<div class="col-lg-2">
					<form:input path="waxPre" cssClass="form-control" />
					<form:errors path="waxPre" />
				</div>
				<div class="col-lg-2">
					<form:input path="WaxWt" cssClass="form-control" />
					<form:errors path="WaxWt" />
				</div>
				<div class="col-lg-2">
					<form:input path="silverWt" cssClass="form-control" />
					<form:errors path="silverWt" />
				</div>
				<div class="col-lg-2">
					<form:input path="grms" cssClass="form-control" />
					<form:errors path="grms" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-2">
					<label for="inputLabel3" class=".col-lg-2 text-right">Email
						Concept:</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-2">
					<input class="form-control" placeholder=".col-lg-2" type="text">
				</div>
				<div class="col-lg-1">
					<label></label>
				</div>
				<div class="col-lg-2">
					<div class="checkbox">
						<form:checkbox path="mustHave" value="2" />
						<strong>Silver Model:</strong>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="checkbox">
						<form:checkbox path="mustHave" value="2" />
						<strong>Rubber Mould:</strong>
					</div>
				</div>
				<div class="col-lg-1">
					<div class="checkbox">
						<form:checkbox path="mustHave" value="2" />
						<strong>Cancel:</strong>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="checkbox">
						<form:checkbox path="mustHave" value="1" />
						<strong>Blue, Black, Coco:</strong>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="checkbox">
						<form:checkbox path="mustHave" value="1" />
						<strong>Must Have</strong>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-1">
					<label for="inputLabel3" class=".col-lg-12 text-right">Hold
						Remarks:</label>
				</div>
				<div class="col-lg-11">
					<form:input path="holdRemark" cssClass="form-control" />
					<form:errors path="holdRemark" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-1">
					<label for="inputLabel3" class=".col-lg-12 text-right">Remarks:</label>
				</div>
				<div class="col-lg-11">
					<form:input path="hsRemark" cssClass="form-control" />
					<form:errors path="hsRemark" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="form-group">
			<div class="col-lg-12 text-center">
				<input type="submit" value="Save" class="btn btn-primary" /> <a
					class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/design.html">Design Listing</a>
				<form:input type="hidden" path="id" />
				<form:input type="hidden" path="defaultImage" />
				<input type="hidden" name="invImg1" id="invImg1" value="${image1}" />
				<input type="hidden" name="invImg2" id="invImg2" value="${image2}" />
				<input type="hidden" name="invImg3" id="invImg3" value="${image3}" />
			</div>
		</div>
	</form:form>
</div>
<div class="row">
	<div class="form-group">
		<div class="col-xs-12">
			<span class="col-lg-12 label label-default text-right" style="font-size: 18px;">Stone Details</span>
		</div>
	</div>
</div>

<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row" id="forOrderDS">
			<div class="col-xs-12">
				<div id="toolbarDS">
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="javascript:goToSType();"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Details</a>
				</div>
				<div>
					<table id="stoneDId" data-toggle="table" data-toolbar="#toolbarDS"
						data-pagination="false" data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]"
						data-height="380">
						<thead>
							<tr>
								<th data-field="stoneType" data-sortable="true">Stone Type</th>
								<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
								<th data-field="subShape" data-sortable="true">Sub Shape</th>
								<th data-field="quality" data-sortable="true">Quality</th>
								<th data-field="mm" data-sortable="true">Size/MM</th>
								<th data-field="sieve" data-sortable="true">Sieve</th>
								<th data-field="sizeGroup" data-sortable="true">Size Group</th>
								<th data-field="stones" data-sortable="true" 	>Stone</th>
								<th data-field="carat" data-sortable="true">Carat</th>
								<th data-field="setting" data-sortable="true">Setting</th>
								<th data-field="settype" data-sortable="true">Set Type</th>
								<th data-field="deactive" data-sortable="true">Status</th>
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Deactivate</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

		<c:set var="option" value="Design Stone" />

		<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
	</div>

	<div id="rowId">
		<div class="form-group">
			<table class="line-items editable table">
				<tr class="panel-heading">
					<th>&nbsp;&nbsp;Total Stone :  
						<input type="text" id="vTotalStone" name="vTotalStone" disabled="true" />
					</th>
					<th>Total Carat : 
						<input type="text" id="vTotalCarat" name="vTotalCarat" disabled="true" />
					</th>
				</tr>
			</table>

			<form:form commandName="designStone" id="designStone"
				action="/jewels/manufacturing/masters/designStone/add.html"
				cssClass="form-horizontal designStoneForm">

				<table class="line-items editable table table-bordered">
					<thead class="panel-heading">
						<tr class="panel-heading">
							<th>Stone Type</th>
							<th>Shape</th>
							<th>Sub Shape</th>
							<th>Quality</th>
							<th>Size/MM</th>
							<th>Sieve</th>
							<th>Size Group</th>
							<th>Stone</th>
							<th>Carat</th>
							<th>Setting</th>
							<th>Set Type</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<form:select path="stoneType.id" class="form-control">
									<form:option value="" label="- Select Stone Type -" />
									<form:options items="${stoneTypeMap}" />
								</form:select>
							</td>
							<td>
								<form:select path="shape.id" class="form-control"
									onChange="javascript:popSShape(this.value); javascript:popQuality(this.value); javascript:popSizeGroup(this.value); javascript:popChart(this.value);">
									<form:option value="" label="- Select Shape -" />
									<form:options items="${shapeMap}" />
								</form:select>
							</td>
							<td>
								<div id="sShapeId">
									<form:select path="subShape.id" class="form-control">
										<form:option value="" label="- Select Sub Shape -" />
										<form:options items="${subShapeMap}" />
									</form:select>
								</div>
							</td>
							<td>
								<div id="qualityId">
									<form:select path="quality.id" class="form-control">
										<form:option value="" label="- Select Quality -" />
										<form:options items="${qualityMap}" />
									</form:select>
								</div>
							</td>
							<td>
								<div id="sizeId">
									<form:select path="size" class="form-control"
										onChange="javascript:getSizeMMDetails();">
										<form:option value="" label="- Select Size -" />
										<form:options items="${chartMap}" />
									</form:select>
								</div>
							</td>
							<td><input type="text" id="vSieve" name="vSieve" class="form-control"
									disabled="true" /></td>
							<td><input type="text" id="vSizeGroupStr" name="vSizeGroupStr" class="form-control"
									disabled="true" /></td>
<%--
							<td>
								<div id="sizeGroupId">
									<form:select path="sizeGroup.id" class="form-control">
										<form:option value="" label="- Select Size Group -" />
										<form:options items="${sizeGroupMap}" />
									</form:select>
								</div>
							</td>
--%>
							<td><form:input path="stone" cssClass="form-control" /></td>
							<td><form:input path="carat" cssClass="form-control" /></td>
							<td>
								<form:select path="setting.id" class="form-control">
									<form:option value="" label="- Select Setting -" />
									<form:options items="${settingMap}" />
								</form:select>
							</td>
						</tr>
						<tr>
							<td colspan="11"><input type="submit" value="Save"
								class="btn btn-primary" /> <form:input type="hidden" path="id" />
								<form:input type="hidden" path="sieve" />
								<form:input type="hidden" path="sizeGroupStr" />
								<input type="hidden" id="dsStyleNo" name="dsStyleNo" /> <input
								type="hidden" id="dsVersion" name="dsVersion" /></td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(
			function(e) {
				$(".designForm").validate(
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
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							},
							messages : {
								name : {
									remote : "Design already exists"
								}
							}
						});

				popDesignStone();

				if ($('#defaultImage').val().trim().length > 0) {
					$('input#dfImage')[$('#defaultImage').val()-1].checked = true;					
				}

				$('input#dfImage').on('change', function() {
				    $('input#dfImage').not(this).prop('checked', false);  
				    $('#defaultImage').val($('input:checked').val());
				});
			});

	function popDesignStone() {
		$("#stoneDId").bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/designStone/listing.html?styleNo="
						+ $('#styleNo').val()
						+ "&version="
						+ $('#version').val()
			});
	}

	function popDesignComponent() {
		$("#stoneDCId").bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/designComponent/listing.html?styleNo="
						+ $('#styleNo').val()
						+ "&version="
						+ $('#version').val()
			});
	}

	function popSCat(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/masters/subCategory/list.html' />?catId='
							+ vSel.value,
					type : 'GET',
					success : function(data) {
						$("#sCatId").html(data);
					}
				});
	}

	function popSShape(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/masters/subShape/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sShapeId").html(data);
					}
				});
	}

	function popQuality(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/masters/quality/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#qualityId").html(data);
					}
				});
	}

	function popChart(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/masters/chart/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sizeId").html(data);
					}
				});
	}

	function getSizeMMDetails() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/masters/sizeMM/details.html' />?shapeId='
							+ $('#shape\\.id').val()
							+ '&size='
							+ $('#size').val(),
					type : 'GET',
					success : function(data) {
						fldData = data.split("_");
						$("#vSieve").val(fldData[0]);
						$("#vSizeGroupStr").val(fldData[1]);
					}
				});
	}

	function popSizeGroup(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/masters/sizeGroup/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sizeGroupId").html(data);
					}
				});
	}

	$(document)
			.on(
					'submit',
					'form#designStone',
					function(e) {
						$('#dsStyleNo').val($('#styleNo').val());
						$('#dsVersion').val($('#version').val());
						$('#sieve').val($('#vSieve').val());
						$('#sizeGroupStr').val($('#vSizeGroupStr').val());

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
										popDesignStone();
										$('form#designStone input[type="text"],texatrea, select').val('');
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						e.preventDefault(); //STOP default action
					});

	function editDStone(dsId) {
		$.ajax({
			url : "/jewels/manufacturing/masters/designStone/edit/" + dsId + ".html",
			type : 'GET',
			success : function(data) {
				$("#rowId").html(data);

				$('#vSieve').val($('#sieve').val());
				$('#vSizeGroupStr').val($('#sizeGroupStr').val());
			}
		});
	}

	function goToSType() {
		$.ajax({
			url : "/jewels/manufacturing/masters/designStone/edit/0.html",
			type : 'GET',
			success : function(data) {
 				$("#dsId").val('');

				$('form#designStone input[type="text"],texatrea, select').val('');
				$('#stoneType\\.id').focus();
 			}
		});
	}


	$(document).on(
		'submit',
		'form#designComponent',
		function(e) {
			$('#dcStyleNo').val($('#styleNo').val());
			$('#dcVersion').val($('#version').val());

			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");

			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					popDesignComponent();
					$('form#designComponent input[type="text"],texatrea, select').val('');
				},
				error : function(jqXHR, textStatus,
						errorThrown) {
				}
			});
			e.preventDefault(); //STOP default action
	});


	function editDComponent(dsId) {
		$.ajax({
			url : "/jewels/manufacturing/masters/designComponent/edit/" + dsId + ".html",
			type : 'GET',
			success : function(data) {
				$("#rowDCId").html(data);
			}
		});
	}

	function goToComponent() {
alert('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx');
		$.ajax({
			url : "/jewels/manufacturing/masters/designComponent/edit/0.html",
			type : 'GET',
			success : function(data) {
 				$("#dcId").val('');

				$('form#designComponent input[type="text"],texatrea').val('');
				$('form#designComponent select').val('');
				$('#component\\.id').focus();
			}
		});
	}

	function stonesTotal(fVal) {
		$('#vTotalStone').val('200');
		return "100";
	}

	
/* 
	function fetchCategory() {
// url : "/jewels/manufacturing/masters/category.html",
// url : "/jewels/manufacturing/masters/designStone/edit/1.html",

		$.ajax({
			url : "/jewels/manufacturing/masters/orderDesignStone.html?styleNo="
				+ $('#styleNo').val()
				+ "&version="
				+ $('#version').val(),
			type : 'GET',
			success : function(data) {
// 				$("#dsPId").html(data);
//alert(data);
//popDesignStone();

				$('#vSieve').val($('#sieve').val());
				$('#vSizeGroupStr').val($('#sizeGroupStr').val());
			}
		});

	}
*/
</script>

