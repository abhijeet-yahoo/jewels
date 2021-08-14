<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>
   
   
   
    <div class="modal fade" id="myPackDtSummaryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>

				<h4 class="modal-title" id="myModalLabel">Summary</h4>
			</div>

			<div class="modal-body">

				
					
   
   <div id="packDtSummaryDivId">
			
			
			<div class="row">

					<div class="col-sm-6 form-group">

						<label for="totPcs" class="col-sm-4 control-label">Pcs</label>
						<div class="col-sm-6">
							<input type="text" id="totPcs" class="form-control" name="totPcs"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
						<div class="col-sm-6 form-group">
						<label for="grossWt" class="col-sm-4 control-label">Gross Wt</label>
						<div class="col-sm-6">
							<input
						type="text" id="grossWt" class="form-control" name="grossWt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

				<div class="col-sm-6 form-group">
						<label for="netWt" class="col-sm-4 control-label">Net Wt</label>
						<div class="col-sm-6">
							<input
						type="text" id="netWt" class="form-control" name="netWt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<!-- <div class="col-sm-6 form-group">
						<label for="pureWt" class="col-sm-4 control-label">Pure Wt</label>
						<div class="col-sm-6">
							<input
						type="text" id="pureWt" class="form-control" name="pureWt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div> -->
					
					<div class="col-sm-6 form-group">
						<label for="stone" class="col-sm-4 control-label">Dia Stone</label>
						<div class="col-sm-6">
							<input
						type="text" id="diaStone" class="form-control" name="diaStone"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="carat" class="col-sm-4 control-label">Dia Carat</label>
						<div class="col-sm-6">
							<input
						type="text" id="diaCarat" class="form-control" name="diaCarat"
						readonly="readonly" style="text-align: right;"/> 
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="colStone" class="col-sm-4 control-label">Col Stone</label>
						<div class="col-sm-6">
							<input
						type="text" id="colStone" class="form-control" name="colStone"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="colCarat" class="col-sm-4 control-label">Col Carat</label>
						<div class="col-sm-6">
							<input
						type="text" id="colCarat" class="form-control" name="colCarat"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totCompWt" class="col-sm-4 control-label">Finding Wt</label>
						<div class="col-sm-6">
							<input
						type="text" id="totCompWt" class="form-control" name="totCompWt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totMetalVal" class="col-sm-4 control-label">Metal Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totMetalVal" class="form-control" name="totMetalVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totStnVal" class="col-sm-4 control-label">Stone Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totStnVal" class="form-control" name="totStnVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totCompVal" class="col-sm-4 control-label">Finding Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totCompVal" class="form-control" name="totCompVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totLabVal" class="col-sm-4 control-label">Labour Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totLabVal" class="form-control" name="totLabVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totSetVal" class="col-sm-4 control-label">Setting Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totSetVal" class="form-control" name="totSetVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totHdlgVal" class="col-sm-4 control-label">Handling Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totHdlgVal" class="form-control" name="totHdlgVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totFobVal" class="col-sm-4 control-label">Fob</label>
						<div class="col-sm-6">
							<input
						type="text" id="totFobVal" class="form-control" name="totFobVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-6 form-group">
						<label for="totDiscAmt" class="col-sm-4 control-label">Discount Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totDiscAmt" class="form-control" name="totDiscAmt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					
					<div class="col-sm-6 form-group">
						<label for="totFinalPrice" class="col-sm-4 control-label">Final Price</label>
						<div class="col-sm-6">
							<input
						type="text" id="totFinalPrice" class="form-control" name="totFinalPrice"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					
					
					
					


</div>
			
			
			</div>
			
   	
		<div class="modal-footer">
		
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				
				</div>	
   
   					
   					
   			</div>
   			
   			
   			
   			
   			
   			</div>
   			</div>
   			</div>			
   			
   			
   			
   			
   			
   			
   					