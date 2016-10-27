var success=0;
var fail = 1;

var resultProjectList = [{
	title:"title",
	school:"school",
	participators:"participators"
}];

function getResultSettingPage(){
	$.ajax({
		  url: '/dcnh/resultsetting',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      }
	});
}

function getResultpage(){
	$.ajax({
		  url: '/dcnh/resultpage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#yulandoc").html(data);
	    	  getResultProjectMap();
	      }
	});
}

/*
 * 设置导出比例
 */
function setResultInfo(){
	var lunwen = $("#lunwen").val();
	var chuangxixiangmu = $("#chuangxixiangmu").val();
	var chuangyexiangmu = $("#chuangyexiangmu").val();
	var zhanshixiangmu = $("#zhanshixiangmu").val();
	var chuangyixiangmu = $("#chuangyixiangmu").val();
	$.ajax({
		url:"/dcnh/resultsettinginfo",
		type:'get',
		dataType:'json',
		data:{
				"学术论文":lunwen,
				"展示项目":zhanshixiangmu,
				"创业直通车项目":chuangyexiangmu,
				"创意作品":chuangyixiangmu
		},
		success:function(data){
			alert(data.message);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){  
	        alert(XMLHttpRequest.readyState+ " ##### " + XMLHttpRequest.status+ " #### " + XMLHttpRequest.responseText+" ## "+errorThrown);  
	    }  
	});
	
}

/*
 * 导出结果预览
 */
function getResultProjectMap(){
	$.ajax({
		url:"/dcnh/resultprojectlist",
		type:"post",
		dataType:'json',
		success:function(data){
		//	alert("data : "+data);
			var lunwen = new Vue({
				el:'#lunwenTable',
				data:{
					projectList:resultProjectList
				}
			});
			var zhanshi = new Vue({
				el:'#zhanshiTable',
				data:{
					projectList:resultProjectList
				}
			});
			var chuangye = new Vue({
				el:'#chuangyeTable',
				data:{
					projectList:resultProjectList
				}
			});
			var chuangyi = new Vue({
				el:'#chuangyiTable',
				data:{
					projectList:resultProjectList
				}
			});
			for(var key in data){
				//console.log("key "+key+" value "+data[key]);
				if(key=="学术论文"){
				/*	alert("hahaha");
					var tem = data[key];
					for(var index in tem){
						console.log(tem[index]);
					}*/
					lunwen.projectList = data[key];
				}
				else if(key=="展示项目"){
					zhanshi.projectList = data[key];
				}
				else if(key=="创业直通车项目"){
					chuangye.projectList = data[key];
				}
				else if(key=="创意作品"){
					chuangyi.projectList = data[key];
				}
			}
		}
	});
} 

function getdocument(){
	var htmlContent = $("#yulandoc").html();
	if(htmlContent==null){
		getResultProjectMap();
		htmlContent = $("#yulandoc").html();
	}
	$.ajax({
		url:"/dcnh/sendhtmlcontent",
		type:"post",
		dataType:'json',
		data:{
			htmlContent:htmlContent
		},
		success:function(data){
			 //alert(data.message);
			if(data.code==success){
			//	alert("filename "+data.message);
			//	$.get();
				$("#getdocument").attr("href","/getresultdocument/"+data.message);
				$("#getdocument").click();
			} 
			else{
				alert("message "+data.message+" code "+data.code);
			}
			
		}
	});
}