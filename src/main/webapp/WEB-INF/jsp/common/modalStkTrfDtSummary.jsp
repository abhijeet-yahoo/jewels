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
   
   
   
    <div class="modal fade" id="myStkTrfDtSummaryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

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

				
					
   
   <div id="stkTrfDtSummaryDivId">
			
			
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
					</div>
					 -->
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
					
					
					


</div>
			
			
			</div>
			
   
   
   					
   					
   			</div>
   			
   			</div>
   			</div>
   			</div>			
   			
   			
   			
   			
   			
   			
   					