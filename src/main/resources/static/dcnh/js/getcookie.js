 function getCookie(cname)
{
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) 
	{
		 var c = ca[i].trim();
		if (c.indexOf(name)==0){
			var result = c.substring(name.length,c.length);
			result = result.replace(/\"/g,"");
			return result;
		}
	}
	return "";
}