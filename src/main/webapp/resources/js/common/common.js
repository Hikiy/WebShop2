/**
 * 
 */

//刷新验证码
function changeVerifyCode(img) {
	img.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
}

//根据name从url传过来的key去获取他的值，比如url结尾?shopId=1，方法参数name填shopId能获取他的值1
function getQueryString(name) {
	//正则表达式，匹配url的参数 取他的值并返回
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}
