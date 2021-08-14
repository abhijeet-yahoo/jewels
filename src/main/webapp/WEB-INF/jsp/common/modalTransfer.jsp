<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<!-- <style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style> -->

	<div class="modal fade" id="myTransferModal">

		  <div class="modal-dialog modal-lg">
		      <div class="modal-content">
		      
		      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Transfer Detail</h4>
	      </div>
	      
	 <div class="modal-body">
	        
	  	<div>
		<table  data-toggle="table" id="modalTransferTblId" data-side-pagination="server" data-search-time-out="1500"
			 data-pagination="true"	data-height="520" data-search="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
					<th data-field="orderNo" data-align="left" data-sortable="true">Order</th>
					<th data-field="refNo" data-align="left" data-sortable="true">Ref </th>
					<th data-field="bagNo" data-align="left" data-sortable="true">Bag </th>
					<th data-field="styleNo" data-align="left" data-sortable="true">Style No</th>
					<th data-field="qty" data-align="left" data-sortable="true">Qty</th>
					<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
										
				</tr>
			</thead>
		</table>
	</div>
	       
	       
	        </div>
	        
	        <div class="modal-footer">
	        	
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		   </div>
	      
		      </div>
	
	</div>

	</div>
	
	
		
	