/**
 * 
 */
package hiki.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hiki.o2o.entity.HeadLine;

/**
 * @Hiki msi
 *
 */
public interface HeadLineDao {
	/**
	 * 根据条件查询头条（头条名）
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition")HeadLine headLineCondition);
}
