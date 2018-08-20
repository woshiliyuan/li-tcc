package com.li.tcc.admin.service.compensate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.li.tcc.admin.page.CommonPager;
import com.li.tcc.admin.page.PageParameter;
import com.li.tcc.admin.query.CompensationQuery;
import com.li.tcc.admin.service.CompensationService;
import com.li.tcc.admin.vo.TccCompensationVO;

/**
 * 
 * @author yuan.li
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MongoCompensationServiceImplTest {

	@Autowired
	private CompensationService compensationService;

	@Test
	public void listByPage() throws Exception {

		CompensationQuery query = new CompensationQuery();

		query.setApplicationName("alipay-service");

		PageParameter pageParameter = new PageParameter(1, 5);

		query.setPageParameter(pageParameter);

		final CommonPager<TccCompensationVO> voCommonPager = compensationService.listByPage(query);
		Assert.assertNotNull(voCommonPager.getDataList());
	}
}