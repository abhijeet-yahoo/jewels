<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>




<div class="panel-body">

<!----------------------costingDt------------------------ -->
		
	<div id="openCostItemEdit" style="display: block" >
	
	<div id="toolbarCostDtItem">
			
									<div id="mergeCostDivId" class="col-sm-2" style="display: block">
										<a class="btn btn-info" type="button"  onClick="popMergeCosting()" >&nbsp;Merge Costing
										</a>
									</div>
									<!----------------------------------------- ApplyRate Button ------------------------------>
		
							
									
		 
						<div class="col-sm-1" align="center">
							<input type="button" value="Apply Rate" class="btn btn-primary" style="color: lime;" onclick="javascript:applyCostItemRate()"/>
						</div>
						
						<div class="col-sm-1" align="center">
							<input type="button" value="005 Dia Wt" class="btn btn-primary" style="color: lime;" onclick="javascript:apply005TagWt()"/>
						</div>
						
						<div class="col-sm-1" align="center">
							<input type="button" value="Auto Tag Wt" class="btn btn-primary" style="color: lime;" onclick="javascript:autoApplyTagWt()"/>
						</div>
		
						<div class="col-sm-2" align="right">
							<input type="button" value="As Per Req Cts" class="btn btn-primary" style="color: lime;" onclick="javascript:updateTagWtReqCts()"/>
						</div>
						
							<div  class="col-sm-1" align="right">
							<input type="button" value="Update Barcode" class="btn btn-primary" style="color: lime;" onclick="javascript:popUpdateBarcodeBtn()"/>
							</div>
							
						<!-- <div  class="col-sm-2" align="right">
							<input type="button" value="Style Not Match" class="btn btn-primary" style="color: lime;" onclick="javascript:popStyleNotMatchBtn()"/>
							</div>	 -->	
			
</div>									
	
				<div class="table-responsive">
							<table  id="costDtItemTabId" 
								data-toggle="table" data-height="400" data-search="true"
								data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
								<thead>
									<tr>
										<th data-field="stateRd" data-radio="true"></th>  
										<th data-field="action1" data-align="center">Edit</th>
										<!-- <th data-field="action2" data-align="center">Delete</th> -->
										<th data-field="srNo">Sr. No.</th>
										<th data-field="itemNo">Barcode</th>
										<!-- <th data-field="party" data-sortable="false">Client</th> -->
										<th data-field="ordNo" data-sortable="false">Order No</th>
										<th data-field="style" data-sortable="false" class="span5">Style No</th>
										<th data-field="purity" data-sortable="false">Kt</th>
										<th data-field="color" data-sortable="false">Color</th>
										<th data-field="prodSize" data-sortable="false">Size</th>
										
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false">Gross Wt</th>
										<th data-field="netWt" data-sortable="false"  data-formatter="netWtFormatter">Net Wt</th>
										<th data-field="actGrossWt" data-sortable="false">ActGross Wt</th>
										<th data-field="actNetWt" data-sortable="false">ActNet Wt</th>
										
										<th data-field="addInNetWt" data-sortable="false">Added Net Wt</th>
										<th data-field="addWtPerc" data-sortable="false">Add%</th>
										
										<th data-field="lossPercDt" data-sortable="false">Loss %</th>
										<!-- <th data-field="metalRate" data-sortable="false">Metal Rate</th> -->
										<th data-field="metalValue" data-sortable="false">Metal Value</th>
										<th data-field="stoneValue" data-sortable="false">Stn Value</th>
										<th data-field="compValue" data-sortable="false">Comp Value</th>
										<th data-field="labourValue" data-sortable="false">Lab Value</th>
										<th data-field="setValue" data-sortable="false">Set Value</th>
										<th data-field="handlingCost" data-sortable="false">Hdlg Cost</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="other" data-sortable="false">Other</th>
										<th data-field="dispPercentDt" data-sortable="false">Disp%</th>
										<th data-field="perPcDiscAmount" data-sortable="false">Per Pc Disc Amt</th>
										<th data-field="discAmount" data-sortable="false">Disc Amt</th>
										<th data-field="finalPrice" data-sortable="false">Final Price</th>
										<th data-field="perPcFinalPrice" data-sortable="false">Per Pc Final Price</th>
										<th data-field="reqCts" data-sortable="false">Req. Cts.</th>
										<th data-field="setNo" data-sortable="false">Set No.</th>
										<th data-field="ordRefNo" data-sortable="false">Ref No</th>
										<th data-field="rLock" data-sortable="false"data-formatter="inputFormatter">Lock</th>  
										<th data-field="actionLock" data-align="center"></th>
										
									</tr>
								</thead>
							</table>
								<input type="hidden" id="costingDtItemPKId" name="costingDtItemPKId" />
						</div>
						
						
	
		
						
						
	<!-- //entry of costingDt Item -->					
						
	<div id="entryCostDtItemId" style="display: none">	
		<div id="costingDtItemRowId"> </div>
  	</div>	
						
						
					
		
	
						
	<!-- //hide on page load start here -->
	<div id="hideOnPageLoadItem" style="display: none" >		
			<!------------------------------------------ CostMetal  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="costMetalItemTableId" data-toggle="table" 
							data-unique-id="id" >
						<thead>
							<tr class="btn-primary">
								<th data-field="partNm" data-sortable="false">PartNm</th>
								<th data-field="purity" data-sortable="false">Purity</th>
								<th data-field="color" data-align="left">Color</th>
								<th data-field="qty" data-sortable="false">Qty</th>
								<th data-field="metalWt" data-sortable="false">Metal Wt</th>
								<th data-field="actMetalWt" data-sortable="false">ActMetal Wt</th>
								<th data-field="metalRate" data-sortable="false">Metal Rate</th>
								<th data-field="perGramRate" data-sortable="false">Per Gram Rate</th>
								<!-- <th data-field="lossPerc" data-sortable="false" data-formatter="lossPercFormatterItem">Loss %</th> -->
								<th data-field="lossPerc" data-formatter="lossPercFormatter">Loss %</th>
								<th data-field="metalValue" data-sortable="false">Metal Value</th>
								<th data-field="mainMetal"  data-formatter="mianMetalFormatter">Main Metal</th>
							</tr>
						</thead>
					</table>
				</div>
			
			
			<!------------------------------------------ costStnDt Item -------------------------------------->
		
			<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Stone Details</span>
				</div>			
			</div>
		</div>

		
	
			
											
						
					<div >
						<table class="table-responsive"  id="costStnDtItemTabId"
							data-toggle="table" 
							data-click-to-select="true"
							>
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<!-- <th data-field="action2" data-align="center">Delete</th> -->
									<th data-field="partNm" data-sortable="true">Part Name</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
									<th data-field="quality" data-sortable="false">      Quality</th>
									<th data-field="size" data-sortable="false">Size</th>
									<th data-field="sieve" data-sortable="false">Sieve</th>
									<th data-field="sizeGroup" data-sortable="false">SizeGroup</th>
									<th data-field="stone" data-sortable="false">Stone</th>
									<th data-field="carat" data-sortable="false">Carat</th>
									<th data-field="perPcStone" data-sortable="false">PerPc Stone</th>
									<th data-field="perPcCarat" data-sortable="false">PerPc Carat</th>
									<th data-field="tagWt" data-sortable="false">Tag Wt</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="stoneValue" data-sortable="false">Stn Value</th>
									<th data-field="handlingRate" data-sortable="false">Hdlg Rate</th>
									<th data-field="handlingValue" data-sortable="false">Hdlg Value</th>
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SetType</th>
									<th data-field="settingRate" data-sortable="false">Set Rate</th>
									<th data-field="settingValue" data-sortable="false">Set Value</th>
									<th data-field="centerStone" data-sortable="false" data-formatter="inputFormatter">Center Stone</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center" >Lock-Unlock</th>
									<th data-field="actStone" data-sortable="false">Act Stn</th>
									<th data-field="actCarat" data-sortable="false">Act Cts</th>
									
								</tr>
							</thead>
						</table>
					</div>
						
						<input type="hidden" id="costingStoneDtItemPKId" name="costingStoneDtItemPKId" />
						
				
		
			
						<div>
					Total Stone : <input type="text" id="vTotalStonesItem" name="vTotalStonesItem" value="${totalStones}" size="3cm" disabled="disabled" /> 
						&nbsp;
					Total Carat : <input type="text" id="vTotalCaratsItem" name="vTotalCaratsItem" value="${totalCarats}" size="3cm" disabled="disabled" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					Stone : <input type="text" id="vPcStonesItem" name="vPcStonesItem"  size="3cm" disabled="disabled" /> 
						&nbsp;
					Carat : <input type="text" id="vPcCaratsItem" name="vPcCaratsItem"  size="3cm" disabled="disabled" />
					
					&nbsp;
					Tag Wt : <input type="text" id="vTagWt" name="vTagWt"  size="3cm"  onchange="javascript:manualTagWtUpdate()"/>
							
				</div>
				
				
			<!-- entry for costStnDt Item -->
	  <div id="entryCostStnDtItem" style="display: none">	
		<div id="costStnDtItemRowId">	
		
		
		
		
		
		
	  </div>
	</div>	
	
	
	
	<!------------------------------------------ costCompDt Item -------------------------------------->
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		
		<div class="row">
			<div class="form-group">
				<div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Component Details</span>
				</div>			
			</div>
		</div>
		
			<div id="toolbarCompDt" style="padding-bottom: 2.50px;padding-top: 2.50px">
			<!-- <a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostCompDtItem(0)">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Component</a> -->
						
						
			</div>
					
		
					<div >
						<table  id="costCompDtItemTabId"
							data-toggle="table"  data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<!-- <th data-field="action2" data-align="center">Delete</th> -->
									<th data-field="compName" data-sortable="true">     Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metal Wt</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="compPcs" data-sortable="false">Qty</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="metalRate" data-sortable="false">Metal Rate</th>
									<th data-field="perGramMetalRate" data-sortable="false">Per GramMetal Rate</th>
									<th data-field="metalValue" data-sortable="false">Metal Value</th>
									<th data-field="perGramRate" data-sortable="false" data-formatter="inputFormatter">Per Gram Rate</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
			<input type="hidden" id="costCompDtItemPk" name="costCompDtItemPk" />
			
			
			
			<!----- entry form (costCompDt) ------->
			  <div id="entryCostCompDtItem" style="display: none">
				<div id="costCompItemRowId">
					
					
					
					
				</div>
			</div>
			
			
		
		<!-----------  costLabDt ---------->
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		<div class="row">
		<div class="form-group">
		 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Labour Details</span>
				</div>
		</div>
		</div>
		
				 <div id="toolbarLabDtItem">
						<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addCostLabDtItem()">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour
						</a>
						
					</div> 
			 
						
				
					<div>
						<table id="costLabDtItemTabId" data-toolbar="#toolbarLabDtItem"
							data-toggle="table" 
							data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="labourType" data-sortable="true">LabourType</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="perPcs" data-sortable="false" data-formatter="inputFormatter">Per Pcs</th>
									<th data-field="perGram" data-sortable="false" data-formatter="inputFormatter">Per Gram</th>
									<th data-field="percent" data-sortable="false" data-formatter="inputFormatter">Percentage</th>
									<th data-field="perCaratRate" data-sortable="false" data-formatter="inputFormatter">Per Carat Rate</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
				
			
			
		<!-- entry form (costLabDt) -->
		
		<div id="entryCostLabDtItem" style="display: none">
			<div id="costLabItemRowId">
			</div> 
		</div>
				
			
			
			
						
	</div>


	</div>
</div>




<script type="text/javascript">

$(document)
.ready(
		function(e) {
			
		
				$('#mergeCostId').on('click', function() {
						 popCostingDtItemDetails();
						
				
				});
			
				//costingDt
				
				$(".costingDtEnttForm")
					.validate(
					 {	
					  rules : {
							'party.id' : {
								required : true,
							},
							grossWt : {
								required : true,
								greaterThan : "0,0",
							},
							other : {
								required : true,
								greaterThan : "0,0",
							},
			
						},
			
						highlight : function(element) {
							$(element).closest(
									'.form-group')
									.removeClass(
											'has-success')
									.addClass('has-error');
						},
						unhighlight : function(element) {
							$(element)
									.closest('.form-group')
									.removeClass(
											'has-error')
									.addClass('has-success');
						},
						
						messages : {
							metalWt : {
								greaterThan : "This field is required"
							},
						
							other : {
								greaterThan : "This field is required"
							}
						},	
			
					});

			
			
			
			
			
		});

			

function netWtFormatter(value, row, index){
	
var tempId = 'netWtval' + Number(index);

	var vId = row.id;
    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onblur="javascript:disableNetWt()" onchange="javascript:updateNetWt(this,'+vId+')" disabled />';
}


	
	var costDtTableRowItem;
	var costDtLockStatusItem = 'null';
	$('#costDtItemTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				costDtTableRowItem = $element.attr('data-index');
				
				$('#entryCostDtItemId').css('display','none');
				$('#costingDtItemPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var defImage = jQuery.parseJSON(JSON.stringify(row)).image;
				costDtLockStatusItem = jQuery.parseJSON(JSON.stringify(row)).rLock;
				
				if ((defImage != 'null') && ($.trim(defImage).length > 0)) {
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+defImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+defImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
					
				}
				
							
				$('#hideOnPageLoadItem').css('display','block');

				 popCostingMetalItemDetails();
				 popCostStnDtItemDetails();
			   	 popCostingCompItemDetails(); 
			   	popCostingLabItemDetails();
				
			})
	
			
				$('#costDtItemTabId').bootstrapTable({}).on(
			'dbl-click-cell.bs.table',
			function(e, field, value, row, $element,index) {
				
				
				if(field==='netWt'){
					
					if(jQuery.parseJSON(JSON.stringify(row)).rLock==='true'){
/* 						 $('#netWtval'+costDtTableRowItem).attr('disabled', 'disabled'); */
						 displayMsg(this, null, ' Cannot Edit Record Is Lock');
					 }else{
						 $('#netWtval'+costDtTableRowItem).removeAttr('disabled');
						 $('#netWtval'+costDtTableRowItem).focus();
					 }
					
					
				}
				
				
			
				
				 
			
			
			})
			
			
			
					function updateNetWt(param, val){
			$.ajax({
				url : "/jewels/manufacturing/transactions/costingDtItem/updateNetWt.html?id="+val+"&netWt="+ param.value,
				type : 'GET',
				success : function(data) {
					
						popCostingMetalItemDetails();
						updateCostingDtItemTable($('#costingDtItemPKId').val());
									
						
				
				}

			});
		}
	
	
	function disableNetWt(){
		
		$('#netWtval'+costDtTableRowItem).attr('disabled', 'disabled');
	}
	
	function popCostingDtItemDetails(){

		$("#costDtItemTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/costingDtItem/listing.html?pInvNo="
									+ $('#invNo').val()
						});
	}
	
	
	//----------------------Metal--------//------------->

	
	function popCostingMetalItemDetails(){
		
	
		$("#costMetalItemTableId")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/costMetalDtItem/listing.html?costDtId="
							+$('#costingDtItemPKId').val()
				});

		
	}
	
	

	

	
	//----------------------costLabDt--------//------------->
	function popCostingLabItemDetails(){
	$("#costLabDtItemTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costLabDtItem/listing.html?costDtId="
						+$('#costingDtItemPKId').val()
			});
		
	}
	
	
	function editcostingDtItem(pkCostDtId){
				
		$.ajax({
			url : "/jewels/manufacturing/transactions/costingDtItem/validation.html?dtId="+pkCostDtId,
			type : 'GET',
			success : function(data) {
				
				if(data === "-1"){
					displayMsg(this, null, 'Cannot edit,Record is Locked');	
				}else if(data === '-2'){
					displayMsg(this, null, 'Export is Closed');
				}else{
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/costingDtItem/edit/"+ pkCostDtId + ".html",
						type : 'GET',
						success : function(data) {
							 $("#costingDtItemRowId").html(data);
							 $("#entryCostDtItemId").css('display' , 'block');
							 $("#costingDtItemEnt #grossWt").focus();
						}
					});
					
				}
				
				
			}
		});
		
	
	}
		
		function popCostDtItemSave(){
			$.ajax({
				url : '<spring:url value='/manufacturing/transactions/costdtItem/saveEdit.html'/>?costDtItemId='+$('#costingDtItemPKId').val()
				 				+"&perPcDiscAmount=" + $('#costingDtItemEnt #perPcDiscAmount').val(),
				type : 'GET',
				success : function(data) {
					if(data === '-1'){
						//popCostingDtItemDetails();
						
							updateCostingDtItemTable($('#costingDtItemPKId').val());
										
					popCostingMetalItemDetails();
					popCostStnDtItemDetails();
					popCostingCompItemDetails(); 
					popCostingLabItemDetails();
						$("#entryCostDtItemId").css('display','none');
					//	$('#hideOnPageLoadItem').css('display','none');
					}else{
						displayMsg(this, null, ' Error ,Contact Admin ');
					}

				}
			});
			
		}
		
		
	
		
		
		
		
		
		
		
		
		function deletecostingDtItem(e,pkCostDtId){
			 if(costDtLockStatusItem === "true" ){
				 displayMsg(this, null, ' Cannot Delete Record Is Lock');
			 }else{
			
				displayDlg(
						e,
						'javascript:deleteCostDtItemData('+pkCostDtId+');',
						'Delete',
						'Do you want to Delete  ?',
						'Continue');
			 }
			
		}
		
		
		function deleteCostDtItemData(costDtPk){
			
			$("#modalDialog").modal("hide");
		
			$.ajax({
				
				url:'/jewels/manufacturing/transactions/costingDtItem/delete/'+costDtPk+'.html',
				data: 'GET',
				success : function(data){
					
					if(data === '-2'){
					   displayMsg(this, null, 'Export is Closed,Cannot Delete');	
					}else{
						 popCostingDtItemDetails();
						/*  popCostingMetalItemDetails();
						 popCostStnDtItemDetails();
					   	 popCostingCompItemDetails(); 
					   	popCostingLabItemDetails(); */
						$('#hideOnPageLoadItem').css('display','none');
					}
					
				}
				
			})
			
			
		}

	
	
	function popMergeCosting(){
		
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
		
		 $.ajax({
				url : "/jewels/manufacturing/transactions/costingDtItem/mergeCosting.html?pInvNo="+ $('#invNo').val(),
				type : 'GET',
				success : function(data) {
					
					if(data === '-1'){
						displayMsg(this,null,"Error Can Not Club");
					}			
					popCostingDtItemDetails();
					
					$('#hideOnPageLoadItem').css('display','none');
					$.unblockUI();
					
				}
			}); 
	}
	
	
	
	//----------------------costStnDt--------//------------->	
	
	function popCostStnDtItemDetails(){
		$("#costStnDtItemTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costStnDtItem/listing.html?costDtId="+ $('#costingDtItemPKId').val()
			});
		
	}
	
	
	var costStnLockStatusItem = 'null';
	$('#costStnDtItemTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				$('#costingStoneDtItemPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				costStnLockStatusItem = jQuery.parseJSON(JSON.stringify(row)).rLock;
				 $("#entryCostStnDtItem").css('display' , 'none');
				 
			})
	
	
	
	/* coststn Item Edit */
	
	
	function editCostStnDtItem(pkCostStnDtId){
		
		 if(costDtLockStatusItem === 'true'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
				$.ajax({
					url : "/jewels/manufacturing/transactions/costStnDtItem/validationEdit.html?stnId="+pkCostStnDtId,
					type : 'GET',
					success : function(data) {
						
						
						if(data === "-1"){
							displayMsg(this, null, 'Cannot edit,Record is Locked');	
						}else if(data === '-2'){
							displayMsg(this, null, 'Export is Closed');
						}else{
						
					//		$('#costStnDtPk').val(pkCostStnDtId);
							$.ajax({
								url : "/jewels/manufacturing/transactions/costStnDtItem/edit/"+ pkCostStnDtId + ".html",
								type : 'GET',
								success : function(data) {
									 $("#costStnDtItemRowId").html(data);
									 $("#entryCostStnDtItem").css('display' , 'block');
									 $("#setting\\.id").focus();
								}
							});
						}
						
					}
				});
				
		 }
	}
	
	
	function saveCostStnDtItem(){
	
		 $
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/costStnDtItem/saveEdit.html' />?costStnDtId='+
				 			$('#costingStoneDtItemPKId').val()
				 			+"&settingId=" + $('#settingItemId').val()
				 			+"&setTypeId=" + $('#settingTypeItemId').val()
				 			+"&stoneRate=" + $('#stnRateItem').val()
				 			+"&handRate=" + $('#handlingRateItem').val()
				 			+"&setRate=" + $('#setRateItem').val(),
				type : 'GET',
				success : function(data) {
						
					/* 	$('form#costStnDtItemEnt select').val('');
						$('#handlingRateItem').val('');
						$('#setRateItem').val(''); */
						$("#entryCostStnDtItem").css('display' , 'none');
						popCostStnDtItemDetails();
						updateCostingDtItemTable($('#costingDtItemPKId').val());
						
						if(data === '-2'){
							$("#entryCostStnDtItem").css('display' , 'none');	
						}
					}

				
			});
	}
	
	
	function popStoneRateFromMasterItem(){
		
		if(!!$('#settingItemId').val() && !! $('#settingTypeItemId').val()){
	
			  $.ajax({
				  url:"/jewels/manufacturing/transactions/costStnDtItem/rateFromMaster.html?setItemId="+$('#settingItemId').val()
						  +"&setTypeItemId="+$('#settingTypeItemId').val()
						  +"&costStnDtItemId="+$('#costingStoneDtItemPKId').val(),
				  type:'GET',
				  success : function(data) {
					 
						 $('#setRateItem').val(data);
					
				 }
				  
			  });
		
		}else{
			
			displayMsg(this, null, ' setting or setting type is not selected');
			
		}
		
	}
	
	
	function popHandlingRate(){
		
		if($('#stnRateItem').val() >0){
			
			  $.ajax({
				  url:"/jewels/manufacturing/transactions/costStnDtItem/handlingRateFromMaster.html?stnRate="+$('#stnRateItem').val()
						    +"&costStnDtItemId="+$('#costingStoneDtItemPKId').val(),
				  type:'GET',
				  success : function(data) {
					 
						 $('#handlingRateItem').val(data);
					
				 }
				  
			  });
		
		}
		
	}
	
	
	
	function deleteCostStnDtItem(e,costStnDtId){
		
		 if(costDtLockStatusItem == 'true'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(costStnLockStatusItem == 'true'){
			 displayMsg(this, null, ' Cannot Delete Record is Lock');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteCostStnDtDataItem('+costStnDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
			 }
			
		}
	
	
	
	function deleteCostStnDtDataItem(costStnDtPk){ 
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/costStnDtItem/delete/'+costStnDtPk+'.html',
			data: 'GET',
			success : function(data){
						
				if(data === '-2'){
					displayMsg(this, null, 'Export is Closed');
				}else{
					
					popCostStnDtItemDetails();
					updateCostingDtItemTable($('#costingDtItemPKId').val());
						
				}
					
		  }

			
		})
		
		
	}
	
	function doStoneDtLockUnLockItem(stnDtIdPk){
		
		 if(costDtLockStatusItem == 'true'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		 
			 $.ajax({
					url : "/jewels/manufacturing/transactions/costStnDtItem/lockUnlock.html?stnDtId="+stnDtIdPk,
					type : 'GET',
					success : function(data) {
						if(data === '-1'){
							popCostStnDtItemDetails();
						}else if(data === '-2'){
							displayMsg(this, null, 'Export is Closed');
						}
					}
				});
			 
		 }
	}
	
	
	$('#costStnDtItemTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#costStnDtItemTabId").bootstrapTable('getData'));
				
				var vStones = 0.0;
				var vCarat = 0.0;
				
				var vPcStones = 0.0;
				var vPcCarat = 0.0;
				var vTag=0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
					
					vPcStones		+= Number(obj.perPcStone);
					vPcCarat		+= Number(obj.perPcCarat);
					
					vTag += Number(obj.tagWt);
					
				});
				
				$('#vTotalStonesItem').val(" " + vStones);
				$('#vTotalCaratsItem').val(" " + parseFloat(vCarat).toFixed(3));
				
				$('#vPcStonesItem').val(" " + vPcStones);
				$('#vPcCaratsItem').val(" " + parseFloat(vPcCarat).toFixed(3));
				
				$('#vTagWt').val(" " + parseFloat(vTag).toFixed(3));
				
			});
	
	
	
	// CostCompDt Item
	
		
	function popCostingCompItemDetails(){
			
		$("#costCompDtItemTabId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costCompDtItem/listing.html?costDtId="
						+$('#costingDtItemPKId').val()
			});
		
	}
	
	
	

	var costCompLockStatusItem = 'null';
	$('#costCompDtItemTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				/* $('form#costCompDtItemEnt select').val('');
				$('#compMetalWtItem').val('0.0');
				$('#compRateItem').val('0.0');
				$('#compLockItem').attr('checked', false); */ 
				$('#entryCostCompDtItem').css('display','none');
				costCompLockStatusItem = jQuery.parseJSON(JSON.stringify(row)).rLock;
				$('#costCompDtItemPk').val(jQuery.parseJSON(JSON.stringify(row)).id);
			})
	
	
	$(document)
		.on(
			'submit',
			'form#costCompDtItemEnt',
			 function(e){
				
				$('#forCompCostMtItemId').val($('#costMtId').val());
				$('#forCompCostDtItemId').val($('#costingDtItemPKId').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {
						
						popCostingCompItemDetails();
						updateCostingDtItemTable($('#costingDtItemPKId').val());
						
				/* 		$('form#costCompDtEnt select').val('');
						$('#compMetalWtItem').val('0.0');
						$('#compRateItem').val('0.0');
						$('#compLockItem').attr('checked', false); 
						$('#costCompDtItemPk').val(''); */
						
						if(data === '-2'){
							$('#entryCostCompDtItem').css('display','none');
						}
					
					},
					
					error : function(data, textStatus, jqXHR){
						alert("ERROR");
					}
						
				})
				
				e.preventDefault();
		
	})		
	
	
	
	
	function addCostCompDtItem(pkCompDtId){
				
		$.ajax({
			url : "/jewels/manufacturing/transactions/costCompDtItem/edit/"+ pkCompDtId + ".html?costMtId="+$('#costMtId').val(),
			type : 'GET',
			success : function(data) {
				$("#costCompItemRowId").html(data);
				$('#entryCostCompDtItem').css('display','block');
				$('#componentItemId').focus();
			}
		});
		
	}
	
	function editCostCompDtItem(pkCompDtId){
		
		 if(costDtLockStatusItem == 'Lock'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else{
		
		
			$.ajax({
				url : "/jewels/manufacturing/transactions/costCompDtItem/validationEdit.html?compId="+pkCompDtId,
				type : 'GET',
				success : function(data) {
					
					if(data === "-1"){
						displayMsg(this, null, 'Cannot edit,Record is Locked');	
					}else if(data === "-2"){
						displayMsg(this, null, 'Export is Closed');
					}else{
						
						$.ajax({
							url : "/jewels/manufacturing/transactions/costCompDtItem/edit/"+ pkCompDtId + ".html?costMtId="+$('#costMtId').val(),
							type : 'GET',
							success : function(data) {
								$("#costCompItemRowId").html(data);
								$('#entryCostCompDtItem').css('display','block');
								$('#componentItemId').focus();
							}
						});
					}
					    
				}
			});
		
		 }
	}
	
	
	function deleteCostCompDtItem(e,costCompDtId){
		
		 if(costDtLockStatusItem == 'true'){
			 displayMsg(this, null, ' Main Item Is Lock');
		 }else if(costCompLockStatusItem == 'true'){
			 displayMsg(this, null, ' Record is Locked Cannot Delete ! ');
		 }else{
		
			displayDlg(
					e,
					'javascript:deleteCostCompDtDataItem('+costCompDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		 }
		
	}
	
function deleteCostCompDtDataItem(costCompDtPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/costCompDtItem/delete/'+costCompDtPk+'.html',
			data: 'GET',
			success : function(data){
				
				if(data === '-2'){
					displayMsg(this, null, 'Export is Closed');
				}else{
									
					popCostingCompItemDetails();
					updateCostingDtItemTable($('#costingDtItemPKId').val());
						
				}
				
				
			}
			
		})
		
	}
	
				
				
			var costLabLockStatusItem = 'null';
			$('#costLabDtItemTabId').bootstrapTable({}).on(
					'click-row.bs.table',
					function(e, row, $element) {
						$('#entryCostLabDtItem').css('display','none'); 
						costLabLockStatusItem = jQuery.parseJSON(JSON.stringify(row)).rLock;
					})
			
					
			$(document)
			.on(
				'submit',
				'form#costLabDtItemEnt',
				 function(e){
					
					$('#forLabCostMtItemId').val($('#costMtId').val());
					$('#forLabCostDtItemId').val($('#costingDtItemPKId').val());
					
					var postData = $(this).serializeArray();
					var formURL = $(this).attr("action");
					
					$.ajax({
						url : formURL,
						type : "POST",
						data : postData,
						success : function(data, textStatus, jqXHR) {
							
							
							if(data === "-11"){
								displayMsg(this, null, 'please select only one check box');	
							}else if(data === "-12"){
								displayMsg(this, null, 'please check the entry');	
							}else{
							
							popCostingLabItemDetails();
							updateCostingDtItemTable($('#costingDtItemPKId').val());
							
							$('form#costLabDtItemEnt select').val('');
							$('#costLabDtItemEnt #labourRate').val('0.0')
							
							$('#costLabDtItemEnt #pcsWise').attr('checked', false); 
							$('#costLabDtItemEnt #gramWise').attr('checked', false); 
							$('#costLabDtItemEnt #percentWise').attr('checked', false); 
							$('#costLabDtItemEnt #perCaratRate').attr('checked', false);
							$('#costLabDtItemEnt #rLock').attr('checked', false);
							$('#costLabDtItemEnt #costLabDtItemPk').val('');
							
							if(data === '-2'){
								$('#entryCostLabDtItem').css('display','none');
							}
							
							}
						
						},
						
						error : function(data, textStatus, jqXHR){
							alert("ERROR");
						}
							
					})
					
					e.preventDefault();
			
			})		
				
			
			function addCostLabDtItem(){
				
				 if(costLabLockStatusItem == 'Lock'){
					 displayMsg(this, null, ' Main Item Is Lock');
				 }else{
				
					 var vId=0;
					 
					 $.ajax({
							url : "/jewels/manufacturing/transactions/costLabDtItem/edit/"+ vId + ".html",
							type : 'GET',
							success : function(data) {
								$("#costLabItemRowId").html(data);
								$('#entryCostLabDtItem').css('display','block');
								$('#metal\\.id').focus();
							}
						});
					 
						
					
						
						
				 }
			}				
			
			function editCostLabDtItem(pkLabDtId){
			
			 if(costLabLockStatusItem == 'Lock'){
				 displayMsg(this, null, ' Main Item Is Lock');
			 }else{
			
			
				$.ajax({
					url : "/jewels/manufacturing/transactions/costLabDtItem/validationEdit.html?labDtId="+pkLabDtId,
					type : 'GET',
					success : function(data) {
						
						if(data === "-1"){
							displayMsg(this, null, 'Cannot edit,Record is Locked');	
						}else if(data === "-2"){
							displayMsg(this, null, 'Export is Closed');
						}else{
							
							$.ajax({
								url : "/jewels/manufacturing/transactions/costLabDtItem/edit/"+ pkLabDtId + ".html",
								type : 'GET',
								success : function(data) {
									$("#costLabItemRowId").html(data);
									$('#entryCostLabDtItem').css('display','block');
									$('#metal\\.id').focus();
								}
							});
						}
						    
					}
				});
			
			 }
			}
			
			
			
			function deleteCostLabDtItem(e,costLabDtId){
				
				 if(costLabLockStatusItem == 'true'){
					 displayMsg(this, null, ' Main Item Is Lock');
				 }else if(costLabLockStatusItem == 'true'){
					 displayMsg(this, null, ' Record is Locked Cannot Delete ! ');
				 }else{
				
					displayDlg(
							e,
							'javascript:deleteCostLabDtDataItem('+costLabDtId+');',
							'Delete',
							'Do you want to Delete  ?',
							'Continue');
					
				 }
				
			}
			
			function deleteCostLabDtDataItem(costLabDtPk){
				
				$("#modalDialog").modal("hide");
				
				$.ajax({
					
					url:'/jewels/manufacturing/transactions/costLabDtItem/delete/'+costLabDtPk+'.html',
					data: 'GET',
					success : function(data){
						
						if(data === '-2'){
							displayMsg(this, null, 'Export is Closed');
						}else{
											
							popCostingLabItemDetails();
							updateCostingDtItemTable($('#costingDtItemPKId').val());
								
						}
						
						
					}
					
				})
				
			}
			
			/* Lab Dt Lock Unlock */
			
			function doLabDtLockUnLockItem(labDtIdPk){
				
				 if(costLabLockStatusItem == 'Lock'){
					 displayMsg(this, null, ' Main Item Is Lock');
				 }else{
				
					 $.ajax({
							url : "/jewels/manufacturing/transactions/costLabDtItem/lockUnlock.html?labDtId="+labDtIdPk,
							type : 'GET',
							success : function(data) {
								if(data === '-2'){
									displayMsg(this, null, 'Export is Closed');
								}else{
									popCostingLabItemDetails();
								}
							}
						});
				 }
			}

		
	
		function doCompDtLockUnLockItem(compDtIdPk){
			
			 if(costDtLockStatus == 'Lock'){
				 displayMsg(this, null, ' Main Item Is Lock');
			 }else{
			
				 $.ajax({
						url : "/jewels/manufacturing/transactions/costCompDtItem/lockUnlock.html?compDtId="+compDtIdPk,
						type : 'GET',
						success : function(data) {
							if(data === '-2'){
								displayMsg(this, null, 'Export is Closed');
							}else{
								popCostingCompItemDetails();
							}
						}
					});
			 }
		}
	
		
		function doCostDtLockUnLockItem(dtIdPk){
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/costingDtItem/lockUnlock.html?dtId="+dtIdPk,
				type : 'GET',
				success : function(data) {
					if(data == -1){
						
						updateCostingDtItemTable($('#costingDtItemPKId').val());
						
					//	popCostingDtItemDetails();
					popCostingMetalItemDetails();
					 popCostStnDtItemDetails();
					popCostingCompItemDetails(); 
					popCostingLabItemDetails();
						//$('#hideOnPageLoadItem').css('display','none');

					}else{
						displayMsg(this, null, 'Export Is Closed');
					}
				}
			});
		}
		
		
		function inputFormatter(value) {
			var booleanValue;
			if (typeof (value) === "boolean") {
				booleanValue = (value);
			} else {
				booleanValue = (value == 'true');
			}

			var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
			return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
		}
		
		
		
		
		var costMetalItemDtTableRow;
		var costMetalItemDtLockStatus = 'null';
		 $('#costMetalItemTableId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					costMetalItemDtTableRow = $element.attr('data-index');
					costMetalItemDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
					
				});
		 
		 
		 
			$('#costMetalItemTableId').bootstrapTable({}).on(
					'dbl-click-cell.bs.table',
					function(e, field, value, row, $element,index) {
						
						
						if(field==='lossPerc'){
							
							if(jQuery.parseJSON(JSON.stringify(row)).rLock==='true'){
								 displayMsg(this, null, ' Cannot Edit Record Is Lock');
							 }else{
								 $('#lossPercVal'+costMetalItemDtTableRow).removeAttr('disabled');
								 $('#lossPercVal'+costMetalItemDtTableRow).focus();
							 }
							
							
						}
						
						
					
						
						 
					
					
					})
	
		
		
	
		function lossPercFormatter(value, row, index){
			
			var tempId = 'lossPercVal' + Number(index);

				var vId = row.id;
			    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onblur="javascript:disableLossPerc()" onchange="javascript:updateTotLossPercItem(this,'+vId+')" disabled />';
			}
		
		
		function disableLossPerc(){
			
			$('#lossPercVal'+costMetalItemDtTableRow).attr('disabled', 'disabled');
		}

			
			
				

			
				function updateTotLossPercItem(param, val) {
					
					
			
					$
							.ajax({

					url : "/jewels/manufacturing/transactions/costingMt/updateLossPerc.html?id="
							+ val
							+"&lossPerc=" + param.value
							+"&costingType=merge",
					type : 'GET',
					success : function(data) {
						popCostingMetalItemDetails();
						updateCostingDtItemTable($('#costingDtItemPKId').val());
						
					}

				});
	}
	
	
				
				function updateCostingDtItemTable(dtId){
					
					$.ajax({
						url : "/jewels/manufacturing/transactions/costingDtItem/getData/"+dtId+".html",
						type : 'GET',
						dataType : 'JSON',
						success : function(data) {
												
							$('#costDtItemTabId').bootstrapTable('updateRow', {
								index : Number(costDtTableRowItem),
								row : data
							});
							
							
						}
					});
				}
				
	
				
				function manualTagWtUpdate(){
					
					
					$.ajax({
						
						url:"/jewels/manufacturing/transactions/costStnDtItem/manualTagUpdate.html?costDtItemId="
							+$('#costingDtItemPKId').val()+'&vTagWt='+$('#vTagWt').val(),
						type:'GET',
						success:function(data){
							if(data =='1'){
								popCostStnDtItemDetails();
								popCostingMetalItemDetails();
								updateCostingDtItemTable($('#costingDtItemPKId').val());
								
							}else{
								displayMsg(this, null, 'Cannot Update Tag Wt ,Contact Admin');	
							}
						}
						
						
					})
					
					
					
					
				}
				
				
			function updateTagWtReqCts(){
				 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
				
				$.ajax({
					
					url: "/jewels/manufacturing/transactions/costingDtItem/updateTagWtReqCts.html?pInvNo="
						+ $('#invNo').val(),
						type:'GET',
						success:function(data){
							$.unblockUI();
							if(data =='1'){
								popCostingDtItemDetails();
								
								$('#hideOnPageLoadItem').css('display','none');

								
								
							}else{
								displayMsg(this, null, 'Cannot Update Tag Wt ,Contact Admin');	
							}
						}
				
				
				
						
						
				})
				
					
				}
			
			
			function autoApplyTagWt(){
				
				
				 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
					
					$.ajax({
						
						url: "/jewels/manufacturing/transactions/costingDtItem/autoApplyTagWt.html?pInvNo="
							+ $('#invNo').val(),
							type:'GET',
							success:function(data){
								$.unblockUI();
								if(data =='1'){
									popCostingDtItemDetails();
									
									$('#hideOnPageLoadItem').css('display','none');

									
									
								}else{
									displayMsg(this, null, 'Cannot Update Tag Wt ,Contact Admin');	
								}
							}
					
					
							
							
							
					})
				
			}
			
			
			function popUpdateBarcodeBtn(){
				
				$.ajax({
					
					url:"/jewels/manufacturing/transactions/costingDtItem/updateBarcode.html?pInvNo="
						+ $('#invNo').val(),
					type:'GET',
					success:function(data){
						
						 popCostingDtItemDetails();
					}
					
					
				})
			}			
				
			
			
			
			//---------------------- APPLY RATE --------//----------------->
			
			
			
		 
		 function applyCostItemRate(){
			 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
			 
			 var postData = $('#costingMtNewDivId').serializeArray();
			 
			 $.ajax({
					url : "/jewels/manufacturing/transactions/applyRate/merge.html?tempPartyId="+$('#party\\.id').val(),
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR){
						
						if(data === '1'){
							popCostingDtItemDetails();
							
							$('#hideOnPageLoadItem').css('display','none');
							/* window.location.reload(true); */
						}else{
							displayMsg(this,null,"Error Can Not Apply Rate");	
						}
						
						
							
						$.unblockUI();

					},
					error : function(data, textStatus, jqXHR){
						$.unblockUI();

					}
					
				})
			 
			 
		 }
		 
		 
		 
		 function apply005TagWt(){
			 
			 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
				
				$.ajax({
					
					url: "/jewels/manufacturing/transactions/costingDtItem/apply005TagWt.html?pInvNo="
						+ $('#invNo').val(),
						type:'GET',
						success:function(data){
							$.unblockUI();
							if(data =='1'){
								popCostingDtItemDetails();
								
								$('#hideOnPageLoadItem').css('display','none');

								
								
							}else{
								displayMsg(this, null, 'Cannot Update Tag Wt ,Contact Admin');	
							}
						}
				
				
						
						
						
				})
			 
		 }
		 
		 
		 function popStyleNotMatchBtn(){
			 
			$
				.ajax({
					url : "/jewels/manufacturing/transactions/costingDtItem/styleNotMatch/report.html?mtid="+$('#costMtId').val()+ "&xml="+ $('#xml').val(),
					type : 'POST',
					success : function(data, textStatus, jqXHR) {
					
						$('#timeValCommonPdf').val(data);
						$("#genStyleNotMatchRpt").trigger('click');
						
						
						
					}
				}); 
		 }
		 
		 function popOrdPcsBtn(){
			 $('#costingOrdPcsModal').modal('show');
			 popCostingOrdPcListing();
				
		 }
		 
		 function popCostingOrdPcListing(){
			 
			 $("#costDtOrdPcsTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/costingDtItem/ordPcslisting.html?pInvNo="+ $('#invNo').val()
						});
			 
		 }
			
			
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

<script src="/jewels/js/common/design.js"></script>

