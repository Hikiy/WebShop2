/**
 * 
 */
package hiki.o2o.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hiki.o2o.dao.HeadLineDao;
import hiki.o2o.entity.HeadLine;
import hiki.o2o.service.HeadLineService;

/**
 * @Hiki msi
 *
 */
@Service
public class HeadLineServiceImpl implements HeadLineService{
	@Autowired
	private HeadLineDao headLineDao;

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		return headLineDao.queryHeadLine(headLineCondition);
	}
	
}
