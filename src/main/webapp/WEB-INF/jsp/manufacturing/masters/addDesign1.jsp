<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:if test="${success eq true}">
	<div class="col-xs-12">
		<div class="alert alert-success">Design ${action} successfully!</div>
	</div>
</c:if>

<div class="form-group">
	<div id="mDivId" class="col-xs-12">
		<form:form commandName="design"
			action="/jewels/manufacturing/masters/design/add.html"
			cssClass="form-horizontal designForm">

			<!-- For Order -->
			<div class="col-xs-12" id="oMsgId" style="display: none;">
				<!-- div class="row" -->
				&nbsp;
				<div class="alert alert-success message-text">Order ${action}
					successfully!</div>
				<!-- /div -->
			</div>
			<!-- For Order -->

			<!-- Column Sizing -->
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Group:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Create
							Date:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Design
							No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Old Style
							No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Style
							No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Version:</label>
					</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<form:select path="designGroup.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label="--- Select Group ---" />
							<form:options items="${groupList}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="designDt" cssClass="form-control"  style="font-size: 13px;" />
						<form:errors path="designDt" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="designNo" iddx="designDesignNo" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="designNo" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="oldStyleNo" cssClass="form-control" disabled="true" style="font-size: 13px;" />
						<form:errors path="oldStyleNo" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<%-- form:input path="styleNo" iddx="designStyleNo" cssClass="form-control" onBlur="javascript:getStyleDt();" / --%>
						<form:input path="styleNo" iddx="designStyleNo" cssClass="form-control" />
						<form:errors path="styleNo" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="version" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="version" />
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Designer:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Category:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Sub
							Category:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Model
							/ Maker:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Concept:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Sub
							Concept:</label>
					</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<form:select path="designerEmployee.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Designeer " />
							<form:options items="${designerMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:select path="category.id" class="form-control" style="font-size: 13px;"
							onChange="javascript:popSCat(this); javascript:popPSize(this);">
							<form:option value="" label="- Select Category -" />
							<form:options items="${categoryMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2" id="sCatId">
						<form:select path="subCategory.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label="- Select Sub Category ---" />
							<form:options items="${subCategoryMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:select path="modelMakerEmployee.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Model/Maker " />
							<form:options items="${modelMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:select path="concept.id" class="form-control"
							onChange="javascript:popSCon(this);" style="font-size: 13px;">
							<form:option value="" label="--- Select Concept ---" />
							<form:options items="${conceptMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2" id="sConId">
						<form:select path="subConcept.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Sub Concept " />
							<form:options items="${subConceptMap}" />
						</form:select>
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Sizer:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Size:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Wax
							Temp:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Wax
							Pressure:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Wax
							Weight:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Silver
							Weight:</label>
					</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<form:select path="sizerEmployee.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Sizer " />
							<form:options items="${sizeingMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2" id="pSizeId">
						<form:select path="productSize.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Size " />
							<form:options items="${productSizeMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="waxTemp" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="waxTemp" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="waxPre" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="waxPre" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="WaxWt" cssClass="form-control" style="font-size: 13px;" onBlur="javascript:calSlvrWt();" />
						<form:errors path="WaxWt" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="silverWt" cssClass="form-control" style="font-size: 13px;" onBlur="javascript:calWaxWt();" />
						<form:errors path="silverWt" />
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">${defaultPurity}:</label>
					</div>
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 12px;">Email
							Concept:</label>
					</div>
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Reference No:</label>
					</div>
 			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<form:input path="grms" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="grms" />
					</div>
					<div class="col-lg-4 col-sm-4">
						<form:select path="emailConcept.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label="--- Select Email Concept ---" />
							<form:options items="${emailConceptMap}" />
						</form:select>
					</div>
					<div class="col-lg-4 col-sm-4">
						<form:input path="refNo" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="refNo" />
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2 text-right">
						<div class="checkbox">&nbsp;&nbsp;
							<form:checkbox path="silverModel" value="1" />
							<strong style="font-size: 13px;">Silver Model</strong>
						</div>
					</div>
					<div class="col-lg-2 col-sm-2 text-center">
						<div class="checkbox">
							<form:checkbox path="cancel" value="1" />
							<strong style="font-size: 13px;">Cancel</strong>
						</div>
					</div>
					<div class="col-lg-2 col-sm-2 text-left">
						<div class="checkbox">
							<form:checkbox path="mustHave" value="1" />
							<strong style="font-size: 13px;">Must Have</strong>
						</div>
					</div>
					<div class="col-lg-2 col-sm-2 text-left">
						<div class="checkbox">
							<form:checkbox path="rubberMould" value="1" />
							<strong style="font-size: 13px;">Rubber Mould</strong>
						</div>
					</div>
					<div class="col-lg-2 col-sm-2 text-center">
						<div class="checkbox">
							<form:checkbox path="bbc" value="1" />
							<strong style="font-size: 13px;">Blue,Black,Coco</strong>
						</div>
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
					<div class="col-lg-12 col-sm-12">
						<label for="inputLabel3" class="text-left" style="font-size: 13px;">Hold
							Remarks:</label>
						<form:input path="holdRemark" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="holdRemark" />
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
					<div class="col-lg-12 col-sm-12">
						<label for="inputLabel3" class="text-right" style="font-size: 13px;">Remarks:</label>
						<form:input path="remarks" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="remarks" />
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="form-group">
				<div class="col-lg-12">
					<input type="submit" value="Save" class="btn btn-primary" /> 
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="oldStyleNo" />
					<input type="hidden" name="invImg1" id="invImg1" value="${image1}" />
					<input type="hidden" name="invImg2" id="invImg2" value="${image2}" />
					<input type="hidden" name="invImg3" id="invImg3" value="${image3}" />
					<input type="hidden" name="defaultImage" id="defaultImage" value="${defImgg}" />
					<input type="hidden" name="defImgChk" id="defImgChk" value="${defChk}"/>
					<input type="hidden" name="waxWtConv" id="waxWtConv" value="${waxWtConv}"  />
					<input type="hidden" name="imgChk" id="imgChk" />
				</div>
			</div>
		</form:form>
	</div>


	<div id="rDivId" class="col-xs-3" style="display: none;">
		<div class="row">
			<div class="col-lg-12 col-sm-12">
				<label for="inputLabel3" class="control-label text-right" style="font-size: 13px;">Search by Style No:</label>
			</div>
			<div class="col-lg-12 col-sm-12">
				<input id="styleNoTmp" name="styleNoTmp" class="form-control" onblur="javascript:getStyleDt();" type="text">
			</div>
		</div>
		
		<div class="row">&nbsp;</div>

		<div class="panel panel-primary" style="width:100%; height:465px">
			<div class="panel-body">
				<div style="width:75%; height:265px">
					<div class="row center-block">
						<span id='styleImgId'></span>
							 <a id="dImgHRId" href="" data-lighter>
								<img id="designImg" class="img-responsive "
								src="/jewels/uploads/manufacturing/blank.png" />
							</a>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="pull-left">
					<table id='stoneDtlsId' style="width:100%"
						class="line-items editable table table-bordered">
					</table>
				</div>
			</div>
		</div>
		
		
	</div> 
 </div>

<div class="row">
	<div class="form-group" style="display: none;"></div>
</div>
<div class="form-group" id="dsPId" style="display: none;">
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Stone Details</span>
		</div>
	</div>
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
						data-click-to-select="false"
					    data-striped="false" 
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="380">
						<thead>
							<tr class="clickable">
								<th data-field="statechk" data-checkbox="true"></th>
								<th data-field="stoneType" data-sortable="false">Stone Type</th>
								<th data-field="shape" data-align="left" data-sortable="false">Shape</th>
								<th data-field="subShape" data-sortable="false">Sub Shape</th>
								<th data-field="quality" data-sortable="false">Quality</th>
								<th data-field="breadth" data-sortable="false">Breadth</th>
								<th data-field="mm" data-sortable="false">Size/MM</th>
								<th data-field="sieve" data-sortable="false">Sieve</th>
								<th data-field="sizeGroup" data-sortable="false">Size Group</th>
								<th data-field="stones" data-sortable="false">Stone</th>
								<th data-field="carat" data-sortable="false">Carat</th>
								<th data-field="setting" data-sortable="false">Setting</th>
								<th data-field="setType" data-sortable="false">Set Type</th>
								<th data-field="deactive" data-sortable="false">Status</th>
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

		<c:set var="option" value="Design Item" />
	</div>

	<div id="rowId">
		<div class="form-group">
			<span style="font-size: 2px;">&nbsp;</span>
			<table class="line-items editable table">
				<tr class="panel-heading">
					<th>&nbsp;&nbsp;Total Stone : <input type="text"
						id="vTotalStones" name="vTotalStones" value="${totalStones}"
						maxlength="7" size="7" disabled="true" /> &nbsp;&nbsp;Total Carat
						: <input type="text" id="vTotalCarats" name="vTotalCarats"
						value="${totalCarats}" maxlength="7" size="7" disabled="true" />
					</th>
				</tr>
			</table>

			<div id="rowOStnDtId">
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						
						
					 	$('#stoneDId').on('click', '.clickable-row', function(event) {
							//alert("XXXX");
							  $(this).addClass('bg-info').siblings().removeClass('bg-info');
							}); 
						
						
						
						$(".designForm")
								.validate(
										{
											rules : {
												designNo : {
													required : true,
													minlength : 3,
													remote : {
														url : "<spring:url value='/manufacturing/masters/design/designNo/Available.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id").val();
															}
														}
													}
												},
												styleNo : {
													required : true,
													minlength : 3,
 													remote : {
														url : "<spring:url value='/manufacturing/masters/design/styleNo/Available.html' />",
														type : "get",
														data : {
															version : function() {
																return $("#version").val();
															},
															id : function() {
																return $("#id").val();
															}
														}
													}
												},
												version : {
													required : true,
 													remote : {
														url : "<spring:url value='/manufacturing/masters/design/styleNo/Available.html' />",
														type : "get",
														data : {
															styleNo : function() {
																return $("#styleNo").val();
															},
															id : function() {
																return $("#id").val();
															}
														}
													}
												},
												'category.id' : {
													required : true,
												},
												'subCategory.id' : {
													required : true,
												},
												'concept.id' : {
													required : true,
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
												styleNo : {
													remote : "StyleNo plus Version already exists"
												},
												version : {
													remote : "StyleNo plus Version already exists"
												},
												designNo : {
													remote : "DesignNo already exists"
												}
											}
										});

						$("#designNo").autocomplete({
							source : "/jewels/manufacturing/masters/designNo/list.html",
							minLength : 2
						});
						
						$("#styleNoTmp").autocomplete({
							source : "/jewels/manufacturing/masters/styleNo/list.html",
							minLength : 2
						});
						
						
						

						$(document).ready(function(e) {
							$(".designStoneForm").validate(
							{
								rules : {
									'stoneType.id' : {
										required : true,
									},
									'shape.id' : {
										required : true,
									},
									size : {
										required : true,
									},
									stone : {
										required : true,
									},
									carat : {
										required : true,
									},
									
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
										remote : "Design already exists"
									}
								}
							});
						});

						$(".designComponentForm").validate({
							rules : {
								'component.id' : {
									required : true,
								},
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
							}
						});

						$("#designDt").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						popDesignStone();
						sumDStoneDtls();
						

						if ($('#defaultImage').val().trim().length > 0) {
							
								var imgName = $('#defaultImage').val();
								
								 $.ajax({
  									  url:'/jewels/uploads/manufacturing/design/'+ imgName,
  									  type:'HEAD',
   									 error: function()
								    {
								       // alert("in error");
								       $("#img1Lb").css("display", "block");
								       $("#img2Lb").css("display", "block");
								       $("#img3Lb").css("display", "block");
								    },
								    success: function()
								    {
								         
								        $('input#dfImage')[$('#defImgChk').val()-1].checked = true;
								       
								        $('#designImg').attr('src','/jewels/uploads/manufacturing/design/'+ imgName);
								        
								        $('#dImgHRId').attr('href','/jewels/uploads/manufacturing/design/'+ imgName);
								    }
								}); 
								

						}
						
						 if ($('#invImg1').val().indexOf('blank.png')) {
							$("#img1Lb").css("display", "block");
						}
						if ($('#invImg2').val().indexOf('blank.png')) {
							$("#img2Lb").css("display", "block");
						}
						if ($('#invImg3').val().indexOf('blank.png')) {
							$("#img3Lb").css("display", "block");
						} 
						
						

						$('input#dfImage').on(
								'change',
								function() {
									$('input#dfImage').not(this).prop('checked', false);
									var chkTmp = $('input[name="dfImage"]:checked').serialize();
									chkTmp = chkTmp.substring(chkTmp.indexOf('=')+1);
									
									var temp = "";
									
									if(chkTmp == 1){
										temp = $('#invImg1').val();
									}
									
									if(chkTmp == 2){
										temp = $('#invImg2').val();
									
									}
									
									if(chkTmp == 3){										
										temp = $('#invImg3').val();
										
									}
								
									$('#defaultImage').val(temp);
									$('#defImgChk').val(chkTmp);
								});

						if (window.location.href.indexOf('edit') != -1) {
							$("#mDivId").attr('class', 'col-xs-9');
							$("#rDivId").css('display', 'block');
							$("#dsPId").css('display', 'block');

							$("#"+ document.querySelector("#disableDesignComponent").id).attr("id", "designComponent");
							$("#"+ document.querySelector("#disableDesignPurityWeight").id).attr("id", "designPurityWeight");
							$("#"+ document.querySelector("#disableUploadImages").id).attr("id", "uploadImages");
							
				
							
						} else {
							$("#designDt").val(new Date().toLocaleDateString('en-GB'));
//							$("#version").val(0);
							$("#designNo").focus();
						}
					});

	$('#stoneDId')
		.bootstrapTable({}).on('click-row.bs.table',
			function(e, row, $element) {
				$("#rowOStnDtId").css('display','none');
				 //$(this).addClass('bg-info').siblings().removeClass('active');
			});

	function calSlvrWt() {
		if (Number($('#WaxWt').val()) > 0) {
			$('#silverWt').val(parseFloat($('#WaxWt').val() * 10.5).toFixed(3));
			$('#grms').val(parseFloat($('#WaxWt').val() * $('#waxWtConv').val()).toFixed(3));
		}
	}

	function calWaxWt() {
		if (Number($('#silverWt').val()) > 0) {
			$('#WaxWt').val(parseFloat($('#silverWt').val() / 10.5).toFixed(3));
			$('#grms').val(parseFloat($('#WaxWt').val() * $('#waxWtConv').val()).toFixed(3));
		}
	}

	function sumDStoneDtls() {
		$('#stoneDId')
				.bootstrapTable({})
				.on(
						'load-success.bs.table',
						function(e, data) {
							var data = JSON.stringify($("#stoneDId")
									.bootstrapTable('getData'));

							var cnt = 0;
							var htStones = new Object();
							var htCarat = new Object();

							var vStones = 0.0;
							var vCarat = 0.0;
							$.each(JSON.parse(data), function(idx, obj) {
								vStones += Number(obj.stones);
								vCarat += Number(obj.carat);

								if (htCarat.hasOwnProperty(obj.stoneType)) {
								} else {
									htCarat[obj.stoneType] = 0;
									htStones[obj.stoneType] = 0;
								}

								++cnt;
							});

							// show the values stored
							$
									.each(
											JSON.parse(data),
											function(idx, obj) {
												if (htCarat
														.hasOwnProperty(obj.stoneType)) {
													htCarat[obj.stoneType] = Number(htCarat[obj.stoneType])
															+ Number(obj.carat);
													htStones[obj.stoneType] = Number(htStones[obj.stoneType])
															+ Number(obj.stones);
												}
											});

							if (cnt > 0) {
								var str = '';
								str += '<tr style="font-size: 11px; width:100%;"><th>Stone Type</th><th class="text-right">Stones</th><th class="text-right">Carat</th></tr>';
								for ( var key in htCarat) {
									str += '<tr style="font-size: 11px"><td class="capitalise"><b>'
											+ key.toLowerCase()
											+ '</b></td><td class="text-right"><b>'
											+ htStones[key]
											+ '</b></td><td class="text-right"><b>'
											+ parseFloat(htCarat[key]).toFixed(
													3) + '</b></td></tr>';
								}
								$('#stoneDtlsId').html(str);
							}

							$('#vTotalStones').val(" " + vStones);
							$('#vTotalCarats').val(
									" " + parseFloat(vCarat).toFixed(3));
						});
	}

/* 	function designDtDelete(e, id, dt) {
		doDelete(e, "javascript:deleteDesignDt(" + id + ", " + dt + ");");
	}

	function deleteDesignDt(id, dt) {
		$("#modalRemove").modal("hide");

		var url = "/jewels/manufacturing/masters/design/delete/" + id;
		if (dt == 1) {
			url = "/jewels/manufacturing/masters/designStone/delete/" + id;
		} else if (dt == 2) {
			url = "/jewels/manufacturing/masters/designComponent/delete/" + id;
		}

		$.ajax({
			url : url + ".html",
			type : 'GET',
			success : function(data, textStatus, jqXHR) {
				if (dt == 1) {
					popDesignStone();
					$('form#designStone').trigger("reset");
				} else if (dt == 2) {
					popDesignComponent();
					$('form#designComponent').trigger("reset");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}
 */
	function goToSType() {
		$("#rowOStnDtId").css('display','block');
		$.ajax({
			url : "/jewels/manufacturing/masters/designStone/add.html",
			type : 'GET',
			success : function(data) {
				$("#dsId").val('');
				$("#rowOStnDtId").html(data);
				$('#stoneType\\.id').focus();
			}
		});
	}

	function getStyleDt() {
		var vStyleNo = $("#styleNoTmp").val();
		vStyleNo = vStyleNo.substring(0, vStyleNo.indexOf('['));
		var vVersion = $("#styleNoTmp").val();
		vVersion = vVersion.substring(vVersion.indexOf('[')+1);
		vVersion = vVersion.substring(0, vVersion.indexOf(']'));

		$.ajax({
			url : "<spring:url value='/manufacturing/masters/design/getStyleId.html' />",
			type : "get",
			data : {
				styleNo : vStyleNo,
				version : vVersion
			},
			success : function(data) {
				if (data != '-1') {
					window.location.href = '/jewels/manufacturing/masters/design/edit/'+data+'.html';
				}
			}
		});
	}

	var isEdit = false;
	function editDStone(dsId) {
		$("#rowOStnDtId").css('display','block');
		$.ajax({
			url : "/jewels/manufacturing/masters/designStone/edit/" + dsId
					+ ".html",
			type : 'GET',
			success : function(data) {
				$("#rowOStnDtId").html(data);

				$('#vSieve').val($('#sieve').val());
//				$('#vSizeGroupStr').val($('#sizeGroupStr').val());

				isEdit = true;
			}
		});
	}

	$(document).on('submit', 'form#designStone', function(e) {
		$('#dsStyleNo').val($('#styleNo').val());
		$('#dsVersion').val($('#version').val());
		$('#sieve').val($('#vSieve').val());
		$('#sizeGroupStr').val($('#vSizeGroupStr').val());

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
 				popDesignStone();
				sumDStoneDtls();
				$('form#designStone').trigger("reset");

				if (isEdit) {
					$("#rowOStnDtId").css('display','none');
					isEdit = false;
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});

	function getPointer() {
		$.ajax({
			url : '/jewels/manufacturing/masters/designStone/stonePointer.html',
			type : "GET",
			data : {shape : $("#shape\\.id :selected").text(), size : $("#size").val()} ,
			success : function(data, textStatus, jqXHR) {
				$("#carat").val(parseFloat(Number(data) * $("#stone").val()).toFixed(3));
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});		
	}


	$(document).on('submit', 'form#designComponent', function(e) {
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
				$('form#designComponent').trigger("reset");
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});

	function editDComponent(dsId) {
		$.ajax({
			url : "/jewels/manufacturing/masters/designComponent/edit/" + dsId
					+ ".html",
			type : 'GET',
			success : function(data) {
				$("#rowDCId").css('display', 'block');
				$("#rowDCId").html(data);
			}
		});
	}

	function goToComponent() {
		$.ajax({
			url : "/jewels/manufacturing/masters/designComponent/edit/0.html",
			type : 'GET',
			success : function(data) {
				$("#dcId").val('');

				$("#rowDCId").css('display', 'block');
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
	
	
	function popCancelBtn(){
		$('#designStone').css('display','none');
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

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js"
	type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet"
	type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
	

<c:set var="optionText" value="Delete" />
