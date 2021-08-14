
function getRate(vSel) {
	
	if (!vSel) {
		alert("please select the prodLabourType ");

	} else {
		$
				.ajax({
					url : '/jewels/manufacturing/transactions/empPcsProduction/getRate.html?prodLabTypeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						//alert(data);
						$("#rate").val(data);
					}
				});

	}

}