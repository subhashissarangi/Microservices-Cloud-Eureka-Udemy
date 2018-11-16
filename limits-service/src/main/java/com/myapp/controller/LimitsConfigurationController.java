package com.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.bean.LimitsConfigurationBean;

@RestController
public class LimitsConfigurationController {

	@Autowired
	LimitsConfigurationBean limitsConfigurationBean;

	@GetMapping("/limits")
	public LimitsConfigurationBean getLimits() {
		return new LimitsConfigurationBean(limitsConfigurationBean.getMaximum(), limitsConfigurationBean.getMinimum());
	}

}
