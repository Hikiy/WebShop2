/**
 * 
 */
$(function() {
	var logouturl = '/o2o/local/logout';
	var frontendurl = '/o2o/frontend/index';
	logout();
	 function logout() {
	    	$.ajax({
				url : (logouturl),
				type : 'get',
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					if (data.success) {
						$.toast('退出成功！');
						setTimeout(function(){
							window.location.href=frontendurl; 
						},2000);
					} else {
						$.toast(data.errMsg);
					}
				}
			});
	    }
})