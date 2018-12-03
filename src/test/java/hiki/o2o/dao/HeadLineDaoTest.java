/**
 * 
 */
package hiki.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hiki.o2o.BaseTest;
import hiki.o2o.entity.HeadLine;

/**
 * @Hiki msi
 *
 */
public class HeadLineDaoTest extends BaseTest{
	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	public void testQueryHeadLine(){
		List<HeadLine> headLineList=headLineDao.queryHeadLine(new HeadLine());
		assertEquals(3,headLineList.size());
		HeadLine headLineCondition1=new HeadLine();
		headLineCondition1.setLineName("1");
		List<HeadLine> headLineList1=headLineDao.queryHeadLine(headLineCondition1);
		assertEquals(1,headLineList1.size());
		HeadLine headLineCondition2=new HeadLine();
		headLineCondition2.setEnbleStatus(2);
		List<HeadLine> headLineList2=headLineDao.queryHeadLine(headLineCondition2);
		assertEquals(1,headLineList2.size());
	}
}
