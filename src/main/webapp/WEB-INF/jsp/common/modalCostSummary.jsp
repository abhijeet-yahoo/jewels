<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>


<!--------- Quot Summary modal --------->
	
	<div class="modal fade" id="myCostSumModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Invoice Summary</h4>
	      </div>
	      
	      
	      
	      		<div id="costSumDivId">
	      		
	
	      		
	      			<div class="col-sm-6 form-group">
	      			
	      				<label for="inputLabel3" class="control-lable">Pcs</label>
	      			
					   <input type="text" id="totPcs" class="form-control" name="totPcs" readonly="readonly"/>
					</div>
						  
					<div class="col-sm-6 form-group">
							<label for="inputLabel3" class="control-lable">Gross Wt</label>
							<input type="text" id="totGrossWt" class="form-control" name="totGrossWt" readonly="readonly"/>
					</div>
					
						
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Net Wt</label>
					  <input type="text" id="totNetWt" class="form-control" name="totNetWt" readonly="readonly"/>
					</div>
					
					
					
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Stone</label>
					   <input type="text" id="totStone" class="form-control" name="totStone" readonly="readonly"/>
					</div>
					
				
						  
					<div class="col-sm-6 form-group">
							<label for="inputLabel3" class="control-lable">Carat</label>
							<input type="text" id="totCarat" class="form-control" name="totCarat" readonly="readonly"/>
					</div>
						
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Find. Wt</label>
					 	<input type="text" id="totCompWt" class="form-control" name="totCompWt" readonly="readonly"/>
					</div>
					
				
	      			<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Metal Value</label>
					 	<input type="text" id="totMetalVal" class="form-control" name="totMetalVal" readonly="readonly"/>
					</div>
					
					
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Stone Value</label>
					 	<input type="text" id="totStnVal" class="form-control" name="totStnVal" readonly="readonly"/>
					</div>
					
					
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Finding Value</label>
					 	<input type="text" id="totCompVal" class="form-control" name="totCompVal" readonly="readonly"/>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Labour Value</label>
					 	<input type="text" id="totLabVal" class="form-control" name="totLabVal" readonly="readonly"/>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Setting Value</label>
					 	<input type="text" id="totSetVal" class="form-control" name="totSetVal" readonly="readonly"/>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Hdlg Value</label>
					 	<input type="text" id="totHdlgVal" class="form-control" name="totHdlgVal" readonly="readonly"/>
					</div>
					
					
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Fob</label>
					 	<input type="text" id="totFobVal" class="form-control" name="totFobVal" readonly="readonly"/>
					</div>


					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Discount</label>
					 	<input type="text" id="totDiscAmt" class="form-control" name="totDiscAmt" readonly="readonly"/>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="inputLabel3" class="control-lable">Final Price</label>
					 	<input type="text" id="totFinalPrice" class="form-control" name="totFinalPrice" readonly="readonly"/>
					</div>
					
					
					
					
	     
	      		
	      		
	      		
	      		
					
	      		</div>
	   	      
	      <div class="modal-footer">
	     	 <div class="row">&nbsp;</div>
	        	<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	        </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	<script type="text/javascript">
	
	</script>