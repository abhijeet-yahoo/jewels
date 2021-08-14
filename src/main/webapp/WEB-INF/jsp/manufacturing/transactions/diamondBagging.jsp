<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalBagDetails.jsp"%>

<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>


<div class="panel panel-primary" style="width: 100%">

	<div class="panel-heading">
		<span style="font-size: 18px;">Diamond </span>
	</div>


	<div class="panel-body">
	
	


	<div class="form-group">
	
		
		<div class="col-xs-10">
		
			<div class="row">
					<div class="col-xs-12">
						
						<!-- <div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Bag</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">TranDate</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Order
								No</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Client</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Style
								No</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Bag
								Pcs</label>
						</div> -->
						
						
					<div class="form-group col-sm-2">
					<label class="control-label" for="bag">Bag</label>
					<div class="input-group">
					<input type="text" id="bagMtName" name="bagMtName" class="form-control" onchange="javascript:popDiamondDetails()">
					 <span class="input-group-btn">
					   <button type="button" class="btn btn-default glyphicon glyphicon-list"  onClick="javascript:openBagDetails()"></button>
					     </span>
					</div>
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="trandate">TranDate</label>
					<input type="text" id="trandate" name="trandate" class="form-control" >
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="invNo">Order</label>
					<input type="text" id="invNo" name="invNo" class="form-control" readonly="readonly" >
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="partyName">Client</label>
					<input type="text" id="partyName" name="partyName" class="form-control" readonly="readonly" >
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="styleNo">Style No</label>
					<input type="text" id="styleNo" name="styleNo" class="form-control" readonly="readonly" >
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="bagPcs">Bag Pcs</label>
					<input type="text" id="bagPcs" name="bagPcs" class="form-control" readonly="readonly" >
					
					</div>
						
						
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						
						 
						<!-- <div class=" col-lg-2 col-sm-2">
							<input type="text" id="bagMtName" name="bagMtName" class="form-control" onchange="javascript:popDiamondDetails()">
						
						<span class="input-group-btn">
					   <button type="button" class="btn btn-default glyphicon glyphicon-list"  onClick="javascript:openBagDetails()"></button>
					     </span>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="trandate" name="trandate" class="form-control">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="invNo" name="invNo" class="form-control" readonly="readonly" >
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="partyName" name="partyName" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="styleNo" name="styleNo" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="bagPcs" name="bagPcs" class="form-control" readonly="readonly">
						</div> -->
						
					</div>
				</div>
	
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
						<!-- <div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">KT:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Color:</label>
						</div> -->
						<div class="col-lg-4 col-sm-4">
							<p class="text-center" style="font-size: 13px;">
								<strong>-- Design Breakup --</strong>
							</p>
						</div>
						<div class="col-xs-4 col-sm-4">
							<p class="text-center" style="font-size: 13px;">
								<strong>-- Bag Breakup --</strong>
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<!-- <div class="col-lg-2 col-sm-2">
							<input type="text" id="purityName" name="purityName" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="colorName" name="colorName" class="form-control" readonly="readonly">
						</div> -->
						<div class="col-sm-2 text-left">
							<input type="text" name="vTotalStones" id="vTotalStones" class="form-control" readonly="readonly" />
						</div>
						<div class="col-sm-2 text-left">
							<input type="text" name="vTotalCarats" id="vTotalCarats" class="form-control" readonly="readonly" />
						</div>
						<div class="col-sm-2 text-left">
							<input type="text" name="vTotalBagStones" id="vTotalBagStones" class="form-control" readonly="readonly" />
						</div>
						<div class="col-sm-2 text-left">
							<input type="text" name="vTotalBagCarats" id="vTotalBagCarats" class="form-control" readonly="readonly" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
					<input type="hidden" id="orderDtId" name="orderDtId" />
				</div>
				
		
		
		</div>
	
	
	
		<div id="odImgDivId" class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
			<div class="panel panel-primary" style=" height:170px;">
				<div class="panel-body">
					<div style="width: 150px; height: 50px" class="col-sm-4">
						<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png"
							data-lighter> <img id="ordImgId" class="img-responsive"
							src="/jewels/uploads/manufacturing/blank.png" />
						</a>
					</div>
				</div>
			</div>
		</div>
	
	
	</div>
	
	
	
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Diamond
					Breakup</span>
			</div>
		</div>
	</div>
	
	
	<div class="form-group" id="dsPId">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
						<table id="diaBagDtId" data-toggle="table"
							data-toolbar="#toolbarDt1" data-click-to-select="false"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"  data-formatter="stateFormatter"></th>
									
									<th data-field="partNm">Part No.</th>
									<th data-field="stoneType">Stone Type</th>
									<th data-field="shape" data-align="left" >Shape</th>
									<th data-field="subShape" data-sortable="true">Sub Shape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="mm" data-align="right">Size/MM</th>
									<th data-field="sieve" data-align="right">Sieve</th>
									<th data-field="sizeGroup">Size Group</th>
									<th data-field="stones" data-align="right">Stone</th>
									<th data-field="carat" data-align="right">Carat</th>
									<th data-field="trfStone" data-formatter="trfStoneFormatter" data-align="right" >Issue Stone</th>
									<th data-field="trfCarat" data-align="right" data-formatter="trfCaratFormatter">Issue Carat</th>
									<th data-field="setting" data-formatter="settingFormatterCombo">Setting</th>
									<th data-field="setType" data-formatter="settingTypeFormatterCombo">Set Type</th>
									<th data-field="diaCateg">Categ</th>
									<th data-field="centerStone" data-formatter="centerStoneFormatter">CenterStn</th>
										<th data-field="bagSrno">Srno</th>							
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="col-sm-12">
	
	<div class="form-group">
	<div id="deptDisplayDivId" style="display: none">
			<label class="col-lg-1 col-sm-2 text-right">Department </label>
			
			<div class="col-sm-2" id="deptDivId" >
					<select class="form-control">
							<option value="">Select Department</option>
							</select>
			</div>
			
			</div>
		
		<div>
				<input type="button" value="Transfer" class="btn btn-primary" id="transferBtnId"  onClick="javascript:trfData(event);" />
		</div>

			
					</div>
					
			
					
				

	
	
		
	</div>



	</div>
</div>



<script type="text/javascript">

$(document).ready(function(e){
	

	if('${trfFlg}'=='true'){
		
		$('#deptDisplayDivId').css('display','block');
		popDeptDropDown();
		
	}
	
		$("#bagMtName").autocomplete({
				source : "<spring:url value='/manufacturing/transactions/jobBag/list.html' />",
				minLength : 2
			});
		
		$("#trandate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
	
		$("#trandate").val('${currentDate}');
		
		
		if('${canEditTranddate}' === "false"){
			$("#trandate").attr("disabled","disabled");
		}
	
		
		$('#diaBagDtId').on('check.bs.table check-all.bs.table', function () {
			
			 var data =JSON.stringify($('#diaBagDtId').bootstrapTable('getData'));
			 $.each(JSON.parse(data),function(idx,obj){
				 if(obj.state==true){
					 
					
								
						if(obj.trfCarat>0 && obj.trfStone>0 ){
							
							
						}else{
							 $("#diaBagDtId").bootstrapTable('updateRow', {
									index : idx,
									row : {
										trfStone : obj.stones,
										trfCarat : obj.carat,
										

									}
								}); 
						}
						
				
					 
					 /* $('#trfStone'+idx).val(obj.stones);
					 $('#trfCarat'+idx).val(obj.carat); */
					 
			
					 
				 }
				 
		
				 
			 });
			
			
			/* $('#delBtn').prop('disabled', (!$('#dbDetailsId').bootstrapTable('getSelections').length)); */
		});
		
		
		
		
})




function popDiamondDetails(){
	if(!!$('#bagMtName').val()){
		$.ajax({
			url:"/jewels/manufacturing/transactions/jobBag/diamond/details.html?bagName="+$('#bagMtName').val(),
			type:"GET",
			datatype:"JSON",
			success:function(data){
				
				if(data ==='-1'){
					displayMsg(this,null,'Error : Please Check Bag No ,Bag No not Valid');
					$('#transferBtnId').prop('disabled', true);
					
				}else if(data ==='-2'){
					
					displayMsg(this,null,'Error : Please Check Bag in Cancel department');
					$('#transferBtnId').prop('disabled', true);
					
				}else{
					
					  $.each(JSON.parse(data), function(key,value){
					        if(key === 'image'){
					        	$('#ordImgId').attr('src', value);
								$('#oImgHRId').attr('href', value);
					        }else{
					        	$('#'+key).val(value)	
					        }
					    });
					  
					  popOrderStnDt();

					  setTimeout(function(){
						  sumbaggingDtl();
						}, 400);

					  checkOrderClose();
						$('#transferBtnId').prop('disabled', false);
						
				}
				
		
			  
			}
		});	
	}else{
		displayMsg(this,null,"Please Enter Bag No");
	}
	
}




function popOrderStnDt() {

	$("#diaBagDtId")
		.bootstrapTable('refresh',{
			url : "/jewels/manufacturing/masters/orderStnDt/listing.html?pInvNo="+$('#invNo').val()
				+"&pOrderDtId="+$('#orderDtId').val()
				+"&pIsBagDt=true"
				+"&pBagName="+$('#bagMtName').val()
		});

	//calTotals();

	//clearAdjDtls();

	//$("#filterOptId").css('display', 'none');
	
	
	
}



	$('#diaBagDtId').bootstrapTable({}).on('load-success.bs.table',
		function(e, data) {
		
			var data = JSON.stringify($("#diaBagDtId").bootstrapTable('getData'));
			var vStones = 0.0;
			var vCarat = 0.0;
			var vBagStones = 0.0;
			var vBagCarat = 0.0;
	
			$.each(JSON.parse(data), function(idx, obj) {
				vStones 	+= Number(obj.stones);
				vCarat 		+= Number(obj.carat);
				vBagStones 	+= Number(obj.trfStone);
				vBagCarat 	+= Number(obj.trfCarat);
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));

					$('#vTotalBagStones').val(" " + vBagStones);
								
					$('#vTotalBagCarats').val(" " + parseFloat(vBagCarat).toFixed(3));
				
				
				if(Number(obj.bagCarat) > 0){
					
					 $("#diaBagDtId").bootstrapTable('updateRow', {
							index : idx,
							row : {
								trfStone : obj.bagStones,
								trfCarat : obj.bagCarat,
								

							}
						}); 
					
					
					
					
			/* 		$('#setting'+i).attr('disabled','disabled');
					$('#settingType'+i).attr('disabled','disabled');
					$('#trfStone'+i).attr('disabled','disabled');
					$('#trfCarat'+i).attr('disabled','disabled');
					$('#trfStone'+i).val(obj.bagStones);
					$('#trfCarat'+i).val(obj.bagCarat); */
					
				}
				
				
			});
			
			
			$.each(JSON.parse(data), function(idx, obj) {
				
				if(Number(obj.bagCarat) > 0){
					$('#diaBagDtId tr:eq('+Number(idx+1)+')').addClass('mySelected');	
				}
				 
				
			});
			
			
		});
	
	
	
	var settingFlag = true;
	var settingData = '';
	var vData ='';
 	function settingFormatterCombo(value,row,index){
 		
 		if(row.bagCarat>0){
 			return value;	
 			
 		}else{
 				
 			

			 var tempData =  '<select  class="form-control" aria-required="true" aria-invalid="false" style="width:120px" onchange="javascript:popSetting(this,'+index+')"><option value="">- Select Setting -</option>';
		 	settingData = '';
		 		if(settingFlag === true){
		 		vData ='';
		 			$.ajax({
		 				url:"/jewels/manufacturing/masters/settinglist.html",
		 				type:"GET",
		 				dataType:"JSON",
		 				success:function(data){
		  					vData = data;
		  				},
		 				async:false
		 			});
		 			
		 			settingFlag = false;
		 		}
		 		
		 		
		 		
				$.each(vData,function(key,val){
					
					 if(value === val){
						settingData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
					}else{
						settingData += "<option value='"+key+"'>"+val+"</option>";
					}
					
				});
		 		
		 		
		 		
		 		
		 
		 		return tempData+''+settingData+''+'</select>';
 		}
 		
  	
	} 

 	function popSetting(param,vidx){
 		 $('#diaBagDtId').bootstrapTable('updateRow', {
			index : Number(vidx),
			 row : {
				setting : param.value,
			} 
		});
 		 
 		 
 		 		
 		//$('#'+param.id).val(param.value);
 		
 		 		
 	}
 	
 	
 	
	
	var settingTypeFlag = true;
	var settingTypeData = '';
	var vSetTypeData ='';
 	function settingTypeFormatterCombo(value,row,index){
 		
 		
 		
 		if(row.bagCarat>0){
 			return value;	
 			
 		}else{
 			
 			var tempData =  '<select  class="form-control" aria-required="true" aria-invalid="false" style="width:145px" onchange="javascript:popSettingType(this,'+index+')"><option value="">- Select Set-Type -</option>';
 	 		
 	 		
 	 		settingTypeData='';
 	 		
 	 		if(settingTypeFlag === true){
 	 			vSetTypeData='';
 	 			
 	 			$.ajax({
 	 				url:"/jewels/manufacturing/masters/settingTypeList.html",
 	 				type:"GET",
 	 				dataType:"JSON",
 	 				success:function(data){
 	 					
 	 					vSetTypeData=data;
 	 					
 	 			
 	 				},
 	 				async:false
 	 			});
 	 			
 	 			settingTypeFlag = false;
 	 		}
 	 		
 	 		
 			$.each(vSetTypeData,function(key,val){
 						
 						 if(value === val){
 							settingTypeData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
 						}else{
 							settingTypeData += "<option value='"+key+"'>"+val+"</option>";
 						}
 					});
 	 		
 	 		
 	 		
 	 		return tempData+''+settingTypeData+''+'</select>';
 			
 		}
 		
 		
	} 
 	
 	function popSettingType(param,vidx){
 		
 		$('#diaBagDtId').bootstrapTable('updateRow', {
			index : Number(vidx),
			row : {
				setType : param.value,
			}
		});
 		
 		
 		
 	}
 	
 	
	
	 function trfStoneFormatter(value,row,index){
		 
			
	 		if(row.bagCarat>0){
	 			return value;	
	 			
	 		}else{
	 			return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateTrfStone(this,'+index+');sumbaggingDtl()"  />';
	 			
	 		}
		
		
	} 
	
	
/* 	function trfStoneFormatter(value){
		var tempId = 'trfStone' + Number(trfStoneSrNo++);
		return '<img src="/jewels/uploads/manufacturing/design/15.jpg" id="'+ tempId+ '" class="img-responsive " />';
	} */
	
	
	
	
	function updateTrfStone(param,vidx){
				
 		$('#diaBagDtId').bootstrapTable('updateRow', {
			index : Number(vidx),
			row : {
				trfStone : param.value,
			}
		});
 		
 		//$('#'+param.id).val(param.value);
 		
 	}
	
	
	function trfCaratFormatter(value,row,index){
		
		if(row.bagCarat>0){
 			return value;	
 			
 		}else{
 			return '<input class="form-control data-input"  style="width:75px" value="'+ value+ '" onchange="javascript:updateTrfCarat(this,'+index+');sumbaggingDtl()"  />';
 		}
		
		
	}
	
	function updateTrfCarat(param,vidx){
		$('#diaBagDtId').bootstrapTable('updateRow', {
			index : Number(vidx),
			row : {
				trfCarat : param.value,
			}
		});
 		
 		//$('#'+param.id).val(param.value);
 		
 	}
	
	function sumbaggingDtl(){
	
		var totstone =0;
		var totcarat =0.0;
		var bagstone =0;
		var bagcarat =0.0;
		var data =JSON.stringify($('#diaBagDtId').bootstrapTable('getData'));

		
		$.each(JSON.parse(data),function(idx,obj){
			
			totstone +=Number(obj.trfStone);
			totcarat +=Number(obj.trfCarat);
			bagstone +=Number(obj.bagStones);
			bagcarat +=Number(obj.bagCarat);
		});
		
		$('#vTotalBagStones').val(totstone);
		$('#vTotalBagCarats').val(Number(totcarat).toFixed(3));
		
	}
	

	
	
	
	function trfData(e){
		displayDlg(e,'javascript:transferData();', 'Transfer Bags','Do you want to transfer?', 'Continue');
		
	}
	
	function transferData(){
		var len = $("#diaBagDtId").bootstrapTable('getData').length;
		for(i=0;i<len;i++){
			$('#diaBagDtId  tbody tr').eq(i).removeAttr('style','color:red');
		}
		 
		$("#modalDialog").modal("hide");

		if(Number($("#diaBagDtId").bootstrapTable('getSelections').length) > 0){
			var data = JSON.stringify($("#diaBagDtId").bootstrapTable('getSelections'));
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/diamondBagging/transfer.html",
				type : "POST",
				data : {
					pBagName : $('#bagMtName').val(),
					tranDate : $('#trandate').val(),
					data : data,
					deptId : $('#deptDropDownId').val(),
					trfFlg: '${trfFlg}',
				},
				success : function(data, textStatus, jqXHR) {
					
					if(data.indexOf("Warning") != '-1'){
						var srNo = data.substring(data.indexOf("[")+1,data.indexOf("]"))
						var tempSrNo = srNo.split(",")
						for(i=0;i<=tempSrNo.length;i++){
							$('#diaBagDtId  tbody tr').eq(Number(tempSrNo[i])-1).attr('style','color:red');
						}
						
						displayMsg(this,null,data);
					}else{
						displayInfoMsg(this,null,data);
						popOrderStnDt();
					}
					
					
				},
				error : function(jqXHR, textStatus, errorThrown) {
				}
			});
		}else{
			displayMsg(this,null,"Record Not Selected");
		}
		
		
	}
	
	
function centerStoneFormatter(value) {
		
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';

	}
	
	
	
	
function stateFormatter(value, row, index) {
	
	if(row.bagCarat>0){
			return {
		        disabled: true,
		        
		      }	
			
		}else{
			  return value
		}

  }


function checkOrderClose(){

	 $.ajax({
			url : "/jewels/manufacturing/masters/orderDt/checkOrderClose.html?pInvNo="
				+ $("#invNo").val(),
			type : 'GET',
			success : function(data) {
				
				if(data === 'true'){
					displayMsg(this, null, 'Order is Closed');
																	
					$('#transferBtnId').prop('disabled', true);
					
				}else{
					
					$('#transferBtnId').prop('disabled', false);
					}
				
			 }
		}); 
	}


function openBagDetails(){

	$('#myOrderBagDtModal').modal('show');
}


$('#orderBagDtTblId').bootstrapTable({}).on(
		'dbl-click-row.bs.table',
		function(e, row, $element) {
			
			var bgNm=jQuery.parseJSON(JSON.stringify(row)).bagNo;
			
			$('#bagMtName').val(bgNm);
			
			$('#myOrderBagDtModal').modal('hide');
		
					$('#bagMtName').trigger('onchange');
			
		})
		
		
			function popDeptDropDown() {
	

		$.ajax({
			url : '/jewels/manufacturing/masters/department/dropDownlist.html',
			type : 'GET',
			success : function(data) {
				$("#deptDivId").html(data);

			}
		});
	}
	
</script>


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



