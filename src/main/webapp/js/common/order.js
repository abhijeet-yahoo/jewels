	function popOrderMt() {
		$("#orderXyz").bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/masters/order/listing.html"
			});
	}

	function orderDtDelete(e, id, dt) {	
		doDelete(e, "javascript:deleteOrderDt(" + id + ", " + dt + ");");
	}

	function deleteOrderDt(id, dt) {
		$("#modalRemove").modal("hide");
		
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });

		var url = "/jewels/manufacturing/masters/order/delete/" + id;
		if (dt == 1) {
			url = "/jewels/manufacturing/masters/orderDt/delete/" + id;
		} else if (dt == 2) {
			url = "/jewels/manufacturing/masters/orderStnDt/delete/" + id;
		} else if (dt == 3) {
			url = "/jewels/manufacturing/masters/orderCompDt/delete/" + id;
		} else if (dt == 4) {
			url = "/jewels/manufacturing/masters/orderQuality/delete/" + id;
		}

		$.ajax({
			url : url + ".html",
			type : 'GET',
			success : function(data, textStatus, jqXHR) {
				$.unblockUI();

				if (dt == 0) {
	
					if (data == '1') {
						displayMsg(this, null, 'This Order cannot be deactivated, since bag has been generated');
					}else if (data == '2') {
						displayMsg(this, null, 'This Order cannot be deactivated, Diamond inward done');
					}
					else {
						popOrderMt();
					}
				} else if (dt == 1) {
					
					if (data == 'true') {
						displayMsg(this, null, 'This Order cannot be deactivated, since bag has been generated');
					} else {
						$("#divBId").css('display', 'none');
						popOrderDetails();
						$('form#orderDt input[type="text"],texatrea').val('');
					}
				} else if (dt == 2) {
					popOrderStoneDetails();
					$('form#orderStnDt input[type="text"],texatrea').val('');
				} else if (dt == 3) {
					popOrderCompDetails();
					$('form#orderCompDt input[type="text"],texatrea').val('');
				} else if (dt == 4) {
					popOrderQDetails();
					$('form#orderQuality input[type="text"],texatrea').val('');
				}
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}
