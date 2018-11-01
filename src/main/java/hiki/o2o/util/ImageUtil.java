/**
 * 
 */
package hiki.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * @Hiki msi
 *
 */
public class ImageUtil {

	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	// 格式化时间用的
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	// 用于生成随机数
	private static final Random r = new Random();

	/**
	 * 生成缩略图
	 * 
	 * @param thumbnailInputStream
	 *            原图
	 * @param targetAddr
	 *            存放路径
	 * @return
	 */
	public static String generateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnailInputStream).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/Watermark_slim.png")), 0.75f)
					.outputQuality(0.8f).toFile(dest);
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录
	 * 
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

	}

	/**
	 * 获取输入文件流的扩展名
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟+5位随机数
	 * 
	 * @return 随机文件名
	 */
	public static String getRandomFileName() {
		// 生成五位随机数10000~99999
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}

	/*
	 * 将图片添加水印并压缩
	 */
	public static void main(String[] args) throws IOException {
		Thumbnails.of("E:/java/WebShop/test/picture/zhangyuge.jpg").size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/Watermark_slim.png")), 0.75f)
				.outputQuality(0.8f).toFile("E:/java/WebShop/test/picture/zhangyugenew.jpg");
		;
	}

	/**
	 * storePath是文件的路径还是目录的路径,
	 * 如果是文件路径则删除,
	 * 如果是目录路径则删除该目录下的所有文件和路径
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
