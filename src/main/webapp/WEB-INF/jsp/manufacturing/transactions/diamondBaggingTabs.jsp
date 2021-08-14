<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<div>
			<label class="col-lg-10 text-left">
				<span style="font-size: 18px;">Diamond Bagging</span>
			</label>
			<div >
				<a>&nbsp;</a>
			</div>
		</div>
	</div>

	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist" id="tablistId">
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Diamond Bagging</a></li>
			<li role="presentation"><a href="#bagDetails" id="bagDetailsId"
				aria-controls="profile" role="tab" data-toggle="tab">Adjustment Details</a></li>
		</ul>
	
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="addDiamondBagging.jsp"%>
			</div>
			<div role="tabpanel" class="tab-pane" id="disableBagDetails">
				<%@include file="diamondBaggingDt.jsp"%>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(e) {
		$('#tablistId').click(function(e) {
			if ($('#orderDt\\.id').val().length > 0) {
				var v = '' + e.target;
				if (v.indexOf("mainScreen") >= 0) {
					popOrderStnDt();
				}

				if (v.indexOf("bagDetails") >= 0) {
					var data = JSON.stringify($("#diaBagDtId").bootstrapTable('getData'));
					var vBagStones = 0.0;
					var vBagCarat = 0.0;

					if ($("#bagDetails").length != 0) {
						$("#" + document.querySelector("#bagDetails").id).attr("id", "disableBagDetails");
					}

					$.each(JSON.parse(data), function(idx, obj) {
						vBagStones += Number(obj.bagStones);
						vBagCarat += Number(obj.bagCarat);

//						if ((!(isNaN(vBagStones))) && (!(isNaN(vBagCarat)))) {
						if (Number(vBagStones) > 0 && Number(vBagCarat) > 0) {
							$('.nav-tabs a[href="#mainScreen"]').tab('show');

							if ($("#disableBagDetails").length != 0) {
								$("#" + document.querySelector("#disableBagDetails").id).attr("id", "bagDetails");
								$('.nav-tabs a[href="#bagDetails"]').tab('show');
							} else {
								$('.nav-tabs a[href="#bagDetails"]').tab('show');
							}

							refreshAdjData();

							return false;
						}
					});
				}
			}
		});
	});
</script>

