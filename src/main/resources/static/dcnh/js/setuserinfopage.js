function setUserinfoPage(){
	$.ajax({
		  url: '/dcnh/getuserinfopage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	    	  getUserInfo();
	      }
	});
}

//获取用户信息数据
function getUserInfo() {
	$.ajax({
		  url: '/dcnh/getuserinfo',
	      type: 'get',
	      dataType: 'json',
	      success: function(data){
	    	 console.log(data);
	    	 $("#fullname").val(data.userName);
	    	 $("#email").val(data.email);
	    	 $("#tel").val(data.phoneNumber);
	    	 $("#school").val(data.school);
	    	 $("#accountType").val(data.permission);
	      }
	});
}