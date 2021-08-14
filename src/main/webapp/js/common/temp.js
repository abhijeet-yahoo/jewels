
$(document).ready(function(){
	$('#toInv').autocomplete({
	 	source:"/jewels/manufacturing/transactions/costing/invNoList.html?fieldNm="+$('#fromInvNo').val(),
	 	minLength : 2
	});
	
});
