/*
 * 获取显示的页面内容
 */
function setCreateaccountPage(){
	$.ajax({
		  url: '/getcreateaccountpage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#show").html(data);
	      }
	});
}
