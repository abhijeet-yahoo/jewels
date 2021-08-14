<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalMultiComponentDeduction.jsp"%>





<div class="panel panel-primary" style="width: 100%">

	<div class="panel-body">


		<div class="form-group">
			<form:form commandName="tranMt"
				cssClass="form-horizontal multiComponentDeductionForm">

				
					<div class="form-group col-xs-7"> 
					<label class="col-sm-3 text-right">Department:</label>
					<div class="col-sm-4">
						<form:select path="department.id" class="form-control" onchange="javascript:popDisplay();">
							<form:option value="" label=" Select department " />
							<form:options items="${departmentMap}" />
						</form:select>
					</div>
				</div>
				
				<div class="col-xs-3" align="right">
						<input type="search" id="searchMultiCompDeduction" class="search form-control" placeholder="Search" />
				</div>
				
				<input type="hidden" name="vBagNo" id="vBagNo" />
				<input type="hidden" id="findingFlg" name="findingFlg" />
			
			</form:form>
	
		</div>

		<div class="panel panel panel-info col-xs-12">
			<div class="panel-body">
				<div class="form-group" id="dsPId">
					<div class="container-fluid">

						<div class="row flex">
							<div class="panel panel panel-default col-sm-10">
								<div>
									<table id="fromTranMt" data-toggle="table"
										data-toolbar="#toolbarDt" data-side-pagination="server"
										data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
										<thead>
											<tr class="btn-primary">
												<th data-field="state" data-checkbox="true">Select</th>
												<th data-field="row">Sr No</th>
												<th data-field="party" data-sortable="true">Party</th>
												<th data-field="orderNo" data-sortable="true">OrderNo</th>
												<th data-field="bagNo" data-align="left" data-sortable="true">Bag No</th>
												<th data-field="pcs" data-align="left" data-sortable="true">Pcs</th>
												<th data-field="style" data-align="left" data-sortable="true">StyleNo</th>
												<th data-field="metalWt" data-align="left" data-sortable="true">GrossWt</th>
												<th data-field="stone" data-align="left" data-sortable="true">Total Stone</th>
												<th data-field="carat" data-align="left" data-sortable="true">Total Carat</th>
												
											</tr>
										</thead>


									</table>
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
					
					
			$("#searchMultiCompDeduction").on(
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

								});

					});
	
		
		
		function popDisplay() {

			$("#fromTranMt")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/costingMtTransfer/listing.html?deptId="
								+ $('#department\\.id').val()

					});
			
			$('#fromTranMt').bootstrapTable({}).on('click-row.bs.table',

					function(e, row, $element) {

						/* var bgNm = jQuery.parseJSON(JSON.stringify(row)).bagNo;

						$('#vBagNo').val(bgNm); */
						
						var defImage = jQuery.parseJSON(JSON.stringify(row)).image;
						if ((defImage != 'null')
								&& ($.trim(defImage).length > 0)) {
							$('#ordImgId').attr('src',	'/jewels/uploads/manufacturing/design/'+ defImage);
							$('#oImgHRId').attr('href',	'/jewels/uploads/manufacturing/design/'+ defImage);
						}
					
					})

				}
		
		
			function popOpenMultiComp(multiComp) {
				
				if (Number($("#fromTranMt").bootstrapTable('getSelections').length) > 0) {
					
					var data = $('#fromTranMt').bootstrapTable('getSelections');
					var bagNo = $.map(data, function(item) {
						return item.bagNo;
					});

					$('#vBagNo').val(bagNo);
					
					if (multiComp === 1) {
						
						$('#multiComponentDeductionModal').modal('show');
						
						$('#vvBagNo').val($('#vBagNo').val());
						
						$('#findingFlg').val('true');
						$('#vFindingFlg').val($('#findingFlg').val());
						
						getChargeableList(true);
						
					} else if (multiComp === 0) {
						$('#multiComponentDeductionModal').modal('show');
						
						$('#vvBagNo').val($('#vBagNo').val());
						
						$('#findingFlg').val('false');
						$('#vFindingFlg').val($('#findingFlg').val());
						
						getChargeableList(false);
					}
					
				} else {
					displayMsg(this, null,"Record Not Selected");
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
				
		
	
		</script>
		
		<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />