

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
	    	  getMainCategory();
	      }
	});
}

/*
 * 显示账户管理页面
 */
function setAccountMangePage(){
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
	var permission=$("#accountpermission").find("option:selected").text();
	var param = {
			password:password1,
			userName:userName,
			school :school,
			permission:permission
	};;
	/* param.put("password",password1);
	 param.put("userName",userName);
	 param.put("school",school);
	 param.put("permission",permission);
	 /*
	  * {
			
			:userName,
			school :school,
			permission:permission
	};
	  */
	
	if(!check()){
		return;
	}
	if(permission=="校级管理员"){
	/*	var paper = $("#paper").val();
		var project = $("#project").val();
		var startup = $("#startup").val();
		var creative = $("#creative").val();
		if(paper=="") paper="0";
		if(project=="") project="0";
		if(startup=="") startup="0";
		if(creative=="")creative="0";
		console.log(paper+" "+project+" "+startup+" "+creative);
		var param1 = {
				userName:userName,
				password:password1,
				school :school,
				permission:permission,
				paper:paper,
				project:project,
				startup:startup,
				creative:creative
		}
		*/
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
function showUserInfo(param){
	$.ajax({
		  url: '/dcnh/getshowuserinfopage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#show").html(data);
	    	  showUser  = new Vue({
	    			el:'#userTable',
	    			data:{
	    				userList:list
	    			},
	    			methods:{
	    				deleteAccount:function(index){
	    					if(index >= this.userList[index].length){
	    						return;
	    					}
	    					//this.rooms[index].roomId
	    					var userName = this.userList[index].userName;//thisuserList[index].userName;
	    					
	    					$.ajax({
	    						  url: '/dcnh/deleteuser',
	    					      type: 'get',
	    					      dataType: 'json',
	    					      data:{
	    					    	  userName:userName
	    					      },
	    					      success: function(data){
	    					    	 if(data.code==0)
	    					    	 {
	    					    		 alert(data.message);
	    					    		/* if (index !== -1) {
	    					    		   this.items.splice(index, 1)
	    					    		 }*/
	    					    	/*	 var newArray = new Array();
	    					    		 for(var i in this.userList){
	    					    			 if(i!=index){
	    					    				 newArray.add(userList[i]);
	    					    			 }
	    					    		 }
	    					    		 this.userList = newArray;
	    					    		 alert("heheh");*/
	    					    		 showUserInfo();
	    					    	 }
	    					    	 else{
	    					    		 alert(data.message);
	    					    	 }
	    					    	 
	    					      }
	    					});
	    				}
	    			}
	    		});
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