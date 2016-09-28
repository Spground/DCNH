function setProjectManageInfo(){
	
}

function projectGrouping(){
	
}

function getProjectGroupingResult(){
	
}

/*
 * 显示项目管理页面
 */
function showProjectManagepage(){
	
	$.ajax({
		  url: '/getprojectmanagepage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      }
	      });
}