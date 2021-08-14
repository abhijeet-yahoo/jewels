 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 <%@ include file="/WEB-INF/layout/taglib.jsp"%>

  <div class="panel-body">
  
  
  <div id="QQIdTable">
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div id="toolbarOQ">
					<a class="btn btn-info" style="font-size: 15px" type="button" href="javascript:goToQuotQuality();"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Quot Quality</a>
				</div>
				<div>
					<table id="quotQId" data-toggle="table" data-toolbar="#toolbarQQuality"
						data-pagination="true" data-side-pagination="server"
						data-click-to-select="true" data-select-item-name="radioName"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350">
						<thead>
							<tr class="btn-primary">
								<th data-field="state" data-radio="true"></th>
								<th data-field="stoneType" data-align="left" data-sortable="true">Stone Type</th>
								<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
								<th data-field="quality" data-sortable="true">Quality</th>
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>

		<div id="rowQQId" style="display: none;">
			<div class="form-group">
				<form:form commandName="quotQuality" id="quottQuality"
					action="/jewels/manufacturing/transactions/quotQuality/add.html"
					cssClass="form-horizontal quottQualityForm">
	
					<table class="line-items editable table table-bordered">
						<thead class="panel-heading">
							<tr class="panel-heading">
								<th>Stone Type</th>
								<th>Shape</th>
								<th>Quality</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<form:select path="stoneType.id" id="quotStoneTypeId" class="form-control" >
										<form:option value="" label="- Select StoneType -" />
										<form:options items="${stoneTypeMap}" />
									</form:select>
								</td>
								<td>
									<form:select path="shape.id" id="quotShapeId" class="form-control" onchange="javascript:popQuotQualitys();">
										<form:option value="" label="- Select Shape -" />
										<form:options items="${shapeMap}" />
									</form:select>
								</td>
								<td>
									<div id="quotQtilyId">
										<form:select path="quality.id" class="form-control">
											<form:option value="" label="- Select Quality -" />
											<form:options items="${qualityMap}" />
										</form:select>
									</div>
								</td>
							</tr>
							
								<tr>
											<td><input type="submit" id="saveId" value="Save"
												class="btn btn-primary" />
											<td><input type="submit" id="saveAllId" value="Save & Update All Style"
												class="btn btn-primary" />	
												<form:input type="hidden" path="id" id="quotQualityPkId"/>
												<input type="hidden" id="pOQInvNo" name="pOQInvNo" />
												<input type="hidden" id="updateFlg" name="updateFlg" />
												
										</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
	  
  
  
 </div>
 
 
 <script type="text/javascript">
	$(document).ready(function(e) {
		
		$('#qQuality').on('click', function() {
			popQuotQDetails();
		});

		$(".quottQualityForm").validate(
		{
			rules : {
				'shape.id' : {
					required : true,
				}
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

	});

	

 	var whichBtn;
/* 	$('#quottQuality input[type="submit"]').click(function(e) {
		
		
		
	}); 
 */
 
	var whichBtn;
	$('#quottQuality input[type="submit"]').click(function(e) {
		whichBtn = $(this).attr("id");
		
		
	});
 
	$(document).on('submit', 'form#quottQuality', function(e) {
		
		if (whichBtn === "saveAllQuotDtId") {
		$('#updateFlg').val('true');
		}else{
			$('#updateFlg').val('false');
		}
		
		$('#pOQInvNo').val($('#invNo').val());

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		
		
		
		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				$('#quotQualityPkId').val('');
				popQuotQDetails();
				$('form#quottQuality input[type="text"],texatrea').val('');
				$('form#quottQuality select').val('');
				$('#quotQualityPkId').val('');
				$("#rowQQId").css('display', 'none');

				$('#saveAllQuotDtId').removeAttr('disabled');
			},

			
			
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});
	
</script>