package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.dao.ExchangeValueDao;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValueDao, Long> {
	
	ExchangeValueDao findByFromAndTo(String from,String to);

}
