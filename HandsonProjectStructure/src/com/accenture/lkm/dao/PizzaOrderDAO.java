package com.accenture.lkm.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.lkm.entity.PizzaEntity;
import com.accenture.lkm.entity.PizzaOrderEntity;

@RepositoryDefinition(idClass = Integer.class, domainClass = PizzaOrderEntity.class)
@Transactional(value = "txManager")
public interface PizzaOrderDAO {

//	@Query(name = "DummyQuery")
	PizzaOrderEntity save(PizzaOrderEntity entity);
}
