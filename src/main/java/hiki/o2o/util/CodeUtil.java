/**
 * 
 */
package hiki.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Hiki msi
 *
 */
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verigyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if (verigyCodeActual == null || !verigyCodeActual.equals(verifyCodeExpected)) {
			return false;
		}
		return true;
	}
}
