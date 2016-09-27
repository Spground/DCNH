function setProjectManageInfo(){
	var projectGroupCount = $("#projectGroupCount").val();
	var judgeGroupCount = $("#judgeGroupCount").val();
	alert(projectGroupCount+" ### "+judgeGroupCount);
	var param = {
			projectGroupCount:projectGroupCount,
			judgeGroupCount:judgeGroupCount
	};
	
	$.ajax({
		  url: '/setprojectmanageinfo',
	      type: 'post',
	      dataType: 'json',
	      data:param,
	      success: function(data){
	    	  alert(data.message);
	      	}
	      });
	
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