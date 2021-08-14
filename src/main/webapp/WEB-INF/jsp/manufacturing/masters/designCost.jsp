<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


	<div class="row">
	
	<div class="col-sm-4">
		<div class="panel panel-primary">
		 <div class="panel-body">
	
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>


		<div class="form-group"> 
			<form:form commandName="designCost" 
				cssClass="form-horizontal designCostForm">
				
			<div class="row">
				 <div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Purity<span style="color:red">*</span> :</label>
					</div>
					<div class="col-lg-6 col-sm-6">
						 <form:select path="purity.id" class="form-control">
							<form:option value="" label=" Select Purity " />
							<form:options items="${purityMapForGold}" />
						</form:select>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			 
			 <div class="row">
				 <div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Gold Rate :</label>
					</div>
					<div class="col-lg-6 col-sm-6">
						<input type="text" id="goldRate" name="goldRate" value="0.0" class="form-control" />
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			 
			 <div class="row">
				 <div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Added % :</label>
					</div>
					<div class="col-lg-6 col-sm-6">
						 <input type="text" id="addedPerc" name="addedPerc" value="0.0" class="form-control" />
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			 
			 <div class="row">
				 <div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Tag % :</label>
					</div>
					<div class="col-lg-6 col-sm-6">
						<input type="text" id="tagPerc" name="tagPerc" value="0.0" class="form-control" />
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			 
			 <div class="row">
				 <div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Disp % :</label>
					</div>
					<div class="col-lg-6 col-sm-6">
						<input type="text" id="dispPerc" name="dispPerc" value="0.0" class="form-control" />
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			 
			 <div class="row">
				 <div class="col-xs-12">
					<div class="col-lg-4 col-sm-4">
						<label for="inputLabel3" class=".col-lg-2 text-right">Handling % :</label>
					</div>
					<div class="col-lg-6 col-sm-6">
						<input type="text" id="handlingPerc" name="handlingPerc" value="0.0" class="form-control" />
					</div>
				</div>
			</div>
			
			
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			<div class="col-sm-offset-2 col-lg-4 col-sm-4">
				<input type="button" id="applyRateBtn" name="applyRateBtn" value="Apply Rate" class="btn btn-primary" onclick="javascript:popApplyRate()">	
				
				
			</div>
			
			
		
			</form:form>
			
			
			
			

		</div>

		
		
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
			<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
			<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
			<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
			</div>
		</div>	
	</div>

	
		
	
	<div class="col-sm-8">
		
		<div class="panel panel-primary">
			<div class="panel-body">
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="col-sm-6">
		
			<div class="form-group"> 
				
					
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Per Gms :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispPerGms" name="dispPerGms" value="0.0" class="form-control" value="${dispPerGmsVal}"/>
						</div>
					</div>
				</div>	
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Wax Wt :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispWaxWt" name="dispWaxWt" value="0.0" class="form-control" value="${dispWaxWtVal}"/>
						</div>
					</div>
				</div>	
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Net Wt :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispNetWt" name="dispNetWt" value="0.0" class="form-control" value="${dispNetWtVal}"/>
						</div>
					</div>
				</div>	
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Metal Value :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispMetalValue" name="dispMetalValue" value="0.0" class="form-control" value="${dispMetalValueVal}"/>
						</div>
					</div>
				</div>	
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				
				<!-- --------------------------------------------- -->
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Comp Wt :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispCompWt" name="dispCompWt" value="0.0" class="form-control" />
						</div>
					</div>
				</div>	
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Comp Value :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispCompValue" name="dispCompValue" value="0.0" class="form-control" />
						</div>
					</div>
				</div>	
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<!-- ---------------------------------------- -->
				
				
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Stone Value :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispStoneValue" name="dispStoneValue" value="0.0" class="form-control" value="${dispStoneValueVal}"/>
						</div>
					</div>
				</div>	
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Cfps :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispGoldLabour" name="dispGoldLabour" value="0.0" class="form-control" value="${dispGoldLabourVal}"/>
						</div>
					</div>
				</div>	
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
			
		
		
			</div>
		</div>
		
		<!-- /////------------6 ---------6---------/////////// -->
		
		<div class="col-sm-6">
		
				<div class="form-group"> 
				
					<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Set Labour :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispSetLabour" name="dispSetLabour" value="0.0" class="form-control" value="${dispSetLabourVal}"/>
						</div>
					</div>
				</div>	
					
						<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Handling :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispHandling" name="dispHandling" value="0.0" class="form-control" value="${dispHandlingVal}"/>
						</div>
					</div>
				</div>		
					
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Rhodium :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispRhodium" name="dispRhodium" value="0.0" class="form-control" value="${dispRhodiumVal}"/>
						</div>
					</div>
				</div>	
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Loss :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispLoss" name="dispLoss" value="0.0" class="form-control" value="${dispLossVal}"/>
						</div>
					</div>
				</div>		
					
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Other :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispOther" name="dispOther" value="0.0" class="form-control" value="${dispOtherVal}"/>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Total Labour :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispTotalLabour" name="dispTotalLabour" value="0.0" class="form-control" value="${dispTotalLabourVal}"/>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right">Final Cost :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispFinalCost" name="dispFinalCost" value="0.0" class="form-control" value="${dispFinalCostVal}"/>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;</div>
				</div>
				
				<div class="row">
					 <div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-right"><span style="color: red">Tag :</span></label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<input type="text" id="dispTag" name="dispTag" value="0.0" class="form-control" value="${dispTagVal}" style="color: red;"/>
						</div>
					</div>
				</div>
				
				
				
				
			
			
				</div>
		</div>
			
			
			</div>
			
			<div class="row">
					<div class="col-xs-12">&nbsp;&nbsp;&nbsp;</div>
				</div>
			
			
		</div>
	</div>
	
	</div> <!-- main div ending -->
	
	
	
	
	
	<script type="text/javascript">
	
	var designCostingFlag = false;
	var editApplyRateFlag = false;
	$(document).ready(function(e){
		designCostingFlag = true;
		editApplyRateFlag = true;
	});
	
	
	
	function editApplyRate(){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/designCost/editApplyRateDetails.html?designId="+designId,
			type:"GET",
			success:function(data){
				
				if(data != '-2'){
					var tempData = data.split("_");
					$('#purity\\.id').val(tempData[0]);
					$('#goldRate').val(tempData[1]);
					$('#addedPerc').val(tempData[2]);
					$('#tagPerc').val(tempData[3]);
					$('#dispPerc').val(tempData[4]);
					$('#handlingPerc').val(tempData[5]);
					
				}
				
			}
					
		})
		
	}
	
	
	
	
	
	
	
	
	function popCostDisplayDetails(){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/designCost/popDetails.html?designId="+designId,
			type:"GET",
			success:function(data){
				
				if(data != '-2'){
				
					var tempData = data.split("_");
					
					$('#dispPerGms').val(tempData[0]);
					$('#dispWaxWt').val(tempData[1]);
					$('#dispNetWt').val(tempData[2]);
					$('#dispMetalValue').val(tempData[3]);
					$('#dispStoneValue').val(tempData[4]);
					$('#dispGoldLabour').val(tempData[5]);
					$('#dispSetLabour').val(tempData[6]);
				
					$('#dispHandling').val(tempData[7]);
					$('#dispRhodium').val(tempData[8]);
					$('#dispLoss').val(tempData[9]);
					$('#dispOther').val(tempData[10]);
					$('#dispTotalLabour').val(tempData[11]);
					$('#dispFinalCost').val(tempData[12]);
					$('#dispTag').val(tempData[13]);
					$('#dispCompWt').val(tempData[15]);
					$('#dispCompValue').val(tempData[14]);
					
					
				}	
					
				
			}
		});
		
	}
	
	
	
	
	var designId;
	
	function popDesignId(){
		var tempUrl = window.location.href;
		designId = tempUrl.substring(tempUrl.indexOf("edit/")+5,tempUrl.indexOf(".html"));
		
		if(designCostingFlag){
			popCostDisplayDetails();
			designCostingFlag = false;
		}
		
		if(editApplyRateFlag){
			editApplyRate();
			editApplyRateFlag = false;
		}
		
	}
	
	
	
	
	function popApplyRate(){
		
		if(!!$('#purity\\.id').val()){
		
				$('#applyRateBtn').attr('disabled', 'disabled');
				
				$.ajax({
					url:"/jewels/manufacturing/masters/designCost/applyRate.html?designId="+designId
							+"&goldRate="+$('#goldRate').val()
							+"&purityId="+$('#purity\\.id').val()
							+"&addedPerc="+$('#addedPerc').val()
							+"&tagPerc="+$('#tagPerc').val()
							+"&dispPerc="+$('#dispPerc').val()
							+"&handlingPerc="+$('#handlingPerc').val(),
					type:"GET",
					success:function(data){
						displayInfoMsg(this, null, 'Rate Applied Succesfully');
						popCostDisplayDetails();
						editApplyRate();
						$('#applyRateBtn').removeAttr('disabled');
					}
					
					
				});
		
		}else{
			displayMsg(event, this,'Purity is Compulsary');
		}
		
	}
	
	
	
	
 </script>
	