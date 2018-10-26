/**
 * 
 */
package hiki.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hiki.o2o.entity.ShopCategory;

/**
 * @Hiki msi
 *
 */
public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
