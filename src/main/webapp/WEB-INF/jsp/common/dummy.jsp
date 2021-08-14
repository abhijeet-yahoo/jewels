<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>





		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				  
				       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
				       
				      		 <h4 class="modal-title" id="myModalLabel">BAG HISTORY</h4>
				      	
				
				      </div>
				      
				      <div class="modal-body">
						
						 <div>
				       	  <div class="col-sm-2">
				       		
				       	  </div>
				       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
						  <div class="col-sm-4">		       	
				       		<input type="search" id="searchDept"  class="search form-control" placeholder="Dept Search " />
				       	  </div>
				       </div>		       
				       
					<div>
						<table id="bagHistoryTabId" data-toggle="table"
							data-toolbar="#toolbar" data-pagination="false" data-click-to-select="true"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
							data-striped="true">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
									
									<th data-field="department" data-align="left" data-sortable="true">Department</th>
									<th data-field="department" data-align="left" data-sortable="true">Department</th>
									 
								</tr>
							</thead>
						</table>
					</div>
				       
				      </div>
				      <div class="modal-footer">
				      	<a href="javascript:popRefreshBagHistoryTab(206709)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				        <button type="button" class="btn btn-primary" onclick="javascript:popSave()">Save changes</button>
				      </div>
				    </div>
				  </div>
				</div>