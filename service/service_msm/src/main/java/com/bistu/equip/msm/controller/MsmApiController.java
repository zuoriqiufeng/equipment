package com.bistu.equip.msm.controller;

import com.bistu.equip.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/21 - 14:37
 */
@RestController
@RequestMapping("api/msm")
public class MsmApiController {
	
	@Autowired
	private MsmService msmService;
	
	
	
	
}
