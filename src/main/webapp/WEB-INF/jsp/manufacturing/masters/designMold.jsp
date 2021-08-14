<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class ="panel panel-success" style="width: 100%">

	<div class = "panel-body">


		<div>
			<div id="toolbar">
				<a class="btn btn-info" style="font-size: 15px" type="button" onClick="openDesingMoldEntryTab()"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add
				</a>
			</div>
			<div>
				<table id="designMoldTabId" data-toggle="table"
					data-toolbar="#toolbar" data-pagination="true"
					data-side-pagination="server"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
					data-striped="true"  data-height="500">
					<thead>
						<tr class="btn-primary">
							<th data-field="design" data-align="left" data-sortable="true">StyleNo</th>
							<th data-field="designMoldType" data-align="left" data-sortable="true">Type</th>
							<th data-field="drawerNo" data-align="left" data-sortable="true">Drawer No</th>
							<th data-field="rackNo" data-sortable="true">Rack No</th>
							<th data-field="mouldQty" data-sortable="true">Mould Qty</th>
							<th data-field="uploadImage" >Image</th>
							<th data-field="action1" data-align="center">Edit</th>
							<th data-field="action2" data-align="center">Delete</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		
		
		<div>
			<div class="row">&nbsp;</div>
		</div>

		
			
			<div id="addDesignMoldDivId" style="display: none">
					<div class="form-group">
						<form:form commandName="designMold" id="designMoldEnt"
							action="/jewels/manufacturing/masters/designMold/add.html"
							cssClass="form-horizontal designMoldEntForm">

							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary">
										<th class="col-sm-2">Style No</th>
										<th class="col-sm-2">Type</th>
										<th class="col-sm-2">Drawer No</th>
										<th class="col-sm-2">Rack No</th>
										<th class="col-sm-2">Mould Qty</th>
										<th class="col-sm-2"></th>  
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="col-sm-2"><form:input path="design.mainStyleNo" cssClass="form-control" /></td>
										<td class="col-sm-2">
											<form:select path="designMoldType.id" class="form-control">
												<form:option value="" label="- Select Type -" />
												<form:options items="${designMoldTypeMap}" />
											</form:select>
										</td>
										<td class="col-sm-2"><form:input path="drawerNo" cssClass="form-control" /></td>
										<td class="col-sm-2"><form:input path="rackNo" cssClass="form-control"/></td>
										<td class="col-sm-2"><form:input path="mouldQty" cssClass="form-control"/></td>
										<td class="col-sm-2">
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="submit" id="saveBtnId" name="saveBtnId"  value="Save" class="btn btn-primary"/>
											&nbsp;
											<input type="button" id="cancelBtn" value="Cancel" class="btn btn-danger" onclick="javascript:popCancel()"/>	
											<form:input type="hidden" path="id" />
										</td>
									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				
			</div>
	
	
		

	</div>
</div> <!-- ending the main panel -->



<script type="text/javascript">


	$(document).ready(function(e){
		
		
		 $(".designMoldEntForm")
			.validate(
					{
					  rules : {
							'design.mainStyleNo' : {
								required : true,
							},
							'designMoldType.id' : {
								required : true,
							},
							drawerNo : {
								required : true,
							},
							rackNo : {
								required : true,
							},
						},

						highlight : function(element) {
							$(element).closest(
									'.form-group')
									.removeClass(
											'has-success')
									.addClass('has-error');
						},
						unhighlight : function(element) {
							$(element)
									.closest('.form-group')
									.removeClass(
											'has-error')
									.addClass('has-success');
						},
						
						

					});
		
		
		$("#design\\.mainStyleNo").autocomplete({
			source : "/jewels/manufacturing/masters/styleNo/list.html",
			minLength : 2
		});
		
		popDesignMoldTable();
		
	});
	
	
	
	
	
	
	$(document)
	.on(
		'submit',
		'form#designMoldEnt',
		 function(e){
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					if(data === '-1'){
						displayInfoMsg(this, null, 'Design Mold Added Succesfully');
						popDesignMoldTable();
						
						$('form#designMoldEnt input[type="text"],textarea, select').val('');
						$('form#designMoldEnt select').val('');
						$('#id').val('');
						
					}else{
						displayInfoMsg(this, null, 'Design Mold updated Succesfully');
						popDesignMoldTable();
						
						$('form#designMoldEnt input[type="text"],textarea, select').val('');
						$('form#designMoldEnt select').val('');
						$('#id').val('');
						
						$('#addDesignMoldDivId').css('display','none');
					}
					
				},
				error : function(data, textStatus, jqXHR){
					alert("ERROR");
				}
					
			})
			
			e.preventDefault();
	
})
	
	
	
	function popDesignMoldTable(){
		
		$("#designMoldTabId")
			.bootstrapTable(
					'refresh',{
							url : "/jewels/manufacturing/masters/designMold/listing.html"
						});
		
		}

	
	
	function editDesignMold(id){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/designMold/edit/"+id+".html",
			type:"GET",
			success:function(data){
				$('#addDesignMoldDivId').html(data);
				$('#addDesignMoldDivId').css('display','block');
				$('#design\\.styleNo').focus();
			}
		});
		
	}
	
	
	function openDesingMoldEntryTab(){
		$('#addDesignMoldDivId').css('display','block');
		$('form#designMoldEnt input[type="text"],textarea, select').val('');
		$('form#designMoldEnt select').val('');
		$('#id').val('');
		//
		$('html, body').animate({
			scrollTop: $("#addDesignMoldDivId").offset().top
		}, 1000);
		$('#design\\.styleNo').focus();
	}

	
	function popCancel(){
		$('#addDesignMoldDivId').css('display','none');
		$('form#designMoldEnt input[type="text"],textarea, select').val('');
		$('form#designMoldEnt select').val('');
		$('#id').val('');
	}
	
	
	function deleteDesignMold(e,id){
		
		displayDlg(
				e,
				'javascript:deleteDesMod('+id+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
		
	}
	
	
	function deleteDesMod(id){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/masters/designMold/delete/'+id+'.html',
			data: 'GET',
			success : function(data){
				popDesignMoldTable();
			}
			
		})
	}
	
	
	
	
</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
