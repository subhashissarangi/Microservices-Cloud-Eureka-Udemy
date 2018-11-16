package com.myapp.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

@RestController

public class CurrencyConverterController {

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/1000")
	@ExceptionHandler(UserDefindException.class)
	@ResponseStatus
	public CurrencyConvertionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConvertionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currecny-exchange/from/{from}/to/{to}", CurrencyConvertionBean.class,
				uriVariables);
		CurrencyConvertionBean response = responseEntity.getBody();
		return new CurrencyConvertionBean(response.getId(), from, to, response.getConversionMultiple(),
				response.getQuantity(), quantity.multiply(response.getTotalCalculatedAmount()), response.getPort());
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

}
