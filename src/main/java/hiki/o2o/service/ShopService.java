/**
 * 
 */
package hiki.o2o.service;

import java.io.InputStream;

import hiki.o2o.dto.ShopExecution;
import hiki.o2o.entity.Shop;
import hiki.o2o.exception.ShopOperationException;

/**
 * @Hiki msi
 *
 */
public interface ShopService {
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws ShopOperationException;
}
