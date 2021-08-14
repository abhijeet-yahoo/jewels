<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalMultiComponentAddition.jsp"%>





<div class="panel panel-primary" style="width: 100%">

	<div class="panel-body">


		<div id="toolbar" class="form-group">
			<form:form commandName="tranMt"
				cssClass="form-horizontal multiComponentAdditionForm">
					
					<div class="col-xs-4">
						
						<label class="control-label" for="trandate">TranDate</label>
								<form:input path="trandate" cssClass="form-control"  autocomplete="off"/>
								<form:errors path="trandate" />
						
						</div>
						
						
						<div class="col-xs-8">
						
						<label class="control-label" for="dept">Department</label>
						<form:select path="department.id" id="currentDeptId" class="form-control" onchange="javascript:popDisplayMultiCompList()">
							<form:option value="" label=" Select department " />
							<form:options items="${departmentMap}" />
						</form:select>
						
						</div>
						
	
			</form:form>
		</div>
		
		<div class="panel panel panel-info col-xs-12">
			<div class="panel-body">
				<div class="form-group" id="dsPId">
					<div class="container-fluid">

						<div class="row flex">
							<div class="panel panel panel-default col-sm-10">
								<div>
									<table id="fromTranMt" data-toggle="table" data-toolbar="#toolbar"
										data-search="true"  data-maintain-meta-data="true" data-pagination="true"
										data-height="450">
										<thead>
											<tr class="btn-primary">
												<th data-field="state" data-checkbox="true">Select</th>
												<th data-field="row">Sr No</th>
												<th data-field="party" data-sortable="true">Party</th>
												<th data-field="orderNo" data-sortable="true">OrderNo</th>
												<th data-field="bagNo" data-align="left"
													data-sortable="true">Bag No</th>
												<th data-field="pcs" data-align="left" data-sortable="true">Pcs</th>
												<th data-field="style" data-align="left"
													data-sortable="true">StyleNo</th>
												<th data-field="metalWt" data-align="left"
													data-sortable="true">GrossWt</th>
												
												
											</tr>
										</thead>


									</table>
								</div>
								
								
										 <div align="right" style="padding-right: 10px; font-weight: bold;">
 				
				Selected Bags : <input type="text" id="vSelectBags" name="vSelectBags" 	maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
						&nbsp;&nbsp;
				Selected Qty : <input type="text" id="vSelectQty" name="vSelectQty"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
							&nbsp;&nbsp;
						
 				 </div>
								
							</div>

							<!-- image -->

							<div id="odImgDivId" class="col-sm-2 center-block">
								<div class="panel panel-default" style="height: 120px">
									<div class="panel-body">
										<div style="width: 150px; height: 50px">
											<a id="oImgHRId"
												href="/jewels/uploads/manufacturing/blank.png" data-lighter>
												<img id="ordImgId" class="img-responsive"
												style="height: 100px"
												src="/jewels/uploads/manufacturing/blank.png" />
											</a>
										</div>
									</div>
								</div>

							</div>



						</div>
					</div>
				</div>

			</div>
		</div>
	
			
		<div class="col-sm-6">
					<a class="btn btn-info" style="font-size: 15px" type="button"
						onclick="javascript:popOpenMultiComp(1);"> <span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Findings
					</a>
					
					<a class="btn btn-info" style="font-size: 15px" type="button"
						onclick="javascript:popOpenMultiComp(0);"> <span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Accessories
					</a>
				</div>
		</div>
		</div>
		
		
		
		
		
		<script type="text/javascript">
		
		
		$(document).ready(
				function(e) {
					
					$("#trandate").datepicker({
						dateFormat : 'dd/mm/yy'
					});
				
					$("#trandate").val('${currentDate}');
					
					
					if('${canEditTranddate}' === "false"){
						$("#trandate").attr("disabled","disabled");
					}
					
					
			/* $("#searchMultiComp").on(
								"keyup",
								function() {
									var value = $(this).val();

									$("#fromTranMt tr").each(
											function(index) {

												if (index != 0) {
													$row = $(this);

													var id = $row.find('td:eq(4)')
															.text();
													if (id.toLowerCase().indexOf(
															value.toLowerCase()) != 0) {
														$(this).hide();
													} else {
														$(this).show();
													}
												}

											});

								}); */

					});
	
		
		
		function popDisplayMultiCompList() {
			
		
			
			$("#fromTranMt")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/multiComponentAddition/deptWiseBag/listing.html?deptId="
										+ $('#currentDeptId').val()

							});
		
			
		}

	
		$('#fromTranMt').bootstrapTable({}).on('click-row.bs.table',

				function(e, row, $element) {
					var defImage = jQuery.parseJSON(JSON.stringify(row)).image;
			
			if ((defImage != 'null')
									&& ($.trim(defImage).length > 0)) {
								$('#ordImgId').attr(
										'src',
										'/jewels/uploads/manufacturing/design/'
												+ defImage);
								$('#oImgHRId').attr(
										'href',
										'/jewels/uploads/manufacturing/design/'
												+ defImage);
							}

						})

			

function popOpenMultiComp(multiComp) {


	if (Number($("#fromTranMt").bootstrapTable('getAllSelections').length) > 0) {
		
		var data = $('#fromTranMt').bootstrapTable('getAllSelections');
		var bagNo = $.map(data, function(item) {
			return item.bagNo;
		});
		
		
		var vQty=0;
		
		var data1 =JSON.stringify($('#fromTranMt').bootstrapTable('getAllSelections'));
		
		$.each(JSON.parse(data1), function(idx, obj) {
			
			vQty += Number(obj.pcs);
			
			
			
		});
		

		$('#vTotQty').val(vQty);
		$('#vvBagNo').val(bagNo);
		$('#vPresentDept').val($('#currentDeptId').val());
		
		
		var validator = $( "#multiComponentAdditionFormId" ).validate();
		validator.resetForm();
		
			if (multiComp === 1) {
				
						$('#multiComponentModal').modal('show');
						$('#vFindingFlg').val('true');
						$('#vTranDate').val($('#trandate').val());
						
						getChargeableList(true);
						
					
			} else if (multiComp === 0) {
						$('#multiComponentModal').modal('show');
						$('#vFindingFlg').val('false');
						$('#vTranDate').val($('#trandate').val());
						
						getChargeableList(false);
					}

					

				} else {
					displayMsg(this, null, "Record Not Selected");
				}

			};
			
			
			function getChargeableList(chList){

				$
						.ajax({
							url : '<spring:url value='/manufacturing/transactions/compAddition/chargeableList.html' />?chargeable='
									+ chList,
							type : 'GET',
							success : function(data) {
								$("#compDivId").html(data);
							}
						});

			}
			
			
			
			
		/* 	$(document).ready(
					function(e) {
						$("#multiComponentAdditionFormId").validate(
								{
									
									rules : {
										
										'component.id' : {
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
											greaterThan : "0,0",
										},
										pcs : {
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
										metalWt : {
											greaterThan : "This field is required"
										}
									},

								});

					});
			 */
			
			
			

			
	
			
			
			
			 $("#multiComponentModal").on('hide.bs.modal', function(){
				 
				$('#currentDeptId').trigger('onchange');
				
				});
			
			 
			 
			 
			 $('#fromTranMt').on('check.bs.table', function (e, row) {
					
				 popSelectedQty();
				
			});



			$('#fromTranMt').on('check-all.bs.table', function (e, row) {
				
				 popSelectedQty();
				
			});


			$('#fromTranMt').on('uncheck.bs.table', function (e, row) {
				
				 popSelectedQty();
				
				
			});



			$('#fromTranMt').on('uncheck-all.bs.table', function (e, row) {
				
				 popSelectedQty();
				
			});



		



			function popSelectedQty(){
				
				
				var data = $('#fromTranMt').bootstrapTable('getAllSelections');
								
				var vBagPcs = 0.0;
				var vWt = 0.0;
				var i=0;
				$.each(data, function(idx, obj) {
					i =i+1;
					vBagPcs		+= Number(obj.pcs);
					
				});
				
				$('#vSelectBags').val(i);
				$('#vSelectQty').val(Number(vBagPcs).toFixed(2));
				
				
				
			}
			

			


			$('#fromTranMt').bootstrapTable({}).on(
					'load-success.bs.table',
					function(e, data) {

						$('#vSelectBags').val(0);
						$('#vSelectQty').val(0);
						
						
					});

	
		</script>
		<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

		<script src="/jewels/js/jquery/jquery-ui.min.js"></script>
		
		<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
		<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />