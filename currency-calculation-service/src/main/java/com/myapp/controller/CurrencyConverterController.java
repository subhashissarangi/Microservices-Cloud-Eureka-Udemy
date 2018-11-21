package com.myapp.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.myapp.bean.CurrencyConvertionBean;
import com.myapp.exceptions.UserDefindException;
import com.myapp.services.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConverterController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	// @ExceptionHandler(UserDefindException.class)
	// @ResponseStatus
	public CurrencyConvertionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConvertionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConvertionBean.class,
				uriVariables);

		CurrencyConvertionBean response = responseEntity.getBody();
		BigDecimal val = quantity.multiply(response.getConversionMultiple());
		response.setTotalCalculatedAmount(val);
		return new CurrencyConvertionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				response.getTotalCalculatedAmount(), response.getPort());
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyConvertionBean response = proxy.retriveExchangeValue(from, to);
		BigDecimal val = quantity.multiply(response.getConversionMultiple());
		response.setTotalCalculatedAmount(val);
		return new CurrencyConvertionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				response.getTotalCalculatedAmount(), response.getPort());
	}

}
