package com.li.tcc.admin.controller;

import com.li.tcc.common.utils.httpclient.AjaxResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.li.tcc.admin.annotation.Permission;
import com.li.tcc.admin.dto.CompensationDTO;
import com.li.tcc.admin.page.CommonPager;
import com.li.tcc.admin.query.CompensationQuery;
import com.li.tcc.admin.service.ApplicationNameService;
import com.li.tcc.admin.service.CompensationService;
import com.li.tcc.admin.vo.TccCompensationVO;

import java.util.List;

/**
 * transaction log rest controller
 * 
 * @author yuan.li
 */
@RestController
@RequestMapping("/compensate")
public class CompensationController {

	private final CompensationService compensationService;

	private final ApplicationNameService applicationNameService;

	@Value("${compensation.retry.max}")
	private Integer recoverRetryMax;

	@Autowired
	public CompensationController(final CompensationService compensationService,
			final ApplicationNameService applicationNameService) {
		this.compensationService = compensationService;
		this.applicationNameService = applicationNameService;
	}

	@Permission
	@PostMapping(value = "/listPage")
	public AjaxResponse listPage(@RequestBody final CompensationQuery recoverQuery) {
		final CommonPager<TccCompensationVO> pager = compensationService.listByPage(recoverQuery);
		return AjaxResponse.success(pager);
	}

	@PostMapping(value = "/batchRemove")
	@Permission
	public AjaxResponse batchRemove(@RequestBody final CompensationDTO compensationDTO) {
		final Boolean success = compensationService.batchRemove(compensationDTO.getIds(),
				compensationDTO.getApplicationName());
		return AjaxResponse.success(success);

	}

	@PostMapping(value = "/update")
	@Permission
	public AjaxResponse update(@RequestBody final CompensationDTO compensationDTO) {
		if (recoverRetryMax < compensationDTO.getRetry()) {
			return AjaxResponse.error("重试次数超过最大设置，请您重新设置！");
		}
		final Boolean success = compensationService.updateRetry(compensationDTO.getId(), compensationDTO.getRetry(),
				compensationDTO.getApplicationName());
		return AjaxResponse.success(success);

	}

	@PostMapping(value = "/listAppName")
	@Permission
	public AjaxResponse listAppName() {
		final List<String> list = applicationNameService.list();
		return AjaxResponse.success(list);
	}

}
