package com.myapp.services.proxy;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myapp.bean.CurrencyConvertionBean;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
//@EnableDiscoveryClient
public interface CurrencyExchangeServiceProxy {

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConvertionBean retriveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);
}


