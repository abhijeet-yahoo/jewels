<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<style>
.bacground{
bgcolor:#FF6347;
}

.mySelected{
    background-color: #FF6347;
}

</style>


<div class="panel panel-primary" style="width: 100%">

	<div class="panel-body">


		<div class="form-group">
			<form:form commandName="tranMt"
				cssClass="form-horizontal transferForm">

				
					<div class="form-group col-xs-12"> 
					<label class="col-sm-1 text-right">Department:</label>
					<div class="col-sm-3">
						<form:select path="department.id" class="form-control" onchange="javascript:popDisplay()">
							<form:option value="" label=" Select department " />
							<form:options items="${departmentMap}" />
						</form:select>
					</div>
				
				<label class="col-sm-1 text-right">All Client:</label>
				<div class="col-sm-1">
						<input type="checkbox" id="clientChk" name="clientChk">
					</div>
					
					<label class="col-sm-1 text-right">Set No:</label>
					<div class="col-sm-3">
						<input type="text" id="setNo" name="setNo" class="form-control">
					</div>
				</div>
				
		<!-- 		<div class="col-xs-3" align="right">
						<input type="search" id="searchSetting" class="search form-control" placeholder="Search" />
				</div> -->
				
				<input type="hidden" name="vBagNo" id="vBagNo" />
	
		
			
			</form:form>
		</div>


		<div class="panel panel panel-info col-xs-12">
			<div class="panel-body">
				<div class="form-group" id="dsPId">
					<div class="container-fluid">

						<div class="row flex">
							<div class="panel panel panel-default col-sm-10" style="padding-bottom: 25px">
								<div>
									<table id="fromTranMt" data-toggle="table"  data-search="true" data-toolbar="#toolbar"
								data-side-pagination="server"  data-maintain-selected="true"
								 data-pagination="true"	 data-height="350"  data-response-handler="responseHandler" data-checkbox-header="false">
										<thead>
											<tr class="btn-primary">
												<th data-field="state" data-checkbox="true">Select</th>
												<th data-field="party" data-sortable="true">Party</th>
												<th data-field="orderNo" data-sortable="true">OrderNo</th>
												<th data-field="bagNo" data-align="left" data-sortable="true">Bag No</th>
												<th data-field="pcs" data-align="left" data-sortable="true">Pcs</th>
												<th data-field="style" data-align="left" data-sortable="true">StyleNo</th>
												<th data-field="metalWt" data-align="left" data-sortable="true">GrossWt</th>
												<th data-field="totalStone" data-align="left" data-sortable="true">Stone</th>
												<th data-field="totalCarat" data-align="left" data-sortable="true">Carat</th>
												<th data-field="itemNo" data-align="left" data-sortable="true">ItemNo</th>
												<!-- <th data-field="orderDtNo" data-align="left" data-sortable="true">OrderDtNo</th> -->
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

		<div class="row">
			<div class="form-group">
				<div class="col-xs-12">
					<form:form commandName="tranMt" id="transferToCostingDt"
						action="/jewels/manufacturing/transactions/transfer/costing.html"
						cssClass="form-horizontal transferForm">

						<table class="table table-bordered">
							<tbody>
								<tr>
									<td class="col-xs-1"><input type="submit" value="Transfer" class="btn btn-primary" id="trfButtonId" /> 
									<form:input type="hidden" path="id" /> 
									<input type="hidden" name="pODIds" id="pODIds" /> 
									<input type="hidden" name="costingMtId" id="costingMtId" /> 
									<input type="hidden" name="vSetNo" id="vSetNo" /> 
									<td class="col-xs-1"><a class="btn btn-info" style="font-size: 15px" type="button" onClick="goToMainPage()">Back
									</a></td>
									<td class="col-xs-3"></td>
									</tr>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
		</div>


		<!-- 	metal details -->

		<div id="metalDivId" style="display: none;">
			<div class="row" id="tblMetalDivId">

				<div id="toolbarDtMetal">
					<label>Metal Details:</label>
				</div>


				<div class="table-responsive">
					<table id="tranMetalTblId" data-toggle="table"
						data-toolbar="#toolbarDtMetal" data-side-pagination="server"
						data-striped="true" data-height="100px">

						<thead>
							<tr class="btn-primary">
								<th data-field="partNm" data-sortable="false">Part</th>
								<th data-field="purity" data-sortable="false">Purity</th>
								<th data-field="color" data-align="left">Color</th>
								<th data-field="qty" data-sortable="false">Qty</th>
								<th data-field="metalWt" data-sortable="false">Metal Wt</th>
							</tr>
						</thead>

					</table>
				</div>



			</div>
		</div>

		<!-- Stone details -->

		<div id="stoneDivId" style="display: none;">

			<div class="row" id="tblStoneDivId">

				<div id="toolbarDt1">
					<label>Stone Details:</label>
				</div>


				<div class="table-responsive">
					<table id="stoneTblId" data-toggle="table"
						data-side-pagination="server" data-toolbar="#toolbarDt1"
						data-pagination="false" data-striped="true" data-height="550px">

						<thead>
							<tr class="btn-primary">
								<th data-field="partNm" data-sortable="false">Part</th>
								<th data-field="stoneType" data-sortable="true">StoneType</th>
								<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
								<th data-field="quality" data-align="left" data-sortable="true">Quality</th>
								<th data-field="size" data-align="left" data-sortable="true">Size</th>
								<th data-field="sieve" data-sortable="true">Sieve</th>
								<th data-field="sizeGroup" data-sortable="true">SizeGroup</th>
								<th data-field="stones" data-sortable="true">Stone</th>
								<th data-field="carat" data-sortable="true">Carat</th>
								<th data-field="setting" data-sortable="true">Setting</th>
								<th data-field="settingType" data-sortable="true">SettingType</th>
							</tr>
						</thead>
					</table>

				</div>
			</div>

		</div>



	</div> <!-- ending the panel body -->
</div> <!-- ending the main panel -->

<script type="text/javascript">

	var vOpt;
	var partyId;
	$(document).ready(
		function(e) {
			
			
			

	if (window.location.href.indexOf('MtId') != -1) {

					var abcx = window.location.href;
					var costMtId = abcx.substring(window.location.href
							.indexOf('MtId') + 5,window.location.href.indexOf('&mOpt'));
					$('#costingMtId').val(costMtId);
					
					vOpt = abcx.substring(window.location.href
							.indexOf('&mOpt') + 6,window.location.href.indexOf('&partyId'));
					
					partyId=abcx.substring(window.location.href
							.indexOf('&partyId') + 9);
					
					
				}
	
	
	
	$.ajax({
		url:"/jewels/manufacturing/transactions/costingDt/getMaxSetNo.html?mtId="+$('#costingMtId').val(),
		type:"GET",
		success:function(data){
			
			$('#setNo').val(data);
		}
	})


/* 	$("#searchSetting").on(
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
						
						
							
	$('#fromTranMt').on('check.bs.table', function (e, row,$element) {
	var	rowIndex=$element.attr('data-index');
	var dataId=row.id;
	 popCheckDiamondTolerance(row.bagNo,rowIndex,dataId);
		
	});

			});
	
	
	
	
	$('#clientChk').change(function() {
		 // alert($('#clientChk').prop('checked'))
		  popDisplay();
		})
	

	var dataIds=null;
	function  popCheckDiamondTolerance(bagNo,idx,dataId){
		$.ajax({
			url:"/jewels/manufacturing/transactions/costingDt/checkDiaTolerance.html?bagNo="+bagNo,
			type:"GET",
			success:function(data){
				
				if(data!=''){

				/* 	$("#fromTranMt").bootstrapTable('updateRow', {
						index : idx,
						row : {
							state : false,
							
						}
					}); */

					displayToreranceMsg(this, null,data+' <br> Press Yes For Allow and No For Not Allow');
					dataIds = dataId;
										

					
				//	alert(data);
					
					
				}
				
			}
		})
	
	}

	function popTorerance(value){

		if(value == 'no'){
			$("#fromTranMt").bootstrapTable('uncheckBy', {field: 'id', values: [dataIds]});
			}
		}

	
	function popDisplay() {

		$('#stoneDivId').css('display', 'none');
		$('#metalDivId').css('display', 'none');

		$("#fromTranMt")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/costingMtTransfer/listing.html?deptId="
									+ $('#department\\.id').val()+
									"&partyFlg="+$('#clientChk').prop('checked')+
									"&partyId="+partyId

						});

		
	}
	
	
	
	
	$('#fromTranMt').bootstrapTable({}).on('click-row.bs.table',

			function(e, row, $element) {

				var bgNm = jQuery.parseJSON(JSON.stringify(row)).bagNo;

				$('#vBagNo').val(bgNm);

				$('#stoneDivId').css('display', 'block');
				$('#metalDivId').css('display', 'block');

				popCostingTranDt();

			})


		
	$(document).on(
			'submit',
			'form#transferToCostingDt',
			function(e) {

				$('#trfButtonId').attr('disabled', 'disabled');

				$('#pODIds').val(itemselections);
				
				$('#vSetNo').val($('#setNo').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,

					success : function(data, textStatus, jqXHR) {
						itemselections=[];

						//$('#fromTranMt').bootstrapTable('uncheck-all.bs.table');

						//alert(data);

						if (data === '1') {
							
							
							displayInfoMsg(this, null,
									"Data transfered Sucessfully !");
							popDisplay();
						}

						$('#ordImgId').attr('src',
								'/jewels/uploads/manufacturing/blank.png');
						$('#oImgHRId').attr('href',
								'/jewels/uploads/manufacturing/blank.png');

						// $('#trfButtonId').removeAttr('disabled');

					},
					error : function(jqXHR, textStatus, errorThrown) {

						alert("please select atleast one record to transfer");
						alert("record not found");
					}

				});
				e.preventDefault();
			});

	$('#fromTranMt').bootstrapTable({}).on('load-success.bs.table',
			function(e, data) {

				$('#trfButtonId').removeAttr('disabled');

			});


	function goToMainPage() {
		
		if(Number(vOpt) == 0){
			window.location.href = "/jewels/manufacturing/transactions/costingMt/edit/"
				+ $('#costingMtId').val() + ".html";
		}else if(Number(vOpt) == 1){
			window.location.href = "/jewels/manufacturing/transactions/costingMtNew/edit/"
				+ $('#costingMtId').val() + ".html";
			
		}
			
		
	}
	
	

	function fetchLastNo(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/costing/itemNo.html' />',
					type : 'GET',
					success : function(data) {

						if (Number(vSel) == 1) {
							displayMsg(this, null, "Max Item No is = " + data);
						} else {
							$('#lastItemNo').val(data);
							vLastItemNo = $('#lastItemNo').val();
							afterLastNumber();
						}

					}
				});

	}

	//again checking the lastNo for concurency

	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt
					.indexOf("</strong>"));
		}

		return dt;
	}

	function generateItemNo() {

		var data = $('#fromTranMt').bootstrapTable('getSelections');
		var rows = $.map(data, function(item) {
			return item.row;
		});

		var orderDtNos = $.map(data, function(item) {
			return item.orderDtNo;
		});

		updateditemno();

		$('#pRows').val(rows);
		$('#pOrderDtNo').val(orderDtNos);

		fetchLastNo(2);

	}

	function updateditemno() {

		var data = $('#fromTranMt').bootstrapTable('getSelections');
		var itemNos = $.map(data, function(item) {
			return item.itemNo;
		});

		$('#pItemNo').val(itemNos);

		if ($('#pItemNo').val() != null) {
			vItemNo = $('#pItemNo').val().split(',');
			;
		} else {

			vItemNo = null;
		}

		// popSearch();

	}

	function afterLastNumber() {

		var vOrderDtNo = $('#pOrderDtNo').val().split(',');
		;
		var temprows = $('#pRows').val().split(',');
		var tempItemNo1 = vLastItemNo;
		var itemNumber = "";
		var tempItemNo = "";
		var tempString = "";
		var tempInt = "";
		var newItemNo = "";
		var tempOrderDtNo = "";

		for (i = 0; i < temprows.length; i++) {

			updateditemno();

			if (vItemNo[i] != "-") {
				continue;
			}

			if (!!vItemNo[i].trim()) {

				tempItemNo = tempItemNo1;
				tempString = tempItemNo.charAt(tempItemNo.length - 1);
				tempInt = tempItemNo.substring(0, tempItemNo.length - 1);

				if (tempString == 'Z') {
					tempInt = parseInt(tempInt) + 1;
					tempString = 'A';
					newItemNo = '0' + tempInt + tempString;
				} else {
					tempString = String
							.fromCharCode(tempString.charCodeAt(0) + 1);
					newItemNo = tempInt + tempString;
				}

				tempOrderDtNo = vOrderDtNo[i];

				var x = temprows[i] - 1;

				$("#fromTranMt").bootstrapTable('updateRow', {
					index : x,
					row : {
						state : true,
						itemNo : newItemNo,
					}
				});

				for (j = 0; j < temprows.length; j++) {

					updateditemno();

					if (!!vItemNo[j]) {
						var tempOrderDtNo2 = vOrderDtNo[j];

						if (Number(tempOrderDtNo2) == Number(tempOrderDtNo)) {
							var y = temprows[j] - 1;
							var sameItemNo = newItemNo;

							$("#fromTranMt").bootstrapTable('updateRow', {
								index : y,
								row : {
									state : true,
									itemNo : sameItemNo,
								}
							});

						}

					} // ending inside if

				} // ending inside for loop

				tempItemNo1 = newItemNo;

			} // ending 1  if condition

		} // ending the main foor loop

		//popSearch();

		updateInTable();

	}

	function updateInTable() {

		var data = $('#fromTranMt').bootstrapTable('getSelections');
		var uIds = $.map(data, function(item) {
			return item.bagId;
		});

		var uItemNos = $.map(data, function(item) {
			return item.itemNo;
		});

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/costing/updateItemNoInTable.html' />?Idd='
							+ uIds + "&ItemNo=" + uItemNos,
					type : 'GET',
					success : function(data) {

					}
				});
	}

	var stnTblRow = -1;
	$('#fromTranMt')
			.bootstrapTable({})
			.on(
					'click-row.bs.table',

					function(e, row, $element) {

						stnTblRow = $element.attr('data-index');
						$('#fldItemNum')
								.val(
										getNumVal(jQuery.parseJSON(JSON
												.stringify(row)).itemNo));
						$('#vBagMtId')
								.val(
										getNumVal(jQuery.parseJSON(JSON
												.stringify(row)).bagId));
					/* 	$('#fldItemNum').focus();
						$('#fldItemNum').select(); */

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

					});

	function manulUpdate() {
		var vNoTag = $('#fldItemNum').val().toUpperCase();
		if (vNoTag === 'NO TAG') {
			$("#fromTranMt").bootstrapTable('updateRow', {
				index : stnTblRow,
				row : {
					state : true,
					itemNo : vNoTag,
				}
			});

		} else {

			if (!!$('#fldItemNum').val().trim()
					&& $('#fldItemNum').val() === '-') {
			} else {
				checkItemNo();
			}
		}

		//popSearch();

	}

	function checkItemNo() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/costing/checkItemNo.html' />?ItemNo='
							+ $('#fldItemNum').val()
							+ "&bagId="
							+ $('#vBagMtId').val()
							+ "&deptId="
							+ $("#department\\.id").val(),
					type : 'GET',
					success : function(data) {

						if (data == 'false') {
							displayMsg(this, null, "duplicate !");
						} else {

							$("#fromTranMt").bootstrapTable('updateRow', {
								index : stnTblRow,
								row : {
									state : true,
									itemNo : $('#fldItemNum').val(),
								}
							});

							//popSearch();

						}

					}
				});

	}

/* 	function popSearch() {

		var value = $('#searchSetting').val();

		$("#fromTranMt tr").each(function(index) {

			if (index != 0) {
				$row = $(this);

				var id = $row.find('td:eq(4)').text();
				if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
					$(this).hide();
				} else {
					$(this).show();
				}
			}

		});

	} */

	$("#fromTranMt").on("change", ":checkbox", function() {

		
	});

	function popCostingTranDt() {

		$("#stoneTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/transfer/tranDt/listing.html?BagNo="
									+ $('#vBagNo').val()
									+ "&departmentId="
									+ $('#department\\.id').val(),
						});

		$("#tranMetalTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/transfer/tranMetal/listing.html?BagNo="
									+ $('#vBagNo').val()
									+ "&departmentId="
									+ $('#department\\.id').val(),
						});

	}
	
	
	
	 var $table = $('#fromTranMt')
	  var itemselections = []

	  function responseHandler(res) {
	    $.each(res.rows, function (i, row) {
	      row.state = $.inArray(row.id, itemselections) !== -1
	    })
	    return res
	  }

	  $(function() {
	    $table.on('check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table',
	      function (e, rowsAfter, rowsBefore) {
	    		    	
	        var rows = rowsAfter

	       if (e.type == 'uncheck-all') {
	          rows = rowsBefore
	        } 

	        var ids = $.map(!$.isArray(rows) ? [rows] : rows, function (row) {
	          return row.id
	        })

	        var func = $.inArray(e.type, ['check', 'check-all']) > -1 ? 'union' : 'difference'
	        itemselections = window._[func](itemselections, ids)
	      })
	  })
	  
	  
	  	$('#fromTranMt').bootstrapTable({}).on('load-success.bs.table',
		function(e, data) {
		
			var data = JSON.stringify($("#fromTranMt").bootstrapTable('getData'));
			
			$.each(JSON.parse(data), function(idx, obj) {

				if(Number(obj.totalCarat) == 0){
					$('#fromTranMt tr:eq('+Number(idx+1)+')').addClass('mySelected');	
				}
				 
				
			});
			
			
		});
	
	
</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/bootstrap/lodash.min.js' />"></script>
