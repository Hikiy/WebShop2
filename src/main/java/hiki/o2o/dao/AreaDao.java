/**
 * 
 */
package hiki.o2o.dao;

import java.util.List;

import hiki.o2o.entity.Area;

/**
 * @Hiki msi
 *	
 */
public interface AreaDao {
	/*
	 * 列出区域列表
	 * @return areaList
	 */
	List<Area> queryArea();
}
