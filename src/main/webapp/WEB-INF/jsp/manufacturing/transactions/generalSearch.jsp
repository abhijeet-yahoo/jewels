<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalOrderFilter.jsp"%>



<div class="panel panel-primary" style="width: 100%; " >
	<div class="panel-heading" >
		<span style="font-size: 18px;">Bag Search</span>
	</div>
	
	<div class="panel body">
	
	<div id="upperDiv">
	
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
	
	
	 <form:form commandName="bagMt" id="bagMtFormId"
	 		action="/jewels/maufacturing/generalSearch/loadData.html"
			cssClass="form-horizontal bagMtForm">
			
			
			 <div class="row">
				<div class="col-xs-12">	
					
						<%-- <label for="inputLabel3" class="col-sm-1 control-label">Client :</label>
						<div class="col-sm-2">
					   		<form:select path="orderMt.party.id"  class="form-control">
								<form:option value="" label="- Select Party -" />
								<form:options items="${partyMap}" />
							</form:select>
						</div> --%>
						
						
					<label for="inputLabel3" class="col-sm-1 control-label">Client :</label>
						<div class="col-sm-2">	
							<div class="input-group">
							<form:textarea path="orderMt.party.id"  cssClass="form-control" id="clientTextBox" readonly="true" />
								<form:errors path="orderMt.party.id" />
								 <span class="input-group-btn">
										<button type="button" class="btn btn-default glyphicon glyphicon-list" data-toggle="modal" data-target="#myClientModal" onclick="javascript:popClient(0)">
							         </span> 
							        </button>
								 </div>
						</div>
						
						
						<label for="inputLabel3" class="col-sm-1 control-label">OrderType:</label>
						<div class="col-sm-2">
					   		<form:select path="orderMt.orderType.id"  class="form-control">
								<form:option value="" label="- Select OrderType -" />
								<form:options items="${orderTypeMap}" />
							</form:select>
						</div>
						
						<label for="inputLabel3" class="col-sm-1 control-label">Order No :</label>
						<div class="col-sm-2">
					   		<form:input path="orderMt.invNo" cssClass="form-control" />
							<form:errors path="orderMt.invNo" />
						</div>
						
						<label for="inputLabel3" class="col-sm-1 control-label">Order Po :</label>
						<div class="col-sm-2">
					   		<form:input path="orderMt.refNo" cssClass="form-control" />
							<form:errors path="orderMt.refNo" />
						</div>
					
			
				</div>
			</div> 
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
	
			
			 <div class="row">
				<div class="col-xs-12">
				
				
					<label for="inputLabel3" class="col-sm-1 control-label">Style No :</label>
						<div class="col-sm-2">
					   		<form:input path="orderDt.design.styleNo" cssClass="form-control" />
							<form:errors path="orderDt.design.styleNo" />
						</div>
				
				
					<label for="inputLabel3" class="col-sm-1 control-label">KT :</label>
						<div class="col-sm-2">
					   		<form:select path="orderMt.purity.id"  class="form-control">
								<form:option value="" label="- Select Purity -" />
								<form:options items="${purityMap}" />
							</form:select>
						</div>
						
						
					<label for="inputLabel3" class="col-sm-1 control-label">Color :</label>
						<div class="col-sm-2">
					   		<form:select path="orderMt.color.id"  class="form-control">
								<form:option value="" label="- Select Color -" />
								<form:options items="${colorMap}" />
							</form:select>
						</div>
				
					
					<label for="inputLabel3" class="col-sm-1 control-label">Bag No :</label>
						<div class="col-sm-2">
					   		<form:input path="name" cssClass="form-control" />
							<form:errors path="name" />
						</div>
					
				
				
				</div>
			</div>
			
			
			
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
	
			
			 <div class="row">
				<div class="col-xs-12">
				
				<label for="inputLabel3" class="col-sm-1 control-label">Department:</label>
					<div class="col-sm-2">
				   		<form:select path="orderDt.design.modelMakerEmployee.department.id"  class="form-control">
							<form:option value="" label="- Select Department -" />
							<form:options items="${departmentMap}" />
						</form:select>
					</div>
				
				
						<label for="inputLabel3" class="col-sm-1 control-label">Ord Ref.:</label>
						<div class="col-sm-2">
						
						<input type="text" class="form-control" id="ordref">
					   		
						</div>
						
							<strong>Without Export : </strong><input type="checkbox" id="withoutExport" title="Without Export" value=0/> 
				
				</div>
			</div>
			
			
			
			<div class="form-group">
			
			<input type="hidden" name="timeValBagHistoryPdf" id="timeValBagHistoryPdf" />
						<input type="hidden" name="xml" id="xml" value="${xml}"  />
			
			</div>
			
			
			
			<div class="row">
				<div class="col-xs-12">
					
					<div class="col-sm-offset-5 col-sm-12">
						<a class="btn btn-success" style="font-size: 15px" type="button"
							onclick="javascript:loadData()"><span class="glyphicon glyphicon-download"></span>&nbsp;Load</a>
					</div>
					
					
					
					
				<!-- <input type="hidden" id="partyId" name="partyId" />
					<input type="hidden" id="orderTypeId" name="orderTypeId" />
					<input type="hidden" id="orderInvNo" name="orderInvNo" />
					<input type="hidden" id="orderRefNo" name="orderRefNo" />
					<input type="hidden" id="styleNo" name="styleNo" />
					<input type="hidden" id="purityId" name="purityId" />
					<input type="hidden" id="colorId" name="colorId" />
					<input type="hidden" id="bagName" name="bagName" />
					<input type="hidden" id="deptId" name="deptId" /> 
				-->
				
				</div>
			</div>
			
			
			
	
	</form:form> 
	
	
		<!-- Download BagHistory Pdf -->
  		
		<div style="display: none">		
				<form:form target="_blank"  id="bagHistoryForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="genBagHistoryRpt"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
	
	
	
	
	</div>
	
	
	
	
	 
	 
	
	<!-- <div>
		<input type="button" id="bagHisBtn" name="bagHisBtn" onclick="window.open('/jewels/manufacturing/transactions/bagHistory.html','popUpWindow','height=600,width=1000,left=140,top=10,,scrollbars=yes,menubar=no'); return false;" />
		
		
	</div> -->
	
	
	
	
	
	
	
	
	<div id="orderStatusTable">
  
  
  	<div class="col-xs-12">
		<hr style="width: 100%; color: red; height: 3px; background-color: skyblue;" />
    </div>
  
		<div class="container-fluid">
				<div class="row" id="forOrderStatusDetailTab">
					
					<div id="odDivId" class="col-xs-12">
					
					<div class="col-xs-2">
						<input type="search" id="searchOrderStatusFilterList"  class="search form-control" placeholder="Search" />
					</div>
					
							
					
						<div>
							<table id="orderStatusDetailTableId" data-toggle="table"
								data-toolbar="#toolbar" data-pagination="false"
								data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350" data-striped="true">
								<thead>
									<tr class="btn-primary">
										<th data-field="action1" data-align="left">Bag History</th>
										<th data-field="partyNm" data-align="left">Party</th>
										<th data-field="orderTypeNm" data-align="left"  >Order Type</th>
										<th data-field="orderNo" data-align="left">Order No</th>
										<th data-field="orderRefNo" data-align="left">OrdRef No</th>
										<th data-field="bagNm" data-align="left">Bag</th>
										<th data-field="styleNo" data-align="left">Style No</th>
										<th data-field="kt" data-align="left">Kt</th>
										<th data-field="color" data-align="left">Color</th>
										<th data-field="sizeNm" data-align="left">Size</th>
										<th data-field="ordPcs" data-align="right">Ord Pcs</th>
										<th data-field="bagPcs" data-align="right">Bag Pcs</th> 
										<th data-field="grossWt" data-align="right">Gross Wt</th>
										<th data-field="date" data-align="left">Date</th>
										<th data-field="departmentNm" data-align="right">Department</th>
										<th data-field="itemNo" data-align="left">Item No</th>
										<th data-field="dtRefNo" data-align="left">Ref No</th>
										<th data-field="dtOrdref" data-align="left">Ord Ref</th>
										<th data-field="expInv" data-align="left">Exp Inv</th>
										<th data-field="expDate" data-align="left">Exp Date</th>
										<th data-field="tagPrice" data-align="left">Tag Price</th>
										<th data-field="diaPcs" data-align="left">DiaPcs</th>
										<th data-field="diaWt" data-align="left">DiaWt</th>
										<th data-field="colPcs" data-align="left">ColPcs</th>
										<th data-field="colWt" data-align="left">ColWt</th>
										<th data-field="czPcs" data-align="left">CzPcs</th>
										<th data-field="czWt" data-align="left">CzWt</th>
										<th data-field="bagStatus" data-align="left">Bag Status</th>
										
									</tr>
								</thead>
							</table>
						</div>
	
	<div>
					<span style="display:inline-block; width: 19cm;"></span>
					Total Bags : <input type="text" id="vTotalCount" name="vTotalCount" 
									maxlength="7" size="7" disabled="true" /> 
						&nbsp;&nbsp;
					Total BagQty : <input type="text" id="vTotalBagPcs" name="vTotalBagPcs"   
									maxlength="7" size="7" disabled="true" />		
				</div>
					</div>
	
			</div>
		
		</div>
			
	</div>
	
	
	

	
	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>
	
	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>
		
		
	<div id="diamondBreakDiv">
	
		<div class="row">
			<div class="form-group">
				<div class="col-xs-12">
					<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Diamond Breakup</span>
				</div>
			</div>
		</div>





		<div class="form-group">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="diaBagBrkDtId" data-toggle="table"
								data-toolbar="#toolbarDt2" data-click-to-highlight="true"
							    data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
								<thead>
									<tr class = "btn-primary">
										<th data-field="stoneType">Stone Type</th>
										<th data-field="shape" data-align="left">Shape</th>
										<th data-field="subShape">Sub Shape</th>
										<th data-field="quality">Quality</th>
										<th data-field="mm" data-align="right">Size/MM</th>
										<th data-field="sieve" data-align="right">Sieve</th>
										<th data-field="sizeGroup">Size Group</th>
										<th data-field="stones" data-align="right">Stone</th>
										<th data-field="carat" data-align="right">Carat</th>
										<th data-field="rate" data-align="right">Rate</th>
										<th data-field="value" data-align="right">Value</th>
										<th data-field="setting">Setting</th>
										<th data-field="setType">Set Type</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			
			
			
				<div>
					<span style="display:inline-block; width: 19cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="true" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="true" />		
				</div>
			
			
		</div>
	
	
	
				
	
	
	
	
  
	
	
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
	
		<div class="row">
			<div class="form-group">
				<div class="col-xs-12">
					<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Component Breakup</span>
				</div>
			</div>
		</div>
	
	
	
		
		
		
		
		<div class="form-group">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="compBreakupId" data-toggle="table"
								data-toolbar="#toolbarDt3" data-click-to-highlight="true"
							    data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
								<thead>
									<tr class = "btn-primary">
										<th data-field="component">Component</th>
										<th data-field="purity" data-align="left">Purity</th>
										<th data-field="color">Color</th>
										<th data-field="metalWt">Metal Wt</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	
	

  </div>
	
	<div id="back-top" style="display: none"><a onclick="javascript:popScroll()">TOP</a></div>
	
	
	
	
		
	
	
	
	
	
	
	</div> <!-- ending the panel body -->
	
</div> <!-- ending the main panel -->



<script type="text/javascript">

	
	function popScroll(){
		$('html, body').animate({
			scrollTop: $("#upperDiv").offset().top
		}, 800);
	}
	
	
	$(document).ready(function(){
		
		//----search fucntionality-----//
		
		
		$("#searchOrderStatusFilterList").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#orderStatusDetailTableId tr").each(function(index) {
		    	
		        if (index != 0) {
		            $row = $(this);
		          
		            
		            var pId  = $row.find('td:eq(0)').text();
		            var oTId = $row.find('td:eq(1)').text();
		            var oId  = $row.find('td:eq(2)').text();
		            var bId  = $row.find('td:eq(4)').text();
		            var sId  = $row.find('td:eq(5)').text();
		            var dId  = $row.find('td:eq(12)').text();
		            
		            if (pId.toLowerCase().indexOf(value.toLowerCase()) != 0  && oTId.toLowerCase().indexOf(value.toLowerCase()) != 0  && oId.toLowerCase().indexOf(value.toLowerCase()) 
		            	&&	bId.toLowerCase().indexOf(value.toLowerCase()) && sId.toLowerCase().indexOf(value.toLowerCase()) && dId.toLowerCase().indexOf(value.toLowerCase())){
		                $(this).hide();
		            }else {
		                $(this).show();
		            }
		            
		        }
		    });
		});
		
		
		
	
		
		$("#orderDt\\.design\\.styleNo").autocomplete({
			source : "/jewels/manufacturing/masters/styleNo/list.html",
			minLength : 2
		});
		
		
		$("#name").autocomplete({
			source : "<spring:url value='/manufacturing/transactions/generalSearch/jobBag/list.html' />",
			minLength : 2
		});
		
		
		 $("#orderMt\\.invNo").autocomplete({
			source : "<spring:url value='/manufacturing/transactions/generalSearch/orderInv/list.html' />",
			minLength : 2
		});
		
		
		
	})


	
	
  function loadData(){

		data = $('#clientIdTbl').bootstrapTable('getSelections');
		partyIds = $.map(data, function(item) {
			return item.id;
		});
		  

		console.log('partyIds   '+partyIds)
		var exportFlg =$('#withoutExport').is(':checked');

		
		$('#diamondBreakDiv').css('display','none');
		
		$('html, body').animate({
			scrollTop: $("#orderStatusTable").offset().top
		}, 1000);
		
 	$("#orderStatusDetailTableId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/generalSearch/listing.html?pPartyId="+partyIds
								+"&pOrderTypeId="+$('#orderMt\\.orderType\\.id').val()
							    +"&pOrderNo="+$('#orderMt\\.invNo').val()
								+"&pOrderRefNo="+$('#orderMt\\.refNo').val()
								+"&pStyleNo="+$('#orderDt\\.design\\.styleNo').val()
								+"&pPurityId="+$('#orderMt\\.purity\\.id').val()
								+"&pColorId="+$('#orderMt\\.color\\.id').val()
								+"&pBagNm="+$('#name').val()
								+"&pDepartment="+$('#orderDt\\.design\\.modelMakerEmployee\\.department\\.id').val()
								+"&pOrdRef="+$('#ordref').val()
								+"&exportFlg="+exportFlg,
							
					});
 
		
		
	}
	
	
	$('#orderStatusDetailTableId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#orderStatusDetailTableId").bootstrapTable('getData'));

				var vCount = 0;
				var vBagQty = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					vCount		+= Number(1);
					vBagQty		+= Number(obj.bagPcs);
				});
				
				$('#vTotalCount').val(" " + vCount);
				$('#vTotalBagPcs').val(" " + vBagQty);
				
			});
	
	
	
	$('#orderStatusDetailTableId').bootstrapTable({})
			.on('dbl-click-row.bs.table',
					function(e, row, $element) {
					var tempBagId = jQuery.parseJSON(JSON.stringify(row)).bagId;
					loadDiamondBreakup(tempBagId);
					loadComponentBreakup(tempBagId);
					$('#back-top').css('display','block');
					
			});
	
	
	
	
	function loadDiamondBreakup(bagId){
		$('#diamondBreakDiv').css('display','block');
		$('html, body').animate({
			scrollTop: $("#diamondBreakDiv").offset().top
		}, 1000);
		
		
		//alert(bagId);
		
		 $("#diaBagBrkDtId")
			.bootstrapTable(
					'refresh',{
						url : "/jewels/manufacturing/transactions/generalSearch/diamondBreakup/listing.html?bagId="+ bagId,
							
					});
			
	 }
	
	
	
	$('#diaBagBrkDtId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#diaBagBrkDtId").bootstrapTable('getData'));

				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					vStones		+= Number(obj.stones);
					vCarat		+= Number(obj.carat);
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});
	
	


	
	
	//------------component-finding---------//
	
	
	
	function loadComponentBreakup(bagId){
		//$('#diamondBreakDiv').css('display','block');
		/* $('html, body').animate({
			scrollTop: $("#diamondBreakDiv").offset().top
		}, 1000); */
		
		
		 $("#compBreakupId")
			.bootstrapTable(
					'refresh',{
						url : "/jewels/manufacturing/transactions/generalSearch/compBreakup/listing.html?bagId="+ bagId,
							
					});
			
	 }
	
	
	
	function popBagHistory(){
		var tempBagNm='';

		 $('#orderStatusDetailTableId').bootstrapTable({}).on(
					'click-row.bs.table',
					function(e, row, $element) {
		
						tempBagNm = jQuery.parseJSON(JSON.stringify(row)).bagNm;
						
		

					})
					setTimeout(function(){
						
						$
						.ajax({
							url : "/jewels/manufacturing/transactions/bagHistory/report.html?bagNo="+tempBagNm+ "&xml="+ $('#xml').val(),
							type : 'POST',
							success : function(data, textStatus, jqXHR) {
								$('#timeValCommonPdf').val(data);
								$("#genBagHistoryRpt").trigger('click');
								
								
								
							}
						});
					}, 50);
	
		
	};
	


	//----------client Search-------//

	$("#searchClient")
			.on(
					"keyup",
					function() {
						var value = $(this).val();

						$("#clientIdTbl tr")
								.each(
										function(index) {

											if (index != 0) {

												$row = $(this);
												var id = $row
														.find(
																'td:eq(1)')
														.text();
												if (id
														.toLowerCase()
														.indexOf(
																value
																		.toLowerCase()) != 0) {
													$(this)
															.hide();
												} else {
													$(this)
															.show();
												}
											}
										});
					});
	
	
</script>





<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/topScroll.css" rel="stylesheet" type="text/css" />



