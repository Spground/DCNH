function setUserinfoPage(){
	$.ajax({
		  url: '/getuserinfopage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      }
	});
}