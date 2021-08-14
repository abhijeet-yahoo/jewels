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



	<!--------- Wip modal --------->
	
	<div class="modal fade" id="myWipModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Wip Detail</h4>
	      </div>
	      
	      <div class="modal-body">
			
 	<div>
			<table id="wipDetailTblId" class="MarkTotal" data-toggle="table"
				data-show-export="true" data-show-columns="true"
				data-side-pagination="server"
				data-pagination="false" data-height="450">
				<thead>
					<tr class="btn-primary">
						<th data-field="invNo" data-align="left" data-sortable="true">Order
							No.</th>
						

						<th data-field="styleNo" data-align="left" data-sortable="true">Style No.</th>
						<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
						<th data-field="color" data-align="left" data-sortable="true">Color</th>
						<th data-field="ordQty" data-align="left" data-sortable="true">Order
							Qty</th>
						<th data-field="balQty" data-align="left" data-sortable="true">Balance
							Qty</th>
						<th data-field="wax" data-align="left" data-sortable="true">Wax</th>
						<th data-field="waxSet" data-align="left" data-sortable="true">Wax
							Set</th>
						<th data-field="casting" data-align="left" data-sortable="true">Casting</th>
						<th data-field="grinding" data-align="left" data-sortable="true">Grinding</th>
						<th data-field="filling" data-align="left" data-sortable="true">Filing</th>
						<th data-field="prepol" data-align="left" data-sortable="true">Pre
							Polish</th>
						<th data-field="metalSet" data-align="left" data-sortable="true">Metal
							Set</th>
						<th data-field="pol" data-align="left" data-sortable="true">Polish</th>
						<th data-field="readyExp" data-align="left" data-sortable="true">Redy
							Exp</th>
						<th data-field="exp" data-align="left" data-sortable="true">Exp</th>	
						<th data-field="rej" data-align="left" data-sortable="true">Rej</th>
						<th data-field="cancel" data-align="left" data-sortable="true">Cancel</th>
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
	
	
	<script type="text/javascript"></script>