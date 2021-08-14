 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/handlingRateAddEdit.jsp"%>


<div class="panel panel-primary">

<div class="panel-body">
	
<div class="row">
	<label class="col-sm-1">Client </label>
	<input type="text" id="partyNm" name="partyNm" class="col-sm-2 " onchange="javascript:popHandlingMasterRateList()" />
</div>


<div id="handlingMasterFlToolBar">
	
	<c:choose>
		<c:when test="${canAdd}">
			<a class="btn btn-info" style="font-size: 15px" type="button"
				onclick="javascript:popHandlingRateAdd()"><span
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
		<table id="handlingMasterFltableId" data-toggle="table" 
		data-toolbar="#handlingMasterFlToolBar" data-pagination="true" 
		data-side-pagination="server" data-striped="true"
		data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
		data-height="450">
			<thead>
				<tr class="btn-primary">
					<th data-field="fromDiaRate" data-align="left" data-sortable="true">From Dia Rate</th>
					<th data-field="toDiaRate" data-align="left" data-sortable="true">To Dia Rate</th>
					<th data-field="rate" data-align="left" data-sortable="true">Handling Rate</th>
					<th data-field="percentage" data-align="left" data-sortable="true">Percentage</th>
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
		minLength : 2,
		
		
        change: function (event, ui) {
            if (ui.item == null || ui.item == undefined) {
                $("#partyNm").val("");
               
            }
            
        }
		
	});
	
	
	$('.handlingMasterFlForm').validate(
			{

				rules : {
					'fromDiaRate' : {
						required : true,
					},
					'toDiaRate' : {
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


	function popHandlingRateAdd() {
		if (!!$('#partyNm').val()) {
			$('#myHandlingMasterFl').modal('show');
			$('#handlingMasterFlFormId input[type="number"]').val('0');
			$('#handlingMasterFlFormId input[type="text"]').val('0.0');
			$('#handlingMasterFlFormId').find('select').val('');
			$('#id').val('');
		
		} else {
			displayMsg(this, null, "Please Enter Client ");
		}

	}
	
	
function popHandlingMasterRateList() {
	if (!!$('#partyNm').val()) {
		$("#handlingMasterFltableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/handlingMasterFl/listing.html?partyNm="
									+ $('#partyNm').val()

						});
		
	} else {
		displayMsg(this, null, "Please Enter Client ");
	}

}

var tempdata = ""
	function handlingRateSave(){
		if($('.handlingMasterFlForm').valid()){
			
			$('#vPartyNm').val($('#partyNm').val());

			var postData = $('#handlingMasterFlFormId').serializeArray();
			var formURL = $('#handlingMasterFlFormId').attr("action");
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
					
					if(data ==='1'){
						displayInfoMsg(this, null, ' Save Successfully ');

						$('#myHandlingMasterFl').modal('hide');
						$('#handlingMasterFlFormId input[type="number"]').val('0.0');
						$('#handlingMasterFlFormId input[type="text"]').val('');
						$('#handlingMasterFlFormId').find('select').val('');
						popHandlingMasterRateList();
						
					}else{
						displayMsg(this, null, data);
					}


				},
				error : function(jqXHR, textStatus, errorThrown) {

				}
			});
			
		}

	

	}


	function popHandlingFlEdit(e, id){
		
		$.ajax({
			url : "/jewels/manufacturing/masters/handlingMasterFl/edit/" + id
					+ ".html",
			type : "GET",
			dataType : 'JSON',
			success : function(data) {
				$('#myHandlingMasterFl').modal('show');

				var tempId = ""
				$.each(data, function(key, value) {
					$('#handlingMasterFlFormId').find('input[name="' + key + '"]').val(value);
					$('#handlingMasterFlFormId').find('select[name="' + key + '"]').val(value);
				});

			},
		});
		
	}
	
	
	function confirmHandlingFlDelete(e,id){
		displayDlg(e, 'javascript:popHandlingRateDelete('+id+');','Handling Rate', 'Do You Want To Delete', 'Continue');
	}
	
	
	function popHandlingRateDelete(id) {
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/masters/handlingMasterFl/delete/"+id+".html",
			type : "GET",
			success : function(data) {
				if (data === '-1') {
					displayMsg(this, null, ' Error Contact Admin  ');

				}else{
					displayInfoMsg(this, null, ' Delete Successfully ');
					popHandlingMasterRateList();
				}
			}
				
			})
		
	}
	


</script>




<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

