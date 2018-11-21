package com.myapp.repository;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.dao.ExchangeValueDao;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValueDao, Long> {

	ExchangeValueDao findByFromAndTo(@PathParam("from") String from, @PathParam("to") String to);

}
