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

	<div class="modal fade" id="myOrderMtModal">

		  <div class="modal-dialog modal-lg">
		      <div class="modal-content">
		      
		      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Order List</h4>
	      </div>
	      
	 <div class="modal-body">
	        
	  	<div>
		<table  data-toggle="table" id="modalOrderMtTblId"
			data-side-pagination="server" 
			 data-pagination="true"	data-height="520" data-search="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="party" data-sortable="true">Client</th>
					<th data-field="invNo" data-align="left" data-sortable="true">Order No</th>
				    <th data-field="refNo" data-sortable="true">Reference No</th>
										
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
	
	
		
	