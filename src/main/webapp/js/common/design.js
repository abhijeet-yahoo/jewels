	function popDesign() {
		$("#designId").bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/design/listing.html"
			});
	}

	function popDesignStone() {
		$("#stoneDId").bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/designStone/listing.html?styleNo="
						+ $('#styleNo').val()
						+ "&version="
						+ $('#version').val()
						+"&canViewFlag="+canViewFlag,
			});
	}

	function popDesignComponent() {
		$("#stoneDCId").bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/designComponent/listing.html?styleNo="
						+ $('#styleNo').val()
						+ "&version="
						+ $('#version').val()
						+"&canViewFlag="+canViewFlag,
			});
	}

	function popSCat(vSel) {
		$
				.ajax({
					url : '/jewels/manufacturing/masters/subCategory/list.html?catId='
							+ vSel.value,
					type : 'GET',
					success : function(data) {
						$("#sCatId").html(data);
					}
				});
	}

	function popSShape(vSel) {
		$
				.ajax({
					url : '/jewels/manufacturing/masters/subShape/list.html?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						//$("#sShapeId").html(data);
						$("#subShape\\.id").html(data);
						$("#subShape\\.id").trigger("chosen:updated");
						$("#vSieve").val('');
						$("#vSizeGroupStr").val('');
					}
				});
	}

	function popQuality(vSel) {
		$
				.ajax({
					url : '/jewels/manufacturing/masters/quality/list.html?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#quality\\.id").html(data);
						$("#quality\\.id").trigger("chosen:updated");
					}
				});
	}
	
	function popChart(vSel) {
		
		$
				.ajax({
					url : '/jewels/manufacturing/masters/chart/list.html?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#size").html(data);
						$("#size").trigger("chosen:updated");
					}
				});
	}

	function getSizeMMDetails() {
		
		$
				.ajax({
					url : '/jewels/manufacturing/masters/sizeMM/details.html?shapeId='
							+ $('#shape\\.id').val()
							+ '&size='
							+ $('#size').val(),
					type : 'GET',
					success : function(data) {
			
						//alert(data);
						
						fldData = data.split("_");
				
						$("#vSieve").val(fldData[0]);
						$("#vSizeGroupStr").val(fldData[1]);
						$("#mcarat").val(parseFloat(Number(fldData[2]) * $("#stone").val()).toFixed(3));
					}
				});
	}

	function popSizeGroup(vSel) {
		$
				.ajax({
					url : '/jewels/manufacturing/masters/sizeGroup/list.html?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sizeGroupId").html(data);
					}
				});
	}

	function popSCon(vSel) {
		$
			.ajax({
				url : '/jewels/manufacturing/masters/subConcept/list.html?conId='
						+ vSel.value,
				type : 'GET',
				success : function(data) {
					$("#sConId").html(data);
				}
			});
	}

	function popPSize(vSel) {
		$
			.ajax({
				url : '/jewels/manufacturing/masters/productSize/list.html?catId='
						+ vSel.value,
				type : 'GET',
				success : function(data) {
					$("#pSizeId").html(data);
				}
			});
	}



	function designDtDelete(e, id, dt) {
		doDelete(e, "javascript:deleteDesignDt(" + id + ", " + dt + ");");
	}

	function deleteDesignDt(id, dt) {	
		$("#modalRemove").modal("hide");

		var url = "/jewels/manufacturing/masters/design/delete/" + id;
		if (dt == 1) {
			url = "/jewels/manufacturing/masters/designStone/delete/" + id;
		} else if (dt == 2) {
			url = "/jewels/manufacturing/masters/designComponent/delete/" + id;
		}

		$.ajax({
			url : url + ".html",
			type : 'GET',
			success : function(data, textStatus, jqXHR) {
				if (dt == 0) {
					if (data == 'true') {
						displayMsg(this, null, 'This Design cannot be deactivated, since it is used in Order or PD Order');
					} else {
						popDesign();						
					}
				} else if (dt == 1) {
					popDesignStone();
					updateGrossWt();
					$('form#designStone').trigger("reset");
				} else if (dt == 2) {
					popDesignComponent();
					updateGrossWt();
					$('form#designComponent').trigger("reset");
				}
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}





	function popSCatReport(vSel) {
		$.ajax({
			url : '/jewels/manufacturing/masters/subCategory/reportList.html?catIds='
					+ $('#catId').val(),
			type : 'GET',
			success : function(data) {
				$("#sCatId").html(data);
			}
		});
	}
	
	
	
	
