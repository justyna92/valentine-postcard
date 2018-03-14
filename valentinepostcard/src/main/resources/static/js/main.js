$(document).ready(function () {
	
	checkFileSizeAndType();
	
});

function checkFileSizeAndType() {
	
	$('input[name=image]').on('change', function() {

		  var fileName = this.files[0].name;
		  var extension = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		  
		  $('#err-type').hide();
		  $('#err-size').hide();
		  if(extension === "jpg" || extension === "jpeg" || extension === "png") {
			  //this.files[0].size gets the size of file.
			  // to 5MB	  
			  if (this.files[0].size >= 5242880) {
				  $('#err-size').show();
				  $('input[type=submit]').prop('disabled',true);
				  $('input[type=submit]').addClass('disabled-btn');
			  } else {
				  $('#err-size').hide();
				  $('input[type=submit]').prop('disabled',false);
				  $('input[type=submit]').removeClass('disabled-btn');
			  } 
		  } else {
			  $('#err-type').show();
			  $('input[type=submit]').prop('disabled',true);
			  $('input[type=submit]').addClass('disabled-btn');
		  }
	});
	
}

function downloadPostcard() {
	
	html2canvas($('#postcard'), { 
		onrendered: function(canvas) {
			var ctx = canvas.getContext('2d');
			var img = canvas.toDataURL("image/png");

		    window.open(img);
		  }
	});
}
