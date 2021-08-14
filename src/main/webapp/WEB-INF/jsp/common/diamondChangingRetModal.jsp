<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<!--------- Diamond Changing Return modal --------->

	<div class="modal fade" id="myDiamondChangingRetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Diamond Changing Return</h4>
	      </div>
	      
	      <div class="modal-body">
	      
	      		<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Type</label>
						</div>
					
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Employee</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Part Name</label>
						</div>
					
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<select id="changTypeId"  class="form-control">
								<option value="0" selected>- Select Type -</option>
								<option value="1">Normal</option>
								<option value="2">Broken</option>
								<option value="3">Fallen</option>
							</select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<div id="employeeId">
								<input type="text" id="xx" name="xxx" />
							</div>
						</div>
						<!-- <div class="col-lg-3 col-sm-3" id="partNmDivId">
							<input type="text" id="partNmId" name="partNmId" class="form-control" readonly="readonly">
						</div>
						
					-->	<div class="col-lg-3 col-sm-3">
							<input type="text" id="mPartNmId" name="mPartNmId" class="form-control" readonly="readonly">
						</div>
						
					</div>
				</div>
	      	
	      	
	      		<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
	      		
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Stone Type</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Shape</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Quality</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Size/MM</label>
						</div>
					</div>
				</div>
				 
			 	<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modRetStoneType" name="modRetStoneType" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modRetShape" name="modRetShape" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modRetQuality" name="modRetQuality" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modRetSize" name="modRetSize" class="form-control" readonly="readonly">
						</div>
						
						
					</div>
				</div> 
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Bag Stone</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Bag Carat</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Ret Stone</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Ret Carat</label>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modRetStone" name="modRetStone" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modRetCarat" name="modRetCarat" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modRetTextStone" name="modRetTextStone" class="form-control" value="0" onchange="popRetStoneValidation()">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modRetTextCarat" name="modRetTextCarat" class="form-control" value="0.0" onchange="popRetCaratValidation()">
						</div>
						<!-- <div class="col-lg-3 col-sm-3">
							<input type="button" id="transferModal" value="Transfer" onclick="javascript:popTransferChangingNew()" class="btn btn-primary">
						</div> -->
					</div>
				</div> 
				
				
				
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<input type="button" id="transferRetModal" value="Transfer" onclick="javascript:popTransferChangingReturn()" class="btn btn-primary">
	       	 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       	 <input type="hidden" id="modalTranDtRetId" name="modalTranDtRetId">
	       	  <input type="hidden" id="modalTranDate" name="modalTranDate">
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">
	
		
	function bringTranDtRetDetails(tranDtId){
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/changing/trandtret/detail.html?tranDtId="+tranDtId,
			type:"GET",
			datatype:"JSON",
			success:function(data){
				
				$.each(JSON.parse(data), function(key,value){
						 $('#'+key).val(value)
				    });
				
				 
				 $('#changTypeId').val('0');
				 $('#modRetTextStone').val('0');
				 $('#modRetTextCarat').val('0.0');
				 $('#employee\\.id').val('');
				
			}
		})
		
	}
	
	
	
	function popTransferChangingReturn(){
		
		if($('#changTypeId').val() === '0' || $('#changTypeId').val() === ''){
			displayMsg(this,null,"Type Is Compulsary");
			return;
		}else{
			if($("#changTypeId :selected").text() !== 'Normal'){
				if($('#employee\\.id').val() === null || $('#employee\\.id').val() === ''){
					displayMsg(this,null,"Employee Is Compulsary");
					return
				}
			}
		}
		
		if($('#modRetTextStone').val() === '' || Number($('#modRetTextStone').val()) <= 0){
			displayMsg(this,null,"Ret Stone Should Be Greater Than Zero(0)");
			return;
		}
		
		if($('#modRetTextCarat').val() === '' || Number($('#modRetTextCarat').val()) <= 0){
			displayMsg(this,null,"Ret Carat Should Be Greater Than Zero(0)");
			return;
		}

		$('#transferRetModal').attr('disabled', 'disabled');
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/diamondchanging/returnsingle.html?tranDtId="+$('#modalTranDtRetId').val()+"&retStone="+$('#modRetTextStone').val()+
				"&retCarat="+$('#modRetTextCarat').val()+"&changingType="+$("#changTypeId :selected").text()+"&employeeId="+$('#employee\\.id').val()+"&tranDate="+$('#modalTranDate').val(),
			type:"POST",
			success:function(data){
				$('#transferRetModal').removeAttr('disabled');
				
				if(data === 'success'){
					displayInfoMsg(this,null,"Record Transfered Successfully");
					$('#myDiamondChangingRetModal').modal('hide');
					popImpStnAdj();
				}else if(data.indexOf("Warning") != "-1"){
						displayMsg(this,null,data);
				}else{
					console.log(data);
					displayMsg(this,null,data)
				}
				
			}
		});
		
		
		
	}
	
	
	function popRetStoneValidation(){
		$('#modRetTextCarat').val('0.0');
		if(Number($('#modRetTextStone').val()) > Number($('#modRetStone').val())){
			displayMsg(this,null,"Ret Stone Cannot Be Greater Than BagStone");
			$('#modRetTextStone').val('0');
			$('#modRetTextCarat').val('0.0');
			return;
		}else if(Number($('#modRetTextStone').val()) === Number($('#modRetStone').val())){
			$('#modRetTextCarat').val($('#modRetCarat').val());
			$('#modRetTextCarat').attr('readonly','readonly');
		}else if(Number($('#modRetTextStone').val()) < Number($('#modRetStone').val())){
			//$('#modRetTextCarat').val('0.0');
			$('#modRetTextCarat').removeAttr('readonly','readonly');
		}
	}
	
	
	
	function popRetCaratValidation(){
		if(Number($('#modRetTextCarat').val()) > Number($('#modRetCarat').val())){
			displayMsg(this,null,"Ret Carat Cannot Be Greater Than BagCarat");
			$('#modRetTextCarat').val('0.0');
			return;
		}else if(Number($('#modRetTextCarat').val()) === Number($('#modRetCarat').val())){
			$('#modRetTextStone').val($('#modRetStone').val());
			$('#modRetTextStone').attr('readonly','readonly');
		}else if(Number($('#modRetTextCarat').val()) < Number($('#modRetCarat').val())){
			//$('#modRetTextStone').val('0.0');
			
			$('#modRetTextStone').removeAttr('readonly','readonly');
			
			if(Number($('#modRetTextStone').val()) === Number($('#modRetStone').val())){
				$('#modRetTextStone').val('0');
				
			}
			
			
		}
	}
	
	
	
	
	
	
	
	
	</script>
	