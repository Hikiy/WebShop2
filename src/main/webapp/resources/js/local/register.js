/**
 * 
 */
$(function() {
	var registerurl = '/o2o/local/register';
	var frontendurl = '/o2o/frontend/index';
	$('#submit').click(
		function() {
			var formData = new FormData();
			formData.append('username', $('#username').val());
			formData.append('password', $('#password').val());
			var verifyCodeActual = $('#j_captcha').val();
			if (!verifyCodeActual) {
				$toast('请输入验证码！');
				return;
			}
			formData.append('verifyCodeActual', verifyCodeActual);
			$.ajax({
				url : (registerurl),
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					if (data.success) {
						$.toast('注册成功');
						setTimeout(function(){
							window.location.href=frontendurl; 
						},2000);
					} else {
						$.toast('注册失败'+data.errorMsg);
					}
					$('#captcha_img').click();
				}
			});
		})
})