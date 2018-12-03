/**
 * 
 */
package hiki.o2o.service;

import java.io.IOException;
import java.util.List;

import hiki.o2o.entity.HeadLine;

/**
 * @Hiki msi
 *
 */
public interface HeadLineService {
	/**
	 * 根据条件查询头条列表
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition)throws IOException;
}
