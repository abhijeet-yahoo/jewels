 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/clientStampAddEdit.jsp"%>


<div class="panel panel-primary">

<div class="panel-body">
	
<div class="row">
	<label class="col-sm-1">Client </label>
	<input type="text" id="partyNm" name="partyNm" class="col-sm-2 " onchange="javascript:popClientStampList()" />
</div>


<div id="clientStampToolBar">
	
	<c:choose>
		<c:when test="${canAdd}">
			<a class="btn btn-info" style="font-size: 15px" type="button"
				onclick="javascript:popClientStampAdd()"><span
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
		<table id="clientStamptableId" data-toggle="table" 
		data-toolbar="#clientStampToolBar" data-pagination="true" data-show-columns="true"
		data-side-pagination="server" data-striped="true"
		data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
		data-height="450" data-show-export="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="fromCts" data-align="left" data-sortable="true">From Cts</th>
					<th data-field="toCts" data-align="left" data-sortable="true">To Cts</th>
					<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
					<th data-field="stampNm" data-align="left" data-sortable="true">Stamp Name</th>
					<th data-field="party" data-align="left" data-sortable="true">Party</th>
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
	
	$('.clientStampForm').validate(
			{

				rules : {
					'fromCts' : {
						required : true,
					},
					'toCts' : {
						required : true,
					},
					'stampNm' : {
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


	function popClientStampAdd() {
		if (!!$('#partyNm').val()) {
			$('#myClientStamp').modal('show');
			$('#clientStampFormId input[type="number"]').val('0');
			$('#clientStampFormId input[type="text"]').val('');
			$('#clientStampFormId').find('select').val('');
			$('#id').val('');
		
		} else {
			displayMsg(this, null, "Please Enter Client ");
		}

	}
	
	
function popClientStampList() {
	if (!!$('#partyNm').val()) {
		$("#clientStamptableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/clientStamp/listing.html?partyNm="
									+ $('#partyNm').val()

						});
		
	} else {
		displayMsg(this, null, "Please Enter Client ");
	}

}

var tempdata = ""
	function clientStampSave(){
		if($('.clientStampForm').valid()){
			
			$('#vPartyNm').val($('#partyNm').val());

			var postData = $('#clientStampFormId').serializeArray();
			var formURL = $('#clientStampFormId').attr("action");
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					if(data ==='1'){
						displayInfoMsg(this, null, ' Save Successfully ');

						$('#myClientStamp').modal('hide');
						$('#clientStampFormId input[type="number"]').val('0.0');
						$('#clientStampFormId input[type="text"]').val('');
						$('#clientStampFormId').find('select').val('');
						popClientStampList();
						
					}else{
						displayMsg(this, null, data);
					}


				},
				error : function(jqXHR, textStatus, errorThrown) {

				}
			});
			
		}

	

	}


	function popClientStampEdit(e, id){
		
		$.ajax({
			url : "/jewels/manufacturing/masters/clientStamp/edit/" + id
					+ ".html",
			type : "GET",
			dataType : 'JSON',
			success : function(data) {
				$('#myClientStamp').modal('show');

				var tempId = ""
				$.each(data, function(key, value) {
					$('#clientStampFormId').find('input[name="' + key + '"]').val(value);
					$('#clientRefFormId').find('select[name="' + key + '"]').val(value);
				});

			},
		});
		
	}
	
	
	function confirmClientStampDelete(e,id){
		displayDlg(e, 'javascript:popClientStampDelete('+id+');','Client Stamp', 'Do You Want To Delete', 'Continue');
	}
	
	
	function popClientStampDelete(e,id) {
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/masters/clientStamp/delete/" + id
					+ ".html",
			type : "GET",
			success : function(data) {
				if (data === '-1') {
					displayMsg(this, null, ' Error Contact Admin  ');

				}else{
					displayInfoMsg(this, null, ' Delete Successfully ');
					popClientStampList();
				}
			}
				
			})
		
	}
	


</script>




<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

