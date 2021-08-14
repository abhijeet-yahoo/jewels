
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<style>
.flex, .flex>div[class*='col-'] {
	display: -webkit-box;
	display: -moz-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	flex: 1 0 auto;
}
</style>

<div class="panel panel-primary">

	<div class="panel-body">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<span style="font-size: 18px;">WIP Style</span>
			</div>

			<div class="panel-body">



				<form:form commandName="wipStyle" id="wipStyleId"
					action="/jewels/manufacturing/masters/wipStyle/add.html"
					cssClass="wipStyleForm">


					<div class="row">

						<div class="col-sm-2"></div>

						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label" for="partyId">Party</label>
								<form:input path="party.partyCode" Class="form-control" onchange="javascript:popStyleList()" />
								<form:errors path="party.partyCode" />

							</div>

						</div>

						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label" for="designId">Design</label>
								<form:input path="design.mainStyleNo" Class="form-control" />
								<form:errors path="design.mainStyleNo" />

							</div>

						</div>

						<div class="col-sm-3" style="padding-top: 25px">
							<div class="form-group">
								<input type="submit" value="Add Style" class="btn btn-primary" id="wipStyleSaveBtnId"
									style="width: 120px;">
									<form:input type="hidden" path="id" />
							</div>
						</div>

					</div>
			</div>
			</form:form>




			<div>
				<table id="wipStyletableId" data-toggle="table"
					data-toolbar="#wipStyleToolBar" data-pagination="false"
					data-striped="true" data-search="true" data-height="450">
					<thead>
						<tr class="btn-primary">
							<th data-field="partyNm" data-align="left" data-sortable="true">Party</th>
							<th data-field="StyleNo" data-align="left" data-sortable="true">Style
								No</th>
							<th data-field="action1" data-align="center">Delete</th>
						</tr>
					</thead>
				</table>
			</div>

		</div>
	</div>
</div>




<script type="text/javascript">
	$(document)
			.ready(
					function(e) {

						$('#party\\.partyCode')
								.autocomplete(
										{
											source : "/jewels/manufacturing/masters/party/autoFillList.html",
											minLength : 2,

											   change: function (event, ui) {
								                    if (ui.item == null || ui.item == undefined) {
								                        $("#party\\.partyCode").val("");
								                       
								                    }
								                    
								                }
										})

						$('#design\\.mainStyleNo')
								.autocomplete(
										{
											source : "/jewels/manufacturing/masters/design/autoFillList.html",
											minLength : 2,

											 change: function (event, ui) {
								                    if (ui.item == null || ui.item == undefined) {
								                    	$('#design\\.mainStyleNo').val("");
								                       
								                    }
								                    
								                }
										})

						$(".wipStyleForm").validate(
								{
									rules
									: {
										'party.partyCode' : {
											required :
									true,

										},
										'design.mainStyleNo' : {
											required
									: true,

										}

									},
									highlight :
									function(element) {
										$(element).closest('.form-group')
												.removeClass('has-success')
												.addClass('has-error');
									},
									unhighlight
									:
									function(element) {
										$(element).closest('.form-group')
												.removeClass('has-error')
												.addClass('has-success');
									},
									messages
									: {

									}
								});

					});
					
	
	
	function popStyleList(){
		if(!!$('#party\\.partyCode').val()) {
			$("#wipStyletableId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/wipStyle/listing.html?partyCode="+ $('#party\\.partyCode').val()

					});
		} else {
			displayMsg(this, null, "Please Enter Party ");
		}

}


	function deleteWipStyle(e,id){

		displayDlg(e, 'javascript:popWipStyleDelete('+id+');','WIP Style', 'Do You Want To Delete', 'Continue');
	}
	
	
	function popWipStyleDelete(id) {
		
		$("#modalDialog").modal("hide");
		$.ajax({
			url : "/jewels/manufacturing/masters/wipStyle/delete/" +id+ ".html",
			type : "GET",
			success : function(data) {
				if (data === '-1') {
					displayMsg(this, null, ' Error Contact Admin  ');

				}else{
					/* displayInfoMsg(this, null, ' Delete Successfully '); */
					popStyleList();
				}
			}
				
			})
		
	}


	$(document).on('submit', 'form#wipStyleId', function(e) {
		
		$('#wipStyleSaveBtnId').attr('disabled', 'disabled');
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

				$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
	
			success : function(data, textStatus, jqXHR) {

					if (Number(data) == 1) {
					displayInfoMsg(event, this,'Style Added sucessfully !');
					$('#party\\.partyCode').val();
					$('#design\\.name').val();
					$('#wipStyleSaveBtnId').removeAttr('disabled');
					 popStyleList();

				}else{
					displayMsg(event, this,data);
					$('#wipStyleSaveBtnId').removeAttr('disabled');
				
				}			
				
				
				$('#wipStyleSaveBtnId').removeAttr('disabled');
				
			
				

			},
			error : function(jqXHR, textStatus, errorThrown) {

			}

		});

		e.preventDefault(); //STOP default action
	});
	
					

</script>

<link rel="stylesheet"  href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" 	type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />


