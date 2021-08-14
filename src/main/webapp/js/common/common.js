/**
 * 
 */

jQuery.validator.addMethod("validEmail", function(value, element) {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	return emailReg.test(value);
}, "Please enter a valid email address.");

jQuery.validator.addMethod("greaterThan", function(value, element, param) {

	var abc = Number($(param).val());

	 //alert(abc === NaN);

	if (isNaN(abc)) {
		return (Number(value) > 0);
	} else {
		return (Number($(param).val()) < Number(value));
	}

}, "Check.");


//force numeric input function
$.fn.ForceNumeric = function() {
	return this.each(function() {
		$(this).keydown(function(event) {
			var key = event.charCode || event.keyCode || 0;
			// allow backspace, tab, delete, arrows, numbers and keypad numbers ONLY
			// home, end, period, and numpad decimal
			return (
				key == 8 ||
				key == 9 ||
				key == 46 ||
				key == 110 ||
				key == 190 ||
				(key >= 35 && key <= 40) ||
				(key >= 48 && key <= 57) ||
				(key >= 96 && key <= 105)
			);
		});
	});
};
