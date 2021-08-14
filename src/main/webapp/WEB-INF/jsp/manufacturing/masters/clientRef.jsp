 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/clientRefAddEdit.jsp"%>




<div class="panel panel-primary">

<div class="panel-body">
	
<div class="row">
	<label class="col-sm-1">Client </label>
	<input type="text" id="partyNm" name="partyNm" class="col-sm-2 " onchange="javascript:popClientRefList()" />
</div>


<div id="clientRefToolBar">
	
	<c:choose>
		<c:when test="${canAdd}">
			<a class="btn btn-info" style="font-size: 15px" type="button"
				onclick="javascript:popClientRefAdd()"><span
				class="glyphicon glyphicon-plus"></span>&nbsp;Add
			</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:displayMsg(event, this)" href="javascript:void(0)">
				<span class="glyphicon glyphicon-plus"></span>&nbsp;Add</a>
		</c:otherwise>
	</c:choose>

</div>
<div>
		<table id="clientReftableId" data-toggle="table" 
		data-toolbar="#clientRefToolBar" data-pagination="true" data-show-columns="true"
		data-side-pagination="server" data-striped="true"
		data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
		data-height="450" data-show-export="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="styleNo" data-align="left" data-sortable="true">Style No.</th>
					<th data-field="purityNm" data-align="left" data-sortable="true">Purity</th>
					<th data-field="y" data-align="left" data-sortable="true">Y</th>
					<th data-field="w" data-align="left" data-sortable="true">W</th>
					<th data-field="p" data-align="left" data-sortable="true">P</th>
					<th data-field="wy" data-align="left" data-sortable="true">WY</th>
					<th data-field="wp" data-align="left" data-sortable="true">WP</th>
					<th data-field="yw" data-align="left" data-sortable="true">YW</th>
					<th data-field="yp" data-align="left" data-sortable="true">YP</th>
					<th data-field="py" data-align="left" data-sortable="true">PY</th>
					<th data-field="pw" data-align="left" data-sortable="true">PW</th>
					<th data-field="tt" data-align="left" data-sortable="true">TT</th>
					<th data-field="finishWt" data-sortable="true">Finish Wt.</th>
					<th data-field="caratWt"  data-sortable="true">Carat Wt.</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>



</div>


</div>

<script type="text/javascript">
$(document).ready(function(){
	
	$('#partyNm').autocomplete({
		source: "/jewels/manufacturing/masters/party/autoFillList.html",
		minLength : 2
	})
	
	
	
	$("#design\\.mainStyleNo")
	.autocomplete(
			{
				source: "/jewels/manufacturing/masters/design/autoFillListForClientRef.html",
				minLength : 2
			});
	
	
	
	$('.clientRefForm').validate(
								{

									rules : {
										'design.mainStyleNo' : {
											required : true,
										},
										'purity.id' : {
											required : true,
										},

									},
									highlight : function(element) {
										$(element).closest('.form-group')
												.removeClass('has-success')
												.addClass('has-error');
									},
									unhighlight : function(element) {
										$(element).closest('.form-group')
												.removeClass('has-error')
												.addClass('has-success');
									},
									messages : {
										
									}

								});

					})

	function popClientRefAdd() {
		if (!!$('#partyNm').val()) {
			$('#myClientRef').modal('show');
			$('#clientRefFormId input[type="number"]').val('0');
			$('#clientRefFormId input[type="text"]').val('');
			$('#clientRefFormId').find('select').val('');
			$('#id').val('');
			/* $('#vPartyNm').val($('#partyNm').val()); */

		} else {
			displayMsg(this, null, "Please Enter Client ");
		}

	}

	function popClientRefList() {
		if (!!$('#partyNm').val()) {
			$("#clientReftableId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/clientRef/listing.html?partyNm="
										+ $('#partyNm').val()

							});
		} else {
			displayMsg(this, null, "Please Enter Client ");
		}

	}

	var tempdata = ""
	function clientRefSave() {
		if($('.clientRefForm').valid()){
			
			$('#vPartyNm').val($('#partyNm').val());

			var postData = $('#clientRefFormId').serializeArray();
			var formURL = $('#clientRefFormId').attr("action");
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					if(data ==='1'){
						displayInfoMsg(this, null, ' Save Successfully ');

						$('#myClientRef').modal('hide');
						$('#clientRefFormId input[type="number"]').val('0.0');
						$('#clientRefFormId input[type="text"]').val('');
						$('#clientRefFormId').find('select').val('');
						popClientRefList();
						
					}else{
						displayMsg(this, null, data);
					}

				/* 	if (data === '-1') {
						displayMsg(this, null, ' Error Contact Admin  ');

					} else if (data === '-2') {
						displayMsg(this, null, ' Duplicate Entry ');
					} else if (data === 'Party Not Valid') {
						displayMsg(this, null, ' Party Not Valid ');
					} 
					else {
						
					} */

				},
				error : function(jqXHR, textStatus, errorThrown) {

				}
			});
			
		}

	

	}

	function popClientRefEdit(e, id) {

		$.ajax({
			url : "/jewels/manufacturing/masters/clientRef/edit/" + id
					+ ".html",
			type : "GET",
			dataType : 'JSON',
			success : function(data) {

				console.log('data ' + JSON.stringify(data));

				$('#myClientRef').modal('show');

				var tempId = ""
				$.each(data, function(key, value) {
					$('#clientRefFormId').find('input[name="' + key + '"]')
							.val(value);
					$('#clientRefFormId').find('select[name="' + key + '"]')
							.val(value);
					if (key === 'design') {
						$('#design\\.mainStyleNo').val(value);
					}

					if (key === 'purity') {
						$('#purity\\.id').val(value);

					}

				});

			},
		/* async:false, */
		});

	}
	
	
	
	function confirmClientRefDelete(e,id){
		displayDlg(e, 'javascript:popClientRefDelete('+id+');','Client Ref', 'Do You Want To Delete', 'Continue');
	}
	
	
	function popClientRefDelete(id) {
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/masters/clientRef/delete/" + id
					+ ".html",
			type : "GET",
			success : function(data) {
				if (data === '-1') {
					displayMsg(this, null, ' Error Contact Admin  ');

				}else{
					displayInfoMsg(this, null, ' Delete Successfully ');
					popClientRefList();
				}
			}
				
			})
		
	}
</script>



<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />


