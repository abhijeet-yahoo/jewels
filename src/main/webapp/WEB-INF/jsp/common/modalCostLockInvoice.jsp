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


<!--------- Lock-Unlock Invoice modal --------->
	
	<div class="modal fade" id="lockInvoceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Lock Invoice</h4>
	      </div>
	      
	      <table  id="costLockInvoiceTblId" data-toggle="table" 
							data-click-to-select="true">
							<thead>
							  <tr>
							 
								
								<th data-field="setNo" data-sortable="false" data-align="center">Set No</th>
								<th data-field="rLock" data-sortable="false" data-align="center" data-formatter="invoceLockInputFormatter">Lock</th>
								<th data-field="actionLock" data-align="center" >Lock-Unlock</th>
							 </tr>
							</thead>
						</table> 
						
						<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				
			</div>
	      
	      
	      </div>
	     </div>
	    </div>  