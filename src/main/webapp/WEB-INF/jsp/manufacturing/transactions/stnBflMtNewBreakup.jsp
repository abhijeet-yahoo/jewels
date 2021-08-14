<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-body">

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>


		<div class="form-group">
			<form:form commandName="stoneInwardDt" id="stnBflDtt">

				<table class="line-items editable table table-bordered">
					<thead class="panel-heading">
						<tr class="btn-warning">
						<th>TransactionType</th>
							<th>StoneType</th>
							<th>Shape</th>
							<th>Quality</th>
							<th>Size</th>
							<th>SizeGroup</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
						
							<td>
								<div class="col-lg-12 col-sm-12" >
								<select id="typeFormat" class="form-control">
									<option value="" label="- Select Type -"></option>
									<option value="brokenType">Broken Recieve</option>
									<option value="fallenType">Fallen Recieve</option>

								</select>
							</div>	
							</td>
							<td>
								<div id="stoneTypeId">
									<form:select path="stoneType.id" class="form-control">
										<form:option value="" label=" SelectStoneType " />
										<form:options items="${stoneTypeMap}" />
									</form:select>
								</div>
							</td>
							<td><form:select path="shape.id" class="form-control"
									onchange="javascript:popQuality();popSize();popSizeGroup();">
									<form:option value="" label="- Select Shape -" />
									<form:options items="${shapeMap}" />
								</form:select></td>
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
									<form:select path="size" class="form-control">
										<form:option value="" label="- Select Size -" />
										<form:options items="${stoneChartMap}" />
									</form:select>
								</div>
							</td>
							<td>
								<div id="sizeGroupId">
									<form:select path="sizeGroup.id" class="form-control">
										<form:option value="" label="- Select SizeGroup -" />
										<form:options items="${sizeGroupMap}" />
									</form:select>
								</div>
							</td>
							
							<td><input type="button" value="Fetch Data" class="btn btn-info" onclick="javascript:fetchData();">
									<input type="hidden" name="tempCarat" id="tempCarat" /> 
							</td>

						</tr>
					</tbody>
				</table>

			</form:form>
		</div>



		
		
	 	<div class="form-group" id="dsPId">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">

						<div>
							<table id="diaChangNewStnId" data-toggle="table"
								data-toolbar="#toolbar" data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300"
								data-striped="true">
								<thead>
									<tr class = "btn-primary">
										<th data-field="state" data-checkbox="true" ></th>
										<th data-field="inwardType" data-sortable="true">Inward</th>
										<th data-field="invNo" data-sortable="true">InvNo</th>
										<th data-field="stoneType" data-sortable="true">StoneType</th>
										<th data-field="shape" data-align="left">Shape</th>
										<th data-field="subShape" data-align="left">SubShape</th>
										<th data-field="quality" data-sortable="true">Quality</th>
										<th data-field="mm" data-sortable="true">Size</th>
										<th data-field="sieve" data-sortable="true">Sieve</th>
										<th data-field="sizeGroup" data-sortable="true">SizeGroup</th>
										<th data-field="rate" data-sortable="true">Rate</th>
										<th data-field="brkStone" data-sortable="true">Stone</th>
										<th data-field="brkCarat" data-sortable="true">Carat</th>
										<th data-field="adjStone" data-sortable="true">AdjStone</th>
										<th data-field="adjCarat" data-sortable="true">AdjCarat</th>
										<th data-field="lossStone" data-sortable="true">LossStone</th>
										<th data-field="lossCarat" data-sortable="true">LossCarat</th>
									<!-- <th data-field="setting" data-sortable="true">Setting</th>
										<th data-field="settingType" data-sortable="true">Set Type</th> -->
									</tr>
								</thead>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div> 
		
		
		
		

	
		
		
		
		
		
	
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<form:form commandName="stnBflDt" id="transferToImpStnAdjNew"
					action="/jewels/manufacturing/transactions/newBreakup/frmStoneInwardDt.html"
					cssClass="form-horizontal transferForm">
	
					<table class="table table-bordered">
						<tbody>
							<tr>
								 <td class="col-xs-1">
									<input type="submit" value="Update Stock" class="btn btn-primary" id="newTrfBtnId" /> 
									<form:input type="hidden" path="id" />	
								    <input type="hidden" name="pODIds" id="pODIds" />
									<input type="hidden" name="adjCarat" id="adjCarat" />
									<input type="hidden" name="adjStone" id="adjStone" />
									<input type="hidden" name="pTypeFormat" id="pTypeFormat" />
									<input type="hidden" name="lossCarat" id="lossCarat" />
									<input type="hidden" name="lossStone" id="lossStone" />
									<input type="hidden" name="pMtId" id="pMtId" />
										
						
								</td> 
								<td class="col-xs-11">
									<a  class="btn btn-info" onclick="javascript:goToMainPage();"
										style="font-size: 15px" type="button"  >Back
									</a>
									<input type="hidden" name="tempCarat" id="tempCarat" /> 
								</td>												
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
		
		
		

		

	</div>
	<!-- ending the panel body -->
</div>
<!-- ending the main panel -->


<script type="text/javascript">



var rowTableId = -1;
var colNumber = 0;
var mtId = "";

	$(document)
	.ready(function(e) {
			
				
		
		if(window.location.href.indexOf("stnBflMtNewBreakup") != -1){
			var url = window.location.href; 
			mtId = url.substring(url.indexOf("MtId=")+5);
		}
		
				
				
				
		 $("#diaChangNewStnId").on("dblclick", "td:not(.active)", function (e, row, $element) {
			 
			
					 
					 var column_num = parseInt( $(this).index());
					 
					 colNumber = column_num;
					 
					 if(column_num === 13 || column_num === 14 || column_num === 15 || column_num === 16 ){
					
						 var $this = $(this);
				   		 var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text(),width:55 });
				    	 $this.html($textbox);
				         $textbox.select();
				         $textbox.focus();
						 
					 }
					 
			    	
			    
			});
		 
/* 		 $("#diaChangNewStnId").on("dblclick", "td:not(.active)", function (e, row, $element) {
			 
				
			 
			 var column_num = parseInt( $(this).index());
			 
			 colNumber = column_num;
			 
			 if(column_num === 15 || column_num === 16){
					
				 var $this = $(this);
		   		 var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text(),width:55 });
		    	 $this.html($textbox);
		         $textbox.select();
		         $textbox.focus();
				 
			 }
			 
	    	
	    
	}); */
		
				
	$("#diaChangNewStnId").on("blur", "input:text", function () {        
			   
		  var $this = $(this);
	      $this.parent().removeClass("active").text($this.val());
 
		  if(colNumber === 13){
				 
					if(!!$this.val() && $this.val() >= 0){  
					  
					 	if($this.val() <= Number(stone)){
					 		
					 		/* if(Number){} */
					 	/* 	alert(Number(chkAdjCarat));
					 		alert(Number(chkAdjStone));
					 		alert(Number(chkLossCarat));
					 		alert(Number(chkLossStone)); */
					 		
					 		if(Number(chkLossCarat) === 0 && Number(chkLossStone) === 0){
					 			 $("#diaChangNewStnId").bootstrapTable('updateRow', {
									    index : rowTableId,
										row : {
											state : false,
											adjStone : $this.val(),
				
										}
									});
					 		}else{
					 			 displayMsg(this, null, 'Not a Valid Entry .Please Check It');
					 			 $("#diaChangNewStnId").bootstrapTable('updateRow', {
									    index : rowTableId,
										row : {
											state : false,
											adjStone : 0,
				
										}
									});
					 		}
					 		
						 
						 }else{
							 
							 displayMsg(this, null, 'AdjStone Cannot be Greater Than Stone');
							 $("#diaChangNewStnId").bootstrapTable('updateRow', {
								    index : rowTableId,
									row : {
										state : false,
										adjStone : 0,
			
									}
								});
							
						 }
					}else{
						
						  displayMsg(this, null, 'AdjStone Cannot be Blank or Connot be less than zero');
						$("#diaChangNewStnId").bootstrapTable('updateRow', {
						    index : rowTableId,
							row : {
								state : false,
								adjStone : 0,
	
							}
						});
					}
				  
			  }else if(colNumber === 14){

				  if(!!$this.val() && $this.val() >= 0){   
					  	if($this.val() <= Number(carat)){
					  		
					  		if(Number(chkLossCarat) === 0 && Number(chkLossStone) === 0){
		
					  			 $("#diaChangNewStnId").bootstrapTable('updateRow', {
									    index : rowTableId,
										row : {
											state : false,
											adjCarat : $this.val(),
			
										}
									});
					  		}else{
					  			
					  			displayMsg(this, null, 'Not a Valid Entry .Please Check It.');
					 			 $("#diaChangNewStnId").bootstrapTable('updateRow', {
									    index : rowTableId,
										row : {
											state : false,
											adjCarat : '0.0',
				
										}
									});
					  		}
					  		
							    
						  }else{
							 
							  displayMsg(this, null, 'AdjCarat Cannot be Greater Than Carat');
							  $("#diaChangNewStnId").bootstrapTable('updateRow', {
								    index : rowTableId,
									row : {
										state : false,
										adjCarat : '0.0',

									}
								});   
						  }
				  
				  }else{
					  
					  displayMsg(this, null, 'AdjCarat Cannot be Blank  or Connot be less than zero');
					  $("#diaChangNewStnId").bootstrapTable('updateRow', {
						    index : rowTableId,
							row : {
								state : false,
								adjCarat : '0.0',
								
							}
						});   
				  }
				 
		      }else if(colNumber === 15){
				 
				if(!!$this.val() && $this.val() >= 0){  
				  
				 	if($this.val() <= Number(stone)){
				 		
				 		if(Number(chkAdjCarat) === 0 && Number(chkAdjStone) === 0){
				 			  $("#diaChangNewStnId").bootstrapTable('updateRow', {
								    index : rowTableId,
									row : {
										state : false,
										lossStone : $this.val(),
			
									}
								});
				 		}
				 		else{
				 			displayMsg(this, null, 'Not a Valid Entry .Please Check It.');
				 			 $("#diaChangNewStnId").bootstrapTable('updateRow', {
								    index : rowTableId,
									row : {
										state : false,
										lossStone : 0,
			
									}
								});
				 			
				 		}
				 		
					
					 }else{
						 
					
						 displayMsg(this, null, 'Loss Stone Cannot be Greater Than Stone');
						 $("#diaChangNewStnId").bootstrapTable('updateRow', {
							    index : rowTableId,
								row : {
									state : false,
									lossStone : 0,
		
								}
							});
						
					 }
				}else{
					
					  displayMsg(this, null, 'Loss Stone Cannot be Blank or Connot be less than zero');
					$("#diaChangNewStnId").bootstrapTable('updateRow', {
					    index : rowTableId,
						row : {
							state : false,
							lossStone : 0,

						}
					});
				}
			  
		  }else if(colNumber === 16){
			  
			  
			  if(!!$this.val() && $this.val() >= 0){   
				  	if($this.val() <= Number(carat)){
				  		
				  		if(Number(chkAdjCarat) === 0 && Number(chkAdjStone) === 0){
				  		  $("#diaChangNewStnId").bootstrapTable('updateRow', {
							    index : rowTableId,
								row : {
									state : false,
									lossCarat : $this.val(),
	
								}
							}); 
				  		}
				  		else{
				  			displayMsg(this, null, 'Not a Valid Entry .Please Check It.');
				 			 $("#diaChangNewStnId").bootstrapTable('updateRow', {
								    index : rowTableId,
									row : {
										state : false,
										lossCarat : 0.0,
			
									}
								});
				  			
				  		}
						  
					  }else{
						
						  displayMsg(this, null, 'Loss Carat Cannot be Greater Than Carat');
						  $("#diaChangNewStnId").bootstrapTable('updateRow', {
							    index : rowTableId,
								row : {
									state : false,
									lossCarat : '0.0',

								}
							});   
					  }
			  
			  }else{
				  
				  displayMsg(this, null, 'Loss Carat Cannot be Blank or Connot be less than zero');
				  $("#diaChangNewStnId").bootstrapTable('updateRow', {
					    index : rowTableId,
						row : {
							state : false,
							lossCarat : '0.0',
							
						}
					});   
			  }
			 
	      }
		  
	      
		  
    
		}); 		 
				 
	
	

	
	
				
  }); //-- end document ready--//---//
	
	
	
	
	
	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt
					.indexOf("</strong>"));
		}

		return dt;
	}
	
	var stone = 0;
	var carat = 0.0;
	
	var chkAdjStone = 0;
	var chkAdjCarat = 0.0;
	var chkLossStone = 0;
	var chkLossCarat = 0.0;
	
	$('#diaChangNewStnId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
			
				rowTableId = $element.attr('data-index');
				stone = getNumVal(jQuery.parseJSON(JSON.stringify(row)).brkStone);
				carat = getNumVal(jQuery.parseJSON(JSON.stringify(row)).brkCarat);
				
				 chkAdjStone = getNumVal(jQuery.parseJSON(JSON.stringify(row)).adjStone);
				 chkAdjCarat = getNumVal(jQuery.parseJSON(JSON.stringify(row)).adjCarat);
				 chkLossStone = getNumVal(jQuery.parseJSON(JSON.stringify(row)).lossStone);
				 chkLossCarat = getNumVal(jQuery.parseJSON(JSON.stringify(row)).lossCarat);
				 
				
			})
	


 
	function popQuality() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/dc/quality/list.html' />?shapeId='
							+ $('#shape\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#qualityId").html(data);
					}
				});

	}

	function popSize() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/dc/size/list.html' />?shapeId='
							+ $('#shape\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#sizeId").html(data);
						
					}
				});

	}

	function popSizeGroup() {
		
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/dc/sizeGroup/list.html' />?shapeId='
							+ $('#shape\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#sizeGroupId").html(data);
					}
				});

	}
	
	
	function fetchData() {
		
	 	if(!$('#stoneType\\.id').val() ||  !$('#shape\\.id').val() || !$('#quality\\.id').val() || !$('#typeFormat').val()){
			displayMsg(this, null, 'StoneType , Shape , Quality And Type is compulsary to fetch data');
		} else{
			
				 
				$("#diaChangNewStnId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/load/stoneInwardDt/data.html?stoneType="
								+ $('#stoneType\\.id').val()
								+"&shape="+$('#shape\\.id').val()
								+"&quality="+$('#quality\\.id').val()
								+"&size="+$("#size").val()
								+"&sizeGroup="+$("#sizeGroup\\.id").val()
								+"&typeFormat="+$("#typeFormat").val()

						});	
			
		}  
	
	}
	



	$(document)
	.on(
			'submit',
			'form#transferToImpStnAdjNew',
			function(e) {
				
				
       			$('#newTrfBtnId').attr('disabled', 'disabled');
				
				var data = $('#diaChangNewStnId').bootstrapTable('getSelections');
				
				var ids = $.map(data, function(item) {
					return item.id;
				});
				 
				
				var vAdjStone  = $.map(data, function(item) {
					return item.adjStone;
				});
			
				var vAdjCarat = $.map(data, function(item) {
					return item.adjCarat;
				});
				
				var vLossStone  = $.map(data, function(item) {
					return item.lossStone;
				});
			
				var vLossCarat = $.map(data, function(item) {
					return item.lossCarat;
				});
				
			

				$('#pODIds').val(ids);
				$('#adjStone').val(vAdjStone);
				$('#adjCarat').val(vAdjCarat);
				$('#pTypeFormat').val($('#typeFormat').val());
				$('#lossStone').val(vLossStone);
				$('#lossCarat').val(vLossCarat);
				$('#pMtId').val(mtId);
			
	

				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

					  $
						.ajax({
							url : formURL,
							type : "POST",	
							data : postData,
							
							success : function(data, textStatus, jqXHR) {
								/* alert(data); */
									
									  if(data === "-1"){
										displayMsg(this, null, 'Record Not Selected ');
									}else if(data === "1"){
										displayInfoMsg(this, null, 'Stock Updated Sucessfully ');
										fetchData();
									}else if(data === "FallenStockError"){
										displayMsg(this, null, 'Fallen Stock Not Available');
										fetchData();
									}else if(data === "BrokenStockError"){
										displayMsg(this, null, 'Broken Stock Not Available');
										fetchData();
									} 
									 $('#newTrfBtnId').removeAttr('disabled');
									 
									 
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
							}

						});
				
				e.preventDefault(); //STOP default action


			});

	
	
	function goToMainPage(){
		window.location.href = "/jewels/manufacturing/transactions/stnBflMt/edit/"+mtId+".html";
	}
	
	
	
	
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

