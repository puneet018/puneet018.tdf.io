function createRequestObject(){
	var reqObj = null;
	try{
		reqObj = new XMLHttpRequest();
	}catch(e){
		try{
			reqObj = new ActiveXObject('Msxml2.XMLHTTP');
		}catch(e){
			try{
				reqObj = new ActiveXObject('Microsoft.XMLHTTP')
			}catch(e){
					alert('Please Update Your Browser!!! :(')
				}
		}
	}
	return reqObj;
}