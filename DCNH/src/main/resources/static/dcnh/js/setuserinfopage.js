function setUserinfoPage(){
	$.ajax({
		  url: '/dcnh/getuserinfopage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      }
	});
}