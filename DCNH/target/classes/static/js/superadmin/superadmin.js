/*
 * 显示大创项目
 */
function showProject(){
	
}

/*
 * 添加新账户
 */
function addNewAccount(){
	$.ajax({
		  url: '/getcreateaccountpage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#show").html(data);
	      }
	});
}

/*
	查看账户
 */
function showAccount(){
	
}