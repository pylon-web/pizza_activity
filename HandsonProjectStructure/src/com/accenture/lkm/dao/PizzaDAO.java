package com.accenture.lkm.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;


import com.accenture.lkm.entity.PizzaEntity;

@RepositoryDefinition(idClass = Integer.class, domainClass = PizzaEntity.class)
@Transactional(value = "txManager")
// name of TransactionManger can be any thing
public interface PizzaDAO {
	@Query(name = "PIZZA.query1_findAllPizzaDetails")
	List<PizzaEntity> findAllPizzaDetails();
}
