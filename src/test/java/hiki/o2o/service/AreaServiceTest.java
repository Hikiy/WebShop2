/**
 * 
 */
package hiki.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.entity.Area;

/**
 * @Hiki msi
 *
 */
public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	@Test
	public void testGetAreaList(){
		List<Area> areaList=areaService.getAreaList();
		assertEquals("泰山区",areaList.get(0).getAreaName());
	}
}
