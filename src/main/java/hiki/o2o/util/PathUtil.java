/**
 * 
 */
package hiki.o2o.util;

/**
 * @Hiki msi
 *
 */
public class PathUtil {
	// 获取文件分隔符
	private static String separator = System.getProperty("file.separator");

	/**
	 * 获取项目图片根路径
	 * 
	 * @return
	 */
	public static String getImgBasePath() {
		// 获取系统名称
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "E:/java/WebShop/WebShopImg";
		} else {
			basePath = "/home/hiki/WebShop/WebShopImg";
		}
		basePath = basePath.replace("/", separator);
		return basePath;
	}

	/**
	 * 返回项目图片的子路径
	 * 
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId) {
		String imagePath = "/upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", separator);
	}
}
