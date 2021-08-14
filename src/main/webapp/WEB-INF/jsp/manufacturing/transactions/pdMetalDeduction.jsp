<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">PD Metal Deduction</span>
	</div>

	<div class="panel-body">
		<div class="col-xs-10">

			<form:form commandName="pdcTranMt"
				action="/jewels/manufacturing/transactions/pdMetalDeduction/add.html"
				cssClass="form-horizontal tranMtForm">
		
				<div class="row">
					<div class="col-xs-12">
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Style
								No :</label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Date
								:</label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Qty
								:</label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Rec
								Wt :</label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right" >Present Department:</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
							<div class="col-sm-2">
							<form:input path="design.styleNo" cssClass="form-control"
								onblur="javascript:popDetails();popMetalDeductionTable();" />
							<form:errors path="design.styleNo" />
						</div>
						<div class="col-sm-2">
							<form:input path="createdDate" cssClass="form-control"
								disabled="true" />
							<form:errors path="createdDate" />
						</div>
						<div class="col-sm-2">
							<form:input path="pcs" cssClass="form-control" disabled="true" />
							<form:errors path="pcs" />
						</div>
					      <div class="col-sm-2">
							<form:input path="recWt" cssClass="form-control" disabled="true" />
							<form:errors path="recWt" />
						</div>
							<div class="col-sm-2" >
							<form:input path="department.name" cssClass="form-control" style="background-color: white;color:red;font-weight: bold;" disabled="true" />
							<form:errors path="department.name" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

			</form:form>
		</div>

		<div class="col-xs-2 center-block">
			<div class="panel panel-primary" style="width: 193px; height: 150px">
				<div class="panel-body">
					<div style="width: 160px; height: 142px">
						<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png"
							data-lighter> <img id="ordImgId" class="img-responsive"
							src="/jewels/uploads/manufacturing/blank.png" />
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- ending the main panel -->


<div class="panel panel-primary" style="width: 100%">

	<div class="panel-heading" >
		<span style="font-size: 18px;">Detail</span>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div id="toolbar">
					<a class="btn btn-info" style="font-size: 15px" type="button"
						onclick="javascript:goToMetalDeductionEntry();"> <span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add
					</a>
				</div>
				<div>
					<table id="metalDeductionTableId" data-toggle="table"
						data-toolbar="#toolbar" data-pagination="false"
						data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
						data-striped="true">
						<thead>
							<tr class="btn-primary">
								<th data-field="department" data-sortable="true">Department</th>
								<th data-field="styleNo" data-sortable="true">Style No</th>
								<th data-field="purity" data-sortable="true">purity</th>
								<th data-field="color" data-sortable="true">Color</th>
								<th data-field="metalWt" data-sortable="true">Fresh MetalWt</th>
								<th data-field="usedMetalWt" data-sortable="true">Used MetalWt</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>


	<div id="mDeductionEntry" style="display: none">
		<div id="rowId">
			<div class="form-group">
				<div class="form-group">
					<form:form commandName="metalTran" id="metalDeductionEntry"
						action="/jewels/manufacturing/transactions/pdMetalDeduction/add.html"
						cssClass="form-horizontal metalDeductionEntryForm">

						<table class="line-items editable table table-bordered">
							<thead class="panel-heading">
								<tr class="btn-warning" class="panel-heading">
									<th>Location</th>
									<th>Purity</th>
									<th>Color</th>
									<th>Fresh Metal Wt</th>
									<th>Used Metal Wt</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div id="locationId">
											<form:select path="department.id" class="form-control">
												<form:option value="" label="- Select Location -" />
												<form:options items="${departmentMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="purityId">
											<form:select path="purity.id" class="form-control">
												<form:option value="" label="- Select Purity -" />
												<form:options items="${purityMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="colorId">
											<form:select path="color.id" class="form-control">
												<form:option value="" label="- Select Color -" />
												<form:options items="${colorMap}" />
											</form:select>
										</div>
									</td>
									<td><form:input path="metalWt" id="recWtId" cssClass="form-control" /></td>
									<td>
										<input type="text" id="vUMetalWt" name="vUMetalWt" class="form-control"  />
									</td>
								</tr>
								<tr>
									<td colspan="10">
										<input type="submit" value="Save" class="btn btn-primary" id="metDeductionBtn" />
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMetalDedCancelBtn()" /> 
										<form:input type="hidden" path="id" />
										<input type="hidden" name="vStyleNo" id="vStyleNo" />
										<input type="hidden" name="vLocationId" id="vLocationId" /> 
										<input type="hidden" name="vPurityId" id="vPurityId" /> 
										<input type="hidden" name="vColorId" id="vColorId" /> 
										<input type="hidden" name="vWeight" id="vWeight" />
										<input type="hidden" id="UMetalWtt" name="UMetalWtt" />
										<input type="hidden" name="vPresentDept" id="vPresentDept" />
									</td>
								</tr>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(
			function(e) {
				$(".tranMtForm").validate({

					rules : {
						'design.styleNo' : {
							remote : {
								url :"<spring:url value='/manufacturing/transactions/pdMetalDeduction/styleNoAvailable.html' />",
								type : "get",
								data : {
									
								}
							}
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
						'design.styleNo' : {
							remote : "StyleNo not found on floor"
						}
					}

						});
		
				$("#design\\.styleNo")
					.autocomplete(
					{
						source : "/jewels/manufacturing/masters/styleNo/list.html",
						minLength : 2
					});

			});
	
	$(document).ready(
			function(e) {
				$(".metalDeductionEntryForm").validate(
						{
							rules : {
								'department.id' : {
									required : true,
								},
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},
								
								metalWt : {
									required : true,
								},
								
								vUMetalWt : {
									required : true,
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
							

						});
				$('#vUMetalWt').val('0.0');
			});

	function popDetails() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/pdMetalDeduction/displayBreakup.html' />?styleNo='
							+ $('#design\\.styleNo').val(),
					type : 'GET',
					success : function(data) {
						//alert(data);
						if(Number(data) != -1){			
							var temp = data.split("#");
							$('#design\\.styleNo').val(temp[0]);
							$('#createdDate').val(temp[1]);
							$('#pcs').val(temp[2]);
							$('#recWt').val(temp[3]);							
							$('#department\\.name').val(temp[4]);
							$('#ordImgId').attr('src', temp[5]);
							$('#oImgHRId').attr('href', temp[5]);
							$('#purityId').html(temp[6]);
						}else{
							//alert("No Such bag present in tranMt");
							
						/* 	$('#design\\.styleNo').val('');
							$('#date').val('');
							$('#recWt').val('0.0');
							$('#pcs').val('0.0');
							$('#department\\.name').val(''); */
							
						}
						
						
					}
				});

	}

	
	$(document)
			.on(
					'submit',
					'form#metalDeductionEntry',
					function(e) {
				
						 $('#metDeductionBtn').attr('disabled', 'disabled');

						var vvStyleNo = $('#design\\.styleNo').val();
						$('#vStyleNo').val(vvStyleNo);
					

						var vvLocationId = $('#department\\.id').val();
						$('#vLocationId').val(vvLocationId);
				

					    var vvPurityId = $('#purity\\.id').val();
						$('#vPurityId').val(vvPurityId);

						var vvColorId = $('#color\\.id').val();
						$('#vColorId').val(vvColorId);

						var vvWeight =  $('#recWtId').val();
						$('#vWeight').val(vvWeight);
						
						
						var vUsedMetalWt = $('#vUMetalWt').val();
						$('#UMetalWtt').val(vUsedMetalWt);
						

						var vvPresentDept = $('#department\\.name').val();
						$('#vPresentDept').val(vvPresentDept);
						

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
										
										//alert(data); 

										 if (data == -1) {
											displayMsg(event, this,'System error :- contact the developer team');
										}else if (Number(data == -2)) {
											displayMsg(event, this,'Receive Wt is Less');
										}else if (Number(data) == -3) {
											displayMsg(event, this , 'Fresh Metal Wt or Used Metal Wt is Compulsary!');
										}else {
											popDetails();
											popMetalDeductionTable();
											$('form#metalDeductionEntry input[type="text"],textarea, select').val('');
											$('form#metalDeductionEntry select').val('');
											
											$('#recWtId').val('0.0');
											$('#vUMetalWt').val('0.0');
											
											$("#mDeductionEntry").css('display', 'none');
										}
										
										$('#metDeductionBtn').removeAttr('disabled');

									},

									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						e.preventDefault(); //STOP default action
					});

	function popMetalDeductionTable() {

		$("#metalDeductionTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/pdMetalDeduction/listing.html?StyleNo="
									+ $('#design\\.styleNo').val()
						});
	}

	function goToMetalDeductionEntry() {
		
		if (! $('#design\\.styleNo').val()) {
			displayMsg(this, null, 'please select the StyleNo!');
		}else{
				$("#mDeductionEntry").css('display', 'block');
				$("#department\\.id").focus();
				

			}	
		}

	function popMetalDedCancelBtn(){
		$("#mDeductionEntry").css('display', 'none');
	}
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />