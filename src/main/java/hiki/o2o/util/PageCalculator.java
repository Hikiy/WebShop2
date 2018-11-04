/**
 * 
 */
package hiki.o2o.util;

/**
 * @Hiki msi
 *
 */
public class PageCalculator {
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		// 例：如果是第一页，也即是pageIndex=1，页面大小为5，则数据库rowIndex从0开始到4五条
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
