<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">
		<span style="font-size: 18px;">Diamond Rate Change</span>
	</div>
	<div class="panel-body">

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		
			
		<div class="form-group">
			<form:form commandName="stoneInwardDt" id="stoneInwardDtt">

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
									<input type="hidden" name="tempCarat" id="tempCarat" /> 
							</td>

						</tr>
					</tbody>
				</table>

			</form:form>
		</div>	
			
			
		<div class="form-group" id="dsPId">
						<div class="col-xs-3">
							<input type="search" id="searchList" class="search form-control" placeholder="Search" />
						</div>
						
						<div class="col-xs-2">
							<input type="number" class="form-control" id="rateId"  placeholder="Rate" />
						</div>
						<div class="col-xs-1" align="left">
							<input type="button" value="Apply To All" id="applyToAllBtn" disabled="true" class="btn btn-info" onclick="javascript:applyToAll();">
							
						</div>
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
										<th data-field="balStone" data-sortable="true">BalStone</th>
										<th data-field="balCarat" data-sortable="true">BalCarat</th>
									</tr>
								</thead>
							</table>
						</div>

					</div>
				</div>
			</div>
			<div>
				<table class="line-items editable table">
				<tr class="panel-heading">
					<th>&nbsp;&nbsp;Avarage Rate : <input type="text"
						id="vAvgRate" name="vAvgRate" size="7"
						disabled="true" /> 
					</th>
				</tr>
			</table>
			</div>
		</div>
		
			
		<div class="col-sm-12">
			<div align="left">
				<input type="button" id="updateRateBtn" name="updateRateBtn" value="Update" class="btn btn-primary" onclick="javascript:popUpdate();">
			</div>
		</div>
		
		
		
		
		
	</div>
</div> <!-- ending the main panel -->


<script type="text/javascript">




	$(document).ready(function(e) {
		
		 $("#diaChangNewStnId").on("dblclick", "td:not(.active)", function () {
			 
			 var column_num = parseInt( $(this).index());
			 
			 if(column_num === 10){
			    var $this = $(this);
			    var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text(),width:48 });
			    $this.html($textbox);
			    $textbox.select();
			    $textbox.focus();
			 }
		    
		});
		 
	
		$("#diaChangNewStnId").on("blur", "input:text", function () {        
		    var $this = $(this);
		    $this.parent().removeClass("active").text($this.val());
		    
		    	if(!!$this.val()){
		    		$("#diaChangNewStnId").bootstrapTable('updateRow', {
						index : stnTblRow,
						row : {
							state : true,
							rate : $this.val(),
						}
					}); 
		    	}else{
		    		$("#diaChangNewStnId").bootstrapTable('updateRow', {
						index : stnTblRow,
						row : {
							state : true,
							rate : '0.0',
						}
					}); 
		    	}
		    
			}); 
		
		
		
		
		
		$('#diaChangNewStnId').bootstrapTable({}).on('load-success.bs.table',
				function(e, data) {
			
			var data = JSON.stringify($("#diaChangNewStnId").bootstrapTable('getData'));

			var vStnValue = 0.0;
			var vCarat = 0.0;
			var vAvgRate=0.0;
			$.each(JSON.parse(data), function(idx, obj) {
				vStnValue += Number(obj.stoneValue);
				vCarat += Number(obj.balCarat);
			});
			
			vAvgRate=(vStnValue/vCarat).toFixed(2);

			$('#vAvgRate').val(vAvgRate);
			
		});
		
		
		
		

		$("#searchList").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#diaChangNewStnId tr").each(function(index) {

		        if (index != 0) {
		        	
		            $row = $(this);
		            var id1 = $row.find('td:eq(2)').text();
		            var id2 = $row.find('td:eq(7)').text();
		            var id3 = $row.find('td:eq(8)').text();
		            var id4 = $row.find('td:eq(9)').text();
		            var id5 = $row.find('td:eq(10)').text();
		            
		            
		            if (id1.toLowerCase().indexOf(value.toLowerCase()) != 0 && id2.toLowerCase().indexOf(value.toLowerCase()) != 0 &&
		            		id3.toLowerCase().indexOf(value.toLowerCase()) != 0 && id4.toLowerCase().indexOf(value.toLowerCase()) != 0 &&
		            		id5.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		               
		            }
		        }
		    });
		});
		
		
		
		
		
	
	});



	 var stnTblRow = -1;
	 $('#diaChangNewStnId').bootstrapTable({}).on('click-row.bs.table',
		function(e, row, $element) {
			stnTblRow = $element.attr('data-index');

	}); 



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
	$.ajax({
				url : '<spring:url value='/manufacturing/transactions/dc/size/list.html' />?shapeId='
						+ $('#shape\\.id').val(),
				type : 'GET',
				success : function(data) {
					$("#sizeId").html(data);
					
				}
			});

}

function popSizeGroup() {
		$.ajax({
				url : '<spring:url value='/manufacturing/transactions/dc/sizeGroup/list.html' />?shapeId='
						+ $('#shape\\.id').val(),
				type : 'GET',
				success : function(data) {
					$("#sizeGroupId").html(data);
				}
			});
}


function fetchData() {
	
 	if(!$('#stoneType\\.id').val() ||  !$('#shape\\.id').val() || !$('#quality\\.id').val()){
		displayMsg(this, null, 'StoneType , Shape And Quality is compulsary to fetch data');
	} else{
		
			
			$("#diaChangNewStnId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/dc/load/stoneInwardDt/data.html?stoneType="
							+ $('#stoneType\\.id').val()
							+"&shape="+$('#shape\\.id').val()
							+"&quality="+$('#quality\\.id').val()
							+"&size="+$("#size").val()
							+"&sizeGroup="+$("#sizeGroup\\.id").val()

					});	
			
			
			
			
			if(!!$('#size').val() || $('#sizeGroup\\.id').val()){
				$('#applyToAllBtn').removeAttr('disabled');
			}else{
				$('#applyToAllBtn').attr('disabled', 'disabled');
			}
		
	}  
	
}


	function applyToAll(){
		 var data = $('#diaChangNewStnId').bootstrapTable('getData');
	       	 for(var i=0; i<data.length;i++){
	       	 
	       	 
	     
	       	   $("#diaChangNewStnId").bootstrapTable('updateRow', {
					index : i,
					row : { 
						state : true,
						rate : $('#rateId').val()
					}
				});  
	          }   
	        
	}


function popUpdate(){
	
	
	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
	$('#genRptAll').attr('disabled', 'disabled');
	
	var data = $('#diaChangNewStnId').bootstrapTable('getSelections');
	
	var ids = $.map(data, function(item) {
		return item.id;
	});
	
	var rates  = $.map(data, function(item) {
		return item.rate;
	});
	
	
	$.ajax({
		url:"/jewels/manufacturing/transactions/updateStoneInward/rate.html",
		type:"POST",
		data:{
			ids:''+ids,
			rate:''+rates
		},
		success:function(data, textStatus, jqXHR){
			
			if(data === '-2'){
				displayMsg(this,null,"No Record Selected");
			}else{
				displayInfoMsg(this,null,"Rate Updated Successfully");
				fetchData();
			}
			
			
			$.unblockUI();
			$('#genRptAll').removeAttr('disabled');
			
			
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			displayMsg(this,null,"Error Occoured Contact Admin");
			$.unblockUI();
		}
		
		
		
	})
	
	
}










</script>





<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>


