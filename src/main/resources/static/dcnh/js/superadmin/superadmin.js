var list = [
		{
			userName:"name",
			school:"school",
			phoneNumber:"1234",
			email:"12@qq.com"
		}
];

var mainCategoryList=[];//大创项目大类

var showUser;

var showProject;

function accountManage(){
	 setAccountMangePage();
}

/*
 * 获取显示的页面内容 创建账户的页面
 */
function setCreateaccountPage(){
	$.ajax({
		  url: '/dcnh/getcreateaccountpage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#show").html(data);
	    	  accountType = $("#permission").val();
	    	  if(accountType == "校级管理员") {
	    		  getMainCategory();
	    	  } else {
	    		  console.log("不是校级管理员");
	    		  //非校级管理员，需要移除多余的输入项
	    		  $("#category").remove();//删除类别分配数量
	    		  if(accountType == "系统管理员")
	    			  $("#school_div").remove();//删除学校，系统管理员不需要学校
	    	  }
	      }
	});
}

/*
 * 显示账户管理页面
 */
function setAccountMangePage(accountType){
	$.ajax({
		  url: '/dcnh/getaccountmanagepage',
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
	var permission=$("#permission").find("option:selected").text();
	console.log("accountPermission" + permission);
	var param = {
			password:password1,
			userName:userName,
			school :school,
			permission:permission
	};
	if(!check()){
		return;
	}
	if(permission=="校级管理员"){
		for(var index in mainCategoryList){
			console.log(mainCategoryList[index]);
			var key = mainCategoryList[index];
			var val = $('#'+mainCategoryList[index]).val();
			if(val==null || val=='') val="0";
			param[key]=val;
		}
		$.ajax({
			url:'/dcnh/addschooluser',
			type:'post',
			dataType: 'json',
			data:param,
			success:function(data){
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
		return;
	}
	$.ajax({
		  url: '/dcnh/addnewuser',
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
	var accountType = $("#permission").val();
	var userName = $("#newUserName").val();
	var school = $("#school").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	
	if(accountType != "系统管理员" && (school=="" || school==null)){
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

/*
 * 获取指定类型的所有账户的信息
 */
function getAllUserInfo(){
	var permission=$("#permission").find("option:selected").text();
	var param = {
			permission:permission
	}
	$.ajax({
		  url: '/dcnh/getalluserinfo',
	      type: 'get',
	      dataType: 'json',
	      data:param,
	      success: function(data){
	    	  showUser.userList = data;
	      }
	});
}


function showUserBySchool(){
	var schoolName = $("#schoolName").val();
	$.ajax({
		url:'/dcnh/getuserbyschool',
		type:'post',
		dataType:'json',
		data:{
			school:schoolName,
		},
		success:function(data){
			showUser.userList = data;
		}
	});
}

/*
 * 显示用户列表信息
 */
function showUserInfo(param) {
	$.ajax({
		  url: '/dcnh/getshowuserinfopage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data) {
	    	  $("#show").html(data);
	    	  showUser  = new Vue({
	    			el:'#userTable',
	    			data:{
	    				userList:list
	    			},
	    			methods:{
	    				deleteAccount:function(index) {
	    					console.log("index" + index);
	    					modalConfirm("确认删除该账户吗？", function() {
		    						if(index >= showUser.userList.length)
			    						return;
		    						console.log("userList")
			    					console.log(showUser.userList);	 
			    					var userName = showUser.userList[index].userName;
		    						$.ajax({
		    						  url: '/dcnh/deleteuser',
		    					      type: 'get',
		    					      dataType: 'json',
		    					      data:{
		    					    	  userName:userName
		    					      },
		    					      success: function(data) {
		    					    	  modalAlert("删除成功！"); 
		    					    	  getAllUserInfo();
		    					      },
		    					      error: function(data) {
		    					    	  modalAlert("删除失败！");
		    					      }
		    					});
	    					});
	    					    					    					
	    				}
	    			}
	    		});
	    	  console.log("showUserInfo");
	    	  if(param==1)
	    		  getAllUserInfo();
	    	  else
	    		  showUserBySchool();
	      }
	});
}

/*
 * 获取大创项目大类类别
 */
function getMainCategory(){
	$.ajax({
		url:"/dcnh/getallmaincategory",
		type:'get',
		dataType:'json',
		success:function(data){
			mainCategoryList = data;
			var mainCategory = new Vue({
				el:'#category',
				data:{
					mainCategoryList:data
				}
			});
		}
	});
}

//但选择的账户类别改变时，clear创建账户的界面
function permssionSelectChange(select) {
	$("#show").empty();
}