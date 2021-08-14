<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%@ include file="/WEB-INF/jsp/common/modal.jsp"%> --%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<style>
.ui-autocomplete{
z-index:1151 !important; 
}
</style>

<div class="modal fade" id="myQltyRateChng" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Quality Rate Change</h4>
			</div>

			<div class="modal-body">
			  <div class="row">
					<label for="shape" class="col-sm-3 control-label">Shape</label>
					<div class="col-sm-9">
					<select class="form-control" id="shapeDropDownDivId" name="shapeDropDownDivId"  onchange="javascript:popQualityDropDown();sizeGroupDropdown();popPackSize()">
							<option value="">Select Shape</option>
							</select>
					
					</div>
					</div>
					<div class="row">&nbsp;</div>
					
					<div class="row">
					<label for="qlty" class="col-sm-3 control-label">Quality</label>
					<div class="col-sm-9" >
					<select class="form-control" id="qualityDropDownDivId" >
							<option value="">Select Quality</option>
							</select>
					</div>
					</div>
					
					<div class="row">&nbsp;</div>
					
					<div class="row">
					<label for="sizeGroup" class="col-sm-3 control-label">Size Group</label>
					<div class="col-sm-9" >
					<select class="form-control" id="sizeGroupDropDownDivId" onchange="javascript:popPackSize()" >
							<option value="">Select Group</option>
							</select>
					</div>
					</div>
					
					
					<div class="row">&nbsp;</div>
					
					<div class="row">
					<label for="size" class="col-sm-3 control-label">Size</label>
					<div class="col-sm-9" >
					<select class="form-control" id="sizeDropDownDivId" >
							<option value="">Select Size</option>
							</select>
					</div>
					</div>
					
					
					<div class="row">&nbsp;</div>
					
					<div class="row">
					<label for="stnRate" class="col-sm-3 control-label">Stone Rate</label>
					<div class="col-sm-9" >
					<input type="number" id="stnRateTxt" class="form-control">
					</div>
					</div>
						
						

	
	
			
			
			
			
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="stoneRateSaveBtnId" onclick="javascript:updateStoneRate()">Update Rate</button>
				
			</div>


		</div>


	</div>

</div>

<script type="text/javascript">


function popQualityDropDown() {
	
	var e = document.getElementById("shapeDropDownDivId");
	var shpId = e.options[e.selectedIndex].value;
	$
			.ajax({

				url : "/jewels/marketing/transactions/packStnDt/qualityDropdown.html?packMtId="+mtid+"&shapeId="+shpId,
				type : 'GET',
				success : function(data) {

						$('#qualityDropDownDivId').html(data);
						
				}
			})

}



function shapeDropdown() {
	$
			.ajax({

				url : "/jewels/marketing/transactions/packStnDt/shapeDropdown.html?packMtId="+mtid,
				type : 'GET',
				success : function(data) {
						$('#shapeDropDownDivId').html(data);
						
				}
			})

}


function sizeGroupDropdown() {
	var e = document.getElementById("shapeDropDownDivId");
	var shpId = e.options[e.selectedIndex].value;
	$
			.ajax({

				url : "/jewels/marketing/transactions/packStnDt/sizeGroupDropdown.html?packMtId="+mtid+"&shapeId="+shpId,
				type : 'GET',
				success : function(data) {

						$('#sizeGroupDropDownDivId').html(data);
						
				}
			})

}



function popPackSize() {
	var e = document.getElementById("shapeDropDownDivId");
	var shpId = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("sizeGroupDropDownDivId");
	var sizeGroupId = e.options[e.selectedIndex].value;
	
	
	$
			.ajax({

				url : "/jewels/marketing/transactions/packStnDt/sizeDropdown.html?packMtId="+mtid+"&shapeId="+shpId+"&sizeGroupId="+sizeGroupId,
				type : 'GET',
				success : function(data) {

						$('#sizeDropDownDivId').html(data);
						
				}
			})

}


function updateStoneRate(){
	 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	
	if($('#stnRateTxt').val()>0 && $('#shapeDropDownDivId').val() !='' && $('#qualityDropDownDivId').val() !='' 
			&&($('#sizeGroupDropDownDivId').val() !='' || $('#sizeDropDownDivId').val() !='')){
		
		$('#stoneRateSaveBtnId').attr('disabled','disabled');
		
		$
		.ajax({

			url : "/jewels/marketing/transactions/packStnDt/updateStoneRate.html?packMtId="+mtid+"&shapeId="+$('#shapeDropDownDivId').val()
					+"&qualityId="+$('#qualityDropDownDivId').val()+"&sizeGroupId="+$('#sizeGroupDropDownDivId').val()
					+"&sizeNm="+$('#sizeDropDownDivId').val()
					+"&stnRate="+$('#stnRateTxt').val(),
			type : 'GET',
			success : function(data) {
				$('#stoneRateSaveBtnId').removeAttr('disabled','disabled');
					if(data==="1"){
						displayInfoMsg(this,null,"Stone Rate Update Successfully");	
						$('#shapeDropDownDivId').val('');
						$('#qualityDropDownDivId').val('');
						$('#sizeGroupDropDownDivId').val('');
						$('#sizeDropDownDivId').val('');
						$('#stnRateTxt').val('');
					}
					
					
					
					$.unblockUI();
					
			}
		})
		
		
		
	}else{
		displayMsg(this,null,"Shape,Quality and Group or Size Compulsary");	
		$.unblockUI();
	}
	
	
}

</script>
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>