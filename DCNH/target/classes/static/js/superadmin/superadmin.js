/*
 * 显示大创项目
 */
function accountManage(){
	 setAccountMangePage();
}

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

/*
 * 显示账户管理页面
 */
function setAccountMangePage(){
	$.ajax({
		  url: '/getaccountmanagepage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      }
	});
}

/*
 * 添加新账户
 */
function addNewAccount(){
	
	var userName = $("#newUserName").val();
	var school = $("#school").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	var premission=$("#permission").find("option:selected").text();
	if(!check()){
		return;
	}
	
	var param= {
				password:password1,
				userName:userName,
				school :school,
			permission:premission
	}
	$.ajax({
		  url: '/addnewuser',
	      type: 'post',
	      dataType: 'json',
	      data:param,
	      success: function(data){
	    	  if(data.code == 0)
	    	  {
	    		  alert("添加成功");
		    	  clear();
	    	  }
	    	  else
	    	  {
	    		  alert(data.message);
	    	 }
	      }
	});
}

/*
 * 账户信息检查
 */
function check(){
	var userName = $("#newUserName").val();
	var school = $("#school").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	if(school=="" || school==null){
		alert("学校信息不能为空");
		return false;
	}
	
	if(password1=="" || password1==null){
		alert("密码不能为空");
		return false;
	}
	if(password1 != password2){
		alert("密码不一致");
		return false;
	}
	
	if(userName=="" || userName==null){
		alert("用户名不能为空");
		return false;
	}
	return true;
}

function clear(){
	$("#newUserName").val("");
	$("#school").val("");
	$("#password1").val("");
	$("#password2").val("");
}
