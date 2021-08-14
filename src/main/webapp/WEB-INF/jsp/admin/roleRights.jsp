<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Role Rights</span>
	</div>
	
		<div class="body">
		

			<div class="row">&nbsp;</div>
		
		 		   	<div class="row">	
		     
		        <div class="col-sm-6"></div>
		        	
		        <div class="col-sm-6 text-right">
		          	<input type="button" value="Save" class="btn btn-primary" id="rightSaveBtnId" onclick="javascript:popRightSave()" />
					<input type="button" value="Close" class="btn btn-danger" onclick="javascript:popRightClose()"/>
		         </div>
           	 
           
              
            
             
            
            
     <form:form commandName="roleRights" 
     	action="" method="POST" 
     	class="form-horizontal masterForm">
		
			<div id="msgDiv" style="display: none">
			 	<div id="msgId" class="alert alert-success"></div>
			 </div>
		
              <div class="box-body">
                <div class="form-group">
                  <label for="role" class="col-sm-1 control-label">Role</label>

                  <div class="col-sm-4">
                    <form:select path="roleMast.id" class="form-control" onchange="javascript:getModals()">
						<form:option value="" label="- Select Role -" />
						<form:options items="${roleMastMap}" />
				   </form:select>
                  </div>
                </div>
              
			      <div class="row">
			      
			      <div class="col-sm-1">
			      </div>
			        <div class="col-sm-6">
			          	<div id="treeview-checkable"></div>
			       	</div>
			        
			        <div class="col-sm-4">
			         		<div class="panel panel-primary" id="checkRightId">
			         		<div class="panel-body">
			         		
			         		 <input type="checkbox" id="canAdd" name="canAdd" value="add" onchange="javascript:getRight()"> Add<br>
  							 <input type="checkbox" id="canEdit" name="canEdit" value="edit" onchange="javascript:getRight()"> Edit<br>
  							 <input type="checkbox" id="canDelete" name="canDelete" value="delete" onchange="javascript:getRight()"> Delete<br>
  							 <input type="checkbox" id="canCopy" name="canCopy" value="copy" onchange="javascript:getRight()"> Copy<br>
  							 <input type="checkbox" id="canView" name="canView" value="view" onchange="javascript:getRight()"> View<br>
  							 <input type="checkbox" id="canPreview" name="canPreview" value="preview" onchange="javascript:getRight()"> Preview<br>
			         		</div>
			         	
			         		</div>
			          
			        </div>
			        
			          <div class="col-sm-1">
			          </div>
			        
			        
			        
			       <!--  <div class="col-sm-2">
			          <h2>Events</h2>
			          <div id="checkable-output"></div>
			        </div> -->
			        
			         <!-- <div class="col-sm-6">
			        <div>
					<table id="roleRightTableId" data-toggle="table"
						data-toolbar="#toolbar" data-pagination="false"
						data-side-pagination="server" data-striped="true" 
						data-height="520">
						<thead>
							<tr class="btn-primary">
								<th data-field="menuHeading" data-align="left">Modal</th>
								<th data-field="canRights"  data-formatter="inputFormatter" data-events="rightsEvents">Rights</th>
								<th data-field="canAdd"  	data-formatter="inputFormatter" data-events="addEvents">Add</th>
								<th data-field="canEdit"  	data-formatter="inputFormatter" data-events="editEvents">Edit</th>
								<th data-field="canDelete"  data-formatter="inputFormatter" data-events="deleteEvents">Delete</th>
								<th data-field="canView"  data-formatter="inputFormatter" data-events="viewEvents">View</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>	
				 -->
			        
			        
			        
			        
			      </div>
           
             
          </div>
              <!-- /.box-body -->
          
            </form:form>
            
            
            
          </div>
         





















<!--  <div class="container">
   
      <div class="row">
      
        <div class="col-sm-4">
         
          <div id="treeview-checkable"></div>
        </div>
        <div class="col-sm-4">
          <h2>Events</h2>
          <div id="checkable-output"></div>
        </div>
      </div>
      <br/>
    
  </div> -->

</div>


</div>
  	<script type="text/javascript">

  		$(function() {
  			
  			 $('#checkRightId').hide();
  			 
  			 
  		});
  		
  		/* function checknode(){
  			for (var i=0; i<children.length; i++) {
		        	
		        	$('#treeview-checkable').treeview('checkNode', [children[i].nodeId, { silent: true } ]);
		        }
  		}
  		 */
  		
  		
  		
  		
  		function myTest(children,str){
  			
  			for (var i=0; i<children.length; i++) {
  				
		        	$('#treeview-checkable').treeview(str, [children[i].nodeId, { silent: true } ]);
		        	if(str ==='uncheckNode'){
		        		delete rightObj[children[i].text];	
		        	}else{
		        		rightObj[children[i].text]="add,edit,delete,copy,view,preview";
		        	}
		        	var childNode=children[i]['nodes'];
			        	 if(childNode !=null){
			        		 myTest(childNode,str);
			        	 }
		        	
		        	
		        }
  		}
  		 
  		 
  		 function  myParent(node,str){
  			 $('#treeview-checkable').treeview(str, [node.nodeId, { silent: true } ]);
  			rightObj[node.text]="add,edit,delete,copy,view,preview";
  			 var grandParent=$('#treeview-checkable').treeview('getParent', node.nodeId);
  			  if (grandParent.nodeId !=undefined){
 			   myParent(grandParent,str);
 		   }
  			
  			 
  		 }
  		
  		 
		var selNodeId="";	  		
  		 var rightObj ={};
  		 var menuText="";
  		function getModals() {
  			$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });  		
  			 $('#canAdd').prop('checked',false);
        	 $('#canEdit').prop('checked',false);
        	 $('#canDelete').prop('checked',false);
        	 $('#canCopy').prop('checked',false);
        	 $('#canView').prop('checked',false);
        	 $('#canPreview').prop('checked',false);
  			rightObj ={};
  			$.ajax({
  				url : "/jewels/admin/roleRights/listing.html?roleMastId="
						+ $('#roleMast\\.id').val(),
				type:"GET",
				dataType:'json',
				success: function(data){
					$.each(data.rightsDetail, function(index,element){
						//rightObj[element.menuMastId]=element.canAdd+','+element.canEdit;
						rightObj[element.menuHeading]=element.rightVal;
					
						
						
						
						
						
					});
					
					
					
					
					
					
					
					/* ----------------------- */
					
					
					 $.ajax({
				    		url:"/jewels/admin/roleRights/list.html",
				    		type:"GET",
				    		success:function(data){
				    			
				    			
				    			
				    		
				    			   var $checkableTree = $('#treeview-checkable').treeview({
				    			          data: data,
				    			          showIcon: false,
				    			          showCheckbox: true,
				    			          checked:true,
				    			          onNodeChecked: function(event, node) {
				    			        	  menuText=node.text;
				    			        	  rightObj[node.text]="add,edit,delete,copy,view,preview";
				    			        	  $('#canAdd').prop('checked',true);
				    			        	  $('#canEdit').prop('checked',true);
				    			        	  $('#canDelete').prop('checked',true);
				    			        	  $('#canCopy').prop('checked',true);
				    			        	  $('#canView').prop('checked',true);
				    			        	  $('#canPreview').prop('checked',true);
				    			        	  
				    			        	var parentNode=$('#treeview-checkable').treeview('getParent', node);
				    			        	
				    			        	
				    			        	
				    			        	   if (parentNode.nodeId !=undefined){
				    			        		   
				    			        		   $('#treeview-checkable').treeview('checkNode', [parentNode.nodeId, { silent: true } ]);
				    			        		   rightObj[parentNode.text]="add,edit,delete,copy,view,preview";
				    			        		   var grandParent=$('#treeview-checkable').treeview('getParent', parentNode.nodeId);
				    			        		   		    			        		   
				    			        	
				    			        		   if (grandParent.nodeId !=undefined){
				    			        			   myParent(grandParent,'checkNode');
				    			        		   }
				    			        		   
				    			        		   
				    			        	   }
				    			        	   
				    			        	   
				    			        	  
				    			        	  if (typeof node['nodes'] != "undefined") {
				  		    				        var children = node['nodes'];
				  		    				        
				  		    				      for (var i=0; i<children.length; i++) {
				  		    		  				
				  		    				    	 		  		    				    	  
				  		    			        	$('#treeview-checkable').treeview('checkNode', [children[i].nodeId, { silent: true } ]);
				  		    			        	rightObj[children[i].text]="add,edit,delete,copy,view,preview";
				  		    			        	
				  		    			        	 var childNode=children[i]['nodes'];
				  		    			        	 if(childNode !=null){
				  		    			        		 myTest(childNode,'checkNode');
				  		    			        	 }
				  		    			        	
				  		    			        	
				  		    			        }
				  		    				      
				  		    				    }
				    			    
				    			          },
				    			          
				    			          
				    			          onNodeSelected: function (event, node) {
				    			        	  	 $('#canAdd').prop('checked',false);
					    			        	 $('#canEdit').prop('checked',false);
					    			        	 $('#canDelete').prop('checked',false);
					    			        	 $('#canCopy').prop('checked',false);
					    			        	 $('#canView').prop('checked',false);
					    			        	 $('#canPreview').prop('checked',false);
					    			        	selNodeId=node.nodeId;		    			        	 
				    			        	  	menuText=node.text;
				    			        		var vtext =rightObj[menuText];
				    			        	
				    			        	 if(vtext !=undefined){
				    			        		 var vtest= rightObj[menuText].split(',');
				    			        	
				    			        		 
				    			        		 for(var i=0;i<vtest.length;i++){
				    			        			 
				    			        			 if(vtest[i]==='add'){
				    			        				 $('#canAdd').prop('checked',true);
				    			        			 }
				    			        			 
				    			        			 if(vtest[i]==='edit'){
				    			        				 $('#canEdit').prop('checked',true);
				    			        			 }
				    			        			 
				    			        			 if(vtest[i]==='delete'){
				    			        				 $('#canDelete').prop('checked',true);
				    			        			 }
				    			        			 
				    			        			 if(vtest[i]==='copy'){
				    			        				 $('#canCopy').prop('checked',true);
				    			        			 }
				    			        			 
				    			        			 if(vtest[i]==='view'){
				    			        				 $('#canView').prop('checked',true);
				    			        			 }
				    			        			 
				    			        			 if(vtest[i]==='preview'){
				    			        				 $('#canPreview').prop('checked',true);
				    			        			 }
				    			        		 }
				    			        		 
				    			        	 }
				    			        	  
				    			          },
				    			          
				    			          onNodeUnchecked: function (event, node) {
				    			        	  delete rightObj[node.text];
				    			        	  $('#canAdd').prop('checked',false);
				    			        	  $('#canEdit').prop('checked',false);
				    			        	  $('#canDelete').prop('checked',false);
				    			        	  $('#canCopy').prop('checked',false);
				    			        	  $('#canView').prop('checked',false);
				    			        	  $('#canPreview').prop('checked',false);
				    			        			    			        	  
				    			        	
				    			        	  
				    			        	  
				    			        	  
				    			        	  if (typeof node['nodes'] != "undefined") {
				  		    				        var children = node['nodes'];
				  		    				        
				  		    				      for (var i=0; i<children.length; i++) {
				  		    				        				
				  		    				        	$('#treeview-checkable').treeview('uncheckNode', [children[i].nodeId, { silent: true } ]);
				  		    				        	delete rightObj[children[i].text];
				  		    				        	var childNode=children[i]['nodes'];
					  		    			        	 if(childNode !=null){
					  		    			        		 myTest(childNode,'uncheckNode');
					  		    			        	 }
				  		    				        	
				  		    				        	
				  		    				        }
				  		    				    }
				  		    			
				    			        	  
				    			            
				    			          }
				    			          
				    			          
				    			      
				    			          
				    			          
				    			          
				    			          
				    			        });
				    			   
				    			   
				    			  
				    			   
				    			  
				    			 
				    			   
				    			   
				    			
				    			   
				    			   
				    			   var data1 =$('#treeview-checkable').treeview('getUnselected', 0);
				    			   
				    			   for (var i=0; i<data1.length; i++) {
				    				   
				    				   var nodeid = data1[i].nodeId;
				    				     var nodetext=data1[i].text;
				    				  			   
				    				   
				    				   for (var key in rightObj) {
				    				         if (rightObj.hasOwnProperty(key)) {
			    				        	 if(key === nodetext){
				    				        		 $('#treeview-checkable').treeview('checkNode', [Number(nodeid), { silent: true } ]);
				    				        		 
				    				        	 }
				    				           
				    				         }
				    				       }
				    				   
				    				   
				    			   }
				    			   
				    			
				    			   $('#treeview-checkable').treeview('collapseAll', { silent: true });
				    			   $('#checkRightId').show(); 
				    			   
				    			   
				    			   
				    			  

				    			   
				    			   
				    				$.unblockUI();

				    		}
		  			
				    		
				    });
					
					
					
					
				}
  			
  			});
  			
  			
  			/* $('#canAdd').change(function() {
  			    if(this.checked) {
  			          			    	
  			    }else{
  			    	
  			    }
  			}); */
  			
  			
  			
  			
  			
  					
  		}
  			
  		function getRight(){
  			
  			var xx="";
  			if($('#canAdd').prop('checked')){
  				if(xx !=""){
  					xx= xx+",add";
		 		}else{
		 			xx="add";
		 		}
  			  	
  			}
  			if($('#canEdit').prop('checked')){
  				if(xx !=""){
  					xx= xx+",edit";
		 		}else{
		 			xx="edit";
		 		}
  			}
  			if($('#canDelete').prop('checked')){
  				if(xx !=""){
  					xx= xx+",delete";
		 		}else{
		 			xx="delete";
		 		}
  			}
  			if($('#canCopy').prop('checked')){
  				if(xx !=""){
  					xx= xx+",copy";
		 		}else{
		 			xx="copy";
		 		}
  			}
  			if($('#canView').prop('checked')){
  				if(xx !=""){
  					xx= xx+",view";
		 		}else{
		 			xx="view";
		 		}
  			}
  			if($('#canPreview').prop('checked')){
  				if(xx !=""){
  					xx= xx+",preview";
		 		}else{
		 			xx="preview";
		 		}
  			}
  			
			
  			 rightObj[menuText]=xx;
  				
  			$('#treeview-checkable').treeview('checkNode', [selNodeId, { silent: true } ]);
  						
				
			}
  		
  		function popRightClose(){
  			var tabId =  $('ul.ui-sortable li.active a').attr('href');
			 tabId = tabId.slice(1);
			 var tabs = $('#tabs').bootstrapDynamicTabs();
			 tabs.closeById(tabId);
  		}
  		
  		function popRightSave(){
  			$('#rightSaveBtnId').attr('disabled', 'disabled');
  			
  			$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });  			
  			$.ajax({
  				url:"/jewels/admin/roleRights/save.html",
				type:"POST",
	    		data :jQuery.param({ roleMastId: $('#roleMast\\.id').val(), roleRightObj : JSON.stringify(rightObj)}) ,
	    		success:function(data){
	    			$.unblockUI();
	    			if(data ==="1"){
	    			displayInfoMsg(this,null,"Rights Save Successfully")
	    			}else{
	    				displayMsg(this,null,"Error ")
	    			}
	    			
	    			$('#rightSaveBtnId').removeAttr('disabled');
	    		}
  			});
  		}
			
  	</script>

  
  
  
  
  <script src="<spring:url value='/js/utils/bootstrap-treeview.js' />"></script>
  <script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
  
  
  
  
  
  
  
  
  