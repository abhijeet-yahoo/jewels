<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<!--------- Diamond Changing modal --------->

	<div class="modal fade" id="myDiamondChangingNewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Diamond Changing</h4>
	      </div>
	      
	      <div class="modal-body">
			
				
				<div class="row">
					<div class="col-xs-12">
					
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Part Name</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Stone Type</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Shape</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Quality</label>
						</div>
					
					</div>
				</div>
				 
			 	<div class="row">
					<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3" id="partNmDivId">
							<input type="text" id="partNmId" name="partNmId" class="form-control" readonly="readonly">
						</div>
					
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modStoneType" name="modStoneType" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modShape" name="modShape" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modQuality" name="modQuality" class="form-control" readonly="readonly">
						</div>
					
					</div>
				</div> 
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Size/MM</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Avl In Stock(Carat)</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Trf Stone</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Trf Carat</label>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modSize" name="modSize" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modBalCarat" name="modBalCarat" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modReqStone" name="modReqStone" class="form-control" value="0">
						</div>
						<div class="col-lg-3 col-sm-3">
							<input type="text" id="modReqCarat" name="modReqCarat" class="form-control" value="0.0" onchange="popDiamondChangeValidation()">
						</div>
						<!-- <div class="col-lg-3 col-sm-3">
							<input type="button" id="transferModal" value="Transfer" onclick="javascript:popTransferChangingNew()" class="btn btn-primary">
						</div> -->
					</div>
				</div> 
				
				
				
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<input type="button" id="transferModal" value="Transfer" onclick="javascript:popTransferChangingNew()" class="btn btn-primary">
	       	 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       	 <input type="hidden" id="modalTranDtId" name="modalTranDtId">
	       	  <input type="hidden" id="modalTranDate" name="modalTranDate">
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	<script type="text/javascript">
	
		
	function bringTranDtDetails(tranDtId){
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/changing/trandt/detail.html?tranDtId="+tranDtId,
			type:"GET",
			datatype:"JSON",
			success:function(data){
				
				if(data.indexOf("Warning") != "-1"){
					displayMsg(this,null,data);
					
				}else{
					$.each(JSON.parse(data), function(key,value){
						 $('#'+key).val(value)
				    });	
				}
				
				 
				 
								
			}
		})
		
	}
	
	
	
	function popDiamondChangeValidation(){
		if('${allowNegativeDiamond}'==false){
			
			if(Number($('#modReqCarat').val()) > Number($('#modBalCarat').val())){
				displayMsg(this,null,"Trf Carat Cannot Be Greater Than Available Stock Carat");
				$('#transferModal').attr('disabled','disabled')
			}else{
				$('#transferModal').removeAttr('disabled','disabled')
			}
			
		}else{
			$('#transferModal').removeAttr('disabled','disabled')
		}
		
		
		
		
	}
	
	
	
	
	function popTransferChangingNew(){
		
		$('#transferModal').attr('disabled', 'disabled');
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/diamondchanging/addnew.html?tranDtId="+$('#modalTranDtId').val()+"&reqStone="+$('#modReqStone').val()+
				"&reqCarat="+$('#modReqCarat').val()+"&tranDate="+$('#modalTranDate').val(),
			type:"POST",
			success:function(data){
			
				$('#transferModal').removeAttr('disabled');
				
				if(data === 'success'){
					displayInfoMsg(this,null,"Record Transfered Successfully");
					$('#myDiamondChangingNewModal').modal('hide');
					popImpStnAdj();
					
				}else if(data.indexOf("Warning") != "-1"){
					displayMsg(this,null,data);
					
				}else{
					displayMsg(this,null,data)
					
				}
				
				
			}
		})
		
	}
	
	
	
	</script>
	
	
