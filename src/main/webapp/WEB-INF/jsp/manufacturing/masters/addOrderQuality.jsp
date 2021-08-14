<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<c:set var="option" value="Order Quality" />

<div id="rowOQId">
	<div class="form-group">
		<form:form commandName="orderQuality" id="orderQuality"
			action="/jewels/manufacturing/masters/orderQuality/add.html"
			cssClass="form-horizontal orderQualityForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="panel-heading">
						<th>StoneType</th>
						<th>Shape</th>
						<th>Quality</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<form:select path="stoneType.id" class="form-control" required="true">
								<form:option value="" label="- Select Stone Type -" />
								<form:options items="${stoneTypeMap}" />
							</form:select>
						</td>
						<td>
							<form:select path="shape.id" id="oQShId" class="form-control" onChange="javascript:popOrderQuality();" required="true" >
								<form:option value="" label="- Select Shape -" />
								<form:options items="${shapeMap}" />
							</form:select>
						</td>
						<td>
							
								<form:select path="quality.id" class="form-control" required="true">
									<form:option value="" label="- Select Quality -" />
									<form:options items="${qualityMap}" />
								</form:select>
							
						</td>
					</tr>
					<tr>
							<td><input type="submit" id="saveId" value="Save"
												class="btn btn-primary" />
											<td><input type="submit" id="saveAllId" value="Save & Update All Style"
												class="btn btn-primary" />	 
							<form:input type="hidden" path="id" id="OQltyId" />
							<input type="hidden" id="pOQInvNo" name="pOQInvNo" />
							<input type="hidden" id="updateFlg" name="updateFlg" />
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>



<script type="text/javascript">
var whichBtn;
$('#orderQuality input[type="submit"]').click(function(e) {
	whichBtn = $(this).attr("id");
	
	
});

$(document).ready(function(e){
	
	$('#orderQuality #stoneType\\.id').chosen();
	$('#orderQuality #oQShId').chosen();
	$('#orderQuality #quality\\.id').chosen();
	
	
	$.validator.setDefaults({ ignore: ":hidden:not(select)" });
	
	
	// validation of chosen on change
		if ($("select.form-control").length > 0) {
		    $("select.form-control").each(function() {
		        if ($(this).attr('required') !== undefined) {
		            $(this).on("change", function() {
		                $(this).valid();
		            });
		        }
		    });
		}
		
	
	
});

</script>
