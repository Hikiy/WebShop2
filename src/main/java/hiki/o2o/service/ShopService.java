/**
 * 
 */
package hiki.o2o.service;

import java.io.File;

import hiki.o2o.dto.ShopExecution;
import hiki.o2o.entity.Shop;

/**
 * @Hiki msi
 *
 */
public interface ShopService {
	public ShopExecution addShop(Shop shop,File shopImg);
}
