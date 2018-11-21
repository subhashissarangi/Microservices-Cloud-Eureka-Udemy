package com.myapp.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.dao.ExchangeValueDao;
import com.myapp.repository.ExchangeValueRepository;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;

	/*
	 * @GetMapping("currecny-exchange/from/{from}/to/{to}") public ExchangeValueDao
	 * retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
	 * ExchangeValueDao exchangeValueDao =
	 * exchangeValueRepository.findByFromAndTo(from, to);
	 * exchangeValueDao.setPort(Integer.parseInt(environment.getProperty(
	 * "local.server.port"))); return exchangeValueDao; }
	 */

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValueDao retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValueDao exchangeValueDao = exchangeValueRepository.findByFromAndTo(from, to);
		exchangeValueDao.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValueDao;
	}

	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

}
