<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary" style="width: 100%">
	<div class="panel-body">
	<div class="form-group">
			<form:form commandName="stoneInwardDt">

				<table class="line-items editable table table-bordered">
					<thead class="panel-heading">
						<tr class="btn-warning">
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
							<table id="stnDtTableId" data-toggle="table"
								data-toolbar="#toolbar" data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300"
								data-striped="true">
								<thead>
									<tr class = "btn-primary">
										<th data-field="state" data-checkbox="true" ></th>
										<th data-field="inwardType" data-sortable="true">Inward</th>
										<th data-field="invNo" data-sortable="true">Inv&nbsp;No</th>
										<th data-field="invDate" data-sortable="true">Inv&nbsp;Date</th>
										<th data-field="stoneType" data-sortable="true">Stone&nbsp;Type</th>
										<th data-field="shape" data-align="left">Shape</th>
										<th data-field="quality" data-sortable="true">Quality</th>
										<th data-field="mm" data-sortable="true">Size</th>
										<th data-field="sieve" data-sortable="true">Sieve</th>
										<th data-field="sizeGroup" data-sortable="true">Size&nbsp;Group</th>
										<th data-field="rate" data-sortable="true">Rate</th>
										<th data-field="balStone" data-sortable="true">Bal&nbsp;Stone</th>
										<th data-field="balCarat" data-sortable="true">Bal&nbsp;Carat</th>
										<th data-field="addCarat" data-sortable="true">(+)&nbsp;Carat</th>
										<th data-field="dedCarat" data-sortable="true">(-)&nbsp;Carat</th>
										
									</tr>
								</thead>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div> 
		
		
		
		
		

	
	</div>
	
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<form:form commandName="stoneAdjustment" id="stoneAdjDt"
					action="/jewels/manufacturing/transactions/stoneAdjustment/save.html"
					cssClass="form-horizontal transferForm">
	
					<table class="table table-bordered">
						<tbody>
							<tr>
								 <td class="col-xs-1">
									<input type="submit" value="Update Stock" class="btn btn-primary" id="saveId" /> 
									<form:input type="hidden" path="id" />	
								    <input type="hidden" name="pODIds" id="pODIds" />
									<input type="hidden" name="paddCarat" id="paddCarat" />
									<input type="hidden" name="pdedCarat" id="pdedCarat" />
								</td> 
							
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var rowTableId = -1;
var colNumber = 0;
var vaddCarat='0.0';
var vdedCarat='0.0';
var vbalCarat='0.0';

$(document).ready(function(e){

	 $("#stnDtTableId").on("dblclick", "td:not(.active)", function (e, row, $element) {
		 
				 var column_num = parseInt( $(this).index());
				 
				 colNumber = column_num;
				 
				 if(column_num === 13){
									 
 					if(vdedCarat>0){
 						displayMsg(this, null, 'Can Not Edit as deducted carat > 0 ');
					 }else{
						 var $this = $(this);
				   		 var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text(),width:55 });
				    	 $this.html($textbox);
				         $textbox.select();
				         $textbox.focus();
					 }
					 
				 }
				 
				 if( column_num === 14){
					 
					 if(vaddCarat>0){
						 displayMsg(this, null, 'Can Not Edit as added carat >0 ');
					 }else{
						 var $this = $(this);
				   		 var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text(),width:55 });
				    	 $this.html($textbox);
				         $textbox.select();
				         $textbox.focus();
					 }
				
					 
					 
				 }
				 
		    	
		    
		});
	 
	 
	 
	 $('#stnDtTableId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
				rowTableId = $element.attr('data-index');
				vaddCarat = getNumVal(jQuery.parseJSON(JSON.stringify(row)).addCarat);
				vdedCarat = getNumVal(jQuery.parseJSON(JSON.stringify(row)).dedCarat);
				vbalCarat=getNumVal(jQuery.parseJSON(JSON.stringify(row)).balCarat);	
			});
	 
	 
	 
	 $("#stnDtTableId").on("blur", "input:text", function () {        
		 
		  var $this = $(this);
	      $this.parent().removeClass("active").text($this.val());

			 if(colNumber === 13){

				  if(!!$this.val() && $this.val() >= 0){  
			 			 $("#stnDtTableId").bootstrapTable('updateRow', {
							    index : rowTableId,
								row : {
									state : false,
									addCarat : $this.val(),
									
		
								}
							});
			 			vaddCarat =$this.val();
			 			 		
				 
				 
			}else{
				
				  displayMsg(this, null, 'Add Carat Cannot be Blank ');
				$("#stnDtTableId").bootstrapTable('updateRow', {
				    index : rowTableId,
					row : {
						state : false,
						addCarat : '0.0',

					}
				});
				vaddCarat ='0.0';
			}
				 
		}else if(colNumber === 14){
			  
			  if(!!$this.val() && $this.val() >= 0){   
				  
				  	if($this.val() <= Number(vbalCarat)){
				 
				  		  $("#stnDtTableId").bootstrapTable('updateRow', {
							    index : rowTableId,
								row : {
									state : false,
									dedCarat : $this.val(),
	
								}
							}); 
				  		  
				  		vdedCarat=$this.val();
				 
						  
					  }else{
						
						  displayMsg(this, null, 'Deduct Carat Cannot be Greater Than Bal Carat');
						  $("#stnDtTableId").bootstrapTable('updateRow', {
							    index : rowTableId,
								row : {
									state : false,
									dedCarat : '0.0',

								}
							});  
						  vdedCarat = '0.0';
					  }
			  
			  }else{
				  
				  displayMsg(this, null, 'Deduct Carat Cannot be Blank ');
				  $("#stnDtTableId").bootstrapTable('updateRow', {
					    index : rowTableId,
						row : {
							state : false,
							dedCarat : '0.0',
							
						}
					});   
				  vdedCarat = '0.0';
			  }
			 
	      }
		  
	}); 	
	
});



function getNumVal(dt) {
	if (typeof dt === 'undefined') {
	} else {
		if(dt >0){
		dt = dt.substring(dt.indexOf("<strong>") + 8, dt
				.indexOf("</strong>"));
		}
	}

	return dt;
}


function fetchData() {
	
 	if(!$('#stoneType\\.id').val() ||  !$('#shape\\.id').val() || !$('#quality\\.id').val()){
		displayMsg(this, null, 'StoneType , Shape , Quality  is compulsary to fetch data');
	} else{
		
			 
			$("#stnDtTableId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/stoneAdj/load/stoneInwardDt/data.html?stoneType="
							+ $('#stoneType\\.id').val()
							+"&shape="+$('#shape\\.id').val()
							+"&quality="+$('#quality\\.id').val()
							+"&size="+$("#size").val()
							+"&sizeGroup="+$("#sizeGroup\\.id").val()

					});	
		
	}  

}

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

$(document)
.on(
		'submit',
		'form#stoneAdjDt',
		function(e) {
			
			
   			$('#saveId').attr('disabled', 'disabled');
			
			var data = $('#stnDtTableId').bootstrapTable('getSelections');
			
			var ids = $.map(data, function(item) {
				return item.id;
			});
			 
		
			var vAddCarat = $.map(data, function(item) {
				return item.addCarat;
			});
		
			var vDedCarat = $.map(data, function(item) {
				return item.dedCarat;
			});
			
		

			$('#pODIds').val(ids);
			$('#paddCarat').val(vAddCarat);
			$('#pdedCarat').val(vDedCarat);
			

			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");

				  $
					.ajax({
						url : formURL,
						type : "POST",	
						data : postData,
						
						success : function(data, textStatus, jqXHR) {
							 
								
								  if(data === "-1"){
									displayMsg(this, null, 'Record Not Selected ');
								}else if(data === "1"){
									displayInfoMsg(this, null, 'Stock Updated Sucessfully ');
									fetchData();
								}else if(data === "-2"){
									displayMsg(this, null, 'Stock Not Available ');
									fetchData();
								}

								 
								 $('#saveId').removeAttr('disabled');
								 
								 
						},
						error : function(jqXHR, textStatus,
								errorThrown) {
						}

					});
			
			e.preventDefault(); //STOP default action


		});



</script>



	
	